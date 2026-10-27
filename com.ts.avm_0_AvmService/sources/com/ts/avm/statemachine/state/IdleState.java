package com.ts.avm.statemachine.state;

import android.os.Message;
import com.ts.avm.statemachine.IAvmStateListener;
import com.ts.avm.utils.LogManager;
import com.ts.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public class IdleState extends State {
    private static final String TAG = "IdleState";
    private final IIdleState listener;

    public interface IIdleState extends IAvmStateListener {
        void qnxServiceConnect();

        void qnxServiceDisconnect();
    }

    public IdleState(IIdleState listener) {
        this.listener = listener;
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public boolean processMessage(Message msg) {
        int i = msg.what;
        if (i == 1) {
            this.listener.qnxServiceConnect();
        } else if (i == 10) {
            IIdleState iIdleState = this.listener;
            iIdleState.transferTo(iIdleState.getAvailableState());
        }
        return super.processMessage(msg);
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void enter() {
        super.enter();
        LogManager.logd(TAG, "enter: ");
        this.listener.onStatusChange(0, "IDLE STATE");
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void exit() {
        super.exit();
        LogManager.logd(TAG, "exit: ");
    }
}
