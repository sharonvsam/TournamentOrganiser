package tournamentModels;

/**
 * Created by VSharon on 3/21/2018.
 */

public class GamePoint {

    public GamePoint() {
    }

    public GamePoint(double win, double draw, double loss) {
        WinPoint = win;
        DrawPoint = draw;
        LossPoint = loss;
    }

    public double WinPoint;
    public double DrawPoint;
    public double LossPoint;
}
