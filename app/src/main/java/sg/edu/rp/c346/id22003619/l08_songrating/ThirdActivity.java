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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    EditText etID,etTitle, etSinger, etYear;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etID=findViewById(R.id.etID);
        etTitle=findViewById(R.id.etTitle);
        etSinger=findViewById(R.id.etSinger);
        etYear=findViewById(R.id.etYear);
//        lv=findViewById(R.id.lv);
        rg=findViewById(R.id.rg);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        btnDelete=findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);

//        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);
//        lv.setAdapter(aa);

        Intent intent = getIntent();
        data=(Song) intent.getSerializableExtra("song");
        if (intent != null ) {
            long id = intent.getLongExtra("id", 0);
            Song song = (Song) intent.getSerializableExtra("song");
            String title = intent.getStringExtra("title");
            String singer = intent.getStringExtra("singers");
            int year = intent.getIntExtra("year", 0);
            int rating = intent.getIntExtra("rating", 0);

            Log.d("ThirdActivity", "Received Data: " +
                    "ID: " + id +
                    ", Title: " + title +
                    ", Singer: " + singer +
                    ", Year: " + year +
                    ", Rating: " + rating);

            etID.setText(String.valueOf(data.getId()));
            etTitle.setText(data.getTitle());
            etSinger.setText(data.getSingers());
            etYear.setText(""+data.getYear());
//            rbStar.setText(String.valueOf(rating));
            if (data.getStar() == 1) {
                rb1.setChecked(true);
            } else if (data.getStar() == 2) {
                rb2.setChecked(true);
            } else if (data.getStar() == 3) {
                rb3.setChecked(true);
            }else if (data.getStar() == 4) {
                rb4.setChecked(true);
            }else if (data.getStar() == 5) {
                rb5.setChecked(true);
            }
        }

            btnUpdate.setOnClickListener(v -> {
                DBHelper db = new DBHelper(ThirdActivity.this);
                int selectedRgStar = rg.getCheckedRadioButtonId();
                int rating = 0;
                if (selectedRgStar == R.id.radioButton1) {
                    rating = 1;
                } else if (selectedRgStar == R.id.radioButton2) {
                    rating = 2;
                } else if (selectedRgStar == R.id.radioButton3) {
                    rating = 3;
                } else if (selectedRgStar == R.id.radioButton4) {
                    rating = 4;
                } else if (selectedRgStar == R.id.radioButton5) {
                    rating = 5;
                }

                String song = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                data.setSongContent(song,singer,year, rating);
                db.updateSong(data);
                db.close();
                Intent i = new Intent(ThirdActivity.this,
                        SecondActivity.class);
                startActivity(i);
            });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                finish();
                Intent intent1= new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(intent1);

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