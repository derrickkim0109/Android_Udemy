package com.example.socket_client.net;

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

    public void mDisConnect();
}
