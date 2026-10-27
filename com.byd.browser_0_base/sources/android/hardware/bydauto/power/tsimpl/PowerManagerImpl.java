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
import defpackage.C0175b;
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
        public void onCarPowerDisplayEvent(int i, int i2, int i3) {
            Log.e(PowerManagerImpl.TAG, " onCarPowerDisplayEvent type : " + i + " displayPanelId : " + i2 + " value : " + i3);
            PowerManagerImpl.this.handleDisplayEvent(i, i2, i3);
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
                } catch (Exception e) {
                    Log.e(PowerManagerImpl.TAG, "onPowerSTRChanged " + e.getMessage());
                }
            }
        }

        public void onCarPowerServiceConnected() {
        }

        public void onCarPowerServiceDisconnected() {
        }

        public void onCarPowerEvent(int i, int i2, CarPowerData carPowerData) {
        }
    };

    public PowerManagerImpl(Context context) {
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (this.mContentResolver != null && 1 == this.mForTest) {
            this.mTestCallback = new TestPowerManagerImplCallback();
            return;
        }
        CarPowerManager carPowerManager = CarPowerManager.getInstance(context, this.mCarPowerEventListener);
        this.mCarPowerManager = carPowerManager;
        carPowerManager.connect();
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(C0175b.j("requires permission ", str));
        }
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
    public void handleDisplayEvent(int i, int i2, int i3) {
        if (i == 2 && i2 == 1) {
            Iterator<AbsBYDAutoPowerListener> it = this.mlisteners.iterator();
            while (it.hasNext()) {
                it.next().onTftBacklightChanged(i3);
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
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " dspReset mForTestValue is 0 execute return");
        }
        return -1;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 2266) {
                        int mcuStatus = getMcuStatus();
                        bYDAutoEventValue.intValue = mcuStatus;
                        sparseArray.put(2266, Integer.valueOf(mcuStatus));
                    } else if (i == 2267) {
                        int iWakeUpMcu = wakeUpMcu();
                        bYDAutoEventValue.intValue = iWakeUpMcu;
                        sparseArray.put(2267, Integer.valueOf(iWakeUpMcu));
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                            i2++;
                        } catch (NullPointerException e) {
                            e.printStackTrace();
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
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAllStatus mForTestValue is 0 execute return");
        }
    }

    public int getBackLightState() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return this.mCarPowerManager.getBackLightState();
        }
        Log.e(TAG, " getBackLightState mForTestValue is 0 execute return");
        return 0;
    }

    public int getBatteryLowVoltageState() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryLowVoltageState mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getBatteryRemainPowerEV() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryRemainPowerEV mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getDevicetype() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getDevicetype mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int[] getFeatureList() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 != this.mForTest) {
            return new int[]{0, 0};
        }
        Log.e(TAG, " getFeatureList mForTestValue is 0 execute return");
        return new int[]{0, 0};
    }

    public String getGetPermission() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getGetPermission mForTestValue is 0 execute return");
        }
        return "ForTest";
    }

    public int getMcuStatus() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getMcuStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getPowerCtlStatus(int i) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getPowerCtlStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getPowerLevel() {
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
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSetPermission mForTestValue is 0 execute return");
        }
        return "ForTest";
    }

    public int getShutdownInfo(int i) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getShutdownInfo mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getTftBacklight() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTftBacklight mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getTpDisplayController() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTpDisplayController mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getType() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getType mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int mcuReset() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return this.mCarPowerManager.mcuReset() ? 0 : -1;
        }
        Log.e(TAG, " mcuReset mForTestValue is 0 execute return");
        return -1;
    }

    public int padReset() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " padReset mForTestValue is 0 execute return");
        }
        return -1;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public int qcomWakeup(int i) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " qcomWakeup mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void registerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        registerListener(absBYDAutoPowerListener, null);
    }

    public void sendInfo(int i) {
        if (1 == this.mForTest) {
            Log.e(TAG, " sendInfo mForTestValue is 0 execute return");
        } else if (i == 0) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 0);
        } else if (i == 1) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 1);
        }
    }

    public int sendQcomRebootState(int i) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendQcomRebootState mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void setAllStatus() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAllStatus mForTestValue is 0 execute return");
        }
    }

    public int setBCMPowerOnLevel() {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setBCMPowerOnLevel mForTestValue is 0 execute return");
        }
        return -1;
    }

    public boolean setBackLightState(int i, int i2) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return this.mCarPowerManager.setBackLightState(i, i2);
        }
        Log.e(TAG, " setBackLightState mForTestValue is 0 execute return");
        return false;
    }

    public int setPowerCtlStatus(int i, int i2) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setPowerCtlStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int setTftBacklight(int i) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 != this.mForTest) {
            this.mCarPowerManager.setDisplayPowerState(1, i);
            return -1;
        }
        this.mCarPowerManager.setDisplayPowerState(1, i);
        Log.e(TAG, " setTftBacklight mForTestValue is 0 execute return");
        return -1;
    }

    public int setTpDisplayController(int i) {
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setTpDisplayController mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void trigerTestPowerManagerImplCallback(int i, Object obj) {
        this.mTestCallback.enqueueMessage(i, obj);
    }

    public void unregisterListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
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
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " wakeUpMcu mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void registerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener, int[] iArr) {
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
                    return;
                }
                this.mlisteners.add(absBYDAutoPowerListener);
                Log.i(TAG, "registerListener add new listener");
            }
        }
    }
}
