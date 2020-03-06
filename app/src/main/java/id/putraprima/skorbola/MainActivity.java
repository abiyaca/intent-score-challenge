package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //image view
    ImageView homeIconImage, awayIconImage;
    EditText homeText, awayText;
    //uri load gambar
    Uri imageUriHome;
    Uri imageUriAway;
    //Bitmap
    Bitmap bitmapHome;
    Bitmap bitmapAway;
    //TAG
    private static final String TAG = MainActivity.class.getCanonicalName();

    public static final String HOME_KEY = "home";
    public static final String AWAY_KEY = "away";

    private EditText homeInput;
    private EditText awayInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        homeInput = findViewById(R.id.home_team);
        awayInput = findViewById(R.id.away_team);

        homeIconImage = findViewById(R.id.home_logo);
        awayIconImage = findViewById(R.id.away_logo);

        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    public void handleNext(View view) {

        if( homeInput.getText().toString().isEmpty()){
            homeInput.setError( "set home team!" );
        }else if( awayInput.getText().toString().isEmpty()) {
            awayInput.setError("set away team!");
        }else if(bitmapHome == null) {
            Toast.makeText(this, "Isi seluruh data terlebih dahulu!", Toast.LENGTH_SHORT).show();
        }else if(bitmapAway == null) {
            Toast.makeText(this, "Isi seluruh data terlebih dahulu!", Toast.LENGTH_SHORT).show();
        }else{
            String home = homeInput.getText().toString();
            String away = awayInput.getText().toString();
            Intent i = new Intent(this, MatchActivity.class);

            //put extra
            i.putExtra(HOME_KEY, home);
            i.putExtra(AWAY_KEY, away);
            i.putExtra("iconHome", imageUriHome.toString());
            i.putExtra("iconAway", imageUriAway.toString());

            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }
        if (requestCode == 1){
            if (data != null){
                try {
                    imageUriHome = data.getData();
                    bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriHome);
                    homeIconImage.setImageBitmap(bitmapHome);
                } catch (IOException e){
                    Toast.makeText(this, "Tak bisa load gambar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }else if (requestCode == 2){
            if (data != null){
                try {
                    imageUriAway = data.getData();
                    bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriAway);
                    awayIconImage.setImageBitmap(bitmapAway);
                } catch (IOException e){
                    Toast.makeText(this, "Tak bisa load gambar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleHomeLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleAwayLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}
