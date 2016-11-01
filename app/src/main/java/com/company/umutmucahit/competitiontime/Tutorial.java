package com.company.umutmucahit.competitiontime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Tutorial - Shows the basic elements and bonuses of the game.
 * @author: Umut Mucahit Koksaldi
 * @version: 1.00 4/24/2015
 */

public class Tutorial extends ActionBarActivity implements View.OnClickListener {

    Button contButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // Initialize the button to continue.
        contButton = (Button) findViewById(R.id.contButton);
        contButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // When the user presses the button, bring up the choosecategory activity.
        if (v == contButton)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.ChooseCategory"));
    }

    // Stop and start the music player on pause and resume.
    @Override
    protected void onPause() {
        super.onPause();
        TitleScreen.player.pause();
        finish();
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
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
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
}
