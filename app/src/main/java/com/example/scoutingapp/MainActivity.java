package com.example.scoutingapp;
import android.content.Intent;
import android.graphics.Color;
import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.button.MaterialButton;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    void clearall(){
        source_pickup.setChecked(false);
        ground_pickup.setChecked(false);
        amp.setChecked(false);
        speaker.setChecked(false);
    }
    void hideall(){
        mTextViewCountDown.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.INVISIBLE);
        mButtonReset.setVisibility(View.INVISIBLE);
        switch_auton.setVisibility(View.INVISIBLE);
        switch_teleop.setVisibility(View.INVISIBLE);
        source_pickup.setVisibility(View.INVISIBLE);
        ground_pickup.setVisibility(View.INVISIBLE);
//        amplify.setVisibility(View.INVISIBLE); something wrong with this line it causes program to crash
        speaker.setVisibility(View.INVISIBLE);
        amp.setVisibility(View.INVISIBLE);
        fail.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        undo.setVisibility(View.INVISIBLE);
        succesful_spotlight.setVisibility(View.INVISIBLE);
        no_try_spotlight.setVisibility(View.INVISIBLE);
        unsuccesful_spotlight.setVisibility(View.INVISIBLE);
        succesful_harmony.setVisibility(View.INVISIBLE);
        no_try_harmony.setVisibility(View.INVISIBLE);
        unsuccesful_harmony.setVisibility(View.INVISIBLE);
        succesful_trap.setVisibility(View.INVISIBLE);
        no_try_trap.setVisibility(View.INVISIBLE);
        unsuccesful_trap.setVisibility(View.INVISIBLE);
        succesful_onstage.setVisibility(View.INVISIBLE);
        no_try_onstage.setVisibility(View.INVISIBLE);
        unsuccesful_onstage.setVisibility(View.INVISIBLE);
        up_times_blocked.setVisibility(View.INVISIBLE);
        down_times_blocked.setVisibility(View.INVISIBLE);
        region_1.setVisibility(View.INVISIBLE);
        region_2.setVisibility(View.INVISIBLE);
        region_3.setVisibility(View.INVISIBLE);
        region_4.setVisibility(View.INVISIBLE);
        region_5.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
    }
    void showall(){
        mButtonStartPause.setVisibility(View.VISIBLE);
        mButtonReset.setVisibility(View.VISIBLE);
        mTextViewCountDown.setVisibility(View.INVISIBLE);
        switch_auton.setVisibility(View.VISIBLE);
        switch_teleop.setVisibility(View.VISIBLE);
        source_pickup.setVisibility(View.VISIBLE);
        ground_pickup.setVisibility(View.VISIBLE);
//      amplify.setVisibility(View.VISIBLE); something wrong with this line it causes program to crash
        speaker.setVisibility(View.VISIBLE);
        amp.setVisibility(View.VISIBLE);
        fail.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        undo.setVisibility(View.VISIBLE);
        succesful_spotlight.setVisibility(View.VISIBLE);
        no_try_spotlight.setVisibility(View.VISIBLE);
        unsuccesful_spotlight.setVisibility(View.VISIBLE);
        succesful_harmony.setVisibility(View.VISIBLE);
        no_try_harmony.setVisibility(View.VISIBLE);
        unsuccesful_harmony.setVisibility(View.VISIBLE);
        succesful_trap.setVisibility(View.VISIBLE);
        no_try_trap.setVisibility(View.VISIBLE);
        unsuccesful_trap.setVisibility(View.VISIBLE);
        succesful_onstage.setVisibility(View.VISIBLE);
        no_try_onstage.setVisibility(View.VISIBLE);
        unsuccesful_onstage.setVisibility(View.VISIBLE);
        up_times_blocked.setVisibility(View.VISIBLE);
        down_times_blocked.setVisibility(View.VISIBLE);
        region_1.setVisibility(View.VISIBLE);
        region_2.setVisibility(View.VISIBLE);
        region_3.setVisibility(View.VISIBLE);
        region_4.setVisibility(View.VISIBLE);
        region_5.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
    }
    void filledout(){
        scout_namev=scout_name.getText().toString();
        team_numberv=team_num.getText().toString();
        match_numberv=match_num.getText().toString();
        String blank = scout_name.getText().toString();;
        if(scout_namev!=blank&&team_numberv!=blank&&match_numberv!=blank){
           showall();
        }
        else{
            hideall();
        }
    }
	void amplified(){
		if(amplifiedv){
			int x = amplify_timerv-10;
            int i =  amplify_timerv;
			if(i > 0 && i != x) {
				if(amplify.isChecked()){
					amplify.setChecked(false);
					//amplify.setBackgroundColor(Color.RED);
				}
				else if(!amplify.isChecked()){
					amplify.setChecked(true);
					//amplify.setBackgroundColor(Color.GREEN);
				}
			}
		}

	}
    void resetall(){
        source_pickup_autonv=0;
        ground_pickup_autonv=0;
        source_pickup_teleopv=0;
        ground_pickup_teleopv=0;
        speaker_autonv=0;
        amp_autonv=0;
        speaker_teleopv=0;
        amp_teleopv=0;
        amplified_speaker_teleopv=0;
        amp_fail_autonv=0;
        speaker_fail_autonv=0;
        amp_fail_teleopv=0;
        speaker_fail_teleopv=0;
        spotlightv="";
        buddy_climbv="";
        trapv=""; onstagev="";
        blocksv=0;
        shots_blockedv=0;
        region_1v = false;
        region_2v = false;
        region_3v = false;
        region_4v = false;
        region_5v = false;
        sourcev = false;
        groundv = false;
        ampv= false;
        speakerv= false;
        autonv=true;
        amplifiedv = false;
        amplify_timerv = 0;
        speaker_scoredv=0;
        amp_scoredv=0;
    }

    //entry data: unless these four have been filled out the rest of the app is hidden
    String scout_namev = ""; String alliance_colorv = "";
    String match_numberv=""; String team_numberv="";
    // variable for timestamps
    String listv = "";
    //variables for timer
    long START_TIME_IN_MILLIS = 15000;
    private CountDownTimer mCountDownTimer; private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    //variables for data collection
    int source_pickup_autonv; int ground_pickup_autonv;
    int source_pickup_teleopv; int ground_pickup_teleopv;
    int speaker_autonv; int amp_autonv;
    int speaker_teleopv; int amp_teleopv; int amplified_speaker_teleopv;
    int amp_fail_autonv; int speaker_fail_autonv;
    int amp_fail_teleopv; int speaker_fail_teleopv;
    String spotlightv; String buddy_climbv;
    String trapv; String onstagev; int blocksv;
    int shots_blockedv;

    boolean region_1v, region_2v, region_3v, region_4v, region_5v;
    //variables use for checking whether or not an action is done or button is clicked
    boolean sourcev; boolean groundv;
    boolean ampv; boolean speakerv;
    boolean autonv=true; boolean amplifiedv = false;
    int amplify_timerv;
    int speaker_scoredv=0; int amp_scoredv=0; // for display use only not actual data sent to sheet

    //textview, editview, and button definitions in order shown on app top to bottom
    EditText scout_name, team_num, match_num;
    TextView mTextViewCountDown;
    Button mButtonStartPause, mButtonReset;
    MaterialButton switch_to_teleop;
    ToggleButton red_alliance, blue_alliance;
    TextView speakers_scored,amps_scored;
    ToggleButton switch_auton, switch_teleop;
    ToggleButton source_pickup, ground_pickup, amplify;
    ToggleButton speaker, amp;
    MaterialButton fail, score, undo, shots_blocked;
    ToggleButton succesful_spotlight, no_try_spotlight, unsuccesful_spotlight;
    ToggleButton succesful_harmony, no_try_harmony, unsuccesful_harmony;
    ToggleButton succesful_trap, no_try_trap, unsuccesful_trap;
    ToggleButton succesful_onstage, no_try_onstage, unsuccesful_onstage;
    MaterialButton up_times_blocked, down_times_blocked;
    TextView times_blocked;
    ToggleButton region_1, region_2,region_3,region_4,region_5;
    Button submit;
    Button upload;
    RequestQueue queue;
    ArrayList<Match> matches = new ArrayList<Match>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch_to_teleop = findViewById(R.id.switch_to_teleop);
        //timer buttons
        mTextViewCountDown = findViewById(R.id.mTextViewCountDown);
        mButtonStartPause = findViewById(R.id.mButtonStartPause);
        mButtonReset = findViewById(R.id.mButtonReset);

        //entry data
        scout_name=(EditText) findViewById(R.id.scout_name);
        team_num=(EditText) findViewById(R.id.team_num);
        match_num=(EditText) findViewById(R.id.match_num);

