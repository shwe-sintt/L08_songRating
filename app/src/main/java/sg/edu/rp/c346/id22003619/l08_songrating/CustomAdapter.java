package sg.edu.rp.c346.id22003619.l08_songrating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> itemList;
    public CustomAdapter(Context context, int resource,
                         ArrayList<Song> objects){
        super(context,resource,objects);
        parent_context=context;
        layout_id=resource;
        itemList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvSong = rowView.findViewById(R.id.textViewSong);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvStar = rowView.findViewById(R.id.textViewStar);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);
        Song currentVersion = itemList.get(position);

        tvSong.setText(currentVersion.getTitle());
        tvYear.setText(String.valueOf(currentVersion.getYear()));
        tvStar.setText(currentVersion.prettyStar());
        tvSinger.setText(currentVersion.getSingers());


        return rowView;

}}

