package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydInstrumentListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class l extends AbsBYDAutoInstrumentListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f855b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f856c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f857d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f858e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f859f = Integer.MIN_VALUE;
    public int g = Integer.MIN_VALUE;
    public int h = Integer.MIN_VALUE;
    public int i = Integer.MIN_VALUE;
    public int j = Integer.MIN_VALUE;
    public int k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;
    public float m = Float.MIN_VALUE;
    public int n = Integer.MIN_VALUE;
    public int o = Integer.MIN_VALUE;
    public int p = Integer.MIN_VALUE;
    public int q = Integer.MIN_VALUE;
    public int r = Integer.MIN_VALUE;
    public int s = Integer.MIN_VALUE;
    public int t = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1AccDistanceChanged(int i) {
        super.on2in1AccDistanceChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1AccTextPromptChanged(int i) {
        super.on2in1AccTextPromptChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1AccTimeDistanceChanged(int i) {
        super.on2in1AccTimeDistanceChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1AccWorkInterfaceChanged(int i) {
        super.on2in1AccWorkInterfaceChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1BodyPositionChanged(int i) {
        super.on2in1BodyPositionChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1FaultFrontFogLightIndicatorChanged(int i) {
        super.on2in1FaultFrontFogLightIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1FaultFrontFogLightIndicatorColorChanged(int i) {
        super.on2in1FaultFrontFogLightIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1FaultGrassIndicatorChanged(int i) {
        super.on2in1FaultGrassIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1FaultGrassIndicatorColorChanged(int i) {
        super.on2in1FaultGrassIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1FaultSmallLightIndicatorChanged(int i) {
        super.on2in1FaultSmallLightIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1FaultSmallLightIndicatorColorChanged(int i) {
        super.on2in1FaultSmallLightIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on2in1MenuStateChanged(int i) {
        super.on2in1MenuStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on50KmEneryConsumptionDisplayStateChanged(int i) {
        super.on50KmEneryConsumptionDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void on50KmFuelConsumptionDisplayStateChanged(int i) {
        super.on50KmFuelConsumptionDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onABSFaultWarningLightChanged(int i) {
        super.onABSFaultWarningLightChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onABSFaultWarningLightColorChanged(int i) {
        super.onABSFaultWarningLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onACCIndicateLightColorChanged(int i) {
        super.onACCIndicateLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onACCIndicateLightStateChanged(int i) {
        super.onACCIndicateLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAccCruisingSpeedChanged(int i) {
        super.onAccCruisingSpeedChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAccCruisingSpeedColorChanged(int i) {
        super.onAccCruisingSpeedColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAccCruisingSpeedValueChanged(int i) {
        super.onAccCruisingSpeedValueChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAirHeatOilDisplayChanged(int i) {
        super.onAirHeatOilDisplayChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAirHeatingDisplayStateChanged(int i) {
        super.onAirHeatingDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAirHeatingOilWarnChanged(int i) {
        super.onAirHeatingOilWarnChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAlarmBuzzleStateChange(int i) {
        if (this.h == i) {
            return;
        }
        this.h = i;
        super.onAlarmBuzzleStateChange(i);
        BydLogHelper.logD("onAlarmBuzzleStateChange: state " + i);
        EventBus.getDefault().post(new b.b.a.c.a(70005, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAppCountdownHourChanged(int i) {
        super.onAppCountdownHourChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAppCountdownMinuteChanged(int i) {
        super.onAppCountdownMinuteChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAppointmentHourChanged(int i) {
        super.onAppointmentHourChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAppointmentMinuteChanged(int i) {
        super.onAppointmentMinuteChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAverageEneryConsumptionDisplayStateChanged(int i) {
        super.onAverageEneryConsumptionDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAverageFuelConsumptionDisplayStateChanged(int i) {
        super.onAverageFuelConsumptionDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onAverageSpeedChanged(int i) {
        super.onAverageSpeedChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onBacklightBrightnessChanged(int i) {
        if (this.q == i) {
            return;
        }
        this.q = i;
        super.onBacklightBrightnessChanged(i);
        BydLogHelper.logD("onBacklightBrightnessChanged: brightness " + i);
        if (b.b.g.r.e.c(70001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(70001, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onBacklightModeStateChanged(int i, int i2) {
        super.onBacklightModeStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onBatteryPercentChanged(int i) {
        super.onBatteryPercentChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onBrakeSysFaultLightColorChanged(int i) {
        super.onBrakeSysFaultLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onBrakeSysFaultLightStateChanged(int i) {
        super.onBrakeSysFaultLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCallInfoResultChanged(int i) {
        super.onCallInfoResultChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onChargeAppTimeOptionChanged(int i) {
        super.onChargeAppTimeOptionChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onChargeDisplayChanged(int i) {
        super.onChargeDisplayChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onChargeNoticeChanged(int i) {
        super.onChargeNoticeChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onChargePercentChanged(int i) {
        super.onChargePercentChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onChargePowerChanged(float f2) {
        super.onChargePowerChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onChargeRestTimeChanged(int[] iArr) {
        super.onChargeRestTimeChanged(iArr);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCoolantTempHighWarnLightColorChanged(int i) {
        super.onCoolantTempHighWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCoolantTempHighWarnLightStateChanged(int i) {
        super.onCoolantTempHighWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCruiseCtrlIndicatorColorChanged(int i) {
        super.onCruiseCtrlIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCruiseCtrlIndicatorStateChanged(int i) {
        super.onCruiseCtrlIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCurrentDriveInterFaceChanged(int i) {
        super.onCurrentDriveInterFaceChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCurrentJourneyDriveMileageChanged(float f2) {
        super.onCurrentJourneyDriveMileageChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onCurrentJourneyDriveTimeChanged(float f2) {
        super.onCurrentJourneyDriveTimeChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDashboardAlarmStateChanged(int i) {
        super.onDashboardAlarmStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDayNightModeChanged(int i) {
        super.onDayNightModeChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDeviationStateChanged(int i) {
        super.onDeviationStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDirectTypePressDisplayStateChanged(int i) {
        super.onDirectTypePressDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDirectionInfoChanged(int i) {
        super.onDirectionInfoChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDischargeElecEnergyChanged(float f2) {
        super.onDischargeElecEnergyChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDischargeIndicatorColorChanged(int i) {
        super.onDischargeIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDischargeModeChanged(int i) {
        super.onDischargeModeChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDischargeUiStateChanged(int i) {
        super.onDischargeUiStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDishargeIndicatorStateChanged(int i) {
        super.onDishargeIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDistanceMileageUnitChanged(int i) {
        super.onDistanceMileageUnitChanged(i);
        BydLogHelper.logD("onDistanceMileageUnitChanged: state " + i);
        if (this.t == i) {
            return;
        }
        this.t = i;
        EventBus.getDefault().post(new b.b.a.c.a(70019, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDoorStateChanged(int i, int i2) {
        super.onDoorStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDrivePowerLimitIndicatorColorChanged(int i) {
        super.onDrivePowerLimitIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onDrivePowerLimitIndicatorStateChanged(int i) {
        super.onDrivePowerLimitIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onECOIndicatorColorChanged(int i) {
        super.onECOIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onECOIndicatorStateChanged(int i) {
        super.onECOIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onELECParkingColorChanged(int i) {
        super.onELECParkingColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onELECParkingStateChanged(int i) {
        super.onELECParkingStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onESPFailWarnLightColorChanged(int i) {
        super.onESPFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onESPFailWarnLightStateChanged(int i) {
        super.onESPFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onEVIndicatorColorChanged(int i) {
        super.onEVIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onEVIndicatorStateChanged(int i) {
        super.onEVIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onEnergyDisplayChanged(int i) {
        super.onEnergyDisplayChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onEnergyFeedbackChanged(int i) {
        super.onEnergyFeedbackChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onEngineFailWarnLightColorChanged(int i) {
        super.onEngineFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onEngineFailWarnLightStateChanged(int i) {
        super.onEngineFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onExpectChargeDisplayChanged(int i) {
        super.onExpectChargeDisplayChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onExpectChargeStateChanged(int i) {
        super.onExpectChargeStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onExternalChargePowerChanged(float f2) {
        super.onExternalChargePowerChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onExternalChargingPowerChanged(float f2) {
        if (this.m == f2) {
            return;
        }
        this.m = f2;
        super.onExternalChargingPowerChanged(f2);
        BydLogHelper.logD("onExternalChargingPowerChanged: value " + f2);
        EventBus.getDefault().post(new b.b.a.c.a(70008, f2));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultIndicatorChanged(int i, int i2) {
        super.onFaultIndicatorChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultIndicatorColorChanged(int i, int i2) {
        super.onFaultIndicatorColorChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultMuddyIndicatorChanged(int i) {
        super.onFaultMuddyIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultMuddyIndicatorColorChanged(int i) {
        super.onFaultMuddyIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultNormalIndicatorChanged(int i) {
        super.onFaultNormalIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultNormalIndicatorColorChanged(int i) {
        super.onFaultNormalIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultOilLifeDetectIndicatorChanged(int i) {
        super.onFaultOilLifeDetectIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultOilLifeDetectIndicatorColorChanged(int i) {
        super.onFaultOilLifeDetectIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultSandIndicatorChanged(int i) {
        super.onFaultSandIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFaultSandIndicatorColorChanged(int i) {
        super.onFaultSandIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFirstMenuChanged(int i) {
        super.onFirstMenuChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFuelConsumptionDisplayStateChanged(int i) {
        super.onFuelConsumptionDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onFuelLowAlarmChanged(int i) {
        super.onFuelLowAlarmChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onGPFIndicatorColorChanged(int i) {
        super.onGPFIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onGPFIndicatorStateChanged(int i) {
        super.onGPFIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onGapDetectionChanged(int i) {
        super.onGapDetectionChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onHEVIndicatorColorChanged(int i) {
        super.onHEVIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onHEVIndicatorStateChanged(int i) {
        super.onHEVIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onHeadlampFailWarnLightColorChanged(int i) {
        super.onHeadlampFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onHeadlampFailWarnLightStateChanged(int i) {
        super.onHeadlampFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onINSAdasCenterDisplayChanged(int i) {
        super.onINSAdasCenterDisplayChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onINSCenterDisplay(int i) {
        super.onINSCenterDisplay(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onINSNavDisplayMode(int i) {
        BydLogHelper.logD("onINSNavDisplayMode: value " + i);
        super.onINSNavDisplayMode(i);
        if (this.o == i) {
            return;
        }
        this.o = i;
        if (b.b.g.r.e.c(70009)) {
            return;
        }
        BydLogHelper.logD("onINSNavDisplayMode: sendMessage value " + i);
        EventBus.getDefault().post(new b.b.a.c.a(70009, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInsUiChanged(int i) {
        super.onInsUiChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInstantFuelConsumptionDisplayStateChanged(int i) {
        super.onInstantFuelConsumptionDisplayStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInstantFuelConsumptionUnitChanged(int i) {
        super.onInstantFuelConsumptionUnitChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInstrumentAutoDimmStateChange(int i) {
        if (this.f857d == i) {
            return;
        }
        this.f857d = i;
        super.onInstrumentAutoDimmStateChange(i);
        EventBus.getDefault().post(new b.b.a.c.a(70002, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInstrumentBrightnessChanged(int i) {
        if (this.f856c == i) {
            return;
        }
        this.f856c = i;
        super.onInstrumentBrightnessChanged(i);
        BydLogHelper.logD("onInstrumentBrightnessChanged: value " + i);
        EventBus.getDefault().post(new b.b.a.c.a(70001, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInstrumentScreenTypeChanged(int i) {
        super.onInstrumentScreenTypeChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInstrumentThemeChange(int i) {
        if (this.f855b == i) {
            return;
        }
        this.f855b = i;
        super.onInstrumentThemeChange(i);
        BydLogHelper.logD("onInstrumentThemeChange: value " + i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onInstrumentViewChanged(int i) {
        super.onInstrumentViewChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onKeyDetectionReminderChanged(int i) {
        super.onKeyDetectionReminderChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLaneLineColorChanged(int i) {
        super.onLaneLineColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLaneLineStateChanged(int i) {
        super.onLaneLineStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLast50KmPowerConsumeChanged(float f2) {
        super.onLast50KmPowerConsumeChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLineValueChanged(int i, int i2) {
        super.onLineValueChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLinkErrKeyTimeChanged(int i, int i2) {
        super.onLinkErrKeyTimeChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLowFuelWarnLightColorChanged(int i) {
        super.onLowFuelWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLowFuelWarnLightStateChanged(int i) {
        super.onLowFuelWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLowPowerBatteryWarnLightColorChanged(int i) {
        super.onLowPowerBatteryWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onLowPowerBatteryWarnLightStateChanged(int i) {
        super.onLowPowerBatteryWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMainAlarmIndicatorColorChanged(int i) {
        super.onMainAlarmIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMainAlarmIndicatorStateChanged(int i) {
        super.onMainAlarmIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMaintenanceInfoChanged(int i, int i2) {
        if (this.k == i && this.l == i2) {
            return;
        }
        this.k = i;
        this.l = i2;
        super.onMaintenanceInfoChanged(i, i2);
        BydLogHelper.logD("onMaintenanceInfoChanged: typeName " + i + " infoValue : " + i2);
        EventBus.getDefault().post(new b.b.a.c.a(70007, i, i2));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMaintenanceInfoStateChanged(int i, int i2) {
        if (this.i == i && this.j == i2) {
            return;
        }
        this.i = i;
        this.j = i2;
        super.onMaintenanceInfoStateChanged(i, i2);
        BydLogHelper.logD("onMaintenanceInfoStateChanged: typeName " + i + " infoValue : " + i2);
        EventBus.getDefault().post(new b.b.a.c.a(70006, i, i2));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMalfunctionInfoChanged(int i, int i2) {
        if (this.f859f == i && this.g == i2) {
            return;
        }
        this.f859f = i;
        this.g = i2;
        super.onMalfunctionInfoChanged(i, i2);
        BydLogHelper.logD("onMalfunctionInfoChanged: typeName " + i + " hasMalfunction : " + i2);
        EventBus.getDefault().post(new b.b.a.c.a(70004, i, i2));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMalfunctionInfoChanged2(int i, int i2) {
        super.onMalfunctionInfoChanged2(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMapFocusChanged(int i) {
        super.onMapFocusChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMileageUnitChanged(int i) {
        if (this.n == i) {
            return;
        }
        this.n = i;
        super.onMileageUnitChanged(i);
        BydLogHelper.logD("onMileageUnitChanged: state " + i);
        if (b.b.g.r.e.c(70013)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(70013, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMileageValidFlagChanged(int i) {
        super.onMileageValidFlagChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMilesTypeChanged(int i) {
        super.onMilesTypeChanged(i);
        BydLogHelper.logD("onMilesTypeChanged: value " + i);
        if (this.f858e == i) {
            return;
        }
        this.f858e = i;
        BydLogHelper.logD("onMilesTypeChanged: send Message value : " + i);
        if (b.b.g.r.e.c(70003)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(70003, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onModuleStateChanged(int i, int i2) {
        super.onModuleStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onMusicInfoResultChanged(int i) {
        super.onMusicInfoResultChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onNaviDestinationCommandChanged(int i) {
        super.onNaviDestinationCommandChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onOKIndicatorColorChanged(int i) {
        super.onOKIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onOKIndicatorStateChanged(int i) {
        super.onOKIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onOdometerDisplayChanged(int i) {
        super.onOdometerDisplayChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onOilLevelAlarmIndicatorChanged(int i) {
        super.onOilLevelAlarmIndicatorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onOilLevelAlarmIndicatorColorChanged(int i) {
        super.onOilLevelAlarmIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onOutCarTemperatureChanged(int i) {
        super.onOutCarTemperatureChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPCWAlarmInstructionChanged(int i) {
        super.onPCWAlarmInstructionChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerBatFailWarnLightColorChanged(int i) {
        super.onPowerBatFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerBatFailWarnLightStateChanged(int i) {
        super.onPowerBatFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerBatteryChargeConnectIndicatorColorChanged(int i) {
        super.onPowerBatteryChargeConnectIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerBatteryChargeConnectIndicatorStateChanged(int i) {
        super.onPowerBatteryChargeConnectIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerBatteryHeatWarnLightColorChanged(int i) {
        super.onPowerBatteryHeatWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerBatteryHeatWarnLightStateChanged(int i) {
        super.onPowerBatteryHeatWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerOffErrInfoChanged(int i) {
        super.onPowerOffErrInfoChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerOnErrInfoChanged(int i) {
        super.onPowerOnErrInfoChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerSysFailWarnLightColorChanged(int i) {
        super.onPowerSysFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerSysFailWarnLightStateChanged(int i) {
        super.onPowerSysFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPowerUnitChanged(int i) {
        super.onPowerUnitChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPressureSupplySysFailWarnLightColorChanged(int i) {
        super.onPressureSupplySysFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPressureSupplySysFailWarnLightStateChanged(int i) {
        super.onPressureSupplySysFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPressureWarnLightColorChanged(int i) {
        super.onPressureWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onPressureWarnLightStateChanged(int i) {
        super.onPressureWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onRadioInfoResultChanged(int i) {
        super.onRadioInfoResultChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onRemoteDrivingReminderChanged(int i) {
        super.onRemoteDrivingReminderChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onRoadNameCheckStateChanged(int i) {
        super.onRoadNameCheckStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSRSFaultWarningLightChanged(int i) {
        super.onSRSFaultWarningLightChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSRSFaultWarningLightColorChanged(int i) {
        super.onSRSFaultWarningLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSafetyBeltStatusChanged(int i, int i2) {
        super.onSafetyBeltStatusChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSecondMenuChanged(int i) {
        super.onSecondMenuChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSmartKeySysWarnLightColorChanged(int i) {
        super.onSmartKeySysWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSmartKeySysWarnLightStateChanged(int i) {
        super.onSmartKeySysWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSoundFreqChanged(int i) {
        super.onSoundFreqChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSoundTypeChanged(int i) {
        super.onSoundTypeChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSpacingStateChanged(int i) {
        super.onSpacingStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSpeedUnitChanged(int i) {
        if (this.p == i) {
            return;
        }
        this.p = i;
        super.onSpeedUnitChanged(i);
        BydLogHelper.logD("onSpeedUnitChanged: state " + i);
        EventBus.getDefault().post(new b.b.a.c.a(70012, i));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSpeedUpCountStateChanged(int i) {
        super.onSpeedUpCountStateChanged(i);
        BydLogHelper.logD("onSpeedUpCountStateChanged: value " + i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSportIndicatorColorChanged(int i) {
        super.onSportIndicatorColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSportIndicatorStateChanged(int i) {
        super.onSportIndicatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSteeringSYSFailWarnLightColorChanged(int i) {
        super.onSteeringSYSFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onSteeringSYSFailWarnLightStateChanged(int i) {
        super.onSteeringSYSFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTextColorChanged(int i) {
        super.onTextColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTextInfoChanged(int i) {
        super.onTextInfoChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTimeIntervalStateChanged(int i) {
        super.onTimeIntervalStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTotalMileageChanged(int i) {
        super.onTotalMileageChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTravelTimeChanged(float f2) {
        super.onTravelTimeChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTyrePressureCarTypeChanged(int i) {
        super.onTyrePressureCarTypeChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTyrePressureSYSFailWarnLightColorChanged(int i) {
        super.onTyrePressureSYSFailWarnLightColorChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onTyrePressureSYSFailWarnLightStateChanged(int i) {
        super.onTyrePressureSYSFailWarnLightStateChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onUnitChanged(int i, int i2) {
        if (this.r == i && this.s == i2) {
            return;
        }
        this.r = i;
        this.s = i2;
        super.onUnitChanged(i, i2);
        BydLogHelper.logD("onUnitChanged: unitName " + i + ",unitValue:" + i2);
        int i3 = -1;
        if (i == 1) {
            i3 = 70015;
        } else if (i == 3) {
            i3 = 70017;
        } else if (i == 2) {
            i3 = 70016;
        }
        if (b.b.g.r.e.c(i3)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(i3, i, i2));
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onViewStatusChanged(int i) {
        super.onViewStatusChanged(i);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onWaterTempMeterPercentChanged(float f2) {
        super.onWaterTempMeterPercentChanged(f2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onWheelColorChanged(int i, int i2) {
        super.onWheelColorChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onWheelPressureChanged(int i, int i2) {
        super.onWheelPressureChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onWheelTemperatureChanged(int i, int i2) {
        super.onWheelTemperatureChanged(i, i2);
    }

    @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
    public void onWheelTemperatureColorChanged(int i, int i2) {
        super.onWheelTemperatureColorChanged(i, i2);
    }
}
