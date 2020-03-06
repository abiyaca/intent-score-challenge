package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView varResult;
    TextView varWinner;
    TextView varScorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        varResult = findViewById(R.id.resultview);
        varWinner = findViewById(R.id.winnerView);
        varScorer = findViewById(R.id.scorerView);

        Bundle extras = getIntent().getExtras();
        // get nilai dari match
        int homeResult = extras.getInt("homeScore");
        int awayResult = extras.getInt("awayScore");
        String txtHomeName = extras.getString("homeText");
        String txtAwayName = extras.getString("awayText");
        String txtScorerH = extras.getString("homeS");
        String txtScorerA = extras.getString("awayS");

        if(extras != null ){
            varResult.setText("Final Score : "+String.valueOf(homeResult) + " - " + String.valueOf(awayResult));
            if(homeResult > awayResult){
                varWinner.setText("pemenangnya adalah "+ txtHomeName + "!");
                varScorer.setText(txtScorerH);
            }else if(awayResult > homeResult){
                varWinner.setText("pemenangnya adalah "+ txtAwayName + "!");
                varScorer.setText(txtScorerA);
            }else{
                varWinner.setText("Skor Seimbang!");
            }
        }
    }
}
