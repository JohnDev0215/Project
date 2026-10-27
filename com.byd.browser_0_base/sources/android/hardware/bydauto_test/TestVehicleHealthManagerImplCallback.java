package android.hardware.bydauto_test;

import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.os.Handler;
import android.os.Message;
import defpackage.C0173a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestVehicleHealthManagerImplCallback {
    public static final int RESET_OIL_LIFE_TIME = 10003;
    public static final int RESET_OIL_VALUE = 1;
    public static final int RESET_TIRE_PRESSURE = 10002;
    public static final int SET_FRONT_WIPER_CHECK_STATE = 10000;
    public static final int SET_OIL_RECORD_LIST = 10004;
    public static final int SET_REAR_WIPER_CHECK_STATE = 10001;
    private List<AbsBYDAutoVehicleHealthListener> mAbsBYDHealthListeners = new ArrayList();
    private Handler mHandler = new Handler(C0173a.h("TestVehicleHealthManagerImplCallback").getLooper()) { // from class: android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TestVehicleHealthManagerImplCallback.this.dispatchMessageCallBack(message);
        }
    };

    public void dispatchMessageCallBack(Message message) {
        List<AbsBYDAutoVehicleHealthListener> list = this.mAbsBYDHealthListeners;
        if (list != null) {
            int i = message.what;
            int i2 = message.arg1;
            switch (i) {
                case SET_FRONT_WIPER_CHECK_STATE /* 10000 */:
                    Iterator<AbsBYDAutoVehicleHealthListener> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().onFrontWiperCheckStateChanged(i2);
                    }
                    break;
                case SET_REAR_WIPER_CHECK_STATE /* 10001 */:
                    Iterator<AbsBYDAutoVehicleHealthListener> it2 = list.iterator();
                    while (it2.hasNext()) {
                        it2.next().onRearWiperCheckStateChanged(i2);
                    }
                    break;
                case RESET_TIRE_PRESSURE /* 10002 */:
                    Iterator<AbsBYDAutoVehicleHealthListener> it3 = list.iterator();
                    while (it3.hasNext()) {
                        it3.next().onResetTirePressureStateChanged(i2);
                    }
                    break;
                case RESET_OIL_LIFE_TIME /* 10003 */:
                    Iterator<AbsBYDAutoVehicleHealthListener> it4 = list.iterator();
                    while (it4.hasNext()) {
                        it4.next().onResetOilLifetimeChanged(i2, 1);
                    }
                    break;
            }
        }
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void setAbsBYDAutoVehicleHealthListener(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener) {
        if (absBYDAutoVehicleHealthListener == null || this.mAbsBYDHealthListeners.contains(absBYDAutoVehicleHealthListener)) {
            return;
        }
        this.mAbsBYDHealthListeners.add(absBYDAutoVehicleHealthListener);
    }
}
