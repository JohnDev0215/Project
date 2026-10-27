package android.hardware.bydauto_test;

import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestACManagerImplCallback {
    public static final int SET_AC_COMPRESSOR_MODE = 1020;
    public static final int SET_AC_COMPRESSOR_STATE = 1015;
    public static final int SET_AC_CONTROL_MODE = 1009;
    public static final int SET_AC_CYCLE_MODE = 1002;
    public static final int SET_AC_DEFROST_STATE = 1007;
    public static final int SET_AC_MAX_COOLING_STATE = 1021;
    public static final int SET_AC_REAR_PANEL_LOCK_STATE = 1019;
    public static final int SET_AC_REMOTE_CTRL_TIME = 1024;
    public static final int SET_AC_TEMPERATURE = 1012;
    public static final int SET_AC_TEMPERATURE_CONTROL_MODE = 1010;
    public static final int SET_AC_VENTILATION_STATE = 1000;
    public static final int SET_AC_WIND_LEVEL = 1016;
    public static final int SET_AC_WIND_MODE = 1005;
    public static final int SET_AUTO_CLEAN_AIR_STATE = 1025;
    public static final int SET_ELECTRIC_DEFROST_STATE = 1001;
    public static final int SET_MAX_AC_STATE = 1004;
    public static final int SET_QUICK_CLEAN_AIR_STATE = 1022;
    public static final int SET_REAR_AC_CONTROL_MODE = 1018;
    public static final int SET_REAR_AC_KEY_LOCK_STATE = 1008;
    public static final int SET_REAR_AC_LOCK_STATE = 1023;
    public static final int SET_REAR_AC_WIND_LEVEL = 1013;
    public static final int SET_REAR_AC_WIND_MODE = 1003;
    public static final int START = 1006;
    public static final int START_REAR_AC = 1014;
    public static final int STOP = 1017;
    public static final int STOP_REAR_AC = 1011;
    private final List<AbsBYDAutoAcListener> mAbsBydAutoAcListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoAcListener(AbsBYDAutoAcListener absBydAutoAcListener) {
        if (absBydAutoAcListener != null && !this.mAbsBydAutoAcListeners.contains(absBydAutoAcListener)) {
            this.mAbsBydAutoAcListeners.add(absBydAutoAcListener);
        }
    }

    public void removeListener(AbsBYDAutoAcListener absBydAutoAcListener) {
        if (absBydAutoAcListener != null) {
            this.mAbsBydAutoAcListeners.remove(absBydAutoAcListener);
        }
    }

    public TestACManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestACManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestACManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestACManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    public void enqueueMessage(int what, int arg1, int arg2) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            message.arg2 = arg2;
            this.mHandler.sendMessage(message);
        }
    }

    public void enqueueMessage(int what, int arg1) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            message.arg2 = 0;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (!this.mAbsBydAutoAcListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int arg2 = msg.arg2;
            switch (what) {
                case 1000:
                    for (AbsBYDAutoAcListener absBydAutoAcListener : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener.onAcVentilationStateChanged(arg1);
                    }
                    break;
                case 1001:
                    for (AbsBYDAutoAcListener absBydAutoAcListener2 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener2.onElectricDefrostStateChanged(arg1);
                    }
                    break;
                case 1002:
                    for (AbsBYDAutoAcListener absBydAutoAcListener3 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener3.onAcCycleModeChanged(arg1);
                    }
                    break;
                case 1003:
                    for (AbsBYDAutoAcListener absBydAutoAcListener4 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener4.onRearAcWindModeChanged(arg1);
                    }
                    break;
                case 1004:
                    for (AbsBYDAutoAcListener absBydAutoAcListener5 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener5.onMaxAcStateChanged(arg1);
                    }
                    break;
                case 1005:
                    for (AbsBYDAutoAcListener absBydAutoAcListener6 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener6.onAcWindModeChanged(arg1);
                    }
                    break;
                case 1006:
                    for (AbsBYDAutoAcListener absBydAutoAcListener7 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener7.onAcStarted();
                    }
                    break;
                case 1007:
                    for (AbsBYDAutoAcListener absBydAutoAcListener8 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener8.onAcDefrostStateChanged(arg1, arg2);
                    }
                    break;
                case 1008:
                    for (AbsBYDAutoAcListener absBydAutoAcListener9 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener9.onRearAcKeyLockStateChanged(arg1);
                    }
                    break;
                case 1009:
                    for (AbsBYDAutoAcListener absBydAutoAcListener10 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener10.onAcCtrlModeChanged(arg1);
                    }
                    break;
                case 1010:
                    for (AbsBYDAutoAcListener absBydAutoAcListener11 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener11.onAcTemperatureControlModeChanged(arg1);
                    }
                    break;
                case 1011:
                    for (AbsBYDAutoAcListener absBydAutoAcListener12 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener12.onAcRearStoped();
                    }
                    break;
                case 1012:
                    for (AbsBYDAutoAcListener absBydAutoAcListener13 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener13.onTemperatureChanged(arg1, arg2);
                    }
                    break;
                case 1013:
                    for (AbsBYDAutoAcListener absBydAutoAcListener14 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener14.onRearAcWindLevelChanged(arg1);
                    }
                    break;
                case 1014:
                    for (AbsBYDAutoAcListener absBydAutoAcListener15 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener15.onAcRearStarted();
                    }
                    break;
                case 1016:
                    for (AbsBYDAutoAcListener absBydAutoAcListener16 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener16.onAcWindLevelChanged(arg1);
                    }
                    break;
                case 1017:
                    for (AbsBYDAutoAcListener absBydAutoAcListener17 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener17.onAcStoped();
                    }
                    break;
                case 1018:
                    for (AbsBYDAutoAcListener absBydAutoAcListener18 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener18.onRearAcCtrlModeChanged(arg1);
                    }
                    break;
                case 1019:
                    for (AbsBYDAutoAcListener absBydAutoAcListener19 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener19.onAcRearPanelLockStateChanged(arg1);
                    }
                    break;
                case 1020:
                    for (AbsBYDAutoAcListener absBydAutoAcListener20 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener20.onAcCompressorModeChanged(arg1);
                    }
                    break;
                case 1021:
                    for (AbsBYDAutoAcListener absBydAutoAcListener21 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener21.onAcMaxCoolingStateChanged(arg1);
                    }
                    break;
                case 1022:
                    for (AbsBYDAutoAcListener absBydAutoAcListener22 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener22.onQuickCleanAirStateChanged(arg1);
                    }
                    break;
                case 1023:
                    for (AbsBYDAutoAcListener absBydAutoAcListener23 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener23.onRearAcLockStateChanged(arg1);
                    }
                    break;
                case 1024:
                    for (AbsBYDAutoAcListener absBydAutoAcListener24 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener24.onAcRemoteCtrlTimeChanged(arg1);
                    }
                    break;
                case 1025:
                    for (AbsBYDAutoAcListener absBydAutoAcListener25 : this.mAbsBydAutoAcListeners) {
                        absBydAutoAcListener25.onAutoCleanAirStateChanged(arg1);
                    }
                    break;
            }
        }
    }
}
