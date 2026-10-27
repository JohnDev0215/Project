package k3;

import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import b.c;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: CpdHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f5845a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f5846b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoDiPilotDevice f5847c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BYDAutoBodyworkDevice f5848d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BYDAutoOtaDevice f5849e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BYDAutoStudy f5850f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public AbsBYDAutoBodyworkListener f5851g = new C0072a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AbsBYDAutoDiPilotListener f5852h = new b();

    /* JADX INFO: renamed from: k3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CpdHandler.java */
    public class C0072a extends AbsBYDAutoBodyworkListener {
        public C0072a() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(int i8, int i9) {
            super.onDoorStateChanged(i8, i9);
            BydLogHelper.logD("onDoorStateChanged , area:" + i8 + ", state:" + i9);
            if (i8 < 1 || i8 > 4 || i9 != 1) {
                return;
            }
            BydLogHelper.logD("the door of car is open , dismiss cpd window, screen off .");
            k3.b.m(a.this.f5846b).g();
        }
    }

    /* JADX INFO: compiled from: CpdHandler.java */
    public class b extends AbsBYDAutoDiPilotListener {
        public b() {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onCpdWarningStateChanged(int i8) {
            super.onCpdWarningStateChanged(i8);
            BydLogHelper.logD("onCpdWarningStateChanged:" + i8);
            int powerLevel = a.this.f5848d.getPowerLevel();
            int cpdSupport = a.this.f5850f.getCpdSupport();
            if (cpdSupport != 1 || powerLevel != 0) {
                BydLogHelper.logD("onCpdWarningStateChanged, powerLevel:" + powerLevel + ", cpdSupport:" + cpdSupport);
                return;
            }
            if (i8 >= 2 && i8 <= 6) {
                if (a.this.f5849e.getBcmOtaState() == 1) {
                    BydLogHelper.logD("BCM OTA STATE , NOT SHOW CPD WIN !!! ");
                    return;
                } else {
                    a.this.f5849e.setBackLightState(1);
                    k3.b.m(a.this.f5846b).s();
                    return;
                }
            }
            if (i8 == 0) {
                int cpdWorkingStatus = a.this.f5847c.getCpdWorkingStatus();
                if (cpdWorkingStatus == 0) {
                    k3.b.m(a.this.f5846b).g();
                    return;
                }
                BydLogHelper.logD("cpdWorkingStatus is not standby ,status: " + cpdWorkingStatus);
            }
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onCpdWorkingStatusChanged(int i8) {
            super.onCpdWorkingStatusChanged(i8);
            BydLogHelper.logD("onCpdWorkingStatusChanged:" + i8);
            if (i8 == 0 && a.this.f5847c.getCpdWarningState() == 0) {
                k3.b.m(a.this.f5846b).g();
            }
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDataChanged(c cVar) {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onError(int i8, String str) {
        }
    }

    public a(Context context) {
        this.f5846b = context.getApplicationContext();
        h();
    }

    public static a g(Context context) {
        if (f5845a == null) {
            synchronized (a.class) {
                if (f5845a == null) {
                    f5845a = new a(context);
                }
            }
        }
        return f5845a;
    }

    public void f() {
        k3.b.m(this.f5846b).f();
    }

    public final void h() {
        this.f5847c = BYDAutoDiPilotDevice.getInstance(this.f5846b);
        this.f5848d = BYDAutoBodyworkDevice.getInstance(this.f5846b);
        this.f5849e = BYDAutoOtaDevice.getInstance(this.f5846b);
        this.f5850f = BYDAutoStudy.getInstance(this.f5846b);
    }

    public void i() {
        this.f5847c.registerListener(this.f5852h);
        this.f5848d.registerListener(this.f5851g);
    }

    public void j() {
        this.f5847c.unregisterListener(this.f5852h);
        this.f5848d.unregisterListener(this.f5851g);
    }
}
