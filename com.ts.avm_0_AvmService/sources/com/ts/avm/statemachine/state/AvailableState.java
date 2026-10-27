package com.ts.avm.statemachine.state;

import android.os.Message;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.statemachine.IAvmStateListener;
import com.ts.avm.utils.LogManager;
import com.ts.avm.utils.wrappers.VideoCatCmd;
import com.ts.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public class AvailableState extends State {
    private static final String TAG = "AvailableState";
    private final IAvailableState listener;

    public interface IAvailableState extends IAvmStateListener {
        void onCommandTransceiver(CmdBean cmdBean);

        void onDisable();

        void onError(int i, String str);

        void onGoBackGround();

        void onGoForeGround();
    }

    public AvailableState(IAvailableState listener) {
        this.listener = listener;
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public boolean processMessage(Message msg) {
        int i = msg.what;
        if (i == 2) {
            this.listener.onGoForeGround();
        } else if (i == 3) {
            this.listener.onGoBackGround();
        } else if (i == 4) {
            this.listener.onError(msg.arg1, (String) msg.obj);
            IAvailableState iAvailableState = this.listener;
            iAvailableState.transferTo(iAvailableState.getErrorState());
        } else if (i == 5) {
            this.listener.onDisable();
            IAvailableState iAvailableState2 = this.listener;
            iAvailableState2.transferTo(iAvailableState2.getDisabledState());
        } else if (i == 8) {
            this.listener.onCommandTransceiver((CmdBean) msg.obj);
        } else if (i == 33554432) {
            VideoCatCmd cmd = (VideoCatCmd) msg.obj;
            if (cmd.value[2] == 5) {
                IAvailableState iAvailableState3 = this.listener;
                iAvailableState3.transferTo(iAvailableState3.getForegroundState());
            } else if (cmd.value[2] == 4) {
                IAvailableState iAvailableState4 = this.listener;
                iAvailableState4.transferTo(iAvailableState4.getBackgroundState());
            }
        } else if (i == 67108864) {
            VideoCatCmd calibration_cmd = (VideoCatCmd) msg.obj;
            if (calibration_cmd.value[2] == 1) {
                IAvailableState iAvailableState5 = this.listener;
                iAvailableState5.transferTo(iAvailableState5.getForegroundState());
            } else if (calibration_cmd.value[2] == 0) {
                IAvailableState iAvailableState6 = this.listener;
                iAvailableState6.transferTo(iAvailableState6.getBackgroundState());
            }
        }
        return super.processMessage(msg);
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void enter() {
        super.enter();
        LogManager.logd(TAG, "enter: ");
        this.listener.onStatusChange(1, "AVAILABLE STATE");
    }

    @Override // com.ts.statemachine.State, com.ts.statemachine.IState
    public void exit() {
        super.exit();
        LogManager.logd(TAG, "exit: ");
    }
}
