package edu.qau.car;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class XmlUtils {

	public static String getValue(String xml, String key) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xml));
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					String node = parser.getName();
					if (key.equals(node)) {
						String text = parser.nextText();
						return text;
					}
				}

				eventType = parser.next();
			}
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
