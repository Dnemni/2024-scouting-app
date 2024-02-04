package com.example.scoutingapp;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    EditText inputTextName;
    EditText inputTextColor;
    EditText inputTextMatch;
    EditText inputTextTeam;
    void clearall(){
        source_pickup.setChecked(false);
        ground_pickup.setChecked(false);
        amp.setChecked(false);
        speaker.setChecked(false);
    }
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    //variables for shaurya below
    String name= "";
    String alliance_color = "";

    int match_number;
    int team_number;

    //variables for data collection
    int source_pickup_autonv; int ground_pickup_autonv;
    int source_pickup_teleopv; int ground_pickup_teleopv;
    int speaker_autonv; int amp_autonv;
    int speaker_teleopv; int amp_teleopv; int amplified_speaker_teleopv = 0; int regular_notes;
    int amp_fail_autonv; int speaker_fail_autonv;
    int amp_fail_teleopv; int speaker_fail_teleopv;
    boolean spotlightv; boolean buddy_climbv;
    boolean trapv; boolean onstagev; int blocksv;

    //other variables
    boolean sourcev; boolean groundv;
    boolean ampv; boolean speakerv;
    boolean autonv=true;
    int speaker_scoredv=0; int amp_scoredv=0; // for display use only not actual data sent to sheet
    //button definitions
    String balance;
    TextView speakers_scored,amps_scored;
    ToggleButton switch_auton, switch_teleop;
    ToggleButton source_pickup, ground_pickup; //CHECK MATCHING UP
    ToggleButton speaker, amp;
    MaterialButton fail, score, undo/*,drop*/;
    MaterialButton succesful_spotlight, unsuccesful_spotlight;
    MaterialButton succesful_buddy_climb, unsuccesful_buddy_climb;
    MaterialButton succesful_trap, unsuccesful_trap;
    MaterialButton succesful_onstage, unsuccesful_onstage;
    MaterialButton up_times_blocked, down_times_blocked;
    ToggleButton region_1, region_2,region_3,region_4,region_5;
    Button submit;
    Button mButtonStartPause, mButtonReset;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
//        inputTextName=(EditText) findViewById(R.id.inputTextName);
//        inputTextColor=(EditText) findViewById(R.id.inputTextColor);
//        inputTextMatch=(EditText) findViewById(R.id.inputTextMatch);
//        inputTextTeam=(EditText) findViewById(R.id.inputTextTeam);
//text views below
        speakers_scored = findViewById(R.id.speakers_scored);
        amps_scored = findViewById(R.id.amps_scored);
//toggle buttons below
        switch_auton = (ToggleButton) findViewById(R.id.switch_auton);
        switch_teleop = (ToggleButton) findViewById(R.id.switch_teleop);
        source_pickup = (ToggleButton) findViewById(R.id.source_pickup);
        ground_pickup = (ToggleButton) findViewById(R.id.ground_pickup);
        speaker = (ToggleButton) findViewById(R.id.speaker);
        amp = (ToggleButton) findViewById(R.id.amp);
//material buttons
        fail = (MaterialButton) findViewById(R.id.fail);
        score = (MaterialButton) findViewById(R.id.score);
        undo = (MaterialButton) findViewById(R.id.undo);
//        drop = (MaterialButton) findViewById(R.id.drop);
        //end game
        succesful_spotlight = (MaterialButton) findViewById(R.id.succesful_spotlight);
        unsuccesful_spotlight = (MaterialButton) findViewById(R.id.unsuccesful_spotlight);
        succesful_buddy_climb = (MaterialButton) findViewById(R.id.succesful_buddy_climb);
        unsuccesful_buddy_climb = (MaterialButton) findViewById(R.id.unsuccesful_buddy_climb);
        succesful_trap = (MaterialButton) findViewById(R.id.succesful_trap);
        unsuccesful_trap = (MaterialButton) findViewById(R.id.unsuccesful_trap);
        succesful_onstage = (MaterialButton) findViewById(R.id.succesful_onstage);
        unsuccesful_onstage = (MaterialButton) findViewById(R.id.unsuccesful_onstage);
        up_times_blocked = (MaterialButton) findViewById(R.id.up_times_blocked);
        down_times_blocked = (MaterialButton) findViewById(R.id.down_times_blocked);
        region_1 = (ToggleButton) findViewById(R.id.region_1);
        region_2 = (ToggleButton) findViewById(R.id.region_2);
        region_3 = (ToggleButton) findViewById(R.id.region_3);
        region_4 = (ToggleButton) findViewById(R.id.region_4);
        region_5 = (ToggleButton) findViewById(R.id.region_5);
