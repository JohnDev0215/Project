package android.hardware.bydauto_test;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.Message;
import defpackage.C0173a;

/* JADX INFO: loaded from: classes.dex */
public class TestSensorManagerImplCallback {
    public static final int SET_ORIENTATION_ANGLE = 22000;
    private AbsBYDAutoSensorListener mAbsBYDAutoSensorListener;
    private Handler mHandler = new Handler(C0173a.h("TestSensorManagerImplCallback").getLooper()) { // from class: android.hardware.bydauto_test.TestSensorManagerImplCallback.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TestSensorManagerImplCallback.this.dispatchMessageCallBack(message);
        }
    };

    public void dispatchMessageCallBack(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.mAbsBYDAutoSensorListener;
        if (absBYDAutoSensorListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            if (i != 22000) {
                return;
            }
            absBYDAutoSensorListener.onSlopeValueChanged(i2);
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

    public void setAbsBYDAutoSensorListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        if (absBYDAutoSensorListener == null) {
            return;
        }
        this.mAbsBYDAutoSensorListener = absBYDAutoSensorListener;
    }
}
