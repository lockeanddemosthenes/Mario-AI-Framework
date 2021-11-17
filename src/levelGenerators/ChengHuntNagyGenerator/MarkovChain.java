package levelGenerators.ChengHuntNagyGenerator;

import java.util.ArrayList;
import java.util.Random;

public class MarkovChain {
    // State transition matrix
    // The value at entry i, j is the probability of choosing the transition between state i and state j
    double[][] transitions = {
            {0, 0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0.45, 0, 0.54, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0, 0.81, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0, 0.9, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9, 0, 1.0, 0, 0, 0, 0},
            {0.09, 0.18, 0.27, 0.36, 0.45, 0.54, 0.63, 0.72, 0.81, 0.9, 1.0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, .20, .40, .60, .80, 1.0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};

    // Each state is a level chunk
    // We can just access a state via its index, i.e. State 0 = element at index 0
    ArrayList<LevelChunk> states;

    private int numStates = 16;
    private LevelChunk currentState;
    private int currentStateIndex;
    private int numSteps;
    private int maxSteps = 6; // Alter this value depending on the number of chunks we want in each level
    private Random random;

    public MarkovChain(ArrayList<LevelChunk> states){
        this.states = states;
        random = new Random();
        numSteps = 0;

        getNextState();
    }

    public LevelChunk getCurrentState(){
        // System.out.println("Current state index = " + currentStateIndex);
        return currentState;
    }

    // Returns a randomly-selected level chunk based on this Markov chain's transition table.
    public LevelChunk getNextState(){
        double randDouble = random.nextDouble();

        // Have we reached the maximum number of generated chunks?
        if(numSteps >= maxSteps-1){
            // If so, place a random flag chunk to finish off the level.
            for(int i=12; i<16;i++){
                if(randDouble <= transitions[12][i]){
                    // Pick this state as the next state
                    // System.out.println("Picked flag state " + i);

                    currentStateIndex = i;
                    currentState = states.get(i);
                    break;
                }
            }
        }

        if(currentState == null){ // First state
            int newState = random.nextInt(12); // random number from 0 to 11
            currentState = states.get(newState);
            currentStateIndex = newState;
        }
        else{
            // Get a new state using probability in the Markov chain
            // System.out.println("Current state: " + currentStateIndex);
            for(int i=0; i<16; i++){
                if(randDouble <= transitions[currentStateIndex][i] && !states.get(i).isFlag){
                    // Pick this state as the next state
                    currentStateIndex = i;
                    currentState = states.get(i);
                    break;
                }
            }
        }

        numSteps++;
        return currentState;
    }
}
