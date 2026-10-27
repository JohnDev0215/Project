package android.hardware.bydauto.power.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.util.Log;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import spi.d;
import spi.e;

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
            StringBuilder sbL = e.l(" onCarPowerDisplayEvent type : ", i, " displayPanelId : ", i2, " value : ");
            sbL.append(i3);
            Log.e(PowerManagerImpl.TAG, sbL.toString());
            PowerManagerImpl.this.handleDisplayEvent(i, i2, i3);
        }

        public void onCarPowerEvent(int i, int i2, CarPowerData carPowerData) {
        }

        public void onCarPowerServiceConnected() {
        }

        public void onCarPowerServiceDisconnected() {
        }

        public void onCarPowerSignalChangeEvent(CarPowerSignalStatus carPowerSignalStatus) {
            PowerManagerImpl.this.handlePowerSignalEvent(carPowerSignalStatus);
        }

        public void onCarPowerWorkModeChangeEvent(com.ts.lib.power.data.CarPowerWorkModeStatus carPowerWorkModeStatus) {
            StringBuilder sbK = e.k("onCarPowerWorkModeChangeEvent ");
            sbK.append(carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal());
            sbK.append(" size:");
            sbK.append(PowerManagerImpl.this.mlisteners.size());
            Log.w(PowerManagerImpl.TAG, sbK.toString());
            Iterator it = PowerManagerImpl.this.mlisteners.iterator();
            while (it.hasNext()) {
                try {
                    ((AbsBYDAutoPowerListener) it.next()).onPowerSTRChanged(carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal());
                } catch (Exception e) {
                    StringBuilder sbK2 = e.k("onPowerSTRChanged ");
                    sbK2.append(e.getMessage());
                    Log.e(PowerManagerImpl.TAG, sbK2.toString());
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
            StringBuilder sbK = e.k("OtaManagerImpl mForTestValue is");
            sbK.append(this.mForTest);
            sbK.append(" execute return");
            Log.v(TAG, sbK.toString());
            this.mTestCallback = new TestPowerManagerImplCallback();
        }
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
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
        Log.v(TAG, "dspReset is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " dspReset mForTestValue is 0 execute return");
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r12, java.lang.Class<?> r13) {
        /*
            r11 = this;
            java.lang.String r13 = "PowerManagerImpl"
            java.lang.String r0 = "get is execute"
            android.util.Log.v(r13, r0)
            java.lang.String r0 = "android.permission.BYDAUTO_POWER_GET"
            r11.enforcePermission(r0)
            android.hardware.bydauto.BYDAutoEventValue r0 = new android.hardware.bydauto.BYDAutoEventValue
            r0.<init>()
            android.util.SparseArray r1 = new android.util.SparseArray
            r1.<init>()
            int r2 = r12.length
            int[] r2 = new int[r2]
            r0.intArrayValue = r2
            int r2 = r12.length
            r3 = -2147482648(0xffffffff800003e8, float:-1.401E-42)
            r4 = 1
            if (r2 >= r4) goto L25
            r0.intValue = r3
            goto L72
        L25:
            int r2 = r12.length
            r4 = 0
            r5 = r4
        L28:
            if (r5 >= r2) goto L72
            r6 = r12[r5]
            if (r6 <= 0) goto L6d
            r7 = 2266(0x8da, float:3.175E-42)
            if (r6 == r7) goto L41
            r7 = 2267(0x8db, float:3.177E-42)
            if (r6 == r7) goto L3c
            java.lang.String r6 = "case fail ! please check out"
            android.util.Log.v(r13, r6)
            goto L4e
        L3c:
            int r6 = r11.wakeUpMcu()
            goto L45
        L41:
            int r6 = r11.getMcuStatus()
        L45:
            r0.intValue = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r1.put(r7, r6)
        L4e:
            int r6 = r12.length
            r7 = r4
            r8 = r7
        L51:
            if (r7 >= r6) goto L6f
            r9 = r12[r7]
            java.lang.Object r9 = r1.get(r9)     // Catch: java.lang.NullPointerException -> L66
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch: java.lang.NullPointerException -> L66
            int r9 = r9.intValue()     // Catch: java.lang.NullPointerException -> L66
            int[] r10 = r0.intArrayValue     // Catch: java.lang.NullPointerException -> L66
            r10[r8] = r9     // Catch: java.lang.NullPointerException -> L66
            int r8 = r8 + 1
            goto L6a
        L66:
            r9 = move-exception
            r9.printStackTrace()
        L6a:
            int r7 = r7 + 1
            goto L51
        L6d:
            r0.intValue = r3
        L6f:
            int r5 = r5 + 1
            goto L28
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.power.tsimpl.PowerManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
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
        if (1 == this.mForTest) {
            Log.e(TAG, " getFeatureList mForTestValue is 0 execute return");
        }
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

    public int getPowerCtlStatus(int i) {
        Log.v(TAG, "getPowerCtlStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getPowerCtlStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int getPowerLevel() {
        String str;
        Log.v(TAG, "getPowerLevel is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_GET);
        if (1 == this.mForTest) {
            str = " getType mForTestValue is 0 execute return";
        } else {
            CarPowerSignalStatus currentSignalState = this.mCarPowerManager.getCurrentSignalState();
            if (currentSignalState != null) {
                return getBydAccState(currentSignalState);
            }
            str = " signalStatus is null";
        }
        Log.e(TAG, str);
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

    public int getShutdownInfo(int i) {
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

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        Log.v(TAG, "postEvent is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public int qcomWakeup(int i) {
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

    public void sendInfo(int i) {
        e.r("sendInfo index :", i, TAG);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendInfo mForTestValue is 0 execute return");
            return;
        }
        if (i == 0) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 0);
        } else if (i == 1) {
            this.mCarPowerManager.setDisplayLvdsLinkState(2, 1);
        } else {
            Log.v(TAG, "sendInfo not 0/1 return");
        }
    }

    public int sendQcomRebootState(int i) {
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

    public boolean setBackLightState(int i, int i2) {
        Log.v(TAG, "setBackLightState is execute state :" + i + "type:" + i2);
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_COMMON);
        if (1 != this.mForTest) {
            return this.mCarPowerManager.setBackLightState(i, i2);
        }
        Log.e(TAG, " setBackLightState mForTestValue is 0 execute return");
        return false;
    }

    public int setPowerCtlStatus(int i, int i2) {
        Log.v(TAG, "setPowerCtlStatus is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setPowerCtlStatus mForTestValue is 0 execute return");
        }
        return -1;
    }

    public int setTftBacklight(int i) {
        Log.v(TAG, "setTftBacklight is execute");
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
        Log.v(TAG, "setTpDisplayController is execute");
        enforcePermission(BYDAutoPowerDevice.PERMISSION_BYDAUTO_POWER_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setTpDisplayController mForTestValue is 0 execute return");
        }
        return -1;
    }

    public void trigerTestPowerManagerImplCallback(int i, Object obj) {
        Log.d(TAG, "trigerTestPowerManagerImplCallback is execute");
        this.mTestCallback.enqueueMessage(i, obj);
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
}
