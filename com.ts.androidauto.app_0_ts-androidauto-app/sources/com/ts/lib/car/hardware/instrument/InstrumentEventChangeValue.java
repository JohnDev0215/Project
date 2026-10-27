package com.ts.lib.car.hardware.instrument;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes2.dex */
public class InstrumentEventChangeValue extends CarBaseProperty {
    private int mAccCounterUiR;
    private int mAlarmBuzzleState;
    private int mAllWeatherLightsStateR;
    private int mAreaInfoR;
    private int mCallInfoDownS;
    private int mCallInfoUpR;
    private int mDayNightModeR;
    private int mDayNightModeS;
    private int mDistanceMileageUnitR;
    private float mExternalChargingPower;
    private int mHeadlightControlModeR;
    private int mHeadlightControlModeS;
    private int mINSAdasCenterDisplay;
    private int mINSAdasCenterDisplayRespond40C;
    private int mINSAdasCenterDisplaySend26F;
    private int mINSAdasCenterDisplaySend4C1;
    private int mINSCenterDisplayR;
    private int mINSCenterDisplayS;
    private int mINSNavDisplayModeR;
    private int mINSNavDisplayModeS;
    private int mInstrumentAutoDimmStateR;
    private int mInstrumentAutoDimmStateS;
    private int mInstrumentBrightnessLeftR;
    private int mInstrumentBrightnessR;
    private int mInstrumentBrightnessS;
    private int mInstrumentConfigure;
    private int mInstrumentScreenType;
    private int mInstrumentThemeR;
    private int mInstrumentThemeS;
    private int mLowFuelWarnLightColorR;
    private int mLowPowerBatteryWarnLightColorR;
    private int mMaintenanceInfoFourR;
    private int mMaintenanceInfoMileStateR;
    private int mMaintenanceInfoMileStateS;
    private int mMaintenanceInfoOneR;
    private int mMaintenanceInfoThreeR;
    private int mMaintenanceInfoTimeState;
    private int mMaintenanceInfoTimeStateInstrument;
    private float mMaintenanceInfoTimeStateMaintain;
    private int mMaintenanceInfoTwoR;
    private int mMalFunctionInfosACHotManaFailYellow;
    private int mMalFunctionInfosAEBFunctLimitYellow;
    private int mMalFunctionInfosAPAFunctionLimitWhite;
    private int mMalFunctionInfosBrakeFluidLevelIsLowRed;
    private int mMalFunctionInfosChargPortCoverNotClsWhite;
    private int mMalFunctionInfosCheckBrakePlateYellow;
    private int mMalFunctionInfosCheckChargOutEquipYellow;
    private int mMalFunctionInfosCheckDischargeEquipYellow;
    private int mMalFunctionInfosCheckFMRRYellow;
    private int mMalFunctionInfosCheckGenerationSysYellow;
    private int mMalFunctionInfosCheckHeadlightSysYellow;
    private int mMalFunctionInfosCheckMotPretBeltSysYellow;
    private int mMalFunctionInfosCheckOnBoardcharSysYellow;
    private int mMalFunctionInfosCheckPedestriDeteSysYellow;
    private int mMalFunctionInfosCheckPredCollAlaSysYellow;
    private int mMalFunctionInfosCheckTheBrakSysYellow;
    private int mMalFunctionInfosCheckTheOilMonitSysYellow;
    private int mMalFunctionInfosCheckTirePresMonSysYellow;
    private int mMalFunctionInfosCheckVideoControllerYellow;
    private int mMalFunctionInfosCoolantLiquiLevelLowYellow;
    private int mMalFunctionInfosEPBIsNotReleasedYellow;
    private int mMalFunctionInfosEVFunctionIsLimitedRed;
    private int mMalFunctionInfosEngAccessoriesLimitedRed;
    private int mMalFunctionInfosEngineCoolTempTooHigh1Red;
    private int mMalFunctionInfosEngineCoolTempTooHigh2Red;
    private int mMalFunctionInfosEngineStartFailedRed;
    private int mMalFunctionInfosExitSmartDrivingModeRed;
    private int mMalFunctionInfosExterDischrgFunctOffYellow;
    private int mMalFunctionInfosGPFNeedsRegeneration;
    private int mMalFunctionInfosGearboxAdaptingWhiite;
    private int mMalFunctionInfosGearboxOilTempTooHighRed;
    private int mMalFunctionInfosHighClutchTemperatureRed;
    private int mMalFunctionInfosInsufficientOilPressureRed;
    private int mMalFunctionInfosIntelligentDriveFailureRed;
    private int mMalFunctionInfosLVPowerSupplySysFaultyRed;
    private int mMalFunctionInfosLackOfParkCapacityYellow;
    private int mMalFunctionInfosLimitLaneDepartuFuncYellow;
    private int mMalFunctionInfosLimitedAdaptiveCruiseYellow;
    private int mMalFunctionInfosLimitedBlindSpotMoniYellow;
    private int mMalFunctionInfosLimitedLaneKeepingYellow;
    private int mMalFunctionInfosLowBatteryLimitPowerYellow;
    private int mMalFunctionInfosLowBatteryLimitedPowerRed;
    private int mMalFunctionInfosOilLevelIsTooHighYellow;
    private int mMalFunctionInfosOilLevelIsTooLowYellow;
    private int mMalFunctionInfosParkingBrakeNotReleaYellow;
    private int mMalFunctionInfosPleaseAddFuelYellow;
    private int mMalFunctionInfosPleaseChargeInTimeYellow;
    private int mMalFunctionInfosPleaseCheckABSSysYellow;
    private int mMalFunctionInfosPleaseCheckADBSystemRed;
    private int mMalFunctionInfosPleaseCheckConnectorYellow;
    private int mMalFunctionInfosPleaseCheckEPBRed;
    private int mMalFunctionInfosPleaseCheckESPSysYellow;
    private int mMalFunctionInfosPleaseCheckEngineSystemRed;
    private int mMalFunctionInfosPleaseCheckGearSysYellow;
    private int mMalFunctionInfosPleaseCheckHDCSysYellow;
    private int mMalFunctionInfosPleaseCheckLBMSRed;
    private int mMalFunctionInfosPleaseCheckLVSupSysRed;
    private int mMalFunctionInfosPleaseCheckMemorySysYellow;
    private int mMalFunctionInfosPleaseCheckPowerSysYellow;
    private int mMalFunctionInfosPleaseCheckSRSSystemRed;
    private int mMalFunctionInfosPleaseCheckSunrooSysYellow;
    private int mMalFunctionInfosPleaseCheckTheBrakeSysRed;
    private int mMalFunctionInfosPleaseCheckThePowerSysRed;
    private int mMalFunctionInfosPleaseCheckTheSteerSysRed;
    private int mMalFunctionInfosPleaseCheckTransmiSys1Red;
    private int mMalFunctionInfosPleaseCheckTransmisSys2Red;
    private int mMalFunctionInfosPleaseCheckVehicleNetRed;
    private int mMalFunctionInfosPleaseGoBydServShopYellow;
    private int mMalFunctionInfosPleaseLeaveVehicleImmdRed;
    private int mMalFunctionInfosPleaseStopDischargingYellow;
    private int mMalFunctionInfosRemoteCtrlDriveSuspeYellow;
    private int mMalFunctionInfosReservedSOne;
    private int mMalFunctionInfosReservedSTwo;
    private int mMalFunctionInfosRestrictedDriveFunctionRed;
    private int mMalFunctionInfosSolenoidValveCleanWhiite;
    private int mMalFunctionInfosStartTypeIronBatFaultyRed;
    private int mMalFunctionInfosSwitchToPRangeYellow;
    private int mMalFunctionInfosTirePressureIsLowYellow;
    private int mMalFunctionInfosTransmisLimitedinFuncRed;
    private int mMalFunctionInfosVehicleEntForEvModeYellow;
    private int mMalfunctionInfo;
    private int mMalfunctionInfoSecond;
    private int mMasterVolumeFirstR;
    private int mMasterVolumeS;
    private int mMasterVolumeSecondR;
    private int mMileageUnitR;
    private int mMilesByType;
    private int mMilesTypeR;
    private int mMilesTypeS;
    private int mMusicChannelStatusFirstR;
    private int mMusicChannelStatusSecondR;
    private int mMusicControlStatusFirstR;
    private int mMusicControlStatusSecondR;
    private int[] mMusicInfoS;
    private int mMusicNameS;
    private int mMusicProgressFirstR;
    private int mMusicProgressSecondR;
    private int mMusicResourceConfigFirstR;
    private int mMusicResourceConfigSecondR;
    private long[] mMusicSingerNameS;
    private byte[] mMusicSingerNameS92;
    private byte[] mMusicSingerNameS93;
    private byte[] mMusicSingerNameS94;
    private byte[] mMusicSingerNameS95;
    private byte[] mMusicSingerNameS96;
    private byte[] mMusicSingerNameS97;
    private byte[] mMusicSingerNameS98;
    private byte[] mMusicSingerNameS99;
    private byte[] mMusicSingerNameS9A;
    private byte[] mMusicSingerNameS9B;
    private byte[] mMusicSingerNameS9C;
    private byte[] mMusicSingerNameS9D;
    private byte[] mMusicSingerNameS9E;
    private int mMuteStateFirstR;
    private int mMuteStateS;
    private int mMuteStateSecondR;
    private int[] mPadPrsntTimeS;
    private int[] mPadTotalTimeS;
    private int mRadioChannelS;
    private int mRearFogLightStateR;
    private int mRearFogLightStateS;
    private int mSpeedUnitR;
    private int mSpeedUpCountStateR;
    private int mSpeedUpCountStateS;
    private int mSpeedUpFormInsR;
    private int mSportModeState;
    private int mTotalMileageR;
    private int mUnitFuelConsumptionAndDistanceInstrumentR;
    private int mUnitFuelConsumptionAndDistanceLeftR;
    private int mUnitFuelConsumptionAndDistanceS;
    private int mUnitPowerInstrumentR;
    private int mUnitPowerLeftR;
    private int mUnitPowerS;
    private int mUnitPressureInstrumentR;
    private int mUnitPressureLeftR;
    private int mUnitPressureS;
    private int mUnitTemperatureInstrumentR;
    private int mUnitTemperatureLeftR;
    private int mUnitTemperatureS;

