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

    GearboxManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
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
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.gearbox.tsimpl.GearboxManagerImpl.1
            public void onCabinChanged(int id, int areaId, CabinPacket packet) {
                if (!GearboxManagerImpl.this.mAbsBydAutoGearboxListeners.isEmpty()) {
                    try {
                        GearboxManagerImpl.this.gearboxCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(GearboxManagerImpl.TAG, "gearboxCallback is error");
                    }
                }
            }

            public void onCabinBinderChanged(boolean binderState) {
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gearboxCallback(int id, int areaId, CabinPacket cabinPacket) {
        if (cabinPacket == null) {
            Log.e(TAG, "gearboxCallback is null");
            return;
        }
        Log.i(TAG, "gearboxCallback propertyId:" + id);
        if (id != 557857290) {
            if (id != 557857293) {
                if (id == 557860357) {
                    for (AbsBYDAutoGearboxListener listener : this.mAbsBydAutoGearboxListeners) {
                        listener.onGearboxAutoModeTypeChanged(cabinPacket.getGearboxAutoModeType());
                    }
                    caseFeatureIds(3602, Integer.valueOf(cabinPacket.getGearR()));
                    return;
                }
                switch (id) {
                    case 557857283:
                        for (AbsBYDAutoGearboxListener listener2 : this.mAbsBydAutoGearboxListeners) {
                            listener2.onBrakeFluidLevelChanged(cabinPacket.getBrakeFluidLevel());
                        }
                        break;
                    case 557857284:
                        for (AbsBYDAutoGearboxListener listener3 : this.mAbsBydAutoGearboxListeners) {
                            listener3.onParkBrakeSwitchChanged(cabinPacket.getParkBrakeSwitch());
                        }
                        break;
                    case 557857285:
                        for (AbsBYDAutoGearboxListener listener4 : this.mAbsBydAutoGearboxListeners) {
                            listener4.onBrakePedalStateChanged(cabinPacket.getBrakePedalState());
                        }
                        break;
                    case 557857286:
                        for (AbsBYDAutoGearboxListener listener5 : this.mAbsBydAutoGearboxListeners) {
                            listener5.onGearboxAutoModeLevelChanged(cabinPacket.getGearboxAutoModeTypeTwo());
                        }
                        caseFeatureIds(3602, Integer.valueOf(cabinPacket.getGearR()));
                        break;
                    case 557857287:
                        for (AbsBYDAutoGearboxListener listener6 : this.mAbsBydAutoGearboxListeners) {
                            listener6.onGearboxManualModeLevelChanged(cabinPacket.getGearboxManualModeLevelR());
                        }
                        break;
                    case 557857288:
                        for (AbsBYDAutoGearboxListener listener7 : this.mAbsBydAutoGearboxListeners) {
                            listener7.onEPBStateChanged(cabinPacket.getEpbStateR());
                        }
                        break;
                }
                return;
            }
            for (AbsBYDAutoGearboxListener listener8 : this.mAbsBydAutoGearboxListeners) {
                listener8.onBrakePedalStatusChanged(cabinPacket.getBrakePedalStatusR());
            }
            return;
        }
        for (AbsBYDAutoGearboxListener listener9 : this.mAbsBydAutoGearboxListeners) {
            listener9.onGearChanged(cabinPacket.getGearR());
        }
        caseFeatureIds(3600, Integer.valueOf(cabinPacket.getGearR()));
    }

    public static GearboxManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (GearboxManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new GearboxManagerImpl(con);
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

    public int getBrakePedalStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBrakePedalStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getBrakePedalStatus();
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
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEpbStateGearBox();
    }

    public int setEPBState(int state) {
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, " setEPBState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setEpbStateGearBox(state);
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

    public void registerListener(AbsBYDAutoGearboxListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoGearboxListener listener, int[] featureIds) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe grearbox listener " + listener + " featureIDs = " + Arrays.toString(featureIds));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoGearboxListener(listener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        if (!this.mAbsBydAutoGearboxListeners.contains(listener)) {
            this.mAbsBydAutoGearboxListeners.add(listener);
        }
        setFeatureIds(featureIds);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public void unregisterListener(AbsBYDAutoGearboxListener listener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoGearboxListener(null);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeGearboxCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoGearboxListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 3608) {
                        bydAutoEventValue.intValue = getParkBrakeSwitch();
                        sparseArray.put(3608, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 3610) {
                        switch (eventType) {
                            case 3600:
                                bydAutoEventValue.intValue = getGearboxAutoModeType();
                                sparseArray.put(3600, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3601:
                                bydAutoEventValue.intValue = getGearboxAutoModeType();
                                sparseArray.put(3601, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3602:
                                int gear = getGear();
                                if ((gear < 1 || gear > 4) && ((gear = getGearboxAutoModeLevel()) < 1 || gear > 4)) {
                                    gear = getGearboxAutoModeType();
                                }
                                bydAutoEventValue.intValue = gear;
                                sparseArray.put(3602, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3603:
                                bydAutoEventValue.intValue = getBrakeFluidLevel();
                                sparseArray.put(3603, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3604:
                                bydAutoEventValue.intValue = getBrakePedalState();
                                sparseArray.put(3604, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3605:
                                bydAutoEventValue.bufferDataValue = getGearboxCode().getBytes();
                                sparseArray.put(3605, bydAutoEventValue.bufferDataValue);
                                break;
                        }
                    } else {
                        bydAutoEventValue.intValue = getGearboxType();
                        sparseArray.put(3610, Integer.valueOf(bydAutoEventValue.intValue));
                    }
                    int index = 0;
                    for (int i : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoGearboxListener listener : this.mAbsBydAutoGearboxListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        if (value instanceof Integer) {
            ((Integer) value).intValue();
            return 1;
        }
        if (value instanceof Float) {
            ((Float) value).floatValue();
            return 1;
        }
        if (value instanceof Double) {
            ((Double) value).doubleValue();
            return 1;
        }
        if (value instanceof byte[]) {
            return 1;
        }
        Log.v(TAG, "the type of value is not a suitable type");
        return -1;
    }
}
