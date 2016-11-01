package com.company.umutmucahit.competitiontime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Game Activity- The main game activity
 * Created by UmutMucahit on 5/10/2015.
 */

public class Game extends ActionBarActivity implements View.OnClickListener {

    Button ans1, ans2, ans3, ans4;
    TextView question, questionNumber, timer, score, coins;
    ImageButton fifty, x2, skip;
    ImageView multiplier;
    Question q;
    boolean mult;
    CountDownTimer time;
    SQLiteDatabase db;
    SQLiteDatabase leaderboard;
    long timeleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Another check for the category finished.
        if (TitleScreen.isFinished)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.TryAgain"));
        setContentView(R.layout.activity_game);
        // Set the multiplier boolean to true
        mult = true;

        // Initialize the UI components.
        ans1 = (Button) findViewById(R.id.ans1);
        ans2 = (Button) findViewById(R.id.ans2);
        ans3 = (Button) findViewById(R.id.ans3);
        ans4 = (Button) findViewById(R.id.ans4);
        question = (TextView) findViewById(R.id.question);
        questionNumber = (TextView) findViewById(R.id.qnumber);
        timer = (TextView) findViewById(R.id.countDown);
        fifty = (ImageButton) findViewById(R.id.fifty);
        x2 = (ImageButton) findViewById(R.id.x2);
        skip = (ImageButton) findViewById(R.id.change);
        multiplier =(ImageView) findViewById(R.id.multiplier);
        score = (TextView) findViewById(R.id.score);


        // Set the question text and the answers.
        if (TitleScreen.scan.hasNext()) {
            q = new Question(TitleScreen.scan.nextLine(), TitleScreen.scan.nextLine(), TitleScreen.scan.nextLine(), TitleScreen.scan.nextLine(), TitleScreen.scan.nextLine(), TitleScreen.scan.nextLine());
            question.setText(q.getQuestion());
            ans1.setText(q.getAnswer1());
            ans2.setText(q.getAnswer2());
            ans3.setText(q.getAnswer3());
            ans4.setText(q.getAnswer4());
        }
        //Set the score
        score.setText("Score: " + TitleScreen.score);

        //Set the coin
        // coins.setText("" + TitleScreen.user.coins);

        // Check if the text file is over and determine if the category is over.
        if (!TitleScreen.scan.hasNext())
            TitleScreen.isFinished = true;

