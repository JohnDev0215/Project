package com.ts.avm.statemachine;

import android.os.Message;
import com.ts.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public interface IAvmStateListener {
    void deliverMessage(Message message);

    State getAvailableState();

    State getBackgroundState();

    State getDisabledState();

    State getErrorState();

    State getForegroundState();

    State getIdleState();

    void onStatusChange(int i, String str);

    void transferTo(State state);
}
