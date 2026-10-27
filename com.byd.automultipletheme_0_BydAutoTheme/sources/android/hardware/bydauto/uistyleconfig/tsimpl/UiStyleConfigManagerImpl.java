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
import android.util.SparseArray;

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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
    }

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

    public void registerConnectListener(BYDAutoStudyListener bYDAutoStudyListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe study listener " + bYDAutoStudyListener);
        if (bYDAutoStudyListener == null) {
            Log.d(TAG, "listener is null");
        } else {
            registerConnectListener(this.mContext, bYDAutoStudyListener);
        }
    }

    public int getPreSaleModelSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPreSaleModelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPreSaleModelSupport();
    }

    public int getCarNum() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCarNum mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCarNum();
    }

    @Deprecated
    public int getEnergyStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEnergyStatus();
    }

    public int getDriveConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveConfig();
    }

    public int getDMType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDMType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDMType();
    }

    @Deprecated
    public int getAcType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAcType();
    }

    public int getSupportRearAc() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAc mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAc();
    }

    public int getSupportRearAcSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcSwitch();
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearAcMaxWindLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearAcMaxWindLevel();
    }

    public int getSupportRearAcKeyLock() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcKeyLock mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcKeyLock();
    }

    public int getSupportRearAcWindModeControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcWindModeControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindModeControl();
    }

    public int getSupportRearAcAutoControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcAutoControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcAutoControl();
    }

    public int getSupportRearAcCompressor() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcCompressor mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcCompressor();
    }

    public int getSupportRearAcTempertureControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcTempertureControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcTempertureControl();
    }

    public int getSupportRearAcWindLevelControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcWindLevelControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindLevelControl();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcFaultNumShownState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAcFaultNumShownState();
    }

    public int getSupportSingleDoubleTempArea() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSingleDoubleTempArea mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSingleDoubleTempArea();
    }

    @Deprecated
    public int getAcWindModeNum() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcWindModeNum mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAcWindModeNum();
    }

    public int getSupportAcAutoKeyCanClose() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAcAutoKeyCanClose mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAcAutoKeyCanClose();
    }

    @Deprecated
    public int getDefrostRearConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDefrostRearConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDefrostRearConfig();
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcWarmTypeOnlineState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAcWarmTypeOnlineState();
    }

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSteeringWheelWarm mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSteeringWheelWarm();
    }

    public int getSupportSeatMassage(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSeatMassage mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(i);
    }

    public int[] getSupportSeatAdjust(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSeatAdjust mForTestValue is 0 execute return");
            return new int[0];
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return new int[0];
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSeatAdjust(i);
    }

    public int getLeftPlatformConfigs() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeftPlatformConfigs mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftPlatformConfigs();
    }

    public int getSupportPM25Mode() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPM25Mode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportPM25Mode();
    }

    public int getSupportNegativeIon() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportNegativeIon mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportNegativeIon();
    }

    public int getSupportHighTemperatureSterilize() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportHighTemperatureSterilize mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportHighTemperatureSterilize();
    }

    public int getSupportFragrance() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFragrance mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportFragrance();
    }

    public int getFragranceInstallState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFragranceType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFragranceInstallState(i);
    }

    public int getCarType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCarType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCarType();
    }

    public int getSupportLampSingnal() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLampSingnal mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportLampSingnal();
    }

    public int getSupportAFS() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAFS mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAFS();
    }

    public int getAutoReportContentSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoReportContentSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAutoReportContentSupport(i);
    }

    public int getHeadlampSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadlampSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadlampSupport();
    }

    public int getHeadlampLevelConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadlampLevelConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadlampLevelConfig();
    }

    public int resetHeadlamp(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetHeadlamp mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().resetHeadlamp(i);
    }

    public int getBackHomeDelaySupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getBackHomeDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getBackHomeDelaySupport();
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeaveHomeDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeaveHomeDelaySupport();
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetBackAndLeaveDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().resetBackAndLeaveDelaySupport(i);
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "backAndLeaveDelayFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().backAndLeaveDelayFactoryMode(i);
    }

    public int getUsherLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getUsherLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getUsherLightSupport();
    }

    public int getDayLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDayLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDayLightSupport();
    }

    public int resetDayLight(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetDayLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().resetDayLight(i);
    }

    @Deprecated
    public int dayLightFactory(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "dayLightFactory mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().dayLightFactory(i);
    }

    public int getIndoorLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIndoorLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIndoorLightSupport();
    }

    public int getDoorLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDoorLightSupport();
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetDoorAndIndoor mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().resetDoorAndIndoor(i);
    }

    public int getAdaptiveHeadlightsSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAdaptiveHeadlightsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAdaptiveHeadlightsSupport();
    }

    public int getSupportReverseRadar() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportReverseRadar mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportReverseRadar();
    }

    public int getIntelligentKeepTemSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentKeepTemSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentKeepTemSupport();
    }

    public int getChargeToaggleGuardSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getChargeToaggleGuardSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getChargeToaggleGuardSupport();
    }

    public int getChargeLimitSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getChargeLimitSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getChargeLimitSupport();
    }

    public int getOBCType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getOBCType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getOBCType();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearViewMirrorFlipSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearViewMirrorFlipSupport();
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoPowerOnStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOnStateSupport();
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoPowerOffSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOffSupport();
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoExternalRearMirrorFollowUpSwitchSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAutoExternalRearMirrorFollowUpSwitchSupport();
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveSeatAutoReturnSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveSeatAutoReturnSupport();
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteerPositionAutoReturnSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSteerPositionAutoReturnSupport();
    }

    public int getBrakePedalModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getBrakePedalModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getBrakePedalModeSupport();
    }

    public int getComfortableParkingSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getComfortableParkingSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingSupport();
    }

    public int getMotorPretensionerSeatbeltSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMotorPretensionerSeatbeltSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMotorPretensionerSeatbeltSupport();
    }

    public int getSteerAssisSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteerAssisSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSteerAssisSupport();
    }

    public int getAutoWiperSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoWiperSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAutoWiperSupport();
    }

    public int getDynamicSuspensionModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDynamicSuspensionModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeSupport();
    }

    public int getLandAdvanceAtSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLandAdvanceAtSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLandAdvanceAtSupport();
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemoteKeyStartAcTimeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRemoteKeyStartAcTimeSupport();
    }

    @Deprecated
    public int getACAutoAirSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getACAutoAirSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getACAutoAirSupport();
    }

    public int getACBTWindSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getACBTWindSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getACBTWindSupport();
    }

    public int getACPauseCycleSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getACPauseCycleSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getACPauseCycleSupport();
    }

    public int getAutoPurificationSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoPurificationSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAutoPurificationSupport();
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetAutoPurificationFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().resetAutoPurificationFactoryMode(i);
    }

    public int getPM25PowerSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM25PowerSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPM25PowerSupport();
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM25SwitchCheckSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPM25SwitchCheckSupport();
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM25TimeCkeckSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPM25TimeCkeckSupport();
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getACTunnelCycleSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getACTunnelCycleSupport();
    }

    public int getHudShowParamsSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudShowParamsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHudShowParamsSupport();
    }

    public int getRearPanelLockTimeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearPanelLockTimeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearPanelLockTimeSupport();
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "setAcWarmFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().setAcWarmFactoryMode(i);
    }

    public int getAcWarmSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcWarmSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAcWarmSupport();
    }

    public int getIntelligentAcAreaSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentAcAreaSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentAcAreaSupport();
    }

    public int getDoorHeightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDoorHeightSupport();
    }

    public int getMoonRoofConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMoonRoofConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMoonRoofConfig();
    }

    public int getSupportIntelligentNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntelligentNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigation();
    }

    public int getSupportIntelligentNavigationBy432() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntelligentNavigationBy432 mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigationBy432();
    }

    public int getSupportHighwayNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportHighwayNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportHighwayNavigation();
    }

    public int getSupportUrbanNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportUrbanNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportUrbanNavigation();
    }

    public int getSupportEndToEndNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportEndToEndNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportEndToEndNavigation();
    }

    public int getSupportLaneChangeAssist() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneChangeAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportLaneChangeAssist();
    }

    public int getSupportLaneDepartureWarning() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneDepartureWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureWarning();
    }

    public int getSupportLaneKeep() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneKeep mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportLaneKeep();
    }

    public int getSupportLaneDepartureStop() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneDepartureStop mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureStop();
    }

    public int getSupportEmergencyLaneKeepAssist() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportEmergencyLaneKeepAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportEmergencyLaneKeepAssist();
    }

    public int getSupportTrafficSignRecogniton() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportTrafficSignRecogniton mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignRecogniton();
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntellignetSpeedLimitAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitAlert();
    }

    public int getSupportIntellignetSpeedLimitControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntellignetSpeedLimitControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitControl();
    }

    public int getSupportTrafficLightAttention() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportTrafficLightAttention mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficLightAttention();
    }

    public int getSupportDoorOpenWarning() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportDoorOpenWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportDoorOpenWarning();
    }

    public int getSupportRearCollisionWarning() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearCollisionWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearCollisionWarning();
    }

    public int getSupportRearTrafficCrossingAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearTrafficCrossingAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearTrafficCrossingAlert();
    }

    public int getSupportFrontCrossingTrafficAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFrontCrossingTrafficAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficAlert();
    }

    public int getSupportFrontCrossingTrafficBrake() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFrontCrossingTrafficBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficBrake();
    }

    public int getSupportShakeAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportShakeAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlert();
    }

    public int getSupportTrafficSignsRecognition() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportTrafficSignsRecognition mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignsRecognition();
    }

    public int getSupportPredictCrashAlertMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPredictCrashAlertMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportPredictCrashAlertMode();
    }

    public int getSupportAiEmergencyBrake() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiEmergencyBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAiEmergencyBrake();
    }

    public int getSupportAiCare() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiCare mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAiCare();
    }

    public int getSupportMistakeGas() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportMistakeGas mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportMistakeGas();
    }

    public int getSupportAutoParking() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAutoParking mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAutoParking();
    }

    public int getSupportBlindSpotAssist() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportBlindSpotAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssist();
    }

    public int getSupportBlindSpotAssistBy418() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportBlindSpotAssistBy418 mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssistBy418();
    }

    public int getSupportRearCrossBrake() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearCrossBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearCrossBrake();
    }

    public int getSupportAiDistanceLight() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiDistanceLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAiDistanceLight();
    }

    public int getSupportAiMatrixLight() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiMatrixLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAiMatrixLight();
    }

    public int getSupportPanoramaImage() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPanoramaImage mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportPanoramaImage();
    }

    public int getSupportESP() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportESP mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportESP();
    }

    public int getSupportDigitalKey() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportDigitalKey mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportDigitalKey();
    }

    public int getSupportShowCarModel() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportShowCarModel mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportShowCarModel();
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemoteControlLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRemoteControlLockWindowSupport();
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemoteControlUnlockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRemoteControlUnlockWindowSupport();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMicroSwitchLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchLockWindowSupport();
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMicroSwitchUnlockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchUnlockWindowSupport();
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLockCarRiseWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLockCarRiseWindowSupport();
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getOverspeedLockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getOverspeedLockSupport();
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRainAutoLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRainAutoLockWindowSupport();
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorknobAutoShrinkSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDoorknobAutoShrinkSupport();
    }

    @Deprecated
    public int getLockOffSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLockOffSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLockOffSupport();
    }

    public int getDigtalKeySupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDigtalKeySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDigtalKeySupport();
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentUnlockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentUnlockSupport();
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentLockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentLockSupport();
    }

    public int getAppUseLimitSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAppUseLimitSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAppUseLimitSupport();
    }

    public int getRemindSpeedSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemindSpeedSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRemindSpeedSupport();
    }

    public int getReportVolumeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getReportVolumeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getReportVolumeSupport();
    }

    public int getEngineSoundSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getEngineSoundSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEngineSoundSupport();
    }

    public int getCarTipsSoundSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCarTipsSoundSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCarTipsSoundSupport();
    }

    public int getReportContentSetSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getReportContentSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getReportContentSetSupport();
    }

    public int getReportItemsSetSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getReportItemsSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getReportItemsSetSupport(i);
    }

    public int getSeatSetSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSeatSetSupport(i);
    }

    public int getSteeringWheelSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteeringWheelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelSupport();
    }

    public int getRearviewMirrorSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearviewMirrorSupport();
    }

    public int getSeatTurnHorizatialSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatTurnHorizatialSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHorizatialSupport(i);
    }

    public int getSeatTurnHeightSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatTurnHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHeightSupport(i);
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadrestTurnHorizatialSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHorizatialSupport(i);
    }

    public int getSeatbackrestSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatbackrestSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSeatbackrestSupport(i);
    }

    public int getHeadrestTurnHeightSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadrestTurnHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHeightSupport(i);
    }

    public int getRefrigeratorModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRefrigeratorModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRefrigeratorModeSupport();
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRemenberPowerOff mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRemenberPowerOff();
    }

    @Deprecated
    public int getDisinfSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDisinfSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDisinfSupport();
    }

    @Deprecated
    public int getTrayControlSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getTrayControlSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTrayControlSupport();
    }

    public int getCushionSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCushionSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
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

    public int getCaliperSplintSupport(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCaliperSplintSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCaliperSplintSupport(i);
    }

    public int getAmbientLightColorSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientLightColorSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
    }

    public int getAmbientLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAmbientLightSupport();
    }

    public int getAmbientAreaSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientAreaSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAmbientAreaSupport();
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientMusicModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAmbientMusicModeSupport();
    }

    public int getAmbientColorsSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientColorsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAmbientColorsSupport();
    }

    public int resetAmbientSettings(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetAmbientSettings mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().resetAmbientSettings(i);
    }

    @Deprecated
    public int ambientFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "ambientFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().ambientFactoryMode(i);
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudParamsStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHudParamsStateSupport();
    }

    public int getHudHeightAdjustSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudHeightAdjustSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHudHeightAdjustSupport();
    }

    public int getHudLightSdjustSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudLightSdjustSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHudLightSdjustSupport();
    }

    public int getInstrumentThemeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentThemeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getInstrumentThemeSupport();
    }

    public int getInstrumentHeightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getInstrumentHeightSupport();
    }

    public int getInstrumentContentSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentContentSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getInstrumentContentSupport();
    }

    public int getDischargeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDischargeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDischargeSupport();
    }

    public int getDriveDistanceShowStandardSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveDistanceShowStandardSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowStandardSupport();
    }

    public int getEPedalStateSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPedalStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEPedalStateSupport();
    }

    public int getDriveDistanceShowModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveDistanceShowModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowModeSupport();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMainSeatRememberSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainSeatRememberSupport();
    }

    public int getSecondSeatRememberSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSecondSeatRememberSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSecondSeatRememberSupport();
    }

    public int getGuestLeftSeatRememeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getGuestLeftSeatRememeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getGuestLeftSeatRememeSupport();
    }

    public int getGuestRightSeatRememeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getGuestRightSeatRememeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getGuestRightSeatRememeSupport();
    }

    public int getElectroniChildLockConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getElectroniChildLockConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectroniChildLockConfig();
    }

    public int getWindowAntiPinchConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowAntiPinchConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getWindowAntiPinchConfig();
    }

    public int getComfortableSpeedUp() {
        Log.v(TAG, "getComfortableSpeedUp is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getComfortableSpeedUp mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedUp();
    }

    public int getComfortableSpeedDown() {
        Log.v(TAG, "getComfortableSpeedDown is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getComfortableSpeedDown mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedDown();
    }

    public int getCvcConfig() {
        Log.v(TAG, "getCvcConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCvcConfig();
    }

    public int getDiSusShowStateByCan() {
        Log.v(TAG, "getDiSusShowStateByCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCan();
    }

    public int getDiSusShowStateByCanFd() {
        Log.v(TAG, "getDiSusShowStateByCanFd is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCanFd();
    }

    public int getWarningAuxiliarySwitchSetting() {
        Log.v(TAG, "getWarningAuxiliarySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getWarningAuxiliarySwitchSetting();
    }

    public int getWelcomeCeremonySwitchSetting() {
        Log.v(TAG, "getWelcomeCeremonySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getWelcomeCeremonySwitchSetting();
    }

    public int getAmbientModeSwitchSettings() {
        Log.v(TAG, "getAmbientModeSwitchSettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAmbientModeSwitchSettings();
    }

    public int getStarTopSwitchSetting() {
        Log.v(TAG, "getStarTopSwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getStarTopSwitchSetting();
    }

    public int getComfortableParkingBy37F() {
        Log.v(TAG, "getComfortableParkingBy37F is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingBy37F();
    }

    public int getLowSpeedEmergencyBrakingConfig() {
        Log.v(TAG, "getLowSpeedEmergencyBrakingConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLowSpeedEmergencyBrakingConfig();
    }

    public int getSideDistanceWarningConfig() {
        Log.v(TAG, "getSideDistanceWarningConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSideDistanceWarningConfig();
    }

    public int getV2xConfig() {
        Log.v(TAG, "getV2xConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getV2xConfig();
    }

    public int getTrailerModeConfig() {
        Log.v(TAG, " getTrailerModeConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTrailerModeConfig();
    }

    public int getFoldDownTheSecondRowOfSeatsConfig() {
        Log.v(TAG, "getFoldDownTheSecondRowOfSeatsConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFoldDownTheSecondRowOfSeatsConfig();
    }

    public int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat() {
        Log.v(TAG, "getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat();
    }

    public int getOneButtonDownSwitchDisplayForTheThirdRowOfSeats() {
        Log.v(TAG, "getOneButtonDownSwitchDisplayForTheThirdRowOfSeats is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownSwitchDisplayForTheThirdRowOfSeats();
    }

    public int getAllRearSeatOneButtonDownSwitchDisplayConfig() {
        Log.v(TAG, "getAllRearSeatOneButtonDownSwitchDisplayConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAllRearSeatOneButtonDownSwitchDisplayConfig();
    }

    public int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getOneButtonDownConfigurationOfTheThirdRowRightRearSeat() {
        Log.v(TAG, "getOneButtonDownConfigurationOfTheThirdRowRightRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowRightRearSeat();
    }

    public int getIntelligentSpeedLimitMapVersionDisplay() {
        Log.v(TAG, "getIntelligentSpeedLimitMapVersionDisplay is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentSpeedLimitMapVersionDisplay();
    }

    public int getDriverSeatHotStoneMassageConfigurationByEcu() {
        Log.v(TAG, "getDriverSeatHotStoneMassageConfigurationByEcu is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByEcu();
    }

    public int getCameraEerRomDeviceAddress() {
        Log.v(TAG, "getCameraEerRomDeviceAddress is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraEerRomDeviceAddress();
    }

    public int getCameraSupplierAndTestBoardInfo() {
        Log.v(TAG, "getCameraSupplierAndTestBoardInfo is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraSupplierAndTestBoardInfo();
    }

    public int getCameraVideoResolution() {
        Log.v(TAG, "getCameraVideoResolution is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraVideoResolution();
    }

    public int getCameraVideoFrameRate() {
        Log.v(TAG, "getCameraVideoFrameRate is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFrameRate();
    }

    public int getCameraVideoFormat() {
        Log.v(TAG, "getCameraVideoFormat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFormat();
    }

    public int getCameraLensId() {
        Log.v(TAG, "getCameraLensId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraLensId();
    }

    public int getCameraSerializerId() {
        Log.v(TAG, "getCameraSerializerId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraSerializerId();
    }

    public int getCameraIspId() {
        Log.v(TAG, "getCameraIspId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCameraIspId();
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
                    if (i != 557) {
                        if (i != 558) {
                            if (i != 566) {
                                if (i != 567) {
                                    if (i != 1154) {
                                        if (i != 1155) {
                                            if (i != 1158) {
                                                if (i != 1159) {
                                                    if (i != 2788) {
                                                        if (i != 2789) {
                                                            if (i != 3173) {
                                                                if (i == 3174) {
                                                                    bYDAutoEventValue.intValue = getSupportEndToEndNavigation();
                                                                    sparseArray.put(3174, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                } else {
                                                                    switch (i) {
                                                                        case 541:
                                                                            bYDAutoEventValue.intValue = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                                                            sparseArray.put(541, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 560:
                                                                            bYDAutoEventValue.intValue = getRestModeConfiguration();
                                                                            sparseArray.put(560, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 569:
                                                                            bYDAutoEventValue.intValue = getMainDriveElectricLegSupportAdjustmentByGCtrl();
                                                                            sparseArray.put(569, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 571:
                                                                            bYDAutoEventValue.intValue = getRearLeftBackrestAdjustmentConfigurationByECU();
                                                                            sparseArray.put(571, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 573:
                                                                            bYDAutoEventValue.intValue = getRearLeftLevelAdjustmentConfigurationByECU();
                                                                            sparseArray.put(573, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 579:
                                                                            bYDAutoEventValue.intValue = getRearLeftLegSupportAdjustmentConfigurationByECU();
                                                                            sparseArray.put(579, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 585:
                                                                            bYDAutoEventValue.intValue = getPassengerSeatElectricAdjustmentConfigurationByECU();
                                                                            sparseArray.put(585, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 590:
                                                                            bYDAutoEventValue.intValue = getRearRightBackrestAdjustmentConfigurationByECU();
                                                                            sparseArray.put(590, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 592:
                                                                            bYDAutoEventValue.intValue = getRearRightLevelAdjustmentConfigurationByECU();
                                                                            sparseArray.put(592, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 598:
                                                                            bYDAutoEventValue.intValue = getRearRightLegSupportAdjustmentConfigurationByECU();
                                                                            sparseArray.put(598, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 953:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getDoorHeightSupport();
                                                                            sparseArray.put(953, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 987:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getElectroniChildLockConfig();
                                                                            sparseArray.put(987, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1066:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(1);
                                                                            sparseArray.put(1066, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1069:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(2);
                                                                            sparseArray.put(1069, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1146:
                                                                            bYDAutoEventValue.intValue = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                                                            sparseArray.put(1146, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1255:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getAmbientMusicModeSupport();
                                                                            sparseArray.put(1255, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1268:
                                                                            bYDAutoEventValue.intValue = getPassengerSeatElectricAdjustmentConfigurationByECU();
                                                                            sparseArray.put(1268, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1285:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(3);
                                                                            sparseArray.put(1285, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1296:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(4);
                                                                            sparseArray.put(1296, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1705:
                                                                            bYDAutoEventValue.intValue = getComfortableSpeedUp();
                                                                            sparseArray.put(1705, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1722:
                                                                            bYDAutoEventValue.intValue = getRefrigeratorModeSupport();
                                                                            sparseArray.put(1722, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 1732:
                                                                            bYDAutoEventValue.intValue = getElectricFrontHatchConfiguration();
                                                                            sparseArray.put(1732, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2005:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getRearviewMirrorSupport();
                                                                            sparseArray.put(2005, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2020:
                                                                            bYDAutoEventValue.intValue = getCarPlanSupport();
                                                                            sparseArray.put(2020, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2089:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getWindowAntiPinchConfig();
                                                                            sparseArray.put(2089, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2553:
                                                                            bYDAutoEventValue.intValue = getIntelligentKeepTemSupport();
                                                                            sparseArray.put(2553, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2575:
                                                                            bYDAutoEventValue.intValue = getDischargeSupport();
                                                                            sparseArray.put(2575, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2587:
                                                                            bYDAutoEventValue.intValue = getChargeToaggleGuardSupport();
                                                                            sparseArray.put(2587, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2609:
                                                                            bYDAutoEventValue.intValue = getOBCType();
                                                                            sparseArray.put(2609, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2660:
                                                                            bYDAutoEventValue.intValue = getSupportSmartChargingPHEV();
                                                                            sparseArray.put(2660, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2671:
                                                                            bYDAutoEventValue.intValue = getChargeLimitSupport();
                                                                            sparseArray.put(2671, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2674:
                                                                            bYDAutoEventValue.intValue = getNoSenseChargeSupport();
                                                                            sparseArray.put(2674, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 2803:
                                                                            bYDAutoEventValue.intValue = getDayLightSupport();
                                                                            sparseArray.put(2803, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3150:
                                                                            bYDAutoEventValue.intValue = getSupportBlindSpotAssist();
                                                                            sparseArray.put(3150, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3160:
                                                                            bYDAutoEventValue.intValue = getComfortableParkingSupport();
                                                                            sparseArray.put(3160, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3163:
                                                                            bYDAutoEventValue.intValue = getBrakePedalModeSupport();
                                                                            sparseArray.put(3163, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3167:
                                                                            bYDAutoEventValue.intValue = getSupportDoorOpenWarning();
                                                                            sparseArray.put(3167, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3180:
                                                                            bYDAutoEventValue.intValue = getSupportEmergencyLaneKeepAssist();
                                                                            sparseArray.put(3180, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3192:
                                                                            bYDAutoEventValue.intValue = getSupportFrontCrossingTrafficAlert();
                                                                            sparseArray.put(3192, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3195:
                                                                            bYDAutoEventValue.intValue = getSupportFrontCrossingTrafficBrake();
                                                                            sparseArray.put(3195, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3199:
                                                                            bYDAutoEventValue.intValue = getSupportPredictCrashAlertMode();
                                                                            sparseArray.put(3199, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3218:
                                                                            bYDAutoEventValue.intValue = getSupportESP();
                                                                            sparseArray.put(3218, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3225:
                                                                            bYDAutoEventValue.intValue = getSupportIntelligentNavigation();
                                                                            sparseArray.put(3225, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3229:
                                                                            bYDAutoEventValue.intValue = getSupportHighwayNavigation();
                                                                            sparseArray.put(3229, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3234:
                                                                            bYDAutoEventValue.intValue = getSupportLaneChangeAssist();
                                                                            sparseArray.put(3234, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3237:
                                                                            bYDAutoEventValue.intValue = getSupportIntellignetSpeedLimitControl();
                                                                            sparseArray.put(3237, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3253:
                                                                            bYDAutoEventValue.intValue = getSupportShakeAlert();
                                                                            sparseArray.put(3253, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3269:
                                                                            bYDAutoEventValue.intValue = getSupportRearCollisionWarning();
                                                                            sparseArray.put(3269, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3276:
                                                                            bYDAutoEventValue.intValue = getSupportRearTrafficCrossingAlert();
                                                                            sparseArray.put(3276, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3278:
                                                                            bYDAutoEventValue.intValue = getSupportRearCrossBrake();
                                                                            sparseArray.put(3278, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3287:
                                                                            bYDAutoEventValue.intValue = getSupportIntellignetSpeedLimitAlert();
                                                                            sparseArray.put(3287, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3300:
                                                                            bYDAutoEventValue.intValue = getSupportTrafficLightAttention();
                                                                            sparseArray.put(3300, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3307:
                                                                            bYDAutoEventValue.intValue = getSupportUrbanNavigation();
                                                                            sparseArray.put(3307, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3315:
                                                                            bYDAutoEventValue.intValue = getSupportAcAutoKeyCanClose();
                                                                            sparseArray.put(3315, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3344:
                                                                            bYDAutoEventValue.intValue = getSupportFRElectricAirOutlet();
                                                                            sparseArray.put(3344, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3348:
                                                                            bYDAutoEventValue.intValue = getSupportFragrance();
                                                                            sparseArray.put(3348, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3465:
                                                                            bYDAutoEventValue.intValue = getSupportRearAcKeyLock();
                                                                            sparseArray.put(3465, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 3520:
                                                                            bYDAutoEventValue.intValue = getIntelligentAcAreaSupport();
                                                                            sparseArray.put(3520, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 4193:
                                                                            bYDAutoEventValue.intValue = getDriveTimeInterfaceStatusForSingleTrip();
                                                                            sparseArray.put(4193, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 4537:
                                                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getAmbientColorsSupport();
                                                                            sparseArray.put(4537, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        case 4553:
                                                                            bYDAutoEventValue.intValue = getSupportAutoParking();
                                                                            sparseArray.put(4553, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case 3208:
                                                                                    bYDAutoEventValue.intValue = getSupportAiEmergencyBrake();
                                                                                    sparseArray.put(3208, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 3209:
                                                                                    bYDAutoEventValue.intValue = getHasPredictCrashAlertMode();
                                                                                    sparseArray.put(3209, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 3210:
                                                                                    bYDAutoEventValue.intValue = getSupportAiDistanceLight();
                                                                                    sparseArray.put(3210, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 3211:
                                                                                    bYDAutoEventValue.intValue = getSupportLaneDepartureWarning();
                                                                                    sparseArray.put(3211, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 3212:
                                                                                    bYDAutoEventValue.intValue = getSupportLaneKeep();
                                                                                    sparseArray.put(3212, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 3213:
                                                                                    bYDAutoEventValue.intValue = getSupportTrafficSignsRecognition();
                                                                                    sparseArray.put(3213, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 3214:
                                                                                    bYDAutoEventValue.intValue = getSupportIntelligentNavigation();
                                                                                    sparseArray.put(3214, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 3677:
                                                                                            bYDAutoEventValue.intValue = getTheLast50KMAveragePowerConsumptionInterfaceState();
                                                                                            sparseArray.put(3677, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3678:
                                                                                            bYDAutoEventValue.intValue = getTheLast50KMAverageEnergyConsumptionInterfaceState();
                                                                                            sparseArray.put(3678, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3679:
                                                                                            bYDAutoEventValue.intValue = getTheLast50KMAverageFuelConsumptionInterfaceState();
                                                                                            sparseArray.put(3679, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3680:
                                                                                            bYDAutoEventValue.intValue = getCumulativeAveragePowerConsumptionInterfaceStatus();
                                                                                            sparseArray.put(3680, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3681:
                                                                                            bYDAutoEventValue.intValue = getCumulativeAverageEnergyConsumptionInterfaceStatus();
                                                                                            sparseArray.put(3681, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3682:
                                                                                            bYDAutoEventValue.intValue = getCumulativeAverageFuelConsumptionInterfaceStatus();
                                                                                            sparseArray.put(3682, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3683:
                                                                                            bYDAutoEventValue.intValue = getAverageSpeedInterfaceStatus();
                                                                                            sparseArray.put(3683, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3684:
                                                                                            bYDAutoEventValue.intValue = getTirePressureInterfaceStatus();
                                                                                            sparseArray.put(3684, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3685:
                                                                                            bYDAutoEventValue.intValue = getEnergyFlowDiagramInterfaceStatus();
                                                                                            sparseArray.put(3685, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3686:
                                                                                            bYDAutoEventValue.intValue = getExternalChargingPowerInterfaceStatus();
                                                                                            sparseArray.put(3686, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3687:
                                                                                            bYDAutoEventValue.intValue = getFuelConsumptionInterfaceStatus();
                                                                                            sparseArray.put(3687, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3688:
                                                                                            bYDAutoEventValue.intValue = getDrivingRangeInterfaceStatusForFuel();
                                                                                            sparseArray.put(3688, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3689:
                                                                                            bYDAutoEventValue.intValue = getInstantaneousFuelConsumptionInterfaceStatus();
                                                                                            sparseArray.put(3689, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 3690:
                                                                                            bYDAutoEventValue.intValue = getCarType();
                                                                                            sparseArray.put(3690, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        default:
                                                                                            switch (i) {
                                                                                                case 4556:
                                                                                                    bYDAutoEventValue.intValue = getRuningCloseDoorSupport();
                                                                                                    sparseArray.put(4556, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                    break;
                                                                                                case 4557:
                                                                                                    bYDAutoEventValue.intValue = getRuningCloseDoorSupport();
                                                                                                    sparseArray.put(4557, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                    break;
                                                                                                case 4558:
                                                                                                    bYDAutoEventValue.intValue = getRuningCloseDoorSupport();
                                                                                                    sparseArray.put(4558, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                    break;
                                                                                                case 4559:
                                                                                                    bYDAutoEventValue.intValue = getRuningCloseDoorSupport();
                                                                                                    sparseArray.put(4559, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                                    break;
                                                                                                default:
                                                                                                    Log.v(TAG, "case fail ! please check out");
                                                                                                    break;
                                                                                            }
                                                                                            break;
                                                                                    }
                                                                                    break;
                                                                            }
                                                                            break;
                                                                    }
                                                                }
                                                            } else {
                                                                bYDAutoEventValue.intValue = getSupportLaneDepartureStop();
                                                                sparseArray.put(3173, Integer.valueOf(bYDAutoEventValue.intValue));
                                                            }
                                                        } else {
                                                            bYDAutoEventValue.intValue = getSupportAiMatrixLight();
                                                            sparseArray.put(2789, Integer.valueOf(bYDAutoEventValue.intValue));
                                                        }
                                                    } else {
                                                        bYDAutoEventValue.intValue = getFrontLightDimmingCtrlConfigBy3ac();
                                                        sparseArray.put(2788, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    }
                                                } else {
                                                    bYDAutoEventValue.intValue = getAmbientLightColorSupport();
                                                    sparseArray.put(1159, Integer.valueOf(bYDAutoEventValue.intValue));
                                                }
                                            } else {
                                                bYDAutoEventValue.intValue = getAmbientLightSupport();
                                                sparseArray.put(1158, Integer.valueOf(bYDAutoEventValue.intValue));
                                            }
                                        } else {
                                            bYDAutoEventValue.intValue = mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
                                            sparseArray.put(1155, Integer.valueOf(bYDAutoEventValue.intValue));
                                        }
                                    } else {
                                        bYDAutoEventValue.intValue = getAmbientAreaSupport();
                                        sparseArray.put(1154, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                } else {
                                    bYDAutoEventValue.intValue = getMainDriveElectricHeadrestAdjustmentByGCtrl();
                                    sparseArray.put(567, Integer.valueOf(bYDAutoEventValue.intValue));
                                }
                            } else {
                                bYDAutoEventValue.intValue = getMainDriverSeatAdjustmentConfigurationByGCtrl();
                                sparseArray.put(566, Integer.valueOf(bYDAutoEventValue.intValue));
                            }
                        } else {
                            bYDAutoEventValue.intValue = getLeftRearMassageChairConfigurationForECU();
                            sparseArray.put(558, Integer.valueOf(bYDAutoEventValue.intValue));
                        }
                    } else {
                        bYDAutoEventValue.intValue = getLeftRearMassageChairConfigurationForECU();
                        sparseArray.put(557, Integer.valueOf(bYDAutoEventValue.intValue));
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

    public int getSteeringWheelHeatingConfiguration() {
        Log.v(TAG, "getSteeringWheelHeatingConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelHeatingConfiguration();
    }

    public int restoreFactorySettings() {
        Log.v(TAG, "restoreFactorySettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().restoreFactorySettings();
    }

    public int getValetParking() {
        Log.v(TAG, "getValetParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getValetParking();
    }

    public int getMemoryParking() {
        Log.v(TAG, "getMemoryParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMemoryParking();
    }

    public int getPassengerSeatMemoryFunctionCan() {
        Log.v(TAG, "getPassengerSeatMemoryFunctionCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCan();
    }

    public int getTirePressureInterfaceStatus() {
        Log.v(TAG, "getTirePressureInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTirePressureInterfaceStatus();
    }

    public int getFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFuelConsumptionInterfaceStatus();
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

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getInstantaneousFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getInstantaneousFuelConsumptionInterfaceStatus();
    }

    public int getExternalChargingPowerInterfaceStatus() {
        Log.v(TAG, "getExternalChargingPowerInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getExternalChargingPowerInterfaceStatus();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        Log.v(TAG, "getEnergyFlowDiagramInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEnergyFlowDiagramInterfaceStatus();
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        Log.v(TAG, "getDrivingRangeInterfaceStatusForFuel is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingRangeInterfaceStatusForFuel();
    }

    public int getAverageSpeedInterfaceStatus() {
        Log.v(TAG, "getAverageSpeedInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAverageSpeedInterfaceStatus();
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        Log.v(TAG, "getDriveTimeInterfaceStatusForSingleTrip is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveTimeInterfaceStatusForSingleTrip();
    }

    public int getRightRearSeatReclineConfiguration() {
        Log.v(TAG, "getRightRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfiguration();
    }

    public int getLeftRearSeatReclineConfiguration() {
        Log.v(TAG, "getLeftRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfiguration();
    }

    public int getRearSeatReclineConfiguration() {
        Log.v(TAG, "getRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
    }

    public int getSupportExtreme() {
        Log.v(TAG, "getSupportExtreme is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportExtreme();
    }

    public int getSupportSmartChargingPHEV() {
        Log.v(TAG, "getSupportSmartChargingPHEV is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSmartChargingPHEV();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        Log.v(TAG, "getMainDriveElectricLegSupportAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        Log.v(TAG, "getMainDriveElectricHeadrestAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        Log.v(TAG, "getPassengerSeatMemoryFunctionCanByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatElectricAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatHeadrestConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl() {
        Log.v(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
    }

    public int getPassengerSeatHeadrestConfigurationByGCtrl() {
        Log.v(TAG, "getPassengerSeatHeadrestConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByGCtrl();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        Log.v(TAG, "getRearLeftMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
    }

    public int getLeftRearSeatReclineConfigurationByECU() {
        Log.v(TAG, "getLeftRearSeatReclineConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfigurationByECU();
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

    public int getLeftRearSeatLegBracket() {
        Log.v(TAG, "getLeftRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
    }

    public int getLeftRearMemoryConfiguration() {
        Log.v(TAG, "getLeftRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        Log.v(TAG, "getRearRightMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
    }

    public int getRightRearSeatReclineConfigurationByEUC() {
        Log.v(TAG, "getRightRearSeatReclineConfigurationByEUC is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
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

    public int getRightRearSeatLegBracket() {
        Log.v(TAG, "getRightRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
    }

    public int getRightRearMemoryConfiguration() {
        Log.v(TAG, "getRightRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
    }

    public int getADASSystemState() {
        Log.v(TAG, "getADASSystemState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getADASSystemState();
    }

    public int getCarPlanSupport() {
        Log.v(TAG, "getCarPlanSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCarPlanSupport();
    }

    public int getNoSenseChargeSupport() {
        Log.v(TAG, "getNoSenseChargeSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getNoSenseChargeSupport();
    }

    public int getElectricFrontHatchConfiguration() {
        Log.v(TAG, "getElectricFrontHatchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
    }

    public int getChargePreheatSupport() {
        Log.v(TAG, "getChargePreheatSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getChargePreheatSupport();
    }

    public int getDMSAssembleStatus() {
        Log.v(TAG, "getDMSAssembleStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDMSAssembleStatus();
    }

    public int getDrivingVoiceBroadcastSwitchConfig() {
        Log.v(TAG, "getDrivingVoiceBroadcastSwitchConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingVoiceBroadcastSwitchConfig();
    }

    public int getRestModeConfiguration() {
        Log.v(TAG, "getRestModeConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRestModeConfiguration();
    }

    public int getLeftRESConfiguration() {
        Log.v(TAG, "getLeftRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRESConfiguration();
    }

    public int getRightRESConfiguration() {
        Log.v(TAG, "getRightRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRESConfiguration();
    }

    public int getHUDModeConfiguration() {
        Log.v(TAG, "getHUDModeConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHUDModeConfiguration();
    }

    public int getIntelligentScreenCastingSwitchConfiguration() {
        Log.v(TAG, "getIntelligentScreenCastingSwitchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentScreenCastingSwitchConfiguration();
    }

    public int getSupportFRElectricAirOutlet() {
        Log.v(TAG, "getSupportFRElectricAirOutlet is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportFRElectricAirOutlet();
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        Log.v(TAG, "getLeftRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
    }

    public int getRightRearMassageChairConfigurationForECU() {
        Log.v(TAG, "getRightRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
    }

    public int getElectronicParkingBrakeConfigutarion() {
        Log.v(TAG, "getElectronicParkingBrakeConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectronicParkingBrakeConfigutarion();
    }

    public int getTrunkConfigutarion() {
        Log.v(TAG, "getTrunkConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTrunkConfigutarion();
    }

    public int getPanoramicSunroofConfigutarion() {
        Log.v(TAG, "getPanoramicSunroofConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPanoramicSunroofConfigutarion();
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

    public int getPassengerSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHotStoneMassageConfigurationByECU();
    }

    public int getLeftRearSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getLeftRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRightRearSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getRightRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRuningCloseDoorSupport() {
        Log.v(TAG, "getRuningCloseDoorSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRuningCloseDoorSupport();
    }

    public int getUiStyle() {
        Log.v(TAG, "getUiStyle is execute UI_STYLE_PROP_KEY is persist.sys.ui_style");
        String str = SystemProperties.get("persist.sys.ui_style");
        Log.v(TAG, "getUiStyle uiStyle is " + str);
        if (str == null || str == "") {
            Log.v(TAG, " uiStyle is null");
            return -1;
        }
        return Integer.parseInt(str);
    }

    public boolean isDynasty() {
        return getUiStyle() == 0;
    }

    public boolean isDenza() {
        return getUiStyle() == 1;
    }

    public boolean isOcean() {
        return getUiStyle() == 2;
    }

    public int getHasPredictCrashAlertMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getHasPredictCrashAlertMode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHasPredictCrashAlertMode();
    }

    public int getAmplifierConfiguration() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getAmplifierConfiguration mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAmplifierConfiguration();
    }

    public int getPabSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getPabSwitchState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPabSwitchState();
    }

    public int getFrontLightDimmingCtrlConfigBy3ac() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontLightDimmingCtrlConfigBy3ac mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ac();
    }

    public int getFrontLightDimmingCtrlConfigBy3ad() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getFrontLightDimmingCtrlConfigBy3ad mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ad();
    }

    public int getTsrLitSpdChgSwitchState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTsrLitSpdChgSwitchState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTsrLitSpdChgSwitchState();
    }

    public int getSupportAvh() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSupportAVH mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportAvh();
    }

    public int getSupportRearControlPanelSign() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSupportRearControlPanelSign mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportRearControlPanelSign();
    }

    public int getSupportDimmingCanopyConfiguration() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSupportDimmingCanopyConfiguration mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportDimmingCanopyConfiguration();
    }

    public int getBsisSupportR() {
        Log.i(TAG, "getBsisSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "BsisSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getBsisSupportR();
    }

    public int getMoisSupportR() {
        Log.i(TAG, "getMoisSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "MoisSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMoisSupportR();
    }

    public int getFeaturesStateR() {
        Log.i(TAG, "getFeaturesStateR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "FeaturesStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFeaturesStateR();
    }

    public int getSupportShakeAlertR() {
        Log.i(TAG, "getSupportShakeAlertR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportShakeAlertR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlertR();
    }

    public int getCpdSupportR() {
        Log.i(TAG, "getCpdSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "CpdSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCpdSupportR();
    }

    public int getSupportSmartChargingPhevPhase2R() {
        Log.i(TAG, "getSupportSmartChargingPhevPhase2R is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportSmartChargingPhevPhase2R mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSmartChargingPhevPhase2R();
    }

    public int getSupportManualModelR() {
        Log.i(TAG, "getSupportManualModelR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportManualModelR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportManualModelR();
    }

    public int getDynamicSuspensionModeByCanR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "DynamicSuspensionModeByCanR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanR();
    }

    public int getDynamicSuspensionModeByCanFdR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanFdR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "DynamicSuspensionModeByCanFdR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanFdR();
    }

    public int getTailgateTypeMarking() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTailgateTypeMarking mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTailgateTypeMarking();
    }

    public int getSupportTsrFlow() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSupportTsrFlow mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportTsrFlow();
    }

    public int getSupportDoubleConfirm() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSupportDoubleConfirm mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportDoubleConfirm();
    }
}
