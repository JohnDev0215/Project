package android.hardware.bydauto_test;

import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestInstrumentManagerImplCallback {
    public static final int SET_INSTRUMENT_AUTO_DIMM_STATE = 7003;
    public static final int SET_INSTRUMENT_BRIGHTNESS = 7004;
    public static final int SET_INSTRUMENT_THEME = 7000;
    public static final int SET_MAINTENANCE_INFO = 7002;
    public static final int SET_MAINTENANCE_INFO_MILE_STATE = 7006;
    public static final int SET_MAINTENANCE_INFO_TIME_STATE = 7005;
    public static final int SET_MILES_TYPE = 7001;
    private final List<AbsBYDAutoInstrumentListener> mAbsBydAutoInstrumentListeners = new ArrayList();
    private Handler mHandler;

    public void setAbsBydAutoInstrumentListener(AbsBYDAutoInstrumentListener listener) {
        if (listener != null && !this.mAbsBydAutoInstrumentListeners.contains(listener)) {
            this.mAbsBydAutoInstrumentListeners.add(listener);
        }
    }

    public void removeListener(AbsBYDAutoInstrumentListener listener) {
        if (listener != null) {
            this.mAbsBydAutoInstrumentListeners.remove(listener);
        }
    }

    public TestInstrumentManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestInstrumentManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestInstrumentManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestInstrumentManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
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

    public void enqueueMessage(int what, int arg1) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            message.arg2 = 0;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (!this.mAbsBydAutoInstrumentListeners.isEmpty()) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int arg2 = msg.arg2;
            switch (what) {
                case SET_INSTRUMENT_THEME /* 7000 */:
                    for (AbsBYDAutoInstrumentListener absBydAutoInstrumentListener : this.mAbsBydAutoInstrumentListeners) {
                        absBydAutoInstrumentListener.onInstrumentThemeChange(arg1);
                    }
                    break;
                case SET_MILES_TYPE /* 7001 */:
                    for (AbsBYDAutoInstrumentListener absBydAutoInstrumentListener2 : this.mAbsBydAutoInstrumentListeners) {
                        absBydAutoInstrumentListener2.onMilesTypeChanged(arg1);
                    }
                    break;
                case SET_MAINTENANCE_INFO /* 7002 */:
                    for (AbsBYDAutoInstrumentListener absBydAutoInstrumentListener3 : this.mAbsBydAutoInstrumentListeners) {
                        absBydAutoInstrumentListener3.onMaintenanceInfoStateChanged(arg1, arg2);
                    }
                    break;
                case SET_INSTRUMENT_AUTO_DIMM_STATE /* 7003 */:
                    for (AbsBYDAutoInstrumentListener absBydAutoInstrumentListener4 : this.mAbsBydAutoInstrumentListeners) {
                        absBydAutoInstrumentListener4.onInstrumentAutoDimmStateChange(arg1);
                    }
                    break;
                case SET_INSTRUMENT_BRIGHTNESS /* 7004 */:
                    for (AbsBYDAutoInstrumentListener absBydAutoInstrumentListener5 : this.mAbsBydAutoInstrumentListeners) {
                        absBydAutoInstrumentListener5.onInstrumentBrightnessChanged(arg1);
                    }
                    break;
            }
        }
    }
}
