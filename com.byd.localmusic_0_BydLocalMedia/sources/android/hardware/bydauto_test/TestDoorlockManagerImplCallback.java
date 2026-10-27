package android.hardware.bydauto_test;

import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
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
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestDoorlockManagerImplCallback.this.dispatchMessageCallBack(message);
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

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoDoorLockListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            int i3 = message.arg2;
            if (i != 32000) {
                return;
            }
            this.mAbsBYDAutoDoorLockListener.onDoorLockStatusChanged(i2, i3);
        }
    }
}
