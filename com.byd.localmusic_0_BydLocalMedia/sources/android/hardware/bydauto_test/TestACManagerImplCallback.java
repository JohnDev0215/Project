package android.hardware.bydauto_test;

import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
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

    public void setAbsBydAutoAcListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        if (absBYDAutoAcListener == null || this.mAbsBydAutoAcListeners.contains(absBYDAutoAcListener)) {
            return;
        }
        this.mAbsBydAutoAcListeners.add(absBYDAutoAcListener);
    }

    public void removeListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        if (absBYDAutoAcListener != null) {
            this.mAbsBydAutoAcListeners.remove(absBYDAutoAcListener);
        }
    }

    public TestACManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestACManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestACManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestACManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void enqueueMessage(int i, int i2, int i3) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoAcListeners.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 1000:
                Iterator<AbsBYDAutoAcListener> it = this.mAbsBydAutoAcListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAcVentilationStateChanged(i2);
                }
                break;
            case 1001:
                Iterator<AbsBYDAutoAcListener> it2 = this.mAbsBydAutoAcListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onElectricDefrostStateChanged(i2);
                }
                break;
            case 1002:
                Iterator<AbsBYDAutoAcListener> it3 = this.mAbsBydAutoAcListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onAcCycleModeChanged(i2);
                }
                break;
            case 1003:
                Iterator<AbsBYDAutoAcListener> it4 = this.mAbsBydAutoAcListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onRearAcWindModeChanged(i2);
                }
                break;
            case 1004:
                Iterator<AbsBYDAutoAcListener> it5 = this.mAbsBydAutoAcListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onMaxAcStateChanged(i2);
                }
                break;
            case 1005:
                Iterator<AbsBYDAutoAcListener> it6 = this.mAbsBydAutoAcListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onAcWindModeChanged(i2);
                }
                break;
            case 1006:
                Iterator<AbsBYDAutoAcListener> it7 = this.mAbsBydAutoAcListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onAcStarted();
                }
                break;
            case 1007:
                Iterator<AbsBYDAutoAcListener> it8 = this.mAbsBydAutoAcListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onAcDefrostStateChanged(i2, i3);
                }
                break;
            case 1008:
                Iterator<AbsBYDAutoAcListener> it9 = this.mAbsBydAutoAcListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onRearAcKeyLockStateChanged(i2);
                }
                break;
            case 1009:
                Iterator<AbsBYDAutoAcListener> it10 = this.mAbsBydAutoAcListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onAcCtrlModeChanged(i2);
                }
                break;
            case 1010:
                Iterator<AbsBYDAutoAcListener> it11 = this.mAbsBydAutoAcListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onAcTemperatureControlModeChanged(i2);
                }
                break;
            case 1011:
                Iterator<AbsBYDAutoAcListener> it12 = this.mAbsBydAutoAcListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onAcRearStoped();
                }
                break;
            case 1012:
                Iterator<AbsBYDAutoAcListener> it13 = this.mAbsBydAutoAcListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onTemperatureChanged(i2, i3);
                }
                break;
            case 1013:
                Iterator<AbsBYDAutoAcListener> it14 = this.mAbsBydAutoAcListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onRearAcWindLevelChanged(i2);
                }
                break;
            case 1014:
                Iterator<AbsBYDAutoAcListener> it15 = this.mAbsBydAutoAcListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onAcRearStarted();
                }
                break;
            case 1016:
                Iterator<AbsBYDAutoAcListener> it16 = this.mAbsBydAutoAcListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onAcWindLevelChanged(i2);
                }
                break;
            case 1017:
                Iterator<AbsBYDAutoAcListener> it17 = this.mAbsBydAutoAcListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onAcStoped();
                }
                break;
            case 1018:
                Iterator<AbsBYDAutoAcListener> it18 = this.mAbsBydAutoAcListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onRearAcCtrlModeChanged(i2);
                }
                break;
            case 1019:
                Iterator<AbsBYDAutoAcListener> it19 = this.mAbsBydAutoAcListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onAcRearPanelLockStateChanged(i2);
                }
                break;
            case 1020:
                Iterator<AbsBYDAutoAcListener> it20 = this.mAbsBydAutoAcListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onAcCompressorModeChanged(i2);
                }
                break;
            case 1021:
                Iterator<AbsBYDAutoAcListener> it21 = this.mAbsBydAutoAcListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onAcMaxCoolingStateChanged(i2);
                }
                break;
            case 1022:
                Iterator<AbsBYDAutoAcListener> it22 = this.mAbsBydAutoAcListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onQuickCleanAirStateChanged(i2);
                }
                break;
            case 1023:
                Iterator<AbsBYDAutoAcListener> it23 = this.mAbsBydAutoAcListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onRearAcLockStateChanged(i2);
                }
                break;
            case 1024:
                Iterator<AbsBYDAutoAcListener> it24 = this.mAbsBydAutoAcListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onAcRemoteCtrlTimeChanged(i2);
                }
                break;
            case 1025:
                Iterator<AbsBYDAutoAcListener> it25 = this.mAbsBydAutoAcListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onAutoCleanAirStateChanged(i2);
                }
                break;
        }
    }
}
