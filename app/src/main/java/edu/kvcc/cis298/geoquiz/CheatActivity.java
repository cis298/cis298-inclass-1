package edu.kvcc.cis298.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    // This will be the key that is used to store data that is
    // put on the Intent object used to start up the Cheat Activity.
    // The string  must be long and include the package name so that
    // it is VERY unique. It must be this unique so that it will not
    // conflict with ANY other app the OS might know about.
    private static final String EXTRA_ANSWER_IS_TRUE =
            "edu.kvcc.cis298.geoquiz.answer_is_true";

    // This intent is what is called an Explicit Intent.
    // We are explicitly telling the OS what Activity
    // to start up. Later in the semester, we will see
    // Implicit Intents.

    // We make this method public and static so that we can call it from
    // any other class without needing an instance.
    // The purpose of this method is to create and return an intent
    // that knows how to get this Cheat activity started.
    public static Intent newIntent(
            Context packageContext,
            boolean answerIsTrue
    ) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    // Class level bool for whether the answer is true or not
    private boolean mAnswerIsTrue;

    // Class level widget vars
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // Fetch out the answer to the question that was sent inside
        // the intent that got this activity started.
        mAnswerIsTrue = getIntent()
                .getBooleanExtra(
                        EXTRA_ANSWER_IS_TRUE,
                        false
                );

        mAnswerTextView = findViewById(R.id.answer_text_view);

        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
            }
        });
    }
}
