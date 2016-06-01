package com.example.samplehashtag;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		HashTagHelper.OnHashTagClickListener, View.OnClickListener {
	private static final String TAG = MainActivity.class.getSimpleName();

	private HashTagHelper mTextHashTagHelper;
	private HashTagHelper mEditTextHashTagHelper;

	private TextView mEditTextView;
	private TextView mHashTagText;
	private TextView mAllHashTag;

	private Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mHashTagText = (TextView) findViewById(R.id.text);
		mAllHashTag = (TextView) findViewById(R.id.all_hashtags);
		mEditTextView = (TextView) findViewById(R.id.edit_text_field);

		Button getEnteredText = (Button) findViewById(R.id.get_entered_text_btn);
		getEnteredText.setOnClickListener(this);
		Button getAllHashTagsBtn = (Button) findViewById(R.id.get_all_hashtags_btn);
		getAllHashTagsBtn.setOnClickListener(this);

		char[] additionalSymbols = new char[] { '_', '$' };
		// If you set additional symbols not only letters and digits will be a
		// valid symbols for hashtag
		// Example:
		// "hash_tag_with_underscore_and$dolar$sign$is$also$valid_hashtag"
		mTextHashTagHelper = HashTagHelper.Creator.create(getResources()
				.getColor(R.color.colorPrimary), this, additionalSymbols);
		mTextHashTagHelper.handle(mHashTagText);

		// Here we don't specify additionalSymbols. It means that in EditText
		// only letters and digits will be valid symbols
		mEditTextHashTagHelper = HashTagHelper.Creator.create(getResources()
				.getColor(R.color.colorPrimaryDark), null);
		mEditTextHashTagHelper.handle(mEditTextView);
	}

	@Override
	public void onHashTagClicked(String hashTag) {
		Log.v(TAG, "onHashTagClicked [" + hashTag + "]");
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(MainActivity.this, hashTag,
				Toast.LENGTH_SHORT);
		mToast.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get_entered_text_btn:
			mHashTagText.setText(mEditTextView.getText());
			break;
		case R.id.get_all_hashtags_btn:
			List<String> allHashTags = mTextHashTagHelper.getAllHashTags();
			mAllHashTag.setText(allHashTags.toString());
			break;
		}
	}
}
