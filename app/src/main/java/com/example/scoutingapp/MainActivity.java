package com.example.scoutingapp;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.Locale;

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

    int amplified;
    int match_number;
    int team_number;

    // , , , , ground_pickup, source_pickup, amplified, drop, source_to_speaker, spotlight, buddy_climb, trap, onstage, list
    //variables for shaurya above

    /*int ground_pickup_autonv;
    int first_pickup_autonv;
    int second_pickup_autonv;*/
    String pickup_location_autonv="";
    /*int ground_pickup_teliopv;
    int first_pickup_teliopv;
    int second_pickup_teliopv;*/
    String pickup_location_teliopv="";

    //variables for data collection
    int source_pickup_autonv; int ground_pickup_autonv;
    int source_pickup_teleopv; int ground_pickup_teleopv;
    int speaker_autonv; int amp_autonv;
    int speaker_teleopv; int amp_teleopv;
    int amp_fail_autonv; int speaker_fail_autonv;
    int amp_fail_teleopv; int speaker_fail_teleopv;
    int drop_autonv; int drop_teleopv;
    int fail_autonv; int score_autonv;
    int fail_teliopv; int score_teliopv;
    boolean spotlightv; boolean buddy_climbv;
    boolean trapv; boolean onstagev; int blocksv;

    //other variables
    boolean sourcev;
    boolean groundv;
    boolean ampv;
    boolean speakerv;
    boolean autonv=true;
    boolean undov;
    int amp_scoredv=0;
    int speaker_scoredv=0;
    //button definitions
    String balance;
    TextView speakers_scored,amps_scored;
    ToggleButton switch_auton, switch_teleop;
    ToggleButton source_pickup, ground_pickup;
    ToggleButton speaker, amp;
    MaterialButton fail, score, undo, drop;
    Button succesful_spotlight, unsuccesful_spotlight;
    Button succesful_buddy_climb, unsuccesful_buddy_climb;
    Button succesful_trap, unsuccesful_trap;
    Button succesful_onstage, unsuccesful_onstage;
    Button up_times_blocked, down_times_blocked;

    ToggleButton region_1, region_2,region_3,region_4,region_5;
    Button submit;
    Button mButtonStartPause, mButtonReset;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        inputTextName=(EditText) findViewById(R.id.inputTextName);
        inputTextColor=(EditText) findViewById(R.id.inputTextColor);
        inputTextMatch=(EditText) findViewById(R.id.inputTextMatch);
        inputTextTeam=(EditText) findViewById(R.id.inputTextTeam);
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
        drop = (MaterialButton) findViewById(R.id.drop);
        //end game
        succesful_spotlight = (Button) findViewById(R.id.succesful_spotlight);
        unsuccesful_spotlight = (Button) findViewById(R.id.unsuccesful_spotlight);
        succesful_buddy_climb = (Button) findViewById(R.id.succesful_buddy_climb);
        unsuccesful_buddy_climb = (Button) findViewById(R.id.unsuccesful_buddy_climb);
        succesful_trap = (Button) findViewById(R.id.succesful_trap);
        unsuccesful_trap = (Button) findViewById(R.id.unsuccesful_trap);
        succesful_onstage = (Button) findViewById(R.id.succesful_onstage);
        unsuccesful_onstage = (Button) findViewById(R.id.unsuccesful_onstage);
        up_times_blocked = (Button) findViewById(R.id.up_times_blocked);
        down_times_blocked = (Button) findViewById(R.id.down_times_blocked);
        region_1 = (ToggleButton) findViewById(R.id.region_1);
        region_2 = (ToggleButton) findViewById(R.id.region_2);
        region_3 = (ToggleButton) findViewById(R.id.region_3);
        region_4 = (ToggleButton) findViewById(R.id.region_4);
        region_5 = (ToggleButton) findViewById(R.id.region_5);


        mTextViewCountDown = findViewById(R.id.text_view_countdown);
