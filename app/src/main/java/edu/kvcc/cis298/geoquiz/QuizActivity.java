package edu.kvcc.cis298.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // Setup a string constant to use as a TAG for filtering in LogCat
    private static final String TAG = "QuizActivity";
    // String constant to use as a key for storing data into the
    // bundle object. Key will be used in onSaveInstanceState and
    // in onCreate whe pulling out.
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    // Array of questions to display
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    // Counter to keep track of which question we are on.
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Log out a message to the LogCat
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        // Check the Bundle and see if there is data in it.
        // If so, fetch it out and use it to set the current
        // question index.
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(
                    KEY_INDEX,
                    0
            );
        }

        // Get a handle to the TextView for the question text
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increment the index and mod by the array length
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // This method gets called around the same time as
        // onPause. It is done before onStop is called.
        // We can save out any information we want to get
        // back out on screen rotation.
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        // Store the current question index in the Bundle with
        // a key of KEY_INDEX.
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    private void updateQuestion() {
        // Get the string resource id for the current question.
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        // Set the question text
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        // Get whether the current question is true or not
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        // Setup a int for what message to toast. Default to 0. Will be changed below.
        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            // If user pressed true and question is true
            messageResId = R.string.correct_toast;
        } else {
            // Else must be false
            messageResId = R.string.incorrect_toast;
        }

        // Toast out the correct message
        Toast.makeText(
                this,
                messageResId,
                Toast.LENGTH_SHORT
        ).show();
    }
}









