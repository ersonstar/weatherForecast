package cn.winxo.qunar.Utils;

import java.io.InputStream;
import java.util.HashSet;

import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlParserUtils {

	public static HashSet<String> getSet(InputStream is) throws Exception {

		HashSet<String> set = new HashSet<String>();
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();

		parser.setInput(is, HTTP.UTF_8);
		int evenType = parser.getEventType();

		while (evenType != XmlPullParser.END_DOCUMENT) {
			if (evenType == XmlPullParser.START_TAG
					&& "key".equals(parser.getName())) {
				String city = parser.nextText();
				parser.next();
				set.add(city);
			}
			parser.next();
			evenType = parser.getEventType();
		}
		return set;

	}
}
