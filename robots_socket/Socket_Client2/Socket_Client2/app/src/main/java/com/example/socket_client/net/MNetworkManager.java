package com.example.socket_client.net;

import android.os.Handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;


public class MNetworkManager {

    /**
     * Socket
     */
    public Socket mSocket;

    /**
     * Network RES
     */
    private BufferedInputStream mBIS;
    private BufferedOutputStream mBOS;
    private byte[] mReadBuffer = new byte[1024];

    /**
     * Run
     */
    public boolean mIsRun = true;

    /**
     * IP
     */
    public String mIP;

    /**
     * Port
     */
    public int mPort;

    /**
     * Sleep Time
     */
    public int mReadSleepTime;

    /**
     * Network
     */
    public ITF_Network itfNetwork;

    /**
     * Handler
     */
    public Handler handler;

    /**
     * Socket Init Thread
     */
    public MSocketInitThread mSocketInitThread;

    /**
     * Flag Connect
     */
    public boolean mIsConnect = false;


    /**
     * MNetworkManager
     *
     * @param _ip
     * @param _port
     */
    public MNetworkManager(String _ip, int _port, ITF_Network _itf, Handler _handler){
        mIP = _ip;
        mPort = _port;
        itfNetwork = _itf;
        handler = _handler;
        mReadSleepTime = 100;
    }

    /**
     *
     * @param _time
     */
    public void mSetNetworkReadSleepTime(int _time){
        mReadSleepTime = _time;
    }

    /**
     * mInit
     *
     * Socket Connect
     */
    public void mInit(){
        mSocketInitThread = new MSocketInitThread();
        mSocketInitThread.start();
    }

    /**
     * mSendData
     *
     * @param _str
     */
    public void mSendData(String _str){
        if(_str != null && !_str.isEmpty()){
            mSendData(_str.getBytes());
        }
    }

    /**
     * mSendData
     *
     * @param _buf
     */
    public void mSendData(byte[] _buf){
        MWriteThread mWriteThread = new MWriteThread(_buf);
        mWriteThread.start();
    }

    /**
     * MSocketInitThread
     */
    private class MSocketInitThread extends Thread{
        @Override
        public void run() {
            if(mSocket != null){
                return;
            }
            try{
                mSocket = new Socket(mIP,mPort);
                mBIS = new BufferedInputStream(mSocket.getInputStream());
                mBOS = new BufferedOutputStream(mSocket.getOutputStream());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        itfNetwork.mConnectSuccess();
                        mIsConnect = true;
                        MReadThread _mReadThread = new MReadThread();
                        _mReadThread.start();
                    }
                },1);
            }catch (Exception e){
                mIsConnect = false;
                mSocket = null;
                e.printStackTrace();

            }
        }
    }

    /**
     * MReadThread
     *
     * Tablet Server -> App
     */
    private class MReadThread extends Thread{
        @Override
        public void run() {
            try{
                if(mBIS != null){
                    while(mIsRun){
                        byte[] _read_buf = new byte[1024];
                        int _read_len = mBIS.read(_read_buf);
                        if(_read_len == -1){ // 연결실패
                            mIsRun = false;
                            itfNetwork.mDisConnect();
                        }else{
                            itfNetwork.mReadComplete(new String(_read_buf,0,_read_len));
                        }

                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * MWriteThread
     *
     * App -> Tablet Server
     */
    private class MWriteThread extends Thread{

        private byte[] mBuf;

        public MWriteThread(byte[] _buf){
            mBuf = _buf;
        }

        @Override
        public void run() {
            try{
                if(!mIsRun) return;

                if(mBOS != null){
                    mBOS.write(mBuf, 0, mBuf.length);
                    mBOS.flush();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    itfNetwork.mWriteComplete();
                }
            },1);

        }
    }

    /**
     * mClose
     */
    public void mClose(){
        mIsRun = false;
        if(mBIS != null){
            try{
                mBIS.close();
                mBIS = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(mBOS != null){
            try{
                mBOS.close();
                mBOS = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(mSocket != null){
            try{
                mSocket.close();
                mSocket = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
