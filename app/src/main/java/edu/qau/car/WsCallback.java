package edu.qau.car;

import org.json.JSONObject;

public interface WsCallback {

	String getUri();

	String getNamespace();

	String getMethodName();

	String getXml();

	String getXtlb();

	String getJkxlh();

	String getJkid();

	void callback(JSONObject obj);
}
