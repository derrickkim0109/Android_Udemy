package com.example.socket_tablet_server.net.model;

import com.example.socket_tablet_server.net.TAG_NET;

public class JSONData {
	public JSONCommon jsonCommon;
	public JSONNotification jsonNotification;
	public JSONResponse jsonResponse;
	
	public ITF_Notification mNotiListener;
	
	public JSONData() {
		jsonCommon = new JSONCommon();
		jsonNotification = new JSONNotification();
		jsonResponse = new JSONResponse();
	}
	
	/**
	 * mSetOnNotificationListener
	 *
	 * @param _listener
	 */
	public void mSetOnNotificationListener(ITF_Notification _listener) {
		mNotiListener = _listener;
	}
	
	/**
	 * mSetJSONData
	 *
	 * @param _str
	 */
	public void mSetJSONData(String _str) {
		jsonCommon.mSetJSONData(_str);
		
		if(jsonCommon.type.equals(TAG_JSON_COMMON.TYPE_RESPONSE)) { // Response from Gulliver
			jsonResponse.mSetJSONData(_str);
			if(jsonResponse.command != null && jsonResponse.command.equals(TAG_NET.PARAM_STATUS)) {
				mNotiListener.mStatus(jsonResponse.result);
			} else if(jsonResponse.command != null && jsonResponse.command.equals(TAG_NET.PARAM_INFO)) {
				mNotiListener.mGetInfo(jsonResponse.result);
			} else {
				mNotiListener.mResponse(jsonResponse.command, jsonResponse.error_message, jsonResponse.status);
			}
		} else if(jsonCommon.type.equals(TAG_JSON_COMMON.TYPE_NOTIFICATION)) { // Notification from gulliver
			jsonNotification.mSetJSONData(_str);
			mNotiListener.mNotiEvent(jsonNotification.code, jsonNotification.level, jsonNotification.description, jsonNotification.dataTarget);
		} else if(jsonCommon.type.equals(TAG_JSON_COMMON.TYPE_CALLBACK)) { // Callback from gulliver


		}
	}
}
