package com.example.socket_tablet_server.net;

public class TAG_NET {

    /**
     *  --------------------- Socket Server ---------------------
     * My Socket Server PORT
     *
     * 32001
     */
    public static final int mPORT = 31002;



    /**
     *  ------------------------ Robot ----------------------------
     * Robot Admin IP
     *
     * 192.168.10.10
     */
    public static final String ROBOT_ADMIN_IP           = "192.168.10.10";

    /**
     * Robot Port
     *
     * 31001
     */
    public static final int ROBOT_PORT                  = 31001;



    /**
     * Param - Status
     */
    public static final String PARAM_STATUS             = "/api/robot_status";

    /**
     * Param - Info
     */
    public static final String PARAM_INFO               = "/api/robot_info";

    /**
     * Param - Move
     */
    public static final String PARAM_MOVE               = "/api/move?marker=";

    /**
     * Read Sleep Time
     */
    public static final int READ_SLEEP_TIME             = 100;
}
