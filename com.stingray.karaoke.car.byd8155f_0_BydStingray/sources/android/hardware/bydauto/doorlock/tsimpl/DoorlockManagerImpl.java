package android.hardware.bydauto.doorlock.tsimpl;

import B5.H;
import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.SparseArray;
import b.d;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import e.C1142e;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DoorlockManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_DoorlockManagerImpl";
    private static final String TAG = "DoorlockManagerImpl";
    private static volatile DoorlockManagerImpl sInstance;
    private AbsBYDAutoDoorLockListener mAbsBydAutoDoorLockListener;
    private CabinPacket mCabinPacket;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private C1142e mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private CopyOnWriteArrayList<AbsBYDAutoDoorLockListener> mAbsDoorlockListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mIgnoreBydPermission = false;

    private DoorlockManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                Log.v(TAG, "DoorlockManagerImpl mForTestValue is" + this.mForTest + " execute return");
                C1142e c1142e = new C1142e();
                HandlerThread handlerThread = new HandlerThread("TestDoorlockManagerImplCallback");
                handlerThread.start();
                c1142e.f13658b = new H(c1142e, handlerThread.getLooper(), 5);
                this.mTestCallback = c1142e;
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.doorlock.tsimpl.DoorlockManagerImpl.1
            public void onCabinBinderChanged(boolean z6) {
                Log.v(DoorlockManagerImpl.TAG, "cabin call back state :" + z6);
            }

            public void onCabinChanged(int i10, int i11, CabinPacket cabinPacket) {
                DoorlockManagerImpl.this.doorLockCallback(i10, i11, cabinPacket);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doorLockCallback(int i7, int i10, CabinPacket cabinPacket) {
        Log.i(TAG, "cabin propertyId:" + i7);
        if (i7 == 557856526) {
            Iterator<AbsBYDAutoDoorLockListener> it = this.mAbsDoorlockListeners.iterator();
            while (it.hasNext()) {
                it.next().onDoorLockStatusChanged(7, cabinPacket.getDoorLockStatusAreaChildLockRight());
            }
            caseFeatureIds(2462, Integer.valueOf(cabinPacket.getDoorLockStatusAreaChildLockRight()));
        }
        if (i7 == 557856528) {
            Iterator<AbsBYDAutoDoorLockListener> it2 = this.mAbsDoorlockListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onLockHeatProtectedState(cabinPacket.getLockHeatProtectedStateR());
            }
            caseFeatureIds(4613, Integer.valueOf(cabinPacket.getLockHeatProtectedStateR()));
            return;
        }
        switch (i7) {
            case DoorlockHalId.ID_DOOR_LOCK_AREA_CHILDLOCK_LEFTR /* 557856512 */:
                Iterator<AbsBYDAutoDoorLockListener> it3 = this.mAbsDoorlockListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onDoorLockStatusChanged(6, cabinPacket.getDoorLockStatusAreaChildlockLeftS());
                }
                caseFeatureIds(2460, Integer.valueOf(cabinPacket.getDoorLockStatusAreaChildlockLeftS()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_BACK /* 557856513 */:
                Iterator<AbsBYDAutoDoorLockListener> it4 = this.mAbsDoorlockListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onDoorLockStatusChanged(5, cabinPacket.getDoorLockStatusAreaBack());
                }
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_FRONT /* 557856514 */:
                Iterator<AbsBYDAutoDoorLockListener> it5 = this.mAbsDoorlockListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onDoorLockStatusChanged(1, cabinPacket.getDoorLockStatusAreaLeftFront());
                }
                caseFeatureIds(2389, Integer.valueOf(cabinPacket.getDoorLockStatusAreaLeftFront()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_FRONT /* 557856515 */:
                Iterator<AbsBYDAutoDoorLockListener> it6 = this.mAbsDoorlockListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onDoorLockStatusChanged(3, cabinPacket.getDoorLockStatusRightFront());
                }
                caseFeatureIds(2025, Integer.valueOf(cabinPacket.getDoorLockStatusRightFront()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_REAR /* 557856516 */:
                Iterator<AbsBYDAutoDoorLockListener> it7 = this.mAbsDoorlockListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onDoorLockStatusChanged(2, cabinPacket.getDoorLockStatusLeftRear());
                }
                caseFeatureIds(1768, Integer.valueOf(cabinPacket.getDoorLockStatusLeftRear()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_REAR /* 557856517 */:
                Iterator<AbsBYDAutoDoorLockListener> it8 = this.mAbsDoorlockListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onDoorLockStatusChanged(4, cabinPacket.getDoorLockStatusAreaRightRear());
                }
                caseFeatureIds(2050, Integer.valueOf(cabinPacket.getDoorLockStatusAreaRightRear()));
                break;
        }
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static DoorlockManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DoorlockManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new DoorlockManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        Log.v(TAG, "BYDAutoEventValue is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 <= 0) {
                    bYDAutoEventValue.intValue = -2147482648;
                } else if (i7 == 1721) {
                    int[] iArr2 = {this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaBack()};
                    bYDAutoEventValue.intArrayValue = iArr2;
                    sparseArray.put(1721, iArr2);
                } else if (i7 == 1768) {
                    int doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear();
                    bYDAutoEventValue.intValue = doorStatusAreaLeftRear;
                    sparseArray.put(1768, Integer.valueOf(doorStatusAreaLeftRear));
                } else if (i7 == 2025) {
                    int doorStatusAreaRightFront = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront();
                    bYDAutoEventValue.intValue = doorStatusAreaRightFront;
                    sparseArray.put(2025, Integer.valueOf(doorStatusAreaRightFront));
                } else if (i7 == 2050) {
                    int doorStatusAreaRightRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear();
                    bYDAutoEventValue.intValue = doorStatusAreaRightRear;
                    sparseArray.put(2050, Integer.valueOf(doorStatusAreaRightRear));
                } else if (i7 == 2389) {
                    int doorStatusAreaLeftFront = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront();
                    bYDAutoEventValue.intValue = doorStatusAreaLeftFront;
                    sparseArray.put(2389, Integer.valueOf(doorStatusAreaLeftFront));
                } else if (i7 != 4613) {
                    switch (i7) {
                        case 2460:
                            int doorStatusChildLeft = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                            bYDAutoEventValue.intValue = doorStatusChildLeft;
                            sparseArray.put(2460, Integer.valueOf(doorStatusChildLeft));
                            break;
                        case 2461:
                            int doorStatusChildLeft2 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                            bYDAutoEventValue.intValue = doorStatusChildLeft2;
                            sparseArray.put(2461, Integer.valueOf(doorStatusChildLeft2));
                            break;
                        case 2462:
                            int doorLockStatusAreaChildLockRight = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaChildLockRight();
                            bYDAutoEventValue.intValue = doorLockStatusAreaChildLockRight;
                            sparseArray.put(2462, Integer.valueOf(doorLockStatusAreaChildLockRight));
                            break;
                        case 2463:
                            int doorLockStatus = getDoorLockStatus(7);
                            bYDAutoEventValue.intValue = doorLockStatus;
                            sparseArray.put(2463, Integer.valueOf(doorLockStatus));
                            break;
                        case 2464:
                            int doorStatusChildRight = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildRight();
                            bYDAutoEventValue.intValue = doorStatusChildRight;
                            sparseArray.put(2464, Integer.valueOf(doorStatusChildRight));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
                    }
                } else {
                    int lockHeatProtectedStateR = getLockHeatProtectedStateR();
                    bYDAutoEventValue.intValue = lockHeatProtectedStateR;
                    sparseArray.put(4613, Integer.valueOf(lockHeatProtectedStateR));
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getControlLockConfig() {
        Log.v(TAG, "getControlLockConfig is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        return 0;
    }

    public int getDoorLockStatus(int i7) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getDoorLockStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i7) {
        }
        return 0;
    }

    public int getDoorStatus(int i7) {
        Log.v(TAG, "getDoorStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getDoorStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i7) {
        }
        return 0;
    }

    public int getLockHeatProtectedStateR() {
        Log.i(TAG, "getLockHeatProtectedStateR is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "LockHeatProtectedStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getLockHeatProtectedStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoDoorLockListener> it = this.mAbsDoorlockListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        registerListener(absBYDAutoDoorLockListener, new int[0]);
    }

    public int setAllDoorsLock(int i7) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setAllDoorsLock mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setAllDoorsLock(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setDoorLockStatus(int i7, int i10) {
        Log.v(TAG, "setDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(i7, i10);
            a.q(new StringBuilder("setDoorLockStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i7 == 6) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setDoorLockStatusAreaChildlockLeftS(i10);
        }
        if (i7 != 7) {
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setDoorLockStatusAreaChildlockRightS(i10);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).getClass();
            } else if (obj instanceof Double) {
                ((Double) obj).getClass();
            } else if (!(obj instanceof byte[])) {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        if (i7 == 1768) {
            setDoorLockStatus(2, iIntValue);
            Log.v(TAG, "setDoorLockStatusLR" + iIntValue);
        } else if (i7 == 2025) {
            setDoorLockStatus(3, iIntValue);
            Log.v(TAG, "setDoorLockStatusRF" + iIntValue);
        } else if (i7 == 2050) {
            setDoorLockStatus(4, iIntValue);
            Log.v(TAG, "setDoorLockStatusRR" + iIntValue);
        } else if (i7 == 2389) {
            setDoorLockStatus(1, iIntValue);
            Log.v(TAG, "setDoorLockStatusLF" + iIntValue);
        } else if (i7 != 2464) {
            switch (i7) {
                case 2460:
                    setDoorLockStatus(6, iIntValue);
                    Log.v(TAG, "setDoorLockStatusLChild" + iIntValue);
                    break;
                case 2461:
                    setDoorLockStatus(6, iIntValue);
                    Log.v(TAG, "setDoorLockStatusLChildControl" + iIntValue);
                    break;
                case 2462:
                    setDoorLockStatus(7, iIntValue);
                    Log.v(TAG, "setDoorLockStatusRChild" + iIntValue);
                    break;
                default:
                    Log.v(TAG, "case fail ! please check out");
                    return -1;
            }
        } else {
            setDoorLockStatus(7, iIntValue);
            Log.v(TAG, "setDoorLockStatusRChildControl" + iIntValue);
        }
        return 1;
    }

    public int setRearWindowLock(int i7) {
        Log.v(TAG, "setRearWindowLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setRearWindowLock mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setRearWindowLock(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(d dVar) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe door lock listener ");
        sb.append(absBYDAutoDoorLockListener);
        sb.append(" featureIDs = ");
        b.q(iArr, sb, TAG);
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            C1142e c1142e = this.mTestCallback;
            if (absBYDAutoDoorLockListener == null) {
                c1142e.getClass();
            } else {
                c1142e.f13657a = absBYDAutoDoorLockListener;
            }
            a.t(new StringBuilder("registerListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
        if (!this.mAbsDoorlockListeners.contains(absBYDAutoDoorLockListener)) {
            this.mAbsDoorlockListeners.add(absBYDAutoDoorLockListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoDoorLockListener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public void unregisterListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
        if (1 == this.mForTest) {
            a.t(new StringBuilder("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        Log.d(TAG, "unsubscribeDoorlockCallback: " + absBYDAutoDoorLockListener);
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.mAbsDoorlockListeners.remove(absBYDAutoDoorLockListener);
        unregisterConnectListener(absBYDAutoDoorLockListener);
    }
}
