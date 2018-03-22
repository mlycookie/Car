package edu.qau.car;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

public class XmlAsyncTask extends AsyncTask<XmlCallback, Integer, String> {

	private String WSDL_URI;
	private String namespace;
	private String methodName;
	private String xtlb;
	private String jkxlh;
	private String jkid;
	private String xml;
	private XmlCallback callback;

	@Override
	protected void onPostExecute(String result) {
		callback.callback(result);
	}

	@Override
	protected String doInBackground(XmlCallback... params) {
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
		String param = callback.getPara() == null ? "QueryXmlDoc" : callback
				.getPara();
		request.addProperty(param, xml);
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
		return result;
	}
}
