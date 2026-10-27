package android.hardware.bydauto.bodywork.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto_test.TestBodyworkManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import com.ts.lib.engineeringmode.EngineeringSdkManager;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class BodyworkManagerImpl extends AbsTsMyCar {
    private static final int C_MALFUNCTION_1 = 1;
    private static final int C_MALFUNCTION_2 = 2;
    private static final int C_MALFUNCTION_3 = 3;
    private static final int C_MALFUNCTION_4 = 4;
    private static final int C_MALFUNCTION_5 = 5;
    private static final int C_MALFUNCTION_6 = 6;
    private static final int C_MALFUNCTION_7 = 7;
    private static final int C_MALFUNCTION_8 = 8;
    private static final String DEFAULT_VIN = "tes0000000000000000";
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_BodyworkManagerImpl";
    private static final int S_NUMBER_FOUR = 4;
    private static final int S_NUMBER_THREE = 3;
    private static final String TAG = "BodyworkManagerImpl";
    private static final int WINDOW_CONTROL_STATE_LEFT_FRONT_S = 1;
    private static final int WINDOW_CONTROL_STATE_LEFT_REAR_S = 3;
    private static final int WINDOW_CONTROL_STATE_RIGHT_FRONT_S = 2;
    private static final int WINDOW_CONTROL_STATE_RIGHT_REAR_S = 4;
    private static volatile BodyworkManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private OtaSdkManager mOtaSdkManager;
    private EngineeringSdkManager mSdkManager;
    private TestBodyworkManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoBodyworkListener> mAbsBydAutoBodyworkListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
    }

    BodyworkManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "BodyworkManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new TestBodyworkManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.bodywork.tsimpl.BodyworkManagerImpl.1
            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                try {
                    BodyworkManagerImpl.this.bodyworkCallback(i2, i3, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(BodyworkManagerImpl.TAG, "bodyworkCallback is error");
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(BodyworkManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
        this.mSdkManager = EngineeringSdkManager.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
    }

    public static synchronized BodyworkManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BodyworkManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new BodyworkManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    public int getWindowState(int i) {
        Log.v(TAG, "getWindowState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i) {
        }
        return 0;
    }

    public int getDoorState(int i) {
        int doorStateLeftFront;
        Log.v(TAG, "getDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        switch (i) {
            case 1:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftFront();
                break;
            case 2:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightFront();
                break;
            case 3:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftRear();
                break;
            case 4:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightRear();
                break;
            case 5:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                break;
            case 6:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                break;
            default:
                doorStateLeftFront = 0;
                break;
        }
        if (doorStateLeftFront == 1) {
            return 0;
        }
        if (doorStateLeftFront == 0) {
            return -1;
        }
        if (doorStateLeftFront == 2) {
            return 1;
        }
        return doorStateLeftFront;
    }

    public int getAutoSystemState() {
        Log.v(TAG, "getAutoSystemState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoSystemState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int autoSystemState = this.mTsManagerImpl.getCarBodyManager().getAutoSystemState();
        if (autoSystemState == 1) {
            return 0;
        }
        if (autoSystemState == 2) {
            return 1;
        }
        return autoSystemState;
    }

    public double getSteeringWheelValue(int i) {
        Log.v(TAG, "getSteeringWheelValue is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteeringWheelValue mForTestValue is" + this.mForTest + " execute return");
            return 0.0d;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0d;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelValue(i);
    }

    public int getPowerLevel() {
        Log.i(TAG, "getPowerLevel is execute and current process " + this.mContext.getPackageName());
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getPowerLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int powerLevel = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
        if (powerLevel == 0) {
            return 255;
        }
        if (powerLevel == 1) {
            return 0;
        }
        if (powerLevel == 2) {
            return 1;
        }
        if (powerLevel == 3) {
            return 2;
        }
        return powerLevel;
    }

    public int getBatteryVoltageLevel() {
        Log.v(TAG, "getBatteryVoltageLevel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBatteryVoltageLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int batteryVoltageLevel = this.mTsManagerImpl.getCarBodyManager().getBatteryVoltageLevel();
        if (batteryVoltageLevel == 0) {
            return 255;
        }
        if (batteryVoltageLevel == 1) {
            return 1;
        }
        if (batteryVoltageLevel == 2) {
            return 0;
        }
        return batteryVoltageLevel;
    }

    public String getAutoVIN() {
        Log.v(TAG, "getAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoVIN mForTestValue is" + this.mForTest + " execute return");
            return ExtensionRequestData.EMPTY_VALUE;
        }
        String autoVin = this.mOtaSdkManager.getAutoVin();
        return (autoVin == null || autoVin.length() == 0) ? DEFAULT_VIN : autoVin;
    }

    public String getRealAutoVIN() {
        Log.v(TAG, "getRealAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRealAutoVIN mForTestValue is" + this.mForTest + " execute return");
            return ExtensionRequestData.EMPTY_VALUE;
        }
        return this.mSdkManager.getRealAutoVin();
    }

    public void settingLuggage(int i) {
        Log.v(TAG, "settingLuggage is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "settingLuggage mForTestValue is" + this.mForTest + " execute return");
        } else if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().settingLuggage(i);
        }
    }

    public int getReadyIndicatorLightState() {
        Log.v(TAG, "getReadyIndicatorLightState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getReadyIndicatorLightState();
    }

    public int getAutoType() {
        Log.v(TAG, "getAutoType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        try {
            int autoTypeTwo = this.mTsManagerImpl.getCarBodyManager().getAutoTypeTwo();
            if (autoTypeTwo > 0) {
                return autoTypeTwo;
            }
            int autoType = this.mTsManagerImpl.getCarBodyManager().getAutoType();
            if (autoType != 0) {
                return autoType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMoonRoofConfig() {
        Log.v(TAG, "getMoonRoofConfig is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMoonRoofConfig mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int moonRoofConfigTwo = this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfigTwo();
        if (moonRoofConfigTwo != 0 && (moonRoofConfigTwo == 1 || moonRoofConfigTwo == 2 || moonRoofConfigTwo == 3 || moonRoofConfigTwo == 6 || moonRoofConfigTwo == 7 || moonRoofConfigTwo == 8 || moonRoofConfigTwo == 9)) {
            return moonRoofConfigTwo;
        }
        int moonRoofConfig = this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfig();
        if (moonRoofConfig != 0) {
            return moonRoofConfig;
        }
        return 0;
    }

    public int getFuelElecLowPower() {
        Log.v(TAG, "getFuelElecLowPower is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFuelElecLowPower mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFuelElecLowPower();
    }

    public int getAlarmState() {
        Log.v(TAG, "getAlarmState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAlarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAlarmState();
    }

    public int getAutoModelName() {
        Log.v(TAG, "getAutoModelName is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoModelName mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAutoModelName();
    }

    public int getMoonRoofType() {
        Log.v(TAG, "getMoonRoofType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMoonRoofType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMoonRoofType();
    }

    public int setMoonRoofType(int i) {
        Log.v(TAG, "setMoonRoofType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setMoonRoofType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setMoonRoofType(i);
    }

    public int setWindowOpenPercent(int i, int i2) {
        Log.v(TAG, "setWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(4000, i, i2);
            Log.v(TAG, "setWindowOpenPercent mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i) {
        }
        return 0;
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        registerListener(absBYDAutoBodyworkListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe bodywork listener " + absBYDAutoBodyworkListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoBodyworkListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoBodyworkListener(absBYDAutoBodyworkListener);
            Log.v(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (!this.mAbsBydAutoBodyworkListeners.contains(absBYDAutoBodyworkListener)) {
            this.mAbsBydAutoBodyworkListeners.add(absBYDAutoBodyworkListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoBodyworkListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void unregisterListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        Log.v(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoBodyworkListener);
            Log.v(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.v(TAG, "unsubscribeBodyworkCallback: " + absBYDAutoBodyworkListener);
        if (absBYDAutoBodyworkListener == null) {
            return;
        }
        this.mAbsBydAutoBodyworkListeners.remove(absBYDAutoBodyworkListener);
        unregisterConnectListener(absBYDAutoBodyworkListener);
    }

    public int getWindowControlState(int i) {
        Log.v(TAG, "getWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowControlState(i);
    }

    public int setWindowControlState(int i, int i2) {
        int[] iArr = {0, 0, 0, 0};
        if (i == 1) {
            iArr[0] = i2;
        } else if (i == 2) {
            iArr[1] = i2;
        } else if (i == 3) {
            iArr[2] = i2;
        } else if (i != 4) {
            Log.v(TAG, "no have area.");
        } else {
            iArr[3] = i2;
        }
        return setWindowControlState(iArr);
    }

    public int setWindowControlState(int[] iArr) {
        Log.v(TAG, "setWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setWindowControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWindowControlState(iArr);
    }

    public int getWindowLockState(int i) {
        Log.v(TAG, "getWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowLockState(i);
    }

    public int setWindowLockState(int i, int i2) {
        Log.v(TAG, "setWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(4001, i2);
            Log.v(TAG, "setWindowLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWindowLockState(i, i2);
    }

    public int getEnergyType() {
        Log.v(TAG, "getEnergyType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getEnergyType();
    }

    public int getSunroofState() {
        Log.v(TAG, "getSunroofState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSunroofState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSunroofState();
    }

    public void remoteWhistle() {
        Log.v(TAG, "remoteWhistle is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSunroofState mForTestValue is" + this.mForTest + " execute return");
        } else if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().remoteWhistle();
        }
    }

    public int getAntiTheftStatus() {
        Log.v(TAG, "getAntiTheftStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAntiTheftStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAntiTheftStatus();
    }

    public int setAntiTheftStatus(int i) {
        Log.v(TAG, "setAntiTheftStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAntiTheftStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAntiTheftStatus(i);
    }

    public int getWindowPermitState() {
        Log.v(TAG, "getWindowPermitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowPermitState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowPermitState();
    }

    public int getWindowblindInitState() {
        Log.v(TAG, "getWindowblindInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowblindInitState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowblindInitState();
    }

    public int getSunroofInitState() {
        Log.v(TAG, "getSunroofInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSunroofInitState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSunroofInitState();
    }

    public int getSunroofCloseNotice() {
        Log.v(TAG, "getSunroofCloseNotice is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSunroofCloseNotice mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSunroofCloseNotice();
    }

    public int getSunroofWindowblindPosition() {
        Log.v(TAG, "getSunroofWindowblindPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSunroofWindowblindPosition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSunroofWindowblindPosition();
    }

    public int getSunroofPosition() {
        Log.v(TAG, "getSunroofPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getSunroofPosition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSunroofPosition();
    }

    public int getWindowblindState() {
        Log.v(TAG, "getWindowblindState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowblindState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowblindState();
    }

    public int getRearWindowblindState() {
        Log.v(TAG, "getRearWindowblindState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearWindowblindState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindState();
    }

    public int getRearWindowblindInitState() {
        Log.v(TAG, "getRearWindowblindInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearWindowblindInitState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindInitState();
    }

    public int getRearWindowblindPosition() {
        Log.v(TAG, "getRearWindowblindPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearWindowblindPosition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindPosition();
    }

    public int setElecBackDoorControlState(int i) {
        Log.v(TAG, "setElecBackDoorControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setElecBackDoorControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setElecBackDoorControlState(i);
    }

    public int setBackDoorHeightPosition(int i) {
        Log.v(TAG, "setBackDoorHeightPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBackDoorHeightPosition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPosition(i);
    }

    public int getBackDoorCurState() {
        Log.v(TAG, "getBackDoorCurState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBackDoorCurState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getBackDoorCurState();
    }

    public int getRightStrutMorotRunningState() {
        Log.v(TAG, "getRightStrutMorotRunningState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRightStrutMorotRunningState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightStrutMorotRunningState();
    }

    public int getBackDoorPosition() {
        Log.v(TAG, "getBackDoorPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBackDoorPosition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getBackDoorPosition();
    }

    public int getBackDoorAntiPlayMode() {
        Log.v(TAG, "getBackDoorAntiPlayMode is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBackDoorAntiPlayMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getBackDoorAntiPlayMode();
    }

    public int getElecSlideDoorState() {
        Log.v(TAG, "getElecSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecSlideDoorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getElecSlideDoorState();
    }

    public int getLeftSlideDoorState() {
        Log.v(TAG, "getLeftSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeftSlideDoorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorState();
    }

    public int getRightSlideDoorState() {
        Log.v(TAG, "getRightSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRightSlideDoorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorState();
    }

    public int getLeftSlideDoorAction() {
        Log.v(TAG, "getLeftSlideDoorAction is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeftSlideDoorAction mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorAction();
    }

    public int getRightSlideDoorAction() {
        Log.v(TAG, "getRightSlideDoorAction is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRightSlideDoorAction mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorAction();
    }

    public int getLeftSlideDoorPercent() {
        Log.v(TAG, "getLeftSlideDoorPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeftSlideDoorPercent mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorPercent();
    }

    public int getRightSlideDoorPercent() {
        Log.v(TAG, "getRightSlideDoorPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRightSlideDoorPercent mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorPercent();
    }

    public int setLeftSlideDoorVoiceControlState(int i) {
        Log.v(TAG, "setLeftSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setLeftSlideDoorVoiceControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftSlideDoorVoiceControlState(i);
    }

    public int setRightSlideDoorVoiceControlState(int i) {
        Log.v(TAG, "setRightSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRightSlideDoorVoiceControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightSlideDoorVoiceControlState(i);
    }

    public int getWindowOpenPercent(int i) {
        Log.v(TAG, "getWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowOpenPercent mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        switch (i) {
            case 1:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentLeftFront();
            case 2:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRightFront();
            case 3:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentLeftRear();
            case 4:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRightRear();
            case 5:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercenMoonRoof();
            case 6:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentPanel();
            case 7:
                return this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRearPanel();
            default:
                return 0;
        }
    }

    public int getLeftStrutMorotRunningState() {
        Log.v(TAG, "getLeftStrutMorotRunningState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeftStrutMorotRunningState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftStrutMorotRunningState();
    }

    public int getWindowInitStateLeftFront() {
        Log.v(TAG, "getWindowInitStateLeftFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowInitStateLeftFront mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
    }

    public int getWindowInitStateLeftRear() {
        Log.v(TAG, "getWindowInitStateLeftRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowInitStateLeftRear mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
    }

    public int getWindowInitStateRightFront() {
        Log.v(TAG, "getWindowInitStateRightFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowInitStateRightFront mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
    }

    public int getWindowInitStateRightRear() {
        Log.v(TAG, "getWindowInitStateRightRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowInitStateRightRear mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
    }

    public int getWindowThermalProtectionStateLeftFront() {
        Log.v(TAG, "getWindowThermalProtectionStateLeftFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateLeftFront mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
    }

    public int getWindowThermalProtectionStateLeftRear() {
        Log.v(TAG, "getWindowThermalProtectionStateLeftRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateLeftRear mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
    }

    public int getWindowThermalProtectionStateRightFront() {
        Log.v(TAG, "getWindowThermalProtectionStateRightFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateRightFront mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
    }

    public int getWindowThermalProtectionStateRightRear() {
        Log.v(TAG, "getWindowThermalProtectionStateRightRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateRightRear mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
    }

    public int getWindowThermalProtectionStateMoonRoof() {
        Log.v(TAG, "getWindowThermalProtectionStateMoonRoof is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateMoonRoof mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateMoonRoof();
    }

    public int getWindowThermalProtectionStatePanel() {
        Log.v(TAG, "getWindowThermalProtectionStatePanel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStatePanel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStatePanel();
    }

    public int getWindowThermalProtectionStateRearPanel() {
        Log.v(TAG, "getWindowThermalProtectionStateRearPanel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateRearPanel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRearPanel();
    }

    public int getWindowInitState(int i) {
        Log.v(TAG, "getWindowInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateRearPanel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
    }

    public int getWindowThermalProtectionState(int i) {
        Log.v(TAG, "getWindowThermalProtectionState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateRearPanel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        switch (i) {
            case 1:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
            case 2:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
            case 3:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
            case 4:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
            case 5:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateMoonRoof();
            case 6:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateSunshadePanel();
            case 7:
                return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRearSunshadePanel();
            default:
                return 0;
        }
    }

    public int getBackDoorInitState() {
        Log.v(TAG, "getBackDoorInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBackDoorInitState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getBackDoorInitState();
    }

    public int getRearLampLightState() {
        Log.v(TAG, "getRearLampLightState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearLampLightState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRearLampLightState();
    }

    public int getWindowAntiPinchConfig() {
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowAntiPinchConfig mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowAntiPinchConfig();
    }

    public int setAllDoorsLock(int i) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAllDoorsLock mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLock(i);
    }

    public int getDoorLockStatus(int i) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorLockStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusLeftRear();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusRightFront();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusAreaRightRear();
    }

    public int getRearviewMirrorSupport() {
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorSupport();
    }

    public void turnSteeringWheel(int i) {
        Log.i(TAG, "turnSteeringWheel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnSteeringWheel(i);
        }
    }

    public void turnRearviewMirror(int i) {
        Log.i(TAG, "turnRearviewMirror is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnRearviewMirror(i);
        }
    }

    public float getRearviewMirrorX(int i) {
        Log.i(TAG, "getRearviewMirrorX is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXLeft();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXRight();
        }
        return 0.0f;
    }

    public float getRearviewMirrorY(int i) {
        Log.i(TAG, "getRearviewMirrorY is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorYLeft();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorYRight();
        }
        return 0.0f;
    }

    public float getSteeringWheelY() {
        Log.i(TAG, "getSteeringWheelY is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelY();
    }

    public float getSteeringWheelZ() {
        Log.i(TAG, "getSteeringWheelZ is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelZ();
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        Log.v(TAG, "getAutoExternalRearMirrorFoldActionState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getAutoExternalRearMirrorFoldActionState();
    }

    public int getAutoExternalRearMirrorFoldedState() {
        Log.v(TAG, "getAutoExternalRearMirrorFoldedState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getAutoExternalRearMirrorFoldedState();
    }

    public int setAutoExternalRearMirrorFoldState(int i) {
        Log.v(TAG, "setAutoExternalRearMirrorFoldState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        return this.mTsManagerImpl.getCarBodyManager().setAutoExternalRearMirrorFoldState(i);
    }

    public int setBackDoorOpenedHeight(int i) {
        Log.i(TAG, "setBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBackDoorOpenedHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setBackDoorOpenedHeight(i);
    }

    public int setDriveDistanceShowMode(int i) {
        Log.v(TAG, "setDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setDriveDistanceShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowMode(i);
    }

    public int getBcmVechileStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBCMVechileStatus mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getBcmVechileStatus();
    }

    public int getLightStatus(int i) {
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, " getBCMVechileStatus mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        int lightSideStatus = 0;
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        switch (i) {
            case 1:
                Log.v(TAG, "getLightSideStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightSideStatus();
                break;
            case 2:
                Log.v(TAG, "getLightLowBeamStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                break;
            case 3:
                Log.v(TAG, "getLightHighBeam");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                break;
            case 4:
                Log.v(TAG, "getLightLeftTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLeftTurnSignal();
                break;
            case 5:
                Log.v(TAG, "getLightRightTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRightTurnSignal();
                break;
            case 6:
                Log.v(TAG, "getLightFrontFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFrontFog();
                break;
            case 7:
                Log.v(TAG, "getLightRearFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                break;
            case 8:
                Log.v(TAG, "getLightFoot");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFoot();
                break;
            case 9:
                Log.v(TAG, "getLightRearPosition");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                break;
        }
        Log.v(TAG, "getLightStatus Type is:" + i + " And Result is:" + lightSideStatus);
        return lightSideStatus;
    }

    public int getSmartVoiceLimit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSmartVoiceLimit mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSmartVoiceLimit();
    }

    public int setBackDoorHeightPositionMyCar(int i) {
        Log.v(TAG, "setBackDoorHeightPositionMyCar is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBackDoorHeightPositionMyCar mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPositionMyCar(i);
    }

    public int setVoiceCtlSunshadePanelS(int i) {
        Log.i(TAG, "setVoiceCtlSunshadePanelS is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setVoiceCtlSunshadePanelS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlSunshadePanelS(i);
    }

    public int setVoiceCtlMoonRoofS(int i) {
        Log.i(TAG, "setVoiceCtlMoonRoofS is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setVoiceCtlMoonRoofS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlMoonRoofS(i);
    }

    public int getElectricFrontHatchConfiguration() {
        Log.i(TAG, "getElectricFrontHatchConfiguration is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElectricFrontHatchConfiguration mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
    }

    public int getMainSaveOrRecoverState() {
        Log.i(TAG, "getMainSaveOrRecoverState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMainSaveOrRecoverState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMainSaveOrRecoverState();
    }

    public int getMain2SaveOrRecoverState() {
        Log.i(TAG, "getMain2SaveOrRecoverState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMain2SaveOrRecoverState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMain2SaveOrRecoverState();
    }

    public int setWindowsMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWindowsMode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWindowsMode(i);
    }

    public int setSunroofAndSunshade(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSunroofAndSunshade mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSunroofAndSunshade(i);
    }

    public int setAllDoorsLockScene(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAllDoorsLockScene mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLockScene(i);
    }

    public int getMoonRoofPercentControlFlag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMoonRoofPercentControlFlag mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMoonRoofPercentControlFlag();
    }

    public int setElecSlideDoorState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setElecSlideDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setElecSlideDoorState(i);
    }

    public int hasMessage(int i) {
        Log.v(TAG, "hasMessage is execute id " + i);
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().hasMessage(i);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoBodyworkListener> it = this.mAbsBydAutoBodyworkListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bodyworkCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        int i3 = -1;
        switch (i) {
            case 557845265:
                Iterator<AbsBYDAutoBodyworkListener> it = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it.hasNext()) {
                    it.next().onFuelElecLowPowerChanged(carBodyPacket.getFuelElecLowPowerR());
                }
                break;
            case 557857850:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onMainSaveOrRecoverStateChanged(2, carBodyPacket.getRecoverOrSaveParamsChanged5());
                }
                caseFeatureIds(4614, Integer.valueOf(carBodyPacket.getRecoverOrSaveParamsChanged5()));
                break;
            case 557860644:
                int lightRearPosition = carBodyPacket.getLightRearPosition();
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onRearLampLightStateChanged(lightRearPosition);
                }
                caseFeatureIds(2006, Integer.valueOf(carBodyPacket.getLightRearPosition()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_LEFT_FRONT_R /* 557862936 */:
                Iterator<AbsBYDAutoBodyworkListener> it4 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onWindowOpenPercentChanged(1, carBodyPacket.getmWindowOpenPercentLeftFrontR());
                }
                caseFeatureIds(2090, Integer.valueOf(carBodyPacket.getmWindowOpenPercentLeftFrontR()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_LEFT_REAR_R /* 557862937 */:
                Iterator<AbsBYDAutoBodyworkListener> it5 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onWindowOpenPercentChanged(3, carBodyPacket.getmWindowOpenPercentLeftRearR());
                }
                caseFeatureIds(2091, Integer.valueOf(carBodyPacket.getmWindowOpenPercentLeftRearR()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_RIGHT_FRONT_R /* 557862938 */:
                Iterator<AbsBYDAutoBodyworkListener> it6 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onWindowOpenPercentChanged(2, carBodyPacket.getmWindowOpenPercentRightFrontR());
                }
                caseFeatureIds(2092, Integer.valueOf(carBodyPacket.getmWindowOpenPercentRightFrontR()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_RIGHT_REAR_R /* 557862939 */:
                Iterator<AbsBYDAutoBodyworkListener> it7 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onWindowOpenPercentChanged(4, carBodyPacket.getmWindowOpenPercentRightRearR());
                }
                caseFeatureIds(2093, Integer.valueOf(carBodyPacket.getmWindowOpenPercentRightRearR()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_MOON_R /* 557862940 */:
                Iterator<AbsBYDAutoBodyworkListener> it8 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onWindowOpenPercentChanged(5, carBodyPacket.getmWindowOpenPercentMoonR());
                }
                caseFeatureIds(1789, Integer.valueOf(carBodyPacket.getmWindowOpenPercentMoonR()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_SUN_R /* 557862941 */:
                Iterator<AbsBYDAutoBodyworkListener> it9 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onWindowOpenPercentChanged(6, carBodyPacket.getmWindowOpenPercentSunR());
                }
                caseFeatureIds(2084, Integer.valueOf(carBodyPacket.getmWindowOpenPercentSunR()));
                break;
            case BodyworkHalId.ID_MOON_ROOF_CONFIG /* 557862942 */:
                Iterator<AbsBYDAutoBodyworkListener> it10 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onMoonRoofConfigChanged(carBodyPacket.getmMoonRoofConfig());
                }
                caseFeatureIds(1786, Integer.valueOf(carBodyPacket.getmMoonRoofConfig()));
                break;
            case 557862943:
                int i4 = carBodyPacket.getmDoorStateLeftFront();
                if (i4 == 1) {
                    i3 = 0;
                } else if (i4 != 0) {
                    i3 = i4 == 2 ? 1 : i4;
                }
                Iterator<AbsBYDAutoBodyworkListener> it11 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onDoorStateChanged(1, i3);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateLeftFront " + i3);
                caseFeatureIds(1749, Integer.valueOf(i3));
                break;
            case 557862944:
                int i5 = carBodyPacket.getmDoorStateRightFront();
                if (i5 == 1) {
                    i3 = 0;
                } else if (i5 != 0) {
                    i3 = i5 == 2 ? 1 : i5;
                }
                Iterator<AbsBYDAutoBodyworkListener> it12 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onDoorStateChanged(2, i3);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateRightFront " + i3);
                caseFeatureIds(2040, Integer.valueOf(i3));
                break;
            case 557862945:
                int i6 = carBodyPacket.getmDoorStateLeftRear();
                if (i6 == 1) {
                    i3 = 0;
                } else if (i6 != 0) {
                    i3 = i6 == 2 ? 1 : i6;
                }
                Iterator<AbsBYDAutoBodyworkListener> it13 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onDoorStateChanged(3, i3);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateLeftRear " + i3);
                caseFeatureIds(1750, Integer.valueOf(i3));
                break;
            case 557862946:
                int i7 = carBodyPacket.getmDoorStateRightRear();
                if (i7 == 1) {
                    i3 = 0;
                } else if (i7 != 0) {
                    i3 = i7 == 2 ? 1 : i7;
                }
                Iterator<AbsBYDAutoBodyworkListener> it14 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onDoorStateChanged(4, i3);
                }
                Log.i(TAG, "onDoorStateChanged: id " + i + ", doorStateRightRear " + i3);
                caseFeatureIds(2041, Integer.valueOf(i3));
                break;
            case 557862947:
                int i8 = carBodyPacket.getmDoorStateLuggage();
                if (i8 == 1) {
                    i3 = 0;
                } else if (i8 != 0) {
                    i3 = i8 == 2 ? 1 : i8;
                }
                Iterator<AbsBYDAutoBodyworkListener> it15 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onDoorStateChanged(6, i3);
                }
                caseFeatureIds(1782, Integer.valueOf(i3));
                break;
            case 557862948:
                int i9 = carBodyPacket.getmDoorStateHood();
                if (i9 == 1) {
                    i3 = 0;
                } else if (i9 != 0) {
                    i3 = i9 == 2 ? 1 : i9;
                }
                Iterator<AbsBYDAutoBodyworkListener> it16 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onDoorStateChanged(5, i3);
                }
                caseFeatureIds(1742, Integer.valueOf(i3));
                break;
            case BodyworkHalId.ID_WINDOW_STATE_MOON_ROOFR /* 557862949 */:
                Iterator<AbsBYDAutoBodyworkListener> it17 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onWindowStateChanged(5, carBodyPacket.getmWindowStateMoonRoofR());
                }
                break;
            case BodyworkHalId.ID_WINDOW_STATE_SUNSHADER /* 557862950 */:
                Iterator<AbsBYDAutoBodyworkListener> it18 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onWindowblindStateChanged(carBodyPacket.getmWindowStateSunshadeR());
                }
                caseFeatureIds(2082, Integer.valueOf(carBodyPacket.getmWindowStateSunshadeR()));
                break;
            case BodyworkHalId.ID_ENERGY_TYPER /* 557862951 */:
                Iterator<AbsBYDAutoBodyworkListener> it19 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onEnergyTypeChanged(carBodyPacket.getmEnergyTypeR());
                }
                break;
            case BodyworkHalId.ID_SUN_ROOF_STATER /* 557862952 */:
                Iterator<AbsBYDAutoBodyworkListener> it20 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onSunroofStateChanged(carBodyPacket.getmSunRoofStateR());
                }
                caseFeatureIds(2079, Integer.valueOf(carBodyPacket.getmSunRoofStateR()));
                break;
            case 557862953:
                Iterator<AbsBYDAutoBodyworkListener> it21 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onLeftStrutMorotRunningStateChanged(carBodyPacket.getLeftStrutMorotRunningState());
                }
                caseFeatureIds(1759, Integer.valueOf(carBodyPacket.getLeftStrutMorotRunningState()));
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_FL /* 557862954 */:
                Iterator<AbsBYDAutoBodyworkListener> it22 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onWindowInitStateChanged(1, carBodyPacket.getWindowInitStateFL());
                }
                caseFeatureIds(1765, Integer.valueOf(carBodyPacket.getWindowInitStateFL()));
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_RL /* 557862955 */:
                Iterator<AbsBYDAutoBodyworkListener> it23 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onWindowInitStateChanged(3, carBodyPacket.getWindowInitStateRL());
                }
                caseFeatureIds(2036, Integer.valueOf(carBodyPacket.getWindowInitStateRL()));
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_FR /* 557862956 */:
                Iterator<AbsBYDAutoBodyworkListener> it24 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onWindowInitStateChanged(2, carBodyPacket.getWindowInitStateFR());
                }
                caseFeatureIds(1780, Integer.valueOf(carBodyPacket.getWindowInitStateFR()));
                break;
            case BodyworkHalId.ID_WINDOW_INIT_STATE_RR /* 557862957 */:
                Iterator<AbsBYDAutoBodyworkListener> it25 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onWindowInitStateChanged(4, carBodyPacket.getWindowInitStateRR());
                }
                caseFeatureIds(2062, Integer.valueOf(carBodyPacket.getWindowInitStateRR()));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_RL /* 557862958 */:
                Iterator<AbsBYDAutoBodyworkListener> it26 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onWindowThermalProtectionStateChanged(1, carBodyPacket.getWindowThermalProtectionStateFl());
                }
                caseFeatureIds(1748, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateFl()));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FL /* 557862959 */:
                Iterator<AbsBYDAutoBodyworkListener> it27 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onWindowThermalProtectionStateChanged(3, carBodyPacket.getWindowThermalProtectionStateRl());
                }
                caseFeatureIds(1752, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateRl()));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FR /* 557862960 */:
                Iterator<AbsBYDAutoBodyworkListener> it28 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onWindowThermalProtectionStateChanged(2, carBodyPacket.getWindowThermalProtectionStateFr());
                }
                caseFeatureIds(2039, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateFr()));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_RR /* 557862961 */:
                Iterator<AbsBYDAutoBodyworkListener> it29 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onWindowThermalProtectionStateChanged(4, carBodyPacket.getWindowThermalProtectionStateRr());
                }
                caseFeatureIds(2043, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateRr()));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_SKYLIGHT /* 557862962 */:
                Iterator<AbsBYDAutoBodyworkListener> it30 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onWindowThermalProtectionStateChanged(5, carBodyPacket.getWindowThermalProtectionStateSkylight());
                }
                caseFeatureIds(2068, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateSkylight()));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FRONT_SUNSHADE /* 557862963 */:
                Iterator<AbsBYDAutoBodyworkListener> it31 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onWindowThermalProtectionStateChanged(6, carBodyPacket.getWindowThermalProtectionStateFrontSunshade());
                }
                caseFeatureIds(1735, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateFrontSunshade()));
                break;
            case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_REAR_SUNSHADE /* 557862964 */:
                Iterator<AbsBYDAutoBodyworkListener> it32 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onWindowThermalProtectionStateChanged(7, carBodyPacket.getWindowThermalProtectionStateRearSunshade());
                }
                caseFeatureIds(2014, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateRearSunshade()));
                break;
            case BodyworkHalId.ID_WINDOW_PERMIT_STATER /* 557862965 */:
                Iterator<AbsBYDAutoBodyworkListener> it33 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onWindowPermitStateChanged(carBodyPacket.getWindowPermitState());
                }
                caseFeatureIds(1747, Integer.valueOf(carBodyPacket.getWindowPermitState()));
                break;
            case BodyworkHalId.ID_WINDOWBLIND_INIT_STATER /* 557862966 */:
                Iterator<AbsBYDAutoBodyworkListener> it34 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onWindoblindInitStateChanged(carBodyPacket.getWindowblindInitStateR());
                }
                caseFeatureIds(2080, Integer.valueOf(carBodyPacket.getWindowblindInitStateR()));
                break;
            case BodyworkHalId.ID_SUNROOF_INIT_STATER /* 557862967 */:
                Iterator<AbsBYDAutoBodyworkListener> it35 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onSunroofInitStateChanged(carBodyPacket.getSunroofInitStateR());
                }
                caseFeatureIds(2077, Integer.valueOf(carBodyPacket.getSunroofInitStateR()));
                break;
            case BodyworkHalId.ID_SUNROOF_CLOSE_NOTICER /* 557862968 */:
                Iterator<AbsBYDAutoBodyworkListener> it36 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onSunroofCloseNoticeChanged(carBodyPacket.getSunroofCloseNoticeR());
                }
                caseFeatureIds(2076, Integer.valueOf(carBodyPacket.getSunroofCloseNoticeR()));
                break;
            case BodyworkHalId.ID_SUNROOF_WINDOWBLIND_POSITIONR /* 557862969 */:
                Iterator<AbsBYDAutoBodyworkListener> it37 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onSunroofWindowblindPositionChanged(carBodyPacket.getSunroofWindowblindPositionR());
                }
                caseFeatureIds(2081, Integer.valueOf(carBodyPacket.getSunroofWindowblindPositionR()));
                break;
            case BodyworkHalId.ID_SUNROOF_POSITIONR /* 557862970 */:
                Iterator<AbsBYDAutoBodyworkListener> it38 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onSunroofPositionChanged(carBodyPacket.getSunroofPositionR());
                }
                caseFeatureIds(2078, Integer.valueOf(carBodyPacket.getSunroofPositionR()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_REAR_PANELR /* 557862991 */:
                Iterator<AbsBYDAutoBodyworkListener> it39 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onWindowOpenPercentChanged(7, carBodyPacket.getWindowOpenPercentRearPanel());
                }
                caseFeatureIds(2012, Integer.valueOf(carBodyPacket.getWindowOpenPercentRearPanel()));
                break;
            case BodyworkHalId.ID_READY_INDICATOR_LIGHT_STATER /* 557863013 */:
                Iterator<AbsBYDAutoBodyworkListener> it40 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onReadyIndicatorLightStateChanged(carBodyPacket.getReadyIndicatorLightStateR());
                }
                break;
            case BodyworkHalId.ID_REAR_WINDOWBLIND_INIT_STATE_NEW_R /* 557863019 */:
                Iterator<AbsBYDAutoBodyworkListener> it41 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onRearWindowblindInitStateChanged(carBodyPacket.getRearWindowblindInitStateNewR());
                }
                caseFeatureIds(2009, Integer.valueOf(carBodyPacket.getRearWindowblindInitStateNewR()));
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_LEFT_LEFT_R /* 557863049 */:
                Iterator<AbsBYDAutoBodyworkListener> it42 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it42.hasNext()) {
                    it42.next().onScreenControlSlideDoorChanged(1, carBodyPacket.getScreenControlSlideDoorLeftLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_LEFT_R /* 557863050 */:
                Iterator<AbsBYDAutoBodyworkListener> it43 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it43.hasNext()) {
                    it43.next().onScreenControlSlideDoorChanged(2, carBodyPacket.getScreenControlSlideDoorRightLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_L_LEFT_RIGHT_R /* 557863051 */:
                Iterator<AbsBYDAutoBodyworkListener> it44 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it44.hasNext()) {
                    it44.next().onScreenControlSlideDoorChanged(3, carBodyPacket.getScreenControlSlideDoorLLeftRightR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_RIGHT_R /* 557863052 */:
                Iterator<AbsBYDAutoBodyworkListener> it45 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it45.hasNext()) {
                    it45.next().onScreenControlSlideDoorChanged(4, carBodyPacket.getScreenControlSlideDoorRightRightR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_LEFT_R /* 557863053 */:
                Iterator<AbsBYDAutoBodyworkListener> it46 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it46.hasNext()) {
                    it46.next().onScreenControlSlideDoorChanged(5, carBodyPacket.getScreenControlSlideDoorSlidingLeftLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_LEFT_R /* 557863054 */:
                Iterator<AbsBYDAutoBodyworkListener> it47 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it47.hasNext()) {
                    it47.next().onScreenControlSlideDoorChanged(6, carBodyPacket.getScreenControlSlideDoorSlidingRightLeftR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_RIGHT_R /* 557863055 */:
                Iterator<AbsBYDAutoBodyworkListener> it48 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it48.hasNext()) {
                    it48.next().onScreenControlSlideDoorChanged(7, carBodyPacket.getScreenControlSlideDoorSlidingLeftRightR());
                }
                break;
            case BodyworkHalId.ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_RIGHT_R /* 557863056 */:
                Iterator<AbsBYDAutoBodyworkListener> it49 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it49.hasNext()) {
                    it49.next().onScreenControlSlideDoorChanged(8, carBodyPacket.getScreenControlSlideDoorSlidingRightRightR());
                }
                break;
            case BodyworkHalId.ID_EV_READY_INDICATOR_LIGHT_STATE_R /* 557863057 */:
                Iterator<AbsBYDAutoBodyworkListener> it50 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it50.hasNext()) {
                    it50.next().onEvReadyIndicatorLightStateChanged(carBodyPacket.getEvReadyIndicatorLightStateR());
                }
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_F_R /* 557863061 */:
                Iterator<AbsBYDAutoBodyworkListener> it51 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it51.hasNext()) {
                    it51.next().onInternalDoorHandleStateByRearBCMChanged(1, carBodyPacket.getInternalDoorHandleStateByRearBCMLFR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(carBodyPacket.getInternalDoorHandleStateByRearBCMLFR()));
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_R_R /* 557863062 */:
                Iterator<AbsBYDAutoBodyworkListener> it52 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it52.hasNext()) {
                    it52.next().onInternalDoorHandleStateByRearBCMChanged(3, carBodyPacket.getInternalDoorHandleStateByRearBCMLRR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(carBodyPacket.getInternalDoorHandleStateByRearBCMLRR()));
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_F_R /* 557863063 */:
                Iterator<AbsBYDAutoBodyworkListener> it53 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it53.hasNext()) {
                    it53.next().onInternalDoorHandleStateByRearBCMChanged(2, carBodyPacket.getInternalDoorHandleStateByRearBCMRFR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(carBodyPacket.getInternalDoorHandleStateByRearBCMRFR()));
                break;
            case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_R_R /* 557863064 */:
                Iterator<AbsBYDAutoBodyworkListener> it54 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it54.hasNext()) {
                    it54.next().onInternalDoorHandleStateByRearBCMChanged(4, carBodyPacket.getInternalDoorHandleStateByRearBCMRRR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(carBodyPacket.getInternalDoorHandleStateByRearBCMRRR()));
                break;
            case BodyworkHalId.ID_SHIFT_MODE_R /* 557868039 */:
                Iterator<AbsBYDAutoBodyworkListener> it55 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it55.hasNext()) {
                    it55.next().onShiftModeChanged(carBodyPacket.getShiftModeR());
                }
                break;
            case 557875227:
                Iterator<AbsBYDAutoBodyworkListener> it56 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it56.hasNext()) {
                    it56.next().onBatteryVoltageLevelLowTips(carBodyPacket.getBatteryVoltageLevelLowTips());
                }
                break;
            case BodyworkHalId.ID_CAN_055_STATE /* 557945600 */:
                int i10 = carBodyPacket.getCan055R()[0];
                hasMessageListener(CanIds.getRealCanId(i10), i10, carBodyPacket.getCan055R()[1]);
                break;
            case BodyworkHalId.ID_CAN_0D5_STATE /* 557945603 */:
                int i11 = carBodyPacket.getCan0d5()[0];
                hasMessageListener(CanIds.getRealCanId(i11), i11, carBodyPacket.getCan0d5()[1]);
                break;
            case BodyworkHalId.ID_CAN_10D_STATE /* 557945605 */:
                int i12 = carBodyPacket.getCan10d()[0];
                hasMessageListener(CanIds.getRealCanId(i12), i12, carBodyPacket.getCan10d()[1]);
                break;
            case BodyworkHalId.ID_CAN_121_STATE /* 557945606 */:
                int i13 = carBodyPacket.getCan121()[0];
                hasMessageListener(CanIds.getRealCanId(i13), i13, carBodyPacket.getCan121()[1]);
                break;
            case BodyworkHalId.ID_CAN_123_STATE /* 557945607 */:
                int i14 = carBodyPacket.getCan123()[0];
                hasMessageListener(CanIds.getRealCanId(i14), i14, carBodyPacket.getCan123()[1]);
                break;
            case BodyworkHalId.ID_CAN_12D_STATE /* 557945608 */:
                int i15 = carBodyPacket.getCan12d()[0];
                hasMessageListener(CanIds.getRealCanId(i15), i15, carBodyPacket.getCan12d()[1]);
                break;
            case BodyworkHalId.ID_CAN_12F_STATE /* 557945609 */:
                int i16 = carBodyPacket.getCan12f()[0];
                hasMessageListener(CanIds.getRealCanId(i16), i16, carBodyPacket.getCan12f()[1]);
                break;
            case BodyworkHalId.ID_CAN_133_STATE /* 557945610 */:
                int i17 = carBodyPacket.getCan133()[0];
                hasMessageListener(CanIds.getRealCanId(i17), i17, carBodyPacket.getCan133()[1]);
                break;
            case BodyworkHalId.ID_CAN_138_STATE /* 557945611 */:
                int i18 = carBodyPacket.getCan138()[0];
                hasMessageListener(CanIds.getRealCanId(i18), i18, carBodyPacket.getCan138()[1]);
                break;
            case BodyworkHalId.ID_CAN_13F_STATE /* 557945612 */:
                int i19 = carBodyPacket.getCan13f()[0];
                hasMessageListener(CanIds.getRealCanId(i19), i19, carBodyPacket.getCan13f()[1]);
                break;
            case BodyworkHalId.ID_CAN_144_STATE /* 557945613 */:
                int i20 = carBodyPacket.getCan144()[0];
                hasMessageListener(CanIds.getRealCanId(i20), i20, carBodyPacket.getCan144()[1]);
                break;
            case BodyworkHalId.ID_CAN_151_STATE /* 557945614 */:
                int i21 = carBodyPacket.getCan151()[0];
                hasMessageListener(CanIds.getRealCanId(i21), i21, carBodyPacket.getCan151()[1]);
                break;
            case BodyworkHalId.ID_CAN_167_STATE /* 557945615 */:
                int i22 = carBodyPacket.getCan167()[0];
                hasMessageListener(CanIds.getRealCanId(i22), i22, carBodyPacket.getCan167()[1]);
                break;
            case BodyworkHalId.ID_CAN_18A_STATE /* 557945616 */:
                int i23 = carBodyPacket.getCan18a()[0];
                hasMessageListener(CanIds.getRealCanId(i23), i23, carBodyPacket.getCan18a()[1]);
                break;
            case BodyworkHalId.ID_CAN_19F_STATE /* 557945617 */:
                int i24 = carBodyPacket.getCan19f()[0];
                hasMessageListener(CanIds.getRealCanId(i24), i24, carBodyPacket.getCan19f()[1]);
                break;
            case BodyworkHalId.ID_CAN_1C0_STATE /* 557945618 */:
                int i25 = carBodyPacket.getCan1c0()[0];
                hasMessageListener(CanIds.getRealCanId(i25), i25, carBodyPacket.getCan1c0()[1]);
                break;
            case BodyworkHalId.ID_CAN_1D1_STATE /* 557945619 */:
                int i26 = carBodyPacket.getCan1d1()[0];
                hasMessageListener(CanIds.getRealCanId(i26), i26, carBodyPacket.getCan1d1()[1]);
                break;
            case BodyworkHalId.ID_CAN_218_STATE /* 557945620 */:
                int i27 = carBodyPacket.getCan218()[0];
                hasMessageListener(CanIds.getRealCanId(i27), i27, carBodyPacket.getCan218()[1]);
                break;
            case BodyworkHalId.ID_CAN_223_STATE /* 557945621 */:
                int i28 = carBodyPacket.getCan223()[0];
                hasMessageListener(CanIds.getRealCanId(i28), i28, carBodyPacket.getCan223()[1]);
                break;
            case BodyworkHalId.ID_CAN_233_STATE /* 557945622 */:
                int i29 = carBodyPacket.getCan233()[0];
                hasMessageListener(CanIds.getRealCanId(i29), i29, carBodyPacket.getCan233()[1]);
                break;
            case BodyworkHalId.ID_CAN_235_STATE /* 557945623 */:
                int i30 = carBodyPacket.getCan235()[0];
                hasMessageListener(CanIds.getRealCanId(i30), i30, carBodyPacket.getCan235()[1]);
                break;
            case BodyworkHalId.ID_CAN_23A_STATE /* 557945624 */:
                int i31 = carBodyPacket.getCan23a()[0];
                hasMessageListener(CanIds.getRealCanId(i31), i31, carBodyPacket.getCan23a()[1]);
                break;
            case BodyworkHalId.ID_CAN_242_STATE /* 557945625 */:
                int i32 = carBodyPacket.getCan242()[0];
                hasMessageListener(CanIds.getRealCanId(i32), i32, carBodyPacket.getCan242()[1]);
                break;
            case BodyworkHalId.ID_CAN_245_STATE /* 557945626 */:
                int i33 = carBodyPacket.getCan245()[0];
                hasMessageListener(CanIds.getRealCanId(i33), i33, carBodyPacket.getCan245()[1]);
                break;
            case BodyworkHalId.ID_CAN_248_STATE /* 557945627 */:
                int i34 = carBodyPacket.getCan248()[0];
                hasMessageListener(CanIds.getRealCanId(i34), i34, carBodyPacket.getCan248()[1]);
                break;
            case BodyworkHalId.ID_CAN_24A_STATE /* 557945628 */:
                int i35 = carBodyPacket.getCan24a()[0];
                hasMessageListener(CanIds.getRealCanId(i35), i35, carBodyPacket.getCan24a()[1]);
                break;
            case BodyworkHalId.ID_CAN_24C_STATE /* 557945629 */:
                int i36 = carBodyPacket.getCan24c()[0];
                hasMessageListener(CanIds.getRealCanId(i36), i36, carBodyPacket.getCan24c()[1]);
                break;
            case BodyworkHalId.ID_CAN_25D_STATE /* 557945630 */:
                int i37 = carBodyPacket.getCan25d()[0];
                hasMessageListener(CanIds.getRealCanId(i37), i37, carBodyPacket.getCan25d()[1]);
                break;
            case BodyworkHalId.ID_CAN_261_STATE /* 557945631 */:
                int i38 = carBodyPacket.getCan261()[0];
                hasMessageListener(CanIds.getRealCanId(i38), i38, carBodyPacket.getCan261()[1]);
                break;
            case BodyworkHalId.ID_CAN_26F_STATE /* 557945632 */:
                int i39 = carBodyPacket.getCan26f()[0];
                hasMessageListener(CanIds.getRealCanId(i39), i39, carBodyPacket.getCan26f()[1]);
                break;
            case BodyworkHalId.ID_CAN_271_STATE /* 557945633 */:
                int i40 = carBodyPacket.getCan271()[0];
                hasMessageListener(CanIds.getRealCanId(i40), i40, carBodyPacket.getCan271()[1]);
                break;
            case BodyworkHalId.ID_CAN_272_STATE /* 557945634 */:
                int i41 = carBodyPacket.getCan272()[0];
                hasMessageListener(CanIds.getRealCanId(i41), i41, carBodyPacket.getCan272()[1]);
                break;
            case BodyworkHalId.ID_CAN_275_STATE /* 557945635 */:
                int i42 = carBodyPacket.getCan275()[0];
                hasMessageListener(CanIds.getRealCanId(i42), i42, carBodyPacket.getCan275()[1]);
                break;
            case BodyworkHalId.ID_CAN_27A_STATE /* 557945636 */:
                int i43 = carBodyPacket.getCan27a()[0];
                hasMessageListener(CanIds.getRealCanId(i43), i43, carBodyPacket.getCan27a()[1]);
                break;
            case BodyworkHalId.ID_CAN_287_STATE /* 557945637 */:
                int i44 = carBodyPacket.getCan287()[0];
                hasMessageListener(CanIds.getRealCanId(i44), i44, carBodyPacket.getCan287()[1]);
                break;
            case BodyworkHalId.ID_CAN_288_STATE /* 557945638 */:
                int i45 = carBodyPacket.getCan288()[0];
                hasMessageListener(CanIds.getRealCanId(i45), i45, carBodyPacket.getCan288()[1]);
                break;
            case BodyworkHalId.ID_CAN_28F_STATE /* 557945639 */:
                int i46 = carBodyPacket.getCan28f()[0];
                hasMessageListener(CanIds.getRealCanId(i46), i46, carBodyPacket.getCan28f()[1]);
                break;
            case BodyworkHalId.ID_CAN_294_STATE /* 557945640 */:
                int i47 = carBodyPacket.getCan294()[0];
                hasMessageListener(CanIds.getRealCanId(i47), i47, carBodyPacket.getCan294()[1]);
                break;
            case BodyworkHalId.ID_CAN_296_STATE /* 557945641 */:
                int i48 = carBodyPacket.getCan296()[0];
                hasMessageListener(CanIds.getRealCanId(i48), i48, carBodyPacket.getCan296()[1]);
                break;
            case BodyworkHalId.ID_CAN_29C_STATE /* 557945642 */:
                int i49 = carBodyPacket.getCan29c()[0];
                hasMessageListener(CanIds.getRealCanId(i49), i49, carBodyPacket.getCan29c()[1]);
                break;
            case BodyworkHalId.ID_CAN_29E_STATE /* 557945643 */:
                int i50 = carBodyPacket.getCan29e()[0];
                hasMessageListener(CanIds.getRealCanId(i50), i50, carBodyPacket.getCan29e()[1]);
                break;
            case BodyworkHalId.ID_CAN_2A4_STATE /* 557945644 */:
                int i51 = carBodyPacket.getCan2a4()[0];
                hasMessageListener(CanIds.getRealCanId(i51), i51, carBodyPacket.getCan2a4()[1]);
                break;
            case BodyworkHalId.ID_CAN_2A6_STATE /* 557945645 */:
                int i52 = carBodyPacket.getCan2a6()[0];
                hasMessageListener(CanIds.getRealCanId(i52), i52, carBodyPacket.getCan2a6()[1]);
                break;
            case BodyworkHalId.ID_CAN_2A7_STATE /* 557945646 */:
                int i53 = carBodyPacket.getCan2a7()[0];
                hasMessageListener(CanIds.getRealCanId(i53), i53, carBodyPacket.getCan2a7()[1]);
                break;
            case BodyworkHalId.ID_CAN_2B0_STATE /* 557945647 */:
                int i54 = carBodyPacket.getCan2b0()[0];
                hasMessageListener(CanIds.getRealCanId(i54), i54, carBodyPacket.getCan2b0()[1]);
                break;
            case BodyworkHalId.ID_CAN_2B4_STATE /* 557945648 */:
                int i55 = carBodyPacket.getCan2b4()[0];
                hasMessageListener(CanIds.getRealCanId(i55), i55, carBodyPacket.getCan2b4()[1]);
                break;
            case BodyworkHalId.ID_CAN_2B6_STATE /* 557945649 */:
                int i56 = carBodyPacket.getCan2b6()[0];
                hasMessageListener(CanIds.getRealCanId(i56), i56, carBodyPacket.getCan2b6()[1]);
                break;
            case BodyworkHalId.ID_CAN_2BA_STATE /* 557945650 */:
                int i57 = carBodyPacket.getCan2ba()[0];
                hasMessageListener(CanIds.getRealCanId(i57), i57, carBodyPacket.getCan2ba()[1]);
                break;
            case BodyworkHalId.ID_CAN_2BC_STATE /* 557945651 */:
                int i58 = carBodyPacket.getCan2bc()[0];
                hasMessageListener(CanIds.getRealCanId(i58), i58, carBodyPacket.getCan2bc()[1]);
                break;
            case BodyworkHalId.ID_CAN_2C0_STATE /* 557945652 */:
                int i59 = carBodyPacket.getCan2c0()[0];
                hasMessageListener(CanIds.getRealCanId(i59), i59, carBodyPacket.getCan2c0()[1]);
                break;
            case BodyworkHalId.ID_CAN_2D5_STATE /* 557945653 */:
                int i60 = carBodyPacket.getCan2d5()[0];
                hasMessageListener(CanIds.getRealCanId(i60), i60, carBodyPacket.getCan2d5()[1]);
                break;
            case BodyworkHalId.ID_CAN_2EB_STATE /* 557945654 */:
                int i61 = carBodyPacket.getCan2eb()[0];
                hasMessageListener(CanIds.getRealCanId(i61), i61, carBodyPacket.getCan2eb()[1]);
                break;
            case BodyworkHalId.ID_CAN_2F0_STATE /* 557945655 */:
                int i62 = carBodyPacket.getCan2f0()[0];
                hasMessageListener(CanIds.getRealCanId(i62), i62, carBodyPacket.getCan2f0()[1]);
                break;
            case BodyworkHalId.ID_CAN_30D_STATE /* 557945656 */:
                int i63 = carBodyPacket.getCan30d()[0];
                hasMessageListener(CanIds.getRealCanId(i63), i63, carBodyPacket.getCan30d()[1]);
                break;
            case BodyworkHalId.ID_CAN_31E_STATE /* 557945657 */:
                int i64 = carBodyPacket.getCan31e()[0];
                hasMessageListener(CanIds.getRealCanId(i64), i64, carBodyPacket.getCan31e()[1]);
                break;
            case BodyworkHalId.ID_CAN_321_STATE /* 557945658 */:
                int i65 = carBodyPacket.getCan321()[0];
                hasMessageListener(CanIds.getRealCanId(i65), i65, carBodyPacket.getCan321()[1]);
                break;
            case BodyworkHalId.ID_CAN_323_STATE /* 557945659 */:
                int i66 = carBodyPacket.getCan323()[0];
                hasMessageListener(CanIds.getRealCanId(i66), i66, carBodyPacket.getCan323()[1]);
                break;
            case BodyworkHalId.ID_CAN_32D_STATE /* 557945660 */:
                int i67 = carBodyPacket.getCan32d()[0];
                hasMessageListener(CanIds.getRealCanId(i67), i67, carBodyPacket.getCan32d()[1]);
                break;
            case BodyworkHalId.ID_CAN_334_STATE /* 557945661 */:
                int i68 = carBodyPacket.getCan334()[0];
                hasMessageListener(CanIds.getRealCanId(i68), i68, carBodyPacket.getCan334()[1]);
                break;
            case BodyworkHalId.ID_CAN_337_STATE /* 557945662 */:
                int i69 = carBodyPacket.getCan337()[0];
                hasMessageListener(CanIds.getRealCanId(i69), i69, carBodyPacket.getCan337()[1]);
                break;
            case BodyworkHalId.ID_CAN_341_STATE /* 557945663 */:
                int i70 = carBodyPacket.getCan341()[0];
                hasMessageListener(CanIds.getRealCanId(i70), i70, carBodyPacket.getCan341()[1]);
                break;
            case BodyworkHalId.ID_CAN_342_STATE /* 557945664 */:
                int i71 = carBodyPacket.getCan342()[0];
                hasMessageListener(CanIds.getRealCanId(i71), i71, carBodyPacket.getCan342()[1]);
                break;
            case BodyworkHalId.ID_CAN_346_STATE /* 557945665 */:
                int i72 = carBodyPacket.getCan346()[0];
                hasMessageListener(CanIds.getRealCanId(i72), i72, carBodyPacket.getCan346()[1]);
                break;
            case BodyworkHalId.ID_CAN_347_STATE /* 557945666 */:
                int i73 = carBodyPacket.getCan347()[0];
                hasMessageListener(CanIds.getRealCanId(i73), i73, carBodyPacket.getCan347()[1]);
                break;
            case BodyworkHalId.ID_CAN_34A_STATE /* 557945667 */:
                int i74 = carBodyPacket.getCan34a()[0];
                hasMessageListener(CanIds.getRealCanId(i74), i74, carBodyPacket.getCan34a()[1]);
                break;
            case BodyworkHalId.ID_CAN_34F_STATE /* 557945668 */:
                int i75 = carBodyPacket.getCan34f()[0];
                hasMessageListener(CanIds.getRealCanId(i75), i75, carBodyPacket.getCan34f()[1]);
                break;
            case BodyworkHalId.ID_CAN_36B_STATE /* 557945669 */:
                int i76 = carBodyPacket.getCan36b()[0];
                hasMessageListener(CanIds.getRealCanId(i76), i76, carBodyPacket.getCan36b()[1]);
                break;
            case BodyworkHalId.ID_CAN_36D_STATE /* 557945670 */:
                int i77 = carBodyPacket.getCan36d()[0];
                hasMessageListener(CanIds.getRealCanId(i77), i77, carBodyPacket.getCan36d()[1]);
                break;
            case BodyworkHalId.ID_CAN_36E_STATE /* 557945671 */:
                int i78 = carBodyPacket.getCan36e()[0];
                hasMessageListener(CanIds.getRealCanId(i78), i78, carBodyPacket.getCan36e()[1]);
                break;
            case BodyworkHalId.ID_CAN_37F_STATE /* 557945672 */:
                int i79 = carBodyPacket.getCan37f()[0];
                hasMessageListener(CanIds.getRealCanId(i79), i79, carBodyPacket.getCan37f()[1]);
                break;
            case BodyworkHalId.ID_CAN_38A_STATE /* 557945673 */:
                int i80 = carBodyPacket.getCan38a()[0];
                hasMessageListener(CanIds.getRealCanId(i80), i80, carBodyPacket.getCan38a()[1]);
                break;
            case BodyworkHalId.ID_CAN_38B_STATE /* 557945674 */:
                int i81 = carBodyPacket.getCan38b()[0];
                hasMessageListener(CanIds.getRealCanId(i81), i81, carBodyPacket.getCan38b()[1]);
                break;
            case BodyworkHalId.ID_CAN_394_STATE /* 557945675 */:
                int i82 = carBodyPacket.getCan394()[0];
                hasMessageListener(CanIds.getRealCanId(i82), i82, carBodyPacket.getCan394()[1]);
                break;
            case BodyworkHalId.ID_CAN_396_STATE /* 557945676 */:
                int i83 = carBodyPacket.getCan396()[0];
                hasMessageListener(CanIds.getRealCanId(i83), i83, carBodyPacket.getCan396()[1]);
                break;
            case BodyworkHalId.ID_CAN_3A0_STATE /* 557945677 */:
                int i84 = carBodyPacket.getCan3a0()[0];
                hasMessageListener(CanIds.getRealCanId(i84), i84, carBodyPacket.getCan3a0()[1]);
                break;
            case BodyworkHalId.ID_CAN_3A7_STATE /* 557945678 */:
                int i85 = carBodyPacket.getCan3a7()[0];
                hasMessageListener(CanIds.getRealCanId(i85), i85, carBodyPacket.getCan3a7()[1]);
                break;
            case BodyworkHalId.ID_CAN_3AC_STATE /* 557945679 */:
                int i86 = carBodyPacket.getCan3ac()[0];
                hasMessageListener(CanIds.getRealCanId(i86), i86, carBodyPacket.getCan3ac()[1]);
                break;
            case BodyworkHalId.ID_CAN_3AD_STATE /* 557945680 */:
                int i87 = carBodyPacket.getCan3ad()[0];
                hasMessageListener(CanIds.getRealCanId(i87), i87, carBodyPacket.getCan3ad()[1]);
                break;
            case BodyworkHalId.ID_CAN_3B0_STATE /* 557945681 */:
                int i88 = carBodyPacket.getCan3b0()[0];
                hasMessageListener(CanIds.getRealCanId(i88), i88, carBodyPacket.getCan3b0()[1]);
                break;
            case BodyworkHalId.ID_CAN_3B3_STATE /* 557945682 */:
                int i89 = carBodyPacket.getCan3b3()[0];
                hasMessageListener(CanIds.getRealCanId(i89), i89, carBodyPacket.getCan3b3()[1]);
                break;
            case BodyworkHalId.ID_CAN_3B4_STATE /* 557945683 */:
                int i90 = carBodyPacket.getCan3b4()[0];
                hasMessageListener(CanIds.getRealCanId(i90), i90, carBodyPacket.getCan3b4()[1]);
                break;
            case BodyworkHalId.ID_CAN_3BB_STATE /* 557945684 */:
                int i91 = carBodyPacket.getCan3bb()[0];
                hasMessageListener(CanIds.getRealCanId(i91), i91, carBodyPacket.getCan3bb()[1]);
                break;
            case BodyworkHalId.ID_CAN_3BD_STATE /* 557945685 */:
                int i92 = carBodyPacket.getCan3bd()[0];
                hasMessageListener(CanIds.getRealCanId(i92), i92, carBodyPacket.getCan3bd()[1]);
                break;
            case BodyworkHalId.ID_CAN_3C1_STATE /* 557945686 */:
                int i93 = carBodyPacket.getCan3c1()[0];
                hasMessageListener(CanIds.getRealCanId(i93), i93, carBodyPacket.getCan3c1()[1]);
                break;
            case BodyworkHalId.ID_CAN_3C8_STATE /* 557945687 */:
                int i94 = carBodyPacket.getCan3c8()[0];
                hasMessageListener(CanIds.getRealCanId(i94), i94, carBodyPacket.getCan3c8()[1]);
                break;
            case BodyworkHalId.ID_CAN_3CA_STATE /* 557945688 */:
                int i95 = carBodyPacket.getCan3ca()[0];
                hasMessageListener(CanIds.getRealCanId(i95), i95, carBodyPacket.getCan3ca()[1]);
                break;
            case BodyworkHalId.ID_CAN_3CD_STATE /* 557945689 */:
                int i96 = carBodyPacket.getCan3cd()[0];
                hasMessageListener(CanIds.getRealCanId(i96), i96, carBodyPacket.getCan3cd()[1]);
                break;
            case BodyworkHalId.ID_CAN_3D9_STATE /* 557945690 */:
                int i97 = carBodyPacket.getCan3d9()[0];
                hasMessageListener(CanIds.getRealCanId(i97), i97, carBodyPacket.getCan3d9()[1]);
                break;
            case BodyworkHalId.ID_CAN_3DA_STATE /* 557945691 */:
                int i98 = carBodyPacket.getCan3da()[0];
                hasMessageListener(CanIds.getRealCanId(i98), i98, carBodyPacket.getCan3da()[1]);
                break;
            case BodyworkHalId.ID_CAN_3F1_STATE /* 557945692 */:
                int i99 = carBodyPacket.getCan3f1()[0];
                hasMessageListener(CanIds.getRealCanId(i99), i99, carBodyPacket.getCan3f1()[1]);
                break;
            case BodyworkHalId.ID_CAN_3F3_STATE /* 557945693 */:
                int i100 = carBodyPacket.getCan3f3()[0];
                hasMessageListener(CanIds.getRealCanId(i100), i100, carBodyPacket.getCan3f3()[1]);
                break;
            case BodyworkHalId.ID_CAN_3FE_STATE /* 557945694 */:
                int i101 = carBodyPacket.getCan3fe()[0];
                hasMessageListener(CanIds.getRealCanId(i101), i101, carBodyPacket.getCan3fe()[1]);
                break;
            case BodyworkHalId.ID_CAN_3FF_STATE /* 557945695 */:
                int i102 = carBodyPacket.getCan3ff()[0];
                hasMessageListener(CanIds.getRealCanId(i102), i102, carBodyPacket.getCan3ff()[1]);
                break;
            case BodyworkHalId.ID_CAN_401_STATE /* 557945696 */:
                int i103 = carBodyPacket.getCan401()[0];
                hasMessageListener(CanIds.getRealCanId(i103), i103, carBodyPacket.getCan401()[1]);
                break;
            case BodyworkHalId.ID_CAN_402_STATE /* 557945697 */:
                int i104 = carBodyPacket.getCan402()[0];
                hasMessageListener(CanIds.getRealCanId(i104), i104, carBodyPacket.getCan402()[1]);
                break;
            case BodyworkHalId.ID_CAN_404_STATE /* 557945698 */:
                int i105 = carBodyPacket.getCan404()[0];
                hasMessageListener(CanIds.getRealCanId(i105), i105, carBodyPacket.getCan404()[1]);
                break;
            case BodyworkHalId.ID_CAN_407_STATE /* 557945699 */:
                int i106 = carBodyPacket.getCan407()[0];
                hasMessageListener(CanIds.getRealCanId(i106), i106, carBodyPacket.getCan407()[1]);
                break;
            case BodyworkHalId.ID_CAN_40D_STATE /* 557945700 */:
                int i107 = carBodyPacket.getCan40d()[0];
                hasMessageListener(CanIds.getRealCanId(i107), i107, carBodyPacket.getCan40d()[1]);
                break;
            case BodyworkHalId.ID_CAN_417_STATE /* 557945701 */:
                int i108 = carBodyPacket.getCan417()[0];
                hasMessageListener(CanIds.getRealCanId(i108), i108, carBodyPacket.getCan417()[1]);
                break;
            case BodyworkHalId.ID_CAN_418_STATE /* 557945702 */:
                int i109 = carBodyPacket.getCan418()[0];
                hasMessageListener(CanIds.getRealCanId(i109), i109, carBodyPacket.getCan418()[1]);
                break;
            case BodyworkHalId.ID_CAN_41A_STATE /* 557945703 */:
                int i110 = carBodyPacket.getCan41a()[0];
                hasMessageListener(CanIds.getRealCanId(i110), i110, carBodyPacket.getCan41a()[1]);
                break;
            case BodyworkHalId.ID_CAN_41C_STATE /* 557945704 */:
                int i111 = carBodyPacket.getCan41c()[0];
                hasMessageListener(CanIds.getRealCanId(i111), i111, carBodyPacket.getCan41c()[1]);
                break;
            case BodyworkHalId.ID_CAN_41E_STATE /* 557945705 */:
                int i112 = carBodyPacket.getCan41e()[0];
                hasMessageListener(CanIds.getRealCanId(i112), i112, carBodyPacket.getCan41e()[1]);
                break;
            case BodyworkHalId.ID_CAN_41F_STATE /* 557945706 */:
                int i113 = carBodyPacket.getCan41f()[0];
                hasMessageListener(CanIds.getRealCanId(i113), i113, carBodyPacket.getCan41f()[1]);
                break;
            case BodyworkHalId.ID_CAN_429_STATE /* 557945707 */:
                int i114 = carBodyPacket.getCan429()[0];
                hasMessageListener(CanIds.getRealCanId(i114), i114, carBodyPacket.getCan429()[1]);
                break;
            case BodyworkHalId.ID_CAN_42E_STATE /* 557945708 */:
                int i115 = carBodyPacket.getCan42e()[0];
                hasMessageListener(CanIds.getRealCanId(i115), i115, carBodyPacket.getCan42e()[1]);
                break;
            case BodyworkHalId.ID_CAN_42F_STATE /* 557945709 */:
                int i116 = carBodyPacket.getCan42f()[0];
                hasMessageListener(CanIds.getRealCanId(i116), i116, carBodyPacket.getCan42f()[1]);
                break;
            case BodyworkHalId.ID_CAN_432_STATE /* 557945710 */:
                int i117 = carBodyPacket.getCan432()[0];
                hasMessageListener(CanIds.getRealCanId(i117), i117, carBodyPacket.getCan432()[1]);
                break;
            case BodyworkHalId.ID_CAN_433_STATE /* 557945711 */:
                int i118 = carBodyPacket.getCan433()[0];
                hasMessageListener(CanIds.getRealCanId(i118), i118, carBodyPacket.getCan433()[1]);
                break;
            case BodyworkHalId.ID_CAN_434_STATE /* 557945712 */:
                int i119 = carBodyPacket.getCan434()[0];
                hasMessageListener(CanIds.getRealCanId(i119), i119, carBodyPacket.getCan434()[1]);
                break;
            case BodyworkHalId.ID_CAN_436_STATE /* 557945713 */:
                int i120 = carBodyPacket.getCan436()[0];
                hasMessageListener(CanIds.getRealCanId(i120), i120, carBodyPacket.getCan436()[1]);
                break;
            case BodyworkHalId.ID_CAN_437_STATE /* 557945714 */:
                int i121 = carBodyPacket.getCan437()[0];
                hasMessageListener(CanIds.getRealCanId(i121), i121, carBodyPacket.getCan437()[1]);
                break;
            case BodyworkHalId.ID_CAN_438_STATE /* 557945715 */:
                int i122 = carBodyPacket.getCan438()[0];
                hasMessageListener(CanIds.getRealCanId(i122), i122, carBodyPacket.getCan438()[1]);
                break;
            case BodyworkHalId.ID_CAN_43E_STATE /* 557945716 */:
                int i123 = carBodyPacket.getCan43e()[0];
                hasMessageListener(CanIds.getRealCanId(i123), i123, carBodyPacket.getCan43e()[1]);
                break;
            case BodyworkHalId.ID_CAN_43F_STATE /* 557945717 */:
                int i124 = carBodyPacket.getCan43f()[0];
                hasMessageListener(CanIds.getRealCanId(i124), i124, carBodyPacket.getCan43f()[1]);
                break;
            case BodyworkHalId.ID_CAN_443_STATE /* 557945718 */:
                int i125 = carBodyPacket.getCan443()[0];
                hasMessageListener(CanIds.getRealCanId(i125), i125, carBodyPacket.getCan443()[1]);
                break;
            case BodyworkHalId.ID_CAN_444_STATE /* 557945719 */:
                int i126 = carBodyPacket.getCan444()[0];
                hasMessageListener(CanIds.getRealCanId(i126), i126, carBodyPacket.getCan444()[1]);
                break;
            case BodyworkHalId.ID_CAN_447_STATE /* 557945720 */:
                int i127 = carBodyPacket.getCan447()[0];
                hasMessageListener(CanIds.getRealCanId(i127), i127, carBodyPacket.getCan447()[1]);
                break;
            case BodyworkHalId.ID_CAN_449_STATE /* 557945721 */:
                int i128 = carBodyPacket.getCan449()[0];
                hasMessageListener(CanIds.getRealCanId(i128), i128, carBodyPacket.getCan449()[1]);
                break;
            case BodyworkHalId.ID_CAN_453_STATE /* 557945722 */:
                int i129 = carBodyPacket.getCan453()[0];
                hasMessageListener(CanIds.getRealCanId(i129), i129, carBodyPacket.getCan453()[1]);
                break;
            case BodyworkHalId.ID_CAN_454_STATE /* 557945723 */:
                int i130 = carBodyPacket.getCan454()[0];
                hasMessageListener(CanIds.getRealCanId(i130), i130, carBodyPacket.getCan454()[1]);
                break;
            case BodyworkHalId.ID_CAN_461_STATE /* 557945724 */:
                int i131 = carBodyPacket.getCan461()[0];
                hasMessageListener(CanIds.getRealCanId(i131), i131, carBodyPacket.getCan461()[1]);
                break;
            case BodyworkHalId.ID_CAN_46C_STATE /* 557945725 */:
                int i132 = carBodyPacket.getCan46c()[0];
                hasMessageListener(CanIds.getRealCanId(i132), i132, carBodyPacket.getCan46c()[1]);
                break;
            case BodyworkHalId.ID_CAN_470_STATE /* 557945726 */:
                int i133 = carBodyPacket.getCan470()[0];
                hasMessageListener(CanIds.getRealCanId(i133), i133, carBodyPacket.getCan470()[1]);
                break;
            case BodyworkHalId.ID_CAN_475_STATE /* 557945727 */:
                int i134 = carBodyPacket.getCan475()[0];
                hasMessageListener(CanIds.getRealCanId(i134), i134, carBodyPacket.getCan475()[1]);
                break;
            case BodyworkHalId.ID_CAN_480_STATE /* 557945728 */:
                int i135 = carBodyPacket.getCan480()[0];
                hasMessageListener(CanIds.getRealCanId(i135), i135, carBodyPacket.getCan480()[1]);
                break;
            case BodyworkHalId.ID_CAN_481_STATE /* 557945729 */:
                int i136 = carBodyPacket.getCan481()[0];
                hasMessageListener(CanIds.getRealCanId(i136), i136, carBodyPacket.getCan481()[1]);
                break;
            case BodyworkHalId.ID_CAN_486_STATE /* 557945730 */:
                int i137 = carBodyPacket.getCan486()[0];
                hasMessageListener(CanIds.getRealCanId(i137), i137, carBodyPacket.getCan486()[1]);
                break;
            case BodyworkHalId.ID_CAN_487_STATE /* 557945731 */:
                int i138 = carBodyPacket.getCan487()[0];
                hasMessageListener(CanIds.getRealCanId(i138), i138, carBodyPacket.getCan487()[1]);
                break;
            case BodyworkHalId.ID_CAN_488_STATE /* 557945732 */:
                int i139 = carBodyPacket.getCan488()[0];
                hasMessageListener(CanIds.getRealCanId(i139), i139, carBodyPacket.getCan488()[1]);
                break;
            case BodyworkHalId.ID_CAN_489_STATE /* 557945733 */:
                int i140 = carBodyPacket.getCan489()[0];
                hasMessageListener(CanIds.getRealCanId(i140), i140, carBodyPacket.getCan489()[1]);
                break;
            case BodyworkHalId.ID_CAN_48A_STATE /* 557945734 */:
                int i141 = carBodyPacket.getCan48a()[0];
                hasMessageListener(CanIds.getRealCanId(i141), i141, carBodyPacket.getCan48a()[1]);
                break;
            case BodyworkHalId.ID_CAN_48E_STATE /* 557945735 */:
                int i142 = carBodyPacket.getCan48e()[0];
                hasMessageListener(CanIds.getRealCanId(i142), i142, carBodyPacket.getCan48e()[1]);
                break;
            case BodyworkHalId.ID_CAN_48F_STATE /* 557945736 */:
                int i143 = carBodyPacket.getCan48f()[0];
                hasMessageListener(CanIds.getRealCanId(i143), i143, carBodyPacket.getCan48f()[1]);
                break;
            case BodyworkHalId.ID_CAN_494_STATE /* 557945737 */:
                int i144 = carBodyPacket.getCan494()[0];
                hasMessageListener(CanIds.getRealCanId(i144), i144, carBodyPacket.getCan494()[1]);
                break;
            case BodyworkHalId.ID_CAN_49C_STATE /* 557945738 */:
                int i145 = carBodyPacket.getCan49c()[0];
                hasMessageListener(CanIds.getRealCanId(i145), i145, carBodyPacket.getCan49c()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A1_STATE /* 557945739 */:
                int i146 = carBodyPacket.getCan4a1()[0];
                hasMessageListener(CanIds.getRealCanId(i146), i146, carBodyPacket.getCan4a1()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A4_STATE /* 557945740 */:
                int i147 = carBodyPacket.getCan4a4()[0];
                hasMessageListener(CanIds.getRealCanId(i147), i147, carBodyPacket.getCan4a4()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A5_STATE /* 557945741 */:
                int i148 = carBodyPacket.getCan4a5()[0];
                hasMessageListener(CanIds.getRealCanId(i148), i148, carBodyPacket.getCan4a5()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A7_STATE /* 557945742 */:
                int i149 = carBodyPacket.getCan4a7()[0];
                hasMessageListener(CanIds.getRealCanId(i149), i149, carBodyPacket.getCan4a7()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B1_STATE /* 557945743 */:
                int i150 = carBodyPacket.getCan4b1()[0];
                hasMessageListener(CanIds.getRealCanId(i150), i150, carBodyPacket.getCan4b1()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B2_STATE /* 557945744 */:
                int i151 = carBodyPacket.getCan4b2()[0];
                hasMessageListener(CanIds.getRealCanId(i151), i151, carBodyPacket.getCan4b2()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B3_STATE /* 557945745 */:
                int i152 = carBodyPacket.getCan4b3()[0];
                hasMessageListener(CanIds.getRealCanId(i152), i152, carBodyPacket.getCan4b3()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B6_STATE /* 557945746 */:
                int i153 = carBodyPacket.getCan4b6()[0];
                hasMessageListener(CanIds.getRealCanId(i153), i153, carBodyPacket.getCan4b6()[1]);
                break;
            case BodyworkHalId.ID_CAN_4BB_STATE /* 557945747 */:
                int i154 = carBodyPacket.getCan4bb()[0];
                hasMessageListener(CanIds.getRealCanId(i154), i154, carBodyPacket.getCan4bb()[1]);
                break;
            case BodyworkHalId.ID_CAN_4BD_STATE /* 557945748 */:
                int i155 = carBodyPacket.getCan4bd()[0];
                hasMessageListener(CanIds.getRealCanId(i155), i155, carBodyPacket.getCan4bd()[1]);
                break;
            case BodyworkHalId.ID_CAN_4BF_STATE /* 557945749 */:
                int i156 = carBodyPacket.getCan4bf()[0];
                hasMessageListener(CanIds.getRealCanId(i156), i156, carBodyPacket.getCan4bf()[1]);
                break;
            case BodyworkHalId.ID_CAN_4C2_STATE /* 557945750 */:
                int i157 = carBodyPacket.getCan4c2()[0];
                hasMessageListener(CanIds.getRealCanId(i157), i157, carBodyPacket.getCan4c2()[1]);
                break;
            case BodyworkHalId.ID_CAN_4C8_STATE /* 557945751 */:
                int i158 = carBodyPacket.getCan4c8()[0];
                hasMessageListener(CanIds.getRealCanId(i158), i158, carBodyPacket.getCan4c8()[1]);
                break;
            case BodyworkHalId.ID_CAN_4D9_STATE /* 557945752 */:
                int i159 = carBodyPacket.getCan4d9()[0];
                hasMessageListener(CanIds.getRealCanId(i159), i159, carBodyPacket.getCan4d9()[1]);
                break;
            case BodyworkHalId.ID_CAN_4E5_STATE /* 557945753 */:
                int i160 = carBodyPacket.getCan4e5()[0];
                hasMessageListener(CanIds.getRealCanId(i160), i160, carBodyPacket.getCan4e5()[1]);
                break;
            case BodyworkHalId.ID_CAN_4E6_STATE /* 557945754 */:
                int i161 = carBodyPacket.getCan4e6()[0];
                hasMessageListener(CanIds.getRealCanId(i161), i161, carBodyPacket.getCan4e6()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F2_STATE /* 557945755 */:
                int i162 = carBodyPacket.getCan4f2()[0];
                hasMessageListener(CanIds.getRealCanId(i162), i162, carBodyPacket.getCan4f2()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F4_STATE /* 557945756 */:
                int i163 = carBodyPacket.getCan4f4()[0];
                hasMessageListener(CanIds.getRealCanId(i163), i163, carBodyPacket.getCan4f4()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F6_STATE /* 557945757 */:
                int i164 = carBodyPacket.getCan4f6()[0];
                hasMessageListener(CanIds.getRealCanId(i164), i164, carBodyPacket.getCan4f6()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F7_STATE /* 557945758 */:
                int i165 = carBodyPacket.getCan4f7()[0];
                hasMessageListener(CanIds.getRealCanId(i165), i165, carBodyPacket.getCan4f7()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FA_STATE /* 557945759 */:
                int i166 = carBodyPacket.getCan4fa()[0];
                hasMessageListener(CanIds.getRealCanId(i166), i166, carBodyPacket.getCan4fa()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FB_STATE /* 557945760 */:
                int i167 = carBodyPacket.getCan4fb()[0];
                hasMessageListener(CanIds.getRealCanId(i167), i167, carBodyPacket.getCan4fb()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FD_STATE /* 557945761 */:
                int i168 = carBodyPacket.getCan4fd()[0];
                hasMessageListener(CanIds.getRealCanId(i168), i168, carBodyPacket.getCan4fd()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FE_STATE /* 557945762 */:
                int i169 = carBodyPacket.getCan4fe()[0];
                hasMessageListener(CanIds.getRealCanId(i169), i169, carBodyPacket.getCan4fe()[1]);
                break;
            case BodyworkHalId.ID_CAN_344_STATE /* 557945763 */:
                int i170 = carBodyPacket.getCan344()[0];
                hasMessageListener(CanIds.getRealCanId(i170), i170, carBodyPacket.getCan344()[1]);
                break;
            case BodyworkHalId.ID_CAN_319_STATE /* 557945764 */:
                int i171 = carBodyPacket.getCan319()[0];
                hasMessageListener(CanIds.getRealCanId(i171), i171, carBodyPacket.getCan319()[1]);
                break;
            case BodyworkHalId.ID_CAN_258_STATE /* 557945765 */:
                int i172 = carBodyPacket.getCan258()[0];
                hasMessageListener(CanIds.getRealCanId(i172), i172, carBodyPacket.getCan258()[1]);
                break;
            case BodyworkHalId.ID_CAN_351_STATE /* 557945766 */:
                int i173 = carBodyPacket.getCan351R()[0];
                hasMessageListener(CanIds.getRealCanId(i173), i173, carBodyPacket.getCan351R()[1]);
                break;
            case BodyworkHalId.ID_CAN_4E8_STATE /* 557945767 */:
                int i174 = carBodyPacket.getCan4e8R()[0];
                hasMessageListener(CanIds.getRealCanId(i174), i174, carBodyPacket.getCan4e8R()[1]);
                break;
            case BodyworkHalId.ID_CAN_4E9_STATE /* 557945768 */:
                int i175 = carBodyPacket.getCan4e9R()[0];
                hasMessageListener(CanIds.getRealCanId(i175), i175, carBodyPacket.getCan4e9R()[1]);
                break;
            case BodyworkHalId.ID_CAN_257_STATE /* 557945769 */:
                int i176 = carBodyPacket.getCan257R()[0];
                hasMessageListener(CanIds.getRealCanId(i176), i176, carBodyPacket.getCan257R()[1]);
                break;
            case BodyworkHalId.ID_CAN_48C_STATE /* 557945770 */:
                int i177 = carBodyPacket.getCan48cR()[0];
                hasMessageListener(CanIds.getRealCanId(i177), i177, carBodyPacket.getCan48cR()[1]);
                break;
            case BodyworkHalId.ID_CAN_3D6_STATE /* 557945771 */:
                int i178 = carBodyPacket.getCan3d6R()[0];
                hasMessageListener(CanIds.getRealCanId(i178), i178, carBodyPacket.getCan3d6R()[1]);
                break;
            case BodyworkHalId.ID_CAN_38F_STATE /* 557945772 */:
                int i179 = carBodyPacket.getCan38fR()[0];
                hasMessageListener(CanIds.getRealCanId(i179), i179, carBodyPacket.getCan38fR()[1]);
                break;
            case BodyworkHalId.ID_CAN_464_STATE /* 557945773 */:
                int i180 = carBodyPacket.getCan464R()[0];
                hasMessageListener(CanIds.getRealCanId(i180), i180, carBodyPacket.getCan464R()[1]);
                break;
            case BodyworkHalId.ID_CAN_338_STATE /* 557945774 */:
                int i181 = carBodyPacket.getCan338R()[0];
                hasMessageListener(CanIds.getRealCanId(i181), i181, carBodyPacket.getCan338R()[1]);
                break;
            case BodyworkHalId.ID_CAN_145_STATE /* 557945775 */:
                int i182 = carBodyPacket.getCan145R()[0];
                hasMessageListener(CanIds.getRealCanId(i182), i182, carBodyPacket.getCan145R()[1]);
                break;
            case 559954810:
                Iterator<AbsBYDAutoBodyworkListener> it57 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it57.hasNext()) {
                    it57.next().turnSteeringWheelChanged(3, carBodyPacket.getSteeringWheelY(), carBodyPacket.getSteeringWheelZ());
                }
                caseFeatureIds(2100, Integer.valueOf((int) carBodyPacket.getSteeringWheelY()));
                break;
            case 559954811:
                Iterator<AbsBYDAutoBodyworkListener> it58 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it58.hasNext()) {
                    it58.next().turnSteeringWheelChanged(1, carBodyPacket.getSteeringWheelY(), carBodyPacket.getSteeringWheelZ());
                }
                caseFeatureIds(2098, Integer.valueOf((int) carBodyPacket.getSteeringWheelZ()));
                break;
            case 559954812:
                Iterator<AbsBYDAutoBodyworkListener> it59 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it59.hasNext()) {
                    it59.next().onTurnRearviewMirrorChanged(3, carBodyPacket.getRearviewMirrorXLeft(), carBodyPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954813:
                Iterator<AbsBYDAutoBodyworkListener> it60 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it60.hasNext()) {
                    it60.next().onTurnRearviewMirrorChanged(7, carBodyPacket.getRearviewMirrorXRight(), carBodyPacket.getRearviewMirrorYRight());
                }
                break;
            case 559954814:
                Iterator<AbsBYDAutoBodyworkListener> it61 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it61.hasNext()) {
                    it61.next().onTurnRearviewMirrorChanged(1, carBodyPacket.getRearviewMirrorXLeft(), carBodyPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954815:
                Iterator<AbsBYDAutoBodyworkListener> it62 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it62.hasNext()) {
                    it62.next().onTurnRearviewMirrorChanged(6, carBodyPacket.getRearviewMirrorXRight(), carBodyPacket.getRearviewMirrorYRight());
                }
                break;
            default:
                switch (i) {
                    case 557857805:
                        Iterator<AbsBYDAutoBodyworkListener> it63 = this.mAbsBydAutoBodyworkListeners.iterator();
                        while (it63.hasNext()) {
                            it63.next().onRecoverOrSaveParamsChanged(0, 1, 1, carBodyPacket.getonSaveSeatParamsAllChangedPlace5());
                        }
                        caseFeatureIds(1305, Integer.valueOf(carBodyPacket.getonSaveSeatParamsAllChangedPlace5()));
                        break;
                    case 557857806:
                        Iterator<AbsBYDAutoBodyworkListener> it64 = this.mAbsBydAutoBodyworkListeners.iterator();
                        while (it64.hasNext()) {
                            it64.next().onMainSaveOrRecoverStateChanged(1, carBodyPacket.getonSaveSeatParamsAllChangedPlace7());
                        }
                        caseFeatureIds(2134, Integer.valueOf(carBodyPacket.getonSaveSeatParamsAllChangedPlace7()));
                        break;
                    default:
                        switch (i) {
                            case 557858048:
                                int autoExternalRearMirrorFoldActionStateR = carBodyPacket.getAutoExternalRearMirrorFoldActionStateR();
                                Iterator<AbsBYDAutoBodyworkListener> it65 = this.mAbsBydAutoBodyworkListeners.iterator();
                                while (it65.hasNext()) {
                                    it65.next().onAutoExternalRearMirrorFoldActionStateChanged(autoExternalRearMirrorFoldActionStateR);
                                }
                                caseFeatureIds(2169, Integer.valueOf(autoExternalRearMirrorFoldActionStateR));
                                break;
                            case 557858049:
                                Iterator<AbsBYDAutoBodyworkListener> it66 = this.mAbsBydAutoBodyworkListeners.iterator();
                                while (it66.hasNext()) {
                                    it66.next().onAutoExternalRearMirrorFoldedState(carBodyPacket.getAutoExternalRearMirrorFoldedStateR());
                                }
                                break;
                            default:
                                switch (i) {
                                    case BodyworkHalId.ID_WINDOW_STATE_LEFT_FRONT /* 557862912 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it67 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it67.hasNext()) {
                                            it67.next().onWindowStateChanged(1, carBodyPacket.getmWindowStateLeftFront());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_LEFT_REAR /* 557862913 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it68 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it68.hasNext()) {
                                            it68.next().onWindowStateChanged(3, carBodyPacket.getmWindowStateLeftRear());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_RIGHT_FRONT /* 557862914 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it69 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it69.hasNext()) {
                                            it69.next().onWindowStateChanged(2, carBodyPacket.getmWindowStateRightFront());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_RIGHT_REAR /* 557862915 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it70 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it70.hasNext()) {
                                            it70.next().onWindowStateChanged(4, carBodyPacket.getmWindowStateRightRear());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_LEFT_FRONT_R /* 557862916 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it71 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it71.hasNext()) {
                                            it71.next().onWindowControlStateChanged(1, carBodyPacket.getWindowControlStateLeftFrontR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_RIGHT_FRONT_R /* 557862917 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it72 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it72.hasNext()) {
                                            it72.next().onWindowControlStateChanged(2, carBodyPacket.getWindowControlStateRightFrontR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_LEFT_REAR_R /* 557862918 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it73 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it73.hasNext()) {
                                            it73.next().onWindowControlStateChanged(3, carBodyPacket.getWindowControlStateLeftRearR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_RIGHT_REAR_R /* 557862919 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it74 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it74.hasNext()) {
                                            it74.next().onWindowControlStateChanged(4, carBodyPacket.getWindowControlStateRightRearR());
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_MOON_S /* 557862924 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it75 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it75.hasNext()) {
                                                    it75.next().onWindowOpenPercentChanged(5, carBodyPacket.getmWindowOpenPercentMoonS());
                                                }
                                                break;
                                            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_SUN_S /* 557862925 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it76 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it76.hasNext()) {
                                                    it76.next().onWindowOpenPercentChanged(6, carBodyPacket.getmWindowOpenPercentSunS());
                                                }
                                                break;
                                            case 557862926:
                                                for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener : this.mAbsBydAutoBodyworkListeners) {
                                                    if (carBodyPacket.getmBatteryVoltageLevel() == 1) {
                                                        absBYDAutoBodyworkListener.onBatteryVoltageLevelChanged(1);
                                                    } else if (carBodyPacket.getmBatteryVoltageLevel() == 2) {
                                                        absBYDAutoBodyworkListener.onBatteryVoltageLevelChanged(0);
                                                    }
                                                }
                                                break;
                                            case 557862927:
                                                for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener2 : this.mAbsBydAutoBodyworkListeners) {
                                                    if (carBodyPacket.getmPowerLevel() == 0) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(255);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 255");
                                                    } else if (carBodyPacket.getmPowerLevel() == 1) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(0);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 0");
                                                    } else if (carBodyPacket.getmPowerLevel() == 2) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(1);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 1");
                                                    } else if (carBodyPacket.getmPowerLevel() == 3) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(2);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 2");
                                                    }
                                                }
                                                caseFeatureIds(2002, Integer.valueOf(carBodyPacket.getmPowerLevel()));
                                                break;
                                            case BodyworkHalId.ID_STEERING_WHEEL_VALUE_ANGEL /* 557862928 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it77 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it77.hasNext()) {
                                                    it77.next().onSteeringWheelValueChanged(1, carBodyPacket.getmSteeringWheelValueAngel());
                                                }
                                                break;
                                            case BodyworkHalId.ID_STEERING_WHEEL_VALUE_SPEED /* 557862929 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it78 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it78.hasNext()) {
                                                    it78.next().onSteeringWheelValueChanged(2, carBodyPacket.getmSteeringWheelValueSpeed());
                                                }
                                                break;
                                            case BodyworkHalId.ID_AUTO_TYPE /* 557862930 */:
                                                caseFeatureIds(1682, Integer.valueOf(carBodyPacket.getmAutoType()));
                                                break;
                                            case BodyworkHalId.ID_AUTO_SYSTEM_STATE /* 557862931 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it79 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it79.hasNext()) {
                                                    it79.next().onAutoSystemStateChanged(carBodyPacket.getmAutoSystemState());
                                                }
                                                break;
                                            default:
                                                switch (i) {
                                                    case BodyworkHalId.ID_REAR_WINDOWBLIND_STATER /* 557862972 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it80 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it80.hasNext()) {
                                                            it80.next().onRearWindowblindStateChanged(carBodyPacket.getRearWindowblindStateR());
                                                        }
                                                        caseFeatureIds(2007, Integer.valueOf(carBodyPacket.getRearWindowblindStateR()));
                                                        break;
                                                    case BodyworkHalId.ID_REAR_WINDOWBLIND_INIT_STATER /* 557862973 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it81 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it81.hasNext()) {
                                                            it81.next().onRearWindowblindInitStateChanged(carBodyPacket.getRearWindowblindInitStateR());
                                                        }
                                                        caseFeatureIds(2009, Integer.valueOf(carBodyPacket.getRearWindowblindInitStateR()));
                                                        break;
                                                    case BodyworkHalId.ID_REAR_WINDOWBLIND_POSITIONR /* 557862974 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it82 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it82.hasNext()) {
                                                            it82.next().onRearWindowblindPositionChanged(carBodyPacket.getRearWindowblindPositionR());
                                                        }
                                                        caseFeatureIds(2008, Integer.valueOf(carBodyPacket.getRearWindowblindPositionR()));
                                                        break;
                                                    case BodyworkHalId.ID_BACK_DOOR_INIT_STATER /* 557862975 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it83 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it83.hasNext()) {
                                                            it83.next().onBackDoorInitStateChanged(carBodyPacket.getBackDoorInitStateR());
                                                        }
                                                        caseFeatureIds(1686, Integer.valueOf(carBodyPacket.getBackDoorInitStateR()));
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case BodyworkHalId.ID_BACK_DOOR_CUR_STATER /* 557862978 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it84 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it84.hasNext()) {
                                                                    it84.next().onBackDoorCurStateChanged(carBodyPacket.getBackDoorCurStateR());
                                                                }
                                                                caseFeatureIds(1685, Integer.valueOf(carBodyPacket.getBackDoorCurStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_STRUT_MOROT_RUNNING_STATER /* 557862979 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it85 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it85.hasNext()) {
                                                                    it85.next().onRightStrutMorotRunningStateChanged(carBodyPacket.getRightStrutMorotRunningStateR());
                                                                }
                                                                caseFeatureIds(2049, Integer.valueOf(carBodyPacket.getRightStrutMorotRunningStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_BACK_DOOR_POSITIONR /* 557862980 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it86 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it86.hasNext()) {
                                                                    it86.next().onBackDoorPositionChanged(carBodyPacket.getBackDoorPositionR());
                                                                }
                                                                caseFeatureIds(1687, Integer.valueOf(carBodyPacket.getBackDoorPositionR()));
                                                                break;
                                                            case BodyworkHalId.ID_BACK_DOOR_ANTI_PLAY_MODER /* 557862981 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it87 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it87.hasNext()) {
                                                                    it87.next().onBackDoorAntiPlayModeChanged(carBodyPacket.getBackDoorAntiPlayModeR());
                                                                }
                                                                caseFeatureIds(1689, Integer.valueOf(carBodyPacket.getElecSlideDoorStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_ELEC_SLIDE_DOOR_STATER /* 557862982 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it88 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it88.hasNext()) {
                                                                    it88.next().onElecSlideDoorStateChanged(carBodyPacket.getElecSlideDoorStateR());
                                                                }
                                                                caseFeatureIds(1717, Integer.valueOf(carBodyPacket.getElecSlideDoorStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_LEFT_SLIDE_DOOR_STATER /* 557862983 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it89 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it89.hasNext()) {
                                                                    it89.next().onLeftSlideDoorStateChanged(carBodyPacket.getLeftSlideDoorStateR());
                                                                }
                                                                caseFeatureIds(1757, Integer.valueOf(carBodyPacket.getLeftSlideDoorStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_STATER /* 557862984 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it90 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it90.hasNext()) {
                                                                    it90.next().onRightSlideDoorStateChanged(carBodyPacket.getRightSlideDoorStateR());
                                                                }
                                                                caseFeatureIds(2047, Integer.valueOf(carBodyPacket.getRightSlideDoorStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_LEFT_SLIDE_DOOR_ACTIONR /* 557862985 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it91 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it91.hasNext()) {
                                                                    it91.next().onLeftSlideDoorActionChanged(carBodyPacket.getLeftSlideDoorActionR());
                                                                }
                                                                caseFeatureIds(1753, Integer.valueOf(carBodyPacket.getLeftSlideDoorActionR()));
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_ACTIONR /* 557862986 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it92 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it92.hasNext()) {
                                                                    it92.next().onRightSlideDoorActionChanged(carBodyPacket.getRightSlideDoorActionR());
                                                                }
                                                                caseFeatureIds(2044, Integer.valueOf(carBodyPacket.getRightSlideDoorActionR()));
                                                                break;
                                                            case BodyworkHalId.ID_LEFT_SLIDE_DOOR_PERCENTR /* 557862987 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it93 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it93.hasNext()) {
                                                                    it93.next().onLeftSlideDoorPercentChanged(carBodyPacket.getLeftSlideDoorPercentR());
                                                                }
                                                                caseFeatureIds(1756, Integer.valueOf(carBodyPacket.getLeftSlideDoorPercentR()));
                                                                break;
                                                            case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_PERCENTR /* 557862988 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it94 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it94.hasNext()) {
                                                                    it94.next().onRightSlideDoorPercentChanged(carBodyPacket.getRightSlideDoorPercentR());
                                                                }
                                                                caseFeatureIds(2046, Integer.valueOf(carBodyPacket.getRightSlideDoorPercentR()));
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case BodyworkHalId.ID_SMART_VOICE_LIMIT_R /* 557863016 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it95 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it95.hasNext()) {
                                                                            it95.next().onSmartVoiceLimitChanged(carBodyPacket.getSmartVoiceLimit());
                                                                        }
                                                                        break;
                                                                    case BodyworkHalId.ID_B_C_M_VECHILE_STATUS_R /* 557863017 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it96 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it96.hasNext()) {
                                                                            it96.next().onBCMVechileStatusChanged(carBodyPacket.getBcmVechileStatus());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case BodyworkHalId.ID_DOOR_LOCK_AREA_RIGHT_FRONT /* 557863022 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it97 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it97.hasNext()) {
                                                                                    it97.next().onDoorLockStatusChanged(3, carBodyPacket.getDoorLockStatusRightFront());
                                                                                }
                                                                                caseFeatureIds(2025, Integer.valueOf(carBodyPacket.getDoorLockStatusRightFront()));
                                                                                break;
                                                                            case BodyworkHalId.ID_DOOR_LOCK_AREA_LEFT_REAR /* 557863023 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it98 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it98.hasNext()) {
                                                                                    it98.next().onDoorLockStatusChanged(2, carBodyPacket.getDoorLockStatusLeftRear());
                                                                                }
                                                                                caseFeatureIds(1768, Integer.valueOf(carBodyPacket.getDoorLockStatusLeftRear()));
                                                                                break;
                                                                            case BodyworkHalId.ID_DOOR_LOCK_AREA_RIGHT_REAR /* 557863024 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it99 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it99.hasNext()) {
                                                                                    it99.next().onDoorLockStatusChanged(4, carBodyPacket.getDoorLockStatusAreaRightRear());
                                                                                }
                                                                                caseFeatureIds(2050, Integer.valueOf(carBodyPacket.getDoorLockStatusAreaRightRear()));
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case BodyworkHalId.ID_SEAT_MODE_WAKEUP_FEEDBACK_STATE_R /* 557863041 */:
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it100 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it100.hasNext()) {
                                                                                            it100.next().onSeatModeWakeupFeedbackStateChanged(carBodyPacket.getSeatModeWakeupFeedbackStateR());
                                                                                        }
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_FRONT_R /* 557863042 */:
                                                                                        int internalDoorHandleStateLeftFrontR = carBodyPacket.getInternalDoorHandleStateLeftFrontR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it101 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it101.hasNext()) {
                                                                                            it101.next().onDoorHandleStateChanged(1, internalDoorHandleStateLeftFrontR);
                                                                                        }
                                                                                        caseFeatureIds(4647, Integer.valueOf(internalDoorHandleStateLeftFrontR));
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_FRONT_R /* 557863043 */:
                                                                                        int internalDoorHandleStateRightFrontR = carBodyPacket.getInternalDoorHandleStateRightFrontR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it102 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it102.hasNext()) {
                                                                                            it102.next().onDoorHandleStateChanged(2, internalDoorHandleStateRightFrontR);
                                                                                        }
                                                                                        caseFeatureIds(4648, Integer.valueOf(internalDoorHandleStateRightFrontR));
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_BACK_R /* 557863044 */:
                                                                                        int internalDoorHandleStateLeftBackR = carBodyPacket.getInternalDoorHandleStateLeftBackR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it103 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it103.hasNext()) {
                                                                                            it103.next().onDoorHandleStateChanged(3, internalDoorHandleStateLeftBackR);
                                                                                        }
                                                                                        caseFeatureIds(4646, Integer.valueOf(internalDoorHandleStateLeftBackR));
                                                                                        break;
                                                                                    case BodyworkHalId.ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_BACK_R /* 557863045 */:
                                                                                        int internalDoorHandleStateRightBackR = carBodyPacket.getInternalDoorHandleStateRightBackR();
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it104 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it104.hasNext()) {
                                                                                            it104.next().onDoorHandleStateChanged(4, internalDoorHandleStateRightBackR);
                                                                                        }
                                                                                        caseFeatureIds(4649, Integer.valueOf(internalDoorHandleStateRightBackR));
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        int i = -2147482648;
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i2 : iArr) {
                if (i2 > 0) {
                    switch (i2) {
                        case 1681:
                            bYDAutoEventValue.intValue = getAntiTheftStatus();
                            sparseArray.put(1681, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1682:
                            bYDAutoEventValue.intValue = getAutoType();
                            sparseArray.put(1682, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1685:
                            bYDAutoEventValue.intValue = getBackDoorCurState();
                            sparseArray.put(1685, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1686:
                            bYDAutoEventValue.intValue = getBackDoorInitState();
                            sparseArray.put(1686, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1687:
                            bYDAutoEventValue.intValue = getBackDoorPosition();
                            sparseArray.put(1687, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1689:
                            bYDAutoEventValue.intValue = getBackDoorAntiPlayMode();
                            sparseArray.put(1689, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1717:
                            bYDAutoEventValue.intValue = getElecSlideDoorState();
                            sparseArray.put(1717, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1731:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                            sparseArray.put(1731, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1732:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
                            sparseArray.put(1732, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1735:
                            bYDAutoEventValue.intValue = getWindowThermalProtectionState(6);
                            sparseArray.put(1735, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1742:
                            bYDAutoEventValue.intValue = getDoorState(5);
                            sparseArray.put(1742, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1746:
                            bYDAutoEventValue.intValue = getWindowState(1);
                            sparseArray.put(1746, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1747:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowPermitState();
                            sparseArray.put(1747, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1748:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
                            sparseArray.put(1748, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1749:
                            bYDAutoEventValue.intValue = getDoorState(1);
                            sparseArray.put(1749, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1750:
                            bYDAutoEventValue.intValue = getDoorState(3);
                            sparseArray.put(1750, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1751:
                            bYDAutoEventValue.intValue = getWindowState(3);
                            sparseArray.put(1751, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1752:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
                            sparseArray.put(1752, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1753:
                            bYDAutoEventValue.intValue = getLeftSlideDoorAction();
                            sparseArray.put(1753, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1756:
                            bYDAutoEventValue.intValue = getLeftSlideDoorPercent();
                            sparseArray.put(1756, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1757:
                            bYDAutoEventValue.intValue = getLeftSlideDoorState();
                            sparseArray.put(1757, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1759:
                            bYDAutoEventValue.intValue = getLeftStrutMorotRunningState();
                            sparseArray.put(1759, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1765:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
                            sparseArray.put(1765, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1768:
                            bYDAutoEventValue.intValue = getDoorLockStatus(2);
                            sparseArray.put(1768, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1778:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(3);
                            sparseArray.put(1778, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1780:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
                            sparseArray.put(1780, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1782:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                            sparseArray.put(1782, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1785:
                            bYDAutoEventValue.intValue = getWindowState(5);
                            sparseArray.put(1785, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1786:
                            bYDAutoEventValue.intValue = getMoonRoofConfig();
                            sparseArray.put(1786, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1788:
                            bYDAutoEventValue.intValue = getMoonRoofConfig();
                            sparseArray.put(1788, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1789:
                            bYDAutoEventValue.intValue = getWindowOpenPercent(5);
                            sparseArray.put(1789, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1896:
                            bYDAutoEventValue.intValue = getReadyIndicatorLightState();
                            sparseArray.put(1896, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2002:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
                            sparseArray.put(2002, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2005:
                            bYDAutoEventValue.intValue = getRearviewMirrorSupport();
                            sparseArray.put(2005, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2006:
                            bYDAutoEventValue.intValue = getRearLampLightState();
                            sparseArray.put(2006, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2007:
                            bYDAutoEventValue.intValue = getRearWindowblindState();
                            sparseArray.put(2007, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2008:
                            bYDAutoEventValue.intValue = getRearWindowblindPosition();
                            sparseArray.put(2008, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2009:
                            bYDAutoEventValue.intValue = getRearWindowblindInitState();
                            sparseArray.put(2009, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2012:
                            bYDAutoEventValue.intValue = getWindowOpenPercent(7);
                            sparseArray.put(2012, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2014:
                            bYDAutoEventValue.intValue = getWindowThermalProtectionState(7);
                            sparseArray.put(2014, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2025:
                            bYDAutoEventValue.intValue = getDoorLockStatus(3);
                            sparseArray.put(2025, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2034:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(2);
                            sparseArray.put(2034, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2036:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
                            sparseArray.put(2036, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2039:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
                            sparseArray.put(2039, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2040:
                            bYDAutoEventValue.intValue = getDoorState(2);
                            sparseArray.put(2040, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2041:
                            bYDAutoEventValue.intValue = getDoorState(4);
                            sparseArray.put(2041, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2043:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
                            sparseArray.put(2043, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2044:
                            bYDAutoEventValue.intValue = getRightSlideDoorAction();
                            sparseArray.put(2044, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2046:
                            bYDAutoEventValue.intValue = getRightSlideDoorPercent();
                            sparseArray.put(2046, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2047:
                            bYDAutoEventValue.intValue = getRightSlideDoorState();
                            sparseArray.put(2047, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2049:
                            bYDAutoEventValue.intValue = getRightStrutMorotRunningState();
                            sparseArray.put(2049, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2050:
                            bYDAutoEventValue.intValue = getDoorLockStatus(4);
                            sparseArray.put(2050, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2060:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(4);
                            sparseArray.put(2060, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2062:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
                            sparseArray.put(2062, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2068:
                            bYDAutoEventValue.intValue = getWindowThermalProtectionState(5);
                            sparseArray.put(2068, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2072:
                            bYDAutoEventValue.doubleValue = getSteeringWheelValue(1);
                            sparseArray.put(2072, Double.valueOf(bYDAutoEventValue.doubleValue));
                            break;
                        case 2075:
                            bYDAutoEventValue.doubleValue = getSteeringWheelValue(2);
                            sparseArray.put(2075, Double.valueOf(bYDAutoEventValue.doubleValue));
                            break;
                        case 2076:
                            bYDAutoEventValue.intValue = getSunroofCloseNotice();
                            sparseArray.put(2076, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2077:
                            bYDAutoEventValue.intValue = getSunroofInitState();
                            sparseArray.put(2077, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2078:
                            bYDAutoEventValue.intValue = getSunroofPosition();
                            sparseArray.put(2078, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2079:
                            bYDAutoEventValue.intValue = getSunroofState();
                            sparseArray.put(2079, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2080:
                            bYDAutoEventValue.intValue = getWindowblindInitState();
                            sparseArray.put(2080, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2081:
                            bYDAutoEventValue.intValue = getSunroofWindowblindPosition();
                            sparseArray.put(2081, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2082:
                            bYDAutoEventValue.intValue = getWindowblindState();
                            sparseArray.put(2082, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2084:
                            bYDAutoEventValue.intValue = getWindowOpenPercent(6);
                            sparseArray.put(2084, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2089:
                            bYDAutoEventValue.intValue = getWindowAntiPinchConfig();
                            sparseArray.put(2089, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2090:
                            bYDAutoEventValue.intValue = getWindowOpenPercent(1);
                            sparseArray.put(2090, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2091:
                            bYDAutoEventValue.intValue = getWindowOpenPercent(3);
                            sparseArray.put(2091, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2092:
                            bYDAutoEventValue.intValue = getWindowOpenPercent(2);
                            sparseArray.put(2092, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2093:
                            bYDAutoEventValue.intValue = getWindowOpenPercent(4);
                            sparseArray.put(2093, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2098:
                            bYDAutoEventValue.floatValue = this.mTsManagerImpl.getCarBodyManager().getSteeringWheelZ();
                            sparseArray.put(2098, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 2100:
                            bYDAutoEventValue.floatValue = getSteeringWheelY();
                            sparseArray.put(2100, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 2120:
                            bYDAutoEventValue.floatValue = getRearviewMirrorX(1);
                            sparseArray.put(2120, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 2122:
                            bYDAutoEventValue.floatValue = getRearviewMirrorY(1);
                            sparseArray.put(2122, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 2130:
                            bYDAutoEventValue.floatValue = getRearviewMirrorX(2);
                            sparseArray.put(2130, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 2132:
                            bYDAutoEventValue.floatValue = getRearviewMirrorY(2);
                            sparseArray.put(2132, Float.valueOf(bYDAutoEventValue.floatValue));
                            break;
                        case 2134:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getMainSaveOrRecoverState();
                            sparseArray.put(2134, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2169:
                            bYDAutoEventValue.intValue = getAutoExternalRearMirrorFoldActionState();
                            sparseArray.put(2169, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2170:
                            bYDAutoEventValue.intValue = getAutoExternalRearMirrorFoldedState();
                            sparseArray.put(2170, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2445:
                            bYDAutoEventValue.intValue = getEnergyType();
                            sparseArray.put(2445, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 3648:
                            bYDAutoEventValue.intValue = getReadyIndicatorLightState();
                            sparseArray.put(3648, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4614:
                            bYDAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getMain2SaveOrRecoverState();
                            sparseArray.put(4614, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4615:
                            bYDAutoEventValue.intValue = getMoonRoofPercentControlFlag();
                            sparseArray.put(4615, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4639:
                            bYDAutoEventValue.intValue = getSeatLumbarSupportConfig(1);
                            sparseArray.put(4639, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4640:
                            bYDAutoEventValue.intValue = getSeatLumbarSupportConfig(2);
                            sparseArray.put(4640, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4643:
                            bYDAutoEventValue.intValue = getFourPeopleEnjoyConfig();
                            sparseArray.put(4643, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4645:
                            bYDAutoEventValue.intValue = getSeatModeWakeupFeedbackState();
                            sparseArray.put(4645, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4646:
                            bYDAutoEventValue.intValue = getInternalDoorHandleState(3);
                            sparseArray.put(4646, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4647:
                            bYDAutoEventValue.intValue = getInternalDoorHandleState(1);
                            sparseArray.put(4647, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4648:
                            bYDAutoEventValue.intValue = getInternalDoorHandleState(2);
                            sparseArray.put(4648, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4649:
                            bYDAutoEventValue.intValue = getInternalDoorHandleState(4);
                            sparseArray.put(4649, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4650:
                            bYDAutoEventValue.intValue = getElectricPedalConfig();
                            sparseArray.put(4650, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4651:
                            bYDAutoEventValue.intValue = getTailDoorTypeConfig();
                            sparseArray.put(4651, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4653:
                            bYDAutoEventValue.intValue = getWindowPercentageControlConfig();
                            sparseArray.put(4653, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4654:
                            bYDAutoEventValue.intValue = getShiftMode();
                            sparseArray.put(4654, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SYSTEM_CONFIG /* 4725 */:
                            bYDAutoEventValue.intValue = getSystemConfig();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SYSTEM_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4726 */:
                            bYDAutoEventValue.intValue = getSystemConfig();
                            if (bYDAutoEventValue.intValue == 2) {
                                bYDAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(1);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4647, Integer.valueOf(bYDAutoEventValue.intValue));
                            }
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4727 */:
                            bYDAutoEventValue.intValue = getSystemConfig();
                            if (bYDAutoEventValue.intValue == 2) {
                                bYDAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(3);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4646, Integer.valueOf(bYDAutoEventValue.intValue));
                            }
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4728 */:
                            bYDAutoEventValue.intValue = getSystemConfig();
                            if (bYDAutoEventValue.intValue == 2) {
                                bYDAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(2);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4648, Integer.valueOf(bYDAutoEventValue.intValue));
                            }
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4729 */:
                            bYDAutoEventValue.intValue = getSystemConfig();
                            if (bYDAutoEventValue.intValue == 2) {
                                bYDAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(4);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4649, Integer.valueOf(bYDAutoEventValue.intValue));
                            }
                            break;
                        case 4732:
                            bYDAutoEventValue.intValue = hasMessage(219);
                            sparseArray.put(4732, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 4733:
                            bYDAutoEventValue.intValue = hasMessage(220);
                            sparseArray.put(4733, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
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
                    i = -2147482648;
                } else {
                    bYDAutoEventValue.intValue = i;
                }
            }
        }
        return bYDAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr != null && bYDAutoEventValue.intArrayValue != null && iArr.length == bYDAutoEventValue.intArrayValue.length) {
            android.util.Log.i(TAG, "more param event type=" + Arrays.toString(iArr));
            boolean zCreateWindowStateData = true;
            if (iArr.length == 1) {
                if (iArr[0] == 1763 || iArr[0] == 1778 || iArr[0] == 2034 || iArr[0] == 2060) {
                    int i = iArr[0];
                    if (i == 1763) {
                        return setWindowControlState(1, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i == 1778) {
                        return setWindowControlState(3, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i == 2034) {
                        return setWindowControlState(2, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i == 2060) {
                        return setWindowControlState(4, bYDAutoEventValue.intArrayValue[0]);
                    }
                    Log.v(TAG, "no have control window event.");
                    return -2147482648;
                }
            } else if (iArr.length > 1) {
                int[] iArr2 = {0, 0, 0, 0};
                for (int i2 = 0; i2 < iArr.length && (zCreateWindowStateData = createWindowStateData(iArr[i2], bYDAutoEventValue.intArrayValue[i2], iArr2)); i2++) {
                }
                if (zCreateWindowStateData) {
                    return setWindowControlState(iArr2);
                }
            }
        }
        return super.set(iArr, bYDAutoEventValue);
    }

    private boolean createWindowStateData(int i, int i2, int[] iArr) {
        if (i == 1763) {
            iArr[0] = i2;
        } else if (i == 1778) {
            iArr[2] = i2;
        } else if (i == 2034) {
            iArr[1] = i2;
        } else if (i == 2060) {
            iArr[3] = i2;
        } else {
            Log.v(TAG, "no have control window feature_id.");
            return false;
        }
        return true;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else if (obj instanceof byte[]) {
            } else {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        switch (i) {
            case 1688:
                setBackDoorHeightPosition(iIntValue);
                Log.v(TAG, "setElecBackDoorControlState" + iIntValue);
                return 0;
            case 1715:
                setElecBackDoorControlState(iIntValue);
                Log.v(TAG, "setElecBackDoorControlState" + iIntValue);
                return 0;
            case 1721:
                setAllDoorsLock(iIntValue);
                Log.v(TAG, "setDoorLockStatusAll" + iIntValue);
                return 0;
            case 1766:
                setWindowOpenPercent(1, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 1781:
                setWindowOpenPercent(3, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 1783:
                settingLuggage(iIntValue);
                Log.v(TAG, "settingLuggage" + iIntValue);
                return 0;
            case 1784:
                setLeftSlideDoorVoiceControlState(iIntValue);
                Log.v(TAG, "setLeftSlideDoorVoiceControlState" + iIntValue);
                return 0;
            case 1790:
                setWindowOpenPercent(5, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2004:
                turnRearviewMirror(iIntValue);
                Log.v(TAG, "turnRearviewMirror:" + iIntValue);
                return 0;
            case 2013:
                setWindowOpenPercent(7, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2015:
                setAutoExternalRearMirrorFoldState(iIntValue);
                Log.v(TAG, "setAutoExternalRearMirrorFoldS" + iIntValue);
                return 0;
            case 2037:
                setWindowOpenPercent(2, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2063:
                setWindowOpenPercent(4, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2067:
                setRightSlideDoorVoiceControlState(iIntValue);
                Log.v(TAG, "setRightSlideDoorVoiceControlState" + iIntValue);
                return 0;
            case 2085:
                setWindowOpenPercent(6, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2135:
                turnSteeringWheel(iIntValue);
                Log.v(TAG, "turnSteeringWheel:" + iIntValue);
                return 0;
            case 4600:
                setBackDoorHeightPositionMyCar(iIntValue);
                Log.v(TAG, "setBackDoorHeightPosition" + iIntValue);
                return 0;
            case 4641:
                setWaistSupportAdjustment(1, iIntValue);
                return 0;
            case 4642:
                setWaistSupportAdjustment(2, iIntValue);
                return 0;
            case 4644:
                setSeatModeControl(iIntValue);
                return 0;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }

    private void hasMessageListener(int i, int i2, int i3) {
        Log.v(TAG, "hasMessageListener realId is " + i + ",hasMessageId" + i2);
        int i4 = i3 == 0 ? 1 : 0;
        for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener : this.mAbsBydAutoBodyworkListeners) {
            if (i2 != 315) {
                if (i2 == 985) {
                    absBYDAutoBodyworkListener.onMessage5sOnlineStateChanged(i, i4);
                    absBYDAutoBodyworkListener.onHasMessageChanged(i, i4);
                } else if (i2 != 1028 && i2 != 1054 && i2 != 1092 && i2 != 1268 && i2 != 1270) {
                    absBYDAutoBodyworkListener.onHasMessageChanged(i, i4);
                }
            }
            absBYDAutoBodyworkListener.onMessage5sOnlineStateChanged(i, i4);
        }
    }

    public boolean isConnectSuccess() {
        return TsManagerImpl.isConnectSuccess();
    }

    public int getMoonRoofConfigTwo() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMoonRoofConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        return this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfigTwo();
    }

    public int getSeatLumbarSupportConfig(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            int seatLumbarSupportConfigMainRight = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainRight(i);
            int seatLumbarSupportConfigMainLeft = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainLeft(i);
            if (seatLumbarSupportConfigMainRight == 1 || seatLumbarSupportConfigMainLeft == 1) {
                return 1;
            }
        } else if (i == 2) {
            int seatLumbarSupportConfigCoPilotRight = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotRight(i);
            int seatLumbarSupportConfigCoPilotLeft = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotLeft(i);
            if (seatLumbarSupportConfigCoPilotRight == 1 || seatLumbarSupportConfigCoPilotLeft == 1) {
                return 1;
            }
        }
        return 0;
    }

    public int getSeatLumbarSupportConfigMainLeft(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigMainLeft mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainLeft(i);
    }

    public int getSeatLumbarSupportConfigCoPilotLeft(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigCoPilotLeft mForTestValue is 0");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotLeft(i);
    }

    public int getSeatLumbarSupportConfigCoPilotRight(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigCoPilotRight mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotRight(i);
    }

    public int setWaistSupportAdjustment(int i, int i2) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWaistSupportAdjustment mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentMain(i, i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentCoPilot(i, i2);
        }
        return 0;
    }

    public int setWaistSupportAdjustmentCoPilot(int i, int i2) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWaistSupportAdjustmentCoPilot mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentCoPilot(i, i2);
    }

    public int getFourPeopleEnjoyConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFourPeopleEnjoyConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFourPeopleEnjoyConfig();
    }

    public int setSeatModeControl(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSeatModeControl mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSeatModeControl(i);
    }

    public int getSeatModeWakeupFeedbackState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeWakeupFeedbackState mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatModeWakeupFeedbackState();
    }

    public int getInternalDoorHandleState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getInternalDoorHandleState mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateLeftFront(i);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateRightFront(i);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateLeftBack(i);
        }
        if (i == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateRightBack(i);
        }
        return 0;
    }

    public int getElectricPedalConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getElectricPedalConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getElectricPedalConfig();
    }

    public int getTailDoorTypeConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTailDoorTypeConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getTailDoorTypeConfig();
    }

    public int getWindowPercentageControlConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getWindowPercentageControlConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowPercentageControlConfig();
    }

    public int getShiftMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getShiftMode mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getShiftMode();
    }

    public int getEvReadyIndicatorLightState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getEvReadyIndicatorLightState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getEvReadyIndicatorLightState();
    }

    public int getAwningConfigutation() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAwningConfigutation mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getAwningConfigutation();
    }

    public int setSideWindowShadeState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSideWindowShadeState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSideWindowShadeState(i);
    }

    public int getInternalDoorHandleStateByRearBcm(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getInternalDoorHandleStateByRearBcm is 0 execute return");
            return -1;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmlfr();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmlrr();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmrfr();
        }
        if (i == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmrrr();
        }
        return -1;
    }

    public int getSystemConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSystemConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSystemConfig();
    }

    public int setModeCommand(int[] iArr) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setModeCommand mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setModeCommand(iArr);
    }
}
