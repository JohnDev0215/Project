package com.android.networkstack.tethering.util;

import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class State implements IState {
    public void enter() {
    }

    public void exit() {
    }

    public boolean processMessage(Message message) {
        return false;
    }

    protected State() {
    }

    @Override // com.android.networkstack.tethering.util.IState
    public String getName() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf(36) + 1);
    }
}
