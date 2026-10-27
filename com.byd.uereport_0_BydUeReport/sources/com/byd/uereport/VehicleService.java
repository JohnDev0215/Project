package com.byd.uereport;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.dms.BYDAutoDmsDevice;
import android.hardware.bydauto.dms.IDMSListener;
import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.hardware.bydauto.fragrance.BYDAutoFragranceDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.reminder.AbsBYDAutoReminderListener;
import android.hardware.bydauto.reminder.BYDAutoReminderDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.showcarmode.BYDAutoShowCarDevice;
import android.hardware.bydauto.showcarmode.IShowCarModelChangeListener;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.hardware.bydauto.xcall.AbsBYDXCallListener;
import android.hardware.bydauto.xcall.BYDAutoXCallDevice;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.SystemProperties;
import android.provider.Settings;
import com.byd.carsettings.db.model.MaintainMileBean;
import com.byd.carsettings.db.model.MaintainTimeBean;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.cpaa.model.CpaaType;
import com.byd.systemsettings.SettingsCallDispatcher;
import com.byd.systemsettings.wifi2.common.WifiAP;
import com.byd.uereport.bean.GpackCarBean;
import com.byd.uereport.dialog.ChargeLimitDialog;
import com.byd.uereport.dialog.ChargePortCoverTipDialog;
import com.byd.uereport.dialog.DialogCarTrailer;
import com.byd.uereport.dialog.DisChargeDialog;
import com.byd.uereport.dialog.DisChargeStatusDialog;
import com.byd.uereport.dialog.FreezerTipsDialog;
import com.byd.uereport.dialog.MemoryCleanTipDialog;
import com.byd.uereport.dialog.OilMaintenanceDialog;
import com.byd.uereport.dialog.OpenBackDoorDialog;
import com.byd.uereport.dialog.OverhaulEPBDialog;
import com.byd.uereport.dialog.RefrigeratorSensorDialog;
import com.byd.uereport.dialog.RefrigeratorUnWorkableDialog;
import com.byd.uereport.dialog.SomeThingLeftDialog;
import com.byd.uereport.dialog.TimeSetDialog;
import com.byd.uereport.dialog.TransportModeDialog;
import com.byd.uereport.dialog.WeakCloudSignalDialog;
import com.byd.uereport.dialog.air.AcAssistModeDialog;
import com.byd.uereport.dialog.air.AcAutoCleanDialog;
import com.byd.uereport.dialog.air.AcCleanFinishDialog;
import com.byd.uereport.dialog.air.AcDefrostRearPanelUnavailableDialog;
import com.byd.uereport.dialog.air.AcFragranceLessAllowanceDialog;
import com.byd.uereport.dialog.air.AcKeepEngineRunDialog;
import com.byd.uereport.dialog.air.AcMaxEvModeDialog;
import com.byd.uereport.dialog.air.AcPreheatDialog;
import com.byd.uereport.dialog.air.AcQuickCleanDialog;
import com.bydux.api.common.tools.util.ListUtils;
import d5.a;
import g3.a;
import h3.c;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleService extends Service {
    public e5.b L;
    public DisChargeDialog M;
    public DisChargeStatusDialog N;
    public TimeSetDialog O;
    public OverhaulEPBDialog P;
    public RefrigeratorUnWorkableDialog Q;
    public OilMaintenanceDialog R;
    public c5.b S;
    public DialogCarTrailer T;
    public int Y;
    public a0 Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f3066a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f3067b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f3068c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BYDAutoAcDevice f3069d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f3070d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BYDAutoBodyworkDevice f3071e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BYDAutoFragranceDevice f3073f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public BYDAutoSpeedDevice f3074f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public BYDAutoSettingDevice f3075g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public String f3076g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public BYDAutoChargingDevice f3077h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f3078h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public BYDAutoXCallDevice f3079i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public int f3080i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public BYDAutoVehicleHealthDevice f3081j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public FreezerTipsDialog f3082j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public BYDAutoInstrumentDevice f3083k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public BYDAutoShowCarDevice f3085l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public BYDAutoReminderDevice f3087m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public BYDAutoDmsDevice f3089n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public BYDAutoPowerDevice f3091o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public BYDAutoDoorLockDevice f3093p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public BYDAutoDiPilotDevice f3095q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public y4.a f3097r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public BYDAutoEnergyDevice f3099s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public BYDAutoStudy f3101t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public BYDAutoGearboxDevice f3103u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f3105v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f3107w = 1;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f3109x = 3;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f3111y = 4;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f3113z = 5;
    public final int A = 6;
    public final int B = 8;
    public final int C = 10;
    public final int D = 11;
    public final int E = 12;
    public final int F = 13;
    public final int G = 14;
    public final int H = 17;
    public final int I = 18;
    public final int J = 19;
    public final int K = 20;
    public int U = 0;
    public final int V = 0;
    public final int W = -1;
    public int X = 2;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final a.AbstractBinderC0046a f3072e0 = new k();

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public Handler f3084k0 = new l(Looper.getMainLooper());

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final AbsBYDAutoBodyworkListener f3086l0 = new t();

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final AbsBYDAutoAcListener f3088m0 = new u();

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final AbsBYDAutoVehicleHealthListener f3090n0 = new v();

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final AbsBYDAutoFragranceListener f3092o0 = new w();

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final AbsBYDAutoSettingListener f3094p0 = new x();

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final AbsBYDAutoChargingListener f3096q0 = new y();

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final AbsBYDXCallListener f3098r0 = new z();

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final BroadcastReceiver f3100s0 = new a();

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final BroadcastReceiver f3102t0 = new b();

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public final BroadcastReceiver f3104u0 = new c();

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public final BroadcastReceiver f3106v0 = new d();

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public final AbsBYDAutoInstrumentListener f3108w0 = new e();

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public final AbsBYDAutoSpeedListener f3110x0 = new f();

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public final IShowCarModelChangeListener f3112y0 = new g();

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public final AbsBYDAutoReminderListener f3114z0 = new h();
    public final IDMSListener A0 = new i();
    public AbsBYDAutoPowerListener B0 = new j();
    public AbsBYDAutoDoorLockListener C0 = new m();
    public final AbsBYDAutoDiPilotListener D0 = new n();
    public final AbsBYDAutoEnergyListener E0 = new o();
    public final AbsBYDAutoGearboxListener F0 = new p();
    public final BroadcastReceiver G0 = new q();
    public final BroadcastReceiver H0 = new r();
    public final BroadcastReceiver I0 = new s();

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g5.a.i("------------lowMemoryReceiver----------");
            VehicleService.this.Z.sendEmptyMessage(TsNormalNum.THOUSAND_FIVE);
        }
    }

    public class a0 extends Handler {
        public a0(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            super.handleMessage(message);
            switch (message.what) {
                case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                    VehicleService.this.X();
                    break;
                case 5001:
                    VehicleService.this.k0();
                    break;
                case 5002:
                    VehicleService.this.W();
                    break;
                case 5003:
                    VehicleService.this.o0();
                    break;
                case 5004:
                    VehicleService.this.j0();
                    break;
            }
        }
    }

    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            d5.a.z(VehicleService.this).G();
        }
    }

    public class c extends BroadcastReceiver {
        public c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g5.a.a("receiver action:" + intent.getAction());
            VehicleService.this.f3071e.registerListener(VehicleService.this.f3086l0);
        }
    }

    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("value", -1);
            g5.a.a("xCallTestState:" + intExtra);
            if (intExtra != -1) {
                Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
                messageObtainMessage.arg1 = intExtra;
                messageObtainMessage.what = 19;
                VehicleService.this.f3084k0.removeMessages(19);
                VehicleService.this.f3084k0.sendMessageDelayed(messageObtainMessage, 100L);
            }
            if (intExtra == 15) {
                g5.a.a("screen on");
                BYDAutoOtaDevice.getInstance(VehicleService.this.getApplicationContext()).setBackLightState(1);
                k3.b.m(VehicleService.this.getApplicationContext()).s();
            } else if (intExtra == 16) {
                g5.a.a("screen off");
                BYDAutoOtaDevice.getInstance(VehicleService.this.getApplicationContext()).setBackLightState(2);
            }
        }
    }

    public class e extends AbsBYDAutoInstrumentListener {
        public e() {
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMalfunctionInfoChanged(int i8, int i9) {
            super.onMalfunctionInfoChanged(i8, i9);
            VehicleService.this.f3084k0.removeMessages(26);
            VehicleService.this.f3084k0.sendEmptyMessageDelayed(26, 1000L);
        }
    }

    public class f extends AbsBYDAutoSpeedListener {
        public f() {
        }

        @Override // android.hardware.bydauto.speed.AbsBYDAutoSpeedListener
        public void onSpeedValueChanged(double d8) {
            super.onSpeedValueChanged(d8);
            g5.a.a("onSpeedValueChanged:" + d8);
            if (BYDAutoStudy.getInstance(VehicleService.this.getApplicationContext()).getTrailerModeConfig() == 2 && d8 >= 3.0d) {
                g5.a.i("onSpeedValueChanged: send message ");
                VehicleService.this.f3084k0.sendEmptyMessage(30);
            }
        }
    }

    public class g implements IShowCarModelChangeListener {
        public g() {
        }

        @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener, b.d
        public void isConnected(boolean z7) {
        }

        @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
        public void showCarGearChange(int i8) {
        }

        @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
        public void showCarModelChange(int i8) {
        }
    }

    public class h extends AbsBYDAutoReminderListener {
        public h() {
        }

        @Override // android.hardware.bydauto.reminder.AbsBYDAutoReminderListener, b.d
        public void isConnected(boolean z7) {
            super.isConnected(z7);
        }

        @Override // android.hardware.bydauto.reminder.AbsBYDAutoReminderListener
        public void onIndChanged(int i8) {
            super.onIndChanged(i8);
            g5.a.a("onIndChanged: " + i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  , UE report return");
                return;
            }
            if (i8 == 137) {
                return;
            }
            if (BYDAutoBodyworkDevice.getInstance(VehicleService.this.getApplicationContext()).getPowerLevel() == 0) {
                g5.a.i("it is in off gear mode , UE broadcast is off. ");
                return;
            }
            if (i8 == 42) {
                VehicleService.this.f3084k0.sendEmptyMessage(31);
            }
            y4.d.n(VehicleService.this.getApplicationContext()).q(i8);
        }
    }

    public class i extends IDMSListener {
        public i() {
        }

        @Override // android.hardware.bydauto.dms.IDMSListener
        public void onAttentionDetectChanged(int i8) {
            super.onAttentionDetectChanged(i8);
            VehicleService.this.Z(1, i8);
        }

        @Override // android.hardware.bydauto.dms.IDMSListener
        public void onDriverNotResponse() {
            super.onDriverNotResponse();
            VehicleService.this.Z(2, -1);
        }

        @Override // android.hardware.bydauto.dms.IDMSListener
        public void onFatigueDetectChanged(int i8) {
            super.onFatigueDetectChanged(i8);
            VehicleService.this.Z(0, i8);
        }
    }

    public class j extends AbsBYDAutoPowerListener {
        public j() {
        }

        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onPowerLevelChanged(int i8) {
            super.onPowerLevelChanged(i8);
            g5.a.i("receiver power change , power level :" + i8);
            if (i8 == 0) {
                y4.d.n(VehicleService.this.getApplicationContext()).l();
                b5.h.e(VehicleService.this.getApplicationContext()).c();
                d5.a.z(VehicleService.this.getApplicationContext()).t();
                VehicleService.this.X = 2;
                return;
            }
            if (i8 == 2) {
                y4.d.n(VehicleService.this.getApplicationContext()).m();
                AndroidAutoManager.getInstance(VehicleService.this.getApplicationContext()).handleAccOnEvent();
                VehicleService.this.Y = 0;
                VehicleService.this.Z.sendEmptyMessageDelayed(5004, z3.e.INTERVAL);
                VehicleService.this.Z.sendEmptyMessageDelayed(5001, z3.e.INTERVAL);
            }
        }
    }

    public class k extends a.AbstractBinderC0046a {
        public k() {
        }

        @Override // g3.a
        public void D(MaintainTimeBean maintainTimeBean) throws RemoteException {
            if (maintainTimeBean != null) {
                g5.k.g(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_TIME_STATE", maintainTimeBean.h());
                g5.k.i(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_TIME", maintainTimeBean.f());
                if (maintainTimeBean.g() != -1 || maintainTimeBean.h()) {
                    g5.k.j(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_TIME_TARGET", maintainTimeBean.g());
                } else {
                    g5.k.l(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_TIME_TARGET");
                }
                g5.a.i("maintain setMaintainTimeBean----MaintainTimeBean:" + maintainTimeBean);
            }
            f3.f.a().c(VehicleService.this.getApplicationContext(), true);
        }

        @Override // g3.a
        public void M(MaintainMileBean maintainMileBean) throws RemoteException {
            if (maintainMileBean != null) {
                g5.k.g(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_MILE_STATE", maintainMileBean.h());
                g5.k.h(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_MILE_1", maintainMileBean.f());
                if (maintainMileBean.g() != -1.0d || maintainMileBean.h()) {
                    g5.k.h(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_MILE_TARGET", maintainMileBean.g());
                } else {
                    g5.k.l(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_MILE_TARGET");
                }
                g5.a.i("maintain setMaintainMileBean----MaintainMileBean:" + maintainMileBean);
            }
            f3.f.a().c(VehicleService.this.getApplicationContext(), true);
        }

        @Override // g3.a
        public MaintainTimeBean V() throws RemoteException {
            MaintainTimeBean maintainTimeBean = new MaintainTimeBean();
            maintainTimeBean.i(g5.k.d(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_TIME", 90));
            maintainTimeBean.j(g5.k.b(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_TIME_STATE", false));
            maintainTimeBean.k(g5.k.e(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_TIME_TARGET", -1L));
            g5.a.i("maintain getMaintainTimeBean----MaintainTimeBean:" + maintainTimeBean);
            return maintainTimeBean;
        }

        @Override // g3.a
        public MaintainMileBean X() throws RemoteException {
            MaintainMileBean maintainMileBean = new MaintainMileBean();
            maintainMileBean.i(g5.k.c(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_MILE_1", g5.c.d(VehicleService.this.getApplicationContext())));
            maintainMileBean.j(g5.k.b(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_MILE_STATE", false));
            maintainMileBean.k(g5.k.c(VehicleService.this.getApplicationContext(), "KEY_MAINTAIN_MILE_TARGET", -1.0d));
            g5.a.i("maintain getMaintainMileBean----MaintainMileBean:" + maintainMileBean);
            return maintainMileBean;
        }

        @Override // g3.a
        public void o0(int i8, int i9) throws RemoteException {
            g5.a.b("VehicleService", "IDialogAidlInterface----setDialog-----" + i8 + "----" + i9);
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            VehicleService.this.f3084k0.sendMessage(messageObtain);
        }

        @Override // g3.a
        public void t(int i8) throws RemoteException {
            g5.a.b("VehicleService", "IDialogAidlInterface----clearOilHint-----" + i8);
            if (i8 == 0) {
                g5.k.g(VehicleService.this.getApplicationContext(), "oil_engine", false);
                return;
            }
            if (1 == i8) {
                g5.k.g(VehicleService.this.getApplicationContext(), "oil_transmission", false);
                return;
            }
            if (2 == i8) {
                g5.k.g(VehicleService.this.getApplicationContext(), "oil_brake", false);
            } else if (3 == i8) {
                g5.k.g(VehicleService.this.getApplicationContext(), "blade_battery", false);
            } else if (4 == i8) {
                g5.k.g(VehicleService.this.getApplicationContext(), "motor_elec", false);
            }
        }
    }

    public class l extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SomeThingLeftDialog f3127a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RefrigeratorSensorDialog f3128b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ChargePortCoverTipDialog f3129c;

        public class a implements a.d {
            public a() {
            }

            @Override // d5.a.d
            public void a() {
                g5.a.i("ECall request type cancel.");
                VehicleService.this.f3079i.setCallType(3);
                VehicleService.this.f3079i.setRequestType(3);
            }
        }

        public class b implements DialogInterface.OnDismissListener {
            public b() {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                l.this.f3129c = null;
            }
        }

        public l(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ChargePortCoverTipDialog chargePortCoverTipDialog;
            super.handleMessage(message);
            List<a5.a> listC = null;
            switch (message.what) {
                case 0:
                    new AcPreheatDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 1:
                    g5.m.d(VehicleService.this.getApplicationContext(), VehicleService.this.getString(R.string.ac_bt_wind_tips));
                    break;
                case 3:
                    new AcQuickCleanDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 4:
                    VehicleService vehicleService = VehicleService.this;
                    vehicleService.S = new c5.b(vehicleService.getApplicationContext());
                    VehicleService.this.S.h();
                    break;
                case 5:
                    new AcKeepEngineRunDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 6:
                    new AcMaxEvModeDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 8:
                    new AcFragranceLessAllowanceDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 9:
                    i = a.a.a(VehicleService.this.getApplicationContext()).getOBCType() == 1 ? 1 : 0;
                    if (!g5.c.g(VehicleService.this.getApplicationContext()) && i != 0) {
                        try {
                            if (VehicleService.this.M != null && VehicleService.this.M.isShowing()) {
                                VehicleService.this.M.j();
                            } else {
                                VehicleService vehicleService2 = VehicleService.this;
                                vehicleService2.M = new DisChargeDialog(vehicleService2.getApplicationContext());
                                VehicleService.this.M.show();
                            }
                        } catch (Exception e8) {
                            g5.a.c(e8.getMessage());
                            return;
                        }
                        break;
                    }
                    break;
                case 10:
                    try {
                        if (VehicleService.this.Q != null && VehicleService.this.Q.isShowing()) {
                            VehicleService.this.Q.dismiss();
                        }
                        if (VehicleService.this.Q == null) {
                            VehicleService vehicleService3 = VehicleService.this;
                            vehicleService3.Q = new RefrigeratorUnWorkableDialog(vehicleService3.getApplicationContext());
                        }
                        VehicleService.this.Q.show();
                    } catch (Exception e9) {
                        g5.a.b("TYPE_REFRIGERATOR_UNWORKABLE_DIALOG", e9.getMessage());
                        return;
                    }
                    break;
                case 11:
                    if (VehicleService.this.L == null) {
                        VehicleService vehicleService4 = VehicleService.this;
                        vehicleService4.L = new e5.b(vehicleService4.getApplicationContext());
                    }
                    int i8 = message.arg1;
                    int i9 = message.arg2;
                    g5.a.b("VehicleService", "massage mode update , mode : " + i8);
                    if (i8 < 7 && i8 >= 1) {
                        VehicleService.this.L.n(i8, VehicleService.this.f3075g.getMassageLevel(i9), i9);
                        break;
                    }
                    break;
                case 12:
                    new AcDefrostRearPanelUnavailableDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 13:
                    if (VehicleService.this.L == null) {
                        VehicleService vehicleService5 = VehicleService.this;
                        vehicleService5.L = new e5.b(vehicleService5.getApplicationContext());
                    }
                    int i10 = message.arg1;
                    int i11 = message.arg2;
                    g5.a.b("VehicleService", "massage level update , level : " + i10);
                    VehicleService.this.L.m(VehicleService.this.f3075g.getMassageMode(i11), i10, i11);
                    break;
                case 14:
                    if (a.a.a(VehicleService.this.getApplicationContext()).getDischargeSupport() == 1) {
                        int vtovDischargeConnectState = VehicleService.this.f3077h.getVtovDischargeConnectState();
                        if (((vtovDischargeConnectState == 1 || vtovDischargeConnectState == 2) ? 1 : 0) != 0) {
                            int batteryManagementDeviceState = VehicleService.this.f3077h.getBatteryManagementDeviceState();
                            if (batteryManagementDeviceState == 14 || batteryManagementDeviceState == 12) {
                                try {
                                    if (VehicleService.this.N != null && VehicleService.this.N.isShowing()) {
                                        VehicleService.this.N.f();
                                    } else {
                                        VehicleService vehicleService6 = VehicleService.this;
                                        vehicleService6.N = new DisChargeStatusDialog(vehicleService6.getApplicationContext());
                                        VehicleService.this.N.show();
                                    }
                                } catch (Exception e10) {
                                    g5.a.c(e10.getMessage());
                                    return;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 15:
                    i = a.a.a(VehicleService.this.getApplicationContext()).getOBCType() == 1 ? 1 : 0;
                    if (!g5.c.g(VehicleService.this.getApplicationContext()) && i != 0) {
                        try {
                            if (VehicleService.this.O != null && VehicleService.this.O.isShowing()) {
                                VehicleService.this.O.d();
                            } else {
                                VehicleService vehicleService7 = VehicleService.this;
                                vehicleService7.O = new TimeSetDialog(vehicleService7.getApplicationContext());
                                VehicleService.this.O.show();
                            }
                        } catch (Exception e11) {
                            g5.a.c(e11.getMessage());
                            return;
                        }
                        break;
                    }
                    break;
                case 17:
                    if (VehicleService.this.f3075g.getRefrigeratorMode() == 2) {
                        RefrigeratorSensorDialog refrigeratorSensorDialog = this.f3128b;
                        if (refrigeratorSensorDialog != null && refrigeratorSensorDialog.isShowing()) {
                            this.f3128b.dismiss();
                        }
                        if (this.f3128b == null) {
                            this.f3128b = new RefrigeratorSensorDialog(VehicleService.this.getApplicationContext());
                        }
                        this.f3128b.show();
                    }
                    break;
                case 18:
                    g5.a.i("show transport state dialog.");
                    int i12 = message.arg1;
                    TransportModeDialog transportModeDialog = new TransportModeDialog(VehicleService.this.getApplicationContext());
                    transportModeDialog.j(i12);
                    transportModeDialog.setFlag(i12);
                    transportModeDialog.show();
                    break;
                case 19:
                    int i13 = message.arg1;
                    if (i13 == 8) {
                        d5.a.z(VehicleService.this).L(i13).t();
                    } else if (i13 == 10) {
                        d5.a.z(VehicleService.this).L(i13).P();
                        d5.a.z(VehicleService.this).K(new a());
                    } else if (i13 == 6 || i13 == 5 || i13 == 4 || i13 == 1 || i13 == 2 || i13 == 3) {
                        d5.a.z(VehicleService.this).L(i13).P();
                    }
                    break;
                case 20:
                    boolean z7 = BYDAutoVehicleHealthDevice.getInstance(VehicleService.this.getApplicationContext()).getEPBConfig() == 1 && BYDAutoVehicleHealthDevice.getInstance(VehicleService.this.getApplicationContext()).getEPBType() == 0;
                    i = VehicleService.this.f3071e.hasMessage(43) == 1 ? 1 : 0;
                    if (z7 && i != 0) {
                        try {
                            if (VehicleService.this.P != null && VehicleService.this.P.isShowing()) {
                                VehicleService.this.P.z();
                            } else {
                                VehicleService.this.P = new OverhaulEPBDialog(VehicleService.this.getApplicationContext());
                                VehicleService.this.P.show();
                            }
                        } catch (Exception e12) {
                            g5.a.c(e12.getMessage());
                            return;
                        }
                        break;
                    }
                    break;
                case 22:
                    new AcAssistModeDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 23:
                    new AcAutoCleanDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 24:
                    g5.a.c("show TYPE_OIL_MAINTENANCE_DIALOG dialog.");
                    int i14 = message.arg1;
                    if (VehicleService.this.R == null) {
                        VehicleService vehicleService8 = VehicleService.this;
                        vehicleService8.R = new OilMaintenanceDialog(vehicleService8.getApplicationContext(), i14);
                    } else if (!VehicleService.this.R.isShowing()) {
                        VehicleService vehicleService9 = VehicleService.this;
                        vehicleService9.R = new OilMaintenanceDialog(vehicleService9.getApplicationContext(), i14);
                    }
                    if (VehicleService.this.R != null) {
                        List<a5.a> listC2 = VehicleService.this.R.c(i14);
                        g5.j.b(VehicleService.this.getApplicationContext(), listC2);
                        if (listC2.size() != 0) {
                            if (!VehicleService.this.R.isShowing()) {
                                VehicleService.this.R.show();
                            } else {
                                VehicleService.this.R.e(i14);
                            }
                            break;
                        }
                    }
                    break;
                case 25:
                    SomeThingLeftDialog someThingLeftDialog = this.f3127a;
                    if (someThingLeftDialog != null && someThingLeftDialog.isShowing()) {
                        this.f3127a.dismiss();
                    }
                    if (this.f3127a == null) {
                        this.f3127a = new SomeThingLeftDialog(VehicleService.this.getApplicationContext());
                    }
                    this.f3127a.show();
                    break;
                case 26:
                    g5.j.a(VehicleService.this.getApplicationContext());
                    break;
                case 27:
                    OilMaintenanceDialog oilMaintenanceDialog = new OilMaintenanceDialog(VehicleService.this.getApplicationContext(), 0);
                    while (true) {
                        if (i < 5) {
                            if (BYDAutoVehicleHealthDevice.getInstance(VehicleService.this.getApplicationContext()).getOilMaintainanceHint(i) == 1) {
                                listC = oilMaintenanceDialog.c(i);
                            } else {
                                i++;
                            }
                        }
                    }
                    g5.j.b(VehicleService.this.getApplicationContext(), listC);
                    break;
                case 28:
                    new AcCleanFinishDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 29:
                    if (VehicleService.this.T != null && VehicleService.this.T.isShowing()) {
                        VehicleService.this.T.dismiss();
                    }
                    VehicleService vehicleService10 = VehicleService.this;
                    vehicleService10.T = new DialogCarTrailer(vehicleService10.getApplicationContext());
                    int trailerModeState = VehicleService.this.f3075g.getTrailerModeState();
                    int dragAndDropModeTipsState = VehicleService.this.f3075g.getDragAndDropModeTipsState();
                    g5.a.b("dialogcarstate", "aa" + trailerModeState);
                    if (Settings.System.getInt(VehicleService.this.getContentResolver(), "sys.byd.safety.warn", 0) == 1 && trailerModeState == 1 && dragAndDropModeTipsState != 1 && BYDAutoSpeedDevice.getInstance(VehicleService.this.getApplicationContext()).getSpeedValue() < 3.0f) {
                        VehicleService.this.T.show();
                        break;
                    }
                    break;
                case 30:
                    g5.a.b("dialogcarstate", "speed over");
                    if (VehicleService.this.T != null && VehicleService.this.T.isShowing()) {
                        VehicleService.this.T.dismiss();
                        break;
                    }
                    break;
                case 31:
                    new WeakCloudSignalDialog(VehicleService.this.getApplicationContext()).show();
                    break;
                case 32:
                    int i15 = message.arg1;
                    MemoryCleanTipDialog memoryCleanTipDialog = new MemoryCleanTipDialog(VehicleService.this.getApplicationContext());
                    memoryCleanTipDialog.e(i15);
                    memoryCleanTipDialog.show();
                    break;
                case 33:
                    VehicleService.this.m0(1);
                    g5.a.a("哪里弹的= !freezerOnlineDlg.isShowing()1");
                    break;
                case 34:
                    VehicleService.this.m0(2);
                    g5.a.a("哪里弹的= !freezerOnlineDlg.isShowing()2");
                    break;
                case 35:
                    int i16 = message.arg1;
                    if (i16 == 0) {
                        g5.a.i("show unlock left child lock toast.");
                        g5.m.b(VehicleService.this.getApplicationContext(), VehicleService.this.getString(R.string.toast_text_unlock_left_child_lock), 0, true).show();
                    } else if (i16 == 1) {
                        g5.a.i("show unlock right child lock toast.");
                        g5.m.b(VehicleService.this.getApplicationContext(), VehicleService.this.getString(R.string.toast_text_unlock_right_child_lock), 0, true).show();
                    }
                    break;
                case 36:
                    int i17 = message.arg1;
                    g5.a.a("handle message to back door UI ,arg:" + i17);
                    if (i17 == 0) {
                        g5.m.b(VehicleService.this.getApplicationContext(), VehicleService.this.getString(R.string.toast_open_back_door_in_p), 1, true).show();
                    } else if (i17 == 1) {
                        g5.m.b(VehicleService.this.getApplicationContext(), VehicleService.this.getString(R.string.toast_close_back_door), 1, true).show();
                    } else if (i17 == 2) {
                        new OpenBackDoorDialog(VehicleService.this.getApplicationContext()).show();
                    }
                    break;
                case 37:
                    VehicleService.this.m0(3);
                    g5.a.a("哪里弹的= !freezerOnlineDlg.isShowing()3");
                    break;
                case 38:
                    VehicleService.this.m0(4);
                    g5.a.a("哪里弹的= !freezerOnlineDlg.isShowing()4");
                    break;
                case 40:
                    int i18 = message.arg1;
                    if (i18 == 1) {
                        ChargePortCoverTipDialog chargePortCoverTipDialog2 = new ChargePortCoverTipDialog(VehicleService.this.getApplicationContext());
                        this.f3129c = chargePortCoverTipDialog2;
                        chargePortCoverTipDialog2.setOnDismissListener(new b());
                        this.f3129c.show();
                        break;
                    } else if (i18 == 0 && (chargePortCoverTipDialog = this.f3129c) != null) {
                        chargePortCoverTipDialog.dismiss();
                        this.f3129c = null;
                        break;
                    }
                    break;
                case 41:
                    new ChargeLimitDialog(VehicleService.this.getApplicationContext()).show();
                    break;
            }
        }
    }

    public class m extends AbsBYDAutoDoorLockListener {
        public m() {
        }

        @Override // android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener
        public void onDoorLockCockpitHatchStateChanged(int i8) {
            super.onDoorLockCockpitHatchStateChanged(i8);
            g5.a.a("onDoorLockCockpitHatchStateChanged: " + i8);
            if (!g5.c.i(VehicleService.this.getApplicationContext())) {
                g5.a.a("not PA car, return. ");
                return;
            }
            if (i8 == 1) {
                if (BYDAutoGearboxDevice.getInstance(VehicleService.this.getApplicationContext()).getGear() != 1) {
                    Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
                    messageObtainMessage.arg1 = 0;
                    messageObtainMessage.what = 36;
                    VehicleService.this.f3084k0.sendMessage(messageObtainMessage);
                    return;
                }
                int doorState = VehicleService.this.f3071e.getDoorState(6);
                g5.a.a("backDoorLockStatus: " + doorState);
                Message messageObtainMessage2 = VehicleService.this.f3084k0.obtainMessage();
                if (doorState == 1) {
                    messageObtainMessage2.arg1 = 1;
                } else {
                    messageObtainMessage2.arg1 = 2;
                }
                messageObtainMessage2.what = 36;
                VehicleService.this.f3084k0.sendMessage(messageObtainMessage2);
            }
        }
    }

    public class n extends AbsBYDAutoDiPilotListener {
        public n() {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDataChanged(b.c cVar) {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onError(int i8, String str) {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onHighHODToastChanged(int i8) {
            g5.a.a("onHighHODToastChanged state = " + i8);
            if (VehicleService.this.f3070d0 == i8) {
                return;
            }
            VehicleService.this.f3070d0 = i8;
            if (i8 == 19) {
                g5.m.c(VehicleService.this.getApplicationContext(), R.string.dipilot_hod_toast);
            }
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onHighIOPToastChanged(int i8) {
            g5.a.a("onHighIOPToastChanged state = " + i8);
            if (VehicleService.this.f3067b0 == i8) {
                return;
            }
            VehicleService.this.f3067b0 = i8;
            if (i8 == 36) {
                g5.m.c(VehicleService.this.getApplicationContext(), R.string.dipilot_iop_toast);
            }
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLowHODToastChanged(int i8) {
            g5.a.a("onLowHODToastChanged state = " + i8);
            if (VehicleService.this.f3068c0 == i8) {
                return;
            }
            VehicleService.this.f3068c0 = i8;
            if (i8 == 29) {
                g5.m.c(VehicleService.this.getApplicationContext(), R.string.dipilot_hod_toast);
            }
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLowIOPToastChanged(int i8) {
            g5.a.a("onLowIOPToastChanged state = " + i8);
            if (VehicleService.this.f3066a0 == i8) {
                return;
            }
            VehicleService.this.f3066a0 = i8;
            if (i8 == 1) {
                g5.m.c(VehicleService.this.getApplicationContext(), R.string.dipilot_iop_toast);
            }
        }
    }

    public class o extends AbsBYDAutoEnergyListener {
        public o() {
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onChargePortCoverHintChanged(int i8) {
            super.onChargePortCoverHintChanged(i8);
            g5.a.a("onChargePortCoverHintChanged: " + i8);
            int dMType = VehicleService.this.f3101t.getDMType();
            if (y4.a.n(VehicleService.this.getApplicationContext()).p()) {
                g5.a.a(" the avm is foreground, don't show charge port tip dialog .");
                return;
            }
            if (dMType != 2 && dMType != 3) {
                g5.a.a(" not dm2 or dm2.5, don't show charge port tip dialog .");
                return;
            }
            if (i8 == 2) {
                Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
                messageObtainMessage.what = 40;
                messageObtainMessage.arg1 = 1;
                VehicleService.this.f3084k0.sendMessage(messageObtainMessage);
                return;
            }
            if (i8 == 1) {
                Message messageObtainMessage2 = VehicleService.this.f3084k0.obtainMessage();
                messageObtainMessage2.what = 40;
                messageObtainMessage2.arg1 = 0;
                VehicleService.this.f3084k0.sendMessage(messageObtainMessage2);
            }
        }
    }

    public class p extends AbsBYDAutoGearboxListener {
        public p() {
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i8) {
            super.onGearChanged(i8);
            if (Integer.valueOf(g5.l.a("persist.sys.energytype", "1")).intValue() == 1) {
                VehicleService.this.l0(i8);
            }
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeLevelChanged(int i8) {
            super.onGearboxAutoModeLevelChanged(i8);
            if (Integer.valueOf(g5.l.a("persist.sys.energytype", "1")).intValue() == 2) {
                VehicleService.this.l0(i8);
            }
        }
    }

    public class q extends BroadcastReceiver {
        public q() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            g5.a.i("WiFi or hotpot switch is change .");
            String action = intent.getAction();
            WifiManager wifiManager = (WifiManager) VehicleService.this.getApplicationContext().getSystemService("wifi");
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                if (wifiManager.getWifiState() == 3) {
                    g5.a.i("WiFi open, close wifi hotpot .");
                    g5.i.a(VehicleService.this.getApplicationContext());
                    return;
                }
                return;
            }
            if (WifiAP.WIFI_AP_STATE_CHANGED_ACTION.equals(action) && g5.i.b(VehicleService.this.getApplicationContext())) {
                g5.a.i("WiFi hotpot open, close wifi .");
                wifiManager.setWifiEnabled(false);
            }
        }
    }

    public class r extends BroadcastReceiver {
        public r() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g5.a.i("receiver BLUETOOTH delete device broadcast .");
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice != null) {
                g5.a.i("delete device: " + bluetoothDevice.getName() + ListUtils.DEFAULT_JOIN_SEPARATOR + bluetoothDevice.getAddress());
                List<i3.a> listE = c.a.a().e();
                int i8 = 0;
                while (true) {
                    if (i8 >= listE.size()) {
                        break;
                    }
                    i3.a aVar = listE.get(i8);
                    String strA = aVar.a();
                    if (strA == null || !strA.equals(bluetoothDevice.getAddress())) {
                        i8++;
                    } else if (aVar.b() == CpaaType.CAR_PLAY) {
                        CarPlayManager.getInstance(VehicleService.this.getApplicationContext()).deleteCarPlayDevice(strA);
                    } else if (aVar.b() == CpaaType.ANDROID_AUTO) {
                        AndroidAutoManager.getInstance(VehicleService.this.getApplicationContext()).deleteAndroidAutoDevice(strA);
                    }
                }
                VehicleService.this.n0(bluetoothDevice);
            }
        }
    }

    public class s extends BroadcastReceiver {
        public s() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g5.a.a("close all dialog action:" + intent.getAction());
            b5.h.e(VehicleService.this.getApplicationContext()).c();
        }
    }

    public class t extends AbsBYDAutoBodyworkListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public FreezerTipsDialog f3140a;

        public t() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int i8, int i9) {
            super.onHasMessageChanged(i8, i9);
            if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("onHasMessageChanged powerlevel = BODYWORK_POWER_LEVEL_OFF");
                return;
            }
            if (i8 == 43) {
                g5.a.i("HAS_0X021800 onHasMessageChanged  -- state=" + i9);
                if (i9 == 0 && VehicleService.this.P != null && VehicleService.this.P.isShowing()) {
                    VehicleService.this.P.dismiss();
                    return;
                }
                return;
            }
            if (i8 == 212) {
                g5.a.i("eCall onHasMessageChanged  -- state=" + i9);
                if (i9 == 0) {
                    d5.a.z(VehicleService.this).t();
                    return;
                }
                return;
            }
            if (i8 == 219) {
                int fridgeExpansionAndContractionFunction = BYDAutoSettingDevice.getInstance(VehicleService.this.getApplicationContext()).getFridgeExpansionAndContractionFunction();
                boolean z7 = fridgeExpansionAndContractionFunction == 1 || fridgeExpansionAndContractionFunction == 3;
                g5.a.a("HAS_0x048C00 onHasMessageChanged  -- state=" + i9);
                if (z7 && g5.b.a(VehicleService.this.getApplicationContext())) {
                    g5.a.a(" isFreezerSup  HAS_0x048C00 onHasMessageChanged  -- isFreezerSup=" + z7);
                    if (i9 == 1) {
                        if (this.f3140a == null) {
                            this.f3140a = new FreezerTipsDialog(VehicleService.this.getApplicationContext());
                        }
                        if (this.f3140a.isShowing()) {
                            g5.a.a("freezerOnlineDlg.isShowing()" + this.f3140a.isShowing());
                            this.f3140a.dismiss();
                            return;
                        }
                        return;
                    }
                    if (this.f3140a == null) {
                        this.f3140a = new FreezerTipsDialog(VehicleService.this.getApplicationContext());
                    }
                    if (this.f3140a.isShowing()) {
                        return;
                    }
                    g5.a.a("!freezerOnlineDlg.isShowing()" + (true ^ this.f3140a.isShowing()));
                    this.f3140a.c(0);
                    g5.a.a("哪里弹的= !freezerOnlineDlg.isShowing()" + z7);
                    this.f3140a.show();
                }
            }
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onScreenControlSlideDoorChanged(int i8, int i9) {
            super.onScreenControlSlideDoorChanged(i8, i9);
            g5.a.a("onScreenControlSlideDoorChanged state=" + i8 + ", value:" + i9);
            if (i9 == 1 || i9 == 2 || i9 == 3) {
                if (BYDAutoBodyworkDevice.getInstance(VehicleService.this.getApplicationContext()).getPowerLevel() == 0) {
                    g5.a.a("it is in off gear mode , skip this screen control. ");
                    return;
                }
                if (VehicleService.this.f3071e.getElecSlideDoorState() != 1) {
                    g5.a.a("electronic slide door state not open , skip this screen control. ");
                    return;
                }
                switch (i8) {
                    case 1:
                    case 2:
                    case 5:
                    case 6:
                        int leftChildLockState = BYDAutoDoorLockDevice.getInstance(VehicleService.this.getApplicationContext()).getLeftChildLockState();
                        g5.a.i("leftChildLockState :" + leftChildLockState);
                        if (leftChildLockState == 2) {
                            VehicleService.this.f3071e.setLeftSlideDoorVoiceControlState(i9);
                        } else if (leftChildLockState == 1) {
                            Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
                            messageObtainMessage.arg1 = 0;
                            messageObtainMessage.what = 35;
                            VehicleService.this.f3084k0.sendMessage(messageObtainMessage);
                        }
                        break;
                    case 3:
                    case 4:
                    case 7:
                    case 8:
                        int rightChildLockState = BYDAutoDoorLockDevice.getInstance(VehicleService.this.getApplicationContext()).getRightChildLockState();
                        g5.a.i("rightChildLockState :" + rightChildLockState);
                        if (rightChildLockState == 2) {
                            VehicleService.this.f3071e.setRightSlideDoorVoiceControlState(i9);
                        } else if (rightChildLockState == 1) {
                            Message messageObtainMessage2 = VehicleService.this.f3084k0.obtainMessage();
                            messageObtainMessage2.arg1 = 1;
                            messageObtainMessage2.what = 35;
                            VehicleService.this.f3084k0.sendMessage(messageObtainMessage2);
                        }
                        break;
                }
            }
        }
    }

    public class u extends AbsBYDAutoAcListener {
        public u() {
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACAssistModeTips(int i8) {
            super.onACAssistModeTips(i8);
            g5.a.a("onACAssistModeTips,state:" + i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
            } else if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
            } else if (i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(22);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACBTWindTips(int i8) {
            super.onACBTWindTips(i8);
            g5.a.a("onACBTWindTips,state:" + i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
            } else if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
            } else if (i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(1);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACDefrostRearPanelNotAvailableTips(int i8) {
            super.onACDefrostRearPanelNotAvailableTips(i8);
            g5.a.a("onACDefrostRearPanelNotAvailableTips,state:" + i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
            } else if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
            } else if (i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(12);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACKeepEngineRunTips(int i8) {
            super.onACKeepEngineRunTips(i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
                return;
            }
            if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
                return;
            }
            if (g5.c.g(VehicleService.this.getApplicationContext())) {
                g5.a.a("Car is EV ,  return");
                return;
            }
            boolean zB = g5.k.b(VehicleService.this.getApplicationContext(), "keep_engine_run", false);
            g5.a.a("onACKeepEngineRunTips,value:" + i8 + "  --- keepEngineRun:" + zB);
            if (!zB && i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(5);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACMaxEVModeTips(int i8) {
            super.onACMaxEVModeTips(i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
                return;
            }
            if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
                return;
            }
            if (g5.c.g(VehicleService.this.getApplicationContext())) {
                g5.a.a("Car is EV ,  return");
                return;
            }
            boolean zB = g5.k.b(VehicleService.this.getApplicationContext(), "max_ev_mode", false);
            g5.a.a("onACMaxEVModeTips,value:" + i8 + "  --- maxEvMode:" + zB);
            if (!zB && i8 == 1) {
                VehicleService.this.f3084k0.sendEmptyMessage(6);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onACPreheatTips(int i8) {
            super.onACPreheatTips(i8);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcCtrlModeChanged(int i8) {
            super.onAcCtrlModeChanged(i8);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcPtcPreheatSignalChanged(int i8) {
            super.onAcPtcPreheatSignalChanged(i8);
            g5.a.a("onAcPtcPreheatSignalChanged,value:" + i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
            } else if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
            } else if (i8 == 1) {
                VehicleService.this.f3084k0.sendEmptyMessage(0);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onHighTempAntivirusStateChanged(int i8) {
            super.onHighTempAntivirusStateChanged(i8);
            g5.a.a("onHighTempAntivirusStateChanged,state:" + i8);
            if (i8 == 1) {
                VehicleService.this.f3084k0.sendEmptyMessage(4);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onQuickCleanAirStateChanged(int i8) {
            super.onQuickCleanAirStateChanged(i8);
            g5.a.a("onQuickCleanAirStateChanged,state:" + i8 + " -- mQuickCleanFinishState:" + VehicleService.this.X);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
                return;
            }
            if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
                return;
            }
            if (VehicleService.this.X != i8 && i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(28);
            }
            VehicleService.this.X = i8;
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onQuickCleanTipChanged(int i8) {
            super.onQuickCleanTipChanged(i8);
            g5.a.a("onQuickCleanTipChanged,tip:" + i8);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
                return;
            }
            if (VehicleService.this.f3071e.getPowerLevel() == 0) {
                g5.a.a("powerlevel = BODYWORK_POWER_LEVEL_OFF,  return");
            } else if (i8 == 1) {
                VehicleService.this.f3084k0.sendEmptyMessage(3);
            } else if (i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(23);
            }
        }
    }

    public class v extends AbsBYDAutoVehicleHealthListener {
        public v() {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onDataChanged(b.c cVar) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onEPBStateChanged(int i8) {
            super.onEPBStateChanged(i8);
            boolean z7 = BYDAutoVehicleHealthDevice.getInstance(VehicleService.this.getApplicationContext()).getEPBConfig() == 1 && BYDAutoVehicleHealthDevice.getInstance(VehicleService.this.getApplicationContext()).getEPBType() == 0;
            g5.a.i("[EPB]----[onEPBStateChanged]----" + i8 + ", isEPBConfig = " + z7);
            if (z7) {
                if (i8 == 1) {
                    VehicleService.this.f3084k0.sendEmptyMessage(20);
                } else if (i8 == 0 && VehicleService.this.P != null && VehicleService.this.P.isShowing()) {
                    g5.m.e(VehicleService.this.getApplicationContext(), VehicleService.this.getResources().getString(R.string.carhealthy_epb_exit_success), 0);
                    VehicleService.this.P.dismiss();
                }
            }
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onError(int i8, String str) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onOilMaintainanceHintChanged(int i8, int i9) {
            super.onOilMaintainanceHintChanged(i8, i9);
            g5.a.i("onOilMaintainanceHintChanged,value:" + i9 + "  --- type:" + i8);
            VehicleService.this.f3084k0.removeMessages(27);
            VehicleService.this.f3084k0.sendEmptyMessageDelayed(27, 1000L);
            Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
            messageObtainMessage.arg1 = i8;
            messageObtainMessage.what = 24;
            VehicleService.this.f3084k0.removeMessages(24);
            VehicleService.this.f3084k0.sendMessageDelayed(messageObtainMessage, 1000L);
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onTransportModeStateHintChanged(int i8) {
            super.onTransportModeStateHintChanged(i8);
            if (i8 < 2 || i8 > 5) {
                return;
            }
            int transportMode = VehicleService.this.f3081j.getTransportMode();
            g5.a.j("onTransportModeStateHintChanged,state:" + i8 + " , transportMode:" + transportMode);
            boolean z7 = true;
            if (transportMode == 1 || transportMode == 2) {
                int i9 = 3;
                if (i8 == 2) {
                    i9 = 0;
                } else if (i8 == 3) {
                    i9 = 1;
                } else if (i8 == 4) {
                    i9 = 2;
                } else if (i8 != 5) {
                    i9 = -1;
                }
                if (i9 != -1) {
                    float totalMileageValue = BYDAutoStatisticDevice.getInstance(VehicleService.this.getApplicationContext()).getTotalMileageValue();
                    int mileageUnit = BYDAutoInstrumentDevice.getInstance(VehicleService.this.getApplicationContext()).getMileageUnit();
                    g5.a.j("totalMileageValue :" + totalMileageValue + ",mileUnit:" + mileageUnit);
                    boolean z8 = mileageUnit == 0 || mileageUnit == 1;
                    boolean z9 = mileageUnit == 2;
                    if ((!z8 || totalMileageValue >= 60.0f) && (!z9 || totalMileageValue >= 37.284d)) {
                        z7 = false;
                    }
                    if (!z7) {
                        g5.a.j("is not less than 60KM. ");
                        return;
                    }
                    Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
                    messageObtainMessage.arg1 = i9;
                    messageObtainMessage.what = 18;
                    VehicleService.this.f3084k0.sendMessage(messageObtainMessage);
                }
            }
        }
    }

    public class w extends AbsBYDAutoFragranceListener {
        public w() {
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceLessAllowance(int i8, int i9) {
            super.onFragranceLessAllowance(i8, i9);
            g5.a.a("onFragranceLessAllowance,type:" + i8 + " -- value=" + i9);
            if (!g5.b.a(VehicleService.this.getApplicationContext())) {
                g5.a.a("SafetyWarn not Finish  ,  return");
                return;
            }
            int supportFragrance = a.a.a(VehicleService.this.getApplicationContext()).getSupportFragrance();
            g5.a.a("onFragranceLessAllowance,value:" + i9 + " -- supportFragrance=" + supportFragrance);
            if (supportFragrance == 1 && i9 == 1) {
                VehicleService.this.f3084k0.sendEmptyMessage(8);
            }
        }
    }

    public class x extends AbsBYDAutoSettingListener {
        public x() {
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onDragModeForwardAndBackwardChanged(int i8) {
            super.onDragModeForwardAndBackwardChanged(i8);
            if (i8 != 1 || BYDAutoSettingDevice.getInstance(VehicleService.this.getApplicationContext()).getDragAndDropModeTipsState() == 1 || BYDAutoSpeedDevice.getInstance(VehicleService.this.getApplicationContext()).getSpeedValue() >= 3.0f || !g5.b.a(VehicleService.this.getApplicationContext())) {
                return;
            }
            g5.m.d(VehicleService.this.getApplicationContext(), VehicleService.this.getApplicationContext().getString(R.string.vehiclesettings_trailer_light_inspection_auto_signal_toast));
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onMassageLevelStateChanged(int i8, int i9) {
            super.onMassageLevelStateChanged(i8, i9);
            g5.a.i("onMassageLevelStateChanged,type:" + i8 + ",state:" + i9);
            if (g5.b.a(VehicleService.this.getApplicationContext())) {
                if (i8 == 1 || i8 == 2) {
                    Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
                    messageObtainMessage.arg1 = VehicleService.this.f3075g.getMassageLevel(i8);
                    messageObtainMessage.arg2 = i8;
                    messageObtainMessage.what = 13;
                    VehicleService.this.f3084k0.sendMessage(messageObtainMessage);
                }
            }
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onMassageModeStateChanged(int i8, int i9) {
            super.onMassageModeStateChanged(i8, i9);
            g5.a.i("onMassageModeStateChanged,type:" + i8 + ",state:" + i9);
            if (g5.b.a(VehicleService.this.getApplicationContext())) {
                if (i8 == 1 || i8 == 2) {
                    Message messageObtainMessage = VehicleService.this.f3084k0.obtainMessage();
                    messageObtainMessage.arg1 = VehicleService.this.f3075g.getMassageMode(i8);
                    messageObtainMessage.arg2 = i8;
                    messageObtainMessage.what = 11;
                    VehicleService.this.f3084k0.sendMessage(messageObtainMessage);
                }
            }
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onRefrigeratorState(int i8) {
            super.onRefrigeratorState(i8);
            g5.a.i("onRefrigeratorState,state:" + i8);
            if (g5.b.a(VehicleService.this.getApplicationContext())) {
                if (i8 == 5) {
                    VehicleService.this.f3084k0.sendEmptyMessage(10);
                } else if (i8 == 4) {
                    VehicleService.this.f3084k0.sendEmptyMessage(17);
                }
            }
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onScreenCallbackMessageChanged(int i8) {
            super.onScreenCallbackMessageChanged(i8);
            if (i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(33);
            } else if (i8 == 3) {
                VehicleService.this.f3084k0.sendEmptyMessage(34);
            }
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSeaModeChargingSocTypeChanged(int i8) {
            super.onSeaModeChargingSocTypeChanged(i8);
            g5.a.a("onSeaModeChargingSocTypeChanged,state:" + i8);
            if (i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(41);
            }
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSomethingLeftInRefrigeratorNotice(int i8) {
            super.onSomethingLeftInRefrigeratorNotice(i8);
            g5.a.i("onSomethingLeftInRefrigeratorNotice,value:" + i8);
            if (i8 == 1) {
                VehicleService.this.f3084k0.sendEmptyMessage(25);
            }
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onTrailerModeStateChanged(int i8) {
            super.onTrailerModeStateChanged(i8);
            g5.a.i("onTrailerModeStateChanged,state:" + i8);
            if (g5.b.a(VehicleService.this.getApplicationContext()) && i8 == 1 && BYDAutoBodyworkDevice.getInstance(VehicleService.this.getApplicationContext()).hasMessage(110) == 1 && BYDAutoSpeedDevice.getInstance(VehicleService.this.getApplicationContext()).getSpeedValue() < 3.0f && BYDAutoSettingDevice.getInstance(VehicleService.this.getApplicationContext()).getDragAndDropModeTipsState() != 1 && BYDAutoStudy.getInstance(VehicleService.this.getApplicationContext()).getTrailerModeConfig() == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(29);
                g5.m.d(VehicleService.this.getApplicationContext(), VehicleService.this.getApplicationContext().getString(R.string.vehiclesettings_trailer_light_inspection_auto_signal_toast));
            }
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onVoiceTipsMessageChanged(int i8) {
            super.onVoiceTipsMessageChanged(i8);
            g5.a.a("onVoiceTipsMessageChanged,value:" + i8);
            if (i8 == 2) {
                VehicleService.this.f3084k0.sendEmptyMessage(33);
                return;
            }
            if (i8 == 3) {
                VehicleService.this.f3084k0.sendEmptyMessage(34);
            } else if (i8 == 4) {
                VehicleService.this.f3084k0.sendEmptyMessage(37);
            } else if (i8 == 5) {
                VehicleService.this.f3084k0.sendEmptyMessage(38);
            }
        }
    }

    public class y extends AbsBYDAutoChargingListener {
        public y() {
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onBatteryManagementDeviceStateChanged(int i8) {
            super.onBatteryManagementDeviceStateChanged(i8);
            g5.a.i("onBatteryManagementDeviceStateChanged,state:" + i8);
            if (i8 == 12 || i8 == 14) {
                VehicleService.this.f3084k0.sendEmptyMessage(14);
            }
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingGunStateChanged(int i8) {
            super.onChargingGunStateChanged(i8);
            g5.a.i("onChargingGunStateChanged,state:" + i8);
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onDischargeToggleChanged(int i8) {
            super.onDischargeToggleChanged(i8);
            g5.a.i("onDischargeToggleChanged,state:" + i8);
            if ((VehicleService.this.U == 0 || VehicleService.this.U == 3) && (i8 == 1 || i8 == 2)) {
                VehicleService.this.f3084k0.sendEmptyMessage(9);
            }
            VehicleService.this.U = i8;
        }
    }

    public class z implements AbsBYDXCallListener {
        public z() {
        }

        @Override // android.hardware.bydauto.xcall.AbsBYDXCallListener
        public void onCallBackModeChanged(int i8) {
            g5.a.i("onCallBackModeChanged:" + i8);
        }

        @Override // android.hardware.bydauto.xcall.AbsBYDXCallListener
        public void onCallEvent(int i8) {
            g5.a.i("onCallEvent: " + i8);
        }

        @Override // android.hardware.bydauto.xcall.AbsBYDXCallListener
        public void onCallStatusChanged(int i8) {
            g5.a.i("onCallStatusChanged:" + i8);
            if (BYDAutoBodyworkDevice.getInstance(VehicleService.this.getApplicationContext()).getPowerLevel() == 0) {
                g5.a.i("it is in off gear mode , skip XCall update UI. ");
            } else {
                VehicleService.this.k0();
            }
        }
    }

    public final void W() {
        y4.a aVarN = y4.a.n(this);
        this.f3097r = aVarN;
        aVarN.o();
    }

    public final void X() {
        this.Z.removeMessages(TsNormalNum.THOUSAND_FIVE);
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = (statFs.getBlockCountLong() * blockSizeLong) / 1024;
        long availableBlocksLong = (blockSizeLong * statFs.getAvailableBlocksLong()) / 1024;
        float f8 = (availableBlocksLong * 1.0f) / blockCountLong;
        g5.a.i("totalSize: " + blockCountLong + ", availableSize: " + availableBlocksLong + ", availableRatio: " + f8);
        long j8 = availableBlocksLong >= 12582912 ? 43200000L : (availableBlocksLong >= 12582912 || availableBlocksLong < 5242880) ? (availableBlocksLong >= 5242880 || availableBlocksLong < 2097152) ? 120000L : 300000L : 1800000L;
        g5.a.i("nextCheckTime: " + j8);
        this.Z.sendEmptyMessageDelayed(TsNormalNum.THOUSAND_FIVE, j8);
        Message messageObtainMessage = this.f3084k0.obtainMessage();
        messageObtainMessage.what = 32;
        if (f8 < 0.15f && f8 >= 0.1f) {
            messageObtainMessage.arg1 = 0;
            this.f3084k0.sendMessage(messageObtainMessage);
            return;
        }
        if (f8 < 0.1f && f8 >= 0.05f) {
            messageObtainMessage.arg1 = 1;
            this.f3084k0.sendMessage(messageObtainMessage);
        } else if (f8 < 0.05f && f8 >= 0.025f) {
            messageObtainMessage.arg1 = 2;
            this.f3084k0.sendMessage(messageObtainMessage);
        } else if (f8 < 0.025f) {
            messageObtainMessage.arg1 = 3;
            this.f3084k0.sendMessage(messageObtainMessage);
        }
    }

    public final int Y() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this);
        this.f3103u = bYDAutoGearboxDevice;
        if (bYDAutoGearboxDevice == null) {
            return 0;
        }
        return SystemProperties.getInt("persist.vendor.byd.vehicle.type", 0) == 2 ? this.f3103u.getGearboxAutoModeLevel() : this.f3103u.getGear();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Z(int r3, int r4) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "submitHandleDMSTask type:"
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = ",state:"
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            g5.a.i(r0)
            r0 = 2
            r1 = 1
            if (r3 != 0) goto L37
            if (r4 == r1) goto L34
            if (r4 == r0) goto L31
            r3 = 3
            if (r4 == r3) goto L2e
            r3 = 4
            if (r4 == r3) goto L2b
            goto L49
        L2b:
            r3 = 143(0x8f, float:2.0E-43)
            goto L4a
        L2e:
            r3 = 142(0x8e, float:1.99E-43)
            goto L4a
        L31:
            r3 = 141(0x8d, float:1.98E-43)
            goto L4a
        L34:
            r3 = 140(0x8c, float:1.96E-43)
            goto L4a
        L37:
            if (r3 != r1) goto L44
            if (r4 == r1) goto L41
            if (r4 == r0) goto L3e
            goto L49
        L3e:
            r3 = 145(0x91, float:2.03E-43)
            goto L4a
        L41:
            r3 = 144(0x90, float:2.02E-43)
            goto L4a
        L44:
            if (r3 != r0) goto L49
            r3 = 146(0x92, float:2.05E-43)
            goto L4a
        L49:
            r3 = -1
        L4a:
            android.content.Context r4 = r2.getApplicationContext()
            y4.d r4 = y4.d.n(r4)
            r4.q(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.uereport.VehicleService.Z(int, int):void");
    }

    public final void a0() {
        registerReceiver(this.f3104u0, new IntentFilter("com.ts.bydapi.ACTION_UE_REPORT_RE_REGISTER"));
    }

    public final void b0() {
        registerReceiver(this.f3102t0, new IntentFilter("com.ts.carplay.ecall.ACTION_REQUEST_UPDATE"));
    }

    public final void c0() {
        this.f3076g0 = g5.g.a();
        this.f3078h0 = getResources().getConfiguration().uiMode & 48;
        this.f3080i0 = getResources().getConfiguration().orientation;
    }

    public final void d0() {
        registerReceiver(this.f3106v0, new IntentFilter("com.byd.uereport.xcall.test"));
    }

    public final void e0() {
        registerReceiver(this.f3100s0, new IntentFilter("android.intent.action.DEVICE_STORAGE_LOW"));
    }

    public final void f0() {
        g5.a.i("initVoicePlayer......");
        y4.d.n(getApplicationContext()).I(new h5.c(new h5.a(getApplicationContext())));
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ce, code lost:
    
        r1 = r4.f3084k0.obtainMessage();
        r1.arg1 = r0;
        r1.what = 24;
        r4.f3084k0.sendMessage(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g0() {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.uereport.VehicleService.g0():void");
    }

    public final void h0() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.action.MAX_PAIRED_DEVICE");
        registerReceiver(this.H0, intentFilter);
    }

    public final void i0() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction(SettingsCallDispatcher.ACTION_DISMISS_ALL_SETTINGS_DIALOG);
        registerReceiver(this.I0, intentFilter);
    }

    public final void j0() throws Throwable {
        int iB = g5.c.b(getApplicationContext());
        g5.a.c("send countryCode:" + iB);
        if (iB != -1) {
            BYDAutoTestDevice.getInstance(getApplicationContext()).sendDLinkCountryCode(iB);
        } else if (this.Y <= 5) {
            this.Z.sendEmptyMessageDelayed(5004, 10000L);
            this.Y++;
        }
    }

    public final void k0() {
        int callType = BYDAutoXCallDevice.getInstance(getApplicationContext()).getCallType();
        int callStatus = BYDAutoXCallDevice.getInstance(getApplicationContext()).getCallStatus();
        if (!(this.f3071e.hasMessage(212) == 1)) {
            g5.a.i("0x319 is not online");
            return;
        }
        g5.a.i("callType:" + callType + ", callState:" + callStatus);
        if (callType != 3 || callStatus == 0) {
            d5.a.z(this).Q();
            return;
        }
        Message messageObtainMessage = this.f3084k0.obtainMessage();
        messageObtainMessage.arg1 = callStatus;
        messageObtainMessage.what = 19;
        this.f3084k0.removeMessages(19);
        this.f3084k0.sendMessageDelayed(messageObtainMessage, 100L);
    }

    public final void l0(int i8) {
        GpackCarBean gpackCarBean = new GpackCarBean();
        if (i8 == -1) {
            gpackCarBean.gearMode = Y();
        } else {
            gpackCarBean.gearMode = i8;
        }
        gpackCarBean.virtualVin = this.f3071e.getAutoVIN();
        gpackCarBean.autoType = this.f3071e.getAutoType();
        gpackCarBean.countryCode = g5.c.c(getApplicationContext()) + "";
        gpackCarBean.platformCode = SystemProperties.get("ro.vehicle.type");
        String jSONString = c2.a.toJSONString(gpackCarBean);
        g5.a.a("shareToGpackGloabl:" + jSONString);
        Settings.Global.putString(getContentResolver(), "sys.share.gpack.agent.data", jSONString);
    }

    public final void m0(int i8) {
        if (g5.b.a(getApplicationContext())) {
            FreezerTipsDialog freezerTipsDialog = this.f3082j0;
            if (freezerTipsDialog != null && freezerTipsDialog.isShowing()) {
                this.f3082j0.dismiss();
            }
            if (this.f3082j0 == null) {
                this.f3082j0 = new FreezerTipsDialog(getApplicationContext());
            }
            this.f3082j0.show();
            this.f3082j0.c(i8);
        }
    }

    public void n0(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        g5.a.i("unpairDevice result:" + ((Boolean) m4.a.b(bluetoothDevice, "removeBond")).booleanValue());
    }

    public final void o0() {
        CarPlayManager.getInstance(getApplicationContext()).setScreenOrientation(getApplicationContext().getResources().getConfiguration().orientation);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f3072e0;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        g5.a.i("VehicleService,onConfigurationChanged:" + configuration);
        int i8 = configuration.uiMode & 48;
        int i9 = configuration.orientation;
        String strA = g5.g.a();
        if (!this.f3076g0.equals(strA)) {
            d5.a.z(getApplicationContext()).C(0);
            this.f3084k0.sendEmptyMessage(26);
            this.f3084k0.sendEmptyMessage(27);
            this.f3076g0 = strA;
            OverhaulEPBDialog overhaulEPBDialog = this.P;
            if (overhaulEPBDialog != null && overhaulEPBDialog.isShowing()) {
                g5.a.i("[EPB]----[onConfigurationChanged]----mCurrentLang:" + this.f3076g0);
                this.P.dismiss();
                this.P = null;
                this.f3084k0.sendEmptyMessage(20);
            }
        }
        if (this.f3078h0 != i8) {
            startService(new Intent(getApplicationContext(), (Class<?>) VehicleService.class).putExtra("uiMode", i8));
            d5.a.z(getApplicationContext()).C(2);
            g0();
            this.f3078h0 = i8;
        }
        if (this.f3080i0 != i9) {
            this.Z.sendEmptyMessageDelayed(5003, 100L);
            d5.a.z(getApplicationContext()).C(1);
            k3.b.m(getApplicationContext()).p();
            int i10 = this.f3080i0;
            if (i10 == 1 || i10 == 2) {
                c5.b bVar = this.S;
                if (bVar != null && bVar.g()) {
                    this.S.c();
                    this.S = null;
                    this.f3084k0.sendEmptyMessage(4);
                }
                DialogCarTrailer dialogCarTrailer = this.T;
                if (dialogCarTrailer != null && dialogCarTrailer.isShowing()) {
                    this.T.dismiss();
                    this.T = null;
                    if (g5.b.a(getApplicationContext())) {
                        this.f3084k0.sendEmptyMessage(29);
                    }
                }
            }
            this.f3080i0 = i9;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        r4.a.d(this);
        super.onCreate();
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        this.f3071e = bYDAutoBodyworkDevice;
        bYDAutoBodyworkDevice.registerListener(this.f3086l0);
        BYDAutoAcDevice bYDAutoAcDevice = BYDAutoAcDevice.getInstance(this);
        this.f3069d = bYDAutoAcDevice;
        bYDAutoAcDevice.registerListener(this.f3088m0);
        BYDAutoFragranceDevice bYDAutoFragranceDevice = BYDAutoFragranceDevice.getInstance(this);
        this.f3073f = bYDAutoFragranceDevice;
        bYDAutoFragranceDevice.registerListener(this.f3092o0);
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this);
        this.f3075g = bYDAutoSettingDevice;
        bYDAutoSettingDevice.registerListener(this.f3094p0);
        BYDAutoChargingDevice bYDAutoChargingDevice = BYDAutoChargingDevice.getInstance(this);
        this.f3077h = bYDAutoChargingDevice;
        bYDAutoChargingDevice.registerListener(this.f3096q0);
        BYDAutoXCallDevice bYDAutoXCallDevice = BYDAutoXCallDevice.getInstance(this);
        this.f3079i = bYDAutoXCallDevice;
        bYDAutoXCallDevice.registerListener(this.f3098r0);
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = BYDAutoVehicleHealthDevice.getInstance(this);
        this.f3081j = bYDAutoVehicleHealthDevice;
        bYDAutoVehicleHealthDevice.registerListener(this.f3090n0);
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(this);
        this.f3083k = bYDAutoInstrumentDevice;
        bYDAutoInstrumentDevice.registerListener(this.f3108w0);
        BYDAutoSpeedDevice bYDAutoSpeedDevice = BYDAutoSpeedDevice.getInstance(this);
        this.f3074f0 = bYDAutoSpeedDevice;
        bYDAutoSpeedDevice.registerListener(this.f3110x0);
        BYDAutoShowCarDevice bYDAutoShowCarDevice = BYDAutoShowCarDevice.getInstance(this);
        this.f3085l = bYDAutoShowCarDevice;
        bYDAutoShowCarDevice.registerListener(this.f3112y0);
        BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(this);
        this.f3091o = bYDAutoPowerDevice;
        bYDAutoPowerDevice.registerListener(this.B0);
        BYDAutoReminderDevice bYDAutoReminderDevice = BYDAutoReminderDevice.getInstance(this);
        this.f3087m = bYDAutoReminderDevice;
        bYDAutoReminderDevice.registerListener(this.f3114z0);
        BYDAutoDmsDevice bYDAutoDmsDevice = BYDAutoDmsDevice.getInstance(this);
        this.f3089n = bYDAutoDmsDevice;
        bYDAutoDmsDevice.registerListener(this.A0);
        BYDAutoDoorLockDevice bYDAutoDoorLockDevice = BYDAutoDoorLockDevice.getInstance(this);
        this.f3093p = bYDAutoDoorLockDevice;
        bYDAutoDoorLockDevice.registerListener(this.C0);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = BYDAutoDiPilotDevice.getInstance(this);
        this.f3095q = bYDAutoDiPilotDevice;
        bYDAutoDiPilotDevice.registerListener(this.D0);
        this.f3101t = BYDAutoStudy.getInstance(this);
        BYDAutoEnergyDevice bYDAutoEnergyDevice = BYDAutoEnergyDevice.getInstance(this);
        this.f3099s = bYDAutoEnergyDevice;
        bYDAutoEnergyDevice.registerListener(this.E0);
        BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this);
        this.f3103u = bYDAutoGearboxDevice;
        bYDAutoGearboxDevice.registerListener(this.F0);
        w2.a.p().r(getApplicationContext(), "com.byd.bluetoothcall");
        g5.j.a(getApplicationContext());
        c0();
        f0();
        d0();
        a0();
        b0();
        e0();
        HandlerThread handlerThread = new HandlerThread("UeReport_Task");
        handlerThread.start();
        a0 a0Var = new a0(handlerThread.getLooper());
        this.Z = a0Var;
        a0Var.sendEmptyMessageDelayed(TsNormalNum.THOUSAND_FIVE, 30000L);
        this.Z.sendEmptyMessageDelayed(5002, 3000L);
        this.Z.sendEmptyMessageDelayed(5001, z3.e.INTERVAL);
        this.Z.sendEmptyMessageDelayed(5004, 10000L);
        h0();
        i0();
        l0(-1);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f3069d.unregisterListener(this.f3088m0);
        this.f3073f.unregisterListener(this.f3092o0);
        this.f3075g.unregisterListener(this.f3094p0);
        this.f3077h.unregisterListener(this.f3096q0);
        this.f3079i.unregisterListener(this.f3098r0);
        this.f3081j.unregisterListener(this.f3090n0);
        this.f3087m.unregisterListener(this.f3114z0);
        this.f3089n.unregisterListener(this.A0);
        this.f3091o.unregisterListener(this.B0);
        this.f3093p.unregisterListener(this.C0);
        this.f3095q.unregisterListener(this.D0);
        this.f3099s.unregisterListener(this.E0);
        this.f3103u.unregisterListener(this.F0);
        this.f3071e.unregisterListener(this.f3086l0);
        this.f3084k0.removeCallbacksAndMessages(null);
        this.f3084k0 = null;
        unregisterReceiver(this.f3106v0);
        unregisterReceiver(this.f3104u0);
        unregisterReceiver(this.f3102t0);
        unregisterReceiver(this.f3100s0);
        unregisterReceiver(this.H0);
        unregisterReceiver(this.I0);
        c.a.a().g(getApplicationContext());
        y4.d.n(getApplicationContext()).l();
        this.Z.removeCallbacksAndMessages(null);
        this.f3097r.v();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i8, int i9) {
        int intExtra;
        if (intent != null && (intExtra = intent.getIntExtra("uiShow", -1)) > 0) {
            this.f3084k0.sendEmptyMessage(intExtra);
        }
        return super.onStartCommand(intent, i8, i9);
    }
}
