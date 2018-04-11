package tournamentModels;

/**
 * Created by VSharon on 3/21/2018.
 */

public class Level {

    public Level() {

    }

    public Level(String id, String name) {
        ID = id;
        Name = name;
    }

    public String ID;
    public String Name;

    public Algorithm Type;

    public int NoOfGroups;

    public Group[] Groups;

    public String TournamentID;

    public int Priority;

}
