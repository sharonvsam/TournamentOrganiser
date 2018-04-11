package tournamentModels;

/**
 * Created by VSharon on 3/21/2018.
 */

public class Group {

    public Group() {
    }

    public Group(String id, String name) {
        ID = id;
        Name = name;
    }

    public String ID;
    public String Name;
    public Team[] Teams;
}
