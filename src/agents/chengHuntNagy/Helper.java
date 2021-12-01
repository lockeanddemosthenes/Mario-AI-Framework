package agents.chengHuntNagy;

import engine.helper.MarioActions;

public class Helper {

    // Credit: robinBaumgarten agent
    // Creates a MarioAction boolean array
    // Each value is true if the corresponding button (down, jump, left, right, etc.) is held down.
    public static boolean[] createAction(boolean left, boolean right, boolean down, boolean jump, boolean speed) {
        boolean[] action = new boolean[5];

        action[MarioActions.DOWN.getValue()] = down;
        action[MarioActions.JUMP.getValue()] = jump;
        action[MarioActions.LEFT.getValue()] = left;
        action[MarioActions.RIGHT.getValue()] = right;
        action[MarioActions.SPEED.getValue()] = speed;
        return action;
    }
}
