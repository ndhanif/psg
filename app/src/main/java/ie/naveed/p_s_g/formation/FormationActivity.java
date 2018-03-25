package ie.naveed.p_s_g.formation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ie.naveed.p_s_g.R;
import ie.naveed.p_s_g.fragments.FragmentFormation0;
import ie.naveed.p_s_g.fragments.FragmentFormation1;
import ie.naveed.p_s_g.fragments.FragmentFormation2;
import ie.naveed.p_s_g.fragments.FragmentFormation3;
import ie.naveed.p_s_g.util.MySingleton;


public class FormationActivity extends AppCompatActivity {

    private Integer formation;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formation_activity_main);
        formation = 1;

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentStage, new FragmentFormation1());
        ft.commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(getResources().getStringArray(R.array.formations)[formation]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.formation))
                        .setSingleChoiceItems(R.array.formations, formation, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                formation = i;
                                MySingleton.getInstance().tinyDB.clear();
                                updateView();
                            }
                        });
                builder.show();


                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String player = data.getStringExtra("player");
            String bt = data.getStringExtra("bt");

            Fragment fragment = getVisibleFragment();
            if (fragment != null) {
                if (fragment instanceof FragmentFormation1) {
                    ((FragmentFormation1) fragment).updatePlayer(bt, player);
                } else if (fragment instanceof FragmentFormation0) {
                    ((FragmentFormation0) fragment).updatePlayer(bt, player);
                } else if (fragment instanceof FragmentFormation2) {
                    ((FragmentFormation2) fragment).updatePlayer(bt, player);
                } else if (fragment instanceof FragmentFormation3) {
                    ((FragmentFormation3) fragment).updatePlayer(bt, player);
                }

            }

            if (getSupportActionBar() != null) {
                getSupportActionBar().setSubtitle(getResources().getStringArray(R.array.formations)[formation]);
            }


        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            //finish();
        }
    }

    private void updateView() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(getResources().getStringArray(R.array.formations)[formation]);
        }

        FragmentTransaction ft = fm.beginTransaction();

        switch (formation) {
            case 0:
                ft.replace(R.id.fragmentStage, new FragmentFormation0());
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.fragmentStage, new FragmentFormation1());
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.fragmentStage, new FragmentFormation2());
                ft.commit();
                break;

            case 3:
                ft.replace(R.id.fragmentStage, new FragmentFormation3());
                ft.commit();
                break;

        }
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = FormationActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible()) {
                    return fragment;
                }
            }
        }
        return null;
    }

}
