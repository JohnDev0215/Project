package android.hardware.bydauto_test;

import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class TestChargingManagerImplCallback {
    public static final int SET_CHARGE_LIMIT = 6012;
    public static final int SET_CHARGE_TOAGGLE_GUARD = 6013;
    public static final int SET_DISCHARGE_LIMIT = 6015;
    public static final int SET_DISCHARGE_TIME = 6017;
    public static final int SET_DISCHARGE_TOGGLE = 6016;
    public static final int SET_INTELLIGENT_KEEP_TEM_MODE = 6011;
    public static final int SET_MOVE_POWER_STATION_TOGGLE = 6014;
    public static final int SET_PORDUCE_POWER_WHEN_LOW_POWER = 6018;
    private final List<AbsBYDAutoChargingListener> mAbsBydAutoChargingListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBYDAutoChargingListener(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener == null || this.mAbsBydAutoChargingListeners.contains(absBYDAutoChargingListener)) {
            return;
        }
        this.mAbsBydAutoChargingListeners.add(absBYDAutoChargingListener);
    }

    public void removeListener(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener != null) {
            this.mAbsBydAutoChargingListeners.remove(absBYDAutoChargingListener);
        }
    }

    public TestChargingManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestChargingManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestChargingManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestChargingManagerImplCallback.this.dispatchMessageCallBack(message);
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
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void enqueueMessage(int i, Object obj) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.obj = obj;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoChargingListeners.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        Object obj = message.obj;
        switch (i) {
            case 6011:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener : this.mAbsBydAutoChargingListeners) {
                    absBYDAutoChargingListener.onIntelligentKeepTemModeChanged(i2);
                    absBYDAutoChargingListener.onChargeTempCtlStateChanged(i2);
                }
                break;
            case SET_CHARGE_LIMIT /* 6012 */:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener2 : this.mAbsBydAutoChargingListeners) {
                    absBYDAutoChargingListener2.onChargeLimitChanged(((Float) obj).floatValue());
                    absBYDAutoChargingListener2.onChargeStopCapacityStateChanged(((Integer) obj).intValue());
                }
                break;
            case SET_CHARGE_TOAGGLE_GUARD /* 6013 */:
                Iterator<AbsBYDAutoChargingListener> it = this.mAbsBydAutoChargingListeners.iterator();
                while (it.hasNext()) {
                    it.next().onChargeToggleGuardChanged(i2);
                }
                break;
            case SET_MOVE_POWER_STATION_TOGGLE /* 6014 */:
                Iterator<AbsBYDAutoChargingListener> it2 = this.mAbsBydAutoChargingListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onMovePowerStationToggleChanged(i2);
                }
                break;
            case SET_DISCHARGE_LIMIT /* 6015 */:
                Iterator<AbsBYDAutoChargingListener> it3 = this.mAbsBydAutoChargingListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onDischargeLimitChanged(i2);
                }
                break;
            case SET_DISCHARGE_TOGGLE /* 6016 */:
                Iterator<AbsBYDAutoChargingListener> it4 = this.mAbsBydAutoChargingListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onDischargeToggleChanged(i2);
                }
                break;
            case SET_DISCHARGE_TIME /* 6017 */:
                Iterator<AbsBYDAutoChargingListener> it5 = this.mAbsBydAutoChargingListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onDischargeTimeChanged(((Long) obj).longValue());
                }
                break;
            case SET_PORDUCE_POWER_WHEN_LOW_POWER /* 6018 */:
                Iterator<AbsBYDAutoChargingListener> it6 = this.mAbsBydAutoChargingListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onPorducePowerWhenLowPowerChanged(i2);
                }
                break;
        }
    }
}
