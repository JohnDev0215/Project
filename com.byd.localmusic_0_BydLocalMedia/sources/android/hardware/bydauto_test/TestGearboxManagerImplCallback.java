package android.hardware.bydauto_test;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class TestGearboxManagerImplCallback {
    private final List<AbsBYDAutoGearboxListener> mAbsBydAutoGearboxListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoGearboxListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.mAbsBydAutoGearboxListeners.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.mAbsBydAutoGearboxListeners.add(absBYDAutoGearboxListener);
    }

    public void removeListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener != null) {
            this.mAbsBydAutoGearboxListeners.remove(absBYDAutoGearboxListener);
        }
    }

    public TestGearboxManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestGearboxManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestGearboxManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestGearboxManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.mHandler.sendEmptyMessage(i);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoGearboxListeners.isEmpty()) {
            return;
        }
        int i = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.mAbsBydAutoGearboxListeners) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i);
            absBYDAutoGearboxListener.onGearboxStateChanged(i);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i);
            absBYDAutoGearboxListener.onEPBStateChanged(i);
            absBYDAutoGearboxListener.onCurrentGearChanged(i);
            absBYDAutoGearboxListener.onError(i, i + "");
            absBYDAutoGearboxListener.onDataEventChanged(i, new BYDAutoEventValue());
        }
    }
}
