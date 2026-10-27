package android.hardware.bydauto.bodywork.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BodyworkManagerImpl extends AbsTsMyCar {
    private static final String ACTION_UE_REPORT_RE_REGISTER = "com.ts.bydapi.ACTION_UE_REPORT_RE_REGISTER";
    private static final int C_MALFUNCTION_1 = 1;
    private static final int C_MALFUNCTION_2 = 2;
    private static final int C_MALFUNCTION_3 = 3;
    private static final int C_MALFUNCTION_4 = 4;
    private static final int C_MALFUNCTION_5 = 5;
    private static final int C_MALFUNCTION_6 = 6;
    private static final int C_MALFUNCTION_7 = 7;
    private static final int C_MALFUNCTION_8 = 8;
    private static final String DEFAULT_VIN = "tes0000000000000000";
    private static final String PACKAGE_UE_REPORT = "com.byd.uereport";
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
    private int mScreenControlSlideDoorLeftLeftR;
    private EngineeringSdkManager mSdkManager;
    private TestBodyworkManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoBodyworkListener> mAbsBydAutoBodyworkListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private boolean mHasUeReportListener = false;
    private boolean mWaitUeReportRegister = false;

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
            public void onBodyChanged(int id, int areaId, CarBodyPacket packet) {
                try {
                    BodyworkManagerImpl.this.bodyworkCallback(id, areaId, packet);
                } catch (Exception e) {
                    Log.e(BodyworkManagerImpl.TAG, "bodyworkCallback is error");
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(BodyworkManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
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

    private void enforcePermission(String permissionName) {
    }

    public int getWindowState(int area) {
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
        switch (area) {
            case 1:
                int result = this.mTsManagerImpl.getCarBodyManager().getWindowStateLeftFront();
                break;
            case 2:
                int result2 = this.mTsManagerImpl.getCarBodyManager().getWindowStateLeftRear();
                break;
            case 3:
                int result3 = this.mTsManagerImpl.getCarBodyManager().getWindowStateRightFront();
                break;
            case 4:
                int result4 = this.mTsManagerImpl.getCarBodyManager().getWindowStateRightRear();
                break;
            case 5:
                int result5 = this.mTsManagerImpl.getCarBodyManager().getWindowStateMoonRoof();
                break;
            case 6:
                int result6 = this.mTsManagerImpl.getCarBodyManager().getWindowStateSunShade();
                break;
        }
        return 0;
    }

    public int getDoorState(int area) {
        Log.v(TAG, "getDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int state = 0;
        switch (area) {
            case 1:
                state = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftFront();
                break;
            case 2:
                state = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightFront();
                break;
            case 3:
                state = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftRear();
                break;
            case 4:
                state = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightRear();
                break;
            case 5:
                state = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                break;
            case 6:
                state = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                break;
        }
        if (state == 1) {
            return 0;
        }
        if (state == 0) {
            return -1;
        }
        if (state == 2) {
            return 1;
        }
        return state;
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
        int getNum = this.mTsManagerImpl.getCarBodyManager().getAutoSystemState();
        if (getNum == 1) {
            return 0;
        }
        if (getNum == 2) {
            return 1;
        }
        return getNum;
    }

    public double getSteeringWheelValue(int type) {
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
        return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelValue(type);
    }

    public int getPowerLevel() {
        Log.i(TAG, "getPowerLevel is execute and current process " + this.mContext.getPackageName());
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getPowerLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int getNum = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
        if (getNum == 0) {
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
        int getNum = this.mTsManagerImpl.getCarBodyManager().getBatteryVoltageLevel();
        if (getNum == 0) {
            return 255;
        }
        if (getNum == 1) {
            return 1;
        }
        if (getNum == 2) {
            return 0;
        }
        return getNum;
    }

    public String getAutoVIN() {
        Log.v(TAG, "getAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoVIN mForTestValue is" + this.mForTest + " execute return");
            return "";
        }
        String result = this.mOtaSdkManager.getAutoVin();
        if (result == null || result.length() == 0) {
            return DEFAULT_VIN;
        }
        return result;
    }

    public String getRealAutoVIN() {
        Log.v(TAG, "getRealAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRealAutoVIN mForTestValue is" + this.mForTest + " execute return");
            return "";
        }
        return this.mSdkManager.getRealAutoVin();
    }

    public void settingLuggage(int state) {
        Log.v(TAG, "settingLuggage is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "settingLuggage mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().settingLuggage(state);
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
            int firstValue = this.mTsManagerImpl.getCarBodyManager().getAutoTypeTwo();
            if (firstValue > 0) {
                return firstValue;
            }
            int secondValue = this.mTsManagerImpl.getCarBodyManager().getAutoType();
            if (secondValue != 0) {
                return secondValue;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
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
        int firstValue = this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfigTwo();
        if (firstValue >= 1 && firstValue <= 11) {
            return firstValue;
        }
        int secondValue = this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfig();
        if (secondValue != 0) {
            return secondValue;
        }
        return 0;
    }

    public int getMoonRoofConfigFirst() {
        Log.v(TAG, "getMoonRoofConfigFirst is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMoonRoofConfigFirst mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfigTwo();
    }

    public int getMoonRoofConfigSecond() {
        Log.v(TAG, "getMoonRoofConfigSeconde is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMoonRoofConfigSeconde mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfig();
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

    public int setMoonRoofType(int value) {
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
        return this.mTsManagerImpl.getCarBodyManager().setMoonRoofType(value);
    }

    public int setWindowOpenPercent(int area, int value) {
        Log.v(TAG, "setWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(4000, area, value);
            Log.v(TAG, "setWindowOpenPercent mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (area) {
            case 1:
                int result = this.mTsManagerImpl.getCarBodyManager().setWindowOpenPercentLeftFront(value);
                break;
            case 2:
                int result2 = this.mTsManagerImpl.getCarBodyManager().setWindowOpenPercentRightFront(value);
                break;
            case 3:
                int result3 = this.mTsManagerImpl.getCarBodyManager().setWindowOpenPercentLeftRear(value);
                break;
            case 4:
                int result4 = this.mTsManagerImpl.getCarBodyManager().setWindowOpenPercentRightRear(value);
                break;
            case 5:
                int result5 = this.mTsManagerImpl.getCarBodyManager().setWindowOpenPercentMoonRoof(value);
                break;
            case 6:
                int result6 = this.mTsManagerImpl.getCarBodyManager().setWindowOpenPercentPanel(value);
                break;
            case 7:
                int result7 = this.mTsManagerImpl.getCarBodyManager().setWindowOpenPercentRearPanel(value);
                break;
        }
        return 0;
    }

    public void registerListener(AbsBYDAutoBodyworkListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoBodyworkListener listener, int[] featureIDs) {
        android.util.Log.w(TAG, this.mContext.getPackageName() + " byd_subscribe bodywork listener " + listener + " featureIDs = " + Arrays.toString(featureIDs) + ", mHasUeReportListener:" + this.mHasUeReportListener);
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoBodyworkListener(listener);
            Log.w(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (!this.mAbsBydAutoBodyworkListeners.contains(listener)) {
            if (PACKAGE_UE_REPORT.equals(this.mContext.getPackageName())) {
                this.mHasUeReportListener = true;
                Log.w(TAG, "registerListener for byduereport, mWaitUeReportRegister:" + this.mWaitUeReportRegister);
                if (this.mWaitUeReportRegister) {
                    Log.w(TAG, "registerListener for byduereport, mScreenControlSlideDoorLeftLeftR:" + this.mScreenControlSlideDoorLeftLeftR);
                    listener.onScreenControlSlideDoorChanged(1, this.mScreenControlSlideDoorLeftLeftR);
                    this.mWaitUeReportRegister = false;
                }
            }
            this.mAbsBydAutoBodyworkListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
    }

    public void unregisterListener(IBYDAutoListener listener) {
    }

    public void unregisterListener(AbsBYDAutoBodyworkListener listener) {
        Log.v(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(listener);
            Log.v(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.v(TAG, "unsubscribeBodyworkCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoBodyworkListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getWindowControlState(int area) {
        Log.v(TAG, "getWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowControlState(area);
    }

    public int setWindowControlState(int area, int state) {
        int[] ctrlArr = {0, 0, 0, 0};
        if (area == 1) {
            ctrlArr[0] = state;
        } else if (area == 2) {
            ctrlArr[1] = state;
        } else if (area != 3) {
            if (area == 4) {
                ctrlArr[3] = state;
            } else {
                Log.v(TAG, "no have area.");
            }
        } else {
            ctrlArr[2] = state;
        }
        return setWindowControlState(ctrlArr);
    }

    public int setWindowControlState(int[] state) {
        Log.v(TAG, "setWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setWindowControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWindowControlState(state);
    }

    public int getWindowLockState(int area) {
        Log.v(TAG, "getWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowLockState(area);
    }

    public int setWindowLockState(int status, int areas) {
        Log.v(TAG, "setWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(4001, areas);
            Log.v(TAG, "setWindowLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWindowLockState(status, areas);
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
            return;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
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

    public int setAntiTheftStatus(int status) {
        Log.v(TAG, "setAntiTheftStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAntiTheftStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAntiTheftStatus(status);
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

    public int setElecBackDoorControlState(int state) {
        Log.v(TAG, "setElecBackDoorControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setElecBackDoorControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setElecBackDoorControlState(state);
    }

    public int setBackDoorHeightPosition(int value) {
        Log.v(TAG, "setBackDoorHeightPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBackDoorHeightPosition mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPosition(value);
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

    public int setLeftSlideDoorVoiceControlState(int state) {
        Log.v(TAG, "setLeftSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setLeftSlideDoorVoiceControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLeftSlideDoorVoiceControlState(state);
    }

    public int setRightSlideDoorVoiceControlState(int state) {
        Log.v(TAG, "setRightSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRightSlideDoorVoiceControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setRightSlideDoorVoiceControlState(state);
    }

    public int getWindowOpenPercent(int area) {
        Log.v(TAG, "getWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowOpenPercent mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        switch (area) {
            case 1:
                int result = this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentLeftFront();
                return result;
            case 2:
                int result2 = this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRightFront();
                return result2;
            case 3:
                int result3 = this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentLeftRear();
                return result3;
            case 4:
                int result4 = this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRightRear();
                return result4;
            case 5:
                int result5 = this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercenMoonRoof();
                return result5;
            case 6:
                int result6 = this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentPanel();
                return result6;
            case 7:
                int result7 = this.mTsManagerImpl.getCarBodyManager().getWindowOpenPercentRearPanel();
                return result7;
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

    public int getWindowInitState(int area) {
        Log.v(TAG, "getWindowInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateRearPanel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (area == 1) {
            int result = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
            return result;
        }
        if (area == 2) {
            int result2 = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
            return result2;
        }
        if (area == 3) {
            int result3 = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
            return result3;
        }
        if (area != 4) {
            return 0;
        }
        int result4 = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
        return result4;
    }

    public int getWindowThermalProtectionState(int area) {
        Log.v(TAG, "getWindowThermalProtectionState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowThermalProtectionStateRearPanel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        switch (area) {
            case 1:
                int result = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
                return result;
            case 2:
                int result2 = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
                return result2;
            case 3:
                int result3 = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
                return result3;
            case 4:
                int result4 = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
                return result4;
            case 5:
                int result5 = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateMoonRoof();
                return result5;
            case 6:
                int result6 = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateSunshadePanel();
                return result6;
            case 7:
                int result7 = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRearSunshadePanel();
                return result7;
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

    public int setAllDoorsLock(int state) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setAllDoorsLock mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLock(state);
    }

    public int getDoorLockStatus(int area) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorLockStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (area == 2) {
            int resetResult = this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusLeftRear();
            return resetResult;
        }
        if (area == 3) {
            int resetResult2 = this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusRightFront();
            return resetResult2;
        }
        if (area != 4) {
            return 0;
        }
        int resetResult3 = this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusAreaRightRear();
        return resetResult3;
    }

    public int getRearviewMirrorSupport() {
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorSupport();
    }

    public void turnSteeringWheel(int action) {
        Log.i(TAG, "turnSteeringWheel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnSteeringWheel(action);
        }
    }

    public void turnRearviewMirror(int action) {
        Log.i(TAG, "turnRearviewMirror is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnRearviewMirror(action);
        }
    }

    public float getRearviewMirrorX(int orientation) {
        Log.i(TAG, "getRearviewMirrorX is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (orientation == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXLeft();
        }
        if (orientation == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXRight();
        }
        return 0.0f;
    }

    public float getRearviewMirrorY(int orientation) {
        Log.i(TAG, "getRearviewMirrorY is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (orientation == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorYLeft();
        }
        if (orientation == 2) {
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

    public int setAutoExternalRearMirrorFoldState(int state) {
        Log.v(TAG, "setAutoExternalRearMirrorFoldState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        return this.mTsManagerImpl.getCarBodyManager().setAutoExternalRearMirrorFoldState(state);
    }

    public int setBackDoorOpenedHeight(int value) {
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
        return this.mTsManagerImpl.getCarBodyManager().setBackDoorOpenedHeight(value);
    }

    public int setDriveDistanceShowMode(int value) {
        Log.v(TAG, "setDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setDriveDistanceShowMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowMode(value);
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

    public int getLightStatus(int type) {
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, " getBCMVechileStatus mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        int result = 0;
        switch (type) {
            case 1:
                Log.v(TAG, "getLightSideStatus");
                result = this.mTsManagerImpl.getCarBodyManager().getLightSideStatus();
                break;
            case 2:
                Log.v(TAG, "getLightLowBeamStatus");
                result = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                break;
            case 3:
                Log.v(TAG, "getLightHighBeam");
                result = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                break;
            case 4:
                Log.v(TAG, "getLightLeftTurnSignal");
                result = this.mTsManagerImpl.getCarBodyManager().getLightLeftTurnSignal();
                break;
            case 5:
                Log.v(TAG, "getLightRightTurnSignal");
                result = this.mTsManagerImpl.getCarBodyManager().getLightRightTurnSignal();
                break;
            case 6:
                Log.v(TAG, "getLightFrontFog");
                result = this.mTsManagerImpl.getCarBodyManager().getLightFrontFog();
                break;
            case 7:
                Log.v(TAG, "getLightRearFog");
                result = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                break;
            case 8:
                Log.v(TAG, "getLightFoot");
                result = this.mTsManagerImpl.getCarBodyManager().getLightFoot();
                break;
            case 9:
                Log.v(TAG, "getLightRearPosition");
                result = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                break;
        }
        Log.v(TAG, "getLightStatus Type is:" + type + " And Result is:" + result);
        return result;
    }

    public int getSmartVoiceLimit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSmartVoiceLimit mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSmartVoiceLimit();
    }

    public int setBackDoorHeightPositionMyCar(int value) {
        Log.v(TAG, "setBackDoorHeightPositionMyCar is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBackDoorHeightPositionMyCar mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPositionMyCar(value);
    }

    public int setVoiceCtlSunshadePanelS(int arg0) {
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
        return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlSunshadePanelS(arg0);
    }

    public int setVoiceCtlMoonRoofS(int arg0) {
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
        return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlMoonRoofS(arg0);
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

    public int setWindowsMode(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWindowsMode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWindowsMode(mode);
    }

    public int setSunroofAndSunshade(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSunroofAndSunshade mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSunroofAndSunshade(mode);
    }

    public int setAllDoorsLockScene(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setAllDoorsLockScene mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLockScene(state);
    }

    public int getMoonRoofPercentControlFlag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMoonRoofPercentControlFlag mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().getMoonRoofPercentControlFlag();
    }

    public int setElecSlideDoorState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setElecSlideDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setElecSlideDoorState(state);
    }

    public int hasMessage(int id) {
        Log.v(TAG, "hasMessage is execute id " + id);
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().hasMessage(id);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoBodyworkListener listener : this.mAbsBydAutoBodyworkListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bodyworkCallback(int id, int areaId, CarBodyPacket packet) {
        switch (id) {
            case 557845265:
                for (AbsBYDAutoBodyworkListener listener : this.mAbsBydAutoBodyworkListeners) {
                    listener.onFuelElecLowPowerChanged(packet.getFuelElecLowPowerR());
                }
                break;
            case 557857850:
                for (AbsBYDAutoBodyworkListener listener2 : this.mAbsBydAutoBodyworkListeners) {
                    listener2.onMainSaveOrRecoverStateChanged(2, packet.getRecoverOrSaveParamsChanged5());
                }
                caseFeatureIds(4614, Integer.valueOf(packet.getRecoverOrSaveParamsChanged5()));
                break;
            case 557860644:
                int lightRear = packet.getLightRearPosition();
                for (AbsBYDAutoBodyworkListener listener3 : this.mAbsBydAutoBodyworkListeners) {
                    listener3.onRearLampLightStateChanged(lightRear);
                }
                caseFeatureIds(2006, Integer.valueOf(packet.getLightRearPosition()));
                break;
            case 557862936:
                for (AbsBYDAutoBodyworkListener listener4 : this.mAbsBydAutoBodyworkListeners) {
                    listener4.onWindowOpenPercentChanged(1, packet.getmWindowOpenPercentLeftFrontR());
                }
                int leftFront = packet.getmWindowOpenPercentLeftFrontR();
                caseFeatureIds(2090, Integer.valueOf(leftFront));
                break;
            case 557862937:
                for (AbsBYDAutoBodyworkListener listener5 : this.mAbsBydAutoBodyworkListeners) {
                    listener5.onWindowOpenPercentChanged(3, packet.getmWindowOpenPercentLeftRearR());
                }
                int leftRear = packet.getmWindowOpenPercentLeftRearR();
                caseFeatureIds(2091, Integer.valueOf(leftRear));
                break;
            case 557862938:
                for (AbsBYDAutoBodyworkListener listener6 : this.mAbsBydAutoBodyworkListeners) {
                    listener6.onWindowOpenPercentChanged(2, packet.getmWindowOpenPercentRightFrontR());
                }
                int rightFrontR = packet.getmWindowOpenPercentRightFrontR();
                caseFeatureIds(2092, Integer.valueOf(rightFrontR));
                break;
            case 557862939:
                for (AbsBYDAutoBodyworkListener listener7 : this.mAbsBydAutoBodyworkListeners) {
                    listener7.onWindowOpenPercentChanged(4, packet.getmWindowOpenPercentRightRearR());
                }
                int rightRearR = packet.getmWindowOpenPercentRightRearR();
                caseFeatureIds(2093, Integer.valueOf(rightRearR));
                break;
            case 557862940:
                for (AbsBYDAutoBodyworkListener listener8 : this.mAbsBydAutoBodyworkListeners) {
                    listener8.onWindowOpenPercentChanged(5, packet.getmWindowOpenPercentMoonR());
                }
                int moonR = packet.getmWindowOpenPercentMoonR();
                caseFeatureIds(1789, Integer.valueOf(moonR));
                break;
            case 557862941:
                for (AbsBYDAutoBodyworkListener listener9 : this.mAbsBydAutoBodyworkListeners) {
                    listener9.onWindowOpenPercentChanged(6, packet.getmWindowOpenPercentSunR());
                }
                int percentSunR = packet.getmWindowOpenPercentSunR();
                caseFeatureIds(2084, Integer.valueOf(percentSunR));
                break;
            case 557862942:
                for (AbsBYDAutoBodyworkListener listener10 : this.mAbsBydAutoBodyworkListeners) {
                    listener10.onMoonRoofConfigChanged(packet.getmMoonRoofConfig());
                }
                caseFeatureIds(1786, Integer.valueOf(packet.getmMoonRoofConfig()));
                break;
            case 557862943:
                int doorStateLeftFront = packet.getmDoorStateLeftFront();
                if (doorStateLeftFront == 1) {
                    doorStateLeftFront = 0;
                } else if (doorStateLeftFront == 0) {
                    doorStateLeftFront = -1;
                } else if (doorStateLeftFront == 2) {
                    doorStateLeftFront = 1;
                }
                for (AbsBYDAutoBodyworkListener listener11 : this.mAbsBydAutoBodyworkListeners) {
                    listener11.onDoorStateChanged(1, doorStateLeftFront);
                }
                Log.i(TAG, "onDoorStateChanged: id " + id + ", doorStateLeftFront " + doorStateLeftFront);
                caseFeatureIds(1749, Integer.valueOf(doorStateLeftFront));
                break;
            case 557862944:
                int doorStateRightFront = packet.getmDoorStateRightFront();
                if (doorStateRightFront == 1) {
                    doorStateRightFront = 0;
                } else if (doorStateRightFront == 0) {
                    doorStateRightFront = -1;
                } else if (doorStateRightFront == 2) {
                    doorStateRightFront = 1;
                }
                for (AbsBYDAutoBodyworkListener listener12 : this.mAbsBydAutoBodyworkListeners) {
                    listener12.onDoorStateChanged(2, doorStateRightFront);
                }
                Log.i(TAG, "onDoorStateChanged: id " + id + ", doorStateRightFront " + doorStateRightFront);
                caseFeatureIds(2040, Integer.valueOf(doorStateRightFront));
                break;
            case 557862945:
                int doorStateLeftRear = packet.getmDoorStateLeftRear();
                if (doorStateLeftRear == 1) {
                    doorStateLeftRear = 0;
                } else if (doorStateLeftRear == 0) {
                    doorStateLeftRear = -1;
                } else if (doorStateLeftRear == 2) {
                    doorStateLeftRear = 1;
                }
                for (AbsBYDAutoBodyworkListener listener13 : this.mAbsBydAutoBodyworkListeners) {
                    listener13.onDoorStateChanged(3, doorStateLeftRear);
                }
                Log.i(TAG, "onDoorStateChanged: id " + id + ", doorStateLeftRear " + doorStateLeftRear);
                caseFeatureIds(1750, Integer.valueOf(doorStateLeftRear));
                break;
            case 557862946:
                int doorStateRightRear = packet.getmDoorStateRightRear();
                if (doorStateRightRear == 1) {
                    doorStateRightRear = 0;
                } else if (doorStateRightRear == 0) {
                    doorStateRightRear = -1;
                } else if (doorStateRightRear == 2) {
                    doorStateRightRear = 1;
                }
                for (AbsBYDAutoBodyworkListener listener14 : this.mAbsBydAutoBodyworkListeners) {
                    listener14.onDoorStateChanged(4, doorStateRightRear);
                }
                Log.i(TAG, "onDoorStateChanged: id " + id + ", doorStateRightRear " + doorStateRightRear);
                caseFeatureIds(2041, Integer.valueOf(doorStateRightRear));
                break;
            case 557862947:
                int luggage = packet.getmDoorStateLuggage();
                if (luggage == 1) {
                    luggage = 0;
                } else if (luggage == 0) {
                    luggage = -1;
                } else if (luggage == 2) {
                    luggage = 1;
                }
                for (AbsBYDAutoBodyworkListener listener15 : this.mAbsBydAutoBodyworkListeners) {
                    listener15.onDoorStateChanged(6, luggage);
                }
                caseFeatureIds(1782, Integer.valueOf(luggage));
                break;
            case 557862948:
                int hood = packet.getmDoorStateHood();
                if (hood == 1) {
                    hood = 0;
                } else if (hood == 0) {
                    hood = -1;
                } else if (hood == 2) {
                    hood = 1;
                }
                for (AbsBYDAutoBodyworkListener listener16 : this.mAbsBydAutoBodyworkListeners) {
                    listener16.onDoorStateChanged(5, hood);
                }
                caseFeatureIds(1742, Integer.valueOf(hood));
                break;
            case 557862949:
                for (AbsBYDAutoBodyworkListener listener17 : this.mAbsBydAutoBodyworkListeners) {
                    listener17.onWindowStateChanged(5, packet.getmWindowStateMoonRoofR());
                }
                break;
            case 557862950:
                for (AbsBYDAutoBodyworkListener listener18 : this.mAbsBydAutoBodyworkListeners) {
                    listener18.onWindowblindStateChanged(packet.getmWindowStateSunshadeR());
                }
                caseFeatureIds(2082, Integer.valueOf(packet.getmWindowStateSunshadeR()));
                break;
            case 557862951:
                for (AbsBYDAutoBodyworkListener listener19 : this.mAbsBydAutoBodyworkListeners) {
                    listener19.onEnergyTypeChanged(packet.getmEnergyTypeR());
                }
                break;
            case 557862952:
                for (AbsBYDAutoBodyworkListener listener20 : this.mAbsBydAutoBodyworkListeners) {
                    listener20.onSunroofStateChanged(packet.getmSunRoofStateR());
                }
                int sunRoof = packet.getmSunRoofStateR();
                caseFeatureIds(2079, Integer.valueOf(sunRoof));
                break;
            case 557862953:
                for (AbsBYDAutoBodyworkListener listener21 : this.mAbsBydAutoBodyworkListeners) {
                    listener21.onLeftStrutMorotRunningStateChanged(packet.getLeftStrutMorotRunningState());
                }
                caseFeatureIds(1759, Integer.valueOf(packet.getLeftStrutMorotRunningState()));
                break;
            case 557862954:
                for (AbsBYDAutoBodyworkListener listener22 : this.mAbsBydAutoBodyworkListeners) {
                    listener22.onWindowInitStateChanged(1, packet.getWindowInitStateFL());
                }
                int leftFrontL = packet.getWindowInitStateFL();
                caseFeatureIds(1765, Integer.valueOf(leftFrontL));
                break;
            case 557862955:
                for (AbsBYDAutoBodyworkListener listener23 : this.mAbsBydAutoBodyworkListeners) {
                    listener23.onWindowInitStateChanged(3, packet.getWindowInitStateRL());
                }
                int leftRearR = packet.getWindowInitStateRL();
                caseFeatureIds(2036, Integer.valueOf(leftRearR));
                break;
            case 557862956:
                for (AbsBYDAutoBodyworkListener listener24 : this.mAbsBydAutoBodyworkListeners) {
                    listener24.onWindowInitStateChanged(2, packet.getWindowInitStateFR());
                }
                int rightFront = packet.getWindowInitStateFR();
                caseFeatureIds(1780, Integer.valueOf(rightFront));
                break;
            case 557862957:
                for (AbsBYDAutoBodyworkListener listener25 : this.mAbsBydAutoBodyworkListeners) {
                    listener25.onWindowInitStateChanged(4, packet.getWindowInitStateRR());
                }
                int rightRear = packet.getWindowInitStateRR();
                caseFeatureIds(2062, Integer.valueOf(rightRear));
                break;
            case 557862958:
                for (AbsBYDAutoBodyworkListener listener26 : this.mAbsBydAutoBodyworkListeners) {
                    listener26.onWindowThermalProtectionStateChanged(1, packet.getWindowThermalProtectionStateFl());
                }
                caseFeatureIds(1748, Integer.valueOf(packet.getWindowThermalProtectionStateFl()));
                break;
            case 557862959:
                for (AbsBYDAutoBodyworkListener listener27 : this.mAbsBydAutoBodyworkListeners) {
                    listener27.onWindowThermalProtectionStateChanged(3, packet.getWindowThermalProtectionStateRl());
                }
                caseFeatureIds(1752, Integer.valueOf(packet.getWindowThermalProtectionStateRl()));
                break;
            case 557862960:
                for (AbsBYDAutoBodyworkListener listener28 : this.mAbsBydAutoBodyworkListeners) {
                    listener28.onWindowThermalProtectionStateChanged(2, packet.getWindowThermalProtectionStateFr());
                }
                caseFeatureIds(2039, Integer.valueOf(packet.getWindowThermalProtectionStateFr()));
                break;
            case 557862961:
                for (AbsBYDAutoBodyworkListener listener29 : this.mAbsBydAutoBodyworkListeners) {
                    listener29.onWindowThermalProtectionStateChanged(4, packet.getWindowThermalProtectionStateRr());
                }
                caseFeatureIds(2043, Integer.valueOf(packet.getWindowThermalProtectionStateRr()));
                break;
            case 557862962:
                for (AbsBYDAutoBodyworkListener listener30 : this.mAbsBydAutoBodyworkListeners) {
                    listener30.onWindowThermalProtectionStateChanged(5, packet.getWindowThermalProtectionStateSkylight());
                }
                caseFeatureIds(2068, Integer.valueOf(packet.getWindowThermalProtectionStateSkylight()));
                break;
            case 557862963:
                for (AbsBYDAutoBodyworkListener listener31 : this.mAbsBydAutoBodyworkListeners) {
                    listener31.onWindowThermalProtectionStateChanged(6, packet.getWindowThermalProtectionStateFrontSunshade());
                }
                caseFeatureIds(1735, Integer.valueOf(packet.getWindowThermalProtectionStateFrontSunshade()));
                break;
            case 557862964:
                for (AbsBYDAutoBodyworkListener listener32 : this.mAbsBydAutoBodyworkListeners) {
                    listener32.onWindowThermalProtectionStateChanged(7, packet.getWindowThermalProtectionStateRearSunshade());
                }
                caseFeatureIds(2014, Integer.valueOf(packet.getWindowThermalProtectionStateRearSunshade()));
                break;
            case 557862965:
                for (AbsBYDAutoBodyworkListener listener33 : this.mAbsBydAutoBodyworkListeners) {
                    listener33.onWindowPermitStateChanged(packet.getWindowPermitState());
                }
                caseFeatureIds(1747, Integer.valueOf(packet.getWindowPermitState()));
                break;
            case 557862966:
                for (AbsBYDAutoBodyworkListener listener34 : this.mAbsBydAutoBodyworkListeners) {
                    listener34.onWindoblindInitStateChanged(packet.getWindowblindInitStateR());
                }
                caseFeatureIds(2080, Integer.valueOf(packet.getWindowblindInitStateR()));
                break;
            case 557862967:
                for (AbsBYDAutoBodyworkListener listener35 : this.mAbsBydAutoBodyworkListeners) {
                    listener35.onSunroofInitStateChanged(packet.getSunroofInitStateR());
                }
                caseFeatureIds(2077, Integer.valueOf(packet.getSunroofInitStateR()));
                break;
            case 557862968:
                for (AbsBYDAutoBodyworkListener listener36 : this.mAbsBydAutoBodyworkListeners) {
                    listener36.onSunroofCloseNoticeChanged(packet.getSunroofCloseNoticeR());
                }
                caseFeatureIds(2076, Integer.valueOf(packet.getSunroofCloseNoticeR()));
                break;
            case 557862969:
                for (AbsBYDAutoBodyworkListener listener37 : this.mAbsBydAutoBodyworkListeners) {
                    listener37.onSunroofWindowblindPositionChanged(packet.getSunroofWindowblindPositionR());
                }
                caseFeatureIds(2081, Integer.valueOf(packet.getSunroofWindowblindPositionR()));
                break;
            case 557862970:
                for (AbsBYDAutoBodyworkListener listener38 : this.mAbsBydAutoBodyworkListeners) {
                    listener38.onSunroofPositionChanged(packet.getSunroofPositionR());
                }
                caseFeatureIds(2078, Integer.valueOf(packet.getSunroofPositionR()));
                break;
            case 557862991:
                for (AbsBYDAutoBodyworkListener listener39 : this.mAbsBydAutoBodyworkListeners) {
                    listener39.onWindowOpenPercentChanged(7, packet.getWindowOpenPercentRearPanel());
                }
                caseFeatureIds(2012, Integer.valueOf(packet.getWindowOpenPercentRearPanel()));
                break;
            case 557863013:
                for (AbsBYDAutoBodyworkListener listener40 : this.mAbsBydAutoBodyworkListeners) {
                    listener40.onReadyIndicatorLightStateChanged(packet.getReadyIndicatorLightStateR());
                }
                break;
            case 557863019:
                for (AbsBYDAutoBodyworkListener listener41 : this.mAbsBydAutoBodyworkListeners) {
                    listener41.onRearWindowblindInitStateChanged(packet.getRearWindowblindInitStateNewR());
                }
                caseFeatureIds(2009, Integer.valueOf(packet.getRearWindowblindInitStateNewR()));
                break;
            case 557863049:
                android.util.Log.w(TAG, "bodyworkCallback: ID_SCREEN_CONTROL_SLIDE_DOOR_LEFT_LEFT_R");
                for (AbsBYDAutoBodyworkListener listener42 : this.mAbsBydAutoBodyworkListeners) {
                    listener42.onScreenControlSlideDoorChanged(1, packet.getScreenControlSlideDoorLeftLeftR());
                }
                if (!this.mHasUeReportListener) {
                    android.util.Log.w(TAG, "bodyworkCallback: unsend ID_SCREEN_CONTROL_SLIDE_DOOR_LEFT_LEFT_R because uereport unregistered");
                    this.mWaitUeReportRegister = true;
                    this.mScreenControlSlideDoorLeftLeftR = packet.getScreenControlSlideDoorLeftLeftR();
                    sendBroadcastToUeReport();
                }
                break;
            case 557863050:
                for (AbsBYDAutoBodyworkListener listener43 : this.mAbsBydAutoBodyworkListeners) {
                    listener43.onScreenControlSlideDoorChanged(2, packet.getScreenControlSlideDoorRightLeftR());
                }
                break;
            case 557863051:
                for (AbsBYDAutoBodyworkListener listener44 : this.mAbsBydAutoBodyworkListeners) {
                    listener44.onScreenControlSlideDoorChanged(3, packet.getScreenControlSlideDoorLLeftRightR());
                }
                break;
            case 557863052:
                for (AbsBYDAutoBodyworkListener listener45 : this.mAbsBydAutoBodyworkListeners) {
                    listener45.onScreenControlSlideDoorChanged(4, packet.getScreenControlSlideDoorRightRightR());
                }
                break;
            case 557863053:
                for (AbsBYDAutoBodyworkListener listener46 : this.mAbsBydAutoBodyworkListeners) {
                    listener46.onScreenControlSlideDoorChanged(5, packet.getScreenControlSlideDoorSlidingLeftLeftR());
                }
                break;
            case 557863054:
                for (AbsBYDAutoBodyworkListener listener47 : this.mAbsBydAutoBodyworkListeners) {
                    listener47.onScreenControlSlideDoorChanged(6, packet.getScreenControlSlideDoorSlidingRightLeftR());
                }
                break;
            case 557863055:
                for (AbsBYDAutoBodyworkListener listener48 : this.mAbsBydAutoBodyworkListeners) {
                    listener48.onScreenControlSlideDoorChanged(7, packet.getScreenControlSlideDoorSlidingLeftRightR());
                }
                break;
            case 557863056:
                for (AbsBYDAutoBodyworkListener listener49 : this.mAbsBydAutoBodyworkListeners) {
                    listener49.onScreenControlSlideDoorChanged(8, packet.getScreenControlSlideDoorSlidingRightRightR());
                }
                break;
            case 557863057:
                for (AbsBYDAutoBodyworkListener listener50 : this.mAbsBydAutoBodyworkListeners) {
                    listener50.onEvReadyIndicatorLightStateChanged(packet.getEvReadyIndicatorLightStateR());
                }
                break;
            case 557863061:
                for (AbsBYDAutoBodyworkListener listener51 : this.mAbsBydAutoBodyworkListeners) {
                    listener51.onInternalDoorHandleStateByRearBCMChanged(1, packet.getInternalDoorHandleStateByRearBCMLFR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(packet.getInternalDoorHandleStateByRearBCMLFR()));
                break;
            case 557863062:
                for (AbsBYDAutoBodyworkListener listener52 : this.mAbsBydAutoBodyworkListeners) {
                    listener52.onInternalDoorHandleStateByRearBCMChanged(3, packet.getInternalDoorHandleStateByRearBCMLRR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(packet.getInternalDoorHandleStateByRearBCMLRR()));
                break;
            case 557863063:
                for (AbsBYDAutoBodyworkListener listener53 : this.mAbsBydAutoBodyworkListeners) {
                    listener53.onInternalDoorHandleStateByRearBCMChanged(2, packet.getInternalDoorHandleStateByRearBCMRFR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(packet.getInternalDoorHandleStateByRearBCMRFR()));
                break;
            case 557863064:
                for (AbsBYDAutoBodyworkListener listener54 : this.mAbsBydAutoBodyworkListeners) {
                    listener54.onInternalDoorHandleStateByRearBCMChanged(4, packet.getInternalDoorHandleStateByRearBCMRRR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(packet.getInternalDoorHandleStateByRearBCMRRR()));
                break;
            case 557863067:
                for (AbsBYDAutoBodyworkListener listener55 : this.mAbsBydAutoBodyworkListeners) {
                    listener55.onThirdRowSeatsExecutionResultsChanged(packet.getThirdRowSeatsExecutionResultsR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_THIRD_ROW_SEATS_FEEDBACK_ON_THE_EXECUTION_RESULTS, Integer.valueOf(packet.getThirdRowSeatsExecutionResultsR()));
                break;
            case 557863068:
                for (AbsBYDAutoBodyworkListener listener56 : this.mAbsBydAutoBodyworkListeners) {
                    listener56.onLeftSeatRailsRow3FeedbackPositionChanged(packet.getLeftSeatRailsRow3FeedbackPositionR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_LEFT_SEAT_RAILS_ROW3_FEEDBACK_POSITION, Integer.valueOf(packet.getLeftSeatRailsRow3FeedbackPositionR()));
                break;
            case 557863069:
                for (AbsBYDAutoBodyworkListener listener57 : this.mAbsBydAutoBodyworkListeners) {
                    listener57.onRightSeatRailsRow3FeedbackPositionChanged(packet.getRightSeatRailsRow3FeedbackPositionR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_RIGHT_SEAT_RAILS_ROW3_FEEDBACK_POSITION, Integer.valueOf(packet.getRightSeatRailsRow3FeedbackPositionR()));
                break;
            case 557863070:
                for (AbsBYDAutoBodyworkListener listener58 : this.mAbsBydAutoBodyworkListeners) {
                    listener58.onLeftSeatRow3NonElectricallyAdjustStateChanged(packet.getLeftSeatRow3NonElectricallyAdjustStateR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_LEFT_SEAT_ROW3_NON_ELECTRICALLY_ADJUSTABLE, Integer.valueOf(packet.getLeftSeatRow3NonElectricallyAdjustStateR()));
                break;
            case 557863071:
                for (AbsBYDAutoBodyworkListener listener59 : this.mAbsBydAutoBodyworkListeners) {
                    listener59.onFseSeatSystemWorkModeChanged(packet.getFseSeatSystemWorkModeR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_FSE_SEAT_SYSTEM_WORK_MODE, Integer.valueOf(packet.getThirdRowSeatsExecutionResultsR()));
                break;
            case 557863076:
                for (AbsBYDAutoBodyworkListener listener60 : this.mAbsBydAutoBodyworkListeners) {
                    listener60.onSeatModeCurFeedbackStatusChanged2(packet.getSeatModeCurFeedbackStatus2R());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_CURRENT_STATUS_FEEDBACK_TOPAD_hal_only, Integer.valueOf(packet.getSeatModeCurFeedbackStatus2R()));
                break;
            case 557863077:
                for (AbsBYDAutoBodyworkListener listener61 : this.mAbsBydAutoBodyworkListeners) {
                    listener61.onSeatModeSuccessFeedbackStatusChanged2(packet.getSeatModeSuccessFeedbackStatus2R());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_SUCCESS_FEEDBACK_TOPAD_hal_only, Integer.valueOf(packet.getSeatModeSuccessFeedbackStatus2R()));
                break;
            case 557863078:
                for (AbsBYDAutoBodyworkListener listener62 : this.mAbsBydAutoBodyworkListeners) {
                    listener62.onSeatModeFailFeedbackStatusChanged2(packet.getSeatModeFailFeedbackStatus2R());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_FAIL_FEEDBACK_TOPAD_hal_only, Integer.valueOf(packet.getSeatModeFailFeedbackStatus2R()));
                break;
            case 557863079:
                for (AbsBYDAutoBodyworkListener listener63 : this.mAbsBydAutoBodyworkListeners) {
                    listener63.onSeatModeCurFeedbackStatusChanged(packet.getSeatModeCurFeedbackStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_CURRENT_STATUS_FEEDBACK_TOPAD, Integer.valueOf(packet.getSeatModeCurFeedbackStatusR()));
                break;
            case 557863080:
                for (AbsBYDAutoBodyworkListener listener64 : this.mAbsBydAutoBodyworkListeners) {
                    listener64.onSeatModeSuccessFeedbackStatusChanged(packet.getSeatModeSuccessFeedbackStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_SUCCESS_FEEDBACK_TOPAD, Integer.valueOf(packet.getSeatModeSuccessFeedbackStatusR()));
                break;
            case 557863081:
                for (AbsBYDAutoBodyworkListener listener65 : this.mAbsBydAutoBodyworkListeners) {
                    listener65.onSeatModeFailFeedbackStatusChanged(packet.getSeatModeFailFeedbackStatusR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_FAIL_FEEDBACK_TOPAD, Integer.valueOf(packet.getSeatModeFailFeedbackStatusR()));
                break;
            case 557863086:
                for (AbsBYDAutoBodyworkListener listener66 : this.mAbsBydAutoBodyworkListeners) {
                    listener66.onLeftMidDoorVoiceWakeUpChanged(packet.getLeftMidDoorVoiceWakeUpChangedR());
                }
                break;
            case 557863087:
                for (AbsBYDAutoBodyworkListener listener67 : this.mAbsBydAutoBodyworkListeners) {
                    listener67.onRightMidDoorVoiceWakeUpChanged(packet.getRightMidDoorVoiceWakeUpChangedR());
                }
                break;
            case 557868039:
                for (AbsBYDAutoBodyworkListener listener68 : this.mAbsBydAutoBodyworkListeners) {
                    listener68.onShiftModeChanged(packet.getShiftModeR());
                }
                break;
            case 557875227:
                for (AbsBYDAutoBodyworkListener listener69 : this.mAbsBydAutoBodyworkListeners) {
                    listener69.onBatteryVoltageLevelLowTips(packet.getBatteryVoltageLevelLowTips());
                }
                break;
            case 557945600:
                int hasMessageId = packet.getCan055R()[0];
                int hasMessageState = packet.getCan055R()[1];
                int realId = CanIds.getRealCanId(hasMessageId);
                hasMessageListener(realId, hasMessageId, hasMessageState);
                break;
            case 557945603:
                int hasMessageId2 = packet.getCan0d5()[0];
                int hasMessageState2 = packet.getCan0d5()[1];
                int realId2 = CanIds.getRealCanId(hasMessageId2);
                hasMessageListener(realId2, hasMessageId2, hasMessageState2);
                break;
            case 557945605:
                int hasMessageId3 = packet.getCan10d()[0];
                int hasMessageState3 = packet.getCan10d()[1];
                int realId3 = CanIds.getRealCanId(hasMessageId3);
                hasMessageListener(realId3, hasMessageId3, hasMessageState3);
                break;
            case 557945606:
                int hasMessageId4 = packet.getCan121()[0];
                int hasMessageState4 = packet.getCan121()[1];
                int realId4 = CanIds.getRealCanId(hasMessageId4);
                hasMessageListener(realId4, hasMessageId4, hasMessageState4);
                break;
            case 557945607:
                int hasMessageId5 = packet.getCan123()[0];
                int hasMessageState5 = packet.getCan123()[1];
                int realId5 = CanIds.getRealCanId(hasMessageId5);
                hasMessageListener(realId5, hasMessageId5, hasMessageState5);
                break;
            case 557945608:
                int hasMessageId6 = packet.getCan12d()[0];
                int hasMessageState6 = packet.getCan12d()[1];
                int realId6 = CanIds.getRealCanId(hasMessageId6);
                hasMessageListener(realId6, hasMessageId6, hasMessageState6);
                break;
            case 557945609:
                int hasMessageId7 = packet.getCan12f()[0];
                int hasMessageState7 = packet.getCan12f()[1];
                int realId7 = CanIds.getRealCanId(hasMessageId7);
                hasMessageListener(realId7, hasMessageId7, hasMessageState7);
                break;
            case 557945610:
                int hasMessageId8 = packet.getCan133()[0];
                int hasMessageState8 = packet.getCan133()[1];
                int realId8 = CanIds.getRealCanId(hasMessageId8);
                hasMessageListener(realId8, hasMessageId8, hasMessageState8);
                break;
            case 557945611:
                int hasMessageId9 = packet.getCan138()[0];
                int hasMessageState9 = packet.getCan138()[1];
                int realId9 = CanIds.getRealCanId(hasMessageId9);
                hasMessageListener(realId9, hasMessageId9, hasMessageState9);
                break;
            case 557945612:
                int hasMessageId10 = packet.getCan13f()[0];
                int hasMessageState10 = packet.getCan13f()[1];
                int realId10 = CanIds.getRealCanId(hasMessageId10);
                hasMessageListener(realId10, hasMessageId10, hasMessageState10);
                break;
            case 557945613:
                int hasMessageId11 = packet.getCan144()[0];
                int hasMessageState11 = packet.getCan144()[1];
                int realId11 = CanIds.getRealCanId(hasMessageId11);
                hasMessageListener(realId11, hasMessageId11, hasMessageState11);
                break;
            case 557945614:
                int hasMessageId12 = packet.getCan151()[0];
                int hasMessageState12 = packet.getCan151()[1];
                int realId12 = CanIds.getRealCanId(hasMessageId12);
                hasMessageListener(realId12, hasMessageId12, hasMessageState12);
                break;
            case 557945615:
                int hasMessageId13 = packet.getCan167()[0];
                int hasMessageState13 = packet.getCan167()[1];
                int realId13 = CanIds.getRealCanId(hasMessageId13);
                hasMessageListener(realId13, hasMessageId13, hasMessageState13);
                break;
            case 557945616:
                int hasMessageId14 = packet.getCan18a()[0];
                int hasMessageState14 = packet.getCan18a()[1];
                int realId14 = CanIds.getRealCanId(hasMessageId14);
                hasMessageListener(realId14, hasMessageId14, hasMessageState14);
                break;
            case 557945617:
                int hasMessageId15 = packet.getCan19f()[0];
                int hasMessageState15 = packet.getCan19f()[1];
                int realId15 = CanIds.getRealCanId(hasMessageId15);
                hasMessageListener(realId15, hasMessageId15, hasMessageState15);
                break;
            case 557945618:
                int hasMessageId16 = packet.getCan1c0()[0];
                int hasMessageState16 = packet.getCan1c0()[1];
                int realId16 = CanIds.getRealCanId(hasMessageId16);
                hasMessageListener(realId16, hasMessageId16, hasMessageState16);
                break;
            case 557945619:
                int hasMessageId17 = packet.getCan1d1()[0];
                int hasMessageState17 = packet.getCan1d1()[1];
                int realId17 = CanIds.getRealCanId(hasMessageId17);
                hasMessageListener(realId17, hasMessageId17, hasMessageState17);
                break;
            case 557945620:
                int hasMessageId18 = packet.getCan218()[0];
                int hasMessageState18 = packet.getCan218()[1];
                int realId18 = CanIds.getRealCanId(hasMessageId18);
                hasMessageListener(realId18, hasMessageId18, hasMessageState18);
                break;
            case 557945621:
                int hasMessageId19 = packet.getCan223()[0];
                int hasMessageState19 = packet.getCan223()[1];
                int realId19 = CanIds.getRealCanId(hasMessageId19);
                hasMessageListener(realId19, hasMessageId19, hasMessageState19);
                break;
            case 557945622:
                int hasMessageId20 = packet.getCan233()[0];
                int hasMessageState20 = packet.getCan233()[1];
                int realId20 = CanIds.getRealCanId(hasMessageId20);
                hasMessageListener(realId20, hasMessageId20, hasMessageState20);
                break;
            case 557945623:
                int hasMessageId21 = packet.getCan235()[0];
                int hasMessageState21 = packet.getCan235()[1];
                int realId21 = CanIds.getRealCanId(hasMessageId21);
                hasMessageListener(realId21, hasMessageId21, hasMessageState21);
                break;
            case 557945624:
                int hasMessageId22 = packet.getCan23a()[0];
                int hasMessageState22 = packet.getCan23a()[1];
                int realId22 = CanIds.getRealCanId(hasMessageId22);
                hasMessageListener(realId22, hasMessageId22, hasMessageState22);
                break;
            case 557945625:
                int hasMessageId23 = packet.getCan242()[0];
                int hasMessageState23 = packet.getCan242()[1];
                int realId23 = CanIds.getRealCanId(hasMessageId23);
                hasMessageListener(realId23, hasMessageId23, hasMessageState23);
                break;
            case 557945626:
                int hasMessageId24 = packet.getCan245()[0];
                int hasMessageState24 = packet.getCan245()[1];
                int realId24 = CanIds.getRealCanId(hasMessageId24);
                hasMessageListener(realId24, hasMessageId24, hasMessageState24);
                break;
            case 557945627:
                int hasMessageId25 = packet.getCan248()[0];
                int hasMessageState25 = packet.getCan248()[1];
                int realId25 = CanIds.getRealCanId(hasMessageId25);
                hasMessageListener(realId25, hasMessageId25, hasMessageState25);
                break;
            case 557945628:
                int hasMessageId26 = packet.getCan24a()[0];
                int hasMessageState26 = packet.getCan24a()[1];
                int realId26 = CanIds.getRealCanId(hasMessageId26);
                hasMessageListener(realId26, hasMessageId26, hasMessageState26);
                break;
            case 557945629:
                int hasMessageId27 = packet.getCan24c()[0];
                int hasMessageState27 = packet.getCan24c()[1];
                int realId27 = CanIds.getRealCanId(hasMessageId27);
                hasMessageListener(realId27, hasMessageId27, hasMessageState27);
                break;
            case 557945630:
                int hasMessageId28 = packet.getCan25d()[0];
                int hasMessageState28 = packet.getCan25d()[1];
                int realId28 = CanIds.getRealCanId(hasMessageId28);
                hasMessageListener(realId28, hasMessageId28, hasMessageState28);
                break;
            case 557945631:
                int hasMessageId29 = packet.getCan261()[0];
                int hasMessageState29 = packet.getCan261()[1];
                int realId29 = CanIds.getRealCanId(hasMessageId29);
                hasMessageListener(realId29, hasMessageId29, hasMessageState29);
                break;
            case 557945632:
                int hasMessageId30 = packet.getCan26f()[0];
                int hasMessageState30 = packet.getCan26f()[1];
                int realId30 = CanIds.getRealCanId(hasMessageId30);
                hasMessageListener(realId30, hasMessageId30, hasMessageState30);
                break;
            case 557945633:
                int hasMessageId31 = packet.getCan271()[0];
                int hasMessageState31 = packet.getCan271()[1];
                int realId31 = CanIds.getRealCanId(hasMessageId31);
                hasMessageListener(realId31, hasMessageId31, hasMessageState31);
                break;
            case 557945634:
                int hasMessageId32 = packet.getCan272()[0];
                int hasMessageState32 = packet.getCan272()[1];
                int realId32 = CanIds.getRealCanId(hasMessageId32);
                hasMessageListener(realId32, hasMessageId32, hasMessageState32);
                break;
            case 557945635:
                int hasMessageId33 = packet.getCan275()[0];
                int hasMessageState33 = packet.getCan275()[1];
                int realId33 = CanIds.getRealCanId(hasMessageId33);
                hasMessageListener(realId33, hasMessageId33, hasMessageState33);
                break;
            case 557945636:
                int hasMessageId34 = packet.getCan27a()[0];
                int hasMessageState34 = packet.getCan27a()[1];
                int realId34 = CanIds.getRealCanId(hasMessageId34);
                hasMessageListener(realId34, hasMessageId34, hasMessageState34);
                break;
            case 557945637:
                int hasMessageId35 = packet.getCan287()[0];
                int hasMessageState35 = packet.getCan287()[1];
                int realId35 = CanIds.getRealCanId(hasMessageId35);
                hasMessageListener(realId35, hasMessageId35, hasMessageState35);
                break;
            case 557945638:
                int hasMessageId36 = packet.getCan288()[0];
                int hasMessageState36 = packet.getCan288()[1];
                int realId36 = CanIds.getRealCanId(hasMessageId36);
                hasMessageListener(realId36, hasMessageId36, hasMessageState36);
                break;
            case 557945639:
                int hasMessageId37 = packet.getCan28f()[0];
                int hasMessageState37 = packet.getCan28f()[1];
                int realId37 = CanIds.getRealCanId(hasMessageId37);
                hasMessageListener(realId37, hasMessageId37, hasMessageState37);
                break;
            case 557945640:
                int hasMessageId38 = packet.getCan294()[0];
                int hasMessageState38 = packet.getCan294()[1];
                int realId38 = CanIds.getRealCanId(hasMessageId38);
                hasMessageListener(realId38, hasMessageId38, hasMessageState38);
                break;
            case 557945641:
                int hasMessageId39 = packet.getCan296()[0];
                int hasMessageState39 = packet.getCan296()[1];
                int realId39 = CanIds.getRealCanId(hasMessageId39);
                hasMessageListener(realId39, hasMessageId39, hasMessageState39);
                break;
            case 557945642:
                int hasMessageId40 = packet.getCan29c()[0];
                int hasMessageState40 = packet.getCan29c()[1];
                int realId40 = CanIds.getRealCanId(hasMessageId40);
                hasMessageListener(realId40, hasMessageId40, hasMessageState40);
                break;
            case 557945643:
                int hasMessageId41 = packet.getCan29e()[0];
                int hasMessageState41 = packet.getCan29e()[1];
                int realId41 = CanIds.getRealCanId(hasMessageId41);
                hasMessageListener(realId41, hasMessageId41, hasMessageState41);
                break;
            case 557945644:
                int hasMessageId42 = packet.getCan2a4()[0];
                int hasMessageState42 = packet.getCan2a4()[1];
                int realId42 = CanIds.getRealCanId(hasMessageId42);
                hasMessageListener(realId42, hasMessageId42, hasMessageState42);
                break;
            case 557945645:
                int hasMessageId43 = packet.getCan2a6()[0];
                int hasMessageState43 = packet.getCan2a6()[1];
                int realId43 = CanIds.getRealCanId(hasMessageId43);
                hasMessageListener(realId43, hasMessageId43, hasMessageState43);
                break;
            case 557945646:
                int hasMessageId44 = packet.getCan2a7()[0];
                int hasMessageState44 = packet.getCan2a7()[1];
                int realId44 = CanIds.getRealCanId(hasMessageId44);
                hasMessageListener(realId44, hasMessageId44, hasMessageState44);
                break;
            case 557945647:
                int hasMessageId45 = packet.getCan2b0()[0];
                int hasMessageState45 = packet.getCan2b0()[1];
                int realId45 = CanIds.getRealCanId(hasMessageId45);
                hasMessageListener(realId45, hasMessageId45, hasMessageState45);
                break;
            case 557945648:
                int hasMessageId46 = packet.getCan2b4()[0];
                int hasMessageState46 = packet.getCan2b4()[1];
                int realId46 = CanIds.getRealCanId(hasMessageId46);
                hasMessageListener(realId46, hasMessageId46, hasMessageState46);
                break;
            case 557945649:
                int hasMessageId47 = packet.getCan2b6()[0];
                int hasMessageState47 = packet.getCan2b6()[1];
                int realId47 = CanIds.getRealCanId(hasMessageId47);
                hasMessageListener(realId47, hasMessageId47, hasMessageState47);
                break;
            case 557945650:
                int hasMessageId48 = packet.getCan2ba()[0];
                int hasMessageState48 = packet.getCan2ba()[1];
                int realId48 = CanIds.getRealCanId(hasMessageId48);
                hasMessageListener(realId48, hasMessageId48, hasMessageState48);
                break;
            case 557945651:
                int hasMessageId49 = packet.getCan2bc()[0];
                int hasMessageState49 = packet.getCan2bc()[1];
                int realId49 = CanIds.getRealCanId(hasMessageId49);
                hasMessageListener(realId49, hasMessageId49, hasMessageState49);
                break;
            case 557945652:
                int hasMessageId50 = packet.getCan2c0()[0];
                int hasMessageState50 = packet.getCan2c0()[1];
                int realId50 = CanIds.getRealCanId(hasMessageId50);
                hasMessageListener(realId50, hasMessageId50, hasMessageState50);
                break;
            case 557945653:
                int hasMessageId51 = packet.getCan2d5()[0];
                int hasMessageState51 = packet.getCan2d5()[1];
                int realId51 = CanIds.getRealCanId(hasMessageId51);
                hasMessageListener(realId51, hasMessageId51, hasMessageState51);
                break;
            case 557945654:
                int hasMessageId52 = packet.getCan2eb()[0];
                int hasMessageState52 = packet.getCan2eb()[1];
                int realId52 = CanIds.getRealCanId(hasMessageId52);
                hasMessageListener(realId52, hasMessageId52, hasMessageState52);
                break;
            case 557945655:
                int hasMessageId53 = packet.getCan2f0()[0];
                int hasMessageState53 = packet.getCan2f0()[1];
                int realId53 = CanIds.getRealCanId(hasMessageId53);
                hasMessageListener(realId53, hasMessageId53, hasMessageState53);
                break;
            case 557945656:
                int hasMessageId54 = packet.getCan30d()[0];
                int hasMessageState54 = packet.getCan30d()[1];
                int realId54 = CanIds.getRealCanId(hasMessageId54);
                hasMessageListener(realId54, hasMessageId54, hasMessageState54);
                break;
            case 557945657:
                int hasMessageId55 = packet.getCan31e()[0];
                int hasMessageState55 = packet.getCan31e()[1];
                int realId55 = CanIds.getRealCanId(hasMessageId55);
                hasMessageListener(realId55, hasMessageId55, hasMessageState55);
                break;
            case 557945658:
                int hasMessageId56 = packet.getCan321()[0];
                int hasMessageState56 = packet.getCan321()[1];
                int realId56 = CanIds.getRealCanId(hasMessageId56);
                hasMessageListener(realId56, hasMessageId56, hasMessageState56);
                break;
            case 557945659:
                int hasMessageId57 = packet.getCan323()[0];
                int hasMessageState57 = packet.getCan323()[1];
                int realId57 = CanIds.getRealCanId(hasMessageId57);
                hasMessageListener(realId57, hasMessageId57, hasMessageState57);
                break;
            case 557945660:
                int hasMessageId58 = packet.getCan32d()[0];
                int hasMessageState58 = packet.getCan32d()[1];
                int realId58 = CanIds.getRealCanId(hasMessageId58);
                hasMessageListener(realId58, hasMessageId58, hasMessageState58);
                break;
            case 557945661:
                int hasMessageId59 = packet.getCan334()[0];
                int hasMessageState59 = packet.getCan334()[1];
                int realId59 = CanIds.getRealCanId(hasMessageId59);
                hasMessageListener(realId59, hasMessageId59, hasMessageState59);
                break;
            case 557945662:
                int hasMessageId60 = packet.getCan337()[0];
                int hasMessageState60 = packet.getCan337()[1];
                int realId60 = CanIds.getRealCanId(hasMessageId60);
                hasMessageListener(realId60, hasMessageId60, hasMessageState60);
                break;
            case 557945663:
                int hasMessageId61 = packet.getCan341()[0];
                int hasMessageState61 = packet.getCan341()[1];
                int realId61 = CanIds.getRealCanId(hasMessageId61);
                hasMessageListener(realId61, hasMessageId61, hasMessageState61);
                break;
            case 557945664:
                int hasMessageId62 = packet.getCan342()[0];
                int hasMessageState62 = packet.getCan342()[1];
                int realId62 = CanIds.getRealCanId(hasMessageId62);
                hasMessageListener(realId62, hasMessageId62, hasMessageState62);
                break;
            case 557945665:
                int hasMessageId63 = packet.getCan346()[0];
                int hasMessageState63 = packet.getCan346()[1];
                int realId63 = CanIds.getRealCanId(hasMessageId63);
                hasMessageListener(realId63, hasMessageId63, hasMessageState63);
                break;
            case 557945666:
                int hasMessageId64 = packet.getCan347()[0];
                int hasMessageState64 = packet.getCan347()[1];
                int realId64 = CanIds.getRealCanId(hasMessageId64);
                hasMessageListener(realId64, hasMessageId64, hasMessageState64);
                break;
            case 557945667:
                int hasMessageId65 = packet.getCan34a()[0];
                int hasMessageState65 = packet.getCan34a()[1];
                int realId65 = CanIds.getRealCanId(hasMessageId65);
                hasMessageListener(realId65, hasMessageId65, hasMessageState65);
                break;
            case 557945668:
                int hasMessageId66 = packet.getCan34f()[0];
                int hasMessageState66 = packet.getCan34f()[1];
                int realId66 = CanIds.getRealCanId(hasMessageId66);
                hasMessageListener(realId66, hasMessageId66, hasMessageState66);
                break;
            case 557945669:
                int hasMessageId67 = packet.getCan36b()[0];
                int hasMessageState67 = packet.getCan36b()[1];
                int realId67 = CanIds.getRealCanId(hasMessageId67);
                hasMessageListener(realId67, hasMessageId67, hasMessageState67);
                break;
            case 557945670:
                int hasMessageId68 = packet.getCan36d()[0];
                int hasMessageState68 = packet.getCan36d()[1];
                int realId68 = CanIds.getRealCanId(hasMessageId68);
                hasMessageListener(realId68, hasMessageId68, hasMessageState68);
                break;
            case 557945671:
                int hasMessageId69 = packet.getCan36e()[0];
                int hasMessageState69 = packet.getCan36e()[1];
                int realId69 = CanIds.getRealCanId(hasMessageId69);
                hasMessageListener(realId69, hasMessageId69, hasMessageState69);
                break;
            case 557945672:
                int hasMessageId70 = packet.getCan37f()[0];
                int hasMessageState70 = packet.getCan37f()[1];
                int realId70 = CanIds.getRealCanId(hasMessageId70);
                hasMessageListener(realId70, hasMessageId70, hasMessageState70);
                break;
            case 557945673:
                int hasMessageId71 = packet.getCan38a()[0];
                int hasMessageState71 = packet.getCan38a()[1];
                int realId71 = CanIds.getRealCanId(hasMessageId71);
                hasMessageListener(realId71, hasMessageId71, hasMessageState71);
                break;
            case 557945674:
                int hasMessageId72 = packet.getCan38b()[0];
                int hasMessageState72 = packet.getCan38b()[1];
                int realId72 = CanIds.getRealCanId(hasMessageId72);
                hasMessageListener(realId72, hasMessageId72, hasMessageState72);
                break;
            case 557945675:
                int hasMessageId73 = packet.getCan394()[0];
                int hasMessageState73 = packet.getCan394()[1];
                int realId73 = CanIds.getRealCanId(hasMessageId73);
                hasMessageListener(realId73, hasMessageId73, hasMessageState73);
                break;
            case 557945676:
                int hasMessageId74 = packet.getCan396()[0];
                int hasMessageState74 = packet.getCan396()[1];
                int realId74 = CanIds.getRealCanId(hasMessageId74);
                hasMessageListener(realId74, hasMessageId74, hasMessageState74);
                break;
            case 557945677:
                int hasMessageId75 = packet.getCan3a0()[0];
                int hasMessageState75 = packet.getCan3a0()[1];
                int realId75 = CanIds.getRealCanId(hasMessageId75);
                hasMessageListener(realId75, hasMessageId75, hasMessageState75);
                break;
            case 557945678:
                int hasMessageId76 = packet.getCan3a7()[0];
                int hasMessageState76 = packet.getCan3a7()[1];
                int realId76 = CanIds.getRealCanId(hasMessageId76);
                hasMessageListener(realId76, hasMessageId76, hasMessageState76);
                break;
            case 557945679:
                int hasMessageId77 = packet.getCan3ac()[0];
                int hasMessageState77 = packet.getCan3ac()[1];
                int realId77 = CanIds.getRealCanId(hasMessageId77);
                hasMessageListener(realId77, hasMessageId77, hasMessageState77);
                break;
            case 557945680:
                int hasMessageId78 = packet.getCan3ad()[0];
                int hasMessageState78 = packet.getCan3ad()[1];
                int realId78 = CanIds.getRealCanId(hasMessageId78);
                hasMessageListener(realId78, hasMessageId78, hasMessageState78);
                break;
            case 557945681:
                int hasMessageId79 = packet.getCan3b0()[0];
                int hasMessageState79 = packet.getCan3b0()[1];
                int realId79 = CanIds.getRealCanId(hasMessageId79);
                hasMessageListener(realId79, hasMessageId79, hasMessageState79);
                break;
            case 557945682:
                int hasMessageId80 = packet.getCan3b3()[0];
                int hasMessageState80 = packet.getCan3b3()[1];
                int realId80 = CanIds.getRealCanId(hasMessageId80);
                hasMessageListener(realId80, hasMessageId80, hasMessageState80);
                break;
            case 557945683:
                int hasMessageId81 = packet.getCan3b4()[0];
                int hasMessageState81 = packet.getCan3b4()[1];
                int realId81 = CanIds.getRealCanId(hasMessageId81);
                hasMessageListener(realId81, hasMessageId81, hasMessageState81);
                break;
            case 557945684:
                int hasMessageId82 = packet.getCan3bb()[0];
                int hasMessageState82 = packet.getCan3bb()[1];
                int realId82 = CanIds.getRealCanId(hasMessageId82);
                hasMessageListener(realId82, hasMessageId82, hasMessageState82);
                break;
            case 557945685:
                int hasMessageId83 = packet.getCan3bd()[0];
                int hasMessageState83 = packet.getCan3bd()[1];
                int realId83 = CanIds.getRealCanId(hasMessageId83);
                hasMessageListener(realId83, hasMessageId83, hasMessageState83);
                break;
            case 557945686:
                int hasMessageId84 = packet.getCan3c1()[0];
                int hasMessageState84 = packet.getCan3c1()[1];
                int realId84 = CanIds.getRealCanId(hasMessageId84);
                hasMessageListener(realId84, hasMessageId84, hasMessageState84);
                break;
            case 557945687:
                int hasMessageId85 = packet.getCan3c8()[0];
                int hasMessageState85 = packet.getCan3c8()[1];
                int realId85 = CanIds.getRealCanId(hasMessageId85);
                hasMessageListener(realId85, hasMessageId85, hasMessageState85);
                break;
            case 557945688:
                int hasMessageId86 = packet.getCan3ca()[0];
                int hasMessageState86 = packet.getCan3ca()[1];
                int realId86 = CanIds.getRealCanId(hasMessageId86);
                hasMessageListener(realId86, hasMessageId86, hasMessageState86);
                break;
            case 557945689:
                int hasMessageId87 = packet.getCan3cd()[0];
                int hasMessageState87 = packet.getCan3cd()[1];
                int realId87 = CanIds.getRealCanId(hasMessageId87);
                hasMessageListener(realId87, hasMessageId87, hasMessageState87);
                break;
            case 557945690:
                int hasMessageId88 = packet.getCan3d9()[0];
                int hasMessageState88 = packet.getCan3d9()[1];
                int realId88 = CanIds.getRealCanId(hasMessageId88);
                hasMessageListener(realId88, hasMessageId88, hasMessageState88);
                break;
            case 557945691:
                int hasMessageId89 = packet.getCan3da()[0];
                int hasMessageState89 = packet.getCan3da()[1];
                int realId89 = CanIds.getRealCanId(hasMessageId89);
                hasMessageListener(realId89, hasMessageId89, hasMessageState89);
                break;
            case 557945692:
                int hasMessageId90 = packet.getCan3f1()[0];
                int hasMessageState90 = packet.getCan3f1()[1];
                int realId90 = CanIds.getRealCanId(hasMessageId90);
                hasMessageListener(realId90, hasMessageId90, hasMessageState90);
                break;
            case 557945693:
                int hasMessageId91 = packet.getCan3f3()[0];
                int hasMessageState91 = packet.getCan3f3()[1];
                int realId91 = CanIds.getRealCanId(hasMessageId91);
                hasMessageListener(realId91, hasMessageId91, hasMessageState91);
                break;
            case 557945694:
                int hasMessageId92 = packet.getCan3fe()[0];
                int hasMessageState92 = packet.getCan3fe()[1];
                int realId92 = CanIds.getRealCanId(hasMessageId92);
                hasMessageListener(realId92, hasMessageId92, hasMessageState92);
                break;
            case 557945695:
                int hasMessageId93 = packet.getCan3ff()[0];
                int hasMessageState93 = packet.getCan3ff()[1];
                int realId93 = CanIds.getRealCanId(hasMessageId93);
                hasMessageListener(realId93, hasMessageId93, hasMessageState93);
                break;
            case 557945696:
                int hasMessageId94 = packet.getCan401()[0];
                int hasMessageState94 = packet.getCan401()[1];
                int realId94 = CanIds.getRealCanId(hasMessageId94);
                hasMessageListener(realId94, hasMessageId94, hasMessageState94);
                break;
            case 557945697:
                int hasMessageId95 = packet.getCan402()[0];
                int hasMessageState95 = packet.getCan402()[1];
                int realId95 = CanIds.getRealCanId(hasMessageId95);
                hasMessageListener(realId95, hasMessageId95, hasMessageState95);
                break;
            case 557945698:
                int hasMessageId96 = packet.getCan404()[0];
                int hasMessageState96 = packet.getCan404()[1];
                int realId96 = CanIds.getRealCanId(hasMessageId96);
                hasMessageListener(realId96, hasMessageId96, hasMessageState96);
                break;
            case 557945699:
                int hasMessageId97 = packet.getCan407()[0];
                int hasMessageState97 = packet.getCan407()[1];
                int realId97 = CanIds.getRealCanId(hasMessageId97);
                hasMessageListener(realId97, hasMessageId97, hasMessageState97);
                break;
            case 557945700:
                int hasMessageId98 = packet.getCan40d()[0];
                int hasMessageState98 = packet.getCan40d()[1];
                int realId98 = CanIds.getRealCanId(hasMessageId98);
                hasMessageListener(realId98, hasMessageId98, hasMessageState98);
                break;
            case 557945701:
                int hasMessageId99 = packet.getCan417()[0];
                int hasMessageState99 = packet.getCan417()[1];
                int realId99 = CanIds.getRealCanId(hasMessageId99);
                hasMessageListener(realId99, hasMessageId99, hasMessageState99);
                break;
            case 557945702:
                int hasMessageId100 = packet.getCan418()[0];
                int hasMessageState100 = packet.getCan418()[1];
                int realId100 = CanIds.getRealCanId(hasMessageId100);
                hasMessageListener(realId100, hasMessageId100, hasMessageState100);
                break;
            case 557945703:
                int hasMessageId101 = packet.getCan41a()[0];
                int hasMessageState101 = packet.getCan41a()[1];
                int realId101 = CanIds.getRealCanId(hasMessageId101);
                hasMessageListener(realId101, hasMessageId101, hasMessageState101);
                break;
            case 557945704:
                int hasMessageId102 = packet.getCan41c()[0];
                int hasMessageState102 = packet.getCan41c()[1];
                int realId102 = CanIds.getRealCanId(hasMessageId102);
                hasMessageListener(realId102, hasMessageId102, hasMessageState102);
                break;
            case 557945705:
                int hasMessageId103 = packet.getCan41e()[0];
                int hasMessageState103 = packet.getCan41e()[1];
                int realId103 = CanIds.getRealCanId(hasMessageId103);
                hasMessageListener(realId103, hasMessageId103, hasMessageState103);
                break;
            case 557945706:
                int hasMessageId104 = packet.getCan41f()[0];
                int hasMessageState104 = packet.getCan41f()[1];
                int realId104 = CanIds.getRealCanId(hasMessageId104);
                hasMessageListener(realId104, hasMessageId104, hasMessageState104);
                break;
            case 557945707:
                int hasMessageId105 = packet.getCan429()[0];
                int hasMessageState105 = packet.getCan429()[1];
                int realId105 = CanIds.getRealCanId(hasMessageId105);
                hasMessageListener(realId105, hasMessageId105, hasMessageState105);
                break;
            case 557945708:
                int hasMessageId106 = packet.getCan42e()[0];
                int hasMessageState106 = packet.getCan42e()[1];
                int realId106 = CanIds.getRealCanId(hasMessageId106);
                hasMessageListener(realId106, hasMessageId106, hasMessageState106);
                break;
            case 557945709:
                int hasMessageId107 = packet.getCan42f()[0];
                int hasMessageState107 = packet.getCan42f()[1];
                int realId107 = CanIds.getRealCanId(hasMessageId107);
                hasMessageListener(realId107, hasMessageId107, hasMessageState107);
                break;
            case 557945710:
                int hasMessageId108 = packet.getCan432()[0];
                int hasMessageState108 = packet.getCan432()[1];
                int realId108 = CanIds.getRealCanId(hasMessageId108);
                hasMessageListener(realId108, hasMessageId108, hasMessageState108);
                break;
            case 557945711:
                int hasMessageId109 = packet.getCan433()[0];
                int hasMessageState109 = packet.getCan433()[1];
                int realId109 = CanIds.getRealCanId(hasMessageId109);
                hasMessageListener(realId109, hasMessageId109, hasMessageState109);
                break;
            case 557945712:
                int hasMessageId110 = packet.getCan434()[0];
                int hasMessageState110 = packet.getCan434()[1];
                int realId110 = CanIds.getRealCanId(hasMessageId110);
                hasMessageListener(realId110, hasMessageId110, hasMessageState110);
                break;
            case 557945713:
                int hasMessageId111 = packet.getCan436()[0];
                int hasMessageState111 = packet.getCan436()[1];
                int realId111 = CanIds.getRealCanId(hasMessageId111);
                hasMessageListener(realId111, hasMessageId111, hasMessageState111);
                break;
            case 557945714:
                int hasMessageId112 = packet.getCan437()[0];
                int hasMessageState112 = packet.getCan437()[1];
                int realId112 = CanIds.getRealCanId(hasMessageId112);
                hasMessageListener(realId112, hasMessageId112, hasMessageState112);
                break;
            case 557945715:
                int hasMessageId113 = packet.getCan438()[0];
                int hasMessageState113 = packet.getCan438()[1];
                int realId113 = CanIds.getRealCanId(hasMessageId113);
                hasMessageListener(realId113, hasMessageId113, hasMessageState113);
                break;
            case 557945716:
                int hasMessageId114 = packet.getCan43e()[0];
                int hasMessageState114 = packet.getCan43e()[1];
                int realId114 = CanIds.getRealCanId(hasMessageId114);
                hasMessageListener(realId114, hasMessageId114, hasMessageState114);
                break;
            case 557945717:
                int hasMessageId115 = packet.getCan43f()[0];
                int hasMessageState115 = packet.getCan43f()[1];
                int realId115 = CanIds.getRealCanId(hasMessageId115);
                hasMessageListener(realId115, hasMessageId115, hasMessageState115);
                break;
            case 557945718:
                int hasMessageId116 = packet.getCan443()[0];
                int hasMessageState116 = packet.getCan443()[1];
                int realId116 = CanIds.getRealCanId(hasMessageId116);
                hasMessageListener(realId116, hasMessageId116, hasMessageState116);
                break;
            case 557945719:
                int hasMessageId117 = packet.getCan444()[0];
                int hasMessageState117 = packet.getCan444()[1];
                int realId117 = CanIds.getRealCanId(hasMessageId117);
                hasMessageListener(realId117, hasMessageId117, hasMessageState117);
                break;
            case 557945720:
                int hasMessageId118 = packet.getCan447()[0];
                int hasMessageState118 = packet.getCan447()[1];
                int realId118 = CanIds.getRealCanId(hasMessageId118);
                hasMessageListener(realId118, hasMessageId118, hasMessageState118);
                break;
            case 557945721:
                int hasMessageId119 = packet.getCan449()[0];
                int hasMessageState119 = packet.getCan449()[1];
                int realId119 = CanIds.getRealCanId(hasMessageId119);
                hasMessageListener(realId119, hasMessageId119, hasMessageState119);
                break;
            case 557945722:
                int hasMessageId120 = packet.getCan453()[0];
                int hasMessageState120 = packet.getCan453()[1];
                int realId120 = CanIds.getRealCanId(hasMessageId120);
                hasMessageListener(realId120, hasMessageId120, hasMessageState120);
                break;
            case 557945723:
                int hasMessageId121 = packet.getCan454()[0];
                int hasMessageState121 = packet.getCan454()[1];
                int realId121 = CanIds.getRealCanId(hasMessageId121);
                hasMessageListener(realId121, hasMessageId121, hasMessageState121);
                break;
            case 557945724:
                int hasMessageId122 = packet.getCan461()[0];
                int hasMessageState122 = packet.getCan461()[1];
                int realId122 = CanIds.getRealCanId(hasMessageId122);
                hasMessageListener(realId122, hasMessageId122, hasMessageState122);
                break;
            case 557945725:
                int hasMessageId123 = packet.getCan46c()[0];
                int hasMessageState123 = packet.getCan46c()[1];
                int realId123 = CanIds.getRealCanId(hasMessageId123);
                hasMessageListener(realId123, hasMessageId123, hasMessageState123);
                break;
            case 557945726:
                int hasMessageId124 = packet.getCan470()[0];
                int hasMessageState124 = packet.getCan470()[1];
                int realId124 = CanIds.getRealCanId(hasMessageId124);
                hasMessageListener(realId124, hasMessageId124, hasMessageState124);
                break;
            case 557945727:
                int hasMessageId125 = packet.getCan475()[0];
                int hasMessageState125 = packet.getCan475()[1];
                int realId125 = CanIds.getRealCanId(hasMessageId125);
                hasMessageListener(realId125, hasMessageId125, hasMessageState125);
                break;
            case 557945728:
                int hasMessageId126 = packet.getCan480()[0];
                int hasMessageState126 = packet.getCan480()[1];
                int realId126 = CanIds.getRealCanId(hasMessageId126);
                hasMessageListener(realId126, hasMessageId126, hasMessageState126);
                break;
            case 557945729:
                int hasMessageId127 = packet.getCan481()[0];
                int hasMessageState127 = packet.getCan481()[1];
                int realId127 = CanIds.getRealCanId(hasMessageId127);
                hasMessageListener(realId127, hasMessageId127, hasMessageState127);
                break;
            case 557945730:
                int hasMessageId128 = packet.getCan486()[0];
                int hasMessageState128 = packet.getCan486()[1];
                int realId128 = CanIds.getRealCanId(hasMessageId128);
                hasMessageListener(realId128, hasMessageId128, hasMessageState128);
                break;
            case 557945731:
                int hasMessageId129 = packet.getCan487()[0];
                int hasMessageState129 = packet.getCan487()[1];
                int realId129 = CanIds.getRealCanId(hasMessageId129);
                hasMessageListener(realId129, hasMessageId129, hasMessageState129);
                break;
            case 557945732:
                int hasMessageId130 = packet.getCan488()[0];
                int hasMessageState130 = packet.getCan488()[1];
                int realId130 = CanIds.getRealCanId(hasMessageId130);
                hasMessageListener(realId130, hasMessageId130, hasMessageState130);
                break;
            case 557945733:
                int hasMessageId131 = packet.getCan489()[0];
                int hasMessageState131 = packet.getCan489()[1];
                int realId131 = CanIds.getRealCanId(hasMessageId131);
                hasMessageListener(realId131, hasMessageId131, hasMessageState131);
                break;
            case 557945734:
                int hasMessageId132 = packet.getCan48a()[0];
                int hasMessageState132 = packet.getCan48a()[1];
                int realId132 = CanIds.getRealCanId(hasMessageId132);
                hasMessageListener(realId132, hasMessageId132, hasMessageState132);
                break;
            case 557945735:
                int hasMessageId133 = packet.getCan48e()[0];
                int hasMessageState133 = packet.getCan48e()[1];
                int realId133 = CanIds.getRealCanId(hasMessageId133);
                hasMessageListener(realId133, hasMessageId133, hasMessageState133);
                break;
            case 557945736:
                int hasMessageId134 = packet.getCan48f()[0];
                int hasMessageState134 = packet.getCan48f()[1];
                int realId134 = CanIds.getRealCanId(hasMessageId134);
                hasMessageListener(realId134, hasMessageId134, hasMessageState134);
                break;
            case 557945737:
                int hasMessageId135 = packet.getCan494()[0];
                int hasMessageState135 = packet.getCan494()[1];
                int realId135 = CanIds.getRealCanId(hasMessageId135);
                hasMessageListener(realId135, hasMessageId135, hasMessageState135);
                break;
            case 557945738:
                int hasMessageId136 = packet.getCan49c()[0];
                int hasMessageState136 = packet.getCan49c()[1];
                int realId136 = CanIds.getRealCanId(hasMessageId136);
                hasMessageListener(realId136, hasMessageId136, hasMessageState136);
                break;
            case 557945739:
                int hasMessageId137 = packet.getCan4a1()[0];
                int hasMessageState137 = packet.getCan4a1()[1];
                int realId137 = CanIds.getRealCanId(hasMessageId137);
                hasMessageListener(realId137, hasMessageId137, hasMessageState137);
                break;
            case 557945740:
                int hasMessageId138 = packet.getCan4a4()[0];
                int hasMessageState138 = packet.getCan4a4()[1];
                int realId138 = CanIds.getRealCanId(hasMessageId138);
                hasMessageListener(realId138, hasMessageId138, hasMessageState138);
                break;
            case 557945741:
                int hasMessageId139 = packet.getCan4a5()[0];
                int hasMessageState139 = packet.getCan4a5()[1];
                int realId139 = CanIds.getRealCanId(hasMessageId139);
                hasMessageListener(realId139, hasMessageId139, hasMessageState139);
                break;
            case 557945742:
                int hasMessageId140 = packet.getCan4a7()[0];
                int hasMessageState140 = packet.getCan4a7()[1];
                int realId140 = CanIds.getRealCanId(hasMessageId140);
                hasMessageListener(realId140, hasMessageId140, hasMessageState140);
                break;
            case 557945743:
                int hasMessageId141 = packet.getCan4b1()[0];
                int hasMessageState141 = packet.getCan4b1()[1];
                int realId141 = CanIds.getRealCanId(hasMessageId141);
                hasMessageListener(realId141, hasMessageId141, hasMessageState141);
                break;
            case 557945744:
                int hasMessageId142 = packet.getCan4b2()[0];
                int hasMessageState142 = packet.getCan4b2()[1];
                int realId142 = CanIds.getRealCanId(hasMessageId142);
                hasMessageListener(realId142, hasMessageId142, hasMessageState142);
                break;
            case 557945745:
                int hasMessageId143 = packet.getCan4b3()[0];
                int hasMessageState143 = packet.getCan4b3()[1];
                int realId143 = CanIds.getRealCanId(hasMessageId143);
                hasMessageListener(realId143, hasMessageId143, hasMessageState143);
                break;
            case 557945746:
                int hasMessageId144 = packet.getCan4b6()[0];
                int hasMessageState144 = packet.getCan4b6()[1];
                int realId144 = CanIds.getRealCanId(hasMessageId144);
                hasMessageListener(realId144, hasMessageId144, hasMessageState144);
                break;
            case 557945747:
                int hasMessageId145 = packet.getCan4bb()[0];
                int hasMessageState145 = packet.getCan4bb()[1];
                int realId145 = CanIds.getRealCanId(hasMessageId145);
                hasMessageListener(realId145, hasMessageId145, hasMessageState145);
                break;
            case 557945748:
                int hasMessageId146 = packet.getCan4bd()[0];
                int hasMessageState146 = packet.getCan4bd()[1];
                int realId146 = CanIds.getRealCanId(hasMessageId146);
                hasMessageListener(realId146, hasMessageId146, hasMessageState146);
                break;
            case 557945749:
                int hasMessageId147 = packet.getCan4bf()[0];
                int hasMessageState147 = packet.getCan4bf()[1];
                int realId147 = CanIds.getRealCanId(hasMessageId147);
                hasMessageListener(realId147, hasMessageId147, hasMessageState147);
                break;
            case 557945750:
                int hasMessageId148 = packet.getCan4c2()[0];
                int hasMessageState148 = packet.getCan4c2()[1];
                int realId148 = CanIds.getRealCanId(hasMessageId148);
                hasMessageListener(realId148, hasMessageId148, hasMessageState148);
                break;
            case 557945751:
                int hasMessageId149 = packet.getCan4c8()[0];
                int hasMessageState149 = packet.getCan4c8()[1];
                int realId149 = CanIds.getRealCanId(hasMessageId149);
                hasMessageListener(realId149, hasMessageId149, hasMessageState149);
                break;
            case 557945752:
                int hasMessageId150 = packet.getCan4d9()[0];
                int hasMessageState150 = packet.getCan4d9()[1];
                int realId150 = CanIds.getRealCanId(hasMessageId150);
                hasMessageListener(realId150, hasMessageId150, hasMessageState150);
                break;
            case 557945753:
                int hasMessageId151 = packet.getCan4e5()[0];
                int hasMessageState151 = packet.getCan4e5()[1];
                int realId151 = CanIds.getRealCanId(hasMessageId151);
                hasMessageListener(realId151, hasMessageId151, hasMessageState151);
                break;
            case 557945754:
                int hasMessageId152 = packet.getCan4e6()[0];
                int hasMessageState152 = packet.getCan4e6()[1];
                int realId152 = CanIds.getRealCanId(hasMessageId152);
                hasMessageListener(realId152, hasMessageId152, hasMessageState152);
                break;
            case 557945755:
                int hasMessageId153 = packet.getCan4f2()[0];
                int hasMessageState153 = packet.getCan4f2()[1];
                int realId153 = CanIds.getRealCanId(hasMessageId153);
                hasMessageListener(realId153, hasMessageId153, hasMessageState153);
                break;
            case 557945756:
                int hasMessageId154 = packet.getCan4f4()[0];
                int hasMessageState154 = packet.getCan4f4()[1];
                int realId154 = CanIds.getRealCanId(hasMessageId154);
                hasMessageListener(realId154, hasMessageId154, hasMessageState154);
                break;
            case 557945757:
                int hasMessageId155 = packet.getCan4f6()[0];
                int hasMessageState155 = packet.getCan4f6()[1];
                int realId155 = CanIds.getRealCanId(hasMessageId155);
                hasMessageListener(realId155, hasMessageId155, hasMessageState155);
                break;
            case 557945758:
                int hasMessageId156 = packet.getCan4f7()[0];
                int hasMessageState156 = packet.getCan4f7()[1];
                int realId156 = CanIds.getRealCanId(hasMessageId156);
                hasMessageListener(realId156, hasMessageId156, hasMessageState156);
                break;
            case 557945759:
                int hasMessageId157 = packet.getCan4fa()[0];
                int hasMessageState157 = packet.getCan4fa()[1];
                int realId157 = CanIds.getRealCanId(hasMessageId157);
                hasMessageListener(realId157, hasMessageId157, hasMessageState157);
                break;
            case 557945760:
                int hasMessageId158 = packet.getCan4fb()[0];
                int hasMessageState158 = packet.getCan4fb()[1];
                int realId158 = CanIds.getRealCanId(hasMessageId158);
                hasMessageListener(realId158, hasMessageId158, hasMessageState158);
                break;
            case 557945761:
                int hasMessageId159 = packet.getCan4fd()[0];
                int hasMessageState159 = packet.getCan4fd()[1];
                int realId159 = CanIds.getRealCanId(hasMessageId159);
                hasMessageListener(realId159, hasMessageId159, hasMessageState159);
                break;
            case 557945762:
                int hasMessageId160 = packet.getCan4fe()[0];
                int hasMessageState160 = packet.getCan4fe()[1];
                int realId160 = CanIds.getRealCanId(hasMessageId160);
                hasMessageListener(realId160, hasMessageId160, hasMessageState160);
                break;
            case 557945763:
                int hasMessageId161 = packet.getCan344()[0];
                int hasMessageState161 = packet.getCan344()[1];
                int realId161 = CanIds.getRealCanId(hasMessageId161);
                hasMessageListener(realId161, hasMessageId161, hasMessageState161);
                break;
            case 557945764:
                int hasMessageId162 = packet.getCan319()[0];
                int hasMessageState162 = packet.getCan319()[1];
                int realId162 = CanIds.getRealCanId(hasMessageId162);
                hasMessageListener(realId162, hasMessageId162, hasMessageState162);
                break;
            case 557945765:
                int hasMessageId163 = packet.getCan258()[0];
                int hasMessageState163 = packet.getCan258()[1];
                int realId163 = CanIds.getRealCanId(hasMessageId163);
                hasMessageListener(realId163, hasMessageId163, hasMessageState163);
                break;
            case 557945766:
                int hasMessageId164 = packet.getCan351R()[0];
                int hasMessageState164 = packet.getCan351R()[1];
                int realId164 = CanIds.getRealCanId(hasMessageId164);
                hasMessageListener(realId164, hasMessageId164, hasMessageState164);
                break;
            case 557945767:
                int hasMessageId165 = packet.getCan4e8R()[0];
                int hasMessageState165 = packet.getCan4e8R()[1];
                int realId165 = CanIds.getRealCanId(hasMessageId165);
                hasMessageListener(realId165, hasMessageId165, hasMessageState165);
                break;
            case 557945768:
                int hasMessageId166 = packet.getCan4e9R()[0];
                int hasMessageState166 = packet.getCan4e9R()[1];
                int realId166 = CanIds.getRealCanId(hasMessageId166);
                hasMessageListener(realId166, hasMessageId166, hasMessageState166);
                break;
            case 557945769:
                int hasMessageId167 = packet.getCan257R()[0];
                int hasMessageState167 = packet.getCan257R()[1];
                int realId167 = CanIds.getRealCanId(hasMessageId167);
                hasMessageListener(realId167, hasMessageId167, hasMessageState167);
                break;
            case 557945770:
                int hasMessageId168 = packet.getCan48cR()[0];
                int hasMessageState168 = packet.getCan48cR()[1];
                int realId168 = CanIds.getRealCanId(hasMessageId168);
                hasMessageListener(realId168, hasMessageId168, hasMessageState168);
                break;
            case 557945771:
                int hasMessageId169 = packet.getCan3d6R()[0];
                int hasMessageState169 = packet.getCan3d6R()[1];
                int realId169 = CanIds.getRealCanId(hasMessageId169);
                hasMessageListener(realId169, hasMessageId169, hasMessageState169);
                break;
            case 557945772:
                int hasMessageId170 = packet.getCan38fR()[0];
                int hasMessageState170 = packet.getCan38fR()[1];
                int realId170 = CanIds.getRealCanId(hasMessageId170);
                hasMessageListener(realId170, hasMessageId170, hasMessageState170);
                break;
            case 557945773:
                int hasMessageId171 = packet.getCan464R()[0];
                int hasMessageState171 = packet.getCan464R()[1];
                int realId171 = CanIds.getRealCanId(hasMessageId171);
                hasMessageListener(realId171, hasMessageId171, hasMessageState171);
                break;
            case 557945774:
                int hasMessageId172 = packet.getCan338R()[0];
                int hasMessageState172 = packet.getCan338R()[1];
                int realId172 = CanIds.getRealCanId(hasMessageId172);
                hasMessageListener(realId172, hasMessageId172, hasMessageState172);
                break;
            case 557945775:
                int hasMessageId173 = packet.getCan145R()[0];
                int hasMessageState173 = packet.getCan145R()[1];
                int realId173 = CanIds.getRealCanId(hasMessageId173);
                hasMessageListener(realId173, hasMessageId173, hasMessageState173);
                break;
            case 557945777:
                int hasMessageId174 = packet.getCan1eaR()[0];
                int hasMessageState174 = packet.getCan1eaR()[1];
                int realId174 = CanIds.getRealCanId(hasMessageId174);
                hasMessageListener(realId174, hasMessageId174, hasMessageState174);
                break;
            case 557945778:
                int hasMessageId175 = packet.getCan359R()[0];
                int hasMessageState175 = packet.getCan359R()[1];
                int realId175 = CanIds.getRealCanId(hasMessageId175);
                hasMessageListener(realId175, hasMessageId175, hasMessageState175);
                break;
            case 559954810:
                for (AbsBYDAutoBodyworkListener listener70 : this.mAbsBydAutoBodyworkListeners) {
                    listener70.turnSteeringWheelChanged(3, packet.getSteeringWheelY(), packet.getSteeringWheelZ());
                }
                caseFeatureIds(2100, Integer.valueOf((int) packet.getSteeringWheelY()));
                break;
            case 559954811:
                for (AbsBYDAutoBodyworkListener listener71 : this.mAbsBydAutoBodyworkListeners) {
                    listener71.turnSteeringWheelChanged(1, packet.getSteeringWheelY(), packet.getSteeringWheelZ());
                }
                caseFeatureIds(2098, Integer.valueOf((int) packet.getSteeringWheelZ()));
                break;
            case 559954812:
                for (AbsBYDAutoBodyworkListener listener72 : this.mAbsBydAutoBodyworkListeners) {
                    listener72.onTurnRearviewMirrorChanged(3, packet.getRearviewMirrorXLeft(), packet.getRearviewMirrorYLeft());
                }
                break;
            case 559954813:
                for (AbsBYDAutoBodyworkListener listener73 : this.mAbsBydAutoBodyworkListeners) {
                    listener73.onTurnRearviewMirrorChanged(7, packet.getRearviewMirrorXRight(), packet.getRearviewMirrorYRight());
                }
                break;
            case 559954814:
                for (AbsBYDAutoBodyworkListener listener74 : this.mAbsBydAutoBodyworkListeners) {
                    listener74.onTurnRearviewMirrorChanged(1, packet.getRearviewMirrorXLeft(), packet.getRearviewMirrorYLeft());
                }
                break;
            case 559954815:
                for (AbsBYDAutoBodyworkListener listener75 : this.mAbsBydAutoBodyworkListeners) {
                    listener75.onTurnRearviewMirrorChanged(6, packet.getRearviewMirrorXRight(), packet.getRearviewMirrorYRight());
                }
                break;
            default:
                switch (id) {
                    case 557857805:
                        for (AbsBYDAutoBodyworkListener listener76 : this.mAbsBydAutoBodyworkListeners) {
                            listener76.onRecoverOrSaveParamsChanged(0, 1, 1, packet.getonSaveSeatParamsAllChangedPlace5());
                        }
                        caseFeatureIds(1305, Integer.valueOf(packet.getonSaveSeatParamsAllChangedPlace5()));
                        break;
                    case 557857806:
                        for (AbsBYDAutoBodyworkListener listener77 : this.mAbsBydAutoBodyworkListeners) {
                            listener77.onMainSaveOrRecoverStateChanged(1, packet.getonSaveSeatParamsAllChangedPlace7());
                        }
                        caseFeatureIds(2134, Integer.valueOf(packet.getonSaveSeatParamsAllChangedPlace7()));
                        break;
                    default:
                        switch (id) {
                            case 557858048:
                                int value = packet.getAutoExternalRearMirrorFoldActionStateR();
                                for (AbsBYDAutoBodyworkListener listener78 : this.mAbsBydAutoBodyworkListeners) {
                                    listener78.onAutoExternalRearMirrorFoldActionStateChanged(value);
                                }
                                caseFeatureIds(2169, Integer.valueOf(value));
                                break;
                            case 557858049:
                                for (AbsBYDAutoBodyworkListener listener79 : this.mAbsBydAutoBodyworkListeners) {
                                    listener79.onAutoExternalRearMirrorFoldedState(packet.getAutoExternalRearMirrorFoldedStateR());
                                }
                                break;
                            default:
                                switch (id) {
                                    case 557862912:
                                        for (AbsBYDAutoBodyworkListener listener80 : this.mAbsBydAutoBodyworkListeners) {
                                            listener80.onWindowStateChanged(1, packet.getmWindowStateLeftFront());
                                        }
                                        break;
                                    case 557862913:
                                        for (AbsBYDAutoBodyworkListener listener81 : this.mAbsBydAutoBodyworkListeners) {
                                            listener81.onWindowStateChanged(3, packet.getmWindowStateLeftRear());
                                        }
                                        break;
                                    case 557862914:
                                        for (AbsBYDAutoBodyworkListener listener82 : this.mAbsBydAutoBodyworkListeners) {
                                            listener82.onWindowStateChanged(2, packet.getmWindowStateRightFront());
                                        }
                                        break;
                                    case 557862915:
                                        for (AbsBYDAutoBodyworkListener listener83 : this.mAbsBydAutoBodyworkListeners) {
                                            listener83.onWindowStateChanged(4, packet.getmWindowStateRightRear());
                                        }
                                        break;
                                    case 557862916:
                                        for (AbsBYDAutoBodyworkListener listener84 : this.mAbsBydAutoBodyworkListeners) {
                                            listener84.onWindowControlStateChanged(1, packet.getWindowControlStateLeftFrontR());
                                        }
                                        break;
                                    case 557862917:
                                        for (AbsBYDAutoBodyworkListener listener85 : this.mAbsBydAutoBodyworkListeners) {
                                            listener85.onWindowControlStateChanged(2, packet.getWindowControlStateRightFrontR());
                                        }
                                        break;
                                    case 557862918:
                                        for (AbsBYDAutoBodyworkListener listener86 : this.mAbsBydAutoBodyworkListeners) {
                                            listener86.onWindowControlStateChanged(3, packet.getWindowControlStateLeftRearR());
                                        }
                                        break;
                                    case 557862919:
                                        for (AbsBYDAutoBodyworkListener listener87 : this.mAbsBydAutoBodyworkListeners) {
                                            listener87.onWindowControlStateChanged(4, packet.getWindowControlStateRightRearR());
                                        }
                                        break;
                                    default:
                                        switch (id) {
                                            case 557862924:
                                                for (AbsBYDAutoBodyworkListener listener88 : this.mAbsBydAutoBodyworkListeners) {
                                                    listener88.onWindowOpenPercentChanged(5, packet.getmWindowOpenPercentMoonS());
                                                }
                                                break;
                                            case 557862925:
                                                for (AbsBYDAutoBodyworkListener listener89 : this.mAbsBydAutoBodyworkListeners) {
                                                    listener89.onWindowOpenPercentChanged(6, packet.getmWindowOpenPercentSunS());
                                                }
                                                break;
                                            case 557862926:
                                                for (AbsBYDAutoBodyworkListener listener90 : this.mAbsBydAutoBodyworkListeners) {
                                                    if (packet.getmBatteryVoltageLevel() == 1) {
                                                        listener90.onBatteryVoltageLevelChanged(1);
                                                    } else if (packet.getmBatteryVoltageLevel() == 2) {
                                                        listener90.onBatteryVoltageLevelChanged(0);
                                                    }
                                                }
                                                break;
                                            case 557862927:
                                                for (AbsBYDAutoBodyworkListener listener91 : this.mAbsBydAutoBodyworkListeners) {
                                                    if (packet.getmPowerLevel() == 0) {
                                                        listener91.onPowerLevelChanged(255);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 255");
                                                    } else if (packet.getmPowerLevel() == 1) {
                                                        listener91.onPowerLevelChanged(0);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 0");
                                                    } else if (packet.getmPowerLevel() == 2) {
                                                        listener91.onPowerLevelChanged(1);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 1");
                                                    } else if (packet.getmPowerLevel() == 3) {
                                                        listener91.onPowerLevelChanged(2);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 2");
                                                    }
                                                }
                                                int powerLevel = packet.getmPowerLevel();
                                                caseFeatureIds(2002, Integer.valueOf(powerLevel));
                                                break;
                                            case 557862928:
                                                for (AbsBYDAutoBodyworkListener listener92 : this.mAbsBydAutoBodyworkListeners) {
                                                    listener92.onSteeringWheelValueChanged(1, packet.getmSteeringWheelValueAngel());
                                                }
                                                break;
                                            case 557862929:
                                                for (AbsBYDAutoBodyworkListener listener93 : this.mAbsBydAutoBodyworkListeners) {
                                                    listener93.onSteeringWheelValueChanged(2, packet.getmSteeringWheelValueSpeed());
                                                }
                                                break;
                                            case 557862930:
                                                caseFeatureIds(1682, Integer.valueOf(packet.getmAutoType()));
                                                break;
                                            case 557862931:
                                                for (AbsBYDAutoBodyworkListener listener94 : this.mAbsBydAutoBodyworkListeners) {
                                                    listener94.onAutoSystemStateChanged(packet.getmAutoSystemState());
                                                }
                                                break;
                                            default:
                                                switch (id) {
                                                    case 557862972:
                                                        for (AbsBYDAutoBodyworkListener listener95 : this.mAbsBydAutoBodyworkListeners) {
                                                            listener95.onRearWindowblindStateChanged(packet.getRearWindowblindStateR());
                                                        }
                                                        caseFeatureIds(2007, Integer.valueOf(packet.getRearWindowblindStateR()));
                                                        break;
                                                    case 557862973:
                                                        for (AbsBYDAutoBodyworkListener listener96 : this.mAbsBydAutoBodyworkListeners) {
                                                            listener96.onRearWindowblindInitStateChanged(packet.getRearWindowblindInitStateR());
                                                        }
                                                        caseFeatureIds(2009, Integer.valueOf(packet.getRearWindowblindInitStateR()));
                                                        break;
                                                    case 557862974:
                                                        for (AbsBYDAutoBodyworkListener listener97 : this.mAbsBydAutoBodyworkListeners) {
                                                            listener97.onRearWindowblindPositionChanged(packet.getRearWindowblindPositionR());
                                                        }
                                                        caseFeatureIds(2008, Integer.valueOf(packet.getRearWindowblindPositionR()));
                                                        break;
                                                    case 557862975:
                                                        for (AbsBYDAutoBodyworkListener listener98 : this.mAbsBydAutoBodyworkListeners) {
                                                            listener98.onBackDoorInitStateChanged(packet.getBackDoorInitStateR());
                                                        }
                                                        caseFeatureIds(1686, Integer.valueOf(packet.getBackDoorInitStateR()));
                                                        break;
                                                    default:
                                                        switch (id) {
                                                            case 557862978:
                                                                for (AbsBYDAutoBodyworkListener listener99 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener99.onBackDoorCurStateChanged(packet.getBackDoorCurStateR());
                                                                }
                                                                caseFeatureIds(1685, Integer.valueOf(packet.getBackDoorCurStateR()));
                                                                break;
                                                            case 557862979:
                                                                for (AbsBYDAutoBodyworkListener listener100 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener100.onRightStrutMorotRunningStateChanged(packet.getRightStrutMorotRunningStateR());
                                                                }
                                                                caseFeatureIds(2049, Integer.valueOf(packet.getRightStrutMorotRunningStateR()));
                                                                break;
                                                            case 557862980:
                                                                for (AbsBYDAutoBodyworkListener listener101 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener101.onBackDoorPositionChanged(packet.getBackDoorPositionR());
                                                                }
                                                                int backDoorR = packet.getBackDoorPositionR();
                                                                caseFeatureIds(1687, Integer.valueOf(backDoorR));
                                                                break;
                                                            case 557862981:
                                                                for (AbsBYDAutoBodyworkListener listener102 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener102.onBackDoorAntiPlayModeChanged(packet.getBackDoorAntiPlayModeR());
                                                                }
                                                                caseFeatureIds(1689, Integer.valueOf(packet.getElecSlideDoorStateR()));
                                                                break;
                                                            case 557862982:
                                                                for (AbsBYDAutoBodyworkListener listener103 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener103.onElecSlideDoorStateChanged(packet.getElecSlideDoorStateR());
                                                                }
                                                                caseFeatureIds(1717, Integer.valueOf(packet.getElecSlideDoorStateR()));
                                                                break;
                                                            case 557862983:
                                                                for (AbsBYDAutoBodyworkListener listener104 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener104.onLeftSlideDoorStateChanged(packet.getLeftSlideDoorStateR());
                                                                }
                                                                caseFeatureIds(1757, Integer.valueOf(packet.getLeftSlideDoorStateR()));
                                                                break;
                                                            case 557862984:
                                                                for (AbsBYDAutoBodyworkListener listener105 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener105.onRightSlideDoorStateChanged(packet.getRightSlideDoorStateR());
                                                                }
                                                                caseFeatureIds(2047, Integer.valueOf(packet.getRightSlideDoorStateR()));
                                                                break;
                                                            case 557862985:
                                                                for (AbsBYDAutoBodyworkListener listener106 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener106.onLeftSlideDoorActionChanged(packet.getLeftSlideDoorActionR());
                                                                }
                                                                caseFeatureIds(1753, Integer.valueOf(packet.getLeftSlideDoorActionR()));
                                                                break;
                                                            case 557862986:
                                                                for (AbsBYDAutoBodyworkListener listener107 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener107.onRightSlideDoorActionChanged(packet.getRightSlideDoorActionR());
                                                                }
                                                                caseFeatureIds(2044, Integer.valueOf(packet.getRightSlideDoorActionR()));
                                                                break;
                                                            case 557862987:
                                                                for (AbsBYDAutoBodyworkListener listener108 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener108.onLeftSlideDoorPercentChanged(packet.getLeftSlideDoorPercentR());
                                                                }
                                                                caseFeatureIds(1756, Integer.valueOf(packet.getLeftSlideDoorPercentR()));
                                                                break;
                                                            case 557862988:
                                                                for (AbsBYDAutoBodyworkListener listener109 : this.mAbsBydAutoBodyworkListeners) {
                                                                    listener109.onRightSlideDoorPercentChanged(packet.getRightSlideDoorPercentR());
                                                                }
                                                                caseFeatureIds(2046, Integer.valueOf(packet.getRightSlideDoorPercentR()));
                                                                break;
                                                            default:
                                                                switch (id) {
                                                                    case 557863016:
                                                                        for (AbsBYDAutoBodyworkListener listener110 : this.mAbsBydAutoBodyworkListeners) {
                                                                            listener110.onSmartVoiceLimitChanged(packet.getSmartVoiceLimit());
                                                                        }
                                                                        break;
                                                                    case 557863017:
                                                                        for (AbsBYDAutoBodyworkListener listener111 : this.mAbsBydAutoBodyworkListeners) {
                                                                            listener111.onBCMVechileStatusChanged(packet.getBcmVechileStatus());
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (id) {
                                                                            case 557863022:
                                                                                for (AbsBYDAutoBodyworkListener doorlockListener : this.mAbsBydAutoBodyworkListeners) {
                                                                                    doorlockListener.onDoorLockStatusChanged(3, packet.getDoorLockStatusRightFront());
                                                                                }
                                                                                int rightFront1 = packet.getDoorLockStatusRightFront();
                                                                                caseFeatureIds(2025, Integer.valueOf(rightFront1));
                                                                                break;
                                                                            case 557863023:
                                                                                for (AbsBYDAutoBodyworkListener doorlockListener2 : this.mAbsBydAutoBodyworkListeners) {
                                                                                    doorlockListener2.onDoorLockStatusChanged(2, packet.getDoorLockStatusLeftRear());
                                                                                }
                                                                                int leftRear1 = packet.getDoorLockStatusLeftRear();
                                                                                caseFeatureIds(1768, Integer.valueOf(leftRear1));
                                                                                break;
                                                                            case 557863024:
                                                                                for (AbsBYDAutoBodyworkListener doorlockListener3 : this.mAbsBydAutoBodyworkListeners) {
                                                                                    doorlockListener3.onDoorLockStatusChanged(4, packet.getDoorLockStatusAreaRightRear());
                                                                                }
                                                                                int rightRear1 = packet.getDoorLockStatusAreaRightRear();
                                                                                caseFeatureIds(2050, Integer.valueOf(rightRear1));
                                                                                break;
                                                                            default:
                                                                                switch (id) {
                                                                                    case 557863041:
                                                                                        for (AbsBYDAutoBodyworkListener listener112 : this.mAbsBydAutoBodyworkListeners) {
                                                                                            listener112.onSeatModeWakeupFeedbackStateChanged(packet.getSeatModeWakeupFeedbackStateR());
                                                                                        }
                                                                                        break;
                                                                                    case 557863042:
                                                                                        int lFrontR = packet.getInternalDoorHandleStateLeftFrontR();
                                                                                        for (AbsBYDAutoBodyworkListener listener113 : this.mAbsBydAutoBodyworkListeners) {
                                                                                            listener113.onDoorHandleStateChanged(1, lFrontR);
                                                                                        }
                                                                                        caseFeatureIds(4647, Integer.valueOf(lFrontR));
                                                                                        break;
                                                                                    case 557863043:
                                                                                        int rFrontR = packet.getInternalDoorHandleStateRightFrontR();
                                                                                        for (AbsBYDAutoBodyworkListener listener114 : this.mAbsBydAutoBodyworkListeners) {
                                                                                            listener114.onDoorHandleStateChanged(2, rFrontR);
                                                                                        }
                                                                                        caseFeatureIds(4648, Integer.valueOf(rFrontR));
                                                                                        break;
                                                                                    case 557863044:
                                                                                        int lRearR = packet.getInternalDoorHandleStateLeftBackR();
                                                                                        for (AbsBYDAutoBodyworkListener listener115 : this.mAbsBydAutoBodyworkListeners) {
                                                                                            listener115.onDoorHandleStateChanged(3, lRearR);
                                                                                        }
                                                                                        caseFeatureIds(4646, Integer.valueOf(lRearR));
                                                                                        break;
                                                                                    case 557863045:
                                                                                        int rRearR = packet.getInternalDoorHandleStateRightBackR();
                                                                                        for (AbsBYDAutoBodyworkListener listener116 : this.mAbsBydAutoBodyworkListeners) {
                                                                                            listener116.onDoorHandleStateChanged(4, rRearR);
                                                                                        }
                                                                                        caseFeatureIds(4649, Integer.valueOf(rRearR));
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
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    switch (eventType) {
                        case 1681:
                            bydAutoEventValue.intValue = getAntiTheftStatus();
                            sparseArray.put(1681, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1682:
                            bydAutoEventValue.intValue = getAutoType();
                            sparseArray.put(1682, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1685:
                            bydAutoEventValue.intValue = getBackDoorCurState();
                            sparseArray.put(1685, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1686:
                            bydAutoEventValue.intValue = getBackDoorInitState();
                            sparseArray.put(1686, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1687:
                            bydAutoEventValue.intValue = getBackDoorPosition();
                            sparseArray.put(1687, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1689:
                            bydAutoEventValue.intValue = getBackDoorAntiPlayMode();
                            sparseArray.put(1689, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1717:
                            bydAutoEventValue.intValue = getElecSlideDoorState();
                            sparseArray.put(1717, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1731:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                            sparseArray.put(1731, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1732:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
                            sparseArray.put(1732, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1735:
                            bydAutoEventValue.intValue = getWindowThermalProtectionState(6);
                            sparseArray.put(1735, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1742:
                            bydAutoEventValue.intValue = getDoorState(5);
                            sparseArray.put(1742, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1746:
                            bydAutoEventValue.intValue = getWindowState(1);
                            sparseArray.put(1746, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1747:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowPermitState();
                            sparseArray.put(1747, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1748:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
                            sparseArray.put(1748, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1749:
                            bydAutoEventValue.intValue = getDoorState(1);
                            sparseArray.put(1749, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1750:
                            bydAutoEventValue.intValue = getDoorState(3);
                            sparseArray.put(1750, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1751:
                            bydAutoEventValue.intValue = getWindowState(3);
                            sparseArray.put(1751, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1752:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
                            sparseArray.put(1752, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1753:
                            bydAutoEventValue.intValue = getLeftSlideDoorAction();
                            sparseArray.put(1753, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1756:
                            bydAutoEventValue.intValue = getLeftSlideDoorPercent();
                            sparseArray.put(1756, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1757:
                            bydAutoEventValue.intValue = getLeftSlideDoorState();
                            sparseArray.put(1757, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1759:
                            bydAutoEventValue.intValue = getLeftStrutMorotRunningState();
                            sparseArray.put(1759, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1765:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
                            sparseArray.put(1765, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1768:
                            bydAutoEventValue.intValue = getDoorLockStatus(2);
                            sparseArray.put(1768, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1778:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(3);
                            sparseArray.put(1778, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1780:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
                            sparseArray.put(1780, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1782:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                            sparseArray.put(1782, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1785:
                            bydAutoEventValue.intValue = getWindowState(5);
                            sparseArray.put(1785, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1786:
                            bydAutoEventValue.intValue = getMoonRoofConfig();
                            sparseArray.put(1786, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1788:
                            bydAutoEventValue.intValue = getMoonRoofConfig();
                            sparseArray.put(1788, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1789:
                            bydAutoEventValue.intValue = getWindowOpenPercent(5);
                            sparseArray.put(1789, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1896:
                            bydAutoEventValue.intValue = getReadyIndicatorLightState();
                            sparseArray.put(1896, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2002:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
                            sparseArray.put(2002, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2005:
                            bydAutoEventValue.intValue = getRearviewMirrorSupport();
                            sparseArray.put(2005, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2006:
                            bydAutoEventValue.intValue = getRearLampLightState();
                            sparseArray.put(2006, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2007:
                            bydAutoEventValue.intValue = getRearWindowblindState();
                            sparseArray.put(2007, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2008:
                            bydAutoEventValue.intValue = getRearWindowblindPosition();
                            sparseArray.put(2008, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2009:
                            bydAutoEventValue.intValue = getRearWindowblindInitState();
                            sparseArray.put(2009, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2012:
                            bydAutoEventValue.intValue = getWindowOpenPercent(7);
                            sparseArray.put(2012, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2014:
                            bydAutoEventValue.intValue = getWindowThermalProtectionState(7);
                            sparseArray.put(2014, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2025:
                            bydAutoEventValue.intValue = getDoorLockStatus(3);
                            sparseArray.put(2025, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2034:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(2);
                            sparseArray.put(2034, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2036:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
                            sparseArray.put(2036, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2039:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
                            sparseArray.put(2039, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2040:
                            bydAutoEventValue.intValue = getDoorState(2);
                            sparseArray.put(2040, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2041:
                            bydAutoEventValue.intValue = getDoorState(4);
                            sparseArray.put(2041, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2043:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
                            sparseArray.put(2043, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2044:
                            bydAutoEventValue.intValue = getRightSlideDoorAction();
                            sparseArray.put(2044, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2046:
                            bydAutoEventValue.intValue = getRightSlideDoorPercent();
                            sparseArray.put(2046, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2047:
                            bydAutoEventValue.intValue = getRightSlideDoorState();
                            sparseArray.put(2047, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2049:
                            bydAutoEventValue.intValue = getRightStrutMorotRunningState();
                            sparseArray.put(2049, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2050:
                            bydAutoEventValue.intValue = getDoorLockStatus(4);
                            sparseArray.put(2050, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2060:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(4);
                            sparseArray.put(2060, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2062:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
                            sparseArray.put(2062, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2068:
                            bydAutoEventValue.intValue = getWindowThermalProtectionState(5);
                            sparseArray.put(2068, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2072:
                            bydAutoEventValue.doubleValue = getSteeringWheelValue(1);
                            sparseArray.put(2072, Double.valueOf(bydAutoEventValue.doubleValue));
                            break;
                        case 2075:
                            bydAutoEventValue.doubleValue = getSteeringWheelValue(2);
                            sparseArray.put(2075, Double.valueOf(bydAutoEventValue.doubleValue));
                            break;
                        case 2076:
                            bydAutoEventValue.intValue = getSunroofCloseNotice();
                            sparseArray.put(2076, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2077:
                            bydAutoEventValue.intValue = getSunroofInitState();
                            sparseArray.put(2077, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2078:
                            bydAutoEventValue.intValue = getSunroofPosition();
                            sparseArray.put(2078, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2079:
                            bydAutoEventValue.intValue = getSunroofState();
                            sparseArray.put(2079, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2080:
                            bydAutoEventValue.intValue = getWindowblindInitState();
                            sparseArray.put(2080, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2081:
                            bydAutoEventValue.intValue = getSunroofWindowblindPosition();
                            sparseArray.put(2081, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2082:
                            bydAutoEventValue.intValue = getWindowblindState();
                            sparseArray.put(2082, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2084:
                            bydAutoEventValue.intValue = getWindowOpenPercent(6);
                            sparseArray.put(2084, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2089:
                            bydAutoEventValue.intValue = getWindowAntiPinchConfig();
                            sparseArray.put(2089, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2090:
                            bydAutoEventValue.intValue = getWindowOpenPercent(1);
                            sparseArray.put(2090, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2091:
                            bydAutoEventValue.intValue = getWindowOpenPercent(3);
                            sparseArray.put(2091, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2092:
                            bydAutoEventValue.intValue = getWindowOpenPercent(2);
                            sparseArray.put(2092, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2093:
                            bydAutoEventValue.intValue = getWindowOpenPercent(4);
                            sparseArray.put(2093, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2098:
                            bydAutoEventValue.floatValue = this.mTsManagerImpl.getCarBodyManager().getSteeringWheelZ();
                            sparseArray.put(2098, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2100:
                            bydAutoEventValue.floatValue = getSteeringWheelY();
                            sparseArray.put(2100, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2120:
                            bydAutoEventValue.floatValue = getRearviewMirrorX(1);
                            sparseArray.put(2120, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2122:
                            bydAutoEventValue.floatValue = getRearviewMirrorY(1);
                            sparseArray.put(2122, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2130:
                            bydAutoEventValue.floatValue = getRearviewMirrorX(2);
                            sparseArray.put(2130, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2132:
                            bydAutoEventValue.floatValue = getRearviewMirrorY(2);
                            sparseArray.put(2132, Float.valueOf(bydAutoEventValue.floatValue));
                            break;
                        case 2134:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getMainSaveOrRecoverState();
                            sparseArray.put(2134, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2169:
                            bydAutoEventValue.intValue = getAutoExternalRearMirrorFoldActionState();
                            sparseArray.put(2169, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2170:
                            bydAutoEventValue.intValue = getAutoExternalRearMirrorFoldedState();
                            sparseArray.put(2170, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2445:
                            bydAutoEventValue.intValue = getEnergyType();
                            sparseArray.put(2445, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 3648:
                            bydAutoEventValue.intValue = getReadyIndicatorLightState();
                            sparseArray.put(3648, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4614:
                            bydAutoEventValue.intValue = this.mTsManagerImpl.getCarBodyManager().getMain2SaveOrRecoverState();
                            sparseArray.put(4614, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4615:
                            bydAutoEventValue.intValue = getMoonRoofPercentControlFlag();
                            sparseArray.put(4615, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4639:
                            bydAutoEventValue.intValue = getSeatLumbarSupportConfig(1);
                            sparseArray.put(4639, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4640:
                            bydAutoEventValue.intValue = getSeatLumbarSupportConfig(2);
                            sparseArray.put(4640, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4643:
                            bydAutoEventValue.intValue = getFourPeopleEnjoyConfig();
                            sparseArray.put(4643, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4645:
                            bydAutoEventValue.intValue = getSeatModeWakeupFeedbackState();
                            sparseArray.put(4645, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4646:
                            bydAutoEventValue.intValue = getInternalDoorHandleState(3);
                            sparseArray.put(4646, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4647:
                            bydAutoEventValue.intValue = getInternalDoorHandleState(1);
                            sparseArray.put(4647, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4648:
                            bydAutoEventValue.intValue = getInternalDoorHandleState(2);
                            sparseArray.put(4648, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4649:
                            bydAutoEventValue.intValue = getInternalDoorHandleState(4);
                            sparseArray.put(4649, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4650:
                            bydAutoEventValue.intValue = getElectricPedalConfig();
                            sparseArray.put(4650, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4651:
                            bydAutoEventValue.intValue = getTailDoorTypeConfig();
                            sparseArray.put(4651, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4653:
                            bydAutoEventValue.intValue = getWindowPercentageControlConfig();
                            sparseArray.put(4653, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4654:
                            bydAutoEventValue.intValue = getShiftMode();
                            sparseArray.put(4654, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SYSTEM_CONFIG /* 4725 */:
                            bydAutoEventValue.intValue = getSystemConfig();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SYSTEM_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4726 */:
                            bydAutoEventValue.intValue = getSystemConfig();
                            if (bydAutoEventValue.intValue == 2) {
                                bydAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(1);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_LEFT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4647, Integer.valueOf(bydAutoEventValue.intValue));
                            }
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4727 */:
                            bydAutoEventValue.intValue = getSystemConfig();
                            if (bydAutoEventValue.intValue == 2) {
                                bydAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(3);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_LEFT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4646, Integer.valueOf(bydAutoEventValue.intValue));
                            }
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4728 */:
                            bydAutoEventValue.intValue = getSystemConfig();
                            if (bydAutoEventValue.intValue == 2) {
                                bydAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(2);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_RIGHT_FRONT_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4648, Integer.valueOf(bydAutoEventValue.intValue));
                            }
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only /* 4729 */:
                            bydAutoEventValue.intValue = getSystemConfig();
                            if (bydAutoEventValue.intValue == 2) {
                                bydAutoEventValue.intValue = getInternalDoorHandleStateByRearBcm(4);
                                sparseArray.put(BYDAutoFeatureIds.BODYWORK_RIGHT_REAR_DOOR_HANDLE_INTERNAL_SWITCH_SIGNAL_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            } else {
                                sparseArray.put(4649, Integer.valueOf(bydAutoEventValue.intValue));
                            }
                            break;
                        case 4732:
                            bydAutoEventValue.intValue = hasMessage(219);
                            sparseArray.put(4732, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 4733:
                            bydAutoEventValue.intValue = hasMessage(220);
                            sparseArray.put(4733, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_MOON_ROOF_CONFIG_hal_only /* 4760 */:
                            bydAutoEventValue.intValue = getMoonRoofConfig();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_MOON_ROOF_CONFIG_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_THIRD_ROW_SEATS_FEEDBACK_ON_THE_EXECUTION_RESULTS /* 4761 */:
                            bydAutoEventValue.intValue = getThirdRowSeatsExecutionResults();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_THIRD_ROW_SEATS_FEEDBACK_ON_THE_EXECUTION_RESULTS, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_FSE_SEAT_SYSTEM_WORK_MODE /* 4762 */:
                            bydAutoEventValue.intValue = getFseSeatSystemWorkMode();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_FSE_SEAT_SYSTEM_WORK_MODE, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RIGHT_SEAT_RAILS_ROW3_FEEDBACK_POSITION /* 4763 */:
                            bydAutoEventValue.intValue = getRightSeatRailsRow3FeedbackPosition();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_RIGHT_SEAT_RAILS_ROW3_FEEDBACK_POSITION, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_FOUR_PEOPLE_ENJOY_CONFIG_hal_only /* 4764 */:
                            bydAutoEventValue.intValue = getFourPeopleEnjoyConfig2();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_FOUR_PEOPLE_ENJOY_CONFIG_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_RESTORE_SEAT_CONFIGURATION /* 4765 */:
                            bydAutoEventValue.intValue = getRestoreSeatConfig();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_RESTORE_SEAT_CONFIGURATION, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_FSE_LARGE_BED_MODE_CONFIGURATION /* 4766 */:
                            bydAutoEventValue.intValue = getFseLagerBedModeConfig();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_FSE_LARGE_BED_MODE_CONFIGURATION, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SEAT_LARGE_BED_MODE_CONFIGURATION /* 4767 */:
                            bydAutoEventValue.intValue = getSeatLargeBedModeConfig();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SEAT_LARGE_BED_MODE_CONFIGURATION, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SEAT_MODE_CURRENT_STATUS_FEEDBACK_TOPAD_hal_only /* 4768 */:
                            bydAutoEventValue.intValue = getSeatModeCurFeedbackStatus2();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_CURRENT_STATUS_FEEDBACK_TOPAD_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SEAT_MODE_SUCCESS_FEEDBACK_TOPAD_hal_only /* 4769 */:
                            bydAutoEventValue.intValue = getSeatModeSuccessFeedbackStatus2();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_SUCCESS_FEEDBACK_TOPAD_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SEAT_MODE_FAIL_FEEDBACK_TOPAD_hal_only /* 4770 */:
                            bydAutoEventValue.intValue = getSeatModeFailFeedbackStatus2();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_FAIL_FEEDBACK_TOPAD_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SEAT_MODE_CURRENT_STATUS_FEEDBACK_TOPAD /* 4771 */:
                            bydAutoEventValue.intValue = getSeatModeCurFeedbackStatus();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_CURRENT_STATUS_FEEDBACK_TOPAD, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SEAT_MODE_SUCCESS_FEEDBACK_TOPAD /* 4772 */:
                            bydAutoEventValue.intValue = getSeatModeSuccessFeedbackStatus();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_SUCCESS_FEEDBACK_TOPAD, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_SEAT_RAILS_ROW3_FEEDBACK_POSITION /* 4773 */:
                            bydAutoEventValue.intValue = getLeftSeatRailsRow3FeedbackPosition();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_LEFT_SEAT_RAILS_ROW3_FEEDBACK_POSITION, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_LEFT_SEAT_ROW3_NON_ELECTRICALLY_ADJUSTABLE /* 4774 */:
                            bydAutoEventValue.intValue = getLeftSeatRow3NonElectricallyAdjustState();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_LEFT_SEAT_ROW3_NON_ELECTRICALLY_ADJUSTABLE, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case BYDAutoFeatureIds.BODYWORK_SEAT_MODE_FAIL_FEEDBACK_TOPAD /* 4778 */:
                            bydAutoEventValue.intValue = getSeatModeFailFeedbackStatus();
                            sparseArray.put(BYDAutoFeatureIds.BODYWORK_SEAT_MODE_FAIL_FEEDBACK_TOPAD, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
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
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        if (eventTypes != null && eventValue.intArrayValue != null && eventTypes.length == eventValue.intArrayValue.length) {
            android.util.Log.i(TAG, "more param event type=" + Arrays.toString(eventTypes));
            if (eventTypes.length == 1) {
                if (eventTypes[0] == 1763 || eventTypes[0] == 1778 || eventTypes[0] == 2034 || eventTypes[0] == 2060) {
                    int i = eventTypes[0];
                    if (i == 1763) {
                        return setWindowControlState(1, eventValue.intArrayValue[0]);
                    }
                    if (i == 1778) {
                        return setWindowControlState(3, eventValue.intArrayValue[0]);
                    }
                    if (i == 2034) {
                        return setWindowControlState(2, eventValue.intArrayValue[0]);
                    }
                    if (i == 2060) {
                        return setWindowControlState(4, eventValue.intArrayValue[0]);
                    }
                    Log.v(TAG, "no have control window event.");
                    return -2147482648;
                }
            } else if (eventTypes.length > 1) {
                int[] data = {0, 0, 0, 0};
                boolean isWindowState = true;
                for (int i2 = 0; i2 < eventTypes.length && (isWindowState = createWindowStateData(eventTypes[i2], eventValue.intArrayValue[i2], data)); i2++) {
                }
                if (isWindowState) {
                    return setWindowControlState(data);
                }
            }
        }
        return super.set(eventTypes, eventValue);
    }

    private boolean createWindowStateData(int eventType, int eventValue, int[] data) {
        if (eventType == 1763) {
            data[0] = eventValue;
        } else if (eventType == 1778) {
            data[2] = eventValue;
        } else if (eventType == 2034) {
            data[1] = eventValue;
        } else if (eventType == 2060) {
            data[3] = eventValue;
        } else {
            Log.v(TAG, "no have control window feature_id.");
            return false;
        }
        return true;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        switch (eventType) {
            case 1688:
                setBackDoorHeightPosition(intValue);
                Log.v(TAG, "setElecBackDoorControlState" + intValue);
                return 0;
            case 1715:
                setElecBackDoorControlState(intValue);
                Log.v(TAG, "setElecBackDoorControlState" + intValue);
                return 0;
            case 1721:
                setAllDoorsLock(intValue);
                Log.v(TAG, "setDoorLockStatusAll" + intValue);
                return 0;
            case 1766:
                setWindowOpenPercent(1, intValue);
                Log.v(TAG, "setWindowOpenPercent" + intValue);
                return 0;
            case 1781:
                setWindowOpenPercent(3, intValue);
                Log.v(TAG, "setWindowOpenPercent" + intValue);
                return 0;
            case 1783:
                settingLuggage(intValue);
                Log.v(TAG, "settingLuggage" + intValue);
                return 0;
            case 1784:
                setLeftSlideDoorVoiceControlState(intValue);
                Log.v(TAG, "setLeftSlideDoorVoiceControlState" + intValue);
                return 0;
            case 1790:
                setWindowOpenPercent(5, intValue);
                Log.v(TAG, "setWindowOpenPercent" + intValue);
                return 0;
            case 2004:
                turnRearviewMirror(intValue);
                Log.v(TAG, "turnRearviewMirror:" + intValue);
                return 0;
            case 2013:
                setWindowOpenPercent(7, intValue);
                Log.v(TAG, "setWindowOpenPercent" + intValue);
                return 0;
            case 2015:
                setAutoExternalRearMirrorFoldState(intValue);
                Log.v(TAG, "setAutoExternalRearMirrorFoldS" + intValue);
                return 0;
            case 2037:
                setWindowOpenPercent(2, intValue);
                Log.v(TAG, "setWindowOpenPercent" + intValue);
                return 0;
            case 2063:
                setWindowOpenPercent(4, intValue);
                Log.v(TAG, "setWindowOpenPercent" + intValue);
                return 0;
            case 2067:
                setRightSlideDoorVoiceControlState(intValue);
                Log.v(TAG, "setRightSlideDoorVoiceControlState" + intValue);
                return 0;
            case 2085:
                setWindowOpenPercent(6, intValue);
                Log.v(TAG, "setWindowOpenPercent" + intValue);
                return 0;
            case 2135:
                turnSteeringWheel(intValue);
                Log.v(TAG, "turnSteeringWheel:" + intValue);
                return 0;
            case 4600:
                setBackDoorHeightPositionMyCar(intValue);
                Log.v(TAG, "setBackDoorHeightPosition" + intValue);
                return 0;
            case 4641:
                setWaistSupportAdjustment(1, intValue);
                return 0;
            case 4642:
                setWaistSupportAdjustment(2, intValue);
                return 0;
            case 4644:
                setSeatModeControl(intValue);
                return 0;
            case BYDAutoFeatureIds.BODYWORK_LARGE_BED_MODE_SET /* 4775 */:
                setLargeBedMode(intValue);
                return 0;
            case BYDAutoFeatureIds.BODYWORK_FSE_LARGE_BED_SET /* 4776 */:
                setFseLargeBedMode(intValue);
                return 0;
            case BYDAutoFeatureIds.BODYWORK_SEAT_ROW3_ONEKEY_ENTER_PIT_SET /* 4777 */:
                setSeatRow3OnekeyEnterPit(intValue);
                return 0;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }

    private void hasMessageListener(int realId, int hasMessageId, int hasMessageState) {
        int hasMessageState2;
        Log.v(TAG, "hasMessageListener realId is " + realId + ",hasMessageId" + hasMessageId);
        if (hasMessageState == 0) {
            hasMessageState2 = 1;
        } else {
            hasMessageState2 = 0;
        }
        for (AbsBYDAutoBodyworkListener listener : this.mAbsBydAutoBodyworkListeners) {
            if (hasMessageId != 315) {
                if (hasMessageId == 985) {
                    listener.onMessage5sOnlineStateChanged(realId, hasMessageState2);
                    listener.onHasMessageChanged(realId, hasMessageState2);
                } else if (hasMessageId != 1028 && hasMessageId != 1054 && hasMessageId != 1092 && hasMessageId != 1268 && hasMessageId != 1270) {
                    listener.onHasMessageChanged(realId, hasMessageState2);
                }
            }
            listener.onMessage5sOnlineStateChanged(realId, hasMessageState2);
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

    public int getSeatLumbarSupportConfig(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfig mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 1) {
            int mainRight = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainRight(area);
            int mainLeft = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainLeft(area);
            if (mainRight == 1 || mainLeft == 1) {
                return 1;
            }
        } else if (area == 2) {
            int coPilotRight = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotRight(area);
            int coPilotLeft = this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotLeft(area);
            if (coPilotRight == 1 || coPilotLeft == 1) {
                return 1;
            }
        }
        return 0;
    }

    public int getSeatLumbarSupportConfigMainLeft(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigMainLeft mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigMainLeft(area);
    }

    public int getSeatLumbarSupportConfigCoPilotLeft(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigCoPilotLeft mForTestValue is 0");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotLeft(area);
    }

    public int getSeatLumbarSupportConfigCoPilotRight(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLumbarSupportConfigCoPilotRight mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatLumbarSupportConfigCoPilotRight(area);
    }

    public int setWaistSupportAdjustment(int area, int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWaistSupportAdjustment mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 1) {
            return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentMain(area, state);
        }
        if (area == 2) {
            return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentCoPilot(area, state);
        }
        return 0;
    }

    public int setWaistSupportAdjustmentCoPilot(int area, int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setWaistSupportAdjustmentCoPilot mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWaistSupportAdjustmentCoPilot(area, state);
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
        int value437 = this.mTsManagerImpl.getCarBodyManager().getFourPeopleEnjoyConfig();
        int value168 = this.mTsManagerImpl.getCarBodyManager().getFourPeopleEnjoyConfig2();
        Log.v(TAG, "getFourPeopleEnjoyConfig value437:" + value437 + ", value168:" + value168);
        if (value437 == 2 || value168 == 2) {
            return 2;
        }
        return value437;
    }

    public int setSeatModeControl(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSeatModeControl mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSeatModeControl(mode);
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

    public int getInternalDoorHandleState(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getInternalDoorHandleState mForTestValue is 0 execute return");
            return -1;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateLeftFront(area);
        }
        if (area == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateRightFront(area);
        }
        if (area == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateLeftBack(area);
        }
        if (area == 4) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateRightBack(area);
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

    public int setSideWindowShadeState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSideWindowShadeState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSideWindowShadeState(state);
    }

    public int getInternalDoorHandleStateByRearBcm(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getInternalDoorHandleStateByRearBcm is 0 execute return");
            return -1;
        }
        if (area == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmlfr();
        }
        if (area == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmlrr();
        }
        if (area == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getInternalDoorHandleStateByRearBcmrfr();
        }
        if (area == 4) {
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

    public int setModeCommand(int[] commands) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setModeCommand mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarBodyManager().setModeCommand(commands);
    }

    public int setModeCommandB(int[] commands) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setModeCommandB mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setModeCommandB(commands);
    }

    public int setModeCommandC(int[] commands) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setModeCommandC mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setModeCommandC(commands);
    }

    public int getThirdRowSeatsExecutionResults() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getThirdRowSeatsExecutionResults mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getThirdRowSeatsExecutionResults();
    }

    public int getLeftSeatRailsRow3FeedbackPosition() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftSeatRailsRow3FeedbackPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftSeatRailsRow3FeedbackPosition();
    }

    public int getRightSeatRailsRow3FeedbackPosition() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRightSeatRailsRow3FeedbackPosition mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRightSeatRailsRow3FeedbackPosition();
    }

    public int getLeftSeatRow3NonElectricallyAdjustState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getLeftSeatRow3NonElectricallyAdjustState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getLeftSeatRow3NonElectricallyAdjustState();
    }

    public int getFseSeatSystemWorkMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFseSeatSystemWorkMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFseSeatSystemWorkMode();
    }

    public int getFourPeopleEnjoyConfig2() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFourPeopleEnjoyConfig2 mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFourPeopleEnjoyConfig2();
    }

    public int getRestoreSeatConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRestoreSeatConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getRestoreSeatConfig();
    }

    public int getSeatLargeBedModeConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatLargeBedModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatLargeBedModeConfig();
    }

    public int getFseLagerBedModeConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFseLagerBedModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getFseLagerBedModeConfig();
    }

    public int getSeatModeCurFeedbackStatus2() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeCurFeedbackStatus2 mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatModeCurFeedbackStatus2();
    }

    public int getSeatModeSuccessFeedbackStatus2() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeSuccessFeedbackStatus2 mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatModeSuccessFeedbackStatus2();
    }

    public int getSeatModeFailFeedbackStatus2() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeFailFeedbackStatus2 mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getSeatModeFailFeedbackStatus2();
    }

    public int getSeatModeCurFeedbackStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeCurFeedbackStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value306 = this.mTsManagerImpl.getCarBodyManager().getSeatModeCurFeedbackStatus();
        int value168 = this.mTsManagerImpl.getCarBodyManager().getSeatModeCurFeedbackStatus2();
        Log.v(TAG, "getSeatModeCurFeedbackStatus value306:" + value306 + ", value168:" + value168);
        if (value168 > 0 && value168 < 16) {
            return value168;
        }
        return value306;
    }

    public int getSeatModeSuccessFeedbackStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeSuccessFeedbackStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value306 = this.mTsManagerImpl.getCarBodyManager().getSeatModeSuccessFeedbackStatus();
        int value168 = this.mTsManagerImpl.getCarBodyManager().getSeatModeSuccessFeedbackStatus2();
        Log.v(TAG, "getSeatModeSuccessFeedbackStatus value306:" + value306 + ", value168:" + value168);
        if (value168 > 0 && value168 < 16) {
            return value168;
        }
        return value306;
    }

    public int getSeatModeFailFeedbackStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSeatModeFailFeedbackStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value306 = this.mTsManagerImpl.getCarBodyManager().getSeatModeFailFeedbackStatus();
        int value168 = this.mTsManagerImpl.getCarBodyManager().getSeatModeFailFeedbackStatus2();
        Log.v(TAG, "getSeatModeFailFeedbackStatus value306:" + value306 + ", value168:" + value168);
        if (value168 > 0 && value168 < 16) {
            return value168;
        }
        return value306;
    }

    public int setLargeBedMode(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setLargeBedMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setLargeBedMode(mode);
    }

    public int setFseLargeBedMode(int mode) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setFseLargeBedMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setFseLargeBedMode(mode);
    }

    public int setSeatRow3OnekeyEnterPit(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setSeatRow3OnekeyEnterPit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setSeatRow3OnekeyEnterPit(state);
    }

    public void sendBroadcastToUeReport() {
        Intent intent = new Intent();
        intent.setAction(ACTION_UE_REPORT_RE_REGISTER);
        this.mContext.sendBroadcast(intent);
    }
}
