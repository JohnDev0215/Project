package android.hardware.bydauto.instrument;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoInstrumentListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoInstrumentListener";

    public void onMalfunctionInfoChanged(int i, int i2) {
        Log.v(TAG, "onMalfunctionInfoChanged");
    }

    public void onMalfunctionInfoChanged2(int i, int i2) {
        Log.v(TAG, "onMalfunctionInfoChanged2");
    }

    public void onBacklightModeStateChanged(int i, int i2) {
        Log.v(TAG, "onBacklightModeStateChanged");
    }

    public void onBacklightBrightnessChanged(int i) {
        Log.v(TAG, "onBacklightBrightnessChanged");
    }

    public void onUnitChanged(int i, int i2) {
        Log.v(TAG, "onUnitChanged");
    }

    public void onMaintenanceInfoChanged(int i, int i2) {
        Log.v(TAG, "onMaintenanceInfoChanged");
    }

    public void onMusicInfoResultChanged(int i) {
        Log.v(TAG, "onMusicInfoResultChanged");
    }

    public void onMusicInfoUrlChange(String str, String str2, int i, int i2, String str3) {
        Log.v(TAG, "onMusicInfoUrlChange");
    }

    public void onCallInfoResultChanged(int i) {
        Log.v(TAG, "onCallInfoResultChanged");
    }

    public void onRadioInfoResultChanged(int i) {
        Log.v(TAG, "onRadioInfoResultChanged");
    }

    public void onAlarmBuzzleStateChange(int i) {
        Log.v(TAG, "onAlarmBuzzleStateChange");
    }

    public void onPowerOnErrInfoChanged(int i) {
        Log.v(TAG, "onPowerOnErrInfoChanged");
    }

    public void onPowerOffErrInfoChanged(int i) {
        Log.v(TAG, "onPowerOffErrInfoChanged");
    }

    public void onRemoteDrivingReminderChanged(int i) {
        Log.v(TAG, "onRemoteDrivingReminderChanged");
    }

    public void onKeyDetectionReminderChanged(int i) {
        Log.v(TAG, "onKeyDetectionReminderChanged");
    }

    public void onAverageSpeedChanged(int i) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public void onExternalChargingPowerChanged(float f) {
        Log.v(TAG, "onExternalChargingPowerChanged");
    }

    public void onInstrumentScreenTypeChanged(int i) {
        Log.v(TAG, "onInstrumentScreenTypeChanged");
    }

    public void onNaviDestinationCommandChanged(int i) {
        Log.v(TAG, "onNaviDestinationCommandChanged");
    }

    public void onRoadNameCheckStateChanged(int i) {
        Log.v(TAG, "onRoadNameCheckStateChanged");
    }

    public void onTextInfoChanged(int i) {
        Log.v(TAG, "onTextInfoChanged");
    }

    public void onModuleStateChanged(int i, int i2) {
        Log.v(TAG, "onModuleStateChanged");
    }

    public void onDoorStateChanged(int i, int i2) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onSafetyBeltStatusChanged(int i, int i2) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }

    public void onWheelColorChanged(int i, int i2) {
        Log.v(TAG, "onWheelColorChanged");
    }

    public void onWheelTemperatureColorChanged(int i, int i2) {
        Log.v(TAG, "onWheelTemperatureColorChanged");
    }

    public void onWheelPressureChanged(int i, int i2) {
        Log.v(TAG, "onWheelPressureChanged");
    }

    public void onWheelTemperatureChanged(int i, int i2) {
        Log.v(TAG, "onWheelTemperatureChanged");
    }

    public void onDeviationStateChanged(int i) {
        Log.v(TAG, "onDeviationStateChanged");
    }

    public void onGapDetectionChanged(int i) {
        Log.v(TAG, "onGapDetectionChanged");
    }

    public void onLaneLineStateChanged(int i) {
        Log.v(TAG, "onLaneLineStateChanged");
    }

    public void onTimeIntervalStateChanged(int i) {
        Log.v(TAG, "onTimeIntervalStateChanged");
    }

    public void onEnergyFeedbackChanged(int i) {
        Log.v(TAG, "onEnergyFeedbackChanged");
    }

    public void onTextColorChanged(int i) {
        Log.v(TAG, "onTextColorChanged");
    }

    public void onSpacingStateChanged(int i) {
        Log.v(TAG, "onSpacingStateChanged");
    }

    public void onSoundTypeChanged(int i) {
        Log.v(TAG, "onSoundTypeChanged");
    }

    public void onAccCruisingSpeedChanged(int i) {
        Log.v(TAG, "onAccCruisingSpeedChanged");
    }

    public void onAccCruisingSpeedColorChanged(int i) {
        Log.v(TAG, "onAccCruisingSpeedColorChanged");
    }

    public void onPCWAlarmInstructionChanged(int i) {
        Log.v(TAG, "onPCWAlarmInstructionChanged");
    }

    public void onLaneLineColorChanged(int i) {
        Log.v(TAG, "onLaneLineColorChanged");
    }

    public void onTotalMileageChanged(int i) {
        Log.v(TAG, "onTotalMileageChanged");
    }

    public void onMileageUnitChanged(int i) {
        Log.v(TAG, "onMileageUnitChanged");
    }

    public void onLast50KmPowerConsumeChanged(float f) {
        Log.v(TAG, "onLast50KmPowerConsumeChanged");
    }

    public void onSpeedUnitChanged(int i) {
        Log.v(TAG, "onSpeedUnitChanged");
    }

    public void onBatteryPercentChanged(int i) {
        Log.v(TAG, "onBatteryPercentChanged");
    }

    public void onExternalChargePowerChanged(float f) {
        Log.v(TAG, "onExternalChargePowerChanged");
    }

    public void onTravelTimeChanged(float f) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onPowerUnitChanged(int i) {
        Log.v(TAG, "onPowerUnitChanged");
    }

    public void onAirHeatOilDisplayChanged(int i) {
        Log.v(TAG, "onAirHeatOilDisplayChanged");
    }

    public void onChargeDisplayChanged(int i) {
        Log.v(TAG, "onChargeDisplayChanged");
    }

    public void onChargePercentChanged(int i) {
        Log.v(TAG, "onChargePercentChanged");
    }

    public void onChargePowerChanged(float f) {
        Log.v(TAG, "onChargePowerChanged");
    }

    public void onChargeNoticeChanged(int i) {
        Log.v(TAG, "onChargeNoticeChanged");
    }

    public void onChargeRestTimeChanged(int[] iArr) {
        Log.v(TAG, "onChargeRestTimeChanged");
    }

    public void onExpectChargeStateChanged(int i) {
        Log.v(TAG, "onExpectChargeStateChanged");
    }

    public void onExpectChargeDisplayChanged(int i) {
        Log.v(TAG, "onExpectChargeDisplayChanged");
    }

    public void onACCIndicateLightStateChanged(int i) {
        Log.v(TAG, "onACCIndicateLightStateChanged");
    }

    public void onACCIndicateLightColorChanged(int i) {
        Log.v(TAG, "onACCIndicateLightColorChanged");
    }

    public void onAccCruisingSpeedValueChanged(int i) {
        Log.v(TAG, "onAccCruisingSpeedValueChanged");
    }

    public void onOilLevelAlarmIndicatorChanged(int i) {
        Log.v(TAG, "onOilLevelAlarmIndicatorChanged");
    }

    public void onOilLevelAlarmIndicatorColorChanged(int i) {
        Log.v(TAG, "onOilLevelAlarmIndicatorColorChanged");
    }

    public void onOutCarTemperatureChanged(int i) {
        Log.v(TAG, "onOutCarTemperatureChanged");
    }

    public void onLinkErrKeyTimeChanged(int i, int i2) {
        Log.v(TAG, "onLinkErrKeyTimeChanged");
    }

    public void onSRSFaultWarningLightChanged(int i) {
        Log.v(TAG, "onSRSFaultWarningLightChanged");
    }

    public void onSRSFaultWarningLightColorChanged(int i) {
        Log.v(TAG, "onSRSFaultWarningLightColorChanged");
    }

    public void onABSFaultWarningLightChanged(int i) {
        Log.v(TAG, "onABSFaultWarningLightChanged");
    }

    public void onABSFaultWarningLightColorChanged(int i) {
        Log.v(TAG, "onABSFaultWarningLightColorChanged");
    }

    public void onBrakeSysFaultLightStateChanged(int i) {
        Log.v(TAG, "onBrakeSysFaultLightStateChanged");
    }

    public void onBrakeSysFaultLightColorChanged(int i) {
        Log.v(TAG, "onBrakeSysFaultLightColorChanged");
    }

    public void onCoolantTempHighWarnLightStateChanged(int i) {
        Log.v(TAG, "onCoolantTempHighWarnLightStateChanged");
    }

    public void onCoolantTempHighWarnLightColorChanged(int i) {
        Log.v(TAG, "onCoolantTempHighWarnLightColorChanged");
    }

    public void onELECParkingStateChanged(int i) {
        Log.v(TAG, "onELECParkingStateChanged");
    }

    public void onELECParkingColorChanged(int i) {
        Log.v(TAG, "onELECParkingColorChanged");
    }

    public void onEngineFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onEngineFailWarnLightStateChanged");
    }

    public void onEngineFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onEngineFailWarnLightColorChanged");
    }

    public void onESPFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onESPFailWarnLightStateChanged");
    }

    public void onESPFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onESPFailWarnLightColorChanged");
    }

    public void onGPFIndicatorStateChanged(int i) {
        Log.v(TAG, "onGPFIndicatorStateChanged");
    }

    public void onGPFIndicatorColorChanged(int i) {
        Log.v(TAG, "onGPFIndicatorColorChanged");
    }

    public void onLowFuelWarnLightStateChanged(int i) {
        Log.v(TAG, "onLowFuelWarnLightStateChanged");
    }

    public void onLowFuelWarnLightColorChanged(int i) {
        Log.v(TAG, "onLowFuelWarnLightColorChanged");
    }

    public void onPressureWarnLightStateChanged(int i) {
        Log.v(TAG, "onPressureWarnLightStateChanged");
    }

    public void onPressureWarnLightColorChanged(int i) {
        Log.v(TAG, "onPressureWarnLightColorChanged");
    }

    public void onMainAlarmIndicatorStateChanged(int i) {
        Log.v(TAG, "onMainAlarmIndicatorStateChanged");
    }

    public void onMainAlarmIndicatorColorChanged(int i) {
        Log.v(TAG, "onMainAlarmIndicatorColorChanged");
    }

    public void onPressureSupplySysFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onPressureSupplySysFailWarnLightStateChanged");
    }

    public void onPressureSupplySysFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onPressureSupplySysFailWarnLightColorChanged");
    }

    public void onSmartKeySysWarnLightStateChanged(int i) {
        Log.v(TAG, "onSmartKeySysWarnLightStateChanged");
    }

    public void onSmartKeySysWarnLightColorChanged(int i) {
        Log.v(TAG, "onSmartKeySysWarnLightColorChanged");
    }

    public void onSteeringSYSFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onSteeringSYSFailWarnLightStateChanged");
    }

    public void onSteeringSYSFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onSteeringSYSFailWarnLightColorChanged");
    }

    public void onTyrePressureSYSFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onTyrePressureSYSFailWarnLightStateChanged");
    }

    public void onTyrePressureSYSFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onTyrePressureSYSFailWarnLightColorChanged");
    }

    public void onHeadlampFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onHeadlampFailWarnLightStateChanged");
    }

    public void onHeadlampFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onHeadlampFailWarnLightColorChanged");
    }

    public void onCruiseCtrlIndicatorStateChanged(int i) {
        Log.v(TAG, "onCruiseCtrlIndicatorStateChanged");
    }

    public void onCruiseCtrlIndicatorColorChanged(int i) {
        Log.v(TAG, "onCruiseCtrlIndicatorColorChanged");
    }

    public void onDishargeIndicatorStateChanged(int i) {
        Log.v(TAG, "onDishargeIndicatorStateChanged");
    }

    public void onDischargeIndicatorColorChanged(int i) {
        Log.v(TAG, "onDischargeIndicatorColorChanged");
    }

    public void onDrivePowerLimitIndicatorStateChanged(int i) {
        Log.v(TAG, "onDrivePowerLimitIndicatorStateChanged");
    }

    public void onDrivePowerLimitIndicatorColorChanged(int i) {
        Log.v(TAG, "onDrivePowerLimitIndicatorColorChanged");
    }

    public void onECOIndicatorStateChanged(int i) {
        Log.v(TAG, "onECOIndicatorStateChanged");
    }

    public void onECOIndicatorColorChanged(int i) {
        Log.v(TAG, "onECOIndicatorColorChanged");
    }

    public void onEVIndicatorStateChanged(int i) {
        Log.v(TAG, "onEVIndicatorStateChanged");
    }

    public void onEVIndicatorColorChanged(int i) {
        Log.v(TAG, "onEVIndicatorColorChanged");
    }

    public void onHEVIndicatorStateChanged(int i) {
        Log.v(TAG, "onHEVIndicatorStateChanged");
    }

    public void onHEVIndicatorColorChanged(int i) {
        Log.v(TAG, "onHEVIndicatorColorChanged");
    }

    public void onLowPowerBatteryWarnLightStateChanged(int i) {
        Log.v(TAG, "onLowPowerBatteryWarnLightStateChanged");
    }

    public void onLowPowerBatteryWarnLightColorChanged(int i) {
        Log.v(TAG, "onLowPowerBatteryWarnLightColorChanged");
    }

    public void onOKIndicatorStateChanged(int i) {
        Log.v(TAG, "onOKIndicatorStateChanged");
    }

    public void onOKIndicatorColorChanged(int i) {
        Log.v(TAG, "onOKIndicatorColorChanged");
    }

    public void onPowerBatteryChargeConnectIndicatorStateChanged(int i) {
        Log.v(TAG, "onPowerBatteryChargeConnectIndicatorStateChanged");
    }

    public void onPowerBatteryChargeConnectIndicatorColorChanged(int i) {
        Log.v(TAG, "onPowerBatteryChargeConnectIndicatorColorChanged");
    }

    public void onPowerBatteryHeatWarnLightStateChanged(int i) {
        Log.v(TAG, "onPowerBatteryHeatWarnLightStateChanged");
    }

    public void onPowerBatteryHeatWarnLightColorChanged(int i) {
        Log.v(TAG, "onPowerBatteryHeatWarnLightColorChanged");
    }

    public void onPowerBatFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onPowerBatFailWarnLightStateChanged");
    }

    public void onPowerBatFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onPowerBatFailWarnLightColorChanged");
    }

    public void onPowerSysFailWarnLightStateChanged(int i) {
        Log.v(TAG, "onPowerSysFailWarnLightStateChanged");
    }

    public void onPowerSysFailWarnLightColorChanged(int i) {
        Log.v(TAG, "onPowerSysFailWarnLightColorChanged");
    }

    public void onSportIndicatorStateChanged(int i) {
        Log.v(TAG, "onSportIndicatorStateChanged");
    }

    public void onSportIndicatorColorChanged(int i) {
        Log.v(TAG, "onSportIndicatorColorChanged");
    }

    public void onDischargeUiStateChanged(int i) {
        Log.v(TAG, "onDischargeUiStateChanged");
    }

    public void onDischargeModeChanged(int i) {
        Log.v(TAG, "onDischargeModeChanged");
    }

    public void onDischargeElecEnergyChanged(float f) {
        Log.v(TAG, "onDischargeElecEnergyChanged");
    }

    public void onDirectionInfoChanged(int i) {
        Log.v(TAG, "onDirectionInfoChanged");
    }

    public void onTyrePressureCarTypeChanged(int i) {
        Log.v(TAG, "onTyrePressureCarTypeChanged");
    }

    public void onMileageValidFlagChanged(int i) {
        Log.v(TAG, "onMileageValidFlagChanged");
    }

    public void onDashboardAlarmStateChanged(int i) {
        Log.v(TAG, "onDashboardAlarmStateChanged");
    }

    public void onCurrentJourneyDriveMileageChanged(float f) {
        Log.v(TAG, "onCurrentJourneyDriveMileageChanged");
    }

    public void onCurrentJourneyDriveTimeChanged(float f) {
        Log.v(TAG, "onCurrentJourneyDriveTimeChanged");
    }

    public void onCurrentDriveInterFaceChanged(int i) {
        Log.v(TAG, "onCurrentDriveInterFaceChanged");
    }

    public void onOdometerDisplayChanged(int i) {
        Log.v(TAG, "onOdometerDisplayChanged");
    }

    public void onAppCountdownHourChanged(int i) {
        Log.v(TAG, "onAppCountdownHourChanged");
    }

    public void onAppCountdownMinuteChanged(int i) {
        Log.v(TAG, "onAppCountdownMinuteChanged");
    }

    public void onViewStatusChanged(int i) {
        Log.v(TAG, "onViewStatusChanged");
    }

    public void onFirstMenuChanged(int i) {
        Log.v(TAG, "onFirstMenuChanged");
    }

    public void onSecondMenuChanged(int i) {
        Log.v(TAG, "onSecondMenuChanged");
    }

    public void onAirHeatingOilWarnChanged(int i) {
        Log.v(TAG, "onAirHeatingOilWarnChanged");
    }

    public void onChargeAppTimeOptionChanged(int i) {
        Log.v(TAG, "onChargeAppTimeOptionChanged");
    }

    public void on2in1AccDistanceChanged(int i) {
        Log.v(TAG, "on2in1AccDistanceChanged");
    }

    public void on2in1AccTextPromptChanged(int i) {
        Log.v(TAG, "on2in1AccTextPromptChanged");
    }

    public void on2in1BodyPositionChanged(int i) {
        Log.v(TAG, "on2in1BodyPositionChanged");
    }

    public void onLineValueChanged(int i, int i2) {
        Log.v(TAG, "onLineValueChanged");
    }

    public void on2in1AccWorkInterfaceChanged(int i) {
        Log.v(TAG, "on2in1AccWorkInterfaceChanged");
    }

    public void on2in1AccTimeDistanceChanged(int i) {
        Log.v(TAG, "on2in1AccTimeDistanceChanged");
    }

    public void onSoundFreqChanged(int i) {
        Log.v(TAG, "onSoundFreqChanged");
    }

    public void on2in1FaultSmallLightIndicatorChanged(int i) {
        Log.v(TAG, "on2in1FaultSmallLightIndicatorChanged");
    }

    public void on2in1FaultSmallLightIndicatorColorChanged(int i) {
        Log.v(TAG, "on2in1FaultSmallLightIndicatorColorChanged");
    }

    public void on2in1FaultFrontFogLightIndicatorChanged(int i) {
        Log.v(TAG, "on2in1FaultFrontFogLightIndicatorChanged");
    }

    public void on2in1FaultFrontFogLightIndicatorColorChanged(int i) {
        Log.v(TAG, "on2in1FaultFrontFogLightIndicatorColorChanged");
    }

    public void on2in1FaultGrassIndicatorChanged(int i) {
        Log.v(TAG, "on2in1FaultGrassIndicatorChanged");
    }

    public void on2in1FaultGrassIndicatorColorChanged(int i) {
        Log.v(TAG, "on2in1FaultGrassIndicatorColorChanged");
    }

    public void on2in1MenuStateChanged(int i) {
        Log.v(TAG, "on2in1MenuStateChanged");
    }

    public void onAppointmentHourChanged(int i) {
        Log.v(TAG, "onAppointmentHourChanged");
    }

    public void onAppointmentMinuteChanged(int i) {
        Log.v(TAG, "onAppointmentMinuteChanged");
    }

    public void onInstrumentViewChanged(int i) {
        Log.v(TAG, "onInstrumentViewChanged");
    }

    public void onFaultMuddyIndicatorChanged(int i) {
        Log.v(TAG, "onFaultMuddyIndicatorChanged");
    }

    public void onFaultMuddyIndicatorColorChanged(int i) {
        Log.v(TAG, "onFaultMuddyIndicatorColorChanged");
    }

    public void onFaultNormalIndicatorChanged(int i) {
        Log.v(TAG, "onFaultNormalIndicatorChanged");
    }

    public void onFaultNormalIndicatorColorChanged(int i) {
        Log.v(TAG, "onFaultNormalIndicatorColorChanged");
    }

    public void onFaultOilLifeDetectIndicatorChanged(int i) {
        Log.v(TAG, "onFaultOilLifeDetectIndicatorChanged");
    }

    public void onFaultOilLifeDetectIndicatorColorChanged(int i) {
        Log.v(TAG, "onFaultOilLifeDetectIndicatorColorChanged");
    }

    public void onFaultSandIndicatorChanged(int i) {
        Log.v(TAG, "onFaultSandIndicatorChanged");
    }

    public void onFaultSandIndicatorColorChanged(int i) {
        Log.v(TAG, "onFaultSandIndicatorColorChanged");
    }

    public void on50KmEneryConsumptionDisplayStateChanged(int i) {
        Log.v(TAG, "on50KmEneryConsumptionDisplayStateChanged");
    }

    public void onAverageEneryConsumptionDisplayStateChanged(int i) {
        Log.v(TAG, "onAverageEneryConsumptionDisplayStateChanged");
    }

    public void onAverageFuelConsumptionDisplayStateChanged(int i) {
        Log.v(TAG, "onAverageFuelConsumptionDisplayStateChanged");
    }

    public void onFuelConsumptionDisplayStateChanged(int i) {
        Log.v(TAG, "onFuelConsumptionDisplayStateChanged");
    }

    public void onInstantFuelConsumptionDisplayStateChanged(int i) {
        Log.v(TAG, "onInstantFuelConsumptionDisplayStateChanged");
    }

    public void onInstantFuelConsumptionUnitChanged(int i) {
        Log.v(TAG, "onInstantFuelConsumptionUnitChanged");
    }

    public void onWaterTempMeterPercentChanged(float f) {
        Log.v(TAG, "onWaterTempMeterPercentChanged");
    }

    public void onAirHeatingDisplayStateChanged(int i) {
        Log.v(TAG, "onAirHeatingDisplayStateChanged");
    }

    public void onDirectTypePressDisplayStateChanged(int i) {
        Log.v(TAG, "onDirectTypePressDisplayStateChanged");
    }

    public void on50KmFuelConsumptionDisplayStateChanged(int i) {
        Log.v(TAG, "on50KmFuelConsumptionDisplayStateChanged");
    }

    public void onEnergyDisplayChanged(int i) {
        Log.v(TAG, "onEnergyDisplayChanged");
    }

    public void onFaultIndicatorChanged(int i, int i2) {
        Log.v(TAG, "onFaultIndicatorChanged");
    }

    public void onFaultIndicatorColorChanged(int i, int i2) {
        Log.v(TAG, "onFaultIndicatorColorChanged");
    }

    public void onFuelLowAlarmChanged(int i) {
        Log.v(TAG, "onFuelLowAlarmChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onInstrumentThemeChange(int i) {
        Log.v(TAG, "onInstrumentThemeChange");
    }

    public void onInstrumentBrightnessChanged(int i) {
        Log.v(TAG, "onInstrumentBrightnessChanged");
    }

    @Deprecated
    public void onInstrumentAutoDimmStateChange(int i) {
        Log.v(TAG, "onInstrumentAutoDimmStateChange");
    }

    public void onMilesTypeChanged(int i) {
        Log.v(TAG, "onMilesTypeChanged");
    }

    public void onMaintenanceInfoStateChanged(int i, int i2) {
        Log.v(TAG, "onMaintenanceInfoStateChanged");
    }

    public void onINSAdasCenterDisplayChanged(int i) {
        Log.v(TAG, "onINSAdasCenterDisplayChanged");
    }

    public void onINSNavDisplayMode(int i) {
        Log.v(TAG, "onINSNavDisplayMode");
    }

    public void onInsUiChanged(int i) {
        Log.v(TAG, "onInsUiChanged");
    }

    public void onMapFocusChanged(int i) {
        Log.v(TAG, "onMapFocusChanged");
    }

    public void onINSCenterDisplay(int i) {
        Log.v(TAG, "onINSCenterDisplay");
    }

    public void onSpeedUpCountStateChanged(int i) {
        Log.v(TAG, "onSpeedUpCountStateChanged");
    }

    public void onDayNightModeChanged(int i) {
        Log.v(TAG, "onDayNightModeChanged");
    }

    public void onSpeedUpFormInsChange(int i) {
        Log.v(TAG, "onSpeedUpFormInsChange");
    }

    public void onAreaInfoChanged(int i) {
        Log.v(TAG, "onAreaInfoChanged");
    }

    public void onAllWeatherLightsStateChanged(int i) {
        Log.v(TAG, "onAllWeatherLightsStateChanged");
    }

    public void onHeadlightControlModeChanged(int i) {
        Log.v(TAG, "onHeadlightControlModeChanged");
    }

    public void onRearFogLightStateChanged(int i) {
        Log.v(TAG, "onRearFogLightStateChanged");
    }

    public void onMusicProgressChanged(int i, int i2) {
        Log.v(TAG, "onMusicProgressChanged");
    }

    public void onMusicControlStatusChanged(int i, int i2) {
        Log.v(TAG, "onMusicControlStatusChanged");
    }

    public void onMusicChannelStatusChanged(int i, int i2) {
        Log.v(TAG, "onMusicChannelStatusChanged");
    }

    public void onMusicResourceConfigChanged(int i, int i2) {
        Log.v(TAG, "onMusicResourceConfigChanged");
    }

    public void onMasterVolumeChanged(int i, int i2) {
        Log.v(TAG, "onMasterVolumeChanged");
    }

    public void onMuteStateChanged(int i, int i2) {
        Log.v(TAG, "onMuteStateChanged");
    }
}
