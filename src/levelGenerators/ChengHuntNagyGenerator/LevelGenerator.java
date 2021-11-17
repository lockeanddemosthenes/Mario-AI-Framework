package levelGenerators.ChengHuntNagyGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator {
    // DO NOT EDIT ANYTHING ABOVE THIS LINE!
    private static final int CHUNK_SIZE = 25;

    // Returns an entire level string from a file
    // Adapted from PlayLevel.java
    private String getFileContents(String filepath){
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            System.out.println("File read failed");
        }
        return content;
    }

    private ArrayList<LevelChunk> getChunks(String filepath){
        ArrayList<LevelChunk> chunks = new ArrayList<LevelChunk>();
        String chunkStrings = getFileContents(filepath);

        // System.out.println(chunkStrings);
        Scanner scanner = new Scanner(chunkStrings);
        while(scanner.hasNextLine()){
            // Parse line
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            //System.out.println("File path = " + splitLine[0]);
            //System.out.println("Start X = " + splitLine[1]);
            //System.out.println("Has flag = " + splitLine[2]);

            // Add new level chunk
            String level = getFileContents(splitLine[0]);
            int startX = Integer.parseInt(splitLine[1]);
            boolean hasFlag = Boolean.parseBoolean(splitLine[2]);
            LevelChunk newChunk = new LevelChunk(level, startX, hasFlag);

            chunks.add(newChunk);
        }
        //System.out.println(chunks);

        return chunks;
    }

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        model.clearMap();

        // Get level chunks from .txt file
        ArrayList<LevelChunk> states = getChunks("./src/levelGenerators/ChengHuntNagyGenerator/chunks.txt");
        LevelChunk state;

        // Form Markov chain and generate level
        MarkovChain markovChain = new MarkovChain(states);
        state = markovChain.getCurrentState();
        model.copyFromString(0, 0, state.startX, 0, 25, 16, state.sourceLevel);
        int currentX = CHUNK_SIZE;
        for(int i = 0; i < 6; i++){
            state = markovChain.getNextState();
            markovChain.getCurrentState();
            model.copyFromString(currentX, 0, state.startX, 0, 25, 16, state.sourceLevel);
            currentX += CHUNK_SIZE;
        }

        return model.getMap();
    }

    @Override
    public String getGeneratorName() {
        return "ChengHuntNagyGenerator";
    }
}

