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
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

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
                StringBuilder sbK = e.k("DoorlockManagerImpl mForTestValue is");
                sbK.append(this.mForTest);
                sbK.append(" execute return");
                Log.v(TAG, sbK.toString());
                this.mTestCallback = new TestDoorlockManagerImplCallback();
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
        int doorLockStatusAreaChildLockRight;
        int i3;
        e.t("cabin propertyId:", i, TAG);
        if (i == 557856526) {
            Iterator<AbsBYDAutoDoorLockListener> it = this.mAbsDoorlockListeners.iterator();
            while (it.hasNext()) {
                it.next().onDoorLockStatusChanged(7, cabinPacket.getDoorLockStatusAreaChildLockRight());
            }
            doorLockStatusAreaChildLockRight = cabinPacket.getDoorLockStatusAreaChildLockRight();
            i3 = 2462;
        } else {
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
                    doorLockStatusAreaChildLockRight = cabinPacket.getDoorLockStatusAreaChildlockLeftS();
                    i3 = 2460;
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_BACK /* 557856513 */:
                    Iterator<AbsBYDAutoDoorLockListener> it5 = this.mAbsDoorlockListeners.iterator();
                    while (it5.hasNext()) {
                        it5.next().onDoorLockStatusChanged(5, cabinPacket.getDoorLockStatusAreaBack());
                    }
                    return;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_FRONT /* 557856514 */:
                    Iterator<AbsBYDAutoDoorLockListener> it6 = this.mAbsDoorlockListeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onDoorLockStatusChanged(1, cabinPacket.getDoorLockStatusAreaLeftFront());
                    }
                    doorLockStatusAreaChildLockRight = cabinPacket.getDoorLockStatusAreaLeftFront();
                    i3 = 2389;
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_FRONT /* 557856515 */:
                    Iterator<AbsBYDAutoDoorLockListener> it7 = this.mAbsDoorlockListeners.iterator();
                    while (it7.hasNext()) {
                        it7.next().onDoorLockStatusChanged(3, cabinPacket.getDoorLockStatusRightFront());
                    }
                    doorLockStatusAreaChildLockRight = cabinPacket.getDoorLockStatusRightFront();
                    i3 = 2025;
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_LEFT_REAR /* 557856516 */:
                    Iterator<AbsBYDAutoDoorLockListener> it8 = this.mAbsDoorlockListeners.iterator();
                    while (it8.hasNext()) {
                        it8.next().onDoorLockStatusChanged(2, cabinPacket.getDoorLockStatusLeftRear());
                    }
                    doorLockStatusAreaChildLockRight = cabinPacket.getDoorLockStatusLeftRear();
                    i3 = 1768;
                    break;
                case DoorlockHalId.ID_DOOR_LOCK_AREA_RIGHT_REAR /* 557856517 */:
                    Iterator<AbsBYDAutoDoorLockListener> it9 = this.mAbsDoorlockListeners.iterator();
                    while (it9.hasNext()) {
                        it9.next().onDoorLockStatusChanged(4, cabinPacket.getDoorLockStatusAreaRightRear());
                    }
                    doorLockStatusAreaChildLockRight = cabinPacket.getDoorLockStatusAreaRightRear();
                    i3 = 2050;
                    break;
                default:
                    return;
            }
        }
        caseFeatureIds(i3, Integer.valueOf(doorLockStatusAreaChildLockRight));
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
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
        int doorStatusAreaLeftRear;
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
                } else if (i2 != 1721) {
                    int i3 = 1768;
                    if (i2 != 1768) {
                        i3 = 2025;
                        if (i2 != 2025) {
                            i3 = 2050;
                            if (i2 != 2050) {
                                i3 = 2389;
                                if (i2 != 2389) {
                                    i3 = 2460;
                                    if (i2 != 2460) {
                                        i3 = 4613;
                                        if (i2 != 4613) {
                                            i3 = 2462;
                                            if (i2 != 2462) {
                                                i3 = 2463;
                                                if (i2 != 2463) {
                                                    Log.v(TAG, "case fail ! please check out");
                                                } else {
                                                    doorStatusAreaLeftRear = getDoorLockStatus(7);
                                                }
                                            } else {
                                                doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorLockStatusAreaChildLockRight();
                                            }
                                        } else {
                                            doorStatusAreaLeftRear = getLockHeatProtectedStateR();
                                        }
                                    } else {
                                        doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusChildLeft();
                                    }
                                } else {
                                    doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront();
                                }
                            } else {
                                doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear();
                            }
                        } else {
                            doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront();
                        }
                    } else {
                        doorStatusAreaLeftRear = this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear();
                    }
                    bYDAutoEventValue.intValue = doorStatusAreaLeftRear;
                    sparseArray.put(i3, Integer.valueOf(doorStatusAreaLeftRear));
                } else {
                    int[] iArr2 = {this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaLeftRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightFront(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaRightRear(), this.mTsManagerImpl.getCarCabinAdapterManager().getDoorStatusAreaBack()};
                    bYDAutoEventValue.intArrayValue = iArr2;
                    sparseArray.put(1721, iArr2);
                    i = -2147482648;
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
            e.s(e.k("getDoorLockStatus mForTestValue is"), this.mForTest, " execute return", TAG);
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
        if (1 == this.mForTest) {
            e.s(e.k("getDoorStatus mForTestValue is"), this.mForTest, " execute return", TAG);
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

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe door lock listener ");
        sb.append(absBYDAutoDoorLockListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBYDAutoSensorListener(absBYDAutoDoorLockListener);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("registerListener mForTestValue is");
            e.u(sb2, this.mForTest, " execute return", TAG);
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

    public int setAllDoorsLock(int i) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            e.s(e.k("setAllDoorsLock mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setAllDoorsLock(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setDoorLockStatus(int i, int i2) {
        Log.v(TAG, "setDoorLockStatus is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(TestDoorlockManagerImplCallback.SET_DOOR_LOCK_STATUS, i, i2);
            StringBuilder sb = new StringBuilder();
            sb.append("setDoorLockStatus mForTestValue is");
            e.s(sb, this.mForTest, " execute return", TAG);
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
    public int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
        StringBuilder sb;
        String str2;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    str = "the type of value is not a suitable type";
                    Log.v(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 1768) {
            setDoorLockStatus(2, iIntValue);
            sb = new StringBuilder();
            str2 = "setDoorLockStatusLR";
        } else if (i == 2025) {
            setDoorLockStatus(3, iIntValue);
            sb = new StringBuilder();
            str2 = "setDoorLockStatusRF";
        } else if (i == 2050) {
            setDoorLockStatus(4, iIntValue);
            sb = new StringBuilder();
            str2 = "setDoorLockStatusRR";
        } else if (i == 2389) {
            setDoorLockStatus(1, iIntValue);
            sb = new StringBuilder();
            str2 = "setDoorLockStatusLF";
        } else if (i != 2464) {
            switch (i) {
                case 2460:
                    setDoorLockStatus(6, iIntValue);
                    sb = new StringBuilder();
                    str2 = "setDoorLockStatusLChild";
                    break;
                case 2461:
                    setDoorLockStatus(6, iIntValue);
                    sb = new StringBuilder();
                    str2 = "setDoorLockStatusLChildControl";
                    break;
                case 2462:
                    setDoorLockStatus(7, iIntValue);
                    sb = new StringBuilder();
                    str2 = "setDoorLockStatusRChild";
                    break;
                default:
                    str = "case fail ! please check out";
                    Log.v(TAG, str);
                    return -1;
            }
        } else {
            setDoorLockStatus(7, iIntValue);
            sb = new StringBuilder();
            str2 = "setDoorLockStatusRChildControl";
        }
        sb.append(str2);
        sb.append(iIntValue);
        Log.v(TAG, sb.toString());
        return 1;
    }

    public int setRearWindowLock(int i) {
        Log.v(TAG, "setRearWindowLock is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_SET);
        if (1 == this.mForTest) {
            e.s(e.k("setRearWindowLock mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().setRearWindowLock(i);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
    }

    public void unregisterListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoDoorLockDevice.PERMISSION_BYDAUTO_DOORLOCK_COMMON);
        if (1 == this.mForTest) {
            e.u(e.k("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
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
