package cn.winxo.qunar.Utils;

import cn.winxo.qunar.Model.WeatherBean;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class CacheUtils {
	public static void saveCache(Context context, WeatherBean bean) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString("city", bean.getCity());
		editor.putString("data_y", bean.getData_y());
		editor.putString("temp", bean.getTemp());
		editor.putString("temperature", bean.getTemperature());
		editor.putString("weather", bean.getWeather());
		editor.putString("wind", bean.getWind());
		editor.putString("week", bean.getWeek());
		editor.putString("week_1", bean.getWeek_1());
		editor.putString("weather_1", bean.getWeather_1());
		editor.putString("temperature_1", bean.getTemperature_1());
		editor.putString("week_2", bean.getWeek_2());
		editor.putString("weather_2", bean.getWeather_2());
		editor.putString("temperature_", bean.getTemperature_2());
		editor.putString("week_3", bean.getWeek_3());
		editor.putString("weather_3", bean.getWeather_3());
		editor.putString("temperature_3", bean.getTemperature_3());
		editor.putString("wid", bean.getWid());
		editor.putString("wid_1", bean.getWid_1());
		editor.putString("wid_2", bean.getWid_2());
		editor.putString("wid_3", bean.getWid_3());
		editor.commit();
	}

	public static WeatherBean getCache(Context context) {
		WeatherBean bean = new WeatherBean();
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		bean.setCity(sharedPreferences.getString("city", null));
		bean.setData_y(sharedPreferences.getString("data_y", null));
		bean.setTemp(sharedPreferences.getString("temp", null));
		bean.setTemperature(sharedPreferences.getString("temperature", null));
		bean.setWeather(sharedPreferences.getString("weather", null));
		bean.setWind(sharedPreferences.getString("wind", null));
		bean.setWeek(sharedPreferences.getString("week", null));
		bean.setWeek_1(sharedPreferences.getString("week_1", null));
		bean.setWeather_1(sharedPreferences.getString("weather_1", null));
		bean.setTemperature_1(sharedPreferences
				.getString("temperature_1", null));
		bean.setWeek_2(sharedPreferences.getString("week_2", null));
		bean.setWeather_2(sharedPreferences.getString("weather_2", null));
		bean.setTemperature_2(sharedPreferences
				.getString("temperature_2", null));
		bean.setWeek_3(sharedPreferences.getString("week_3", null));
		bean.setWeather_3(sharedPreferences.getString("weather_3", null));
		bean.setTemperature_3(sharedPreferences
				.getString("temperature_3", null));
		bean.setWid(sharedPreferences.getString("wid", null));
		bean.setWid_1(sharedPreferences.getString("wid_1", null));
		bean.setWid_2(sharedPreferences.getString("wid_2", null));
		bean.setWid_3(sharedPreferences.getString("wid_3", null));
		return bean;
	}
}
