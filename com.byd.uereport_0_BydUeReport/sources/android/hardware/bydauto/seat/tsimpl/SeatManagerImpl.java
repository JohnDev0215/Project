package android.hardware.bydauto.seat.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.seat.AbsBYDAutoSeatListener;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.CarCabinAdapterManager;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import com.ts.lib.caradapter.interfaces.OnCarAdapterConnectListener;
import e.m;
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
    private m mTestCallback;

    public SeatManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i8 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i8;
            if (i8 == 0) {
                this.mTestCallback = new m();
                Log.d(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(context);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1
            public void onBinderStateChanged(boolean z7) {
                if (z7) {
                    SeatManagerImpl seatManagerImpl = SeatManagerImpl.this;
                    seatManagerImpl.mCarCabinAdapterManager = (CarCabinAdapterManager) seatManagerImpl.mCarAdapterManager.getCarAdapterManager("cabin");
                    SeatManagerImpl.this.mCarCabinAdapterManager.subscribeCabinCallback(new OnCabinChangedListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1.1
                        public void onCabinBinderChanged(boolean z8) {
                        }

                        public void onCabinChanged(int i9, int i10, CabinPacket cabinPacket) {
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

    public int getSeatMassageLevel(int i8) {
        Log.d(TAG, "getSeatMassageLevel is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.getSeatMassageLevel(i8);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getSeatMassageLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSeatMassageType(int i8) {
        Log.d(TAG, "getSeatMassageType is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.getSeatMassageType(i8);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getSeatMassageType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSeatVentilationState(int i8) {
        Log.d(TAG, "getSeatVentilationState is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.getSeatVentilationState(i8);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getSeatVentilationState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSeatWarmState(int i8) {
        Log.d(TAG, "getSeatWarmState is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.getSeatWarmState(i8);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getSeatWarmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getSteeringWheelWarmState() {
        Log.d(TAG, "getSteeringWheelWarmState is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.getSteeringWheelWarmState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getSteeringWheelWarmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void registerListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        Log.d(TAG, "registerListener is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.e(absBYDAutoSeatListener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "subscribeSeatCallback: " + absBYDAutoSeatListener);
        if (absBYDAutoSeatListener == null || this.mAbsBydAutoSeatListeners.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.mAbsBydAutoSeatListeners.add(absBYDAutoSeatListener);
    }

    public int setSeatMassageLevel(int i8, int i9) {
        Log.d(TAG, "setSeatMassageLevel is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.setSeatMassageLevel(i8, i9);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.c(3001, i8, i9);
        Log.d(TAG, "setSeatMassageLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setSeatMassageType(int i8, int i9) {
        Log.d(TAG, "setSeatMassageType is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.setSeatMassageType(i8, i9);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.c(3002, i8, i9);
        Log.d(TAG, "setSeatMassageType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setSeatVentilationState(int i8, int i9) {
        Log.d(TAG, "setSeatVentilationState is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.setSeatVentilationState(i8, i9);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.c(3004, i8, i9);
        Log.d(TAG, "setSeatVentilationState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setSeatWarmState(int i8, int i9) {
        Log.d(TAG, "setSeatWarmState is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.setSeatWarmState(i8, i9);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.c(3000, i8, i9);
        Log.d(TAG, "setSeatWarmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setSteeringWheelWarmState(int i8) {
        Log.d(TAG, "setSteeringWheelWarmState is execute");
        if (this.mForTest != 0) {
            CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
            if (carCabinAdapterManager != null) {
                return carCabinAdapterManager.setSteeringWheelWarmState(i8);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.b(3003, i8);
        Log.d(TAG, "setSteeringWheelWarmState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.d(absBYDAutoSeatListener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeSeatCallback: " + absBYDAutoSeatListener);
        if (absBYDAutoSeatListener == null) {
            return;
        }
        this.mAbsBydAutoSeatListeners.remove(absBYDAutoSeatListener);
    }
}
