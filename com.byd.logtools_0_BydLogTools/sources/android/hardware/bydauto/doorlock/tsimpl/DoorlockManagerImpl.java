package android.hardware.bydauto.doorlock.tsimpl;

import a.a.d;
import a.a.e.e;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import java.util.Arrays;
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
    private e mTestCallback;
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
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "DoorlockManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new e();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.doorlock.tsimpl.DoorlockManagerImpl.1
            public void onCabinBinderChanged(boolean z) {
                Log.v(DoorlockManagerImpl.TAG, "cabin call back state :" + z);
            }

            public void onCabinChanged(int i2, int i3, CabinPacket cabinPacket) {
                try {
                    DoorlockManagerImpl.this.doorLockCallback(i2, i3, cabinPacket);
                } catch (Exception unused) {
                    Log.e(DoorlockManagerImpl.TAG, "doorLockCallback is error");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doorLockCallback(int i, int i2, CabinPacket cabinPacket) {
        Log.i(TAG, "cabin propertyId:" + i);
        if (i == 557856526) {
            Iterator<AbsBYDAutoDoorLockListener> it = this.mAbsDoorlockListeners.iterator();
            while (it.hasNext()) {
                it.next().onDoorLockStatusChanged(7, cabinPacket.getDoorLockStatusAreaChildLockRight());
            }
            caseFeatureIds(2462, Integer.valueOf(cabinPacket.getDoorLockStatusAreaChildLockRight()));
        }
        if (i == 557856528) {
            Iterator<AbsBYDAutoDoorLockListener> it2 = this.mAbsDoorlockListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onLockHeatProtectedState(cabinPacket.getLockHeatProtectedStateR());
            }
            caseFeatureIds(4613, Integer.valueOf(cabinPacket.getLockHeatProtectedStateR()));
            return;
        }
        if (i == 557856531) {
            Iterator<AbsBYDAutoDoorLockListener> it3 = this.mAbsDoorlockListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onDoorLockCockpitHatchStateChanged(cabinPacket.getDoorLockCockpitHatchStateR());
            }
            return;
        }
        switch (i) {
            case DoorlockHalId.ID_DOOR_LOCK_AREA_CHILDLOCK_LEFTR /* 557856512 */:
                Iterator<AbsBYDAutoDoorLockListener> it4 = this.mAbsDoorlockListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onDoorLockStatusChanged(6, cabinPacket.getDoorLockStatusAreaChildlockLeftS());
                }
                caseFeatureIds(2460, Integer.valueOf(cabinPacket.getDoorLockStatusAreaChildlockLeftS()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_BACK /* 557856513 */:
                Iterator<AbsBYDAutoDoorLockListener> it5 = this.mAbsDoorlockListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onDoorLockStatusChanged(5, cabinPacket.getDoorLockStatusAreaBack());
                }
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_FRONT /* 557856514 */:
                Iterator<AbsBYDAutoDoorLockListener> it6 = this.mAbsDoorlockListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onDoorLockStatusChanged(1, cabinPacket.getDoorLockStatusAreaLeftFront());
                }
                caseFeatureIds(2389, Integer.valueOf(cabinPacket.getDoorLockStatusAreaLeftFront()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_FRONT /* 557856515 */:
                Iterator<AbsBYDAutoDoorLockListener> it7 = this.mAbsDoorlockListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onDoorLockStatusChanged(3, cabinPacket.getDoorLockStatusRightFront());
                }
                caseFeatureIds(2025, Integer.valueOf(cabinPacket.getDoorLockStatusRightFront()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_REAR /* 557856516 */:
                Iterator<AbsBYDAutoDoorLockListener> it8 = this.mAbsDoorlockListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onDoorLockStatusChanged(2, cabinPacket.getDoorLockStatusLeftRear());
                }
                caseFeatureIds(1768, Integer.valueOf(cabinPacket.getDoorLockStatusLeftRear()));
                break;
            case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_REAR /* 557856517 */:
                Iterator<AbsBYDAutoDoorLockListener> it9 = this.mAbsDoorlockListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onDoorLockStatusChanged(4, cabinPacket.getDoorLockStatusAreaRightRear());
                }
                caseFeatureIds(2050, Integer.valueOf(cabinPacket.getDoorLockStatusAreaRightRear()));
                break;
        }
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static DoorlockManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DoorlockManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new DoorlockManagerImpl(context);
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
        int i = -2147482648;
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i2 : iArr) {
                if (i2 <= 0) {
                    bYDAutoEventValue.intValue = i;
                } else if (i2 == 1721) {
                    int[] iArr2 = {this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaBack()};
                    bYDAutoEventValue.intArrayValue = iArr2;
                    sparseArray.put(1721, iArr2);
                    i = -2147482648;
                } else if (i2 == 1768) {
                    int doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear();
                    bYDAutoEventValue.intValue = doorStatusAreaLeftRear;
                    sparseArray.put(1768, Integer.valueOf(doorStatusAreaLeftRear));
                } else if (i2 == 2025) {
                    int doorStatusAreaRightFront = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront();
                    bYDAutoEventValue.intValue = doorStatusAreaRightFront;
                    sparseArray.put(2025, Integer.valueOf(doorStatusAreaRightFront));
                } else if (i2 == 2050) {
                    int doorStatusAreaRightRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear();
                    bYDAutoEventValue.intValue = doorStatusAreaRightRear;
                    sparseArray.put(2050, Integer.valueOf(doorStatusAreaRightRear));
                } else if (i2 == 2389) {
                    int doorStatusAreaLeftFront = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront();
                    bYDAutoEventValue.intValue = doorStatusAreaLeftFront;
                    sparseArray.put(2389, Integer.valueOf(doorStatusAreaLeftFront));
                } else if (i2 == 2460) {
                    int doorStatusChildLeft = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                    bYDAutoEventValue.intValue = doorStatusChildLeft;
                    sparseArray.put(2460, Integer.valueOf(doorStatusChildLeft));
                } else if (i2 == 4613) {
                    int lockHeatProtectedStateR = getLockHeatProtectedStateR();
                    bYDAutoEventValue.intValue = lockHeatProtectedStateR;
                    sparseArray.put(4613, Integer.valueOf(lockHeatProtectedStateR));
                } else if (i2 == 2462) {
                    int doorLockStatusAreaChildLockRight = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaChildLockRight();
                    bYDAutoEventValue.intValue = doorLockStatusAreaChildLockRight;
                    sparseArray.put(2462, Integer.valueOf(doorLockStatusAreaChildLockRight));
                } else if (i2 != 2463) {
                    Log.v(TAG, "case fail ! please check out");
                } else {
                    int doorLockStatus = getDoorLockStatus(7);
                    bYDAutoEventValue.intValue = doorLockStatus;
                    sparseArray.put(2463, Integer.valueOf(doorLockStatus));
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

    public int getDoorLockCockpitHatchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDoorLockCockpitHatchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockCockpitHatchState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDoorLockStatus(int i) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorLockStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        switch (i) {
            case 1:
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaLeftFront();
            case 2:
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusLeftRear();
            case 3:
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusRightFront();
            case 4:
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaRightRear();
            case 5:
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaBack();
            case 6:
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
            case 7:
                return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaChildLockRight();
            default:
                return 0;
        }
    }

    public int getDoorStatus(int i) {
        Log.v(TAG, "getDoorStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
                Log.e(TAG, "caradatper still connecting, please wait");
                return 0;
            }
            switch (i) {
            }
            return 0;
        }
        Log.v(TAG, "getDoorStatus mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getLeftChildLockState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftChildLockState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getLeftChildLockState();
        }
        Log.v(TAG, "adapter is not connect");
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

    public int getRightChildLockState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightChildLockState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getRightChildLockState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoDoorLockListener> it = this.mAbsDoorlockListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        registerListener(absBYDAutoDoorLockListener, new int[0]);
    }

    public int setAllDoorsLock(int i) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setAllDoorsLock(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setAllDoorsLock mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int setDoorLockStatus(int i, int i2) {
        Log.v(TAG, "setDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.b(32000, i, i2);
            Log.v(TAG, "setDoorLockStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 6) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setDoorLockStatusAreaChildlockLeftS(i2);
        }
        if (i != 7) {
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setDoorLockStatusAreaChildlockRightS(i2);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    Log.v(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 1768) {
            setDoorLockStatus(2, iIntValue);
            Log.v(TAG, "setDoorLockStatusLR" + iIntValue);
        } else if (i == 2025) {
            setDoorLockStatus(3, iIntValue);
            Log.v(TAG, "setDoorLockStatusRF" + iIntValue);
        } else if (i == 2050) {
            setDoorLockStatus(4, iIntValue);
            Log.v(TAG, "setDoorLockStatusRR" + iIntValue);
        } else if (i == 2389) {
            setDoorLockStatus(1, iIntValue);
            Log.v(TAG, "setDoorLockStatusLF" + iIntValue);
        } else if (i != 2464) {
            switch (i) {
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

    public int setRearWindowLock(int i) {
        Log.v(TAG, "setRearWindowLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setRearWindowLock(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "setRearWindowLock mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void unregisterListener(d dVar) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe door lock listener " + absBYDAutoDoorLockListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.c(absBYDAutoDoorLockListener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
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
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
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
