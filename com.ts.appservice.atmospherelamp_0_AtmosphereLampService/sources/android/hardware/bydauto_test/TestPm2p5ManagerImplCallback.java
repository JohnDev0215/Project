package android.hardware.bydauto_test;

import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestPm2p5ManagerImplCallback {
    public static final int SET_NEGATIVE_ION_STATE = 5001;
    public static final int SET_P_M2P5_ANION_DETECT_STATE = 5004;
    public static final int SET_P_M2P5_CHECK_STATE = 5002;
    public static final int SET_P_M2P5_DUAL_CHANNEL_DETECT_STATE = 5003;
    public static final int SET_RAPID_PURIFICATION_P_M2P5_STATE = 5000;
    private final List<AbsBYDAutoPM2p5Listener> mAbsBydAutoPM2p5Listeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoPm2p5Listener(AbsBYDAutoPM2p5Listener listener) {
        if (listener != null && !this.mAbsBydAutoPM2p5Listeners.contains(listener)) {
            this.mAbsBydAutoPM2p5Listeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoPM2p5Listener listener) {
        if (listener != null) {
            this.mAbsBydAutoPM2p5Listeners.remove(listener);
        }
    }

    public TestPm2p5ManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestPm2p5ManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestPm2p5ManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestPm2p5ManagerImplCallback.this.dispatchMessageCallBack(msg);
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
        if (!this.mAbsBydAutoPM2p5Listeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int arg2 = msg.arg2;
            switch (what) {
                case 5000:
                    for (AbsBYDAutoPM2p5Listener absBydAutoPm2p5Listener : this.mAbsBydAutoPM2p5Listeners) {
                        absBydAutoPm2p5Listener.onRapidPurificationPM2p5StateChanged(arg1);
                    }
                    break;
                case 5001:
                    for (AbsBYDAutoPM2p5Listener absBydAutoPm2p5Listener2 : this.mAbsBydAutoPM2p5Listeners) {
                        absBydAutoPm2p5Listener2.onNegativeIonStateChange(arg1);
                    }
                    break;
                case 5002:
                    for (AbsBYDAutoPM2p5Listener absBydAutoPm2p5Listener3 : this.mAbsBydAutoPM2p5Listeners) {
                        absBydAutoPm2p5Listener3.onPM2p5CheckStateChanged(arg1, arg2);
                    }
                    break;
                case 5003:
                    for (AbsBYDAutoPM2p5Listener absBydAutoPm2p5Listener4 : this.mAbsBydAutoPM2p5Listeners) {
                        absBydAutoPm2p5Listener4.onPM2p5DualChannelDetectChanged(arg1);
                    }
                    break;
                case 5004:
                    for (AbsBYDAutoPM2p5Listener absBydAutoPm2p5Listener5 : this.mAbsBydAutoPM2p5Listeners) {
                        absBydAutoPm2p5Listener5.onPM2p5AnionDetectChanged(arg1);
                    }
                    break;
            }
        }
    }
}
