package android.hardware.bydauto.power.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.util.Log;
import android.util.SparseArray;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;
import java.util.ArrayList;
import java.util.Iterator;
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
    private int mForTest = 0;
    private final List<AbsBYDAutoPowerListener> mlisteners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private CarPowerEventListener mCarPowerEventListener = new CarPowerEventListener() { // from class: android.hardware.bydauto.power.tsimpl.PowerManagerImpl.1
        public void onCarPowerDisplayEvent(int i2, int i3, int i4) {
            Log.e(PowerManagerImpl.TAG, " onCarPowerDisplayEvent type : " + i2 + " displayPanelId : " + i3 + " value : " + i4);
            PowerManagerImpl.this.handleDisplayEvent(i2, i3, i4);
        }

        public void onCarPowerEvent(int i2, int i3, CarPowerData carPowerData) {
        }

        public void onCarPowerServiceConnected() {
        }

        public void onCarPowerServiceDisconnected() {
        }

        public void onCarPowerSignalChangeEvent(CarPowerSignalStatus carPowerSignalStatus) {
            PowerManagerImpl.this.handlePowerSignalEvent(carPowerSignalStatus);
        }

        public void onCarPowerWorkModeChangeEvent(com.ts.lib.power.data.CarPowerWorkModeStatus carPowerWorkModeStatus) {
            Log.w(PowerManagerImpl.TAG, "onCarPowerWorkModeChangeEvent " + carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal() + " size:" + PowerManagerImpl.this.mlisteners.size());
            Iterator it = PowerManagerImpl.this.mlisteners.iterator();
            while (it.hasNext()) {
                try {
                    ((AbsBYDAutoPowerListener) it.next()).onPowerSTRChanged(carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal());
                } catch (Exception e2) {
                    Log.e(PowerManagerImpl.TAG, "onPowerSTRChanged " + e2.getMessage());
                }
            }
        }
    };

    public PowerManagerImpl(Context context) {
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (this.mContentResolver == null || 1 != this.mForTest) {
            CarPowerManager carPowerManager = CarPowerManager.getInstance(context, this.mCarPowerEventListener);
            this.mCarPowerManager = carPowerManager;
            carPowerManager.connect();
        } else {
            Log.v(TAG, "OtaManagerImpl mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback = new TestPowerManagerImplCallback();
        }
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    private int getBydAccState(CarPowerSignalStatus carPowerSignalStatus) {
        int val;
        if (carPowerSignalStatus == null || carPowerSignalStatus.getCarPowerSignalStatusEnum() == null || (val = carPowerSignalStatus.getCarPowerSignalStatusEnum().getVal()) == 0) {
            return 255;
        }
        if (val == 1) {
            return 0;
        }
        if (val == 2) {
            return 1;
        }
        if (val == 3) {
            return 2;
        }
        return val;
    }

    public static synchronized PowerManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PowerManagerImpl(context);
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDisplayEvent(int i2, int i3, int i4) {
        if (i2 == 2 && i3 == 1) {
            Iterator<AbsBYDAutoPowerListener> it = this.mlisteners.iterator();
            while (it.hasNext()) {
                it.next().onTftBacklightChanged(i4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePowerSignalEvent(CarPowerSignalStatus carPowerSignalStatus) {
        int bydAccState = getBydAccState(carPowerSignalStatus);
        Log.e(TAG, " handlePowerSignalEvent carPowerSignalStatus value : " + bydAccState);
        Iterator<AbsBYDAutoPowerListener> it = this.mlisteners.iterator();
        while (it.hasNext()) {
            it.next().onPowerLevelChanged(bydAccState);
        }
    }

    public int dspReset() {
        Log.v(TAG, "dspReset is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " dspReset mForTestValue is 0 execute return");
        }
        return -1;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        Log.v(TAG, "get is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i2 : iArr) {
                if (i2 > 0) {
                    if (i2 == 2266) {
                        int mcuStatus = getMcuStatus();
                        bYDAutoEventValue.intValue = mcuStatus;
                        sparseArray.put(2266, Integer.valueOf(mcuStatus));
                    } else if (i2 != 2267) {
                        Log.v(TAG, "case fail ! please check out");
                    } else {
                        int iWakeUpMcu = wakeUpMcu();
                        bYDAutoEventValue.intValue = iWakeUpMcu;
                        sparseArray.put(2267, Integer.valueOf(iWakeUpMcu));
                    }
                    int i3 = 0;
                    for (int i4 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i3] = ((Integer) sparseArray.get(i4)).intValue();
                            i3++;
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        }
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public void getAllStatus() {
        Log.v(TAG, "getAllStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAllStatus mForTestValue is 0 execute return");
        }
    }

    public int getBackLightState() {
        Log.v(TAG, "getBackLightState is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return this.mCarPowerManager.getBackLightState();
        }
        Log.e(TAG, " getBackLightState mForTestValue is 0 execute return");
        return 0;
    }

    public int getBatteryLowVoltageState() {
        Log.v(TAG, "getBatteryLowVoltageState is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryLowVoltageState mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getBatteryRemainPowerEV() {
        Log.v(TAG, "getBatteryRemainPowerEV is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryRemainPowerEV mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getDevicetype mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return new int[]{0, 0};
        }
        Log.e(TAG, " getFeatureList mForTestValue is 0 execute return");
        return new int[]{0, 0};
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getGetPermission mForTestValue is 0 execute return");
        }
        return "ForTest";
    }

    public int getMcuStatus() {
        Log.v(TAG, "getMcuStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getMcuStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getPowerCtlStatus(int i2) {
        Log.v(TAG, "getPowerCtlStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getPowerCtlStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getPowerLevel() {
        Log.v(TAG, "getPowerLevel is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getType mForTestValue is 0 execute return");
            return -1;
        }
        CarPowerSignalStatus currentSignalState = this.mCarPowerManager.getCurrentSignalState();
        if (currentSignalState != null) {
            return getBydAccState(currentSignalState);
        }
        Log.e(TAG, " signalStatus is null");
        return -1;
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSetPermission mForTestValue is 0 execute return");
        }
        return "ForTest";
    }

    public int getShutdownInfo(int i2) {
        Log.v(TAG, "getShutdownInfo is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getShutdownInfo mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getTftBacklight() {
        Log.v(TAG, "getTftBacklight is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTftBacklight mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getTpDisplayController() {
        Log.v(TAG, "getTpDisplayController is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTpDisplayController mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getType() {
        Log.v(TAG, "getType is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getType mForTestValue is 0 execute return");
        }
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

    public int padReset() {
        Log.v(TAG, "padReset is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " padReset mForTestValue is 0 execute return");
        }
        return -1;
    }

    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        Log.v(TAG, "postEvent is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public int qcomWakeup(int i2) {
        Log.v(TAG, "qcomWakeup is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " qcomWakeup mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void registerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
        Log.d(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        registerListener(absBYDAutoPowerListener, null);
    }

    public void sendInfo(int i2) {
        Log.v(TAG, "sendInfo index :" + i2);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendInfo mForTestValue is 0 execute return");
            return;
        }
        if (i2 == 0) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 0);
        } else if (i2 == 1) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 1);
        } else {
            Log.v(TAG, "sendInfo not 0/1 return");
        }
    }

    public int sendQcomRebootState(int i2) {
        Log.v(TAG, "sendQcomRebootState is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendQcomRebootState mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void setAllStatus() {
        Log.v(TAG, "setAllStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAllStatus mForTestValue is 0 execute return");
        }
    }

    public int setBCMPowerOnLevel() {
        Log.v(TAG, "setBCMPowerOnLevel is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setBCMPowerOnLevel mForTestValue is 0 execute return");
        }
        return -1;
    }

    public boolean setBackLightState(int i2, int i3) {
        Log.v(TAG, "setBackLightState is execute state :" + i2 + "type:" + i3);
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return this.mCarPowerManager.setBackLightState(i2, i3);
        }
        Log.e(TAG, " setBackLightState mForTestValue is 0 execute return");
        return false;
    }

    public int setPowerCtlStatus(int i2, int i3) {
        Log.v(TAG, "setPowerCtlStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setPowerCtlStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int setTftBacklight(int i2) {
        Log.v(TAG, "setTftBacklight is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            this.mCarPowerManager.setDisplayPowerState(1, i2);
            return -1;
        }
        this.mCarPowerManager.setDisplayPowerState(1, i2);
        Log.e(TAG, " setTftBacklight mForTestValue is 0 execute return");
        return -1;
    }

    public int setTpDisplayController(int i2) {
        Log.v(TAG, "setTpDisplayController is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setTpDisplayController mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void trigerTestPowerManagerImplCallback(int i2, Object obj) {
        Log.d(TAG, "trigerTestPowerManagerImplCallback is execute");
        this.mTestCallback.enqueueMessage(i2, obj);
    }

    public void unregisterListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
        Log.d(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoPowerListener(null);
        } else {
            if (absBYDAutoPowerListener == null) {
                Log.e(TAG, "unregisterListener listener is null");
                return;
            }
            synchronized (this.mlisteners) {
                if (this.mlisteners.contains(absBYDAutoPowerListener)) {
                    this.mlisteners.remove(absBYDAutoPowerListener);
                    Log.i(TAG, "unregisterListener remove listener");
                }
            }
        }
    }

    public int wakeUpMcu() {
        Log.v(TAG, "wakeUpMcu is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " wakeUpMcu mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void registerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener, int[] iArr) {
        Log.d(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoPowerListener(absBYDAutoPowerListener);
        } else {
            if (absBYDAutoPowerListener == null) {
                Log.e(TAG, "registerListener listener is null");
                return;
            }
            synchronized (this.mlisteners) {
                if (this.mlisteners.contains(absBYDAutoPowerListener)) {
                    Log.d(TAG, "registerListener listener is contained");
                } else {
                    this.mlisteners.add(absBYDAutoPowerListener);
                    Log.i(TAG, "registerListener add new listener");
                }
            }
        }
    }
}
