package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {

    private EditText scorerInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        scorerInput = findViewById(R.id.scorer);
    }

    public void handleBscore(View view) {
        String scorer = scorerInput.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("score_key", scorer);

        /** String homes = homesInput.getText().toString();
        String aways = awaysInput.getText().toString();

        //put extra
        intent.putExtra(HOMES_KEY, homes);
        intent.putExtra(AWAYS_KEY, aways); **/

        setResult(RESULT_OK, intent);
        finish();
    }
}
