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

    @Deprecated
    public int ambientFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.ambientFactoryMode(i);
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.backAndLeaveDelayFactoryMode(i);
    }

    @Deprecated
    public int dayLightFactory(int i) {
        return this.mUiStyleConfigManagerImpl.dayLightFactory(i);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mUiStyleConfigManagerImpl.get(iArr, cls);
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

    @Deprecated
    public int getACTunnelCycleSupport() {
        return this.mUiStyleConfigManagerImpl.getACTunnelCycleSupport();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        return this.mUiStyleConfigManagerImpl.getAcFaultNumShownState();
    }

    @Deprecated
    public int getAcType() {
        return this.mUiStyleConfigManagerImpl.getAcType();
    }

    public int getAcWarmSupport() {
        return this.mUiStyleConfigManagerImpl.getAcWarmSupport();
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        return this.mUiStyleConfigManagerImpl.getAcWarmTypeOnlineState();
    }

    @Deprecated
    public int getAcWindModeNum() {
        return this.mUiStyleConfigManagerImpl.getAcWindModeNum();
    }

    public int getAdaptiveHeadlightsSupport() {
        return this.mUiStyleConfigManagerImpl.getAdaptiveHeadlightsSupport();
    }

    public int getAmbientAreaSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientAreaSupport();
    }

    public int getAmbientColorsSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientColorsSupport();
    }

    public int getAmbientLightColorSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientLightColorSupport();
    }

    public int getAmbientLightSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientLightSupport();
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientMusicModeSupport();
    }

    public int getAppUseLimitSupport() {
        return this.mUiStyleConfigManagerImpl.getAppUseLimitSupport();
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoExternalRearMirrorFollowUpSwitchSupport();
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPowerOffSupport();
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPowerOnStateSupport();
    }

    public int getAutoPurificationSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPurificationSupport();
    }

    public int getAutoReportContentSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getAutoReportContentSupport(i);
    }

    public int getAutoWiperSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoWiperSupport();
    }

    public int getAverageSpeedInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getAverageSpeedInterfaceStatus();
    }

    public int getBackHomeDelaySupport() {
        return this.mUiStyleConfigManagerImpl.getBackHomeDelaySupport();
    }

    public int getBrakePedalModeSupport() {
        return this.mUiStyleConfigManagerImpl.getBrakePedalModeSupport();
    }

    public int getCaliperSplintSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getCaliperSplintSupport(i);
    }

    public int getCarNum() {
        return this.mUiStyleConfigManagerImpl.getCarNum();
    }

    public int getCarTipsSoundSupport() {
        return this.mUiStyleConfigManagerImpl.getCarTipsSoundSupport();
    }

    public int getCarType() {
        return this.mUiStyleConfigManagerImpl.getCarType();
    }

    public int getChargeLimitSupport() {
        return this.mUiStyleConfigManagerImpl.getChargeLimitSupport();
    }

    public int getChargeToaggleGuardSupport() {
        return this.mUiStyleConfigManagerImpl.getChargeToaggleGuardSupport();
    }

    public int getComfortableParkingSupport() {
        return this.mUiStyleConfigManagerImpl.getComfortableParkingSupport();
    }

    public int getComfortableSpeedDown() {
        return this.mUiStyleConfigManagerImpl.getComfortableSpeedDown();
    }

    public int getComfortableSpeedUp() {
        return this.mUiStyleConfigManagerImpl.getComfortableSpeedUp();
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

    public int getCushionSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getCushionSupport(i);
    }

    public int getDMType() {
        return this.mUiStyleConfigManagerImpl.getDMType();
    }

    public int getDayLightSupport() {
        return this.mUiStyleConfigManagerImpl.getDayLightSupport();
    }

    @Deprecated
    public int getDefrostRearConfig() {
        return this.mUiStyleConfigManagerImpl.getDefrostRearConfig();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getDigtalKeySupport() {
        return this.mUiStyleConfigManagerImpl.getDigtalKeySupport();
    }

    public int getDischargeSupport() {
        return this.mUiStyleConfigManagerImpl.getDischargeSupport();
    }

    @Deprecated
    public int getDisinfSupport() {
        return this.mUiStyleConfigManagerImpl.getDisinfSupport();
    }

    public int getDoorHeightSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorHeightSupport();
    }

    public int getDoorLightSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorLightSupport();
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorknobAutoShrinkSupport();
    }

    public int getDriveConfig() {
        return this.mUiStyleConfigManagerImpl.getDriveConfig();
    }

    public int getDriveDistanceShowModeSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveDistanceShowModeSupport();
    }

    public int getDriveDistanceShowStandardSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveDistanceShowStandardSupport();
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveSeatAutoReturnSupport();
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        return this.mUiStyleConfigManagerImpl.getDriveTimeInterfaceStatusForSingleTrip();
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        return this.mUiStyleConfigManagerImpl.getDrivingRangeInterfaceStatusForFuel();
    }

    public int getDynamicSuspensionModeSupport() {
        return this.mUiStyleConfigManagerImpl.getDynamicSuspensionModeSupport();
    }

    public int getEPedalStateSupport() {
        return this.mUiStyleConfigManagerImpl.getEPedalStateSupport();
    }

    public int getElectroniChildLockConfig() {
        return this.mUiStyleConfigManagerImpl.getElectroniChildLockConfig();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getEnergyFlowDiagramInterfaceStatus();
    }

    @Deprecated
    public int getEnergyStatus() {
        return this.mUiStyleConfigManagerImpl.getEnergyStatus();
    }

    public int getEngineSoundSupport() {
        return this.mUiStyleConfigManagerImpl.getEngineSoundSupport();
    }

    public int getExternalChargingPowerInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getExternalChargingPowerInterfaceStatus();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getFuelConsumptionInterfaceStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    public int getGuestLeftSeatRememeSupport() {
        return this.mUiStyleConfigManagerImpl.getGuestLeftSeatRememeSupport();
    }

    public int getGuestRightSeatRememeSupport() {
        return this.mUiStyleConfigManagerImpl.getGuestRightSeatRememeSupport();
    }

    public int getHeadlampSupport() {
        return this.mUiStyleConfigManagerImpl.getHeadlampSupport();
    }

    public int getHeadrestTurnHeightSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getHeadrestTurnHeightSupport(i);
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getHeadrestTurnHorizatialSupport(i);
    }

    public int getHudHeightAdjustSupport() {
        return this.mUiStyleConfigManagerImpl.getHudHeightAdjustSupport();
    }

    public int getHudLightSdjustSupport() {
        return this.mUiStyleConfigManagerImpl.getHudLightSdjustSupport();
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        return this.mUiStyleConfigManagerImpl.getHudParamsStateSupport();
    }

    public int getHudShowParamsSupport() {
        return this.mUiStyleConfigManagerImpl.getHudShowParamsSupport();
    }

    public int getIndoorLightSupport() {
        return this.mUiStyleConfigManagerImpl.getIndoorLightSupport();
    }

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getInstantaneousFuelConsumptionInterfaceStatus();
    }

    public int getInstrumentContentSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentContentSupport();
    }

    public int getInstrumentHeightSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentHeightSupport();
    }

    public int getInstrumentThemeSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentThemeSupport();
    }

    public int getIntelligentAcAreaSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentAcAreaSupport();
    }

    public int getIntelligentKeepTemSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentKeepTemSupport();
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentLockSupport();
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentUnlockSupport();
    }

    public int getLandAdvanceAtSupport() {
        return this.mUiStyleConfigManagerImpl.getLandAdvanceAtSupport();
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        return this.mUiStyleConfigManagerImpl.getLeaveHomeDelaySupport();
    }

    public int getLeftPlatformConfigs() {
        return this.mUiStyleConfigManagerImpl.getLeftPlatformConfigs();
    }

    public int getLeftRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatReclineConfiguration();
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getLockCarRiseWindowSupport();
    }

    @Deprecated
    public int getLockOffSupport() {
        return this.mUiStyleConfigManagerImpl.getLockOffSupport();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        return this.mUiStyleConfigManagerImpl.getMainSeatRememberSupport();
    }

    public int getMemoryParking() {
        return this.mUiStyleConfigManagerImpl.getMemoryParking();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getMicroSwitchLockWindowSupport();
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getMicroSwitchUnlockWindowSupport();
    }

    public int getMoonRoofConfig() {
        return this.mUiStyleConfigManagerImpl.getMoonRoofConfig();
    }

    public int getMotorPretensionerSeatbeltSupport() {
        return this.mUiStyleConfigManagerImpl.getMotorPretensionerSeatbeltSupport();
    }

    public int getOBCType() {
        return this.mUiStyleConfigManagerImpl.getOBCType();
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        return this.mUiStyleConfigManagerImpl.getOverspeedLockSupport();
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

    public int getPassengerSeatMemoryFunctionCan() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatMemoryFunctionCan();
    }

    public int getPreSaleModelSupport() {
        return this.mUiStyleConfigManagerImpl.getPreSaleModelSupport();
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getRainAutoLockWindowSupport();
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        return this.mUiStyleConfigManagerImpl.getRearAcMaxWindLevel();
    }

    public int getRearPanelLockTimeSupport() {
        return this.mUiStyleConfigManagerImpl.getRearPanelLockTimeSupport();
    }

    public int getRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRearSeatReclineConfiguration();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        return this.mUiStyleConfigManagerImpl.getRearViewMirrorFlipSupport();
    }

    public int getRearviewMirrorSupport() {
        return this.mUiStyleConfigManagerImpl.getRearviewMirrorSupport();
    }

    public int getRefrigeratorModeSupport() {
        return this.mUiStyleConfigManagerImpl.getRefrigeratorModeSupport();
    }

    public int getRemindSpeedSupport() {
        return this.mUiStyleConfigManagerImpl.getRemindSpeedSupport();
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
    public int getRemoteKeyStartAcTimeSupport() {
        return this.mUiStyleConfigManagerImpl.getRemoteKeyStartAcTimeSupport();
    }

    public int getReportContentSetSupport() {
        return this.mUiStyleConfigManagerImpl.getReportContentSetSupport();
    }

    public int getReportItemsSetSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getReportItemsSetSupport(i);
    }

    public int getReportVolumeSupport() {
        return this.mUiStyleConfigManagerImpl.getReportVolumeSupport();
    }

    public int getRightRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatReclineConfiguration();
    }

    public int getSeatSetSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatSetSupport(i);
    }

    public int getSeatTurnHeightSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatTurnHeightSupport(i);
    }

    public int getSeatTurnHorizatialSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatTurnHorizatialSupport(i);
    }

    public int getSeatbackrestSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatbackrestSupport(i);
    }

    public int getSecondSeatRememberSupport() {
        return this.mUiStyleConfigManagerImpl.getSecondSeatRememberSupport();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    public int getSteerAssisSupport() {
        return this.mUiStyleConfigManagerImpl.getSteerAssisSupport();
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        return this.mUiStyleConfigManagerImpl.getSteerPositionAutoReturnSupport();
    }

    public int getSteeringWheelHeatingConfiguration() {
        return this.mUiStyleConfigManagerImpl.getSteeringWheelHeatingConfiguration();
    }

    public int getSteeringWheelSupport() {
        return this.mUiStyleConfigManagerImpl.getSteeringWheelSupport();
    }

    @Deprecated
    public int getSupportAFS() {
        return this.mUiStyleConfigManagerImpl.getSupportAFS();
    }

    public int getSupportAcAutoKeyCanClose() {
        return this.mUiStyleConfigManagerImpl.getSupportAcAutoKeyCanClose();
    }

    public int getSupportAiCare() {
        return this.mUiStyleConfigManagerImpl.getSupportAiCare();
    }

    public int getSupportAiDistanceLight() {
        return this.mUiStyleConfigManagerImpl.getSupportAiDistanceLight();
    }

    public int getSupportAiEmergencyBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportAiEmergencyBrake();
    }

    public int getSupportAiMatrixLight() {
        return this.mUiStyleConfigManagerImpl.getSupportAiMatrixLight();
    }

    public int getSupportAutoParking() {
        return this.mUiStyleConfigManagerImpl.getSupportAutoParking();
    }

    public int getSupportBlindSpotAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportBlindSpotAssist();
    }

    public int getSupportDigitalKey() {
        return this.mUiStyleConfigManagerImpl.getSupportDigitalKey();
    }

    public int getSupportDoorOpenWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportDoorOpenWarning();
    }

    public int getSupportESP() {
        return this.mUiStyleConfigManagerImpl.getSupportESP();
    }

    public int getSupportEmergencyLaneKeepAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportEmergencyLaneKeepAssist();
    }

    public int getSupportEndToEndNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportEndToEndNavigation();
    }

    public int getSupportExtreme() {
        return this.mUiStyleConfigManagerImpl.getSupportExtreme();
    }

    public int getSupportFragrance() {
        return this.mUiStyleConfigManagerImpl.getSupportFragrance();
    }

    public int getSupportFragranceType(int i) {
        return this.mUiStyleConfigManagerImpl.getFragranceInstallState(i);
    }

    public int getSupportFrontCrossingTrafficAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportFrontCrossingTrafficAlert();
    }

    public int getSupportFrontCrossingTrafficBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportFrontCrossingTrafficBrake();
    }

    public int getSupportHighTemperatureSterilize() {
        return this.mUiStyleConfigManagerImpl.getSupportHighTemperatureSterilize();
    }

    public int getSupportHighwayNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportHighwayNavigation();
    }

    public int getSupportIntelligentNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportIntelligentNavigation();
    }

    public int getSupportIntelligentNavigationBy432() {
        return this.mUiStyleConfigManagerImpl.getSupportIntelligentNavigationBy432();
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportIntellignetSpeedLimitAlert();
    }

    public int getSupportIntellignetSpeedLimitControl() {
        return this.mUiStyleConfigManagerImpl.getSupportIntellignetSpeedLimitControl();
    }

    public int getSupportLampSingnal() {
        return this.mUiStyleConfigManagerImpl.getSupportLampSingnal();
    }

    public int getSupportLaneChangeAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneChangeAssist();
    }

    public int getSupportLaneDepartureStop() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneDepartureStop();
    }

    public int getSupportLaneDepartureWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneDepartureWarning();
    }

    public int getSupportLaneKeep() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneKeep();
    }

    public int getSupportMistakeGas() {
        return this.mUiStyleConfigManagerImpl.getSupportMistakeGas();
    }

    public int getSupportNegativeIon() {
        return this.mUiStyleConfigManagerImpl.getSupportNegativeIon();
    }

    public int getSupportPM25Mode() {
        return this.mUiStyleConfigManagerImpl.getSupportPM25Mode();
    }

    public int getSupportPanoramaImage() {
        return this.mUiStyleConfigManagerImpl.getSupportPanoramaImage();
    }

    public int getSupportPredictCrashAlertMode() {
        return this.mUiStyleConfigManagerImpl.getSupportPredictCrashAlertMode();
    }

    public int getSupportRearAc() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAc();
    }

    public int getSupportRearAcAutoControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcAutoControl();
    }

    public int getSupportRearAcCompressor() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcCompressor();
    }

    public int getSupportRearAcKeyLock() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcKeyLock();
    }

    public int getSupportRearAcSwitch() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcSwitch();
    }

    public int getSupportRearAcTempertureControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcTempertureControl();
    }

    public int getSupportRearAcWindLevelControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcWindLevelControl();
    }

    public int getSupportRearAcWindModeControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcWindModeControl();
    }

    public int getSupportRearCollisionWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportRearCollisionWarning();
    }

    public int getSupportRearCrossBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportRearCrossBrake();
    }

    public int getSupportRearTrafficCrossingAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportRearTrafficCrossingAlert();
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        return this.mUiStyleConfigManagerImpl.getSupportRemenberPowerOff();
    }

    public int getSupportReverseRadar() {
        return this.mUiStyleConfigManagerImpl.getSupportReverseRadar();
    }

    public int[] getSupportSeatAdjust(int i) {
        return this.mUiStyleConfigManagerImpl.getSupportSeatAdjust(i);
    }

    public int getSupportSeatMassage(int i) {
        return this.mUiStyleConfigManagerImpl.getSupportSeatMassage(i);
    }

    public int getSupportShakeAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportShakeAlert();
    }

    public int getSupportShowCarModel() {
        return this.mUiStyleConfigManagerImpl.getSupportShowCarModel();
    }

    public int getSupportSingleDoubleTempArea() {
        return this.mUiStyleConfigManagerImpl.getSupportSingleDoubleTempArea();
    }

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        return this.mUiStyleConfigManagerImpl.getSupportSteeringWheelWarm();
    }

    public int getSupportTrafficLightAttention() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficLightAttention();
    }

    public int getSupportTrafficSignRecogniton() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficSignRecogniton();
    }

    public int getSupportTrafficSignsRecognition() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficSignsRecognition();
    }

    public int getSupportUrbanNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportUrbanNavigation();
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

    public int getTirePressureInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getTirePressureInterfaceStatus();
    }

    @Deprecated
    public int getTrayControlSupport() {
        return this.mUiStyleConfigManagerImpl.getTrayControlSupport();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getUiStyle() {
        return this.mUiStyleConfigManagerImpl.getUiStyle();
    }

    public int getUsherLightSupport() {
        return this.mUiStyleConfigManagerImpl.getUsherLightSupport();
    }

    public int getValetParking() {
        return this.mUiStyleConfigManagerImpl.getValetParking();
    }

    public int getWindowAntiPinchConfig() {
        return this.mUiStyleConfigManagerImpl.getWindowAntiPinchConfig();
    }

    public boolean isDenza() {
        return this.mUiStyleConfigManagerImpl.isDenza();
    }

    public boolean isDynasty() {
        return this.mUiStyleConfigManagerImpl.isDynasty();
    }

    public boolean isOcean() {
        return this.mUiStyleConfigManagerImpl.isOcean();
    }

    public int resetAmbientSettings(int i) {
        return this.mUiStyleConfigManagerImpl.resetAmbientSettings(i);
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.resetAutoPurificationFactoryMode(i);
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        return this.mUiStyleConfigManagerImpl.resetBackAndLeaveDelaySupport(i);
    }

    public int resetDayLight(int i) {
        return this.mUiStyleConfigManagerImpl.resetDayLight(i);
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        return this.mUiStyleConfigManagerImpl.resetDoorAndIndoor(i);
    }

    public int resetHeadlamp(int i) {
        return this.mUiStyleConfigManagerImpl.resetHeadlamp(i);
    }

    public int restoreFactorySettings() {
        return this.mUiStyleConfigManagerImpl.restoreFactorySettings();
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.setAcWarmFactoryMode(i);
    }
}