//      mTextViewCountDown = findViewById(R.id.text_view_countdown);
//		private static final long START_TIME_IN_MILLIS = 600000;
//		private TextView mTextViewCountDown;
//		private CountDownTimer mCountDownTimer;
//		private boolean mTimerRunning;
//		private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        mButtonStartPause = (MaterialButton) findViewById(R.id.button_start_pause);
//        mButtonReset = (MaterialButton) findViewById(R.id.button_reset);
        submit = (MaterialButton) findViewById(R.id.submit);
        // code below for submitting data to google sheet

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = "amogh";
//                int matchnumber = 76;
//                int teamnumber = 7390;
//                saveData(name, matchnumber, teamnumber, source_pickup, pickup_location_autonv, pickup_location_teliopv,
//                         fail_teliopv, fail_autonv, balance, robot_strengthv,
//                        robot_speedv, robot_maneuvarablev);
//
//               saveData(name, matchnumber, teamnumber, alliance_color, pickup_location_autonv, pickup_location_teliopv,
//                     high_cube_teliopv, middle_cube_teliopv, low_cube_teliopv, high_cone_teliopv, middle_cone_teliopv,
//                     low_cone_teliopv, high_cube_autonv, middle_cube_autonv, low_cube_autonv, high_cone_autonv, middle_cone_autonv,
//                      low_cone_autonv, fail_teliopv, fail_autonv, num_of_links_teliopv, num_of_links_autonv, balance, robot_strengthv,
//                        robot_speedv, robot_maneuvarablev);
//            }
//        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("submit pressed");
//                String name = "amogh";
//                int matchnumber = 76;
//                int teamnumber = 7390;
//                saveData(name, matchnumber, teamnumber, alliance_color, ground_pickup, source_pickup,
//                        amplified, drop, source_to_speaker, spotlight, buddy_climb, trap, onstage, list);
//            }
//        });

//        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if (mTimerRunning) {
////                    pauseTimer();
////                } else {
////                    startTimer();
////                }
//            }
//        });
//        mButtonReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resetTimer();
//            }
//        });
//
//        updateCountDownText();
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
            }
        });
        switch_teleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autonv=false;
                switch_auton.setChecked(false);
                switch_teleop.setChecked(true);}});
        source_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourcev=true;
                groundv=false;
                ground_pickup.setChecked(false);}});
        ground_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groundv=true;
                sourcev=false;
                source_pickup.setChecked(false);}});
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
                if(ampv&&autonv) {
                    amp_fail_autonv++;
                } else if (ampv&&!autonv) {
                    amp_fail_teleopv++;
                } else if(speakerv&&autonv) {
                    speaker_fail_autonv++;
                } else if(speakerv&&!autonv) {speaker_fail_autonv++;}
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
                }
                else if(ampv&&!autonv){
                    amplified_speaker_teleopv++;
                }
                else if(!ampv&&!autonv){
                    //reg_notesv++;
                }
                else if(speakerv&&autonv){
                    speaker_autonv++;
                }
                else if(speakerv&&!autonv){
                    speaker_teleopv++;
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
                    amplified_speaker_teleopv++;
                    amps_scored.setText(String.valueOf(amplified_speaker_teleopv));
                }
                groundv=false;
                sourcev=false;
                ampv=false;
                speakerv=false;
                clearall();}});
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                }
                else if(ampv&&!autonv){
                    amp_teleopv++;
                }
                else if(speakerv&&autonv){
                    speaker_autonv++;
                }
                else if(speakerv&&!autonv){
                    speaker_teleopv++;
                }
                //pickup location
                if(groundv&&autonv)ground_pickup_autonv++;
