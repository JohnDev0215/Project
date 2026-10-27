package android.hardware.bydauto_test;

import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestFragranceManagerImplCallback {
    public static final int SET_FRAGRANCE_CONCENTRATION = 2001;
    public static final int SET_FRAGRANCE_TYPE = 2000;
    private final List<AbsBYDAutoFragranceListener> mAbsBydAutoFragranceListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoFragranceListener(AbsBYDAutoFragranceListener listener) {
        if (listener != null && !this.mAbsBydAutoFragranceListeners.contains(listener)) {
            this.mAbsBydAutoFragranceListeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoFragranceListener listener) {
        if (listener != null) {
            this.mAbsBydAutoFragranceListeners.remove(listener);
        }
    }

    public TestFragranceManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestFragranceManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestFragranceManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestFragranceManagerImplCallback.this.dispatchMessageCallBack(msg);
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
        if (!this.mAbsBydAutoFragranceListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int arg2 = msg.arg2;
            if (what == 2000) {
                for (AbsBYDAutoFragranceListener absBydAutoFragranceListener : this.mAbsBydAutoFragranceListeners) {
                    absBydAutoFragranceListener.onFragranceTypeChanged(arg1);
                }
                return;
            }
            if (what == 2001) {
                for (AbsBYDAutoFragranceListener absBydAutoFragranceListener2 : this.mAbsBydAutoFragranceListeners) {
                    absBydAutoFragranceListener2.onFragranceConcentrationChanged(arg1, arg2);
                }
            }
        }
    }
}
