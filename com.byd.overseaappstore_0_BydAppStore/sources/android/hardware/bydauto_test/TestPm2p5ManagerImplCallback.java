package android.hardware.bydauto_test;

import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
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

    public TestPm2p5ManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestPm2p5ManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestPm2p5ManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestPm2p5ManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoPM2p5Listeners.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 5000:
                Iterator<AbsBYDAutoPM2p5Listener> it = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it.hasNext()) {
                    it.next().onRapidPurificationPM2p5StateChanged(i2);
                }
                break;
            case 5001:
                Iterator<AbsBYDAutoPM2p5Listener> it2 = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onNegativeIonStateChange(i2);
                }
                break;
            case 5002:
                Iterator<AbsBYDAutoPM2p5Listener> it3 = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onPM2p5CheckStateChanged(i2, i3);
                }
                break;
            case 5003:
                Iterator<AbsBYDAutoPM2p5Listener> it4 = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onPM2p5DualChannelDetectChanged(i2);
                }
                break;
            case 5004:
                Iterator<AbsBYDAutoPM2p5Listener> it5 = this.mAbsBydAutoPM2p5Listeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onPM2p5AnionDetectChanged(i2);
                }
                break;
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

    public void enqueueMessage(int i, int i2, int i3) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void removeListener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener != null) {
            this.mAbsBydAutoPM2p5Listeners.remove(absBYDAutoPM2p5Listener);
        }
    }

    public void setAbsBydAutoPm2p5Listener(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener == null || this.mAbsBydAutoPM2p5Listeners.contains(absBYDAutoPM2p5Listener)) {
            return;
        }
        this.mAbsBydAutoPM2p5Listeners.add(absBYDAutoPM2p5Listener);
    }
}
