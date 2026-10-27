package android.hardware.bydauto.gearbox.tsimpl;

import a.b.f.i;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.CarCabinAdapterManager;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GearboxManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_GearboxManagerImpl";
    private static final String TAG = "GearboxManagerImpl";
    private static volatile GearboxManagerImpl sInstance;
    private AbsBYDAutoGearboxListener mAbsBydAutoGearboxListener;
    private CabinPacket mCabinPacket;
    private CarAdapterManager mCarAdapterManager;
    private CarCabinAdapterManager mCarCabinAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private i mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoGearboxListener> mAbsBydAutoGearboxListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    public GearboxManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i2 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i2;
            if (1 == i2) {
                this.mTestCallback = new i();
                Log.v(TAG, "GearboxManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.gearbox.tsimpl.GearboxManagerImpl.1
            public void onCabinBinderChanged(boolean z) {
            }

            public void onCabinChanged(int i3, int i4, CabinPacket cabinPacket) {
                if (GearboxManagerImpl.this.mAbsBydAutoGearboxListeners.isEmpty()) {
                    return;
                }
                try {
                    GearboxManagerImpl.this.gearboxCallback(i3, i4, cabinPacket);
                } catch (Exception unused) {
                    Log.e(GearboxManagerImpl.TAG, "gearboxCallback is error");
                }
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
    public void gearboxCallback(int i2, int i3, CabinPacket cabinPacket) {
        if (cabinPacket == null) {
            Log.e(TAG, "gearboxCallback is null");
        }
        Log.i(TAG, "gearboxCallback propertyId:" + i2);
        if (i2 == 557857290) {
            Iterator<AbsBYDAutoGearboxListener> it = this.mAbsBydAutoGearboxListeners.iterator();
            while (it.hasNext()) {
                it.next().onGearChanged(cabinPacket.getGearR());
            }
            caseFeatureIds(3600, Integer.valueOf(cabinPacket.getGearR()));
            return;
        }
        if (i2 == 557857293) {
            Iterator<AbsBYDAutoGearboxListener> it2 = this.mAbsBydAutoGearboxListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onBrakePedalStatusChanged(cabinPacket.getBrakePedalStatusR());
            }
            return;
        }
        if (i2 == 557860357) {
            Iterator<AbsBYDAutoGearboxListener> it3 = this.mAbsBydAutoGearboxListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onGearboxAutoModeTypeChanged(cabinPacket.getGearboxAutoModeType());
            }
            caseFeatureIds(3602, Integer.valueOf(cabinPacket.getGearR()));
            return;
        }
        switch (i2) {
            case GearBoxHalid.ID_BRAKE_FLUID_LEVEL /* 557857283 */:
                Iterator<AbsBYDAutoGearboxListener> it4 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onBrakeFluidLevelChanged(cabinPacket.getBrakeFluidLevel());
                }
                break;
            case 557857284:
                Iterator<AbsBYDAutoGearboxListener> it5 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onParkBrakeSwitchChanged(cabinPacket.getParkBrakeSwitch());
                }
                break;
            case GearBoxHalid.ID_BRAKE_PEDAL_STATE /* 557857285 */:
                Iterator<AbsBYDAutoGearboxListener> it6 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onBrakePedalStateChanged(cabinPacket.getBrakePedalState());
                }
                break;
            case GearBoxHalid.ID_GEAR_BOX_AUTO_MODE_TYPE_TWO /* 557857286 */:
                Iterator<AbsBYDAutoGearboxListener> it7 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onGearboxAutoModeLevelChanged(cabinPacket.getGearboxAutoModeTypeTwo());
                }
                caseFeatureIds(3602, Integer.valueOf(cabinPacket.getGearR()));
                break;
            case GearBoxHalid.ID_GEARBOX_MANUAL_MODE_LEVELR /* 557857287 */:
                Iterator<AbsBYDAutoGearboxListener> it8 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onGearboxManualModeLevelChanged(cabinPacket.getGearboxManualModeLevelR());
                }
                break;
            case GearBoxHalid.ID_E_P_B_STATE_R /* 557857288 */:
                Iterator<AbsBYDAutoGearboxListener> it9 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onEPBStateChanged(cabinPacket.getEpbStateR());
                }
                break;
        }
    }

    public static GearboxManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (GearboxManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new GearboxManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i2 : iArr) {
                if (i2 > 0) {
                    if (i2 == 3608) {
                        int parkBrakeSwitch = getParkBrakeSwitch();
                        bYDAutoEventValue.intValue = parkBrakeSwitch;
                        sparseArray.put(3608, Integer.valueOf(parkBrakeSwitch));
                    } else if (i2 != 3610) {
                        switch (i2) {
                            case 3600:
                                int gearboxAutoModeType = getGearboxAutoModeType();
                                bYDAutoEventValue.intValue = gearboxAutoModeType;
                                sparseArray.put(3600, Integer.valueOf(gearboxAutoModeType));
                                break;
                            case 3601:
                                int gearboxAutoModeType2 = getGearboxAutoModeType();
                                bYDAutoEventValue.intValue = gearboxAutoModeType2;
                                sparseArray.put(3601, Integer.valueOf(gearboxAutoModeType2));
                                break;
                            case 3602:
                                int gear = getGear();
                                if ((gear < 1 || gear > 4) && ((gear = getGearboxAutoModeLevel()) < 1 || gear > 4)) {
                                    gear = getGearboxAutoModeType();
                                }
                                bYDAutoEventValue.intValue = gear;
                                sparseArray.put(3602, Integer.valueOf(gear));
                                break;
                            case 3603:
                                int brakeFluidLevel = getBrakeFluidLevel();
                                bYDAutoEventValue.intValue = brakeFluidLevel;
                                sparseArray.put(3603, Integer.valueOf(brakeFluidLevel));
                                break;
                            case 3604:
                                int brakePedalState = getBrakePedalState();
                                bYDAutoEventValue.intValue = brakePedalState;
                                sparseArray.put(3604, Integer.valueOf(brakePedalState));
                                break;
                            case 3605:
                                byte[] bytes = getGearboxCode().getBytes();
                                bYDAutoEventValue.bufferDataValue = bytes;
                                sparseArray.put(3605, bytes);
                                break;
                        }
                    } else {
                        int gearboxType = getGearboxType();
                        bYDAutoEventValue.intValue = gearboxType;
                        sparseArray.put(3610, Integer.valueOf(gearboxType));
                    }
                    int i3 = 0;
                    for (int i4 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i3] = ((Integer) sparseArray.get(i4)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i3++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getBrakeFluidLevel() {
        Log.v(TAG, "getBrakeFluidLevel is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getBrakeFluidLevel();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getBrakeFluidLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getBrakePedalState() {
        Log.v(TAG, "getBrakePedalState is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getBrakePedalState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getBrakePedalState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getBrakePedalStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBrakePedalStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getBrakePedalStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getEPBState() {
        Log.v(TAG, "getEPBState is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getEpbStateGearBox();
        }
        Log.v(TAG, "getEPBState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getGear() {
        Log.v(TAG, "getGear is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getGear();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getGear mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getGearboxAutoModeLevel() {
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxAutoModeLevel();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getGearboxAutoModeLevel mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getGearboxAutoModeType() {
        Log.v(TAG, "getGearboxAutoModeType is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxAutoModeType();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getGearboxAutoModeType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public String getGearboxCode() {
        Log.v(TAG, "getGearboxCode is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxCode();
            }
            Log.v(TAG, "adapter is not connect");
            return "";
        }
        Log.v(TAG, "getGearboxCode mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public int getGearboxManualModeLevel() {
        Log.v(TAG, "getGearboxManualModeLevel is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxManualModeLevel();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getGearboxManualModeLevel mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getGearboxType() {
        Log.v(TAG, "getGearboxType is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxType();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getGearboxType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getParkBrakeSwitch() {
        Log.v(TAG, "getParkBrakeSwitch is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().getParkBrakeSwitch();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getParkBrakeSwitch mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoGearboxListener> it = this.mAbsBydAutoGearboxListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i2, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        registerListener(absBYDAutoGearboxListener, new int[0]);
    }

    public int setEPBState(int i2) {
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_SET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCabinAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCabinAdapterManager().setEpbStateGearBox(i2);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setEPBState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i2, Object obj) {
        if (obj instanceof Integer) {
            ((Integer) obj).intValue();
            return 1;
        }
        if (obj instanceof Float) {
            ((Float) obj).floatValue();
            return 1;
        }
        if (obj instanceof Double) {
            ((Double) obj).doubleValue();
            return 1;
        }
        if (obj instanceof byte[]) {
            return 1;
        }
        Log.v(TAG, "the type of value is not a suitable type");
        return -1;
    }

    public void unregisterListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.b(null);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeGearboxCallback: " + absBYDAutoGearboxListener);
        if (absBYDAutoGearboxListener == null) {
            return;
        }
        this.mAbsBydAutoGearboxListeners.remove(absBYDAutoGearboxListener);
        unregisterConnectListener(absBYDAutoGearboxListener);
    }

    public void registerListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe grearbox listener " + absBYDAutoGearboxListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoGearboxListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.b(absBYDAutoGearboxListener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        if (!this.mAbsBydAutoGearboxListeners.contains(absBYDAutoGearboxListener)) {
            this.mAbsBydAutoGearboxListeners.add(absBYDAutoGearboxListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoGearboxListener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }
}
