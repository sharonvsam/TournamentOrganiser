package tournamentModels;

import android.media.Image;

/**
 * Created by VSharon on 3/20/2018.
 */

public class Team {

    public Team() {
    }

    public Team(String id, String name) {
        ID = id;
        Name = name;
    }

    public String ID;

    public String Name;

    public String Logo;

    public String Locality;

    //public int NoOfPlayers;

    public Player[] Players;
}
