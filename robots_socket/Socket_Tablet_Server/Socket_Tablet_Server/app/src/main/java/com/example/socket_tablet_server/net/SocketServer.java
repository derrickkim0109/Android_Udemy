package com.example.socket_tablet_server.net;

import android.os.Handler;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer {

    public ITF_SocketServer mItfSocketServer;

    public Socket socket;
    public ServerSocket serverSocket;

    ServerThread serverThread;

    public Handler mHandler;



    private BufferedInputStream mBIS;
    private BufferedOutputStream mBOS;
    private byte[] mReadBuffer = new byte[1024];

    public boolean isConnected = true;


    /**
     * Constructor
     *
     * @param _mHandler
     * @param _itfSocketServer
     */
    public SocketServer(Handler _mHandler, ITF_SocketServer _itfSocketServer) {
        mHandler = _mHandler;
        mItfSocketServer = _itfSocketServer;
    }

    /**
     * mInit()
     *
     * Server Connect
     */
    public void mInit(){
        serverThread = new ServerThread();
        serverThread.start();
    }


//    public void mSendData(byte[] _byte){
//        MWriteToRobotThread mWriteToRobotThread = new MWriteToRobotThread(_byte);
//        mWriteToRobotThread.start();
//    }
//
//
//    public void mSendData(String _str){
//        if(_str != null && _str.isEmpty()){
//            mSendData(_str.getBytes());
//        }
//    }

    /**
     * ServerSocket Connect Thread
     *
     *  Data
     *  App -> Tablet Server
     */
    public class ServerThread extends Thread{
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(TAG_NET.mPORT);
                Log.d("cjs","ServerSocket connect");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                socket = serverSocket.accept();

                mBIS = new BufferedInputStream(socket.getInputStream());
                mBOS = new BufferedOutputStream(socket.getOutputStream());

                while(isConnected){
                    byte[] _read_buf = new byte[1024];
                    int _read_len = mBIS.read(_read_buf);
                    if(_read_len == -1){
                        isConnected = false;
                    }else{
                        // 데이터 들어올때 실행되는곳
                        mItfSocketServer.mReadComplete(new String(_read_buf,0,_read_len));
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
//    public class MWriteToRobotThread extends Thread{
//        private byte[] mBuf;
//
//        public MWriteToRobotThread(byte[] _mBuf){
//            this.mBuf = _mBuf;
//        }
//
//        @Override
//        public void run() {
//
//        }
//    }








}
