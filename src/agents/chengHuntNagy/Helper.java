/**
 * This class is adapted from the robinBaumgarten agent
 * provided by the original Mario AI Framework. Slight
 * adjustments have been made to better suit the goals
 * of our agent.
 */

package agents.chengHuntNagy;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

import java.util.ArrayList;

public class Helper {

    public static final int visitedListPenalty = 1500;
    public static final float maxMarioSpeed = 10.9090909f;

    /**
     * Creates a MarioAction boolean array
     * Each value is true if the corresponding button (down, jump, left, right, etc.) is held down.
     */
    public static boolean[] createAction(boolean left, boolean right, boolean down, boolean jump, boolean speed) {
        boolean[] action = new boolean[5];

        action[MarioActions.LEFT.getValue()] = left;
        action[MarioActions.RIGHT.getValue()] = right;
        action[MarioActions.DOWN.getValue()] = down;
        action[MarioActions.JUMP.getValue()] = jump;
        action[MarioActions.SPEED.getValue()] = speed;
        return action;
    }

    public static boolean isEnemyNearby(MarioForwardModel model){
        // getEnemiesFloatPos() in MarioForwardModel may be useful here
        // if enemy is nearby & |y pos| within 2 of mario's, return true
        return false;
    }

    public static boolean isBlockNearby(MarioForwardModel model){
        // if enemy is nearby & |y pos| within 2 of mario's, return true
        return false;
    }

    public static boolean canJumpHigher(agents.chengHuntNagy.SearchNode node, boolean checkParent) {
        if (node.parentPos != null && checkParent && canJumpHigher(node.parentPos, false))
            return true;
        return node.sceneSnapshot.mayMarioJump() || node.sceneSnapshot.getMarioCanJumpHigher();
    }

    public static ArrayList<boolean[]> createPossibleActions(SearchNode node) {
        ArrayList<boolean[]> possibleActions = new ArrayList<boolean[]>();
        // jump
        if (canJumpHigher(node, true))
            possibleActions.add(agents.chengHuntNagy.Helper.createAction(false, false, false, true, false));
        if (canJumpHigher(node, true))
            possibleActions.add(agents.chengHuntNagy.Helper.createAction(false, false, false, true, false));

        // run right
        possibleActions.add(agents.chengHuntNagy.Helper.createAction(false, true, false, false, false));
        if (canJumpHigher(node, true))
            possibleActions.add(agents.chengHuntNagy.Helper.createAction(false, true, false, true, false));
        possibleActions.add(agents.chengHuntNagy.Helper.createAction(false, true, false, false, false));
        if (canJumpHigher(node, true))
            possibleActions.add(agents.chengHuntNagy.Helper.createAction(false, true, false, true, false));

        // run left
        possibleActions.add(agents.chengHuntNagy.Helper.createAction(true, false, false, false, false));
        if (canJumpHigher(node, true))
            possibleActions.add(agents.chengHuntNagy.Helper.createAction(true, false, false, true, false));
        possibleActions.add(agents.chengHuntNagy.Helper.createAction(true, false, false, false, false));
        if (canJumpHigher(node, true))
            possibleActions.add(agents.chengHuntNagy.Helper.createAction(true, false, false, true, false));

        return possibleActions;
    }

    public static int getMarioDamage(MarioForwardModel model, MarioForwardModel prevModel) {
        int damage = 0;
        if (prevModel.getMarioMode() > model.getMarioMode()) {
            damage += 1;
        }
        if (model.getGameStatus() == GameStatus.LOSE) {
            if (model.getMarioFloatPos()[1] > model.getLevelFloatDimensions()[1] - 20) {
                damage += 5;
            } else {
                damage += 2;
            }
        }
        return damage;
    }

    // TODO: rewrite this to use StateMachine stuff :)
    public static String getActionString(boolean[] action) {
        String s = "";
        if (action[MarioActions.RIGHT.getValue()])
            s += "Forward ";
        if (action[MarioActions.LEFT.getValue()])
            s += "Backward ";
        if (action[MarioActions.SPEED.getValue()])
            s += "Speed ";
        if (action[MarioActions.JUMP.getValue()])
            s += "Jump ";
        if (action[MarioActions.DOWN.getValue()])
            s += "Duck";
        if (s.length() == 0) {
            s = "[NONE]";
        }
        return s;
    }

    public static float[] estimateMaximumForwardMovement(float currentAccel, boolean[] action, int ticks) {
        float dist = 0;
        float runningSpeed = action[MarioActions.SPEED.getValue()] ? 1.2f : 0.6f;
        int dir = 0;
        if (action[MarioActions.LEFT.getValue()])
            dir = -1;
        if (action[MarioActions.RIGHT.getValue()])
            dir = 1;
        for (int i = 0; i < ticks; i++) {
            currentAccel += runningSpeed * dir;
            dist += currentAccel;
            currentAccel *= 0.89f;
        }
        float[] ret = new float[2];
        ret[0] = dist;
        ret[1] = currentAccel;
        return ret;
    }

}
