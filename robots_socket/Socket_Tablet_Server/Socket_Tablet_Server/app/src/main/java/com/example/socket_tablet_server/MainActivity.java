package com.example.socket_tablet_server;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.socket_tablet_server.fd.FD_BATTERY;
import com.example.socket_tablet_server.net.ITF_Network;
import com.example.socket_tablet_server.net.ITF_SocketServer;
import com.example.socket_tablet_server.net.MNetworkManager;
import com.example.socket_tablet_server.net.SocketServer;
import com.example.socket_tablet_server.net.TAG_MAP;
import com.example.socket_tablet_server.net.TAG_NET;
import com.example.socket_tablet_server.net.model.ITF_Notification;
import com.example.socket_tablet_server.net.model.JSONData;
import com.example.socket_tablet_server.net.model.TAG_NOTIFICATION;

import org.json.JSONObject;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    /**
     * Activity
     */
    TextView tvBattery,tvCharge,tvStatus;
    Button btnReceive;
    String TARGET = "";
    boolean isHome = true;

    /**
     * Socket Server
     */
    SocketServer socketServer;
    public Handler mHandler;

    /**
     * Robot
     */
    MNetworkManager mNetworkManager;
    JSONData mJSONData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBattery = findViewById(R.id.tv_battery);
        tvCharge = findViewById(R.id.tv_charge);
        tvStatus = findViewById(R.id.tv_status);
        btnReceive = findViewById(R.id.btn_received);

        btnReceive.setOnClickListener(btnClickListener);

        mHandler = new Handler();

        mHandler.post(runnable);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mSetSocketServer();
            mSetNetworkMGR();
        }
    };

    /**
     * Connect Socket Server
     *
     * App - Tablet Server
     */
    private void mSetSocketServer(){
        socketServer = new SocketServer(mHandler,itfSocketServer);
        socketServer.mInit();
    }

    /**
     * OnClickListener
     */
    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mNetworkManager.mSendData(TAG_NET.PARAM_MOVE+ TAG_MAP.HOME);
        }
    };

    /**
     * Connect Robot
     *
     * Tablet - Robot Server
     */
    private void mSetNetworkMGR(){
        if(mJSONData == null) {
            mJSONData = new JSONData();
            mJSONData.mSetOnNotificationListener(mITFNotification);
        }

        if(mNetworkManager == null) {
            mNetworkManager = new MNetworkManager(TAG_NET.ROBOT_ADMIN_IP, TAG_NET.ROBOT_PORT, itfNetwork, mHandler);
            mNetworkManager.mInit();
        }
    }

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    };

    /**
     * Interface
     *
     * SocketServer Connect
     */
    ITF_SocketServer itfSocketServer = new ITF_SocketServer() {
        @Override
        public void mReadComplete(String _str) {
            mNetworkManager.mSendData(TAG_NET.PARAM_MOVE+_str); // App 에서 데이터를 받아오면 바로 Robot 으로 데이터 보냄
            TARGET = _str;
        }
    };

    /**
     * Interface
     *
     * Robot Network Connect
     */
    ITF_Network itfNetwork = new ITF_Network() {
        @Override
        public void mReadComplete(String _str) {
            mJSONData.mSetJSONData(_str);
        }

        @Override
        public void mWriteComplete() {

        }

        @Override
        public void mConnectSuccess() {

        }

        @Override
        public void mConnectFail() {

        }
    };


    ITF_Notification mITFNotification = new ITF_Notification() {
        @Override
        public void mNotiEvent(String _code, String _level, String _description, String _target) {
            switch (_code){
                case TAG_NOTIFICATION
                        .CD_MOVE_TASK_STARTED:
                    btnReceive.setVisibility(View.GONE);
                    tvStatus.setText(TARGET+"목적지로\n 이동중 입니다.");
                    break;
                case TAG_NOTIFICATION.CD_MOVE_TASK_FINISHED:
                    if(isHome){
                        btnReceive.setVisibility(View.GONE);
                        tvStatus.setText("대기중");
                    }else{
                        btnReceive.setVisibility(View.VISIBLE);
                        tvStatus.setText("수령후 완료버튼을 눌러주세요.");
                    }
            }
        }

        @Override
        public void mResponse(String _command, String _error_message, String _status) {

        }

        @Override
        public void mStatus(String _result) {
            if(_result == null && _result.isEmpty()) return;

            try{
                JSONObject _obj = new JSONObject(_result);
                final int _power = _obj.getInt(FD_BATTERY.TAG_JSON_POWER_PERCENT);
                final boolean _charge_state = _obj.getBoolean(FD_BATTERY.TAG_JSON_CHARGE_STATE);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(_charge_state){
                            tvCharge.setText("충전 O");
                        }else{
                            tvCharge.setText("충전 X");
                        }
                        String power = Integer.toString(_power);
                        tvBattery.setText(power+"%");
                    }
                },1);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void mGetInfo(String _result) {

        }
    };





}