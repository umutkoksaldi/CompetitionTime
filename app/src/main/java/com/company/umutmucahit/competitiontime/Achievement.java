package com.company.umutmucahit.competitiontime;

/**
 * Achievement - Reference to an achievmeent object with a title, description and unlocked state.
 * Created by UmutMucahit on 5/10/2015.
 */
public class Achievement {

    private boolean unlocked;
    private String description;
    private String title;

    // Constructor initialized the title and description, also sets the unlocked state to false.
    public Achievement(String title, String description)
    {
        this.description = description;
        this.title = title;
        unlocked = false;
    }

    // Returns a string representation of the achievement object.
    @Override
    public String toString() {
        if (unlocked)
            return title + "\n" + description + "\n" + "Unlocked";
        else
            return title + "\n" + description + "\n" + "Locked";
    }
    // Unlocks the achievement permanently.
    public void unlock()
    {
        unlocked = true;
    }

    // Returns the unlocked state of the achievement.
    public boolean getUnlocked()
    {return unlocked;}

}
