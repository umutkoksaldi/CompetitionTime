package com.company.umutmucahit.competitiontime;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Player class - Holds all of the variables for the user inside the class.
 * Created by UmutMucahit on 5/10/2015.
 */
public class Player {

    private String name;
    private String password;

    // Hardcoded achievements
    Achievement welcome = new Achievement("The First One is Free", "Open up the main menu for the first time");
    Achievement completeRound = new Achievement("Round Complete","Complete your first round");
    Achievement bilcomplete = new Achievement("Bilkent Complete", "Complete all questions in the Bilkent category");
    Achievement gencomplete = new Achievement("General Knowledge Complete", "Complete all questions in the General Knowledge category");;
    Achievement geocomplete = new Achievement("Geography Complete", "Complete all questions in the Geography category");;
    Achievement histcomplete = new Achievement("History Complete", "Complete all questions in the History category");;
    Achievement progcomplete = new Achievement("Programming Complete", "Complete all questions in the Programming category");;
    Achievement mathcomplete = new Achievement("Math Complete", "Complete all questions int the Math category");;
    Achievement sportscomplete = new Achievement("Sports Complete", "Complete all questions in the Sports category");;
    Achievement credits = new Achievement("We Made It!", "Visit the credits page");;

    private Achievement[] achievements = new Achievement[10];

    // achievement check values
    String welcomeEarned;
    String completeRoundEarned;
    String bilkentCompleted;
    String generalCompleted;
    String geographyCompleted;
    String historyCompleted;
    String programmingCompleted;
    String mathCompleted;
    String sportsCompleted;
    String creditsVisited;

    // progress counters.
    int bilcount;
    int progcount;
    int gencount;
    int geocount;
    int mathcount;
    int sportscount;
    int histcount;

    // An extra unlock system check variables (not part of the original project, did not have the time to implement.)
    int bilunlocked;
    int progunlocked;
    int genunlocked;
    int geounlocked;
    int mathunlocked;
    int sportsunlocked;
    int histunlocked;

    int coins;

    private Cursor c;

    // Constructor initializes the name and the password of the user.
    public Player(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    // Sets all variables from the database.
    public void setValues(SQLiteDatabase db)
    {
        // Open the database and get the cursor for the user.
        c = db.rawQuery("select * from login where name='"+name+"' and password='"+password+"'",null);
        c.moveToFirst();

        // Initialize all of the variables.
        StringBuffer buffer = new StringBuffer(c.getString(c.getColumnIndex("unlocks")));
        coins = Integer.parseInt(c.getString(c.getColumnIndex("coins")));
        bilcount = Integer.parseInt(c.getString(c.getColumnIndex("bil")));
        progcount = Integer.parseInt(c.getString(c.getColumnIndex("prog")));
        gencount = Integer.parseInt(c.getString(c.getColumnIndex("gen")));
        geocount = Integer.parseInt(c.getString(c.getColumnIndex("geo")));
        mathcount = Integer.parseInt(c.getString(c.getColumnIndex("math")));
        sportscount = Integer.parseInt(c.getString(c.getColumnIndex("sports")));
        histcount = Integer.parseInt(c.getString(c.getColumnIndex("hist")));

        welcomeEarned = c.getString(c.getColumnIndex("welcome"));
        completeRoundEarned = c.getString(c.getColumnIndex("round"));
        bilkentCompleted = c.getString(c.getColumnIndex("bilc"));
        generalCompleted = c.getString(c.getColumnIndex("genc"));
        geographyCompleted = c.getString(c.getColumnIndex("geoc"));
        historyCompleted = c.getString(c.getColumnIndex("histc"));
        programmingCompleted = c.getString(c.getColumnIndex("progc"));
        mathCompleted = c.getString(c.getColumnIndex("mathc"));
        sportsCompleted = c.getString(c.getColumnIndex("sportsc"));
        creditsVisited = c.getString(c.getColumnIndex("cred"));

        bilunlocked = 1;

        if (buffer.charAt(0) == '1')
            histunlocked = 1;
        if (buffer.charAt(1) == '1')
            genunlocked = 1;
        if (buffer.charAt(2) == '1')
            sportsunlocked = 1;
        if (buffer.charAt(3) == '1')
            progunlocked = 1;
        if (buffer.charAt(4) == '1')
            mathunlocked = 1;
        if (buffer.charAt(5) == '1')
            geounlocked = 1;

        c.close();
    }

    // Goes through and unlocks all of the achievements of the user. Meant to be called after setValues
    public void setAchievements() {
        if (welcomeEarned.equals("1"))
            welcome.unlock();
        if (completeRoundEarned.equals("1"))
            completeRound.unlock();
        if (bilkentCompleted.equals("1"))
            bilcomplete.unlock();
        if (generalCompleted.equals("1"))
            gencomplete.unlock();
        if (geographyCompleted.equals("1"))
            geocomplete.unlock();
        if (historyCompleted.equals("1"))
            histcomplete.unlock();
        if (programmingCompleted.equals("1"))
            progcomplete.unlock();
        if (mathCompleted.equals("1"))
            mathcomplete.unlock();
        if (sportsCompleted.equals("1"))
            sportscomplete.unlock();
        if (creditsVisited.equals("1"))
            credits.unlock();
    }

    // Return string representation of the achievements
    public String retrieveAchievements()
    {
        achievements[0] = welcome;
        achievements[1] = completeRound;
        achievements[2] = bilcomplete;
        achievements[3] = gencomplete;
        achievements[4] = geocomplete;
        achievements[5] = histcomplete;
        achievements[6] = progcomplete;
        achievements[7] = mathcomplete;
        achievements[8] = sportscomplete;
        achievements[9] = credits;

        String result = "";

        for (Achievement a : achievements)
            result += a.toString() + "\n\n";

        return result;
    }


    // Updates the current values into the database.
    public void updateDB (SQLiteDatabase db)
    {
        ContentValues c = new ContentValues();

        String s = "" + histunlocked + genunlocked + sportsunlocked + progunlocked + mathunlocked + geounlocked;

        c.put("coins", coins + "");
        c.put("bil", bilcount + "");
        c.put("prog", progcount + "");
        c.put("gen", gencount + "");
        c.put("geo", geocount + "");
        c.put("math", mathcount + "");
        c.put("sports", sportscount + "");
        c.put("hist", histcount + "");

        c.put("welcome", welcomeEarned);
        c.put("round", completeRoundEarned);
        c.put("bilc", bilkentCompleted);
        c.put("genc", generalCompleted);
        c.put("geoc", geographyCompleted);
        c.put("histc", historyCompleted);
        c.put("progc", programmingCompleted);
        c.put("mathc", mathCompleted);
        c.put("sportsc", sportsCompleted);
        c.put("cred", creditsVisited);

        c.put("unlocks", s);

        db.update("login", c, "name=?", new String[] {name});
    }

    public String getName() {
        return name;}
}
