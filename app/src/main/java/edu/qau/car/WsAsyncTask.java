package edu.qau.car;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

public class WsAsyncTask extends AsyncTask<WsCallback, Integer, JSONObject> {

	private String WSDL_URI;
	private String namespace;
	private String methodName;
	private String xtlb;
	private String jkxlh;
	private String jkid;
	private String xml;
	private WsCallback callback;

	@Override
	protected void onPostExecute(JSONObject result) {
		callback.callback(result);
	}

	@Override
	protected JSONObject doInBackground(WsCallback... params) {
		callback = params[0];
		WSDL_URI = callback.getUri() == null ? (AppURL.BASE_URL +AppURL.SECOND_URL)
				: callback.getUri();
		namespace = callback.getNamespace() == null ? "http://tempuri.org/"
				: callback.getNamespace();
		methodName = callback.getMethodName() == null ? "queryObjectOut"
				: callback.getMethodName();
		xtlb = callback.getXtlb();
		jkxlh = callback.getJkxlh();
		jkid = callback.getJkid();
		xml = callback.getXml();

		SoapObject request = new SoapObject(namespace, methodName);
		request.addProperty("xtlb", xtlb);
		request.addProperty("jkxlh", jkxlh);
		request.addProperty("jkid", jkid);
		request.addProperty("QueryXmlDoc", xml);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapSerializationEnvelope.VER11);
		envelope.bodyOut = request;
		HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
		try {
			httpTransportSE.call(namespace + methodName, envelope);
		} catch (Exception e) {
			return null;
		}

		SoapObject object = (SoapObject) envelope.bodyIn;
		String result = object.getProperty(0).toString();
		try {
			JSONObject jsonObject = new JSONObject(result);
			return jsonObject;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
