package com.ts.avm.statemachine.state;

import android.os.Message;
import com.ts.avm.statemachine.IAvmStateListener;
import com.ts.avm.utils.LogManager;
import com.ts.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public class ForegroundState extends State {
    private static final String TAG = "ForegroundState";
    private final IForegroundState listener;

    public interface IForegroundState extends IAvmStateListener {
    }

    public ForegroundState(IForegroundState listener) {
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
        this.listener.onStatusChange(2, "FOREGROUND STATE");
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void exit() {
        super.exit();
        LogManager.logd(TAG, "exit: ");
    }
}