    public int getDistanceMileageUnitR() {
        return this.mDistanceMileageUnitR;
    }

    public void setDistanceMileageUnitR(int result) {
        this.mDistanceMileageUnitR = result;
    }

    public int getMasterVolumeFirstR() {
        return this.mMasterVolumeFirstR;
    }

    public void setMasterVolumeFirstR(int masterVolumeFirstR) {
        this.mMasterVolumeFirstR = masterVolumeFirstR;
    }

    public int getMasterVolumeSecondR() {
        return this.mMasterVolumeSecondR;
    }

    public void setMasterVolumeSecondR(int masterVolumeSecondR) {
        this.mMasterVolumeSecondR = masterVolumeSecondR;
    }

    public long[] getMusicSingerNameS() {
        return this.mMusicSingerNameS;
    }

    public void setMusicSingerNameS(long[] musicSingerNameS) {
        this.mMusicSingerNameS = musicSingerNameS;
    }

    public byte[] getMusicSingerNameS92() {
        return this.mMusicSingerNameS92;
    }

    public void setMusicSingerNameS92(byte[] musicSingerNameS92) {
        this.mMusicSingerNameS92 = musicSingerNameS92;
    }

    public byte[] getMusicSingerNameS93() {
        return this.mMusicSingerNameS93;
    }

    public void setMusicSingerNameS93(byte[] musicSingerNameS93) {
        this.mMusicSingerNameS93 = musicSingerNameS93;
    }

    public byte[] getMusicSingerNameS94() {
        return this.mMusicSingerNameS94;
    }

    public void setMusicSingerNameS94(byte[] musicSingerNameS94) {
        this.mMusicSingerNameS94 = musicSingerNameS94;
    }

    public byte[] getMusicSingerNameS95() {
        return this.mMusicSingerNameS95;
    }

    public void setMusicSingerNameS95(byte[] musicSingerNameS95) {
        this.mMusicSingerNameS95 = musicSingerNameS95;
    }

    public byte[] getMusicSingerNameS96() {
        return this.mMusicSingerNameS96;
    }

    public void setMusicSingerNameS96(byte[] musicSingerNameS96) {
        this.mMusicSingerNameS96 = musicSingerNameS96;
    }

    public byte[] getMusicSingerNameS97() {
        return this.mMusicSingerNameS97;
    }

    public void setMusicSingerNameS97(byte[] musicSingerNameS97) {
        this.mMusicSingerNameS97 = musicSingerNameS97;
    }

    public byte[] getMusicSingerNameS98() {
        return this.mMusicSingerNameS98;
    }

    public void setMusicSingerNameS98(byte[] musicSingerNameS98) {
        this.mMusicSingerNameS98 = musicSingerNameS98;
    }

    public byte[] getMusicSingerNameS99() {
        return this.mMusicSingerNameS99;
    }

    public void setMusicSingerNameS99(byte[] musicSingerNameS99) {
        this.mMusicSingerNameS99 = musicSingerNameS99;
    }

    public byte[] getMusicSingerNameS9A() {
        return this.mMusicSingerNameS9A;
    }

    public void setMusicSingerNameS9A(byte[] musicSingerNameS9A) {
        this.mMusicSingerNameS9A = musicSingerNameS9A;
    }

    public byte[] getMusicSingerNameS9B() {
        return this.mMusicSingerNameS9B;
    }

    public void setMusicSingerNameS9B(byte[] musicSingerNameS9B) {
        this.mMusicSingerNameS9B = musicSingerNameS9B;
    }

    public byte[] getMusicSingerNameS9C() {
        return this.mMusicSingerNameS9C;
    }

    public void setMusicSingerNameS9C(byte[] musicSingerNameS9C) {
        this.mMusicSingerNameS9C = musicSingerNameS9C;
    }

    public byte[] getMusicSingerNameS9D() {
        return this.mMusicSingerNameS9D;
    }

    public void setMusicSingerNameS9D(byte[] musicSingerNameS9D) {
        this.mMusicSingerNameS9D = musicSingerNameS9D;
    }

    public byte[] getMusicSingerNameS9E() {
        return this.mMusicSingerNameS9E;
    }

    public void setMusicSingerNameS9E(byte[] musicSingerNameS9E) {
        this.mMusicSingerNameS9E = musicSingerNameS9E;
    }

    public int getMuteStateFirstR() {
        return this.mMuteStateFirstR;
    }

    public void setMuteStateFirstR(int muteStateFirstR) {
        this.mMuteStateFirstR = muteStateFirstR;
    }

    public int getMuteStateSecondR() {
        return this.mMuteStateSecondR;
    }

    public void setMuteStateSecondR(int muteStateSecondR) {
        this.mMuteStateSecondR = muteStateSecondR;
    }

    public int getMasterVolumeS() {
        return this.mMasterVolumeS;
    }

    public void setMasterVolumeS(int masterVolumeS) {
        this.mMasterVolumeS = masterVolumeS;
    }

    public int getMusicChannelStatusFirstR() {
        return this.mMusicChannelStatusFirstR;
    }

    public void setMusicChannelStatusFirstR(int musicChannelStatusFirstR) {
        this.mMusicChannelStatusFirstR = musicChannelStatusFirstR;
    }

    public int getMusicChannelStatusSecondR() {
        return this.mMusicChannelStatusSecondR;
    }

    public void setMusicChannelStatusSecondR(int musicChannelStatusSecondR) {
        this.mMusicChannelStatusSecondR = musicChannelStatusSecondR;
    }

    public int getMusicControlStatusFirstR() {
        return this.mMusicControlStatusFirstR;
    }

    public void setMusicControlStatusFirstR(int musicControlStatusFirstR) {
        this.mMusicControlStatusFirstR = musicControlStatusFirstR;
    }

    public int getMusicControlStatusSecondR() {
        return this.mMusicControlStatusSecondR;
    }

    public void setMusicControlStatusSecondR(int musicControlStatusSecondR) {
        this.mMusicControlStatusSecondR = musicControlStatusSecondR;
    }

    public int[] getMusicInfoS() {
        return this.mMusicInfoS;
    }

    public void setMusicInfoS(int[] musicInfoS) {
        this.mMusicInfoS = musicInfoS;
    }

    public int getMusicNameS() {
        return this.mMusicNameS;
    }

    public void setMusicNameS(int musicNameS) {
        this.mMusicNameS = musicNameS;
    }

    public int getMusicProgressFirstR() {
        return this.mMusicProgressFirstR;
    }

    public void setMusicProgressFirstR(int musicProgressFirstR) {
        this.mMusicProgressFirstR = musicProgressFirstR;
    }

    public int getMusicProgressSecondR() {
        return this.mMusicProgressSecondR;
    }

    public void setMusicProgressSecondR(int musicProgressSecondR) {
        this.mMusicProgressSecondR = musicProgressSecondR;
    }

    public int getMusicResourceConfigFirstR() {
        return this.mMusicResourceConfigFirstR;
    }

