package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.ChargingTimerInfo;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydChargingListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends AbsBYDAutoChargingListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f840b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f841c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f842d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f843e = Float.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f844f = Float.MIN_VALUE;
    public int g = Integer.MIN_VALUE;
    public int h = Integer.MIN_VALUE;
    public long i = Long.MIN_VALUE;
    public int j = Integer.MIN_VALUE;
    public int k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;
    public int m = Integer.MIN_VALUE;
    public int n = Integer.MIN_VALUE;
    public int o = Integer.MIN_VALUE;
    public int p = Integer.MIN_VALUE;
    public int q = Integer.MIN_VALUE;
    public int r = Integer.MIN_VALUE;
    public int s = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onACChargingLimitChanged(int i) {
        if (this.r == i) {
            return;
        }
        this.r = i;
        super.onACChargingLimitChanged(i);
        if (b.b.g.r.e.c(140019)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140019, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onBMSWakeNoSenseChargeChanged(byte[] bArr) {
        super.onBMSWakeNoSenseChargeChanged(bArr);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onBatteryManagementDeviceStateChanged(int i) {
        if (this.n == i) {
            return;
        }
        this.n = i;
        super.onBatteryManagementDeviceStateChanged(i);
        b.b.g.r.e.h(140016);
        EventBus.getDefault().post(new b.b.a.c.a(140016, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onBatteryTypeChanged(int i) {
        super.onBatteryTypeChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onCapStateChanged(int i) {
        super.onCapStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onCarDischargeLowWarnChanged(int i) {
        super.onCarDischargeLowWarnChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onCarDischargeStateChanged(int i) {
        super.onCarDischargeStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onCarPlanAccOnStateChanged(int i) {
        super.onCarPlanAccOnStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onCarPlanBatteryHeatStateChanged(int i) {
        super.onCarPlanBatteryHeatStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onCarPlanStateChanged(int i) {
        super.onCarPlanStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargeLimitChanged(float f2) {
        super.onChargeLimitChanged(f2);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargePreheatStateChanged(int i) {
        if (this.q == i) {
            return;
        }
        this.q = i;
        super.onChargePreheatStateChanged(i);
        if (b.b.g.r.e.c(140017)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140017, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargeStopCapacityStateChanged(int i) {
        if (this.f841c == i) {
            return;
        }
        this.f841c = i;
        super.onChargeStopCapacityStateChanged(i);
        if (b.b.g.r.e.c(140002)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140002, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargeStopCapacityStateNotLiChanged(int i) {
        if (this.s == i) {
            return;
        }
        this.s = i;
        super.onChargeStopCapacityStateNotLiChanged(i);
        if (b.b.g.r.e.c(140020)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140020, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargeStopSwitchStateChanged(int i) {
        super.onChargeStopSwitchStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargeTempCtlStateChanged(int i) {
        if (this.f840b == i) {
            return;
        }
        this.f840b = i;
        super.onChargeTempCtlStateChanged(i);
        if (b.b.g.r.e.c(140000)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140000, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargerFaultStateChanged(int i) {
        super.onChargerFaultStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargerStateChanged(int i) {
        super.onChargerStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargerWorkStateChanged(int i) {
        super.onChargerWorkStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingCapStateChanged(int i, int i2) {
        super.onChargingCapStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingCapacityChanged(float f2) {
        super.onChargingCapacityChanged(f2);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingGunNotInsertedStateChanged(int i) {
        super.onChargingGunNotInsertedStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingGunStateChanged(int i) {
        if (this.k == i) {
            return;
        }
        this.k = i;
        super.onChargingGunStateChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(140009, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingModeChanged(int i) {
        super.onChargingModeChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingPortLockRebackStateChanged(int i) {
        super.onChargingPortLockRebackStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingPowerChanged(float f2) {
        super.onChargingPowerChanged(f2);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingRestTimeChanged(int i, int i2) {
        super.onChargingRestTimeChanged(i, i2);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingScheduleEnableStateChanged(int i) {
        super.onChargingScheduleEnableStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingScheduleStateChanged(int i) {
        super.onChargingScheduleStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingScheduleTimeCountDownChanged(int i, int i2) {
        super.onChargingScheduleTimeCountDownChanged(i, i2);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingStateChanged(int i) {
        super.onChargingStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingTimerInfoChanged(ChargingTimerInfo chargingTimerInfo) {
        super.onChargingTimerInfoChanged(chargingTimerInfo);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onChargingTypeChanged(int i) {
        super.onChargingTypeChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDisChargeWarningStateChanged(int i) {
        super.onDisChargeWarningStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDischargeConnecting() {
        super.onDischargeConnecting();
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDischargeGunConnectCountDown(int i) {
        super.onDischargeGunConnectCountDown(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDischargeLimitChanged(float f2) {
        if (this.f844f == f2) {
            return;
        }
        this.f844f = f2;
        super.onDischargeLimitChanged(f2);
        EventBus.getDefault().post(new b.b.a.c.a(140004, f2));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDischargeRequestStateChanged(int i) {
        super.onDischargeRequestStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDischargeStateChanged(int i, int i2) {
        super.onDischargeStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDischargeTimeChanged(long j) {
        if (this.i == j) {
            return;
        }
        this.i = j;
        super.onDischargeTimeChanged(j);
        EventBus.getDefault().post(new b.b.a.c.a(140007, j));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onDischargeToggleChanged(int i) {
        if (this.h == i) {
            return;
        }
        this.h = i;
        super.onDischargeToggleChanged(i);
        if (b.b.g.r.e.c(140006)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140006, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onFeatureChanged(String str, int i) {
        super.onFeatureChanged(str, i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onIntelligentKeepTemModeChanged(int i) {
        super.onIntelligentKeepTemModeChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onMovePowerStationToggleChanged(int i) {
        if (this.l == i) {
            return;
        }
        this.l = i;
        super.onMovePowerStationToggleChanged(i);
        if (b.b.g.r.e.c(140010)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140010, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onNoSenseChargeStateChanged(int i) {
        super.onNoSenseChargeStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onOBCWakeNoSenseChargeChanged(byte[] bArr) {
        super.onOBCWakeNoSenseChargeChanged(bArr);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onPorducePowerWhenLowPowerChanged(int i) {
        if (this.g == i) {
            return;
        }
        this.g = i;
        super.onPorducePowerWhenLowPowerChanged(i);
        if (b.b.g.r.e.c(140005)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140005, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onSituPowerGenerationStateChanged(int i) {
        if (this.o == i) {
            return;
        }
        this.o = i;
        super.onSituPowerGenerationStateChanged(i);
        if (b.b.g.r.e.c(140011)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(140011, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onSituPowerGenerationSupportChanged(int i) {
        if (this.p == i) {
            return;
        }
        this.p = i;
        super.onSituPowerGenerationSupportChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(140012, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onSmartChargingStateChanged(int i) {
        super.onSmartChargingStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onSocSaveSwitchChanged(int i) {
        super.onSocSaveSwitchChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onVtovChargeBatteryLevelNumChanged(int i) {
        super.onVtovChargeBatteryLevelNumChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onVtovDischargeConnectStateChanged(int i) {
        if (this.j == i) {
            return;
        }
        this.j = i;
        super.onVtovDischargeConnectStateChanged(i);
        b.b.g.r.e.h(140008);
        EventBus.getDefault().post(new b.b.a.c.a(140008, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onVtovDischargeLimitValChanged(int i) {
        if (this.f842d == i) {
            return;
        }
        this.f842d = i;
        super.onVtovDischargeLimitValChanged(i);
        b.b.g.r.e.h(140015);
        EventBus.getDefault().post(new b.b.a.c.a(140015, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onVtovDischargeLowestValChanged(int i) {
        super.onVtovDischargeLowestValChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onVtovDischargeQuantityChanged(float f2) {
        if (this.f843e == f2) {
            return;
        }
        this.f843e = f2;
        super.onVtovDischargeQuantityChanged(f2);
        EventBus.getDefault().post(new b.b.a.c.a(140018, f2));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWeatherAndTimeRequestChanged(int i) {
        super.onWeatherAndTimeRequestChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirelessChargingFRStateChanged(int i) {
        super.onWirelessChargingFRStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirelessChargingFRSwitchStateChanged(int i) {
        super.onWirelessChargingFRSwitchStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirelessChargingOnline5sStateChanged(int i) {
        super.onWirelessChargingOnline5sStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirelessChargingPowerFLStateChanged(int i) {
        super.onWirelessChargingPowerFLStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirelessChargingPowerFRStateChanged(int i) {
        super.onWirelessChargingPowerFRStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirelessChargingStateChanged(int i) {
        super.onWirelessChargingStateChanged(i);
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirelessChargingSwitchStateChanged(int i) {
        if (this.m == i) {
            return;
        }
        this.m = i;
        super.onWirelessChargingSwitchStateChanged(i);
        b.b.g.r.e.h(140014);
        EventBus.getDefault().post(new b.b.a.c.a(140014, i));
    }

    @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
    public void onWirlessChargingStateChanged(int i) {
        super.onWirlessChargingStateChanged(i);
    }
}
