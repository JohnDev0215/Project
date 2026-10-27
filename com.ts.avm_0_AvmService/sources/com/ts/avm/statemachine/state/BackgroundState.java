package com.ts.avm.statemachine.state;

import android.os.Message;
import com.ts.avm.statemachine.IAvmStateListener;
import com.ts.avm.utils.LogManager;
import com.ts.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundState extends State {
    private static final String TAG = "BackgroundState";
    private final IBackgroundState listener;

    public interface IBackgroundState extends IAvmStateListener {
    }

    public BackgroundState(IBackgroundState listener) {
        this.listener = listener;
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public boolean processMessage(Message msg) {
        return super.processMessage(msg);
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void enter() {
        super.enter();
        LogManager.logd(TAG, "enter: ");
        this.listener.onStatusChange(3, "BACKGROUND STATE");
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void exit() {
        super.exit();
        LogManager.logd(TAG, "exit: ");
    }
}
