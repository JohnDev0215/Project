package android.hardware.bydauto_test;

import android.hardware.bydauto.seat.AbsBYDAutoSeatListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestSeatManagerImplCallback {
    public static final int SET_SEAT_MASSAGE_LEVEL = 3001;
    public static final int SET_SEAT_MASSAGE_TYPE = 3002;
    public static final int SET_SEAT_VENTILATION_STATE = 3004;
    public static final int SET_SEAT_WARM_STATE = 3000;
    public static final int SET_STEERING_WHEEL_WARM_STATE = 3003;
    private final List<AbsBYDAutoSeatListener> mAbsBydAutoSeatListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoSeatListener(AbsBYDAutoSeatListener listener) {
        if (listener != null && !this.mAbsBydAutoSeatListeners.contains(listener)) {
            this.mAbsBydAutoSeatListeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoSeatListener listener) {
        if (listener != null) {
            this.mAbsBydAutoSeatListeners.remove(listener);
        }
    }

    public TestSeatManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestSeatManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestSeatManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestSeatManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
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

    public void enqueueMessage(int what, int arg1, int arg2) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            message.arg2 = arg2;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (!this.mAbsBydAutoSeatListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int arg2 = msg.arg2;
            switch (what) {
                case 3000:
                    for (AbsBYDAutoSeatListener absBydAutoSeatListener : this.mAbsBydAutoSeatListeners) {
                        absBydAutoSeatListener.onSeatWarmStateChanged(arg1, arg2);
                    }
                    break;
                case 3001:
                    for (AbsBYDAutoSeatListener absBydAutoSeatListener2 : this.mAbsBydAutoSeatListeners) {
                        absBydAutoSeatListener2.onSeatMassageLevelChange(arg1, arg2);
                    }
                    break;
                case 3002:
                    for (AbsBYDAutoSeatListener absBydAutoSeatListener3 : this.mAbsBydAutoSeatListeners) {
                        absBydAutoSeatListener3.onSeatMassageTypeChange(arg1, arg2);
                    }
                    break;
                case 3003:
                    for (AbsBYDAutoSeatListener absBydAutoSeatListener4 : this.mAbsBydAutoSeatListeners) {
                        absBydAutoSeatListener4.onSteeringWheelWarmStateChanged(arg1);
                    }
                    break;
                case 3004:
                    for (AbsBYDAutoSeatListener absBydAutoSeatListener5 : this.mAbsBydAutoSeatListeners) {
                        absBydAutoSeatListener5.onSeatVentilationState(arg1, arg2);
                    }
                    break;
            }
        }
    }
}
