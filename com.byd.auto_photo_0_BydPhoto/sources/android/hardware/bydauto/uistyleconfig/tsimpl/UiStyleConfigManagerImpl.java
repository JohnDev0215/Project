package android.hardware.bydauto.uistyleconfig.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.os.SystemProperties;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class UiStyleConfigManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final int S_INVALID = -1;
    private static final String S_KEY_FOR_TEST = "key_for_test_UiStyleManagerImpl";
    protected static final String TAG = "UiStyleManagerImpl";
    private static TsManagerImpl mTsManagerImpl;
    private static UiStyleConfigManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;

    UiStyleConfigManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "UiStyleConfigManagerImpl mForTestValue is" + this.mForTest + " execute return");
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
        String str;
        if (1 == this.mForTest) {
            str = "ambientFactoryMode mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().ambientFactoryMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "backAndLeaveDelayFactoryMode mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().backAndLeaveDelayFactoryMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int dayLightFactory(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "dayLightFactory mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().dayLightFactory(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:135:0x0436 A[PHI: r8
  0x0436: PHI (r8v1 int) = (r8v0 int), (r8v7 int) binds: [B:10:0x0027, B:12:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0442  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r13, java.lang.Class<?> r14) {
        /*
            Method dump skipped, instruction units count: 1432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    @Deprecated
    public int getACAutoAirSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getACAutoAirSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getACAutoAirSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getACBTWindSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getACBTWindSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getACBTWindSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getACPauseCycleSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getACPauseCycleSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getACPauseCycleSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getACTunnelCycleSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getACTunnelCycleSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getAcFaultNumShownState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAcFaultNumShownState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getAcType() {
        String str;
        if (1 == this.mForTest) {
            str = "getAcType mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAcType();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAcWarmSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAcWarmSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAcWarmSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        String str;
        if (1 == this.mForTest) {
            str = "getAcWarmTypeOnlineState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAcWarmTypeOnlineState();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getAcWindModeNum() {
        String str;
        if (1 == this.mForTest) {
            str = "getAcWindModeNum mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAcWindModeNum();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAdaptiveHeadlightsSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAdaptiveHeadlightsSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAdaptiveHeadlightsSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getAmbientAreaSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmbientAreaSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientColorsSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAmbientColorsSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmbientColorsSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientLightColorSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAmbientLightColorSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmbientLightSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAmbientLightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmbientLightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getAmbientMusicModeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmbientMusicModeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAmplifierConfiguration() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmplifierConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getAmplifierConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getAppUseLimitSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAppUseLimitSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAppUseLimitSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAutoExternalRearMirrorFollowUpSwitchSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAutoExternalRearMirrorFollowUpSwitchSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAutoPowerOffSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOffSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAutoPowerOnStateSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOnStateSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAutoPurificationSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAutoPurificationSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAutoPurificationSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAutoReportContentSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getAutoReportContentSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAutoReportContentSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getAutoWiperSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getAutoWiperSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAutoWiperSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getBackHomeDelaySupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getBackHomeDelaySupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getBrakePedalModeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getBrakePedalModeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getBrakePedalModeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getCaliperSplintSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getCaliperSplintSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getCaliperSplintSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getCarNum() {
        String str;
        if (1 == this.mForTest) {
            str = "getCarNum mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getCarNum();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getCarTipsSoundSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getCarTipsSoundSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getCarType() {
        String str;
        if (1 == this.mForTest) {
            str = "getCarType mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getCarType();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getChargeLimitSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getChargeLimitSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getChargeLimitSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getChargeToaggleGuardSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getChargeToaggleGuardSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getComfortableParkingSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getComfortableSpeedDown() {
        Log.v(TAG, "getComfortableSpeedDown is execute");
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedDown();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getComfortableSpeedDown mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getComfortableSpeedUp() {
        Log.v(TAG, "getComfortableSpeedUp is execute");
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedUp();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getComfortableSpeedUp mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getCpdSupportR() {
        Log.i(TAG, "getCpdSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "CpdSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCpdSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
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

    public int getCushionSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getCushionSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
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
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
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
        String str;
        if (1 == this.mForTest) {
            str = "getDMType mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDMType();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDayLightSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDayLightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDayLightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getDefrostRearConfig() {
        String str;
        if (1 == this.mForTest) {
            str = "getDefrostRearConfig mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDefrostRearConfig();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDiSusShowStateByCan() {
        Log.v(TAG, "getDiSusShowStateByCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCan();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDiSusShowStateByCanFd() {
        Log.v(TAG, "getDiSusShowStateByCanFd is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCanFd();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDigtalKeySupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDigtalKeySupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDigtalKeySupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDischargeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDischargeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDischargeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getDisinfSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDisinfSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDisinfSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDoorHeightSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDoorHeightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDoorHeightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDoorLightSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDoorLightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDoorLightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDoorknobAutoShrinkSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDoorknobAutoShrinkSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDriveConfig() {
        String str;
        if (1 == this.mForTest) {
            str = "getDriveConfig mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDriveConfig();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDriveDistanceShowModeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDriveDistanceShowModeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowModeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getDriveDistanceShowStandardSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDriveDistanceShowStandardSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowStandardSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDriveSeatAutoReturnSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDriveSeatAutoReturnSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        if (1 == this.mForTest) {
            Log.v(TAG, "DynamicSuspensionModeByCanFdR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanFdR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDynamicSuspensionModeByCanR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "DynamicSuspensionModeByCanR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDynamicSuspensionModeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getDynamicSuspensionModeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getEPedalStateSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getEPedalStateSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getEPedalStateSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getElectroniChildLockConfig mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getElectroniChildLockConfig();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getElectronicParkingBrakeConfigutarion() {
        Log.v(TAG, "getElectronicParkingBrakeConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectronicParkingBrakeConfigutarion();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        Log.v(TAG, "getEnergyFlowDiagramInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEnergyFlowDiagramInterfaceStatus();
    }

    @Deprecated
    public int getEnergyStatus() {
        String str;
        if (1 == this.mForTest) {
            str = "getEnergyStatus mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getEnergyStatus();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getEngineSoundSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getEngineSoundSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getEngineSoundSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
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
        String str;
        if (1 == this.mForTest) {
            str = "getSupportFragranceType mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getFragranceInstallState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getFrontLightDimmingCtrlConfigBy3ac() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ac();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getFrontLightDimmingCtrlConfigBy3ac mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getFrontLightDimmingCtrlConfigBy3ad() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ad();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getFrontLightDimmingCtrlConfigBy3ad mForTestValue is " + this.mForTest + " execute return");
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
        String str;
        if (1 == this.mForTest) {
            str = "getGuestLeftSeatRememeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getGuestLeftSeatRememeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getGuestRightSeatRememeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getGuestRightSeatRememeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getGuestRightSeatRememeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHasPredictCrashAlertMode();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getHasPredictCrashAlertMode mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getHeadlampLevelConfig() {
        String str;
        if (1 == this.mForTest) {
            str = "getHeadlampLevelConfig mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHeadlampLevelConfig();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getHeadlampSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getHeadlampSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHeadlampSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getHeadrestTurnHeightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHeightSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getHeadrestTurnHorizatialSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHorizatialSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getHudHeightAdjustSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getHudHeightAdjustSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHudHeightAdjustSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getHudLightSdjustSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getHudLightSdjustSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHudLightSdjustSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getHudParamsStateSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHudParamsStateSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getHudShowParamsSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getHudShowParamsSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHudShowParamsSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getIndoorLightSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getIndoorLightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getIndoorLightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getInstrumentContentSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getInstrumentContentSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getInstrumentHeightSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getInstrumentHeightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getInstrumentHeightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getInstrumentThemeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getInstrumentThemeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getInstrumentThemeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getIntelligentAcAreaSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getIntelligentAcAreaSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getIntelligentAcAreaSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getIntelligentKeepTemSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getIntelligentKeepTemSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getIntelligentKeepTemSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getIntelligentLockSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getIntelligentLockSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentSpeedLimitMapVersionDisplay();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getIntelligentUnlockSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getIntelligentUnlockSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLandAdvanceAtSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getLandAdvanceAtSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getLandAdvanceAtSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getLeaveHomeDelaySupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getLeaveHomeDelaySupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getLeftPlatformConfigs() {
        String str;
        if (1 == this.mForTest) {
            str = "getLeftPlatformConfigs mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getLeftPlatformConfigs();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getLockCarRiseWindowSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getLockCarRiseWindowSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getLockOffSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getLockOffSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getLockOffSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getMainSeatRememberSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getMainSeatRememberSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getMicroSwitchLockWindowSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchLockWindowSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getMicroSwitchUnlockWindowSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchUnlockWindowSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getMoonRoofConfig mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getMoonRoofConfig();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getMotorPretensionerSeatbeltSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getMotorPretensionerSeatbeltSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getMotorPretensionerSeatbeltSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getOBCType mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getOBCType();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getOverspeedLockSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getOverspeedLockSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getPM25PowerSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getPM25PowerSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getPM25PowerSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getPM25SwitchCheckSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getPM25SwitchCheckSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getPM25TimeCkeckSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getPM25TimeCkeckSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getPabSwitchState() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getPabSwitchState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPabSwitchState mForTestValue is " + this.mForTest + " execute return");
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
        String str;
        if (1 == this.mForTest) {
            str = "getPreSaleModelSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getPreSaleModelSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getRainAutoLockWindowSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRainAutoLockWindowSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        String str;
        if (1 == this.mForTest) {
            str = "getRearAcMaxWindLevel mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRearAcMaxWindLevel();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
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
        String str;
        if (1 == this.mForTest) {
            str = "getRearPanelLockTimeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRearPanelLockTimeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getRearViewMirrorFlipSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRearViewMirrorFlipSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getRearviewMirrorSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getRearviewMirrorSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRearviewMirrorSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getRefrigeratorModeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getRefrigeratorModeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRefrigeratorModeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getRemindSpeedSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getRemindSpeedSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRemindSpeedSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getRemoteControlLockWindowSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRemoteControlLockWindowSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getRemoteControlUnlockWindowSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRemoteControlUnlockWindowSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getRemoteKeyStartAcTimeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getRemoteKeyStartAcTimeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getReportContentSetSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getReportContentSetSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getReportContentSetSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getReportItemsSetSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getReportItemsSetSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getReportItemsSetSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getReportVolumeSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getReportVolumeSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getReportVolumeSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getSeatSetSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getSeatSetSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSeatSetSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSeatTurnHeightSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getSeatTurnHeightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHeightSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSeatTurnHorizatialSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getSeatTurnHorizatialSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHorizatialSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSeatbackrestSupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getSeatbackrestSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSeatbackrestSupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSecondSeatRememberSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getSecondSeatRememberSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSecondSeatRememberSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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

    public int getStarTopSwitchSetting() {
        Log.v(TAG, "getStarTopSwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getStarTopSwitchSetting();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getSteerAssisSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getSteerAssisSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSteerAssisSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getSteerPositionAutoReturnSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSteerPositionAutoReturnSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getSteeringWheelSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAFS() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportAFS mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAFS();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAcAutoKeyCanClose() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportAcAutoKeyCanClose mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAcAutoKeyCanClose();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAiCare() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportAiCare mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAiCare();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAiDistanceLight() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportAiDistanceLight mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAiDistanceLight();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAiEmergencyBrake() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportAiEmergencyBrake mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAiEmergencyBrake();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAiMatrixLight() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportAiMatrixLight mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAiMatrixLight();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAutoParking() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportAutoParking mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAutoParking();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportAvh() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAvh();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportAVH mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportBlindSpotAssist() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportBlindSpotAssist mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssist();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportBlindSpotAssistBy418() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportBlindSpotAssistBy418 mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssistBy418();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportDigitalKey() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportDigitalKey mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDigitalKey();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportDimmingCanopyConfiguration() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDimmingCanopyConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportDimmingCanopyConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportDoorOpenWarning() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportDoorOpenWarning mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDoorOpenWarning();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportDoubleConfirm() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDoubleConfirm();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportDoubleConfirm mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportESP() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportESP mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportESP();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportEmergencyLaneKeepAssist() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportEmergencyLaneKeepAssist mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportEmergencyLaneKeepAssist();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportEndToEndNavigation() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportEndToEndNavigation mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportEndToEndNavigation();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getSupportFragrance mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportFragrance();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportFrontCrossingTrafficAlert() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportFrontCrossingTrafficAlert mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficAlert();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportFrontCrossingTrafficBrake() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportFrontCrossingTrafficBrake mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficBrake();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportHighTemperatureSterilize() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportHighTemperatureSterilize mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportHighTemperatureSterilize();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportHighwayNavigation() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportHighwayNavigation mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportHighwayNavigation();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportIntelligentNavigation() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportIntelligentNavigation mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigation();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportIntelligentNavigationBy432() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportIntelligentNavigationBy432 mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigationBy432();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportIntellignetSpeedLimitAlert mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitAlert();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportIntellignetSpeedLimitControl() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportIntellignetSpeedLimitControl mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitControl();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportLampSingnal() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportLampSingnal mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportLampSingnal();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportLaneChangeAssist() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportLaneChangeAssist mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportLaneChangeAssist();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportLaneDepartureStop() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportLaneDepartureStop mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureStop();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportLaneDepartureWarning() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportLaneDepartureWarning mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureWarning();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportLaneKeep() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportLaneKeep mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportLaneKeep();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getSupportMistakeGas mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportMistakeGas();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportNegativeIon() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportNegativeIon mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportNegativeIon();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportPM25Mode() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportPM25Mode mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportPM25Mode();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportPanoramaImage() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportPanoramaImage mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportPanoramaImage();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportPredictCrashAlertMode() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportPredictCrashAlertMode mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportPredictCrashAlertMode();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAc() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAc mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAc();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAcAutoControl() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAcAutoControl mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcAutoControl();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAcCompressor() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAcCompressor mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcCompressor();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAcKeyLock() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAcKeyLock mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcKeyLock();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAcSwitch() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAcSwitch mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcSwitch();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAcTempertureControl() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAcTempertureControl mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcTempertureControl();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAcWindLevelControl() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAcWindLevelControl mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindLevelControl();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearAcWindModeControl() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearAcWindModeControl mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindModeControl();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearCollisionWarning() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearCollisionWarning mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearCollisionWarning();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearControlPanelSign() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearControlPanelSign();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportRearControlPanelSign mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportRearCrossBrake() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearCrossBrake mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearCrossBrake();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportRearTrafficCrossingAlert() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRearTrafficCrossingAlert mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearTrafficCrossingAlert();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportRemenberPowerOff mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRemenberPowerOff();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportReverseRadar() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportReverseRadar mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportReverseRadar();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
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
        String str;
        if (1 == this.mForTest) {
            str = "getSupportSeatMassage mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportShakeAlert() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportShakeAlert mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlert();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getSupportShowCarModel mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportShowCarModel();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportSingleDoubleTempArea() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportSingleDoubleTempArea mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportSingleDoubleTempArea();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
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

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportSteeringWheelWarm mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportSteeringWheelWarm();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportTrafficLightAttention() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportTrafficLightAttention mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficLightAttention();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportTrafficSignRecogniton() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportTrafficSignRecogniton mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignRecogniton();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportTrafficSignsRecognition() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportTrafficSignsRecognition mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignsRecognition();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getSupportTsrFlow() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportTsrFlow();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportTsrFlow mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportUrbanNavigation() {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportUrbanNavigation mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportUrbanNavigation();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTailgateTypeMarking() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getTailgateTypeMarking();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTailgateTypeMarking mForTestValue is " + this.mForTest + " execute return");
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
        String str;
        if (1 == this.mForTest) {
            str = "getTrayControlSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getTrayControlSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTrunkConfigutarion() {
        Log.v(TAG, "getTrunkConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTrunkConfigutarion();
    }

    public int getTsrLitSpdChgSwitchState() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getTsrLitSpdChgSwitchState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTsrLitSpdChgSwitchState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getUiStyle() {
        Log.v(TAG, "getUiStyle is execute UI_STYLE_PROP_KEY is persist.sys.ui_style");
        String str = SystemProperties.get(BYDAutoSysPropertiesDevice.UI_STYLE);
        Log.v(TAG, "getUiStyle uiStyle is " + str);
        if (str != null && str != "") {
            return Integer.parseInt(str);
        }
        Log.v(TAG, " uiStyle is null");
        return -1;
    }

    public int getUsherLightSupport() {
        String str;
        if (1 == this.mForTest) {
            str = "getUsherLightSupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getUsherLightSupport();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "getWindowAntiPinchConfig mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getWindowAntiPinchConfig();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "resetAmbientSettings mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().resetAmbientSettings(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "resetAutoPurificationFactoryMode mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().resetAutoPurificationFactoryMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "resetBackAndLeaveDelaySupport mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().resetBackAndLeaveDelaySupport(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetDayLight(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "resetDayLight mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().resetDayLight(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "resetDoorAndIndoor mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().resetDoorAndIndoor(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetHeadlamp(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "resetHeadlamp mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().resetHeadlamp(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
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
        String str;
        if (1 == this.mForTest) {
            str = "setAcWarmFactoryMode mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().setAcWarmFactoryMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
    }
}