//		private static final long START_TIME_IN_MILLIS = 600000;
//		private TextView mTextViewCountDown;
//		private CountDownTimer mCountDownTimer;
//		private boolean mTimerRunning;
//		private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

        mButtonStartPause = (MaterialButton) findViewById(R.id.button_start_pause);
        mButtonReset = (MaterialButton) findViewById(R.id.button_reset);
        submit = (MaterialButton) findViewById(R.id.submit);
        // code below for submitting data to google sheet
<<<<<<< Updated upstream
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
////                saveData(name, matchnumber, teamnumber, alliance_color, pickup_location_autonv, pickup_location_teliopv,
////                        high_cube_teliopv, middle_cube_teliopv, low_cube_teliopv, high_cone_teliopv, middle_cone_teliopv,
////                        low_cone_teliopv, high_cube_autonv, middle_cube_autonv, low_cube_autonv, high_cone_autonv, middle_cone_autonv,
////                        low_cone_autonv, fail_teliopv, fail_autonv, num_of_links_teliopv, num_of_links_autonv, balance, robot_strengthv,
////                        robot_speedv, robot_maneuvarablev);
//            }
//        });
=======
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("submit pressed");
                String name = "amogh";
                int matchnumber = 76;
                int teamnumber = 7390;
                saveData(name, matchnumber, teamnumber, alliance_color, ground_pickup, source_pickup,
                        amplified, drop, source_to_speaker, spotlight, buddy_climb, trap, onstage, list);
            }
        });
