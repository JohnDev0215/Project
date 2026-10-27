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

    private DoorlockManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
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
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.doorlock.tsimpl.DoorlockManagerImpl.1
            public void onCabinChanged(int id, int areaId, CabinPacket packet) {
                try {
                    DoorlockManagerImpl.this.doorLockCallback(id, areaId, packet);
                } catch (Exception e) {
                    Log.e(DoorlockManagerImpl.TAG, "doorLockCallback is error");
                }
            }

            public void onCabinBinderChanged(boolean binderState) {
                Log.v(DoorlockManagerImpl.TAG, "cabin call back state :" + binderState);
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doorLockCallback(int propertyId, int areaId, CabinPacket cabinPacket) {
        Log.i(TAG, "cabin propertyId:" + propertyId);
        if (propertyId == 557856526) {
            for (AbsBYDAutoDoorLockListener doorlockListener : this.mAbsDoorlockListeners) {
                doorlockListener.onDoorLockStatusChanged(7, cabinPacket.getDoorLockStatusAreaChildLockRight());
            }
            int childLockRight = cabinPacket.getDoorLockStatusAreaChildLockRight();
            caseFeatureIds(2462, Integer.valueOf(childLockRight));
            return;
        }
        if (propertyId == 557856528) {
            for (AbsBYDAutoDoorLockListener doorlockListener2 : this.mAbsDoorlockListeners) {
                doorlockListener2.onLockHeatProtectedState(cabinPacket.getLockHeatProtectedStateR());
            }
            caseFeatureIds(4613, Integer.valueOf(cabinPacket.getLockHeatProtectedStateR()));
            return;
        }
        if (propertyId != 557856531) {
            switch (propertyId) {
                case 557856512:
                    for (AbsBYDAutoDoorLockListener doorlockListener3 : this.mAbsDoorlockListeners) {
                        doorlockListener3.onDoorLockStatusChanged(6, cabinPacket.getDoorLockStatusAreaChildlockLeftS());
                    }
                    int leftChild = cabinPacket.getDoorLockStatusAreaChildlockLeftS();
                    caseFeatureIds(2460, Integer.valueOf(leftChild));
                    break;
                case 557856513:
                    for (AbsBYDAutoDoorLockListener doorlockListener4 : this.mAbsDoorlockListeners) {
                        doorlockListener4.onDoorLockStatusChanged(5, cabinPacket.getDoorLockStatusAreaBack());
                    }
                    break;
                case 557856514:
                    for (AbsBYDAutoDoorLockListener doorlockListener5 : this.mAbsDoorlockListeners) {
                        doorlockListener5.onDoorLockStatusChanged(1, cabinPacket.getDoorLockStatusAreaLeftFront());
                    }
                    int leftFront = cabinPacket.getDoorLockStatusAreaLeftFront();
                    caseFeatureIds(2389, Integer.valueOf(leftFront));
                    break;
                case 557856515:
                    for (AbsBYDAutoDoorLockListener doorlockListener6 : this.mAbsDoorlockListeners) {
                        doorlockListener6.onDoorLockStatusChanged(3, cabinPacket.getDoorLockStatusRightFront());
                    }
                    int rightFront = cabinPacket.getDoorLockStatusRightFront();
                    caseFeatureIds(2025, Integer.valueOf(rightFront));
                    break;
                case 557856516:
                    for (AbsBYDAutoDoorLockListener doorlockListener7 : this.mAbsDoorlockListeners) {
                        doorlockListener7.onDoorLockStatusChanged(2, cabinPacket.getDoorLockStatusLeftRear());
                    }
                    int leftRear = cabinPacket.getDoorLockStatusLeftRear();
                    caseFeatureIds(1768, Integer.valueOf(leftRear));
                    break;
                case 557856517:
                    for (AbsBYDAutoDoorLockListener doorlockListener8 : this.mAbsDoorlockListeners) {
                        doorlockListener8.onDoorLockStatusChanged(4, cabinPacket.getDoorLockStatusAreaRightRear());
                    }
                    int rightRear = cabinPacket.getDoorLockStatusAreaRightRear();
                    caseFeatureIds(2050, Integer.valueOf(rightRear));
                    break;
            }
            return;
        }
        for (AbsBYDAutoDoorLockListener doorlockListener9 : this.mAbsDoorlockListeners) {
            doorlockListener9.onDoorLockCockpitHatchStateChanged(cabinPacket.getDoorLockCockpitHatchStateR());
        }
    }

    public static DoorlockManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (DoorlockManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new DoorlockManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    public int getDoorLockStatus(int area) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorLockStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        switch (area) {
            case 1:
                int resetResult = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaLeftFront();
                return resetResult;
            case 2:
                int resetResult2 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusLeftRear();
                return resetResult2;
            case 3:
                int resetResult3 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusRightFront();
                return resetResult3;
            case 4:
                int resetResult4 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaRightRear();
                return resetResult4;
            case 5:
                int resetResult5 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaBack();
                return resetResult5;
            case 6:
                int resetResult6 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                return resetResult6;
            case 7:
                int resetResult7 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaChildLockRight();
                return resetResult7;
            default:
                return 0;
        }
    }

    public int setDoorLockStatus(int areas, int state) {
        Log.v(TAG, "setDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDoorlockManagerImplCallback.SET_DOOR_LOCK_STATUS, areas, state);
            Log.v(TAG, "setDoorLockStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (areas == 6) {
            int resetResult = this.mTsManagerImpl.getCarCabinAdapterManager().setDoorLockStatusAreaChildlockLeftS(state);
            return resetResult;
        }
        if (areas != 7) {
            return 0;
        }
        int resetResult2 = this.mTsManagerImpl.getCarCabinAdapterManager().setDoorLockStatusAreaChildlockRightS(state);
        return resetResult2;
    }

    public int getDoorStatus(int area) {
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
        switch (area) {
            case 1:
                int resetResult = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront();
                break;
            case 2:
                int resetResult2 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear();
                break;
            case 3:
                int resetResult3 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront();
                break;
            case 4:
                int resetResult4 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear();
                break;
            case 5:
                int resetResult5 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaBack();
                break;
            case 6:
                int resetResult6 = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                break;
        }
        return 0;
    }

    public void registerListener(AbsBYDAutoDoorLockListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoDoorLockListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe door lock listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoSensorListener(listener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
        if (!this.mAbsDoorlockListeners.contains(listener)) {
            this.mAbsDoorlockListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public void unregisterListener(IBYDAutoListener listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
    }

    public void unregisterListener(AbsBYDAutoDoorLockListener listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeDoorlockCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsDoorlockListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getControlLockConfig() {
        Log.v(TAG, "getControlLockConfig is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        return 0;
    }

    public int setAllDoorsLock(int state) {
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
        return this.mTsManagerImpl.getCarCabinAdapterManager().setAllDoorsLock(state);
    }

    public int setRearWindowLock(int state) {
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
        return this.mTsManagerImpl.getCarCabinAdapterManager().setRearWindowLock(state);
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

    public int getLeftChildLockState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftChildLockState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getLeftChildLockState();
    }

    public int getRightChildLockState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightChildLockState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getRightChildLockState();
    }

    public int getDoorLockCockpitHatchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDoorLockCockpitHatchState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockCockpitHatchState();
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        Log.v(TAG, "BYDAutoEventValue is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_GET);
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        char c = 1;
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            int length = eventTypes.length;
            int i = 0;
            while (i < length) {
                int eventType = eventTypes[i];
                if (eventType > 0) {
                    if (eventType == 1721) {
                        int lf = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront();
                        int lr = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear();
                        int rf = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront();
                        int rr = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear();
                        int bd = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaBack();
                        int[] iArr = new int[5];
                        iArr[0] = lf;
                        iArr[c] = lr;
                        iArr[2] = rf;
                        iArr[3] = rr;
                        iArr[4] = bd;
                        bydAutoEventValue.intArrayValue = iArr;
                        sparseArray.put(1721, bydAutoEventValue.intArrayValue);
                    } else if (eventType == 1768) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear();
                        sparseArray.put(1768, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2025) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront();
                        sparseArray.put(2025, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2050) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear();
                        sparseArray.put(2050, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2389) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront();
                        sparseArray.put(2389, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2460) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                        sparseArray.put(2460, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 4613) {
                        bydAutoEventValue.intValue = getLockHeatProtectedStateR();
                        sparseArray.put(4613, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2462) {
                        bydAutoEventValue.intValue = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaChildLockRight();
                        sparseArray.put(2462, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2463) {
                        bydAutoEventValue.intValue = getDoorLockStatus(7);
                        sparseArray.put(2463, Integer.valueOf(bydAutoEventValue.intValue));
                    } else {
                        Log.v(TAG, "case fail ! please check out");
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
                i++;
                c = 1;
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoDoorLockListener listener : this.mAbsDoorlockListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        if (eventType == 1768) {
            setDoorLockStatus(2, intValue);
            Log.v(TAG, "setDoorLockStatusLR" + intValue);
        } else if (eventType == 2025) {
            setDoorLockStatus(3, intValue);
            Log.v(TAG, "setDoorLockStatusRF" + intValue);
        } else if (eventType == 2050) {
            setDoorLockStatus(4, intValue);
            Log.v(TAG, "setDoorLockStatusRR" + intValue);
        } else if (eventType == 2389) {
            setDoorLockStatus(1, intValue);
            Log.v(TAG, "setDoorLockStatusLF" + intValue);
        } else if (eventType != 2464) {
            switch (eventType) {
                case 2460:
                    setDoorLockStatus(6, intValue);
                    Log.v(TAG, "setDoorLockStatusLChild" + intValue);
                    break;
                case 2461:
                    setDoorLockStatus(6, intValue);
                    Log.v(TAG, "setDoorLockStatusLChildControl" + intValue);
                    break;
                case 2462:
                    setDoorLockStatus(7, intValue);
                    Log.v(TAG, "setDoorLockStatusRChild" + intValue);
                    break;
                default:
                    Log.v(TAG, "case fail ! please check out");
                    return -1;
            }
        } else {
            setDoorLockStatus(7, intValue);
            Log.v(TAG, "setDoorLockStatusRChildControl" + intValue);
        }
        return 1;
    }
}
