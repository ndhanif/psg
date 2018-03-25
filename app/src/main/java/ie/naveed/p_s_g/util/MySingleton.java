package ie.naveed.p_s_g.util;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.naveed.p_s_g.R;
import ie.naveed.p_s_g.model.Player;

/**
 * Created by Diogo on 29/07/2016.
 * DreamTeam
 */
public class MySingleton {

    private static MySingleton instance;
    public TinyDB tinyDB;

    private MySingleton(){
        tinyDB = new TinyDB(MyApp.get());
    }

    public static MySingleton getInstance(){

        if(instance == null)
        {
            initInstance();
        }
        return instance;
    }

    private static synchronized void initInstance()
    {
        if (instance == null)
        {
            instance = new MySingleton();
        }
    }

    public void showToast(String s) {
        Toast.makeText(MyApp.get(), s, Toast.LENGTH_LONG).show();
    }

    public ArrayList<Player> getRawPlayerList(){

        List<Player> playerList = new ArrayList<>();
        List<String> playersList = null;
        playersList = Arrays.asList(MyApp.get().getResources().getStringArray(R.array.players_manchester_united));


        for (int i = 0; i < playersList.size(); i++) {
            playerList.add(new Player(false, "", "", playersList.get(i), String.valueOf(i)));
        }

        return (ArrayList<Player>) playerList;
    }

    public ArrayList<Player> getRawDreamteam(){

        List<Player> playerList = new ArrayList<>();
        List<String> playersList = null;
        playersList = Arrays.asList(MyApp.get().getResources().getStringArray(R.array.players_manchester_united));


        for (int i = 0; i < playersList.size(); i++) {
            playerList.add(new Player(false, "", "", playersList.get(i), String.valueOf(i)));
        }

        return (ArrayList<Player>) playerList;
    }

    /**
     * Default view animation
     * http://www.androidhive.info/2013/06/android-working-with-xml-animations/#fade_in
     *
     * @param view - some button, image, etc.
     * @param anim - Ex: R.anim.fade_in
     */
    public void animView(View view, int anim){
        Animation animation = AnimationUtils.loadAnimation(MyApp.get(),anim);
        view.setAnimation(animation);
    }


}
