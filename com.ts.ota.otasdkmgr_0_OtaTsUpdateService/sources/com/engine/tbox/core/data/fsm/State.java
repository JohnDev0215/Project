package com.engine.tbox.core.data.fsm;

/* JADX INFO: loaded from: classes.dex */
public abstract class State {
    protected Fsm mFsm;

    public abstract byte[] currentEventByte();

    public abstract void onEnter();

    public abstract int onEvent(byte[] bArr);

    public abstract void onExit();

    public void addFsm(Fsm fsm) {
        this.mFsm = fsm;
    }

    public String name() {
        return getClass().getSimpleName();
    }
}
