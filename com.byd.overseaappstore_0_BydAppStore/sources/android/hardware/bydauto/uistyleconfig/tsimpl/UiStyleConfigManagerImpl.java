package android.hardware.bydauto.uistyleconfig.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.os.SystemProperties;
import android.provider.Settings;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class UiStyleConfigManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final int S_INVALID = -1;
    private static final String S_KEY_FOR_TEST = "key_for_test_UiStyleManagerImpl";
    public static final String TAG = "UiStyleManagerImpl";
    private static TsManagerImpl mTsManagerImpl;
    private static UiStyleConfigManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;

    public UiStyleConfigManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                e.s(e.k("UiStyleConfigManagerImpl mForTestValue is"), this.mForTest, " execute return", TAG);
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
    }

    public static UiStyleConfigManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (UiStyleConfigManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new UiStyleConfigManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    @Deprecated
    public int ambientFactoryMode(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().ambientFactoryMode(i);
        }
        Log.v(TAG, "ambientFactoryMode mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().backAndLeaveDelayFactoryMode(i);
        }
        Log.v(TAG, "backAndLeaveDelayFactoryMode mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int dayLightFactory(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().dayLightFactory(i);
        }
        Log.v(TAG, "dayLightFactory mForTestValue is 0 execute return");
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0440 A[PHI: r8
  0x0440: PHI (r8v1 int) = (r8v0 int), (r8v7 int) binds: [B:10:0x0027, B:12:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x044c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r13, java.lang.Class<?> r14) {
        /*
            Method dump skipped, instruction units count: 1446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    @Deprecated
    public int getACAutoAirSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getACAutoAirSupport();
        }
        Log.v(TAG, "getACAutoAirSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getACBTWindSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getACBTWindSupport();
        }
        Log.v(TAG, "getACBTWindSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getACPauseCycleSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getACPauseCycleSupport();
        }
        Log.v(TAG, "getACPauseCycleSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getACTunnelCycleSupport();
        }
        Log.v(TAG, "getACTunnelCycleSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getADASSystemState() {
        Log.v(TAG, "getADASSystemState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getADASSystemState();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAcFaultNumShownState();
        }
        Log.v(TAG, "getAcFaultNumShownState mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getAcType() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAcType();
        }
        Log.v(TAG, "getAcType mForTestValue is 0 execute return");
        return 0;
    }

    public int getAcWarmSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWarmSupport();
        }
        Log.v(TAG, "getAcWarmSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWarmTypeOnlineState();
        }
        Log.v(TAG, "getAcWarmTypeOnlineState mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getAcWindModeNum() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWindModeNum();
        }
        Log.v(TAG, "getAcWindModeNum mForTestValue is 0 execute return");
        return 0;
    }

    public int getAcceleratorPedalSuppressionSystem() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAcceleratorPedalSuppressionSystem();
        }
        Log.v(TAG, " getAcceleratorPedalSuppressionSystem mForTestValue is 0 execute return");
        return -1;
    }

    public int getAdaptiveHeadlightsSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAdaptiveHeadlightsSupport();
        }
        Log.v(TAG, "getAdaptiveHeadlightsSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAllRearSeatOneButtonDownSwitchDisplayConfig() {
        Log.v(TAG, "getAllRearSeatOneButtonDownSwitchDisplayConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAllRearSeatOneButtonDownSwitchDisplayConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getAmbientAreaSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientAreaSupport();
        }
        Log.v(TAG, "getAmbientAreaSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAmbientColorsSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientColorsSupport();
        }
        Log.v(TAG, "getAmbientColorsSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAmbientLightColorSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
        }
        Log.v(TAG, "getAmbientLightColorSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAmbientLightSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientLightSupport();
        }
        Log.v(TAG, "getAmbientLightSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAmbientModeSwitchSettings() {
        Log.v(TAG, "getAmbientModeSwitchSettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientModeSwitchSettings();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientMusicModeSupport();
        }
        Log.v(TAG, "getAmbientMusicModeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAmplifierConfiguration() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAmplifierConfiguration();
        }
        e.s(e.k(" getAmplifierConfiguration mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getAppUseLimitSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAppUseLimitSupport();
        }
        Log.v(TAG, "getAppUseLimitSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoExternalRearMirrorFollowUpSwitchSupport();
        }
        Log.v(TAG, "getAutoExternalRearMirrorFollowUpSwitchSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOffSupport();
        }
        Log.v(TAG, "getAutoPowerOffSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOnStateSupport();
        }
        Log.v(TAG, "getAutoPowerOnStateSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAutoPurificationSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPurificationSupport();
        }
        Log.v(TAG, "getAutoPurificationSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAutoReportContentSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoReportContentSupport(i);
        }
        Log.v(TAG, "getAutoReportContentSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAutoWiperSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoWiperSupport();
        }
        Log.v(TAG, "getAutoWiperSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getAverageSpeedInterfaceStatus() {
        Log.v(TAG, "getAverageSpeedInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAverageSpeedInterfaceStatus();
    }

    public int getBackHomeDelaySupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getBackHomeDelaySupport();
        }
        Log.v(TAG, "getBackHomeDelaySupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getBatteryHealthConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getBatteryHealthConfig();
        }
        Log.v(TAG, " getBatteryHealthConfig mForTestValue is 0 execute return");
        return -1;
    }

    public int getBrakePedalModeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getBrakePedalModeSupport();
        }
        Log.v(TAG, "getBrakePedalModeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getBsisSupportR() {
        Log.i(TAG, "getBsisSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "BsisSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBsisSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCabinMovementMonitoringFunction() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCabinMovementMonitoringFunction();
        }
        Log.v(TAG, " getCabinMovementMonitoringFunction mForTestValue is 0 execute return");
        return -1;
    }

    public int getCaliperSplintSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCaliperSplintSupport(i);
        }
        Log.v(TAG, "getCaliperSplintSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getCameraEerRomDeviceAddress() {
        Log.v(TAG, "getCameraEerRomDeviceAddress is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraEerRomDeviceAddress();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraIspId() {
        Log.v(TAG, "getCameraIspId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraIspId();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraLensId() {
        Log.v(TAG, "getCameraLensId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraLensId();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraSerializerId() {
        Log.v(TAG, "getCameraSerializerId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraSerializerId();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraSupplierAndTestBoardInfo() {
        Log.v(TAG, "getCameraSupplierAndTestBoardInfo is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraSupplierAndTestBoardInfo();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoFormat() {
        Log.v(TAG, "getCameraVideoFormat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFormat();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoFrameRate() {
        Log.v(TAG, "getCameraVideoFrameRate is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFrameRate();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoResolution() {
        Log.v(TAG, "getCameraVideoResolution is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoResolution();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCarFridgeType() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCarFridgeType();
        }
        Log.v(TAG, " getCarFridgeType mForTestValue is 0 execute return");
        return -1;
    }

    public int getCarNum() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCarNum();
        }
        Log.v(TAG, "getCarNum mForTestValue is 0 execute return");
        return 0;
    }

    public int getCarPlanSupport() {
        Log.v(TAG, "getCarPlanSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCarPlanSupport();
    }

    public int getCarTipsSoundSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCarTipsSoundSupport();
        }
        Log.v(TAG, "getCarTipsSoundSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getCarType() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCarType();
        }
        Log.v(TAG, "getCarType mForTestValue is 0 execute return");
        return 0;
    }

    public int getChargeLimitSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getChargeLimitSupport();
        }
        Log.v(TAG, "getChargeLimitSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getChargePreheatSupport() {
        Log.v(TAG, "getChargePreheatSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getChargePreheatSupport();
    }

    public int getChargeToaggleGuardSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getChargeToaggleGuardSupport();
        }
        Log.v(TAG, "getChargeToaggleGuardSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getComfortableParkingBy37F() {
        Log.v(TAG, "getComfortableParkingBy37F is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingBy37F();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getComfortableParkingSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingSupport();
        }
        Log.v(TAG, "getComfortableParkingSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getComfortableSpeedDown() {
        Log.v(TAG, "getComfortableSpeedDown is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getComfortableSpeedDown mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedDown();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getComfortableSpeedUp() {
        Log.v(TAG, "getComfortableSpeedUp is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getComfortableSpeedUp mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedUp();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getCpdSupportR() {
        Log.i(TAG, "getCpdSupportR is execute");
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCpdSupportR();
        }
        Log.v(TAG, "CpdSupportR mForTestValue is 0 execute return");
        return 0;
    }

    public int getCpdSwMemStatus() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getCpdSwMemStatus();
        }
        Log.v(TAG, " getCpdSwMemStatus mForTestValue is 0 execute return");
        return -1;
    }

    public int getCumulativeAverageEnergyConsumptionInterfaceStatus() {
        Log.v(TAG, "getCumulativeAverageEnergyConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAverageEnergyConsumptionInterfaceStatus();
    }

    public int getCumulativeAverageFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getCumulativeAverageFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAverageFuelConsumptionInterfaceStatus();
    }

    public int getCumulativeAveragePowerConsumptionInterfaceStatus() {
        Log.v(TAG, "getCumulativeAveragePowerConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAveragePowerConsumptionInterfaceStatus();
    }

    public int getCuraAssembleStatusFunction() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCuraAssembleStatusFunction mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCuraAssembleStatusFunction();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getCushionSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCushionSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (i == 1) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportDriver();
        }
        if (i == 2) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportPassenger();
        }
        if (i == 3) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportRearLeft();
        }
        if (i != 4) {
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCushionSupportRearRight();
    }

    public int getCvcConfig() {
        Log.v(TAG, "getCvcConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCvcConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDMSAssembleStatus() {
        Log.v(TAG, "getDMSAssembleStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDMSAssembleStatus();
    }

    public int getDMType() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDMType();
        }
        Log.v(TAG, "getDMType mForTestValue is 0 execute return");
        return 0;
    }

    public int getDayLightSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDayLightSupport();
        }
        Log.v(TAG, "getDayLightSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getDefrostRearConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDefrostRearConfig();
        }
        Log.v(TAG, "getDefrostRearConfig mForTestValue is 0 execute return");
        return 0;
    }

    public int getDiDynaConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDiDynaConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDiDynaConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDiSusShowStateByCan() {
        Log.v(TAG, "getDiSusShowStateByCan is execute");
        return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCan();
    }

    public int getDiSusShowStateByCanFd() {
        Log.v(TAG, "getDiSusShowStateByCanFd is execute");
        return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCanFd();
    }

    public int getDigtalKeySupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDigtalKeySupport();
        }
        Log.v(TAG, "getDigtalKeySupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getDischargeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDischargeSupport();
        }
        Log.v(TAG, "getDischargeSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getDisinfSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDisinfSupport();
        }
        Log.v(TAG, "getDisinfSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getDoorHeightSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorHeightSupport();
        }
        Log.v(TAG, "getDoorHeightSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getDoorLightSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorLightSupport();
        }
        Log.v(TAG, "getDoorLightSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorknobAutoShrinkSupport();
        }
        Log.v(TAG, "getDoorknobAutoShrinkSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getDriveConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveConfig();
        }
        Log.v(TAG, "getDriveConfig mForTestValue is 0 execute return");
        return 0;
    }

    public int getDriveDistanceShowModeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowModeSupport();
        }
        Log.v(TAG, "getDriveDistanceShowModeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getDriveDistanceShowStandardSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowStandardSupport();
        }
        Log.v(TAG, "getDriveDistanceShowStandardSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveSeatAutoReturnSupport();
        }
        Log.v(TAG, "getDriveSeatAutoReturnSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        Log.v(TAG, "getDriveTimeInterfaceStatusForSingleTrip is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveTimeInterfaceStatusForSingleTrip();
    }

    public int getDriverSeatHotStoneMassageConfigurationByEcu() {
        Log.v(TAG, "getDriverSeatHotStoneMassageConfigurationByEcu is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByEcu();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDriverSeatHotStoneMassageConfigurationByLeftCtrl() {
        Log.v(TAG, "getDriverSeatHotStoneMassageConfigurationByLeftCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByLeftCtrl();
    }

    public int getDriverSeatHotStoneMassageConfigurationByUniversalCtrl() {
        Log.v(TAG, "getDriverSeatHotStoneMassageConfigurationByUniversalCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByUniversalCtrl();
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        Log.v(TAG, "getDrivingRangeInterfaceStatusForFuel is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingRangeInterfaceStatusForFuel();
    }

    public int getDrivingVoiceBroadcastSwitchConfig() {
        Log.v(TAG, "getDrivingVoiceBroadcastSwitchConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingVoiceBroadcastSwitchConfig();
    }

    public int getDynamicSuspensionModeByCanFdR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanFdR is execute");
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanFdR();
        }
        Log.v(TAG, "DynamicSuspensionModeByCanFdR mForTestValue is 0 execute return");
        return 0;
    }

    public int getDynamicSuspensionModeByCanR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanR is execute");
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanR();
        }
        Log.v(TAG, "DynamicSuspensionModeByCanR mForTestValue is 0 execute return");
        return 0;
    }

    public int getDynamicSuspensionModeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeSupport();
        }
        Log.v(TAG, "getDynamicSuspensionModeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getEPedalStateSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getEPedalStateSupport();
        }
        Log.v(TAG, "getEPedalStateSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getElectricFrontHatchConfiguration() {
        Log.v(TAG, "getElectricFrontHatchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
    }

    public int getElectroniChildLockConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getElectroniChildLockConfig();
        }
        Log.v(TAG, "getElectroniChildLockConfig mForTestValue is 0 execute return");
        return 0;
    }

    public int getElectronicParkingBrakeConfigutarion() {
        Log.v(TAG, "getElectronicParkingBrakeConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectronicParkingBrakeConfigutarion();
    }

    public int getEnergyFeedbackSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getEnergyFeedbackSupport();
        }
        Log.v(TAG, " getEnergyFeedbackSupport mForTestValue is 0 execute return");
        return -1;
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        Log.v(TAG, "getEnergyFlowDiagramInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEnergyFlowDiagramInterfaceStatus();
    }

    public int getEnergyShowSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getEnergyShowSupport();
        }
        Log.v(TAG, " getEnergyShowSupport mForTestValue is 0 execute return");
        return -1;
    }

    @Deprecated
    public int getEnergyStatus() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getEnergyStatus();
        }
        Log.v(TAG, "getEnergyStatus mForTestValue is 0 execute return");
        return 0;
    }

    public int getEngineSoundSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getEngineSoundSupport();
        }
        Log.v(TAG, "getEngineSoundSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getExclusiveSaveFlag() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getExclusiveSaveFlag();
        }
        Log.v(TAG, " getExclusiveSaveFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getExternalChargingPowerInterfaceStatus() {
        Log.v(TAG, "getExternalChargingPowerInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getExternalChargingPowerInterfaceStatus();
    }

    public int getFeaturesStateR() {
        Log.i(TAG, "getFeaturesStateR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "FeaturesStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFeaturesStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFoldDownTheSecondRowOfSeatsConfig() {
        Log.v(TAG, "getFoldDownTheSecondRowOfSeatsConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFoldDownTheSecondRowOfSeatsConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getFragranceInstallState(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFragranceInstallState(i);
        }
        Log.v(TAG, "getSupportFragranceType mForTestValue is 0 execute return");
        return 0;
    }

    public int getFridgeChildLockFlag() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeChildLockFlag();
        }
        Log.v(TAG, " getFridgeChildLockFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeDelayedPowerOutage() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeDelayedPowerOutage();
        }
        Log.v(TAG, " getFridgeDelayedPowerOutage mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeExpansionAndContractionFunction() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeExpansionAndContractionFunction();
        }
        Log.v(TAG, " getFridgeExpansionAndContractionFunction mForTestValue is 0");
        return -1;
    }

    public int getFridgeMinWagePowerGeneration() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeMinWagePowerGeneration();
        }
        Log.v(TAG, " getFridgeMinWagePowerGeneration mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeNum() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeNum();
        }
        Log.v(TAG, " getFridgeNum mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeOnePosition() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeOnePosition();
        }
        Log.v(TAG, " getFridgeOnePosition mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeScreenFlag() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeScreenFlag();
        }
        Log.v(TAG, " getFridgeScreenFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeTemperatureRegulation() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeTemperatureRegulation();
        }
        Log.v(TAG, " getFridgeTemperatureRegulation mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeThreePosition() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeThreePosition();
        }
        Log.v(TAG, " getFridgeThreePosition mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeTwoPosition() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeTwoPosition();
        }
        Log.v(TAG, " getFridgeTwoPosition mForTestValue is 0 execute return");
        return -1;
    }

    public int getFridgeWorkingMode() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFridgeWorkingMode();
        }
        Log.v(TAG, " getFridgeWorkingMode mForTestValue is 0 execute return");
        return -1;
    }

    public int getFrontFridgeDisinfectFlag() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFrontFridgeDisinfectFlag();
        }
        Log.v(TAG, " getFrontFridgeDisinfectFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getFrontLightDimmingCtrlConfigBy3ac() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ac();
        }
        e.s(e.k(" getFrontLightDimmingCtrlConfigBy3ac mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getFrontLightDimmingCtrlConfigBy3ad() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ad();
        }
        e.s(e.k(" getFrontLightDimmingCtrlConfigBy3ad mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFuelConsumptionInterfaceStatus();
    }

    public int getGuestLeftSeatRememeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getGuestLeftSeatRememeSupport();
        }
        Log.v(TAG, "getGuestLeftSeatRememeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getGuestRightSeatRememeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getGuestRightSeatRememeSupport();
        }
        Log.v(TAG, "getGuestRightSeatRememeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getHUDModeConfiguration() {
        Log.v(TAG, "getHUDModeConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHUDModeConfiguration();
    }

    public int getHasPredictCrashAlertMode() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHasPredictCrashAlertMode();
        }
        e.s(e.k(" getHasPredictCrashAlertMode mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getHeadlampLevelConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadlampLevelConfig();
        }
        Log.v(TAG, "getHeadlampLevelConfig mForTestValue is 0 execute return");
        return 0;
    }

    public int getHeadlampSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadlampSupport();
        }
        Log.v(TAG, "getHeadlampSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestTurnHeightSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHeightSupport(i);
        }
        Log.v(TAG, "getHeadrestTurnHeightSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHorizatialSupport(i);
        }
        Log.v(TAG, "getHeadrestTurnHorizatialSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHevCarPlanSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHevCarPlanSupport();
        }
        Log.v(TAG, " getHevCarPlanSupport mForTestValue is 0 execute return");
        return -1;
    }

    public int getHudHeightAdjustSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHudHeightAdjustSupport();
        }
        Log.v(TAG, "getHudHeightAdjustSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getHudLightSdjustSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHudLightSdjustSupport();
        }
        Log.v(TAG, "getHudLightSdjustSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHudParamsStateSupport();
        }
        Log.v(TAG, "getHudParamsStateSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getHudShowParamsSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getHudShowParamsSupport();
        }
        Log.v(TAG, "getHudShowParamsSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getIndoorLightSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getIndoorLightSupport();
        }
        Log.v(TAG, "getIndoorLightSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getInstantaneousFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getInstantaneousFuelConsumptionInterfaceStatus();
    }

    public int getInstrumentContentSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentContentSupport();
        }
        Log.v(TAG, "getInstrumentContentSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getInstrumentHeightSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentHeightSupport();
        }
        Log.v(TAG, "getInstrumentHeightSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getInstrumentThemeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentThemeSupport();
        }
        Log.v(TAG, "getInstrumentThemeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getIntelligentAcAreaSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentAcAreaSupport();
        }
        Log.v(TAG, "getIntelligentAcAreaSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getIntelligentKeepTemSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentKeepTemSupport();
        }
        Log.v(TAG, "getIntelligentKeepTemSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentLockSupport();
        }
        Log.v(TAG, "getIntelligentLockSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getIntelligentScreenCastingSwitchConfiguration() {
        Log.v(TAG, "getIntelligentScreenCastingSwitchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentScreenCastingSwitchConfiguration();
    }

    public int getIntelligentSpeedLimitMapVersionDisplay() {
        Log.v(TAG, "getIntelligentSpeedLimitMapVersionDisplay is execute");
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentSpeedLimitMapVersionDisplay();
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentUnlockSupport();
        }
        Log.v(TAG, "getIntelligentUnlockSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getLandAdvanceAtSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getLandAdvanceAtSupport();
        }
        Log.v(TAG, "getLandAdvanceAtSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getLeaveHomeDelaySupport();
        }
        Log.v(TAG, "getLeaveHomeDelaySupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getLeftPlatformConfigs() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getLeftPlatformConfigs();
        }
        Log.v(TAG, "getLeftPlatformConfigs mForTestValue is 0 execute return");
        return 0;
    }

    public int getLeftRESConfiguration() {
        Log.v(TAG, "getLeftRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRESConfiguration();
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        Log.v(TAG, "getLeftRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
    }

    public int getLeftRearMemoryConfiguration() {
        Log.v(TAG, "getLeftRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        Log.v(TAG, "getLeftRearSeatBackAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        Log.v(TAG, "getLeftRearSeatHorizontalAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getLeftRearSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getLeftRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getLeftRearSeatLegBracket() {
        Log.v(TAG, "getLeftRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
    }

    public int getLeftRearSeatReclineConfiguration() {
        Log.v(TAG, "getLeftRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfiguration();
    }

    public int getLeftRearSeatReclineConfigurationByECU() {
        Log.v(TAG, "getLeftRearSeatReclineConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfigurationByECU();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getLockCarRiseWindowSupport();
        }
        Log.v(TAG, "getLockCarRiseWindowSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getLockOffSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getLockOffSupport();
        }
        Log.v(TAG, "getLockOffSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getLowSpeedEmergencyBrakingConfig() {
        Log.v(TAG, "getLowSpeedEmergencyBrakingConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLowSpeedEmergencyBrakingConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        Log.v(TAG, "getMainDriveElectricHeadrestAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        Log.v(TAG, "getMainDriveElectricLegSupportAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getMainSeatRememberSupport();
        }
        Log.v(TAG, "getMainSeatRememberSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getMemoryParking() {
        Log.v(TAG, "getMemoryParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMemoryParking();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchLockWindowSupport();
        }
        Log.v(TAG, "getMicroSwitchLockWindowSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchUnlockWindowSupport();
        }
        Log.v(TAG, "getMicroSwitchUnlockWindowSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getMileageResetConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMileageResetConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMileageResetConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getMoisSupportR() {
        Log.i(TAG, "getMoisSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "MoisSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMoisSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMoonRoofConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getMoonRoofConfig();
        }
        Log.v(TAG, "getMoonRoofConfig mForTestValue is 0 execute return");
        return 0;
    }

    public int getMotorPretensionerSeatbeltSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getMotorPretensionerSeatbeltSupport();
        }
        Log.v(TAG, "getMotorPretensionerSeatbeltSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getNoSenseChargeSupport() {
        Log.v(TAG, "getNoSenseChargeSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getNoSenseChargeSupport();
    }

    public int getOBCType() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getOBCType();
        }
        Log.v(TAG, "getOBCType mForTestValue is 0 execute return");
        return 0;
    }

    public int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat() {
        Log.v(TAG, "getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getOneButtonDownConfigurationOfTheThirdRowRightRearSeat() {
        Log.v(TAG, "getOneButtonDownConfigurationOfTheThirdRowRightRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowRightRearSeat();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getOneButtonDownSwitchDisplayForTheThirdRowOfSeats() {
        Log.v(TAG, "getOneButtonDownSwitchDisplayForTheThirdRowOfSeats is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownSwitchDisplayForTheThirdRowOfSeats();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getOverspeedLockSupport();
        }
        Log.v(TAG, "getOverspeedLockSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getPM25PowerSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25PowerSupport();
        }
        Log.v(TAG, "getPM25PowerSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25SwitchCheckSupport();
        }
        Log.v(TAG, "getPM25SwitchCheckSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25TimeCkeckSupport();
        }
        Log.v(TAG, "getPM25TimeCkeckSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getPabSwitchState() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getPabSwitchState();
        }
        e.s(e.k(" getPabSwitchState mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getPanoramicSunroofConfigutarion() {
        Log.v(TAG, "getPanoramicSunroofConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPanoramicSunroofConfigutarion();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatElectricAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatHeadrestConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByGCtrl() {
        Log.v(TAG, "getPassengerSeatHeadrestConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByGCtrl();
    }

    public int getPassengerSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHotStoneMassageConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl() {
        Log.v(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
    }

    public int getPassengerSeatMemoryFunctionCan() {
        Log.v(TAG, "getPassengerSeatMemoryFunctionCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCan();
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        Log.v(TAG, "getPassengerSeatMemoryFunctionCanByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
    }

    public int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getPreSaleModelSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getPreSaleModelSupport();
        }
        Log.v(TAG, "getPreSaleModelSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRainAutoLockWindowSupport();
        }
        Log.v(TAG, "getRainAutoLockWindowSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearAcMaxWindLevel();
        }
        Log.v(TAG, "getRearAcMaxWindLevel mForTestValue is 0 execute return");
        return 0;
    }

    public int getRearFridgeDisinfectFlag() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearFridgeDisinfectFlag();
        }
        Log.v(TAG, " getRearFridgeDisinfectFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getRearFridgeKeyTypeFlag() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearFridgeKeyTypeFlag();
        }
        Log.v(TAG, " getRearFridgeKeyTypeFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getRearFridgeWorkTypeFlag() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearFridgeWorkTypeFlag();
        }
        Log.v(TAG, " getRearFridgeWorkTypeFlag mForTestValue is 0 execute return");
        return -1;
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        Log.v(TAG, "getRearLeftMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
    }

    public int getRearPanelLockTimeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearPanelLockTimeSupport();
        }
        Log.v(TAG, "getRearPanelLockTimeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
    }

    public int getRearRightFridgeHeatingScheme() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearRightFridgeHeatingScheme();
        }
        Log.v(TAG, " getRearRightFridgeHeatingScheme mForTestValue is 0 execute return");
        return -1;
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        Log.v(TAG, "getRearRightMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
    }

    public int getRearSeatReclineConfiguration() {
        Log.v(TAG, "getRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearViewMirrorFlipSupport();
        }
        Log.v(TAG, "getRearViewMirrorFlipSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getRearviewMirrorSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRearviewMirrorSupport();
        }
        Log.v(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getRefrigeratorModeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRefrigeratorModeSupport();
        }
        Log.v(TAG, "getRefrigeratorModeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getRemindSpeedSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRemindSpeedSupport();
        }
        Log.v(TAG, "getRemindSpeedSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteControlLockWindowSupport();
        }
        Log.v(TAG, "getRemoteControlLockWindowSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteControlUnlockWindowSupport();
        }
        Log.v(TAG, "getRemoteControlUnlockWindowSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteKeyStartAcTimeSupport();
        }
        Log.v(TAG, "getRemoteKeyStartAcTimeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getReportContentSetSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getReportContentSetSupport();
        }
        Log.v(TAG, "getReportContentSetSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getReportItemsSetSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getReportItemsSetSupport(i);
        }
        Log.v(TAG, "getReportItemsSetSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getReportVolumeSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getReportVolumeSupport();
        }
        Log.v(TAG, "getReportVolumeSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getRestModeConfiguration() {
        Log.v(TAG, "getRestModeConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRestModeConfiguration();
    }

    public int getRightRESConfiguration() {
        Log.v(TAG, "getRightRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRESConfiguration();
    }

    public int getRightRearMassageChairConfigurationForECU() {
        Log.v(TAG, "getRightRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
    }

    public int getRightRearMemoryConfiguration() {
        Log.v(TAG, "getRightRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        Log.v(TAG, "getRightRearSeatBackAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        Log.v(TAG, "getRightRearSeatHorizontalAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getRightRearSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getRightRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRightRearSeatLegBracket() {
        Log.v(TAG, "getRightRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
    }

    public int getRightRearSeatReclineConfiguration() {
        Log.v(TAG, "getRightRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfiguration();
    }

    public int getRightRearSeatReclineConfigurationByEUC() {
        Log.v(TAG, "getRightRearSeatReclineConfigurationByEUC is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
    }

    public int getRuningCloseDoorSupport() {
        Log.v(TAG, "getRuningCloseDoorSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRuningCloseDoorSupport();
    }

    public int getRunningCodingConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRunningCodingConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRunningCodingConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSandModeAbsConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSandModeAbsConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSandModeAbsConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSeatSetSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatSetSupport(i);
        }
        Log.v(TAG, "getSeatSetSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getSeatTurnHeightSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHeightSupport(i);
        }
        Log.v(TAG, "getSeatTurnHeightSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getSeatTurnHorizatialSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHorizatialSupport(i);
        }
        Log.v(TAG, "getSeatTurnHorizatialSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getSeatbackrestSupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatbackrestSupport(i);
        }
        Log.v(TAG, "getSeatbackrestSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getSecondSeatRememberSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSecondSeatRememberSupport();
        }
        Log.v(TAG, "getSecondSeatRememberSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getSideDistanceWarningConfig() {
        Log.v(TAG, "getSideDistanceWarningConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSideDistanceWarningConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getSocTargetConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSocTargetConfig();
        }
        Log.v(TAG, " getSocTargetConfig mForTestValue is 0 execute return");
        return -1;
    }

    public int getStarTopSwitchSetting() {
        Log.v(TAG, "getStarTopSwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getStarTopSwitchSetting();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getSteerAssisSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSteerAssisSupport();
        }
        Log.v(TAG, "getSteerAssisSupport mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSteerPositionAutoReturnSupport();
        }
        Log.v(TAG, "getSteerPositionAutoReturnSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getSteeringWheelHeatingConfiguration() {
        Log.v(TAG, "getSteeringWheelHeatingConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelHeatingConfiguration();
    }

    public int getSteeringWheelSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelSupport();
        }
        Log.v(TAG, "getSteeringWheelSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAFS() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAFS();
        }
        Log.v(TAG, "getSupportAFS mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAcAutoKeyCanClose() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAcAutoKeyCanClose();
        }
        Log.v(TAG, "getSupportAcAutoKeyCanClose mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAcChargingLimit() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAcChargingLimit();
        }
        Log.v(TAG, " getSupportAcChargingLimit mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportAiCare() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiCare();
        }
        Log.v(TAG, "getSupportAiCare mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAiDistanceLight() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiDistanceLight();
        }
        Log.v(TAG, "getSupportAiDistanceLight mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAiEmergencyBrake() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiEmergencyBrake();
        }
        Log.v(TAG, "getSupportAiEmergencyBrake mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAiMatrixLight() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiMatrixLight();
        }
        Log.v(TAG, "getSupportAiMatrixLight mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAutoParking() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAutoParking();
        }
        Log.v(TAG, "getSupportAutoParking mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportAvh() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAvh();
        }
        e.s(e.k(" getSupportAVH mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getSupportBlindSpotAssist() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssist();
        }
        Log.v(TAG, "getSupportBlindSpotAssist mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportBlindSpotAssistBy418() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssistBy418();
        }
        Log.v(TAG, "getSupportBlindSpotAssistBy418 mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportCampModeConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSupportCampModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportCampModeConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportChargeHevThree() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportChargeHevThree();
        }
        Log.v(TAG, " getSupportChargeHevThree mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportChargeHevThreeVcu() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportChargeHevThreeVcu();
        }
        Log.v(TAG, " getSupportChargeHevThreeVcu mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportDigitalKey() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDigitalKey();
        }
        Log.v(TAG, "getSupportDigitalKey mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportDimmingCanopyConfiguration() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDimmingCanopyConfiguration();
        }
        e.s(e.k(" getSupportDimmingCanopyConfiguration mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getSupportDoorOpenWarning() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDoorOpenWarning();
        }
        Log.v(TAG, "getSupportDoorOpenWarning mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportDoubleConfirm() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDoubleConfirm();
        }
        e.s(e.k(" getSupportDoubleConfirm mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getSupportESP() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportESP();
        }
        Log.v(TAG, "getSupportESP mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportEmergencyLaneKeepAssist() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportEmergencyLaneKeepAssist();
        }
        Log.v(TAG, "getSupportEmergencyLaneKeepAssist mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportEndToEndNavigation() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportEndToEndNavigation();
        }
        Log.v(TAG, "getSupportEndToEndNavigation mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportExtreme() {
        Log.v(TAG, "getSupportExtreme is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportExtreme();
    }

    public int getSupportFRElectricAirOutlet() {
        Log.v(TAG, "getSupportFRElectricAirOutlet is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportFRElectricAirOutlet();
    }

    public int getSupportFragrance() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFragrance mForTestValue is 0 execute return");
            return 0;
        }
        Log.i(TAG, this.mContext.getPackageName() + "getSupportFragrance");
        return mTsManagerImpl.getStudyAdapterManager().getSupportFragrance();
    }

    public int getSupportFrontCrossingTrafficAlert() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficAlert();
        }
        Log.v(TAG, "getSupportFrontCrossingTrafficAlert mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportFrontCrossingTrafficBrake() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficBrake();
        }
        Log.v(TAG, "getSupportFrontCrossingTrafficBrake mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportHighTemperatureSterilize() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportHighTemperatureSterilize();
        }
        Log.v(TAG, "getSupportHighTemperatureSterilize mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportHighwayNavigation() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportHighwayNavigation();
        }
        Log.v(TAG, "getSupportHighwayNavigation mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportInsUiMode() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportInsUiMode();
        }
        Log.v(TAG, " getSupportInsUiMode mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportInsUiShape() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportInsUiShape();
        }
        Log.v(TAG, " getSupportInsUiShape mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportInsUiSize() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportInsUiSize();
        }
        Log.v(TAG, " getSupportInsUiSize mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportIntelligentNavigation() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigation();
        }
        Log.v(TAG, "getSupportIntelligentNavigation mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportIntelligentNavigationBy432() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigationBy432();
        }
        Log.v(TAG, "getSupportIntelligentNavigationBy432 mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitAlert();
        }
        Log.v(TAG, "getSupportIntellignetSpeedLimitAlert mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportIntellignetSpeedLimitControl() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitControl();
        }
        Log.v(TAG, "getSupportIntellignetSpeedLimitControl mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportLampSingnal() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLampSingnal();
        }
        Log.v(TAG, "getSupportLampSingnal mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportLaneChangeAssist() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneChangeAssist();
        }
        Log.v(TAG, "getSupportLaneChangeAssist mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportLaneDepartureStop() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureStop();
        }
        Log.v(TAG, "getSupportLaneDepartureStop mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportLaneDepartureWarning() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureWarning();
        }
        Log.v(TAG, "getSupportLaneDepartureWarning mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportLaneKeep() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneKeep();
        }
        Log.v(TAG, "getSupportLaneKeep mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportManualModelR() {
        Log.i(TAG, "getSupportManualModelR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportManualModelR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportManualModelR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportMistakeGas() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportMistakeGas();
        }
        Log.v(TAG, "getSupportMistakeGas mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportNegativeIon() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportNegativeIon();
        }
        Log.v(TAG, "getSupportNegativeIon mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportOilBlade() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportOilBlade();
        }
        Log.v(TAG, " getSupportOilBlade mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportOilBrake() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportOilBrake();
        }
        Log.v(TAG, " getSupportOilBrake mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportOilCool() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportOilCool();
        }
        Log.v(TAG, " getSupportOilCool mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportOilEngine() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportOilEngine();
        }
        Log.v(TAG, " getSupportOilEngine mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportOilTransmission() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportOilTransmission();
        }
        Log.v(TAG, " getSupportOilTransmission mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportPM25Mode() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPM25Mode();
        }
        Log.v(TAG, "getSupportPM25Mode mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportPanoramaImage() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPanoramaImage();
        }
        Log.v(TAG, "getSupportPanoramaImage mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportPredictCrashAlertMode() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPredictCrashAlertMode();
        }
        Log.v(TAG, "getSupportPredictCrashAlertMode mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAc() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAc();
        }
        Log.v(TAG, "getSupportRearAc mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAcAutoControl() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcAutoControl();
        }
        Log.v(TAG, "getSupportRearAcAutoControl mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAcCompressor() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcCompressor();
        }
        Log.v(TAG, "getSupportRearAcCompressor mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAcKeyLock() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcKeyLock();
        }
        Log.v(TAG, "getSupportRearAcKeyLock mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAcSwitch() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcSwitch();
        }
        Log.v(TAG, "getSupportRearAcSwitch mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAcTempertureControl() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcTempertureControl();
        }
        Log.v(TAG, "getSupportRearAcTempertureControl mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAcWindLevelControl() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindLevelControl();
        }
        Log.v(TAG, "getSupportRearAcWindLevelControl mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearAcWindModeControl() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindModeControl();
        }
        Log.v(TAG, "getSupportRearAcWindModeControl mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearCollisionWarning() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearCollisionWarning();
        }
        Log.v(TAG, "getSupportRearCollisionWarning mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearControlPanelSign() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearControlPanelSign();
        }
        e.s(e.k(" getSupportRearControlPanelSign mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getSupportRearCrossBrake() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearCrossBrake();
        }
        Log.v(TAG, "getSupportRearCrossBrake mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRearTrafficCrossingAlert() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearTrafficCrossingAlert();
        }
        Log.v(TAG, "getSupportRearTrafficCrossingAlert mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRemenberPowerOff();
        }
        Log.v(TAG, "getSupportRemenberPowerOff mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRestModeConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSupportRestModeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRestModeConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportReverseRadar() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportReverseRadar();
        }
        Log.v(TAG, "getSupportReverseRadar mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportRnc() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRnc();
        }
        Log.v(TAG, " getSupportRnc mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportRoadMode() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRoadMode();
        }
        Log.v(TAG, " getSupportRoadMode mForTestValue is 0 execute return");
        return -1;
    }

    public int[] getSupportSeatAdjust(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSeatAdjust mForTestValue is 0 execute return");
            return new int[0];
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSeatAdjust(i);
        }
        Log.v(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getSupportSeatMassage(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(i);
        }
        Log.v(TAG, "getSupportSeatMassage mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportShakeAlert() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlert();
        }
        Log.v(TAG, "getSupportShakeAlert mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportShakeAlertR() {
        Log.i(TAG, "getSupportShakeAlertR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportShakeAlertR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlertR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportShowCarModel() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShowCarModel();
        }
        Log.v(TAG, "getSupportShowCarModel mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportSingleDoubleTempArea() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSingleDoubleTempArea();
        }
        Log.v(TAG, "getSupportSingleDoubleTempArea mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportSlw() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSlw();
        }
        Log.v(TAG, " getSupportSlw mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportSmartChargingPHEV() {
        Log.v(TAG, "getSupportSmartChargingPHEV is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSmartChargingPHEV();
    }

    public int getSupportSmartChargingPhevPhase2R() {
        Log.i(TAG, "getSupportSmartChargingPhevPhase2R is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportSmartChargingPhevPhase2R mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSmartChargingPhevPhase2R();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportSmrm() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSmrm();
        }
        Log.v(TAG, " getSupportSmrm mForTestValue is 0 execute return");
        return -1;
    }

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSteeringWheelWarm();
        }
        Log.v(TAG, "getSupportSteeringWheelWarm mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportTrafficLightAttention() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficLightAttention();
        }
        Log.v(TAG, "getSupportTrafficLightAttention mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportTrafficSignRecogniton() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignRecogniton();
        }
        Log.v(TAG, "getSupportTrafficSignRecogniton mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportTrafficSignsRecognition() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignsRecognition();
        }
        Log.v(TAG, "getSupportTrafficSignsRecognition mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportTsrFlow() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTsrFlow();
        }
        e.s(e.k(" getSupportTsrFlow mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getSupportUrbanNavigation() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportUrbanNavigation();
        }
        Log.v(TAG, "getSupportUrbanNavigation mForTestValue is 0 execute return");
        return 0;
    }

    public int getSupportiTac() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportiTac();
        }
        Log.v(TAG, " getSupportiTac mForTestValue is 0 execute return");
        return -1;
    }

    public int getSupportiTacConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportiTacConfig();
        }
        Log.v(TAG, " getSupportiTacConfig mForTestValue is 0 execute return");
        return -1;
    }

    public int getTailgateTypeMarking() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getTailgateTypeMarking();
        }
        e.s(e.k(" getTailgateTypeMarking mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getTheLast50KMAverageEnergyConsumptionInterfaceState() {
        Log.v(TAG, "getTheLast50KMAverageEnergyConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAverageEnergyConsumptionInterfaceState();
    }

    public int getTheLast50KMAverageFuelConsumptionInterfaceState() {
        Log.v(TAG, "getTheLast50KMAverageFuelConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAverageFuelConsumptionInterfaceState();
    }

    public int getTheLast50KMAveragePowerConsumptionInterfaceState() {
        Log.v(TAG, "getTheLast50KMAveragePowerConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAveragePowerConsumptionInterfaceState();
    }

    public int getTirePressureInterfaceStatus() {
        Log.v(TAG, "getTirePressureInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTirePressureInterfaceStatus();
    }

    public int getTrailerModeConfig() {
        Log.v(TAG, " getTrailerModeConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getTrailerModeConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getTrayControlSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getTrayControlSupport();
        }
        Log.v(TAG, "getTrayControlSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getTrunkConfigutarion() {
        Log.v(TAG, "getTrunkConfigutarion is execute");
        return mTsManagerImpl.getStudyAdapterManager().getTrunkConfigutarion();
    }

    public int getTsrLitSpdChgSwitchState() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getTsrLitSpdChgSwitchState();
        }
        e.s(e.k(" getTsrLitSpdChgSwitchState mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getUiStyle() {
        Log.v(TAG, "getUiStyle is execute UI_STYLE_PROP_KEY is persist.sys.ui_style");
        String str = SystemProperties.get("persist.sys.ui_style");
        Log.v(TAG, "getUiStyle uiStyle is " + str);
        if (str != null && str != "") {
            return Integer.parseInt(str);
        }
        Log.v(TAG, " uiStyle is null");
        return -1;
    }

    public int getUsherLightSupport() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getUsherLightSupport();
        }
        Log.v(TAG, "getUsherLightSupport mForTestValue is 0 execute return");
        return 0;
    }

    public int getV2xConfig() {
        Log.v(TAG, "getV2xConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getV2xConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getValetParking() {
        Log.v(TAG, "getValetParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getValetParking();
    }

    public int getWadingModeSetFlag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getWadingModeSetFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWadingModeSetFlag();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getWarningAuxiliarySwitchSetting() {
        Log.v(TAG, "getWarningAuxiliarySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWarningAuxiliarySwitchSetting();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getWelcomeCeremonySwitchSetting() {
        Log.v(TAG, "getWelcomeCeremonySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWelcomeCeremonySwitchSetting();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getWindowAntiPinchConfig() {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().getWindowAntiPinchConfig();
        }
        Log.v(TAG, "getWindowAntiPinchConfig mForTestValue is 0 execute return");
        return 0;
    }

    public boolean isDenza() {
        return getUiStyle() == 1;
    }

    public boolean isDynasty() {
        return getUiStyle() == 0;
    }

    public boolean isOcean() {
        return getUiStyle() == 2;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerConnectListener(BYDAutoStudyListener bYDAutoStudyListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe study listener " + bYDAutoStudyListener);
        if (bYDAutoStudyListener == null) {
            Log.d(TAG, "listener is null");
        } else {
            registerConnectListener(this.mContext, bYDAutoStudyListener);
        }
    }

    public int resetAmbientSettings(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().resetAmbientSettings(i);
        }
        Log.v(TAG, "resetAmbientSettings mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().resetAutoPurificationFactoryMode(i);
        }
        Log.v(TAG, "resetAutoPurificationFactoryMode mForTestValue is 0 execute return");
        return 0;
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().resetBackAndLeaveDelaySupport(i);
        }
        Log.v(TAG, "resetBackAndLeaveDelaySupport mForTestValue is 0 execute return");
        return 0;
    }

    public int resetDayLight(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().resetDayLight(i);
        }
        Log.v(TAG, "resetDayLight mForTestValue is 0 execute return");
        return 0;
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().resetDoorAndIndoor(i);
        }
        Log.v(TAG, "resetDoorAndIndoor mForTestValue is 0 execute return");
        return 0;
    }

    public int resetHeadlamp(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().resetHeadlamp(i);
        }
        Log.v(TAG, "resetHeadlamp mForTestValue is 0 execute return");
        return 0;
    }

    public int restoreFactorySettings() {
        Log.v(TAG, "restoreFactorySettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().restoreFactorySettings();
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().setAcWarmFactoryMode(i);
        }
        Log.v(TAG, "setAcWarmFactoryMode mForTestValue is 0 execute return");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

    public int setRncState(int i) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getStudyAdapterManager().setRncState(i);
        }
        Log.v(TAG, " setRncState mForTestValue is 0 execute return");
        return -1;
    }
}
