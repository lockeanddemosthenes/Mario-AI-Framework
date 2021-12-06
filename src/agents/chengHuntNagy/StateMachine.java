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
            float[] nearbyEnemies;
            nearbyEnemies = Helper.enemiesNearby(model);
            if(nearbyEnemies[1] != 0.0f){
                return Kill; 
            }

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
            float[] nearbyEnemies;
            nearbyEnemies = Helper.enemiesNearby(model);
            if(nearbyEnemies[1] != 0.0f){
                return this; 
            }

            return Run;
        }
    };

    public abstract String getState();
    public abstract StateMachine nextState(MarioForwardModel model);
}
