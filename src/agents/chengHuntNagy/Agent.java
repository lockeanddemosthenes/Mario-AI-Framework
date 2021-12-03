package agents.chengHuntNagy;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
    private boolean[] action;
    private PathFinder pathFinder;
    private StateMachine FSM;

    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        this.action = new boolean[MarioActions.numberOfActions()];
        this.pathFinder = new PathFinder();
        this.FSM = StateMachine.Run;
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        FSM = FSM.nextState(model);
        //System.out.println(FSM.getState());
        if(FSM.getState().equals("Kill")){
            // Kill behavior
            System.out.println("Kill state");
            action = new boolean[]{false, false, false, false, false};
        }
        else{ // Run state, not using Block state for now
            action = this.pathFinder.optimise(model, timer);
            //action = new boolean[]{false, true, false, true, false};
        }


        return action;
    }

    @Override
    public String getAgentName() {
        return "chengHuntNagyAgent";
    }
}
