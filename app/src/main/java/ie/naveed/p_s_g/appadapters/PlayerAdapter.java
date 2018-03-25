package ie.naveed.p_s_g.appadapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import ie.naveed.p_s_g.model.Player;
import ie.naveed.p_s_g.util.MySingleton;
import ie.naveed.p_s_g.util.TinyDB;

public class PlayerAdapter extends BaseAdapter {

    private Context ctx;
    private TinyDB tinydb;
    private ArrayList<Player> objects;

    public PlayerAdapter(Context context, ArrayList<Player> prod) {
        this.ctx = context;
        this.objects = prod;
        tinydb = MySingleton.getInstance().tinyDB;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            holder.tv_1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Player item = objects.get(position);
        holder.tv_1.setText(item.getName());

        Player player = (Player) tinydb.getObject(item.getId(), Player.class);
        if (player != null) {
            if (player.getChoosen() && !player.getPosition().isEmpty()) {
                holder.tv_1.setTextColor(Color.GRAY);
            }
        }


        return convertView;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Player getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView tv_1;
    }

}