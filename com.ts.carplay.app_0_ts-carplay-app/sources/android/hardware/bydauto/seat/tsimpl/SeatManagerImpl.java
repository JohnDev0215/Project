package android.hardware.bydauto.seat.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.seat.AbsBYDAutoSeatListener;
import android.hardware.bydauto_test.TestSeatManagerImplCallback;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.CarCabinAdapterManager;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import com.ts.lib.caradapter.interfaces.OnCarAdapterConnectListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SeatManagerImpl {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_SeatManagerImpl";
    private static final String TAG = "SeatManagerImpl";
    private static volatile SeatManagerImpl sInstance;
    private final List<AbsBYDAutoSeatListener> mAbsBydAutoSeatListeners = new ArrayList();
    private CabinPacket mCabinPacket;
    private CarAdapterManager mCarAdapterManager;
    private CarCabinAdapterManager mCarCabinAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private TestSeatManagerImplCallback mTestCallback;

    SeatManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        ContentResolver contentResolver = con.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (i == 0) {
                this.mTestCallback = new TestSeatManagerImplCallback();
                Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(con);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1
            public void onBinderStateChanged(boolean b) {
                if (b) {
                    SeatManagerImpl seatManagerImpl = SeatManagerImpl.this;
                    seatManagerImpl.mCarCabinAdapterManager = (CarCabinAdapterManager) seatManagerImpl.mCarAdapterManager.getCarAdapterManager("cabin");
                    SeatManagerImpl.this.mCarCabinAdapterManager.subscribeCabinCallback(new OnCabinChangedListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1.1
                        public void onCabinChanged(int id, int areaId, CabinPacket cabinPacket) {
                            SeatManagerImpl.this.mCabinPacket = cabinPacket;
                        }

                        public void onCabinBinderChanged(boolean b2) {
                        }
                    });
                }
            }
        });
        this.mCarAdapterManager.connect();
    }

    public static SeatManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (SeatManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new SeatManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    public int getSteeringWheelWarmState() {
        Log.d(TAG, "getSteeringWheelWarmState is execute");
        if (this.mForTest == 0) {
            Log.d(TAG, "getSteeringWheelWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.getSteeringWheelWarmState();
    }

    public int setSteeringWheelWarmState(int state) {
        Log.d(TAG, "setSteeringWheelWarmState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3003, state);
            Log.d(TAG, "setSteeringWheelWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSteeringWheelWarmState(state);
    }

    public int getSeatVentilationState(int area) {
        Log.d(TAG, "getSeatVentilationState is execute");
        if (this.mForTest == 0) {
            Log.d(TAG, "getSeatVentilationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.getSeatVentilationState(area);
    }

    public int setSeatVentilationState(int area, int value) {
        Log.d(TAG, "setSeatVentilationState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3004, area, value);
            Log.d(TAG, "setSeatVentilationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatVentilationState(area, value);
    }

    public int getSeatWarmState(int area) {
        Log.d(TAG, "getSeatWarmState is execute");
        if (this.mForTest == 0) {
            Log.d(TAG, "getSeatWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.getSeatWarmState(area);
    }

    public int setSeatWarmState(int area, int state) {
        Log.d(TAG, "setSeatWarmState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3000, area, state);
            Log.d(TAG, "setSeatWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatWarmState(area, state);
    }

    public int getSeatMassageLevel(int area) {
        Log.d(TAG, "getSeatMassageLevel is execute");
        if (this.mForTest == 0) {
            Log.d(TAG, "getSeatMassageLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.getSeatMassageLevel(area);
    }

    public int setSeatMassageLevel(int area, int value) {
        Log.d(TAG, "setSeatMassageLevel is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3001, area, value);
            Log.d(TAG, "setSeatMassageLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatMassageLevel(area, value);
    }

    public int getSeatMassageType(int area) {
        Log.d(TAG, "getSeatMassageType is execute");
        if (this.mForTest == 0) {
            Log.d(TAG, "getSeatMassageType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.getSeatMassageType(area);
    }

    public int setSeatMassageType(int area, int state) {
        Log.d(TAG, "setSeatMassageType is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3002, area, state);
            Log.d(TAG, "setSeatMassageType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatMassageType(area, state);
    }

    public void registerListener(AbsBYDAutoSeatListener listener) {
        Log.d(TAG, "registerListener is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.setAbsBydAutoSeatListener(listener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "subscribeSeatCallback: " + listener);
        if (listener != null && !this.mAbsBydAutoSeatListeners.contains(listener)) {
            this.mAbsBydAutoSeatListeners.add(listener);
        }
    }

    public void unregisterListener(AbsBYDAutoSeatListener listener) {
        Log.d(TAG, "unregisterListener is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.removeListener(listener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeSeatCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoSeatListeners.remove(listener);
    }
}
