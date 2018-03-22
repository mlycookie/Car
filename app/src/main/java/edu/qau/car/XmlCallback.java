package edu.qau.car;

import org.json.JSONObject;

public interface XmlCallback {

	String getUri();

	String getNamespace();

	String getMethodName();

	String getXml();

	String getXtlb();

	String getJkxlh();

	String getJkid();

	String getPara();

	void callback(String obj);
}
