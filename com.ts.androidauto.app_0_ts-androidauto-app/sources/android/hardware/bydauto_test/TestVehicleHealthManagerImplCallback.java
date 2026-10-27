package android.hardware.bydauto_test;

import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
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
    private Handler mHandler;

    public void setAbsBYDAutoVehicleHealthListener(AbsBYDAutoVehicleHealthListener listener) {
        if (listener != null && !this.mAbsBYDHealthListeners.contains(listener)) {
            this.mAbsBYDHealthListeners.add(listener);
        }
    }

    public TestVehicleHealthManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestVehicleHealthManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestVehicleHealthManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    public void enqueueMessage(int what, int arg1) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (this.mAbsBYDHealthListeners != null) {
            int what = msg.what;
            int arg1 = msg.arg1;
            switch (what) {
                case SET_FRONT_WIPER_CHECK_STATE /* 10000 */:
                    for (AbsBYDAutoVehicleHealthListener mAbsBYDHealthListener : this.mAbsBYDHealthListeners) {
                        mAbsBYDHealthListener.onFrontWiperCheckStateChanged(arg1);
                    }
                    break;
                case SET_REAR_WIPER_CHECK_STATE /* 10001 */:
                    for (AbsBYDAutoVehicleHealthListener mAbsBYDHealthListener2 : this.mAbsBYDHealthListeners) {
                        mAbsBYDHealthListener2.onRearWiperCheckStateChanged(arg1);
                    }
                    break;
                case RESET_TIRE_PRESSURE /* 10002 */:
                    for (AbsBYDAutoVehicleHealthListener mAbsBYDHealthListener3 : this.mAbsBYDHealthListeners) {
                        mAbsBYDHealthListener3.onResetTirePressureStateChanged(arg1);
                    }
                    break;
                case RESET_OIL_LIFE_TIME /* 10003 */:
                    for (AbsBYDAutoVehicleHealthListener mAbsBYDHealthListener4 : this.mAbsBYDHealthListeners) {
                        mAbsBYDHealthListener4.onResetOilLifetimeChanged(arg1, 1);
                    }
                    break;
            }
        }
    }
}
