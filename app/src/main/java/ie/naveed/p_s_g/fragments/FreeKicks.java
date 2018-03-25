package ie.naveed.p_s_g.fragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import ie.naveed.p_s_g.ClipActivity;
import ie.naveed.p_s_g.ItemData;
import ie.naveed.p_s_g.ListViewAdapter;
import ie.naveed.p_s_g.MainActivity;
import ie.naveed.p_s_g.R;
import ie.naveed.p_s_g.Scrolling;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreeKicks extends Fragment implements AdapterView.OnItemClickListener {

    private ListView list;
    ImageView imageView;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Drawable logo = this.getResources().getDrawable(R.drawable.fragmentlogo);

        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(logo);

        View view = inflater.inflate(R.layout.fragment_free_kicks, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        list = (ListView) view.findViewById(R.id.listView);
        imageView = (ImageView) view.findViewById(R.id.imageView);




        Drawable team2logo = this.getResources().getDrawable(R.drawable.psglogo);
        Drawable team1logo = this.getResources().getDrawable(R.drawable.marsellielogo);

        // Construct the data source
        ArrayList<ItemData> listOfItem = new ArrayList<>();

        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), R.layout.item_layout, listOfItem);
        // list.setAdapter(new ListViewAdapter(Athletics.this,R.layout.item_layout,listOfItem));
        list.setAdapter(listViewAdapter);


        listOfItem.add(new ItemData("French Cup - Qtr Finals","PSG","Marsellies","https://s3-eu-west-1.amazonaws.com/ienquirevideos/goal1.1.mp4","3","1",team1logo,team2logo,team1logo));
        listOfItem.add(new ItemData("French Cup - Qtr Finals","PSG","Marsellies","https://s3-eu-west-1.amazonaws.com/ienquirevideos/goal1.1.mp4","3","1",team1logo,team2logo,team1logo));
        list.setOnItemClickListener(this);

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("app", "onItemClick onimte ");
        ItemData clip = (ItemData) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this.getContext(), ClipActivity.class);
        intent.putExtra("clip", clip.getUrl());
        startActivity(intent);


    }





}
