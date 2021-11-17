package levelGenerators.ChengHuntNagyGenerator;

public class LevelChunk {
    String sourceLevel;
    int startX;
    int length = 25;
    boolean isFlag; // Does this chunk contain a flag?

    public LevelChunk(String level, int startX, boolean isFlag){
        this.sourceLevel = level;
        this.startX = startX;
        this.isFlag = isFlag;
        //System.out.println("Level chunk from level " + this.sourceLevel + " starting X = " + this.startX + " has flag? = " + isFlag);
        //System.out.println();
    }

    public LevelChunk(){
        sourceLevel = "";
        this.startX = 0;
        this.isFlag = false;
    }

    public void setLevel(String level){
        this.sourceLevel = level;
    }

    public void setStart(int startX){
        this.startX = startX;
    }

    public String getChunkText(){
        return sourceLevel.substring(startX, startX+25);
    }

    public String toString(){
        return "Level chunk: \n" + this.sourceLevel + "\nstarting X = " +
                this.startX + "\nhas flag? = " + isFlag + "\n";
    }
}
