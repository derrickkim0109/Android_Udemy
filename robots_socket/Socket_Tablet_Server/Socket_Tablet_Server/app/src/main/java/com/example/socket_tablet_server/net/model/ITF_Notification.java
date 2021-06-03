package com.example.socket_tablet_server.net.model;

public interface ITF_Notification {
	public void mNotiEvent(String _code, String _level, String _description, String _target);

	public void mResponse(String _command, String _error_message, String _status);

	public void mStatus(String _result);

	public void mGetInfo(String _result);
}
