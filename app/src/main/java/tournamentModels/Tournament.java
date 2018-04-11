package tournamentModels;

import java.util.Date;
import java.util.List;

/**
 * Created by VSharon on 3/20/2018.
 */

public class Tournament {

    public Tournament() {
    }

    public Tournament(String id, String name) {
        ID = id;
        Name = name;
    }

    public String ID;

    public String Name;

    public String Logo;

    public String Locality;

    public int NoOfTeams;

    public Team[] Teams;

    public Date StartDate;

    public Date EndDate;

    public List<Level> Levels;
}
