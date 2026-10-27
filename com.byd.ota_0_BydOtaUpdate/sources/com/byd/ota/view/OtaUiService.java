package com.byd.ota.view;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import c.a.d.b;
import c.a.d.i.c1.p;
import c.a.d.i.c1.q;
import c.a.d.i.c1.r;
import c.a.d.i.c1.s;
import c.a.d.i.c1.t;
import c.a.d.i.c1.u;
import c.a.d.i.c1.v;
import c.a.d.i.c1.w;
import c.a.d.i.e1.c;
import com.byd.common.constants.ResultCodeConsts;
import com.byd.ota.R;
import com.byd.ota.broadcast.AppointmentBroadcastReceiver;
import com.byd.ota.broadcast.SystemChangeBroadcastReceiver;
import com.byd.ota.master.VehicleVersionInfo;
import com.byd.ota.view.OtaUiService;
import com.byd.repository.bean.Description;
import com.byd.repository.bean.DiagnoseEcuInfo;
import com.byd.repository.bean.EcuVersionInfo;
import com.byd.repository.bean.UpdateResultInfo;
import com.byd.repository.bean.request.RequestSubmitReportInfo;
import com.byd.repository.bean.response.ResponseCheckVersionInfo;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class OtaUiService extends Service implements c.a.d.a, c.a.a.b, c.a.g.d {
    public static PendingIntent I3;
    public BYDAutoVersionDevice A4;
    public BYDAutoBodyworkDevice B4;
    public BYDAutoStudy C4;
    public OtaUpgradeManager D4;
    public c.a.d.e.i E4;
    public c.a.d.e.j F4;
    public c.a.d.e.k G4;
    public c.a.d.g.c H4;
    public c.a.d.g.a I4;
    public String J3;
    public c.a.j.e J4;
    public int K3;
    public o K4;
    public int L3;
    public n L4;
    public int M3;
    public int N3;
    public int O3;
    public int P3;
    public long Q3;
    public int R3;
    public String S3;
    public c.a.a.d T3;
    public c.a.a.c U3;
    public r V3;
    public p W3;
    public t X3;
    public w Y3;
    public s Z3;
    public u a4;
    public v b4;
    public q c4;
    public r d4;
    public c.a.d.i.e1.c e4;
    public Timer g4;
    public Timer h4;
    public Context i4;
    public c.a.b.c.a j4;
    public Handler k4;
    public HandlerThread l4;
    public c.a.a.a m4;
    public c.a.g.c n4;
    public c.a.d.f.k o4;
    public c.a.d.f.h p4;
    public c.a.d.f.e q4;
    public c.a.d.f.f r4;
    public c.a.d.f.l s4;
    public c.a.d.f.j t4;
    public c.a.d.f.i u4;
    public AppointmentBroadcastReceiver v4;
    public SystemChangeBroadcastReceiver w4;
    public c.a.d.f.g x4;
    public BYDAutoOtaDevice y4;
    public BYDAutoPowerDevice z4;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3075c = OtaUiService.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile int f3076d = 0;
    public static volatile int s = 0;
    public static volatile int x = 0;
    public static volatile boolean y = false;
    public static volatile boolean E3 = false;
    public static volatile boolean F3 = false;
    public static volatile boolean G3 = false;
    public static volatile boolean H3 = false;
    public Object f4 = new Object();
    public int M4 = 10;
    public final int N4 = 2;

    public class a extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ long f3077a;

        public a(long j) {
            this.f3077a = j;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAppointmentTime(int i2) {
            super.onAppointmentTime(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onAppointmentTime->updateTime = " + c.a.b.d.n.d("yyyy-MM-dd HH:mm:ss", this.f3077a) + ", resultCode = " + i2);
        }
    }

    public class b extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ long f3079a;

        public b(long j) {
            this.f3079a = j;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onAppointmentTime(int i2) {
            super.onAppointmentTime(i2);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onAppointmentTime->updateTime = " + c.a.b.d.n.d("yyyy-MM-dd HH:mm:ss", this.f3079a) + ", resultCode = " + i2);
        }
    }

    public class c extends TimerTask {
        public c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.a.b.d.e.c(OtaUiService.f3075c, "quitOtaModel()");
            if (OtaUiService.this.m4 != null) {
                OtaUiService.this.m4.f();
                OtaUiService.this.m4.c(0);
            }
            if (OtaUiService.this.b4 != null && OtaUiService.this.b4.isShowing()) {
                OtaUiService.this.b4.dismissDialog();
            }
            if (OtaUiService.this.c4 != null && OtaUiService.this.c4.isShowing()) {
                OtaUiService.this.c4.dismissDialog();
            }
            if (OtaUiService.this.g4 != null) {
                OtaUiService.this.g4.cancel();
                OtaUiService.this.g4 = null;
            }
        }
    }

    public class d implements c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3082a;

        public d(int i2) {
            this.f3082a = i2;
        }

        @Override // c.a.d.i.e1.c.b
        public void a() throws Throwable {
            if (c.a.g.l.l.g() || c.a.g.l.l.e() || c.a.g.l.l.f()) {
                c.a.g.l.l.k(false);
                c.a.g.l.l.m(false);
                c.a.g.l.l.l(false);
                OtaUiService.this.e4.j("当前循环次数：" + this.f3082a + "\n已停止循环");
            }
        }

        @Override // c.a.d.i.e1.c.b
        public void b() {
            OtaUiService.this.e4.c();
        }
    }

    public class e extends Handler {
        public e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 != 2) {
                    return;
                }
                OtaUiService.this.c0();
            } else {
                synchronized (OtaUiService.this.f4) {
                    OtaUiService.this.d0();
                    OtaUiService.this.f4.notifyAll();
                }
            }
        }
    }

    public class f extends AbsBYDAutoBodyworkListener {
        public f() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener, a.b.d
        public void isConnected(boolean z) {
            c.a.b.d.e.c(AbsBYDAutoBodyworkListener.TAG, "isBYDAutoBodyworkConnected = " + z);
            if (z) {
                OtaUiService.this.k4.sendEmptyMessage(2);
            }
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i2) {
            super.onPowerLevelChanged(i2);
            c.a.b.d.e.c(AbsBYDAutoBodyworkListener.TAG, "onPowerLevelChanged  level = " + i2);
            if (i2 == 2) {
                if (OtaUiService.this.b4 != null && OtaUiService.this.b4.isShowing()) {
                    OtaUiService.this.b4.dismissDialog();
                }
                if (OtaUiService.this.c4 == null || !OtaUiService.this.c4.isShowing()) {
                    return;
                }
                OtaUiService.this.c4.dismissDialog();
            }
        }
    }

    public class g extends TimerTask {
        public g() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.a.b.d.e.c(OtaUiService.f3075c, "isMediaProgressChange = " + OtaUiService.F3);
            if (OtaUiService.F3) {
                boolean unused = OtaUiService.F3 = false;
                return;
            }
            OtaUiService otaUiService = OtaUiService.this;
            otaUiService.D4 = OtaUpgradeManager.getInstance(otaUiService.i4);
            OtaUiService.this.e1();
        }
    }

    public class h extends TimerTask {
        public h() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.a.b.d.e.c(OtaUiService.f3075c, "isEcuRemainTimeChange = " + OtaUiService.G3);
            if (OtaUiService.G3) {
                boolean unused = OtaUiService.G3 = false;
                return;
            }
            OtaUiService otaUiService = OtaUiService.this;
            otaUiService.D4 = OtaUpgradeManager.getInstance(otaUiService.i4);
            OtaUiService.this.e1();
        }
    }

    public class i implements p.a {
        public i() {
        }

        @Override // c.a.d.i.c1.p.a
        public void a() {
            c.a.d.h.d.a(OtaUiService.this.i4, OtaUiService.this.i4.getString(R.string.toast_cancel_appointment_update), 0);
            OtaUiService.this.i(0L);
            OtaUiService.this.n4.c().j(1);
        }

        @Override // c.a.d.i.c1.p.a
        public void b() {
        }
    }

    public class j implements t.a {
        public j() {
        }

        @Override // c.a.d.i.c1.t.a
        public void a() {
            if (OtaUiService.this.m4.k()) {
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
            OtaUiService.this.o2(4);
        }

        @Override // c.a.d.i.c1.t.a
        public void b() {
            if (OtaUiService.this.m4.k()) {
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
            OtaUiService.this.O0();
        }

        @Override // c.a.d.i.c1.t.a
        public void c() {
            OtaUiService.this.m4.m(true, "updateInfoDialog onImmediately");
            if (OtaUiService.this.m4.k()) {
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
            OtaUiService.this.S0();
        }

        @Override // c.a.d.i.c1.t.a
        public void d() {
            if (OtaUiService.this.m4.k()) {
                OtaUiService.this.m4.m(false, "showUpdateInfoDialog onNotNow");
                OtaUiService.this.E("showUpdateInfoDialog onNotNow");
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
        }
    }

    public class k implements u.b {
        public k() {
        }

        @Override // c.a.d.i.c1.u.b
        public void a(long j) {
            OtaUiService.this.i(j);
            OtaUiService.this.n4.c().i(0, c.a.b.d.n.d("yyyy-MM-dd HH:mm", j));
            if (OtaUiService.this.m4.k()) {
                OtaUiService.this.E("showUpdateTimePickerDialog onConfirm");
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
        }

        @Override // c.a.d.i.c1.u.b
        public void b() {
            if (OtaUiService.this.m4.k()) {
                OtaUiService.this.E("showUpdateTimePickerDialog onConfirm");
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
        }
    }

    public class l implements p.a {
        public l() {
        }

        @Override // c.a.d.i.c1.p.a
        public void a() {
            if (OtaUiService.this.m4.k()) {
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
            if (OtaUiService.this.m4.k()) {
                OtaUiService.this.m4.e(2);
            } else {
                OtaUiService.this.m4.e(1);
            }
        }

        @Override // c.a.d.i.c1.p.a
        public void b() {
            if (OtaUiService.this.m4.k()) {
                OtaUiService.this.E("showUpdateConfirmDialog onCancel");
                c.a.b.d.l.d().i(OtaUiService.this.K4);
            }
            if (OtaUiService.this.j4.s() >= 10) {
                OtaUiService.this.m4.m(false, "showUpdateConfirmDialog onCancel");
            }
        }
    }

    public class m implements s.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c.a.a.d f3092a;

        public m(c.a.a.d dVar) {
            this.f3092a = dVar;
        }

        @Override // c.a.d.i.c1.s.d
        public void a() {
            this.f3092a.a();
            OtaUiService.this.n4.c().k(OtaUiService.this.j4.A(), false);
        }

        @Override // c.a.d.i.c1.s.d
        public void b() {
            this.f3092a.b();
            OtaUiService.this.n4.c().k(OtaUiService.this.j4.A(), false);
        }

        @Override // c.a.d.i.c1.s.d
        public void c() {
            if (OtaUiService.this.m4.k()) {
                OtaUiService.this.E("showCountDownDialog onActionUpdateCancel");
            }
            if (OtaUiService.this.j4.s() >= 10) {
                OtaUiService.this.m4.m(false, "showCountDownDialog onActionUpdateCancel");
            }
            c.a.g.l.r.b();
            this.f3092a.c();
            OtaUiService.this.n4.c().j(0);
        }
    }

    public class n implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c.a.d.i.c1.n f3094c;

        public n(c.a.d.i.c1.n nVar) {
            this.f3094c = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OtaUiService.this.V3 == null || !OtaUiService.this.V3.isShowing()) {
                return;
            }
            OtaUiService.this.V3.dismissDialog();
        }
    }

    public class o implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c.a.d.i.c1.n f3096c;

        public o(c.a.d.i.c1.n nVar) {
            if (OtaUiService.this.m4.k()) {
                nVar.g(false);
                if (!OtaUiService.this.m4.b()) {
                    c.a.b.d.e.c(OtaUiService.f3075c, "ScreenOffRunnable isProcessingOta = " + OtaUiService.this.m4.b());
                    OtaUiService.this.m4.m(false, "ScreenOffRunnable time out");
                }
            }
            this.f3096c = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OtaUiService.this.m4.k()) {
                c.a.d.i.c1.n nVar = this.f3096c;
                if (nVar != null && nVar.isShowing()) {
                    this.f3096c.dismissDialog();
                }
                OtaUiService.this.E("ScreenOffRunnable run");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B0() {
        r rVar = this.V3;
        if (rVar != null && rVar.isShowing()) {
            this.V3.dismissDialog();
            this.V3 = null;
        }
        K0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B1() {
        o2(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: C1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void D1() {
        c.a.b.d.l.d().i(this.L4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: E1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void F1() {
        this.j4.R(-1);
        E("showCycleTestFailDialog setActionClick");
        c.a.b.d.l.d().i(this.K4);
    }

    public static /* synthetic */ void G1() {
        c.a.g.l.o.a();
        c.a.b.d.d.g(c.a.b.b.a.f2322c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: H1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void I1(ResultCodeConsts.NormalCodeEnum normalCodeEnum) {
        c.a.g.l.r.b();
        C(true);
        E3 = false;
        this.n4.b(normalCodeEnum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: I0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void J0() {
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.dismissDialog();
        }
        Intent intent = new Intent();
        intent.setClassName("com.byd.ota", "com.byd.ota.view.OtaMasterActivity");
        intent.setFlags(268435456);
        this.i4.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: J1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void K1() {
        o2(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: L1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void M1() {
        r rVar = this.V3;
        if (rVar != null && rVar.isShowing()) {
            this.V3.dismissDialog();
        }
        if (c.a.g.l.l.g()) {
            this.m4.h(c.a.g.l.l.f());
            this.m4.e(6);
        } else if (c.a.g.l.l.e()) {
            this.m4.h(c.a.g.l.l.f());
            this.m4.e(21);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: M0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void N0() {
        o2(this.R3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: N1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void O1() {
        r rVar = this.V3;
        if (rVar != null && rVar.isShowing()) {
            this.V3.dismissDialog();
        }
        if (c.a.g.l.l.g()) {
            this.m4.h(c.a.g.l.l.f());
            this.m4.e(6);
        } else if (c.a.g.l.l.e()) {
            this.m4.h(c.a.g.l.l.f());
            this.m4.e(21);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: P1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Q1() {
        this.j4.R(-1);
        E("otaCycleTest showNormalCodeDialog setActionClick");
        c.a.b.d.l.d().i(this.K4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: R1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void S1() {
        c.a.g.l.i.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U1() {
        this.j4.R(-1);
        E("otaCycleTest showNormalCodeDialog setActionClick");
        c.a.b.d.l.d().i(this.K4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: U0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void V0() {
        C0(this.T3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W1() {
        c.a.g.l.i.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: X1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Y1() {
        this.j4.R(-1);
        E("showNormalCodeDialog setActionClick");
        c.a.b.d.l.d().i(this.K4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Y0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Z0() throws Throwable {
        s(this.P3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Z1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a2(int i2) throws Throwable {
        c.a.b.d.e.c(f3075c, "showVersionDetailDialog->triggerDialogType = " + i2);
        if (i2 == 1) {
            if (this.m4.k()) {
                this.j4.R(-1);
                c.a.b.d.l.d().i(this.K4);
            }
            s(2000);
            return;
        }
        if (i2 != 4) {
            return;
        }
        if (this.m4.k()) {
            this.j4.R(-1);
            c.a.b.d.l.d().i(this.K4);
        }
        K0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d1() {
        w(this.Q3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h0() {
        c.a.b.d.e.b(f3075c, "onServiceCrash");
        c.a.d.h.c.a();
        this.n4.r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h1() {
        j2(this.K3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j1() {
        i2(this.K3, (this.L3 / 60) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l0() {
        i2(this.K3, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l1() {
        G0(this.i4.getString(R.string.rebooting_system));
        e1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n0(int i2) {
        q qVar = this.c4;
        if (qVar != null && qVar.isShowing()) {
            this.c4.dismissDialog();
        }
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.dismissDialog();
        }
        String strA = c.a.b.d.g.a(this.i4, i2);
        r rVar = this.d4;
        if (rVar != null && rVar.isShowing()) {
            this.d4.r(strA);
            return;
        }
        r rVar2 = new r(this.i4);
        this.d4 = rVar2;
        rVar2.r(strA);
        this.d4.h(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n1() {
        i2(this.K3, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void p0() {
        i2(this.K3, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void r0() {
        G0(this.i4.getString(R.string.rebooting_system));
        e1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void r1() throws Throwable {
        o1(ResultCodeConsts.NormalCodeEnum.LARGER_VERSION_UPDATE_FAILURE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void t0() {
        j2(this.K3);
    }

    public static /* synthetic */ void s1() {
        if (c.a.b.d.h.d() || c.a.g.l.l.f()) {
            return;
        }
        c.a.g.l.o.a();
        c.a.b.d.d.g(c.a.b.b.a.f2322c);
        c.a.g.l.f.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: t1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void u1() {
        this.n4.h("otadiagnose");
        this.n4.h("otaupgrade");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void v0() {
        i2(this.K3, (this.L3 / 60) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void w1() throws Throwable {
        o1(ResultCodeConsts.NormalCodeEnum.LARGER_VERSION_UPDATE_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: w0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void x0() {
        i2(this.K3, (this.L3 / 60) + 1);
    }

    public static /* synthetic */ void x1() {
        if (c.a.b.d.h.d() || c.a.g.l.l.f()) {
            return;
        }
        c.a.g.l.o.a();
        c.a.b.d.d.g(c.a.b.b.a.f2322c);
        c.a.g.l.f.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void z0() throws Throwable {
        c.a.d.i.e1.c cVar = this.e4;
        if (cVar != null) {
            cVar.c();
            this.e4 = null;
        }
        c.a.d.i.e1.c cVarD = c.a.d.i.e1.c.d(this.i4);
        this.e4 = cVarD;
        cVarD.l();
        this.e4.k(c.a.g.l.l.g() ? "实车循环测试" : c.a.g.l.l.e() ? "台架循环测试" : "");
        int iA = c.a.g.l.l.a() + 1;
        c.a.g.l.l.j(iA);
        this.e4.j("当前循环次数：" + iA);
        this.e4.i(new d(iA));
        r rVar = this.d4;
        if (rVar == null || !rVar.isShowing()) {
            return;
        }
        this.d4.dismissDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void z1() {
        if (this.m4.k()) {
            c.a.b.d.l.d().i(this.K4);
        }
        Q0();
    }

    @Override // c.a.g.d
    public void A(EcuVersionInfo ecuVersionInfo, int i2, int i3, long j2, long j3) {
        c.a.d.h.a.e(this.i4).f(i2, i3, j2, j3);
    }

    @Override // c.a.a.b
    public void B(c.a.a.c cVar) throws Throwable {
        this.U3 = cVar;
        f(false);
        this.m4.m(false, "onDownloadFactoryPackage");
        c.a.g.l.o.a();
        c.a.b.d.d.g(c.a.b.b.a.f2322c);
        if (this.n4.n()) {
            return;
        }
        this.n4.i(true);
        this.n4.d();
    }

    @Override // c.a.d.a
    public void C(boolean z) {
        y = z;
    }

    @Override // c.a.a.b
    public void D(final ResultCodeConsts.NormalCodeEnum normalCodeEnum) {
        if (!this.m4.g()) {
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.n
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    this.f2589c.F0(normalCodeEnum);
                }
            });
        } else {
            this.j4.V(normalCodeEnum.normalCode);
            e1();
        }
    }

    @Override // c.a.d.a
    public void E(String str) {
        if (this.m4.k()) {
            int i2 = Settings.System.getInt(getContentResolver(), "byd_cpd_window_event", 0);
            String str2 = f3075c;
            c.a.b.d.e.a(str2, "setScreenOff->tag = " + str + ",type =" + i2);
            if (i2 == 0) {
                this.y4.setBackLightState(2);
            } else {
                c.a.b.d.e.a(str2, "cpd show");
            }
        }
        if (E3) {
            E3 = false;
            this.m4.c(0);
        }
    }

    @Override // c.a.a.b
    public void F(final String str) {
        c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.n0
            @Override // java.lang.Runnable
            public final void run() {
                this.f2591c.H0(str);
            }
        });
    }

    @Override // c.a.g.d
    public void a() {
        c.a.b.d.e.c(f3075c, "onClearDtcFinish");
        if (this.J3.equals("ecu")) {
            this.M3 = 0;
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.d0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2541c.l0();
                }
            });
        }
        G3 = true;
        this.n4.u();
    }

    @Override // c.a.g.d
    public void b() {
        c.a.b.d.e.c(f3075c, "onSubmitReportFail");
        C(false);
        if (c.a.g.l.l.d()) {
            return;
        }
        if (!this.m4.k()) {
            this.m4.c(0);
        } else if (b0() == 2) {
            this.m4.c(0);
        } else {
            E3 = true;
        }
    }

    public int b0() {
        int backLightState = this.y4.getBackLightState();
        c.a.b.d.e.c(f3075c, "getBackLightState->backLightState = " + backLightState);
        return backLightState;
    }

    public final void b2(Configuration configuration) {
        int i2 = this.O3;
        if (i2 != 19 && i2 != 35) {
            this.O3 = configuration.uiMode;
        }
        this.O3 = configuration.uiMode;
        if (this.m4.k()) {
            return;
        }
        c.a.b.d.e.c(f3075c, "curDialogType = " + f3076d);
        switch (f3076d) {
            case 1:
                r rVar = this.V3;
                if (rVar != null && rVar.isShowing()) {
                    this.V3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.m0
                        @Override // java.lang.Runnable
                        public final void run() throws Throwable {
                            this.f2588c.Z0();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 2:
                r rVar2 = this.V3;
                if (rVar2 != null && rVar2.isShowing()) {
                    this.V3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.b0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2511c.b1();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 3:
                p pVar = this.W3;
                if (pVar != null && pVar.isShowing()) {
                    this.W3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.u
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2607c.d1();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 4:
                t tVar = this.X3;
                if (tVar != null && tVar.isShowing()) {
                    this.X3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.a0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2508c.L0();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 5:
                w wVar = this.Y3;
                if (wVar != null && wVar.isShowing()) {
                    this.Y3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.i
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2579c.N0();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 6:
                r rVar3 = this.V3;
                if (rVar3 != null && rVar3.isShowing()) {
                    this.V3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.g0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2575c.P0();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 7:
                u uVar = this.a4;
                if (uVar != null && uVar.isShowing()) {
                    this.a4.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.e0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2557c.R0();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 8:
                p pVar2 = this.W3;
                if (pVar2 != null && pVar2.isShowing()) {
                    this.W3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.s0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2604c.T0();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 9:
                s sVar = this.Z3;
                if (sVar != null && sVar.isShowing()) {
                    this.Z3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.f
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2571c.V0();
                        }
                    }, 100L);
                    break;
                }
                break;
            case 10:
                r rVar4 = this.V3;
                if (rVar4 != null && rVar4.isShowing()) {
                    this.V3.dismissDialog();
                    c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.q0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2599c.X0();
                        }
                    }, 100L);
                    break;
                }
                break;
        }
    }

    @Override // c.a.g.d
    public void c(String str) {
        c.a.d.h.a.e(this.i4).a();
    }

    public final void c0() throws Throwable {
        String str = f3075c;
        c.a.b.d.e.c(str, "initBodyWorkService start...");
        e0();
        f0();
        c.a.d.g.a aVar = new c.a.d.g.a(this.n4, this.m4, this, this);
        this.I4 = aVar;
        this.y4.setCloudServiceListener(aVar);
        this.v4 = new AppointmentBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("vendor.intent.action.OTA_APPOINTMENT_BROADCAST");
        this.i4.registerReceiver(this.v4, intentFilter);
        this.q4.a();
        SystemChangeBroadcastReceiver systemChangeBroadcastReceiver = new SystemChangeBroadcastReceiver();
        this.w4 = systemChangeBroadcastReceiver;
        systemChangeBroadcastReceiver.a(this.n4);
        this.i4.registerReceiver(this.w4, new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
        c.a.d.g.c cVar = new c.a.d.g.c(this.t4, this.u4);
        this.H4 = cVar;
        this.B4.registerListener((AbsBYDAutoBodyworkListener) cVar);
        c.a.b.d.e.c(str, "carTypeStr = " + c.a.b.d.b.a(this.B4.getAutoType()));
        c.a.b.d.e.c(str, "energyType = " + this.B4.getEnergyType());
        c.a.d.f.g gVar = new c.a.d.f.g(this.i4);
        this.x4 = gVar;
        gVar.c();
        this.x4.b(new c.a.d.g.b(this.m4, this));
        this.n4.c().f(2);
        c.a.b.d.h.f(VehicleVersionInfo.FORCE_UPGRADE);
        c.a.b.d.e.c(str, "initBodyWorkService end...");
    }

    public final void c2(final ResultCodeConsts.NormalCodeEnum normalCodeEnum) {
        int i2;
        c.a.b.d.e.c(f3075c, "processQnxNormalCode->qnxNormalCode = " + normalCodeEnum.normalCode);
        this.m4.d();
        this.t4.h(true);
        this.u4.b(true);
        RequestSubmitReportInfo requestSubmitReportInfoA = c.a.g.l.p.a();
        if (requestSubmitReportInfoA != null && (i2 = requestSubmitReportInfoA.updateMode) != 3 && i2 != 4 && i2 != 5 && this.m4.k()) {
            this.m4.m(true, "processQnxNormalCode screen on");
            v();
        }
        c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.f0
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f2572c.p1(normalCodeEnum);
            }
        });
    }

    @Override // c.a.g.d
    public void d(String str) throws Throwable {
        c.a.a.c cVar = this.U3;
        if (cVar != null) {
            cVar.a(str);
        }
        if (c.a.g.l.l.d()) {
            final String str2 = str + " [vin = " + this.j4.c() + ", iccid = " + this.j4.E() + "]";
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2602c.j0(str2);
                }
            });
            c.a.g.l.l.o(str2);
        }
    }

    public final void d0() {
        String str = f3075c;
        c.a.b.d.e.c(str, "initServiceParam start...");
        this.j4 = c.a.b.c.a.k();
        this.y4 = BYDAutoOtaDevice.getInstance(this.i4);
        this.z4 = BYDAutoPowerDevice.getInstance(this.i4);
        this.A4 = BYDAutoVersionDevice.getInstance(this.i4);
        this.B4 = BYDAutoBodyworkDevice.getInstance(this.i4);
        this.C4 = BYDAutoStudy.getInstance(this.i4);
        this.D4 = OtaUpgradeManager.getInstance(this.i4);
        this.m4 = new c.a.a.g.i(this.i4, this, this.D4);
        this.n4 = new c.a.g.i.f(this.i4, this);
        this.o4 = new c.a.d.f.k(this.i4, this.m4, this.n4, this, this, this);
        this.p4 = new c.a.d.f.h(this.m4, this.n4, this, this, this);
        this.q4 = new c.a.d.f.e(this.i4, this.m4, this.n4, this, this, this);
        this.r4 = new c.a.d.f.f(this.m4, this.n4, this, this);
        this.s4 = new c.a.d.f.l(this);
        this.t4 = new c.a.d.f.j(this.i4, this.m4, this.n4, this, this);
        this.u4 = new c.a.d.f.i(this.i4, this.m4, this.n4, this, this);
        this.E4 = new c.a.d.e.i(this.i4, this.n4, this.m4, this, this);
        this.F4 = new c.a.d.e.j(this.i4, this.n4, this.m4, this, this, this.D4);
        this.G4 = new c.a.d.e.k(this.i4, this.n4, this.m4, this, this);
        TextUtils.isEmpty(c.a.g.l.n.a(this.i4, SystemProperties.getInt("persist.sys.byd.countrycode", -1)));
        this.B4.registerListener((AbsBYDAutoBodyworkListener) new f());
        this.z4.registerListener((AbsBYDAutoPowerListener) new c.a.d.g.d(this.q4));
        c.a.d.b.d().g(new b.a() { // from class: c.a.d.i.e
            @Override // c.a.d.b.a
            public final void a() {
                this.f2556a.h0();
            }
        });
        c.a.b.d.e.c(str, "initServiceParam end...");
    }

    public final void d2(UpdateResultInfo updateResultInfo) throws Throwable {
        int i2;
        c.a.b.d.e.c(f3075c, "processUpdateFail");
        this.m4.d();
        f(false);
        this.t4.h(true);
        this.u4.b(true);
        if (((updateResultInfo != null && (i2 = updateResultInfo.updateMode) != 3 && i2 != 4 && i2 != 5) || updateResultInfo == null) && this.m4.k()) {
            this.m4.m(true, "processUpdateFail screen on");
            v();
        }
        if (c.a.g.l.l.d()) {
            c.a.g.l.r.m(this.S3);
        }
        this.j4.J(updateResultInfo == null ? "V1.0.0" : updateResultInfo.normalOriginVer);
        c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.j
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f2581c.r1();
            }
        });
        c.a.b.d.l.d().a(new Runnable() { // from class: c.a.d.i.t0
            @Override // java.lang.Runnable
            public final void run() {
                OtaUiService.s1();
            }
        });
        c.a.b.d.l.d().a(new Runnable() { // from class: c.a.d.i.z
            @Override // java.lang.Runnable
            public final void run() {
                this.f2617c.u1();
            }
        });
    }

    @Override // c.a.a.b
    public void e() {
        c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.a1
            @Override // java.lang.Runnable
            public final void run() {
                this.f2509c.J0();
            }
        });
    }

    public final void e0() throws Throwable {
        String str = f3075c;
        c.a.b.d.e.c(str, "initVinAndIccid");
        String strTrim = this.B4.getRealAutoVIN().trim();
        String strC = c.a.b.d.h.c("persist.vendor.bosch.cfg.diag.vin");
        if (!TextUtils.isEmpty(strTrim)) {
            this.j4.H(strTrim);
        } else if (TextUtils.isEmpty(strC)) {
            c.a.b.d.e.b(str, "vin is empty");
        } else {
            this.j4.H(strC);
        }
        c.a.b.d.e.b(str, "IS_BENCH_TEST = false");
        String strB = c.a.b.d.m.c(this.i4).b();
        if (TextUtils.isEmpty(strB)) {
            c.a.b.d.e.b(str, "iccid is empty");
            this.j4.i0("000000000000000000000");
        } else {
            this.j4.i0(strB);
        }
        c.a.b.d.e.c(str, "vin = " + c.a.b.d.i.b(this.j4.c()) + ", iccid = " + c.a.b.d.i.a(this.j4.E()));
        c.a.b.d.e.c(str, "timeZoneId = " + TimeZone.getDefault().getID() + ", displayName = " + TimeZone.getDefault().getDisplayName(false, 0, Locale.getDefault()));
        StringBuilder sb = new StringBuilder();
        sb.append("createGmtOffsetString = ");
        sb.append(c.a.b.d.o.b(true, true, TimeZone.getDefault().getRawOffset()));
        c.a.b.d.e.c(str, sb.toString());
    }

    public final void e2(UpdateResultInfo updateResultInfo) throws Throwable {
        int i2;
        c.a.b.d.e.c(f3075c, "processUpdateSuccess");
        this.m4.d();
        f(false);
        this.t4.h(true);
        this.u4.b(true);
        if (updateResultInfo != null && (i2 = updateResultInfo.updateMode) != 3 && i2 != 4 && i2 != 5 && this.m4.k()) {
            this.m4.m(true, "processUpdateSuccess screen on");
            v();
        }
        this.j4.J(updateResultInfo == null ? "V1.0.0" : updateResultInfo.normalVersion);
        c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.t
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f2605c.w1();
            }
        });
        c.a.b.d.l.d().a(new Runnable() { // from class: c.a.d.i.k
            @Override // java.lang.Runnable
            public final void run() {
                OtaUiService.x1();
            }
        });
    }

    @Override // c.a.g.d
    public void f(boolean z) throws Throwable {
        c.a.b.d.e.c(f3075c, "onSetUpdateFlag->flag = " + z);
        ResponseCheckVersionInfo responseCheckVersionInfoB = c.a.g.l.o.b();
        if (responseCheckVersionInfoB != null) {
            this.j4.I(responseCheckVersionInfoB.strategy.batchId);
            this.j4.U(responseCheckVersionInfoB.strategy.priorityType);
            this.j4.P(responseCheckVersionInfoB.strategy.strategyVer);
            this.j4.b0(responseCheckVersionInfoB.strategy.lowestSoc);
            this.j4.d0(responseCheckVersionInfoB.strategy.lowestVoltage);
            this.j4.c0(responseCheckVersionInfoB.strategy.temperature);
            this.j4.F(responseCheckVersionInfoB.strategy.acknowledgements);
            this.j4.Z(responseCheckVersionInfoB.strategy.strategyVer);
            this.j4.Y(responseCheckVersionInfoB.strategy.strategyType);
            this.j4.Q(responseCheckVersionInfoB.strategy.msgreportPath);
            this.j4.M(c.a.g.l.o.d(responseCheckVersionInfoB));
            this.j4.L(c.a.g.l.o.c(responseCheckVersionInfoB));
            if (!TextUtils.isEmpty(responseCheckVersionInfoB.strategy.functionAddition)) {
                this.j4.N(responseCheckVersionInfoB.strategy.functionAddition);
            }
            if (!TextUtils.isEmpty(responseCheckVersionInfoB.strategy.functionOptimization)) {
                this.j4.O(responseCheckVersionInfoB.strategy.functionOptimization);
            }
        }
        this.j4.a0(z);
        Settings.Global.putInt(this.i4.getContentResolver(), "UPDATE_FLAG", z ? 1 : 0);
        c.a.d.e.i iVar = this.E4;
        if (iVar != null) {
            iVar.v(z);
        }
        if (z && this.n4.e()) {
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.k0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2584c.B0();
                }
            });
        }
        this.n4.i(false);
        if (z && this.B4.getPowerLevel() == 2 && this.j4.b() == 0) {
            if (this.j4.s() < 10) {
                this.m4.m(true, "onSetUpdateFlag showCountWhenCarOff");
            } else {
                this.m4.m(false, "onSetUpdateFlag not showCountWhenCarOff");
            }
        }
        this.n4.c().n(z ? 1 : 0, null, this.j4.i(), this.j4.j(), this.j4.a(), String.valueOf(this.j4.h()), this.j4.l());
    }

    public final void f0() throws Throwable {
        int i2;
        String str = f3075c;
        c.a.b.d.e.c(str, "judgeUpgradeResult");
        String bydSystemVersion = this.A4.getBydSystemVersion();
        String strC = c.a.b.d.h.c("ro.product.version.soc.name");
        if (!TextUtils.isEmpty(bydSystemVersion)) {
            this.S3 = bydSystemVersion;
        } else if (TextUtils.isEmpty(strC)) {
            c.a.b.d.e.b(str, "mediaSoftVersion is empty");
        } else {
            this.S3 = strC;
        }
        c.a.b.d.e.c(str, "mediaSoftVersion = " + this.S3 + ", tBoxSoftVersion = " + this.y4.getTBoxSoftVersion());
        boolean zE = c.a.b.d.h.e();
        StringBuilder sb = new StringBuilder();
        sb.append("isUsbUpgrade = ");
        sb.append(zE);
        c.a.b.d.e.c(str, sb.toString());
        if (zE) {
            return;
        }
        c.a.b.d.e.c(str, "/data/vendor/nfs/nfs_ota/ exists? " + c.a.b.d.d.n("/data/vendor/nfs/nfs_ota/"));
        StringBuilder sb2 = new StringBuilder();
        String str2 = c.a.b.b.a.f2320a;
        sb2.append(str2);
        sb2.append(" exists? ");
        sb2.append(c.a.b.d.d.n(str2));
        c.a.b.d.e.c(str, sb2.toString());
        String str3 = c.a.b.b.a.f2321b;
        boolean zN = c.a.b.d.d.n(str3);
        c.a.b.d.e.c(str, str3 + " exists? " + zN);
        if (!zN) {
            int i3 = 0;
            while (i3 < this.M4) {
                try {
                    Thread.sleep(1000L);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                i3++;
                boolean zN2 = c.a.b.d.d.n(c.a.b.b.a.f2321b);
                c.a.b.d.e.c(f3075c, "retry resultFileExists, resultFileExists = " + zN2);
                if (zN2) {
                    break;
                }
            }
        }
        File[] fileArrListFiles = new File(c.a.b.b.a.f2321b).listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            c.a.b.d.e.c(f3075c, "listFile == 0, listFiles = " + fileArrListFiles);
        } else {
            for (File file : fileArrListFiles) {
                c.a.b.d.e.c(f3075c, "listFile = " + file.getAbsolutePath());
            }
        }
        try {
            File file2 = new File("/data/vendor/nfs/nfs_ota/OTA_ROOT_DIR.txt");
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        boolean zH = c.a.g.l.r.h();
        String str4 = f3075c;
        c.a.b.d.e.c(str4, "isUpgradeInfoFileExists = " + zH);
        if (zH) {
            RequestSubmitReportInfo requestSubmitReportInfoA = c.a.g.l.p.a();
            if (requestSubmitReportInfoA == null) {
                c.a.b.d.e.c(str4, "requestSubmitReportInfo is null");
                i2 = 0;
            } else {
                i2 = requestSubmitReportInfoA.updateMode;
            }
            c.a.b.d.e.c(str4, "upgradeInfo file exist, updateMode = " + i2);
            c.a.b.d.e.c(str4, "controlPresenter.isPowerLevelOff() = " + this.m4.k());
            c.a.b.d.e.c(str4, "vendor.ota.update.status = " + c.a.b.d.h.a());
            if (!H3) {
                if (!this.m4.k()) {
                    d2(null);
                    c.a.b.d.e.c(str4, "processUpdateFail null");
                    return;
                } else if (i2 == 0) {
                    this.m4.i(2, false);
                    return;
                } else {
                    this.m4.i(i2, false);
                    return;
                }
            }
            if (this.m4.b()) {
                if (!this.m4.k()) {
                    d2(null);
                    c.a.b.d.e.c(str4, "processUpdateFail null");
                    return;
                } else if (i2 == 0) {
                    this.m4.j(2);
                    return;
                } else {
                    this.m4.j(i2);
                    return;
                }
            }
            return;
        }
        if (this.m4.b() && H3) {
            if (this.m4.k()) {
                E("upgradeInfo file not exist, service crash restart");
            }
            this.m4.f();
            this.m4.c(0);
            return;
        }
        boolean zN3 = c.a.b.d.d.n("/data/vendor/nfs/nfs_ota/update/result/updateResultInfo.json");
        c.a.b.d.e.c(str4, "isUpdateResultInfoJsonExists = " + zN3);
        boolean zG = c.a.g.l.r.g();
        c.a.b.d.e.c(str4, "isUpdateResultInfoFileExists = " + zG);
        if (zG || zN3) {
            UpdateResultInfo updateResultInfoD = c.a.g.l.r.d();
            if (c.a.g.l.r.a(this.S3)) {
                e2(updateResultInfoD);
                return;
            }
            if (!c.a.g.l.m.b() || !c.a.g.l.m.a().isNeedRepairUpgrade) {
                d2(updateResultInfoD);
                return;
            } else {
                if (this.m4.k()) {
                    this.m4.i(updateResultInfoD.updateMode, true);
                    return;
                }
                return;
            }
        }
        int iR = this.j4.r();
        if (iR != -1) {
            this.j4.V(-1);
            c2(ResultCodeConsts.a(iR));
        } else {
            if (zH || zG || !this.m4.b()) {
                return;
            }
            if (this.m4.k()) {
                E("upgradeInfo file not exist && updateResultInfo file not exist && processing ota");
            }
            this.m4.f();
            this.m4.c(0);
        }
    }

    /* JADX INFO: renamed from: f2, reason: merged with bridge method [inline-methods] */
    public void P0() {
        r rVar = this.V3;
        if (rVar != null && rVar.isShowing()) {
            this.V3.dismissDialog();
            this.V3 = null;
        }
        this.V3 = new r(this);
        if (this.K4 != null) {
            c.a.b.d.l.d().i(this.K4);
            this.K4 = null;
        }
        this.K4 = new o(this.V3);
        if (this.m4.k()) {
            c.a.b.d.l.d().c(this.K4, 60000L);
        }
        this.V3.q(R.string.appointment_tips_EV_DM);
        this.V3.o(new r.b() { // from class: c.a.d.i.g
            @Override // c.a.d.i.c1.r.b
            public final void a() {
                this.f2574a.z1();
            }
        });
        this.V3.h(6);
    }

    @Override // c.a.d.a
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void b1() {
        String string;
        c.a.b.d.e.c(f3075c, "showCurOtaServerStateDialog, getNoNewVersionFlag = " + this.n4.s() + "getUserClickCheckState = " + this.j4.C());
        boolean z = false;
        if (!this.n4.s()) {
            int i2 = x;
            string = (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) ? getResources().getString(R.string.check_version) : i2 != 11 ? "null message" : getResources().getString(R.string.download_package);
        } else if (this.j4.C()) {
            string = String.format(getResources().getString(R.string.current_version), this.j4.e());
            z = true;
        } else {
            string = getResources().getString(R.string.check_version);
            c.a.b.c.a.k().g0(true);
        }
        if (this.L4 != null) {
            c.a.b.d.l.d().i(this.L4);
            this.L4 = null;
        }
        r rVar = this.V3;
        if (rVar != null && rVar.isShowing()) {
            this.V3.dismissDialog();
            this.V3 = null;
        }
        r rVar2 = new r(this);
        this.V3 = rVar2;
        this.L4 = new n(rVar2);
        this.V3.r(string);
        if (z) {
            this.V3.s(new r.c() { // from class: c.a.d.i.v0
                @Override // c.a.d.i.c1.r.c
                public final void a() {
                    this.f2610a.B1();
                }
            }, this.j4.e());
        }
        this.V3.o(new r.b() { // from class: c.a.d.i.x0
            @Override // c.a.d.i.c1.r.b
            public final void a() {
                this.f2614a.D1();
            }
        });
        this.V3.i(2, 2001);
        c.a.b.d.l.d().c(this.L4, 5000L);
    }

    /* JADX INFO: renamed from: g2, reason: merged with bridge method [inline-methods] */
    public void D0(c.a.a.d dVar) {
        this.T3 = dVar;
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.dismissDialog();
        }
        s sVar = this.Z3;
        if (sVar != null && sVar.isShowing()) {
            this.Z3.dismissDialog();
            this.Z3 = null;
        }
        s sVar2 = new s(this);
        this.Z3 = sVar2;
        sVar2.o(new m(dVar));
        this.Z3.p();
        this.Z3.h(9);
        this.n4.c().k(this.j4.A(), true);
    }

    @Override // c.a.g.d
    public void h() {
        c.a.d.h.a.e(this.i4).a();
        if (this.U3 != null) {
            c.a.g.l.p.b();
            this.U3.b();
        }
    }

    /* JADX INFO: renamed from: h2, reason: merged with bridge method [inline-methods] */
    public void X0() {
        r rVar = this.V3;
        if (rVar != null && rVar.isShowing()) {
            this.V3.dismissDialog();
            this.V3 = null;
        }
        r rVar2 = new r(this);
        this.V3 = rVar2;
        rVar2.q(R.string.E_call);
        this.V3.h(10);
    }

    @Override // c.a.d.a
    public void i(long j2) {
        if (j2 == 0) {
            this.y4.setAppointmentTime(0, 0, 0, 2, 0, new a(j2));
            PendingIntent pendingIntent = I3;
            if (pendingIntent != null) {
                c.a.b.d.a.a(this.i4, pendingIntent);
                I3 = null;
            }
            if (this.j4.w() && this.B4.getPowerLevel() == 2 && this.j4.s() < 10) {
                this.m4.m(true, "setAppointmentTime 0 showCountWhenCarOff");
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j2);
            c.a.b.d.e.c(f3075c, "year = " + calendar.get(1) + ", month = " + (calendar.get(2) + 1) + ", day = " + calendar.get(5) + ", hour = " + calendar.get(11) + ", minute = " + calendar.get(12));
            this.y4.setAppointmentTime(calendar.get(5), calendar.get(11), calendar.get(12), 2, 1, new b(j2));
            PendingIntent pendingIntent2 = I3;
            if (pendingIntent2 != null) {
                c.a.b.d.a.a(this.i4, pendingIntent2);
                I3 = null;
            }
            PendingIntent broadcast = PendingIntent.getBroadcast(this.i4, 0, new Intent("vendor.intent.action.OTA_APPOINTMENT_BROADCAST"), 0);
            I3 = broadcast;
            c.a.b.d.a.b(this.i4, calendar, broadcast);
            if (this.j4.w()) {
                this.m4.m(false, "setAppointmentTime updateTime");
            }
        }
        this.j4.G(j2);
        c.a.d.e.i iVar = this.E4;
        if (iVar != null) {
            iVar.k(j2);
        }
    }

    public void i2(int i2, int i3) {
        int i4 = i3 + this.M3 + this.N3;
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.g(i2, i4);
            return;
        }
        q qVar = this.c4;
        if (qVar != null && qVar.isShowing()) {
            this.c4.dismissDialog();
        }
        v vVar2 = new v(this);
        this.b4 = vVar2;
        vVar2.g(i2, i4);
        this.b4.show();
    }

    @Override // c.a.d.a
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public void j0(String str) {
        q qVar = this.c4;
        if (qVar != null && qVar.isShowing()) {
            this.c4.dismissDialog();
        }
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.dismissDialog();
        }
        r rVar = this.d4;
        if (rVar == null || !rVar.isShowing()) {
            r rVar2 = new r(this);
            this.d4 = rVar2;
            rVar2.r(str);
            this.d4.h(1);
        } else {
            this.d4.r(str);
        }
        if (this.m4.k()) {
            if (c.a.g.l.l.d()) {
                this.j4.R(10000);
            }
            if (this.K4 != null) {
                c.a.b.d.l.d().i(this.K4);
                this.K4 = null;
            }
            this.K4 = new o(this.d4);
            c.a.b.d.l.d().c(this.K4, 10000L);
            this.d4.o(new r.b() { // from class: c.a.d.i.u0
                @Override // c.a.d.i.c1.r.b
                public final void a() {
                    this.f2608a.F1();
                }
            });
        }
    }

    public void j2(int i2) {
        q qVar = this.c4;
        if (qVar != null && qVar.isShowing()) {
            this.c4.a(i2);
            return;
        }
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.dismissDialog();
        }
        q qVar2 = new q(this);
        this.c4 = qVar2;
        qVar2.a(i2);
        this.c4.show();
    }

    @Override // c.a.g.d
    public void k() {
        c.a.b.d.e.c(f3075c, "onSubmitReportSuccess");
        C(false);
        if (c.a.g.l.l.d()) {
            return;
        }
        if (!this.m4.k()) {
            this.m4.c(0);
        } else if (b0() == 2) {
            this.m4.c(0);
        } else {
            E3 = true;
        }
    }

    /* JADX INFO: renamed from: k2, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void p1(final ResultCodeConsts.NormalCodeEnum normalCodeEnum) throws Throwable {
        boolean z;
        int i2;
        String str = f3075c;
        c.a.b.d.e.c(str, "showNormalCodeDialog->normalCodeEnum.normalCode = " + normalCodeEnum.normalCode);
        RequestSubmitReportInfo requestSubmitReportInfoA = c.a.g.l.p.a();
        if (requestSubmitReportInfoA == null || !((i2 = requestSubmitReportInfoA.updateMode) == 4 || i2 == 5)) {
            z = false;
        } else {
            int i3 = normalCodeEnum.normalCode;
            if (i3 != 2000 && i3 != 2001) {
                f(false);
                q qVar = this.c4;
                if (qVar != null && qVar.isShowing()) {
                    this.c4.dismissDialog();
                }
                v vVar = this.b4;
                if (vVar != null && vVar.isShowing()) {
                    this.b4.dismissDialog();
                }
                c.a.b.d.l.d().a(new Runnable() { // from class: c.a.d.i.r0
                    @Override // java.lang.Runnable
                    public final void run() {
                        OtaUiService.G1();
                    }
                });
            }
            z = true;
        }
        c.a.b.d.e.c(str, "showNormalCodeDialog->isOtaCycleTestNoNet = " + c.a.g.l.l.f());
        if (c.a.g.l.l.f()) {
            c.a.b.d.e.c(str, "showNormalCodeDialog->isOtaCycleTestNoNet true");
            if (c.a.g.l.r.f()) {
                if (c.a.g.l.l.d()) {
                    c.a.g.l.r.j();
                } else {
                    c.a.g.l.r.i();
                }
            }
            if (c.a.g.l.r.g()) {
                if (c.a.g.l.l.d()) {
                    c.a.g.l.r.l();
                } else {
                    c.a.g.l.r.k();
                }
            }
        } else {
            c.a.b.d.e.c(str, "showNormalCodeDialog->isOtaCycleTestNoNet false");
            c.a.b.d.l.d().a(new Runnable() { // from class: c.a.d.i.q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2597c.I1(normalCodeEnum);
                }
            });
        }
        if (!z) {
            s(normalCodeEnum.normalCode);
        }
        if (this.j4.w() && this.B4.getPowerLevel() == 2 && this.j4.b() == 0) {
            if (this.j4.s() < 10) {
                this.m4.m(true, "showNormalCodeDialog showCountWhenCarOff");
            } else {
                this.m4.m(false, "showNormalCodeDialog not showCountWhenCarOff");
            }
        }
    }

    @Override // c.a.d.a
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public void L0() {
        t tVar = this.X3;
        if (tVar != null && tVar.isShowing()) {
            this.X3.dismissDialog();
            this.X3 = null;
        }
        this.X3 = new t(this);
        if (this.K4 != null) {
            c.a.b.d.l.d().i(this.K4);
            this.K4 = null;
        }
        this.K4 = new o(this.X3);
        if (this.m4.k()) {
            c.a.b.d.l.d().c(this.K4, 60000L);
        }
        this.X3.x(String.format(getResources().getString(R.string.update_info_title), this.j4.l()));
        this.X3.v(String.format(getResources().getString(R.string.update_info_tips_EV_DM), Integer.valueOf(this.j4.x())));
        int iH = this.j4.h();
        String str = f3075c;
        c.a.b.d.e.c(str, "updateInfoDialog.showDialog time = " + iH);
        this.X3.w(String.format(getResources().getString(R.string.update_info_time_take), Integer.valueOf(iH % 60 > 0 ? (int) Math.round(((double) ((iH * 1.0f) / 60.0f)) + 0.5d) : Math.round((iH * 1.0f) / 60.0f))));
        this.X3.u(this.j4.a());
        this.X3.t(new j());
        this.X3.h(4);
        c.a.b.d.e.c(str, "updateInfoDialog.showDialog");
    }

    /* JADX INFO: renamed from: l2, reason: merged with bridge method [inline-methods] */
    public void T0() {
        p pVar = this.W3;
        if (pVar != null && pVar.isShowing()) {
            this.W3.dismissDialog();
            this.W3 = null;
        }
        this.W3 = new p(this);
        if (this.K4 != null) {
            c.a.b.d.l.d().i(this.K4);
            this.K4 = null;
        }
        this.K4 = new o(this.W3);
        if (this.m4.k()) {
            c.a.b.d.l.d().c(this.K4, 60000L);
        }
        this.W3.r(R.string.update_confirm);
        this.W3.q(R.string.update_cancel);
        this.W3.s(R.string.update_confirm_tips);
        this.W3.p(new l());
        this.W3.h(8);
    }

    @Override // c.a.d.a
    public void m() {
        Bundle bundle = new Bundle();
        bundle.putInt("overtime", 60);
        bundle.putInt("silence", 1);
        bundle.putInt("soc", 15);
        bundle.putInt("voltage", 12);
        this.I4.onCloudServiceListener(1, bundle);
    }

    /* JADX INFO: renamed from: m2, reason: merged with bridge method [inline-methods] */
    public void R0() {
        u uVar = this.a4;
        if (uVar != null && uVar.isShowing()) {
            this.a4.dismissDialog();
            this.a4 = null;
        }
        this.a4 = new u(this);
        if (this.K4 != null) {
            c.a.b.d.l.d().i(this.K4);
            this.K4 = null;
        }
        this.K4 = new o(this.a4);
        if (this.m4.k()) {
            c.a.b.d.l.d().c(this.K4, 60000L);
        }
        this.a4.q(new k());
        this.a4.h(7);
    }

    @Override // c.a.d.a
    public void n() {
        c.a.b.d.e.c(f3075c, "dismissOnPowerLevelDialog->curDialogType = " + f3076d);
        switch (f3076d) {
            case 1:
                r rVar = this.V3;
                if (rVar != null && rVar.isShowing()) {
                    this.V3.dismissDialog();
                    break;
                }
                break;
            case 2:
                r rVar2 = this.V3;
                if (rVar2 != null && rVar2.isShowing()) {
                    this.V3.dismissDialog();
                    break;
                }
                break;
            case 3:
                p pVar = this.W3;
                if (pVar != null && pVar.isShowing()) {
                    this.W3.dismissDialog();
                    break;
                }
                break;
            case 4:
                t tVar = this.X3;
                if (tVar != null && tVar.isShowing()) {
                    this.X3.dismissDialog();
                    break;
                }
                break;
            case 5:
                w wVar = this.Y3;
                if (wVar != null && wVar.isShowing()) {
                    this.Y3.dismissDialog();
                    break;
                }
                break;
            case 6:
                r rVar3 = this.V3;
                if (rVar3 != null && rVar3.isShowing()) {
                    this.V3.dismissDialog();
                    break;
                }
                break;
            case 7:
                u uVar = this.a4;
                if (uVar != null && uVar.isShowing()) {
                    this.a4.dismissDialog();
                    break;
                }
                break;
            case 8:
                p pVar2 = this.W3;
                if (pVar2 != null && pVar2.isShowing()) {
                    this.W3.dismissDialog();
                    break;
                }
                break;
            case 9:
                s sVar = this.Z3;
                if (sVar != null && sVar.isShowing()) {
                    this.Z3.dismissDialog();
                    break;
                }
                break;
            case 10:
                r rVar4 = this.V3;
                if (rVar4 != null && rVar4.isShowing()) {
                    this.V3.dismissDialog();
                    break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: n2, reason: merged with bridge method [inline-methods] */
    public void H0(String str) {
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.d(str);
            return;
        }
        q qVar = this.c4;
        if (qVar != null && qVar.isShowing()) {
            this.c4.dismissDialog();
        }
        v vVar2 = new v(this);
        this.b4 = vVar2;
        vVar2.d(str);
        this.b4.show();
    }

    @Override // c.a.a.b
    public void o(final c.a.a.d dVar) {
        c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.b1
            @Override // java.lang.Runnable
            public final void run() {
                this.f2512c.D0(dVar);
            }
        });
    }

    public void o2(final int i2) {
        this.R3 = i2;
        String str = f3075c;
        c.a.b.d.e.a(str, "showVersionDetailDialog triggerDialogType =" + i2);
        w wVar = this.Y3;
        if (wVar != null && wVar.isShowing()) {
            this.Y3.dismissDialog();
            this.Y3 = null;
        }
        this.Y3 = new w(this);
        if (this.K4 != null) {
            c.a.b.d.l.d().i(this.K4);
            this.K4 = null;
        }
        this.K4 = new o(this.Y3);
        if (this.m4.k()) {
            if (i2 == 4) {
                this.j4.R(-1);
                c.a.b.d.l.d().c(this.K4, 60000L);
            } else if (i2 == 1) {
                this.j4.R(2000);
                c.a.b.d.l.d().c(this.K4, 10000L);
            }
        }
        if (i2 == 2 || i2 == 1) {
            c.a.b.d.e.a(str, "showVersionDetailDialog getLatestVehicleVersion =" + this.j4.e());
            this.Y3.q(String.format(getResources().getString(R.string.version_detail_title), this.j4.e()));
        } else {
            c.a.b.d.e.a(str, "showVersionDetailDialog getLatestVehicleVersion =" + this.j4.l());
            this.Y3.q(String.format(getResources().getString(R.string.version_detail_title), this.j4.l()));
        }
        String strI = this.j4.i();
        String strJ = this.j4.j();
        Description descriptionC = c.a.g.l.q.c();
        if (descriptionC != null) {
            if (!TextUtils.isEmpty(descriptionC.functionAddition)) {
                strI = descriptionC.functionAddition;
            }
            if (!TextUtils.isEmpty(descriptionC.functionOptimization)) {
                strJ = descriptionC.functionOptimization;
            }
        } else {
            this.n4.c().e(4, Locale.getDefault().getLanguage(), 2);
        }
        this.Y3.o(strI);
        this.Y3.p(strJ);
        this.Y3.n(new w.a() { // from class: c.a.d.i.c0
            @Override // c.a.d.i.c1.w.a
            public final void a() throws Throwable {
                this.f2515a.a2(i2);
            }
        });
        this.Y3.h(5);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        c.a.d.e.k kVar;
        c.a.d.e.j jVar;
        c.a.d.e.i iVar;
        String action = intent.getAction();
        String str = f3075c;
        c.a.b.d.e.c(str, "OtaUiService onBind->action = " + action);
        if (action.equals("com.intent.action.OTA_UPGRADE_SDK")) {
            synchronized (this.f4) {
                try {
                    this.f4.wait(2000L);
                    if (this.E4 == null) {
                        c.a.b.d.e.b(str, "otaSdkCommandStub is null");
                        this.E4 = new c.a.d.e.i(this.i4, this.n4, this.m4, this, this);
                    }
                    iVar = this.E4;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    return null;
                } finally {
                }
            }
            return iVar;
        }
        if (action.equals("com.intent.action.OTA_UPGRADE_TEST")) {
            synchronized (this.f4) {
                try {
                    this.f4.wait(2000L);
                    if (this.F4 == null) {
                        c.a.b.d.e.b(str, "otaTestCommandStub is null");
                        this.F4 = new c.a.d.e.j(this.i4, this.n4, this.m4, this, this, this.D4);
                    }
                    jVar = this.F4;
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                    return null;
                } finally {
                }
            }
            return jVar;
        }
        if (!action.equals("com.intent.action.OTA_TOOL_TEST")) {
            return null;
        }
        synchronized (this.f4) {
            try {
                this.f4.wait(2000L);
                if (this.G4 == null) {
                    c.a.b.d.e.b(str, "otaToolCommandStub is null");
                    this.G4 = new c.a.d.e.k(this.i4, this.n4, this.m4, this, this);
                }
                kVar = this.G4;
            } catch (InterruptedException e4) {
                e4.printStackTrace();
                return null;
            } finally {
            }
        }
        return kVar;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c.a.j.e eVar = this.J4;
        if (eVar != null) {
            eVar.b(configuration);
        }
        c.a.b.d.e.c(f3075c, "onConfigurationChanged->powerLevel = " + this.B4.getPowerLevel() + ", orientation = " + configuration.orientation + ", uiMode = " + configuration.uiMode);
        int i2 = configuration.orientation;
        int i3 = configuration.uiMode;
        if (i3 == 19 || i3 == 35) {
            b2(configuration);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        String str = f3075c;
        c.a.b.d.e.c(str, "OtaUiService onCreate start...");
        c.a.b.d.e.b(str, "OtaUiService versionName = 1.19.9-202504271425");
        this.i4 = this;
        c.a.d.h.e.a();
        c.a.b.d.c.e(this.i4).g();
        c.a.b.d.m.c(this.i4).d();
        c.a.d.h.a.e(this.i4).a();
        H3 = c.a.d.h.c.c();
        c.a.b.d.e.c(str, "isServiceCrashRestart = " + H3);
        c.a.d.h.c.b();
        HandlerThread handlerThread = new HandlerThread("handlerThread");
        this.l4 = handlerThread;
        handlerThread.start();
        e eVar = new e(this.l4.getLooper());
        this.k4 = eVar;
        eVar.sendEmptyMessage(1);
        c.a.j.e eVar2 = new c.a.j.e();
        this.J4 = eVar2;
        eVar2.a(this);
        c.a.b.d.e.c(str, "OtaUiService onCreate end...");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        c.a.b.d.e.c(f3075c, "OtaUiService onDestroy");
        c.a.a.a aVar = this.m4;
        if (aVar != null) {
            aVar.a();
            this.m4 = null;
        }
        c.a.g.c cVar = this.n4;
        if (cVar != null) {
            cVar.a();
            this.n4 = null;
        }
        AppointmentBroadcastReceiver appointmentBroadcastReceiver = this.v4;
        if (appointmentBroadcastReceiver != null) {
            this.i4.unregisterReceiver(appointmentBroadcastReceiver);
        }
        SystemChangeBroadcastReceiver systemChangeBroadcastReceiver = this.w4;
        if (systemChangeBroadcastReceiver != null) {
            this.i4.unregisterReceiver(systemChangeBroadcastReceiver);
        }
        c.a.d.g.c cVar2 = this.H4;
        if (cVar2 != null) {
            this.B4.unregisterListener((AbsBYDAutoBodyworkListener) cVar2);
        }
        c.a.d.f.g gVar = this.x4;
        if (gVar != null) {
            gVar.d();
        }
        c.a.b.d.m.c(this.i4).e();
        this.l4.quitSafely();
    }

    @Override // c.a.a.b
    public void onNotify(Bundle bundle) {
        int i2 = bundle.getInt("type");
        if (i2 != 0) {
            if (i2 == 4) {
                G3 = true;
                return;
            } else {
                if (i2 != 5) {
                    return;
                }
                G3 = true;
                this.L3 = bundle.getInt("remainTime");
                c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.p0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2596c.x0();
                    }
                });
                return;
            }
        }
        String string = bundle.getString("updateType");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.J3 = string;
        this.K3 = 0;
        this.L3 = 0;
        p2();
        if (string.equals("media")) {
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.z0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2618c.t0();
                }
            });
            this.n4.c().m(1, this.j4.A());
            return;
        }
        if (string.equals("ecu")) {
            List<DiagnoseEcuInfo> listA = c.a.g.l.c.a();
            if (listA == null || listA.size() <= 0) {
                c.a.b.d.e.c(f3075c, "diagnoseEcuInfoList == null || diagnoseEcuInfoList.size() <= 0");
                this.M3 = 0;
                this.N3 = 0;
            } else {
                c.a.b.d.e.c(f3075c, "diagnoseEcuInfoList != null && diagnoseEcuInfoList.size() > 0");
                this.M3 = 0;
                this.N3 = 1;
            }
            c.a.b.d.e.c(f3075c, "clearDtcTime = " + this.M3 + ", getDtcTime = " + this.N3);
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.r
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2600c.v0();
                }
            });
            this.n4.c().m(2, this.j4.A());
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("otaBroadcastType", 0);
            if (intExtra == 1) {
                this.o4.a(intent.getStringExtra("method_name"));
            } else if (intExtra == 2) {
                this.p4.a();
            } else if (intExtra == 3) {
                this.q4.a();
            } else if (intExtra == 4) {
                this.r4.a();
            } else if (intExtra == 5) {
                this.s4.a();
            }
        }
        return super.onStartCommand(intent, i2, i3);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        String action = intent.getAction();
        c.a.b.d.e.c(f3075c, "OtaUiService onUnbind->action = " + action);
        return super.onUnbind(intent);
    }

    @Override // c.a.a.b
    public void onUpdateFail(int i2) {
        c.a.b.d.e.c(f3075c, "onUpdateFail->errorCode = " + i2);
        c.a.a.a aVar = this.m4;
        if (aVar != null) {
            aVar.f();
            this.m4.c(0);
        }
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.dismissDialog();
        }
        q qVar = this.c4;
        if (qVar != null && qVar.isShowing()) {
            this.c4.dismissDialog();
        }
        c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.x
            @Override // java.lang.Runnable
            public final void run() {
                this.f2613c.f1();
            }
        }, 5000L);
    }

    @Override // c.a.a.b
    public void onUpdateProgress(int i2) {
        c.a.b.d.e.c(f3075c, "onUpdateProgress->progress = " + i2);
        if (TextUtils.isEmpty(this.J3)) {
            return;
        }
        F3 = true;
        this.K3 = i2;
        if (this.J3.equals("media")) {
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2585c.h1();
                }
            });
        } else if (this.J3.equals("ecu")) {
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2540c.j1();
                }
            });
        }
    }

    @Override // c.a.a.b
    public void onUpdateSuccess() {
        boolean zF = c.a.g.l.r.f();
        c.a.b.d.e.c(f3075c, "onUpdateSuccess->upgradeType = " + this.J3 + ", isEcuOtaResultFileExists = " + zF);
        if (TextUtils.isEmpty(this.J3)) {
            c.a.g.l.r.e();
            c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.v
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2609c.l1();
                }
            }, 5000L);
        } else {
            if (this.J3.equals("ecu")) {
                c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2576c.n1();
                    }
                });
            }
            G3 = true;
            this.n4.t();
        }
    }

    @Override // c.a.d.a
    public boolean p() {
        return y;
    }

    public final void p2() {
        Timer timer = this.g4;
        if (timer != null) {
            timer.cancel();
            this.g4 = null;
        }
        F3 = false;
        G3 = false;
        this.g4 = new Timer("heartbeat");
        if (TextUtils.isEmpty(this.J3)) {
            return;
        }
        if (this.J3.equals("media")) {
            this.g4.schedule(new g(), 900000L, 900000L);
        } else if (this.J3.equals("ecu")) {
            this.g4.schedule(new h(), 600000L, 600000L);
        }
    }

    @Override // c.a.a.b
    public void q(int i2) {
        if (c.a.g.l.l.d()) {
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.p
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    this.f2595c.z0();
                }
            });
        }
        i(0L);
        this.j4.R(-1);
        this.j4.e0(i2);
        c.a.g.l.p.b();
        this.n4.c().l(i2);
    }

    @Override // c.a.g.d
    public void r(final int i2) {
        x = i2;
        c.a.g.c cVar = this.n4;
        if (cVar != null) {
            cVar.q(x);
        }
        if (!c.a.g.l.l.d() || i2 == 0) {
            return;
        }
        c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.h0
            @Override // java.lang.Runnable
            public final void run() {
                this.f2577c.n0(i2);
            }
        });
    }

    @Override // c.a.d.a
    public void s(int i2) throws Throwable {
        String str = f3075c;
        c.a.b.d.e.c(str, "showNormalCodeDialog->normalCode = " + i2);
        this.P3 = i2;
        String strA = c.a.g.l.k.a(this, this.j4, i2, null);
        boolean z = i2 == 2000;
        boolean z2 = i2 == 2007 || i2 == 2008 || i2 == 2139 || i2 == 2009;
        q qVar = this.c4;
        if (qVar != null && qVar.isShowing()) {
            this.c4.dismissDialog();
        }
        v vVar = this.b4;
        if (vVar != null && vVar.isShowing()) {
            this.b4.dismissDialog();
        }
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        r rVar = this.V3;
        if (rVar != null && rVar.isShowing()) {
            this.V3.dismissDialog();
            this.V3 = null;
        }
        r rVar2 = new r(this);
        this.V3 = rVar2;
        rVar2.r(strA);
        if (z2) {
            this.V3.p();
        }
        if (z) {
            this.V3.s(new r.c() { // from class: c.a.d.i.o
                @Override // c.a.d.i.c1.r.c
                public final void a() {
                    this.f2593a.K1();
                }
            }, this.j4.e());
        }
        this.V3.h(1);
        if (!c.a.g.l.l.d()) {
            if (this.m4.k()) {
                this.j4.R(i2);
                if (this.K4 != null) {
                    c.a.b.d.l.d().i(this.K4);
                    this.K4 = null;
                }
                this.K4 = new o(this.V3);
                c.a.b.d.l.d().c(this.K4, 10000L);
                this.V3.o(new r.b() { // from class: c.a.d.i.w
                    @Override // c.a.d.i.c1.r.b
                    public final void a() {
                        this.f2611a.Y1();
                    }
                });
                return;
            }
            return;
        }
        if (i2 == 2000) {
            c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.m
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2587c.M1();
                }
            }, 5000L);
            return;
        }
        if (i2 != 2001) {
            this.m4.c(0);
            c.a.b.d.e.c(str, "ota test not satisfied setTboxAndQnxOtaState(0)");
            if (this.m4.k()) {
                this.j4.R(i2);
                if (this.K4 != null) {
                    c.a.b.d.l.d().i(this.K4);
                    this.K4 = null;
                }
                this.K4 = new o(this.V3);
                c.a.b.d.l.d().c(this.K4, 10000L);
                this.V3.o(new r.b() { // from class: c.a.d.i.j0
                    @Override // c.a.d.i.c1.r.b
                    public final void a() {
                        this.f2582a.U1();
                    }
                });
            }
            c.a.b.d.l.d().g(new Runnable() { // from class: c.a.d.i.w0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2612c.W1();
                }
            }, 2000L);
            return;
        }
        if (c.a.g.l.l.f()) {
            c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2615c.O1();
                }
            }, 5000L);
            return;
        }
        this.m4.c(0);
        c.a.b.d.e.c(str, "ota test update fail setTboxAndQnxOtaState(0)");
        if (this.m4.k()) {
            this.j4.R(i2);
            if (this.K4 != null) {
                c.a.b.d.l.d().i(this.K4);
                this.K4 = null;
            }
            this.K4 = new o(this.V3);
            c.a.b.d.l.d().c(this.K4, 10000L);
            this.V3.o(new r.b() { // from class: c.a.d.i.o0
                @Override // c.a.d.i.c1.r.b
                public final void a() {
                    this.f2594a.Q1();
                }
            });
        }
        c.a.b.d.l.d().g(new Runnable() { // from class: c.a.d.i.y0
            @Override // java.lang.Runnable
            public final void run() {
                this.f2616c.S1();
            }
        }, 2000L);
    }

    @Override // c.a.d.a
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public void f1() {
        c.a.b.d.e.c(f3075c, "reboot");
        Bundle bundle = new Bundle();
        bundle.putInt("rebootQNX", 1);
        this.D4.executeCMD(bundle);
        Timer timer = this.h4;
        if (timer != null) {
            timer.cancel();
            this.h4 = null;
        }
        Timer timer2 = new Timer("reboot");
        this.h4 = timer2;
        timer2.schedule(new c(), 300000L);
    }

    @Override // c.a.g.d
    public void u() throws Throwable {
        e0();
    }

    @Override // c.a.d.a
    public void v() {
        if (this.m4.k()) {
            c.a.b.d.e.c(f3075c, "setScreenOn");
            this.y4.setBackLightState(1);
        }
    }

    @Override // c.a.d.a
    public void w(long j2) {
        this.Q3 = j2;
        p pVar = this.W3;
        if (pVar != null && pVar.isShowing()) {
            this.W3.dismissDialog();
            this.W3 = null;
        }
        p pVar2 = new p(this);
        this.W3 = pVar2;
        pVar2.u(R.string.cancel_appointment_update_title);
        c.a.b.d.e.c(f3075c, "getLanguage = " + Locale.getDefault().getLanguage() + ", getCountry = " + Locale.getDefault().getCountry() + ", toString = " + Locale.getDefault().toString());
        String strE = c.a.b.d.n.e(this.i4, j2);
        String strReplace = String.format(getResources().getString(R.string.cancel_appointment_update_tips), strE);
        if (strE.startsWith("In")) {
            strReplace = strReplace.replace("after In", "in");
        }
        this.W3.t(strReplace);
        this.W3.p(new i());
        this.W3.h(3);
    }

    @Override // c.a.g.d
    public void x(int i2, int i3) {
        c.a.d.h.a.e(this.i4).d(i2, i3);
    }

    @Override // c.a.g.d
    public void y() {
        c.a.b.d.e.c(f3075c, "onGetDtcFinish");
        if (this.J3.equals("ecu")) {
            this.N3 = 0;
            c.a.b.d.l.d().b(new Runnable() { // from class: c.a.d.i.i0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2580c.p0();
                }
            });
        }
        G3 = true;
        c.a.g.l.r.e();
        c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.l0
            @Override // java.lang.Runnable
            public final void run() {
                this.f2586c.r0();
            }
        }, 5000L);
    }

    @Override // c.a.a.b
    public void z() {
        int iA = this.j4.A();
        String strB = c.a.g.l.f.b(iA);
        String str = f3075c;
        c.a.b.d.e.c(str, "onRegisterUpdateListener->updateMode = " + iA + ", otaModel = " + strB);
        boolean zC = c.a.g.l.f.c(strB);
        StringBuilder sb = new StringBuilder();
        sb.append("onRegisterUpdateListener->isSaveFotaUpdate = ");
        sb.append(zC);
        c.a.b.d.e.c(str, sb.toString());
    }
}
