package levelGenerators.ChengHuntNagyGenerator;

public class LevelChunk {
    String sourceLevel;
    int startX;
    boolean isFlag; // Does this chunk contain a flag?

    public LevelChunk(String level, int startX, boolean isFlag){
        this.sourceLevel = level;
        this.startX = startX;
        this.isFlag = isFlag;
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
}