    public void setMusicResourceConfigFirstR(int musicResourceConfigFirstR) {
        this.mMusicResourceConfigFirstR = musicResourceConfigFirstR;
    }

    public int getMusicResourceConfigSecondR() {
        return this.mMusicResourceConfigSecondR;
    }

    public void setMusicResourceConfigSecondR(int musicResourceConfigSecondR) {
        this.mMusicResourceConfigSecondR = musicResourceConfigSecondR;
    }

    public int getMuteStateS() {
        return this.mMuteStateS;
    }

    public void setMuteStateS(int muteStateS) {
        this.mMuteStateS = muteStateS;
    }

    public int[] getPadPrsntTimeS() {
        return this.mPadPrsntTimeS;
    }

    public void setPadPrsntTimeS(int[] padPrsntTimeS) {
        this.mPadPrsntTimeS = padPrsntTimeS;
    }

    public int[] getPadTotalTimeS() {
        return this.mPadTotalTimeS;
    }

    public void setPadTotalTimeS(int[] padTotalTimeS) {
        this.mPadTotalTimeS = padTotalTimeS;
    }

    public int getRadioChannelS() {
        return this.mRadioChannelS;
    }

    public void setRadioChannelS(int radioChannelS) {
        this.mRadioChannelS = radioChannelS;
    }

    public int getAllWeatherLightsStateR() {
        return this.mAllWeatherLightsStateR;
    }

    public void setAllWeatherLightsStateR(int allWeatherLightsStateR) {
        this.mAllWeatherLightsStateR = allWeatherLightsStateR;
    }

    public int getHeadlightControlModeR() {
        return this.mHeadlightControlModeR;
    }

    public void setHeadlightControlModeR(int headlightControlModeR) {
        this.mHeadlightControlModeR = headlightControlModeR;
    }

    public int getHeadlightControlModeS() {
        return this.mHeadlightControlModeS;
    }

    public void setHeadlightControlModeS(int headlightControlModeS) {
        this.mHeadlightControlModeS = headlightControlModeS;
    }

    public int getRearFogLightStateR() {
        return this.mRearFogLightStateR;
    }

    public void setRearFogLightStateR(int rearFogLightStateR) {
        this.mRearFogLightStateR = rearFogLightStateR;
    }

    public int getRearFogLightStateS() {
        return this.mRearFogLightStateS;
    }

    public void setRearFogLightStateS(int rearFogLightStateS) {
        this.mRearFogLightStateS = rearFogLightStateS;
    }

    public int getLowFuelWarnLightColorR() {
        return this.mLowFuelWarnLightColorR;
    }

    public void setLowFuelWarnLightColorR(int lowFuelWarnLightColorR) {
        this.mLowFuelWarnLightColorR = lowFuelWarnLightColorR;
    }

    public int getLowPowerBatteryWarnLightColorR() {
        return this.mLowPowerBatteryWarnLightColorR;
    }

    public void setLowPowerBatteryWarnLightColorR(int lowPowerBatteryWarnLightColorR) {
        this.mLowPowerBatteryWarnLightColorR = lowPowerBatteryWarnLightColorR;
    }

    public int getInstrumentConfigure() {
        return this.mInstrumentConfigure;
    }

    public void setInstrumentConfigure(int instrumentConfigure) {
        this.mInstrumentConfigure = instrumentConfigure;
    }

    public int getCallInfoDownS() {
        return this.mCallInfoDownS;
    }

    public void setCallInfoDownS(int callInfoDownS) {
        this.mCallInfoDownS = callInfoDownS;
    }

    public int getCallInfoUpR() {
        return this.mCallInfoUpR;
    }

    public void setCallInfoUpR(int callInfoUpR) {
        this.mCallInfoUpR = callInfoUpR;
    }

    public int getSpeedUpFormInsR() {
        return this.mSpeedUpFormInsR;
    }

    public void setSpeedUpFormInsR(int speedUpFormInsR) {
        this.mSpeedUpFormInsR = speedUpFormInsR;
    }

    public int getInstrumentScreenType() {
        return this.mInstrumentScreenType;
    }

    public void setInstrumentScreenType(int instrumentScreenType) {
        this.mInstrumentScreenType = instrumentScreenType;
    }

    public int getMaintenanceInfoFourR() {
        return this.mMaintenanceInfoFourR;
    }

    public void setMaintenanceInfoFourR(int maintenanceInfoFourR) {
        this.mMaintenanceInfoFourR = maintenanceInfoFourR;
    }

    public int getDayNightModeR() {
        return this.mDayNightModeR;
    }

    public void setDayNightModeR(int dayNightModeR) {
        this.mDayNightModeR = dayNightModeR;
    }

    public int getDayNightModeS() {
        return this.mDayNightModeS;
    }

    public void setDayNightModeS(int dayNightModeS) {
        this.mDayNightModeS = dayNightModeS;
    }

    public int getINSCenterDisplayR() {
        return this.mINSCenterDisplayR;
    }

    public void setINSCenterDisplayR(int iNSCenterDisplayR) {
        this.mINSCenterDisplayR = iNSCenterDisplayR;
    }

    public int getINSCenterDisplayS() {
        return this.mINSCenterDisplayS;
    }

    public void setINSCenterDisplayS(int iNSCenterDisplayS) {
        this.mINSCenterDisplayS = iNSCenterDisplayS;
    }

    public int getMaintenanceInfoMileStateR() {
        return this.mMaintenanceInfoMileStateR;
    }

    public void setMaintenanceInfoMileStateR(int maintenanceInfoMileStateR) {
        this.mMaintenanceInfoMileStateR = maintenanceInfoMileStateR;
    }

    public int getMaintenanceInfoMileStateS() {
        return this.mMaintenanceInfoMileStateS;
    }

    public void setMaintenanceInfoMileStateS(int maintenanceInfoMileStateS) {
        this.mMaintenanceInfoMileStateS = maintenanceInfoMileStateS;
    }

    public int getMaintenanceInfoOneR() {
        return this.mMaintenanceInfoOneR;
    }

    public void setMaintenanceInfoOneR(int maintenanceInfoOneR) {
        this.mMaintenanceInfoOneR = maintenanceInfoOneR;
    }

    public int getMaintenanceInfoThreeR() {
        return this.mMaintenanceInfoThreeR;
    }

    public void setMaintenanceInfoThreeR(int maintenanceInfoThreeR) {
        this.mMaintenanceInfoThreeR = maintenanceInfoThreeR;
    }

    public int getMaintenanceInfoTwoR() {
        return this.mMaintenanceInfoTwoR;
    }

    public void setMaintenanceInfoTwoR(int maintenanceInfoTwoR) {
        this.mMaintenanceInfoTwoR = maintenanceInfoTwoR;
    }

    public int getMalFunctionInfosReservedSOne() {
        return this.mMalFunctionInfosReservedSOne;
    }

    public void setMalFunctionInfosReservedSOne(int malFunctionInfosReservedSOne) {
        this.mMalFunctionInfosReservedSOne = malFunctionInfosReservedSOne;
    }

    public int getMalFunctionInfosReservedSTwo() {
        return this.mMalFunctionInfosReservedSTwo;
    }

    public void setMalFunctionInfosReservedSTwo(int malFunctionInfosReservedSTwo) {
        this.mMalFunctionInfosReservedSTwo = malFunctionInfosReservedSTwo;
    }

    public int getMileageUnitR() {
        return this.mMileageUnitR;
    }

    public void setMileageUnitR(int mileageUnitR) {
        this.mMileageUnitR = mileageUnitR;
    }

    public int getSpeedUnitR() {
        return this.mSpeedUnitR;
    }

    public void setSpeedUnitR(int speedUnitR) {
        this.mSpeedUnitR = speedUnitR;
    }

    public int getSpeedUpCountStateR() {
        return this.mSpeedUpCountStateR;
    }

    public void setSpeedUpCountStateR(int speedUpCountStateR) {
        this.mSpeedUpCountStateR = speedUpCountStateR;
    }

    public int getSpeedUpCountStateS() {
        return this.mSpeedUpCountStateS;
    }

    public void setSpeedUpCountStateS(int speedUpCountStateS) {
        this.mSpeedUpCountStateS = speedUpCountStateS;
    }

    public int getTotalMileageR() {
        return this.mTotalMileageR;
    }

    public void setTotalMileageR(int totalMileageR) {
        this.mTotalMileageR = totalMileageR;
    }

    public int getAlarmBuzzleState() {
        return this.mAlarmBuzzleState;
    }

    public void setAlarmBuzzleState(int alarmBuzzleState) {
        this.mAlarmBuzzleState = alarmBuzzleState;
    }

