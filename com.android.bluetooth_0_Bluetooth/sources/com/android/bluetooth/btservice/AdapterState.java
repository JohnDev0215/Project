package com.android.bluetooth.btservice;

import android.bluetooth.BluetoothAdapter;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;

/* JADX INFO: loaded from: classes.dex */
final class AdapterState extends StateMachine {
    static final int BLE_STARTED = 7;
    static final int BLE_START_TIMEOUT = 12;
    static final int BLE_START_TIMEOUT_DELAY = 4000;
    static final int BLE_STOPPED = 8;
    static final int BLE_STOP_TIMEOUT = 11;
    static final int BLE_STOP_TIMEOUT_DELAY = 2500;
    static final int BLE_TURN_OFF = 4;
    static final int BLE_TURN_ON = 3;
    static final int BREDR_STARTED = 5;
    static final int BREDR_START_TIMEOUT = 9;
    static final int BREDR_START_TIMEOUT_DELAY = 4000;
    static final int BREDR_STOPPED = 6;
    static final int BREDR_STOP_TIMEOUT = 10;
    static final int BREDR_STOP_TIMEOUT_DELAY = 4000;
    private static final boolean DBG = true;
    static final int START_PROFILE_SERVICE = 13;
    private static final String TAG = AdapterState.class.getSimpleName();
    static final int USER_TURN_OFF = 2;
    static final int USER_TURN_ON = 1;
    private AdapterService mAdapterService;
    private BleOnState mBleOnState;
    private OffState mOffState;
    private OnState mOnState;
    private int mPrevState;
    private TurningBleOffState mTurningBleOffState;
    private TurningBleOnState mTurningBleOnState;
    private TurningOffState mTurningOffState;
    private TurningOnState mTurningOnState;

