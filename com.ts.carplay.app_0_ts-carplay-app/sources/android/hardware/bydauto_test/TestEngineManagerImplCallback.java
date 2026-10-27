package android.hardware.bydauto_test;

import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestEngineManagerImplCallback {
    public static final int SET_ENGINE_CONDITION = 8000;
    private final List<AbsBYDAutoEngineListener> mAbsBydAutoEngineListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoEngineListener(AbsBYDAutoEngineListener listener) {
        if (listener != null && !this.mAbsBydAutoEngineListeners.contains(listener)) {
            this.mAbsBydAutoEngineListeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoEngineListener listener) {
        if (listener != null) {
            this.mAbsBydAutoEngineListeners.remove(listener);
        }
    }

    public TestEngineManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestEngineManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestEngineManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestEngineManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    public void enqueueMessage(int what, int arg1) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            this.mHandler.sendEmptyMessage(what);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (!this.mAbsBydAutoEngineListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int i = msg.arg2;
            if (what == 8000) {
                for (AbsBYDAutoEngineListener absBydAutoEngineListener : this.mAbsBydAutoEngineListeners) {
                    absBydAutoEngineListener.onEngineConditionChanged(arg1);
                }
            }
        }
    }
}