        // Check the id that was initialized in the ChooseCategory activity and determine the round,
        // question number for the question and check the round number in order to unlock the achievement.
        // Then update the database.
        if (TitleScreen.id == R.raw.bilkent) {
            questionNumber.setText("Question " + (ChooseCategory.count));
            if (ChooseCategory.skip == TitleScreen.user.bilcount / 10)
            {
                show("Round " + (ChooseCategory.skip + 1));
                ChooseCategory.skip = -27;
            }
            else if (TitleScreen.user.bilcount % 10 == 1) {
                show("Round " + (TitleScreen.user.bilcount / 10 + 1));
                if (TitleScreen.user.completeRoundEarned.equals("0")) {
                    TitleScreen.user.completeRoundEarned = "1";
                    TitleScreen.user.setAchievements();
                    show(TitleScreen.user.completeRound.toString());
                }
            }
        }
        if (TitleScreen.id == R.raw.geography) {
            questionNumber.setText("Question " + ((TitleScreen.user.geocount / 10 * 10 + 1)));
            if (ChooseCategory.skip == TitleScreen.user.geocount / 10)
            {
                show("Round " + (ChooseCategory.skip + 1));
                ChooseCategory.skip = -27;
            }
            else if (TitleScreen.user.geocount % 10 == 1) {
                show("Round " + (TitleScreen.user.geocount / 10 + 1));
                if (TitleScreen.user.completeRoundEarned.equals("0")) {
                    TitleScreen.user.completeRoundEarned = "1";
                    TitleScreen.user.setAchievements();
                    show(TitleScreen.user.completeRound.toString());
                }
            }
        }
        if (TitleScreen.id == R.raw.general) {
            questionNumber.setText("Question " + ((ChooseCategory.count)));
            if (ChooseCategory.skip == TitleScreen.user.gencount / 10)
            {
                show("Round " + (ChooseCategory.skip + 1));
                ChooseCategory.skip = -27;
            }
            else if (TitleScreen.user.gencount % 10 == 1) {
                show("Round " + (TitleScreen.user.gencount / 10 + 1));
                if (TitleScreen.user.completeRoundEarned.equals("0")) {
                    TitleScreen.user.completeRoundEarned = "1";
                    TitleScreen.user.setAchievements();
                    show(TitleScreen.user.completeRound.toString());
                }
            }
        }
        if (TitleScreen.id == R.raw.math) {
            questionNumber.setText("Question " + ((ChooseCategory.count)));
            if (ChooseCategory.skip == TitleScreen.user.mathcount / 10)
            {
                show("Round " + (ChooseCategory.skip + 1));
                ChooseCategory.skip = -27;
            }
            else if (TitleScreen.user.mathcount % 10 == 1) {
                show("Round " + (TitleScreen.user.mathcount / 10 + 1));
                if (TitleScreen.user.completeRoundEarned.equals("0")) {
                    TitleScreen.user.completeRoundEarned = "1";
                    TitleScreen.user.setAchievements();
                    show(TitleScreen.user.completeRound.toString());
                }
            }
        }
        if (TitleScreen.id == R.raw.programming) {
            questionNumber.setText("Question " + (ChooseCategory.count));
            if (ChooseCategory.skip == TitleScreen.user.progcount / 10)
            {
                show("Round " + (ChooseCategory.skip + 1));
                ChooseCategory.skip = -27;
            }
            else if (TitleScreen.user.progcount % 10 == 1) {
                show("Round " + (TitleScreen.user.progcount / 10 + 1));
                if (TitleScreen.user.completeRoundEarned.equals("0")) {
                    TitleScreen.user.completeRoundEarned = "1";
                    TitleScreen.user.setAchievements();
                    show(TitleScreen.user.completeRound.toString());
                }
            }
        }
        if (TitleScreen.id == R.raw.sports) {
            questionNumber.setText("Question " + (ChooseCategory.count));
            if (ChooseCategory.skip == TitleScreen.user.sportscount / 10)
            {
                show("Round " + (ChooseCategory.skip + 1));
                ChooseCategory.skip = -27;
            }
            else if (TitleScreen.user.sportscount % 10 == 1) {
                show("Round " + (TitleScreen.user.sportscount / 10 + 1));
                if (TitleScreen.user.completeRoundEarned.equals("0")) {
                    TitleScreen.user.completeRoundEarned = "1";
                    TitleScreen.user.setAchievements();
                    show(TitleScreen.user.completeRound.toString());
                }
            }
        }
        if (TitleScreen.id == R.raw.history) {
            questionNumber.setText("Question " + (ChooseCategory.count));
            if (ChooseCategory.skip == TitleScreen.user.histcount / 10)
            {
                show("Round " + (ChooseCategory.skip + 1));
                ChooseCategory.skip = -27;

            }
            else if (TitleScreen.user.histcount % 10 == 1) {
                show("Round " + (TitleScreen.user.histcount / 10 + 1));
                if (TitleScreen.user.completeRoundEarned.equals("0")) {
                    TitleScreen.user.completeRoundEarned = "1";
                    TitleScreen.user.setAchievements();
                    show(TitleScreen.user.completeRound.toString());
                }
            }
        }
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        TitleScreen.user.updateDB(db);
        db.close();

        // Set the coin number.
        //coins.setText(TitleScreen.user.coins + "");

        // Check if the bonuses were used, if they were erase them from the screen.
        if (TitleScreen.x2)
            x2.setVisibility(View.INVISIBLE);
        if (TitleScreen.fifty)
            fifty.setVisibility(View.INVISIBLE);
        if (TitleScreen.skip)
            skip.setVisibility(View.INVISIBLE);

