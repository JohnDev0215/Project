package com.ts.statemachine;

import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class State implements IState {
    protected State() {
    }

    @Override // com.ts.statemachine.IState
    public void enter() {
    }

    @Override // com.ts.statemachine.IState
    public void exit() {
    }

    @Override // com.ts.statemachine.IState
    public boolean processMessage(Message msg) {
        return false;
    }

    @Override // com.ts.statemachine.IState
    public String getName() {
        String name = getClass().getName();
        int lastDollar = name.lastIndexOf(36);
        return name.substring(lastDollar + 1);
    }
}
