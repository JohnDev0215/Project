package android.hardware.bydauto_test;

import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Handler;
import android.os.Message;
import defpackage.C0173a;

/* JADX INFO: loaded from: classes.dex */
public class TestDoorlockManagerImplCallback {
    public static final int SET_DOOR_LOCK_STATUS = 32000;
    private AbsBYDAutoDoorLockListener mAbsBYDAutoDoorLockListener;
    private Handler mHandler = new Handler(C0173a.h("TestDoorlockManagerImplCallback").getLooper()) { // from class: android.hardware.bydauto_test.TestDoorlockManagerImplCallback.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TestDoorlockManagerImplCallback.this.dispatchMessageCallBack(message);
        }
    };

    public void dispatchMessageCallBack(Message message) {
        AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener = this.mAbsBYDAutoDoorLockListener;
        if (absBYDAutoDoorLockListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            int i3 = message.arg2;
            if (i != 32000) {
                return;
            }
            absBYDAutoDoorLockListener.onDoorLockStatusChanged(i2, i3);
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

    public void setAbsBYDAutoSensorListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.mAbsBYDAutoDoorLockListener = absBYDAutoDoorLockListener;
    }
}
