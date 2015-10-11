package cn.winxo.qunar.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.winxo.qunar.Model.WeatherBean;

public class JsonDataUtils {

	public static WeatherBean parseJson(String weatherData) {
		WeatherBean weatherBean = new WeatherBean();
		try {
			JSONObject data = new JSONObject(weatherData);
			JSONObject result = data.getJSONObject("result");
			JSONObject sk = result.getJSONObject("sk");
			weatherBean.setTemp(sk.getString("temp")); // 实时温度
			JSONObject today = result.getJSONObject("today");

			// 今日参数
			weatherBean.setTemperature(today.getString("temperature"));
			weatherBean.setWeather(today.getString("weather"));
			weatherBean.setWind(today.getString("wind"));
			weatherBean.setCity(today.getString("city"));
			weatherBean.setData_y(today.getString("date_y"));
			weatherBean.setWeek(today.getString("week"));

			// 天气标识
			JSONObject weather_id = today.getJSONObject("weather_id");
			weatherBean.setWid(weather_id.getString("fa"));

			// 未来三天
			JSONArray future = result.getJSONArray("future");

			JSONObject future_1 = future.getJSONObject(1);
			weatherBean.setWeek_1(future_1.getString("week"));
			weatherBean.setWeather_1(future_1.getString("weather"));
			weatherBean.setTemperature_1(future_1.getString("temperature"));
			JSONObject weather_id1 = future_1.getJSONObject("weather_id");
			weatherBean.setWid_1(weather_id1.getString("fa"));

			JSONObject future_2 = future.getJSONObject(2);
			weatherBean.setWeek_2(future_2.getString("week"));
			weatherBean.setWeather_2(future_2.getString("weather"));
			weatherBean.setTemperature_2(future_2.getString("temperature"));
			JSONObject weather_id2 = future_2.getJSONObject("weather_id");
			weatherBean.setWid_2(weather_id2.getString("fa"));

			JSONObject future_3 = future.getJSONObject(3);
			weatherBean.setWeek_3(future_3.getString("week"));
			weatherBean.setWeather_3(future_3.getString("weather"));
			weatherBean.setTemperature_3(future_3.getString("temperature"));
			JSONObject weather_id3 = future_3.getJSONObject("weather_id");
			weatherBean.setWid_3(weather_id3.getString("fa"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weatherBean;
	}
}
