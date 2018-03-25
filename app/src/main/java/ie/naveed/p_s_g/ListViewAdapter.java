package ie.naveed.p_s_g;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Diogo on 21/03/2018.
 */

public class ListViewAdapter extends ArrayAdapter<ItemData> {


    public ListViewAdapter(Context context, int resource, List<ItemData> items) {
        super(context, resource, items);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }
    private final List<ItemData> items;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public ItemData getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View v = convertView;
        ViewHolder holder = null;

        if (v == null) {
            holder = new ViewHolder();
            int layout = R.layout.item_layout;
            v = inflater.inflate(layout, null);

            holder.heading = (TextView) v.findViewById(R.id.heading);
            holder.team1 = (TextView) v.findViewById(R.id.team1);
            holder.team2 = (TextView) v.findViewById(R.id.team2);

            holder.team1score = (TextView) v.findViewById(R.id.scoreteam1);
            holder.team2score = (TextView) v.findViewById(R.id.scoreteam2);


            holder.img = (ImageView) v.findViewById(R.id.icon);
            holder.iconteam1= (ImageView) v.findViewById(R.id.logoteam1);
            holder.iconteam2= (ImageView) v.findViewById(R.id.logoteam2);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        ItemData p = items.get(position); //getItem(position);

        holder.heading.setText(p.getHeading());
        holder.team1.setText(p.getTeam1());
        holder.team2.setText(p.getTeam2());

        holder.team1score.setText(p.getScoreteam1());
        holder.team2score.setText(p.getScoreteam2());


        holder.img.setImageDrawable(p.getMainimage());
        holder.iconteam1.setImageDrawable(p.getImageteam1());
        holder.iconteam2.setImageDrawable(p.getImageteam2());


        Log.i("fff", "getView: image = " + p.getMainimage());

        //load image directly


        return v;
    }


    static class ViewHolder {
        TextView heading,team1,team2,team1score,team2score;
        ImageView img,iconteam1,iconteam2;


    }

}