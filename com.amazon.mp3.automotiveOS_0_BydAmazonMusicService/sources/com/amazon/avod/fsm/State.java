package com.amazon.avod.fsm;

/* JADX INFO: loaded from: classes2.dex */
public interface State<S, T> {
    void enter(Trigger<T> trigger);

    void exit(Trigger<T> trigger);

    S getType();
}
