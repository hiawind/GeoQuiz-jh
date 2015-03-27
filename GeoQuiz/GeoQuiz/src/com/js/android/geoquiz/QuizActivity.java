package com.js.android.geoquiz;

//import android.R;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
	
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	
	private Button mTrueButton;
	private Button mFalseButton;
	
	// 2014-08-06
	private ImageButton mNextButton;
	private TextView mQuestionTextView;
	
	// 2014-08-11
	private ImageButton mPrevButton;
	
	private Button mCheatButton;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, true),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, false),
			new TrueFalse(R.string.question_asia, true),
	};
	
	private int mCurrentIndex = 0;
	
	private void updateQuestion() {
		Log.d(TAG, "updateQuestion, mCurrentIndex: " + mCurrentIndex);
		
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		
		if(userPressedTrue == answerIsTrue) {
			messageResId = R.string.correct_toast;
		}
		else {
			messageResId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
        
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Log.d(TAG, "question view: mCurrentIndex: " + mCurrentIndex);
				
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				
				Log.d(TAG, "prev button process: mCurrentIndex: " + mCurrentIndex);
				
				updateQuestion();
			}
		});
        
        
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
				checkAnswer(true);
			}
		});
        
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
				checkAnswer(false);
			}
		});
        
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Log.d(TAG, "next button: mCurrentIndex: " + mCurrentIndex);
				
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				
				Log.d(TAG, "next button process: mCurrentIndex: " + mCurrentIndex);
				
				updateQuestion();
			}
		});
        
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Log.d(TAG, "prev button: mCurrentIndex: " + mCurrentIndex);
				
				if(mCurrentIndex > 0)
					mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
				else
					mCurrentIndex = mQuestionBank.length - 1;
				
				Log.d(TAG, "prev button process: mCurrentIndex: " + mCurrentIndex);
				
				updateQuestion();
			}
		});
        
        if(savedInstanceState != null) {
        	mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				startActivity(i);
			}
		});
        
        updateQuestion();
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	Log.i(TAG, "OnSaveInstanceState");
    	savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	Log.d(TAG, "onStart() called");
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	Log.d(TAG, "onPause() called");
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(TAG, "onResume() called");
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	Log.d(TAG, "onStop() called");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "onDestroy() called");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
