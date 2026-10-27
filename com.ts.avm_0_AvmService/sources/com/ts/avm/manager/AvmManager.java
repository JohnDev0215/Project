package com.ts.avm.manager;

import android.os.Message;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.statemachine.AvmStateMachine;
import com.ts.avm.utils.LogManager;
import com.ts.statemachine.IState;

/* JADX INFO: loaded from: classes.dex */
public class AvmManager {
    private static final String TAG = AvmManager.class.getSimpleName();
    private static volatile AvmManager instance = null;
    private final AvmStateMachine mAvmStateMachine;

    private AvmManager() {
        AvmStateMachine avmStateMachine = new AvmStateMachine();
        this.mAvmStateMachine = avmStateMachine;
        avmStateMachine.start();
    }

    public static synchronized AvmManager getInstance() {
        if (instance == null) {
            instance = new AvmManager();
        }
        return instance;
    }

    public void init() {
        LogManager.logd(TAG, "init: ");
        Message msg = this.mAvmStateMachine.obtainMessage(1);
        this.mAvmStateMachine.sendMessage(msg);
    }

    public void start() {
        LogManager.logw(TAG, "start: ");
        Message msg = this.mAvmStateMachine.obtainMessage(2);
        this.mAvmStateMachine.sendMessage(msg);
    }

    public void stop() {
        LogManager.logw(TAG, "stop: ");
        Message msg = this.mAvmStateMachine.obtainMessage(3);
        this.mAvmStateMachine.sendMessage(msg);
    }

    public void sendCommand(CmdBean bean) {
        LogManager.logw(TAG, "sendCommand cmd send bean :  id: " + bean.getId() + " src_id: " + bean.getSrcId() + " target_id: " + bean.getTargetId() + " value0: " + bean.getValue()[0] + " value1: " + bean.getValue()[1] + " value2: " + bean.getValue()[2] + " value3: " + bean.getValue()[3]);
        Message msg = this.mAvmStateMachine.obtainMessage(8);
        msg.obj = bean;
        this.mAvmStateMachine.sendMessage(msg);
    }

    public int getCurrentState() {
        IState state = this.mAvmStateMachine.getCurrentState();
        if (state.equals(this.mAvmStateMachine.getAvailableState())) {
            return 1;
        }
        if (state.equals(this.mAvmStateMachine.getForegroundState())) {
            return 2;
        }
        if (state.equals(this.mAvmStateMachine.getBackgroundState())) {
            return 3;
        }
        if (state.equals(this.mAvmStateMachine.getErrorState())) {
            return 5;
        }
        if (state.equals(this.mAvmStateMachine.getDisabledState())) {
            return 4;
        }
        return -1;
    }

    public void registerAvmStatusListener(AvmStateManagerListener mServiceListenerManager) {
        this.mAvmStateMachine.setStatusChangeListener(mServiceListenerManager);
    }

    public void registerAvmCommandListener(AvmCommandManagerListener listener) {
        this.mAvmStateMachine.setCommandListener(listener);
    }

    public void registerExtraAvmCommandListener(AvmCommandManagerListener listener) {
        this.mAvmStateMachine.setExtraCommandListener(listener);
    }

    public void sendNaviState2Qnx(int mapType, int naviState, int isForeground, String mapSource) {
        this.mAvmStateMachine.sendNaviState2Qnx(mapType, naviState, isForeground, mapSource);
    }
}
