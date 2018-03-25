package ie.naveed.p_s_g;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ie.naveed.p_s_g.fragments.Goals;

/**
 * Created by Diogo on 23/03/2018.
 */

public class Scrolling extends Fragment {


    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.scrolling_layout, null);

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.linear);
        ArrayList<Integer> array_image = new ArrayList<Integer>();

                array_image.add(R.drawable.goalicon);
                array_image.add(R.drawable.saveicon);
                array_image.add(R.drawable.freekickicon);
                array_image.add(R.drawable.lineouticon);
                array_image.add(R.drawable.intericon);
                array_image.add(R.drawable.shotsblockicon);



        for (int i = 0; i < array_image.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setId(i);
            imageView.setPadding(2, 000, 2, 2);
            imageView.setImageResource(array_image.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            layout.addView(imageView);


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //compare the v id to show the different fragment
                    Log.i("app","user id"+v.getId());

                    if (v.getId()==0){

                        Goals goals = new Goals();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.frament, goals);
                        ft.commit();


                    }

                    else if (v.getId()==0)
                    {
//                        Saves saves = new Saves();
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.replace(R.id.frament, saves);
//                        ft.commit();


                    }

                    else if (v.getId()==0)
                    {

                    }

                    else if (v.getId()==0)
                    {


                    }

                    else if (v.getId()==0)
                    {


                    }



                }
            });

        }

        return root;
    }

}
