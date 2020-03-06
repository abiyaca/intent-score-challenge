package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;
    private static final int FIRST_ACTIVITY_RESULT_CODE = 1;

    TextView homeS;
    TextView awayS;

    int counterH = 0;
    int counterA = 0;

    TextView homeText;
    TextView awayText;

    String homeValue;
    String awayValue;

    String homesValue;
    String awaysValue;

    private TextView hscoreText;
    private TextView ascoreText;

    ImageView homeIcon;
    ImageView awayIcon;

    Uri imageUriHome;
    Uri imageUriAway;

    Bitmap bitmapHome;
    Bitmap bitmapAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);

        homeS = findViewById(R.id.txt_homeS);
        awayS = findViewById(R.id.txt_awayS);

        hscoreText = findViewById(R.id.score_home);
        ascoreText = findViewById(R.id.score_away);

        homeIcon = findViewById(R.id.home_logo);
        awayIcon = findViewById(R.id.away_logo);
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

        Bundle extras = getIntent().getExtras();

        homeValue = extras.getString("home");
        awayValue = extras.getString("away");

        homesValue = extras.getString("homes");
        awaysValue = extras.getString("aways");


        if (extras != null) {
            imageUriHome = Uri.parse(extras.getString("iconHome"));
            imageUriAway = Uri.parse(extras.getString("iconAway"));
            //Attribut image
            bitmapHome = null;
            bitmapAway = null;

            try{
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriHome);
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriAway);
            }catch(IOException e){
                e.printStackTrace();
            }

            // TODO: display value here
            homeText.setText(homeValue);
            awayText.setText(awayValue);
            homeS.setText(homesValue);
            awayS.setText(awaysValue);

            homeIcon.setImageBitmap(bitmapHome);
            awayIcon.setImageBitmap(bitmapAway);
        }
    }

    public void handleResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        //Dari tambah button score
        intent.putExtra("homeScore", counterH);
        intent.putExtra("awayScore", counterA);
        //Dari output nama team
        intent.putExtra("homeText", homeValue);
        intent.putExtra("awayText", awayValue);

        intent.putExtra("homeS", homesValue);
        intent.putExtra("awayS", homesValue);

        startActivity(intent);
    }

    public void handleHscore(View view) {
        Intent i = new Intent(this, ScorerActivity.class);
        startActivityForResult(i, SECOND_ACTIVITY_RESULT_CODE);

        //Intent i = new Intent(this, ScorerActivity.class);
        //startActivity(i);

        //counterH++;
        //hscoreText.setText(Integer.toString(counterH));
    }

    public void handleAscore(View view) {
        Intent i = new Intent(this, ScorerActivity.class);
        startActivityForResult(i, FIRST_ACTIVITY_RESULT_CODE);

        //Intent i = new Intent(this, ScorerActivity.class);
        //startActivity(i);

        //counterA++;
        //ascoreText.setText(Integer.toString(counterA));
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                String homes = data.getStringExtra("score_key");
                String homess;
                homess = homeS.getText().toString();
                counterH++;
                hscoreText.setText(Integer.toString(counterH));
                homeS.setText(homess + "\n" + String.valueOf(homes));
            }
        }else if (requestCode == FIRST_ACTIVITY_RESULT_CODE){
            if (resultCode == RESULT_OK) {
                String aways = data.getStringExtra("score_key");
                String awayss;
                awayss = awayS.getText().toString();
                counterA++;
                ascoreText.setText(Integer.toString(counterA));
                awayS.setText(awayss + "\n" + String.valueOf(aways));
            }
        }
    }
}
