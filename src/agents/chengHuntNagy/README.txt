chengHuntNagy agent created by Errica Cheng, Alex Hunt, and Matthew Nagy

Robin Baumgarten's agent (robinBaumgarten) was heavily referenced in this project, particularly with regards to their A* pathfinding.

FILES CREATED:
    - Agent.java
    - Helper.java
    - PathFinder.java
    - README.txt (this file)
    - SearchNode.java
    - StateMachine.java
All files created can be found in this directory.

INSTRUCTIONS:
    - Place these files under 'Mario-AI-Framework/src/agents/chengHuntNagy'.
    - Update the main() function of PlayLevel.java to contain the following:
        MarioGame game = new MarioGame();
        printResults(game.runGame(new agents.chengHuntNagy.Agent(), getLevel("./levels/original/lvl-2.txt"), 30, 0, true));
    - The file name in the getLevel() function can be changed if desired.
    - Run PlayLevel.java.

VIDEO: https://youtu.be/oLLySk_d23o