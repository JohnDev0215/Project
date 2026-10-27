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
import java.util.Objects;

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

    public SeatManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (i == 0) {
                this.mTestCallback = new TestSeatManagerImplCallback();
                return;
            }
        }
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(context);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                if (z) {
                    SeatManagerImpl seatManagerImpl = SeatManagerImpl.this;
                    seatManagerImpl.mCarCabinAdapterManager = (CarCabinAdapterManager) seatManagerImpl.mCarAdapterManager.getCarAdapterManager("cabin");
                    SeatManagerImpl.this.mCarCabinAdapterManager.subscribeCabinCallback(new OnCabinChangedListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1.1
                        public void onCabinChanged(int i2, int i3, CabinPacket cabinPacket) {
                            SeatManagerImpl.this.mCabinPacket = cabinPacket;
                        }

                        public void onCabinBinderChanged(boolean z2) {
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

    public int getSeatMassageLevel(int i) {
        if (this.mForTest == 0) {
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatMassageLevel(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatMassageType(int i) {
        if (this.mForTest == 0) {
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatMassageType(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatVentilationState(int i) {
        if (this.mForTest == 0) {
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatVentilationState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatWarmState(int i) {
        if (this.mForTest == 0) {
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatWarmState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSteeringWheelWarmState() {
        if (this.mForTest == 0) {
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSteeringWheelWarmState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public void registerListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (this.mForTest == 0) {
            this.mTestCallback.setAbsBydAutoSeatListener(absBYDAutoSeatListener);
            return;
        }
        Objects.toString(absBYDAutoSeatListener);
        if (absBYDAutoSeatListener == null || this.mAbsBydAutoSeatListeners.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.mAbsBydAutoSeatListeners.add(absBYDAutoSeatListener);
    }

    public int setSeatMassageLevel(int i, int i2) {
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3001, i, i2);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatMassageLevel(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setSeatMassageType(int i, int i2) {
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3002, i, i2);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatMassageType(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setSeatVentilationState(int i, int i2) {
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3004, i, i2);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatVentilationState(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setSeatWarmState(int i, int i2) {
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3000, i, i2);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatWarmState(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setSteeringWheelWarmState(int i) {
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(3003, i);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSteeringWheelWarmState(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (this.mForTest == 0) {
            this.mTestCallback.removeListener(absBYDAutoSeatListener);
            return;
        }
        Objects.toString(absBYDAutoSeatListener);
        if (absBYDAutoSeatListener == null) {
            return;
        }
        this.mAbsBydAutoSeatListeners.remove(absBYDAutoSeatListener);
    }
}
