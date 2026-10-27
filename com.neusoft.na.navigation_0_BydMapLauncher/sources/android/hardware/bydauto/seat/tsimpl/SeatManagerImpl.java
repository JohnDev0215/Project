package android.hardware.bydauto.seat.tsimpl;

import android.car.Car;
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

    SeatManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
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
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(context);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                if (z) {
                    SeatManagerImpl seatManagerImpl = SeatManagerImpl.this;
                    seatManagerImpl.mCarCabinAdapterManager = (CarCabinAdapterManager) seatManagerImpl.mCarAdapterManager.getCarAdapterManager(Car.CABIN_SERVICE);
                    SeatManagerImpl.this.mCarCabinAdapterManager.subscribeCabinCallback(new OnCabinChangedListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1.1
                        public void onCabinBinderChanged(boolean z2) {
                        }

                        public void onCabinChanged(int i2, int i3, CabinPacket cabinPacket) {
                            SeatManagerImpl.this.mCabinPacket = cabinPacket;
                        }
                    });
                }
            }
        });
        this.mCarAdapterManager.connect();
    }

    public static SeatManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SeatManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new SeatManagerImpl(context);
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

    public int setSteeringWheelWarmState(int i) {
        Log.d(TAG, "setSteeringWheelWarmState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3003, i);
            Log.d(TAG, "setSteeringWheelWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSteeringWheelWarmState(i);
    }

    public int getSeatVentilationState(int i) {
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
        return carCabinAdapterManager.getSeatVentilationState(i);
    }

    public int setSeatVentilationState(int i, int i2) {
        Log.d(TAG, "setSeatVentilationState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3004, i, i2);
            Log.d(TAG, "setSeatVentilationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatVentilationState(i, i2);
    }

    public int getSeatWarmState(int i) {
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
        return carCabinAdapterManager.getSeatWarmState(i);
    }

    public int setSeatWarmState(int i, int i2) {
        Log.d(TAG, "setSeatWarmState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3000, i, i2);
            Log.d(TAG, "setSeatWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatWarmState(i, i2);
    }

    public int getSeatMassageLevel(int i) {
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
        return carCabinAdapterManager.getSeatMassageLevel(i);
    }

    public int setSeatMassageLevel(int i, int i2) {
        Log.d(TAG, "setSeatMassageLevel is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3001, i, i2);
            Log.d(TAG, "setSeatMassageLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatMassageLevel(i, i2);
    }

    public int getSeatMassageType(int i) {
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
        return carCabinAdapterManager.getSeatMassageType(i);
    }

    public int setSeatMassageType(int i, int i2) {
        Log.d(TAG, "setSeatMassageType is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3002, i, i2);
            Log.d(TAG, "setSeatMassageType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return carCabinAdapterManager.setSeatMassageType(i, i2);
    }

    public void registerListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        Log.d(TAG, "registerListener is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.setAbsBydAutoSeatListener(absBYDAutoSeatListener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "subscribeSeatCallback: " + absBYDAutoSeatListener);
        if (absBYDAutoSeatListener == null || this.mAbsBydAutoSeatListeners.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.mAbsBydAutoSeatListeners.add(absBYDAutoSeatListener);
    }

    public void unregisterListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.removeListener(absBYDAutoSeatListener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
        } else {
            Log.d(TAG, "unsubscribeSeatCallback: " + absBYDAutoSeatListener);
            if (absBYDAutoSeatListener == null) {
                return;
            }
            this.mAbsBydAutoSeatListeners.remove(absBYDAutoSeatListener);
        }
    }
}