//                else if(groundv&&!autonv) ground_pickup_teleopv++;
//                else if(sourcev&&autonv)source_pickup_autonv++;
//                else if(sourcev&&!autonv)source_pickup_teleopv++;
                //scoring location
                if(ampv&&autonv)amp_autonv++;
//                else if(ampv&&!autonv)amp_teleopv++;
//                else if(speakerv&&autonv)speaker_autonv++;
//                else if(speakerv&&!autonv)speaker_teleopv++;


                if(speakerv){
                    speaker_scoredv++;
                    speakers_scored.setText(String.valueOf(speaker_scoredv));                }
                if(ampv){
                    amp_scoredv++;
                    amps_scored.setText(String.valueOf(amp_scoredv));
                }
                groundv=false;
                sourcev=false;
                ampv=false;
                speakerv=false;
                clearall();}});
//        score.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(groundv&&autonv){
//                    ground_pickup_autonv++;
//                }
//                else if(groundv&&!autonv){
//                    ground_pickup_teleopv++;
//                }
//                else if(sourcev&&autonv){
//                    source_pickup_autonv++;
//                }
//                else if(sourcev&&!autonv){
//                    source_pickup_teleopv++;
//                }
//                if(ampv&&autonv){
//                    amp_autonv++;
//                }
//                else if(ampv&&!autonv){
//                    amp_teleopv++;
//                }
//                else if(speakerv&&autonv){
//                    speaker_autonv++;
//                }
//                else if(speakerv&&!autonv){
//                    speaker_teleopv++;
//                }
//                //pickup location
//                if(groundv&&autonv)ground_pickup_autonv++;
////                else if(groundv&&!autonv) ground_pickup_teleopv++;
////                else if(sourcev&&autonv)source_pickup_autonv++;
////                else if(sourcev&&!autonv)source_pickup_teleopv++;
//                //scoring location
//                if(ampv&&autonv)amp_autonv++;
////                else if(ampv&&!autonv)amp_teleopv++;
////                else if(speakerv&&autonv)speaker_autonv++;
////                else if(speakerv&&!autonv)speaker_teleopv++;
//
//
//                if(speakerv){
//                    speaker_scoredv ++;
//                    speakers_scored.setText(String.valueOf(speaker_scoredv));                }
//                if(ampv){
//                    amp_scoredv ++;
//                    amps_scored.setText(String.valueOf(amp_scoredv));
//                }
//                groundv=false;
//                sourcev=false;
//                ampv=false;
//                speakerv=false;
//                clearall();}});
//        drop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(autonv) {
//                    drop_autonv++;
//                } else if(!autonv) {drop_teleopv++;}
//                groundv=false;
//                sourcev=false;
//                ampv=false;
//                speakerv=false;
//                clearall();}});
        succesful_spotlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spotlightv=true;}});
        unsuccesful_spotlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spotlightv=false;
            }
        });
        succesful_buddy_climb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddy_climbv=true;
            }
        });
        unsuccesful_buddy_climb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddy_climbv=false;
            }
        });
        succesful_trap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trapv=true;
            }
        });
        unsuccesful_trap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trapv=false;
            }
        });
        succesful_onstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onstagev=true;
            }
        });
        unsuccesful_onstage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onstagev=false;
            }
        });
        up_times_blocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blocksv++;
            }
        });
        down_times_blocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blocksv--;
            }
        });
        region_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        region_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  num_of_links_autonv++;
            }
        });
        region_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   num_of_links_autonv--;
            }
        });
        region_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  num_of_links_teliopv++;
            }
        });
        region_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  num_of_links_teliopv--;
            }
        });
