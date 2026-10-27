package android.hardware.bydauto_test;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class TestSensorManagerImplCallback {
    public static final int SET_ORIENTATION_ANGLE = 22000;
    private AbsBYDAutoSensorListener mAbsBYDAutoSensorListener;
    private Handler mHandler;

    public void setAbsBYDAutoSensorListener(AbsBYDAutoSensorListener absBYDAutoSensorListener) {
        if (absBYDAutoSensorListener == null) {
            return;
        }
        this.mAbsBYDAutoSensorListener = absBYDAutoSensorListener;
    }

    public TestSensorManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestSensorManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestSensorManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestSensorManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoSensorListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            if (i != 22000) {
                return;
            }
            this.mAbsBYDAutoSensorListener.onSlopeValueChanged(i2);
        }
    }
}
