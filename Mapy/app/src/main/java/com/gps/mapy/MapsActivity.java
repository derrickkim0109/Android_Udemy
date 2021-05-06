package com.gps.mapy;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        , GoogleMap.OnPolylineClickListener
{


    // ---------------- //
    // Properties
    // ---------------- //

    private GoogleMap mMap;
    private Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.®
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng m1 = new LatLng(-20, -20);
        LatLng m2 = new LatLng(20, -20);
        LatLng m3 = new LatLng(20, 20);
        LatLng m4 = new LatLng(-20, 20);
//        LatLng m5 = new LatLng(0, 0);


        LatLng m6 = new LatLng(0,0);
        LatLng m7 = new LatLng(5,0);
        LatLng m8 = new LatLng(5,5);
        LatLng m9 = new LatLng(0,0);

        marker = mMap.addMarker(new MarkerOptions()
                .position(m1)
                .draggable(true)
                .title("My Marker")
                .snippet("Lat" )

        );
        // List
        List<PatternItem> pattern = Arrays.asList(
                new Dot(),
                new Gap(10),
                new Dash(30)
        );


        // Polyline opt
        PolylineOptions  opt = new PolylineOptions()
                .add(m1)
                .add(m2)
                .add(m3)
                .add(m4)
                ;

        // Polyline
        Polyline p = mMap.addPolyline(opt);
        p.setPattern(pattern);

//-------//-------//-------//-------//-------//-------

        // Circle
        CircleOptions circly = new CircleOptions()
                .center(m1)
                .radius(10000); // in meters


        // get back the mutable circle
        Circle circle = mMap.addCircle(circly);
        circle.setStrokeColor(Color.RED);


        // ArrayList
        ArrayList<LatLng> latyy = new ArrayList<>();
        latyy.add(m6);
        latyy.add(m7);
        latyy.add(m8);
        latyy.add(m9);

        // polygons
        PolygonOptions polygonOption = new PolygonOptions()
                .add(m1,m2,m3,m4)
                .fillColor(Color.BLUE)
                .strokeColor(Color.GREEN)
                ;

        // Get back the mutable polygon
        Polygon polygon = mMap.addPolygon(polygonOption);


//        mMap.setOnMarkerClickListener(this);  1

//        mMap.setOnMarkerDragListener(this);  2

//        mMap.setOnMapClickListener(this);  3


//        mMap.addMarker(new MarkerOptions()
//                .position(m1)
////                .snippet("Subscirbe to Our Channel")
////                .alpha(40)
////                .icon(BitmapDescriptorFactory.fromResource(R.drawable.girl))
////                .visible(false)
////                .rotation((float) 90.0)
////                .zIndex(1.0f)
//
//                .title("m1"));
//
//
//        LatLng m2 = new LatLng(10,10);
//
//        mMap.addMarker(new MarkerOptions()
//                .position(m2)
//                .draggable(true) // it can dragable wjen
//                .title("m2"));



        // Polyline
        PolylineOptions rectOption = new PolylineOptions()
                .add(m1)
                .add(m2)
                .add(m3)
                .add(m4)
//                .add(m5)
                .color(R.color.design_default_color_primary_dark)
                .width(30)
                ;

        Polyline polyline = mMap.addPolyline(rectOption);

        polyline.isClickable(); // we can click polyline


//        mMap.setOnInfoWindowClickListener(this);  // 4
        mMap.moveCamera(CameraUpdateFactory.newLatLng(m1));


    }
    // ------------------------------------------------
    // ===============================  //
    // , GoogleMap.OnMapClickListener  3
    // ===============================  //

//    @Override
//    public void onMapClick(@NonNull LatLng latLng) {
//        Toast.makeText(this, "Map is clicked", Toast.LENGTH_LONG).show();
//
//    }
    // ===============================  //
    // ------------------------------------------------

    // ===============================  //
    // , GoogleMap.OnInfoWindowClickListener  4
    // ===============================  //
//
//
//    @Override
//    public void onInfoWindowClick(@NonNull Marker marker) {
//        Toast.makeText(this, "Info Window is clicked", Toast.LENGTH_LONG).show();
//    }
    // ===============================  //
// ------------------------------------------------

    // ===============================  //
    //  GoogleMap.OnPolylineClickListener 5
    // ===============================  //

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }

    // Marker //

    // ===============================  //
    // , GoogleMap.OnMarkerClickListener  2
    // ===============================  //

//    @Override
//    public boolean onMarkerClick(@NonNull Marker marker) {
//
//        // Do here what you need
//        // marker.getPosition()
//        // 내 위치 찾기.
////        Toast.makeText(this, "My Latitude  : " + marker.getPosition().latitude,
////                Toast.LENGTH_LONG).show();
//
//
//        return true;
//    }
    // ===============================  //
// ------------------------------------------------

//  // ===============================  //
    //  GoogleMap.OnMarkerDragListener   1
    // ===============================  //
//    @Override
//    public void onMarkerDragStart(@NonNull Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDrag(@NonNull Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDragEnd(@NonNull Marker marker) {
//
//        // Getting the new position lat and long
//
//        Toast.makeText(this, "Latitude" + marker.getPosition().latitude
//                + "\nLongitude" + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
//    }
    // ===============================  //
    // ------------------------------------------------
}