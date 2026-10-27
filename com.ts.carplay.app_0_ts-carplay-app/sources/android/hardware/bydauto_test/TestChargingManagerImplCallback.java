package android.hardware.bydauto_test;

import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
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

    public void setAbsBYDAutoChargingListener(AbsBYDAutoChargingListener listener) {
        if (listener != null && !this.mAbsBydAutoChargingListeners.contains(listener)) {
            this.mAbsBydAutoChargingListeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoChargingListener listener) {
        if (listener != null) {
            this.mAbsBydAutoChargingListeners.remove(listener);
        }
    }

    public TestChargingManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestChargingManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestChargingManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestChargingManagerImplCallback.this.dispatchMessageCallBack(msg);
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
            this.mHandler.sendMessage(message);
        }
    }

    public void enqueueMessage(int what, Object object) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.obj = object;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (!this.mAbsBydAutoChargingListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int i = msg.arg2;
            Object object = msg.obj;
            switch (what) {
                case 6011:
                    for (AbsBYDAutoChargingListener absBydAutoChargingListener : this.mAbsBydAutoChargingListeners) {
                        absBydAutoChargingListener.onIntelligentKeepTemModeChanged(arg1);
                        absBydAutoChargingListener.onChargeTempCtlStateChanged(arg1);
                    }
                    break;
                case SET_CHARGE_LIMIT /* 6012 */:
                    for (AbsBYDAutoChargingListener absBydAutoChargingListener2 : this.mAbsBydAutoChargingListeners) {
                        absBydAutoChargingListener2.onChargeLimitChanged(((Float) object).floatValue());
                        absBydAutoChargingListener2.onChargeStopCapacityStateChanged(((Integer) object).intValue());
                    }
                    break;
                case SET_CHARGE_TOAGGLE_GUARD /* 6013 */:
                    Iterator<AbsBYDAutoChargingListener> it = this.mAbsBydAutoChargingListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onChargeToggleGuardChanged(arg1);
                    }
                    break;
                case SET_MOVE_POWER_STATION_TOGGLE /* 6014 */:
                    Iterator<AbsBYDAutoChargingListener> it2 = this.mAbsBydAutoChargingListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onMovePowerStationToggleChanged(arg1);
                    }
                    break;
                case SET_DISCHARGE_LIMIT /* 6015 */:
                    Iterator<AbsBYDAutoChargingListener> it3 = this.mAbsBydAutoChargingListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onDischargeLimitChanged(arg1);
                    }
                    break;
                case SET_DISCHARGE_TOGGLE /* 6016 */:
                    Iterator<AbsBYDAutoChargingListener> it4 = this.mAbsBydAutoChargingListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onDischargeToggleChanged(arg1);
                    }
                    break;
                case SET_DISCHARGE_TIME /* 6017 */:
                    Iterator<AbsBYDAutoChargingListener> it5 = this.mAbsBydAutoChargingListeners.iterator();
                    while (it5.hasNext()) {
                        it5.next().onDischargeTimeChanged(((Long) object).longValue());
                    }
                    break;
                case SET_PORDUCE_POWER_WHEN_LOW_POWER /* 6018 */:
                    Iterator<AbsBYDAutoChargingListener> it6 = this.mAbsBydAutoChargingListeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onPorducePowerWhenLowPowerChanged(arg1);
                    }
                    break;
            }
        }
    }
}