//text views for display
        speakers_scored = findViewById(R.id.speakers_scored);
        amps_scored = findViewById(R.id.amps_scored);
//toggle buttons
        red_alliance = (ToggleButton) findViewById(R.id.red_alliance);
        blue_alliance = (ToggleButton) findViewById(R.id.blue_alliance);
        switch_auton = (ToggleButton) findViewById(R.id.switch_auton);
        switch_teleop = (ToggleButton) findViewById(R.id.switch_teleop);
        source_pickup = (ToggleButton) findViewById(R.id.source_pickup);
        ground_pickup = (ToggleButton) findViewById(R.id.ground_pickup);
        amplify = (ToggleButton) findViewById(R.id.amplify);
        speaker = (ToggleButton) findViewById(R.id.speaker);
        amp = (ToggleButton) findViewById(R.id.amp);
//material buttons
        fail = (MaterialButton) findViewById(R.id.fail);
        score = (MaterialButton) findViewById(R.id.score);
        undo = (MaterialButton) findViewById(R.id.undo);
        shots_blocked = (MaterialButton) findViewById(R.id.shots_blocked);
//end game
        succesful_spotlight =  findViewById(R.id.succesful_spotlight);
        no_try_spotlight =  findViewById(R.id.no_try_spotlight);
        unsuccesful_spotlight =  findViewById(R.id.unsuccesful_spotlight);
        succesful_harmony =  findViewById(R.id.succesful_harmony);
        no_try_harmony =  findViewById(R.id.no_try_harmony);
        unsuccesful_harmony =  findViewById(R.id.unsuccesful_harmony);
        succesful_trap =  findViewById(R.id.succesful_trap);
        no_try_trap =  findViewById(R.id.no_try_trap);
        unsuccesful_trap =  findViewById(R.id.unsuccesful_trap);
        succesful_onstage =  findViewById(R.id.succesful_onstage);
        no_try_onstage =  findViewById(R.id.no_try_onstage);
        unsuccesful_onstage =  findViewById(R.id.unsuccesful_onstage);
        times_blocked = findViewById(R.id.times_blocked);
        up_times_blocked = (MaterialButton) findViewById(R.id.up_times_blocked);
        down_times_blocked = (MaterialButton) findViewById(R.id.down_times_blocked);
        region_1 = (ToggleButton) findViewById(R.id.region_1);
        region_2 = (ToggleButton) findViewById(R.id.region_2);
        region_3 = (ToggleButton) findViewById(R.id.region_3);
        region_4 = (ToggleButton) findViewById(R.id.region_4);
        region_5 = (ToggleButton) findViewById(R.id.region_5);
