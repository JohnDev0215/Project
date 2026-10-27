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
import java.util.Iterator;
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

    ACManagerImpl(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.ac.tsimpl.ACManagerImpl.1
            public void onHvacChanged(int i, int i2, HvacPacket hvacPacket) {
                if (ACManagerImpl.this.mAbsBydAutoAcListeners.isEmpty()) {
                    return;
                }
                try {
                    ACManagerImpl.this.hvacCallback(i, i2, hvacPacket);
                } catch (Exception unused) {
                    Log.e(ACManagerImpl.TAG, "hvacCallback is error");
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

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
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
        return acCompressorManualSign == 1 ? 1 : -1;
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
        return heatPumpCompressorWorkMode == 3 ? 3 : -1;
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
        return acCompressorMode == 1 ? 1 : -1;
    }

    public int getAcMaxCoolingState() {
        Log.v(TAG, "getAcMaxCoolingState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcMaxCoolingState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        int acMaxCoolingState = this.mTsManagerImpl.getHvacAdapterManager().getAcMaxCoolingState();
        if (acMaxCoolingState == 2) {
            return 0;
        }
        if (acMaxCoolingState == 0) {
            return -1;
        }
        return acMaxCoolingState;
    }

    public int getAcDefrostState(int i) {
        Log.v(TAG, "getAcDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getAcDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        Log.v(TAG, "getAcDefrostState area  is: " + i);
        if (i != 1) {
            if (i != 2) {
                return 0;
            }
            int electricDefrostState = getElectricDefrostState();
            Log.v(TAG, "getAcDefrostState value is: " + electricDefrostState);
            return electricDefrostState;
        }
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
        return acWindLevelManualSign == 1 ? 1 : -1;
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

    public int getTemprature(int i) {
        Log.v(TAG, "getTemprature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_GET");
        if (this.mForTest == 0) {
            Log.v(TAG, "getTemprature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureMain();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureDeputy();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().getTempratureRear();
        }
        if (i == 4) {
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

    public int setAcCycleMode(int i, int i2) {
        Log.v(TAG, "setAcCycleMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1002, i2);
            Log.v(TAG, "setAcCycleMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        }
        Log.v(TAG, "setAcCycleMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCycleMode(i, i2);
    }

    public int setAcControlMode(int i, int i2) {
        Log.v(TAG, "setAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1009, i2);
            Log.v(TAG, "setAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        }
        Log.v(TAG, "setAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcControlMode(i, i2);
    }

    public int setAcTemperature(int i, int i2, int i3, int i4) {
        Log.v(TAG, "setAcTemperature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1012, i, i2);
            Log.v(TAG, "setAcTemperature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureMain(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureDeputy(i2);
        }
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureMainDeputy(i2);
        }
        if (i == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureRear(i2);
        }
        return 0;
    }

    public int setAcTemperatureControlMode(int i, int i2) {
        Log.v(TAG, "setAcTemperatureControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1010, i2);
            Log.v(TAG, "setAcTemperatureControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 1;
        } else if (i2 == 1) {
            i2 = 2;
        }
        Log.v(TAG, "setAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcTemperatureControlMode(i, i2);
    }

    public int setAcVentilationState(int i, int i2) {
        Log.v(TAG, "setAcVentilationState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1000, i2);
            Log.v(TAG, "setAcVentilationState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        } else if (i2 == 1) {
            i2 = 1;
        }
        Log.v(TAG, "setAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcVentilationState(i, i2);
    }

    public int start(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().start(i);
    }

    public int startRearAc(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().startRearAc(i);
    }

    public int stop(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().stop(i);
    }

    public int stopRearAc(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().stopRearAc(i);
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

    public int setAcHeatingState(int i) {
        if (this.mForTest == 0) {
            Log.v(TAG, " setAcHeatingState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcHeatingState(i);
    }

    public int getTempRegulatingAccuracy() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getTempRegulatingAccuracy mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getTempRegulatingAccuracy();
    }

    public void registerListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        registerListener(absBYDAutoAcListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoAcListener absBYDAutoAcListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe ac listener " + absBYDAutoAcListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoAcListener == null) {
            return;
        }
        if (this.mForTest == 0) {
            Log.v(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBydAutoAcListener(absBYDAutoAcListener);
            return;
        }
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (!this.mAbsBydAutoAcListeners.contains(absBYDAutoAcListener)) {
            this.mAbsBydAutoAcListeners.add(absBYDAutoAcListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoAcListener);
        TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
    }

    public void unregisterListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        Log.v(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoAcDevice.PERMISSION_BYDAUTO_AC_COMMON);
        if (this.mForTest == 0) {
            this.mTestCallback.removeListener(absBYDAutoAcListener);
            Log.v(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.v(TAG, "unsubscribeHvacCallback: " + absBYDAutoAcListener);
        if (absBYDAutoAcListener == null) {
            return;
        }
        this.mAbsBydAutoAcListeners.remove(absBYDAutoAcListener);
        unregisterConnectListener(absBYDAutoAcListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int i, int i2, HvacPacket hvacPacket) {
        int i3;
        if (hvacPacket == null) {
            Log.e(TAG, "hvacCallback is null");
        }
        Log.v(TAG, "hvacCallback propertyId:" + i + "hvacPacket" + hvacPacket.toString());
        switch (i) {
            case 557846528:
                int temperatureUnit = hvacPacket.getTemperatureUnit();
                Log.v(TAG, "getTemperatureUnit value is: " + temperatureUnit);
                i3 = temperatureUnit == 0 ? 1 : 0;
                Iterator<AbsBYDAutoAcListener> it = this.mAbsBydAutoAcListeners.iterator();
                while (it.hasNext()) {
                    it.next().onTemperatureUnitChanged(i3);
                }
                break;
            case HvacHalId.ID_AC_REAR_WIND_LEVEL /* 557846532 */:
                Iterator<AbsBYDAutoAcListener> it2 = this.mAbsBydAutoAcListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onRearAcMaxWindLevelChanged(hvacPacket.getAcRearAcWindLevel());
                }
                break;
            case HvacHalId.ID_AC_FAULT_NUM_SHOWN_STATE /* 557846542 */:
                Iterator<AbsBYDAutoAcListener> it3 = this.mAbsBydAutoAcListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onAcFaultNumShownStateChanged(hvacPacket.getAcFaultNumShownState());
                }
                break;
            case HvacHalId.ID_AC_TEMPCTRL_SEPARATE_STATE /* 557846543 */:
                int acTempCtrlSeparateState = hvacPacket.getAcTempCtrlSeparateState();
                Log.v(TAG, "ID_AC_TEMPCTRL_SEPARATE_STATE value is: " + acTempCtrlSeparateState);
                i = acTempCtrlSeparateState != 0 ? acTempCtrlSeparateState : -1;
                i3 = i != 1 ? i : 0;
                int i4 = i3 != 2 ? i3 : 1;
                Iterator<AbsBYDAutoAcListener> it4 = this.mAbsBydAutoAcListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onAcTemperatureControlModeChanged(i4);
                }
                break;
            case HvacHalId.ID_AC_REAR_CTRL_MODE /* 557846547 */:
                int acCtrlMode = hvacPacket.getAcCtrlMode();
                Log.v(TAG, "ID_AC_REAR_CTRL_MODE value is: " + acCtrlMode);
                if (1 == acCtrlMode) {
                    i = 1;
                } else if (2 == acCtrlMode) {
                    i = 0;
                } else if (acCtrlMode != 0) {
                    i = acCtrlMode;
                }
                Iterator<AbsBYDAutoAcListener> it5 = this.mAbsBydAutoAcListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onAcCtrlModeChanged(i);
                }
                break;
            case HvacHalId.ID_AC_DEFROST /* 557846549 */:
                int acDefrost = hvacPacket.getAcDefrost();
                Log.v(TAG, "getAcDefrostState value is: " + acDefrost);
                if (acDefrost == 1) {
                    i = 0;
                } else if (acDefrost == 2) {
                    i = 1;
                } else if (acDefrost != 0) {
                    i = acDefrost;
                }
                Iterator<AbsBYDAutoAcListener> it6 = this.mAbsBydAutoAcListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onAcDefrostStateChanged(1, i);
                }
                break;
            case HvacHalId.ID_AC_OPEN_STATE /* 557846552 */:
                int acOpenState = hvacPacket.getAcOpenState();
                for (AbsBYDAutoAcListener absBYDAutoAcListener : this.mAbsBydAutoAcListeners) {
                    if (acOpenState == 1) {
                        absBYDAutoAcListener.onAcStarted();
                    } else if (acOpenState == 2) {
                        absBYDAutoAcListener.onAcStoped();
                    }
                }
                break;
            case HvacHalId.ID_AC_CIRCLE_MODE /* 557846553 */:
                int acCircleMode = hvacPacket.getAcCircleMode();
                Log.v(TAG, "getAcCycleMode value is: " + acCircleMode);
                if (1 == acCircleMode) {
                    i = 0;
                } else if (2 == acCircleMode) {
                    i = 1;
                } else if (acCircleMode != 0) {
                    i = acCircleMode;
                }
                Iterator<AbsBYDAutoAcListener> it7 = this.mAbsBydAutoAcListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onAcCycleModeChanged(i);
                }
                break;
            case HvacHalId.ID_AC_HEAT_MODE_FLAG /* 557846557 */:
                Iterator<AbsBYDAutoAcListener> it8 = this.mAbsBydAutoAcListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onAcWarmTypeOnlineStateChanged(hvacPacket.getAcHeatModeFlag());
                }
                break;
            case HvacHalId.ID_AC_HEAT_PUMP_COMPRESSOR_MODE /* 557846558 */:
                int acHeatPumpCompressorMode = hvacPacket.getAcHeatPumpCompressorMode();
                Log.v(TAG, "ID_AC_HEAT_PUMP_COMPRESSOR_MODE value is:" + acHeatPumpCompressorMode);
                Iterator<AbsBYDAutoAcListener> it9 = this.mAbsBydAutoAcListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onHeatPumpCompressorWorkModeChanged(acHeatPumpCompressorMode);
                }
                break;
            case HvacHalId.ID_AC_NATURAL_WIND_STATE /* 557846559 */:
                int acNaturalWind = hvacPacket.getAcNaturalWind();
                Log.v(TAG, "ID_AC_NATURAL_WIND_STATE value is " + acNaturalWind);
                if (acNaturalWind == 1) {
                    i = 1;
                } else if (acNaturalWind == 2) {
                    i = 0;
                } else if (acNaturalWind != 0) {
                    i = acNaturalWind;
                }
                Iterator<AbsBYDAutoAcListener> it10 = this.mAbsBydAutoAcListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onAcVentilationStateChanged(i);
                }
                break;
            case HvacHalId.ID_AC_COMPRESSOR_MODE /* 557846561 */:
                int acRequestRight = hvacPacket.getAcRequestRight();
                Log.v(TAG, "ID_AC_COMPRESSOR_MODE value is:" + acRequestRight);
                i3 = acRequestRight != 0 ? acRequestRight == 1 ? 1 : acRequestRight : 0;
                Iterator<AbsBYDAutoAcListener> it11 = this.mAbsBydAutoAcListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onAcCompressorModeChanged(i3);
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_DRIVER_TEMP_SET /* 557846563 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener2 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_MAIN is: " + hvacPacket.getAcControllerDriverTemp());
                    absBYDAutoAcListener2.onTemperatureChanged(1, hvacPacket.getAcControllerDriverTemp());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_WIND_LEVEL /* 557846567 */:
                Iterator<AbsBYDAutoAcListener> it12 = this.mAbsBydAutoAcListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onAcWindLevelChanged(hvacPacket.getAcFrontWindLevelR());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_WIND_MODE /* 557846569 */:
                Iterator<AbsBYDAutoAcListener> it13 = this.mAbsBydAutoAcListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onAcWindModeChanged(hvacPacket.getAcWindMode());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_MAX_COLD_STATE /* 557846571 */:
                int acMaxCold = hvacPacket.getAcMaxCold();
                Log.v(TAG, "ID_AC_CONTROLLER_MAX_COLD_STATE value is: " + acMaxCold);
                i = acMaxCold != 0 ? acMaxCold == 2 ? 0 : acMaxCold : -1;
                Iterator<AbsBYDAutoAcListener> it14 = this.mAbsBydAutoAcListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onAcMaxCoolingStateChanged(i);
                }
                caseFeatureIds(3388, Integer.valueOf(i));
                break;
            case HvacHalId.ID_AC_REAR_PANEL_LOCK /* 557846576 */:
                Iterator<AbsBYDAutoAcListener> it15 = this.mAbsBydAutoAcListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onAcRearPanelLockStateChanged(hvacPacket.getAcRearPanelLock());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_AC_CTRL_MODE /* 557846578 */:
                int acRearAcCtrlMode = hvacPacket.getAcRearAcCtrlMode();
                Log.v(TAG, "ID_AC_CONTROLLER_REAR_AC_CTRL_MODE value is: " + acRearAcCtrlMode);
                if (2 == acRearAcCtrlMode) {
                    i = 0;
                } else if (acRearAcCtrlMode != 0) {
                    i = acRearAcCtrlMode;
                }
                Iterator<AbsBYDAutoAcListener> it16 = this.mAbsBydAutoAcListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onRearAcCtrlModeChanged(i);
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_STATE /* 557846580 */:
                int acRearAcState = hvacPacket.getAcRearAcState();
                for (AbsBYDAutoAcListener absBYDAutoAcListener3 : this.mAbsBydAutoAcListeners) {
                    if (acRearAcState == 1) {
                        absBYDAutoAcListener3.onAcRearStarted();
                    } else if (acRearAcState == 2) {
                        absBYDAutoAcListener3.onAcRearStoped();
                    }
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_WIND_MODE /* 557846582 */:
                Iterator<AbsBYDAutoAcListener> it17 = this.mAbsBydAutoAcListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onRearAcWindModeChanged(hvacPacket.getAcRearWindMode());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_TEMP /* 557846584 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener4 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_REAR is: " + hvacPacket.getAcRearTemp());
                    absBYDAutoAcListener4.onTemperatureChanged(3, hvacPacket.getAcRearTemp());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_WIND_LEVEL /* 557846586 */:
                Iterator<AbsBYDAutoAcListener> it18 = this.mAbsBydAutoAcListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onRearAcWindLevelChanged(hvacPacket.getAcRearWindLevel());
                }
                break;
            case HvacHalId.ID_AC_CONTROLLER_REAR_LOCK /* 557846588 */:
                Iterator<AbsBYDAutoAcListener> it19 = this.mAbsBydAutoAcListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onRearAcKeyLockStateChanged(hvacPacket.getAcRearLock());
                }
                caseFeatureIds(3477, Integer.valueOf(hvacPacket.getAcRearLock()));
                break;
            case HvacHalId.ID_AC_COMPRESSOR_MANUAL_SIGN /* 557846590 */:
                int acCompressorManualSign = hvacPacket.getAcCompressorManualSign();
                Log.v(TAG, "ID_AC_COMPRESSOR_MANUAL_SIGN value is: " + acCompressorManualSign);
                if (acCompressorManualSign == 0) {
                    i = 0;
                } else if (acCompressorManualSign == 1) {
                    i = 1;
                }
                Iterator<AbsBYDAutoAcListener> it20 = this.mAbsBydAutoAcListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onAcCompressorManualSignChanged(i);
                }
                break;
            case HvacHalId.ID_AC_WIND_LEVEL_MANUAL_SIGN /* 557846591 */:
                int acWindLevelManualSign = hvacPacket.getAcWindLevelManualSign();
                Log.v(TAG, "ID_AC_WIND_LEVEL_MANUAL_SIGN value is: " + acWindLevelManualSign);
                i3 = acWindLevelManualSign != 0 ? acWindLevelManualSign == 1 ? 1 : acWindLevelManualSign : 0;
                Iterator<AbsBYDAutoAcListener> it21 = this.mAbsBydAutoAcListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onAcWindLevelManualSignChanged(i3);
                }
                break;
            case HvacHalId.ID_AC_WIND_MODE_MANUAL_SIGN /* 557846592 */:
                Iterator<AbsBYDAutoAcListener> it22 = this.mAbsBydAutoAcListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onAcWindModeManualSignChanged(hvacPacket.getAcWindModeManualSign());
                }
                break;
            case HvacHalId.ID_HIGH_TEMP_ANTIVIRUS_COUNT_DOWN /* 557846596 */:
                Iterator<AbsBYDAutoAcListener> it23 = this.mAbsBydAutoAcListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onHighTempAntivirusCountDownChanged(hvacPacket.getHighTempAntivirusCountDown());
                }
                break;
            case HvacHalId.ID_HIGHTEMPANTIVIRUSSTATE /* 557846597 */:
                Iterator<AbsBYDAutoAcListener> it24 = this.mAbsBydAutoAcListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onHighTempAntivirusStateChanged(hvacPacket.getHighTempAntivirusState());
                }
                break;
            case HvacHalId.ID_AC_PTC_PREHEAT_SIGNALR /* 557846601 */:
                Iterator<AbsBYDAutoAcListener> it25 = this.mAbsBydAutoAcListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onAcPtcPreheatSignalChanged(hvacPacket.getAcPtcPreheatSignal());
                }
                break;
            case HvacHalId.ID_AC_TEMP_DEPUTY /* 557846603 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener5 : this.mAbsBydAutoAcListeners) {
                    Log.e(TAG, this.mContext.getPackageName() + "AC_TEMPERATURE_DEPUTY is: " + hvacPacket.getAcTempratureDeputy());
                    absBYDAutoAcListener5.onTemperatureChanged(2, hvacPacket.getAcTempratureDeputy());
                }
                break;
            case HvacHalId.ID_AC_QUICK_CLEAN_AIR_STATER /* 557846606 */:
                Iterator<AbsBYDAutoAcListener> it26 = this.mAbsBydAutoAcListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onQuickCleanAirStateChanged(hvacPacket.getQuickCleanAirStateR());
                }
                break;
            case HvacHalId.ID_QUICK_CLEAN_TIPR /* 557846607 */:
                Iterator<AbsBYDAutoAcListener> it27 = this.mAbsBydAutoAcListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onQuickCleanTipChanged(hvacPacket.getQuickCleanTipR());
                }
                break;
            case 557846608:
                Iterator<AbsBYDAutoAcListener> it28 = this.mAbsBydAutoAcListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onAutoCleanAirStateChanged(hvacPacket.getAutoCleanAirStateR());
                }
                caseFeatureIds(3316, Integer.valueOf(hvacPacket.getAutoCleanAirStateR()));
                break;
            case HvacHalId.ID_AC_ASSIST_MODE_TIPS /* 557846609 */:
                Iterator<AbsBYDAutoAcListener> it29 = this.mAbsBydAutoAcListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onACAssistModeTips(hvacPacket.getAcAssistModeTips());
                }
                break;
            case HvacHalId.ID_ACBT_WIND_TIPS /* 557846611 */:
                Iterator<AbsBYDAutoAcListener> it30 = this.mAbsBydAutoAcListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onACBTWindTips(hvacPacket.getAcbtWindTips());
                }
                break;
            case 557846612:
                Iterator<AbsBYDAutoAcListener> it31 = this.mAbsBydAutoAcListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onACDefrostRearPanelNotAvailableTips(hvacPacket.getAcDefrosRearPanelNotAvailableTips());
                }
                break;
            case HvacHalId.ID_AC_DEFROST_OPEN_STATE_LEFT /* 557846613 */:
                Iterator<AbsBYDAutoAcListener> it32 = this.mAbsBydAutoAcListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onAcDefrostStateChanged(2, hvacPacket.getAcDefrostOpenStateLeft());
                }
                break;
            case HvacHalId.ID_FREE_WIND_BTN_STATE_DRIVER_R /* 557846622 */:
                Iterator<AbsBYDAutoAcListener> it33 = this.mAbsBydAutoAcListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onFreeWindBTNStateChanged(0, hvacPacket.getFreeWindBtnStateDriverR());
                }
                caseFeatureIds(3359, Integer.valueOf(hvacPacket.getFreeWindBtnStateDriverR()));
                break;
            case HvacHalId.ID_FREE_WIND_BTN_STATE_COPILOT_R /* 557846623 */:
                Iterator<AbsBYDAutoAcListener> it34 = this.mAbsBydAutoAcListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onFreeWindBTNStateChanged(1, hvacPacket.getFreeWindBtnStateCopilotR());
                }
                caseFeatureIds(3413, Integer.valueOf(hvacPacket.getFreeWindBtnStateCopilotR()));
                break;
            case HvacHalId.ID_BLOW_PEOPLE_STATE_DRIVER_R /* 557846624 */:
                Iterator<AbsBYDAutoAcListener> it35 = this.mAbsBydAutoAcListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onBlowPeopleState(0, hvacPacket.getBlowPeopleStateDriverR());
                }
                caseFeatureIds(3357, Integer.valueOf(hvacPacket.getBlowPeopleStateDriverR()));
                break;
            case HvacHalId.ID_BLOW_PEOPLE_STATE_COPILOT_R /* 557846625 */:
                Iterator<AbsBYDAutoAcListener> it36 = this.mAbsBydAutoAcListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onBlowPeopleState(1, hvacPacket.getBlowPeopleStateCopilotR());
                }
                caseFeatureIds(3411, Integer.valueOf(hvacPacket.getBlowPeopleStateCopilotR()));
                break;
            case HvacHalId.ID_AVOID_BLOW_PEOPLE_STATE_DRIVER_R /* 557846626 */:
                Iterator<AbsBYDAutoAcListener> it37 = this.mAbsBydAutoAcListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onAvoidBlowPeopleStateChanged(0, hvacPacket.getAvoidBlowPeopleStateDriverR());
                }
                caseFeatureIds(3382, Integer.valueOf(hvacPacket.getAvoidBlowPeopleStateDriverR()));
                break;
            case HvacHalId.ID_AVOID_BLOW_PEOPLE_STATE_COPILOT_R /* 557846627 */:
                Iterator<AbsBYDAutoAcListener> it38 = this.mAbsBydAutoAcListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onAvoidBlowPeopleStateChanged(1, hvacPacket.getAvoidBlowPeopleStateCopilotR());
                }
                caseFeatureIds(3436, Integer.valueOf(hvacPacket.getAvoidBlowPeopleStateCopilotR()));
                break;
            case HvacHalId.ID_SWEEP_BLOW_STATE_DRIVER_R /* 557846628 */:
                Iterator<AbsBYDAutoAcListener> it39 = this.mAbsBydAutoAcListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onSweepBlowStateChanged(0, hvacPacket.getSweepBlowStateDriverR());
                }
                caseFeatureIds(3361, Integer.valueOf(hvacPacket.getSweepBlowStateDriverR()));
                break;
            case HvacHalId.ID_SWEEP_BLOW_STATE_COPILOT_R /* 557846629 */:
                Iterator<AbsBYDAutoAcListener> it40 = this.mAbsBydAutoAcListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onSweepBlowStateChanged(1, hvacPacket.getSweepBlowStateCopilotR());
                }
                caseFeatureIds(3415, Integer.valueOf(hvacPacket.getSweepBlowStateCopilotR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_STATE_DRIVER_R /* 557846630 */:
                Iterator<AbsBYDAutoAcListener> it41 = this.mAbsBydAutoAcListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onAirOutletStateChanged(0, hvacPacket.getAirOutletStateDriverR());
                }
                caseFeatureIds(3384, Integer.valueOf(hvacPacket.getAirOutletStateDriverR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_STATE_COPILOT_R /* 557846631 */:
                Iterator<AbsBYDAutoAcListener> it42 = this.mAbsBydAutoAcListeners.iterator();
                while (it42.hasNext()) {
                    it42.next().onAirOutletStateChanged(1, hvacPacket.getAirOutletStateCopilotR());
                }
                caseFeatureIds(3438, Integer.valueOf(hvacPacket.getAirOutletStateCopilotR()));
                break;
            case HvacHalId.ID_INTELLIGENT_CONTROL_STATE_R /* 557846632 */:
                Iterator<AbsBYDAutoAcListener> it43 = this.mAbsBydAutoAcListeners.iterator();
                while (it43.hasNext()) {
                    it43.next().onIntelligentControlStateChanged(hvacPacket.getIntelligentControlStateR());
                }
                caseFeatureIds(3352, Integer.valueOf(hvacPacket.getIntelligentControlStateR()));
                break;
            case HvacHalId.ID_FRONT_ROW_AIR_OUTLET_STATE_R /* 557846633 */:
                Iterator<AbsBYDAutoAcListener> it44 = this.mAbsBydAutoAcListeners.iterator();
                while (it44.hasNext()) {
                    it44.next().onFrontRowAirOutletStateChanged(hvacPacket.getFrontRowAirOutletStateR());
                }
                caseFeatureIds(3342, Integer.valueOf(hvacPacket.getFrontRowAirOutletStateR()));
                break;
            case HvacHalId.ID_AC_WORK_MODE_DRIVER_R /* 557846634 */:
                Iterator<AbsBYDAutoAcListener> it45 = this.mAbsBydAutoAcListeners.iterator();
                while (it45.hasNext()) {
                    it45.next().onACWorkModeChanged(0, hvacPacket.getAcWorkModeDriverR());
                }
                caseFeatureIds(3355, Integer.valueOf(hvacPacket.getAcWorkModeDriverR()));
                break;
            case HvacHalId.ID_AC_WORK_MODE_COPILOT_R /* 557846635 */:
                Iterator<AbsBYDAutoAcListener> it46 = this.mAbsBydAutoAcListeners.iterator();
                while (it46.hasNext()) {
                    it46.next().onACWorkModeChanged(1, hvacPacket.getAcWorkModeCopilotR());
                }
                caseFeatureIds(3509, Integer.valueOf(hvacPacket.getAcWorkModeCopilotR()));
                break;
            case HvacHalId.ID_AC_PROMPT_BOX_SHOWN_STATE_R /* 557846657 */:
                Iterator<AbsBYDAutoAcListener> it47 = this.mAbsBydAutoAcListeners.iterator();
                while (it47.hasNext()) {
                    it47.next().onAcPromptBoxShownStateChanged(hvacPacket.getAcPromptBoxShownStateR());
                }
                break;
            case HvacHalId.ID_AC_KEEP_ENGINE_RUN_TIPS /* 557846658 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener6 : this.mAbsBydAutoAcListeners) {
                    absBYDAutoAcListener6.onACKeepEngineRunTips(hvacPacket.getAcKeepEngineRunTIp());
                    absBYDAutoAcListener6.onACMaxEVModeTips(hvacPacket.getAcKeepEngineRunTIp());
                }
                break;
            case HvacHalId.ID_AC_HEATING_STATE_R /* 557846665 */:
                Iterator<AbsBYDAutoAcListener> it48 = this.mAbsBydAutoAcListeners.iterator();
                while (it48.hasNext()) {
                    it48.next().onAcHeatingStateChanged(hvacPacket.getAcHeatingStateR());
                }
                break;
            case HvacHalId.ID_A_C_WORK_MODE_R /* 557846692 */:
                Iterator<AbsBYDAutoAcListener> it49 = this.mAbsBydAutoAcListeners.iterator();
                while (it49.hasNext()) {
                    it49.next().onACWorkCommandChanged(hvacPacket.getAcWorkModeR());
                }
                break;
            case HvacHalId.ID_A_C_WORK_MODE_MID_LEFT_R /* 557846693 */:
                Iterator<AbsBYDAutoAcListener> it50 = this.mAbsBydAutoAcListeners.iterator();
                while (it50.hasNext()) {
                    it50.next().onACWorkModeChanged(2, hvacPacket.getAcWorkModeMidLeftR());
                }
                break;
            case HvacHalId.ID_A_C_WORK_MODE_MID_RIGHT_R /* 557846694 */:
                Iterator<AbsBYDAutoAcListener> it51 = this.mAbsBydAutoAcListeners.iterator();
                while (it51.hasNext()) {
                    it51.next().onACWorkModeChanged(3, hvacPacket.getAcWorkModeMidRightR());
                }
                break;
            case HvacHalId.ID_A_C_WORK_MODE_REAR_R /* 557846695 */:
                Iterator<AbsBYDAutoAcListener> it52 = this.mAbsBydAutoAcListeners.iterator();
                while (it52.hasNext()) {
                    it52.next().onACWorkModeChanged(4, hvacPacket.getAcWorkModeRearR());
                }
                break;
            case HvacHalId.ID_AC_TUYERE_SWITCH_STATE_MAIN_LEFT_R /* 557846696 */:
                Iterator<AbsBYDAutoAcListener> it53 = this.mAbsBydAutoAcListeners.iterator();
                while (it53.hasNext()) {
                    it53.next().onAcTuyereSwitchStateChanged(0, hvacPacket.getAcTuyereSwitchStateMainLeftR());
                }
                break;
            case HvacHalId.ID_AC_TUYERE_SWITCH_STATE_MAIN_RIGHT_R /* 557846697 */:
                Iterator<AbsBYDAutoAcListener> it54 = this.mAbsBydAutoAcListeners.iterator();
                while (it54.hasNext()) {
                    it54.next().onAcTuyereSwitchStateChanged(1, hvacPacket.getAcTuyereSwitchStateMainRightR());
                }
                break;
            case HvacHalId.ID_AC_TUYERE_SWITCH_STATE_COPILOT_LEFT_R /* 557846698 */:
                Iterator<AbsBYDAutoAcListener> it55 = this.mAbsBydAutoAcListeners.iterator();
                while (it55.hasNext()) {
                    it55.next().onAcTuyereSwitchStateChanged(2, hvacPacket.getAcTuyereSwitchStateCopilotLeftR());
                }
                break;
            case HvacHalId.ID_AC_TUYERE_SWITCH_STATE_COPILOT_RIGHT_R /* 557846699 */:
                Iterator<AbsBYDAutoAcListener> it56 = this.mAbsBydAutoAcListeners.iterator();
                while (it56.hasNext()) {
                    it56.next().onAcTuyereSwitchStateChanged(3, hvacPacket.getAcTuyereSwitchStateCopilotRightR());
                }
                break;
            case HvacHalId.ID_AC_COCKPIT_PURIFICATION_STATUS_R /* 557846705 */:
                Iterator<AbsBYDAutoAcListener> it57 = this.mAbsBydAutoAcListeners.iterator();
                while (it57.hasNext()) {
                    it57.next().onAcCockpitPurificationStatusChanged(hvacPacket.getAcCockpitPurificationStatusR());
                }
                break;
            case 557856063:
                Iterator<AbsBYDAutoAcListener> it58 = this.mAbsBydAutoAcListeners.iterator();
                while (it58.hasNext()) {
                    it58.next().onAcRemoteCtrlTimeChanged(hvacPacket.getAcRemoteCtrlTimeR());
                }
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_LEFT_VERTICAL_R /* 559943766 */:
                for (AbsBYDAutoAcListener absBYDAutoAcListener7 : this.mAbsBydAutoAcListeners) {
                    if (i2 == 2) {
                        this.mAirOutletOnline = 0;
                    } else {
                        this.mAirOutletOnline = 1;
                        absBYDAutoAcListener7.onAirOutletValueChanged(0, hvacPacket.getAirOutletValueDriverLeftVerticalR());
                    }
                }
                caseFeatureIds(3365, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverLeftHorizontalR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_LEFT_HORIZONTAL_R /* 559943767 */:
                Iterator<AbsBYDAutoAcListener> it59 = this.mAbsBydAutoAcListeners.iterator();
                while (it59.hasNext()) {
                    it59.next().onAirOutletValueChanged(1, hvacPacket.getAirOutletValueDriverLeftHorizontalR());
                }
                caseFeatureIds(3363, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverLeftHorizontalR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_VERTICAL_R /* 559943768 */:
                Iterator<AbsBYDAutoAcListener> it60 = this.mAbsBydAutoAcListeners.iterator();
                while (it60.hasNext()) {
                    it60.next().onAirOutletValueChanged(2, hvacPacket.getAirOutletValueDriverRightVerticalR());
                }
                caseFeatureIds(3369, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverRightVerticalR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_HORIZONTAL_R /* 559943769 */:
                Iterator<AbsBYDAutoAcListener> it61 = this.mAbsBydAutoAcListeners.iterator();
                while (it61.hasNext()) {
                    it61.next().onAirOutletValueChanged(3, hvacPacket.getAirOutletValueDriverRightHorizontalR());
                }
                caseFeatureIds(3368, Integer.valueOf((int) hvacPacket.getAirOutletValueDriverRightHorizontalR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_LEFT_VERTICAL_R /* 559943770 */:
                Iterator<AbsBYDAutoAcListener> it62 = this.mAbsBydAutoAcListeners.iterator();
                while (it62.hasNext()) {
                    it62.next().onAirOutletValueChanged(4, hvacPacket.getAirOutletValueCopilotLeftVerticalR());
                }
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_LEFT_HORIZONTAL_R /* 559943771 */:
                Iterator<AbsBYDAutoAcListener> it63 = this.mAbsBydAutoAcListeners.iterator();
                while (it63.hasNext()) {
                    it63.next().onAirOutletValueChanged(5, hvacPacket.getAirOutletValueCopilotLeftHorizontalR());
                }
                caseFeatureIds(3417, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotLeftHorizontalR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_VERTICAL_R /* 559943772 */:
                Iterator<AbsBYDAutoAcListener> it64 = this.mAbsBydAutoAcListeners.iterator();
                while (it64.hasNext()) {
                    it64.next().onAirOutletValueChanged(6, hvacPacket.getAirOutletValueCopilotRightVerticalR());
                }
                caseFeatureIds(3423, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotRightVerticalR()));
                break;
            case HvacHalId.ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_HORIZONTAL_R /* 559943773 */:
                Iterator<AbsBYDAutoAcListener> it65 = this.mAbsBydAutoAcListeners.iterator();
                while (it65.hasNext()) {
                    it65.next().onAirOutletValueChanged(7, hvacPacket.getAirOutletValueCopilotRightHorizontalR());
                }
                caseFeatureIds(3421, Integer.valueOf((int) hvacPacket.getAirOutletValueCopilotRightHorizontalR()));
                break;
        }
    }

    public int setAcWindMode(int i, int i2) {
        Log.v(TAG, "setAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1005, i2);
            Log.v(TAG, "setAcWindMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        Log.v(TAG, "setAcWindMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcWindMode(i, i2);
    }

    public int setAcDefrostState(int i, int i2, int i3) {
        Log.v(TAG, "setAcDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1007, i2, i3);
            Log.v(TAG, "setAcDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i3 == 1) {
            i3 = 2;
        } else if (i3 == 0) {
            i3 = 1;
        }
        Log.v(TAG, "setAcDefrostState param0 is:" + i + " param1 is:" + i2 + " param2 is:" + i3);
        if (i2 == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcDefrostStateFront(i3);
        }
        if (i2 == 2) {
            return setElectricDefrostState(i3);
        }
        return 0;
    }

    public int setAcWindLevel(int i, int i2) {
        Log.v(TAG, "setAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1016, i2);
            Log.v(TAG, "setAcWindLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcWindLevel(i, i2);
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

    public int setRearAcKeyLockState(int i) {
        Log.v(TAG, "setRearAcKeyLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1008, i);
            Log.v(TAG, "setRearAcKeyLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcKeyLockState(i);
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
        int rearAcControlMode = this.mTsManagerImpl.getHvacAdapterManager().getRearAcControlMode();
        Log.v(TAG, "getAcRearAcCtrlMode value is: " + rearAcControlMode);
        if (2 == rearAcControlMode) {
            return 0;
        }
        if (rearAcControlMode == 0) {
            return -1;
        }
        return rearAcControlMode;
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

    public int setAcAntibacterialControlState(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().setAcAntibacterialControlState(i);
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

    public int setRearAcWindMode(int i, int i2) {
        Log.v(TAG, "setRearAcWindMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1003, i2);
            Log.v(TAG, "setRearAcWindMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcWindMode(i, i2);
    }

    public int setRearAcWindLevel(int i, int i2) {
        Log.v(TAG, "setRearAcWindLevel is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1013, i2);
            Log.v(TAG, "setRearAcWindLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcWindLevel(i, i2);
    }

    public int setAcCompressorState(int i) {
        Log.v(TAG, "setAcCompressorState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1015, i);
            Log.v(TAG, "setAcCompressorState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCompressorState(i);
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

    public int setMaxAcState(int i) {
        Log.v(TAG, "setMaxAcState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1004, i);
            Log.v(TAG, "setMaxAcState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setMaxAcState(i);
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

    public int setElectricDefrostState(int i) {
        Log.v(TAG, "setElectricDefrostState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1001, i);
            Log.v(TAG, "setElectricDefrostState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setElectricDefrostState(i);
    }

    public int setRearAcControlMode(int i, int i2) {
        Log.v(TAG, "setRearAcControlMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1018, i2);
            Log.v(TAG, "setRearAcControlMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 2;
        }
        Log.v(TAG, "setRearAcControlMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcControlMode(i, i2);
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

    public int setAcCompressorMode(int i, int i2) {
        Log.v(TAG, "setAcCompressorMode is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1020, i2);
            Log.v(TAG, "setAcCompressorMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i2 == 0) {
            i2 = 1;
        } else if (i2 == 1) {
            i2 = 2;
        }
        Log.v(TAG, "setAcCompressorMode param0 is:" + i + " param1 is:" + i2);
        return this.mTsManagerImpl.getHvacAdapterManager().setAcCompressorMode(i, i2);
    }

    public int setAcMaxCoolingState(int i) {
        Log.v(TAG, "setAcMaxCoolingState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1021, i);
            Log.v(TAG, "setAcMaxCoolingState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 0) {
            i = 2;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcMaxCoolingState(i);
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
        if (this.mForTest == 0) {
            Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is execute");
        if (this.mForTest == 0) {
            Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        }
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

    public int setAcWarmState(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().setAcWarmState(i);
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

    public int setRearAcLockState(int i) {
        Log.v(TAG, "setRearAcLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1023, i);
            Log.v(TAG, "setRearAcLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setRearAcLockState(i);
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

    public int feelColdHot(int i, int i2) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().feelColdHot(i, i2);
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

    public int hasFeature(String str) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().hasFeature(str);
    }

    public int enablePurificationFunctionPrompt(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().enablePurificationFunctionPrompt(i);
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

    public int setAcRemoteCtrlTime(int i) {
        Log.v(TAG, "setAcRemoteCtrlTime is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1024, i);
            Log.v(TAG, "setAcRemoteCtrlTime mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcRemoteCtrlTime(i);
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

    public int reset(int i, int i2) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().reset(i, i2);
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

    public int setAcRearPanelLockState(int i) {
        Log.v(TAG, "setAcRearPanelLockState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1019, i);
            Log.v(TAG, "setAcRearPanelLockState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcRearPanelLockState(i);
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

    public int setQuickCleanAirState(int i) {
        Log.v(TAG, "setQuickCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1022, i);
            Log.v(TAG, "setQuickCleanAirState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setQuickCleanAirState(i);
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

    public int confirmCleanAirState(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().confirmCleanAirState(i);
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

    public int setAutoCleanAirState(int i) {
        Log.v(TAG, "setAutoCleanAirState is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            this.mTestCallback.enqueueMessage(1025, i);
            Log.v(TAG, "setAutoCleanAirState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAutoCleanAirState(i);
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

    public int setBlowPeopleState(int i, int i2) {
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
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setBlowPeopleStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setBlowPeopleStateCopilot(i2);
        }
        return 0;
    }

    public int getBlowPeopleState(int i) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getBlowPeopleStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getBlowPeopleStateCopilot();
    }

    public int setAvoidBlowPeopleState(int i, int i2) {
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
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setAvoidBlowPeopleStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setAvoidBlowPeopleStateCopilot(i2);
        }
        return 0;
    }

    public int getAvoidBlowPeopleState(int i) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAvoidBlowPeopleStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAvoidBlowPeopleStateCopilot();
    }

    public int setSweepBlowState(int i, int i2) {
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
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setSweepBlowStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setSweepBlowStateCopilot(i2);
        }
        return 0;
    }

    public int getSweepBlowState(int i) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getSweepBlowStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getSweepBlowStateCopilot();
    }

    public int setFreeWindBtnState(int i, int i2) {
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
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setFreeWindBtnStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setFreeWindBtnStateCopilot(i2);
        }
        return 0;
    }

    public int getFreeWindBtnState(int i) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFreeWindBtnStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFreeWindBtnStateCopilot();
    }

    public int setAirOutletState(int i, int i2) {
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
        if (i == 0) {
            this.mTsManagerImpl.getHvacAdapterManager().setAirOutletStateDriver(i2);
        } else if (i == 1) {
            this.mTsManagerImpl.getHvacAdapterManager().setAirOutletStateCopilot(i2);
        }
        return 0;
    }

    public int getAirOutletState(int i) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAirOutletStateDriver();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAirOutletStateCopilot();
    }

    public int getAcWorkMode(int i) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeDriver();
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeCopilot();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeMiddleLeft();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeMiddleRight();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcWorkModeRear();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int setAirOutletValue(int i, int i2) {
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
        switch (i) {
            case 0:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverLeftVertical(i2);
                return 0;
            case 1:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverLeftHorizontal(i2);
                return 0;
            case 2:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverRightVertical(i2);
                return 0;
            case 3:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueDriverRightHorizontal(i2);
                return 0;
            case 4:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotLeftVertical(i2);
                return 0;
            case 5:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotLeftHorizontal(i2);
                return 0;
            case 6:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotRightVertical(i2);
                return 0;
            case 7:
                this.mTsManagerImpl.getHvacAdapterManager().setAirOutletValueCopilotRightHorizontal(i2);
                return 0;
            default:
                return 0;
        }
    }

    public float getAirOutletValue(int i) {
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
        switch (i) {
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

    public int setIntelligentControlState(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().setIntelligentControlState(i);
    }

    public int setFrontRowAirOutletState(int i) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().setFrontRowAirOutletState(i);
    }

    public int setConstantTemperature(int i) {
        Log.v(TAG, "setConstantTemperature is execute");
        enforcePermission("android.permission.BYDAUTO_AC_SET");
        if (this.mForTest == 0) {
            Log.v(TAG, "setConstantTemperature mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setConstantTemperature(i);
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
        } else if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
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
        } else if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getHvacAdapterManager().getAllStatus();
        }
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
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
        return this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatVentilating2aaConfig();
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
        return this.mTsManagerImpl.getHvacAdapterManager().getDriverSeatHeating2aaConfig();
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
        return this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatVentilating2aaConfig();
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
        return this.mTsManagerImpl.getHvacAdapterManager().getPassengerSeatHeating2aaConfig();
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
        return this.mTsManagerImpl.getHvacAdapterManager().getLrSeatVentilating2aaConfig();
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
        return this.mTsManagerImpl.getHvacAdapterManager().getLrSeatHeating2aaConfig();
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
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatVentilating2aaConfig();
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
        return this.mTsManagerImpl.getHvacAdapterManager().getRrSeatHeating2aaConfig();
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

    public int getAcTuyereSwitchState(int i) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateMainLeft();
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateMainRight();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateDeputyLeft();
        }
        if (i != 3) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getAcTuyereSwitchStateDeputyRight();
    }

    public int setAcTuyereSwitchState(int i, int i2) {
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
        if (i == 0) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateMainLeft(i2);
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateMainRight(i2);
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateDeputyLeft(i2);
        }
        if (i != 3) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setAcTuyereSwitchStateDeputyRight(i2);
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 3335) {
                        bYDAutoEventValue.intValue = getDefrostRearConfig();
                        sparseArray.put(3335, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3336) {
                        bYDAutoEventValue.intValue = getElectricDefrostState();
                        sparseArray.put(3336, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3349) {
                        bYDAutoEventValue.intValue = getHighTempAntivirusCountDown();
                        sparseArray.put(3349, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3350) {
                        bYDAutoEventValue.intValue = getHighTempAntivirusState();
                        sparseArray.put(3350, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3479) {
                        bYDAutoEventValue.intValue = getRearAcMaxWindLevel();
                        sparseArray.put(3479, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3480) {
                        bYDAutoEventValue.intValue = getAcDefrostRearPanelNotAvailableTipsState();
                        sparseArray.put(3480, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3553) {
                        bYDAutoEventValue.intValue = getAcType();
                        sparseArray.put(3553, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3554) {
                        bYDAutoEventValue.intValue = getAcVentilationState();
                        sparseArray.put(3554, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3561) {
                        bYDAutoEventValue.intValue = getAcWindLevelManualSign();
                        sparseArray.put(3561, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3562) {
                        bYDAutoEventValue.intValue = getAcWindModeManualSign();
                        sparseArray.put(3562, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3566) {
                        bYDAutoEventValue.intValue = getAcWindMode();
                        sparseArray.put(3566, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i != 3567) {
                        switch (i) {
                            case 3313:
                                bYDAutoEventValue.intValue = getHeatPumpCompressorWorkMode();
                                sparseArray.put(3313, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3316:
                                bYDAutoEventValue.intValue = getAutoCleanAirState();
                                sparseArray.put(3316, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3325:
                                bYDAutoEventValue.intValue = getAcControlMode();
                                sparseArray.put(3325, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3331:
                                bYDAutoEventValue.intValue = getAcCycleMode();
                                sparseArray.put(3331, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3333:
                                bYDAutoEventValue.intValue = getAcDefrostState(1);
                                sparseArray.put(3333, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3340:
                                bYDAutoEventValue.intValue = getAcFaultNumShownState();
                                sparseArray.put(3340, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3342:
                                bYDAutoEventValue.intValue = getFrontRowAirOutletState();
                                sparseArray.put(3342, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3352:
                                bYDAutoEventValue.intValue = getIntelligentControlState();
                                sparseArray.put(3352, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3355:
                                bYDAutoEventValue.intValue = getAcWorkMode(0);
                                sparseArray.put(3355, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3357:
                                bYDAutoEventValue.intValue = getBlowPeopleState(0);
                                sparseArray.put(3357, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3359:
                                bYDAutoEventValue.intValue = getFreeWindBtnState(0);
                                sparseArray.put(3359, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3361:
                                bYDAutoEventValue.intValue = getSweepBlowState(0);
                                sparseArray.put(3361, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3382:
                                bYDAutoEventValue.intValue = getAvoidBlowPeopleState(0);
                                sparseArray.put(3382, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3384:
                                bYDAutoEventValue.intValue = getAirOutletState(0);
                                sparseArray.put(3384, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3388:
                                bYDAutoEventValue.intValue = getAcMaxCoolingState();
                                sparseArray.put(3388, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3411:
                                bYDAutoEventValue.intValue = getBlowPeopleState(1);
                                sparseArray.put(3411, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3413:
                                bYDAutoEventValue.intValue = getFreeWindBtnState(1);
                                sparseArray.put(3413, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3415:
                                bYDAutoEventValue.intValue = getSweepBlowState(1);
                                sparseArray.put(3415, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3436:
                                bYDAutoEventValue.intValue = getAvoidBlowPeopleState(1);
                                sparseArray.put(3436, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3438:
                                bYDAutoEventValue.intValue = getAirOutletState(1);
                                sparseArray.put(3438, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3447:
                                bYDAutoEventValue.intValue = getAcStartState();
                                sparseArray.put(3447, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3450:
                                bYDAutoEventValue.intValue = getAcPtcPreheatSignal();
                                sparseArray.put(3450, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3453:
                                bYDAutoEventValue.intValue = getQuickCleanAirState();
                                sparseArray.put(3453, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3457:
                                bYDAutoEventValue.intValue = getQuickCleanTip();
                                sparseArray.put(3457, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3462:
                                bYDAutoEventValue.intValue = getRearAcControlMode();
                                sparseArray.put(3462, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3477:
                                bYDAutoEventValue.intValue = getRearACkeyLockState();
                                sparseArray.put(3477, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3483:
                                bYDAutoEventValue.intValue = getAcRearPanelLockState();
                                sparseArray.put(3483, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3486:
                                bYDAutoEventValue.intValue = getRearAcStartState();
                                sparseArray.put(3486, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3500:
                                bYDAutoEventValue.intValue = getRearAcWindLevel();
                                sparseArray.put(3500, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3503:
                                bYDAutoEventValue.intValue = getRearAcWindMode();
                                sparseArray.put(3503, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3506:
                                bYDAutoEventValue.intValue = getAcRemoteCtrlTime();
                                sparseArray.put(3506, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3509:
                                bYDAutoEventValue.intValue = getAcWorkMode(1);
                                sparseArray.put(3509, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3524:
                                bYDAutoEventValue.intValue = getAcTemperatureControlMode();
                                sparseArray.put(3524, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3526:
                                bYDAutoEventValue.intValue = getTemperatureUnit();
                                sparseArray.put(3526, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3529:
                                bYDAutoEventValue.intValue = getTemprature(2);
                                sparseArray.put(3529, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3532:
                                bYDAutoEventValue.intValue = getTemprature(1);
                                sparseArray.put(3532, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3535:
                                bYDAutoEventValue.intValue = getTemprature(3);
                                sparseArray.put(3535, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3559:
                                bYDAutoEventValue.intValue = getAcWarmTypeOnlineState();
                                sparseArray.put(3559, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3564:
                                bYDAutoEventValue.intValue = getAcWindLevel();
                                sparseArray.put(3564, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 4045:
                                bYDAutoEventValue.intValue = getTemprature(4);
                                sparseArray.put(4045, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            default:
                                switch (i) {
                                    case 3320:
                                        bYDAutoEventValue.intValue = getAcKeepEngineRunTipsState();
                                        sparseArray.put(3320, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 3321:
                                        bYDAutoEventValue.intValue = getAcCompressorManualSign();
                                        sparseArray.put(3321, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 3322:
                                        bYDAutoEventValue.intValue = getAcCompressorMode();
                                        sparseArray.put(3322, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    default:
                                        switch (i) {
                                            case BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG /* 4793 */:
                                                bYDAutoEventValue.intValue = getDriverSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG_2E4_hal_only /* 4794 */:
                                                bYDAutoEventValue.intValue = getDriverSeatVentilating2e4Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_VENTILATION_CONFIG_2E4_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG /* 4795 */:
                                                bYDAutoEventValue.intValue = getDriverSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG_2E4_hal_only /* 4796 */:
                                                bYDAutoEventValue.intValue = getDriverSeatHeating2e4Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LF_SEAT_HEATING_CONFIG_2E4_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG /* 4797 */:
                                                bYDAutoEventValue.intValue = getPassengerSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_41C_hal_only /* 4798 */:
                                                bYDAutoEventValue.intValue = getPassengerSeatVentilating41cConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_41C_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_2FF_hal_only /* 4799 */:
                                                bYDAutoEventValue.intValue = getPassengerSeatVentilating2ffConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_VENTILATION_CONFIG_2FF_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG /* 4800 */:
                                                bYDAutoEventValue.intValue = getPassengerSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_2FF_hal_only /* 4801 */:
                                                bYDAutoEventValue.intValue = getPassengerSeatHeating2ffConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_2FF_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_41C_hal_only /* 4802 */:
                                                bYDAutoEventValue.intValue = getPassengerSeatHeating41cConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RF_SEAT_HEATING_CONFIG_41C_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG /* 4803 */:
                                                bYDAutoEventValue.intValue = getLrSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_349_hal_only /* 4804 */:
                                                bYDAutoEventValue.intValue = getLrSeatVentilating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_349_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_35D_hal_only /* 4805 */:
                                                bYDAutoEventValue.intValue = getLrSeatVentilating35dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_VENTILATION_CONFIG_35D_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG /* 4806 */:
                                                bYDAutoEventValue.intValue = getLrSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_349_hal_only /* 4807 */:
                                                bYDAutoEventValue.intValue = getLrSeatHeating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_349_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_35D_hal_only /* 4808 */:
                                                bYDAutoEventValue.intValue = getLrSeatHeating35dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_LR_SEAT_HEATING_CONFIG_35D_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG /* 4809 */:
                                                bYDAutoEventValue.intValue = getRrSeatVentilating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_349_hal_only /* 4810 */:
                                                bYDAutoEventValue.intValue = getRrSeatVentilating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_349_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_363_hal_only /* 4811 */:
                                                bYDAutoEventValue.intValue = getRrSeatVentilating363Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_363_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_21D_hal_only /* 4812 */:
                                                bYDAutoEventValue.intValue = getRrSeatVentilating21dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_VENTILATION_CONFIG_21D_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG /* 4813 */:
                                                bYDAutoEventValue.intValue = getRrSeatHeating2aaConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_349_hal_only /* 4814 */:
                                                bYDAutoEventValue.intValue = getRrSeatHeating349Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_349_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_363_hal_only /* 4815 */:
                                                bYDAutoEventValue.intValue = getRrSeatHeating363Config();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_363_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                            case BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_21D_hal_only /* 4816 */:
                                                bYDAutoEventValue.intValue = getRrSeatHeating21dConfig();
                                                sparseArray.put(BYDAutoFeatureIds.AC_RR_SEAT_HEATING_CONFIG_21D_hal_only, Integer.valueOf(bYDAutoEventValue.intValue));
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                    } else {
                        bYDAutoEventValue.intValue = getAcWindModeNum();
                        sparseArray.put(3567, Integer.valueOf(bYDAutoEventValue.intValue));
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
        Iterator<AbsBYDAutoAcListener> it = this.mAbsBydAutoAcListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
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
            case 369:
                setAcAntibacterialControlState(iIntValue);
                return 1;
            case 3317:
                setAutoCleanAirState(iIntValue);
                return 1;
            case 3323:
                setAcCompressorMode(0, iIntValue);
                return 1;
            case 3332:
                setAcCycleMode(0, iIntValue);
                return 1;
            case 3334:
                setAcDefrostState(0, 1, iIntValue);
                return 1;
            case 3337:
                setElectricDefrostState(iIntValue);
                return 1;
            case 3364:
                setAirOutletValue(1, iIntValue);
                return 1;
            case 3366:
                setAirOutletValue(0, iIntValue);
                return 1;
            case 3367:
                setAirOutletValue(3, iIntValue);
                return 1;
            case 3370:
                setAirOutletValue(0, iIntValue);
                return 1;
            case 3389:
                setAcMaxCoolingState(iIntValue);
                return 1;
            case 3418:
                setAirOutletValue(5, iIntValue);
                return 1;
            case 3420:
                setAirOutletValue(4, iIntValue);
                return 1;
            case 3422:
                setAirOutletValue(7, iIntValue);
                return 1;
            case 3424:
                setAirOutletValue(6, iIntValue);
                return 1;
            case 3448:
                if (iIntValue == 1) {
                    start(0);
                } else if (iIntValue == 2) {
                    stop(0);
                }
                return 1;
            case 3452:
                confirmCleanAirState(iIntValue);
                return 1;
            case 3456:
                setQuickCleanAirState(iIntValue);
                return 1;
            case 3463:
                setRearAcControlMode(0, iIntValue);
                return 1;
            case 3478:
                setRearAcKeyLockState(iIntValue);
                return 1;
            case 3485:
                setAcRearPanelLockState(iIntValue);
                return 1;
            case 3487:
                startRearAc(iIntValue);
                return 1;
            case 3501:
                setRearAcWindLevel(0, iIntValue);
                return 1;
            case 3504:
                setRearAcWindMode(0, iIntValue);
                return 1;
            case 3507:
                setAcRemoteCtrlTime(iIntValue);
                return 1;
            case 3525:
                setAcTemperatureControlMode(0, iIntValue);
                return 1;
            case 3530:
                setAcTemperature(2, iIntValue, 0, 0);
                return 1;
            case 3533:
                setAcTemperature(1, iIntValue, 0, 0);
                return 1;
            case 3536:
                setAcTemperature(3, iIntValue, 0, 0);
                return 1;
            case 3555:
                setAcVentilationState(0, iIntValue);
                return 1;
            case 3565:
                setAcWindLevel(0, iIntValue);
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }
}
