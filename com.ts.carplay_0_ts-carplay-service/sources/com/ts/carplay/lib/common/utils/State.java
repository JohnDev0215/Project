package com.ts.carplay.lib.common.utils;

import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class State implements IState {
    protected State() {
    }

    @Override // com.ts.carplay.lib.common.utils.IState
    public void enter() {
    }

    @Override // com.ts.carplay.lib.common.utils.IState
    public void exit() {
    }

    @Override // com.ts.carplay.lib.common.utils.IState
    public boolean processMessage(Message msg) {
        return false;
    }

    @Override // com.ts.carplay.lib.common.utils.IState
    public String getName() {
        String name = getClass().getName();
        int lastDollar = name.lastIndexOf(36);
        return name.substring(lastDollar + 1);
    }
}
