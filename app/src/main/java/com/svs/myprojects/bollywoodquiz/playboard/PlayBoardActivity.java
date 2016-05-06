package com.svs.myprojects.bollywoodquiz.playboard;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.svs.myprojects.bollywoodquiz.R;
import com.svs.myprojects.bollywoodquiz.utils.Utility;

public class PlayBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logoutButton;
    private Button viewMyScoreButton;
    private Button viewLeaderBoardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_board_activity);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        setupViewAndListeners();
    }

    private void setupViewAndListeners() {
        logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(this);

        viewMyScoreButton = (Button) findViewById(R.id.view_my_score_button);
        viewMyScoreButton.setOnClickListener(this);

        viewLeaderBoardButton = (Button) findViewById(R.id.view_leader_board_button);
        viewLeaderBoardButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_my_score_button:
                replaceFragment(ViewMyScoreFragment.newInstance(), ViewMyScoreFragment.TAG);
                break;
            case R.id.view_leader_board_button:
                replaceFragment(ViewLeaderBoardFragment.newInstance(), ViewLeaderBoardFragment.TAG);
                break;
            case R.id.logout_button:
                showGoodByeMessage();
                Utility.cleanSharedPreferences(PlayBoardActivity.this);
                break;
        }
    }

    private void showGoodByeMessage() {
        FrameLayout byeMessageLayout = (FrameLayout) findViewById(R.id.bye_message_layout);
        byeMessageLayout.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(3000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        countDownTimer.start();
    }

    public void replaceFragment(Fragment fragment, String TAG) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, TAG);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }
}