package android.hardware.bydauto.doorlock.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.hardware.bydauto_test.TestDoorlockManagerImplCallback;
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
    private TestDoorlockManagerImplCallback mTestCallback;
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
                this.mTestCallback = new TestDoorlockManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.doorlock.tsimpl.DoorlockManagerImpl.1
            public void onCabinChanged(int i2, int i3, CabinPacket cabinPacket) {
                try {
                    DoorlockManagerImpl.this.doorLockCallback(i2, i3, cabinPacket);
                } catch (Exception unused) {
                    Log.e(DoorlockManagerImpl.TAG, "doorLockCallback is error");
                }
            }

            public void onCabinBinderChanged(boolean z) {
                Log.v(DoorlockManagerImpl.TAG, "cabin call back state :" + z);
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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
            return;
        }
        if (i != 557856528) {
            switch (i) {
                case DoorlockHalId.ID_DOOR_LOCK_AREA_CHILDLOCK_LEFTR /* 557856512 */:
                    Iterator<AbsBYDAutoDoorLockListener> it2 = this.mAbsDoorlockListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onDoorLockStatusChanged(6, cabinPacket.getDoorLockStatusAreaChildlockLeftS());
                    }
                    caseFeatureIds(2460, Integer.valueOf(cabinPacket.getDoorLockStatusAreaChildlockLeftS()));
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_BACK /* 557856513 */:
                    Iterator<AbsBYDAutoDoorLockListener> it3 = this.mAbsDoorlockListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onDoorLockStatusChanged(5, cabinPacket.getDoorLockStatusAreaBack());
                    }
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_FRONT /* 557856514 */:
                    Iterator<AbsBYDAutoDoorLockListener> it4 = this.mAbsDoorlockListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onDoorLockStatusChanged(1, cabinPacket.getDoorLockStatusAreaLeftFront());
                    }
                    caseFeatureIds(2389, Integer.valueOf(cabinPacket.getDoorLockStatusAreaLeftFront()));
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_FRONT /* 557856515 */:
                    Iterator<AbsBYDAutoDoorLockListener> it5 = this.mAbsDoorlockListeners.iterator();
                    while (it5.hasNext()) {
                        it5.next().onDoorLockStatusChanged(3, cabinPacket.getDoorLockStatusRightFront());
                    }
                    caseFeatureIds(2025, Integer.valueOf(cabinPacket.getDoorLockStatusRightFront()));
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_REAR /* 557856516 */:
                    Iterator<AbsBYDAutoDoorLockListener> it6 = this.mAbsDoorlockListeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onDoorLockStatusChanged(2, cabinPacket.getDoorLockStatusLeftRear());
                    }
                    caseFeatureIds(1768, Integer.valueOf(cabinPacket.getDoorLockStatusLeftRear()));
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_REAR /* 557856517 */:
                    Iterator<AbsBYDAutoDoorLockListener> it7 = this.mAbsDoorlockListeners.iterator();
                    while (it7.hasNext()) {
                        it7.next().onDoorLockStatusChanged(4, cabinPacket.getDoorLockStatusAreaRightRear());
                    }
                    caseFeatureIds(2050, Integer.valueOf(cabinPacket.getDoorLockStatusAreaRightRear()));
                    break;
            }
            return;
        }
        Iterator<AbsBYDAutoDoorLockListener> it8 = this.mAbsDoorlockListeners.iterator();
        while (it8.hasNext()) {
            it8.next().onLockHeatProtectedState(cabinPacket.getLockHeatProtectedStateR());
        }
        caseFeatureIds(4613, Integer.valueOf(cabinPacket.getLockHeatProtectedStateR()));
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

    public int getDoorLockStatus(int i) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorLockStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i) {
        }
        return 0;
    }

    public int setDoorLockStatus(int i, int i2) {
        Log.v(TAG, "setDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDoorlockManagerImplCallback.SET_DOOR_LOCK_STATUS, i, i2);
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

    public int getDoorStatus(int i) {
        Log.v(TAG, "getDoorStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i) {
        }
        return 0;
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        registerListener(absBYDAutoDoorLockListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe door lock listener " + absBYDAutoDoorLockListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoSensorListener(absBYDAutoDoorLockListener);
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

    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
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

    public int getControlLockConfig() {
        Log.v(TAG, "getControlLockConfig is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        return 0;
    }

    public int setAllDoorsLock(int i) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAllDoorsLock mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setAllDoorsLock(i);
    }

    public int setRearWindowLock(int i) {
        Log.v(TAG, "setRearWindowLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearWindowLock mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setRearWindowLock(i);
    }

    public int getLockHeatProtectedStateR() {
        Log.i(TAG, "getLockHeatProtectedStateR is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "LockHeatProtectedStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getLockHeatProtectedStateR();
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
                    bYDAutoEventValue.intArrayValue = new int[]{this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaBack()};
                    sparseArray.put(1721, bYDAutoEventValue.intArrayValue);
                    i = -2147482648;
                } else if (i2 == 1768) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear();
                    sparseArray.put(1768, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i2 == 2025) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront();
                    sparseArray.put(2025, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i2 == 2050) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear();
                    sparseArray.put(2050, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i2 == 2389) {
                    bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront();
                    sparseArray.put(2389, Integer.valueOf(bYDAutoEventValue.intValue));
                } else if (i2 != 4613) {
                    switch (i2) {
                        case 2460:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                            sparseArray.put(2460, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2461:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                            sparseArray.put(2461, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2462:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaChildLockRight();
                            sparseArray.put(2462, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2463:
                            bYDAutoEventValue.intValue = getDoorLockStatus(7);
                            sparseArray.put(2463, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2464:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildRight();
                            sparseArray.put(2464, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
                    }
                } else {
                    bYDAutoEventValue.intValue = getLockHeatProtectedStateR();
                    sparseArray.put(4613, Integer.valueOf(bYDAutoEventValue.intValue));
                }
            }
        }
        return bYDAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoDoorLockListener> it = this.mAbsDoorlockListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
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
            } else if (obj instanceof byte[]) {
            } else {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
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
}
