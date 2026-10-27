package b.b.g.k;

import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import net.sqlcipher.database.SQLiteDatabase;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydCarsettingsListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends AbsBYDAutoSettingListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f835b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f836c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile int f837d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f838e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile int f839f = Integer.MIN_VALUE;
    public volatile int g = Integer.MIN_VALUE;
    public volatile int h = Integer.MIN_VALUE;
    public volatile int i = Integer.MIN_VALUE;
    public volatile int j = Integer.MIN_VALUE;
    public volatile int k = Integer.MIN_VALUE;
    public volatile int l = Integer.MIN_VALUE;
    public volatile int m = Integer.MIN_VALUE;
    public volatile int n = Integer.MIN_VALUE;
    public volatile int o = Integer.MIN_VALUE;
    public volatile int p = Integer.MIN_VALUE;
    public volatile int q = Integer.MIN_VALUE;
    public volatile int r = Integer.MIN_VALUE;
    public volatile int s = Integer.MIN_VALUE;
    public volatile int t = Integer.MIN_VALUE;
    public volatile int u = Integer.MIN_VALUE;
    public volatile int v = Integer.MIN_VALUE;
    public volatile int w = Integer.MIN_VALUE;
    public volatile int x = Integer.MIN_VALUE;
    public volatile int y = Integer.MIN_VALUE;
    public volatile int z = Integer.MIN_VALUE;
    public volatile int A = Integer.MIN_VALUE;
    public volatile int B = Integer.MIN_VALUE;
    public volatile int C = Integer.MIN_VALUE;
    public volatile int D = Integer.MIN_VALUE;
    public volatile int E = Integer.MIN_VALUE;
    public volatile int F = Integer.MIN_VALUE;
    public volatile int G = Integer.MIN_VALUE;
    public volatile int H = Integer.MIN_VALUE;
    public volatile int I = Integer.MIN_VALUE;
    public volatile int J = Integer.MIN_VALUE;
    public volatile int K = Integer.MIN_VALUE;
    public volatile int L = Integer.MIN_VALUE;
    public volatile int M = Integer.MIN_VALUE;
    public volatile int N = Integer.MIN_VALUE;
    public volatile int O = Integer.MIN_VALUE;
    public volatile int P = Integer.MIN_VALUE;
    public volatile int Q = Integer.MIN_VALUE;
    public volatile int R = Integer.MIN_VALUE;
    public volatile int S = Integer.MIN_VALUE;
    public volatile int T = Integer.MIN_VALUE;
    public volatile int U = Integer.MIN_VALUE;
    public volatile int V = Integer.MIN_VALUE;
    public volatile int W = Integer.MIN_VALUE;
    public volatile int X = Integer.MIN_VALUE;
    public volatile int Y = Integer.MIN_VALUE;
    public volatile int Z = Integer.MIN_VALUE;
    public volatile int a0 = Integer.MIN_VALUE;
    public volatile int b0 = Integer.MIN_VALUE;
    public volatile int c0 = Integer.MIN_VALUE;
    public volatile int d0 = Integer.MIN_VALUE;
    public volatile int e0 = Integer.MIN_VALUE;
    public volatile int f0 = Integer.MIN_VALUE;
    public volatile int g0 = Integer.MIN_VALUE;
    public volatile int h0 = Integer.MIN_VALUE;
    public volatile int i0 = Integer.MIN_VALUE;
    public volatile int j0 = Integer.MIN_VALUE;
    public volatile int k0 = Integer.MIN_VALUE;
    public volatile int l0 = Integer.MIN_VALUE;
    public volatile int m0 = Integer.MIN_VALUE;
    public volatile int n0 = Integer.MIN_VALUE;
    public volatile int o0 = Integer.MIN_VALUE;
    public volatile int p0 = Integer.MIN_VALUE;
    public volatile int q0 = Integer.MIN_VALUE;
    public volatile int r0 = Integer.MIN_VALUE;
    public volatile int s0 = Integer.MIN_VALUE;
    public volatile int t0 = Integer.MIN_VALUE;
    public volatile int u0 = Integer.MIN_VALUE;
    public volatile int v0 = Integer.MIN_VALUE;
    public volatile int w0 = Integer.MIN_VALUE;
    public volatile int x0 = Integer.MIN_VALUE;
    public volatile int y0 = Integer.MIN_VALUE;
    public volatile int z0 = Integer.MIN_VALUE;
    public volatile int A0 = Integer.MIN_VALUE;
    public volatile int B0 = Integer.MIN_VALUE;
    public volatile int C0 = Integer.MIN_VALUE;
    public volatile int D0 = Integer.MIN_VALUE;
    public volatile int E0 = Integer.MIN_VALUE;
    public volatile int F0 = Integer.MIN_VALUE;
    public volatile int G0 = Integer.MIN_VALUE;
    public volatile int H0 = Integer.MIN_VALUE;
    public volatile int I0 = Integer.MIN_VALUE;
    public volatile int J0 = Integer.MIN_VALUE;
    public volatile int K0 = Integer.MIN_VALUE;
    public volatile int L0 = Integer.MIN_VALUE;
    public volatile int M0 = Integer.MIN_VALUE;
    public volatile int N0 = Integer.MIN_VALUE;
    public volatile int O0 = Integer.MIN_VALUE;
    public volatile int P0 = Integer.MIN_VALUE;
    public volatile int Q0 = Integer.MIN_VALUE;
    public volatile int R0 = Integer.MIN_VALUE;
    public volatile int S0 = Integer.MIN_VALUE;
    public volatile int T0 = Integer.MIN_VALUE;
    public volatile int U0 = Integer.MIN_VALUE;
    public volatile int V0 = Integer.MIN_VALUE;
    public volatile int W0 = Integer.MIN_VALUE;
    public volatile int X0 = Integer.MIN_VALUE;
    public volatile int Y0 = Integer.MIN_VALUE;
    public volatile int Z0 = Integer.MIN_VALUE;
    public volatile int a1 = Integer.MIN_VALUE;
    public volatile int b1 = Integer.MIN_VALUE;
    public volatile int c1 = Integer.MIN_VALUE;
    public volatile int d1 = Integer.MIN_VALUE;
    public volatile int e1 = Integer.MIN_VALUE;
    public volatile int f1 = Integer.MIN_VALUE;
    public volatile int g1 = Integer.MIN_VALUE;
    public volatile byte h1 = -1;
    public volatile int i1 = Integer.MIN_VALUE;
    public volatile int j1 = Integer.MIN_VALUE;
    public volatile int k1 = Integer.MIN_VALUE;
    public volatile int l1 = Integer.MIN_VALUE;
    public volatile int m1 = Integer.MIN_VALUE;
    public volatile int n1 = Integer.MIN_VALUE;
    public volatile int o1 = Integer.MIN_VALUE;
    public volatile int p1 = Integer.MIN_VALUE;
    public volatile int q1 = Integer.MIN_VALUE;
    public volatile int r1 = Integer.MIN_VALUE;
    public volatile int s1 = Integer.MIN_VALUE;
    public int t1 = -1;

    public final boolean a(int i, int i2) {
        return i2 == i;
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onACAutoAirModeChanged(int i) {
        if (a(i, this.f838e)) {
            return;
        }
        this.f838e = i;
        super.onACAutoAirModeChanged(i);
        BydLogHelper.logD("onACAutoAirModeChanged  state :" + i);
        if (b.b.g.r.e.c(30001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onACBTWindSwitchChanged(int i) {
        if (a(i, this.f835b)) {
            return;
        }
        this.f835b = i;
        super.onACBTWindSwitchChanged(i);
        BydLogHelper.logD("onACBTWindSwitchChanged  state :" + i);
        if (b.b.g.r.e.c(30011)) {
            return;
        }
        BydLogHelper.logD("onACBTWindSwitchChanged  state hasMessage :" + i);
        b.b.a.c.a.j(new b.b.a.c.a(30011, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onACPauseCycleSwitchChanged(int i) {
        if (a(i, this.f837d)) {
            return;
        }
        this.f837d = i;
        super.onACPauseCycleSwitchChanged(i);
        BydLogHelper.logD("onACPauseCycleSwitchChanged  state :" + i);
        if (b.b.g.r.e.c(30009)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30009, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onACTunnelCycleSwitchChanged(int i) {
        if (a(i, this.f836c)) {
            return;
        }
        this.f836c = i;
        super.onACTunnelCycleSwitchChanged(i);
        BydLogHelper.logD("onACTunnelCycleSwitchChanged  state :" + i);
        if (b.b.g.r.e.c(30010)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30010, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAcAutoPurificationStateChanged(int i) {
        if (a(i, this.a0)) {
            return;
        }
        this.a0 = i;
        super.onAcAutoPurificationStateChanged(i);
        BydLogHelper.logD("onAcAutoPurificationStateChanged   state :" + i);
        if (b.b.g.r.e.c(30008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAcKeyStartTimeChanged(int i) {
        super.onAcKeyStartTimeChanged(i);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAcWarmModeChanged(int i) {
        if (a(i, this.Z)) {
            return;
        }
        this.Z = i;
        super.onAcWarmModeChanged(i);
        BydLogHelper.logD("onAcWarmModeChanged   mode :" + i);
        if (b.b.g.r.e.c(30002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAmbientMusicModeState(int i, int i2) {
        if (a(i, this.H0) && a(i2, this.I0)) {
            return;
        }
        this.H0 = i;
        this.I0 = i2;
        super.onAmbientMusicModeState(i, i2);
        BydLogHelper.logD("onAmbientMusicModeState: " + i2 + "area ：" + i);
        if (b.b.g.r.e.c(20002)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(20002, i, i2));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAutoExternalRearMirrorFollowUpStateChanged(int i) {
        if (a(i, this.L)) {
            return;
        }
        this.L = i;
        super.onAutoExternalRearMirrorFollowUpStateChanged(i);
        BydLogHelper.logD("onAutoExternalRearMirrorFollowUpStateChanged: value " + i);
        if (b.b.g.r.e.c(50004)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(50004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAutoPowerOffStateChanged(int i) {
        if (a(i, this.b0)) {
            return;
        }
        this.b0 = i;
        super.onAutoPowerOffStateChanged(i);
        BydLogHelper.logD("onAutoPowerOffStateChanged   value :" + i);
        if (b.b.g.r.e.c(40012)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40012, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAutoPowerOnStateChanged(int i) {
        if (a(i, this.K)) {
            return;
        }
        this.K = i;
        super.onAutoPowerOnStateChanged(i);
        BydLogHelper.logD("onAutoPowerOnStateChanged: value " + i);
        if (b.b.g.r.e.c(40011)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40011, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAutoRainWiperStateChanged(int i) {
        if (a(i, this.l)) {
            return;
        }
        this.l = i;
        super.onAutoRainWiperStateChanged(i);
        BydLogHelper.logD("onAutoRainWiperStateChanged: state " + i);
        if (b.b.g.r.e.c(50001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(50001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onBackDoorOpenedHeightChanged(int i) {
        if (a(i, this.z)) {
            return;
        }
        this.z = i;
        super.onBackDoorOpenedHeightChanged(i);
        BydLogHelper.logD(" onBackDoorOpenedHeightChanged : height " + i);
        if (b.b.g.r.e.c(40001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onBackHomeLightDelayValueChanged(int i) {
        if (a(i, this.e0)) {
            return;
        }
        this.e0 = i;
        super.onBackHomeLightDelayValueChanged(i);
        BydLogHelper.logD("onBackHomeLightDelayValueChanged  : value " + i);
        if (b.b.g.r.e.c(10002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onBrakePedalModeChanged(int i) {
        if (a(i, this.P)) {
            return;
        }
        this.P = i;
        super.onBrakePedalModeChanged(i);
        BydLogHelper.logD("onBrakePedalModeChanged: value " + i);
        if (b.b.g.r.e.c(90002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onCarTipsSoundTypeChanged(int i) {
        super.onCarTipsSoundTypeChanged(i);
        BydLogHelper.logD("onCarTipsSoundTypeChanged   value :" + i);
        b.b.g.r.e.h(80003);
        b.b.a.c.a.j(new b.b.a.c.a(80003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onChargingPortSwitchChanged(int i) {
        if (a(i, this.A0)) {
            return;
        }
        this.A0 = i;
        super.onChargingPortSwitchChanged(i);
        if (b.b.g.r.e.c(140003) || b.b.g.r.e.c(140013)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(140003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onComSpeedDownStateChanged(int i) {
        if (a(i, this.p0)) {
            return;
        }
        this.p0 = i;
        BydLogHelper.logD("onComSpeedDownStateChanged  : value " + i);
        if (b.b.g.r.e.c(90006)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90006, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onComSpeedUpStateChanged(int i) {
        if (a(i, this.o0)) {
            return;
        }
        this.o0 = i;
        BydLogHelper.logD("onComSpeedUpStateChanged  : value " + i);
        if (b.b.g.r.e.c(90005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onComfortableParkingStateChanged(int i) {
        if (a(i, this.Q)) {
            return;
        }
        this.Q = i;
        super.onComfortableParkingStateChanged(i);
        BydLogHelper.logD("onComfortableParkingStateChanged : value " + i);
        if (b.b.g.r.e.c(90003) || i == this.t1) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90003, i));
        this.t1 = i;
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onCpdImsSwitchStateChanged(int i) {
        BydLogHelper.logD(" onCpdImsSwitchStateChanged : " + i);
        super.onCpdImsSwitchStateChanged(i);
        if (a(i, this.d1)) {
            return;
        }
        this.d1 = i;
        if (b.b.g.r.e.c(241008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(241008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onCuraSwitchStateChanged(int i) {
        super.onCuraSwitchStateChanged(i);
        BydLogHelper.logD(" onRearRightSeatGreetStateChanged =" + i);
        if (a(i, this.k1)) {
            return;
        }
        this.k1 = i;
        if (b.b.g.r.e.c(241008)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(241008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDelayPowerDownTimeChanged(int i) {
        BydLogHelper.logD(" onDelayPowerDownTimeChanged : " + i);
        super.onDelayPowerDownTimeChanged(i);
        if (a(i, this.Y0)) {
            return;
        }
        this.Y0 = i;
        if (b.b.g.r.e.c(400003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDelayPowerOffStatusChanged(int i) {
        BydLogHelper.logD(" onDelayPowerOffStatusChanged : " + i);
        super.onDelayPowerOffStatusChanged(i);
        if (a(i, this.X0)) {
            return;
        }
        this.X0 = i;
        if (b.b.g.r.e.c(400003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDelayPowerOffTimeChanged(int i) {
        BydLogHelper.logD(" onDelayPowerOffTimeChanged : " + i);
        super.onDelayPowerOffTimeChanged(i);
        if (a(i, this.Z0)) {
            return;
        }
        this.Z0 = i;
        if (b.b.g.r.e.c(400003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDisinfStateChanged(int i) {
        if (a(i, this.T)) {
            return;
        }
        this.T = i;
        super.onDisinfStateChanged(i);
        BydLogHelper.logD("onDisinfStateChanged : state " + i);
        b.b.g.r.e.h(120004);
        b.b.a.c.a.j(new b.b.a.c.a(120004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDoorknobAutoShrinkStateChanged(int i) {
        if (a(i, this.B)) {
            return;
        }
        this.B = i;
        super.onDoorknobAutoShrinkStateChanged(i);
        BydLogHelper.logD("onDoorknobAutoShrinkStateChanged : state " + i);
        if (b.b.g.r.e.c(40002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDragModeForwardAndBackwardChanged(int i) {
        if (a(i, this.P0)) {
            return;
        }
        this.P0 = i;
        super.onDragModeForwardAndBackwardChanged(i);
        BydLogHelper.logD(" onDragModeForwardAndBackwardChanged : " + i);
        if (b.b.g.r.e.c(240010)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(240010, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDriveModeChanged(int i) {
        super.onDriveModeChanged(i);
        BydLogHelper.logD(" onDriveModeChanged =" + i);
        if (a(i, this.q1)) {
            return;
        }
        this.q1 = i;
        EventBus.getDefault().post(new b.b.a.c.a(50011, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDriverSeatAutoReturnB3Changded(int i) {
        if (a(i, this.M0)) {
            return;
        }
        this.M0 = i;
        super.onDriverSeatAutoReturnB3Changded(i);
        BydLogHelper.logD(" onDriverSeatAutoReturnB3Changded : " + i);
        if (b.b.g.r.e.c(50002)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(50002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDriverSeatAutoReturnStateChanged(int i) {
        if (a(i, this.M)) {
            return;
        }
        this.M = i;
        super.onDriverSeatAutoReturnStateChanged(i);
        BydLogHelper.logD("onDriverSeatAutoReturnStateChanged: value " + i);
        if (b.b.g.r.e.c(50002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(50002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDynamicSuspensionModeChanged(int i) {
        if (a(i, this.S)) {
            return;
        }
        this.S = i;
        super.onDynamicSuspensionModeChanged(i);
        BydLogHelper.logD("onDynamicSuspensionModeChanged : value " + i);
        if (b.b.g.r.e.c(90000)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90000, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onEnergyFeedbackStrengthChanged(int i) {
        if (a(i, this.t0)) {
            return;
        }
        this.t0 = i;
        super.onEnergyFeedbackStrengthChanged(i);
        BydLogHelper.logD("onEnergyFeedbackStrengthChanged : level ： " + i);
        if (b.b.g.r.e.c(150015)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(150015, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onFdComSpeedUpStateChanged(int i) {
        if (a(i, this.g1)) {
            return;
        }
        this.g1 = i;
        BydLogHelper.logD("onComSpeedUpStateChanged  : value " + i);
        if (b.b.g.r.e.c(90005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onFdDynamicSuspensionMode(int i) {
        if (a(i, this.O0)) {
            return;
        }
        this.O0 = i;
        super.onFdDynamicSuspensionMode(i);
        BydLogHelper.logD(" onFdDynamicSuspensionMode : " + i);
        if (b.b.g.r.e.c(90000)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90000, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onFridgeChildLockChanged(int i) {
        BydLogHelper.logD(" onFridgeChildLockChanged : " + i);
        super.onFridgeChildLockChanged(i);
        if (a(i, this.c1)) {
            return;
        }
        this.c1 = i;
        if (b.b.g.r.e.c(400007)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400007, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onFridgeDoorStateChanged(int i) {
        BydLogHelper.logD(" onFridgeDoorStateChanged : " + i);
        super.onFridgeDoorStateChanged(i);
        if (!a(i, this.U0)) {
            this.U0 = i;
            if (b.b.g.r.e.c(400000)) {
                return;
            }
            b.b.a.c.a.j(new b.b.a.c.a(400000, i));
            return;
        }
        BydLogHelper.logD(" onFridgeDoorStateChanged : " + this.U0 + ", " + i);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onFridgeWorkColdTemperatureChanged(int i) {
        super.onFridgeWorkColdTemperatureChanged(i);
        BydLogHelper.logD(" onFridgeWorkColdTemperatureChanged : " + i);
        super.onRefrigeratorWorkTemperatureChanged(i);
        if (b.b.g.r.e.c(400008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onFridgeWorkHeatTemperatureChanged(int i) {
        super.onFridgeWorkHeatTemperatureChanged(i);
        BydLogHelper.logD(" onFridgeWorkHeatTemperatureChanged : " + i);
        super.onRefrigeratorWorkTemperatureChanged(i);
        if (b.b.g.r.e.c(400008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onFridgeWorkSaveTemperatureChanged(int i) {
        super.onFridgeWorkSaveTemperatureChanged(i);
        BydLogHelper.logD(" onFridgeWorkSaveTemperatureChanged : " + i);
        super.onRefrigeratorWorkTemperatureChanged(i);
        if (b.b.g.r.e.c(400008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onGoalRoadModeChanged(int i) {
        super.onGoalRoadModeChanged(i);
        BydLogHelper.logD(" onGoalRoadModeChanged =" + i);
        if (a(i, this.l1)) {
            return;
        }
        this.l1 = i;
        if (b.b.g.r.e.c(50008)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(50008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHSpeedAutoCloseStateChanged(int i) {
        if (a(i, this.m0)) {
            return;
        }
        this.m0 = i;
        BydLogHelper.logD("onHSpeedAutoCloseStateChanged  : value " + i);
        if (b.b.g.r.e.c(40014)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40014, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHeadlampLevelChanged(int i) {
        if (a(i, this.f0)) {
            return;
        }
        this.f0 = i;
        super.onHeadlampLevelChanged(i);
        BydLogHelper.logD("onHeadlampLevelChanged  : level " + i);
        if (b.b.g.r.e.c(10001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHeadrestTurnHeightChanged(int i, int i2, float f2) {
        super.onHeadrestTurnHeightChanged(i, i2, f2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHeadrestTurnHorizationChanged(int i, int i2, float f2) {
        super.onHeadrestTurnHorizationChanged(i, i2, f2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHevChargingPortSwitchChanged(int i) {
        if (a(i, this.B0)) {
            return;
        }
        this.B0 = i;
        super.onHevChargingPortSwitchChanged(i);
        if (b.b.g.r.e.c(140003) || b.b.g.r.e.c(140013)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(140013, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHudBrightnessChanged(int i) {
        if (a(i, this.E)) {
            return;
        }
        this.E = i;
        super.onHudBrightnessChanged(i);
        BydLogHelper.logD("onHudBrightnessChanged : state " + i);
        if (b.b.g.r.e.c(60002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(60002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHudDisplayContentChanged(int i, int i2) {
        if (a(i, this.H) && a(i2, this.I)) {
            return;
        }
        this.H = i;
        this.I = i2;
        super.onHudDisplayContentChanged(i, i2);
        BydLogHelper.logD("onHudDisplayContentChanged : setSource " + i + "value :" + i2);
        if (b.b.g.r.e.c(60005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(60005, i, i2));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHudHeightChanged(int i) {
        if (a(i, this.D)) {
            return;
        }
        this.D = i;
        super.onHudHeightChanged(i);
        BydLogHelper.logD("onHudHeightChanged : state " + i);
        if (b.b.g.r.e.c(60001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(60001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHudModeChanged(int i) {
        if (a(i, this.G)) {
            return;
        }
        this.G = i;
        super.onHudModeChanged(i);
        BydLogHelper.logD("onHudModeChanged : state " + i);
        if (b.b.g.r.e.c(60004)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(60004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHudRotationAngleChanged(int i) {
        if (a(i, this.F)) {
            return;
        }
        this.F = i;
        super.onHudRotationAngleChanged(i);
        BydLogHelper.logD("onHudRotationAngleChanged : state " + i);
        if (b.b.g.r.e.c(60003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(60003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onHudStateChanged(int i) {
        if (a(i, this.C)) {
            return;
        }
        this.C = i;
        super.onHudStateChanged(i);
        BydLogHelper.logD("onHudStateChanged : state " + i);
        if (b.b.g.r.e.c(60000)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(60000, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onIALAreaChanged(int i) {
        if (a(i, this.y)) {
            return;
        }
        this.y = i;
        super.onIALAreaChanged(i);
        BydLogHelper.logD("onIALAreaChanged:  area " + i);
        if (b.b.g.r.e.c(20001)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(20001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onIALBrightnessChanged(int i, int i2) {
        if (a(i, this.w) && a(i2, this.x)) {
            return;
        }
        this.w = i;
        this.x = i2;
        super.onIALBrightnessChanged(i, i2);
        BydLogHelper.logD("onAmbientMulticolorStateChanged: " + i + "brightness ：" + i2);
        b.b.g.r.e.h(20004);
        EventBus.getDefault().post(new b.b.a.c.a(20004, i, i2));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onIALColorChanged(int i, int i2) {
        if (a(i, this.u) && a(i2, this.v)) {
            return;
        }
        this.u = i;
        this.v = i2;
        super.onIALColorChanged(i, i2);
        BydLogHelper.logD("onIALColorChanged: " + i + "color ：" + i2);
        if (b.b.g.r.e.c(20005)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(20005, i, i2));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onINSThemeChanged(int i) {
        super.onINSThemeChanged(i);
        BydLogHelper.logD(" onINSThemeChanged : state " + i);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onIntelligentAcAreaStateChanged(int i) {
        if (a(i, this.g0)) {
            return;
        }
        this.g0 = i;
        super.onIntelligentAcAreaStateChanged(i);
        BydLogHelper.logD("onIntelligentAcAreaStateChanged  : state " + i);
        if (b.b.g.r.e.c(30012)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30012, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onIntelligentLockStateChanged(int i) {
        if (a(i, this.q0)) {
            return;
        }
        this.q0 = i;
        super.onIntelligentLockStateChanged(i);
        BydLogHelper.logD("onIntelligentLockStateChanged  : state " + i);
        if (b.b.g.r.e.c(40016)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40016, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onIntelligentUnlockStateChanged(int i) {
        if (a(i, this.r0)) {
            return;
        }
        this.r0 = i;
        super.onIntelligentUnlockStateChanged(i);
        BydLogHelper.logD("onIntelligentUnlockStateChanged  : state " + i);
        if (b.b.g.r.e.c(40015)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40015, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onKeyVibratorLevelChanged(int i) {
        if (a(i, this.y0)) {
            return;
        }
        this.y0 = i;
        super.onKeyVibratorLevelChanged(i);
        BydLogHelper.logD("onKeyVibratorLevelChanged : level ： " + i);
        if (b.b.g.r.e.c(80007)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(80007, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onLanguageChanged(int i) {
        super.onLanguageChanged(i);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onLanternLanguageModeChanged(int i) {
        if (a(i, this.o)) {
            return;
        }
        this.o = i;
        super.onLanternLanguageModeChanged(i);
        BydLogHelper.logD("onLanternLanguageModeChanged: mode " + i);
        if (b.b.g.r.e.c(10005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onLeftHeadlampLevelChanged(int i) {
        if (a(i, this.E0)) {
            return;
        }
        this.E0 = i;
        super.onLeftHeadlampLevelChanged(i);
        BydLogHelper.logD("onHeadlampLevelChanged  : level " + i);
        if (b.b.g.r.e.c(10001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onLeftHomeLightDelayValueChanged(int i) {
        if (a(i, this.j)) {
            return;
        }
        this.j = i;
        super.onLeftHomeLightDelayValueChanged(i);
        BydLogHelper.logD("onLeftHomeLightDelayValueChanged:" + i);
        if (b.b.g.r.e.c(10003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onLiftModeStateChanged(int i) {
        if (a(i, this.G0)) {
            return;
        }
        this.G0 = i;
        super.onLiftModeStateChanged(i);
        BydLogHelper.logD("onLiftModeStateChanged  : level " + i);
        if (b.b.g.r.e.c(200008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(200008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onLockCarRiseWindowStateChange(int i) {
        if (a(i, this.s)) {
            return;
        }
        this.s = i;
        super.onLockCarRiseWindowStateChange(i);
        BydLogHelper.logD("onLockCarRiseWindowStateChange: value " + i);
        if (b.b.g.r.e.c(40010)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40010, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onLockOffChanged(int i) {
        if (a(i, this.c0)) {
            return;
        }
        this.c0 = i;
        super.onLockOffChanged(i);
        BydLogHelper.logD("onLockOffChanged  : state " + i);
        if (b.b.g.r.e.c(40003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onMassageLevelStateChanged(int i, int i2) {
        if (a(i, this.h0) && a(i2, this.i0)) {
            return;
        }
        this.h0 = i;
        this.i0 = i2;
        super.onMassageLevelStateChanged(i, i2);
        BydLogHelper.logD("onMassageLevelStateChanged  : state " + i2 + " type : " + i);
        if (i == 1 && !b.b.g.r.e.c(110013)) {
            b.b.a.c.a.j(new b.b.a.c.a(110012, i, i2));
            if (b.b.g.r.e.c(110013)) {
                b.b.g.r.e.h(110013);
            }
        }
        if (i == 2 && !b.b.g.r.e.c(110014)) {
            b.b.a.c.a.j(new b.b.a.c.a(110012, i, i2));
            if (b.b.g.r.e.c(110014)) {
                b.b.g.r.e.h(110014);
            }
        }
        if (i == 3 && !b.b.g.r.e.c(110015)) {
            b.b.a.c.a.j(new b.b.a.c.a(110012, i, i2));
            if (b.b.g.r.e.c(110023)) {
                b.b.g.r.e.h(110023);
            }
        }
        if (i != 4 || b.b.g.r.e.c(110016)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(110012, i, i2));
        if (b.b.g.r.e.c(110024)) {
            b.b.g.r.e.h(110024);
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onMassageModeStateChanged(int i, int i2) {
        if (a(i, this.j0) && a(i2, this.k0)) {
            return;
        }
        this.j0 = i;
        this.k0 = i2;
        super.onMassageModeStateChanged(i, i2);
        BydLogHelper.logD("onMassageModeStateChanged  : state " + i2 + " type : " + i);
        if (i == 1 && !b.b.g.r.e.c(110013)) {
            b.b.a.c.a.j(new b.b.a.c.a(110011, i, i2));
            if (b.b.g.r.e.c(110013)) {
                b.b.g.r.e.h(110013);
            }
        }
        if (i == 2 && !b.b.g.r.e.c(110014)) {
            b.b.a.c.a.j(new b.b.a.c.a(110011, i, i2));
            if (b.b.g.r.e.c(110014)) {
                b.b.g.r.e.h(110014);
            }
        }
        if (i == 3 && !b.b.g.r.e.c(110015)) {
            b.b.a.c.a.j(new b.b.a.c.a(110011, i, i2));
            if (b.b.g.r.e.c(110023)) {
                b.b.g.r.e.h(110023);
            }
        }
        if (i != 4 || b.b.g.r.e.c(110016)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(110011, i, i2));
        if (b.b.g.r.e.c(110024)) {
            b.b.g.r.e.h(110024);
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onMicroSwitchLockWindowStateChanged(int i) {
        if (a(i, this.t)) {
            return;
        }
        this.t = i;
        super.onMicroSwitchLockWindowStateChanged(i);
        BydLogHelper.logD("onMicroSwitchLockWindowStateChanged : state " + i);
        if (b.b.g.r.e.c(40006)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40006, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onMicroSwitchUnlockWindowStateChanged(int i) {
        if (a(i, this.k)) {
            return;
        }
        this.k = i;
        super.onMicroSwitchUnlockWindowStateChanged(i);
        BydLogHelper.logD("onMicroSwitchUnlockWindowStateChanged: state " + i);
        if (b.b.g.r.e.c(40007)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40007, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onMotorPretensionerSeatbeltStateChanged(int i) {
        if (a(i, this.R)) {
            return;
        }
        this.R = i;
        super.onMotorPretensionerSeatbeltStateChanged(i);
        BydLogHelper.logD("onMotorPretensionerSeatbeltStateChanged : value " + i);
        if (b.b.g.r.e.c(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onNavigationMediaStateChanged(int i) {
        super.onNavigationMediaStateChanged(i);
        BydLogHelper.logD("onNavigationMediaStateChanged  : state " + i);
        b.b.a.c.a.j(new b.b.a.c.a(80006, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onOverSpeedLockStateChanged(int i) {
        if (a(i, this.Y)) {
            return;
        }
        this.Y = i;
        super.onOverSpeedLockStateChanged(i);
        BydLogHelper.logD(" onOverSpeedLockStateChanged : state " + i);
        if (b.b.g.r.e.c(40008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onPM25PowerSwitchChanged(int i) {
        if (a(i, this.g)) {
            return;
        }
        this.g = i;
        super.onPM25PowerSwitchChanged(i);
        BydLogHelper.logD("onPM25PowerSwitchChanged :  state " + i);
        if (b.b.g.r.e.c(30005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onPM25SwitchCheckChanged(int i) {
        if (a(i, this.h)) {
            return;
        }
        this.h = i;
        super.onPM25SwitchCheckChanged(i);
        BydLogHelper.logD("onPM25SwitchCheckChanged : state " + i);
        if (b.b.g.r.e.c(30007)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30007, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onPM25TimeCheckChanged(int i) {
        if (a(i, this.i)) {
            return;
        }
        this.i = i;
        super.onPM25TimeCheckChanged(i);
        BydLogHelper.logD("onPM25TimeCheckChanged : state " + i);
        if (b.b.g.r.e.c(30006)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30006, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onPedalSuppressionSystemStateChanged(int i) {
        BydLogHelper.logD(" onPedalSuppressionSystemStateChanged =" + i);
        if (this.h1 != i) {
            this.h1 = (byte) i;
            b.b.a.c.a.j(new b.b.a.c.a(210109, i));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRainAutoLockWindowStateChanged(int i) {
        if (a(i, this.A)) {
            return;
        }
        this.A = i;
        super.onRainAutoLockWindowStateChanged(i);
        BydLogHelper.logD("onRainAutoLockWindowStateChanged: state " + i);
        if (b.b.g.r.e.c(40009)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40009, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearAcPanelAutoLockTimeChanged(int i) {
        if (a(i, this.n)) {
            return;
        }
        this.n = i;
        super.onRearAcPanelAutoLockTimeChanged(i);
        BydLogHelper.logD("onRearAcPanelAutoLockTimeChanged: time " + i);
        if (b.b.g.r.e.c(30004)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(30004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearRightSeatGreetStateChanged(int i) {
        super.onRearRightSeatGreetStateChanged(i);
        BydLogHelper.logD(" onRearRightSeatGreetStateChanged =" + i);
        if (a(i, this.j1)) {
            return;
        }
        this.j1 = i;
        if (b.b.g.r.e.c(50007)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(50007, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearSeatGreetStateChanged(int i) {
        if (a(i, this.L0)) {
            return;
        }
        this.L0 = i;
        super.onRearSeatGreetStateChanged(i);
        BydLogHelper.logD(" onRearSeatGreetStateChanged : " + i);
        if (b.b.g.r.e.c(50007)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(50007, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearViewEcuMirrorFlipStateChanged(int i) {
        if (a(i, this.N0)) {
            return;
        }
        this.N0 = i;
        super.onRearViewEcuMirrorFlipStateChanged(i);
        BydLogHelper.logD(" onRearViewEcuMirrorFlipStateChanged : " + i);
        if (b.b.g.r.e.c(50005)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(50005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearViewMirrorFlipChanged(int i) {
        if (a(i, this.J)) {
            return;
        }
        this.J = i;
        super.onRearViewMirrorFlipChanged(i);
        BydLogHelper.logD("onRearViewMirrorFlipChanged: value " + i);
        if (b.b.g.r.e.c(50005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(50005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearViewMirrorFlipSetChanged(int i) {
        if (a(i, this.s0)) {
            return;
        }
        this.s0 = i;
        super.onRearViewMirrorFlipSetChanged(i);
        BydLogHelper.logD("onRearViewMirrorFlipSetChanged  : value " + i);
        if (b.b.g.r.e.c(50006)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(50006, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearviewMirrorIsAutoLightStateChanged(int i) {
        BydLogHelper.logD(" onRearviewMirrorIsAutoLightStateChanged : " + i);
        super.onRearviewMirrorIsAutoLightStateChanged(i);
        if (a(i, this.S0)) {
            return;
        }
        this.S0 = i;
        if (b.b.g.r.e.c(300001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(300001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearviewMirrorIsStateChanged(int i) {
        super.onRearviewMirrorIsStateChanged(i);
        BydLogHelper.logD(" onRearviewMirrorIsStateChanged : " + i);
        if (a(i, this.T0)) {
            return;
        }
        this.T0 = i;
        if (b.b.g.r.e.c(300002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(300002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearviewMirrorIsTurnedStateChanged(int i) {
        super.onRearviewMirrorIsTurnedStateChanged(i);
        BydLogHelper.logD(" onRearviewMirrorIsTurnedStateChanged : " + i);
        if (a(i, this.R0)) {
            return;
        }
        this.R0 = i;
        if (b.b.g.r.e.c(300000)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(300000, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRecoverOrSaveParamsChanged(int i, int i2, int i3, int i4) {
        if (a(i, this.u0) && a(i2, this.v0) && a(i3, this.w0) && a(i4, this.x0)) {
            return;
        }
        this.u0 = i;
        this.v0 = i2;
        this.w0 = i3;
        this.x0 = i4;
        super.onRecoverOrSaveParamsChanged(i, i2, i3, i4);
        BydLogHelper.logD("onRecoverOrSaveParamsChanged : position ： " + i + "  location ：" + i2 + "state ：" + i3 + "code ：" + i4);
        b.b.a.c.a.j(new b.b.a.c.a(110010, i, i2, i3, i4));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRefrigeratorModeChanged(int i) {
        if (a(i, this.W)) {
            return;
        }
        this.W = i;
        super.onRefrigeratorModeChanged(i);
        BydLogHelper.logD("onRefrigeratorModeChanged   state :" + i);
        b.b.g.r.e.h(120002);
        b.b.a.c.a.j(new b.b.a.c.a(120002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRefrigeratorState(int i) {
        if (a(i, this.l0)) {
            return;
        }
        this.l0 = i;
        super.onRefrigeratorState(i);
        BydLogHelper.logD("onRefrigeratorState  : state " + i);
        b.b.a.c.a.j(new b.b.a.c.a(120005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRefrigeratorWorkModeChanged(int i) {
        BydLogHelper.logD(" onRefrigeratorWorkModeChanged : " + i);
        super.onRefrigeratorWorkModeChanged(i);
        if (a(i, this.V0)) {
            return;
        }
        this.V0 = i;
        if (b.b.g.r.e.c(400008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRefrigeratorWorkTemperatureChanged(int i) {
        BydLogHelper.logD(" onRefrigeratorWorkTemperatureChanged : " + i);
        super.onRefrigeratorWorkTemperatureChanged(i);
        if (b.b.g.r.e.c(400008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRemberPowerOffStateChanged(int i) {
        BydLogHelper.logD(" onRemberPowerOffStateChanged : " + i);
        super.onRemberPowerOffStateChanged(i);
        if (a(i, this.a1)) {
            return;
        }
        this.a1 = i;
        if (b.b.g.r.e.c(400005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRemenberPowerOffStateChanged(int i) {
        if (a(i, this.X)) {
            return;
        }
        this.X = i;
        super.onRemenberPowerOffStateChanged(i);
        BydLogHelper.logD("onRemenberPowerOffStateChanged   state :" + i);
        if (b.b.g.r.e.c(120003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(120003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRemoteControlSwitchChanged(int i) {
        if (a(i, this.r)) {
            return;
        }
        this.r = i;
        super.onRemoteControlSwitchChanged(i);
        BydLogHelper.logD("onRemoteUnlockWindowStateChanged: state " + i);
        if (b.b.g.r.e.c(40005)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40005, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRemoteControlUpwindowStateChanged(int i) {
        if (a(i, this.q)) {
            return;
        }
        this.q = i;
        super.onRemoteControlUpwindowStateChanged(i);
        BydLogHelper.logD("onRemoteLockWindowStateChanged: state " + i);
        if (b.b.g.r.e.c(40004)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRightHeadlampLevelChanged(int i) {
        if (a(i, this.F0)) {
            return;
        }
        this.F0 = i;
        super.onRightHeadlampLevelChanged(i);
        BydLogHelper.logD("onHeadlampLevelChanged  : level " + i);
        if (b.b.g.r.e.c(10001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRuningCloseDoorStateChanged(int i) {
        if (a(i, this.n0)) {
            return;
        }
        this.n0 = i;
        BydLogHelper.logD("onRuningCloseDoorStateChanged  : value " + i);
        if (b.b.g.r.e.c(40013)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40013, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSOCTargetLowLimitChanged(int i) {
        BydLogHelper.logD(" onSOCTargetLowLimitChanged : " + i);
        super.onSOCTargetLowLimitChanged(i);
        if (a(i, this.e1)) {
            return;
        }
        this.e1 = i;
        if (b.b.g.r.e.c(150029)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(150029, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSOCTargetRangeChange(int i) {
        if (a(i, this.C0)) {
            return;
        }
        this.C0 = i;
        super.onSOCTargetRangeChange(i);
        if (b.b.g.r.e.c(150016)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150016, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSOCTargetRangeChanged(int i) {
        BydLogHelper.logD(" onSOCTargetRangeChanged : " + i);
        super.onSOCTargetRangeChanged(i);
        if (a(i, this.f1)) {
            return;
        }
        this.f1 = i;
        if (b.b.g.r.e.c(150030)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(150030, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSafeBeltWarnStateChanged(int i) {
        if (a(i, this.z0)) {
            return;
        }
        this.z0 = i;
        super.onSafeBeltWarnStateChanged(i);
        BydLogHelper.logD("onSafeBeltWarnStateChanged : level ： " + i);
        if (b.b.g.r.e.c(80008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(80008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSandAbsModeStateChanged(int i) {
        super.onSandAbsModeStateChanged(i);
        BydLogHelper.logD(" onSandAbsModeStateChanged =" + i);
        if (a(i, this.r1)) {
            return;
        }
        this.r1 = i;
        if (i == 2) {
            EventBus.getDefault().post(new b.b.a.c.a(50013, i));
        }
        if (b.b.g.r.e.c(50010)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(50010, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSandAbsTipMessageChanged(int i) {
        super.onSandAbsTipMessageChanged(i);
        BydLogHelper.logD(" onSandAbsModeStateChanged =" + i);
        if (a(i, this.s1)) {
            return;
        }
        this.s1 = i;
        EventBus.getDefault().post(new b.b.a.c.a(50012, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSaveSeatParamsAllChanged(int i, int i2, int i3) {
        super.onSaveSeatParamsAllChanged(i, i2, i3);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatHeatingStateChanged(int i, int i2) {
        super.onSeatHeatingStateChanged(i, i2);
        BydLogHelper.logD("onSeatHeatingStateChanged  : seatID " + i + " state " + i2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatVentilatingStateChanged(int i, int i2) {
        super.onSeatVentilatingStateChanged(i, i2);
        BydLogHelper.logD("onSeatVentilatingStateChanged  : seatID " + i + " state " + i2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSmartWelcomeLightStateChanged(int i) {
        if (a(i, this.m)) {
            return;
        }
        this.m = i;
        super.onSmartWelcomeLightStateChanged(i);
        BydLogHelper.logD("onSmartWelcomeLightStateChanged: state " + i);
        if (b.b.g.r.e.c(10004)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10004, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSpeedReminderValueChanged(int i) {
        if (a(i, this.p)) {
            return;
        }
        this.p = i;
        super.onSpeedReminderValueChanged(i);
        BydLogHelper.logD("onSpeedReminderValueChanged: value " + i);
        if (b.b.g.r.e.c(80002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(80002, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteerAssisModeChanged(int i) {
        if (a(i, this.f839f)) {
            return;
        }
        this.f839f = i;
        super.onSteerAssisModeChanged(i);
        BydLogHelper.logD("onSteerAssisModeChanged  state :" + i);
        if (b.b.g.r.e.c(90001)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(90001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteerAssisSupportChanged(int i) {
        if (a(i, this.D0)) {
            return;
        }
        this.D0 = i;
        super.onSteerAssisSupportChanged(i);
        b.b.g.r.e.h(90007);
        EventBus.getDefault().post(new b.b.a.c.a(90007, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteerPositionAutoReturnStateChanged(int i) {
        if (a(i, this.O)) {
            return;
        }
        this.O = i;
        super.onSteerPositionAutoReturnStateChanged(i);
        BydLogHelper.logD("onSteerPositionAutoReturnStateChanged: value " + i);
        if (b.b.g.r.e.c(50003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(50003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteerPositionAutoReturnSwitchChanged(int i) {
        if (a(i, this.N)) {
            return;
        }
        this.N = i;
        super.onSteerPositionAutoReturnSwitchChanged(i);
        BydLogHelper.logD("onSteerPositionAutoReturnStateChanged: value " + i);
        if (b.b.g.r.e.c(50003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(50003, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSterilizationStateChanged(int i) {
        BydLogHelper.logD(" onSterilizationStateChanged : " + i);
        super.onSterilizationStateChanged(i);
        if (a(i, this.b1)) {
            return;
        }
        this.b1 = i;
        if (b.b.g.r.e.c(400006)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(400006, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSwControlStateChanged(int i) {
        super.onSwControlStateChanged(i);
        BydLogHelper.logD(" onSwControlStateChanged =" + i);
        if (this.i1 != i) {
            this.i1 = i;
            b.b.a.c.a.j(new b.b.a.c.a(300004, i));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTrailerLightCheckStateChanged(int i) {
        if (a(i, this.K0)) {
            return;
        }
        this.K0 = i;
        super.onTrailerLightCheckStateChanged(i);
        BydLogHelper.logD(" onTrailerLightCheckStateChanged : " + i);
        if (b.b.g.r.e.c(240009)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(240009, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTrailerModeMileageChanged(int i) {
        BydLogHelper.logD(" onTrailerModeMileageChanged : " + i);
        super.onTrailerModeMileageChanged(i);
        if (a(i, this.Q0)) {
            return;
        }
        this.Q0 = i;
        if (b.b.g.r.e.c(240012)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(240012, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTrailerModeStateChanged(int i) {
        if (a(i, this.J0)) {
            return;
        }
        this.J0 = i;
        super.onTrailerModeStateChanged(i);
        BydLogHelper.logD(" onTrailerModeStateChanged : " + i);
        if (b.b.g.r.e.c(240008)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(240008, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTrayActionStateChanged(int i) {
        if (a(i, this.d0)) {
            return;
        }
        this.d0 = i;
        super.onTrayActionStateChanged(i);
        BydLogHelper.logD("onTrayActionStateChanged  : value " + i);
        b.b.a.c.a.j(new b.b.a.c.a(120001, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTrayStateChanged(int i, int i2) {
        if (a(i, this.U) && a(i2, this.V)) {
            return;
        }
        this.U = i;
        this.V = i2;
        super.onTrayStateChanged(i, i2);
        BydLogHelper.logD("onTrayStateChanged   state :" + i);
        b.b.g.r.e.h(120000);
        b.b.a.c.a.j(new b.b.a.c.a(120000, i2));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTurnRearviewMirrorChanged(int i, float f2, float f3) {
        super.onTurnRearviewMirrorChanged(i, f2, f3);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTurnSeatCaliperSplintChanged(int i, int i2, float f2) {
        super.onTurnSeatCaliperSplintChanged(i, i2, f2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTurnSeatCushionChanged(int i, int i2, float f2) {
        super.onTurnSeatCushionChanged(i, i2, f2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTurnSeatHeightChanged(int i, int i2, float f2) {
        super.onTurnSeatHeightChanged(i, i2, f2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTurnSeatHorizationChanged(int i, int i2, float f2) {
        super.onTurnSeatHorizationChanged(i, i2, f2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTurnSeatbackrestChanged(int i, int i2, float f2) {
        super.onTurnSeatbackrestChanged(i, i2, f2);
        BydLogHelper.logD("onTurnSeatHeightChanged  : location " + i + " orientation " + i2 + "value " + f2);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onTurnSteeringWheelChanged(int i, float f2, float f3) {
        super.onTurnSteeringWheelChanged(i, f2, f3);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onWadingPatternSocTipsChanaged(int i) {
        super.onWadingPatternSocTipsChanaged(i);
        BydLogHelper.logD(" onWadingPatternSocTipsChanaged =" + i);
        if (a(i, this.m1)) {
            return;
        }
        this.m1 = i;
        EventBus.getDefault().post(new b.b.a.c.a(50009, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onWadingPatternSpeedTipsChanged(int i) {
        super.onWadingPatternSpeedTipsChanged(i);
        BydLogHelper.logD(" onWadingPatternSpeedTipsChanged =" + i);
        if (a(i, this.o1)) {
            return;
        }
        this.o1 = i;
        EventBus.getDefault().post(new b.b.a.c.a(50009, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onWadingPatternStateTipsChanged(int i) {
        super.onWadingPatternStateTipsChanged(i);
        BydLogHelper.logD(" onWadingPatternStateTipsChanged =" + i);
        if (a(i, this.n1)) {
            return;
        }
        this.n1 = i;
        EventBus.getDefault().post(new b.b.a.c.a(50009, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onWadingPatternTestTipsChanged(int i) {
        super.onWadingPatternTestTipsChanged(i);
        BydLogHelper.logD(" onWadingPatternTestTipsChanged =" + i);
        if (a(i, this.p1)) {
            return;
        }
        this.p1 = i;
        EventBus.getDefault().post(new b.b.a.c.a(50009, i));
    }
}
