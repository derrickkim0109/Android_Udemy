package com.example.socket_tablet_server.net.model;



import org.json.JSONException;
import org.json.JSONObject;

public class JSONNotification {
	public String code;
	public String level;
	public String description;

	public String dataTarget;
	
	/**
	 * mSetJSONData
	 *
	 * @param _str
	 */
	public void mSetJSONData(String _str) {
		TAG_JSON_NOTIFICATION _TAG = new TAG_JSON_NOTIFICATION();
		try {
			JSONObject _json = new JSONObject(_str);
			code = _json.getString(_TAG.CODE_);
			level = _json.getString(_TAG.LEVEL_);
			description = _json.getString(_TAG.DESCRIPTION_);
			if(_json.getString(_TAG.DATA_).equals("null")) {
				dataTarget = "no data target" + description;
			} else {
				try {
					//Dlog.e("data >>>>>>>>> " + _json.getString(_TAG.DATA_));
					JSONObject _json_data = new JSONObject(_json.getString(_TAG.DATA_));
					dataTarget = _json_data.getString(_TAG.TARGET_);
				} catch (JSONException e) {
					//MUtils.mWriteLogWithError(e, _str);
					e.printStackTrace();
				}
			}
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
//
//			if(code.equals("01030") || code.equals("02003") || code.equals("02002") || code.equals("01031") || code.equals("01203")
//					|| code.equals("02001") || code.equals("02004")) {
//		dataTarget = "no data target";
//	} else if(code.equals("04000")) {
//		// Description : Start to go to lift. E/V 문으로 출발.
//		dataTarget = "no data target, Start to go to lift.";
//	} else if(code.equals("04001")) {
//		// Description : Succeed to go to lift. E/V 도착 성공.
//		dataTarget = "no data target, Succeed to go to lift.";
//	} else if(code.equals("04002")) {
//		// Description : Failed to go to lift. E/V 도착 실패.
//	} else if(code.equals("04010")) {
//		// Description : Start to call lift. E/V 호출 시작.
//	} else if(code.equals("04011")) {
//		// Description : Succeed to call lift. E/V 호출 성공.
//	} else if(code.equals("04013")) {
//		// Description : Call lift more than 3 minutes. E/V 3분이상 호출 하였으나 미도착.
//	} else if(code.equals("04020")) {
//		// Description : Start to take lift. E/V 탑승 시작.
//	} else if(code.equals("04021")) {
//		// Description : Succeed to take lift. E/V 탑승 성공.
//	} else if(code.equals("04023")) {
//		// Description : Take lift more than 3 minutes. E/V 탑승시 3분이상 소요.
//	} else if(code.equals("04030")) {
//		// Description : Start to enter lift. E/V 탑승시 3분이상 소요.
//	} else if(code.equals("04031")) {
//		// Description : Succeed to enter lift. E/V 내부도착 성공.
//	} else if(code.equals("04032")) {
//		// Description : Failed to enter lift. E/V 내부도착 실패.
//		dataTarget = "no data target, Failed to enter lift.";
//	} else if(code.equals("04040")) {
//		// Description : Start to avoid lift. E/V 탑승 실패, 회피시작.
//		dataTarget = "no data target, Start to avoid lift.";
//	} else if(code.equals("04041")) {
//		// Description : Finish to avoid lift.
//		dataTarget = "no data target, Finish to avoid lift.";
//	} else if(code.equals("04050")) {
//		// Description : Start to exit lift. E/V 나가기 시작.
//	} else if(code.equals("04051")) {
//		// Description : Succeed to exit lift. E/V 나가기 성공.
//	} else if(code.equals("04052")) {
//		// Description : Failed to exit lift. E/V 나오기 실패
//	} else if(code.equals("04060")) {
//		// Description : Start to back to lift. E/V 돌아가기 시작.
//	} else if(code.equals("04061")) {
//		// Description : Succeed to back to lift. E/V 돌아가기 성공.
//	} else if(code.equals("04062")) {
//		// Description : Failed to back to lift. E/V 돌아가기 실패.
////			} else if(code.equals("04041")) {
////			} else if(code.equals("04041")) {
//	} else {
//		try {
//			JSONObject _json_data = new JSONObject(_json.getString(_TAG.DATA_));
//			dataTarget = _json_data.getString(_TAG.TARGET_);
//		} catch (JSONException e) {
//			MUtils.mWriteLogWithError(e, _str);
//		}
//	}
}
