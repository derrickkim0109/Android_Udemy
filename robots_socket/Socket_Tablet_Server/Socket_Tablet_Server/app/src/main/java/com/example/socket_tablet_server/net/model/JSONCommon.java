package com.example.socket_tablet_server.net.model;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONCommon {
	public String type;
	
	/**
	 * mSetJSONData
	 *
	 * @param _str
	 */
	public void mSetJSONData(String _str) {
		try {
			JSONObject _json = new JSONObject(_str);
			type = _json.getString(TAG_JSON_COMMON.TYPE_);
		} catch(JSONException e) {
			e.printStackTrace();
		}
		
	}
}
