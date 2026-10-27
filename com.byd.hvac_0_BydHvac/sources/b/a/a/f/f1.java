package b.a.a.f;

import android.content.Context;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.hardware.bydauto.fragrance.BYDAutoFragranceDevice;
import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.showcarmode.BYDAutoShowCarDevice;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Method;
import java.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static BYDAutoAcDevice f109b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f110c = {-1, -1};
    public b.a.a.f.i1.c e;
    public BYDAutoPM2p5Device f;
    public b.a.a.f.i1.d g;
    public BYDAutoSettingDevice h;
    public b.a.a.f.i1.e i;
    public BYDAutoFragranceDevice j;
    public b.a.a.f.i1.b k;
    public BYDAutoBodyworkDevice l;
    public b.a.a.f.i1.a m;
    public BYDAutoShowCarDevice n;
    public String d = f1.class.getSimpleName();
    public String o = "persist.vendor.byd.cfg.huc.steering.wheel.position";

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static f1 f111a = new f1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: B1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void C1(int i, int i2) {
        BydLogHelper.logW(this.d, "set5LevelSeatVentilatingLevel  area = " + i + " -- value = " + i2);
        this.h.set5LevelSeatVentilatingLevel(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: B2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void C2(int i) {
        BydLogHelper.logW(this.d, "setFrontRowAirOutletState  state = " + i);
        f109b.setFrontRowAirOutletState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: D1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void E1(int i, int i2) {
        BydLogHelper.logW(this.d, "set5LevelSeatVentilatingMode  area = " + i + " -- value = " + i2);
        this.h.set5LevelSeatVentilatingMode(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: D2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void E2(int i, int i2) {
        BydLogHelper.logW(this.d, "setIntelligentControlState  area = " + i + " -- value = " + i2);
        f109b.setIntelligentControlState(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: F1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void G1(int i, int i2) {
        BydLogHelper.logW(this.d, "set5LevelSeatVentilatingState  area = " + i + " -- value = " + i2);
        this.h.set5LevelSeatVentilatingState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: F2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void G2(int i) {
        BydLogHelper.logW(this.d, "setAcMaxCoolingState state = " + i);
        f109b.setAcMaxCoolingState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: H1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void I1(int i) {
        BydLogHelper.logW(this.d, "setACAntibacterialControlState  state = " + i);
        f109b.setACAntibacterialControlState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: H2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void I2(int i) {
        BydLogHelper.logW(this.d, "setPM2p5AnionDetectState  state = " + i);
        this.f.setPM2p5AnionDetectState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: J1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void K1(int i) {
        BydLogHelper.logW(this.d, "setAcCompressorMode state = " + i);
        f109b.setAcCompressorMode(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: J2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void K2(int i) {
        BydLogHelper.logW(this.d, "setPM2p5CheckState  state = " + i);
        this.f.setPM2p5CheckState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: L1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void M1(int i) {
        BydLogHelper.logW(this.d, "setAcControlMode mode = " + i);
        f109b.setAcControlMode(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: L2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void M2(int i) {
        BydLogHelper.logW(this.d, "setPM2p5DualChannelDetectState  state = " + i);
        this.f.setPM2p5DualChannelDetectState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: N1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void O1(int i) {
        BydLogHelper.logW(this.d, "setAcCycleMode mode = " + i);
        f109b.setAcCycleMode(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: N2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void O2(int i) {
        BydLogHelper.logW(this.d, "setQuickCleanAirState state = " + i);
        f109b.setQuickCleanAirState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: P1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Q1(int i, int i2) {
        BydLogHelper.logW(this.d, "setAcDefrostState area = " + i + " -- state = " + i2);
        f109b.setAcDefrostState(0, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: P2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Q2(int i) {
        BydLogHelper.logW(this.d, "setQuickCleanAirState  state = " + i);
        f109b.setQuickCleanAirState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: R1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void S1(int i) {
        BydLogHelper.logW(this.d, "setAcHeatingState state = " + i);
        f109b.setAcHeatingState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: R2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void S2(int i) {
        BydLogHelper.logW(this.d, "setRearACkeyLockState state = " + i);
        f109b.setRearAcKeyLockState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U1(int i) {
        BydLogHelper.logW(this.d, "setAcRearPanelLockState  state = " + i);
        f109b.setAcRearPanelLockState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U2(int i) {
        BydLogHelper.logW(this.d, "setRearAcControlMode mode = " + i);
        f109b.setRearAcControlMode(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W1(int i) {
        BydLogHelper.logW(this.d, "setAcRemoteCtrlTime state = " + i);
        f109b.setAcRemoteCtrlTime(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W2(int i) {
        BydLogHelper.logW(this.d, "setRearAcWindLevel level = " + i);
        f109b.setRearAcWindLevel(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: X1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Y1(int i, int i2) {
        BydLogHelper.logW(this.d, "setAcTemperature area = " + i + " -- value = " + i2);
        f109b.setAcTemperature(i, i2, 0, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: X2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Y2(int i) {
        BydLogHelper.logW(this.d, "setRearAcWindMode mode = " + i);
        f109b.setRearAcWindMode(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Z1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a2(int i) {
        BydLogHelper.logW(this.d, "setAcTemperatureControlMode mode = " + i);
        f109b.setAcTemperatureControlMode(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Z2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a3(int i) {
        BydLogHelper.logW(this.d, "setSeatHeatingState  state = " + i);
        this.h.setSeatHeatingState(2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c2(int i, int i2) {
        BydLogHelper.logW(this.d, "setAcTuyereSwitchState  area = " + i + " -- state = " + i2);
        f109b.setAcTuyereSwitchState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c3(int i) {
        BydLogHelper.logW(this.d, "setSeatVentilatingState  state = " + i);
        this.h.setSeatVentilatingState(2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e2(int i) {
        BydLogHelper.logW(this.d, "setAcVentilationState state = " + i);
        f109b.setAcVentilationState(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e3(int i, int i2) {
        BydLogHelper.logW(this.d, "setSeatVentilatingState  area = " + i + " -- value = " + i2);
        this.h.setSeatVentilatingState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: f2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void g2(int i) {
        BydLogHelper.logW(this.d, "setAcWindLevel level = " + i);
        f109b.setAcWindLevel(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: f3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void g3(int i, int i2) {
        BydLogHelper.logW(this.d, "setSeatVentilatingState  area = " + i + " -- value = " + i2);
        this.h.setSeatVentilatingState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: h2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void i2(int i) {
        BydLogHelper.logW(this.d, "setAcWindMode mode = " + i);
        f109b.setAcWindMode(0, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: h3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void i3(int i, int i2) {
        BydLogHelper.logW(this.d, "setSeatHeatingState  area = " + i + " -- state = " + i2);
        this.h.setSeatHeatingState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: j2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void k2(int i, int i2) {
        BydLogHelper.logW(this.d, "setAirOutletState  area = " + i + " -- value = " + i2);
        f109b.setAirOutletState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: j3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void k3(int i, int i2) {
        BydLogHelper.logW(this.d, "setSeatHeatingState  area = " + i + " -- state = " + i2);
        this.h.setSeatHeatingState(i, i2);
    }

    public static f1 l0() {
        return a.f111a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m2(int i, String str, int i2) {
        int i3 = i + 128;
        BydLogHelper.logW(this.d, "setAirOutletValue  area = " + str + " -- value = " + i + " -- setValue = " + i3 + " -- setHexValue = " + Integer.toHexString(i3));
        f109b.setAirOutletValue(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m3(int i) {
        BydLogHelper.logW(this.d, "setSteeringWheelHeatingAutoState  state = " + i);
        this.h.setSteeringWheelHeatingAutoState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o2(int i) {
        BydLogHelper.logW(this.d, "setAutoCleanAirState state = " + i);
        f109b.setAutoCleanAirState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o3(int i) {
        BydLogHelper.logW(this.d, "setSteeringWheelHeatingGear  value = " + i);
        this.h.setSteeringWheelHeatingGear(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q2(int i, int i2) {
        BydLogHelper.logW(this.d, "setAvoidBlowPeopleState  area = " + i + " -- value = " + i2);
        f109b.setAvoidBlowPeopleState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q3(int i) {
        BydLogHelper.logW(this.d, "setSteeringWheelHeatingState  state = " + i);
        this.h.setSteeringWheelHeatingState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: r2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void s2(int i, int i2) {
        BydLogHelper.logW(this.d, "setBlowPeopleState  area = " + i + " -- value = " + i2);
        f109b.setBlowPeopleState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: r3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void s3(int i, int i2) {
        BydLogHelper.logW(this.d, "setSweepBlowState  area = " + i + " -- value = " + i2);
        f109b.setSweepBlowState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: t1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void u1(int i) {
        BydLogHelper.logW(this.d, "confirmCleanAirState state = " + i);
        f109b.confirmCleanAirState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: t2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void u2(int i) {
        BydLogHelper.logW(this.d, "setCallState  state = " + i);
        this.h.setCallState(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: t3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void u3() {
        BydLogHelper.logW(this.d, "start = ");
        f109b.start(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void w1(int i, int i2) {
        BydLogHelper.logW(this.d, "set5LevelSeatHeatingLevel  area = " + i + " -- value = " + i2);
        this.h.set5LevelSeatHeatingLevel(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void w2(int i) {
        BydLogHelper.logW(this.d, "setFragranceConcentration  value = " + i);
        this.j.setFragranceConcentration(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void w3() {
        BydLogHelper.logW(this.d, "startRearAc = ");
        f109b.startRearAc(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: x1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void y1(int i, int i2) {
        BydLogHelper.logW(this.d, "set5LevelSeatHeatingMode  area = " + i + " -- value = " + i2);
        this.h.set5LevelSeatHeatingMode(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: x2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void y2(int i) {
        BydLogHelper.logW(this.d, "setFragranceType  type = " + i);
        this.j.setFragranceType(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: x3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void y3() {
        BydLogHelper.logW(this.d, "stop = ");
        f109b.stop(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: z1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void A1(int i, int i2) {
        BydLogHelper.logW(this.d, "set5LevelSeatHeatingState  area = " + i + " -- value = " + i2);
        this.h.set5LevelSeatHeatingState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: z2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void A2(int i, int i2) {
        BydLogHelper.logW(this.d, "setFreeWindBTNState  area = " + i + " -- value = " + i2);
        f109b.setFreeWindBTNState(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: z3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void A3() {
        BydLogHelper.logW(this.d, "stopRearAc = ");
        f109b.stopRearAc(0);
    }

    public int A() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcStartState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acStartState = bYDAutoAcDevice.getAcStartState();
        BydLogHelper.logW(this.d, "getAcStartState  acStartState= " + acStartState);
        return acStartState;
    }

    public int A0() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logW(this.d, "getPM2p5WarningInfo mAutoPM2p5Device == null !!! ");
            return -1;
        }
        int pM2p5WarningInfo = bYDAutoPM2p5Device.getPM2p5WarningInfo();
        BydLogHelper.logW(this.d, "getPM2p5WarningInfo pm2p5WarningInfo = " + pM2p5WarningInfo);
        return pM2p5WarningInfo;
    }

    public int A4(final int i) {
        b.a.a.h.a.g(1400);
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.z0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f213a.q3(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int B(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcTemperature mAutoAcDevice == null !!! ");
            return -1;
        }
        int temprature = bYDAutoAcDevice.getTemprature(i);
        BydLogHelper.logW(this.d, "getAcTemperature temperature = " + temprature);
        return temprature;
    }

    public int B0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getPassengerSeatHeating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int passengerSeatHeating2aaConfig = bYDAutoAcDevice.getPassengerSeatHeating2aaConfig();
        BydLogHelper.logW(this.d, "getPassengerSeatHeating2aaConfig  state = " + passengerSeatHeating2aaConfig);
        return passengerSeatHeating2aaConfig;
    }

    public void B3() {
        b.a.a.f.i1.c cVar = new b.a.a.f.i1.c();
        this.e = cVar;
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice != null) {
            bYDAutoAcDevice.registerListener((AbsBYDAutoAcListener) cVar);
        }
        b.a.a.f.i1.d dVar = new b.a.a.f.i1.d();
        this.g = dVar;
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device != null) {
            bYDAutoPM2p5Device.registerListener((AbsBYDAutoPM2p5Listener) dVar);
        }
        b.a.a.f.i1.e eVar = new b.a.a.f.i1.e();
        this.i = eVar;
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.registerListener((AbsBYDAutoSettingListener) eVar);
        }
        b.a.a.f.i1.b bVar = new b.a.a.f.i1.b();
        this.k = bVar;
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice != null) {
            bYDAutoFragranceDevice.registerListener((AbsBYDAutoFragranceListener) bVar);
        }
        b.a.a.f.i1.a aVar = new b.a.a.f.i1.a();
        this.m = aVar;
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.l;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.registerListener((AbsBYDAutoBodyworkListener) aVar);
        }
        BydLogHelper.logW(this.d, "HvacManager registerListener end  = ");
    }

    public int B4(final int i, final int i2) {
        if (i == 0) {
            b.a.a.h.a.h(1027, i2);
        } else if (i == 1) {
            b.a.a.h.a.h(1051, i2);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f182a.s3(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int C() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcTemperatureControlMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int acTemperatureControlMode = bYDAutoAcDevice.getAcTemperatureControlMode();
        BydLogHelper.logW(this.d, "getAcTemperatureControlMode acTemperatureControlMode = " + acTemperatureControlMode);
        return acTemperatureControlMode;
    }

    public int C0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getPassengerSeatHeating2ffConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int passengerSeatHeating2ffConfig = bYDAutoAcDevice.getPassengerSeatHeating2ffConfig();
        BydLogHelper.logW(this.d, "getPassengerSeatHeating2ffConfig  state = " + passengerSeatHeating2ffConfig);
        return passengerSeatHeating2ffConfig;
    }

    public int C3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1722, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1723, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1724, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1725, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f68a.w1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int C4() {
        b.a.a.h.a.g(1022);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.x0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f207a.u3();
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int D(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcTuyereSwitchState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acTuyereSwitchState = bYDAutoAcDevice.getAcTuyereSwitchState(i);
        BydLogHelper.logW(this.d, "getAcTuyereSwitchState  area = " + i + " -- airOutletState = " + acTuyereSwitchState);
        return acTuyereSwitchState;
    }

    public int D0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getPassengerSeatHeating41cConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int passengerSeatHeating41cConfig = bYDAutoAcDevice.getPassengerSeatHeating41cConfig();
        BydLogHelper.logW(this.d, "getPassengerSeatHeating41cConfig  state = " + passengerSeatHeating41cConfig);
        return passengerSeatHeating41cConfig;
    }

    public int D3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1713, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1714, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1715, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1716, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.f0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f105a.y1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int D4() {
        b.a.a.h.a.g(1106);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.a0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f71a.w3();
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int E() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcType mAutoAcDevice == null !!! ");
            return -1;
        }
        int acType = bYDAutoAcDevice.getAcType();
        BydLogHelper.logW(this.d, "getAcType acType = " + acType);
        return acType;
    }

    public int E0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getPassengerSeatVentilating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int passengerSeatVentilating2aaConfig = bYDAutoAcDevice.getPassengerSeatVentilating2aaConfig();
        BydLogHelper.logW(this.d, "getPassengerSeatVentilating2aaConfig  state = " + passengerSeatVentilating2aaConfig);
        return passengerSeatVentilating2aaConfig;
    }

    public int E3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1705, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1706, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1707, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1708, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.j0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f142a.A1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int E4() {
        b.a.a.h.a.g(1022);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.y0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f210a.y3();
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int F() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcVentilationState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acVentilationState = bYDAutoAcDevice.getAcVentilationState();
        BydLogHelper.logW(this.d, "getAcVentilationState acVentilationState = " + acVentilationState);
        return acVentilationState;
    }

    public int F0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getPassengerSeatVentilating2ffConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int passengerSeatVentilating2ffConfig = bYDAutoAcDevice.getPassengerSeatVentilating2ffConfig();
        BydLogHelper.logW(this.d, "getPassengerSeatVentilating2ffConfig  state = " + passengerSeatVentilating2ffConfig);
        return passengerSeatVentilating2ffConfig;
    }

    public int F3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1717, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1718, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1720, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1721, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.d1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f94a.C1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int F4() {
        b.a.a.h.a.g(1106);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f97a.A3();
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int G() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcWarmTypeOnlineState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acWarmTypeOnlineState = bYDAutoAcDevice.getAcWarmTypeOnlineState();
        BydLogHelper.logW(this.d, "getAcWarmTypeOnlineState acWarmTypeOnlineState = " + acWarmTypeOnlineState);
        return acWarmTypeOnlineState;
    }

    public int G0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getPassengerSeatVentilating41cConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int passengerSeatVentilating41cConfig = bYDAutoAcDevice.getPassengerSeatVentilating41cConfig();
        BydLogHelper.logW(this.d, "getPassengerSeatVentilating41cConfig  state = " + passengerSeatVentilating41cConfig);
        return passengerSeatVentilating41cConfig;
    }

    public int G3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1709, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1710, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1711, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1712, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f160a.E1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public void G4() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice != null) {
            bYDAutoAcDevice.unregisterListener((AbsBYDAutoAcListener) this.e);
        }
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device != null) {
            bYDAutoPM2p5Device.unregisterListener((AbsBYDAutoPM2p5Listener) this.g);
        }
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.unregisterListener((AbsBYDAutoSettingListener) this.i);
        }
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice != null) {
            bYDAutoFragranceDevice.unregisterListener((AbsBYDAutoFragranceListener) this.k);
        }
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.l;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.unregisterListener((AbsBYDAutoBodyworkListener) this.m);
        }
        BydLogHelper.logW(this.d, "HvacManager unRegisterListener end  = ");
    }

    public int H() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcTemperature mAutoAcDevice == null !!! ");
            return -1;
        }
        int acWindLevel = bYDAutoAcDevice.getAcWindLevel();
        BydLogHelper.logW(this.d, "getAcWindLevel acWindLevel = " + acWindLevel);
        return acWindLevel;
    }

    public int H0() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.l;
        if (bYDAutoBodyworkDevice == null) {
            BydLogHelper.logW(this.d, "AutoBodywork getPowerLevel mAutoBodyworkDevice == null !!! ");
            return -1;
        }
        int powerLevel = bYDAutoBodyworkDevice.getPowerLevel();
        BydLogHelper.logW(this.d, "AutoBodywork getPowerLevel powerLevel = " + powerLevel);
        return powerLevel;
    }

    public int H3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1701, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1702, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1703, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1704, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.a1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f72a.G1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int I() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcWindLevelManualSign mAutoAcDevice == null !!! ");
            return -1;
        }
        int acWindLevelManualSign = bYDAutoAcDevice.getAcWindLevelManualSign();
        BydLogHelper.logW(this.d, "getAcWindLevelManualSign acWindLevelManualSign = " + acWindLevelManualSign);
        return acWindLevelManualSign;
    }

    public int I0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getQuickCleanAirState mAutoAcDevice == null !!! ");
            return -1;
        }
        int quickCleanAirState = bYDAutoAcDevice.getQuickCleanAirState();
        BydLogHelper.logW(this.d, "getQuickCleanAirState value = " + quickCleanAirState);
        return quickCleanAirState;
    }

    public int I3(final int i) {
        b.a.a.h.a.g(1023);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.d0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f92a.I1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int J() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcWindMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int acWindMode = bYDAutoAcDevice.getAcWindMode();
        BydLogHelper.logW(this.d, "getAcWindMode acWindMode = " + acWindMode);
        return acWindMode;
    }

    public int J0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getQuickCleanTip mAutoAcDevice == null !!! ");
            return -1;
        }
        int quickCleanTip = bYDAutoAcDevice.getQuickCleanTip();
        BydLogHelper.logD("getQuickCleanTip value = " + quickCleanTip);
        return quickCleanTip;
    }

    public int J3(final int i) {
        b.a.a.h.a.g(1004);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.p0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f172a.K1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int K() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcWindModeManualSign mAutoAcDevice == null !!! ");
            return -1;
        }
        int acWindModeManualSign = bYDAutoAcDevice.getAcWindModeManualSign();
        BydLogHelper.logW(this.d, "getAcWindModeManualSign acWindModeManualSign = " + acWindModeManualSign);
        return acWindModeManualSign;
    }

    public int K0() {
        if (this.f == null) {
            BydLogHelper.logW(this.d, "getRapidPurificationPM2p5State mAutoPM2p5Device == null !!! ");
            return -1;
        }
        int quickCleanAirState = f109b.getQuickCleanAirState();
        BydLogHelper.logW(this.d, "getQuickCleanAirState rapidPurificationPM2p5State = " + quickCleanAirState);
        return quickCleanAirState;
    }

    public int K3(final int i) {
        b.a.a.h.a.g(1003);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f145a.M1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int L() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcWindModeNum mAutoAcDevice == null !!! ");
            return -1;
        }
        int acWindModeNum = bYDAutoAcDevice.getAcWindModeNum();
        BydLogHelper.logW(this.d, "getAcWindModeNum acWindModeNum = " + acWindModeNum);
        return acWindModeNum;
    }

    public int L0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRearACkeyLockState mAutoAcDevice == null !!! ");
            return -1;
        }
        int rearACkeyLockState = bYDAutoAcDevice.getRearACkeyLockState();
        BydLogHelper.logW(this.d, "getRearACkeyLockState rearACkeyLockState = " + rearACkeyLockState);
        return rearACkeyLockState;
    }

    public int L3(final int i) {
        b.a.a.h.a.g(1007);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.o0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f167a.O1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int M() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAirOutleOnlineState mAutoAcDevice == null !!! ");
            return -1;
        }
        int airOutleOnlineState = bYDAutoAcDevice.getAirOutleOnlineState();
        BydLogHelper.logW(this.d, "getAirOutleOnlineState  airOutletValue = " + airOutleOnlineState);
        return airOutleOnlineState;
    }

    public int M0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRearAcControlMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int rearAcControlMode = bYDAutoAcDevice.getRearAcControlMode();
        BydLogHelper.logW(this.d, "getRearAcControlMode rearAcControlMode = " + rearAcControlMode);
        return rearAcControlMode;
    }

    public int M3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1006, i2);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.c1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f86a.Q1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int N(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAirOutletState mAutoAcDevice == null !!! ");
            return -1;
        }
        int airOutletState = bYDAutoAcDevice.getAirOutletState(i);
        BydLogHelper.logW(this.d, "getAirOutletState  area = " + i + " -- airOutletState = " + airOutletState);
        return airOutletState;
    }

    public int N0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRearAcMaxWindLevel mAutoAcDevice == null !!! ");
            return -1;
        }
        int rearAcMaxWindLevel = bYDAutoAcDevice.getRearAcMaxWindLevel();
        BydLogHelper.logW(this.d, "getRearAcMaxWindLevel rearAcMaxWindLevel = " + rearAcMaxWindLevel);
        return rearAcMaxWindLevel;
    }

    public int N3(final int i) {
        b.a.a.h.a.g(1041);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.x
                @Override // java.lang.Runnable
                public final void run() {
                    this.f205a.S1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int O(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAirOutletValue mAutoAcDevice == null !!! ");
            return -1;
        }
        float airOutletValue = bYDAutoAcDevice.getAirOutletValue(i);
        BydLogHelper.logW(this.d, "getAirOutletValue  area = " + i + " -- airOutletValue = " + airOutletValue);
        int i2 = (int) ((airOutletValue * 100.0f) - 128.0f);
        BydLogHelper.logW(this.d, "getAirOutletValue   value = " + i2);
        return i2;
    }

    public int O0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRearAcStartState mAutoAcDevice == null !!! ");
            return -1;
        }
        int rearAcStartState = bYDAutoAcDevice.getRearAcStartState();
        BydLogHelper.logW(this.d, "getRearAcStartState rearAcStartState = " + rearAcStartState);
        return rearAcStartState;
    }

    public int O3(final int i) {
        b.a.a.h.a.g(1105);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f128a.U1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int P() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAutoCleanAirState mAutoAcDevice == null !!! ");
            return -1;
        }
        int autoCleanAirState = bYDAutoAcDevice.getAutoCleanAirState();
        BydLogHelper.logW(this.d, "getAutoCleanAirState autoCleanAirState = " + autoCleanAirState);
        return autoCleanAirState;
    }

    public int P0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRearAcWindLevel mAutoAcDevice == null !!! ");
            return -1;
        }
        int rearAcWindLevel = bYDAutoAcDevice.getRearAcWindLevel();
        BydLogHelper.logW(this.d, "getRearAcWindLevel acWindLevel = " + rearAcWindLevel);
        return rearAcWindLevel;
    }

    public int P3(final int i) {
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f208a.W1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int Q() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.l;
        if (bYDAutoBodyworkDevice == null) {
            BydLogHelper.logW(this.d, "AutoBodywork getAutoType mAutoBodyworkDevice == null !!! ");
            return -1;
        }
        int autoType = bYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logW(this.d, "AutoBodywork getAutoType  value = " + autoType);
        return autoType;
    }

    public int Q0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRearAcWindMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int rearAcWindMode = bYDAutoAcDevice.getRearAcWindMode();
        BydLogHelper.logW(this.d, "getRearAcWindMode rearAcWindMode = " + rearAcWindMode);
        return rearAcWindMode;
    }

    public int Q3(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1034, i);
        } else if (i == 2) {
            b.a.a.h.a.h(1035, i);
        } else if (i == 3) {
            b.a.a.h.a.h(1036, i);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.p
                @Override // java.lang.Runnable
                public final void run() {
                    this.f169a.Y1(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int R(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAvoidBlowPeopleState mAutoAcDevice == null !!! ");
            return -1;
        }
        int avoidBlowPeopleState = bYDAutoAcDevice.getAvoidBlowPeopleState(i);
        BydLogHelper.logW(this.d, "getAvoidBlowPeopleState  area = " + i + " -- avoidBlowPeopleState = " + avoidBlowPeopleState);
        return avoidBlowPeopleState;
    }

    public int R0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatHeating21dConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatHeating21dConfig = bYDAutoAcDevice.getRrSeatHeating21dConfig();
        BydLogHelper.logW(this.d, "getRrSeatHeating21dConfig  state = " + rrSeatHeating21dConfig);
        return rrSeatHeating21dConfig;
    }

    public int R3(final int i) {
        b.a.a.h.a.g(1011);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.n0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f163a.a2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int S(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getBlowPeopleState mAutoAcDevice == null !!! ");
            return -1;
        }
        int blowPeopleState = bYDAutoAcDevice.getBlowPeopleState(i);
        BydLogHelper.logW(this.d, "getBlowPeopleState  area = " + i + " -- blowPeopleState = " + blowPeopleState);
        return blowPeopleState;
    }

    public int S0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatHeating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatHeating2aaConfig = bYDAutoAcDevice.getRrSeatHeating2aaConfig();
        BydLogHelper.logW(this.d, "getRrSeatHeating2aaConfig  state = " + rrSeatHeating2aaConfig);
        return rrSeatHeating2aaConfig;
    }

    public int S3(final int i, final int i2) {
        if (i == 0) {
            b.a.a.h.a.h(1044, i2);
        } else if (i == 1) {
            b.a.a.h.a.h(1045, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1046, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1047, i2);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.k0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f147a.c2(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int T() {
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice == null) {
            BydLogHelper.logW(this.d, "getCurFragranceType mAutoFragranceDevice == null !!! ");
            return -1;
        }
        int curFragranceType = bYDAutoFragranceDevice.getCurFragranceType();
        BydLogHelper.logW(this.d, "getCurFragranceType curFragranceType = " + curFragranceType);
        return curFragranceType;
    }

    public int T0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatHeating349Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatHeating349Config = bYDAutoAcDevice.getRrSeatHeating349Config();
        BydLogHelper.logW(this.d, "getRrSeatHeating349Config  state = " + rrSeatHeating349Config);
        return rrSeatHeating349Config;
    }

    public int T3(final int i) {
        b.a.a.h.a.g(1008);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.t
                @Override // java.lang.Runnable
                public final void run() {
                    this.f187a.e2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int U() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getDefrostRearConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int defrostRearConfig = bYDAutoAcDevice.getDefrostRearConfig();
        BydLogHelper.logW(this.d, "getDefrostRearConfig defrostRearConfig = " + defrostRearConfig);
        return defrostRearConfig;
    }

    public int U0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatHeating363Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatHeating363Config = bYDAutoAcDevice.getRrSeatHeating363Config();
        BydLogHelper.logW(this.d, "getRrSeatHeating363Config  state = " + rrSeatHeating363Config);
        return rrSeatHeating363Config;
    }

    public int U3(final int i) {
        b.a.a.h.a.g(1012);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.b1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f79a.g2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int V() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getDriverSeatHeating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int driverSeatHeating2aaConfig = bYDAutoAcDevice.getDriverSeatHeating2aaConfig();
        BydLogHelper.logW(this.d, "getDriverSeatHeating2aaConfig  state = " + driverSeatHeating2aaConfig);
        return driverSeatHeating2aaConfig;
    }

    public int V0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatVentilating21dConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatVentilating21dConfig = bYDAutoAcDevice.getRrSeatVentilating21dConfig();
        BydLogHelper.logW(this.d, "getRrSeatVentilating21dConfig  state = " + rrSeatVentilating21dConfig);
        return rrSeatVentilating21dConfig;
    }

    public int V3(final int i) {
        b.a.a.h.a.g(1009);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f192a.i2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int W() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getDriverSeatHeating2e4Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int driverSeatHeating2e4Config = bYDAutoAcDevice.getDriverSeatHeating2e4Config();
        BydLogHelper.logW(this.d, "getDriverSeatHeating2e4Config  state = " + driverSeatHeating2e4Config);
        return driverSeatHeating2e4Config;
    }

    public int W0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatVentilating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatVentilating2aaConfig = bYDAutoAcDevice.getRrSeatVentilating2aaConfig();
        BydLogHelper.logW(this.d, "getRrSeatVentilating2aaConfig  state = " + rrSeatVentilating2aaConfig);
        return rrSeatVentilating2aaConfig;
    }

    public int W3(final int i, final int i2) {
        if (i == 0) {
            b.a.a.h.a.h(1028, i2);
        } else if (i == 1) {
            b.a.a.h.a.h(1052, i2);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f89a.k2(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int X() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getDriverSeatVentilating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int driverSeatVentilating2aaConfig = bYDAutoAcDevice.getDriverSeatVentilating2aaConfig();
        BydLogHelper.logW(this.d, "getDriverSeatVentilating2aaConfig  state = " + driverSeatVentilating2aaConfig);
        return driverSeatVentilating2aaConfig;
    }

    public int X0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatVentilating349Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatVentilating349Config = bYDAutoAcDevice.getRrSeatVentilating349Config();
        BydLogHelper.logW(this.d, "getRrSeatVentilating349Config  state = " + rrSeatVentilating349Config);
        return rrSeatVentilating349Config;
    }

    public int X3(final int i, final int i2) {
        final String str;
        switch (i) {
            case 0:
                b.a.a.h.a.h(1040, i2);
                str = "MAIN_LEFT_UD";
                break;
            case 1:
                b.a.a.h.a.h(1053, i2);
                str = "MAIN_LEFT_LF";
                break;
            case 2:
                b.a.a.h.a.h(1054, i2);
                str = "MAIN_RIGHT_UD";
                break;
            case 3:
                b.a.a.h.a.h(1055, i2);
                str = "MAIN_RIGHT_LF";
                break;
            case 4:
                b.a.a.h.a.h(1056, i2);
                str = "DEPUTY_LEFT_UD";
                break;
            case 5:
                b.a.a.h.a.h(1057, i2);
                str = "DEPUTY_LEFT_LF";
                break;
            case 6:
                b.a.a.h.a.h(1058, i2);
                str = "DEPUTY_RIGHT_UD";
                break;
            case 7:
                b.a.a.h.a.h(1059, i2);
                str = "DEPUTY_RIGHT_LF";
                break;
            default:
                str = null;
                break;
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.m
                @Override // java.lang.Runnable
                public final void run() {
                    this.f154a.m2(i2, str, i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int Y() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getDriverSeatVentilating2e4Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int driverSeatVentilating2e4Config = bYDAutoAcDevice.getDriverSeatVentilating2e4Config();
        BydLogHelper.logW(this.d, "getDriverSeatVentilating2e4Config  state = " + driverSeatVentilating2e4Config);
        return driverSeatVentilating2e4Config;
    }

    public int Y0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getRrSeatVentilating363Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int rrSeatVentilating363Config = bYDAutoAcDevice.getRrSeatVentilating363Config();
        BydLogHelper.logW(this.d, "getRrSeatVentilating363Config  state = " + rrSeatVentilating363Config);
        return rrSeatVentilating363Config;
    }

    public int Y3(final int i) {
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.r0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f180a.o2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int Z() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getElectricDefrostState mAutoAcDevice == null !!! ");
            return -1;
        }
        int electricDefrostState = bYDAutoAcDevice.getElectricDefrostState();
        BydLogHelper.logW(this.d, "getElectricDefrostState  electricDefrostState = " + electricDefrostState);
        return electricDefrostState;
    }

    public int Z0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSeatHeatingNotL1PState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int seatHeatingNotL1PState = bYDAutoSettingDevice.getSeatHeatingNotL1PState();
        BydLogHelper.logW(this.d, "getSeatHeatingNotL1PState seatVentilationState = " + seatHeatingNotL1PState);
        return seatHeatingNotL1PState;
    }

    public int Z3(final int i, final int i2) {
        if (i == 0) {
            b.a.a.h.a.h(1026, i2);
        } else if (i == 1) {
            b.a.a.h.a.h(1050, i2);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.v0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f198a.q2(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int a(final int i) {
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.u0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f194a.u1(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int a0(int i) {
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice == null) {
            BydLogHelper.logW(this.d, "getFragranceConcentration mAutoFragranceDevice == null !!! ");
            return -1;
        }
        int fragranceConcentration = bYDAutoFragranceDevice.getFragranceConcentration(i);
        BydLogHelper.logW(this.d, "getFragranceConcentration fragranceConcentration = " + fragranceConcentration);
        return fragranceConcentration;
    }

    public int a1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSeatHeatingState2 mAutoSeatDevice == null !!! ");
            return -1;
        }
        int seatHeatingState2 = bYDAutoSettingDevice.getSeatHeatingState2(i);
        BydLogHelper.logW(this.d, "getSeatHeatingState2 seatVentilationState = " + seatHeatingState2);
        return seatHeatingState2;
    }

    public int a4(final int i, final int i2) {
        if (i == 0) {
            b.a.a.h.a.h(1025, i2);
        } else if (i == 1) {
            b.a.a.h.a.h(1049, i2);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.t0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f189a.s2(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int b(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "get5LevelSeatHeatingLevel mAutoSeatDevice == null !!! ");
            return -1;
        }
        int i2 = bYDAutoSettingDevice.get5LevelSeatHeatingLevel(i);
        BydLogHelper.logW(this.d, "get5LevelSeatHeatingLevel area = " + i + " --- value = " + i2);
        return i2;
    }

    public int b0(int i) {
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice == null) {
            BydLogHelper.logW(this.d, "getFragranceInstallState mAutoFragranceDevice == null !!! ");
            return -1;
        }
        int fragranceInstallState = bYDAutoFragranceDevice.getFragranceInstallState(i);
        BydLogHelper.logW(this.d, "getFragranceInstallState fragranceInstallState = " + fragranceInstallState);
        return fragranceInstallState;
    }

    public int b1() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.l;
        if (bYDAutoBodyworkDevice == null) {
            BydLogHelper.logW(this.d, "AutoBodywork getSeatType mAutoBodyworkDevice == null !!! ");
            return -1;
        }
        int autoType = bYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logW(this.d, "AutoBodywork getAutoType  value = " + autoType);
        return (autoType == 60 || autoType == 123 || autoType == 137 || autoType == 196) ? 2 : 1;
    }

    public int b4(final int i) {
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.l0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f152a.u2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int c(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "get5LevelSeatHeatingMode mAutoSeatDevice == null !!! ");
            return -1;
        }
        int i2 = bYDAutoSettingDevice.get5LevelSeatHeatingMode(i);
        BydLogHelper.logW(this.d, "get5LevelSeatHeatingMode area = " + i + " --- value = " + i2);
        return i2;
    }

    public int c0(int i) {
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice == null) {
            BydLogHelper.logW(this.d, "getFragranceLessAllowanceState mAutoFragranceDevice == null !!! ");
            return -1;
        }
        int fragranceLessAllowanceState = bYDAutoFragranceDevice.getFragranceLessAllowanceState(i);
        BydLogHelper.logW(this.d, "getFragranceLessAllowanceState type = " + i + " -- fragranceInstallState = " + fragranceLessAllowanceState);
        return fragranceLessAllowanceState;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int c1() {
        /*
            r18 = this;
            r0 = r18
            r1 = 1
            int r2 = r0.f1(r1)
            r3 = 2
            int r4 = r0.f1(r3)
            r5 = 3
            int r6 = r0.f1(r5)
            r7 = 4
            int r8 = r0.f1(r7)
            int r9 = r0.e1(r5)
            int r10 = r0.e1(r7)
            int r11 = r18.d1()
            int r12 = r0.g1(r1)
            int r13 = r0.g1(r3)
            int r14 = r0.g1(r5)
            int r15 = r0.g1(r7)
            int r16 = r0.a1(r5)
            int r7 = r0.a1(r7)
            int r17 = r18.Z0()
            int r12 = r12 + r13
            int r12 = r12 + r17
            int r12 = r12 + r14
            int r12 = r12 + r15
            int r12 = r12 + r16
            int r12 = r12 + r7
            r7 = 0
            if (r12 < r1) goto L4b
            r12 = r1
            goto L4c
        L4b:
            r12 = r7
        L4c:
            int r2 = r2 + r4
            int r2 = r2 + r11
            int r2 = r2 + r6
            int r2 = r2 + r8
            int r2 = r2 + r9
            int r2 = r2 + r10
            if (r2 < r1) goto L56
            r2 = r1
            goto L57
        L56:
            r2 = r7
        L57:
            java.lang.String r4 = r0.d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "getSeatVentHeatType mIsSupportSeatHeat:"
            r6.append(r8)
            r6.append(r12)
            java.lang.String r8 = "  -- mIsSupportSeatVent:"
            r6.append(r8)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logW(r4, r6)
            if (r12 == 0) goto L7b
            if (r2 == 0) goto L7b
        L79:
            r1 = r5
            goto L8f
        L7b:
            if (r12 == 0) goto L7e
            goto L8f
        L7e:
            if (r2 == 0) goto L82
            r1 = r3
            goto L8f
        L82:
            int r2 = r18.l1()
            int r3 = r18.j1()
            int r2 = r2 + r3
            if (r2 < r1) goto L8e
            goto L79
        L8e:
            r1 = r7
        L8f:
            java.lang.String r2 = r0.d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "getSeatVentHeatType seatType = "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logW(r2, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.f.f1.c1():int");
    }

    public int c4(int i) {
        BydLogHelper.logW(this.d, "setElectricDefrostState  state = " + i);
        b.a.a.h.a.g(1019);
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice != null) {
            return bYDAutoAcDevice.setElectricDefrostState(i);
        }
        return -2147482648;
    }

    public int d(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "get5LevelSeatHeatingState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int i2 = bYDAutoSettingDevice.get5LevelSeatHeatingState(i);
        BydLogHelper.logW(this.d, "get5LevelSeatHeatingState area = " + i + " --- value = " + i2);
        return i2;
    }

    public int d0(int i) {
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice == null) {
            BydLogHelper.logW(this.d, "getFragranceName mAutoFragranceDevice == null !!! ");
            return -1;
        }
        int fragranceName = bYDAutoFragranceDevice.getFragranceName(i);
        BydLogHelper.logW(this.d, "getFragranceName fragranceName = " + fragranceName);
        return fragranceName;
    }

    public int d1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSeatVentilatingNotL1PState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int seatVentilatingNotL1PState = bYDAutoSettingDevice.getSeatVentilatingNotL1PState();
        BydLogHelper.logW(this.d, "getSeatVentilatingNotL1PState seatVentilationState = " + seatVentilatingNotL1PState);
        return seatVentilatingNotL1PState;
    }

    public int d4(final int i) {
        b.a.a.h.a.g(1501);
        if (this.j != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.h0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f123a.w2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoFragranceDevice == null !! ");
        return -2147482648;
    }

    public int e(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "get5LevelSeatVentilatingLevel mAutoSeatDevice == null !!! ");
            return -1;
        }
        int i2 = bYDAutoSettingDevice.get5LevelSeatVentilatingLevel(i);
        BydLogHelper.logW(this.d, "get5LevelSeatVentilatingLevel area = " + i + " --- value = " + i2);
        return i2;
    }

    public int e0() {
        int iQ1 = q1(186);
        BydLogHelper.logD("getFragranceOnlineState fragranceOnlineState = " + iQ1);
        return iQ1;
    }

    public int e1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSeatVentilatingState2 mAutoSeatDevice == null !!! ");
            return -1;
        }
        int seatVentilatingState2 = bYDAutoSettingDevice.getSeatVentilatingState2(i);
        BydLogHelper.logW(this.d, "getSeatVentilatingState2 seatVentilationState = " + seatVentilatingState2);
        return seatVentilatingState2;
    }

    public int e4(final int i) {
        b.a.a.h.a.g(1500);
        if (this.j != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f103a.y2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoFragranceDevice == null !! ");
        return -2147482648;
    }

    public int f(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "get5LevelSeatVentilatingMode mAutoSeatDevice == null !!! ");
            return -1;
        }
        int i2 = bYDAutoSettingDevice.get5LevelSeatVentilatingMode(i);
        BydLogHelper.logW(this.d, "get5LevelSeatVentilatingMode area = " + i + " --- value = " + i2);
        return i2;
    }

    public int f0(int i) {
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.j;
        if (bYDAutoFragranceDevice == null) {
            BydLogHelper.logW(this.d, "getFragranceResidue mAutoFragranceDevice == null !!! ");
            return -1;
        }
        int fragranceResidue = bYDAutoFragranceDevice.getFragranceResidue(i);
        BydLogHelper.logW(this.d, "getFragranceResidue  type = " + i + " --- fragranceResidue = " + fragranceResidue);
        return fragranceResidue;
    }

    public int f1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSeatVentilationState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int seatVentilatingState = bYDAutoSettingDevice.getSeatVentilatingState(i);
        BydLogHelper.logW(this.d, "getSeatVentilationState seatVentilationState = " + seatVentilatingState);
        return seatVentilatingState;
    }

    public int f4(final int i, final int i2) {
        if (i == 0) {
            b.a.a.h.a.h(1024, i2);
        } else if (i == 1) {
            b.a.a.h.a.h(1048, i2);
        }
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.m0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f157a.A2(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int g(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "get5LevelSeatVentilatingState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int i2 = bYDAutoSettingDevice.get5LevelSeatVentilatingState(i);
        BydLogHelper.logW(this.d, "get5LevelSeatVentilatingState area = " + i + " --- value = " + i2);
        return i2;
    }

    public int g0(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getFreeWindBTNState mAutoAcDevice == null !!! ");
            return -1;
        }
        int freeWindBTNState = bYDAutoAcDevice.getFreeWindBTNState(i);
        BydLogHelper.logW(this.d, "getFreeWindBTNState  area = " + i + " -- freeWindBTNState = " + freeWindBTNState);
        return freeWindBTNState;
    }

    public int g1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSeatWarmState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int seatHeatingState = bYDAutoSettingDevice.getSeatHeatingState(i);
        BydLogHelper.logW(this.d, "getSeatWarmState seatWarmState = " + seatHeatingState);
        return seatHeatingState;
    }

    public int g4(final int i) {
        b.a.a.h.a.g(1030);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f75a.C2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int h() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getACAssistModeTipsState mAutoAcDevice == null !!! ");
            return -1;
        }
        int aCAssistModeTipsState = bYDAutoAcDevice.getACAssistModeTipsState();
        BydLogHelper.logW(this.d, "getACAssistModeTipsState result = " + aCAssistModeTipsState);
        return aCAssistModeTipsState;
    }

    public int h0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getFrontRowAirOutletState mAutoAcDevice == null !!! ");
            return -1;
        }
        int frontRowAirOutletState = bYDAutoAcDevice.getFrontRowAirOutletState();
        BydLogHelper.logW(this.d, "getFrontRowAirOutletState  airOutletValue = " + frontRowAirOutletState);
        return frontRowAirOutletState;
    }

    public int h1() {
        BYDAutoShowCarDevice bYDAutoShowCarDevice = this.n;
        if (bYDAutoShowCarDevice == null) {
            BydLogHelper.logW(this.d, "getShowCarModelType mAutoShowCarDevice == null !!! ");
            return -1;
        }
        int showCarModelType = bYDAutoShowCarDevice.getShowCarModelType();
        BydLogHelper.logW(this.d, "getShowCarModelType type = " + showCarModelType);
        return showCarModelType;
    }

    public int h4(final int i, final int i2) {
        b.a.a.h.a.h(1029, i);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f139a.E2(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int i() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getACBTWindTipsState mAutoAcDevice == null !!! ");
            return -1;
        }
        int aCBTWindTipsState = bYDAutoAcDevice.getACBTWindTipsState();
        BydLogHelper.logW(this.d, "getACBTWindTipsState result = " + aCBTWindTipsState);
        return aCBTWindTipsState;
    }

    public int i0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getHeatPumpCompressorWorkMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int heatPumpCompressorWorkMode = bYDAutoAcDevice.getHeatPumpCompressorWorkMode();
        BydLogHelper.logW(this.d, "getHeatPumpCompressorWorkMode heatPumpCompressorWorkMode = " + heatPumpCompressorWorkMode);
        return heatPumpCompressorWorkMode;
    }

    public int i1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSteeringWheelHeatedPlan mAutoSeatDevice == null !!! ");
            return -1;
        }
        int steeringWheelHeatedPlan = bYDAutoSettingDevice.getSteeringWheelHeatedPlan();
        BydLogHelper.logW(this.d, "getSteeringWheelHeatedPlan steeringWheelHeatedPlan = " + steeringWheelHeatedPlan);
        return steeringWheelHeatedPlan;
    }

    public int i4(final int i) {
        b.a.a.h.a.g(1005);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.v
                @Override // java.lang.Runnable
                public final void run() {
                    this.f196a.G2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int j() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getACDefrostRearPanelNotAvailableTipsState mAutoAcDevice == null !!! ");
            return -1;
        }
        int aCDefrostRearPanelNotAvailableTipsState = bYDAutoAcDevice.getACDefrostRearPanelNotAvailableTipsState();
        BydLogHelper.logW(this.d, "getACDefrostRearPanelNotAvailableTipsState result = " + aCDefrostRearPanelNotAvailableTipsState);
        return aCDefrostRearPanelNotAvailableTipsState;
    }

    public int j0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getHighTempAntivirusCountDown mAutoAcDevice == null !!! ");
            return -1;
        }
        int highTempAntivirusCountDown = bYDAutoAcDevice.getHighTempAntivirusCountDown();
        BydLogHelper.logW(this.d, "getHighTempAntivirusCountDown value = " + highTempAntivirusCountDown);
        return highTempAntivirusCountDown;
    }

    public int j1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSteeringWheelHeatingAutoState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int steeringWheelHeatingAutoState = bYDAutoSettingDevice.getSteeringWheelHeatingAutoState();
        BydLogHelper.logW(this.d, "getSteeringWheelHeatingAutoState value = " + steeringWheelHeatingAutoState);
        return steeringWheelHeatingAutoState;
    }

    public int j4(final int i) {
        if (this.f != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.s0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f185a.I2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoPM2p5Device == null !! ");
        return -2147482648;
    }

    public int k() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getACKeepEngineRunTipsState mAutoAcDevice == null !!! ");
            return -1;
        }
        int aCKeepEngineRunTipsState = bYDAutoAcDevice.getACKeepEngineRunTipsState();
        BydLogHelper.logW(this.d, "getACKeepEngineRunTipsState result = " + aCKeepEngineRunTipsState);
        return aCKeepEngineRunTipsState;
    }

    public int k0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getHighTempAntivirusState mAutoAcDevice == null !!! ");
            return -1;
        }
        int highTempAntivirusState = bYDAutoAcDevice.getHighTempAntivirusState();
        BydLogHelper.logW(this.d, "getHighTempAntivirusState value = " + highTempAntivirusState);
        return highTempAntivirusState;
    }

    public int k1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSteeringWheelHeatingGear mAutoSeatDevice == null !!! ");
            return -1;
        }
        int steeringWheelHeatingGear = bYDAutoSettingDevice.getSteeringWheelHeatingGear();
        BydLogHelper.logW(this.d, "getSteeringWheelHeatingGear value = " + steeringWheelHeatingGear);
        return steeringWheelHeatingGear;
    }

    public int k4(final int i) {
        if (i == 1) {
            b.a.a.h.a.g(1312);
        } else if (i == 2) {
            b.a.a.h.a.g(1313);
        } else if (i == 3) {
            b.a.a.h.a.g(1312);
            b.a.a.h.a.g(1313);
        }
        if (this.f != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.w0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f203a.K2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoPM2p5Device == null !! ");
        return -2147482648;
    }

    public int l(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getACWorkMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int aCWorkMode = bYDAutoAcDevice.getACWorkMode(i);
        BydLogHelper.logW(this.d, "getACWorkMode  area = " + i + " -- acWorkMode = " + aCWorkMode);
        return aCWorkMode;
    }

    public int l1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.h;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logW(this.d, "getSteeringWheelHeatingState mAutoSeatDevice == null !!! ");
            return -1;
        }
        int steeringWheelHeatingState = bYDAutoSettingDevice.getSteeringWheelHeatingState();
        BydLogHelper.logW(this.d, "getSteeringWheelHeatingState steeringWheelWarmState = " + steeringWheelHeatingState);
        return steeringWheelHeatingState;
    }

    public int l4(final int i) {
        b.a.a.h.a.g(1304);
        if (this.f != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f150a.M2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoPM2p5Device == null !! ");
        return -2147482648;
    }

    public int m() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcCompressorManualSign mAutoAcDevice == null !!! ");
            return -1;
        }
        int acCompressorManualSign = bYDAutoAcDevice.getAcCompressorManualSign();
        BydLogHelper.logW(this.d, "getAcCompressorManualSign acCompressorManualSign = " + acCompressorManualSign);
        return acCompressorManualSign;
    }

    public int m0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getIntelligentControlState mAutoAcDevice == null !!! ");
            return -1;
        }
        int intelligentControlState = bYDAutoAcDevice.getIntelligentControlState();
        BydLogHelper.logW(this.d, "getIntelligentControlState  intelligentControlState = " + intelligentControlState);
        return intelligentControlState;
    }

    public int m1() {
        int iIntValue = 0;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, this.o, 0);
            if (objInvoke instanceof Integer) {
                iIntValue = ((Integer) objInvoke).intValue();
            }
        } catch (Exception e) {
            BydLogHelper.logE("AutoTestDevice getSteeringWheelPosition Exception = " + e.toString());
        }
        BydLogHelper.logW(this.d, "AutoTestDevice getSteeringWheelPosition position = " + iIntValue);
        return iIntValue;
    }

    public int m4(final int i) {
        b.a.a.h.a.g(1308);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.b0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f77a.O2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int n() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcCompressorState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acCompressorMode = bYDAutoAcDevice.getAcCompressorMode();
        BydLogHelper.logW(this.d, "getAcCompressorState acCompressorState = " + acCompressorMode);
        return acCompressorMode;
    }

    public int n0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getLrSeatHeating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int lrSeatHeating2aaConfig = bYDAutoAcDevice.getLrSeatHeating2aaConfig();
        BydLogHelper.logW(this.d, "getLrSeatHeating2aaConfig  state = " + lrSeatHeating2aaConfig);
        return lrSeatHeating2aaConfig;
    }

    public int n1(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getSweepBlowState mAutoAcDevice == null !!! ");
            return -1;
        }
        int sweepBlowState = bYDAutoAcDevice.getSweepBlowState(i);
        BydLogHelper.logW(this.d, "getSweepBlowState  area = " + i + " -- sweepBlowState = " + sweepBlowState);
        return sweepBlowState;
    }

    public int n4(final int i) {
        b.a.a.h.a.g(1308);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.z
                @Override // java.lang.Runnable
                public final void run() {
                    this.f211a.Q2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int o() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcControlMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int acControlMode = bYDAutoAcDevice.getAcControlMode();
        BydLogHelper.logW(this.d, "getAcControlMode acControlMode = " + acControlMode);
        return acControlMode;
    }

    public int o0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getLrSeatHeating349Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int lrSeatHeating349Config = bYDAutoAcDevice.getLrSeatHeating349Config();
        BydLogHelper.logW(this.d, "getLrSeatHeating349Config  state = " + lrSeatHeating349Config);
        return lrSeatHeating349Config;
    }

    public int o1() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getTempRegulatingAccuracy mAutoAcDevice == null !!! ");
            return -1;
        }
        int tempRegulatingAccuracy = bYDAutoAcDevice.getTempRegulatingAccuracy();
        BydLogHelper.logW(this.d, "getTempRegulatingAccuracy tempRegulatingAccuracy = " + tempRegulatingAccuracy);
        return tempRegulatingAccuracy;
    }

    public int o4(final int i) {
        b.a.a.h.a.g(1105);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.r
                @Override // java.lang.Runnable
                public final void run() {
                    this.f178a.S2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logW(this.d, "setRearACkeyLockState mAutoAcDevice == null = ");
        return -2147482648;
    }

    public int p() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcCycleMode mAutoAcDevice == null !!! ");
            return -1;
        }
        int acCycleMode = bYDAutoAcDevice.getAcCycleMode();
        BydLogHelper.logW(this.d, "getAcCycleMode acCycleMode = " + acCycleMode);
        return acCycleMode;
    }

    public int p0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getLrSeatHeating35dConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int lrSeatHeating35dConfig = bYDAutoAcDevice.getLrSeatHeating35dConfig();
        BydLogHelper.logW(this.d, "getLrSeatHeating35dConfig  state = " + lrSeatHeating35dConfig);
        return lrSeatHeating35dConfig;
    }

    public int p1() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getTemperatureUnit mAutoAcDevice == null !!! ");
            return -1;
        }
        int temperatureUnit = bYDAutoAcDevice.getTemperatureUnit();
        BydLogHelper.logW(this.d, "getTemperatureUnit temperatureUnit = " + temperatureUnit);
        return temperatureUnit;
    }

    public int p4(final int i) {
        b.a.a.h.a.g(1102);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.c0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f84a.U2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int q(int i) {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcDefrostState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acDefrostState = bYDAutoAcDevice.getAcDefrostState(i);
        BydLogHelper.logW(this.d, "getAcDefrostState area = " + i + " --- acDefrostState = " + acDefrostState);
        return acDefrostState;
    }

    public int q0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getLrSeatVentilating2aaConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int lrSeatVentilating2aaConfig = bYDAutoAcDevice.getLrSeatVentilating2aaConfig();
        BydLogHelper.logW(this.d, "getLrSeatVentilating2aaConfig  state = " + lrSeatVentilating2aaConfig);
        return lrSeatVentilating2aaConfig;
    }

    public int q1(int i) {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.l;
        if (bYDAutoBodyworkDevice == null) {
            BydLogHelper.logW(this.d, "AutoBodywork hasMessage mAutoBodyworkDevice == null !!! ");
            return -1;
        }
        int iHasMessage = bYDAutoBodyworkDevice.hasMessage(i);
        BydLogHelper.logW(this.d, "AutoBodywork hasMessage id ： " + i + " --  value = " + iHasMessage);
        return iHasMessage;
    }

    public int q4(final int i) {
        b.a.a.h.a.g(1104);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f201a.W2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int r() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcFaultNumShownState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acFaultNumShownState = bYDAutoAcDevice.getAcFaultNumShownState();
        BydLogHelper.logW(this.d, "getAcFaultNumShownState state = " + acFaultNumShownState);
        return acFaultNumShownState;
    }

    public int r0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getLrSeatVentilating349Config mAutoAcDevice == null !!! ");
            return -1;
        }
        int lrSeatVentilating349Config = bYDAutoAcDevice.getLrSeatVentilating349Config();
        BydLogHelper.logW(this.d, "getLrSeatVentilating349Config  state = " + lrSeatVentilating349Config);
        return lrSeatVentilating349Config;
    }

    public void r1(Context context) {
        BydLogHelper.logW(this.d, "HvacManager init start  = ");
        f108a = context;
        f109b = BYDAutoAcDevice.getInstance(context);
        this.f = BYDAutoPM2p5Device.getInstance(f108a);
        this.h = BYDAutoSettingDevice.getInstance(f108a);
        this.j = BYDAutoFragranceDevice.getInstance(f108a);
        this.l = BYDAutoBodyworkDevice.getInstance(f108a);
        this.n = BYDAutoShowCarDevice.getInstance(f108a);
        BydLogHelper.logW(this.d, "HvacManager init end  = ");
    }

    public int r4(final int i) {
        b.a.a.h.a.g(1103);
        if (f109b != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.h
                @Override // java.lang.Runnable
                public final void run() {
                    this.f121a.Y2(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoAcDevice == null !! ");
        return -2147482648;
    }

    public int s() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcHeatingState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acHeatingButtonConfig = bYDAutoAcDevice.getAcHeatingButtonConfig();
        BydLogHelper.logW(this.d, "getAcHeatingButtonConfig acHeatingButtonConfig = " + acHeatingButtonConfig);
        return acHeatingButtonConfig;
    }

    public int s0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getLrSeatVentilating35dConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int lrSeatVentilating35dConfig = bYDAutoAcDevice.getLrSeatVentilating35dConfig();
        BydLogHelper.logW(this.d, "getLrSeatVentilating35dConfig  state = " + lrSeatVentilating35dConfig);
        return lrSeatVentilating35dConfig;
    }

    public boolean s1() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.l;
        if (bYDAutoBodyworkDevice == null) {
            return false;
        }
        int autoType = bYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logW(this.d, "AutoBodywork getAutoType type = " + autoType);
        switch (autoType) {
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 196:
            case 208:
            case 224:
                break;
        }
        return false;
    }

    public int s4(final int i) {
        b.a.a.h.a.h(1407, i);
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.o
                @Override // java.lang.Runnable
                public final void run() {
                    this.f165a.a3(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int t() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcHeatingState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acHeatingState = bYDAutoAcDevice.getAcHeatingState();
        BydLogHelper.logW(this.d, "getAcHeatingState acHeatingState = " + acHeatingState);
        return acHeatingState;
    }

    public int t0() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcMaxCoolingState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acMaxCoolingState = bYDAutoAcDevice.getAcMaxCoolingState();
        BydLogHelper.logW(this.d, "getAcMaxCoolingState maxAcState = " + acMaxCoolingState);
        return acMaxCoolingState;
    }

    public int t4(final int i) {
        b.a.a.h.a.h(1409, i);
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f174a.c3(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int u() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcMaxCoolingState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acMaxCoolingState = bYDAutoAcDevice.getAcMaxCoolingState();
        BydLogHelper.logW(this.d, "getAcMaxCoolingState acMaxCoolingState = " + acMaxCoolingState);
        return acMaxCoolingState;
    }

    public int u0() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logW(this.d, "getPM2p5AnionState mAutoPM2p5Device == null !!! ");
            return -1;
        }
        int pM2p5AnionState = bYDAutoPM2p5Device.getPM2p5AnionState();
        BydLogHelper.logW(this.d, "getPM2p5AnionState negativeIonState = " + pM2p5AnionState);
        return pM2p5AnionState;
    }

    public int u4(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1410, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1411, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1412, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1413, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.g0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f114a.e3(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int v() {
        int iQ1 = q1(59);
        BydLogHelper.logD("getAcOnlineState acOnlineState = " + iQ1);
        return iQ1;
    }

    public int[] v0() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logW(this.d, "getPM2p5CheckState mAutoPM2p5Device == null !!! ");
            return f110c;
        }
        int[] pM2p5CheckState = bYDAutoPM2p5Device.getPM2p5CheckState();
        BydLogHelper.logD("getPM2p5CheckState pm2p5CheckState = " + Arrays.toString(pM2p5CheckState));
        return pM2p5CheckState;
    }

    public int v4(final int i, final int i2) {
        if (i == 3) {
            b.a.a.h.a.h(1424, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1425, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.i0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f130a.g3(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int w() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcPromptBoxShownState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acPromptBoxShownState = bYDAutoAcDevice.getAcPromptBoxShownState();
        BydLogHelper.logW(this.d, "getAcPromptBoxShownState  state = " + acPromptBoxShownState);
        return acPromptBoxShownState;
    }

    public int w0() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logW(this.d, "getPM2p5DualChannelDetectState mAutoPM2p5Device == null !!! ");
            return -1;
        }
        int pM2p5DualChannelDetectState = bYDAutoPM2p5Device.getPM2p5DualChannelDetectState();
        BydLogHelper.logW(this.d, "getPM2p5DualChannelDetectState negativeIonState = " + pM2p5DualChannelDetectState);
        return pM2p5DualChannelDetectState;
    }

    public int w4(final int i, final int i2) {
        if (i == 1) {
            b.a.a.h.a.h(1414, i2);
        } else if (i == 2) {
            b.a.a.h.a.h(1415, i2);
        } else if (i == 3) {
            b.a.a.h.a.h(1416, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1417, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f81a.i3(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int x() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcPtcPreheatSignal mAutoAcDevice == null !!! ");
            return -1;
        }
        int acPtcPreheatSignal = bYDAutoAcDevice.getAcPtcPreheatSignal();
        BydLogHelper.logW(this.d, "getAcPtcPreheatSignal acPtcPreheatSignal = " + acPtcPreheatSignal);
        return acPtcPreheatSignal;
    }

    public int[] x0() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logW(this.d, "getPM2p5Level mAutoPM2p5Device == null !!! ");
            return f110c;
        }
        int[] pM2p5Level = bYDAutoPM2p5Device.getPM2p5Level();
        BydLogHelper.logD("getPM2p5Level pm2p5Level = " + Arrays.toString(pM2p5Level));
        return pM2p5Level;
    }

    public int x4(final int i, final int i2) {
        if (i == 3) {
            b.a.a.h.a.h(1420, i2);
        } else if (i == 4) {
            b.a.a.h.a.h(1421, i2);
        }
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.e0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f98a.k3(i, i2);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int y() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcRearPanelLockState mAutoAcDevice == null !!! ");
            return -1;
        }
        int acRearPanelLockState = bYDAutoAcDevice.getAcRearPanelLockState();
        BydLogHelper.logW(this.d, "getAcRearPanelLockState  acRearPanelLockState = " + acRearPanelLockState);
        return acRearPanelLockState;
    }

    public int y0() {
        int iQ1 = q1(2);
        BydLogHelper.logD("getPM2p5OnlineState pm2p5OnlineState = " + iQ1);
        return iQ1;
    }

    public int y4(final int i) {
        b.a.a.h.a.g(1700);
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f112a.m3(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }

    public int z() {
        BYDAutoAcDevice bYDAutoAcDevice = f109b;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.d, "getAcRemoteCtrlTime mAutoAcDevice == null !!! ");
            return -1;
        }
        int acRemoteCtrlTime = bYDAutoAcDevice.getAcRemoteCtrlTime();
        BydLogHelper.logD("getAcRemoteCtrlTime acRemoteCtrlTime = " + acRemoteCtrlTime);
        return acRemoteCtrlTime;
    }

    public int[] z0() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.f;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logW(this.d, "getPM2p5Value mAutoPM2p5Device == null !!! ");
            return f110c;
        }
        int[] pM2p5Value = bYDAutoPM2p5Device.getPM2p5Value();
        BydLogHelper.logD("getPM2p5Value pm2p5Value = " + Arrays.toString(pM2p5Value));
        return pM2p5Value;
    }

    public int z4(final int i) {
        b.a.a.h.a.g(1726);
        if (this.h != null) {
            b.a.a.h.i.b(new Runnable() { // from class: b.a.a.f.q0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f176a.o3(i);
                }
            });
            return 0;
        }
        BydLogHelper.logE("mAutoSeatDevice == null !! ");
        return -2147482648;
    }
}