    public float getExternalChargingPower() {
        return this.mExternalChargingPower;
    }

    public void setExternalChargingPower(float externalChargingPower) {
        this.mExternalChargingPower = externalChargingPower;
    }

    public int getINSAdasCenterDisplay() {
        return this.mINSAdasCenterDisplay;
    }

    public void setINSAdasCenterDisplay(int iNSAdasCenterDisplay) {
        this.mINSAdasCenterDisplay = iNSAdasCenterDisplay;
    }

    public int getINSAdasCenterDisplayRespond40C() {
        return this.mINSAdasCenterDisplayRespond40C;
    }

    public void setINSAdasCenterDisplayRespond40C(int iNSAdasCenterDisplayRespond40C) {
        this.mINSAdasCenterDisplayRespond40C = iNSAdasCenterDisplayRespond40C;
    }

    public int getINSAdasCenterDisplaySend26F() {
        return this.mINSAdasCenterDisplaySend26F;
    }

    public void setINSAdasCenterDisplaySend26F(int iNSAdasCenterDisplaySend26F) {
        this.mINSAdasCenterDisplaySend26F = iNSAdasCenterDisplaySend26F;
    }

    public int getINSAdasCenterDisplaySend4C1() {
        return this.mINSAdasCenterDisplaySend4C1;
    }

    public void setINSAdasCenterDisplaySend4C1(int iNSAdasCenterDisplaySend4C1) {
        this.mINSAdasCenterDisplaySend4C1 = iNSAdasCenterDisplaySend4C1;
    }

    public int getINSNavDisplayModeR() {
        return this.mINSNavDisplayModeR;
    }

    public void setINSNavDisplayModeR(int iNSNavDisplayModeR) {
        this.mINSNavDisplayModeR = iNSNavDisplayModeR;
    }

    public int getINSNavDisplayModeS() {
        return this.mINSNavDisplayModeS;
    }

    public void setINSNavDisplayModeS(int iNSNavDisplayModeS) {
        this.mINSNavDisplayModeS = iNSNavDisplayModeS;
    }

    public int getInstrumentAutoDimmStateR() {
        return this.mInstrumentAutoDimmStateR;
    }

    public void setInstrumentAutoDimmStateR(int instrumentAutoDimmStateR) {
        this.mInstrumentAutoDimmStateR = instrumentAutoDimmStateR;
    }

    public int getInstrumentAutoDimmStateS() {
        return this.mInstrumentAutoDimmStateS;
    }

    public void setInstrumentAutoDimmStateS(int instrumentAutoDimmStateS) {
        this.mInstrumentAutoDimmStateS = instrumentAutoDimmStateS;
    }

    public int getInstrumentBrightnessLeftR() {
        return this.mInstrumentBrightnessLeftR;
    }

    public void setInstrumentBrightnessLeftR(int instrumentBrightnessLeftR) {
        this.mInstrumentBrightnessLeftR = instrumentBrightnessLeftR;
    }

    public int getInstrumentBrightnessR() {
        return this.mInstrumentBrightnessR;
    }

    public void setInstrumentBrightnessR(int instrumentBrightnessR) {
        this.mInstrumentBrightnessR = instrumentBrightnessR;
    }

    public int getInstrumentBrightnessS() {
        return this.mInstrumentBrightnessS;
    }

    public void setInstrumentBrightnessS(int instrumentBrightnessS) {
        this.mInstrumentBrightnessS = instrumentBrightnessS;
    }

    public int getInstrumentThemeR() {
        return this.mInstrumentThemeR;
    }

    public void setInstrumentThemeR(int instrumentThemeR) {
        this.mInstrumentThemeR = instrumentThemeR;
    }

    public int getInstrumentThemeS() {
        return this.mInstrumentThemeS;
    }

    public void setInstrumentThemeS(int instrumentThemeS) {
        this.mInstrumentThemeS = instrumentThemeS;
    }

    public int getMaintenanceInfoTimeState() {
        return this.mMaintenanceInfoTimeState;
    }

    public void setMaintenanceInfoTimeState(int maintenanceInfoTimeState) {
        this.mMaintenanceInfoTimeState = maintenanceInfoTimeState;
    }

    public int getMaintenanceInfoTimeStateInstrument() {
        return this.mMaintenanceInfoTimeStateInstrument;
    }

    public void setMaintenanceInfoTimeStateInstrument(int maintenanceInfoTimeStateInstrument) {
        this.mMaintenanceInfoTimeStateInstrument = maintenanceInfoTimeStateInstrument;
    }

    public float getMaintenanceInfoTimeStateMaintain() {
        return this.mMaintenanceInfoTimeStateMaintain;
    }

    public void setMaintenanceInfoTimeStateMaintain(float maintenanceInfoTimeStateMaintain) {
        this.mMaintenanceInfoTimeStateMaintain = maintenanceInfoTimeStateMaintain;
    }

    public int getMalFunctionInfosACHotManaFailYellow() {
        return this.mMalFunctionInfosACHotManaFailYellow;
    }

    public void setMalFunctionInfosACHotManaFailYellow(int malFunctionInfosACHotManaFailYellow) {
        this.mMalFunctionInfosACHotManaFailYellow = malFunctionInfosACHotManaFailYellow;
    }

    public int getMalFunctionInfosAEBFunctLimitYellow() {
        return this.mMalFunctionInfosAEBFunctLimitYellow;
    }

    public void setMalFunctionInfosAEBFunctLimitYellow(int malFunctionInfosAEBFunctLimitYellow) {
        this.mMalFunctionInfosAEBFunctLimitYellow = malFunctionInfosAEBFunctLimitYellow;
    }

    public int getMalFunctionInfosAPAFunctionLimitWhite() {
        return this.mMalFunctionInfosAPAFunctionLimitWhite;
    }

    public void setMalFunctionInfosAPAFunctionLimitWhite(int malFunctionInfosAPAFunctionLimitWhite) {
        this.mMalFunctionInfosAPAFunctionLimitWhite = malFunctionInfosAPAFunctionLimitWhite;
    }

    public int getMalFunctionInfosBrakeFluidLevelIsLowRed() {
        return this.mMalFunctionInfosBrakeFluidLevelIsLowRed;
    }

    public void setMalFunctionInfosBrakeFluidLevelIsLowRed(int malFunctionInfosBrakeFluidLevelIsLowRed) {
        this.mMalFunctionInfosBrakeFluidLevelIsLowRed = malFunctionInfosBrakeFluidLevelIsLowRed;
    }

    public int getMalFunctionInfosChargPortCoverNotClsWhite() {
        return this.mMalFunctionInfosChargPortCoverNotClsWhite;
    }

    public void setMalFunctionInfosChargPortCoverNotClsWhite(int malFunctionInfosChargPortCoverNotClsWhite) {
        this.mMalFunctionInfosChargPortCoverNotClsWhite = malFunctionInfosChargPortCoverNotClsWhite;
    }

    public int getMalFunctionInfosCheckBrakePlateYellow() {
        return this.mMalFunctionInfosCheckBrakePlateYellow;
    }

    public void setMalFunctionInfosCheckBrakePlateYellow(int malFunctionInfosCheckBrakePlateYellow) {
        this.mMalFunctionInfosCheckBrakePlateYellow = malFunctionInfosCheckBrakePlateYellow;
    }

    public int getMalFunctionInfosCheckChargOutEquipYellow() {
        return this.mMalFunctionInfosCheckChargOutEquipYellow;
    }

    public void setMalFunctionInfosCheckChargOutEquipYellow(int malFunctionInfosCheckChargOutEquipYellow) {
        this.mMalFunctionInfosCheckChargOutEquipYellow = malFunctionInfosCheckChargOutEquipYellow;
    }

    public int getMalFunctionInfosCheckDischargeEquipYellow() {
        return this.mMalFunctionInfosCheckDischargeEquipYellow;
    }

    public void setMalFunctionInfosCheckDischargeEquipYellow(int malFunctionInfosCheckDischargeEquipYellow) {
        this.mMalFunctionInfosCheckDischargeEquipYellow = malFunctionInfosCheckDischargeEquipYellow;
    }

    public int getMalFunctionInfosCheckFMRRYellow() {
        return this.mMalFunctionInfosCheckFMRRYellow;
    }

    public void setMalFunctionInfosCheckFMRRYellow(int malFunctionInfosCheckFMRRYellow) {
        this.mMalFunctionInfosCheckFMRRYellow = malFunctionInfosCheckFMRRYellow;
    }

    public int getMalFunctionInfosCheckGenerationSysYellow() {
        return this.mMalFunctionInfosCheckGenerationSysYellow;
    }

