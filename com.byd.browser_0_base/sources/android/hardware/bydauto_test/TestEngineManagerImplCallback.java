package android.hardware.bydauto_test;

import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.os.Handler;
import android.os.Message;
import defpackage.C0173a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestEngineManagerImplCallback {
    public static final int SET_ENGINE_CONDITION = 8000;
    private final List<AbsBYDAutoEngineListener> mAbsBydAutoEngineListeners = new ArrayList();
    private Handler mHandler = new Handler(C0173a.h("TestEngineManagerImplCallback").getLooper()) { // from class: android.hardware.bydauto_test.TestEngineManagerImplCallback.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TestEngineManagerImplCallback.this.dispatchMessageCallBack(message);
        }
    };

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoEngineListeners.isEmpty()) {
            return;
        }
        int i = message.what;
        int i2 = message.arg1;
        if (i != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.mAbsBydAutoEngineListeners.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i2);
        }
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.mHandler.sendEmptyMessage(i);
        }
    }

    public void removeListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.mAbsBydAutoEngineListeners.remove(absBYDAutoEngineListener);
        }
    }

    public void setAbsBydAutoEngineListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.mAbsBydAutoEngineListeners.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.mAbsBydAutoEngineListeners.add(absBYDAutoEngineListener);
    }
}
