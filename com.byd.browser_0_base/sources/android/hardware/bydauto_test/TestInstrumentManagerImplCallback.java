package android.hardware.bydauto_test;

import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.os.Handler;
import android.os.Message;
import defpackage.C0173a;
import java.util.ArrayList;
import java.util.Iterator;
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
    private Handler mHandler = new Handler(C0173a.h("TestInstrumentManagerImplCallback").getLooper()) { // from class: android.hardware.bydauto_test.TestInstrumentManagerImplCallback.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TestInstrumentManagerImplCallback.this.dispatchMessageCallBack(message);
        }
    };

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBydAutoInstrumentListeners.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case SET_INSTRUMENT_THEME /* 7000 */:
                Iterator<AbsBYDAutoInstrumentListener> it = this.mAbsBydAutoInstrumentListeners.iterator();
                while (it.hasNext()) {
                    it.next().onInstrumentThemeChange(i2);
                }
                break;
            case SET_MILES_TYPE /* 7001 */:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.mAbsBydAutoInstrumentListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onMilesTypeChanged(i2);
                }
                break;
            case SET_MAINTENANCE_INFO /* 7002 */:
                Iterator<AbsBYDAutoInstrumentListener> it3 = this.mAbsBydAutoInstrumentListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onMaintenanceInfoStateChanged(i2, i3);
                }
                break;
            case SET_INSTRUMENT_AUTO_DIMM_STATE /* 7003 */:
                Iterator<AbsBYDAutoInstrumentListener> it4 = this.mAbsBydAutoInstrumentListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onInstrumentAutoDimmStateChange(i2);
                }
                break;
            case SET_INSTRUMENT_BRIGHTNESS /* 7004 */:
                Iterator<AbsBYDAutoInstrumentListener> it5 = this.mAbsBydAutoInstrumentListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onInstrumentBrightnessChanged(i2);
                }
                break;
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

    public void removeListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener != null) {
            this.mAbsBydAutoInstrumentListeners.remove(absBYDAutoInstrumentListener);
        }
    }

    public void setAbsBydAutoInstrumentListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener == null || this.mAbsBydAutoInstrumentListeners.contains(absBYDAutoInstrumentListener)) {
            return;
        }
        this.mAbsBydAutoInstrumentListeners.add(absBYDAutoInstrumentListener);
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
}
