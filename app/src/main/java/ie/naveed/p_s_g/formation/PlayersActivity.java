package ie.naveed.p_s_g.formation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import ie.naveed.p_s_g.R;
import ie.naveed.p_s_g.appadapters.PlayerAdapter;
import ie.naveed.p_s_g.model.Player;
import ie.naveed.p_s_g.util.MySingleton;

public class PlayersActivity extends AppCompatActivity {

    private List<Player> listPlayers;
    private String bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ListView listView = (ListView) findViewById(R.id.listView);
        bt = getIntent().getStringExtra("bt");

        listPlayers = MySingleton.getInstance().getRawPlayerList();


        assert listView != null;
        listView.setAdapter(new PlayerAdapter(this, (ArrayList<Player>) listPlayers));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Player aPlayer = (Player) parent.getAdapter().getItem(position);
                Player player = (Player) MySingleton.getInstance().tinyDB.getObject(aPlayer.getId(), Player.class);

                if (player != null){
                    MySingleton.getInstance().showToast("You have selected this one.");
                }
                else{

                    aPlayer.setChoosen(true);
                    aPlayer.setPosition(bt);
                    MySingleton.getInstance().tinyDB.putObject(aPlayer.getId(), aPlayer);


                    Bundle conData = new Bundle();
                    conData.putString("player", aPlayer.getName());
                    conData.putString("bt", bt);

                    Intent intent = new Intent();
                    intent.putExtras(conData);
                    setResult(RESULT_OK, intent);
                    finish();

                }

            }
        });


    }
}
