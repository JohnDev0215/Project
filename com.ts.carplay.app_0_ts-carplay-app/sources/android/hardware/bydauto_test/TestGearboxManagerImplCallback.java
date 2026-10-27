package android.hardware.bydauto_test;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestGearboxManagerImplCallback {
    private final List<AbsBYDAutoGearboxListener> mAbsBydAutoGearboxListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoGearboxListener(AbsBYDAutoGearboxListener listener) {
        if (listener != null && !this.mAbsBydAutoGearboxListeners.contains(listener)) {
            this.mAbsBydAutoGearboxListeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoGearboxListener listener) {
        if (listener != null) {
            this.mAbsBydAutoGearboxListeners.remove(listener);
        }
    }

    public TestGearboxManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestGearboxManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestGearboxManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestGearboxManagerImplCallback.this.dispatchMessageCallBack(msg);
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
        if (!this.mAbsBydAutoGearboxListeners.isEmpty()) {
            int arg1 = msg.arg1;
            for (AbsBYDAutoGearboxListener absBydAutoGearboxListener : this.mAbsBydAutoGearboxListeners) {
                absBydAutoGearboxListener.onGearboxAutoModeTypeChanged(arg1);
                absBydAutoGearboxListener.onGearboxManualModeLevelChanged(arg1);
                absBydAutoGearboxListener.onGearboxStateChanged(arg1);
                absBydAutoGearboxListener.onBrakeFluidLevelChanged(arg1);
                absBydAutoGearboxListener.onParkBrakeSwitchChanged(arg1);
                absBydAutoGearboxListener.onBrakePedalStateChanged(arg1);
                absBydAutoGearboxListener.onEPBStateChanged(arg1);
                absBydAutoGearboxListener.onCurrentGearChanged(arg1);
                absBydAutoGearboxListener.onError(arg1, arg1 + "");
                absBydAutoGearboxListener.onDataEventChanged(arg1, new BYDAutoEventValue());
            }
        }
    }
}
