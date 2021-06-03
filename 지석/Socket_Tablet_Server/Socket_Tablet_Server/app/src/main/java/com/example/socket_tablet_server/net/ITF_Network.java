package com.example.socket_tablet_server.net;

public interface ITF_Network {
    /**
     * mReadComplete
     *
     * @param _str
     */
    public void mReadComplete(String _str);

    /**
     * mWriteComplete
     */
    public void mWriteComplete();

    /**
     * mConnectSuccess
     */
    public void mConnectSuccess();

    /**
     * mConnectFail
     */
    public void mConnectFail();
}
