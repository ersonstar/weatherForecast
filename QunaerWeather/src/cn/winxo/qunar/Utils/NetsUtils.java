package cn.winxo.qunar.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetsUtils {

	public static String doGet(String uri) {

		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			// 获取返回码 200为全部得到 206为部分
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream inputStream = connection.getInputStream();
				BufferedReader bf = new BufferedReader(new InputStreamReader(
						inputStream));
				String line = bf.readLine();
				StringBuffer stringBuffer = new StringBuffer();
				while (line != null) {
					stringBuffer.append(line);
					line = bf.readLine();
				}

				return stringBuffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null) {
			return false;
		} else {
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0) {
				for (int i = 0; i < networkInfo.length; i++) {
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
