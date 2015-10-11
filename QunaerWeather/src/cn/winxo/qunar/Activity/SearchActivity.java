package cn.winxo.qunar.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;
import cn.winxo.qunar.R;
import cn.winxo.qunar.Utils.NetsUtils;
import cn.winxo.qunar.Utils.XmlParserUtils;

public class SearchActivity extends Activity {

	private ImageView iv_seacher;
	private AutoCompleteTextView autoTV;
	protected String city;
	private HashSet<String> set;
	private Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);

		iv_seacher = (ImageView) findViewById(R.id.iv_seacher);
		autoTV = (AutoCompleteTextView) findViewById(R.id.autoTv);

		try {
			initData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		initAutoTV();
		listener();

	}

	private void initData() throws Exception {
		try {
			InputStream is = this.getAssets().open("city_code.xml");
			set = XmlParserUtils.getSet(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initAutoTV() {

		String[] array = new String[set.size()];
		set.toArray(array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array);

		autoTV.setAdapter(adapter);
		autoTV.setThreshold(1);
	}

	public void listener() {
		iv_seacher.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (NetsUtils.isNetworkAvailable(SearchActivity.this)) {
					intent = getIntent();
					city = autoTV.getEditableText().toString().trim();

					if (!set.contains(city)) {
						Toast.makeText(getApplicationContext(), "请输入正确的城市名",
								Toast.LENGTH_LONG).show();
						autoTV.setText("");
						return;
					}

					intent.putExtra("city", city);
					setResult(1, intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "当前没有可用网络！",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			intent.putExtra("city", "null");
			setResult(1, intent);
		}
		return super.onKeyDown(keyCode, event);
	}

}
