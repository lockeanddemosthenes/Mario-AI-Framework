package levelGenerators.ChengHuntNagyGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class MarkovChain {
    // State transition matrix
    // The value at entry i, j is the probability of choosing the transition between state i and state j
    double[][] transitions = {{}, {}, {}};

    // Each state is a level chunk
    // We can just access a state via its index, i.e. State 0 = element at index 0
    ArrayList<LevelChunk> states;

    int numStates;
    int currentState;
    int maxSteps = 10; // Arbitrary, can alter this value

    public MarkovChain(ArrayList<LevelChunk> states){
        this.states = states;
        currentState = 0;
    }
}
