package android.hardware.bydauto_test;

import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
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

    public TestEngergyManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestEngergyManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestEngergyManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestEngergyManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoEnergyListeners.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 6000:
                Iterator<AbsBYDAutoEnergyListener> it = this.mAbsBydAutoEnergyListeners.iterator();
                while (it.hasNext()) {
                    it.next().onEPedalStateChanged(i2);
                }
                break;
            case SET_ENERGY_FEEDBACK /* 6001 */:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onEnergyFeedbackLevelChanged(i2);
                }
                break;
            case SET_DRIVE_DISTANCE_SHOW_STANDARD /* 6002 */:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriveDistanceShowStandardChanged(i2);
                }
                break;
            case SET_MEMORY_MAX_EV_STATE /* 6003 */:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onMemoryMaxEvStateChanged(i2);
                }
                break;
            case SET_ELECTRIC_SHOW_MODE /* 6004 */:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onElectricShowModeChanged(i2);
                }
                break;
            case SET_MEMORY_SPORT_STATE /* 6005 */:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onMemorySportStateChanged(i2);
                }
                break;
            case SET_MANDATORY_ELECTRIC_STATE /* 6006 */:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onMandatoryElectricStateChanged(i2);
                }
                break;
            case SET_S_O_C_TARGET /* 6007 */:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onSOCTargetChanged(i2);
                }
                break;
            case SET_MEMORY_ELECTRIC_STATE /* 6008 */:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onMemoryElectricStateChanged(i2);
                }
                break;
            case SET_DRIVE_DISTANCE_SHOW_MODE /* 6009 */:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowModeChanged(i2);
                }
                break;
            case SET_PRE_SALE_MODEL_STATE /* 6010 */:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onPreSaleModelStateChanged(i2);
                }
                break;
            case 6011:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onResetCurveDataChanged(i2, 0);
                }
                break;
        }
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

    public void removeListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener != null) {
            this.mAbsBydAutoEnergyListeners.remove(absBYDAutoEnergyListener);
        }
    }

    public void setAbsBydAutoEnergyListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener == null || this.mAbsBydAutoEnergyListeners.contains(absBYDAutoEnergyListener)) {
            return;
        }
        this.mAbsBydAutoEnergyListeners.add(absBYDAutoEnergyListener);
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
}