    public void setMalFunctionInfosCheckGenerationSysYellow(int malFunctionInfosCheckGenerationSysYellow) {
        this.mMalFunctionInfosCheckGenerationSysYellow = malFunctionInfosCheckGenerationSysYellow;
    }

    public int getMalFunctionInfosCheckHeadlightSysYellow() {
        return this.mMalFunctionInfosCheckHeadlightSysYellow;
    }

    public void setMalFunctionInfosCheckHeadlightSysYellow(int malFunctionInfosCheckHeadlightSysYellow) {
        this.mMalFunctionInfosCheckHeadlightSysYellow = malFunctionInfosCheckHeadlightSysYellow;
    }

    public int getMalFunctionInfosCheckMotPretBeltSysYellow() {
        return this.mMalFunctionInfosCheckMotPretBeltSysYellow;
    }

    public void setMalFunctionInfosCheckMotPretBeltSysYellow(int malFunctionInfosCheckMotPretBeltSysYellow) {
        this.mMalFunctionInfosCheckMotPretBeltSysYellow = malFunctionInfosCheckMotPretBeltSysYellow;
    }

    public int getMalFunctionInfosCheckOnBoardcharSysYellow() {
        return this.mMalFunctionInfosCheckOnBoardcharSysYellow;
    }

    public void setMalFunctionInfosCheckOnBoardcharSysYellow(int malFunctionInfosCheckOnBoardcharSysYellow) {
        this.mMalFunctionInfosCheckOnBoardcharSysYellow = malFunctionInfosCheckOnBoardcharSysYellow;
    }

    public int getMalFunctionInfosCheckPedestriDeteSysYellow() {
        return this.mMalFunctionInfosCheckPedestriDeteSysYellow;
    }

    public void setMalFunctionInfosCheckPedestriDeteSysYellow(int malFunctionInfosCheckPedestriDeteSysYellow) {
        this.mMalFunctionInfosCheckPedestriDeteSysYellow = malFunctionInfosCheckPedestriDeteSysYellow;
    }

    public int getMalFunctionInfosCheckPredCollAlaSysYellow() {
        return this.mMalFunctionInfosCheckPredCollAlaSysYellow;
    }

    public void setMalFunctionInfosCheckPredCollAlaSysYellow(int malFunctionInfosCheckPredCollAlaSysYellow) {
        this.mMalFunctionInfosCheckPredCollAlaSysYellow = malFunctionInfosCheckPredCollAlaSysYellow;
    }

    public int getMalFunctionInfosCheckTheBrakSysYellow() {
        return this.mMalFunctionInfosCheckTheBrakSysYellow;
    }

    public void setMalFunctionInfosCheckTheBrakSysYellow(int malFunctionInfosCheckTheBrakSysYellow) {
        this.mMalFunctionInfosCheckTheBrakSysYellow = malFunctionInfosCheckTheBrakSysYellow;
    }

    public int getMalFunctionInfosCheckTheOilMonitSysYellow() {
        return this.mMalFunctionInfosCheckTheOilMonitSysYellow;
    }

    public void setMalFunctionInfosCheckTheOilMonitSysYellow(int malFunctionInfosCheckTheOilMonitSysYellow) {
        this.mMalFunctionInfosCheckTheOilMonitSysYellow = malFunctionInfosCheckTheOilMonitSysYellow;
    }

    public int getMalFunctionInfosCheckTirePresMonSysYellow() {
        return this.mMalFunctionInfosCheckTirePresMonSysYellow;
    }

    public void setMalFunctionInfosCheckTirePresMonSysYellow(int malFunctionInfosCheckTirePresMonSysYellow) {
        this.mMalFunctionInfosCheckTirePresMonSysYellow = malFunctionInfosCheckTirePresMonSysYellow;
    }

    public int getMalFunctionInfosCheckVideoControllerYellow() {
        return this.mMalFunctionInfosCheckVideoControllerYellow;
    }

    public void setMalFunctionInfosCheckVideoControllerYellow(int malFunctionInfosCheckVideoControllerYellow) {
        this.mMalFunctionInfosCheckVideoControllerYellow = malFunctionInfosCheckVideoControllerYellow;
    }

    public int getMalFunctionInfosCoolantLiquiLevelLowYellow() {
        return this.mMalFunctionInfosCoolantLiquiLevelLowYellow;
    }

    public void setMalFunctionInfosCoolantLiquiLevelLowYellow(int malFunctionInfosCoolantLiquiLevelLowYellow) {
        this.mMalFunctionInfosCoolantLiquiLevelLowYellow = malFunctionInfosCoolantLiquiLevelLowYellow;
    }

    public int getMalFunctionInfosEPBIsNotReleasedYellow() {
        return this.mMalFunctionInfosEPBIsNotReleasedYellow;
    }

    public void setMalFunctionInfosEPBIsNotReleasedYellow(int malFunctionInfosEPBIsNotReleasedYellow) {
        this.mMalFunctionInfosEPBIsNotReleasedYellow = malFunctionInfosEPBIsNotReleasedYellow;
    }

    public int getMalFunctionInfosEVFunctionIsLimitedRed() {
        return this.mMalFunctionInfosEVFunctionIsLimitedRed;
    }

    public void setMalFunctionInfosEVFunctionIsLimitedRed(int malFunctionInfosEVFunctionIsLimitedRed) {
        this.mMalFunctionInfosEVFunctionIsLimitedRed = malFunctionInfosEVFunctionIsLimitedRed;
    }

    public int getMalFunctionInfosEngAccessoriesLimitedRed() {
        return this.mMalFunctionInfosEngAccessoriesLimitedRed;
    }

    public void setMalFunctionInfosEngAccessoriesLimitedRed(int malFunctionInfosEngAccessoriesLimitedRed) {
        this.mMalFunctionInfosEngAccessoriesLimitedRed = malFunctionInfosEngAccessoriesLimitedRed;
    }

    public int getMalFunctionInfosEngineCoolTempTooHigh1Red() {
        return this.mMalFunctionInfosEngineCoolTempTooHigh1Red;
    }

    public void setMalFunctionInfosEngineCoolTempTooHigh1Red(int malFunctionInfosEngineCoolTempTooHigh1Red) {
        this.mMalFunctionInfosEngineCoolTempTooHigh1Red = malFunctionInfosEngineCoolTempTooHigh1Red;
    }

    public int getMalFunctionInfosEngineCoolTempTooHigh2Red() {
        return this.mMalFunctionInfosEngineCoolTempTooHigh2Red;
    }

    public void setMalFunctionInfosEngineCoolTempTooHigh2Red(int malFunctionInfosEngineCoolTempTooHigh2Red) {
        this.mMalFunctionInfosEngineCoolTempTooHigh2Red = malFunctionInfosEngineCoolTempTooHigh2Red;
    }

    public int getMalFunctionInfosEngineStartFailedRed() {
        return this.mMalFunctionInfosEngineStartFailedRed;
    }

    public void setMalFunctionInfosEngineStartFailedRed(int malFunctionInfosEngineStartFailedRed) {
        this.mMalFunctionInfosEngineStartFailedRed = malFunctionInfosEngineStartFailedRed;
    }

    public int getMalFunctionInfosExitSmartDrivingModeRed() {
        return this.mMalFunctionInfosExitSmartDrivingModeRed;
    }

    public void setMalFunctionInfosExitSmartDrivingModeRed(int malFunctionInfosExitSmartDrivingModeRed) {
        this.mMalFunctionInfosExitSmartDrivingModeRed = malFunctionInfosExitSmartDrivingModeRed;
    }

    public int getMalFunctionInfosExterDischrgFunctOffYellow() {
        return this.mMalFunctionInfosExterDischrgFunctOffYellow;
    }

    public void setMalFunctionInfosExterDischrgFunctOffYellow(int malFunctionInfosExterDischrgFunctOffYellow) {
        this.mMalFunctionInfosExterDischrgFunctOffYellow = malFunctionInfosExterDischrgFunctOffYellow;
    }

    public int getMalFunctionInfosGPFNeedsRegeneration() {
        return this.mMalFunctionInfosGPFNeedsRegeneration;
    }

    public void setMalFunctionInfosGPFNeedsRegeneration(int malFunctionInfosGPFNeedsRegeneration) {
        this.mMalFunctionInfosGPFNeedsRegeneration = malFunctionInfosGPFNeedsRegeneration;
    }

    public int getMalFunctionInfosGearboxAdaptingWhiite() {
        return this.mMalFunctionInfosGearboxAdaptingWhiite;
    }

