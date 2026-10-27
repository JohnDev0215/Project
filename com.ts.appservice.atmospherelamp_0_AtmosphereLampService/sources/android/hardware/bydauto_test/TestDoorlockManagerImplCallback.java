package android.hardware.bydauto_test;

import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class TestDoorlockManagerImplCallback {
    public static final int SET_DOOR_LOCK_STATUS = 32000;
    private AbsBYDAutoDoorLockListener mAbsBYDAutoDoorLockListener;
    private Handler mHandler;

    public void setAbsBYDAutoSensorListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.mAbsBYDAutoDoorLockListener = absBYDAutoDoorLockListener;
    }

    public TestDoorlockManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestDoorlockManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestDoorlockManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestDoorlockManagerImplCallback.this.dispatchMessageCallBack(msg);
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

    public void dispatchMessageCallBack(Message msg) {
        if (this.mAbsBYDAutoDoorLockListener != null) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int arg2 = msg.arg2;
            if (what == 32000) {
                this.mAbsBYDAutoDoorLockListener.onDoorLockStatusChanged(arg1, arg2);
            }
        }
    }
}
