package android.hardware.bydauto_test;

import android.hardware.bydauto.seat.AbsBYDAutoSeatListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
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

    public TestSeatManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestSeatManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestSeatManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestSeatManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoSeatListeners.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 3000:
                Iterator<AbsBYDAutoSeatListener> it = this.mAbsBydAutoSeatListeners.iterator();
                while (it.hasNext()) {
                    it.next().onSeatWarmStateChanged(i2, i3);
                }
                break;
            case 3001:
                Iterator<AbsBYDAutoSeatListener> it2 = this.mAbsBydAutoSeatListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onSeatMassageLevelChange(i2, i3);
                }
                break;
            case 3002:
                Iterator<AbsBYDAutoSeatListener> it3 = this.mAbsBydAutoSeatListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onSeatMassageTypeChange(i2, i3);
                }
                break;
            case 3003:
                Iterator<AbsBYDAutoSeatListener> it4 = this.mAbsBydAutoSeatListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteeringWheelWarmStateChanged(i2);
                }
                break;
            case 3004:
                Iterator<AbsBYDAutoSeatListener> it5 = this.mAbsBydAutoSeatListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onSeatVentilationState(i2, i3);
                }
                break;
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

    public void enqueueMessage(int i, int i2, int i3) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void removeListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener != null) {
            this.mAbsBydAutoSeatListeners.remove(absBYDAutoSeatListener);
        }
    }

    public void setAbsBydAutoSeatListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener == null || this.mAbsBydAutoSeatListeners.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.mAbsBydAutoSeatListeners.add(absBYDAutoSeatListener);
    }
}
