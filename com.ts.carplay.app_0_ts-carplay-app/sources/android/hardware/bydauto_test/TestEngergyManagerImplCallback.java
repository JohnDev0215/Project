package android.hardware.bydauto_test;

import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestEngergyManagerImplCallback {
    public static final int RESET_CURVE_DATA = 6011;
    public static final int SET_DRIVE_DISTANCE_SHOW_MODE = 6009;
    public static final int SET_DRIVE_DISTANCE_SHOW_STANDARD = 6002;
    public static final int SET_ELECTRIC_SHOW_MODE = 6004;
    public static final int SET_ENERGY_FEEDBACK = 6001;
    public static final int SET_E_PEDAL_STATE = 6000;
    public static final int SET_MANDATORY_ELECTRIC_STATE = 6006;
    public static final int SET_MEMORY_ELECTRIC_STATE = 6008;
    public static final int SET_MEMORY_MAX_EV_STATE = 6003;
    public static final int SET_MEMORY_SPORT_STATE = 6005;
    public static final int SET_PRE_SALE_MODEL_STATE = 6010;
    public static final int SET_S_O_C_TARGET = 6007;
    private final List<AbsBYDAutoEnergyListener> mAbsBydAutoEnergyListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoEnergyListener(AbsBYDAutoEnergyListener listener) {
        if (listener != null && !this.mAbsBydAutoEnergyListeners.contains(listener)) {
            this.mAbsBydAutoEnergyListeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoEnergyListener listener) {
        if (listener != null) {
            this.mAbsBydAutoEnergyListeners.remove(listener);
        }
    }

    public TestEngergyManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestEngergyManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestEngergyManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestEngergyManagerImplCallback.this.dispatchMessageCallBack(msg);
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
        if (!this.mAbsBydAutoEnergyListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int i = msg.arg2;
            switch (what) {
                case 6000:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener.onEPedalStateChanged(arg1);
                    }
                    break;
                case SET_ENERGY_FEEDBACK /* 6001 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener2 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener2.onEnergyFeedbackLevelChanged(arg1);
                    }
                    break;
                case SET_DRIVE_DISTANCE_SHOW_STANDARD /* 6002 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener3 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener3.onDriveDistanceShowStandardChanged(arg1);
                    }
                    break;
                case SET_MEMORY_MAX_EV_STATE /* 6003 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener4 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener4.onMemoryMaxEvStateChanged(arg1);
                    }
                    break;
                case SET_ELECTRIC_SHOW_MODE /* 6004 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener5 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener5.onElectricShowModeChanged(arg1);
                    }
                    break;
                case SET_MEMORY_SPORT_STATE /* 6005 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener6 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener6.onMemorySportStateChanged(arg1);
                    }
                    break;
                case SET_MANDATORY_ELECTRIC_STATE /* 6006 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener7 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener7.onMandatoryElectricStateChanged(arg1);
                    }
                    break;
                case SET_S_O_C_TARGET /* 6007 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener8 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener8.onSOCTargetChanged(arg1);
                    }
                    break;
                case SET_MEMORY_ELECTRIC_STATE /* 6008 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener9 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener9.onMemoryElectricStateChanged(arg1);
                    }
                    break;
                case SET_DRIVE_DISTANCE_SHOW_MODE /* 6009 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener10 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener10.onDriveDistanceShowModeChanged(arg1);
                    }
                    break;
                case SET_PRE_SALE_MODEL_STATE /* 6010 */:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener11 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener11.onPreSaleModelStateChanged(arg1);
                    }
                    break;
                case 6011:
                    for (AbsBYDAutoEnergyListener absBydAutoEnergyListener12 : this.mAbsBydAutoEnergyListeners) {
                        absBydAutoEnergyListener12.onResetCurveDataChanged(arg1, 0);
                    }
                    break;
            }
        }
    }
}
