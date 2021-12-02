package agents.chengHuntNagy;

import agents.chengHuntNagy.Helper;
import engine.core.MarioForwardModel;

public enum StateMachine {
    Run {
        @Override
        public String getState() {
            return "Run";
        }

        @Override
        public StateMachine nextState(MarioForwardModel model) {
            if(Helper.isEnemyNearby(model)){ return Kill; }
            else if(Helper.isBlockNearby(model)) { return Block; }
            return this;
        }
    },  
    Kill {
        @Override
        public String getState() {
            return "Kill";
        }

        @Override
        public StateMachine nextState(MarioForwardModel model) {
            if(Helper.isEnemyNearby(model)){ return this; }
            else if(Helper.isBlockNearby(model)) { return Block; }

            return Run;
        }
    },
    Block {
        @Override
        public String getState() {
            return "Block";
        }

        @Override
        public StateMachine nextState(MarioForwardModel model) {
            if(Helper.isEnemyNearby(model)){ return Kill; }
            else if(Helper.isBlockNearby(model)) { return this; }

            return Run;
        }
    };

    public abstract String getState();
    public abstract StateMachine nextState(MarioForwardModel model);
}
