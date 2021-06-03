package com.example.socket_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.socket_client.databinding.ActivityMainBinding;
import com.example.socket_client.net.ITF_Network;
import com.example.socket_client.net.MNetworkManager;
import com.example.socket_client.net.TAG_NET;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     *   Activity
     */
    private ActivityMainBinding binding;
    private int btnCheck = 0;

    /**
     * Socket
     */
    public MNetworkManager mNetworkManager;
    public Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnServing.setOnClickListener(this::onClick);

        binding.btn1.setOnClickListener(destinationClick);
        binding.btn2.setOnClickListener(destinationClick);
        binding.btn3.setOnClickListener(destinationClick);
        binding.btn4.setOnClickListener(destinationClick);

        binding.btnStart.setOnClickListener(startClick);

        handler = new Handler();

        handler.postDelayed(mRunDriving,1);
    }


    /**
     * Connect Socket Server
     *
     * App - tablet Server
     */
    private void mCreateNetworkMGR(){
        if(mNetworkManager == null){
            mNetworkManager = new MNetworkManager(TAG_NET.SERVER_IP,TAG_NET.SERVER_PORT,itf_network,handler);
            mNetworkManager.mInit();
        }
    }

    /**
     *    Click Listener
     */
    @Override
    public void onClick(View v) {
        binding.lyMain.setVisibility(View.GONE);
        binding.lyDestinationSelect.setVisibility(View.VISIBLE);
    }

    View.OnClickListener destinationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.btn1.setBackgroundColor(0xFF6200EE);
            binding.btn2.setBackgroundColor(0xFF6200EE);
            binding.btn3.setBackgroundColor(0xFF6200EE);
            binding.btn4.setBackgroundColor(0xFF6200EE);
            switch (v.getId()){
                case R.id.btn_1:
                    binding.btn1.setBackgroundColor(0xFF3700B3);
                    btnCheck = 1;
                    break;
                case R.id.btn_2:
                    binding.btn2.setBackgroundColor(0xFF3700B3);
                    btnCheck = 2;
                    break;
                case R.id.btn_3:
                    binding.btn3.setBackgroundColor(0xFF3700B3);
                    btnCheck = 3;
                    break;
                case R.id.btn_4:
                    binding.btn4.setBackgroundColor(0xFF3700B3);
                    btnCheck = 4;
                    break;
            }
        }
    };

    View.OnClickListener startClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (btnCheck){
                case 0:
                    Toast.makeText(getApplicationContext(),"목적지를 설정해 주세요.",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    mNetworkManager.mSendData(Integer.toString(btnCheck));
                    break;
            }
        }
    };

    /**
     * Connect Tablet Server Thread
     */
    private class MStartCommandToTabletServer extends Thread{
        private Handler _handler;

        public MStartCommandToTabletServer(Handler _handler){
            this._handler = _handler;
        }

        @Override
        public void run() {
                _handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCreateNetworkMGR();
                    }
                },1);
        }
    }

    /**
     * Connect Tablet Server Runnable
     */
    private Runnable mRunDriving = new Runnable() {
        @Override
        public void run() {
            new MStartCommandToTabletServer(handler).start();
        }
    };

    /**
     * Interface
     *
     * Connect Tablet Server
     */
    private ITF_Network itf_network = new ITF_Network() {
        @Override
        public void mReadComplete(String _str) {

        }

        @Override
        public void mWriteComplete() {

        }

        @Override
        public void mConnectSuccess() {

        }

        @Override
        public void mDisConnect() {
            mNetworkManager.mClose();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new MStartCommandToTabletServer(handler).start();
                }
            },1);
        }
    };

}