package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoChargingDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f1265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoChargingDevice f1266b;

    public static synchronized c K() {
        if (f1265a == null) {
            f1265a = new c();
        }
        return f1265a;
    }

    public int A() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargingScheduleState();
        }
        return 0;
    }

    public int A0(int i) {
        b.b.g.r.e.j(140005, i == 2 ? 1 : 2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setPorducePowerWhenLowPower(i);
        }
        return 0;
    }

    public double B() {
        if (this.f1266b != null) {
            return r0.getDischargeElectric();
        }
        return 0.0d;
    }

    public int B0(int i) {
        b.b.g.r.e.j(140011, i == 2 ? 1 : 2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setSituPowerGenerationState(i);
        }
        return 0;
    }

    public int C() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeGunTimer();
        }
        return 0;
    }

    public int C0(int i, int i2, int i3, int i4, int i5) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setSyncTime(i, i2, i3, i4, i5);
        }
        return 0;
    }

    public int D() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeOverReason();
        }
        return 0;
    }

    public int D0(int i) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setWirelessChargingFRSwitchState(i);
        }
        return 0;
    }

    public int E() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeRemainTime();
        }
        return 0;
    }

    public int E0(int i) {
        b.b.g.r.e.j(140014, i == 1 ? 2 : 1);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setWirelessChargingSwitchState(i);
        }
        return 0;
    }

    public int F() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeRequestState();
        }
        return 0;
    }

    public void F0(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (this.f1266b == null) {
            return;
        }
        String simpleName = c.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener mBydChargingListener start listener == null : ");
        sb.append(absBYDAutoChargingListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1266b.unregisterListener(absBYDAutoChargingListener);
    }

    public long G() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeTime();
        }
        return 0L;
    }

    public int H() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeToggle();
        }
        return 0;
    }

    public int I() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeTotalTime();
        }
        return 0;
    }

    public int J() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getDischargeVoltage();
        }
        return 0;
    }

    public int L() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getMovePowerStationToggle();
        }
        return 0;
    }

    public int M() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getNoSenseChargeState();
        }
        return 0;
    }

    public int N() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getPorducePowerWhenLowPower();
        }
        return 0;
    }

    public int O(int i) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getPortCapState(i);
        }
        return 0;
    }

    public int P() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getSituPowerGenerationState();
        }
        return 0;
    }

    public int Q() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getSituPowerGenerationSupport();
        }
        return 0;
    }

    public double R() {
        if (this.f1266b != null) {
            return r0.getVoltageMax();
        }
        return 0.0d;
    }

    public int S() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getVtovChargeBatteryLevelNum();
        }
        return 0;
    }

    public int T() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getVtovChargeBatteryLevelNumLimit();
        }
        return 0;
    }

    public int U() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getVtovDischargeConnectState();
        }
        return 0;
    }

    public int V() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getVtovDischargeLimitVal();
        }
        return 0;
    }

    public int W() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getVtovDischargeLowPowerHint();
        }
        return 0;
    }

    public int X() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getVtovDischargeLowestVal();
        }
        return 0;
    }

    public double Y() {
        if (this.f1266b != null) {
            return r0.getVtovDischargeQuantity();
        }
        return 0.0d;
    }

    public int Z() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getWirelessChargingFRState();
        }
        return 0;
    }

    public int a() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getACChargingLimit();
        }
        return 0;
    }

    public int a0() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getWirelessChargingFRSwitchState();
        }
        return 0;
    }

    public byte[] b() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        return bYDAutoChargingDevice != null ? bYDAutoChargingDevice.getBMSWakeNoSenseCharge() : new byte[0];
    }

    public int b0() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getWirelessChargingPowerFLState();
        }
        return 0;
    }

    public int c() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getBatteryManagementDeviceState();
        }
        return 0;
    }

    public int c0() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getWirelessChargingPowerFRState();
        }
        return 0;
    }

    public int d() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getBatteryType();
        }
        return 0;
    }

    public int d0() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getWirelessChargingState();
        }
        return 0;
    }

    public int e() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getCarPlanAccOnState();
        }
        return 0;
    }

    public int e0() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getWirelessChargingSwitchState();
        }
        return 0;
    }

    public int f() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getCarPlanBatteryHeatState();
        }
        return 0;
    }

    public void f0(Context context) {
        this.f1266b = BYDAutoChargingDevice.getInstance(context);
    }

    public int g() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getCarPlanState();
        }
        return 0;
    }

    public void g0(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (this.f1266b == null) {
            return;
        }
        String simpleName = c.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener mBydChargingListener start listener == null : ");
        sb.append(absBYDAutoChargingListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1266b.registerListener(absBYDAutoChargingListener);
    }

    public int h() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargeBatteryLevelBegin();
        }
        return 0;
    }

    public int h0(int i, int i2) {
        b.b.g.r.e.j(140015, i2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.sendVtovDischargeLimitVal(i);
        }
        return 0;
    }

    public int i() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargeBatteryLimitTemp();
        }
        return 0;
    }

    public int i0(int i) {
        b.b.g.r.e.j(140019, i);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setACChargingLimit(i);
        }
        return 0;
    }

    public int j() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargeBatteryTemp();
        }
        return 0;
    }

    public int j0(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setCarPlan(i, i2, i3, i4, i5, i6, i7, i8);
        }
        return 0;
    }

    public double k() {
        if (this.f1266b != null) {
            return r0.getChargeElectric();
        }
        return 0.0d;
    }

    public int k0(int i) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setCarPlanState(i);
        }
        return 0;
    }

    public int l() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargePreheatAllow();
        }
        return 0;
    }

    public int l0(int i) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setChargePlanState(i);
        }
        return 0;
    }

    public int m() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargePreheatState();
        }
        return 0;
    }

    public int m0(int i) {
        b.b.g.r.e.j(140017, i == 2 ? 1 : 2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setChargePreheatState(i);
        }
        return 0;
    }

    public int n() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return (int) bYDAutoChargingDevice.getChargeStopCapacityState();
        }
        return 0;
    }

    public int n0(int i, int i2) {
        b.b.g.r.e.j(140002, i2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setChargeStopCapacityState(i);
        }
        return 0;
    }

    public int o() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargeStopCapacityStateNotLi();
        }
        return 0;
    }

    public int o0(int i) {
        b.b.g.r.e.j(140020, i);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setChargeStopCapacityStateNotLi(i);
        }
        return 0;
    }

    public int p() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargeTempCtlState();
        }
        return 0;
    }

    public int p0(int i) {
        b.b.g.r.e.j(140000, i == 2 ? 1 : 2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setChargeTempCtlState(i);
        }
        return 0;
    }

    public int q() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargeVoltage();
        }
        return 0;
    }

    public int q0(long j) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setDischargeTime(j);
        }
        return 0;
    }

    public double r() {
        if (this.f1266b != null) {
            return r0.getChargeVoltageMax();
        }
        return 0.0d;
    }

    public int r0(int i) {
        b.b.g.r.e.j(140006, i == 2 ? 1 : 2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setDischargeToggle(i);
        }
        return 0;
    }

    public int s() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargerFaultState();
        }
        return 0;
    }

    public int s0(int i, int i2, int i3, int i4, int i5) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setEVChargePlanEndTime(i, i2, i3, i4, i5);
        }
        return 0;
    }

    public int t() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargerState();
        }
        return 0;
    }

    public int t0(int i, int i2, int i3, int i4, int i5) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setEVChargePlanStartTime(i, i2, i3, i4, i5);
        }
        return 0;
    }

    public int u(int i) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargingCapState(i);
        }
        return 0;
    }

    public int u0(byte[] bArr) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setElectricityPriceLevel(bArr);
        }
        return 0;
    }

    public int v() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargingGunState();
        }
        return 0;
    }

    public int v0(int i, int i2, int i3, int i4, int i5) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setHEVChargePlanEndTime(i, i2, i3, i4, i5);
        }
        return 0;
    }

    public int w() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargingPortLockRebackState();
        }
        return 0;
    }

    public int w0(int i, int i2, int i3, int i4, int i5) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setHEVChargePlanStartTime(i, i2, i3, i4, i5);
        }
        return 0;
    }

    public double x() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargingPower();
        }
        return 0.0d;
    }

    public int x0(int i) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setHEVChargingType(i);
        }
        return 0;
    }

    public int[] y() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        return bYDAutoChargingDevice != null ? bYDAutoChargingDevice.getChargingRestTime() : new int[0];
    }

    public int y0(int i) {
        b.b.g.r.e.j(140010, i == 2 ? 1 : 2);
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setMovePowerStationToggle(i);
        }
        return 0;
    }

    public int z() {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.getChargingScheduleEnableState();
        }
        return 0;
    }

    public int z0(int i) {
        BYDAutoChargingDevice bYDAutoChargingDevice = this.f1266b;
        if (bYDAutoChargingDevice != null) {
            return bYDAutoChargingDevice.setNoSenseChargeState(i);
        }
        return 0;
    }
}