        fifty.setOnClickListener(this);
        x2.setOnClickListener(this);
        skip.setOnClickListener(this);
        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);


        // Start the timer for the question from 30 seconds.
        time = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 + "");
                timeleft = millisUntilFinished / 1000;

                // Get rid of the multiplier indicator after 3 seconds have passed and make it false.
                if (millisUntilFinished <= 27000) {
                    mult = false;
                    multiplier.setVisibility(View.INVISIBLE);
                }
                if (millisUntilFinished < 2000)
                    startActivity(new Intent("com.company.umutmucahit.competitiontime.TryAgain"));
            }

            @Override
            public void onFinish() {
            }
        };
        // Start the timer.
        time.start();

    }

    @Override
    public void onClick(View v) {
        final View source = v;

        // Check which button was pressed.
        if (v == x2) {
            // Set the boolean to true in order to check it later
            TitleScreen.x2 = true;
            v.setVisibility(View.INVISIBLE);
        } else if (v == fifty) {
            // Set boolean to true, get rid of 2 incorrect answers (make them invisible)
            TitleScreen.fifty = true;
            int i = 0;
            v.setVisibility(View.INVISIBLE);

            if (!ans3.getText().equals(q.getCorrect()) && i < 2) {
                ans3.setVisibility(View.INVISIBLE);
                i++;
            }
            if (!ans1.getText().equals(q.getCorrect()) && i < 2) {
                ans1.setVisibility(View.INVISIBLE);
                i++;
            }
            if (!ans4.getText().equals(q.getCorrect()) && i < 2) {
                ans4.setVisibility(View.INVISIBLE);
                i++;
            }
            if (!ans2.getText().equals(q.getCorrect()) && i < 2) {
                ans2.setVisibility(View.INVISIBLE);
                i++;
            }

        }
        // If the user wanted to skip the question, check the category,
        // update the user progress and start another Game activity. Also update the database.
        else if (v == skip) {
            if (TitleScreen.id == R.raw.bilkent)
                TitleScreen.user.bilcount++;
            if (TitleScreen.id == R.raw.geography)
                TitleScreen.user.geocount++;
            if (TitleScreen.id == R.raw.general)
                TitleScreen.user.gencount++;
            if (TitleScreen.id == R.raw.programming)
                TitleScreen.user.progcount++;
            if (TitleScreen.id == R.raw.math)
                TitleScreen.user.mathcount++;
            if (TitleScreen.id == R.raw.history)
                TitleScreen.user.histcount++;
            if (TitleScreen.id == R.raw.sports)
                TitleScreen.user.sportscount++;
            ChooseCategory.count++;
            SQLiteDatabase db = openOrCreateDatabase("db", MODE_PRIVATE, null);
            TitleScreen.user.updateDB(db);
            db.close();
            TitleScreen.skip = true;

            startActivity(new Intent("com.company.umutmucahit.competitiontime.Game"));


        }
        // If the user gave the correct answer.
        else if (((Button) v).getText().equals(q.getCorrect())) {
            // Check the multiplier and update the score.
            if (mult)
                TitleScreen.score += 3 * timeleft;
            else
                TitleScreen.score += timeleft;

            // Update the coins.
            TitleScreen.user.coins += 1;

            // Increase the user progress and update the database.
            if (TitleScreen.id == R.raw.bilkent)
                TitleScreen.user.bilcount++;
            if (TitleScreen.id == R.raw.geography)
                TitleScreen.user.geocount++;
            if (TitleScreen.id == R.raw.general)
                TitleScreen.user.gencount++;
            if (TitleScreen.id == R.raw.programming)
                TitleScreen.user.progcount++;
            if (TitleScreen.id == R.raw.math)
                TitleScreen.user.mathcount++;
            if (TitleScreen.id == R.raw.history)
                TitleScreen.user.histcount++;
            if (TitleScreen.id == R.raw.sports)
                TitleScreen.user.sportscount++;
            ChooseCategory.count++;

            // Set the button to green and start another activity based on the category finished.
            source.setBackgroundColor(Color.GREEN);
            if (TitleScreen.effects) {
                MediaPlayer player = MediaPlayer.create(this, R.raw.win);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }

                });
                player.start();
            }
            if (!TitleScreen.isFinished)
                startActivity(new Intent("com.company.umutmucahit.competitiontime.Game"));
            else
                startActivity(new Intent("com.company.umutmucahit.competitiontime.TryAgain"));
        }

        // If the user pressed x2 before, make the incorrect answer invisible and incraese
        // the answer count so the user cannot use it again
        else if (TitleScreen.x2 && TitleScreen.x2count != 1) {
            v.setVisibility(View.INVISIBLE);
            TitleScreen.x2count = 1;
        } else {
            // Set button color to red.
            source.setBackgroundColor(Color.RED);
            if (TitleScreen.effects) {
                MediaPlayer player = MediaPlayer.create(this, R.raw.lose);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }

                });
                player.start();
                startActivity(new Intent("com.company.umutmucahit.competitiontime.TryAgain"));
            }

            // Update the database in the end
            SQLiteDatabase db = openOrCreateDatabase("db", MODE_PRIVATE, null);
            TitleScreen.user.updateDB(db);
            db.close();
        }
    }

    // Show a dialog box to see if the user wants to get back to the main menu.
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit to main menu?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
        if (isFinishing())
            TitleScreen.player.pause();
        else if (TitleScreen.music)
            TitleScreen.player.start();
    }

    // Stop and start the music player on pause and resume.
    @Override
    protected void onPause() {
        super.onPause();
        time.cancel();
        finish();
        TitleScreen.player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TitleScreen.music)
            TitleScreen.player.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void show (String str) {
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
    }
}
