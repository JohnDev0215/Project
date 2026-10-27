package android.app;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class UiStyleConfig extends AbsBYDAutoDevice {
    public static final int AC_AUTO_KEY_CANNOT_CLOSE = 0;
    public static final int AC_AUTO_KEY_CAN_CLOSE = 1;
    public static final int AC_COMMAND_HAVE = 1;
    public static final int AC_COMMAND_NONE = 0;
    public static final int AC_CONTROL_TYPE_AUTO = 0;
    public static final int AC_CONTROL_TYPE_ELECTRIC = 1;
    public static final int AC_TEMPERATURE_AREA_DOUBLE = 2;
    public static final int AC_TEMPERATURE_AREA_SINGLE = 1;
    public static final int AC_WIND_MODE_COUNT5 = 5;
    public static final int AC_WIND_MODE_COUNT7 = 7;
    public static final int AREA_ALL = 2;
    public static final int AREA_INVALID = 0;
    public static final int AREA_NULL = 1;
    public static final int AREA_OTHER = 3;
    public static final int AUTO_REPORT_0 = 0;
    public static final int AUTO_REPORT_1 = 1;
    public static final int AUTO_REPORT_10 = 10;
    public static final int AUTO_REPORT_2 = 2;
    public static final int AUTO_REPORT_3 = 3;
    public static final int AUTO_REPORT_4 = 4;
    public static final int AUTO_REPORT_5 = 5;
    public static final int AUTO_REPORT_6 = 6;
    public static final int AUTO_REPORT_7 = 7;
    public static final int AUTO_REPORT_8 = 8;
    public static final int AUTO_REPORT_9 = 9;
    public static final int CHARGE_GURAD_NOT_SUPPORT = 1;
    public static final int CHARGE_GURAD_SUPPORT = 0;
    public static final int CHARGE_LIMIT_NOT_SUPPORT = 1;
    public static final int CHARGE_LIMIT_SUPPORT = 2;
    public static final int DISCHARGE_NOT_SUPPORT = 0;
    public static final int DISCHARGE_SUPPORT = 1;
    public static final int DRIVE_CONFIG_EV_1 = 1;
    public static final int DRIVE_CONFIG_EV_2 = 2;
    public static final int DRIVE_CONFIG_EV_3 = 3;
    public static final int DRIVE_CONFIG_EV_4 = 4;
    public static final int DRIVE_CONFIG_HEV_1 = 1;
    public static final int DRIVE_CONFIG_HEV_2 = 2;
    public static final int DRIVE_CONFIG_HEV_3 = 3;
    public static final int DRIVE_CONFIG_HEV_4 = 4;
    public static final int DRIVE_CONFIG_HEV_5 = 5;
    public static final int DRIVE_PLATFORM_DM_TW0_ZERO = 3;
    public static final int DRIVE_PLATFORM_DM_TWO_FIVE = 2;
    public static final int DRIVE_PLATFORM_EV = 1;
    public static final int DRIVE_STANDARD_NOT_SUPPORT = 1;
    public static final int DRIVE_STANDARD_SUPPORT = 0;
    public static final int ELECTRIC_DEFROST_CONFIG1 = 1;
    public static final int ELECTRIC_DEFROST_CONFIG2 = 2;
    public static final int ELECTRIC_DEFROST_CONFIG3 = 3;
    public static final int ENERGY_STATUS_EV_1 = 1;
    public static final int ENERGY_STATUS_EV_2 = 2;
    public static final int ENERGY_STATUS_EV_3 = 3;
    public static final int ENERGY_STATUS_EV_4 = 4;
    public static final int ENERGY_STATUS_EV_5 = 5;
    public static final int ENERGY_STATUS_EV_6 = 6;
    public static final int ENERGY_STATUS_HEV_1 = 1;
    public static final int EPEDAL_NOT_SUPPORT = 1;
    public static final int EPEDAL_SUPPORT = 0;
    public static final int FRAGRANCE_COMMAND_HAVE = 1;
    public static final int FRAGRANCE_COMMAND_NONE = 0;
    public static final int INTELLIGENT_KEEP_TEM_NOT_SUPPORT = 2;
    public static final int INTELLIGENT_KEEP_TEM_SUPPORT = 1;
    public static final int LAMPSIGNAL_SUPPORT = 1;
    public static final int LAMPSIGNAL_UNSUPPORT = 0;
    public static final int LIGHT_INVALID = 0;
    public static final int LIGHT_NULL = 1;
    public static final int LIGHT_OTHER = 3;
    public static final int LIGHT_SIX = 2;
    public static final int MODE_BACK = 0;
    public static final int MODE_LEAVE = 1;
    public static final int OBC_TYPE_ISOLATION_NOT = 0;
    public static final int OBC_TYPE_ISOLATION_ONE = 2;
    public static final int OBC_TYPE_ISOLATION_TWO = 1;
    public static final int PM2P5_AISLE_MODE1 = 1;
    public static final int PM2P5_AISLE_MODE2 = 2;
    public static final int PM2P5_COMMAND_HAVE = 1;
    public static final int PM2P5_COMMAND_NONE = 0;
    public static final int PRESALE_MODEL_NOT_SUPPORT = 0;
    public static final int PRESALE_MODEL_SUPPORT = 1;
    public static final int REAR_AC_WIND_LEVEL_COUNT4 = 4;
    public static final int REAR_AC_WIND_LEVEL_COUNT7 = 7;
    public static final int SEAT_COMMAND_HAVE = 1;
    public static final int SEAT_COMMAND_NONE = 0;
    public static final int SEAT_MASSAGE_DEPUTY = 2;
    public static final int SEAT_MASSAGE_LEFT_REAR = 3;
    public static final int SEAT_MASSAGE_MAIN = 1;
    public static final int SEAT_MASSAGE_RIGHT_REAR = 4;
    public static final int SET_OFF = 0;
    public static final int SET_ON = 1;
    public static final int STATE_INVISIABLE = 0;
    public static final int STATE_INVISIABLE_ALL = 0;
    public static final int STATE_INVISIABLE_ROTATION = 1;
    public static final int STATE_SUPPORT = 1;
    public static final int STATE_UNSUPPORT = 0;
    public static final int STATE_VISIABLE = 1;
    public static final int STATE_VISIABLE_ROTATION = 2;
    public static final int STYLE_DENZA = 1;
    public static final int STYLE_DYNASTY = 0;
    public static final int STYLE_OCEAN = 2;
    public static final int STYLE_UNDEFINED = -1;
    protected static final String TAG = "UiStyleConfig";
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_NOT_YU = 0;
    public static final int TYPE_NULL = 1;
    public static final int TYPE_OTHER = 6;
    public static final int TYPE_SEVEN = 4;
    public static final int TYPE_SINGLE = 3;
    public static final int TYPE_SUPPORT = 2;
    public static final int TYPE_THIRYONE = 5;
    public static final int TYPE_YU = 1;
    public static final String UI_STYLE_PROP_KEY = "persist.sys.ui_style";
    private static UiStyleConfig sUiStyleConfig;
    private UiStyleConfigManagerImpl mUiStyleConfigManagerImpl;

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    UiStyleConfig(Context context) {
        super(context.getApplicationContext());
        this.mUiStyleConfigManagerImpl = UiStyleConfigManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized UiStyleConfig getInstance(Context context) {
        if (sUiStyleConfig == null) {
            sUiStyleConfig = new UiStyleConfig(context.getApplicationContext());
        }
        return sUiStyleConfig;
    }

    public int getPreSaleModelSupport() {
        return this.mUiStyleConfigManagerImpl.getPreSaleModelSupport();
    }

    public int getCarNum() {
        return this.mUiStyleConfigManagerImpl.getCarNum();
    }

    @Deprecated
    public int getEnergyStatus() {
        return this.mUiStyleConfigManagerImpl.getEnergyStatus();
    }

    public int getDriveConfig() {
        return this.mUiStyleConfigManagerImpl.getDriveConfig();
    }

    public int getDMType() {
        return this.mUiStyleConfigManagerImpl.getDMType();
    }

    @Deprecated
    public int getAcType() {
        return this.mUiStyleConfigManagerImpl.getAcType();
    }

    public int getSupportRearAc() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAc();
    }

    public int getSupportRearAcSwitch() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcSwitch();
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        return this.mUiStyleConfigManagerImpl.getRearAcMaxWindLevel();
    }

    public int getSupportRearAcKeyLock() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcKeyLock();
    }

    public int getSupportRearAcWindModeControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcWindModeControl();
    }

    public int getSupportRearAcAutoControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcAutoControl();
    }

    public int getSupportRearAcCompressor() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcCompressor();
    }

    public int getSupportRearAcTempertureControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcTempertureControl();
    }

    public int getSupportRearAcWindLevelControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcWindLevelControl();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        return this.mUiStyleConfigManagerImpl.getAcFaultNumShownState();
    }

    public int getSupportSingleDoubleTempArea() {
        return this.mUiStyleConfigManagerImpl.getSupportSingleDoubleTempArea();
    }

    @Deprecated
    public int getAcWindModeNum() {
        return this.mUiStyleConfigManagerImpl.getAcWindModeNum();
    }

    public int getSupportAcAutoKeyCanClose() {
        return this.mUiStyleConfigManagerImpl.getSupportAcAutoKeyCanClose();
    }

    @Deprecated
    public int getDefrostRearConfig() {
        return this.mUiStyleConfigManagerImpl.getDefrostRearConfig();
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        return this.mUiStyleConfigManagerImpl.getAcWarmTypeOnlineState();
    }

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        return this.mUiStyleConfigManagerImpl.getSupportSteeringWheelWarm();
    }

    public int getSupportSeatMassage(int i) {
        return this.mUiStyleConfigManagerImpl.getSupportSeatMassage(i);
    }

    public int[] getSupportSeatAdjust(int i) {
        return this.mUiStyleConfigManagerImpl.getSupportSeatAdjust(i);
    }

    public int getLeftPlatformConfigs() {
        return this.mUiStyleConfigManagerImpl.getLeftPlatformConfigs();
    }

    public int getSupportPM25Mode() {
        return this.mUiStyleConfigManagerImpl.getSupportPM25Mode();
    }

    public int getSupportNegativeIon() {
        return this.mUiStyleConfigManagerImpl.getSupportNegativeIon();
    }

    public int getSupportHighTemperatureSterilize() {
        return this.mUiStyleConfigManagerImpl.getSupportHighTemperatureSterilize();
    }

    public int getSupportFragrance() {
        return this.mUiStyleConfigManagerImpl.getSupportFragrance();
    }

    public int getSupportFragranceType(int i) {
        return this.mUiStyleConfigManagerImpl.getFragranceInstallState(i);
    }

    public int getCarType() {
        return this.mUiStyleConfigManagerImpl.getCarType();
    }

    public int getSupportLampSingnal() {
        return this.mUiStyleConfigManagerImpl.getSupportLampSingnal();
    }

    @Deprecated
    public int getSupportAFS() {
        return this.mUiStyleConfigManagerImpl.getSupportAFS();
    }

    public int getAutoReportContentSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getAutoReportContentSupport(i);
    }

    public int getHeadlampSupport() {
        return this.mUiStyleConfigManagerImpl.getHeadlampSupport();
    }

    public int resetHeadlamp(int i) {
        return this.mUiStyleConfigManagerImpl.resetHeadlamp(i);
    }

    public int getBackHomeDelaySupport() {
        return this.mUiStyleConfigManagerImpl.getBackHomeDelaySupport();
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        return this.mUiStyleConfigManagerImpl.getLeaveHomeDelaySupport();
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        return this.mUiStyleConfigManagerImpl.resetBackAndLeaveDelaySupport(i);
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.backAndLeaveDelayFactoryMode(i);
    }

    public int getUsherLightSupport() {
        return this.mUiStyleConfigManagerImpl.getUsherLightSupport();
    }

    public int getDayLightSupport() {
        return this.mUiStyleConfigManagerImpl.getDayLightSupport();
    }

    public int resetDayLight(int i) {
        return this.mUiStyleConfigManagerImpl.resetDayLight(i);
    }

    @Deprecated
    public int dayLightFactory(int i) {
        return this.mUiStyleConfigManagerImpl.dayLightFactory(i);
    }

    public int getIndoorLightSupport() {
        return this.mUiStyleConfigManagerImpl.getIndoorLightSupport();
    }

    public int getDoorLightSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorLightSupport();
    }

    public int getSupportReverseRadar() {
        return this.mUiStyleConfigManagerImpl.getSupportReverseRadar();
    }

    public int getIntelligentKeepTemSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentKeepTemSupport();
    }

    public int getChargeToaggleGuardSupport() {
        return this.mUiStyleConfigManagerImpl.getChargeToaggleGuardSupport();
    }

    public int getChargeLimitSupport() {
        return this.mUiStyleConfigManagerImpl.getChargeLimitSupport();
    }

    public int getOBCType() {
        return this.mUiStyleConfigManagerImpl.getOBCType();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        return this.mUiStyleConfigManagerImpl.getRearViewMirrorFlipSupport();
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPowerOnStateSupport();
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPowerOffSupport();
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoExternalRearMirrorFollowUpSwitchSupport();
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveSeatAutoReturnSupport();
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        return this.mUiStyleConfigManagerImpl.getSteerPositionAutoReturnSupport();
    }

    public int getBrakePedalModeSupport() {
        return this.mUiStyleConfigManagerImpl.getBrakePedalModeSupport();
    }

    public int getComfortableParkingSupport() {
        return this.mUiStyleConfigManagerImpl.getComfortableParkingSupport();
    }

    public int getMotorPretensionerSeatbeltSupport() {
        return this.mUiStyleConfigManagerImpl.getMotorPretensionerSeatbeltSupport();
    }

    public int getSteerAssisSupport() {
        return this.mUiStyleConfigManagerImpl.getSteerAssisSupport();
    }

    public int getAutoWiperSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoWiperSupport();
    }

    public int getDynamicSuspensionModeSupport() {
        return this.mUiStyleConfigManagerImpl.getDynamicSuspensionModeSupport();
    }

    public int getLandAdvanceAtSupport() {
        return this.mUiStyleConfigManagerImpl.getLandAdvanceAtSupport();
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        return this.mUiStyleConfigManagerImpl.getRemoteKeyStartAcTimeSupport();
    }

    @Deprecated
    public int getACAutoAirSupport() {
        return this.mUiStyleConfigManagerImpl.getACAutoAirSupport();
    }

    public int getACBTWindSupport() {
        return this.mUiStyleConfigManagerImpl.getACBTWindSupport();
    }

    public int getACPauseCycleSupport() {
        return this.mUiStyleConfigManagerImpl.getACPauseCycleSupport();
    }

    public int getAutoPurificationSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPurificationSupport();
    }

    public int getPM25PowerSupport() {
        return this.mUiStyleConfigManagerImpl.getPM25PowerSupport();
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        return this.mUiStyleConfigManagerImpl.getPM25SwitchCheckSupport();
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        return this.mUiStyleConfigManagerImpl.getPM25TimeCkeckSupport();
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        return this.mUiStyleConfigManagerImpl.getACTunnelCycleSupport();
    }

    public int getHudShowParamsSupport() {
        return this.mUiStyleConfigManagerImpl.getHudShowParamsSupport();
    }

    public int getRearPanelLockTimeSupport() {
        return this.mUiStyleConfigManagerImpl.getRearPanelLockTimeSupport();
    }

    public int getAcWarmSupport() {
        return this.mUiStyleConfigManagerImpl.getAcWarmSupport();
    }

    public int getIntelligentAcAreaSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentAcAreaSupport();
    }

    public int getDoorHeightSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorHeightSupport();
    }

    public int getSupportIntelligentNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportIntelligentNavigation();
    }

    public int getSupportIntelligentNavigationBy432() {
        return this.mUiStyleConfigManagerImpl.getSupportIntelligentNavigationBy432();
    }

    public int getSupportShakeAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportShakeAlert();
    }

    public int getSupportTrafficSignsRecognition() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficSignsRecognition();
    }

    public int getSupportPredictCrashAlertMode() {
        return this.mUiStyleConfigManagerImpl.getSupportPredictCrashAlertMode();
    }

    public int getSupportAiEmergencyBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportAiEmergencyBrake();
    }

    public int getSupportAiCare() {
        return this.mUiStyleConfigManagerImpl.getSupportAiCare();
    }

    public int getSupportMistakeGas() {
        return this.mUiStyleConfigManagerImpl.getSupportMistakeGas();
    }

    public int getSupportAutoParking() {
        return this.mUiStyleConfigManagerImpl.getSupportAutoParking();
    }

    public int getSupportBlindSpotAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportBlindSpotAssist();
    }

    public int getSupportRearCrossBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportRearCrossBrake();
    }

    public int getSupportAiDistanceLight() {
        return this.mUiStyleConfigManagerImpl.getSupportAiDistanceLight();
    }

    public int getSupportAiMatrixLight() {
        return this.mUiStyleConfigManagerImpl.getSupportAiMatrixLight();
    }

    public int getSupportPanoramaImage() {
        return this.mUiStyleConfigManagerImpl.getSupportPanoramaImage();
    }

    public int getSupportESP() {
        return this.mUiStyleConfigManagerImpl.getSupportESP();
    }

    public int getSupportDigitalKey() {
        return this.mUiStyleConfigManagerImpl.getSupportDigitalKey();
    }

    public int getSupportShowCarModel() {
        return this.mUiStyleConfigManagerImpl.getSupportShowCarModel();
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getRemoteControlLockWindowSupport();
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getRemoteControlUnlockWindowSupport();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getMicroSwitchLockWindowSupport();
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getMicroSwitchUnlockWindowSupport();
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getLockCarRiseWindowSupport();
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        return this.mUiStyleConfigManagerImpl.getOverspeedLockSupport();
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getRainAutoLockWindowSupport();
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorknobAutoShrinkSupport();
    }

    @Deprecated
    public int getLockOffSupport() {
        return this.mUiStyleConfigManagerImpl.getLockOffSupport();
    }

    public int getDigtalKeySupport() {
        return this.mUiStyleConfigManagerImpl.getDigtalKeySupport();
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentUnlockSupport();
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentLockSupport();
    }

    public int getAppUseLimitSupport() {
        return this.mUiStyleConfigManagerImpl.getAppUseLimitSupport();
    }

    public int getRemindSpeedSupport() {
        return this.mUiStyleConfigManagerImpl.getRemindSpeedSupport();
    }

    public int getReportVolumeSupport() {
        return this.mUiStyleConfigManagerImpl.getReportVolumeSupport();
    }

    public int getEngineSoundSupport() {
        return this.mUiStyleConfigManagerImpl.getEngineSoundSupport();
    }

    public int getCarTipsSoundSupport() {
        return this.mUiStyleConfigManagerImpl.getCarTipsSoundSupport();
    }

    public int getReportContentSetSupport() {
        return this.mUiStyleConfigManagerImpl.getReportContentSetSupport();
    }

    public int getReportItemsSetSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getReportItemsSetSupport(i);
    }

    public int getSeatSetSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatSetSupport(i);
    }

    public int getSteeringWheelSupport() {
        return this.mUiStyleConfigManagerImpl.getSteeringWheelSupport();
    }

    public int getRearviewMirrorSupport() {
        return this.mUiStyleConfigManagerImpl.getRearviewMirrorSupport();
    }

    public int getSeatTurnHorizatialSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatTurnHorizatialSupport(i);
    }

    public int getSeatTurnHeightSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatTurnHeightSupport(i);
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getHeadrestTurnHorizatialSupport(i);
    }

    public int getSeatbackrestSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatbackrestSupport(i);
    }

    public int getHeadrestTurnHeightSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getHeadrestTurnHeightSupport(i);
    }

    public int getRefrigeratorModeSupport() {
        return this.mUiStyleConfigManagerImpl.getRefrigeratorModeSupport();
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        return this.mUiStyleConfigManagerImpl.getSupportRemenberPowerOff();
    }

    @Deprecated
    public int getDisinfSupport() {
        return this.mUiStyleConfigManagerImpl.getDisinfSupport();
    }

    @Deprecated
    public int getTrayControlSupport() {
        return this.mUiStyleConfigManagerImpl.getTrayControlSupport();
    }

    public int getCushionSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getCushionSupport(i);
    }

    public int getCaliperSplintSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getCaliperSplintSupport(i);
    }

    public int getAmbientLightColorSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientLightColorSupport();
    }

    public int getAmbientLightSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientLightSupport();
    }

    public int getAmbientAreaSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientAreaSupport();
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientMusicModeSupport();
    }

    public int getAmbientColorsSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientColorsSupport();
    }

    public int resetAmbientSettings(int i) {
        return this.mUiStyleConfigManagerImpl.resetAmbientSettings(i);
    }

    @Deprecated
    public int ambientFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.ambientFactoryMode(i);
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        return this.mUiStyleConfigManagerImpl.getHudParamsStateSupport();
    }

    public int getHudHeightAdjustSupport() {
        return this.mUiStyleConfigManagerImpl.getHudHeightAdjustSupport();
    }

    public int getHudLightSdjustSupport() {
        return this.mUiStyleConfigManagerImpl.getHudLightSdjustSupport();
    }

    public int getInstrumentThemeSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentThemeSupport();
    }

    public int getInstrumentHeightSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentHeightSupport();
    }

    public int getInstrumentContentSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentContentSupport();
    }

    public int getDischargeSupport() {
        return this.mUiStyleConfigManagerImpl.getDischargeSupport();
    }

    public int getDriveDistanceShowStandardSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveDistanceShowStandardSupport();
    }

    public int getEPedalStateSupport() {
        return this.mUiStyleConfigManagerImpl.getEPedalStateSupport();
    }

    public int getDriveDistanceShowModeSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveDistanceShowModeSupport();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        return this.mUiStyleConfigManagerImpl.getMainSeatRememberSupport();
    }

    public int getSecondSeatRememberSupport() {
        return this.mUiStyleConfigManagerImpl.getSecondSeatRememberSupport();
    }

    public int getGuestLeftSeatRememeSupport() {
        return this.mUiStyleConfigManagerImpl.getGuestLeftSeatRememeSupport();
    }

    public int getGuestRightSeatRememeSupport() {
        return this.mUiStyleConfigManagerImpl.getGuestRightSeatRememeSupport();
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        return this.mUiStyleConfigManagerImpl.resetDoorAndIndoor(i);
    }

    public int getAdaptiveHeadlightsSupport() {
        return this.mUiStyleConfigManagerImpl.getAdaptiveHeadlightsSupport();
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.resetAutoPurificationFactoryMode(i);
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.setAcWarmFactoryMode(i);
    }

    public int getMoonRoofConfig() {
        return this.mUiStyleConfigManagerImpl.getMoonRoofConfig();
    }

    public int getSupportHighwayNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportHighwayNavigation();
    }

    public int getSupportUrbanNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportUrbanNavigation();
    }

    public int getSupportEndToEndNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportEndToEndNavigation();
    }

    public int getSupportLaneChangeAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneChangeAssist();
    }

    public int getSupportLaneDepartureWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneDepartureWarning();
    }

    public int getSupportLaneKeep() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneKeep();
    }

    public int getSupportLaneDepartureStop() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneDepartureStop();
    }

    public int getSupportEmergencyLaneKeepAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportEmergencyLaneKeepAssist();
    }

    public int getSupportTrafficSignRecogniton() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficSignRecogniton();
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportIntellignetSpeedLimitAlert();
    }

    public int getSupportIntellignetSpeedLimitControl() {
        return this.mUiStyleConfigManagerImpl.getSupportIntellignetSpeedLimitControl();
    }

    public int getSupportTrafficLightAttention() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficLightAttention();
    }

    public int getSupportDoorOpenWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportDoorOpenWarning();
    }

    public int getSupportRearCollisionWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportRearCollisionWarning();
    }

    public int getSupportRearTrafficCrossingAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportRearTrafficCrossingAlert();
    }

    public int getSupportFrontCrossingTrafficAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportFrontCrossingTrafficAlert();
    }

    public int getSupportFrontCrossingTrafficBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportFrontCrossingTrafficBrake();
    }

    public int getElectroniChildLockConfig() {
        return this.mUiStyleConfigManagerImpl.getElectroniChildLockConfig();
    }

    public int getWindowAntiPinchConfig() {
        return this.mUiStyleConfigManagerImpl.getWindowAntiPinchConfig();
    }

    public int getComfortableSpeedUp() {
        return this.mUiStyleConfigManagerImpl.getComfortableSpeedUp();
    }

    public int getComfortableSpeedDown() {
        return this.mUiStyleConfigManagerImpl.getComfortableSpeedDown();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mUiStyleConfigManagerImpl.get(iArr, cls);
    }

    public int getSteeringWheelHeatingConfiguration() {
        return this.mUiStyleConfigManagerImpl.getSteeringWheelHeatingConfiguration();
    }

    public int restoreFactorySettings() {
        return this.mUiStyleConfigManagerImpl.restoreFactorySettings();
    }

    public int getValetParking() {
        return this.mUiStyleConfigManagerImpl.getValetParking();
    }

    public int getMemoryParking() {
        return this.mUiStyleConfigManagerImpl.getMemoryParking();
    }

    public int getPassengerSeatMemoryFunctionCan() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatMemoryFunctionCan();
    }

    public int getTirePressureInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getTirePressureInterfaceStatus();
    }

    public int getFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getFuelConsumptionInterfaceStatus();
    }

    public int getCumulativeAverageEnergyConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getCumulativeAverageEnergyConsumptionInterfaceStatus();
    }

    public int getCumulativeAverageFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getCumulativeAverageFuelConsumptionInterfaceStatus();
    }

    public int getCumulativeAveragePowerConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getCumulativeAveragePowerConsumptionInterfaceStatus();
    }

    public int getTheLast50KMAverageEnergyConsumptionInterfaceState() {
        return this.mUiStyleConfigManagerImpl.getTheLast50KMAverageEnergyConsumptionInterfaceState();
    }

    public int getTheLast50KMAverageFuelConsumptionInterfaceState() {
        return this.mUiStyleConfigManagerImpl.getTheLast50KMAverageFuelConsumptionInterfaceState();
    }

    public int getTheLast50KMAveragePowerConsumptionInterfaceState() {
        return this.mUiStyleConfigManagerImpl.getTheLast50KMAveragePowerConsumptionInterfaceState();
    }

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getInstantaneousFuelConsumptionInterfaceStatus();
    }

    public int getExternalChargingPowerInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getExternalChargingPowerInterfaceStatus();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getEnergyFlowDiagramInterfaceStatus();
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        return this.mUiStyleConfigManagerImpl.getDrivingRangeInterfaceStatusForFuel();
    }

    public int getAverageSpeedInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getAverageSpeedInterfaceStatus();
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        return this.mUiStyleConfigManagerImpl.getDriveTimeInterfaceStatusForSingleTrip();
    }

    public int getRightRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatReclineConfiguration();
    }

    public int getLeftRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatReclineConfiguration();
    }

    public int getRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRearSeatReclineConfiguration();
    }

    public int getSupportExtreme() {
        return this.mUiStyleConfigManagerImpl.getSupportExtreme();
    }

    public int getUiStyle() {
        return this.mUiStyleConfigManagerImpl.getUiStyle();
    }

    public boolean isDynasty() {
        return this.mUiStyleConfigManagerImpl.isDynasty();
    }

    public boolean isDenza() {
        return this.mUiStyleConfigManagerImpl.isDenza();
    }

    public boolean isOcean() {
        return this.mUiStyleConfigManagerImpl.isOcean();
    }
}
