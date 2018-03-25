package ie.naveed.p_s_g.formation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daasuu.library.Container;
import com.daasuu.library.DisplayObject;
import com.daasuu.library.FPSTextureView;
import com.daasuu.library.callback.AnimCallBack;
import com.daasuu.library.drawer.SpriteSheetDrawer;
import com.daasuu.library.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ie.naveed.p_s_g.R;
import ie.naveed.p_s_g.util.MySingleton;
import ie.naveed.p_s_g.util.UIUtil;

public class DreamTeam extends AppCompatActivity implements View.OnClickListener {

    private FPSTextureView mFPSTextureView;
    private RelativeLayout stage;
    private String team;
    private Boolean isDreamTeam;

    private Container mSparkContainer = new Container();
    private Container mMainContainer = new Container();
    private RelativeLayout.LayoutParams lparams;
    private float frameWidth, frameHeight;
    private Bitmap baseSpriteBitmapB, spriteBitmapB;
    private SpriteSheetDrawer spriteSheetDrawer;
    private Map<Button, TextView> players;
    private Button playerSelected;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_formation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        team = getIntent().getStringExtra("team");
        isDreamTeam = getIntent().getBooleanExtra("isDreamTeam", false);



        mFPSTextureView = (FPSTextureView) findViewById(R.id.animation_texture_view);
        stage = (RelativeLayout) findViewById(R.id.stage);
        players = new HashMap<>();

        mFPSTextureView.addChild(mSparkContainer).addChild(mMainContainer);

        frameWidth = Util.convertDpToPixel(22f, this);
        frameHeight = Util.convertDpToPixel(36f, this);

        baseSpriteBitmapB = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        spriteBitmapB = Bitmap.createScaledBitmap(baseSpriteBitmapB,
                (int) Util.convertDpToPixel(600f, this),
                (int) Util.convertDpToPixel(36f, this),
                false);

        spriteSheetDrawer = new SpriteSheetDrawer(spriteBitmapB, frameWidth,
                frameHeight, 25, 25)
                .spriteLoop(true);


        for (DisplayObject a : buildPlayers442()) {
            mMainContainer.addChild(a);
        }

    }

    private void endPlayerAnimation(final DisplayObject player, final Integer playerId, final String playerName) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mMainContainer.removeChild(player);

                lparams = new RelativeLayout.LayoutParams(23, RelativeLayout.LayoutParams.WRAP_CONTENT);
                float x = player.getAnimParameter().x;
                float y = player.getAnimParameter().y;
                Button img = new Button(DreamTeam.this);
                img.setBackground(null);

                Drawable top = getResources().getDrawable(R.drawable.player2);
                img.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                img.setTag(playerId);
                img.setLayoutParams(lparams);
                img.setX(x);
                img.setY(y);
                img.setOnClickListener(DreamTeam.this);


                TextView t1 = new TextView(DreamTeam.this);
                t1.setTextSize(8);
                t1.setGravity(Gravity.CENTER);
                t1.setTextColor(ContextCompat.getColor(DreamTeam.this, android.R.color.black));

                t1.setText(String.format(Locale.ENGLISH, playerName, playerId + 1));


                lparams = new RelativeLayout.LayoutParams(230, LinearLayout.LayoutParams.WRAP_CONTENT);
                t1.setLayoutParams(lparams);
                t1.setX(x - 100);
                t1.setY(y);


                players.put(img, t1);
                stage.addView(img);
                stage.addView(t1);

            }
        });
    }


    /**
     * Formation 4 - 4 - 2
     *
     * @return: sprites
     */
    private List<DisplayObject> buildPlayers442() {
        List<DisplayObject> list = new ArrayList<>();
        int windowH = UIUtil.getWindowHeight(this);
        int windowW = UIUtil.getWindowWidth(this);

        List<String> listNames = new ArrayList<>();

        if (isDreamTeam) {
            listNames = Arrays.asList(getResources().getStringArray(R.array.players_manchester_united));
        } else {
            for (int i = 0; i < 11; i++) {
                listNames.add(String.format(Locale.ENGLISH, "Player %d", i));
            }
        }


        //GOALKEEPER
        list.add(createPlayer(2000, windowW / 2, 10, 0, listNames.get(0)));

        //BACK
        list.add(createPlayer(2000, windowW / 8, windowH / 7, 1, listNames.get(1)));
        list.add(createPlayer(2000, (float) (windowW / 3), windowH / 6, 2, listNames.get(2)));

        list.add(createPlayer(2000, (float) (windowW / 1.5), windowH / 6, 3, listNames.get(3)));
        list.add(createPlayer(2000, (float) (windowW / 1.2), windowH / 7, 4, listNames.get(4)));

        //Medium
        list.add(createPlayer(2000, windowW / 8, (float) (windowH / 2.5), 5, listNames.get(5)));
        list.add(createPlayer(2000, windowW / 3, (float) (windowH / 3), 6, listNames.get(6)));
        list.add(createPlayer(2000, (float) (windowW / 1.5), (float) (windowH / 3), 7, listNames.get(7)));
        list.add(createPlayer(2000, (float) (windowW / 1.2), (float) (windowH / 2.5), 8, listNames.get(8)));


        //FRONT
        list.add(createPlayer(2000, (float) (windowW / 3), (float) (windowH / 1.7), 9, listNames.get(9)));
        list.add(createPlayer(2000, (float) (windowW / 1.5), (float) (windowH / 1.7), 10, listNames.get(10)));


        return list;
    }

    private DisplayObject createPlayer(long anim, float x, float y, final Integer playerId, final String playerName) {
        final DisplayObject displayObject = new DisplayObject();
        displayObject
                .with(spriteSheetDrawer)
                .tween()
                .tweenLoop(false)
                .to(anim, x, y)
                .call(new AnimCallBack() {
                    @Override
                    public void call() {
                        endPlayerAnimation(displayObject, playerId, playerName);
                    }
                })
                .end();
        return displayObject;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFPSTextureView.tickStart();
        getSupportActionBar().setSubtitle("4-4-2");
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(team);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFPSTextureView.tickStop();
    }

    @Override
    public void onClick(View v) {
        playerSelected = (Button) v;

        if (players.get(playerSelected).getText().toString().contains("Player")) {
            Intent playerProfile = new Intent(DreamTeam.this, PlayersActivity.class);
            playerProfile.putExtra("bt", players.get(playerSelected).getText().toString());
            startActivityForResult(playerProfile, 1);
        } else {
            //// TODO: 11/11/2016 show a user profile
            String playerName = players.get(playerSelected).getText().toString();
            MySingleton.getInstance().showToast(playerName);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String player = data.getStringExtra("player");
            String bt = data.getStringExtra("bt");
            players.get(playerSelected).setText(player);

        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            //finish();


        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;

        }
        return false;

    }
}
