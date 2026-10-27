package android.hardware.bydauto.gearbox.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto_test.TestGearboxManagerImplCallback;
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
    private TestGearboxManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoGearboxListener> mAbsBydAutoGearboxListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    GearboxManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestGearboxManagerImplCallback();
                Log.v(TAG, "GearboxManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.gearbox.tsimpl.GearboxManagerImpl.1
            public void onCabinBinderChanged(boolean z) {
            }

            public void onCabinChanged(int i2, int i3, CabinPacket cabinPacket) {
                if (GearboxManagerImpl.this.mAbsBydAutoGearboxListeners.isEmpty()) {
                    return;
                }
                try {
                    GearboxManagerImpl.this.gearboxCallback(i2, i3, cabinPacket);
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
    public void gearboxCallback(int i, int i2, CabinPacket cabinPacket) {
        if (cabinPacket == null) {
            Log.e(TAG, "gearboxCallback is null");
        }
        Log.i(TAG, "gearboxCallback propertyId:" + i);
        if (i == 557857290) {
            Iterator<AbsBYDAutoGearboxListener> it = this.mAbsBydAutoGearboxListeners.iterator();
            while (it.hasNext()) {
                it.next().onGearChanged(cabinPacket.getGearR());
            }
            return;
        }
        if (i == 557860357) {
            Iterator<AbsBYDAutoGearboxListener> it2 = this.mAbsBydAutoGearboxListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onGearboxAutoModeTypeChanged(cabinPacket.getGearboxAutoModeType());
            }
            return;
        }
        switch (i) {
            case GearBoxHalid.ID_BRAKE_FLUID_LEVEL /* 557857283 */:
                Iterator<AbsBYDAutoGearboxListener> it3 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onBrakeFluidLevelChanged(cabinPacket.getBrakeFluidLevel());
                }
                break;
            case 557857284:
                Iterator<AbsBYDAutoGearboxListener> it4 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onParkBrakeSwitchChanged(cabinPacket.getParkBrakeSwitch());
                }
                break;
            case GearBoxHalid.ID_BRAKE_PEDAL_STATE /* 557857285 */:
                Iterator<AbsBYDAutoGearboxListener> it5 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onBrakePedalStateChanged(cabinPacket.getBrakePedalState());
                }
                break;
            case GearBoxHalid.ID_GEAR_BOX_AUTO_MODE_TYPE_TWO /* 557857286 */:
                Iterator<AbsBYDAutoGearboxListener> it6 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onGearboxAutoModeLevelChanged(cabinPacket.getGearboxAutoModeTypeTwo());
                }
                break;
            case GearBoxHalid.ID_GEARBOX_MANUAL_MODE_LEVELR /* 557857287 */:
                Iterator<AbsBYDAutoGearboxListener> it7 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onGearboxManualModeLevelChanged(cabinPacket.getGearboxManualModeLevelR());
                }
                break;
            case GearBoxHalid.ID_E_P_B_STATE_R /* 557857288 */:
                Iterator<AbsBYDAutoGearboxListener> it8 = this.mAbsBydAutoGearboxListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onEPBStateChanged(cabinPacket.getEpbStateR());
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

    public String getGearboxCode() {
        Log.v(TAG, "getGearboxCode is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getGearboxCode mForTestValue is" + this.mForTest + " execute return");
            return "";
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return "";
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxCode();
    }

    public int getGearboxType() {
        Log.v(TAG, "getGearboxType is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getGearboxType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxType();
    }

    public int getGearboxAutoModeType() {
        Log.v(TAG, "getGearboxAutoModeType is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getGearboxAutoModeType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxAutoModeType();
    }

    public int getGear() {
        Log.v(TAG, "getGear is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getGear mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getGear();
    }

    public int getGearboxManualModeLevel() {
        Log.v(TAG, "getGearboxManualModeLevel is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getGearboxManualModeLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxManualModeLevel();
    }

    public int getBrakeFluidLevel() {
        Log.v(TAG, "getBrakeFluidLevel is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBrakeFluidLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getBrakeFluidLevel();
    }

    public int getParkBrakeSwitch() {
        Log.v(TAG, "getParkBrakeSwitch is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getParkBrakeSwitch mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getParkBrakeSwitch();
    }

    public int getBrakePedalState() {
        Log.v(TAG, "getBrakePedalState is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBrakePedalState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getBrakePedalState();
    }

    public int getEPBState() {
        Log.v(TAG, "getEPBState is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPBState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEpbStateGearBox();
    }

    public int setEPBState(int i) {
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, " setEPBState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setEpbStateGearBox(i);
    }

    public int getGearboxAutoModeLevel() {
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, " getGearboxAutoModeLevel mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getGearboxAutoModeLevel();
    }

    public void registerListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        registerListener(absBYDAutoGearboxListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe grearbox listener " + absBYDAutoGearboxListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoGearboxListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoGearboxListener(absBYDAutoGearboxListener);
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

    public void unregisterListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoGearboxListener(null);
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    switch (i) {
                        case 3600:
                            bYDAutoEventValue.intValue = getGearboxAutoModeType();
                            sparseArray.put(3600, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3601:
                            bYDAutoEventValue.intValue = getGearboxAutoModeType();
                            sparseArray.put(3601, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3603:
                            bYDAutoEventValue.intValue = getBrakeFluidLevel();
                            sparseArray.put(3603, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3604:
                            bYDAutoEventValue.intValue = getBrakePedalState();
                            sparseArray.put(3604, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3605:
                            bYDAutoEventValue.bufferDataValue = getGearboxCode().getBytes();
                            sparseArray.put(3605, bYDAutoEventValue.bufferDataValue);
                            break;
                        case 3608:
                            bYDAutoEventValue.intValue = getParkBrakeSwitch();
                            sparseArray.put(3608, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3610:
                            bYDAutoEventValue.intValue = getGearboxType();
                            sparseArray.put(3610, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoGearboxListener> it = this.mAbsBydAutoGearboxListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
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
}
