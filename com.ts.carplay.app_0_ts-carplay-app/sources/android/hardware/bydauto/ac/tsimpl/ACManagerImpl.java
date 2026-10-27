package android.hardware.bydauto.ac.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto_test.TestACManagerImplCallback;
import android.util.SparseArray;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ACManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_ACManagerImpl";
    private static final String TAG = "ACManagerImpl";
    private static final int TEST_VALUE = 100;
    private static final int TWO = 2;
    private static volatile ACManagerImpl sInstance;
    private AbsBYDAutoAcListener mAbsBydAutoAcListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private TestACManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 100;
    private final CopyOnWriteArrayList<AbsBYDAutoAcListener> mAbsBydAutoAcListeners = new CopyOnWriteArrayList<>();
    private int mOnline = 0;
    private int mAirOutletOnline = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    ACManagerImpl(Context con) {
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.ac.tsimpl.ACManagerImpl.1
            public void onHvacChanged(int id, int statusId, HvacPacket packet) {
                if (!ACManagerImpl.this.mAbsBydAutoAcListeners.isEmpty()) {
                    try {
                        ACManagerImpl.this.hvacCallback(id, statusId, packet);
                    } catch (Exception e) {
                        Log.e(ACManagerImpl.TAG, "hvacCallback is error");
                    }
                }
            }
        });
    }

    public static ACManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (ACManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new ACManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public int getAcStartState() {
        Log.v(TAG, "getAcStartState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcStartState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int acStartState = this.mTsManagerImpl.getHvacAdapterManager().getAcStartState();
        Log.v(TAG, "getAcStartState value is: " + acStartState);
        if (1 == acStartState) {
            return 1;
        }
        if (2 == acStartState) {
            return 0;
        }
        if (acStartState == 0) {
            return -1;
        }
        return acStartState;
    }

    public int getAcCompressorManualSign() {
        Log.v(TAG, "getAcCompressorManualSign is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcCompressorManualSign mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acCompressorManualSign = this.mTsManagerImpl.getHvacAdapterManager().getAcCompressorManualSign();
        Log.v(TAG, "getAcCompressorManualSign value is: " + acCompressorManualSign);
        if (acCompressorManualSign == 0) {
            return 0;
        }
        if (acCompressorManualSign == 1) {
            return 1;
        }
        return -1;
    }

    public int getHeatPumpCompressorWorkMode() {
        Log.v(TAG, "getHeatPumpCompressorWorkMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getHeatPumpCompressorWorkMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int heatPumpCompressorWorkMode = this.mTsManagerImpl.getHvacAdapterManager().getHeatPumpCompressorWorkMode();
        Log.v(TAG, "getHeatPumpCompressorWorkMode value is:" + heatPumpCompressorWorkMode);
        if (heatPumpCompressorWorkMode == 0) {
            return 0;
        }
        if (heatPumpCompressorWorkMode == 1) {
            return 1;
        }
        if (heatPumpCompressorWorkMode == 2) {
            return 2;
        }
        if (heatPumpCompressorWorkMode == 3) {
            return 3;
        }
        return -1;
    }

    public int getAcCompressorMode() {
        Log.v(TAG, "getAcCompressorMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcCompressorMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acCompressorMode = this.mTsManagerImpl.getHvacAdapterManager().getAcCompressorMode();
        Log.v(TAG, "getAcCompressorMode value is:" + acCompressorMode);
        if (acCompressorMode == 0) {
            return 0;
        }
        if (acCompressorMode == 1) {
            return 1;
        }
        return -1;
    }

    public int getAcMaxCoolingState() {
        Log.v(TAG, "getAcMaxCoolingState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcMaxCoolingState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int result = this.mTsManagerImpl.getHvacAdapterManager().getAcMaxCoolingState();
        if (result == 2) {
            return 0;
        }
        if (result == 0) {
            return -1;
        }
        return result;
    }

    public int getAcDefrostState(int area) {
        Log.v(TAG, "getAcDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        Log.v(TAG, "getAcDefrostState area  is: " + area);
        if (area == 1) {
            int acDefrostStateFront = this.mTsManagerImpl.getHvacAdapterManager().getAcDefrostStateFront();
            Log.v(TAG, "getAcDefrostState value is: " + acDefrostStateFront);
            if (acDefrostStateFront == 1) {
                return 0;
            }
            if (acDefrostStateFront == 2) {
                return 1;
            }
            if (acDefrostStateFront == 0) {
                return -1;
            }
            return acDefrostStateFront;
        }
        if (area != 2) {
            return 0;
        }
        int acDefrostStateRear = getElectricDefrostState();
        Log.v(TAG, "getAcDefrostState value is: " + acDefrostStateRear);
        return acDefrostStateRear;
    }

    public int getAcWindLevelManualSign() {
        Log.v(TAG, "getAcWindLevelManualSign is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWindLevelManualSign mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acWindLevelManualSign = this.mTsManagerImpl.getHvacAdapterManager().getAcWindLevelManualSign();
        Log.v(TAG, "getAcWindLevelManualSign value is: " + acWindLevelManualSign);
        if (acWindLevelManualSign == 0) {
            return 0;
        }
        if (acWindLevelManualSign == 1) {
            return 1;
        }
        return -1;
    }

    public int getAcWindModeManualSign() {
        Log.v(TAG, "getAcWindModeManualSign is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWindModeManualSign mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWindModeManualSign();
    }

    public int getAcControlMode() {
        Log.v(TAG, "getAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int acControlMode = this.mTsManagerImpl.getHvacAdapterManager().getAcControlMode();
        Log.v(TAG, "getAcControlMode value is: " + acControlMode);
        if (1 == acControlMode) {
            return 1;
        }
        if (2 == acControlMode) {
            return 0;
        }
        if (acControlMode == 0) {
            return -1;
        }
        return acControlMode;
    }

    public int getAcCycleMode() {
        Log.v(TAG, "getAcCycleMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcCycleMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int acCycleMode = this.mTsManagerImpl.getHvacAdapterManager().getAcCycleMode();
        Log.v(TAG, "getAcCycleMode value is: " + acCycleMode);
        if (1 == acCycleMode) {
            return 0;
        }
        if (2 == acCycleMode) {
            return 1;
        }
        if (acCycleMode == 0) {
            return -1;
        }
        return acCycleMode;
    }

    public int getAcWindMode() {
        Log.v(TAG, "getAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWindMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWindMode();
    }

    public int getTemprature(int area) {
        Log.v(TAG, "getTemprature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getTemprature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (area == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureMain();
        }
        if (area == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureDeputy();
        }
        if (area == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureRear();
        }
        if (area == 4) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureOut();
        }
        return 0;
    }

    public int getTemperatureUnit() {
        Log.v(TAG, "getTemperatureUnit is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getTemperatureUnit mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int temperatureUnit = this.mTsManagerImpl.getHvacAdapterManager().getTemperatureUnit();
        Log.v(TAG, "getTemperatureUnit value is: " + temperatureUnit);
        return temperatureUnit == 0 ? 1 : 0;
    }

    public int getAcTemperatureControlMode() {
        Log.v(TAG, "getAcTemperatureControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcTemperatureControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int acTemperatureControlMode = this.mTsManagerImpl.getHvacAdapterManager().getAcTemperatureControlMode();
        Log.v(TAG, "getAcTemperatureControlMode value is: " + acTemperatureControlMode);
        if (acTemperatureControlMode == 1) {
            return 0;
        }
        if (acTemperatureControlMode == 2) {
            return 1;
        }
        if (acTemperatureControlMode == 0) {
            return -1;
        }
        return acTemperatureControlMode;
    }

    public int getAcVentilationState() {
        Log.v(TAG, "getAcVentilationState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcVentilationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int acVentilationState = this.mTsManagerImpl.getHvacAdapterManager().getAcVentilationState();
        if (acVentilationState == 1) {
            return 1;
        }
        return (acVentilationState != 2 && acVentilationState == 0) ? -1 : 0;
    }

    public int getRearAcStartState() {
        Log.v(TAG, "getRearAcStartState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearAcStartState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int rearAcStartState = this.mTsManagerImpl.getHvacAdapterManager().getRearAcStartState();
        if (rearAcStartState == 1) {
            return 1;
        }
        if (rearAcStartState == 2) {
            return 0;
        }
        if (rearAcStartState == 0) {
            return -1;
        }
        return rearAcStartState;
    }

    public int setAcCycleMode(int setSource, int mode) {
        Log.v(TAG, "setAcCycleMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1002, mode);
            Log.v(TAG, "setAcCycleMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (mode == 0) {
            mode = 2;
        }
        Log.v(TAG, "setAcCycleMode param0 is:" + setSource + " param1 is:" + mode);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCycleMode(setSource, mode);
    }

    public int setAcControlMode(int setSource, int mode) {
        Log.v(TAG, "setAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1009, mode);
            Log.v(TAG, "setAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (mode == 0) {
            mode = 2;
        }
        Log.v(TAG, "setAcControlMode param0 is:" + setSource + " param1 is:" + mode);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcControlMode(setSource, mode);
    }

    public int setAcTemperature(int area, int value, int tempSource, int unit) {
        Log.v(TAG, "setAcTemperature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1012, area, value);
            Log.v(TAG, "setAcTemperature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (area == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureMain(value);
        }
        if (area == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureDeputy(value);
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureMainDeputy(value);
        }
        if (area == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureRear(value);
        }
        return 0;
    }

    public int setAcTemperatureControlMode(int setSource, int mode) {
        int convertMode;
        Log.v(TAG, "setAcTemperatureControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1010, mode);
            Log.v(TAG, "setAcTemperatureControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (mode == 0) {
            convertMode = 1;
        } else if (mode == 1) {
            convertMode = 2;
        } else {
            convertMode = mode;
        }
        Log.v(TAG, "setAcControlMode param0 is:" + setSource + " param1 is:" + convertMode);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureControlMode(setSource, convertMode);
    }

    public int setAcVentilationState(int setSource, int state) {
        int convertState;
        Log.v(TAG, "setAcVentilationState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1000, state);
            Log.v(TAG, "setAcVentilationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (state == 0) {
            convertState = 2;
        } else if (state == 1) {
            convertState = 1;
        } else {
            convertState = state;
        }
        Log.v(TAG, "setAcControlMode param0 is:" + setSource + " param1 is:" + convertState);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcVentilationState(setSource, convertState);
    }

    public int start(int setSource) {
        Log.v(TAG, "start is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1006, 0);
            Log.v(TAG, "start mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().start(setSource);
    }

    public int startRearAc(int setSource) {
        Log.v(TAG, "startRearAc is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1014, 0);
            Log.v(TAG, "startRearAc mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().startRearAc(setSource);
    }

    public int stop(int setSource) {
        Log.v(TAG, "stop is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1017, 0);
            Log.v(TAG, "stop mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().stop(setSource);
    }

    public int stopRearAc(int setSource) {
        Log.v(TAG, "stopRearAc is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1011, 0);
            Log.v(TAG, "stopRearAc mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().stopRearAc(setSource);
    }

    public int getAcAssistModeTipsState() {
        Log.v(TAG, "getAcAssistModeTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcAssistModeTipsState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcAssistModeTipsState();
    }

    public int getAcKeepEngineRunTipsState() {
        Log.v(TAG, "getAcKeepEngineRunTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcKeepEngineRunTipsState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcKeepEngineRunTipsState();
    }

    public int getAcbtWindTipsState() {
        Log.v(TAG, "getAcbtWindTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcbtWindTipsState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcbtWindTipsState();
    }

    public int getAcDefrostRearPanelNotAvailableTipsState() {
        Log.v(TAG, "getAcDefrostRearPanelNotAvailableTipsState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcDefrostRearPanelNotAvailableTipsState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcDefrostRearPanelNotAvailableTipsState();
    }

    public int getAcHeatingButtonConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getAcHeatingButtonConfig mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcHeatingButtonConfig();
    }

    public int getAcHeatingState() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getAcHeatingState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcHeatingState();
    }

    public int setAcHeatingState(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setAcHeatingState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcHeatingState(state);
    }

    public int getTempRegulatingAccuracy() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTempRegulatingAccuracy mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getTempRegulatingAccuracy();
    }

    public void registerListener(AbsBYDAutoAcListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoAcListener listener, int[] featureIds) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe ac listener " + listener + " featureIDs = " + Arrays.toString(featureIds));
        if (listener == null) {
            return;
        }
        if (this.mForTest == 0) {
            Log.v(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBydAutoAcListener(listener);
            return;
        }
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (!this.mAbsBydAutoAcListeners.contains(listener)) {
            this.mAbsBydAutoAcListeners.add(listener);
        }
        setFeatureIds(featureIds);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
    }

    public void unregisterListener(AbsBYDAutoAcListener listener) {
        Log.v(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.removeListener(listener);
            Log.v(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.v(TAG, "unsubscribeHvacCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoAcListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int id, int statusId, HvacPacket hvacPacket) {
        int tempUnit;
        int resultRequestRight;
        int result;
        int resultWindLevelManualSign;
        if (hvacPacket == null) {
            Log.e(TAG, "hvacCallback is null");
        }
        Log.v(TAG, "hvacCallback propertyId:" + id + "hvacPacket" + hvacPacket.toString());
        switch (id) {
            case 557846528:
                int temperatureUnit = hvacPacket.getTemperatureUnit();
                Log.v(TAG, "getTemperatureUnit value is: " + temperatureUnit);
                if (temperatureUnit == 0) {
                    tempUnit = 1;
                } else {
                    tempUnit = 0;
                }
                for (AbsBYDAutoAcListener listener : this.mAbsBydAutoAcListeners) {
                    listener.onTemperatureUnitChanged(tempUnit);
                }
                break;
            case 557846532:
                for (AbsBYDAutoAcListener listener2 : this.mAbsBydAutoAcListeners) {
                    listener2.onRearAcMaxWindLevelChanged(hvacPacket.getAcRearAcWindLevel());
                }
                break;
            case 557846542:
                for (AbsBYDAutoAcListener listener3 : this.mAbsBydAutoAcListeners) {
                    listener3.onAcFaultNumShownStateChanged(hvacPacket.getAcFaultNumShownState());
                }
                break;
            case 557846543:
                int acWindLevelManualSign = hvacPacket.getAcTempCtrlSeparateState();
                Log.v(TAG, "ID_AC_TEMPCTRL_SEPARATE_STATE value is: " + acWindLevelManualSign);
                if (acWindLevelManualSign == 0) {
                    acWindLevelManualSign = -1;
                }
                if (acWindLevelManualSign == 1) {
                    acWindLevelManualSign = 0;
                }
                if (acWindLevelManualSign == 2) {
                    acWindLevelManualSign = 1;
                }
                for (AbsBYDAutoAcListener listener4 : this.mAbsBydAutoAcListeners) {
                    listener4.onAcTemperatureControlModeChanged(acWindLevelManualSign);
                }
                break;
            case 557846547:
                int acControlMode = hvacPacket.getAcCtrlMode();
                Log.v(TAG, "ID_AC_REAR_CTRL_MODE value is: " + acControlMode);
                if (1 == acControlMode) {
                    acControlMode = 1;
                } else if (2 == acControlMode) {
                    acControlMode = 0;
                } else if (acControlMode == 0) {
                    acControlMode = -1;
                }
                for (AbsBYDAutoAcListener listener5 : this.mAbsBydAutoAcListeners) {
                    listener5.onAcCtrlModeChanged(acControlMode);
                }
                break;
            case 557846549:
                int acDefrostStateFront = hvacPacket.getAcDefrost();
                Log.v(TAG, "getAcDefrostState value is: " + acDefrostStateFront);
                if (acDefrostStateFront == 1) {
                    acDefrostStateFront = 0;
                } else if (acDefrostStateFront == 2) {
                    acDefrostStateFront = 1;
                } else if (acDefrostStateFront == 0) {
                    acDefrostStateFront = -1;
                }
                for (AbsBYDAutoAcListener listener6 : this.mAbsBydAutoAcListeners) {
                    listener6.onAcDefrostStateChanged(1, acDefrostStateFront);
                }
                break;
            case 557846552:
                int state = hvacPacket.getAcOpenState();
                for (AbsBYDAutoAcListener listener7 : this.mAbsBydAutoAcListeners) {
                    if (state == 1) {
                        listener7.onAcStarted();
                    } else if (state == 2) {
                        listener7.onAcStoped();
                    }
                }
                break;
            case 557846553:
                int acCycleMode = hvacPacket.getAcCircleMode();
                Log.v(TAG, "getAcCycleMode value is: " + acCycleMode);
                if (1 == acCycleMode) {
                    acCycleMode = 0;
                } else if (2 == acCycleMode) {
                    acCycleMode = 1;
                } else if (acCycleMode == 0) {
                    acCycleMode = -1;
                }
                for (AbsBYDAutoAcListener listener8 : this.mAbsBydAutoAcListeners) {
                    listener8.onAcCycleModeChanged(acCycleMode);
                }
                break;
            case 557846557:
                for (AbsBYDAutoAcListener listener9 : this.mAbsBydAutoAcListeners) {
                    listener9.onAcWarmTypeOnlineStateChanged(hvacPacket.getAcHeatModeFlag());
                }
                break;
            case 557846558:
                int acCompressorMode = hvacPacket.getAcHeatPumpCompressorMode();
                Log.v(TAG, "ID_AC_HEAT_PUMP_COMPRESSOR_MODE value is:" + acCompressorMode);
                for (AbsBYDAutoAcListener listener10 : this.mAbsBydAutoAcListeners) {
                    listener10.onHeatPumpCompressorWorkModeChanged(acCompressorMode);
                }
                break;
            case 557846559:
                int acVentilationState = hvacPacket.getAcNaturalWind();
                Log.v(TAG, "ID_AC_NATURAL_WIND_STATE value is " + acVentilationState);
                if (acVentilationState == 1) {
                    acVentilationState = 1;
                } else if (acVentilationState == 2) {
                    acVentilationState = 0;
                } else if (acVentilationState == 0) {
                    acVentilationState = -1;
                }
                for (AbsBYDAutoAcListener listener11 : this.mAbsBydAutoAcListeners) {
                    listener11.onAcVentilationStateChanged(acVentilationState);
                }
                break;
            case 557846561:
                int acRequestRight = hvacPacket.getAcRequestRight();
                Log.v(TAG, "ID_AC_COMPRESSOR_MODE value is:" + acRequestRight);
                if (acRequestRight == 0) {
                    resultRequestRight = 0;
                } else if (acRequestRight == 1) {
                    resultRequestRight = 1;
                } else {
                    resultRequestRight = acRequestRight;
                }
                for (AbsBYDAutoAcListener listener12 : this.mAbsBydAutoAcListeners) {
                    listener12.onAcCompressorModeChanged(resultRequestRight);
                }
                break;
            case 557846563:
                for (AbsBYDAutoAcListener listener13 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_MAIN is: " + hvacPacket.getAcControllerDriverTemp());
                    listener13.onTemperatureChanged(1, hvacPacket.getAcControllerDriverTemp());
                }
                break;
            case 557846567:
                for (AbsBYDAutoAcListener listener14 : this.mAbsBydAutoAcListeners) {
                    listener14.onAcWindLevelChanged(hvacPacket.getAcFrontWindLevelR());
                }
                break;
            case 557846569:
                for (AbsBYDAutoAcListener listener15 : this.mAbsBydAutoAcListeners) {
                    listener15.onAcWindModeChanged(hvacPacket.getAcWindMode());
                }
                break;
            case 557846571:
                int acMaxCold = hvacPacket.getAcMaxCold();
                Log.v(TAG, "ID_AC_CONTROLLER_MAX_COLD_STATE value is: " + acMaxCold);
                if (acMaxCold == 0) {
                    acMaxCold = -1;
                } else if (acMaxCold == 2) {
                    acMaxCold = 0;
                }
                for (AbsBYDAutoAcListener listener16 : this.mAbsBydAutoAcListeners) {
                    listener16.onAcMaxCoolingStateChanged(acMaxCold);
                }
                caseFeatureIds(3388, Integer.valueOf(acMaxCold));
                break;
            case 557846576:
                for (AbsBYDAutoAcListener listener17 : this.mAbsBydAutoAcListeners) {
                    listener17.onAcRearPanelLockStateChanged(hvacPacket.getAcRearPanelLock());
                }
                break;
            case 557846578:
                int rearAcCtrlMode = hvacPacket.getAcRearAcCtrlMode();
                Log.v(TAG, "ID_AC_CONTROLLER_REAR_AC_CTRL_MODE value is: " + rearAcCtrlMode);
                if (2 == rearAcCtrlMode) {
                    rearAcCtrlMode = 0;
                } else if (rearAcCtrlMode == 0) {
                    rearAcCtrlMode = -1;
                }
                for (AbsBYDAutoAcListener listener18 : this.mAbsBydAutoAcListeners) {
                    listener18.onRearAcCtrlModeChanged(rearAcCtrlMode);
                }
                break;
            case 557846580:
                int rearState = hvacPacket.getAcRearAcState();
                for (AbsBYDAutoAcListener listener19 : this.mAbsBydAutoAcListeners) {
                    if (rearState == 1) {
                        listener19.onAcRearStarted();
                    } else if (rearState == 2) {
                        listener19.onAcRearStoped();
                    }
                }
                break;
            case 557846582:
                for (AbsBYDAutoAcListener listener20 : this.mAbsBydAutoAcListeners) {
                    listener20.onRearAcWindModeChanged(hvacPacket.getAcRearWindMode());
                }
                break;
            case 557846584:
                for (AbsBYDAutoAcListener listener21 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_REAR is: " + hvacPacket.getAcRearTemp());
                    listener21.onTemperatureChanged(3, hvacPacket.getAcRearTemp());
                }
                break;
            case 557846586:
                for (AbsBYDAutoAcListener listener22 : this.mAbsBydAutoAcListeners) {
                    listener22.onRearAcWindLevelChanged(hvacPacket.getAcRearWindLevel());
                }
                break;
            case 557846588:
                for (AbsBYDAutoAcListener listener23 : this.mAbsBydAutoAcListeners) {
                    listener23.onRearAcKeyLockStateChanged(hvacPacket.getAcRearLock());
                }
                caseFeatureIds(3477, Integer.valueOf(hvacPacket.getAcRearLock()));
                break;
            case 557846590:
                int acCompressorManualSign = hvacPacket.getAcCompressorManualSign();
                Log.v(TAG, "ID_AC_COMPRESSOR_MANUAL_SIGN value is: " + acCompressorManualSign);
                if (acCompressorManualSign == 0) {
                    result = 0;
                } else if (acCompressorManualSign == 1) {
                    result = 1;
                } else {
                    result = -1;
                }
                for (AbsBYDAutoAcListener listener24 : this.mAbsBydAutoAcListeners) {
                    listener24.onAcCompressorManualSignChanged(result);
                }
                break;
            case 557846591:
                int acWindLevelManualSign2 = hvacPacket.getAcWindLevelManualSign();
                Log.v(TAG, "ID_AC_WIND_LEVEL_MANUAL_SIGN value is: " + acWindLevelManualSign2);
                if (acWindLevelManualSign2 == 0) {
                    resultWindLevelManualSign = 0;
                } else if (acWindLevelManualSign2 == 1) {
                    resultWindLevelManualSign = 1;
                } else {
                    resultWindLevelManualSign = acWindLevelManualSign2;
                }
                for (AbsBYDAutoAcListener listener25 : this.mAbsBydAutoAcListeners) {
                    listener25.onAcWindLevelManualSignChanged(resultWindLevelManualSign);
                }
                break;
            case 557846592:
                for (AbsBYDAutoAcListener listener26 : this.mAbsBydAutoAcListeners) {
                    listener26.onAcWindModeManualSignChanged(hvacPacket.getAcWindModeManualSign());
                }
                break;
            case 557846596:
                for (AbsBYDAutoAcListener listener27 : this.mAbsBydAutoAcListeners) {
                    listener27.onHighTempAntivirusCountDownChanged(hvacPacket.getHighTempAntivirusCountDown());
                }
                break;
            case 557846597:
                for (AbsBYDAutoAcListener listener28 : this.mAbsBydAutoAcListeners) {
                    listener28.onHighTempAntivirusStateChanged(hvacPacket.getHighTempAntivirusState());
                }
                break;
            case 557846601:
                for (AbsBYDAutoAcListener listener29 : this.mAbsBydAutoAcListeners) {
                    listener29.onAcPtcPreheatSignalChanged(hvacPacket.getAcPtcPreheatSignal());
                }
                break;
            case 557846603:
                for (AbsBYDAutoAcListener listener30 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_DEPUTY is: " + hvacPacket.getAcTempratureDeputy());
                    listener30.onTemperatureChanged(2, hvacPacket.getAcTempratureDeputy());
                }
                break;
            case 557846606:
                for (AbsBYDAutoAcListener listener31 : this.mAbsBydAutoAcListeners) {
                    listener31.onQuickCleanAirStateChanged(hvacPacket.getQuickCleanAirStateR());
                }
                break;
            case 557846607:
                for (AbsBYDAutoAcListener listener32 : this.mAbsBydAutoAcListeners) {
                    listener32.onQuickCleanTipChanged(hvacPacket.getQuickCleanTipR());
                }
                break;
            case 557846608:
                for (AbsBYDAutoAcListener listener33 : this.mAbsBydAutoAcListeners) {
                    listener33.onAutoCleanAirStateChanged(hvacPacket.getAutoCleanAirStateR());
                }
                caseFeatureIds(3316, Integer.valueOf(hvacPacket.getAutoCleanAirStateR()));
                break;
            case 557846609:
                for (AbsBYDAutoAcListener listener34 : this.mAbsBydAutoAcListeners) {
                    listener34.onACAssistModeTips(hvacPacket.getAcAssistModeTips());
                }
                break;
            case 557846611:
                for (AbsBYDAutoAcListener listener35 : this.mAbsBydAutoAcListeners) {
                    listener35.onACBTWindTips(hvacPacket.getAcbtWindTips());
                }
                break;
            case 557846612:
                for (AbsBYDAutoAcListener listener36 : this.mAbsBydAutoAcListeners) {
                    listener36.onACDefrostRearPanelNotAvailableTips(hvacPacket.getAcDefrosRearPanelNotAvailableTips());
                }
                break;
            case 557846613:
                for (AbsBYDAutoAcListener listener37 : this.mAbsBydAutoAcListeners) {
                    listener37.onAcDefrostStateChanged(2, hvacPacket.getAcDefrostOpenStateLeft());
                }
                break;
            case 557846622:
                for (AbsBYDAutoAcListener listener38 : this.mAbsBydAutoAcListeners) {
                    listener38.onFreeWindBTNStateChanged(0, hvacPacket.getFreeWindBtnStateDriverR());
                }
                caseFeatureIds(3359, Integer.valueOf(hvacPacket.getFreeWindBtnStateDriverR()));
                break;
            case 557846623:
                for (AbsBYDAutoAcListener listener39 : this.mAbsBydAutoAcListeners) {
                    listener39.onFreeWindBTNStateChanged(1, hvacPacket.getFreeWindBtnStateCopilotR());
                }
                caseFeatureIds(3413, Integer.valueOf(hvacPacket.getFreeWindBtnStateCopilotR()));
                break;
            case 557846624:
                for (AbsBYDAutoAcListener listener40 : this.mAbsBydAutoAcListeners) {
                    listener40.onBlowPeopleState(0, hvacPacket.getBlowPeopleStateDriverR());
                }
                caseFeatureIds(3357, Integer.valueOf(hvacPacket.getBlowPeopleStateDriverR()));
                break;
            case 557846625:
                for (AbsBYDAutoAcListener listener41 : this.mAbsBydAutoAcListeners) {
                    listener41.onBlowPeopleState(1, hvacPacket.getBlowPeopleStateCopilotR());
                }
                caseFeatureIds(3411, Integer.valueOf(hvacPacket.getBlowPeopleStateCopilotR()));
                break;
            case 557846626:
                for (AbsBYDAutoAcListener listener42 : this.mAbsBydAutoAcListeners) {
                    listener42.onAvoidBlowPeopleStateChanged(0, hvacPacket.getAvoidBlowPeopleStateDriverR());
                }
                caseFeatureIds(3382, Integer.valueOf(hvacPacket.getAvoidBlowPeopleStateDriverR()));
                break;
            case 557846627:
                for (AbsBYDAutoAcListener listener43 : this.mAbsBydAutoAcListeners) {
                    listener43.onAvoidBlowPeopleStateChanged(1, hvacPacket.getAvoidBlowPeopleStateCopilotR());
                }
                caseFeatureIds(3436, Integer.valueOf(hvacPacket.getAvoidBlowPeopleStateCopilotR()));
                break;
            case 557846628:
                for (AbsBYDAutoAcListener listener44 : this.mAbsBydAutoAcListeners) {
                    listener44.onSweepBlowStateChanged(0, hvacPacket.getSweepBlowStateDriverR());
                }
                caseFeatureIds(3361, Integer.valueOf(hvacPacket.getSweepBlowStateDriverR()));
                break;
            case 557846629:
                for (AbsBYDAutoAcListener listener45 : this.mAbsBydAutoAcListeners) {
                    listener45.onSweepBlowStateChanged(1, hvacPacket.getSweepBlowStateCopilotR());
                }
                caseFeatureIds(3415, Integer.valueOf(hvacPacket.getSweepBlowStateCopilotR()));
                break;
            case 557846630:
                for (AbsBYDAutoAcListener listener46 : this.mAbsBydAutoAcListeners) {
                    listener46.onAirOutletStateChanged(0, hvacPacket.getAirOutletStateDriverR());
                }
                caseFeatureIds(3384, Integer.valueOf(hvacPacket.getAirOutletStateDriverR()));
                break;
            case 557846631:
                for (AbsBYDAutoAcListener listener47 : this.mAbsBydAutoAcListeners) {
                    listener47.onAirOutletStateChanged(1, hvacPacket.getAirOutletStateCopilotR());
                }
                caseFeatureIds(3438, Integer.valueOf(hvacPacket.getAirOutletStateCopilotR()));
                break;
            case 557846632:
                for (AbsBYDAutoAcListener listener48 : this.mAbsBydAutoAcListeners) {
                    listener48.onIntelligentControlStateChanged(hvacPacket.getIntelligentControlStateR());
                }
                caseFeatureIds(3352, Integer.valueOf(hvacPacket.getIntelligentControlStateR()));
                break;
            case 557846633:
                for (AbsBYDAutoAcListener listener49 : this.mAbsBydAutoAcListeners) {
                    listener49.onFrontRowAirOutletStateChanged(hvacPacket.getFrontRowAirOutletStateR());
                }
                caseFeatureIds(3342, Integer.valueOf(hvacPacket.getFrontRowAirOutletStateR()));
                break;
            case 557846634:
                for (AbsBYDAutoAcListener listener50 : this.mAbsBydAutoAcListeners) {
                    listener50.onACWorkModeChanged(0, hvacPacket.getAcWorkModeDriverR());
                }
                caseFeatureIds(3355, Integer.valueOf(hvacPacket.getAcWorkModeDriverR()));
                break;
            case 557846635:
                for (AbsBYDAutoAcListener listener51 : this.mAbsBydAutoAcListeners) {
                    listener51.onACWorkModeChanged(1, hvacPacket.getAcWorkModeCopilotR());
                }
                caseFeatureIds(3509, Integer.valueOf(hvacPacket.getAcWorkModeCopilotR()));
                break;
            case 557846657:
                for (AbsBYDAutoAcListener listener52 : this.mAbsBydAutoAcListeners) {
                    listener52.onAcPromptBoxShownStateChanged(hvacPacket.getAcPromptBoxShownStateR());
                }
                break;
            case 557846658:
                for (AbsBYDAutoAcListener listener53 : this.mAbsBydAutoAcListeners) {
                    listener53.onACKeepEngineRunTips(hvacPacket.getAcKeepEngineRunTIp());
                    listener53.onACMaxEVModeTips(hvacPacket.getAcKeepEngineRunTIp());
                }
                break;
            case 557846665:
                for (AbsBYDAutoAcListener listener54 : this.mAbsBydAutoAcListeners) {
                    listener54.onAcHeatingStateChanged(hvacPacket.getAcHeatingStateR());
                }
                break;
            case 557846692:
                for (AbsBYDAutoAcListener listener55 : this.mAbsBydAutoAcListeners) {
                    listener55.onACWorkCommandChanged(hvacPacket.getAcWorkModeR());
                }
                break;
            case 557846693:
                for (AbsBYDAutoAcListener listener56 : this.mAbsBydAutoAcListeners) {
                    listener56.onACWorkModeChanged(2, hvacPacket.getAcWorkModeMidLeftR());
                }
                break;
            case 557846694:
                for (AbsBYDAutoAcListener listener57 : this.mAbsBydAutoAcListeners) {
                    listener57.onACWorkModeChanged(3, hvacPacket.getAcWorkModeMidRightR());
                }
                break;
            case 557846695:
                for (AbsBYDAutoAcListener listener58 : this.mAbsBydAutoAcListeners) {
                    listener58.onACWorkModeChanged(4, hvacPacket.getAcWorkModeRearR());
                }
                break;
            case 557846696:
                for (AbsBYDAutoAcListener listener59 : this.mAbsBydAutoAcListeners) {
                    listener59.onAcTuyereSwitchStateChanged(0, hvacPacket.getAcTuyereSwitchStateMainLeftR());
                }
                break;
            case 557846697:
                for (AbsBYDAutoAcListener listener60 : this.mAbsBydAutoAcListeners) {
                    listener60.onAcTuyereSwitchStateChanged(1, hvacPacket.getAcTuyereSwitchStateMainRightR());
                }
                break;
            case 557846698:
                for (AbsBYDAutoAcListener listener61 : this.mAbsBydAutoAcListeners) {
                    listener61.onAcTuyereSwitchStateChanged(2, hvacPacket.getAcTuyereSwitchStateCopilotLeftR());
                }
                break;
            case 557846699:
                for (AbsBYDAutoAcListener listener62 : this.mAbsBydAutoAcListeners) {
                    listener62.onAcTuyereSwitchStateChanged(3, hvacPacket.getAcTuyereSwitchStateCopilotRightR());
                }
                break;
            case 557846705:
                for (AbsBYDAutoAcListener listener63 : this.mAbsBydAutoAcListeners) {
                    listener63.onAcCockpitPurificationStatusChanged(hvacPacket.getAcCockpitPurificationStatusR());
                }
                break;
            case 557856063:
                for (AbsBYDAutoAcListener listener64 : this.mAbsBydAutoAcListeners) {
                    listener64.onAcRemoteCtrlTimeChanged(hvacPacket.getAcRemoteCtrlTimeR());
                }
                break;
            case 559943766:
                for (AbsBYDAutoAcListener listener65 : this.mAbsBydAutoAcListeners) {
                    if (statusId == 2) {
                        this.mAirOutletOnline = 0;
                    } else {
                        this.mAirOutletOnline = 1;
                        listener65.onAirOutletValueChanged(0, hvacPacket.getAirOutletValueDriverLeftVerticalR());
                    }
                }
                caseFeatureIds(3365, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverLeftHorizontalR()));
                break;
            case 559943767:
                for (AbsBYDAutoAcListener listener66 : this.mAbsBydAutoAcListeners) {
                    listener66.onAirOutletValueChanged(1, hvacPacket.getAirOutletValueDriverLeftHorizontalR());
                }
                caseFeatureIds(3363, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverLeftHorizontalR()));
                break;
            case 559943768:
                for (AbsBYDAutoAcListener listener67 : this.mAbsBydAutoAcListeners) {
                    listener67.onAirOutletValueChanged(2, hvacPacket.getAirOutletValueDriverRightVerticalR());
                }
                caseFeatureIds(3369, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverRightVerticalR()));
                break;
            case 559943769:
                for (AbsBYDAutoAcListener listener68 : this.mAbsBydAutoAcListeners) {
                    listener68.onAirOutletValueChanged(3, hvacPacket.getAirOutletValueDriverRightHorizontalR());
                }
                caseFeatureIds(3368, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverRightHorizontalR()));
                break;
            case 559943770:
                for (AbsBYDAutoAcListener listener69 : this.mAbsBydAutoAcListeners) {
                    listener69.onAirOutletValueChanged(4, hvacPacket.getAirOutletValueCopilotLeftVerticalR());
                }
                break;
            case 559943771:
                for (AbsBYDAutoAcListener listener70 : this.mAbsBydAutoAcListeners) {
                    listener70.onAirOutletValueChanged(5, hvacPacket.getAirOutletValueCopilotLeftHorizontalR());
                }
                caseFeatureIds(3417, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotLeftHorizontalR()));
                break;
            case 559943772:
                for (AbsBYDAutoAcListener listener71 : this.mAbsBydAutoAcListeners) {
                    listener71.onAirOutletValueChanged(6, hvacPacket.getAirOutletValueCopilotRightVerticalR());
                }
                caseFeatureIds(3423, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotRightVerticalR()));
                break;
            case 559943773:
                for (AbsBYDAutoAcListener listener72 : this.mAbsBydAutoAcListeners) {
                    listener72.onAirOutletValueChanged(7, hvacPacket.getAirOutletValueCopilotRightHorizontalR());
                }
                caseFeatureIds(3421, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotRightHorizontalR()));
                break;
        }
    }

    public int setAcWindMode(int setSource, int mode) {
        Log.v(TAG, "setAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1005, mode);
            Log.v(TAG, "setAcWindMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.v(TAG, "setAcWindMode param0 is:" + setSource + " param1 is:" + mode);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcWindMode(setSource, mode);
    }

    public int setAcDefrostState(int setSource, int area, int state) {
        int convertState;
        Log.v(TAG, "setAcDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1007, area, state);
            Log.v(TAG, "setAcDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (state == 1) {
            convertState = 2;
        } else if (state == 0) {
            convertState = 1;
        } else {
            convertState = state;
        }
        Log.v(TAG, "setAcDefrostState param0 is:" + setSource + " param1 is:" + area + " param2 is:" + convertState);
        if (area == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcDefrostStateFront(convertState);
        }
        if (area == 2) {
            return setElectricDefrostState(convertState);
        }
        return 0;
    }

    public int setAcWindLevel(int setSource, int level) {
        Log.v(TAG, "setAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1016, level);
            Log.v(TAG, "setAcWindLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcWindLevel(setSource, level);
    }

    public int getAcWindLevel() {
        Log.v(TAG, "getAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWindLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWindLevel();
    }

    public int setRearAcKeyLockState(int state) {
        Log.v(TAG, "setRearAcKeyLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1008, state);
            Log.v(TAG, "setRearAcKeyLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcKeyLockState(state);
    }

    public int getRearAcControlMode() {
        Log.v(TAG, "getRearAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int rearAcCtrlMode = this.mTsManagerImpl.getHvacAdapterManager().getRearAcControlMode();
        Log.v(TAG, "getAcRearAcCtrlMode value is: " + rearAcCtrlMode);
        if (2 == rearAcCtrlMode) {
            return 0;
        }
        if (rearAcCtrlMode == 0) {
            return -1;
        }
        return rearAcCtrlMode;
    }

    public int getRearAcWindMode() {
        Log.v(TAG, "getRearAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearAcWindMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRearAcWindMode();
    }

    public int setAcAntibacterialControlState(int state) {
        Log.v(TAG, "setACAntibacterialControlState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setACAntibacterialControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcAntibacterialControlState(state);
    }

    public int getRearAcWindLevel() {
        Log.v(TAG, "getRearAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearAcWindLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRearAcWindLevel();
    }

    public int setRearAcWindMode(int setSource, int mode) {
        Log.v(TAG, "setRearAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1003, mode);
            Log.v(TAG, "setRearAcWindMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcWindMode(setSource, mode);
    }

    public int setRearAcWindLevel(int setSource, int level) {
        Log.v(TAG, "setRearAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1013, level);
            Log.v(TAG, "setRearAcWindLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcWindLevel(setSource, level);
    }

    public int setAcCompressorState(int state) {
        Log.v(TAG, "setAcCompressorState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1015, state);
            Log.v(TAG, "setAcCompressorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCompressorState(state);
    }

    public int getMaxAcState() {
        Log.v(TAG, "getMaxAcState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getMaxAcState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getMaxAcState();
    }

    public int setMaxAcState(int state) {
        Log.v(TAG, "setMaxAcState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1004, state);
            Log.v(TAG, "setMaxAcState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setMaxAcState(state);
    }

    public int getElectricDefrostState() {
        Log.v(TAG, "getElectricDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getElectricDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getElectricDefrostState();
    }

    public int setElectricDefrostState(int state) {
        Log.v(TAG, "setElectricDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1001, state);
            Log.v(TAG, "setElectricDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setElectricDefrostState(state);
    }

    public int setRearAcControlMode(int setSource, int mode) {
        Log.v(TAG, "setRearAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1018, mode);
            Log.v(TAG, "setRearAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int convertMode = mode;
        if (mode == 0) {
            convertMode = 2;
        }
        Log.v(TAG, "setRearAcControlMode param0 is:" + setSource + " param1 is:" + convertMode);
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcControlMode(setSource, convertMode);
    }

    public int getRearACkeyLockState() {
        Log.v(TAG, "getRearACkeyLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearACkeyLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRearACkeyLockState();
    }

    public int setAcCompressorMode(int setSource, int state) {
        int convertState;
        Log.v(TAG, "setAcCompressorMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1020, state);
            Log.v(TAG, "setAcCompressorMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (state == 0) {
            convertState = 1;
        } else if (state == 1) {
            convertState = 2;
        } else {
            convertState = state;
        }
        Log.v(TAG, "setAcCompressorMode param0 is:" + setSource + " param1 is:" + convertState);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCompressorMode(setSource, convertState);
    }

    public int setAcMaxCoolingState(int state) {
        Log.v(TAG, "setAcMaxCoolingState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1021, state);
            Log.v(TAG, "setAcMaxCoolingState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int convertValue = state;
        if (convertValue == 0) {
            convertValue = 2;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcMaxCoolingState(convertValue);
    }

    public int getACSystemState() {
        Log.v(TAG, "getACSystemState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getACSystemState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getACSystemState();
    }

    public int getACPreheatState() {
        Log.v(TAG, "getACPreheatState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getACPreheatState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getACPreheatState();
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getDevicetype();
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is execute");
        if (this.mForTest != 0) {
            return "";
        }
        Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is execute");
        if (this.mForTest != 0) {
            return "";
        }
        Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public int getAcOnlineState() {
        Log.v(TAG, "getAcOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcOnlineState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mOnline;
    }

    public int setAcWarmState(int state) {
        Log.v(TAG, "setAcWarmState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setAcWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcWarmState(state);
    }

    public int getAcWarmState() {
        Log.v(TAG, "getAcWarmState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWarmState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWarmState();
    }

    public int getAcWarmTypeOnlineState() {
        Log.v(TAG, "getAcWarmTypeOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWarmTypeOnlineState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWarmTypeOnlineState();
    }

    public int getAcPromptBoxShownState() {
        Log.v(TAG, "getAcPromptBoxShownState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcPromptBoxShownState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcPromptBoxShownState();
    }

    public int getAcFaultNumShownState() {
        Log.v(TAG, "getAcFaultNumShownState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcFaultNumShownState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcFaultNumShownState();
    }

    public int getAcWindModeShownState() {
        Log.v(TAG, "getAcWindModeShownState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWindModeShownState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWindModeShownState();
    }

    public int getAirQualityCtrlMenuState() {
        Log.v(TAG, "getAirQualityCtrlMenuState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAirQualityCtrlMenuState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAirQualityCtrlMenuState();
    }

    public int getAcKeyActionState() {
        Log.v(TAG, "getAcKeyActionState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcKeyActionState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcKeyActionState();
    }

    public int setRearAcLockState(int state) {
        Log.v(TAG, "setRearAcLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1023, state);
            Log.v(TAG, "setRearAcLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcLockState(state);
    }

    public int getRearAcLockState() {
        Log.v(TAG, "getRearAcLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearAcLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRearAcLockState();
    }

    public int feelColdHot(int whichAC, int signal) {
        Log.v(TAG, "feelColdHot is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            Log.v(TAG, "feelColdHot mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().feelColdHot(whichAC, signal);
    }

    public int getVoiceCmdResult() {
        Log.v(TAG, "getVoiceCmdResult is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getVoiceCmdResult mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getVoiceCmdResult();
    }

    public int hasFeature(String feature) {
        Log.v(TAG, "hasFeature is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            Log.v(TAG, "hasFeature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().hasFeature(feature);
    }

    public int enablePurificationFunctionPrompt(int enable) {
        Log.v(TAG, "enablePurificationFunctionPrompt is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "enablePurificationFunctionPrompt mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().enablePurificationFunctionPrompt(enable);
    }

    public int getRearAcMaxWindLevel() {
        Log.v(TAG, "getRearAcMaxWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getRearAcMaxWindLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRearAcMaxWindLevel();
    }

    public int getAcRemoteCtrlTime() {
        Log.v(TAG, "getAcRemoteCtrlTime is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcRemoteCtrlTime mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcRemoteCtrlTime();
    }

    public int setAcRemoteCtrlTime(int value) {
        Log.v(TAG, "setAcRemoteCtrlTime is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1024, value);
            Log.v(TAG, "setAcRemoteCtrlTime mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcRemoteCtrlTime(value);
    }

    public int getAcType() {
        Log.v(TAG, "getAcType is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcType();
    }

    public int getAcPtcPreheatSignal() {
        Log.v(TAG, "getAcPtcPreheatSignal is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcPtcPreheatSignal mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcPtcPreheatSignal();
    }

    public int reset(int item, int value) {
        Log.v(TAG, "reset is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            Log.v(TAG, "reset mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().reset(item, value);
    }

    public int getAcDefrostOnlineState() {
        Log.v(TAG, "getAcDefrostOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcDefrostOnlineState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcDefrostOnlineState();
    }

    public int getAcSubBatteryTemperature() {
        Log.v(TAG, "getAcSubBatteryTemperature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcSubBatteryTemperature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcSubBatteryTemperature();
    }

    public int getAcRearPanelLockState() {
        Log.v(TAG, "getAcRearPanelLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcRearPanelLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcRearPanelLockState();
    }

    public int setAcRearPanelLockState(int value) {
        Log.v(TAG, "setAcRearPanelLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1019, value);
            Log.v(TAG, "setAcRearPanelLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcRearPanelLockState(value);
    }

    public int getAcRearPaneNotAvailableMenuState() {
        Log.v(TAG, "getAcRearPaneNotAvailableMenuState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcRearPaneNotAvailableMenuState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcRearPaneNotAvailableMenuState();
    }

    public int setQuickCleanAirState(int state) {
        Log.v(TAG, "setQuickCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1022, state);
            Log.v(TAG, "setQuickCleanAirState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setQuickCleanAirState(state);
    }

    public int getQuickCleanAirState() {
        Log.v(TAG, "getQuickCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getQuickCleanAirState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getQuickCleanAirState();
    }

    public int getQuickCleanTip() {
        Log.v(TAG, "getQuickCleanTip is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getQuickCleanTip mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getQuickCleanTip();
    }

    public int confirmCleanAirState(int state) {
        Log.v(TAG, "confirmCleanAirState is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            Log.v(TAG, "confirmCleanAirState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().confirmCleanAirState(state);
    }

    public int getAcWindModeNum() {
        Log.v(TAG, "getAcWindModeNum is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWindModeNum mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWindModeNum();
    }

    public int get3daOnlineState() {
        Log.v(TAG, "get3daOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "get3daOnlineState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().get3daOnlineState();
    }

    public int setAutoCleanAirState(int state) {
        Log.v(TAG, "setAutoCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1025, state);
            Log.v(TAG, "setAutoCleanAirState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAutoCleanAirState(state);
    }

    public int getAutoCleanAirState() {
        Log.v(TAG, "getAutoCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAutoCleanAirState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAutoCleanAirState();
    }

    public int getHighTempAntivirusState() {
        Log.v(TAG, "getHighTempAntivirusState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getHighTempAntivirusState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getHighTempAntivirusState();
    }

    public int getHighTempAntivirusCountDown() {
        Log.v(TAG, "getHighTempAntivirusCountDown is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getHighTempAntivirusCountDown mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getHighTempAntivirusCountDown();
    }

    public int getAcPanelLockPromptState() {
        Log.v(TAG, "getAcPanelLockPromptState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcPanelLockPromptState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcPanelLockPromptState();
    }

    public int qurAcAllStatus() {
        Log.v(TAG, "qurAcAllStatus is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            Log.v(TAG, "qurAcAllStatus mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().qurAcAllStatus();
    }

    public int getDefrostRearConfig() {
        Log.v(TAG, "getDefrostRearConfig is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getDefrostRearConfig mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getDefrostRearConfig();
    }

    public int setBlowPeopleState(int area, int value) {
        Log.v(TAG, "setBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setBlowPeopleStateDriver(value);
        } else if (area == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setBlowPeopleStateCopilot(value);
        }
        return 0;
    }

    public int getBlowPeopleState(int area) {
        Log.v(TAG, "getBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getBlowPeopleStateDriver();
        }
        if (area != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getBlowPeopleStateCopilot();
    }

    public int setAvoidBlowPeopleState(int area, int value) {
        Log.v(TAG, "setAvoidBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setAvoidBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setAvoidBlowPeopleStateDriver(value);
        } else if (area == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setAvoidBlowPeopleStateCopilot(value);
        }
        return 0;
    }

    public int getAvoidBlowPeopleState(int area) {
        Log.v(TAG, "getAvoidBlowPeopleState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAvoidBlowPeopleState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAvoidBlowPeopleStateDriver();
        }
        if (area != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAvoidBlowPeopleStateCopilot();
    }

    public int setSweepBlowState(int area, int value) {
        Log.v(TAG, "setSweepBlowState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setSweepBlowState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setSweepBlowStateDriver(value);
        } else if (area == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setSweepBlowStateCopilot(value);
        }
        return 0;
    }

    public int getSweepBlowState(int area) {
        Log.v(TAG, "getSweepBlowState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getSweepBlowState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getSweepBlowStateDriver();
        }
        if (area != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getSweepBlowStateCopilot();
    }

    public int setFreeWindBtnState(int area, int value) {
        Log.v(TAG, "setFreeWindBtnState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setFreeWindBtnState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setFreeWindBtnStateDriver(value);
        } else if (area == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setFreeWindBtnStateCopilot(value);
        }
        return 0;
    }

    public int getFreeWindBtnState(int area) {
        Log.v(TAG, "getFreeWindBtnState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getFreeWindBtnState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFreeWindBtnStateDriver();
        }
        if (area != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFreeWindBtnStateCopilot();
    }

    public int setAirOutletState(int area, int value) {
        Log.v(TAG, "setAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setAirOutletState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setAirOutletStateDriver(value);
        } else if (area == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setAirOutletStateCopilot(value);
        }
        return 0;
    }

    public int getAirOutletState(int area) {
        Log.v(TAG, "getAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAirOutletState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAirOutletStateDriver();
        }
        if (area != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAirOutletStateCopilot();
    }

    public int getAcWorkMode(int area) {
        Log.v(TAG, "getAcWorkMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcWorkMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeDriver();
        }
        if (area == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeCopilot();
        }
        if (area == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeMiddleLeft();
        }
        if (area == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeMiddleRight();
        }
        if (area != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeRear();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int setAirOutletValue(int area, int value) {
        Log.v(TAG, "setAirOutletValue is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setAirOutletValue mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        switch (area) {
            case 0:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverLeftVertical(value);
                return 0;
            case 1:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverLeftHorizontal(value);
                return 0;
            case 2:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverRightVertical(value);
                return 0;
            case 3:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverRightHorizontal(value);
                return 0;
            case 4:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotLeftVertical(value);
                return 0;
            case 5:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotLeftHorizontal(value);
                return 0;
            case 6:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotRightVertical(value);
                return 0;
            case 7:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotRightHorizontal(value);
                return 0;
            default:
                return 0;
        }
    }

    public float getAirOutletValue(int area) {
        Log.v(TAG, "getAirOutletValue is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAirOutletValue mForTestValue is" + this.mForTest + " execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0.0f;
        }
        switch (area) {
        }
        return 0.0f;
    }

    public int getIntelligentControlState() {
        Log.v(TAG, "getIntelligentControlState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getIntelligentControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getIntelligentControlState();
    }

    public int getFrontRowAirOutletState() {
        Log.v(TAG, "getFrontRowAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getFrontRowAirOutletState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFrontRowAirOutletState();
    }

    public int setIntelligentControlState(int state) {
        Log.v(TAG, "setIntelligentControlState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setIntelligentControlState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setIntelligentControlState(state);
    }

    public int setFrontRowAirOutletState(int state) {
        Log.v(TAG, "setFrontRowAirOutletState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setFrontRowAirOutletState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setFrontRowAirOutletState(state);
    }

    public int setConstantTemperature(int type) {
        Log.v(TAG, "setConstantTemperature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setConstantTemperature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setConstantTemperature(type);
    }

    public int getAirOutleOnlineState() {
        Log.v(TAG, "getAirOutleOnlineState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAirOutleOnlineState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mAirOutletOnline;
    }

    public void setAllStatus() {
        Log.v(TAG, "setAllStatus is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setAllStatus mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getHvacAdapterManager().setAllStatus();
        }
    }

    public void getAllStatus() {
        Log.v(TAG, "getAllStatus is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAllStatus mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getHvacAdapterManager().getAllStatus();
        }
    }

    public boolean postEvent(int deviceType, int eventType, int value, Object data) {
        Log.v(TAG, "postEvent is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        return false;
    }

    public int getType() {
        Log.v(TAG, "getType is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getType();
    }

    public int[] getFeatrueList() {
        Log.v(TAG, "getFeatrueList is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getFeatrueList mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return new int[0];
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFeatrueList();
    }

    public int getDriverSeatVentilating2e4Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverSeatVentilating2e4Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatVentilating2e4Config();
    }

    public int getDriverSeatVentilating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverSeatVentilating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2aa = this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatVentilating2aaConfig();
        int value2e4 = this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatVentilating2e4Config();
        Log.v(TAG, "getDriverSeatVentilating2aaConfig value2aa:" + value2aa + " value2e4:" + value2e4);
        if (value2aa > 0 && value2aa < 8) {
            return value2aa;
        }
        return value2e4;
    }

    public int getDriverSeatHeating2e4Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverSeatHeating2e4Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatHeating2e4Config();
    }

    public int getDriverSeatHeating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDriverSeatHeating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2aa = this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatHeating2aaConfig();
        int value2e4 = this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatHeating2e4Config();
        Log.v(TAG, "getDriverSeatHeating2aaConfig value2aa:" + value2aa + " value2e4:" + value2e4);
        if (value2aa > 0 && value2aa < 8) {
            return value2aa;
        }
        return value2e4;
    }

    public int getPassengerSeatVentilating41cConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatVentilating41cConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatVentilating41cConfig();
    }

    public int getPassengerSeatVentilating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatVentilating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2Aa = this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatVentilating2aaConfig();
        int value41c = this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatVentilating41cConfig();
        int value2Ff = this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatVentilating2ffConfig();
        Log.v(TAG, "getPassengerSeatVentilating41cConfig value2Aa:" + value2Aa + " value41c:" + value41c + " value2Ff:" + value2Ff);
        if (value41c > 0 && value41c < 8) {
            return value41c;
        }
        if (value2Aa > 0 && value2Aa < 8) {
            return value2Aa;
        }
        return value2Ff;
    }

    public int getPassengerSeatVentilating2ffConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatVentilating2ffConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatVentilating2ffConfig();
    }

    public int getPassengerSeatHeating41cConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatHeating41cConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatHeating41cConfig();
    }

    public int getPassengerSeatHeating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatHeating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2aa = this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatHeating2aaConfig();
        int value41c = this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatHeating41cConfig();
        int value2ff = this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatHeating2ffConfig();
        Log.v(TAG, "getPassengerSeatHeating41cConfig value2aa:" + value2aa + " value41c:" + value41c + " value2ff:" + value2ff);
        if (value41c > 0 && value41c < 8) {
            return value41c;
        }
        if (value2aa > 0 && value2aa < 8) {
            return value2aa;
        }
        return value2ff;
    }

    public int getPassengerSeatHeating2ffConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPassengerSeatHeating2ffConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatHeating2ffConfig();
    }

    public int getLrSeatVentilating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrSeatVentilating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2aa = this.mTsManagerImpl.getHvacAdapterManager().getLrSeatVentilating2aaConfig();
        int value35d = this.mTsManagerImpl.getHvacAdapterManager().getLrSeatVentilating35dConfig();
        int value349 = this.mTsManagerImpl.getHvacAdapterManager().getLrSeatVentilating349Config();
        Log.v(TAG, "getLrSeatVentilating2aaConfig value2aa:" + value2aa + " value35d:" + value35d + " value349:" + value349);
        if (value2aa > 0 && value2aa < 8) {
            return value2aa;
        }
        if (value35d > 0 && value35d < 8) {
            return value35d;
        }
        return value349;
    }

    public int getLrSeatVentilating35dConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrSeatVentilating35dConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getLrSeatVentilating35dConfig();
    }

    public int getLrSeatVentilating349Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrSeatVentilating349Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getLrSeatVentilating349Config();
    }

    public int getLrSeatHeating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrSeatHeating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2aa = this.mTsManagerImpl.getHvacAdapterManager().getLrSeatHeating2aaConfig();
        int value349 = this.mTsManagerImpl.getHvacAdapterManager().getLrSeatHeating349Config();
        int value35d = this.mTsManagerImpl.getHvacAdapterManager().getLrSeatHeating35dConfig();
        Log.v(TAG, "getLrSeatHeating2aaConfig value2aa:" + value2aa + " value349:" + value349 + " value35d:" + value35d);
        if (value2aa > 0 && value2aa < 8) {
            return value2aa;
        }
        if (value35d > 0 && value35d < 8) {
            return value35d;
        }
        return value349;
    }

    public int getLrSeatHeating35dConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrSeatHeating35dConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getLrSeatHeating35dConfig();
    }

    public int getLrSeatHeating349Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getLrSeatHeating349Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getLrSeatHeating349Config();
    }

    public int getRrSeatVentilating363Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatVentilating363Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating363Config();
    }

    public int getRrSeatVentilating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatVentilating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2aa = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating2aaConfig();
        int value363 = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating363Config();
        int value349 = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating349Config();
        int value21d = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating21dConfig();
        Log.v(TAG, "getRrSeatVentilating2aaConfig value2aa:" + value2aa + " value349:" + value349 + " value363:" + value363 + " value21d:" + value21d);
        if (value2aa > 0 && value2aa < 8) {
            return value2aa;
        }
        if (value363 > 0 && value363 < 8) {
            return value363;
        }
        if (value349 > 0 && value349 < 8) {
            return value349;
        }
        return value21d;
    }

    public int getRrSeatVentilating349Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatVentilating349Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating349Config();
    }

    public int getRrSeatVentilating21dConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatVentilating21dConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating21dConfig();
    }

    public int getRrSeatHeating363Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatHeating363Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating363Config();
    }

    public int getRrSeatHeating2aaConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatHeating2aaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int value2aa = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating2aaConfig();
        int value363 = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating363Config();
        int value349 = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating349Config();
        int value21d = this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating21dConfig();
        Log.v(TAG, "getRrSeatHeating2aaConfig value2aa:" + value2aa + " value349:" + value349 + " value363:" + value363 + " value21d:" + value21d);
        if (value2aa > 0 && value2aa < 8) {
            return value2aa;
        }
        if (value363 > 0 && value363 < 8) {
            return value363;
        }
        if (value349 > 0 && value349 < 8) {
            return value349;
        }
        return value21d;
    }

    public int getRrSeatHeating349Config() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatHeating349Config mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating349Config();
    }

    public int getRrSeatHeating21dConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getRrSeatHeating21dConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating21dConfig();
    }

    public int getAcWorkCommand() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getAcWorkCommand mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkCommand();
    }

    public int getAcTuyereSwitchState(int area) {
        Log.v(TAG, "getAcTuyereSwitchState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcTuyereSwitchState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateMainLeft();
        }
        if (area == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateMainRight();
        }
        if (area == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateDeputyLeft();
        }
        if (area != 3) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateDeputyRight();
    }

    public int setAcTuyereSwitchState(int area, int state) {
        Log.v(TAG, "setAcTuyereSwitchState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setAcTuyereSwitchState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateMainLeft(state);
        }
        if (area == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateMainRight(state);
        }
        if (area == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateDeputyLeft(state);
        }
        if (area != 3) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateDeputyRight(state);
    }

    public int getAcAqsConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getAcAqsConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcAqsConfig();
    }

    public int getAcCockpitPurificationStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getAcCockpitPurificationStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcCockpitPurificationStatus();
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        int i = 1;
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            int length = eventTypes.length;
            int i2 = 0;
            while (i2 < length) {
                int eventType = eventTypes[i2];
                if (eventType > 0) {
                    if (eventType == 3335) {
                        bydAutoEventValue.intValue = getDefrostRearConfig();
                        sparseArray.put(3335, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3336) {
                        bydAutoEventValue.intValue = getElectricDefrostState();
                        sparseArray.put(3336, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3349) {
                        bydAutoEventValue.intValue = getHighTempAntivirusCountDown();
                        sparseArray.put(3349, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3350) {
                        bydAutoEventValue.intValue = getHighTempAntivirusState();
                        sparseArray.put(3350, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3479) {
                        bydAutoEventValue.intValue = getRearAcMaxWindLevel();
                        sparseArray.put(3479, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3480) {
                        bydAutoEventValue.intValue = getAcDefrostRearPanelNotAvailableTipsState();
                        sparseArray.put(3480, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3553) {
                        bydAutoEventValue.intValue = getAcType();
                        sparseArray.put(3553, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3554) {
                        bydAutoEventValue.intValue = getAcVentilationState();
                        sparseArray.put(3554, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3561) {
                        bydAutoEventValue.intValue = getAcWindLevelManualSign();
                        sparseArray.put(3561, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3562) {
                        bydAutoEventValue.intValue = getAcWindModeManualSign();
                        sparseArray.put(3562, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3566) {
                        bydAutoEventValue.intValue = getAcWindMode();
                        sparseArray.put(3566, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 3567) {
                        switch (eventType) {
                            case 3313:
                                bydAutoEventValue.intValue = getHeatPumpCompressorWorkMode();
                                sparseArray.put(3313, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3316:
                                bydAutoEventValue.intValue = getAutoCleanAirState();
                                sparseArray.put(3316, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3325:
                                bydAutoEventValue.intValue = getAcControlMode();
                                sparseArray.put(3325, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3331:
                                bydAutoEventValue.intValue = getAcCycleMode();
                                sparseArray.put(3331, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3333:
                                bydAutoEventValue.intValue = getAcDefrostState(i);
                                sparseArray.put(3333, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3340:
                                bydAutoEventValue.intValue = getAcFaultNumShownState();
                                sparseArray.put(3340, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3342:
                                bydAutoEventValue.intValue = getFrontRowAirOutletState();
                                sparseArray.put(3342, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3352:
                                bydAutoEventValue.intValue = getIntelligentControlState();
                                sparseArray.put(3352, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3355:
                                bydAutoEventValue.intValue = getAcWorkMode(0);
                                sparseArray.put(3355, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3357:
                                bydAutoEventValue.intValue = getBlowPeopleState(0);
                                sparseArray.put(3357, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3359:
                                bydAutoEventValue.intValue = getFreeWindBtnState(0);
                                sparseArray.put(3359, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3361:
                                bydAutoEventValue.intValue = getSweepBlowState(0);
                                sparseArray.put(3361, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3382:
                                bydAutoEventValue.intValue = getAvoidBlowPeopleState(0);
                                sparseArray.put(3382, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3384:
                                bydAutoEventValue.intValue = getAirOutletState(0);
                                sparseArray.put(3384, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3388:
                                bydAutoEventValue.intValue = getAcMaxCoolingState();
                                sparseArray.put(3388, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3411:
                                bydAutoEventValue.intValue = getBlowPeopleState(i);
                                sparseArray.put(3411, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3413:
                                bydAutoEventValue.intValue = getFreeWindBtnState(i);
                                sparseArray.put(3413, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3415:
                                bydAutoEventValue.intValue = getSweepBlowState(i);
                                sparseArray.put(3415, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3436:
                                bydAutoEventValue.intValue = getAvoidBlowPeopleState(i);
                                sparseArray.put(3436, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3438:
                                bydAutoEventValue.intValue = getAirOutletState(i);
                                sparseArray.put(3438, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3447:
                                bydAutoEventValue.intValue = getAcStartState();
                                sparseArray.put(3447, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3450:
                                bydAutoEventValue.intValue = getAcPtcPreheatSignal();
                                sparseArray.put(3450, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3453:
                                bydAutoEventValue.intValue = getQuickCleanAirState();
                                sparseArray.put(3453, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3457:
                                bydAutoEventValue.intValue = getQuickCleanTip();
                                sparseArray.put(3457, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3462:
                                bydAutoEventValue.intValue = getRearAcControlMode();
                                sparseArray.put(3462, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3477:
                                bydAutoEventValue.intValue = getRearACkeyLockState();
                                sparseArray.put(3477, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3483:
                                bydAutoEventValue.intValue = getAcRearPanelLockState();
                                sparseArray.put(3483, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3486:
                                bydAutoEventValue.intValue = getRearAcStartState();
                                sparseArray.put(3486, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3500:
                                bydAutoEventValue.intValue = getRearAcWindLevel();
                                sparseArray.put(3500, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3503:
                                bydAutoEventValue.intValue = getRearAcWindMode();
                                sparseArray.put(3503, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3506:
                                bydAutoEventValue.intValue = getAcRemoteCtrlTime();
                                sparseArray.put(3506, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3509:
                                bydAutoEventValue.intValue = getAcWorkMode(i);
                                sparseArray.put(3509, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3524:
                                bydAutoEventValue.intValue = getAcTemperatureControlMode();
                                sparseArray.put(3524, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3526:
                                bydAutoEventValue.intValue = getTemperatureUnit();
                                sparseArray.put(3526, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3529:
                                bydAutoEventValue.intValue = getTemprature(2);
                                sparseArray.put(3529, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3532:
                                bydAutoEventValue.intValue = getTemprature(i);
                                sparseArray.put(3532, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3535:
                                bydAutoEventValue.intValue = getTemprature(3);
                                sparseArray.put(3535, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3559:
                                bydAutoEventValue.intValue = getAcWarmTypeOnlineState();
                                sparseArray.put(3559, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3564:
                                bydAutoEventValue.intValue = getAcWindLevel();
                                sparseArray.put(3564, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 4045:
                                bydAutoEventValue.intValue = getTemprature(4);
                                sparseArray.put(4045, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            default:
                                switch (eventType) {
                                    case 3320:
                                        bydAutoEventValue.intValue = getAcKeepEngineRunTipsState();
                                        sparseArray.put(3320, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 3321:
                                        bydAutoEventValue.intValue = getAcCompressorManualSign();
                                        sparseArray.put(3321, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 3322:
                                        bydAutoEventValue.intValue = getAcCompressorMode();
                                        sparseArray.put(3322, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    default:
                                        switch (eventType) {
                                            case BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG /* 4793 */:
                                                bydAutoEventValue.intValue = getDriverSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG_2E4_hal_only /* 4794 */:
                                                bydAutoEventValue.intValue = getDriverSeatVentilating2e4Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG_2E4_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG /* 4795 */:
                                                bydAutoEventValue.intValue = getDriverSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG_2E4_hal_only /* 4796 */:
                                                bydAutoEventValue.intValue = getDriverSeatHeating2e4Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG_2E4_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG /* 4797 */:
                                                bydAutoEventValue.intValue = getPassengerSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_41C_hal_only /* 4798 */:
                                                bydAutoEventValue.intValue = getPassengerSeatVentilating41cConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_41C_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_2FF_hal_only /* 4799 */:
                                                bydAutoEventValue.intValue = getPassengerSeatVentilating2ffConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_2FF_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG /* 4800 */:
                                                bydAutoEventValue.intValue = getPassengerSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_2FF_hal_only /* 4801 */:
                                                bydAutoEventValue.intValue = getPassengerSeatHeating2ffConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_2FF_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_41C_hal_only /* 4802 */:
                                                bydAutoEventValue.intValue = getPassengerSeatHeating41cConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_41C_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG /* 4803 */:
                                                bydAutoEventValue.intValue = getLrSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_349_hal_only /* 4804 */:
                                                bydAutoEventValue.intValue = getLrSeatVentilating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_349_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_35D_hal_only /* 4805 */:
                                                bydAutoEventValue.intValue = getLrSeatVentilating35dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_35D_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG /* 4806 */:
                                                bydAutoEventValue.intValue = getLrSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_349_hal_only /* 4807 */:
                                                bydAutoEventValue.intValue = getLrSeatHeating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_349_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_35D_hal_only /* 4808 */:
                                                bydAutoEventValue.intValue = getLrSeatHeating35dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_35D_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG /* 4809 */:
                                                bydAutoEventValue.intValue = getRrSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_349_hal_only /* 4810 */:
                                                bydAutoEventValue.intValue = getRrSeatVentilating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_349_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_363_hal_only /* 4811 */:
                                                bydAutoEventValue.intValue = getRrSeatVentilating363Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_363_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_21D_hal_only /* 4812 */:
                                                bydAutoEventValue.intValue = getRrSeatVentilating21dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_21D_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG /* 4813 */:
                                                bydAutoEventValue.intValue = getRrSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_349_hal_only /* 4814 */:
                                                bydAutoEventValue.intValue = getRrSeatHeating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_349_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_363_hal_only /* 4815 */:
                                                bydAutoEventValue.intValue = getRrSeatHeating363Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_363_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_21D_hal_only /* 4816 */:
                                                bydAutoEventValue.intValue = getRrSeatHeating21dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_21D_hal_only, Integer.valueOf(bydAutoEventValue.intValue));
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                    } else {
                        bydAutoEventValue.intValue = getAcWindModeNum();
                        sparseArray.put(3567, Integer.valueOf(bydAutoEventValue.intValue));
                    }
                    int index = 0;
                    for (int i3 : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i3)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
                i2++;
                i = 1;
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoAcListener listener : this.mAbsBydAutoAcListeners) {
            listener.onDataEventChanged(id, value);
        }
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
            case 369:
                setAcAntibacterialControlState(intValue);
                return 1;
            case 3317:
                setAutoCleanAirState(intValue);
                return 1;
            case 3323:
                setAcCompressorMode(0, intValue);
                return 1;
            case 3332:
                setAcCycleMode(0, intValue);
                return 1;
            case 3334:
                setAcDefrostState(0, 1, intValue);
                return 1;
            case 3337:
                setElectricDefrostState(intValue);
                return 1;
            case 3364:
                setAirOutletValue(1, intValue);
                return 1;
            case 3366:
                setAirOutletValue(0, intValue);
                return 1;
            case 3367:
                setAirOutletValue(3, intValue);
                return 1;
            case 3370:
                setAirOutletValue(0, intValue);
                return 1;
            case 3389:
                setAcMaxCoolingState(intValue);
                return 1;
            case 3418:
                setAirOutletValue(5, intValue);
                return 1;
            case 3420:
                setAirOutletValue(4, intValue);
                return 1;
            case 3422:
                setAirOutletValue(7, intValue);
                return 1;
            case 3424:
                setAirOutletValue(6, intValue);
                return 1;
            case 3448:
                if (intValue != 1) {
                    if (intValue == 2) {
                        stop(0);
                    }
                } else {
                    start(0);
                }
                return 1;
            case 3452:
                confirmCleanAirState(intValue);
                return 1;
            case 3456:
                setQuickCleanAirState(intValue);
                return 1;
            case 3463:
                setRearAcControlMode(0, intValue);
                return 1;
            case 3478:
                setRearAcKeyLockState(intValue);
                return 1;
            case 3485:
                setAcRearPanelLockState(intValue);
                return 1;
            case 3487:
                startRearAc(intValue);
                return 1;
            case 3501:
                setRearAcWindLevel(0, intValue);
                return 1;
            case 3504:
                setRearAcWindMode(0, intValue);
                return 1;
            case 3507:
                setAcRemoteCtrlTime(intValue);
                return 1;
            case 3525:
                setAcTemperatureControlMode(0, intValue);
                return 1;
            case 3530:
                setAcTemperature(2, intValue, 0, 0);
                return 1;
            case 3533:
                setAcTemperature(1, intValue, 0, 0);
                return 1;
            case 3536:
                setAcTemperature(3, intValue, 0, 0);
                return 1;
            case 3555:
                setAcVentilationState(0, intValue);
                return 1;
            case 3565:
                setAcWindLevel(0, intValue);
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }
}