//      mTextViewCountDown = findViewById(R.id.text_view_countdown);
//		private TextView mTextViewCountDown;
//		private CountDownTimer mCountDownTimer;
//		private boolean mTimerRunning;
//		private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        mButtonStartPause = (MaterialButton) findViewById(R.id.button_start_pause);
//        mButtonReset = (MaterialButton) findViewById(R.id.button_reset);
        submit = (MaterialButton) findViewById(R.id.submit);

        // code below for submitting data to google sheet
        queue = Volley.newRequestQueue(this);
		TextView lastScoreTime = findViewById(R.id.last_score_time);

        switch_to_teleop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               System.out.println("submit pressed");
               //matches.add(newMatch);
				if (view.getId() == R.id.submit) {
					source_pickup.setChecked(false);
					ground_pickup.setChecked(false);
					amp.setChecked(false);
					speaker.setChecked(false);
					switch_teleop.setChecked(false);
					switch_auton.setChecked(false);
					clearall();
					scout_name.setText("");
					team_num.setText("");
					match_num.setText("");
					speakers_scored.setText("0");
					amps_scored.setText("0");
					times_blocked.setText("0");
					for (ToggleButton button : new ToggleButton[]{switch_auton, switch_teleop, source_pickup, ground_pickup, amp, speaker, succesful_spotlight, no_try_spotlight, unsuccesful_spotlight, succesful_harmony, no_try_harmony, unsuccesful_harmony, succesful_trap, no_try_trap, unsuccesful_trap, succesful_onstage, no_try_onstage, unsuccesful_onstage, region_1, region_2, region_3, region_4, region_5}) {
						button.setChecked(false);
					}
					if (mCountDownTimer != null) {
						mCountDownTimer.cancel();
						resetTimer();
					}
                    resetall();
				}
               String source_to_speaker = "NA";
               // String name, int matchnumber, int teamnumber, String alliance_color, int ground_pickup_auton, int ground_pickup_teleop, int source_pickup_auton, int source_pickup_teleop, int regular_note_auton, int regular_note_teleop, int amplified_note, int speaker_notes_auton, int speaker_notes_teleop, int amp_notes_auton, int amp_notes_teleop, int drop, String source_to_speaker, boolean spotlight, boolean buddy_climb, boolean trap, boolean onstage, String list
               Match newMatch = new Match(scout_namev, match_numberv, team_numberv, alliance_colorv ,ground_pickup_autonv, ground_pickup_teleopv, source_pickup_autonv, source_pickup_teleopv, speaker_autonv, speaker_teleopv, amplified_speaker_teleopv, speaker_autonv, speaker_teleopv+amplified_speaker_teleopv, amp_autonv, amp_teleopv, amp_fail_autonv+amp_fail_teleopv+speaker_fail_autonv+speaker_fail_teleopv, source_to_speaker, spotlightv, buddy_climbv, trapv, onstagev, listv);

               //String scoutNamev, String matchNumberv, String teamNumberv, String alliance_colorv, int groundPickupAutonv, int groundPickupTeleopv, int sourcePickupAutonv, int sourcePickupTeleopv, int speakerAutonv, int speakerTeleopv, int amplifiedSpeakerTeleopv, int totalSpeakerAutonv, int totalSpeakerTeleopv, int ampAutonv, int ampTeleopv, int totalFails, int sourceToSpeaker, String Spotlightv, boolean buddyClimbv, boolean Trapv, boolean Onstagev, String Listv)

               matches.add(newMatch);

                //saveData(match.scout_namev, match.match_numberv, team_numberv, alliance_colorv ,ground_pickup_autonv, ground_pickup_teleopv, source_pickup_autonv, source_pickup_teleopv, speaker_autonv, speaker_teleopv, amplified_speaker_teleopv, speaker_autonv, speaker_teleopv+amplified_speaker_teleopv, amp_autonv, amp_teleopv, amp_fail_autonv+amp_fail_teleopv+speaker_fail_autonv+speaker_fail_teleopv, source_to_speaker, spotlightv, buddy_climbv, trapv, onstagev, listv);

