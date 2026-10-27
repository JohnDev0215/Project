package com.ts.appservice.caradapter.c;

import android.content.Context;
import android.os.Handler;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import com.ts.appservice.caradapter.d.InterfaceC0009a;
import com.ts.appservice.caradapter.d.InterfaceC0010b;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.ambient.CarVendorAmbientManager;
import com.ts.lib.car.hardware.assist.CarVendorAssistManager;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager;
import com.ts.lib.car.hardware.carbody.CarbodyEventChangeValue;
import com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager;
import com.ts.lib.car.hardware.carmode.CarmodeEventChangeValue;
import com.ts.lib.car.hardware.charging.CarVendorChargingManager;
import com.ts.lib.car.hardware.charging.ChargingEventChangeValue;
import com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager;
import com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager;
import com.ts.lib.car.hardware.collectdata.CollectdataEventChangeValue;
import com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager;
import com.ts.lib.car.hardware.digitalkey.DigitalkeyEventChangeValue;
import com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager;
import com.ts.lib.car.hardware.dipilot.DipilotEventChangeValue;
import com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager;
import com.ts.lib.car.hardware.doorlock.DoorlockEventChangeValue;
import com.ts.lib.car.hardware.energy.CarVendorEnergyManager;
import com.ts.lib.car.hardware.energy.EnergyEventChangeValue;
import com.ts.lib.car.hardware.engine.CarVendorEngineManager;
import com.ts.lib.car.hardware.engine.EngineEventChangeValue;
import com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager;
import com.ts.lib.car.hardware.fragrance.FragranceEventChangeValue;
import com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager;
import com.ts.lib.car.hardware.gearbox.GearboxEventChangeValue;
import com.ts.lib.car.hardware.health.CarVendorHealthManager;
import com.ts.lib.car.hardware.health.HealthEventChangeValue;
import com.ts.lib.car.hardware.hvac.CarVendorHvacManager;
import com.ts.lib.car.hardware.hvac.HvacEventChangeValue;
import com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager;
import com.ts.lib.car.hardware.instrument.InstrumentEventChangeValue;
import com.ts.lib.car.hardware.light.CarVendorLightManager;
import com.ts.lib.car.hardware.light.LightEventChangeValue;
import com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager;
import com.ts.lib.car.hardware.pm2p5.Pm2p5EventChangeValue;
import com.ts.lib.car.hardware.radar.CarVendorRadarManager;
import com.ts.lib.car.hardware.radar.RadarEventChangeValue;
import com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager;
import com.ts.lib.car.hardware.rearviewmirror.RearviewmirrorEventChangeValue;
import com.ts.lib.car.hardware.reminder.CarVendorReminderManager;
import com.ts.lib.car.hardware.reminder.ReminderEventChangeValue;
import com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager;
import com.ts.lib.car.hardware.safetybelt.SafetybeltEventChangeValue;
import com.ts.lib.car.hardware.sensor.CarVendorSensorManager;
import com.ts.lib.car.hardware.sensor.SensorEventChangeValue;
import com.ts.lib.car.hardware.setting.CarVendorSettingManager;
import com.ts.lib.car.hardware.setting.SettingEventChangeValue;
import com.ts.lib.car.hardware.speed.CarVendorSpeedManager;
import com.ts.lib.car.hardware.speed.SpeedEventChangeValue;
import com.ts.lib.car.hardware.statics.CarVendorStaticManager;
import com.ts.lib.car.hardware.statics.StaticEventChangeValue;
import com.ts.lib.car.hardware.tyre.CarVendorTyreManager;
import com.ts.lib.car.hardware.tyre.TyreEventChangeValue;
import com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager;
import com.ts.lib.car.hardware.whud.CarVendorWhudManager;
import com.ts.lib.car.hardware.whud.WhudEventChangeValue;
import com.ts.lib.car.hardware.xcall.CarVendorXcallManager;
import com.ts.lib.car.hardware.xcall.XcallEventChangeValue;
import com.ts.lib.car.hardware.yun.CarVendorYunManager;
import com.ts.lib.car.hardware.yun.YunEventChangeValue;
import com.ts.lib.caradapter.ICarAdapterService;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: CarAdapterServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends ICarAdapterService.Stub {
    private static final String b1 = e.class.getSimpleName();
    private CarVendorInstrumentManager A;
    private CarVendorSensorManager B;
    private CarVendorRadarManager C;
    private CarVendorSpeedManager D;
    private CarVendorUistyleManager E;
    private CarVendorDipilotManager F;
    private CarVendorReminderManager G;
    private CarVendorCarmodeManager H;
    private CarVendorDigitalkeyManager I;
    private CarVendorCollectdataManager J;
    private CarVendorLightManager K;
    private CarVendorYunManager L;
    private CarVendorWhudManager M;
    private CarVendorXcallManager N;
    private CarVendorCloudMessageManager O;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ts.appservice.caradapter.c.k f28a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ts.appservice.caradapter.c.f f29b;
    private com.ts.appservice.caradapter.c.g c;
    private d d;
    private com.ts.appservice.caradapter.c.j e;
    private com.ts.appservice.caradapter.c.i f;
    private c g;
    private com.ts.appservice.caradapter.c.l h;
    private com.ts.appservice.caradapter.c.h i;
    private CarVendorAmbientManager j;
    private CarVendorDoorlockManager k;
    private CarVendorFragranceManager l;
    private CarVendorHealthManager m;
    private CarVendorSafetybeltManager n;
    private CarVendorStaticManager o;
    private CarVendorChargingManager p;
    private CarVendorCarbodyManager q;
    private CarVendorEngineManager r;
    private CarVendorTyreManager s;
    private Car s0;
    private CarVendorEnergyManager t;
    private Context t0;
    private CarVendorAssistManager u;
    private CarVendorRearviewmirrorManager v;
    private a.a.a.a.a.a v0;
    private CarVendorHvacManager w;
    private String w0;
    private CarVendorPm2p5Manager x;
    private CarVendorSettingManager y;
    private CarVendorGearboxManager z;
    private final List<com.ts.appservice.caradapter.d.r> P = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.v> Q = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.w> R = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.m> S = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.j> T = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.l> U = new ArrayList(20);
    private final List<InterfaceC0010b> V = new ArrayList(20);
    private final List<InterfaceC0009a> W = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.q> X = new ArrayList(20);
    private final List<Object> Y = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.g> Z = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.y> a0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.h> b0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.o> c0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.f> d0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.u> e0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.k> f0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.n> g0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.i> h0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.z> i0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.c> j0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.x> k0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.s> l0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.d> m0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.t> n0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.e> o0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.p> p0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.A> q0 = new ArrayList(20);
    private final List<com.ts.appservice.caradapter.d.B> r0 = new ArrayList(20);
    private volatile boolean u0 = false;
    private final CarConnectionCallback x0 = new j();
    private final CarVendorGearboxManager.OnCarGearboxEventListener y0 = new t();
    private final CarVendorInstrumentManager.OnCarInstrumentEventListener z0 = new x();
    private final CarVendorRadarManager.OnCarRadarEventListener A0 = new y();
    private final CarVendorSensorManager.OnCarSensorEventListener B0 = new z();
    private final CarVendorHvacManager.OnCarHvacEventListener C0 = new A();
    private final CarVendorPm2p5Manager.OnCarPm2p5EventListener D0 = new B();
    private final CarVendorHealthManager.OnCarHealthEventListener E0 = new C();
    private final CarVendorFragranceManager.OnCarFragranceEventListener F0 = new D();
    private final CarVendorChargingManager.OnCarChargingEventListener G0 = new C0005a();
    private final CarVendorLightManager.OnCarLightEventListener H0 = new C0006b();
    private final CarVendorDipilotManager.OnCarDipilotEventListener I0 = new C0007c();
    private final CarVendorCarbodyManager.OnCarCarbodyEventListener J0 = new C0008d();
    private final CarVendorSpeedManager.OnCarSpeedEventListener K0 = new C0003e();
    private final CarVendorSafetybeltManager.OnCarSafetybeltEventListener L0 = new f();
    private final CarVendorSettingManager.OnCarSettingEventListener M0 = new g();
    private final CarVendorEnergyManager.OnCarEnergyEventListener N0 = new h();
    private final CarVendorEngineManager.OnCarEngineEventListener O0 = new i();
    private final CarVendorTyreManager.OnCarTyreEventListener P0 = new k();
    private final CarVendorStaticManager.OnCarStaticEventListener Q0 = new l();
    private final CarVendorYunManager.OnCarYunEventListener R0 = new m();
    private final CarVendorRearviewmirrorManager.OnCarRearviewmirrorEventListener S0 = new n();
    private final CarVendorCollectdataManager.OnCarCollectdataEventListener T0 = new o();
    private final CarVendorReminderManager.OnCarReminderEventListener U0 = new p();
    private final CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener V0 = new q();
    private final CarVendorCarmodeManager.OnCarCarmodeEventListener W0 = new r();
    private final CarVendorWhudManager.OnCarWhudEventListener X0 = new s();
    private final CarVendorXcallManager.OnCarXcallEventListener Y0 = new u();
    private CarVendorDoorlockManager.OnCarDoorlockEventListener Z0 = new v();
    private final E a1 = new E(this, null);

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class A implements CarVendorHvacManager.OnCarHvacEventListener {
        A() {
        }

        public void onHvacErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onHvacErrorEvent:" + i);
        }

        public void onHvacEventChange(HvacEventChangeValue hvacEventChangeValue) {
            try {
                if (e.this.S.size() > 0) {
                    Iterator it = e.this.S.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.m) it.next()).a(hvacEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class B implements CarVendorPm2p5Manager.OnCarPm2p5EventListener {
        B() {
        }

        public void onPm2p5ErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onHvacErrorEvent:" + i);
        }

        public void onPm2p5EventChange(Pm2p5EventChangeValue pm2p5EventChangeValue) {
            try {
                if (e.this.X.size() > 0) {
                    Iterator it = e.this.X.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.q) it.next()).b(pm2p5EventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class C implements CarVendorHealthManager.OnCarHealthEventListener {
        C() {
        }

        public void onHealthErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onHealthErrorEvent:" + i);
        }

        public void onHealthEventChange(HealthEventChangeValue healthEventChangeValue) {
            try {
                if (e.this.U.size() > 0) {
                    Iterator it = e.this.U.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.l) it.next()).d(healthEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class D implements CarVendorFragranceManager.OnCarFragranceEventListener {
        D() {
        }

        public void onFragranceErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onFragranceErrorEvent:" + i);
        }

        public void onFragranceEventChange(FragranceEventChangeValue fragranceEventChangeValue) {
            try {
                if (e.this.T.size() > 0) {
                    Iterator it = e.this.T.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.j) it.next()).d(fragranceEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    private class E implements IHwBinder.DeathRecipient {
        private E() {
        }

        public void serviceDied(long j) {
            LogUtil.error(e.b1, "***Vehicle HAL died.***");
            try {
                e.this.v0.f(this);
            } catch (RemoteException unused) {
                LogUtil.error(e.b1, "Failed to unlinkToDeath");
            }
            e.this.v0 = null;
            LogUtil.error(e.b1, "Trying to reconnect to Vehicle HAL: " + e.this.w0);
            e eVar = e.this;
            eVar.v0 = eVar.e3(10000L);
            if (e.this.v0 == null) {
                LogUtil.error(e.b1, "Failed to reconnect to Vehicle HAL");
            }
            com.ts.appservice.caradapter.e.c.d(e.this.v0);
            e.j3(e.this.v0, this);
        }

        /* synthetic */ E(e eVar, j jVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.ts.appservice.caradapter.c.e$a, reason: case insensitive filesystem */
    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class C0005a implements CarVendorChargingManager.OnCarChargingEventListener {
        C0005a() {
        }

        public void onChargingErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onChargingEventChange:" + i);
        }

        public void onChargingEventChange(ChargingEventChangeValue chargingEventChangeValue) {
            try {
                if (e.this.V.size() > 0) {
                    Iterator it = e.this.V.iterator();
                    while (it.hasNext()) {
                        ((InterfaceC0010b) it.next()).l(chargingEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.appservice.caradapter.c.e$b, reason: case insensitive filesystem */
    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class C0006b implements CarVendorLightManager.OnCarLightEventListener {
        C0006b() {
        }

        public void onLightErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onLightErrorEvent:" + i);
        }

        public void onLightEventChange(LightEventChangeValue lightEventChangeValue) {
            try {
                if (e.this.c0.size() > 0) {
                    Iterator it = e.this.c0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.o) it.next()).i(lightEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.appservice.caradapter.c.e$c, reason: case insensitive filesystem */
    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class C0007c implements CarVendorDipilotManager.OnCarDipilotEventListener {
        C0007c() {
        }

        public void onDipilotErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onDipilotErrorEvent:" + i);
        }

        public void onDipilotEventChange(DipilotEventChangeValue dipilotEventChangeValue) {
            try {
                if (e.this.d0.size() > 0) {
                    Iterator it = e.this.d0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.f) it.next()).f(dipilotEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.appservice.caradapter.c.e$d, reason: case insensitive filesystem */
    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class C0008d implements CarVendorCarbodyManager.OnCarCarbodyEventListener {
        C0008d() {
        }

        public void onCarbodyErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onCarbodyErrorEvent:" + i);
        }

        public void onCarbodyEventChange(CarbodyEventChangeValue carbodyEventChangeValue) {
            try {
                if (e.this.W.size() > 0) {
                    Iterator it = e.this.W.iterator();
                    while (it.hasNext()) {
                        ((InterfaceC0009a) it.next()).a(carbodyEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.appservice.caradapter.c.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class C0003e implements CarVendorSpeedManager.OnCarSpeedEventListener {
        C0003e() {
        }

        public void onSpeedErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onSpeedErrorEvent:" + i);
        }

        public void onSpeedEventChange(SpeedEventChangeValue speedEventChangeValue) {
            try {
                if (e.this.k0.size() > 0) {
                    Iterator it = e.this.k0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.x) it.next()).b(speedEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class f implements CarVendorSafetybeltManager.OnCarSafetybeltEventListener {
        f() {
        }

        public void onSafetybeltErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onSafetybeltErrorEvent:" + i);
        }

        public void onSafetybeltEventChange(SafetybeltEventChangeValue safetybeltEventChangeValue) {
            try {
                if (e.this.e0.size() > 0) {
                    Iterator it = e.this.e0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.u) it.next()).onSafetybeltEventChange(safetybeltEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class g implements CarVendorSettingManager.OnCarSettingEventListener {
        g() {
        }

        public void onSettingErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onSettingErrorEvent:" + i);
        }

        public void onSettingEventChange(SettingEventChangeValue settingEventChangeValue) {
            try {
                if (e.this.R.size() > 0) {
                    Iterator it = e.this.R.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.w) it.next()).a(settingEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class h implements CarVendorEnergyManager.OnCarEnergyEventListener {
        h() {
        }

        public void onEnergyErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onEnergyErrorEvent:" + i);
        }

        public void onEnergyEventChange(EnergyEventChangeValue energyEventChangeValue) {
            try {
                if (e.this.b0.size() > 0) {
                    Iterator it = e.this.b0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.h) it.next()).f(energyEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class i implements CarVendorEngineManager.OnCarEngineEventListener {
        i() {
        }

        public void onEngineErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "mOnCarEngineEventListener:" + i);
        }

        public void onEngineEventChange(EngineEventChangeValue engineEventChangeValue) {
            try {
                if (e.this.h0.size() > 0) {
                    Iterator it = e.this.h0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.i) it.next()).d(engineEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class j extends CarConnectionCallback {
        j() {
        }

        public void onConnected(Car car) {
            try {
                LogUtil.warning(e.b1, "Car connected in onServiceConnected start");
                e.this.E = (CarVendorUistyleManager) car.getCarManager("uistyle");
                e.this.y = (CarVendorSettingManager) car.getCarManager("setting");
                e.this.y.addCarSettingEventListener(e.this.M0);
                e.this.u = (CarVendorAssistManager) car.getCarManager("assist");
                if (e.this.h != null) {
                    e.this.h.l(e.this.E);
                }
                if (e.this.g != null) {
                    e.this.T2(e.this.g);
                    e.this.g.f(e.this.y, e.this.u);
                }
                e.this.k = (CarVendorDoorlockManager) car.getCarManager("doorlock");
                e.this.k.addCarDoorlockEventListener(e.this.Z0);
                e.this.w = (CarVendorHvacManager) car.getCarManager("hvac");
                e.this.w.addCarHvacEventListener(e.this.C0);
                e.this.x = (CarVendorPm2p5Manager) car.getCarManager("pm2p5");
                e.this.x.addCarPm2p5EventListener(e.this.D0);
                e.this.l = (CarVendorFragranceManager) car.getCarManager("fragrance");
                e.this.l.addCarFragranceEventListener(e.this.F0);
                e.this.m = (CarVendorHealthManager) car.getCarManager("health");
                e.this.m.addCarHealthEventListener(e.this.E0);
                e.this.p = (CarVendorChargingManager) car.getCarManager("charging");
                e.this.p.addCarChargingEventListener(e.this.G0);
                e.this.q = (CarVendorCarbodyManager) car.getCarManager("carbody");
                e.this.q.addCarCarbodyEventListener(e.this.J0);
                e.this.D = (CarVendorSpeedManager) car.getCarManager("speed");
                e.this.D.addCarSpeedEventListener(e.this.K0);
                e.this.t = (CarVendorEnergyManager) car.getCarManager("energy");
                e.this.t.addCarEnergyEventListener(e.this.N0);
                e.this.n = (CarVendorSafetybeltManager) car.getCarManager("safetybelt");
                e.this.n.addCarSafetybeltEventListener(e.this.L0);
                e.this.o = (CarVendorStaticManager) car.getCarManager("statics");
                e.this.o.addCarStaticEventListener(e.this.Q0);
                e.this.C = (CarVendorRadarManager) car.getCarManager("radar");
                e.this.C.addCarRadarEventListener(e.this.A0);
                e.this.A = (CarVendorInstrumentManager) car.getCarManager("instrument");
                e.this.A.addCarInstrumentEventListener(e.this.z0);
                e.this.z = (CarVendorGearboxManager) car.getCarManager("gearbox");
                e.this.z.addCarGearboxEventListener(e.this.y0);
                e.this.M = (CarVendorWhudManager) car.getCarManager("whud");
                e.this.M.addCarWhudEventListener(e.this.X0);
                e.this.B = (CarVendorSensorManager) car.getCarManager("sensor");
                e.this.B.addCarSensorEventListener(e.this.B0);
                e.this.r = (CarVendorEngineManager) car.getCarManager("engine");
                e.this.r.addCarEngineEventListener(e.this.O0);
                e.this.s = (CarVendorTyreManager) car.getCarManager("tyre");
                e.this.s.addCarTyreEventListener(e.this.P0);
                e.this.K = (CarVendorLightManager) car.getCarManager("light");
                e.this.K.addCarLightEventListener(e.this.H0);
                e.this.F = (CarVendorDipilotManager) car.getCarManager("dipilot");
                e.this.F.addCarDipilotEventListener(e.this.I0);
                e.this.L = (CarVendorYunManager) car.getCarManager("yun");
                e.this.L.addCarYunEventListener(e.this.R0);
                e.this.v = (CarVendorRearviewmirrorManager) car.getCarManager("rearviewmirror");
                e.this.v.addCarRearviewmirrorEventListener(e.this.S0);
                e.this.G = (CarVendorReminderManager) car.getCarManager("reminder");
                e.this.G.addCarReminderEventListener(e.this.U0);
                e.this.I = (CarVendorDigitalkeyManager) car.getCarManager("digitalkey");
                e.this.I.addCarDigitalkeyEventListener(e.this.V0);
                e.this.H = (CarVendorCarmodeManager) car.getCarManager("carmode");
                e.this.H.addCarCarmodeEventListener(e.this.W0);
                e.this.J = (CarVendorCollectdataManager) car.getCarManager("collectdata");
                e.this.J.addCarCollectdataEventListener(e.this.T0);
                e.this.N = (CarVendorXcallManager) car.getCarManager("xcall");
                e.this.N.addCarXcallEventListener(e.this.Y0);
                e.this.O = (CarVendorCloudMessageManager) car.getCarManager("cloud_message");
                e.this.j = (CarVendorAmbientManager) car.getCarManager("ambient");
                if (e.this.f29b != null) {
                    e.this.E2(e.this.f29b);
                    e.this.Q2(e.this.f29b);
                    e.this.D2(e.this.f29b);
                    e.this.A2(e.this.f29b);
                    e.this.X2(e.this.f29b);
                    e.this.f29b.s(e.this.F, e.this.I, e.this.H, e.this.N);
                }
                if (e.this.f28a != null) {
                    e.this.K2(e.this.f28a);
                    e.this.H2(e.this.f28a);
                    e.this.N2(e.this.f28a);
                    e.this.f28a.o(e.this.w, e.this.x, e.this.l);
                }
                if (e.this.e != null) {
                    e.this.O2(e.this.e);
                    e.this.S2(e.this.e);
                    e.this.U2(e.this.e);
                    e.this.e.p(e.this.C, e.this.B, e.this.D);
                }
                if (e.this.f != null) {
                    e.this.R2(e.this.f);
                    e.this.V2(e.this.f);
                    e.this.W2(e.this.f);
                    e.this.f.o(e.this.n, e.this.o, e.this.M, e.this.j);
                }
                if (e.this.d != null) {
                    e.this.J2(e.this.d);
                    e.this.F2(e.this.d);
                    e.this.L2(e.this.d);
                    e.this.P2(e.this.d);
                    e.this.I2(e.this.d);
                    e.this.d.t(e.this.m, e.this.k, e.this.z, e.this.A, e.this.v);
                }
                if (e.this.c != null) {
                    e.this.B2(e.this.c);
                    e.this.M2(e.this.c);
                    e.this.w2(e.this.c);
                    e.this.y2(e.this.c);
                    e.this.z2(e.this.c);
                    e.this.G2(e.this.c);
                    e.this.c.x(e.this.p, e.this.q, e.this.t, e.this.r, e.this.s, e.this.K);
                }
                if (e.this.i != null) {
                    e.this.x2(e.this.i);
                    e.this.C2(e.this.i);
                    e.this.i.o(e.this.O, e.this.L, e.this.J);
                }
                LogUtil.warning(e.b1, "Car connected in onServiceConnected end");
                e.this.u0 = true;
            } catch (CarNotConnectedException e) {
                LogUtil.error(e.b1, "Car not connected in onServiceConnected");
                e.printStackTrace();
            }
        }

        public void onDisconnected(Car car) {
            LogUtil.warning(e.b1, "onDisconnected car:");
            e.this.u0 = false;
            e.this.s0 = null;
            e.this.a3();
            e.this.l3();
            e.this.k3();
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class k implements CarVendorTyreManager.OnCarTyreEventListener {
        k() {
        }

        public void onTyreErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "mOnCarTyreEventListener:" + i);
        }

        public void onTyreEventChange(TyreEventChangeValue tyreEventChangeValue) {
            try {
                if (e.this.i0.size() > 0) {
                    Iterator it = e.this.i0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.z) it.next()).b(tyreEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class l implements CarVendorStaticManager.OnCarStaticEventListener {
        l() {
        }

        public void onStaticErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onStaticErrorEvent:" + i);
        }

        public void onStaticEventChange(StaticEventChangeValue staticEventChangeValue) {
            try {
                if (e.this.a0.size() > 0) {
                    Iterator it = e.this.a0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.y) it.next()).onStaticEventChange(staticEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class m implements CarVendorYunManager.OnCarYunEventListener {
        m() {
        }

        public void onYunErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onYunErrorEvent:" + i);
        }

        public void onYunEventChange(YunEventChangeValue yunEventChangeValue) {
            try {
                if (e.this.j0.size() > 0) {
                    int propertyId = yunEventChangeValue.getPropertyId();
                    LogUtil.optionError(e.b1, "onYunEventChange: " + propertyId);
                    Iterator it = e.this.j0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.c) it.next()).a(yunEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class n implements CarVendorRearviewmirrorManager.OnCarRearviewmirrorEventListener {
        n() {
        }

        public void onRearviewmirrorErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onRearviewmirrorErrorEvent:" + i);
        }

        public void onRearviewmirrorEventChange(RearviewmirrorEventChangeValue rearviewmirrorEventChangeValue) {
            try {
                if (e.this.l0.size() > 0) {
                    Iterator it = e.this.l0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.s) it.next()).i(rearviewmirrorEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class o implements CarVendorCollectdataManager.OnCarCollectdataEventListener {
        o() {
        }

        public void onCollectdataErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onRearviewmirrorErrorEvent:" + i);
        }

        public void onCollectdataEventChange(CollectdataEventChangeValue collectdataEventChangeValue) {
            try {
                if (e.this.m0.size() > 0) {
                    Iterator it = e.this.m0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.d) it.next()).b(collectdataEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class p implements CarVendorReminderManager.OnCarReminderEventListener {
        p() {
        }

        public void onReminderErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onReminderErrorEvent:" + i);
        }

        public void onReminderEventChange(ReminderEventChangeValue reminderEventChangeValue) {
            try {
                if (e.this.n0.size() > 0) {
                    Iterator it = e.this.n0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.t) it.next()).b(reminderEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class q implements CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener {
        q() {
        }

        public void onDigitalkeyErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onDigitalkeyErrorEvent:" + i);
        }

        public void onDigitalkeyEventChange(DigitalkeyEventChangeValue digitalkeyEventChangeValue) {
            try {
                if (e.this.o0.size() > 0) {
                    Iterator it = e.this.o0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.e) it.next()).a(digitalkeyEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class r implements CarVendorCarmodeManager.OnCarCarmodeEventListener {
        r() {
        }

        public void onCarmodeErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onCarmodeErrorEvent:" + i);
        }

        public void onCarmodeEventChange(CarmodeEventChangeValue carmodeEventChangeValue) {
            try {
                if (e.this.p0.size() > 0) {
                    Iterator it = e.this.p0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.p) it.next()).i(carmodeEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class s implements CarVendorWhudManager.OnCarWhudEventListener {
        s() {
        }

        public void onWhudErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onWhudErrorEvent:" + i);
        }

        public void onWhudEventChange(WhudEventChangeValue whudEventChangeValue) {
            try {
                if (e.this.q0.size() > 0) {
                    Iterator it = e.this.q0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.A) it.next()).a(whudEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class t implements CarVendorGearboxManager.OnCarGearboxEventListener {
        t() {
        }

        public void onGearboxErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onGearboxErrorEvent:" + i);
        }

        public void onGearboxEventChange(GearboxEventChangeValue gearboxEventChangeValue) {
            try {
                if (e.this.f0.size() > 0) {
                    Iterator it = e.this.f0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.k) it.next()).a(gearboxEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class u implements CarVendorXcallManager.OnCarXcallEventListener {
        u() {
        }

        public void onXcallErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onWhudErrorEvent:" + i);
        }

        public void onXcallEventChange(XcallEventChangeValue xcallEventChangeValue) {
            try {
                if (e.this.r0.size() > 0) {
                    for (com.ts.appservice.caradapter.d.B b2 : e.this.r0) {
                        LogUtil.debug(e.b1, "CarVendorXcallManager value = " + xcallEventChangeValue);
                        b2.d(xcallEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class v implements CarVendorDoorlockManager.OnCarDoorlockEventListener {
        v() {
        }

        public void onDoorlockErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onDoorlockErrorEvent:" + i);
        }

        public void onDoorlockEventChange(DoorlockEventChangeValue doorlockEventChangeValue) {
            try {
                if (e.this.Z.size() > 0) {
                    Iterator it = e.this.Z.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.g) it.next()).f(doorlockEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class w extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f57a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        w(Looper looper, long j) {
            super(looper);
            this.f57a = j;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (SystemClock.elapsedRealtime() - this.f57a > 3000) {
                LogUtil.info(e.b1, "get Vehicle HAL time out 3000");
                removeCallbacksAndMessages(null);
                return;
            }
            if (message.what == 1000) {
                e.this.v0 = e.c3();
                if (e.this.v0 == null) {
                    sendEmptyMessageDelayed(1000, 100L);
                    return;
                }
                LogUtil.error(e.b1, "get Vehicle Hal successful");
                com.ts.appservice.caradapter.e.c.d(e.this.v0);
                e.this.d3();
                e.j3(e.this.v0, e.this.a1);
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class x implements CarVendorInstrumentManager.OnCarInstrumentEventListener {
        x() {
        }

        public void onInstrumentErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onInstrumentErrorEvent:" + i);
        }

        public void onInstrumentEventChange(InstrumentEventChangeValue instrumentEventChangeValue) {
            try {
                if (e.this.g0.size() > 0) {
                    Iterator it = e.this.g0.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.n) it.next()).b(instrumentEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class y implements CarVendorRadarManager.OnCarRadarEventListener {
        y() {
        }

        public void onRadarErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onRadarErrorEvent:" + i);
        }

        public void onRadarEventChange(RadarEventChangeValue radarEventChangeValue) {
            try {
                if (e.this.P.size() > 0) {
                    Iterator it = e.this.P.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.r) it.next()).d(radarEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CarAdapterServiceImpl.java */
    class z implements CarVendorSensorManager.OnCarSensorEventListener {
        z() {
        }

        public void onSensorErrorEvent(int i, int i2) {
            LogUtil.error(e.b1, "onSensorErrorEvent:" + i);
        }

        public void onSensorEventChange(SensorEventChangeValue sensorEventChangeValue) {
            try {
                if (e.this.Q.size() > 0) {
                    Iterator it = e.this.Q.iterator();
                    while (it.hasNext()) {
                        ((com.ts.appservice.caradapter.d.v) it.next()).a(sensorEventChangeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public e(Context context) {
        this.t0 = context;
        a.a.a.a.a.a aVarC3 = c3();
        this.v0 = aVarC3;
        com.ts.appservice.caradapter.e.c.d(aVarC3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A2(com.ts.appservice.caradapter.d.p pVar) {
        if (this.p0.contains(pVar) || pVar == null) {
            return;
        }
        this.p0.add(pVar);
        LogUtil.optionError(b1, "addCarmodePropertyListener ok! listener size: " + this.p0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B2(InterfaceC0010b interfaceC0010b) {
        if (this.V.contains(interfaceC0010b) || interfaceC0010b == null) {
            return;
        }
        this.V.add(interfaceC0010b);
        LogUtil.optionError(b1, "addChargingPropertyListener ok! listener size: " + this.V.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C2(com.ts.appservice.caradapter.d.d dVar) {
        if (this.m0.contains(dVar) || dVar == null) {
            return;
        }
        this.m0.add(dVar);
        LogUtil.optionError(b1, "addCollectDataPropertyListener ok! listener size: " + this.m0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D2(com.ts.appservice.caradapter.d.e eVar) {
        if (this.o0.contains(eVar) || eVar == null) {
            return;
        }
        this.o0.add(eVar);
        LogUtil.optionError(b1, "addDigitalPropertyListener ok! listener size: " + this.o0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E2(com.ts.appservice.caradapter.d.f fVar) {
        if (this.d0.contains(fVar) || fVar == null) {
            return;
        }
        this.d0.add(fVar);
        LogUtil.optionError(b1, "addDipilotPropertyListener ok! listener size: " + this.d0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F2(com.ts.appservice.caradapter.d.g gVar) {
        if (this.Z.contains(gVar) || gVar == null) {
            return;
        }
        this.Z.add(gVar);
        LogUtil.optionError(b1, "addDoorlockPropertyListener ok! listener size: " + this.Z.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G2(com.ts.appservice.caradapter.d.h hVar) {
        if (this.b0.contains(hVar) || hVar == null) {
            return;
        }
        this.b0.add(hVar);
        LogUtil.optionError(b1, "addEnergyPropertyListener ok! listener size: " + this.b0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H2(com.ts.appservice.caradapter.d.j jVar) {
        if (this.T.contains(jVar) || jVar == null) {
            return;
        }
        this.T.add(jVar);
        LogUtil.optionError(b1, "addFragrancePropertyListener ok! listener size: " + this.T.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I2(com.ts.appservice.caradapter.d.k kVar) {
        if (this.f0.contains(kVar) || kVar == null) {
            return;
        }
        this.f0.add(kVar);
        LogUtil.optionError(b1, "addGearboxPropertyListener ok! listener size: " + this.f0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J2(com.ts.appservice.caradapter.d.l lVar) {
        if (this.U.contains(lVar) || lVar == null) {
            return;
        }
        this.U.add(lVar);
        LogUtil.optionError(b1, "addHealthPropertyListener ok! listener size: " + this.U.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K2(com.ts.appservice.caradapter.d.m mVar) {
        if (this.S.contains(mVar) || mVar == null) {
            return;
        }
        this.S.add(mVar);
        LogUtil.optionError(b1, "addHvacPropertyListener ok! listener size: " + this.S.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L2(com.ts.appservice.caradapter.d.n nVar) {
        if (this.g0.contains(nVar) || nVar == null) {
            return;
        }
        this.g0.add(nVar);
        LogUtil.optionError(b1, "addInstrumentPropertyListener ok! listener size: " + this.g0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M2(com.ts.appservice.caradapter.d.o oVar) {
        if (this.c0.contains(oVar) || oVar == null) {
            return;
        }
        this.c0.add(oVar);
        LogUtil.optionError(b1, "addLightPropertyListener ok! listener size: " + this.c0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N2(com.ts.appservice.caradapter.d.q qVar) {
        if (this.X.contains(qVar) || qVar == null) {
            return;
        }
        this.X.add(qVar);
        LogUtil.optionError(b1, "addPm2p5PropertyListener ok! listener size: " + this.X.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O2(com.ts.appservice.caradapter.d.r rVar) {
        if (this.P.contains(rVar) || rVar == null) {
            return;
        }
        this.P.add(rVar);
        LogUtil.optionError(b1, "addRadarPropertyListener ok! listener size: " + this.P.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P2(com.ts.appservice.caradapter.d.s sVar) {
        if (this.l0.contains(sVar) || sVar == null) {
            return;
        }
        this.l0.add(sVar);
        LogUtil.optionError(b1, "addRearViewMirrorPropertyListener ok! listener size: " + this.l0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q2(com.ts.appservice.caradapter.d.t tVar) {
        if (this.n0.contains(tVar) || tVar == null) {
            return;
        }
        this.n0.add(tVar);
        LogUtil.optionError(b1, "addReminderPropertyListener ok! listener size: " + this.n0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R2(com.ts.appservice.caradapter.d.u uVar) {
        if (this.e0.contains(uVar) || uVar == null) {
            return;
        }
        this.e0.add(uVar);
        LogUtil.optionError(b1, "addSafetybeltPropertyListener ok! listener size: " + this.e0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S2(com.ts.appservice.caradapter.d.v vVar) {
        if (this.Q.contains(vVar) || vVar == null) {
            return;
        }
        this.Q.add(vVar);
        LogUtil.optionError(b1, "addSensorPropertyListener ok! listener size: " + this.Q.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T2(com.ts.appservice.caradapter.d.w wVar) {
        if (this.R.contains(wVar) || wVar == null) {
            return;
        }
        this.R.add(wVar);
        LogUtil.optionError(b1, "addSettingPropertyListener ok! listener size: " + this.R.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U2(com.ts.appservice.caradapter.d.x xVar) {
        if (this.k0.contains(xVar) || xVar == null) {
            return;
        }
        this.k0.add(xVar);
        LogUtil.optionError(b1, "addSpeedPropertyListener ok! listener size: " + this.k0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V2(com.ts.appservice.caradapter.d.y yVar) {
        if (this.a0.contains(yVar) || yVar == null) {
            return;
        }
        this.a0.add(yVar);
        LogUtil.optionError(b1, "addStatisticPropertyListener ok! listener size: " + this.a0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W2(com.ts.appservice.caradapter.d.A a2) {
        if (this.q0.contains(a2) || a2 == null) {
            return;
        }
        this.q0.add(a2);
        LogUtil.optionError(b1, "CarWhudPropertyListener ok! listener size: " + this.q0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X2(com.ts.appservice.caradapter.d.B b2) {
        if (this.r0.contains(b2) || b2 == null) {
            return;
        }
        this.r0.add(b2);
        LogUtil.optionError(b1, "addXcallPropertyListener ok! listener size: " + this.r0.size());
    }

    private void Y2() {
        String str = b1;
        if (this.v0 == null) {
            LogUtil.info(str, "Unable to get Vehicle HAL into child thread loop get.");
            new w(com.ts.appservice.caradapter.e.b.a("getVehicle").getLooper(), SystemClock.elapsedRealtime()).sendEmptyMessageDelayed(1000, 100L);
        } else {
            LogUtil.error(str, "get Vehicle Hal successful");
            d3();
            j3(this.v0, this.a1);
        }
    }

    private void Z2() {
        List<com.ts.appservice.caradapter.d.g> list = this.Z;
        if (list != null && list.size() > 0) {
            this.Z.clear();
        }
        List<com.ts.appservice.caradapter.d.w> list2 = this.R;
        if (list2 != null && list2.size() > 0) {
            this.R.clear();
        }
        List<com.ts.appservice.caradapter.d.m> list3 = this.S;
        if (list3 != null && list3.size() > 0) {
            this.S.clear();
        }
        List<com.ts.appservice.caradapter.d.l> list4 = this.U;
        if (list4 != null && list4.size() > 0) {
            this.U.clear();
        }
        List<com.ts.appservice.caradapter.d.j> list5 = this.T;
        if (list5 != null && list5.size() > 0) {
            this.T.clear();
        }
        List<com.ts.appservice.caradapter.d.q> list6 = this.X;
        if (list6 != null && list6.size() > 0) {
            this.X.clear();
        }
        List<Object> list7 = this.Y;
        if (list7 != null && list7.size() > 0) {
            this.Y.clear();
        }
        List<com.ts.appservice.caradapter.d.r> list8 = this.P;
        if (list8 != null && list8.size() > 0) {
            this.P.clear();
        }
        List<com.ts.appservice.caradapter.d.v> list9 = this.Q;
        if (list9 != null && list9.size() > 0) {
            this.Q.clear();
        }
        List<com.ts.appservice.caradapter.d.h> list10 = this.b0;
        if (list10 != null && list10.size() > 0) {
            this.b0.clear();
        }
        List<com.ts.appservice.caradapter.d.y> list11 = this.a0;
        if (list11 != null && list11.size() > 0) {
            this.a0.clear();
        }
        List<com.ts.appservice.caradapter.d.u> list12 = this.e0;
        if (list12 != null && list12.size() > 0) {
            this.e0.clear();
        }
        List<com.ts.appservice.caradapter.d.k> list13 = this.f0;
        if (list13 != null && list13.size() > 0) {
            this.f0.clear();
        }
        List<com.ts.appservice.caradapter.d.n> list14 = this.g0;
        if (list14 != null && list14.size() > 0) {
            this.g0.clear();
        }
        List<com.ts.appservice.caradapter.d.A> list15 = this.q0;
        if (list15 == null || list15.size() <= 0) {
            return;
        }
        this.q0.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a3() {
        try {
            if (this.w != null) {
                this.w.removeCarHvacEventListener(this.C0);
                this.w = null;
            }
            if (this.x != null) {
                this.x.removeCarPm2p5EventListener(this.D0);
                this.x = null;
            }
            if (this.m != null) {
                this.m.removeCarHealthEventListener(this.E0);
                this.m = null;
            }
            if (this.p != null) {
                this.p.removeCarChargingEventListener(this.G0);
                this.p = null;
            }
            if (this.q != null) {
                this.q.removeCarCarbodyEventListener(this.J0);
                this.q = null;
            }
            if (this.D != null) {
                this.D.removeCarSpeedEventListener(this.K0);
                this.D = null;
            }
            if (this.C != null) {
                this.C.removeCarRadarEventListener(this.A0);
                this.C = null;
            }
            if (this.B != null) {
                this.B.removeCarSensorEventListener(this.B0);
                this.B = null;
            }
            if (this.y != null) {
                this.y.removeCarSettingEventListener(this.M0);
                this.y = null;
            }
            if (this.t != null) {
                this.t.removeCarEnergyEventListener(this.N0);
                this.t = null;
            }
            if (this.n != null) {
                this.n.removeCarSafetybeltEventListener(this.L0);
                this.n = null;
            }
            if (this.o != null) {
                this.o.removeCarStaticEventListener(this.Q0);
                this.o = null;
            }
            if (this.K != null) {
                this.K.removeCarLightEventListener(this.H0);
                this.K = null;
            }
            if (this.F != null) {
                this.F.removeCarDipilotEventListener(this.I0);
                this.F = null;
            }
            if (this.z != null) {
                this.z.removeCarGearboxEventListener(this.y0);
                this.z = null;
            }
            if (this.A != null) {
                this.A.removeCarInstrumentEventListener(this.z0);
                this.A = null;
            }
            if (this.k != null) {
                this.k.removeCarDoorlockEventListener(this.Z0);
                this.k = null;
            }
            if (this.l != null) {
                this.l.removeCarFragranceEventListener(this.F0);
                this.l = null;
            }
            if (this.r != null) {
                this.r.removeCarEngineEventListener(this.O0);
                this.r = null;
            }
            if (this.s != null) {
                this.s.removeCarTyreEventListener(this.P0);
                this.s = null;
            }
            if (this.E != null) {
                this.E = null;
            }
            if (this.L != null) {
                this.L.removeCarYunEventListener(this.R0);
                this.L = null;
            }
            if (this.v != null) {
                this.v.removeCarRearviewmirrorEventListener(this.S0);
                this.v = null;
            }
            if (this.G != null) {
                this.G.removeCarReminderEventListener(this.U0);
            }
            if (this.H != null) {
                this.H.removeCarCarmodeEventListener(this.W0);
            }
            if (this.I != null) {
                this.I.removeCarDigitalkeyEventListener(this.V0);
            }
            if (this.J != null) {
                this.J.removeCarCollectdataEventListener(this.T0);
            }
            if (this.O != null) {
                this.O = null;
            }
            if (this.u != null) {
                this.u = null;
            }
            if (this.j != null) {
                this.j = null;
            }
        } catch (CarNotConnectedException e) {
            LogUtil.error(b1, "Car not connected in onServiceConnected");
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a.a.a.a.a.a c3() {
        String str = SystemProperties.get("ro.vehicle.hal", "default");
        try {
            return a.a.a.a.a.a.b(str);
        } catch (RemoteException e) {
            LogUtil.error("getVehicle", "Failed to get IVehicle/" + str + " service", e);
            return null;
        } catch (NoSuchElementException unused) {
            LogUtil.error("getVehicle", "IVehicle/" + str + " service not registered yet");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d3() {
        try {
            this.w0 = this.v0.d();
        } catch (RemoteException unused) {
            LogUtil.error(b1, "Unable to get Vehicle HAL interface descriptor");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a.a.a.a.a.a e3(long j2) {
        a.a.a.a.a.a aVarC3 = c3();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (aVarC3 == null && jElapsedRealtime + j2 > SystemClock.elapsedRealtime()) {
            try {
                Thread.sleep(100L);
                aVarC3 = c3();
            } catch (InterruptedException e) {
                throw new RuntimeException("Sleep was interrupted", e);
            }
        }
        return aVarC3;
    }

    private void g3() {
        String str = b1;
        LogUtil.warning(str, "initCar start");
        Car carCreateCar = Car.createCar(this.t0, this.x0);
        this.s0 = carCreateCar;
        carCreateCar.connect();
        LogUtil.warning(str, "initCar end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: h3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void i3() {
        String str = b1;
        LogUtil.warning(str, "reinitCar start");
        Car carCreateCar = Car.createCar(this.t0, this.x0);
        this.s0 = carCreateCar;
        carCreateCar.connect();
        LogUtil.warning(str, "reinitCar end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j3(a.a.a.a.a.a aVar, IHwBinder.DeathRecipient deathRecipient) {
        try {
            aVar.g(deathRecipient, 0L);
        } catch (RemoteException unused) {
            LogUtil.error(b1, "Failed to linkToDeath Vehicle HAL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k3() {
        new Handler().postDelayed(new Runnable() { // from class: com.ts.appservice.caradapter.c.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f20a.i3();
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l3() {
        this.f29b.z(null, null, null, null);
        this.f28a.t(null, null, null);
        this.e.u(null, null, null);
        this.f.t(null, null, null, null);
        this.d.A(null, null, null, null, null);
        this.c.F(null, null, null, null, null, null);
        this.g.n(null, null);
        this.h.p(null);
        this.i.t(null, null, null);
    }

    private void m3() {
        CarVendorHvacManager carVendorHvacManager = this.w;
        if (carVendorHvacManager != null) {
            carVendorHvacManager.removeCarHvacEventListener(this.C0);
        }
        CarVendorRadarManager carVendorRadarManager = this.C;
        if (carVendorRadarManager != null) {
            carVendorRadarManager.removeCarRadarEventListener(this.A0);
        }
        CarVendorSensorManager carVendorSensorManager = this.B;
        if (carVendorSensorManager != null) {
            carVendorSensorManager.removeCarSensorEventListener(this.B0);
        }
        CarVendorPm2p5Manager carVendorPm2p5Manager = this.x;
        if (carVendorPm2p5Manager != null) {
            carVendorPm2p5Manager.removeCarPm2p5EventListener(this.D0);
        }
        CarVendorHealthManager carVendorHealthManager = this.m;
        if (carVendorHealthManager != null) {
            carVendorHealthManager.removeCarHealthEventListener(this.E0);
        }
        CarVendorChargingManager carVendorChargingManager = this.p;
        if (carVendorChargingManager != null) {
            carVendorChargingManager.removeCarChargingEventListener(this.G0);
        }
        CarVendorCarbodyManager carVendorCarbodyManager = this.q;
        if (carVendorCarbodyManager != null) {
            carVendorCarbodyManager.removeCarCarbodyEventListener(this.J0);
        }
        CarVendorSpeedManager carVendorSpeedManager = this.D;
        if (carVendorSpeedManager != null) {
            carVendorSpeedManager.removeCarSpeedEventListener(this.K0);
        }
        CarVendorSettingManager carVendorSettingManager = this.y;
        if (carVendorSettingManager != null) {
            carVendorSettingManager.removeCarSettingEventListener(this.M0);
        }
        CarVendorEnergyManager carVendorEnergyManager = this.t;
        if (carVendorEnergyManager != null) {
            carVendorEnergyManager.removeCarEnergyEventListener(this.N0);
        }
        CarVendorSafetybeltManager carVendorSafetybeltManager = this.n;
        if (carVendorSafetybeltManager != null) {
            carVendorSafetybeltManager.removeCarSafetybeltEventListener(this.L0);
        }
        CarVendorStaticManager carVendorStaticManager = this.o;
        if (carVendorStaticManager != null) {
            carVendorStaticManager.removeCarStaticEventListener(this.Q0);
        }
        CarVendorLightManager carVendorLightManager = this.K;
        if (carVendorLightManager != null) {
            carVendorLightManager.removeCarLightEventListener(this.H0);
        }
        CarVendorDipilotManager carVendorDipilotManager = this.F;
        if (carVendorDipilotManager != null) {
            carVendorDipilotManager.removeCarDipilotEventListener(this.I0);
        }
        CarVendorGearboxManager carVendorGearboxManager = this.z;
        if (carVendorGearboxManager != null) {
            carVendorGearboxManager.removeCarGearboxEventListener(this.y0);
        }
        CarVendorWhudManager carVendorWhudManager = this.M;
        if (carVendorWhudManager != null) {
            carVendorWhudManager.removeCarWhudEventListener(this.X0);
        }
        CarVendorXcallManager carVendorXcallManager = this.N;
        if (carVendorXcallManager != null) {
            carVendorXcallManager.removeCarXcallEventListener(this.Y0);
        }
        CarVendorInstrumentManager carVendorInstrumentManager = this.A;
        if (carVendorInstrumentManager != null) {
            carVendorInstrumentManager.removeCarInstrumentEventListener(this.z0);
        }
        CarVendorDoorlockManager carVendorDoorlockManager = this.k;
        if (carVendorDoorlockManager != null) {
            carVendorDoorlockManager.removeCarDoorlockEventListener(this.Z0);
        }
        CarVendorFragranceManager carVendorFragranceManager = this.l;
        if (carVendorFragranceManager != null) {
            carVendorFragranceManager.removeCarFragranceEventListener(this.F0);
        }
        CarVendorEngineManager carVendorEngineManager = this.r;
        if (carVendorEngineManager != null) {
            carVendorEngineManager.removeCarEngineEventListener(this.O0);
        }
        CarVendorTyreManager carVendorTyreManager = this.s;
        if (carVendorTyreManager != null) {
            carVendorTyreManager.removeCarTyreEventListener(this.P0);
        }
        CarVendorYunManager carVendorYunManager = this.L;
        if (carVendorYunManager != null) {
            carVendorYunManager.removeCarYunEventListener(this.R0);
        }
        CarVendorRearviewmirrorManager carVendorRearviewmirrorManager = this.v;
        if (carVendorRearviewmirrorManager != null) {
            carVendorRearviewmirrorManager.removeCarRearviewmirrorEventListener(this.S0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w2(InterfaceC0009a interfaceC0009a) {
        if (this.W.contains(interfaceC0009a) || interfaceC0009a == null) {
            return;
        }
        this.W.add(interfaceC0009a);
        LogUtil.optionError(b1, "addCarBodyPropertyListener ok! listener size: " + this.W.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x2(com.ts.appservice.caradapter.d.c cVar) {
        if (this.j0.contains(cVar) || cVar == null) {
            return;
        }
        this.j0.add(cVar);
        LogUtil.optionError(b1, "addCarCloudPropertyListener ok! listener size: " + this.j0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y2(com.ts.appservice.caradapter.d.i iVar) {
        if (this.h0.contains(iVar) || iVar == null) {
            return;
        }
        this.h0.add(iVar);
        LogUtil.optionError(b1, "addCarEnginePropertyListener ok! listener size: " + this.h0.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z2(com.ts.appservice.caradapter.d.z zVar) {
        if (this.i0.contains(zVar) || zVar == null) {
            return;
        }
        this.i0.add(zVar);
        LogUtil.optionError(b1, "addCarTyrePropertyListener ok! listener size: " + this.i0.size());
    }

    public void b3() {
        Car car = this.s0;
        if (car != null) {
            car.disconnect();
        }
        try {
            m3();
        } catch (CarNotConnectedException e) {
            LogUtil.error(b1, "Car not connected in onServiceConnected");
            e.printStackTrace();
        }
        Z2();
        this.e.o();
        this.f28a.n();
        this.f29b.r();
        this.c.u();
        this.d.s();
        this.f.n();
        this.g.d();
        this.h.i();
        this.i.n();
        a.a.a.a.a.a aVar = this.v0;
        if (aVar != null) {
            try {
                aVar.f(this.a1);
                this.v0 = null;
            } catch (RemoteException unused) {
            }
        }
    }

    public void f3() {
        this.e = new com.ts.appservice.caradapter.c.j(this.t0);
        this.f28a = new com.ts.appservice.caradapter.c.k(this.t0);
        this.f29b = new com.ts.appservice.caradapter.c.f(this.t0);
        this.c = new com.ts.appservice.caradapter.c.g(this.t0);
        this.d = new d(this.t0);
        this.f = new com.ts.appservice.caradapter.c.i(this.t0);
        this.g = new c(this.t0);
        this.h = new com.ts.appservice.caradapter.c.l(this.t0);
        this.i = new com.ts.appservice.caradapter.c.h(this.t0);
        Y2();
        g3();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.IBinder getCarAdapterService(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.caradapter.c.e.getCarAdapterService(java.lang.String):android.os.IBinder");
    }

    public boolean isServiceBound() {
        LogUtil.debug(b1, "isServiceBound return is " + this.u0);
        return this.u0;
    }
}
