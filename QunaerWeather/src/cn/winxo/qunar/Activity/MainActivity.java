package cn.winxo.qunar.Activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.winxo.qunar.R;
import cn.winxo.qunar.Model.WeatherBean;
import cn.winxo.qunar.Model.WidMap;
import cn.winxo.qunar.Utils.CacheUtils;
import cn.winxo.qunar.Utils.JsonDataUtils;
import cn.winxo.qunar.Utils.NetsUtils;

public class MainActivity extends Activity {

	private TextView tv_temp;
	private TextView tv_city;
	private ImageView iv_icon;
	private TextView tv_date;
	private TextView tv_tempscoreValue;
	private TextView tv_weatherValue;
	private TextView tv_windValue;
	private String city;
	private final String url = "http://v.juhe.cn/weather/index?format=2&cityname=";
	private final String key = "&key=0c295a78261dc83696ef7b76c38f9222";
	private TextView tv_today_day;
	private TextView tv_today_temp;
	private String city_utf8;
	private String weatherData;
	private TextView tv_tomorrow_day;
	private TextView tv_DAtomorrow_day;
	private TextView tv_DDAtomorrow_day;
	private TextView tv_tomorrow_temp;
	private TextView tv_DAtomorrow_temp;
	private TextView tv_DDAtomorrow_temp;
	private ImageView iv_today_icon;
	private ImageView iv_tomorrow_icon;
	private ImageView iv_DAtomorrow_icon;
	private ImageView iv_DDAtomorrow_icon;
	private WeatherBean bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initTTF();
		listener();

		city = PreferenceManager.getDefaultSharedPreferences(this).getString(
				"city", null);
		if (city != null) {
			bean = CacheUtils.getCache(this);
			setView(bean);
		} else {
			city = "自贡";
		}

		try {
			city_utf8 = URLEncoder.encode(city, "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		getWeatherData();
	}

	@Override
	protected void onStop() {
		// 保存当前天气数据作为缓存
		CacheUtils.saveCache(this, bean);
		super.onStop();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		city = data.getExtras().getString("city");
		if (city.equals("null")) {
			return;
		}
		try {
			city_utf8 = URLEncoder.encode(city, "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		getWeatherData();
	}

	private void initView() {
		tv_temp = (TextView) findViewById(R.id.Utv_temp);
		tv_city = (TextView) findViewById(R.id.Utv_city);
		iv_icon = (ImageView) findViewById(R.id.Uiv_icon);

		tv_date = (TextView) findViewById(R.id.Utv_date);
		tv_tempscoreValue = (TextView) findViewById(R.id.Utv_tempscoreValue);
		tv_weatherValue = (TextView) findViewById(R.id.Utv_weatherValue);
		tv_windValue = (TextView) findViewById(R.id.Utv_windValue);

		tv_today_day = (TextView) findViewById(R.id.Utv_today_day);
		tv_tomorrow_day = (TextView) findViewById(R.id.Utv_tomorrow_day);
		tv_DAtomorrow_day = (TextView) findViewById(R.id.Utv_DAtomorrow_day);
		tv_DDAtomorrow_day = (TextView) findViewById(R.id.Utv_DDAtomorrow_day);

		iv_today_icon = (ImageView) findViewById(R.id.Uiv_today_icon);
		iv_tomorrow_icon = (ImageView) findViewById(R.id.Uiv_tomorrow_icon);
		iv_DAtomorrow_icon = (ImageView) findViewById(R.id.Uiv_DAtomorrow_icon);
		iv_DDAtomorrow_icon = (ImageView) findViewById(R.id.Uiv_DDAtomorrow_icon);

		tv_today_temp = (TextView) findViewById(R.id.Utv_today_temp);
		tv_tomorrow_temp = (TextView) findViewById(R.id.Utv_tomorrow_temp);
		tv_DAtomorrow_temp = (TextView) findViewById(R.id.Utv_DAtomorrow_temp);
		tv_DDAtomorrow_temp = (TextView) findViewById(R.id.Utv_DDAtomorrow_temp);
	}

	private void initTTF() {
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/heixi.TTF");
		tv_temp.setTypeface(typeface);
	}

	private void getWeatherData() {
		if (NetsUtils.isNetworkAvailable(MainActivity.this)) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					weatherData = NetsUtils.doGet(url + city_utf8 + key);
					if (weatherData != null) {
						Message msg = Message.obtain();
						msg.obj = JsonDataUtils.parseJson(weatherData);
						mHandler.sendMessage(msg);
					}
				}
			}).start();
		} else {
			Toast.makeText(getApplicationContext(), "当前无网络", Toast.LENGTH_SHORT)
					.show();
		}
	}

	public void setView(WeatherBean bean) {
		tv_city.setText(bean.getCity());
		tv_date.setText(bean.getData_y());
		tv_temp.setText(bean.getTemp());
		tv_tempscoreValue.setText(bean.getTemperature());
		tv_weatherValue.setText(bean.getWeather());
		tv_windValue.setText(bean.getWind());

		tv_today_day.setText(bean.getWeek());
		tv_tomorrow_day.setText(bean.getWeek_1());
		tv_DAtomorrow_day.setText(bean.getWeek_2());
		tv_DDAtomorrow_day.setText(bean.getWeek_3());

		tv_today_temp.setText(bean.getTemperature());
		tv_tomorrow_temp.setText(bean.getTemperature_1());
		tv_DAtomorrow_temp.setText(bean.getTemperature_2());
		tv_DDAtomorrow_temp.setText(bean.getTemperature_3());

		// 切换图标
		iv_icon.setImageResource(WidMap.getWid(bean.getWid()));
		iv_today_icon.setImageResource(WidMap.getWid(bean.getWid()));
		iv_tomorrow_icon.setImageResource(WidMap.getWid(bean.getWid_1()));
		iv_DAtomorrow_icon.setImageResource(WidMap.getWid(bean.getWid_2()));
		iv_DDAtomorrow_icon.setImageResource(WidMap.getWid(bean.getWid_3()));

	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			bean = (WeatherBean) msg.obj;
			setView(bean);
		}
	};

	private void listener() {

		// 设置点击城市搜索
		tv_city.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SearchActivity.class);
				startActivityForResult(intent, 1);
			}
		});

		// 设置点击温度刷新
		tv_temp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				getWeatherData();
				Toast.makeText(getApplicationContext(), "刷新完成",
						Toast.LENGTH_SHORT).show();
			}
		});

	}
}