    public void setMalFunctionInfosGearboxAdaptingWhiite(int malFunctionInfosGearboxAdaptingWhiite) {
        this.mMalFunctionInfosGearboxAdaptingWhiite = malFunctionInfosGearboxAdaptingWhiite;
    }

    public int getMalFunctionInfosGearboxOilTempTooHighRed() {
        return this.mMalFunctionInfosGearboxOilTempTooHighRed;
    }

    public void setMalFunctionInfosGearboxOilTempTooHighRed(int malFunctionInfosGearboxOilTempTooHighRed) {
        this.mMalFunctionInfosGearboxOilTempTooHighRed = malFunctionInfosGearboxOilTempTooHighRed;
    }

    public int getMalFunctionInfosHighClutchTemperatureRed() {
        return this.mMalFunctionInfosHighClutchTemperatureRed;
    }

    public void setMalFunctionInfosHighClutchTemperatureRed(int malFunctionInfosHighClutchTemperatureRed) {
        this.mMalFunctionInfosHighClutchTemperatureRed = malFunctionInfosHighClutchTemperatureRed;
    }

    public int getMalFunctionInfosInsufficientOilPressureRed() {
        return this.mMalFunctionInfosInsufficientOilPressureRed;
    }

    public void setMalFunctionInfosInsufficientOilPressureRed(int malFunctionInfosInsufficientOilPressureRed) {
        this.mMalFunctionInfosInsufficientOilPressureRed = malFunctionInfosInsufficientOilPressureRed;
    }

    public int getMalFunctionInfosIntelligentDriveFailureRed() {
        return this.mMalFunctionInfosIntelligentDriveFailureRed;
    }

    public void setMalFunctionInfosIntelligentDriveFailureRed(int malFunctionInfosIntelligentDriveFailureRed) {
        this.mMalFunctionInfosIntelligentDriveFailureRed = malFunctionInfosIntelligentDriveFailureRed;
    }

    public int getMalFunctionInfosLVPowerSupplySysFaultyRed() {
        return this.mMalFunctionInfosLVPowerSupplySysFaultyRed;
    }

    public void setMalFunctionInfosLVPowerSupplySysFaultyRed(int malFunctionInfosLVPowerSupplySysFaultyRed) {
        this.mMalFunctionInfosLVPowerSupplySysFaultyRed = malFunctionInfosLVPowerSupplySysFaultyRed;
    }

    public int getMalFunctionInfosLackOfParkCapacityYellow() {
        return this.mMalFunctionInfosLackOfParkCapacityYellow;
    }

    public void setMalFunctionInfosLackOfParkCapacityYellow(int malFunctionInfosLackOfParkCapacityYellow) {
        this.mMalFunctionInfosLackOfParkCapacityYellow = malFunctionInfosLackOfParkCapacityYellow;
    }

    public int getMalFunctionInfosLimitLaneDepartuFuncYellow() {
        return this.mMalFunctionInfosLimitLaneDepartuFuncYellow;
    }

    public void setMalFunctionInfosLimitLaneDepartuFuncYellow(int malFunctionInfosLimitLaneDepartuFuncYellow) {
        this.mMalFunctionInfosLimitLaneDepartuFuncYellow = malFunctionInfosLimitLaneDepartuFuncYellow;
    }

    public int getMalFunctionInfosLimitedAdaptiveCruiseYellow() {
        return this.mMalFunctionInfosLimitedAdaptiveCruiseYellow;
    }

    public void setMalFunctionInfosLimitedAdaptiveCruiseYellow(int malFunctionInfosLimitedAdaptiveCruiseYellow) {
        this.mMalFunctionInfosLimitedAdaptiveCruiseYellow = malFunctionInfosLimitedAdaptiveCruiseYellow;
    }

    public int getMalFunctionInfosLimitedBlindSpotMoniYellow() {
        return this.mMalFunctionInfosLimitedBlindSpotMoniYellow;
    }

    public void setMalFunctionInfosLimitedBlindSpotMoniYellow(int malFunctionInfosLimitedBlindSpotMoniYellow) {
        this.mMalFunctionInfosLimitedBlindSpotMoniYellow = malFunctionInfosLimitedBlindSpotMoniYellow;
    }

    public int getMalFunctionInfosLimitedLaneKeepingYellow() {
        return this.mMalFunctionInfosLimitedLaneKeepingYellow;
    }

    public void setMalFunctionInfosLimitedLaneKeepingYellow(int malFunctionInfosLimitedLaneKeepingYellow) {
        this.mMalFunctionInfosLimitedLaneKeepingYellow = malFunctionInfosLimitedLaneKeepingYellow;
    }

    public int getMalFunctionInfosLowBatteryLimitPowerYellow() {
        return this.mMalFunctionInfosLowBatteryLimitPowerYellow;
    }

    public void setMalFunctionInfosLowBatteryLimitPowerYellow(int malFunctionInfosLowBatteryLimitPowerYellow) {
        this.mMalFunctionInfosLowBatteryLimitPowerYellow = malFunctionInfosLowBatteryLimitPowerYellow;
    }

    public int getMalFunctionInfosLowBatteryLimitedPowerRed() {
        return this.mMalFunctionInfosLowBatteryLimitedPowerRed;
    }

    public void setMalFunctionInfosLowBatteryLimitedPowerRed(int malFunctionInfosLowBatteryLimitedPowerRed) {
        this.mMalFunctionInfosLowBatteryLimitedPowerRed = malFunctionInfosLowBatteryLimitedPowerRed;
    }

    public int getMalFunctionInfosOilLevelIsTooHighYellow() {
        return this.mMalFunctionInfosOilLevelIsTooHighYellow;
    }

    public void setMalFunctionInfosOilLevelIsTooHighYellow(int malFunctionInfosOilLevelIsTooHighYellow) {
        this.mMalFunctionInfosOilLevelIsTooHighYellow = malFunctionInfosOilLevelIsTooHighYellow;
    }

    public int getMalFunctionInfosOilLevelIsTooLowYellow() {
        return this.mMalFunctionInfosOilLevelIsTooLowYellow;
    }

    public void setMalFunctionInfosOilLevelIsTooLowYellow(int malFunctionInfosOilLevelIsTooLowYellow) {
        this.mMalFunctionInfosOilLevelIsTooLowYellow = malFunctionInfosOilLevelIsTooLowYellow;
    }

    public int getMalFunctionInfosParkingBrakeNotReleaYellow() {
        return this.mMalFunctionInfosParkingBrakeNotReleaYellow;
    }

    public void setMalFunctionInfosParkingBrakeNotReleaYellow(int malFunctionInfosParkingBrakeNotReleaYellow) {
        this.mMalFunctionInfosParkingBrakeNotReleaYellow = malFunctionInfosParkingBrakeNotReleaYellow;
    }

    public int getMalFunctionInfosPleaseAddFuelYellow() {
        return this.mMalFunctionInfosPleaseAddFuelYellow;
    }

    public void setMalFunctionInfosPleaseAddFuelYellow(int malFunctionInfosPleaseAddFuelYellow) {
        this.mMalFunctionInfosPleaseAddFuelYellow = malFunctionInfosPleaseAddFuelYellow;
    }

    public int getMalFunctionInfosPleaseChargeInTimeYellow() {
        return this.mMalFunctionInfosPleaseChargeInTimeYellow;
    }

    public void setMalFunctionInfosPleaseChargeInTimeYellow(int malFunctionInfosPleaseChargeInTimeYellow) {
        this.mMalFunctionInfosPleaseChargeInTimeYellow = malFunctionInfosPleaseChargeInTimeYellow;
    }

    public int getMalFunctionInfosPleaseCheckABSSysYellow() {
        return this.mMalFunctionInfosPleaseCheckABSSysYellow;
    }

    public void setMalFunctionInfosPleaseCheckABSSysYellow(int malFunctionInfosPleaseCheckABSSysYellow) {
        this.mMalFunctionInfosPleaseCheckABSSysYellow = malFunctionInfosPleaseCheckABSSysYellow;
    }

    public int getMalFunctionInfosPleaseCheckADBSystemRed() {
        return this.mMalFunctionInfosPleaseCheckADBSystemRed;
    }

    public void setMalFunctionInfosPleaseCheckADBSystemRed(int malFunctionInfosPleaseCheckADBSystemRed) {
        this.mMalFunctionInfosPleaseCheckADBSystemRed = malFunctionInfosPleaseCheckADBSystemRed;
    }

    public int getMalFunctionInfosPleaseCheckConnectorYellow() {
        return this.mMalFunctionInfosPleaseCheckConnectorYellow;
    }

    public void setMalFunctionInfosPleaseCheckConnectorYellow(int malFunctionInfosPleaseCheckConnectorYellow) {
        this.mMalFunctionInfosPleaseCheckConnectorYellow = malFunctionInfosPleaseCheckConnectorYellow;
    }

