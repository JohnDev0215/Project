package com.ts.avm.statemachine.state;

import android.os.Message;
import com.ts.avm.statemachine.IAvmStateListener;
import com.ts.avm.utils.LogManager;
import com.ts.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public class ErrorState extends State {
    private static final String TAG = "ErrorState";
    private IErrorState listener;

    public interface IErrorState extends IAvmStateListener {
        void onErrorRecovery();
    }

    public ErrorState(IErrorState listener) {
        this.listener = listener;
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public boolean processMessage(Message msg) {
        if (msg.what == 6) {
            this.listener.onErrorRecovery();
        }
        return super.processMessage(msg);
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void enter() {
        super.enter();
        LogManager.logd(TAG, "enter: ");
        this.listener.onStatusChange(5, "ERROR STATE");
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void exit() {
        super.exit();
        LogManager.logd(TAG, "exit: ");
    }
}