//                String name = "amogh";
//                int matchnumber = 76;
//                int teamnumber = 7390;
//                saveData(name, matchnumber, teamnumber, alliance_color, ground_pickup, source_pickup,
//                        amplified, drop, source_to_speaker, spotlight, buddy_climb, trap, onstage, list);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.upload) {

                }
            }

        });

        red_alliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alliance_colorv="red";
                red_alliance.setChecked(true);
                blue_alliance.setChecked(false);
                scout_namev=scout_name.getText().toString();
                team_numberv=team_num.getText().toString();
                match_numberv=match_num.getText().toString();
            }});
        blue_alliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alliance_colorv="blue";
                blue_alliance.setChecked(true);
                red_alliance.setChecked(false);
                scout_namev=scout_name.getText().toString();
                team_numberv=team_num.getText().toString();
                match_numberv=match_num.getText().toString();
            }});
        switch_auton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autonv=true;
                switch_auton.setChecked(true);
                switch_teleop.setChecked(false);
                amplified_speaker_teleopv =0;
                speaker_scoredv=0;
                amps_scored.setText("0");
                speakers_scored.setText("0");
                START_TIME_IN_MILLIS = 15000;
                resetTimer();
            }
        });
        switch_teleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autonv=false;
                switch_auton.setChecked(false);
                switch_teleop.setChecked(true);
                START_TIME_IN_MILLIS = 135000;
                resetTimer();}});
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();
        source_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listv += ", pickup from source " + (mTimeLeftInMillis / 1000);
                sourcev=true;
                groundv=false;
                ground_pickup.setChecked(false);}});
        ground_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listv += ", pickup from ground " + (mTimeLeftInMillis / 1000);
                groundv=true;
                sourcev=false;
                source_pickup.setChecked(false);}});
        amplify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amplifiedv=true;
                amplified();
            }
        });
        amp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ampv=true;
                speakerv=false;
                speaker.setChecked(false);
            }
        });
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakerv=true;
                ampv=false;
                amp.setChecked(false);
            }
        });
        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listv += ", failed " + (mTimeLeftInMillis / 1000);
                if(ampv&&autonv) {
                    amp_fail_autonv++;
                } else if (ampv&&!autonv) {
                    amp_fail_teleopv++;
                } else if(speakerv&&autonv) {
                    speaker_fail_autonv++;
                } else if(speakerv&&!autonv) {
                    speaker_fail_teleopv++;}
                groundv=false;
                sourcev=false;
                ampv=false;
                speakerv=false;
                clearall();}});
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groundv=false;
                sourcev=false;
                ampv=false;
                speakerv=false;
                clearall();}});
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listv += ", scored " + (mTimeLeftInMillis / 1000);
                if(groundv&&autonv){
                    ground_pickup_autonv++;
                }
                else if(groundv&&!autonv){
                    ground_pickup_teleopv++;
                }
                else if(sourcev&&autonv){
                    source_pickup_autonv++;
                }
                else if(sourcev&&!autonv){
                    source_pickup_teleopv++;
                }
                if(ampv&&autonv){
                    amp_autonv++;
                    listv += ", scored in amp " + (mTimeLeftInMillis / 1000);
                }
                else if(ampv&&!autonv){
                    amp_teleopv++;
                    listv += ", scored in amp " + (mTimeLeftInMillis / 1000);
                }
                else if(speakerv&&autonv){
                    speaker_autonv++;
                    listv += ", scored in speaker " + (mTimeLeftInMillis / 1000);
                }
                else if(speakerv&&!autonv){
                    if(amplifiedv)amplified_speaker_teleopv++;
                    if(!amplifiedv)speaker_teleopv++;
                    listv += ", scored in speaker " + (mTimeLeftInMillis / 1000);
                }
                //pickup location
                if(groundv&&autonv)ground_pickup_autonv++;
                else if(groundv&&!autonv) ground_pickup_teleopv++;
                else if(sourcev&&autonv)source_pickup_autonv++;
                else if(sourcev&&!autonv)source_pickup_teleopv++;
                //scoring location
                if(ampv&&autonv)amp_autonv++;
                else if(ampv&&!autonv)amp_teleopv++;
                else if(speakerv&&autonv)speaker_autonv++;
                else if(speakerv&&!autonv)speaker_teleopv++;


                if(speakerv){
                    speaker_scoredv ++;
                    speakers_scored.setText(String.valueOf(speaker_scoredv));                }
                if(ampv){
                    amp_scoredv ++;
                    amps_scored.setText(String.valueOf(amp_scoredv));
                }
                groundv=false;
                sourcev=false;
                ampv=false;
                speakerv=false;
                clearall();}});
        shots_blocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shots_blockedv++;
                groundv=false;
                sourcev=false;
                ampv=false;
                speakerv=false;
                clearall();}});
        succesful_spotlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spotlightv="succesful";}});
        no_try_spotlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spotlightv="no try";
            }
        });
        unsuccesful_spotlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spotlightv="fail";
            }
        });
        succesful_harmony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddy_climbv="succesful";
            }
        });
        no_try_harmony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddy_climbv="no try";
            }
        });
        unsuccesful_harmony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddy_climbv="fail";
            }
        });
        succesful_trap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trapv="succesful";
            }
        });
        no_try_trap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trapv="no try";
            }
        });
        unsuccesful_trap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trapv="fail";
            }
        });
        succesful_onstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onstagev="succesful";
            }
        });
        unsuccesful_onstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onstagev="no try";
            }
        });
        unsuccesful_onstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onstagev="fail";
            }
        });
        up_times_blocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blocksv++;
                times_blocked.setText(String.valueOf(blocksv));
            }
        });
        down_times_blocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blocksv>=1){
                    blocksv--;
                }
                times_blocked.setText(String.valueOf(blocksv));
            }
        });
        region_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                region_1v=true;
            }
        });
        region_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                region_2v=true;
            }
        });
        region_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                region_3v=true;
            }
        });
        region_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                region_4v=true;            }
        });
        region_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                region_5v=true;
            }
        });
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        amplify_timerv=seconds;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void saveData(String name, String matchnumber, String teamnumber, String alliance_color, int ground_pickup_auton, int ground_pickup_teleop, int source_pickup_auton, int source_pickup_teleop, int regular_note_auton, int regular_note_teleop, int amplified_note, int speaker_notes_auton, int speaker_notes_teleop, int amp_notes_auton, int amp_notes_teleop, int drop, String source_to_speaker, String spotlight, String buddy_climb, String trap, String onstage, String list) {
        String url = "https://script.google.com/macros/s/AKfycbzfPRl0QhBQ_meqcIeDAb1qwZKtK-BQ-IO9uiyBwjRCu0xmr2__KJEKbmj0ri46KTsp/exec?";
        url = url + "action=create&name=" + name + "&matchnumber=" + matchnumber + "&teamnumber=" + teamnumber;
        url = url + "&color=" + alliance_color + "&groundpickupauton=" + ground_pickup_auton + "&groundpickupteleop=" + ground_pickup_teleop + "&sourcepickupauton=" + source_pickup_auton + "&sourcepickupteleop=" + source_pickup_teleop + "&regularnoteauton=" + regular_note_auton + "&regularnoteteleop=" + regular_note_teleop;
        url = url + "&amplifiednote=" + amplified_note + "&speakernotesauton=" + speaker_notes_auton + "&speakernotesteleop=" + speaker_notes_teleop + "&ampnotesauton=" + amp_notes_auton + "&ampnotesteleop=" + amp_notes_teleop + "&drop=" + drop + "&sourcetospeaker=" + source_to_speaker + "&spotlight=" + spotlight;
        url = url + "&buddyclimb=" + buddy_climb + "&trap=" + trap + "&onstage=" + onstage + "&list=" + list;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Received response");
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Received error");
                System.out.println(error.getMessage());
            }
        });
        queue.add(stringRequest);
    }


    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

    }
}
