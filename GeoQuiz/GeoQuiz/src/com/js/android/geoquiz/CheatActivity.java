package com.js.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	private static final String TAG = "CheatActivity";
	
	public static final String EXTRA_ANSWER_IS_TRUE = 
			"com.js.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN =
			"com.js.android.geoquiz.answer_shown";
	
	private static final String ON_CLICK = "on_click";
	
	private boolean mAnswerIsTrue;
	private boolean mOnClick = false;
	
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	private TextView mVersionTextView;
	
	private void setAnswerShowResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mVersionTextView = (TextView)findViewById(R.id.versionTextView);
		mVersionTextView.setText(Build.VERSION.SDK);
		
		if(savedInstanceState != null) {
        	mOnClick = savedInstanceState.getBoolean(ON_CLICK);
        }
		
		//Answer will not be shown until the user presses the button
		setAnswerShowResult(false);
		
		Log.d(TAG, "mAnswerIsTrue: "+mAnswerIsTrue);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onSetText(mAnswerIsTrue);
				setAnswerShowResult(true);
				mOnClick = true;
				Log.d(TAG, "onClick enter, mOnClick: "+mOnClick);
			}
		});
		Log.d(TAG, "mOnClick: "+mOnClick);
		if(mOnClick)
		{
			onSetText(mAnswerIsTrue);
		}
	}
	
	public void onSetText(boolean mAnswer) {
		if (mAnswer) {
			mAnswerTextView.setText(R.string.true_button);
		} else {
			mAnswerTextView.setText(R.string.false_button);
		}
	}
	
	@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	Log.i(TAG, "OnSaveInstanceState");
    	savedInstanceState.putBoolean(ON_CLICK, mOnClick);
    }

}
