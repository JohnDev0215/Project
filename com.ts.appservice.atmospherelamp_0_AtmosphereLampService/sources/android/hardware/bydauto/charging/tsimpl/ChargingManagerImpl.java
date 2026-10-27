package android.hardware.bydauto.charging.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto_test.TestChargingManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnChargingChangedListener;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ChargingManagerImpl extends AbsTsMyCar {
    private static final int BASE_MILLIONS_SECOND = 1000;
    private static final int BASE_SECOND = 60;
    private static final int CHARGING_COMMAND_INVALID_VALUE = 0;
    private static final float CHARGING_COMMAND_INVALID_VALUE_FLOAT = 0.0f;
    private static final float CHARGING_COMMAND_INVALID_VALUE_float = 0.0f;
    private static final String S_KEY_FOR_TEST = "KEY_FOR_TEST_ChargingManagerImpl";
    private static final int S_VALUE_FOR_TEST = 1;
    private static final String TAG = "ChargingManagerImpl";
    private static volatile ChargingManagerImpl mInstance;
    private AbsBYDAutoChargingListener mAbsBydAutoChargingListener;
    private int mAcChargingLimitR;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTestValue;
    private int[] mHevChargePlanEndTimeR;
    private TestChargingManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private boolean mIgnoreBydPermission = false;
    private int evYear = 0;
    private int evMonth = 0;
    private int evDay = 0;
    private int evHour = 0;
    private int evMinute = 0;
    private int evYearEnd = 0;
    private int evMonthEnd = 0;
    private int evDayEnd = 0;
    private int evHourEnd = 0;
    private int evMinuteEnd = 0;
    private int dmYear = 0;
    private int dmMonth = 0;
    private int dmDay = 0;
    private int dmHour = 0;
    private int dmMinute = 0;
    private int dmYearEnd = 0;
    private int dmMonthEnd = 0;
    private int dmDayEnd = 0;
    private int dmHourEnd = 0;
    private int dmMinuteEnd = 0;
    private int carPlanYear = 0;
    private int carPlanMonth = 0;
    private int carPlanDay = 0;
    private int carPlanHour = 0;
    private int carPlanMinute = 0;
    private int syncTimeYear = 0;
    private int syncTimeMonth = 0;
    private int syncTimeDay = 0;
    private int syncTimeHour = 0;
    private int syncTimeMinute = 0;
    private int mHevChargePlanStartTimeYearR = 0;
    private int mHevChargePlanStartTimeMonthR = 0;
    private int mHevChargePlanStartTimeYearDayR = 0;
    private int mHevChargePlanStartTimeYearHourR = 0;
    private int mHevChargePlanStartTimeYearMinuteR = 0;
    private int mHevChargePlaneEndTimeYearR = 0;
    private int mHevChargePlanEndTimeMonthR = 0;
    private int mHevChargePlanEndTimeYearDayR = 0;
    private int mHevChargePlanEndTimeYearHourR = 0;
    private int mHevChargePlanEndTimeYearMinuteR = 0;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private final CopyOnWriteArrayList<AbsBYDAutoChargingListener> mAbsBydAutoChargingListeners = new CopyOnWriteArrayList<>();
    private int[] mDisChargeTime = new int[2];

    ChargingManagerImpl(Context context) {
        this.mForTestValue = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTestValue = i;
            if (1 == i) {
                Log.v(TAG, "ChargingManagerImpl mForTestValue is" + this.mForTestValue + " execute return");
                this.mTestCallback = new TestChargingManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(this.mContext);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnChargingChangedListener(new OnChargingChangedListener() { // from class: android.hardware.bydauto.charging.tsimpl.ChargingManagerImpl.1
            public void onChargingChanged(int id, int areaId, CarBodyPacket packet) {
                if (!ChargingManagerImpl.this.mAbsBydAutoChargingListeners.isEmpty()) {
                    try {
                        ChargingManagerImpl.this.charginCallback(id, areaId, packet);
                        return;
                    } catch (Exception e) {
                        Log.e(ChargingManagerImpl.TAG, "charginCallback is error");
                        return;
                    }
                }
                Log.v(ChargingManagerImpl.TAG, "mAbsBydAutoChargingListener is null. ");
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(ChargingManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    public static ChargingManagerImpl getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ChargingManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new ChargingManagerImpl(context);
                }
            }
        }
        return mInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public int getChargerFaultState() {
        Log.v(TAG, "getChargerFaultState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargerFaultState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargerFaultState();
    }

    public int getChargerWorkState() {
        Log.v(TAG, "getChargerWorkState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargerWorkState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargerWorkState();
    }

    public float getChargingCapacity() {
        Log.v(TAG, "getChargingCapacity is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingCapacity mForTestValue is " + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingCapacity();
    }

    public int getChargingType() {
        Log.v(TAG, "getChargingType is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingType mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingType();
    }

    public int[] getChargingRestTime() {
        Log.v(TAG, "getChargingRestTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingRestTime mForTestValue is " + this.mForTestValue + " execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return new int[0];
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingRestTime();
    }

    public int getChargingCapState(int type) {
        Log.v(TAG, "getChargingCapState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingCapState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (type == 3) {
            return this.mTsManagerImpl.getChargingAdapterManager().getChargingCapStateLeft();
        }
        if (type != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingCapStateRight();
    }

    public int getChargingPortLockRebackState() {
        Log.v(TAG, "getChargingPortLockRebackState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingPortLockRebackState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingPortLockRebackState();
    }

    public int getDischargeRequestState() {
        Log.v(TAG, "getDischargeRequestState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeRequestState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeRequestState();
    }

    public int getChargerState() {
        Log.v(TAG, "getChargerState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargerState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargerState();
    }

    public int getChargingGunState() {
        Log.v(TAG, "getChargingGunState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingGunState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingGunState();
    }

    public float getChargingPower() {
        Log.v(TAG, "getChargingPower is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingPower mForTestValue is " + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingPower();
    }

    public int getBatteryManagementDeviceState() {
        Log.v(TAG, "getBatteryManagementDeviceState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getBatteryManagementDeviceState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getBatteryManagementDeviceState();
    }

    public int getChargingScheduleEnableState() {
        Log.v(TAG, "getChargingScheduleEnableState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingScheduleEnableState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingScheduleEnableState();
    }

    public int getChargingScheduleState() {
        Log.v(TAG, "getChargingScheduleState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingScheduleState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingScheduleState();
    }

    public int getChargingGunNotInsertedState() {
        Log.v(TAG, "getChargingGunNotInsertedState is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingGunNotInsertedState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingGunNotInsertedState();
    }

    public int[] getChargingScheduleTime() {
        Log.v(TAG, "getChargingScheduleTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargingScheduleTime mForTestValue is " + this.mForTestValue + " execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return new int[0];
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargingScheduleTime();
    }

    public int getPortCapState(int type) {
        Log.i(TAG, "getPortCapState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getPortCapState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getChargingAdapterManager().getgetPortCapStateLeftR();
        }
        if (type != 2) {
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getgetPortCapStateRightR();
    }

    public void registerListener(AbsBYDAutoChargingListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoChargingListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe charging listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTestValue) {
            Log.v(TAG, "registerListener one para mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.setAbsBYDAutoChargingListener(listener);
            return;
        }
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_COMMON);
        if (!this.mAbsBydAutoChargingListeners.contains(listener)) {
            this.mAbsBydAutoChargingListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerChargingCallback();
    }

    public void unregisterListener(AbsBYDAutoChargingListener listener) {
        Log.v(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_COMMON);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "unregisterListener mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.removeListener(listener);
            return;
        }
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoChargingListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getTimerSwitch() {
        Log.v(TAG, "getTimerSwitch is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 != this.mForTestValue) {
            return 0;
        }
        Log.v(TAG, "getTimerSwitch mForTestValue is " + this.mForTestValue + " execute return");
        return 0;
    }

    public int setTimerSwitch(int timerSwitch) {
        Log.v(TAG, "setTimerSwitch is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerSwitch mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerSwitch(timerSwitch);
    }

    public int getTimerType() {
        Log.v(TAG, "getTimerType is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerType mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerType();
    }

    public int setTimerType(int timerType) {
        Log.v(TAG, "setTimerType is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerType mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerType(timerType);
    }

    public int getTimerUnitHour() {
        Log.v(TAG, "getTimerUnitHour is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerUnitHour mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerUnitHour();
    }

    public int setTimerUnitHour(int timerUnitHour) {
        Log.v(TAG, "setTimerUnitHour is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerUnitHour mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerUnitHour(timerUnitHour);
    }

    public int getTimerUnitMinite() {
        Log.v(TAG, "getTimerUnitMinite is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerUnitMinite mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerUnitMinite();
    }

    public int setTimerUnitMinite(int timerUnitMinite) {
        Log.v(TAG, "setTimerUnitMinite is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerUnitMinite mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerUnitMinite(timerUnitMinite);
    }

    public int getTimerCycleMon() {
        Log.v(TAG, "getTimerCycleMon is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerCycleMon mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerCycleMon();
    }

    public int setTimerCycleMon(int timerCycleMon) {
        Log.v(TAG, "setTimerCycleMon is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerCycleMon mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerCycleMon(timerCycleMon);
    }

    public int getTimerCycleTue() {
        Log.v(TAG, "getTimerCycleTue is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerCycleTue mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerCycleTue();
    }

    public int setTimerCycleTue(int timerCycleTue) {
        Log.v(TAG, "setTimerCycleTue is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerCycleTue mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerCycleTue(timerCycleTue);
    }

    public int getTimerCycleWed() {
        Log.v(TAG, "getTimerCycleWed is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerCycleWed mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerCycleWed();
    }

    public int setTimerCycleWed(int timerCycleWed) {
        Log.v(TAG, "setTimerCycleWed is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerCycleWed mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerCycleWed(timerCycleWed);
    }

    public int getTimerCycleThu() {
        Log.v(TAG, "getTimerCycleThu is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerCycleThu mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerCycleThu();
    }

    public int setTimerCycleThu(int timerCycleThu) {
        Log.v(TAG, "setTimerCycleThu is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerCycleThu mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerCycleThu(timerCycleThu);
    }

    public int getTimerCycleFri() {
        Log.v(TAG, "getTimerCycleFri is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerCycleFri mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerCycleFri();
    }

    public int setTimerCycleFri(int timerCycleFri) {
        Log.v(TAG, "setTimerCycleFri is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerCycleFri mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerCycleFri(timerCycleFri);
    }

    public int getTimerCycleSat() {
        Log.v(TAG, "getTimerCycleSat is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerCycleSat mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerCycleSat();
    }

    public int setTimerCycleSat(int timerCycleSat) {
        Log.v(TAG, "setTimerCycleSat is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerCycleSat mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerCycleSat(timerCycleSat);
    }

    public int getTimerCycleSun() {
        Log.v(TAG, "getTimerCycleSun is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getTimerCycleSun mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTimerCycleSun();
    }

    public int setTimerCycleSun(int timerCycleSun) {
        Log.v(TAG, "setTimerCycleSun is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setTimerCycleSun mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setTimerCycleSun(timerCycleSun);
    }

    public int getIntelligentKeepTemMode() {
        Log.v(TAG, "getIntelligentKeepTemMode is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getIntelligentKeepTemMode mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getIntelligentKeepTemMode();
    }

    public int setIntelligentKeepTemMode(int mode) {
        Log.v(TAG, "setIntelligentKeepTemMode is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setIntelligentKeepTemMode mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(6011, mode);
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setIntelligentKeepTemMode(mode);
    }

    public float getChargeLimit() {
        Log.v(TAG, "getChargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeLimit mForTestValue is " + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeLimit();
    }

    public int setChargeLimit(float percent) {
        Log.v(TAG, "setChargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setChargeLimit mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(TestChargingManagerImplCallback.SET_CHARGE_LIMIT, Float.valueOf(percent));
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setChargeLimit(percent);
    }

    public int setChargeToggleGuard(int mode) {
        Log.v(TAG, "setChargeToaggleGuard is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setChargeToaggleGuard mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(TestChargingManagerImplCallback.SET_CHARGE_TOAGGLE_GUARD, mode);
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setChargeToggleGuard(mode);
    }

    public int getChargeToggleGuard() {
        Log.v(TAG, "getChargeToaggleGuard is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeToaggleGuard mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeToggleGuard();
    }

    public int setMovePowerStationToggle(int mode) {
        Log.v(TAG, "isSupportDischarge is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setMovePowerStationToggle mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(TestChargingManagerImplCallback.SET_MOVE_POWER_STATION_TOGGLE, mode);
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setMovePowerStationToggle(mode);
    }

    public int getMovePowerStationToggle() {
        Log.v(TAG, "getMovePowerStationToggle is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getMovePowerStationToggle mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getMovePowerStationToggle();
    }

    public int setDischargeLimit(int percent) {
        Log.v(TAG, "setDischargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setDischargeLimit mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(TestChargingManagerImplCallback.SET_DISCHARGE_LIMIT, percent);
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setDischargeLimit(percent);
    }

    public int getDischargeLimit() {
        Log.v(TAG, "getDischargeLimit is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeLimit mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeLimit();
    }

    public int setDischargeToggle(int state) {
        Log.v(TAG, "setDischargeToggle is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setDischargeToggle mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(TestChargingManagerImplCallback.SET_DISCHARGE_TOGGLE, state);
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setDischargeToggle(state);
    }

    public int getDischargeToggle() {
        Log.v(TAG, "getDischargeToggle is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeToggle mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeToggle();
    }

    public int setDischargeTime(long time) {
        Log.v(TAG, "setDischargeTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setDischargeTime mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(TestChargingManagerImplCallback.SET_DISCHARGE_TIME, Long.valueOf(time));
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int[] times = millionsSec2HourMinArray(time);
        return this.mTsManagerImpl.getChargingAdapterManager().setDischargeTime(times);
    }

    public long getDischargeTime() {
        Log.v(TAG, "getDischargeTime is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0L;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0L;
        }
        int[] dischargeTime = this.mTsManagerImpl.getChargingAdapterManager().getDischargeTime();
        return hourMinArray2MillionsSec(dischargeTime);
    }

    public int setPorducePowerWhenLowPower(int state) {
        Log.v(TAG, "setPorducePowerWhenLowPower is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setPorducePowerWhenLowPower mForTestValue is " + this.mForTestValue + " execute return");
            this.mTestCallback.enqueueMessage(TestChargingManagerImplCallback.SET_PORDUCE_POWER_WHEN_LOW_POWER, state);
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setPorducePowerWhenLowPower(state);
    }

    public int getPorducePowerWhenLowPower() {
        Log.v(TAG, "getPorducePowerWhenLowPower is run. ");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getPorducePowerWhenLowPower mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getPorducePowerWhenLowPower();
    }

    public int setWirelessChargingSwitchState(int state) {
        Log.v(TAG, "setWirelessChargingSwitchState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setWirelessChargingSwitchState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setWirelessChargingSwitchState(state);
    }

    public int getWirelessChargingSwitchState() {
        Log.v(TAG, "getWirelessChargingSwitchState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getWirelessChargingSwitchState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingSwitchState();
    }

    public int getWirelessChargingState() {
        Log.v(TAG, "getWirelessChargingState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getWirelessChargingState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingState();
    }

    private int[] millionsSec2HourMinArray(long time) {
        int minAll = (int) ((time / 1000) / 60);
        int min = minAll % 60;
        int hours = minAll / 60;
        int[] times = {hours, min};
        return times;
    }

    private long hourMinArray2MillionsSec(int[] time) {
        long hourSecond = time[0] * 60 * 60 * 1000;
        long minSecond = time[1] * 60 * 1000;
        return hourSecond + minSecond;
    }

    public int getVtovChargeBatteryLevelNum() {
        Log.v(TAG, "getVtovChargeBatteryLevelNum is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVtovChargeBatteryLevelNum mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVtovChargeBatteryLevelNum();
    }

    public int getVtovChargeBatteryLevelNumLimit() {
        Log.v(TAG, "getVtovChargeBatteryLevelNumLimit is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVtovChargeBatteryLevelNumLimit mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVtovChargeBatteryLevelNumLimit();
    }

    public int getDischargeRemainTime() {
        Log.v(TAG, "getDischargeRemainTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeRemainTime mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeRemainTime();
    }

    public float getChargeVoltageMax() {
        Log.v(TAG, "getChargeVoltageMax is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeVoltageMax mForTestValue is" + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeVoltageMax();
    }

    public float getVoltageMax() {
        Log.v(TAG, "getVoltageMax is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVoltageMax mForTestValue is" + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVoltageMax();
    }

    public int getDischargeVoltage() {
        Log.v(TAG, "getDischargeVoltage is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeVoltage mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeVoltage();
    }

    public float getDischargeElectric() {
        Log.v(TAG, "getDischargeElectric is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeElectric mForTestValue is" + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeElectric();
    }

    public int getDischargeGunTimer() {
        Log.v(TAG, "getDischargeGunTimer is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeGunTimer mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeGunTimer();
    }

    public int getDischargeTotalTime() {
        Log.v(TAG, "getDischargeTotalTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeTotalTime mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeTotalTime();
    }

    public int getDischargeOverReason() {
        Log.v(TAG, "getDischargeOverReason is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getDischargeOverReason mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getDischargeOverReason();
    }

    public int getChargeVoltage() {
        Log.v(TAG, "getChargeVoltage is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeVoltage mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeVoltage();
    }

    public float getChargeElectric() {
        Log.v(TAG, "getChargeElectric is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeElectric mForTestValue is" + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeElectric();
    }

    public int getChargeBatteryLevelBegin() {
        Log.v(TAG, "getChargeBatteryLevelBegin is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeBatteryLevelBegin mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeBatteryLevelBegin();
    }

    public int getChargeBatteryLimitTemp() {
        Log.v(TAG, "getChargeBatteryLimitTemp is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeBatteryLimitTemp mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeBatteryLimitTemp();
    }

    public int getChargeBatteryTemp() {
        Log.v(TAG, "getChargeBatteryTemp is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeBatteryTemp mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeBatteryTemp();
    }

    public int getVtovDischargeLowestVal() {
        Log.v(TAG, "getVtovDischargeLowestVal is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVtovDischargeLowestVal mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeLowestVal();
    }

    public float getVtovDischargeQuantity() {
        Log.v(TAG, "getVtovDischargeQuantity is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVtovDischargeQuantity mForTestValue is" + this.mForTestValue + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeQuantity();
    }

    public int getBatteryType() {
        Log.v(TAG, "getBatteryType is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getBatteryType mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getBatteryType();
    }

    public int getFullChgRemanTimH() {
        Log.v(TAG, "getFullChgRemanTimH is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getFullChgRemanTimH mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getFullChgRemanTimH();
    }

    public int getFullChgRemanTimM() {
        Log.v(TAG, "getFullChgRemanTimM is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getFullChgRemanTimM mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getFullChgRemanTimM();
    }

    public int setHevChargingType(int type) {
        Log.v(TAG, "setHEVChargingType is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setHEVChargingType mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setHevChargingType(type);
    }

    public int getHevChargingType() {
        Log.v(TAG, "getHEVChargingType is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getHEVChargingType mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getHevChargingType();
    }

    public int getChargeTempCtlState() {
        Log.v(TAG, "getChargeTempCtlState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeTempCtlState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeTempCtlState();
    }

    public int setChargeTempCtlState(int mode) {
        Log.v(TAG, "setChargeTempCtlState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setChargeTempCtlState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setChargeTempCtlState(mode);
    }

    public int getChargeStopCapacityState() {
        Log.v(TAG, "getChargeStopCapacityState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargeStopCapacityState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeStopCapacityState();
    }

    public int setChargeStopCapacityState(int state) {
        Log.v(TAG, "setChargeStopCapacityState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setChargeStopCapacityState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setChargeStopCapacityState(state);
    }

    public int getVtovDischargeLimitVal() {
        Log.v(TAG, "getVtovDischargeLimitVal is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVtovDischargeLimitVal mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeLimitVal();
    }

    public int getVtovDischargeConnectState() {
        Log.v(TAG, "getVtovDischargeConnectState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVtovDischargeConnectState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeConnectState();
    }

    public int setChargePlanState(int state) {
        Log.v(TAG, "setChargePlanState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setChargePlanState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setChargePlanState(state);
    }

    public int setSyncTime(int year, int month, int day, int hour, int minute) {
        Log.v(TAG, "setSyncTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setSyncTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setSyncTime(year, month, day, hour, minute);
    }

    public int setCarPlan(int appointmentFlag, int chargeMode, int safeBattery, int year, int month, int day, int hour, int minute) {
        Log.v(TAG, "setCarPlan is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setCarPlan mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setCarPlan(appointmentFlag, chargeMode, safeBattery, year, month, day, hour, minute);
    }

    public int setEvChargePlanEndTime(int year, int month, int day, int hour, int minute) {
        Log.v(TAG, "setEVChargePlanEndTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setEVChargePlanEndTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.evYearEnd = year;
        this.evMonthEnd = month;
        this.evDayEnd = day;
        this.evHourEnd = hour;
        this.evMinuteEnd = minute;
        return this.mTsManagerImpl.getChargingAdapterManager().setEvChargePlanEndTime(year, month, day, hour, minute);
    }

    public int setEvChargePlanStartTime(int year, int month, int day, int hour, int minute) {
        Log.v(TAG, "setEVChargePlanStartTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setEVChargePlanStartTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.evYear = year;
        this.evMonth = month;
        this.evDay = day;
        this.evHour = hour;
        this.evMinute = minute;
        return this.mTsManagerImpl.getChargingAdapterManager().setEvChargePlanStartTime(year, month, day, hour, minute);
    }

    public int setHevChargePlanEndTime(int year, int month, int day, int hour, int minute) {
        Log.v(TAG, "setHEVChargePlanEndTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setHEVChargePlanEndTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setHevChargePlanEndTime(year, month, day, hour, minute);
    }

    public int setHevChargePlanStartTime(int year, int month, int day, int hour, int minute) {
        Log.v(TAG, "setHEVChargePlanStartTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setHEVChargePlanStartTime mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.dmYear = year;
        this.dmMonth = month;
        this.dmDay = day;
        this.dmHour = hour;
        this.dmMinute = minute;
        return this.mTsManagerImpl.getChargingAdapterManager().setHevChargePlanStartTime(year, month, day, hour, minute);
    }

    public int getSituPowerGenerationState() {
        Log.v(TAG, "getSituPowerGenerationState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getSituPowerGenerationState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getSituPowerGenerationState();
    }

    public int setSituPowerGenerationState(int state) {
        Log.v(TAG, "setSituPowerGenerationState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setSituPowerGenerationState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setSituPowerGenerationState(state);
    }

    public int getSituPowerGenerationSupport() {
        Log.v(TAG, "getSituPowerGenerationSupport is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getSituPowerGenerationSupport mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getSituPowerGenerationSupport();
    }

    public int getChargePreheatAllow() {
        Log.v(TAG, "getChargePreheatAllow is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargePreheatAllow mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargePreheatAllow();
    }

    public int getVtovDischargeLowPowerHint() {
        Log.v(TAG, "getVtovDischargeLowPowerHint is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getVtovDischargeLowPowerHint mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getVtovDischargeLowPowerHint();
    }

    public int getChargePreheatState() {
        Log.v(TAG, "getChargePreheatState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getChargePreheatState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargePreheatState();
    }

    public int setChargePreheatState(int state) {
        Log.v(TAG, "setChargePreheatState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setChargePreheatState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setChargePreheatState(state);
    }

    @Deprecated
    public int setCarPlanState(int state) {
        Log.v(TAG, "setCarPlanState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setCarPlanState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setCarPlanState(state);
    }

    public int getCarPlanState() {
        Log.v(TAG, "getCarPlanState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getCarPlanState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getCarPlanState();
    }

    public int getCarPlanAccOnState() {
        Log.v(TAG, "getCarPlanAccOnState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getCarPlanAccOnState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getCarPlanAccOnState();
    }

    public int getCarPlanBatteryHeatState() {
        Log.v(TAG, "getCarPlanBatteryHeatState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getCarPlanBatteryHeatState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getCarPlanBatteryHeatState();
    }

    public int setNoSenseChargeState(int state) {
        Log.v(TAG, "setNoSenseChargeState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setNoSenseChargeState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setNoSenseChargeState(state);
    }

    public int setDriveDistanceShowStandard(int value) {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        Log.v(TAG, "setDriveDistanceShowStandard is execute");
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setDriveDistanceShowStandard mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowStandard(value);
    }

    public int setPreSaleModelState(int state) {
        Log.v(TAG, "setPreSaleModelState is execute");
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setPreSaleModelState mForTestValue is" + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setPreSaleModelState(state);
    }

    public int getNoSenseChargeState() {
        Log.v(TAG, "getNoSenseChargeState is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getNoSenseChargeState mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getNoSenseChargeState();
    }

    public byte[] getObcWakeNoSenseCharge() {
        Log.v(TAG, "getOBCWakeNoSenseCharge is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getOBCWakeNoSenseCharge mForTestValue is " + this.mForTestValue + " execute return");
            return new byte[0];
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return new byte[0];
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getObcWakeNoSenseCharge();
    }

    public byte[] getBmsWakeNoSenseCharge() {
        Log.v(TAG, "getBMSWakeNoSenseCharge is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "getBMSWakeNoSenseCharge mForTestValue is " + this.mForTestValue + " execute return");
            return new byte[0];
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return new byte[0];
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getBmsWakeNoSenseCharge();
    }

    public int setElectricityPriceLevel(byte[] value) {
        Log.v(TAG, "setElectricityPriceLevel is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, "setElectricityPriceLevel mForTestValue is " + this.mForTestValue + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setElectricityPriceLevel(value);
    }

    public int setWirelessChargingFrSwitchState(int state) {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_SET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, " setWirelessChargingFrSwitchState mForTestValue is " + this.mForTestValue + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setWirelessChargingFrSwitchState(state);
    }

    public int getWirelessChargingFrSwitchState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getWirelessChargingFrSwitchState mForTestValue is " + this.mForTestValue + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingFrSwitchState();
    }

    public int getWirelessChargingFrState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getWirelessChargingFrState mForTestValue is " + this.mForTestValue + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingFrState();
    }

    public int getWirelessChargingPowerFlState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getWirelessChargingPowerFlState mForTestValue is " + this.mForTestValue + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingPowerFlState();
    }

    public int getWirelessChargingPowerFrState() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getWirelessChargingPowerFrState mForTestValue is " + this.mForTestValue + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getWirelessChargingPowerFrState();
    }

    public int[] getHevChargePlanStartTime() {
        Log.i(TAG, "getHevChargePlanStartTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new int[0];
        }
        return new int[]{this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeMonthR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearDayR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearHourR(), this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeYearMinuteR()};
    }

    public int[] getHevChargePlanEndTime() {
        Log.i(TAG, "getHevChargePlanEndTime is execute");
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return new int[0];
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanEndTime();
    }

    public int getHevChargePlanStartTimeTag() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getHevChargePlanStartTimeTag mForTestValue is " + this.mForTestValue + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanStartTimeTag();
    }

    public int getHevChargePlanEndTimeTag() {
        enforcePermission(BYDAutoChargingDevice.PERMISSION_BYDAUTO_CHARGING_GET);
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getHevChargePlanEndTimeTag mForTestValue is " + this.mForTestValue + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getHevChargePlanEndTimeTag();
    }

    public int getAcChargingLimit() {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getAcChargingLimit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getAcChargingLimit();
    }

    public int setAcChargingLimit(int state) {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " setAcChargingLimit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return -1;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setAcChargingLimit(state);
    }

    public int getChargeStopCapacityStateNotLi() {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getChargeStopCapacityStateNotLi mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getChargeStopCapacityStateNotLi();
    }

    public int setChargeStopCapacityStateNotLi(int state) {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " setChargeStopCapacityStateNotLi mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setChargeStopCapacityStateNotLi(state);
    }

    public int setV2gStatus(int state) {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " setV2gStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setV2gStatus(state);
    }

    public int setV2gPower(float value) {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " setV2gPower mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setV2gPower(value);
    }

    public int setV2gCmd(int value) {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " setV2gCmd mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().setV2gCmd(value);
    }

    public int getV2gStatus() {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getV2gStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getV2gStatus();
    }

    public int getV2gChargeStatus() {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getV2gChargeStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getV2gChargeStatus();
    }

    public int getV2gOutputPower() {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getV2gOutputPower mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getChargingAdapterManager().getV2gOutputPower();
    }

    public int getElectricPortCapState(int type) {
        if (1 == this.mForTestValue) {
            Log.v(TAG, " getElectricPortCapState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getChargingAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getChargingAdapterManager().getElectricPortCapStateLeft();
        }
        if (type == 2) {
            return this.mTsManagerImpl.getChargingAdapterManager().getElectricPortCapStateRight();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void charginCallback(int id, int areaId, CarBodyPacket packet) {
        Log.i(TAG, "charginCallback propertyId:" + id);
        switch (id) {
            case 557855744:
                for (AbsBYDAutoChargingListener listener : this.mAbsBydAutoChargingListeners) {
                    listener.onChargerFaultStateChanged(packet.getChargerFaultStateR());
                }
                break;
            case 557855745:
                for (AbsBYDAutoChargingListener listener2 : this.mAbsBydAutoChargingListeners) {
                    listener2.onBatteryManagementDeviceStateChanged(packet.getChargerWorkStateR());
                }
                break;
            default:
                switch (id) {
                    case 557855748:
                        for (AbsBYDAutoChargingListener listener3 : this.mAbsBydAutoChargingListeners) {
                            listener3.onChargingPortLockRebackStateChanged(packet.getCharingPortLockRebackStateR());
                        }
                        break;
                    case 557855749:
                        for (AbsBYDAutoChargingListener listener4 : this.mAbsBydAutoChargingListeners) {
                            listener4.onDischargeRequestStateChanged(packet.getDischargeRequestStateR());
                        }
                        break;
                    case 557855750:
                        for (AbsBYDAutoChargingListener listener5 : this.mAbsBydAutoChargingListeners) {
                            listener5.onChargerStateChanged(packet.getChargerStateR());
                        }
                        break;
                    case 557855751:
                        for (AbsBYDAutoChargingListener listener6 : this.mAbsBydAutoChargingListeners) {
                            listener6.onChargingGunStateChanged(packet.getChargingGunStateR());
                        }
                        caseFeatureIds(2520, Integer.valueOf(packet.getChargingGunStateR()));
                        break;
                    default:
                        switch (id) {
                            case 557855754:
                                for (AbsBYDAutoChargingListener listener7 : this.mAbsBydAutoChargingListeners) {
                                    listener7.onChargingScheduleEnableStateChanged(packet.getChargingScheduleEnableStateR());
                                }
                                break;
                            case 557855755:
                                for (AbsBYDAutoChargingListener listener8 : this.mAbsBydAutoChargingListeners) {
                                    listener8.onChargingScheduleStateChanged(packet.getChargingScheduleStateR());
                                }
                                break;
                            case 557855756:
                            case 557855757:
                            case 557855758:
                            case 557855759:
                            case 557855760:
                                for (AbsBYDAutoChargingListener listener9 : this.mAbsBydAutoChargingListeners) {
                                    listener9.onChargingScheduleTimeCountDownChanged(packet.getChargingScheduleTimeHourR(), packet.getChargingScheduleTimeMinuteR());
                                }
                                break;
                            default:
                                switch (id) {
                                    case 557855762:
                                        for (AbsBYDAutoChargingListener listener10 : this.mAbsBydAutoChargingListeners) {
                                            listener10.onChargeToggleGuardChanged(packet.getChargeToggleGuardR());
                                        }
                                        break;
                                    case 557855764:
                                        for (AbsBYDAutoChargingListener listener11 : this.mAbsBydAutoChargingListeners) {
                                            listener11.onMovePowerStationToggleChanged(packet.getMovePowerStationToggleR());
                                        }
                                        break;
                                    case 557855768:
                                        for (AbsBYDAutoChargingListener listener12 : this.mAbsBydAutoChargingListeners) {
                                            listener12.onDischargeToggleChanged(packet.getDischargeToggleR());
                                        }
                                        break;
                                    case 557855774:
                                        for (AbsBYDAutoChargingListener listener13 : this.mAbsBydAutoChargingListeners) {
                                            listener13.onPorducePowerWhenLowPowerChanged(packet.getProducePowerWhenLowPowerR());
                                        }
                                        break;
                                    case 557855793:
                                        for (AbsBYDAutoChargingListener listener14 : this.mAbsBydAutoChargingListeners) {
                                            listener14.onVtovDischargeLowestValChanged(packet.getVtovDischargeLowestValR());
                                        }
                                        break;
                                    case 557855798:
                                        for (AbsBYDAutoChargingListener listener15 : this.mAbsBydAutoChargingListeners) {
                                            listener15.onChargerWorkStateChanged(packet.getChargerWorkStateFixR());
                                        }
                                        break;
                                    case 557855804:
                                        for (AbsBYDAutoChargingListener listener16 : this.mAbsBydAutoChargingListeners) {
                                            listener16.onChargeStopCapacityStateChanged(packet.getChargeStopCapacityStateR());
                                        }
                                        break;
                                    case 557855806:
                                        for (AbsBYDAutoChargingListener listener17 : this.mAbsBydAutoChargingListeners) {
                                            listener17.onChargeToggleGuardChanged(packet.getChargeToaggleGuardMixR());
                                        }
                                        break;
                                    case 557855807:
                                        for (AbsBYDAutoChargingListener listener18 : this.mAbsBydAutoChargingListeners) {
                                            listener18.onVtovDischargeLimitValChanged(packet.getVtovDischargeLimitValR());
                                        }
                                        caseFeatureIds(2748, Integer.valueOf(packet.getVtovDischargeLimitValR()));
                                        break;
                                    case 557855808:
                                    case 557855809:
                                        int hour = packet.getChargingResetTimeHourR();
                                        int minute = packet.getChargingResetTimeMinuteR();
                                        for (AbsBYDAutoChargingListener listener19 : this.mAbsBydAutoChargingListeners) {
                                            listener19.onChargingRestTimeChanged(hour, minute);
                                        }
                                        break;
                                    case 557855810:
                                        for (AbsBYDAutoChargingListener listener20 : this.mAbsBydAutoChargingListeners) {
                                            listener20.onVtovDischargeConnectStateChanged(packet.getVtovDischargeConnectState());
                                        }
                                        break;
                                    case 557855811:
                                        byte[] obcBytes2 = {(byte) packet.getObcWakeNoSenseCharge1R()};
                                        for (AbsBYDAutoChargingListener listener21 : this.mAbsBydAutoChargingListeners) {
                                            listener21.onOBCWakeNoSenseChargeChanged(obcBytes2);
                                        }
                                        break;
                                    case 557855812:
                                        byte[] obcBytes3 = {(byte) packet.getObcWakeNoSenseCharge2R()};
                                        for (AbsBYDAutoChargingListener listener22 : this.mAbsBydAutoChargingListeners) {
                                            listener22.onOBCWakeNoSenseChargeChanged(obcBytes3);
                                        }
                                        break;
                                    case 557855813:
                                        byte[] obcBytes4 = {(byte) packet.getObcWakeNoSenseCharge3R()};
                                        for (AbsBYDAutoChargingListener listener23 : this.mAbsBydAutoChargingListeners) {
                                            listener23.onOBCWakeNoSenseChargeChanged(obcBytes4);
                                        }
                                        break;
                                    case 557855814:
                                        byte[] obcBytes5 = {(byte) packet.getObcWakeNoSenseCharge4R()};
                                        for (AbsBYDAutoChargingListener listener24 : this.mAbsBydAutoChargingListeners) {
                                            listener24.onOBCWakeNoSenseChargeChanged(obcBytes5);
                                        }
                                        break;
                                    case 557855815:
                                        byte[] obcBytes6 = {(byte) packet.getObcWakeNoSenseCharge5R()};
                                        for (AbsBYDAutoChargingListener listener25 : this.mAbsBydAutoChargingListeners) {
                                            listener25.onOBCWakeNoSenseChargeChanged(obcBytes6);
                                        }
                                        break;
                                    case 557855816:
                                        byte[] obcBytes7 = {(byte) packet.getObcWakeNoSenseCharge6R()};
                                        for (AbsBYDAutoChargingListener listener26 : this.mAbsBydAutoChargingListeners) {
                                            listener26.onOBCWakeNoSenseChargeChanged(obcBytes7);
                                        }
                                        break;
                                    case 557855817:
                                        byte[] obcBytes8 = {(byte) packet.getObcWakeNoSenseCharge7R()};
                                        for (AbsBYDAutoChargingListener listener27 : this.mAbsBydAutoChargingListeners) {
                                            listener27.onOBCWakeNoSenseChargeChanged(obcBytes8);
                                        }
                                        break;
                                    case 557855818:
                                        byte[] obcBytes9 = {(byte) packet.getObcWakeNoSenseCharge8R()};
                                        for (AbsBYDAutoChargingListener listener28 : this.mAbsBydAutoChargingListeners) {
                                            listener28.onOBCWakeNoSenseChargeChanged(obcBytes9);
                                        }
                                        break;
                                    case 557855819:
                                        byte[] obcBytes10 = {(byte) packet.getObcWakeNoSenseCharge9R()};
                                        for (AbsBYDAutoChargingListener listener29 : this.mAbsBydAutoChargingListeners) {
                                            listener29.onOBCWakeNoSenseChargeChanged(obcBytes10);
                                        }
                                        break;
                                    case 557855820:
                                        byte[] obcBytes11 = {(byte) packet.getObcWakeNoSenseCharge10R()};
                                        for (AbsBYDAutoChargingListener listener30 : this.mAbsBydAutoChargingListeners) {
                                            listener30.onOBCWakeNoSenseChargeChanged(obcBytes11);
                                        }
                                        break;
                                    case 557855821:
                                        byte[] obcBytes12 = {(byte) packet.getObcWakeNoSenseCharge11R()};
                                        for (AbsBYDAutoChargingListener listener31 : this.mAbsBydAutoChargingListeners) {
                                            listener31.onOBCWakeNoSenseChargeChanged(obcBytes12);
                                        }
                                        break;
                                    case 557855822:
                                        byte[] obcBytes13 = {(byte) packet.getObcWakeNoSenseCharge12R()};
                                        for (AbsBYDAutoChargingListener listener32 : this.mAbsBydAutoChargingListeners) {
                                            listener32.onOBCWakeNoSenseChargeChanged(obcBytes13);
                                        }
                                        break;
                                    case 557855823:
                                        byte[] obcBytes14 = {(byte) packet.getObcWakeNoSenseCharge13R()};
                                        for (AbsBYDAutoChargingListener listener33 : this.mAbsBydAutoChargingListeners) {
                                            listener33.onOBCWakeNoSenseChargeChanged(obcBytes14);
                                        }
                                        break;
                                    case 557855824:
                                        byte[] obcBytes15 = {(byte) packet.getObcWakeNoSenseCharge14R()};
                                        for (AbsBYDAutoChargingListener listener34 : this.mAbsBydAutoChargingListeners) {
                                            listener34.onOBCWakeNoSenseChargeChanged(obcBytes15);
                                        }
                                        break;
                                    case 557855825:
                                        byte[] obcBytes16 = {(byte) packet.getObcWakeNoSenseCharge15R()};
                                        for (AbsBYDAutoChargingListener listener35 : this.mAbsBydAutoChargingListeners) {
                                            listener35.onOBCWakeNoSenseChargeChanged(obcBytes16);
                                        }
                                        break;
                                    case 557855826:
                                        byte[] obcBytes17 = {(byte) packet.getObcWakeNoSenseCharge16R()};
                                        for (AbsBYDAutoChargingListener listener36 : this.mAbsBydAutoChargingListeners) {
                                            listener36.onOBCWakeNoSenseChargeChanged(obcBytes17);
                                        }
                                        break;
                                    case 557855827:
                                        byte[] obcBytes18 = {(byte) packet.getObcWakeNoSenseCharge17R()};
                                        for (AbsBYDAutoChargingListener listener37 : this.mAbsBydAutoChargingListeners) {
                                            listener37.onOBCWakeNoSenseChargeChanged(obcBytes18);
                                        }
                                        break;
                                    case 557855828:
                                        byte[] obcBytes19 = {(byte) packet.getObcWakeNoSenseCharge18R()};
                                        for (AbsBYDAutoChargingListener listener38 : this.mAbsBydAutoChargingListeners) {
                                            listener38.onOBCWakeNoSenseChargeChanged(obcBytes19);
                                        }
                                        break;
                                    case 557855829:
                                        byte[] obcBytes20 = {(byte) packet.getObcWakeNoSenseCharge19R()};
                                        for (AbsBYDAutoChargingListener listener39 : this.mAbsBydAutoChargingListeners) {
                                            listener39.onOBCWakeNoSenseChargeChanged(obcBytes20);
                                        }
                                        break;
                                    case 557855830:
                                        byte[] obcBytes21 = {(byte) packet.getObcWakeNoSenseCharge20R()};
                                        for (AbsBYDAutoChargingListener listener40 : this.mAbsBydAutoChargingListeners) {
                                            listener40.onOBCWakeNoSenseChargeChanged(obcBytes21);
                                        }
                                        break;
                                    case 557855831:
                                        byte[] obcBytes22 = {(byte) packet.getObcWakeNoSenseCharge21R()};
                                        for (AbsBYDAutoChargingListener listener41 : this.mAbsBydAutoChargingListeners) {
                                            listener41.onOBCWakeNoSenseChargeChanged(obcBytes22);
                                        }
                                        break;
                                    case 557855832:
                                        byte[] obcBytes23 = {(byte) packet.getObcWakeNoSenseCharge22R()};
                                        for (AbsBYDAutoChargingListener listener42 : this.mAbsBydAutoChargingListeners) {
                                            listener42.onOBCWakeNoSenseChargeChanged(obcBytes23);
                                        }
                                        break;
                                    case 557855833:
                                        byte[] obcBytes24 = {(byte) packet.getObcWakeNoSenseCharge23R()};
                                        for (AbsBYDAutoChargingListener listener43 : this.mAbsBydAutoChargingListeners) {
                                            listener43.onOBCWakeNoSenseChargeChanged(obcBytes24);
                                        }
                                        break;
                                    case 557855834:
                                        byte[] obcBytes25 = {(byte) packet.getObcWakeNoSenseCharge24R()};
                                        for (AbsBYDAutoChargingListener listener44 : this.mAbsBydAutoChargingListeners) {
                                            listener44.onOBCWakeNoSenseChargeChanged(obcBytes25);
                                        }
                                        break;
                                    case 557855835:
                                        byte[] obcBytes26 = {(byte) packet.getObcWakeNoSenseCharge25R()};
                                        for (AbsBYDAutoChargingListener listener45 : this.mAbsBydAutoChargingListeners) {
                                            listener45.onOBCWakeNoSenseChargeChanged(obcBytes26);
                                        }
                                        break;
                                    case 557855836:
                                        byte[] obcBytes27 = {(byte) packet.getObcWakeNoSenseCharge26R()};
                                        for (AbsBYDAutoChargingListener listener46 : this.mAbsBydAutoChargingListeners) {
                                            listener46.onOBCWakeNoSenseChargeChanged(obcBytes27);
                                        }
                                        break;
                                    case 557855837:
                                        byte[] obcBytes28 = {(byte) packet.getObcWakeNoSenseCharge27R()};
                                        for (AbsBYDAutoChargingListener listener47 : this.mAbsBydAutoChargingListeners) {
                                            listener47.onOBCWakeNoSenseChargeChanged(obcBytes28);
                                        }
                                        break;
                                    case 557855838:
                                        byte[] obcBytes29 = {(byte) packet.getObcWakeNoSenseCharge28R()};
                                        for (AbsBYDAutoChargingListener listener48 : this.mAbsBydAutoChargingListeners) {
                                            listener48.onOBCWakeNoSenseChargeChanged(obcBytes29);
                                        }
                                        break;
                                    case 557855839:
                                        byte[] obcBytes30 = {(byte) packet.getObcWakeNoSenseCharge29R()};
                                        for (AbsBYDAutoChargingListener listener49 : this.mAbsBydAutoChargingListeners) {
                                            listener49.onOBCWakeNoSenseChargeChanged(obcBytes30);
                                        }
                                        break;
                                    case 557855840:
                                        byte[] obcBytes31 = {(byte) packet.getObcWakeNoSenseCharge30R()};
                                        for (AbsBYDAutoChargingListener listener50 : this.mAbsBydAutoChargingListeners) {
                                            listener50.onOBCWakeNoSenseChargeChanged(obcBytes31);
                                        }
                                        break;
                                    case 557855841:
                                        byte[] obcBytes32 = {(byte) packet.getObcWakeNoSenseCharge31R()};
                                        for (AbsBYDAutoChargingListener listener51 : this.mAbsBydAutoChargingListeners) {
                                            listener51.onOBCWakeNoSenseChargeChanged(obcBytes32);
                                        }
                                        break;
                                    case 557855842:
                                        byte[] obcBytes33 = {(byte) packet.getObcWakeNoSenseCharge32R()};
                                        for (AbsBYDAutoChargingListener listener52 : this.mAbsBydAutoChargingListeners) {
                                            listener52.onOBCWakeNoSenseChargeChanged(obcBytes33);
                                        }
                                        break;
                                    case 557855843:
                                        byte[] obcBytes34 = {(byte) packet.getObcWakeNoSenseCharge33R()};
                                        for (AbsBYDAutoChargingListener listener53 : this.mAbsBydAutoChargingListeners) {
                                            listener53.onOBCWakeNoSenseChargeChanged(obcBytes34);
                                        }
                                        break;
                                    case 557855844:
                                        byte[] obcBytes35 = {(byte) packet.getObcWakeNoSenseCharge34R()};
                                        for (AbsBYDAutoChargingListener listener54 : this.mAbsBydAutoChargingListeners) {
                                            listener54.onOBCWakeNoSenseChargeChanged(obcBytes35);
                                        }
                                        break;
                                    case 557855845:
                                        byte[] obcBytes352 = {(byte) packet.getObcWakeNoSenseCharge35R()};
                                        for (AbsBYDAutoChargingListener listener55 : this.mAbsBydAutoChargingListeners) {
                                            listener55.onOBCWakeNoSenseChargeChanged(obcBytes352);
                                        }
                                        break;
                                    case 557856266:
                                        for (AbsBYDAutoChargingListener listener56 : this.mAbsBydAutoChargingListeners) {
                                            listener56.onSituPowerGenerationStateChanged(packet.getSituPowerGenerationStateR());
                                        }
                                        break;
                                    case 557856276:
                                        for (AbsBYDAutoChargingListener listener57 : this.mAbsBydAutoChargingListeners) {
                                            listener57.onNoSenseChargeStateChanged(packet.getNoSenseChargeStateR());
                                        }
                                        break;
                                    case 557856280:
                                        this.mAcChargingLimitR = packet.getAcChargingLimitR();
                                        for (AbsBYDAutoChargingListener listener58 : this.mAbsBydAutoChargingListeners) {
                                            listener58.onACChargingLimitChanged(this.mAcChargingLimitR);
                                        }
                                        break;
                                    case 557856326:
                                        for (AbsBYDAutoChargingListener listener59 : this.mAbsBydAutoChargingListeners) {
                                            listener59.onWirelessChargingFRSwitchStateChanged(packet.getWirelessChargingFrSwitchStateR());
                                        }
                                        break;
                                    case 557856327:
                                        for (AbsBYDAutoChargingListener listener60 : this.mAbsBydAutoChargingListeners) {
                                            listener60.onWirelessChargingFRStateChanged(packet.getWirelessChargingFrStateR());
                                        }
                                        break;
                                    case 557856328:
                                        for (AbsBYDAutoChargingListener listener61 : this.mAbsBydAutoChargingListeners) {
                                            listener61.onWirelessChargingPowerFLStateChanged(packet.getWirelessChargingPowerFlStateR());
                                        }
                                        break;
                                    case 557856329:
                                        for (AbsBYDAutoChargingListener listener62 : this.mAbsBydAutoChargingListeners) {
                                            listener62.onWirelessChargingPowerFRStateChanged(packet.getWirelessChargingPowerFrStateR());
                                        }
                                        break;
                                    case 557856330:
                                        for (AbsBYDAutoChargingListener listener63 : this.mAbsBydAutoChargingListeners) {
                                            listener63.onPortCapStateChanged(2, packet.getgetPortCapStateRightR());
                                        }
                                        caseFeatureIds(2628, Integer.valueOf(packet.getgetPortCapStateRightR()));
                                        break;
                                    case 557856331:
                                        for (AbsBYDAutoChargingListener listener64 : this.mAbsBydAutoChargingListeners) {
                                            listener64.onPortCapStateChanged(1, packet.getgetPortCapStateLeftR());
                                        }
                                        caseFeatureIds(2596, Integer.valueOf(packet.getgetPortCapStateLeftR()));
                                        break;
                                    case 557856332:
                                        this.mHevChargePlanStartTimeYearR = packet.getHevChargePlanStartTimeYearR();
                                        for (AbsBYDAutoChargingListener listener65 : this.mAbsBydAutoChargingListeners) {
                                            listener65.onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856333:
                                        this.mHevChargePlanStartTimeMonthR = packet.getHevChargePlanStartTimeMonthR();
                                        for (AbsBYDAutoChargingListener listener66 : this.mAbsBydAutoChargingListeners) {
                                            listener66.onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856334:
                                        this.mHevChargePlanStartTimeYearDayR = packet.getHevChargePlanStartTimeYearDayR();
                                        for (AbsBYDAutoChargingListener listener67 : this.mAbsBydAutoChargingListeners) {
                                            listener67.onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856335:
                                        this.mHevChargePlanStartTimeYearHourR = packet.getHevChargePlanStartTimeYearHourR();
                                        for (AbsBYDAutoChargingListener listener68 : this.mAbsBydAutoChargingListeners) {
                                            listener68.onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856336:
                                        this.mHevChargePlanStartTimeYearMinuteR = packet.getHevChargePlanStartTimeYearMinuteR();
                                        for (AbsBYDAutoChargingListener listener69 : this.mAbsBydAutoChargingListeners) {
                                            listener69.onHEVChargePlanStartTimeChargeChanged(new int[]{this.mHevChargePlanStartTimeYearR, this.mHevChargePlanStartTimeMonthR, this.mHevChargePlanStartTimeYearDayR, this.mHevChargePlanStartTimeYearHourR, this.mHevChargePlanStartTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856337:
                                        this.mHevChargePlaneEndTimeYearR = packet.getHevChargePlaneEndTimeYearR();
                                        for (AbsBYDAutoChargingListener listener70 : this.mAbsBydAutoChargingListeners) {
                                            listener70.onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856338:
                                        this.mHevChargePlanEndTimeMonthR = packet.getHevChargePlanEndTimeMonthR();
                                        for (AbsBYDAutoChargingListener listener71 : this.mAbsBydAutoChargingListeners) {
                                            listener71.onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856339:
                                        int result = packet.getChargeStopCapacityStateNotLiR();
                                        for (AbsBYDAutoChargingListener listener72 : this.mAbsBydAutoChargingListeners) {
                                            listener72.onChargeStopCapacityStateNotLiChanged(result);
                                        }
                                        break;
                                    case 557856340:
                                        this.mHevChargePlanEndTimeYearHourR = packet.getHevChargePlanEndTimeYearHourR();
                                        for (AbsBYDAutoChargingListener listener73 : this.mAbsBydAutoChargingListeners) {
                                            listener73.onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856341:
                                        this.mHevChargePlanEndTimeYearMinuteR = packet.getHevChargePlanEndTimeYearMinuteR();
                                        for (AbsBYDAutoChargingListener listener74 : this.mAbsBydAutoChargingListeners) {
                                            listener74.onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    case 557856344:
                                        int getv2gStatusR = packet.getv2gStatusR();
                                        for (AbsBYDAutoChargingListener listener75 : this.mAbsBydAutoChargingListeners) {
                                            listener75.onV2GStatusChanged(getv2gStatusR);
                                        }
                                        break;
                                    case 557856345:
                                        int getv2gChargeStatusR = packet.getv2gChargeStatusR();
                                        for (AbsBYDAutoChargingListener listener76 : this.mAbsBydAutoChargingListeners) {
                                            listener76.onV2GChargeStatusChanged(getv2gChargeStatusR);
                                        }
                                        break;
                                    case 557856346:
                                        int getv2gOutputPowerR = packet.getv2gOutputPowerR();
                                        for (AbsBYDAutoChargingListener listener77 : this.mAbsBydAutoChargingListeners) {
                                            listener77.onV2GOutputPowerChanged(getv2gOutputPowerR);
                                        }
                                        break;
                                    case 557856347:
                                        for (AbsBYDAutoChargingListener listener78 : this.mAbsBydAutoChargingListeners) {
                                            listener78.onElectricPortCapStateChanged(1, packet.getElectricPortCapStateLeftR());
                                        }
                                        break;
                                    case 557856348:
                                        for (AbsBYDAutoChargingListener listener79 : this.mAbsBydAutoChargingListeners) {
                                            listener79.onElectricPortCapStateChanged(2, packet.getElectricPortCapStateRightR());
                                        }
                                        break;
                                    case 557921815:
                                        this.mHevChargePlanEndTimeR = packet.getHevChargePlanEndTimeR();
                                        for (AbsBYDAutoChargingListener listener80 : this.mAbsBydAutoChargingListeners) {
                                            listener80.onHEVChargePlanEndTimeChargeChanged(this.mHevChargePlanEndTimeR);
                                        }
                                        break;
                                    case 559952904:
                                        for (AbsBYDAutoChargingListener listener81 : this.mAbsBydAutoChargingListeners) {
                                            listener81.onChargingPowerChanged(packet.getChargingPowerR());
                                        }
                                        break;
                                    case 559952946:
                                        for (AbsBYDAutoChargingListener listener82 : this.mAbsBydAutoChargingListeners) {
                                            listener82.onVtovDischargeQuantityChanged(packet.getVtovDischargeQuantityR());
                                        }
                                        break;
                                    case 567856339:
                                        this.mHevChargePlanEndTimeYearDayR = packet.getHevChargePlanEndTimeYearDayR();
                                        for (AbsBYDAutoChargingListener listener83 : this.mAbsBydAutoChargingListeners) {
                                            listener83.onHEVChargePlanEndTimeChargeChanged(new int[]{this.mHevChargePlaneEndTimeYearR, this.mHevChargePlanEndTimeMonthR, this.mHevChargePlanEndTimeYearDayR, this.mHevChargePlanEndTimeYearHourR, this.mHevChargePlanEndTimeYearMinuteR});
                                        }
                                        break;
                                    default:
                                        switch (id) {
                                            case 557855771:
                                            case 557855772:
                                                int dischargeHour = packet.getDischargeTimeHourR();
                                                int dischargeMinute = packet.getDischargeTimeMinuteR();
                                                int[] iArr = this.mDisChargeTime;
                                                iArr[0] = dischargeHour;
                                                iArr[1] = dischargeMinute;
                                                for (AbsBYDAutoChargingListener listener84 : this.mAbsBydAutoChargingListeners) {
                                                    listener84.onDischargeTimeChanged(hourMinArray2MillionsSec(this.mDisChargeTime));
                                                }
                                                break;
                                            default:
                                                switch (id) {
                                                    case 557855776:
                                                        for (AbsBYDAutoChargingListener listener85 : this.mAbsBydAutoChargingListeners) {
                                                            listener85.onWirelessChargingSwitchStateChanged(packet.getWirelessChargingSwitchStateR());
                                                        }
                                                        break;
                                                    case 557855777:
                                                        for (AbsBYDAutoChargingListener listener86 : this.mAbsBydAutoChargingListeners) {
                                                            listener86.onWirelessChargingStateChanged(packet.getWirelessChargingStateR());
                                                        }
                                                        break;
                                                    case 557855778:
                                                        for (AbsBYDAutoChargingListener listener87 : this.mAbsBydAutoChargingListeners) {
                                                            listener87.onVtovChargeBatteryLevelNumChanged(packet.getVtovChargeBatteryLevelNumR());
                                                        }
                                                        break;
                                                    default:
                                                        switch (id) {
                                                            case 557855800:
                                                                for (AbsBYDAutoChargingListener listener88 : this.mAbsBydAutoChargingListeners) {
                                                                    listener88.onChargingCapStateChanged(3, packet.getChargingCapStateLeftR());
                                                                }
                                                                break;
                                                            case 557855801:
                                                                for (AbsBYDAutoChargingListener listener89 : this.mAbsBydAutoChargingListeners) {
                                                                    listener89.onChargingCapStateChanged(4, packet.getChargingCapStateRightR());
                                                                }
                                                                break;
                                                            case 557855802:
                                                                for (AbsBYDAutoChargingListener listener90 : this.mAbsBydAutoChargingListeners) {
                                                                    listener90.onChargeTempCtlStateChanged(packet.getChargeTempCtlStateR());
                                                                }
                                                                break;
                                                            default:
                                                                switch (id) {
                                                                    case 557856268:
                                                                        for (AbsBYDAutoChargingListener listener91 : this.mAbsBydAutoChargingListeners) {
                                                                            listener91.onSituPowerGenerationSupportChanged(packet.getSituPowerGenerationSupportR());
                                                                        }
                                                                        break;
                                                                    case 557856269:
                                                                        for (AbsBYDAutoChargingListener listener92 : this.mAbsBydAutoChargingListeners) {
                                                                            listener92.onChargePreheatStateChanged(packet.getChargePreheatStateR());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (id) {
                                                                            case 557856272:
                                                                                for (AbsBYDAutoChargingListener listener93 : this.mAbsBydAutoChargingListeners) {
                                                                                    listener93.onCarPlanStateChanged(packet.getCarPlanStateR());
                                                                                }
                                                                                break;
                                                                            case 557856273:
                                                                                for (AbsBYDAutoChargingListener listener94 : this.mAbsBydAutoChargingListeners) {
                                                                                    listener94.onCarPlanAccOnStateChanged(packet.getCarPlanAccOnStateR());
                                                                                }
                                                                                break;
                                                                            case 557856274:
                                                                                for (AbsBYDAutoChargingListener listener95 : this.mAbsBydAutoChargingListeners) {
                                                                                    listener95.onCarPlanBatteryHeatStateChanged(packet.getCarPlanBatteryHeatStateR());
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (id) {
                                                                                    case 557856288:
                                                                                        byte[] bmsBytes2 = {(byte) packet.getBmsWakeNoSenseCharge1R()};
                                                                                        for (AbsBYDAutoChargingListener listener96 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener96.onBMSWakeNoSenseChargeChanged(bmsBytes2);
                                                                                        }
                                                                                        break;
                                                                                    case 557856289:
                                                                                        byte[] bmsBytes3 = {(byte) packet.getBmsWakeNoSenseCharge2R()};
                                                                                        for (AbsBYDAutoChargingListener listener97 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener97.onBMSWakeNoSenseChargeChanged(bmsBytes3);
                                                                                        }
                                                                                        break;
                                                                                    case 557856290:
                                                                                        byte[] bmsBytes4 = {(byte) packet.getBmsWakeNoSenseCharge3R()};
                                                                                        for (AbsBYDAutoChargingListener listener98 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener98.onBMSWakeNoSenseChargeChanged(bmsBytes4);
                                                                                        }
                                                                                        break;
                                                                                    case 557856291:
                                                                                        byte[] bmsBytes5 = {(byte) packet.getBmsWakeNoSenseCharge4R()};
                                                                                        for (AbsBYDAutoChargingListener listener99 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener99.onBMSWakeNoSenseChargeChanged(bmsBytes5);
                                                                                        }
                                                                                        break;
                                                                                    case 557856292:
                                                                                        byte[] bmsBytes6 = {(byte) packet.getBmsWakeNoSenseCharge5R()};
                                                                                        for (AbsBYDAutoChargingListener listener100 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener100.onBMSWakeNoSenseChargeChanged(bmsBytes6);
                                                                                        }
                                                                                        break;
                                                                                    case 557856293:
                                                                                        byte[] bmsBytes7 = {(byte) packet.getBmsWakeNoSenseCharge6R()};
                                                                                        for (AbsBYDAutoChargingListener listener101 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener101.onBMSWakeNoSenseChargeChanged(bmsBytes7);
                                                                                        }
                                                                                        break;
                                                                                    case 557856294:
                                                                                        byte[] bmsBytes8 = {(byte) packet.getBmsWakeNoSenseCharge7R()};
                                                                                        for (AbsBYDAutoChargingListener listener102 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener102.onBMSWakeNoSenseChargeChanged(bmsBytes8);
                                                                                        }
                                                                                        break;
                                                                                    case 557856295:
                                                                                        byte[] bmsBytes9 = {(byte) packet.getBmsWakeNoSenseCharge8R()};
                                                                                        for (AbsBYDAutoChargingListener listener103 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener103.onBMSWakeNoSenseChargeChanged(bmsBytes9);
                                                                                        }
                                                                                        break;
                                                                                    case 557856296:
                                                                                        byte[] bmsBytes10 = {(byte) packet.getBmsWakeNoSenseCharge9R()};
                                                                                        for (AbsBYDAutoChargingListener listener104 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener104.onBMSWakeNoSenseChargeChanged(bmsBytes10);
                                                                                        }
                                                                                        break;
                                                                                    case 557856297:
                                                                                        byte[] bmsBytes11 = {(byte) packet.getBmsWakeNoSenseCharge10R()};
                                                                                        for (AbsBYDAutoChargingListener listener105 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener105.onBMSWakeNoSenseChargeChanged(bmsBytes11);
                                                                                        }
                                                                                        break;
                                                                                    case 557856298:
                                                                                        byte[] bmsBytes12 = {(byte) packet.getBmsWakeNoSenseCharge11R()};
                                                                                        for (AbsBYDAutoChargingListener listener106 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener106.onBMSWakeNoSenseChargeChanged(bmsBytes12);
                                                                                        }
                                                                                        break;
                                                                                    case 557856299:
                                                                                        byte[] bmsBytes13 = {(byte) packet.getBmsWakeNoSenseCharge12R()};
                                                                                        for (AbsBYDAutoChargingListener listener107 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener107.onBMSWakeNoSenseChargeChanged(bmsBytes13);
                                                                                        }
                                                                                        break;
                                                                                    case 557856300:
                                                                                        byte[] bmsBytes14 = {(byte) packet.getBmsWakeNoSenseCharge13R()};
                                                                                        for (AbsBYDAutoChargingListener listener108 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener108.onBMSWakeNoSenseChargeChanged(bmsBytes14);
                                                                                        }
                                                                                        break;
                                                                                    case 557856301:
                                                                                        byte[] bmsBytes15 = {(byte) packet.getBmsWakeNoSenseCharge14R()};
                                                                                        for (AbsBYDAutoChargingListener listener109 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener109.onBMSWakeNoSenseChargeChanged(bmsBytes15);
                                                                                        }
                                                                                        break;
                                                                                    case 557856302:
                                                                                        byte[] bmsBytes16 = {(byte) packet.getBmsWakeNoSenseCharge15R()};
                                                                                        for (AbsBYDAutoChargingListener listener110 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener110.onBMSWakeNoSenseChargeChanged(bmsBytes16);
                                                                                        }
                                                                                        break;
                                                                                    case 557856303:
                                                                                        byte[] bmsBytes17 = {(byte) packet.getBmsWakeNoSenseCharge16R()};
                                                                                        for (AbsBYDAutoChargingListener listener111 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener111.onBMSWakeNoSenseChargeChanged(bmsBytes17);
                                                                                        }
                                                                                        break;
                                                                                    case 557856304:
                                                                                        byte[] bmsBytes18 = {(byte) packet.getBmsWakeNoSenseCharge17R()};
                                                                                        for (AbsBYDAutoChargingListener listener112 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener112.onBMSWakeNoSenseChargeChanged(bmsBytes18);
                                                                                        }
                                                                                        break;
                                                                                    case 557856305:
                                                                                        byte[] bmsBytes19 = {(byte) packet.getBmsWakeNoSenseCharge18R()};
                                                                                        for (AbsBYDAutoChargingListener listener113 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener113.onBMSWakeNoSenseChargeChanged(bmsBytes19);
                                                                                        }
                                                                                        break;
                                                                                    case 557856306:
                                                                                        byte[] bmsBytes20 = {(byte) packet.getBmsWakeNoSenseCharge19R()};
                                                                                        for (AbsBYDAutoChargingListener listener114 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener114.onBMSWakeNoSenseChargeChanged(bmsBytes20);
                                                                                        }
                                                                                        break;
                                                                                    case 557856307:
                                                                                        byte[] bmsBytes21 = {(byte) packet.getBmsWakeNoSenseCharge20R()};
                                                                                        for (AbsBYDAutoChargingListener listener115 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener115.onBMSWakeNoSenseChargeChanged(bmsBytes21);
                                                                                        }
                                                                                        break;
                                                                                    case 557856308:
                                                                                        byte[] bmsBytes22 = {(byte) packet.getBmsWakeNoSenseCharge21R()};
                                                                                        for (AbsBYDAutoChargingListener listener116 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener116.onBMSWakeNoSenseChargeChanged(bmsBytes22);
                                                                                        }
                                                                                        break;
                                                                                    case 557856309:
                                                                                        byte[] bmsBytes23 = {(byte) packet.getBmsWakeNoSenseCharge22R()};
                                                                                        for (AbsBYDAutoChargingListener listener117 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener117.onBMSWakeNoSenseChargeChanged(bmsBytes23);
                                                                                        }
                                                                                        break;
                                                                                    case 557856310:
                                                                                        byte[] bmsBytes24 = {(byte) packet.getBmsWakeNoSenseCharge23R()};
                                                                                        for (AbsBYDAutoChargingListener listener118 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener118.onBMSWakeNoSenseChargeChanged(bmsBytes24);
                                                                                        }
                                                                                        break;
                                                                                    case 557856311:
                                                                                        byte[] bmsBytes25 = {(byte) packet.getBmsWakeNoSenseCharge24R()};
                                                                                        for (AbsBYDAutoChargingListener listener119 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener119.onBMSWakeNoSenseChargeChanged(bmsBytes25);
                                                                                        }
                                                                                        break;
                                                                                    case 557856312:
                                                                                        byte[] bmsBytes26 = {(byte) packet.getBmsWakeNoSenseCharge25R()};
                                                                                        for (AbsBYDAutoChargingListener listener120 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener120.onBMSWakeNoSenseChargeChanged(bmsBytes26);
                                                                                        }
                                                                                        break;
                                                                                    case 557856313:
                                                                                        byte[] bmsBytes27 = {(byte) packet.getBmsWakeNoSenseCharge26R()};
                                                                                        for (AbsBYDAutoChargingListener listener121 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener121.onBMSWakeNoSenseChargeChanged(bmsBytes27);
                                                                                        }
                                                                                        break;
                                                                                    case 557856314:
                                                                                        byte[] bmsBytes28 = {(byte) packet.getBmsWakeNoSenseCharge27R()};
                                                                                        for (AbsBYDAutoChargingListener listener122 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener122.onBMSWakeNoSenseChargeChanged(bmsBytes28);
                                                                                        }
                                                                                        break;
                                                                                    case 557856315:
                                                                                        byte[] bmsBytes29 = {(byte) packet.getBmsWakeNoSenseCharge28R()};
                                                                                        for (AbsBYDAutoChargingListener listener123 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener123.onBMSWakeNoSenseChargeChanged(bmsBytes29);
                                                                                        }
                                                                                        break;
                                                                                    case 557856316:
                                                                                        byte[] bmsBytes30 = {(byte) packet.getBmsWakeNoSenseCharge29R()};
                                                                                        for (AbsBYDAutoChargingListener listener124 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener124.onBMSWakeNoSenseChargeChanged(bmsBytes30);
                                                                                        }
                                                                                        break;
                                                                                    case 557856317:
                                                                                        byte[] bmsBytes31 = {(byte) packet.getBmsWakeNoSenseCharge30R()};
                                                                                        for (AbsBYDAutoChargingListener listener125 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener125.onBMSWakeNoSenseChargeChanged(bmsBytes31);
                                                                                        }
                                                                                        break;
                                                                                    case 557856318:
                                                                                        byte[] bmsBytes32 = {(byte) packet.getBmsWakeNoSenseCharge31R()};
                                                                                        for (AbsBYDAutoChargingListener listener126 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener126.onBMSWakeNoSenseChargeChanged(bmsBytes32);
                                                                                        }
                                                                                        break;
                                                                                    case 557856319:
                                                                                        byte[] bmsBytes33 = {(byte) packet.getBmsWakeNoSenseCharge32R()};
                                                                                        for (AbsBYDAutoChargingListener listener127 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener127.onBMSWakeNoSenseChargeChanged(bmsBytes33);
                                                                                        }
                                                                                        break;
                                                                                    case 557856320:
                                                                                        byte[] bmsBytes34 = {(byte) packet.getBmsWakeNoSenseCharge33R()};
                                                                                        for (AbsBYDAutoChargingListener listener128 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener128.onBMSWakeNoSenseChargeChanged(bmsBytes34);
                                                                                        }
                                                                                        break;
                                                                                    case 557856321:
                                                                                        byte[] bmsBytes35 = {(byte) packet.getBmsWakeNoSenseCharge34R()};
                                                                                        for (AbsBYDAutoChargingListener listener129 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener129.onBMSWakeNoSenseChargeChanged(bmsBytes35);
                                                                                        }
                                                                                        break;
                                                                                    case 557856322:
                                                                                        byte[] bmsBytes352 = {(byte) packet.getBmsWakeNoSenseCharge35R()};
                                                                                        for (AbsBYDAutoChargingListener listener130 : this.mAbsBydAutoChargingListeners) {
                                                                                            listener130.onBMSWakeNoSenseChargeChanged(bmsBytes352);
                                                                                        }
                                                                                        break;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    switch (eventType) {
                        case 2019:
                            bydAutoEventValue.intValue = getCarPlanBatteryHeatState();
                            sparseArray.put(2019, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2021:
                            bydAutoEventValue.intValue = getCarPlanAccOnState();
                            sparseArray.put(2021, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2022:
                            bydAutoEventValue.intValue = getCarPlanState();
                            sparseArray.put(2022, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2516:
                            bydAutoEventValue.intValue = getBatteryManagementDeviceState();
                            sparseArray.put(2516, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2519:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getChargingAdapterManager().getChargingGunState();
                            sparseArray.put(2519, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2535:
                            bydAutoEventValue.intValue = getChargerState();
                            sparseArray.put(2535, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2536:
                            bydAutoEventValue.intValue = getChargerFaultState();
                            sparseArray.put(2536, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2547:
                            bydAutoEventValue.floatValue = getChargingPower();
                            sparseArray.put(2547, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2554:
                            bydAutoEventValue.intValue = getChargeTempCtlState();
                            sparseArray.put(2554, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2565:
                            bydAutoEventValue.intValue = getWirelessChargingState();
                            sparseArray.put(2565, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2566:
                            bydAutoEventValue.intValue = getWirelessChargingSwitchState();
                            sparseArray.put(2566, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2571:
                            bydAutoEventValue.intValue = getDischargeRequestState();
                            sparseArray.put(2571, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2576:
                            bydAutoEventValue.intValue = getMovePowerStationToggle();
                            sparseArray.put(2576, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2578:
                            bydAutoEventValue.intValue = getVtovDischargeLowPowerHint();
                            sparseArray.put(2578, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2584:
                            int[] restTime = getChargingRestTime();
                            if (restTime.length == 2) {
                                bydAutoEventValue.intValue = restTime[0];
                            }
                            sparseArray.put(2584, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2585:
                            int[] restTime2 = getChargingRestTime();
                            if (restTime2.length == 2) {
                                bydAutoEventValue.intValue = restTime2[1];
                            }
                            sparseArray.put(2585, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2588:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getChargingAdapterManager().getChargingGunState();
                            sparseArray.put(2588, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2592:
                            bydAutoEventValue.intValue = getSituPowerGenerationSupport();
                            sparseArray.put(2592, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2594:
                            bydAutoEventValue.intValue = getSituPowerGenerationState();
                            sparseArray.put(2594, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2596:
                            bydAutoEventValue.intValue = getPortCapState(1);
                            sparseArray.put(2596, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2601:
                            bydAutoEventValue.intValue = getPorducePowerWhenLowPower();
                            sparseArray.put(2601, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2619:
                            bydAutoEventValue.intValue = getNoSenseChargeState();
                            sparseArray.put(2619, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2628:
                            bydAutoEventValue.intValue = getPortCapState(2);
                            sparseArray.put(2628, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2634:
                            bydAutoEventValue.intValue = getChargingScheduleEnableState();
                            sparseArray.put(2634, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2669:
                            bydAutoEventValue.intValue = getChargeStopCapacityState();
                            sparseArray.put(2669, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2733:
                            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                                int[] dischargeTime = this.mTsManagerImpl.getChargingAdapterManager().getDischargeTime();
                                if (dischargeTime.length == 2) {
                                    bydAutoEventValue.intValue = dischargeTime[0];
                                }
                            }
                            sparseArray.put(2733, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2735:
                            if (this.mTsManagerImpl.getChargingAdapterManager() != null) {
                                int[] dischargeTime2 = this.mTsManagerImpl.getChargingAdapterManager().getDischargeTime();
                                if (dischargeTime2.length == 2) {
                                    bydAutoEventValue.intValue = dischargeTime2[1];
                                }
                            }
                            sparseArray.put(2735, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2738:
                            bydAutoEventValue.intValue = getDischargeToggle();
                            sparseArray.put(2738, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2740:
                            bydAutoEventValue.intValue = getVtovDischargeConnectState();
                            sparseArray.put(2740, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2742:
                            bydAutoEventValue.floatValue = getVtovDischargeQuantity();
                            sparseArray.put(2742, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2748:
                            bydAutoEventValue.intValue = getVtovDischargeLimitVal();
                            sparseArray.put(2748, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2749:
                            bydAutoEventValue.intValue = getVtovDischargeLowestVal();
                            sparseArray.put(2749, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
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
        for (AbsBYDAutoChargingListener listener : this.mAbsBydAutoChargingListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue;
        if (value instanceof Integer) {
            int intValue2 = ((Integer) value).intValue();
            intValue = intValue2;
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
            intValue = 0;
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
            intValue = 0;
        } else if (value instanceof byte[]) {
            intValue = 0;
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        if (eventType == 2736) {
            setDischargeTime(intValue);
            return 1;
        }
        if (eventType != 2737) {
            switch (eventType) {
                case 127:
                    setSyncTime(this.syncTimeYear, this.syncTimeMonth, intValue, this.syncTimeHour, this.syncTimeMinute);
                    return 1;
                case 135:
                    setSyncTime(this.syncTimeYear, this.syncTimeMonth, this.syncTimeDay, intValue, this.syncTimeMinute);
                    return 1;
                case 139:
                    setSyncTime(this.syncTimeYear, this.syncTimeMonth, this.syncTimeDay, this.syncTimeHour, intValue);
                    return 1;
                case 143:
                    setSyncTime(this.syncTimeYear, intValue, this.syncTimeDay, this.syncTimeHour, this.syncTimeMinute);
                    return 1;
                case 162:
                    setSyncTime(intValue, this.syncTimeMonth, this.syncTimeDay, this.syncTimeHour, this.syncTimeMinute);
                    return 1;
                case 2496:
                    this.evDayEnd = intValue;
                    setEvChargePlanEndTime(this.evYearEnd, this.evMonthEnd, intValue, this.evHourEnd, this.evMinuteEnd);
                    Log.v(TAG, "setEvChargePlanEndTimeDay" + intValue);
                    return 1;
                case 2498:
                    this.evHourEnd = intValue;
                    setEvChargePlanEndTime(this.evYearEnd, this.evMonthEnd, this.evDayEnd, intValue, this.evMinuteEnd);
                    Log.v(TAG, "setEvChargePlanEndTimeHour" + intValue);
                    return 1;
                case 2500:
                    this.evMinuteEnd = intValue;
                    setEvChargePlanEndTime(this.evYearEnd, this.evMonthEnd, this.evDayEnd, this.evHourEnd, intValue);
                    Log.v(TAG, "setEvChargePlanEndTimeMinute" + intValue);
                    return 1;
                case 2502:
                    this.evMonthEnd = intValue;
                    setEvChargePlanEndTime(this.evYearEnd, intValue, this.evDayEnd, this.evHourEnd, this.evMinuteEnd);
                    Log.v(TAG, "setEvChargePlanEndTimeMonth" + intValue);
                    return 1;
                case 2504:
                    this.evYearEnd = intValue;
                    setEvChargePlanEndTime(intValue, this.evMonthEnd, this.evDayEnd, this.evHourEnd, this.evMinuteEnd);
                    Log.v(TAG, "setEvChargePlanEndTimeYear" + intValue);
                    return 1;
                case 2507:
                    setEvChargePlanStartTime(this.evYear, this.evMonth, intValue, this.evHour, this.evMinute);
                    return 1;
                case 2509:
                    setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, intValue, this.evMinute);
                    return 1;
                case 2511:
                    setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, this.evHour, intValue);
                    return 1;
                case 2513:
                    this.evMonth = intValue;
                    setEvChargePlanStartTime(this.evYear, intValue, this.evDay, this.evHour, this.evMinute);
                    Log.v(TAG, "setEVChargePlanStartTimeMonth" + intValue);
                    return 1;
                case 2515:
                    this.evYear = intValue;
                    setEvChargePlanStartTime(intValue, this.evMonth, this.evDay, this.evHour, this.evMinute);
                    Log.v(TAG, "setEVChargePlanStartTimeYear" + intValue);
                    return 1;
                case 2543:
                    setHevChargingType(intValue);
                    return 1;
                case 2567:
                    setWirelessChargingSwitchState(intValue);
                    return 1;
                case 2577:
                    setMovePowerStationToggle(intValue);
                    return 1;
                case 2593:
                    setSituPowerGenerationState(intValue);
                    return 1;
                case 2602:
                    setPorducePowerWhenLowPower(intValue);
                    return 1;
                case 2620:
                    setNoSenseChargeState(intValue);
                    return 1;
                case 2670:
                    setChargeStopCapacityState(intValue);
                    return 1;
                case 2722:
                    setChargePlanState(intValue);
                    Log.v(TAG, "setChargePlanState" + intValue);
                    return 1;
                case 2734:
                    setDischargeTime(intValue);
                    return 1;
                case 2745:
                    setDischargeLimit(intValue);
                    return 1;
                default:
                    switch (eventType) {
                        case 2529:
                            setCarPlan(0, 0, 0, this.carPlanYear, this.carPlanMonth, intValue, this.carPlanHour, this.carPlanMinute);
                            return 1;
                        case 2530:
                            setCarPlan(0, 0, 0, this.carPlanYear, this.carPlanMonth, this.carPlanDay, intValue, this.carPlanMinute);
                            return 1;
                        case 2531:
                            setCarPlan(0, 0, 0, this.carPlanYear, this.carPlanMonth, this.carPlanDay, this.carPlanHour, intValue);
                            return 1;
                        case 2532:
                            setCarPlan(0, 0, 0, this.carPlanYear, intValue, this.carPlanDay, this.carPlanHour, this.carPlanMinute);
                            return 1;
                        case 2533:
                            setCarPlanState(intValue);
                            return 1;
                        case 2534:
                            setCarPlan(0, 0, 0, intValue, this.carPlanMonth, this.carPlanDay, this.carPlanHour, this.carPlanMinute);
                            return 1;
                        default:
                            switch (eventType) {
                                case 2635:
                                    this.dmDayEnd = intValue;
                                    setHevChargePlanEndTime(this.dmYearEnd, this.dmMonthEnd, intValue, this.dmHourEnd, this.dmMinuteEnd);
                                    Log.v(TAG, "setHevChargePlanEndTimeDay" + intValue);
                                    return 1;
                                case 2636:
                                    this.dmHourEnd = intValue;
                                    setHevChargePlanEndTime(this.dmYearEnd, this.dmMonthEnd, this.dmDayEnd, intValue, this.dmMinuteEnd);
                                    Log.v(TAG, "setHevChargePlanEndTimeHour" + intValue);
                                    return 1;
                                case 2637:
                                    this.dmMinuteEnd = intValue;
                                    setHevChargePlanEndTime(this.dmYearEnd, this.dmMonthEnd, this.dmDayEnd, this.dmHourEnd, intValue);
                                    Log.v(TAG, "setHevChargePlanEndTimeMinute" + intValue);
                                    return 1;
                                case 2638:
                                    this.dmMonthEnd = intValue;
                                    setHevChargePlanEndTime(this.dmYearEnd, intValue, this.dmDayEnd, this.dmHourEnd, this.dmMinuteEnd);
                                    Log.v(TAG, "setHevChargePlanEndTimeMonth" + intValue);
                                    return 1;
                                case 2639:
                                    this.dmYearEnd = intValue;
                                    setHevChargePlanEndTime(intValue, this.dmMonthEnd, this.dmDayEnd, this.dmHourEnd, this.dmMinuteEnd);
                                    Log.v(TAG, "setHevChargePlanEndTimeYear" + intValue);
                                    return 1;
                                case 2640:
                                    this.evDay = intValue;
                                    this.dmDay = intValue;
                                    int i = intValue;
                                    setEvChargePlanStartTime(this.evYear, this.evMonth, i, this.evHour, this.evMinute);
                                    setHevChargePlanStartTime(this.dmYear, this.dmMonth, i, this.dmHour, this.dmMinute);
                                    Log.v(TAG, "setEVChargePlanStartTimeDay" + intValue);
                                    return 1;
                                case 2641:
                                    this.evHour = intValue;
                                    this.dmHour = intValue;
                                    int i2 = intValue;
                                    setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, i2, this.evMinute);
                                    setHevChargePlanStartTime(this.dmYear, this.dmMonth, this.dmDay, i2, this.dmMinute);
                                    Log.v(TAG, "setEVChargePlanStartTimeHour" + intValue);
                                    return 1;
                                case 2642:
                                    this.evMinute = intValue;
                                    this.dmMinute = intValue;
                                    int i3 = intValue;
                                    setEvChargePlanStartTime(this.evYear, this.evMonth, this.evDay, this.evHour, i3);
                                    setHevChargePlanStartTime(this.dmYear, this.dmMonth, this.dmDay, this.dmHour, i3);
                                    Log.v(TAG, "setEVChargePlanStartTimeMinute" + intValue);
                                    return 1;
                                case 2643:
                                    this.dmMonth = intValue;
                                    setHevChargePlanStartTime(this.dmYear, intValue, this.dmDay, this.dmHour, this.dmMinute);
                                    Log.v(TAG, "setHevChargePlanStartTimeMonth" + intValue);
                                    return 1;
                                case 2644:
                                    this.dmYear = intValue;
                                    setHevChargePlanStartTime(intValue, this.dmMonth, this.dmDay, this.dmHour, this.dmMinute);
                                    Log.v(TAG, "setHevChargePlanStartTimeYear" + intValue);
                                    return 1;
                                default:
                                    Log.v(TAG, "case fail ! please check out");
                                    return -1;
                            }
                    }
            }
        }
        setDischargeToggle(intValue);
        return 1;
    }
}
