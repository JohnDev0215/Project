package com.ts.avm.statemachine.state;

import android.os.Message;
import com.ts.avm.statemachine.IAvmStateListener;
import com.ts.avm.utils.LogManager;
import com.ts.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public class DisableState extends State {
    private static final String TAG = "DisableState";
    private final IDisableState listener;

    public interface IDisableState extends IAvmStateListener {
        void onDisableRecovery();
    }

    public DisableState(IDisableState listener) {
        this.listener = listener;
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public boolean processMessage(Message msg) {
        if (msg.what == 7) {
            this.listener.onDisableRecovery();
        }
        return super.processMessage(msg);
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void enter() {
        super.enter();
        LogManager.logd(TAG, "enter: ");
        this.listener.onStatusChange(4, "DISABLE STATE");
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void exit() {
        super.exit();
        LogManager.logd(TAG, "exit: ");
    }
}