>>>>>>> Stashed changes
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mTimerRunning) {
//                    pauseTimer();
//                } else {
//                    startTimer();
//                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();
        switch_auton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autonv=true;
                switch_auton.setChecked(true);
                switch_teleop.setChecked(false);
                amp_scoredv=0;
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
                if(ampv&&autonv)amp_fail_autonv++;
                else if(ampv&&!autonv)amp_fail_teleopv++;
                else if(speakerv&&autonv)speaker_fail_autonv++;
                else if(speakerv&&!autonv)speaker_fail_autonv++;
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
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autonv)drop_autonv++;
                else if(!autonv)drop_teleopv++;
                groundv=false;
                sourcev=false;
                ampv=false;
                speakerv=false;
                clearall();}});
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
    private void saveData(String name, int matchnumber, int teamnumber, String alliance_color, int ground_pickup, int source_pickup, int amplified, int drop, String source_to_speaker, boolean spotlight, boolean buddy_climb, boolean trap, boolean onstage, String list) {
        String url = "https://script.google.com/macros/s/AKfycbyAV85vNvfMll4YgmE9JHlQhQJE7TS4xcpiqCstvx_jC9MC8e907iphCl06l81oloAC/exec";
        url = url+"action=create&name="+name+"&matchnumber="+matchnumber+"&teamnumber="+teamnumber;
        url = url+"&color="+alliance_color+"&groundpickup="+ground_pickup+"&sourcepickup="+source_pickup;
        url = url+"&amplified="+amplified+"&drop="+drop+"&sourcetospeaker="+source_to_speaker+"&spotlight="+spotlight;
        url = url+"&buddyclimb="+buddy_climb+"&trap="+trap+"&onstage="+onstage+"&list="+list;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
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

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

//        //pickup location
//         switch (view.getId()){
//            case 1000022: //ground pickup auton
//        ground_pickup_autonv++;
//        break;
//        case 1000006: //1st pickup auton
//        first_pickup_autonv++;
//        break;
//        case 1000017: //2nd pickup auton
//        second_pickup_autonv++;
//        break;
//        case 1000024: //ground pickup teliop
//        ground_pickup_teliopv++;
//        break;
//        case 1000036: //1st pickup teliop
//        first_pickup_teliopv++;
//        break;
//        case 1000005: //2nd pickup teliop
//        second_pickup_teliopv++;
//        break;
//    }
//            //cone or cube
//        switch (view.getId()){
//            case 1000032: //cube_auton
//                cube_autonv = true;
//                break;
//            case 1000008: //cone_auton
//                cube_autonv = false;
//                break;
//            case 1000034: //cube_teliop
//                cube_teliopv = true;
//                break;
//            case 1000000: //cone_,teliop
//                cube_teliopv = false;
//                break;
//
//        }
//        //cone/cube placement location
//        switch (view.getId()){
//
//            case 1000013: //high auton
//                if(!cube_autonv)high_cone_autonv++;
//                else if(cube_autonv)high_cube_autonv++;
//                break;
//            case 1000019: //middle auton
//                if(!cube_autonv)middle_cone_autonv++;
//                else if(cube_autonv)middle_cube_autonv++;
//                break;
//            case 1000035: //low auton
//                if(!cube_autonv)low_cone_autonv++;
//                else if(cube_autonv)low_cube_autonv++;
//                break;
//            case 1000032: //high teliop
//                if(!cube_teliopv)high_cone_teliopv++;
//                else if(cube_teliopv)high_cube_teliopv++;
//                break;
//            case 1000007: //middle teliop
//                if(!cube_teliopv)middle_cone_teliopv++;
//                else if(cube_teliopv)middle_cube_teliopv++;
//                break;
//            case 1000004: //low teliop
//                if(!cube_autonv)low_cone_teliopv++;
//                else if(cube_autonv)low_cube_teliopv++;
//                break;
//        }
//score, fail, undo
//        switch (view.getId()){
//            case 1000048: /*fail auton*/
//                first_pickup_auton.setChecked(false);
//                second_pickup_auton.setChecked(false);
//                ground_pickup_auton.setChecked(false);
//                cone_auton.setChecked(false);
//                cube_auton.setChecked(false);
//                highgoal_auton.setChecked(false);
//                middlegoal_auton.setChecked(false);
//                lowgoal_auton.setChecked(false);
//                fail_autonv++;
//                break;
//
//            case 1000019: /*score auton*/
//                if(first_pickup_auton.isChecked())pickup_location_autonv="first";
//                if(second_pickup_auton.isChecked())pickup_location_autonv="second";
//                if(ground_pickup_auton.isChecked())pickup_location_autonv="ground";
//
//            case 1000020: /*score auton*/
//                if(first_pickup_auton.isChecked())first_pickup_autonv++;
//                if(second_pickup_auton.isChecked())second_pickup_autonv++;
//                if(ground_pickup_auton.isChecked())ground_pickup_autonv++;
//
//            case 1000019: /*score auton*/
//                if(first_pickup_auton.isChecked())first_pickup_autonv++;
//                if(second_pickup_auton.isChecked())second_pickup_autonv++;
//                if(ground_pickup_auton.isChecked())ground_pickup_autonv++;
//
//                if(cone_auton.isChecked())cone_autonv = true;
//                if(cube_auton.isChecked())cube_autonv = true;
//                if(highgoal_auton.isChecked()){
//                    if(!cube_autonv)high_cone_autonv++;
//                    else if(cube_autonv)high_cube_autonv++;
//                }
//                if(middlegoal_auton.isChecked()){
//                    if(!cube_autonv)middle_cone_autonv++;
//                    else if(cube_autonv)middle_cube_autonv++;
//                }
//                if(lowgoal_auton.isChecked()) {
//                    if (!cube_autonv) low_cone_autonv++;
//                    else if (cube_autonv) low_cube_autonv++;
//                }
//                    first_pickup_auton.setChecked(false);
//                    second_pickup_auton.setChecked(false);
//                    ground_pickup_auton.setChecked(false);
//                    cone_auton.setChecked(false);
//                    cube_auton.setChecked(false);
//                    highgoal_auton.setChecked(false);
//                    middlegoal_auton.setChecked(false);
//                    lowgoal_auton.setChecked(false);
//                break;
//            case 1000050: /*undo auton*/
//                first_pickup_auton.setChecked(false);
//                second_pickup_auton.setChecked(false);
//                ground_pickup_auton.setChecked(false);
//                cone_auton.setChecked(false);
//                cube_auton.setChecked(false);
//                highgoal_auton.setChecked(false);
//                middlegoal_auton.setChecked(false);
//                lowgoal_auton.setChecked(false);
//                break;
////            /*case 1000060: /*fail teliop*/
////                first_pickup_teliop.setChecked(false);
////                second_pickup_teliop.setChecked(false);
////                ground_pickup_teliop.setChecked(false);
////                cone_teliop.setChecked(false);
////                cube_teliop.setChecked(false);
////                highgoal_teliop.setChecked(false);
////                middlegoal_teliop.setChecked(false);
////                lowgoal_teliop.setChecked(false);
////                fail_teliopv++;
////                break;
////
////            case 1000011: /*score teliop*/
////                if(first_pickup_teliop.isChecked())pickup_location_teliopv="first";
////                if(second_pickup_teliop.isChecked())pickup_location_teliopv="second";
////                if(ground_pickup_teliop.isChecked())pickup_location_teliopv="ground";
////
////            case 1000011: /*score teliop*/
////                if(first_pickup_teliop.isChecked())first_pickup_teliopv++;
////                if(second_pickup_teliop.isChecked())second_pickup_teliopv++;
////                if(ground_pickup_teliop.isChecked())ground_pickup_teliopv++;
////
////            case 1000038: /*score teliop*/
////                if(first_pickup_teliop.isChecked())first_pickup_teliopv++;
////                if(second_pickup_teliop.isChecked())second_pickup_teliopv++;
////                if(ground_pickup_teliop.isChecked())ground_pickup_teliopv++;
////
////                if(cone_teliop.isChecked())cone_teliopv = true;
////                if(cube_teliop.isChecked())cube_teliopv = true;
////                if(highgoal_teliop.isChecked()){
////                    if(!cube_autonv)high_cone_teliopv++;
////                    else if(cube_autonv)high_cube_teliopv++;
////                }
////                if(middlegoal_teliop.isChecked()){
////                    if(!cube_autonv)middle_cone_teliopv++;
////                    else if(cube_autonv)middle_cube_teliopv++;
////                }
////                if(lowgoal_teliop.isChecked()) {
////                    if(!cube_autonv)low_cone_teliopv++;
////                    else if(cube_autonv)low_cube_teliopv++;
////                }
//
//                first_pickup_teliop.setChecked(false);
//                second_pickup_teliop.setChecked(false);
//                ground_pickup_teliop.setChecked(false);
//                cone_teliop.setChecked(false);
//                cube_teliop.setChecked(false);
//                highgoal_teliop.setChecked(false);
//                middlegoal_teliop.setChecked(false);
//                lowgoal_teliop.setChecked(false);
//                break;
//            case 1000044: /*undo teliop*/
//                first_pickup_teliop.setChecked(false);
//                second_pickup_teliop.setChecked(false);
//                ground_pickup_teliop.setChecked(false);
//                cone_teliop.setChecked(false);
//                cube_teliop.setChecked(false);
//                highgoal_teliop.setChecked(false);
//                middlegoal_teliop.setChecked(false);
//                lowgoal_teliop.setChecked(false);
//                break;
//        }
//        //successful or unsuccessful balance
//        switch (view.getId()){
//            case 1000039:
//                balance = "yes";
//                break;
//            case 1000030:
//                balance = "no";
//                break;
//        }
//        //robot strength
//        switch (view.getId()){
//            case 1000001:
//                robot_strengthv=1;
//            break;
//            case 1000008:
//                robot_strengthv=2;
//            break;
//            case 1000009:
//                robot_strengthv=3;
//                break;
//            case 1000004:
//                robot_strengthv=4;
//                break;
//            case 1000005:
//                robot_strengthv=5;
//                break;
//        }
//        //robot speed
//        switch (view.getId()){
//            case 1000055:
//                robot_speedv=1;
//                break;
//            case 1000052:
//                robot_speedv=2;
//                break;
//            case 1000053:
//                robot_speedv=3;
//                break;
//            case 1000059:
//                robot_speedv=4;
//                break;
//            case 1000061:
//                robot_speedv=5;
//                break;
//        }
//        //robot maneuverability
//        switch (view.getId()){
//            case 1000011:
//                robot_maneuvarablev=1;
//                break;
//            case 1000015:
//                robot_maneuvarablev=2;
//                break;
//            case 1000016:
//                robot_maneuvarablev=3;
//                break;
//            case 1000013:
//                robot_maneuvarablev=4;
//                break;
//            case 1000014:
//                robot_maneuvarablev=5;
//                break;
//        }
    }
}
