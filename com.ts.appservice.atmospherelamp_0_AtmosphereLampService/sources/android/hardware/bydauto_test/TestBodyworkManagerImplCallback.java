package android.hardware.bydauto_test;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestBodyworkManagerImplCallback {
    public static final int SET_WINDOW_CONTROL_STATE = 4002;
    public static final int SET_WINDOW_LOCK_STATE = 4001;
    public static final int SET_WINDOW_OPEN_PERCENT = 4000;
    private final List<AbsBYDAutoBodyworkListener> mAbsBydAutoBodyworkListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoBodyworkListener(AbsBYDAutoBodyworkListener absBydAutoBodyworkListener) {
        if (absBydAutoBodyworkListener != null && !this.mAbsBydAutoBodyworkListeners.contains(absBydAutoBodyworkListener)) {
            this.mAbsBydAutoBodyworkListeners.add(absBydAutoBodyworkListener);
        }
    }

    public void removeListener(AbsBYDAutoBodyworkListener absBydAutoBodyworkListener) {
        if (absBydAutoBodyworkListener != null) {
            this.mAbsBydAutoBodyworkListeners.remove(absBydAutoBodyworkListener);
        }
    }

    public TestBodyworkManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestBodyworkManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestBodyworkManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestBodyworkManagerImplCallback.this.dispatchMessageCallBack(msg);
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
        if (!this.mAbsBydAutoBodyworkListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int arg2 = msg.arg2;
            switch (what) {
                case 4000:
                    for (AbsBYDAutoBodyworkListener absBydAutoBodyworkListener : this.mAbsBydAutoBodyworkListeners) {
                        absBydAutoBodyworkListener.onWindowOpenPercentChanged(arg1, arg2);
                    }
                    break;
                case 4001:
                    for (AbsBYDAutoBodyworkListener absBydAutoBodyworkListener2 : this.mAbsBydAutoBodyworkListeners) {
                        absBydAutoBodyworkListener2.onWindowLockStateChanged(arg1, arg2);
                    }
                    break;
                case 4002:
                    for (AbsBYDAutoBodyworkListener absBydAutoBodyworkListener3 : this.mAbsBydAutoBodyworkListeners) {
                        absBydAutoBodyworkListener3.onWindowControlStateChanged(arg1, arg2);
                    }
                    break;
            }
        }
    }
}
