package com.amazon.avod.fsm;

/* JADX INFO: loaded from: classes2.dex */
public interface StateMachine<S, T> {
    boolean doTrigger(Trigger<T> trigger);
}
