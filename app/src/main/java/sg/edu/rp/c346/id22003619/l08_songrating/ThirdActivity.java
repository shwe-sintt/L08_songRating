package sg.edu.rp.c346.id22003619.l08_songrating;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    EditText etID,etTitle, etSinger, etYear;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Song updateSong;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etID=findViewById(R.id.etID);
        etTitle=findViewById(R.id.etTitle);
        etSinger=findViewById(R.id.etSinger);
        etYear=findViewById(R.id.etYear);
        rg=findViewById(R.id.rg);
        rb1 = findViewById(R.id.radioButton0);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        btnDelete=findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        if (intent != null ) {
//            long id = intent.getLongExtra("id", 0);
//            Song song = (Song) intent.getSerializableExtra("song");
//            String title = intent.getStringExtra("title");
//            String singer = intent.getStringExtra("singers");
//            int year = intent.getIntExtra("year", 0);
//            int rating = intent.getIntExtra("rating", 0);

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            long id = sharedPreferences.getLong("id", 0);
            String title = sharedPreferences.getString("title", "");
            String singer = sharedPreferences.getString("singers", "");
            int year = sharedPreferences.getInt("year", 0);
            int rating = sharedPreferences.getInt("rating", 0);

            Log.d("ThirdActivity", "Received Data: " +
                    "ID: " + id +
                    ", Title: " + title +
                    ", Singer: " + singer +
                    ", Year: " + year +
                    ", Rating: " + rating);


            etID.setText(String.valueOf(id));
            etTitle.setText(title);
            etSinger.setText(singer);
            etYear.setText(String.valueOf(year));
//            rbStar.setText(String.valueOf(rating));
            if (rating == 1) {
                rb1.setChecked(true);
            } else if (rating == 2) {
                rb2.setChecked(true);
            } else if (rating == 3) {
                rb3.setChecked(true);
            }else if (rating == 4) {
                rb4.setChecked(true);
            }else if (rating == 5) {
                rb5.setChecked(true);
            }
        }

            btnUpdate.setOnClickListener(v -> {
                    DBHelper dbh = new DBHelper(ThirdActivity.this);
                    updateSong.setTitle(etTitle.getText().toString());  // Replace newTitle with the new title for the song
                    updateSong.setSingers(etSinger.getText().toString());  // Replace newSinger with the new singer for the song
                    updateSong.setYear(Integer.parseInt(etYear.getText().toString()));  // Replace newYear with the new year for the song
                    int selectedRadioButtonId = rg.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    if (selectedRadioButton != null) {
                        int starsInt = Integer.parseInt(selectedRadioButton.getText().toString());
                        updateSong.setStars(starsInt);
                }
            });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(updateSong.getId());
                int songIdToDelete = Integer.parseInt(etID.getText().toString());
                boolean isDeleted = dbh.deleteSong(songIdToDelete);
                for (int i = 0; i < songList.size(); i++) {
                    Song song = songList.get(i);
                    if (song.getId() == songIdToDelete) {
                        songList.remove(i);
                        break;
                    }
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}