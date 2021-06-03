package com.example.socket_tablet_server.net.model;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONResponse {
    public String command;
    public String error_message;
    public String status;
    public String result;

    public void mSetJSONData(String _str) {
        TAG_JSON_RESPONSE _TAG = new TAG_JSON_RESPONSE();
        try {
            JSONObject _json = new JSONObject(_str);
            command = _json.getString(_TAG.COMMAND);
            error_message = _json.getString(_TAG.ERROR_MESSAGE);
            status = _json.getString(_TAG.STATUS);

            try {
                result = _json.getString(_TAG.RESULT);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
