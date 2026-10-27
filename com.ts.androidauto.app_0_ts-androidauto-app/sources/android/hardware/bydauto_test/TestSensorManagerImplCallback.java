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
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestSensorManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    public void enqueueMessage(int what, int arg1) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (this.mAbsBYDAutoSensorListener != null) {
            int what = msg.what;
            int arg1 = msg.arg1;
            if (what == 22000) {
                this.mAbsBYDAutoSensorListener.onSlopeValueChanged(arg1);
            }
        }
    }
}
