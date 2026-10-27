package android.hardware.bydauto.power.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.util.Log;
import android.util.SparseArray;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PowerManagerImpl {
    public static final int BODYWORK_POWER_LEVEL_ACC = 1;
    public static final int BODYWORK_POWER_LEVEL_INVALID = 255;
    public static final int BODYWORK_POWER_LEVEL_OFF = 0;
    public static final int BODYWORK_POWER_LEVEL_ON = 2;
    private static final int INDEX_CLUSTER_OSD_DISPLAY_OFF = 0;
    private static final int INDEX_CLUSTER_OSD_DISPLAY_ON = 1;
    public static final int S_CONSTANT_THREE = 3;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_PowerManagerImpl";
    private static final String TAG = "PowerManagerImpl";
    private static PowerManagerImpl sInstance;
    private CarPowerManager mCarPowerManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private TestPowerManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 0;
    private final List<AbsBYDAutoPowerListener> mlisteners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private CarPowerEventListener mCarPowerEventListener = new CarPowerEventListener() { // from class: android.hardware.bydauto.power.tsimpl.PowerManagerImpl.1
        public void onCarPowerWorkModeChangeEvent(com.ts.lib.power.data.CarPowerWorkModeStatus carPowerWorkModeStatus) {
            Log.w(PowerManagerImpl.TAG, "onCarPowerWorkModeChangeEvent " + carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal() + " size:" + PowerManagerImpl.this.mlisteners.size());
            for (AbsBYDAutoPowerListener listener : PowerManagerImpl.this.mlisteners) {
                try {
                    listener.onPowerSTRChanged(carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal());
                } catch (Exception exception) {
                    Log.e(PowerManagerImpl.TAG, "onPowerSTRChanged " + exception.getMessage());
                }
            }
        }

        public void onCarPowerSignalChangeEvent(CarPowerSignalStatus carPowerSignalStatus) {
            PowerManagerImpl.this.handlePowerSignalEvent(carPowerSignalStatus);
        }

        public void onCarPowerServiceConnected() {
        }

        public void onCarPowerServiceDisconnected() {
        }

        public void onCarPowerEvent(int i, int i1, CarPowerData carPowerData) {
        }

        public void onCarPowerDisplayEvent(int type, int displayPanelId, int value) {
            Log.e(PowerManagerImpl.TAG, " onCarPowerDisplayEvent type : " + type + " displayPanelId : " + displayPanelId + " value : " + value);
            PowerManagerImpl.this.handleDisplayEvent(type, displayPanelId, value);
        }
    };

    PowerManagerImpl(Context con) {
        this.mContentResolver = con.getContentResolver();
        this.mContext = con;
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (this.mContentResolver != null && 1 == this.mForTest) {
            Log.v(TAG, "OtaManagerImpl mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback = new TestPowerManagerImplCallback();
            return;
        }
        this.mTsManagerImpl = TsManagerImpl.getInstance(this.mContext);
        CarPowerManager carPowerManager = CarPowerManager.getInstance(con, this.mCarPowerEventListener);
        this.mCarPowerManager = carPowerManager;
        carPowerManager.connect();
    }

    public static synchronized PowerManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PowerManagerImpl(context);
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePowerSignalEvent(CarPowerSignalStatus carPowerSignalStatus) {
        int value = getBydAccState(carPowerSignalStatus);
        Log.e(TAG, " handlePowerSignalEvent carPowerSignalStatus value : " + value);
        this.mTsManagerImpl.checkServiceConnection();
        for (AbsBYDAutoPowerListener listener : this.mlisteners) {
            listener.onPowerLevelChanged(value);
        }
    }

    private int getBydAccState(CarPowerSignalStatus carPowerSignalStatus) {
        int getNum;
        if (carPowerSignalStatus == null || carPowerSignalStatus.getCarPowerSignalStatusEnum() == null || (getNum = carPowerSignalStatus.getCarPowerSignalStatusEnum().getVal()) == 0) {
            return 255;
        }
        if (getNum == 1) {
            return 0;
        }
        if (getNum == 2) {
            return 1;
        }
        if (getNum == 3) {
            return 2;
        }
        return getNum;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDisplayEvent(int type, int displayPanelId, int value) {
        if (type == 2 && displayPanelId == 1) {
            for (AbsBYDAutoPowerListener listener : this.mlisteners) {
                listener.onTftBacklightChanged(value);
            }
        }
    }

    public void trigerTestPowerManagerImplCallback(int what, Object para1object) {
        Log.d(TAG, "trigerTestPowerManagerImplCallback is execute");
        this.mTestCallback.enqueueMessage(what, para1object);
    }

    public void registerListener(AbsBYDAutoPowerListener listener) {
        Log.d(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        registerListener(listener, null);
    }

    public void registerListener(AbsBYDAutoPowerListener listener, int[] featureIDs) {
        Log.d(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoPowerListener(listener);
        } else {
            if (listener == null) {
                Log.e(TAG, "registerListener listener is null");
                return;
            }
            synchronized (this.mlisteners) {
                if (this.mlisteners.contains(listener)) {
                    Log.d(TAG, "registerListener listener is contained");
                } else {
                    this.mlisteners.add(listener);
                    Log.i(TAG, "registerListener add new listener");
                }
            }
        }
    }

    public void unregisterListener(AbsBYDAutoPowerListener listener) {
        Log.d(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoPowerListener(null);
        } else {
            if (listener == null) {
                Log.e(TAG, "unregisterListener listener is null");
                return;
            }
            synchronized (this.mlisteners) {
                if (this.mlisteners.contains(listener)) {
                    this.mlisteners.remove(listener);
                    Log.i(TAG, "unregisterListener remove listener");
                }
            }
        }
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getDevicetype mForTestValue is 0 execute return");
        return -1;
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return "ForTest";
        }
        Log.e(TAG, " getSetPermission mForTestValue is 0 execute return");
        return "ForTest";
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return "ForTest";
        }
        Log.e(TAG, " getGetPermission mForTestValue is 0 execute return");
        return "ForTest";
    }

    public int setPowerCtlStatus(int event_type, int value) {
        Log.v(TAG, "setPowerCtlStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " setPowerCtlStatus mForTestValue is 0 execute return");
        return -1;
    }

    public int dspReset() {
        Log.v(TAG, "dspReset is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " dspReset mForTestValue is 0 execute return");
        return -1;
    }

    public int mcuReset() {
        Log.v(TAG, "mcuReset is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return this.mCarPowerManager.mcuReset() ? 0 : -1;
        }
        Log.e(TAG, " mcuReset mForTestValue is 0 execute return");
        return -1;
    }

    public int getBackLightState() {
        Log.v(TAG, "getBackLightState is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBackLightState mForTestValue is 0 execute return");
            return 0;
        }
        return this.mCarPowerManager.getBackLightState();
    }

    public boolean setBackLightState(int state, int type) {
        Log.v(TAG, "setBackLightState is execute state :" + state + "type:" + type);
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " setBackLightState mForTestValue is 0 execute return");
            return false;
        }
        return this.mCarPowerManager.setBackLightState(state, type);
    }

    public int qcomWakeup(int reason) {
        Log.v(TAG, "qcomWakeup is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " qcomWakeup mForTestValue is 0 execute return");
        return -1;
    }

    public int padReset() {
        Log.v(TAG, "padReset is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " padReset mForTestValue is 0 execute return");
        return -1;
    }

    public int getBatteryRemainPowerEV() {
        Log.v(TAG, "getBatteryRemainPowerEV is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getBatteryRemainPowerEV mForTestValue is 0 execute return");
        return -1;
    }

    public int getPowerCtlStatus(int event_type) {
        Log.v(TAG, "getPowerCtlStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getPowerCtlStatus mForTestValue is 0 execute return");
        return -1;
    }

    public int setTpDisplayController(int value) {
        Log.v(TAG, "setTpDisplayController is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " setTpDisplayController mForTestValue is 0 execute return");
        return -1;
    }

    public int getTpDisplayController() {
        Log.v(TAG, "getTpDisplayController is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getTpDisplayController mForTestValue is 0 execute return");
        return -1;
    }

    public int setTftBacklight(int value) {
        Log.v(TAG, "setTftBacklight is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            this.mCarPowerManager.setDisplayPowerState(1, value);
            Log.e(TAG, " setTftBacklight mForTestValue is 0 execute return");
            return -1;
        }
        this.mCarPowerManager.setDisplayPowerState(1, value);
        return -1;
    }

    public int getTftBacklight() {
        Log.v(TAG, "getTftBacklight is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getTftBacklight mForTestValue is 0 execute return");
        return -1;
    }

    public int wakeUpMcu() {
        Log.v(TAG, "wakeUpMcu is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " wakeUpMcu mForTestValue is 0 execute return");
        return -1;
    }

    public int getMcuStatus() {
        Log.v(TAG, "getMcuStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getMcuStatus mForTestValue is 0 execute return");
        return -1;
    }

    public int setBCMPowerOnLevel() {
        Log.v(TAG, "setBCMPowerOnLevel is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " setBCMPowerOnLevel mForTestValue is 0 execute return");
        return -1;
    }

    public int getShutdownInfo(int device) {
        Log.v(TAG, "getShutdownInfo is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getShutdownInfo mForTestValue is 0 execute return");
        return -1;
    }

    public int sendQcomRebootState(int state) {
        Log.v(TAG, "sendQcomRebootState is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " sendQcomRebootState mForTestValue is 0 execute return");
        return -1;
    }

    public int getBatteryLowVoltageState() {
        Log.v(TAG, "getBatteryLowVoltageState is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getBatteryLowVoltageState mForTestValue is 0 execute return");
        return -1;
    }

    public void setAllStatus() {
        Log.v(TAG, "setAllStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAllStatus mForTestValue is 0 execute return");
        }
    }

    public void getAllStatus() {
        Log.v(TAG, "getAllStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAllStatus mForTestValue is 0 execute return");
        }
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        Log.v(TAG, "postEvent is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            return false;
        }
        Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        return false;
    }

    public int getType() {
        Log.v(TAG, "getType is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return -1;
        }
        Log.e(TAG, " getType mForTestValue is 0 execute return");
        return -1;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getFeatureList mForTestValue is 0 execute return");
            return new int[]{0, 0};
        }
        return new int[]{0, 0};
    }

    public int getPowerLevel() {
        Log.v(TAG, "getPowerLevel is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getType mForTestValue is 0 execute return");
            return -1;
        }
        CarPowerSignalStatus signalStatus = this.mCarPowerManager.getCurrentSignalState();
        if (signalStatus == null) {
            Log.e(TAG, " signalStatus is null");
            return -1;
        }
        return getBydAccState(signalStatus);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        Log.v(TAG, "get is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 2266) {
                        bydAutoEventValue.intValue = getMcuStatus();
                        sparseArray.put(2266, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 2267) {
                        bydAutoEventValue.intValue = wakeUpMcu();
                        sparseArray.put(2267, Integer.valueOf(bydAutoEventValue.intValue));
                    } else {
                        Log.v(TAG, "case fail ! please check out");
                    }
                    int index = 0;
                    for (int i : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                            index++;
                        } catch (NullPointerException errors) {
                            errors.printStackTrace();
                        }
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    public void sendInfo(int index) {
        Log.v(TAG, "sendInfo index :" + index);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendInfo mForTestValue is 0 execute return");
            return;
        }
        if (index == 0) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 0);
        } else if (index == 1) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 1);
        } else {
            Log.v(TAG, "sendInfo not 0/1 return");
        }
    }
}
