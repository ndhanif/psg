package ie.naveed.p_s_g;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import ie.naveed.p_s_g.formation.PlayersActivity;
import ie.naveed.p_s_g.util.MySingleton;
import ie.naveed.p_s_g.util.UIUtil;

public class DreamTeam extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout stage;
    private RelativeLayout.LayoutParams params;
    private Button imageButton;
    private Map<Button, TextView> players;
    private Button playerSelected;
    private float primordialX;
    private int stageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_formation);
        setTitle(R.string.app_name);

        stage = (RelativeLayout) findViewById(R.id.stage);
        players = new HashMap<>();
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageButton = (Button) findViewById(R.id.imageButton);

        if (getSupportActionBar() != null){
            getSupportActionBar().setSubtitle("4-2-4");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stageHeight = stage.getMeasuredHeight();
                imageButton.setTag(1);
                primordialX = imageButton.getX();
                players.put(imageButton, null);

                //goalkeeper
                ObjectAnimator anim = ObjectAnimator.ofFloat(imageButton, "y", 40);
                anim.setDuration(3000);
                anim.start();

                createPlayer(primordialX / 4, stageHeight, 2, stageHeight / 7, 3000);
                createPlayer((float) (primordialX / 1.8), stageHeight, 3, stageHeight / 6, 3000);
                createPlayer((float) (primordialX * 1.1), stageHeight, 4, stageHeight / 6, 3000);
                createPlayer((float) (primordialX * 1.4), stageHeight, 5, stageHeight / 7, 3000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        createPlayer(primordialX / 4, stageHeight - 100, 6, stageHeight / 2, 3000);
                        createPlayer((float) (primordialX / 1.8), stageHeight - 100, 7, stageHeight / 3, 3000);
                        createPlayer((float) (primordialX * 1.1), stageHeight - 100, 8, stageHeight / 3, 3000);
                        createPlayer((float) (primordialX * 1.4), stageHeight - 100, 9, stageHeight / 2, 3000);
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        createPlayer((float) (primordialX / 1.8), stageHeight - 100, 10, (int) (stageHeight / 1.8), 3000);
                        createPlayer((float) (primordialX * 1.1), stageHeight - 100, 10, (int) (stageHeight / 1.8), 3000);
                    }
                }, 2000);


            }
        }, 1000);


    }

    @Override
    public void onClick(View view) {
        playerSelected = (Button) view;
        Intent playerProfile = new Intent(DreamTeam.this, PlayersActivity.class);
        playerProfile.putExtra("bt", "Player " + playerSelected.getTag());
        startActivityForResult(playerProfile, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String player = data.getStringExtra("player");
            playerSelected.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
            float x = playerSelected.getX();
            float y = playerSelected.getY();
            Log.i("app", "onActivityResult: x: " + x + " y: " + y);





            if (players.get(playerSelected) == null) {
                TextView t1 = new TextView(DreamTeam.this);
                t1.setTextSize(10);
                t1.setGravity(Gravity.CENTER);
                t1.setTextColor(ContextCompat.getColor(DreamTeam.this, android.R.color.black));
                t1.setText(player);
                t1.setLayoutParams(params);
                t1.setX(x);
                t1.setY(y - 10);
                stage.addView(t1);
                players.put(playerSelected, t1);
            } else {
                players.get(playerSelected).setText(player);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void createPlayer(float x, float y, final Integer playerId, int animToY, long animDelay) {

        final Button img = new Button(this);
        img.setBackground(null);

        Drawable top = ContextCompat.getDrawable(this, R.drawable.tshirt);
        img.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        img.setTag(playerId);
        img.setLayoutParams(params);
        img.setX(x);
        img.setY(y);
        img.setOnClickListener(this);

        ObjectAnimator anim = ObjectAnimator.ofFloat(img, "y", animToY);
        anim.setDuration(animDelay);
        stage.addView(img);
        anim.start();
        players.put(img, null);
    }
}
