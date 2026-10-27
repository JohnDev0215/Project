package android.hardware.bydauto.seat.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.seat.AbsBYDAutoSeatListener;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.CarCabinAdapterManager;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import com.ts.lib.caradapter.interfaces.OnCarAdapterConnectListener;
import e.C1150m;
import h6.n;
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
    private C1150m mTestCallback;

    public SeatManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (i7 == 0) {
                this.mTestCallback = new C1150m();
                a.u(new StringBuilder("getAcStartState mForTestValue is"), this.mForTest, " execute return", TAG);
                return;
            }
        }
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(context);
        this.mCarAdapterManager = carAdapterManager;
        carAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1
            public void onBinderStateChanged(boolean z6) {
                if (z6) {
                    SeatManagerImpl seatManagerImpl = SeatManagerImpl.this;
                    seatManagerImpl.mCarCabinAdapterManager = (CarCabinAdapterManager) seatManagerImpl.mCarAdapterManager.getCarAdapterManager("cabin");
                    SeatManagerImpl.this.mCarCabinAdapterManager.subscribeCabinCallback(new OnCabinChangedListener() { // from class: android.hardware.bydauto.seat.tsimpl.SeatManagerImpl.1.1
                        public void onCabinBinderChanged(boolean z10) {
                        }

                        public void onCabinChanged(int i10, int i11, CabinPacket cabinPacket) {
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
                try {
                    if (sInstance == null) {
                        sInstance = new SeatManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    public int getSeatMassageLevel(int i7) {
        Log.d(TAG, "getSeatMassageLevel is execute");
        if (this.mForTest == 0) {
            a.u(new StringBuilder("getSeatMassageLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatMassageLevel(i7);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatMassageType(int i7) {
        Log.d(TAG, "getSeatMassageType is execute");
        if (this.mForTest == 0) {
            a.u(new StringBuilder("getSeatMassageType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatMassageType(i7);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatVentilationState(int i7) {
        Log.d(TAG, "getSeatVentilationState is execute");
        if (this.mForTest == 0) {
            a.u(new StringBuilder("getSeatVentilationState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatVentilationState(i7);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatWarmState(int i7) {
        Log.d(TAG, "getSeatWarmState is execute");
        if (this.mForTest == 0) {
            a.u(new StringBuilder("getSeatWarmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSeatWarmState(i7);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getSteeringWheelWarmState() {
        Log.d(TAG, "getSteeringWheelWarmState is execute");
        if (this.mForTest == 0) {
            a.u(new StringBuilder("getSteeringWheelWarmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.getSteeringWheelWarmState();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public void registerListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        Log.d(TAG, "registerListener is execute");
        if (this.mForTest == 0) {
            C1150m c1150m = this.mTestCallback;
            if (absBYDAutoSeatListener == null) {
                c1150m.getClass();
            } else {
                ArrayList arrayList = c1150m.f13672a;
                if (!arrayList.contains(absBYDAutoSeatListener)) {
                    arrayList.add(absBYDAutoSeatListener);
                }
            }
            a.u(new StringBuilder("registerListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        Log.d(TAG, "subscribeSeatCallback: " + absBYDAutoSeatListener);
        if (absBYDAutoSeatListener == null || this.mAbsBydAutoSeatListeners.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.mAbsBydAutoSeatListeners.add(absBYDAutoSeatListener);
    }

    public int setSeatMassageLevel(int i7, int i10) {
        Log.d(TAG, "setSeatMassageLevel is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.b(3001, i7, i10);
            a.u(new StringBuilder("setSeatMassageLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatMassageLevel(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int setSeatMassageType(int i7, int i10) {
        Log.d(TAG, "setSeatMassageType is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.b(3002, i7, i10);
            a.u(new StringBuilder("setSeatMassageType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatMassageType(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int setSeatVentilationState(int i7, int i10) {
        Log.d(TAG, "setSeatVentilationState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.b(3004, i7, i10);
            a.u(new StringBuilder("setSeatVentilationState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatVentilationState(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int setSeatWarmState(int i7, int i10) {
        Log.d(TAG, "setSeatWarmState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.b(3000, i7, i10);
            a.u(new StringBuilder("setSeatWarmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSeatWarmState(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int setSteeringWheelWarmState(int i7) {
        Log.d(TAG, "setSteeringWheelWarmState is execute");
        if (this.mForTest == 0) {
            this.mTestCallback.a(i7);
            a.u(new StringBuilder("setSteeringWheelWarmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        CarCabinAdapterManager carCabinAdapterManager = this.mCarCabinAdapterManager;
        if (carCabinAdapterManager != null) {
            return carCabinAdapterManager.setSteeringWheelWarmState(i7);
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (this.mForTest == 0) {
            C1150m c1150m = this.mTestCallback;
            if (absBYDAutoSeatListener != null) {
                c1150m.f13672a.remove(absBYDAutoSeatListener);
            } else {
                c1150m.getClass();
            }
            a.u(new StringBuilder("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        Log.d(TAG, "unsubscribeSeatCallback: " + absBYDAutoSeatListener);
        if (absBYDAutoSeatListener == null) {
            return;
        }
        this.mAbsBydAutoSeatListeners.remove(absBYDAutoSeatListener);
    }
}
