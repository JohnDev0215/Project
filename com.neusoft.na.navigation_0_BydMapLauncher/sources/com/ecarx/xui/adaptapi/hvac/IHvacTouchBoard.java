package com.ecarx.xui.adaptapi.hvac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IHvacTouchBoard {
    public static final int HARD_BOARD_BLOWING_LEVEL_ADD = 2;
    public static final int HARD_BOARD_BLOWING_LEVEL_DEC = 1;
    public static final int HARD_BOARD_BLOWING_MODE = 3;
    public static final int HARD_BOARD_DEFROST_FRONT = 4;
    public static final int HARD_BOARD_DEFROST_REAR = 5;
    public static final int HARD_BOARD_TOUCH_CLICK = 4;
    public static final int HARD_BOARD_TOUCH_DOWN = 1;
    public static final int HARD_BOARD_TOUCH_MOVE = 2;
    public static final int HARD_BOARD_TOUCH_UP = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoardTouchFunc {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoardTouchMode {
    }

    public interface ITouchBoardListener {
        void onHardBoardTouch(int i, int i2, int i3, int i4);
    }

    int[] getSupportBoardTouchFunc();

    int getTouchBoardHeight();

    int getTouchBoardWidth();

    void registerStateListener(ITouchBoardListener iTouchBoardListener);

    void unregisterStateListener(ITouchBoardListener iTouchBoardListener);
}
