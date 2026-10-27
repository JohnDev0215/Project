package com.ecarx.xui.adaptapi.hvac.impl;

import android.content.Context;
import com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard;

/* JADX INFO: loaded from: classes2.dex */
public class HvacTouchBoard implements IHvacTouchBoard {
    private IHvacTouchBoard.ITouchBoardListener touchListener;

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard
    public int getTouchBoardHeight() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard
    public int getTouchBoardWidth() {
        return 0;
    }

    public HvacTouchBoard(Context context) {
    }

    public IHvacTouchBoard.ITouchBoardListener getTouchListener() {
        return this.touchListener;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard
    public int[] getSupportBoardTouchFunc() {
        return new int[]{1, 2, 3, 4, 5};
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard
    public void registerStateListener(IHvacTouchBoard.ITouchBoardListener iTouchBoardListener) {
        if (iTouchBoardListener == null) {
            return;
        }
        this.touchListener = iTouchBoardListener;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard
    public void unregisterStateListener(IHvacTouchBoard.ITouchBoardListener iTouchBoardListener) {
        if (this.touchListener != null) {
            this.touchListener = null;
        }
    }
}
