package ie.naveed.p_s_g.formation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ie.naveed.p_s_g.MainActivity;
import ie.naveed.p_s_g.R;
import ie.naveed.p_s_g.util.MySingleton;
import ie.naveed.p_s_g.util.UIUtil;

/**
 * Created by Diogo on 27/10/2016.
 */
public class FootballFormationActivity extends AppCompatActivity implements View.OnClickListener {

    private FPSTextureView mFPSTextureView;
    private RelativeLayout stage;
    private String team;

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

        Drawable logo = this.getResources().getDrawable(R.drawable.fragmentlogo);

        FootballFormationActivity.this.getSupportActionBar().setBackgroundDrawable(logo);

        team = getIntent().getStringExtra("Sport");



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

    private void endPlayerAnimation(final DisplayObject player, final Integer playerId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                StringBuilder builder = new StringBuilder();





                mMainContainer.removeChild(player);
                StringBuilder str = new StringBuilder();



                lparams = new RelativeLayout.LayoutParams(23, RelativeLayout.LayoutParams.WRAP_CONTENT);
                float x = player.getAnimParameter().x;
                float y = player.getAnimParameter().y;
                Button img = new Button(FootballFormationActivity.this);
                img.setBackground(null);

                Drawable top = getResources().getDrawable(R.drawable.player2);
                img.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                img.setTag(playerId);
                img.setLayoutParams(lparams);
                img.setX(x);
                img.setY(y);
                img.setOnClickListener(FootballFormationActivity.this);


                TextView t1 = new TextView(FootballFormationActivity.this);
                t1.setTextSize(8);
                t1.setGravity(Gravity.CENTER);
                t1.setTextColor(ContextCompat.getColor(FootballFormationActivity.this, android.R.color.black));

                t1.setText(String.format(Locale.ENGLISH, "Player %d", playerId +1 ));


                lparams = new RelativeLayout.LayoutParams(230, LinearLayout.LayoutParams.WRAP_CONTENT);
                t1.setLayoutParams(lparams);
                t1.setX(x - 100);
                t1.setY(y + 100);


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

        // time - x - y

        //GOALKEEPER
        list.add(createPlayer(2000, windowW / 2, 10, 0));

        //BACK
        list.add(createPlayer(2000, windowW / 8, windowH / 7, 1));
        list.add(createPlayer(2000, (float) (windowW / 3), windowH / 6, 2));

        list.add(createPlayer(2000, (float) (windowW / 1.5), windowH / 6, 3));
        list.add(createPlayer(2000, (float) (windowW / 1.2), windowH / 7, 4));

        //Medium
        list.add(createPlayer(2000, windowW / 8, (float) (windowH / 2.5), 5));
        list.add(createPlayer(2000, windowW / 3, (float) (windowH / 2.5), 6));
        list.add(createPlayer(2000, (float) (windowW / 1.5), (float) (windowH / 2.5), 7));
        list.add(createPlayer(2000, (float) (windowW / 1.2), (float) (windowH / 2.5), 8));


        //FRONT
        list.add(createPlayer(2000, (float) (windowW / 3), (float) (windowH / 1.7), 9));
        list.add(createPlayer(2000, (float) (windowW / 1.5), (float) (windowH / 1.7), 10));

        return list;
    }

    private DisplayObject createPlayer(long anim, float x, float y, final Integer playerId) {
        final DisplayObject displayObject = new DisplayObject();
        displayObject
                .with(spriteSheetDrawer)
                .tween()
                .tweenLoop(false)
                .to(anim, x, y)
                .call(new AnimCallBack() {
                    @Override
                    public void call() {
                        endPlayerAnimation(displayObject, playerId);
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
        final String tests;

        Intent playerProfile = new Intent(FootballFormationActivity.this, PlayersActivity.class);
        playerProfile.putExtra("bt", players.get(playerSelected).getText().toString());
        //startActivityForResult(playerProfile, 1);
        tests = players.get(playerSelected).getText().toString();
        Toast.makeText(FootballFormationActivity.this, "this is"+" "+tests, Toast.LENGTH_SHORT).show();
        Log.d("app","this is player"+tests);


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
