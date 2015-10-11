package cn.winxo.qunar.Model;

import java.util.HashMap;
import java.util.Map;

import cn.winxo.qunar.R;

public class WidMap {

	public static Map<String, String> getWidMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("00", "晴");
		map.put("01", "多云");
		map.put("02", "阴");
		map.put("03", "阵雨");
		map.put("04", "雷阵雨");
		map.put("05", "雷阵雨伴有冰雹");
		map.put("06", "雨夹雪");
		map.put("07", "小雨");
		map.put("08", "中雨");
		map.put("09", "大雨");
		map.put("10", "暴雨");
		map.put("11", "大暴雨");
		map.put("12", "特大暴雨");
		map.put("13", "阵雪");
		map.put("14", "小雪");
		map.put("15", "中雪");
		map.put("16", "大雪");
		map.put("17", "暴雪");
		map.put("18", "雾");
		map.put("19", "冻雨");
		map.put("20", "沙尘暴");
		map.put("21", "小雨-中雨");
		map.put("22", "中雨-大雨");
		map.put("23", "大雨-暴雨");
		map.put("24", "暴雨-大暴雨");
		map.put("25", "大暴雨-特大暴雨");
		map.put("26", "小雪-中雪");
		map.put("27", "中雪-大雪");
		map.put("28", "大雪-暴雪");
		map.put("29", "浮尘");
		map.put("30", "扬沙");
		map.put("31", "强沙尘暴");
		map.put("53", "霾");
		return map;
	}

	public static int getWid(String wid) {
		int widInt = Integer.parseInt(wid);
		switch (widInt) {
		case 0:
			return R.drawable.a00;
		case 1:
			return R.drawable.a01;
		case 2:
			return R.drawable.a02;
		case 3:
			return R.drawable.a03;
		case 4:
			return R.drawable.a04;
		case 5:
			return R.drawable.a05;
		case 6:
			return R.drawable.a06;
		case 7:
			return R.drawable.a07;
		case 8:
			return R.drawable.a08;
		case 9:
			return R.drawable.a09;
		case 10:
			return R.drawable.a10;
		case 11:
			return R.drawable.a11;
		case 12:
			return R.drawable.a12;
		case 13:
			return R.drawable.a13;
		case 14:
			return R.drawable.a14;
		case 15:
			return R.drawable.a15;
		case 16:
			return R.drawable.a16;
		case 17:
			return R.drawable.a17;
		case 18:
			return R.drawable.a18;
		case 19:
			return R.drawable.a19;
		case 20:
			return R.drawable.a20;
		case 21:
			return R.drawable.a21;
		case 22:
			return R.drawable.a22;
		case 23:
			return R.drawable.a23;
		case 24:
			return R.drawable.a24;
		case 25:
			return R.drawable.a25;
		case 26:
			return R.drawable.a26;
		case 27:
			return R.drawable.a27;
		case 28:
			return R.drawable.a28;
		case 29:
			return R.drawable.a29;
		case 30:
			return R.drawable.a30;
		case 31:
			return R.drawable.a31;
		default:
			return R.drawable.a01;
		}

	}
}
