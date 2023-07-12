package sg.edu.rp.c346.id22003619.l08_songrating;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button btnInsert;
Button btnShow;
EditText etTitle;
EditText etSinger;
EditText etYear;
RadioGroup rg;
RadioButton rbStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert=findViewById(R.id.btnInsert);
        btnShow=findViewById(R.id.btnShow);
        etTitle=findViewById(R.id.etTitle);
        etSinger=findViewById(R.id.etSinger);
        etYear=findViewById(R.id.etYear);
        rg=findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = rg.getCheckedRadioButtonId();
                rbStar = (RadioButton) findViewById(id);
                //rbStar.getText();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                String strTitle= etTitle.getText().toString();
                String strSinger= etSinger.getText().toString();
                Integer intYear= Integer.parseInt(etYear.getText().toString());
                Integer intStar= Integer.parseInt(rbStar.getText().toString());
                db.insertSong(strTitle, strSinger,intYear,intStar);
                long insertedId = db.insertSong(strTitle, strSinger, intYear, intStar);

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("id", insertedId);
                editor.putString("title", strTitle);
                editor.putString("singers", strSinger);
                editor.putInt("year", intYear);
                editor.putInt("rating", intStar);
                editor.commit();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Song target = lvSongs.get(0);

                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
//                i.putExtra("Song", target);
                startActivity(i);
            }
        });

    }

}