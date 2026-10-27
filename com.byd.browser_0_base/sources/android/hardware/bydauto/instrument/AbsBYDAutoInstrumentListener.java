package android.hardware.bydauto.instrument;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoInstrumentListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoInstrumentListener";

    public void on2in1AccDistanceChanged(int i) {
    }

    public void on2in1AccTextPromptChanged(int i) {
    }

    public void on2in1AccTimeDistanceChanged(int i) {
    }

    public void on2in1AccWorkInterfaceChanged(int i) {
    }

    public void on2in1BodyPositionChanged(int i) {
    }

    public void on2in1FaultFrontFogLightIndicatorChanged(int i) {
    }

    public void on2in1FaultFrontFogLightIndicatorColorChanged(int i) {
    }

    public void on2in1FaultGrassIndicatorChanged(int i) {
    }

    public void on2in1FaultGrassIndicatorColorChanged(int i) {
    }

    public void on2in1FaultSmallLightIndicatorChanged(int i) {
    }

    public void on2in1FaultSmallLightIndicatorColorChanged(int i) {
    }

    public void on2in1MenuStateChanged(int i) {
    }

    public void on50KmEneryConsumptionDisplayStateChanged(int i) {
    }

    public void on50KmFuelConsumptionDisplayStateChanged(int i) {
    }

    public void onABSFaultWarningLightChanged(int i) {
    }

    public void onABSFaultWarningLightColorChanged(int i) {
    }

    public void onACCIndicateLightColorChanged(int i) {
    }

    public void onACCIndicateLightStateChanged(int i) {
    }

    public void onAccCruisingSpeedChanged(int i) {
    }

    public void onAccCruisingSpeedColorChanged(int i) {
    }

    public void onAccCruisingSpeedValueChanged(int i) {
    }

    public void onAirHeatOilDisplayChanged(int i) {
    }

    public void onAirHeatingDisplayStateChanged(int i) {
    }

    public void onAirHeatingOilWarnChanged(int i) {
    }

    public void onAlarmBuzzleStateChange(int i) {
    }

    public void onAllWeatherLightsStateChanged(int i) {
    }

    public void onAppCountdownHourChanged(int i) {
    }

    public void onAppCountdownMinuteChanged(int i) {
    }

    public void onAppointmentHourChanged(int i) {
    }

    public void onAppointmentMinuteChanged(int i) {
    }

    public void onAreaInfoChanged(int i) {
    }

    public void onAverageEneryConsumptionDisplayStateChanged(int i) {
    }

    public void onAverageFuelConsumptionDisplayStateChanged(int i) {
    }

    public void onAverageSpeedChanged(int i) {
    }

    public void onBacklightBrightnessChanged(int i) {
    }

    public void onBatteryPercentChanged(int i) {
    }

    public void onBrakeSysFaultLightColorChanged(int i) {
    }

    public void onBrakeSysFaultLightStateChanged(int i) {
    }

    public void onCallInfoResultChanged(int i) {
    }

    public void onChargeAppTimeOptionChanged(int i) {
    }

    public void onChargeDisplayChanged(int i) {
    }

    public void onChargeNoticeChanged(int i) {
    }

    public void onChargePercentChanged(int i) {
    }

    public void onChargePowerChanged(float f) {
    }

    public void onChargeRestTimeChanged(int[] iArr) {
    }

    public void onCoolantTempHighWarnLightColorChanged(int i) {
    }

    public void onCoolantTempHighWarnLightStateChanged(int i) {
    }

    public void onCruiseCtrlIndicatorColorChanged(int i) {
    }

    public void onCruiseCtrlIndicatorStateChanged(int i) {
    }

    public void onCurrentDriveInterFaceChanged(int i) {
    }

    public void onCurrentJourneyDriveMileageChanged(float f) {
    }

    public void onCurrentJourneyDriveTimeChanged(float f) {
    }

    public void onDashboardAlarmStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDayNightModeChanged(int i) {
    }

    public void onDeviationStateChanged(int i) {
    }

    public void onDirectTypePressDisplayStateChanged(int i) {
    }

    public void onDirectionInfoChanged(int i) {
    }

    public void onDischargeElecEnergyChanged(float f) {
    }

    public void onDischargeIndicatorColorChanged(int i) {
    }

    public void onDischargeModeChanged(int i) {
    }

    public void onDischargeUiStateChanged(int i) {
    }

    public void onDishargeIndicatorStateChanged(int i) {
    }

    public void onDistanceMileageUnitChanged(int i) {
    }

    public void onDrivePowerLimitIndicatorColorChanged(int i) {
    }

    public void onDrivePowerLimitIndicatorStateChanged(int i) {
    }

    public void onECOIndicatorColorChanged(int i) {
    }

    public void onECOIndicatorStateChanged(int i) {
    }

    public void onELECParkingColorChanged(int i) {
    }

    public void onELECParkingStateChanged(int i) {
    }

    public void onESPFailWarnLightColorChanged(int i) {
    }

    public void onESPFailWarnLightStateChanged(int i) {
    }

    public void onEVIndicatorColorChanged(int i) {
    }

    public void onEVIndicatorStateChanged(int i) {
    }

    public void onEnergyDisplayChanged(int i) {
    }

    public void onEnergyFeedbackChanged(int i) {
    }

    public void onEngineFailWarnLightColorChanged(int i) {
    }

    public void onEngineFailWarnLightStateChanged(int i) {
    }

    public void onExpectChargeDisplayChanged(int i) {
    }

    public void onExpectChargeStateChanged(int i) {
    }

    public void onExternalChargePowerChanged(float f) {
    }

    public void onExternalChargingPowerChanged(float f) {
    }

    public void onFaultMuddyIndicatorChanged(int i) {
    }

    public void onFaultMuddyIndicatorColorChanged(int i) {
    }

    public void onFaultNormalIndicatorChanged(int i) {
    }

    public void onFaultNormalIndicatorColorChanged(int i) {
    }

    public void onFaultOilLifeDetectIndicatorChanged(int i) {
    }

    public void onFaultOilLifeDetectIndicatorColorChanged(int i) {
    }

    public void onFaultSandIndicatorChanged(int i) {
    }

    public void onFaultSandIndicatorColorChanged(int i) {
    }

    public void onFirstMenuChanged(int i) {
    }

    public void onFuelConsumptionDisplayStateChanged(int i) {
    }

    public void onFuelLowAlarmChanged(int i) {
    }

    public void onGPFIndicatorColorChanged(int i) {
    }

    public void onGPFIndicatorStateChanged(int i) {
    }

    public void onGapDetectionChanged(int i) {
    }

    public void onHEVIndicatorColorChanged(int i) {
    }

    public void onHEVIndicatorStateChanged(int i) {
    }

    public void onHeadlampFailWarnLightColorChanged(int i) {
    }

    public void onHeadlampFailWarnLightStateChanged(int i) {
    }

    public void onHeadlightControlModeChanged(int i) {
    }

    public void onINSAdasCenterDisplayChanged(int i) {
    }

    public void onINSCenterDisplay(int i) {
    }

    public void onINSNavDisplayMode(int i) {
    }

    public void onInsUiChanged(int i) {
    }

    public void onInstantFuelConsumptionDisplayStateChanged(int i) {
    }

    public void onInstantFuelConsumptionUnitChanged(int i) {
    }

    @Deprecated
    public void onInstrumentAutoDimmStateChange(int i) {
    }

    public void onInstrumentBrightnessChanged(int i) {
    }

    public void onInstrumentScreenTypeChanged(int i) {
    }

    public void onInstrumentThemeChange(int i) {
    }

    public void onInstrumentViewChanged(int i) {
    }

    public void onKeyDetectionReminderChanged(int i) {
    }

    public void onLaneLineColorChanged(int i) {
    }

    public void onLaneLineStateChanged(int i) {
    }

    public void onLast50KmPowerConsumeChanged(float f) {
    }

    public void onLowFuelWarnLightColorChanged(int i) {
    }

    public void onLowFuelWarnLightStateChanged(int i) {
    }

    public void onLowPowerBatteryWarnLightColorChanged(int i) {
    }

    public void onLowPowerBatteryWarnLightStateChanged(int i) {
    }

    public void onMainAlarmIndicatorColorChanged(int i) {
    }

    public void onMainAlarmIndicatorStateChanged(int i) {
    }

    public void onMapFocusChanged(int i) {
    }

    public void onMileageUnitChanged(int i) {
    }

    public void onMileageValidFlagChanged(int i) {
    }

    public void onMilesTypeChanged(int i) {
    }

    public void onMusicInfoResultChanged(int i) {
    }

    public void onNaviDestinationCommandChanged(int i) {
    }

    public void onOKIndicatorColorChanged(int i) {
    }

    public void onOKIndicatorStateChanged(int i) {
    }

    public void onOdometerDisplayChanged(int i) {
    }

    public void onOilLevelAlarmIndicatorChanged(int i) {
    }

    public void onOilLevelAlarmIndicatorColorChanged(int i) {
    }

    public void onOutCarTemperatureChanged(int i) {
    }

    public void onPCWAlarmInstructionChanged(int i) {
    }

    public void onPowerBatFailWarnLightColorChanged(int i) {
    }

    public void onPowerBatFailWarnLightStateChanged(int i) {
    }

    public void onPowerBatteryChargeConnectIndicatorColorChanged(int i) {
    }

    public void onPowerBatteryChargeConnectIndicatorStateChanged(int i) {
    }

    public void onPowerBatteryHeatWarnLightColorChanged(int i) {
    }

    public void onPowerBatteryHeatWarnLightStateChanged(int i) {
    }

    public void onPowerOffErrInfoChanged(int i) {
    }

    public void onPowerOnErrInfoChanged(int i) {
    }

    public void onPowerSysFailWarnLightColorChanged(int i) {
    }

    public void onPowerSysFailWarnLightStateChanged(int i) {
    }

    public void onPowerUnitChanged(int i) {
    }

    public void onPressureSupplySysFailWarnLightColorChanged(int i) {
    }

    public void onPressureSupplySysFailWarnLightStateChanged(int i) {
    }

    public void onPressureWarnLightColorChanged(int i) {
    }

    public void onPressureWarnLightStateChanged(int i) {
    }

    public void onRadioInfoResultChanged(int i) {
    }

    public void onRearFogLightStateChanged(int i) {
    }

    public void onRemoteDrivingReminderChanged(int i) {
    }

    public void onRoadNameCheckStateChanged(int i) {
    }

    public void onSRSFaultWarningLightChanged(int i) {
    }

    public void onSRSFaultWarningLightColorChanged(int i) {
    }

    public void onSecondMenuChanged(int i) {
    }

    public void onSmartKeySysWarnLightColorChanged(int i) {
    }

    public void onSmartKeySysWarnLightStateChanged(int i) {
    }

    public void onSoundFreqChanged(int i) {
    }

    public void onSoundTypeChanged(int i) {
    }

    public void onSpacingStateChanged(int i) {
    }

    public void onSpeedUnitChanged(int i) {
    }

    public void onSpeedUpCountStateChanged(int i) {
    }

    public void onSpeedUpFormInsChange(int i) {
    }

    public void onSportIndicatorColorChanged(int i) {
    }

    public void onSportIndicatorStateChanged(int i) {
    }

    public void onSportModeStateChanged(int i) {
    }

    public void onSteeringSYSFailWarnLightColorChanged(int i) {
    }

    public void onSteeringSYSFailWarnLightStateChanged(int i) {
    }

    public void onTextColorChanged(int i) {
    }

    public void onTextInfoChanged(int i) {
    }

    public void onTimeIntervalStateChanged(int i) {
    }

    public void onTotalMileageChanged(int i) {
    }

    public void onTravelTimeChanged(float f) {
    }

    public void onTyrePressureCarTypeChanged(int i) {
    }

    public void onTyrePressureSYSFailWarnLightColorChanged(int i) {
    }

    public void onTyrePressureSYSFailWarnLightStateChanged(int i) {
    }

    public void onViewStatusChanged(int i) {
    }

    public void onWaterTempMeterPercentChanged(float f) {
    }

    public void onBacklightModeStateChanged(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDoorStateChanged(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFaultIndicatorChanged(int i, int i2) {
    }

    public void onFaultIndicatorColorChanged(int i, int i2) {
    }

    public void onLineValueChanged(int i, int i2) {
    }

    public void onLinkErrKeyTimeChanged(int i, int i2) {
    }

    public void onMaintenanceInfoChanged(int i, int i2) {
    }

    public void onMaintenanceInfoStateChanged(int i, int i2) {
    }

    public void onMalfunctionInfoChanged(int i, int i2) {
    }

    public void onMalfunctionInfoChanged2(int i, int i2) {
    }

    public void onMasterVolumeChanged(int i, int i2) {
    }

    public void onModuleStateChanged(int i, int i2) {
    }

    public void onMusicChannelStatusChanged(int i, int i2) {
    }

    public void onMusicControlStatusChanged(int i, int i2) {
    }

    public void onMusicProgressChanged(int i, int i2) {
    }

    public void onMusicResourceConfigChanged(int i, int i2) {
    }

    public void onMuteStateChanged(int i, int i2) {
    }

    public void onSafetyBeltStatusChanged(int i, int i2) {
    }

    public void onUnitChanged(int i, int i2) {
    }

    public void onWheelColorChanged(int i, int i2) {
    }

    public void onWheelPressureChanged(int i, int i2) {
    }

    public void onWheelTemperatureChanged(int i, int i2) {
    }

    public void onWheelTemperatureColorChanged(int i, int i2) {
    }

    public void onMusicInfoUrlChange(String str, String str2, int i, int i2, String str3) {
    }
}
