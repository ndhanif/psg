package ie.naveed.p_s_g.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import ie.naveed.p_s_g.R;
import ie.naveed.p_s_g.formation.PlayersActivity;
import ie.naveed.p_s_g.util.MySingleton;

/**
 * Created by barry on 08/08/2016.
 * DreamTeam
 */
public class FragmentFormation1 extends Fragment {

    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;
    Button Button7;
    Button Button8;
    Button Button9;
    Button Button10;
    private Map<String, Button> players;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.formation_1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addListenerOnButton(view);
    }

    public void addListenerOnButton(View view) {

        Button1 = (Button) view.findViewById(R.id.p1);
        setOnClick(Button1);
        Button2 = (Button) view.findViewById(R.id.p2);
        setOnClick(Button2);
        Button3 = (Button) view.findViewById(R.id.p3);
        setOnClick(Button3);
        Button4 = (Button) view.findViewById(R.id.p4);
        setOnClick(Button4);
        Button5 = (Button) view.findViewById(R.id.p5);
        setOnClick(Button5);
        Button6 = (Button) view.findViewById(R.id.p6);
        setOnClick(Button6);
        Button7 = (Button) view.findViewById(R.id.p7);
        setOnClick(Button7);
        Button8 = (Button) view.findViewById(R.id.p8);
        setOnClick(Button8);
        Button9 = (Button) view.findViewById(R.id.p9);
        setOnClick(Button9);
        Button10 = (Button) view.findViewById(R.id.p10);
        setOnClick(Button10);

        players = new HashMap<>();
        players.put("p1", Button1);
        players.put("p2", Button2);
        players.put("p3", Button3);
        players.put("p4", Button4);
        players.put("p5", Button5);
        players.put("p6", Button6);
        players.put("p7", Button7);
        players.put("p8", Button8);
        players.put("p9", Button9);
        players.put("p10", Button10);

    }

    private void setOnClick(final Button imgBtn) {
        imgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String bt = getResources().getResourceEntryName(arg0.getId());

                Intent playerProfile = new Intent(getActivity(), PlayersActivity.class);
                playerProfile.putExtra("bt", bt);
                getActivity().startActivityForResult(playerProfile, 1);
            }
        });
    }

    public void updatePlayer(String bt, String player){
        players.get(bt).setText(player);
        MySingleton.getInstance().animView(players.get(bt),R.anim.fade_in);
    }

}
