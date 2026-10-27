package android.hardware.bydauto.instrument;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoInstrumentListener implements d {
    public static final String TAG = "AbsBYDAutoInstrumentListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void on2in1AccDistanceChanged(int i2) {
        Log.v(TAG, "on2in1AccDistanceChanged");
    }

    public void on2in1AccTextPromptChanged(int i2) {
        Log.v(TAG, "on2in1AccTextPromptChanged");
    }

    public void on2in1AccTimeDistanceChanged(int i2) {
        Log.v(TAG, "on2in1AccTimeDistanceChanged");
    }

    public void on2in1AccWorkInterfaceChanged(int i2) {
        Log.v(TAG, "on2in1AccWorkInterfaceChanged");
    }

    public void on2in1BodyPositionChanged(int i2) {
        Log.v(TAG, "on2in1BodyPositionChanged");
    }

    public void on2in1FaultFrontFogLightIndicatorChanged(int i2) {
        Log.v(TAG, "on2in1FaultFrontFogLightIndicatorChanged");
    }

    public void on2in1FaultFrontFogLightIndicatorColorChanged(int i2) {
        Log.v(TAG, "on2in1FaultFrontFogLightIndicatorColorChanged");
    }

    public void on2in1FaultGrassIndicatorChanged(int i2) {
        Log.v(TAG, "on2in1FaultGrassIndicatorChanged");
    }

    public void on2in1FaultGrassIndicatorColorChanged(int i2) {
        Log.v(TAG, "on2in1FaultGrassIndicatorColorChanged");
    }

    public void on2in1FaultSmallLightIndicatorChanged(int i2) {
        Log.v(TAG, "on2in1FaultSmallLightIndicatorChanged");
    }

    public void on2in1FaultSmallLightIndicatorColorChanged(int i2) {
        Log.v(TAG, "on2in1FaultSmallLightIndicatorColorChanged");
    }

    public void on2in1MenuStateChanged(int i2) {
        Log.v(TAG, "on2in1MenuStateChanged");
    }

    public void on50KmEneryConsumptionDisplayStateChanged(int i2) {
        Log.v(TAG, "on50KmEneryConsumptionDisplayStateChanged");
    }

    public void on50KmFuelConsumptionDisplayStateChanged(int i2) {
        Log.v(TAG, "on50KmFuelConsumptionDisplayStateChanged");
    }

    public void onABSFaultWarningLightChanged(int i2) {
        Log.v(TAG, "onABSFaultWarningLightChanged");
    }

    public void onABSFaultWarningLightColorChanged(int i2) {
        Log.v(TAG, "onABSFaultWarningLightColorChanged");
    }

    public void onACCIndicateLightColorChanged(int i2) {
        Log.v(TAG, "onACCIndicateLightColorChanged");
    }

    public void onACCIndicateLightStateChanged(int i2) {
        Log.v(TAG, "onACCIndicateLightStateChanged");
    }

    public void onAccCruisingSpeedChanged(int i2) {
        Log.v(TAG, "onAccCruisingSpeedChanged");
    }

    public void onAccCruisingSpeedColorChanged(int i2) {
        Log.v(TAG, "onAccCruisingSpeedColorChanged");
    }

    public void onAccCruisingSpeedValueChanged(int i2) {
        Log.v(TAG, "onAccCruisingSpeedValueChanged");
    }

    public void onAirHeatOilDisplayChanged(int i2) {
        Log.v(TAG, "onAirHeatOilDisplayChanged");
    }

    public void onAirHeatingDisplayStateChanged(int i2) {
        Log.v(TAG, "onAirHeatingDisplayStateChanged");
    }

    public void onAirHeatingOilWarnChanged(int i2) {
        Log.v(TAG, "onAirHeatingOilWarnChanged");
    }

    public void onAlarmBuzzleStateChange(int i2) {
        Log.v(TAG, "onAlarmBuzzleStateChange");
    }

    public void onAllWeatherLightsStateChanged(int i2) {
        Log.v(TAG, "onAllWeatherLightsStateChanged");
    }

    public void onAppCountdownHourChanged(int i2) {
        Log.v(TAG, "onAppCountdownHourChanged");
    }

    public void onAppCountdownMinuteChanged(int i2) {
        Log.v(TAG, "onAppCountdownMinuteChanged");
    }

    public void onAppointmentHourChanged(int i2) {
        Log.v(TAG, "onAppointmentHourChanged");
    }

    public void onAppointmentMinuteChanged(int i2) {
        Log.v(TAG, "onAppointmentMinuteChanged");
    }

    public void onAreaInfoChanged(int i2) {
        Log.v(TAG, "onAreaInfoChanged");
    }

    public void onAverageEneryConsumptionDisplayStateChanged(int i2) {
        Log.v(TAG, "onAverageEneryConsumptionDisplayStateChanged");
    }

    public void onAverageFuelConsumptionDisplayStateChanged(int i2) {
        Log.v(TAG, "onAverageFuelConsumptionDisplayStateChanged");
    }

    public void onAverageSpeedChanged(int i2) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public void onBacklightBrightnessChanged(int i2) {
        Log.v(TAG, "onBacklightBrightnessChanged");
    }

    public void onBacklightModeStateChanged(int i2, int i3) {
        Log.v(TAG, "onBacklightModeStateChanged");
    }

    public void onBatteryPercentChanged(int i2) {
        Log.v(TAG, "onBatteryPercentChanged");
    }

    public void onBrakeSysFaultLightColorChanged(int i2) {
        Log.v(TAG, "onBrakeSysFaultLightColorChanged");
    }

    public void onBrakeSysFaultLightStateChanged(int i2) {
        Log.v(TAG, "onBrakeSysFaultLightStateChanged");
    }

    public void onCallInfoResultChanged(int i2) {
        Log.v(TAG, "onCallInfoResultChanged");
    }

    public void onChargeAppTimeOptionChanged(int i2) {
        Log.v(TAG, "onChargeAppTimeOptionChanged");
    }

    public void onChargeDisplayChanged(int i2) {
        Log.v(TAG, "onChargeDisplayChanged");
    }

    public void onChargeNoticeChanged(int i2) {
        Log.v(TAG, "onChargeNoticeChanged");
    }

    public void onChargePercentChanged(int i2) {
        Log.v(TAG, "onChargePercentChanged");
    }

    public void onChargePowerChanged(float f2) {
        Log.v(TAG, "onChargePowerChanged");
    }

    public void onChargeRestTimeChanged(int[] iArr) {
        Log.v(TAG, "onChargeRestTimeChanged");
    }

    public void onCoolantTempHighWarnLightColorChanged(int i2) {
        Log.v(TAG, "onCoolantTempHighWarnLightColorChanged");
    }

    public void onCoolantTempHighWarnLightStateChanged(int i2) {
        Log.v(TAG, "onCoolantTempHighWarnLightStateChanged");
    }

    public void onCruiseCtrlIndicatorColorChanged(int i2) {
        Log.v(TAG, "onCruiseCtrlIndicatorColorChanged");
    }

    public void onCruiseCtrlIndicatorStateChanged(int i2) {
        Log.v(TAG, "onCruiseCtrlIndicatorStateChanged");
    }

    public void onCurrentDriveInterFaceChanged(int i2) {
        Log.v(TAG, "onCurrentDriveInterFaceChanged");
    }

    public void onCurrentJourneyDriveMileageChanged(float f2) {
        Log.v(TAG, "onCurrentJourneyDriveMileageChanged");
    }

    public void onCurrentJourneyDriveTimeChanged(float f2) {
        Log.v(TAG, "onCurrentJourneyDriveTimeChanged");
    }

    public void onDashboardAlarmStateChanged(int i2) {
        Log.v(TAG, "onDashboardAlarmStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDayNightModeChanged(int i2) {
        Log.v(TAG, "onDayNightModeChanged");
    }

    public void onDeviationStateChanged(int i2) {
        Log.v(TAG, "onDeviationStateChanged");
    }

    public void onDirectTypePressDisplayStateChanged(int i2) {
        Log.v(TAG, "onDirectTypePressDisplayStateChanged");
    }

    public void onDirectionInfoChanged(int i2) {
        Log.v(TAG, "onDirectionInfoChanged");
    }

    public void onDischargeElecEnergyChanged(float f2) {
        Log.v(TAG, "onDischargeElecEnergyChanged");
    }

    public void onDischargeIndicatorColorChanged(int i2) {
        Log.v(TAG, "onDischargeIndicatorColorChanged");
    }

    public void onDischargeModeChanged(int i2) {
        Log.v(TAG, "onDischargeModeChanged");
    }

    public void onDischargeUiStateChanged(int i2) {
        Log.v(TAG, "onDischargeUiStateChanged");
    }

    public void onDishargeIndicatorStateChanged(int i2) {
        Log.v(TAG, "onDishargeIndicatorStateChanged");
    }

    public void onDistanceMileageUnitChanged(int i2) {
        Log.v(TAG, "onDistanceMileageUnitChanged unit:" + i2);
    }

    public void onDoorStateChanged(int i2, int i3) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onDrivePowerLimitIndicatorColorChanged(int i2) {
        Log.v(TAG, "onDrivePowerLimitIndicatorColorChanged");
    }

    public void onDrivePowerLimitIndicatorStateChanged(int i2) {
        Log.v(TAG, "onDrivePowerLimitIndicatorStateChanged");
    }

    public void onECOIndicatorColorChanged(int i2) {
        Log.v(TAG, "onECOIndicatorColorChanged");
    }

    public void onECOIndicatorStateChanged(int i2) {
        Log.v(TAG, "onECOIndicatorStateChanged");
    }

    public void onELECParkingColorChanged(int i2) {
        Log.v(TAG, "onELECParkingColorChanged");
    }

    public void onELECParkingStateChanged(int i2) {
        Log.v(TAG, "onELECParkingStateChanged");
    }

    public void onESPFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onESPFailWarnLightColorChanged");
    }

    public void onESPFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onESPFailWarnLightStateChanged");
    }

    public void onEVIndicatorColorChanged(int i2) {
        Log.v(TAG, "onEVIndicatorColorChanged");
    }

    public void onEVIndicatorStateChanged(int i2) {
        Log.v(TAG, "onEVIndicatorStateChanged");
    }

    public void onEnergyDisplayChanged(int i2) {
        Log.v(TAG, "onEnergyDisplayChanged");
    }

    public void onEnergyFeedbackChanged(int i2) {
        Log.v(TAG, "onEnergyFeedbackChanged");
    }

    public void onEngineFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onEngineFailWarnLightColorChanged");
    }

    public void onEngineFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onEngineFailWarnLightStateChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onExpectChargeDisplayChanged(int i2) {
        Log.v(TAG, "onExpectChargeDisplayChanged");
    }

    public void onExpectChargeStateChanged(int i2) {
        Log.v(TAG, "onExpectChargeStateChanged");
    }

    public void onExternalChargePowerChanged(float f2) {
        Log.v(TAG, "onExternalChargePowerChanged");
    }

    public void onExternalChargingPowerChanged(float f2) {
        Log.v(TAG, "onExternalChargingPowerChanged");
    }

    public void onFaultIndicatorChanged(int i2, int i3) {
        Log.v(TAG, "onFaultIndicatorChanged");
    }

    public void onFaultIndicatorColorChanged(int i2, int i3) {
        Log.v(TAG, "onFaultIndicatorColorChanged");
    }

    public void onFaultMuddyIndicatorChanged(int i2) {
        Log.v(TAG, "onFaultMuddyIndicatorChanged");
    }

    public void onFaultMuddyIndicatorColorChanged(int i2) {
        Log.v(TAG, "onFaultMuddyIndicatorColorChanged");
    }

    public void onFaultNormalIndicatorChanged(int i2) {
        Log.v(TAG, "onFaultNormalIndicatorChanged");
    }

    public void onFaultNormalIndicatorColorChanged(int i2) {
        Log.v(TAG, "onFaultNormalIndicatorColorChanged");
    }

    public void onFaultOilLifeDetectIndicatorChanged(int i2) {
        Log.v(TAG, "onFaultOilLifeDetectIndicatorChanged");
    }

    public void onFaultOilLifeDetectIndicatorColorChanged(int i2) {
        Log.v(TAG, "onFaultOilLifeDetectIndicatorColorChanged");
    }

    public void onFaultSandIndicatorChanged(int i2) {
        Log.v(TAG, "onFaultSandIndicatorChanged");
    }

    public void onFaultSandIndicatorColorChanged(int i2) {
        Log.v(TAG, "onFaultSandIndicatorColorChanged");
    }

    public void onFirstMenuChanged(int i2) {
        Log.v(TAG, "onFirstMenuChanged");
    }

    public void onFuelConsumptionDisplayStateChanged(int i2) {
        Log.v(TAG, "onFuelConsumptionDisplayStateChanged");
    }

    public void onFuelLowAlarmChanged(int i2) {
        Log.v(TAG, "onFuelLowAlarmChanged");
    }

    public void onGPFIndicatorColorChanged(int i2) {
        Log.v(TAG, "onGPFIndicatorColorChanged");
    }

    public void onGPFIndicatorStateChanged(int i2) {
        Log.v(TAG, "onGPFIndicatorStateChanged");
    }

    public void onGapDetectionChanged(int i2) {
        Log.v(TAG, "onGapDetectionChanged");
    }

    public void onHEVIndicatorColorChanged(int i2) {
        Log.v(TAG, "onHEVIndicatorColorChanged");
    }

    public void onHEVIndicatorStateChanged(int i2) {
        Log.v(TAG, "onHEVIndicatorStateChanged");
    }

    public void onHeadlampFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onHeadlampFailWarnLightColorChanged");
    }

    public void onHeadlampFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onHeadlampFailWarnLightStateChanged");
    }

    public void onHeadlightControlModeChanged(int i2) {
        Log.v(TAG, "onHeadlightControlModeChanged");
    }

    public void onINSAdasCenterDisplayChanged(int i2) {
        Log.v(TAG, "onINSAdasCenterDisplayChanged");
    }

    public void onINSCenterDisplay(int i2) {
        Log.v(TAG, "onINSCenterDisplay");
    }

    public void onINSNavDisplayMode(int i2) {
        Log.v(TAG, "onINSNavDisplayMode");
    }

    public void onInsUiChanged(int i2) {
        Log.v(TAG, "onInsUiChanged");
    }

    public void onInstantFuelConsumptionDisplayStateChanged(int i2) {
        Log.v(TAG, "onInstantFuelConsumptionDisplayStateChanged");
    }

    public void onInstantFuelConsumptionUnitChanged(int i2) {
        Log.v(TAG, "onInstantFuelConsumptionUnitChanged");
    }

    @Deprecated
    public void onInstrumentAutoDimmStateChange(int i2) {
        Log.v(TAG, "onInstrumentAutoDimmStateChange");
    }

    public void onInstrumentBrightnessChanged(int i2) {
        Log.v(TAG, "onInstrumentBrightnessChanged");
    }

    public void onInstrumentScreenTypeChanged(int i2) {
        Log.v(TAG, "onInstrumentScreenTypeChanged");
    }

    public void onInstrumentThemeChange(int i2) {
        Log.v(TAG, "onInstrumentThemeChange");
    }

    public void onInstrumentViewChanged(int i2) {
        Log.v(TAG, "onInstrumentViewChanged");
    }

    public void onKeyDetectionReminderChanged(int i2) {
        Log.v(TAG, "onKeyDetectionReminderChanged");
    }

    public void onLaneLineColorChanged(int i2) {
        Log.v(TAG, "onLaneLineColorChanged");
    }

    public void onLaneLineStateChanged(int i2) {
        Log.v(TAG, "onLaneLineStateChanged");
    }

    public void onLast50KmPowerConsumeChanged(float f2) {
        Log.v(TAG, "onLast50KmPowerConsumeChanged");
    }

    public void onLineValueChanged(int i2, int i3) {
        Log.v(TAG, "onLineValueChanged");
    }

    public void onLinkErrKeyTimeChanged(int i2, int i3) {
        Log.v(TAG, "onLinkErrKeyTimeChanged");
    }

    public void onLowFuelWarnLightColorChanged(int i2) {
        Log.v(TAG, "onLowFuelWarnLightColorChanged");
    }

    public void onLowFuelWarnLightStateChanged(int i2) {
        Log.v(TAG, "onLowFuelWarnLightStateChanged");
    }

    public void onLowPowerBatteryWarnLightColorChanged(int i2) {
        Log.v(TAG, "onLowPowerBatteryWarnLightColorChanged");
    }

    public void onLowPowerBatteryWarnLightStateChanged(int i2) {
        Log.v(TAG, "onLowPowerBatteryWarnLightStateChanged");
    }

    public void onMainAlarmIndicatorColorChanged(int i2) {
        Log.v(TAG, "onMainAlarmIndicatorColorChanged");
    }

    public void onMainAlarmIndicatorStateChanged(int i2) {
        Log.v(TAG, "onMainAlarmIndicatorStateChanged");
    }

    public void onMaintenanceInfoChanged(int i2, int i3) {
        Log.v(TAG, "onMaintenanceInfoChanged");
    }

    public void onMaintenanceInfoStateChanged(int i2, int i3) {
        Log.v(TAG, "onMaintenanceInfoStateChanged");
    }

    public void onMalfunctionInfoChanged(int i2, int i3) {
        Log.v(TAG, "onMalfunctionInfoChanged");
    }

    public void onMalfunctionInfoChanged2(int i2, int i3) {
        Log.v(TAG, "onMalfunctionInfoChanged2");
    }

    public void onMapFocusChanged(int i2) {
        Log.v(TAG, "onMapFocusChanged");
    }

    public void onMasterVolumeChanged(int i2, int i3) {
        Log.v(TAG, "onMasterVolumeChanged");
    }

    public void onMileageUnitChanged(int i2) {
        Log.v(TAG, "onMileageUnitChanged");
    }

    public void onMileageValidFlagChanged(int i2) {
        Log.v(TAG, "onMileageValidFlagChanged");
    }

    public void onMilesTypeChanged(int i2) {
        Log.v(TAG, "onMilesTypeChanged");
    }

    public void onModuleStateChanged(int i2, int i3) {
        Log.v(TAG, "onModuleStateChanged");
    }

    public void onMusicChannelStatusChanged(int i2, int i3) {
        Log.v(TAG, "onMusicChannelStatusChanged");
    }

    public void onMusicControlStatusChanged(int i2, int i3) {
        Log.v(TAG, "onMusicControlStatusChanged");
    }

    public void onMusicInfoResultChanged(int i2) {
        Log.v(TAG, "onMusicInfoResultChanged");
    }

    public void onMusicInfoUrlChange(String str, String str2, int i2, int i3, String str3) {
        Log.v(TAG, "onMusicInfoUrlChange");
    }

    public void onMusicProgressChanged(int i2, int i3) {
        Log.v(TAG, "onMusicProgressChanged");
    }

    public void onMusicResourceConfigChanged(int i2, int i3) {
        Log.v(TAG, "onMusicResourceConfigChanged");
    }

    public void onMuteStateChanged(int i2, int i3) {
        Log.v(TAG, "onMuteStateChanged");
    }

    public void onNaviDestinationCommandChanged(int i2) {
        Log.v(TAG, "onNaviDestinationCommandChanged");
    }

    public void onOKIndicatorColorChanged(int i2) {
        Log.v(TAG, "onOKIndicatorColorChanged");
    }

    public void onOKIndicatorStateChanged(int i2) {
        Log.v(TAG, "onOKIndicatorStateChanged");
    }

    public void onOdometerDisplayChanged(int i2) {
        Log.v(TAG, "onOdometerDisplayChanged");
    }

    public void onOilLevelAlarmIndicatorChanged(int i2) {
        Log.v(TAG, "onOilLevelAlarmIndicatorChanged");
    }

    public void onOilLevelAlarmIndicatorColorChanged(int i2) {
        Log.v(TAG, "onOilLevelAlarmIndicatorColorChanged");
    }

    public void onOutCarTemperatureChanged(int i2) {
        Log.v(TAG, "onOutCarTemperatureChanged");
    }

    public void onPCWAlarmInstructionChanged(int i2) {
        Log.v(TAG, "onPCWAlarmInstructionChanged");
    }

    public void onPowerBatFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onPowerBatFailWarnLightColorChanged");
    }

    public void onPowerBatFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onPowerBatFailWarnLightStateChanged");
    }

    public void onPowerBatteryChargeConnectIndicatorColorChanged(int i2) {
        Log.v(TAG, "onPowerBatteryChargeConnectIndicatorColorChanged");
    }

    public void onPowerBatteryChargeConnectIndicatorStateChanged(int i2) {
        Log.v(TAG, "onPowerBatteryChargeConnectIndicatorStateChanged");
    }

    public void onPowerBatteryHeatWarnLightColorChanged(int i2) {
        Log.v(TAG, "onPowerBatteryHeatWarnLightColorChanged");
    }

    public void onPowerBatteryHeatWarnLightStateChanged(int i2) {
        Log.v(TAG, "onPowerBatteryHeatWarnLightStateChanged");
    }

    public void onPowerOffErrInfoChanged(int i2) {
        Log.v(TAG, "onPowerOffErrInfoChanged");
    }

    public void onPowerOnErrInfoChanged(int i2) {
        Log.v(TAG, "onPowerOnErrInfoChanged");
    }

    public void onPowerSysFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onPowerSysFailWarnLightColorChanged");
    }

    public void onPowerSysFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onPowerSysFailWarnLightStateChanged");
    }

    public void onPowerUnitChanged(int i2) {
        Log.v(TAG, "onPowerUnitChanged");
    }

    public void onPressureSupplySysFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onPressureSupplySysFailWarnLightColorChanged");
    }

    public void onPressureSupplySysFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onPressureSupplySysFailWarnLightStateChanged");
    }

    public void onPressureWarnLightColorChanged(int i2) {
        Log.v(TAG, "onPressureWarnLightColorChanged");
    }

    public void onPressureWarnLightStateChanged(int i2) {
        Log.v(TAG, "onPressureWarnLightStateChanged");
    }

    public void onRadioInfoResultChanged(int i2) {
        Log.v(TAG, "onRadioInfoResultChanged");
    }

    public void onRearFogLightStateChanged(int i2) {
        Log.v(TAG, "onRearFogLightStateChanged");
    }

    public void onRemoteDrivingReminderChanged(int i2) {
        Log.v(TAG, "onRemoteDrivingReminderChanged");
    }

    public void onRoadNameCheckStateChanged(int i2) {
        Log.v(TAG, "onRoadNameCheckStateChanged");
    }

    public void onSRSFaultWarningLightChanged(int i2) {
        Log.v(TAG, "onSRSFaultWarningLightChanged");
    }

    public void onSRSFaultWarningLightColorChanged(int i2) {
        Log.v(TAG, "onSRSFaultWarningLightColorChanged");
    }

    public void onSafetyBeltStatusChanged(int i2, int i3) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }

    public void onSecondMenuChanged(int i2) {
        Log.v(TAG, "onSecondMenuChanged");
    }

    public void onSmartKeySysWarnLightColorChanged(int i2) {
        Log.v(TAG, "onSmartKeySysWarnLightColorChanged");
    }

    public void onSmartKeySysWarnLightStateChanged(int i2) {
        Log.v(TAG, "onSmartKeySysWarnLightStateChanged");
    }

    public void onSoundFreqChanged(int i2) {
        Log.v(TAG, "onSoundFreqChanged");
    }

    public void onSoundTypeChanged(int i2) {
        Log.v(TAG, "onSoundTypeChanged");
    }

    public void onSpacingStateChanged(int i2) {
        Log.v(TAG, "onSpacingStateChanged");
    }

    public void onSpeedUnitChanged(int i2) {
        Log.v(TAG, "onSpeedUnitChanged");
    }

    public void onSpeedUpCountStateChanged(int i2) {
        Log.v(TAG, "onSpeedUpCountStateChanged");
    }

    public void onSpeedUpFormInsChange(int i2) {
        Log.v(TAG, "onSpeedUpFormInsChange");
    }

    public void onSportIndicatorColorChanged(int i2) {
        Log.v(TAG, "onSportIndicatorColorChanged");
    }

    public void onSportIndicatorStateChanged(int i2) {
        Log.v(TAG, "onSportIndicatorStateChanged");
    }

    public void onSportModeStateChanged(int i2) {
        Log.v(TAG, "onSportModeStateChanged state:" + i2);
    }

    public void onSteeringSYSFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onSteeringSYSFailWarnLightColorChanged");
    }

    public void onSteeringSYSFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onSteeringSYSFailWarnLightStateChanged");
    }

    public void onTextColorChanged(int i2) {
        Log.v(TAG, "onTextColorChanged");
    }

    public void onTextInfoChanged(int i2) {
        Log.v(TAG, "onTextInfoChanged");
    }

    public void onTimeIntervalStateChanged(int i2) {
        Log.v(TAG, "onTimeIntervalStateChanged");
    }

    public void onTotalMileageChanged(int i2) {
        Log.v(TAG, "onTotalMileageChanged");
    }

    public void onTravelTimeChanged(float f2) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onTyrePressureCarTypeChanged(int i2) {
        Log.v(TAG, "onTyrePressureCarTypeChanged");
    }

    public void onTyrePressureSYSFailWarnLightColorChanged(int i2) {
        Log.v(TAG, "onTyrePressureSYSFailWarnLightColorChanged");
    }

    public void onTyrePressureSYSFailWarnLightStateChanged(int i2) {
        Log.v(TAG, "onTyrePressureSYSFailWarnLightStateChanged");
    }

    public void onUnitChanged(int i2, int i3) {
        Log.v(TAG, "onUnitChanged");
    }

    public void onViewStatusChanged(int i2) {
        Log.v(TAG, "onViewStatusChanged");
    }

    public void onWaterTempMeterPercentChanged(float f2) {
        Log.v(TAG, "onWaterTempMeterPercentChanged");
    }

    public void onWheelColorChanged(int i2, int i3) {
        Log.v(TAG, "onWheelColorChanged");
    }

    public void onWheelPressureChanged(int i2, int i3) {
        Log.v(TAG, "onWheelPressureChanged");
    }

    public void onWheelTemperatureChanged(int i2, int i3) {
        Log.v(TAG, "onWheelTemperatureChanged");
    }

    public void onWheelTemperatureColorChanged(int i2, int i3) {
        Log.v(TAG, "onWheelTemperatureColorChanged");
    }
}