    public int getMalFunctionInfosPleaseCheckEPBRed() {
        return this.mMalFunctionInfosPleaseCheckEPBRed;
    }

    public void setMalFunctionInfosPleaseCheckEPBRed(int malFunctionInfosPleaseCheckEPBRed) {
        this.mMalFunctionInfosPleaseCheckEPBRed = malFunctionInfosPleaseCheckEPBRed;
    }

    public int getMalFunctionInfosPleaseCheckESPSysYellow() {
        return this.mMalFunctionInfosPleaseCheckESPSysYellow;
    }

    public void setMalFunctionInfosPleaseCheckESPSysYellow(int malFunctionInfosPleaseCheckESPSysYellow) {
        this.mMalFunctionInfosPleaseCheckESPSysYellow = malFunctionInfosPleaseCheckESPSysYellow;
    }

    public int getMalFunctionInfosPleaseCheckEngineSystemRed() {
        return this.mMalFunctionInfosPleaseCheckEngineSystemRed;
    }

    public void setMalFunctionInfosPleaseCheckEngineSystemRed(int malFunctionInfosPleaseCheckEngineSystemRed) {
        this.mMalFunctionInfosPleaseCheckEngineSystemRed = malFunctionInfosPleaseCheckEngineSystemRed;
    }

    public int getMalFunctionInfosPleaseCheckGearSysYellow() {
        return this.mMalFunctionInfosPleaseCheckGearSysYellow;
    }

    public void setMalFunctionInfosPleaseCheckGearSysYellow(int malFunctionInfosPleaseCheckGearSysYellow) {
        this.mMalFunctionInfosPleaseCheckGearSysYellow = malFunctionInfosPleaseCheckGearSysYellow;
    }

    public int getMalFunctionInfosPleaseCheckHDCSysYellow() {
        return this.mMalFunctionInfosPleaseCheckHDCSysYellow;
    }

    public void setMalFunctionInfosPleaseCheckHDCSysYellow(int malFunctionInfosPleaseCheckHDCSysYellow) {
        this.mMalFunctionInfosPleaseCheckHDCSysYellow = malFunctionInfosPleaseCheckHDCSysYellow;
    }

    public int getMalFunctionInfosPleaseCheckLBMSRed() {
        return this.mMalFunctionInfosPleaseCheckLBMSRed;
    }

    public void setMalFunctionInfosPleaseCheckLBMSRed(int malFunctionInfosPleaseCheckLBMSRed) {
        this.mMalFunctionInfosPleaseCheckLBMSRed = malFunctionInfosPleaseCheckLBMSRed;
    }

    public int getMalFunctionInfosPleaseCheckLVSupSysRed() {
        return this.mMalFunctionInfosPleaseCheckLVSupSysRed;
    }

    public void setMalFunctionInfosPleaseCheckLVSupSysRed(int malFunctionInfosPleaseCheckLVSupSysRed) {
        this.mMalFunctionInfosPleaseCheckLVSupSysRed = malFunctionInfosPleaseCheckLVSupSysRed;
    }

    public int getMalFunctionInfosPleaseCheckMemorySysYellow() {
        return this.mMalFunctionInfosPleaseCheckMemorySysYellow;
    }

    public void setMalFunctionInfosPleaseCheckMemorySysYellow(int malFunctionInfosPleaseCheckMemorySysYellow) {
        this.mMalFunctionInfosPleaseCheckMemorySysYellow = malFunctionInfosPleaseCheckMemorySysYellow;
    }

    public int getMalFunctionInfosPleaseCheckPowerSysYellow() {
        return this.mMalFunctionInfosPleaseCheckPowerSysYellow;
    }

    public void setMalFunctionInfosPleaseCheckPowerSysYellow(int malFunctionInfosPleaseCheckPowerSysYellow) {
        this.mMalFunctionInfosPleaseCheckPowerSysYellow = malFunctionInfosPleaseCheckPowerSysYellow;
    }

    public int getMalFunctionInfosPleaseCheckSRSSystemRed() {
        return this.mMalFunctionInfosPleaseCheckSRSSystemRed;
    }

    public void setMalFunctionInfosPleaseCheckSRSSystemRed(int malFunctionInfosPleaseCheckSRSSystemRed) {
        this.mMalFunctionInfosPleaseCheckSRSSystemRed = malFunctionInfosPleaseCheckSRSSystemRed;
    }

    public int getMalFunctionInfosPleaseCheckSunrooSysYellow() {
        return this.mMalFunctionInfosPleaseCheckSunrooSysYellow;
    }

    public void setMalFunctionInfosPleaseCheckSunrooSysYellow(int malFunctionInfosPleaseCheckSunrooSysYellow) {
        this.mMalFunctionInfosPleaseCheckSunrooSysYellow = malFunctionInfosPleaseCheckSunrooSysYellow;
    }

    public int getMalFunctionInfosPleaseCheckTheBrakeSysRed() {
        return this.mMalFunctionInfosPleaseCheckTheBrakeSysRed;
    }

    public void setMalFunctionInfosPleaseCheckTheBrakeSysRed(int malFunctionInfosPleaseCheckTheBrakeSysRed) {
        this.mMalFunctionInfosPleaseCheckTheBrakeSysRed = malFunctionInfosPleaseCheckTheBrakeSysRed;
    }

    public int getMalFunctionInfosPleaseCheckThePowerSysRed() {
        return this.mMalFunctionInfosPleaseCheckThePowerSysRed;
    }

    public void setMalFunctionInfosPleaseCheckThePowerSysRed(int malFunctionInfosPleaseCheckThePowerSysRed) {
        this.mMalFunctionInfosPleaseCheckThePowerSysRed = malFunctionInfosPleaseCheckThePowerSysRed;
    }

    public int getMalFunctionInfosPleaseCheckTheSteerSysRed() {
        return this.mMalFunctionInfosPleaseCheckTheSteerSysRed;
    }

    public void setMalFunctionInfosPleaseCheckTheSteerSysRed(int malFunctionInfosPleaseCheckTheSteerSysRed) {
        this.mMalFunctionInfosPleaseCheckTheSteerSysRed = malFunctionInfosPleaseCheckTheSteerSysRed;
    }

    public int getMalFunctionInfosPleaseCheckTransmiSys1Red() {
        return this.mMalFunctionInfosPleaseCheckTransmiSys1Red;
    }

    public void setMalFunctionInfosPleaseCheckTransmiSys1Red(int malFunctionInfosPleaseCheckTransmiSys1Red) {
        this.mMalFunctionInfosPleaseCheckTransmiSys1Red = malFunctionInfosPleaseCheckTransmiSys1Red;
    }

    public int getMalFunctionInfosPleaseCheckTransmisSys2Red() {
        return this.mMalFunctionInfosPleaseCheckTransmisSys2Red;
    }

    public void setMalFunctionInfosPleaseCheckTransmisSys2Red(int malFunctionInfosPleaseCheckTransmisSys2Red) {
        this.mMalFunctionInfosPleaseCheckTransmisSys2Red = malFunctionInfosPleaseCheckTransmisSys2Red;
    }

    public int getMalFunctionInfosPleaseCheckVehicleNetRed() {
        return this.mMalFunctionInfosPleaseCheckVehicleNetRed;
    }

    public void setMalFunctionInfosPleaseCheckVehicleNetRed(int malFunctionInfosPleaseCheckVehicleNetRed) {
        this.mMalFunctionInfosPleaseCheckVehicleNetRed = malFunctionInfosPleaseCheckVehicleNetRed;
    }

    public int getMalFunctionInfosPleaseGoBydServShopYellow() {
        return this.mMalFunctionInfosPleaseGoBydServShopYellow;
    }

    public void setMalFunctionInfosPleaseGoBydServShopYellow(int malFunctionInfosPleaseGoBydServShopYellow) {
        this.mMalFunctionInfosPleaseGoBydServShopYellow = malFunctionInfosPleaseGoBydServShopYellow;
    }

    public int getMalFunctionInfosPleaseLeaveVehicleImmdRed() {
        return this.mMalFunctionInfosPleaseLeaveVehicleImmdRed;
    }

    public void setMalFunctionInfosPleaseLeaveVehicleImmdRed(int malFunctionInfosPleaseLeaveVehicleImmdRed) {
        this.mMalFunctionInfosPleaseLeaveVehicleImmdRed = malFunctionInfosPleaseLeaveVehicleImmdRed;
    }

    public int getMalFunctionInfosPleaseStopDischargingYellow() {
        return this.mMalFunctionInfosPleaseStopDischargingYellow;
    }

