package agents.chengHuntNagy;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
    private boolean[] action;
    private PathFinder pathFinder;

    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        this.action = new boolean[MarioActions.numberOfActions()];
        this.pathFinder = new PathFinder();
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        action = this.pathFinder.optimise(model, timer);
        // action = new boolean[]{false, true, false, true, true};
        return action;
    }

    @Override
    public String getAgentName() {
        return "chengHuntNagyAgent";
    }
}
