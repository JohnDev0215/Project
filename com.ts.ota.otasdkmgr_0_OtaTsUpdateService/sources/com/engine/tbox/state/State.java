package com.engine.tbox.state;

import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class State implements IState {
    protected State() {
    }

    @Override // com.engine.tbox.state.IState
    public void enter() {
    }

    @Override // com.engine.tbox.state.IState
    public void exit() {
    }

    @Override // com.engine.tbox.state.IState
    public boolean processMessage(Message msg) {
        return false;
    }

    @Override // com.engine.tbox.state.IState
    public String getName() {
        String name = getClass().getName();
        int lastDollar = name.lastIndexOf(36);
        return name.substring(lastDollar + 1);
    }
}