    public void setMalFunctionInfosPleaseStopDischargingYellow(int malFunctionInfosPleaseStopDischargingYellow) {
        this.mMalFunctionInfosPleaseStopDischargingYellow = malFunctionInfosPleaseStopDischargingYellow;
    }

    public int getMalFunctionInfosRemoteCtrlDriveSuspeYellow() {
        return this.mMalFunctionInfosRemoteCtrlDriveSuspeYellow;
    }

    public void setMalFunctionInfosRemoteCtrlDriveSuspeYellow(int malFunctionInfosRemoteCtrlDriveSuspeYellow) {
        this.mMalFunctionInfosRemoteCtrlDriveSuspeYellow = malFunctionInfosRemoteCtrlDriveSuspeYellow;
    }

    public int getMalFunctionInfosRestrictedDriveFunctionRed() {
        return this.mMalFunctionInfosRestrictedDriveFunctionRed;
    }

    public void setMalFunctionInfosRestrictedDriveFunctionRed(int malFunctionInfosRestrictedDriveFunctionRed) {
        this.mMalFunctionInfosRestrictedDriveFunctionRed = malFunctionInfosRestrictedDriveFunctionRed;
    }

    public int getMalFunctionInfosSolenoidValveCleanWhiite() {
        return this.mMalFunctionInfosSolenoidValveCleanWhiite;
    }

    public void setMalFunctionInfosSolenoidValveCleanWhiite(int malFunctionInfosSolenoidValveCleanWhiite) {
        this.mMalFunctionInfosSolenoidValveCleanWhiite = malFunctionInfosSolenoidValveCleanWhiite;
    }

    public int getMalFunctionInfosStartTypeIronBatFaultyRed() {
        return this.mMalFunctionInfosStartTypeIronBatFaultyRed;
    }

    public void setMalFunctionInfosStartTypeIronBatFaultyRed(int malFunctionInfosStartTypeIronBatFaultyRed) {
        this.mMalFunctionInfosStartTypeIronBatFaultyRed = malFunctionInfosStartTypeIronBatFaultyRed;
    }

    public int getMalFunctionInfosSwitchToPRangeYellow() {
        return this.mMalFunctionInfosSwitchToPRangeYellow;
    }

    public void setMalFunctionInfosSwitchToPRangeYellow(int malFunctionInfosSwitchToPRangeYellow) {
        this.mMalFunctionInfosSwitchToPRangeYellow = malFunctionInfosSwitchToPRangeYellow;
    }

    public int getMalFunctionInfosTirePressureIsLowYellow() {
        return this.mMalFunctionInfosTirePressureIsLowYellow;
    }

    public void setMalFunctionInfosTirePressureIsLowYellow(int malFunctionInfosTirePressureIsLowYellow) {
        this.mMalFunctionInfosTirePressureIsLowYellow = malFunctionInfosTirePressureIsLowYellow;
    }

    public int getMalFunctionInfosTransmisLimitedinFuncRed() {
        return this.mMalFunctionInfosTransmisLimitedinFuncRed;
    }

    public void setMalFunctionInfosTransmisLimitedinFuncRed(int malFunctionInfosTransmisLimitedinFuncRed) {
        this.mMalFunctionInfosTransmisLimitedinFuncRed = malFunctionInfosTransmisLimitedinFuncRed;
    }

    public int getMalFunctionInfosVehicleEntForEvModeYellow() {
        return this.mMalFunctionInfosVehicleEntForEvModeYellow;
    }

    public void setMalFunctionInfosVehicleEntForEvModeYellow(int malFunctionInfosVehicleEntForEvModeYellow) {
        this.mMalFunctionInfosVehicleEntForEvModeYellow = malFunctionInfosVehicleEntForEvModeYellow;
    }

    public int getMalfunctionInfo() {
        return this.mMalfunctionInfo;
    }

    public void setMalfunctionInfo(int malfunctionInfo) {
        this.mMalfunctionInfo = malfunctionInfo;
    }

    public int getMilesByType() {
        return this.mMilesByType;
    }

    public void setMilesByType(int milesByType) {
        this.mMilesByType = milesByType;
    }

    public int getMilesTypeR() {
        return this.mMilesTypeR;
    }

    public void setMilesTypeR(int milesTypeR) {
        this.mMilesTypeR = milesTypeR;
    }

    public int getMilesTypeS() {
        return this.mMilesTypeS;
    }

    public void setMilesTypeS(int milesTypeS) {
        this.mMilesTypeS = milesTypeS;
    }

    public int getSportModeStateR() {
        return this.mSportModeState;
    }

    public void setSportModeStateR(int sportModeState) {
        this.mSportModeState = sportModeState;
    }

    public int getUnitFuelConsumptionAndDistanceInstrumentR() {
        return this.mUnitFuelConsumptionAndDistanceInstrumentR;
    }

    public void setUnitFuelConsumptionAndDistanceInstrumentR(int unitFuelConsumptionAndDistanceInstrumentR) {
        this.mUnitFuelConsumptionAndDistanceInstrumentR = unitFuelConsumptionAndDistanceInstrumentR;
    }

    public int getUnitFuelConsumptionAndDistanceLeftR() {
        return this.mUnitFuelConsumptionAndDistanceLeftR;
    }

    public void setUnitFuelConsumptionAndDistanceLeftR(int unitFuelConsumptionAndDistanceLeftR) {
        this.mUnitFuelConsumptionAndDistanceLeftR = unitFuelConsumptionAndDistanceLeftR;
    }

    public int getUnitFuelConsumptionAndDistanceS() {
        return this.mUnitFuelConsumptionAndDistanceS;
    }

    public void setUnitFuelConsumptionAndDistanceS(int unitFuelConsumptionAndDistanceS) {
        this.mUnitFuelConsumptionAndDistanceS = unitFuelConsumptionAndDistanceS;
    }

    public int getUnitPowerInstrumentR() {
        return this.mUnitPowerInstrumentR;
    }

    public void setUnitPowerInstrumentR(int unitPowerInstrumentR) {
        this.mUnitPowerInstrumentR = unitPowerInstrumentR;
    }

    public int getUnitPowerLeftR() {
        return this.mUnitPowerLeftR;
    }

    public void setUnitPowerLeftR(int unitPowerLeftR) {
        this.mUnitPowerLeftR = unitPowerLeftR;
    }

    public int getUnitPowerS() {
        return this.mUnitPowerS;
    }

    public void setUnitPowerS(int unitPowerS) {
        this.mUnitPowerS = unitPowerS;
    }

    public int getUnitPressureInstrumentR() {
        return this.mUnitPressureInstrumentR;
    }

    public void setUnitPressureInstrumentR(int unitPressureInstrumentR) {
        this.mUnitPressureInstrumentR = unitPressureInstrumentR;
    }

    public int getUnitPressureLeftR() {
        return this.mUnitPressureLeftR;
    }

    public void setUnitPressureLeftR(int unitPressureLeftR) {
        this.mUnitPressureLeftR = unitPressureLeftR;
    }

    public int getUnitPressureS() {
        return this.mUnitPressureS;
    }

    public void setUnitPressureS(int unitPressureS) {
        this.mUnitPressureS = unitPressureS;
    }

    public int getUnitTemperatureInstrumentR() {
        return this.mUnitTemperatureInstrumentR;
    }

    public void setUnitTemperatureInstrumentR(int unitTemperatureInstrumentR) {
        this.mUnitTemperatureInstrumentR = unitTemperatureInstrumentR;
    }

    public int getUnitTemperatureLeftR() {
        return this.mUnitTemperatureLeftR;
    }

    public void setUnitTemperatureLeftR(int unitTemperatureLeftR) {
        this.mUnitTemperatureLeftR = unitTemperatureLeftR;
    }

    public int getUnitTemperatureS() {
        return this.mUnitTemperatureS;
    }

    public void setUnitTemperatureS(int unitTemperatureS) {
        this.mUnitTemperatureS = unitTemperatureS;
    }

    public int getMalfunctionInfoSecond() {
        return this.mMalfunctionInfoSecond;
    }

    public void setMalfunctionInfoSecond(int malfunctionInfoSecond) {
        this.mMalfunctionInfoSecond = malfunctionInfoSecond;
    }

    public int getAreaInfoR() {
        return this.mAreaInfoR;
    }

    public void setAreaInfoR(int areaInfoR) {
        this.mAreaInfoR = areaInfoR;
    }

    public int getAccCounterUiR() {
        return this.mAccCounterUiR;
    }

    public void setAccCounterUiR(int accCounterUiR) {
        this.mAccCounterUiR = accCounterUiR;
    }
}
