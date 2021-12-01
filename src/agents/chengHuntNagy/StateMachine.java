package agents.chengHuntNagy;

public enum StateMachine {
    Run {
        @Override
        public String getState() {
            return "Run";
        }
        //TODO
        public StateMachine nextState() {
            return this;
        }
    },  
    Kill {
        @Override
        public String getState() {
            return "Kill";
        }
        //TODO
        public StateMachine nextState() {
            return this;
        }
    },
    Block {
        @Override
        public String getState() {
            return "Block";
        }
        //TODO
        public StateMachine nextState() {
            return this;
        }
    };

    public abstract String getState();
}
