package android.hardware.bydauto.instrument;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoInstrumentListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoInstrumentListener";

    public void onMalfunctionInfoChanged(int typeName, int hasMalfunction) {
        Log.v(TAG, "onMalfunctionInfoChanged");
    }

    public void onMalfunctionInfoChanged2(int typeName, int hasMalfunction) {
        Log.v(TAG, "onMalfunctionInfoChanged2");
    }

    public void onBacklightModeStateChanged(int backlightMode, int state) {
        Log.v(TAG, "onBacklightModeStateChanged");
    }

    public void onBacklightBrightnessChanged(int brightness) {
        Log.v(TAG, "onBacklightBrightnessChanged");
    }

    public void onUnitChanged(int unitName, int unitValue) {
        Log.v(TAG, "onUnitChanged");
    }

    public void onMaintenanceInfoChanged(int typeName, int infoValue) {
        Log.v(TAG, "onMaintenanceInfoChanged");
    }

    public void onMusicInfoResultChanged(int result) {
        Log.v(TAG, "onMusicInfoResultChanged");
    }

    public void onMusicInfoUrlChange(String musicName, String artistName, int playState, int musicSource, String imgUrl) {
        Log.v(TAG, "onMusicInfoUrlChange");
    }

    public void onCallInfoResultChanged(int result) {
        Log.v(TAG, "onCallInfoResultChanged");
    }

    public void onRadioInfoResultChanged(int result) {
        Log.v(TAG, "onRadioInfoResultChanged");
    }

    public void onAlarmBuzzleStateChange(int state) {
        Log.v(TAG, "onAlarmBuzzleStateChange");
    }

    public void onPowerOnErrInfoChanged(int err) {
        Log.v(TAG, "onPowerOnErrInfoChanged");
    }

    public void onPowerOffErrInfoChanged(int err) {
        Log.v(TAG, "onPowerOffErrInfoChanged");
    }

    public void onRemoteDrivingReminderChanged(int value) {
        Log.v(TAG, "onRemoteDrivingReminderChanged");
    }

    public void onKeyDetectionReminderChanged(int value) {
        Log.v(TAG, "onKeyDetectionReminderChanged");
    }

    public void onAverageSpeedChanged(int value) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public void onExternalChargingPowerChanged(float value) {
        Log.v(TAG, "onExternalChargingPowerChanged");
    }

    public void onInstrumentScreenTypeChanged(int value) {
        Log.v(TAG, "onInstrumentScreenTypeChanged");
    }

    public void onNaviDestinationCommandChanged(int command) {
        Log.v(TAG, "onNaviDestinationCommandChanged");
    }

    public void onRoadNameCheckStateChanged(int state) {
        Log.v(TAG, "onRoadNameCheckStateChanged");
    }

    public void onTextInfoChanged(int value) {
        Log.v(TAG, "onTextInfoChanged");
    }

    public void onModuleStateChanged(int module, int state) {
        Log.v(TAG, "onModuleStateChanged");
    }

    public void onDoorStateChanged(int area, int state) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onSafetyBeltStatusChanged(int area, int state) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }

    public void onWheelColorChanged(int position, int state) {
        Log.v(TAG, "onWheelColorChanged");
    }

    public void onWheelTemperatureColorChanged(int position, int state) {
        Log.v(TAG, "onWheelTemperatureColorChanged");
    }

    public void onWheelPressureChanged(int position, int value) {
        Log.v(TAG, "onWheelPressureChanged");
    }

    public void onWheelTemperatureChanged(int position, int value) {
        Log.v(TAG, "onWheelTemperatureChanged");
    }

    public void onDeviationStateChanged(int state) {
        Log.v(TAG, "onDeviationStateChanged");
    }

    public void onGapDetectionChanged(int state) {
        Log.v(TAG, "onGapDetectionChanged");
    }

    public void onLaneLineStateChanged(int state) {
        Log.v(TAG, "onLaneLineStateChanged");
    }

    public void onTimeIntervalStateChanged(int state) {
        Log.v(TAG, "onTimeIntervalStateChanged");
    }

    public void onEnergyFeedbackChanged(int state) {
        Log.v(TAG, "onEnergyFeedbackChanged");
    }

    public void onTextColorChanged(int state) {
        Log.v(TAG, "onTextColorChanged");
    }

    public void onSpacingStateChanged(int state) {
        Log.v(TAG, "onSpacingStateChanged");
    }

    public void onSoundTypeChanged(int state) {
        Log.v(TAG, "onSoundTypeChanged");
    }

    public void onAccCruisingSpeedChanged(int state) {
        Log.v(TAG, "onAccCruisingSpeedChanged");
    }

    public void onAccCruisingSpeedColorChanged(int state) {
        Log.v(TAG, "onAccCruisingSpeedColorChanged");
    }

    public void onPCWAlarmInstructionChanged(int state) {
        Log.v(TAG, "onPCWAlarmInstructionChanged");
    }

    public void onLaneLineColorChanged(int state) {
        Log.v(TAG, "onLaneLineColorChanged");
    }

    public void onTotalMileageChanged(int state) {
        Log.v(TAG, "onTotalMileageChanged");
    }

    public void onMileageUnitChanged(int state) {
        Log.v(TAG, "onMileageUnitChanged");
    }

    public void onLast50KmPowerConsumeChanged(float state) {
        Log.v(TAG, "onLast50KmPowerConsumeChanged");
    }

    public void onSpeedUnitChanged(int state) {
        Log.v(TAG, "onSpeedUnitChanged");
    }

    public void onBatteryPercentChanged(int state) {
        Log.v(TAG, "onBatteryPercentChanged");
    }

    public void onExternalChargePowerChanged(float state) {
        Log.v(TAG, "onExternalChargePowerChanged");
    }

    public void onTravelTimeChanged(float state) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onPowerUnitChanged(int state) {
        Log.v(TAG, "onPowerUnitChanged");
    }

    public void onAirHeatOilDisplayChanged(int state) {
        Log.v(TAG, "onAirHeatOilDisplayChanged");
    }

    public void onChargeDisplayChanged(int value) {
        Log.v(TAG, "onChargeDisplayChanged");
    }

    public void onChargePercentChanged(int value) {
        Log.v(TAG, "onChargePercentChanged");
    }

    public void onChargePowerChanged(float value) {
        Log.v(TAG, "onChargePowerChanged");
    }

    public void onChargeNoticeChanged(int value) {
        Log.v(TAG, "onChargeNoticeChanged");
    }

    public void onChargeRestTimeChanged(int[] resetTime) {
        Log.v(TAG, "onChargeRestTimeChanged");
    }

    public void onExpectChargeStateChanged(int value) {
        Log.v(TAG, "onExpectChargeStateChanged");
    }

    public void onExpectChargeDisplayChanged(int value) {
        Log.v(TAG, "onExpectChargeDisplayChanged");
    }

    public void onACCIndicateLightStateChanged(int state) {
        Log.v(TAG, "onACCIndicateLightStateChanged");
    }

    public void onACCIndicateLightColorChanged(int color) {
        Log.v(TAG, "onACCIndicateLightColorChanged");
    }

    public void onAccCruisingSpeedValueChanged(int value) {
        Log.v(TAG, "onAccCruisingSpeedValueChanged");
    }

    public void onOilLevelAlarmIndicatorChanged(int value) {
        Log.v(TAG, "onOilLevelAlarmIndicatorChanged");
    }

    public void onOilLevelAlarmIndicatorColorChanged(int value) {
        Log.v(TAG, "onOilLevelAlarmIndicatorColorChanged");
    }

    public void onOutCarTemperatureChanged(int value) {
        Log.v(TAG, "onOutCarTemperatureChanged");
    }

    public void onLinkErrKeyTimeChanged(int flag, int value) {
        Log.v(TAG, "onLinkErrKeyTimeChanged");
    }

    public void onSRSFaultWarningLightChanged(int value) {
        Log.v(TAG, "onSRSFaultWarningLightChanged");
    }

    public void onSRSFaultWarningLightColorChanged(int value) {
        Log.v(TAG, "onSRSFaultWarningLightColorChanged");
    }

    public void onABSFaultWarningLightChanged(int value) {
        Log.v(TAG, "onABSFaultWarningLightChanged");
    }

    public void onABSFaultWarningLightColorChanged(int value) {
        Log.v(TAG, "onABSFaultWarningLightColorChanged");
    }

    public void onBrakeSysFaultLightStateChanged(int value) {
        Log.v(TAG, "onBrakeSysFaultLightStateChanged");
    }

    public void onBrakeSysFaultLightColorChanged(int value) {
        Log.v(TAG, "onBrakeSysFaultLightColorChanged");
    }

    public void onCoolantTempHighWarnLightStateChanged(int value) {
        Log.v(TAG, "onCoolantTempHighWarnLightStateChanged");
    }

    public void onCoolantTempHighWarnLightColorChanged(int value) {
        Log.v(TAG, "onCoolantTempHighWarnLightColorChanged");
    }

    public void onELECParkingStateChanged(int value) {
        Log.v(TAG, "onELECParkingStateChanged");
    }

    public void onELECParkingColorChanged(int value) {
        Log.v(TAG, "onELECParkingColorChanged");
    }

    public void onEngineFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onEngineFailWarnLightStateChanged");
    }

    public void onEngineFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onEngineFailWarnLightColorChanged");
    }

    public void onESPFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onESPFailWarnLightStateChanged");
    }

    public void onESPFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onESPFailWarnLightColorChanged");
    }

    public void onGPFIndicatorStateChanged(int value) {
        Log.v(TAG, "onGPFIndicatorStateChanged");
    }

    public void onGPFIndicatorColorChanged(int value) {
        Log.v(TAG, "onGPFIndicatorColorChanged");
    }

    public void onLowFuelWarnLightStateChanged(int value) {
        Log.v(TAG, "onLowFuelWarnLightStateChanged");
    }

    public void onLowFuelWarnLightColorChanged(int value) {
        Log.v(TAG, "onLowFuelWarnLightColorChanged");
    }

    public void onPressureWarnLightStateChanged(int value) {
        Log.v(TAG, "onPressureWarnLightStateChanged");
    }

    public void onPressureWarnLightColorChanged(int value) {
        Log.v(TAG, "onPressureWarnLightColorChanged");
    }

    public void onMainAlarmIndicatorStateChanged(int value) {
        Log.v(TAG, "onMainAlarmIndicatorStateChanged");
    }

    public void onMainAlarmIndicatorColorChanged(int value) {
        Log.v(TAG, "onMainAlarmIndicatorColorChanged");
    }

    public void onPressureSupplySysFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onPressureSupplySysFailWarnLightStateChanged");
    }

    public void onPressureSupplySysFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onPressureSupplySysFailWarnLightColorChanged");
    }

    public void onSmartKeySysWarnLightStateChanged(int value) {
        Log.v(TAG, "onSmartKeySysWarnLightStateChanged");
    }

    public void onSmartKeySysWarnLightColorChanged(int value) {
        Log.v(TAG, "onSmartKeySysWarnLightColorChanged");
    }

    public void onSteeringSYSFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onSteeringSYSFailWarnLightStateChanged");
    }

    public void onSteeringSYSFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onSteeringSYSFailWarnLightColorChanged");
    }

    public void onTyrePressureSYSFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onTyrePressureSYSFailWarnLightStateChanged");
    }

    public void onTyrePressureSYSFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onTyrePressureSYSFailWarnLightColorChanged");
    }

    public void onHeadlampFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onHeadlampFailWarnLightStateChanged");
    }

    public void onHeadlampFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onHeadlampFailWarnLightColorChanged");
    }

    public void onCruiseCtrlIndicatorStateChanged(int value) {
        Log.v(TAG, "onCruiseCtrlIndicatorStateChanged");
    }

    public void onCruiseCtrlIndicatorColorChanged(int value) {
        Log.v(TAG, "onCruiseCtrlIndicatorColorChanged");
    }

    public void onDishargeIndicatorStateChanged(int value) {
        Log.v(TAG, "onDishargeIndicatorStateChanged");
    }

    public void onDischargeIndicatorColorChanged(int value) {
        Log.v(TAG, "onDischargeIndicatorColorChanged");
    }

    public void onDrivePowerLimitIndicatorStateChanged(int value) {
        Log.v(TAG, "onDrivePowerLimitIndicatorStateChanged");
    }

    public void onDrivePowerLimitIndicatorColorChanged(int value) {
        Log.v(TAG, "onDrivePowerLimitIndicatorColorChanged");
    }

    public void onECOIndicatorStateChanged(int value) {
        Log.v(TAG, "onECOIndicatorStateChanged");
    }

    public void onECOIndicatorColorChanged(int value) {
        Log.v(TAG, "onECOIndicatorColorChanged");
    }

    public void onEVIndicatorStateChanged(int value) {
        Log.v(TAG, "onEVIndicatorStateChanged");
    }

    public void onEVIndicatorColorChanged(int value) {
        Log.v(TAG, "onEVIndicatorColorChanged");
    }

    public void onHEVIndicatorStateChanged(int value) {
        Log.v(TAG, "onHEVIndicatorStateChanged");
    }

    public void onHEVIndicatorColorChanged(int value) {
        Log.v(TAG, "onHEVIndicatorColorChanged");
    }

    public void onLowPowerBatteryWarnLightStateChanged(int value) {
        Log.v(TAG, "onLowPowerBatteryWarnLightStateChanged");
    }

    public void onLowPowerBatteryWarnLightColorChanged(int value) {
        Log.v(TAG, "onLowPowerBatteryWarnLightColorChanged");
    }

    public void onOKIndicatorStateChanged(int value) {
        Log.v(TAG, "onOKIndicatorStateChanged");
    }

    public void onOKIndicatorColorChanged(int value) {
        Log.v(TAG, "onOKIndicatorColorChanged");
    }

    public void onPowerBatteryChargeConnectIndicatorStateChanged(int value) {
        Log.v(TAG, "onPowerBatteryChargeConnectIndicatorStateChanged");
    }

    public void onPowerBatteryChargeConnectIndicatorColorChanged(int value) {
        Log.v(TAG, "onPowerBatteryChargeConnectIndicatorColorChanged");
    }

    public void onPowerBatteryHeatWarnLightStateChanged(int value) {
        Log.v(TAG, "onPowerBatteryHeatWarnLightStateChanged");
    }

    public void onPowerBatteryHeatWarnLightColorChanged(int value) {
        Log.v(TAG, "onPowerBatteryHeatWarnLightColorChanged");
    }

    public void onPowerBatFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onPowerBatFailWarnLightStateChanged");
    }

    public void onPowerBatFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onPowerBatFailWarnLightColorChanged");
    }

    public void onPowerSysFailWarnLightStateChanged(int value) {
        Log.v(TAG, "onPowerSysFailWarnLightStateChanged");
    }

    public void onPowerSysFailWarnLightColorChanged(int value) {
        Log.v(TAG, "onPowerSysFailWarnLightColorChanged");
    }

    public void onSportIndicatorStateChanged(int value) {
        Log.v(TAG, "onSportIndicatorStateChanged");
    }

    public void onSportIndicatorColorChanged(int value) {
        Log.v(TAG, "onSportIndicatorColorChanged");
    }

    public void onDischargeUiStateChanged(int value) {
        Log.v(TAG, "onDischargeUiStateChanged");
    }

    public void onDischargeModeChanged(int mode) {
        Log.v(TAG, "onDischargeModeChanged");
    }

    public void onDischargeElecEnergyChanged(float value) {
        Log.v(TAG, "onDischargeElecEnergyChanged");
    }

    public void onDirectionInfoChanged(int value) {
        Log.v(TAG, "onDirectionInfoChanged");
    }

    public void onTyrePressureCarTypeChanged(int type) {
        Log.v(TAG, "onTyrePressureCarTypeChanged");
    }

    public void onMileageValidFlagChanged(int value) {
        Log.v(TAG, "onMileageValidFlagChanged");
    }

    public void onDashboardAlarmStateChanged(int value) {
        Log.v(TAG, "onDashboardAlarmStateChanged");
    }

    public void onCurrentJourneyDriveMileageChanged(float value) {
        Log.v(TAG, "onCurrentJourneyDriveMileageChanged");
    }

    public void onCurrentJourneyDriveTimeChanged(float value) {
        Log.v(TAG, "onCurrentJourneyDriveTimeChanged");
    }

    public void onCurrentDriveInterFaceChanged(int state) {
        Log.v(TAG, "onCurrentDriveInterFaceChanged");
    }

    public void onOdometerDisplayChanged(int state) {
        Log.v(TAG, "onOdometerDisplayChanged");
    }

    public void onAppCountdownHourChanged(int value) {
        Log.v(TAG, "onAppCountdownHourChanged");
    }

    public void onAppCountdownMinuteChanged(int value) {
        Log.v(TAG, "onAppCountdownMinuteChanged");
    }

    public void onViewStatusChanged(int state) {
        Log.v(TAG, "onViewStatusChanged");
    }

    public void onFirstMenuChanged(int value) {
        Log.v(TAG, "onFirstMenuChanged");
    }

    public void onSecondMenuChanged(int value) {
        Log.v(TAG, "onSecondMenuChanged");
    }

    public void onAirHeatingOilWarnChanged(int value) {
        Log.v(TAG, "onAirHeatingOilWarnChanged");
    }

    public void onChargeAppTimeOptionChanged(int state) {
        Log.v(TAG, "onChargeAppTimeOptionChanged");
    }

    public void on2in1AccDistanceChanged(int value) {
        Log.v(TAG, "on2in1AccDistanceChanged");
    }

    public void on2in1AccTextPromptChanged(int value) {
        Log.v(TAG, "on2in1AccTextPromptChanged");
    }

    public void on2in1BodyPositionChanged(int value) {
        Log.v(TAG, "on2in1BodyPositionChanged");
    }

    public void onLineValueChanged(int flag, int value) {
        Log.v(TAG, "onLineValueChanged");
    }

    public void on2in1AccWorkInterfaceChanged(int value) {
        Log.v(TAG, "on2in1AccWorkInterfaceChanged");
    }

    public void on2in1AccTimeDistanceChanged(int value) {
        Log.v(TAG, "on2in1AccTimeDistanceChanged");
    }

    public void onSoundFreqChanged(int state) {
        Log.v(TAG, "onSoundFreqChanged");
    }

    public void on2in1FaultSmallLightIndicatorChanged(int value) {
        Log.v(TAG, "on2in1FaultSmallLightIndicatorChanged");
    }

    public void on2in1FaultSmallLightIndicatorColorChanged(int value) {
        Log.v(TAG, "on2in1FaultSmallLightIndicatorColorChanged");
    }

    public void on2in1FaultFrontFogLightIndicatorChanged(int value) {
        Log.v(TAG, "on2in1FaultFrontFogLightIndicatorChanged");
    }

    public void on2in1FaultFrontFogLightIndicatorColorChanged(int value) {
        Log.v(TAG, "on2in1FaultFrontFogLightIndicatorColorChanged");
    }

    public void on2in1FaultGrassIndicatorChanged(int value) {
        Log.v(TAG, "on2in1FaultGrassIndicatorChanged");
    }

    public void on2in1FaultGrassIndicatorColorChanged(int value) {
        Log.v(TAG, "on2in1FaultGrassIndicatorColorChanged");
    }

    public void on2in1MenuStateChanged(int state) {
        Log.v(TAG, "on2in1MenuStateChanged");
    }

    public void onAppointmentHourChanged(int value) {
        Log.v(TAG, "onAppointmentHourChanged");
    }

    public void onAppointmentMinuteChanged(int value) {
        Log.v(TAG, "onAppointmentMinuteChanged");
    }

    public void onInstrumentViewChanged(int value) {
        Log.v(TAG, "onInstrumentViewChanged");
    }

    public void onFaultMuddyIndicatorChanged(int value) {
        Log.v(TAG, "onFaultMuddyIndicatorChanged");
    }

    public void onFaultMuddyIndicatorColorChanged(int value) {
        Log.v(TAG, "onFaultMuddyIndicatorColorChanged");
    }

    public void onFaultNormalIndicatorChanged(int value) {
        Log.v(TAG, "onFaultNormalIndicatorChanged");
    }

    public void onFaultNormalIndicatorColorChanged(int value) {
        Log.v(TAG, "onFaultNormalIndicatorColorChanged");
    }

    public void onFaultOilLifeDetectIndicatorChanged(int value) {
        Log.v(TAG, "onFaultOilLifeDetectIndicatorChanged");
    }

    public void onFaultOilLifeDetectIndicatorColorChanged(int value) {
        Log.v(TAG, "onFaultOilLifeDetectIndicatorColorChanged");
    }

    public void onFaultSandIndicatorChanged(int value) {
        Log.v(TAG, "onFaultSandIndicatorChanged");
    }

    public void onFaultSandIndicatorColorChanged(int value) {
        Log.v(TAG, "onFaultSandIndicatorColorChanged");
    }

    public void on50KmEneryConsumptionDisplayStateChanged(int value) {
        Log.v(TAG, "on50KmEneryConsumptionDisplayStateChanged");
    }

    public void onAverageEneryConsumptionDisplayStateChanged(int value) {
        Log.v(TAG, "onAverageEneryConsumptionDisplayStateChanged");
    }

    public void onAverageFuelConsumptionDisplayStateChanged(int value) {
        Log.v(TAG, "onAverageFuelConsumptionDisplayStateChanged");
    }

    public void onFuelConsumptionDisplayStateChanged(int value) {
        Log.v(TAG, "onFuelConsumptionDisplayStateChanged");
    }

    public void onInstantFuelConsumptionDisplayStateChanged(int value) {
        Log.v(TAG, "onInstantFuelConsumptionDisplayStateChanged");
    }

    public void onInstantFuelConsumptionUnitChanged(int value) {
        Log.v(TAG, "onInstantFuelConsumptionUnitChanged");
    }

    public void onWaterTempMeterPercentChanged(float value) {
        Log.v(TAG, "onWaterTempMeterPercentChanged");
    }

    public void onAirHeatingDisplayStateChanged(int value) {
        Log.v(TAG, "onAirHeatingDisplayStateChanged");
    }

    public void onDirectTypePressDisplayStateChanged(int value) {
        Log.v(TAG, "onDirectTypePressDisplayStateChanged");
    }

    public void on50KmFuelConsumptionDisplayStateChanged(int value) {
        Log.v(TAG, "on50KmFuelConsumptionDisplayStateChanged");
    }

    public void onEnergyDisplayChanged(int status) {
        Log.v(TAG, "onEnergyDisplayChanged");
    }

    public void onFaultIndicatorChanged(int indicatorType, int value) {
        Log.v(TAG, "onFaultIndicatorChanged");
    }

    public void onFaultIndicatorColorChanged(int indicatorType, int value) {
        Log.v(TAG, "onFaultIndicatorColorChanged");
    }

    public void onFuelLowAlarmChanged(int value) {
        Log.v(TAG, "onFuelLowAlarmChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onInstrumentThemeChange(int value) {
        Log.v(TAG, "onInstrumentThemeChange");
    }

    public void onInstrumentBrightnessChanged(int value) {
        Log.v(TAG, "onInstrumentBrightnessChanged");
    }

    @Deprecated
    public void onInstrumentAutoDimmStateChange(int value) {
        Log.v(TAG, "onInstrumentAutoDimmStateChange");
    }

    public void onMilesTypeChanged(int value) {
        Log.v(TAG, "onMilesTypeChanged");
    }

    public void onMaintenanceInfoStateChanged(int typeName, int infoValue) {
        Log.v(TAG, "onMaintenanceInfoStateChanged");
    }

    public void onINSAdasCenterDisplayChanged(int value) {
        Log.v(TAG, "onINSAdasCenterDisplayChanged");
    }

    public void onINSNavDisplayMode(int value) {
        Log.v(TAG, "onINSNavDisplayMode");
    }

    public void onInsUiChanged(int uiState) {
        Log.v(TAG, "onInsUiChanged");
    }

    public void onMapFocusChanged(int mapType) {
        Log.v(TAG, "onMapFocusChanged");
    }

    public void onINSCenterDisplay(int value) {
        Log.v(TAG, "onINSCenterDisplay");
    }

    public void onSpeedUpCountStateChanged(int state) {
        Log.v(TAG, "onSpeedUpCountStateChanged");
    }

    public void onDayNightModeChanged(int mode) {
        Log.v(TAG, "onDayNightModeChanged");
    }

    public void onSpeedUpFormInsChange(int state) {
        Log.v(TAG, "onSpeedUpFormInsChange");
    }

    public void onAreaInfoChanged(int value) {
        Log.v(TAG, "onAreaInfoChanged");
    }

    public void onAllWeatherLightsStateChanged(int state) {
        Log.v(TAG, "onAllWeatherLightsStateChanged");
    }

    public void onHeadlightControlModeChanged(int state) {
        Log.v(TAG, "onHeadlightControlModeChanged");
    }

    public void onRearFogLightStateChanged(int state) {
        Log.v(TAG, "onRearFogLightStateChanged");
    }

    public void onMusicProgressChanged(int type, int state) {
        Log.v(TAG, "onMusicProgressChanged");
    }

    public void onMusicControlStatusChanged(int type, int state) {
        Log.v(TAG, "onMusicControlStatusChanged");
    }

    public void onMusicChannelStatusChanged(int type, int state) {
        Log.v(TAG, "onMusicChannelStatusChanged");
    }

    public void onMusicResourceConfigChanged(int type, int state) {
        Log.v(TAG, "onMusicResourceConfigChanged");
    }

    public void onMasterVolumeChanged(int type, int state) {
        Log.v(TAG, "onMasterVolumeChanged");
    }

    public void onMuteStateChanged(int type, int state) {
        Log.v(TAG, "onMuteStateChanged");
    }

    public void onDistanceMileageUnitChanged(int unit) {
        Log.v(TAG, "onDistanceMileageUnitChanged unit:" + unit);
    }

    public void onSportModeStateChanged(int state) {
        Log.v(TAG, "onSportModeStateChanged state:" + state);
    }
}