    private AdapterState(AdapterService service) {
        super(TAG);
        this.mTurningOnState = new TurningOnState();
        this.mTurningBleOnState = new TurningBleOnState();
        this.mTurningOffState = new TurningOffState();
        this.mTurningBleOffState = new TurningBleOffState();
        this.mOnState = new OnState();
        this.mOffState = new OffState();
        this.mBleOnState = new BleOnState();
        this.mPrevState = 10;
        addState(this.mOnState);
        addState(this.mBleOnState);
        addState(this.mOffState);
        addState(this.mTurningOnState);
        addState(this.mTurningOffState);
        addState(this.mTurningBleOnState);
        addState(this.mTurningBleOffState);
        this.mAdapterService = service;
        setInitialState(this.mOffState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String messageString(int message) {
        switch (message) {
            case 1:
                return "USER_TURN_ON";
            case 2:
                return "USER_TURN_OFF";
            case 3:
                return "BLE_TURN_ON";
            case 4:
                return "BLE_TURN_OFF";
            case 5:
                return "BREDR_STARTED";
            case 6:
                return "BREDR_STOPPED";
            case 7:
                return "BLE_STARTED";
            case 8:
                return "BLE_STOPPED";
            case 9:
                return "BREDR_START_TIMEOUT";
            case 10:
                return "BREDR_STOP_TIMEOUT";
            case 11:
                return "BLE_STOP_TIMEOUT";
            case 12:
                return "BLE_START_TIMEOUT";
            default:
                return "Unknown message (" + message + ")";
        }
    }

    public static AdapterState make(AdapterService service) {
        Log.d(TAG, "make() - Creating AdapterState");
        AdapterState as = new AdapterState(service);
        as.start();
        return as;
    }

    public void doQuit() {
        quitNow();
    }

    private void cleanup() {
        if (this.mAdapterService != null) {
            this.mAdapterService = null;
        }
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void onQuitting() {
        cleanup();
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected String getLogRecString(Message msg) {
        return messageString(msg.what);
    }

    private abstract class BaseAdapterState extends State {
        abstract int getStateValue();

        private BaseAdapterState() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            int currState = getStateValue();
            infoLog("entered ");
            AdapterState.this.mAdapterService.updateAdapterState(AdapterState.this.mPrevState, currState);
            AdapterState.this.mPrevState = currState;
        }

        void infoLog(String msg) {
            Log.i(AdapterState.TAG, BluetoothAdapter.nameForState(getStateValue()) + " : " + msg);
        }

        void errorLog(String msg) {
            Log.e(AdapterState.TAG, BluetoothAdapter.nameForState(getStateValue()) + " : " + msg);
        }
    }

    private class OffState extends BaseAdapterState {
        private OffState() {
            super();
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState
        int getStateValue() {
            return 10;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                AdapterState.this.mAdapterService.bringUpBle();
                AdapterState adapterState = AdapterState.this;
                adapterState.transitionTo(adapterState.mTurningOnState);
            } else if (i == 3) {
                AdapterState adapterState2 = AdapterState.this;
                adapterState2.transitionTo(adapterState2.mTurningBleOnState);
            } else {
                infoLog("Unhandled message - " + AdapterState.this.messageString(msg.what));
                return false;
            }
            return true;
        }
    }

    private class BleOnState extends BaseAdapterState {
        private BleOnState() {
            super();
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState
        int getStateValue() {
            return 15;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                AdapterState adapterState = AdapterState.this;
                adapterState.transitionTo(adapterState.mTurningOnState);
            } else if (i == 4) {
                AdapterState adapterState2 = AdapterState.this;
                adapterState2.transitionTo(adapterState2.mTurningBleOffState);
            } else {
                infoLog("Unhandled message - " + AdapterState.this.messageString(msg.what));
                return false;
            }
            return true;
        }
    }

    private class OnState extends BaseAdapterState {
        private OnState() {
            super();
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState
        int getStateValue() {
            return 12;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            if (msg.what == 2) {
                AdapterState adapterState = AdapterState.this;
                adapterState.transitionTo(adapterState.mTurningOffState);
                return true;
            }
            infoLog("Unhandled message - " + AdapterState.this.messageString(msg.what));
            return false;
        }
    }

    private class TurningBleOnState extends BaseAdapterState {
        private TurningBleOnState() {
            super();
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState
        int getStateValue() {
            return 14;
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            AdapterState.this.sendMessageDelayed(12, 4000L);
            AdapterState.this.mAdapterService.bringUpBle();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            AdapterState.this.removeMessages(12);
            super.exit();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            int i = msg.what;
            if (i == 7) {
                AdapterState adapterState = AdapterState.this;
                adapterState.transitionTo(adapterState.mBleOnState);
                return true;
            }
            if (i == 12) {
                errorLog(AdapterState.this.messageString(msg.what));
                AdapterState adapterState2 = AdapterState.this;
                adapterState2.transitionTo(adapterState2.mTurningBleOffState);
                return true;
            }
            infoLog("Unhandled message - " + AdapterState.this.messageString(msg.what));
            return false;
        }
    }

    private class TurningOnState extends BaseAdapterState {
        private TurningOnState() {
            super();
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState
        int getStateValue() {
            return 11;
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            AdapterState.this.sendMessageDelayed(9, 4000L);
            if (AdapterState.this.mAdapterService.isBleSupported()) {
                AdapterState.this.mAdapterService.startProfileServices();
            }
            if (!AdapterState.this.mAdapterService.isBleSupported()) {
                AdapterState.this.mAdapterService.enableNative();
            }
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            AdapterState.this.removeMessages(9);
            super.exit();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            int i = msg.what;
            if (i == 5) {
                AdapterState adapterState = AdapterState.this;
                adapterState.transitionTo(adapterState.mOnState);
                return true;
            }
            if (i == 9) {
                errorLog(AdapterState.this.messageString(msg.what));
                AdapterState adapterState2 = AdapterState.this;
                adapterState2.transitionTo(adapterState2.mTurningOffState);
                return true;
            }
            if (i == 13) {
                AdapterState.this.mAdapterService.startProfileServices();
                return true;
            }
            infoLog("Unhandled message - " + AdapterState.this.messageString(msg.what));
            return false;
        }
    }

    private class TurningOffState extends BaseAdapterState {
        private TurningOffState() {
            super();
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState
        int getStateValue() {
            return 13;
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            AdapterState.this.sendMessageDelayed(10, 4000L);
            AdapterState.this.mAdapterService.stopProfileServices();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            AdapterState.this.removeMessages(10);
            super.exit();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            int i = msg.what;
            if (i == 6) {
                if (AdapterState.this.mAdapterService.isBleSupported()) {
                    AdapterState adapterState = AdapterState.this;
                    adapterState.transitionTo(adapterState.mBleOnState);
                    return true;
                }
                AdapterState adapterState2 = AdapterState.this;
                adapterState2.transitionTo(adapterState2.mOffState);
                return true;
            }
            if (i == 10) {
                errorLog(AdapterState.this.messageString(msg.what));
                AdapterState adapterState3 = AdapterState.this;
                adapterState3.transitionTo(adapterState3.mTurningBleOffState);
                return true;
            }
            infoLog("Unhandled message - " + AdapterState.this.messageString(msg.what));
            return false;
        }
    }

    private class TurningBleOffState extends BaseAdapterState {
        private TurningBleOffState() {
            super();
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState
        int getStateValue() {
            return 16;
        }

        @Override // com.android.bluetooth.btservice.AdapterState.BaseAdapterState, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            AdapterState.this.sendMessageDelayed(11, 2500L);
            AdapterState.this.mAdapterService.bringDownBle();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            AdapterState.this.removeMessages(11);
            super.exit();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            int i = msg.what;
            if (i == 8) {
                AdapterState adapterState = AdapterState.this;
                adapterState.transitionTo(adapterState.mOffState);
                return true;
            }
            if (i == 11) {
                errorLog(AdapterState.this.messageString(msg.what));
                AdapterState adapterState2 = AdapterState.this;
                adapterState2.transitionTo(adapterState2.mOffState);
                return true;
            }
            infoLog("Unhandled message - " + AdapterState.this.messageString(msg.what));
            return false;
        }
    }
}
