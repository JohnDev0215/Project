package android.hardware.bydauto_test;

import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestFragranceManagerImplCallback {
    public static final int SET_FRAGRANCE_CONCENTRATION = 2001;
    public static final int SET_FRAGRANCE_TYPE = 2000;
    private final List<AbsBYDAutoFragranceListener> mAbsBydAutoFragranceListeners = new ArrayList();
    private Handler mHandler;

    public TestFragranceManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestFragranceManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestFragranceManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestFragranceManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoFragranceListeners.isEmpty()) {
            return;
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        if (i == 2000) {
            Iterator<AbsBYDAutoFragranceListener> it = this.mAbsBydAutoFragranceListeners.iterator();
            while (it.hasNext()) {
                it.next().onFragranceTypeChanged(i2);
            }
        } else {
            if (i != 2001) {
                return;
            }
            Iterator<AbsBYDAutoFragranceListener> it2 = this.mAbsBydAutoFragranceListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onFragranceConcentrationChanged(i2, i3);
            }
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

    public void removeListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener != null) {
            this.mAbsBydAutoFragranceListeners.remove(absBYDAutoFragranceListener);
        }
    }

    public void setAbsBydAutoFragranceListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener == null || this.mAbsBydAutoFragranceListeners.contains(absBYDAutoFragranceListener)) {
            return;
        }
        this.mAbsBydAutoFragranceListeners.add(absBYDAutoFragranceListener);
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
}
