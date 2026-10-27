package com.amazon.avod.fsm;

/* JADX INFO: loaded from: classes2.dex */
public interface StateBuilder<S, T> {
    StateBuilder<S, T> registerTransition(T t, State<S, T> state);
}
