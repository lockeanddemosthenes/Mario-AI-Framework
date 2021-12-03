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
                float enemyX = nearbyEnemies[1];
                float enemyY = nearbyEnemies[2];
                // System.out.println("Enemy found at : " + enemyX + ", " + enemyY);
                return Kill; 
            }
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
            float[] nearbyEnemies;
            nearbyEnemies = Helper.enemiesNearby(model);
            if(nearbyEnemies[1] != 0.0f){
                float enemyX = nearbyEnemies[1];
                float enemyY = nearbyEnemies[2];
                //System.out.println("Enemy found at : " + enemyX + ", " + enemyY);
                return this; 
            }
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
            float[] nearbyEnemies;
            nearbyEnemies = Helper.enemiesNearby(model);
            if(nearbyEnemies[1] != 0.0f){
                float enemyX = nearbyEnemies[1];
                float enemyY = nearbyEnemies[2];
                System.out.println("Enemy found at : " + enemyX + ", " + enemyY);
                return Kill; 
            }
            else if(Helper.isBlockNearby(model)) { return this; }

            return Run;
        }
    };

    public abstract String getState();
    public abstract StateMachine nextState(MarioForwardModel model);
}
