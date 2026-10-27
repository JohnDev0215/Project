package android.hardware.bydauto_test;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class TestBodyworkManagerImplCallback {
    public static final int SET_WINDOW_CONTROL_STATE = 4002;
    public static final int SET_WINDOW_LOCK_STATE = 4001;
    public static final int SET_WINDOW_OPEN_PERCENT = 4000;
    private final List<AbsBYDAutoBodyworkListener> mAbsBydAutoBodyworkListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoBodyworkListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener == null || this.mAbsBydAutoBodyworkListeners.contains(absBYDAutoBodyworkListener)) {
            return;
        }
        this.mAbsBydAutoBodyworkListeners.add(absBYDAutoBodyworkListener);
    }

    public void removeListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener != null) {
            this.mAbsBydAutoBodyworkListeners.remove(absBYDAutoBodyworkListener);
        }
    }

    public TestBodyworkManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestBodyworkManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestBodyworkManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestBodyworkManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
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

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoBodyworkListeners.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 4000:
                Iterator<AbsBYDAutoBodyworkListener> it = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it.hasNext()) {
                    it.next().onWindowOpenPercentChanged(i2, i3);
                }
                break;
            case 4001:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onWindowLockStateChanged(i2, i3);
                }
                break;
            case 4002:
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onWindowControlStateChanged(i2, i3);
                }
                break;
        }
    }
}