//        succesful_balance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                balance = "yes";
//            }
//        });
//        unsuccesful_balance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                balance = "no";
//            }
//        });
//        strength_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_strengthv = 1;
//            }
//        });
//        strength_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_strengthv = 2;
//            }
//        });
//        strength_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_strengthv = 3;
//            }
//        });
//        strength_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_strengthv = 4;
//            }
//        });
//        strength_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_strengthv = 5;
//            }
//        });
//        speed_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_speedv = 1;
//            }
//        });
//        speed_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_speedv = 2;
//            }
//        });
//        speed_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_speedv = 3;
//            }
//        });
//        speed_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_speedv = 4;
//            }
//        });
//        speed_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_speedv = 5;
//            }
//        });
//        maneuvering_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_maneuvarablev = 1;
//            }
//        });
//        maneuvering_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_maneuvarablev = 2;
//            }
//        });
//        maneuvering_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_maneuvarablev = 3;
//            }
//        });
//        maneuvering_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_maneuvarablev = 5;
//            }
//        });
//        maneuvering_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                robot_maneuvarablev = 4;
//            }
//        });

// name, matchnumber, teamnumber, alliance_color, ground_pickup, source_pickup, amplified, drop, source_to_speaker, spotlight, buddy_climb, trap, onstage, list
    }
    private void saveData(String name, int matchnumber, int teamnumber, String alliance_color, int ground_pickup_auton, int ground_pickup_teleop, int source_pickup_auton, int source_pickup_teleop, int regular_note_auton, int regular_note_teleop, int amplified_note, int speaker_notes_auton, int speaker_notes_teleop, int amp_notes_auton, int amp_notes_teleop, int drop, String source_to_speaker, boolean spotlight, boolean buddy_climb, boolean trap, boolean onstage, String list) {
        String url = "https://script.google.com/macros/s/AKfycbwEq0zDFuf0nxK62CI65RkVFZelua9lvjGHePq5gHoro8K2lEzJL_8mbzPBY2xELl6Q/exec";
        url = url + "action=create&name=" + name + "&matchnumber=" + matchnumber + "&teamnumber=" + teamnumber;
        url = url + "&color=" + alliance_color + "&groundpickupauton=" + ground_pickup_auton + "&groundpickupteleop=" + ground_pickup_teleop + "&sourcepickupauton=" + source_pickup_auton + "&sourcepickupteleop=" + source_pickup_teleop + "&regularnoteauton=" + regular_note_auton + "&regularnoteteleop=" + regular_note_teleop;
        url = url + "&amplifiednote=" + amplified_note + "&speakernotesauton=" + speaker_notes_auton + "&speakernotesteleop=" + speaker_notes_teleop + "&ampnotesauton=" + amp_notes_auton + "&ampnotesteleop=" + amp_notes_teleop + "&drop=" + drop + "&sourcetospeaker=" + source_to_speaker + "&spotlight=" + spotlight;
        url = url + "&buddyclimb=" + buddy_climb + "&trap=" + trap + "&onstage=" + onstage + "&list=" + list;
    }
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//    }
//    void assignId(MaterialButton btn, int id) {
//        btn = findViewById(id);
//        btn.setOnClickListener(this);
//    }
//    private void startTimer() {
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
//                mButtonStartPause.setText("Start");
//                mButtonStartPause.setVisibility(View.INVISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);
//            }
//        }.start();
//
//        mTimerRunning = true;
//        mButtonStartPause.setText("pause");
//        mButtonReset.setVisibility(View.INVISIBLE);
//    }
//    private void pauseTimer() {
//        mCountDownTimer.cancel();
//        mTimerRunning = false;
//        mButtonStartPause.setText("Start");
//        mButtonReset.setVisibility(View.VISIBLE);
//    }
//    private void resetTimer() {
//        mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        updateCountDownText();
//        mButtonReset.setVisibility(View.INVISIBLE);
//        mButtonStartPause.setVisibility(View.VISIBLE);
//    }
//    private void updateCountDownText() {
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//
//        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//
//        mTextViewCountDown.setText(timeLeftFormatted);
//    }
    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
    }
}