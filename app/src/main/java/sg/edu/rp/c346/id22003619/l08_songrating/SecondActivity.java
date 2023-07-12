package sg.edu.rp.c346.id22003619.l08_songrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
ListView lvSongs;
Button btn5;
Song data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lvSongs=findViewById(R.id.lv);
        btn5=findViewById(R.id.btn5);
        Intent in = getIntent();
        data = (Song) in.getSerializableExtra("data");

//        tvID.setText("ID: " + data.getId());
//        etContent.setText(data.getNoteContent());
        DBHelper db = new DBHelper(SecondActivity.this);
        ArrayList<Song> data=db.getSongs();
        db.close();
        String txt="";
        for (int i = 0; i < data.size(); i++) {
            Log.d("Database Content", i +". "+data.get(i));
            txt += i + ". " + data.get(i) + "\n";
        }
        //tvResults.setText(txt);
        ArrayAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        lvSongs.setAdapter(adapter);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song clickedSong = (Song) parent.getItemAtPosition(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("song", clickedSong);
                startActivity(intent);
            }
        });

    }

}