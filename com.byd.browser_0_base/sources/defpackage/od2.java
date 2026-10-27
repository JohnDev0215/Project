package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import androidx.preference.Preference;
import java.lang.ref.WeakReference;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.password_check.a;
import org.chromium.chrome.browser.password_check.e;
import org.chromium.chrome.browser.password_manager.PasswordStoreBridge;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SafetyCheckMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class od2 implements a.InterfaceC0101a, PasswordStoreBridge.a {
    public PropertyModel a;
    public qd2 b;
    public final SettingsLauncher c;
    public final a8 d;
    public boolean e;
    public boolean f;
    public boolean g;
    public final zp1<bi1> h;
    public int i;
    public Handler j;
    public Runnable k;
    public Runnable l;
    public Runnable m;
    public long n;
    public Integer o;
    public final SharedPreferencesManager p;
    public final on0 q;

    public od2(PropertyModel propertyModel, qd2 qd2Var, SettingsLauncher settingsLauncher, a8 a8Var, zp1<bi1> zp1Var) {
        Handler handler = new Handler();
        this.n = -1L;
        this.o = 0;
        this.q = new on0(6, this);
        this.a = propertyModel;
        this.b = qd2Var;
        this.c = settingsLauncher;
        this.d = a8Var;
        this.j = handler;
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        this.p = sharedPreferencesManager;
        this.a.p(pd2.g, new k52(1));
        this.a.p(pd2.f, new md2(0, settingsLauncher));
        k();
        this.a.p(pd2.h, new ie0(18, this));
        this.a.o(pd2.i, sharedPreferencesManager.e(0L, "Chrome.SafetyCheck.LastRunTimestamp"));
        new PasswordStoreBridge();
        this.h = zp1Var;
    }

    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    public final void b(int i) {
        if (i == 1 || this.i != 1 || this.a == null) {
            return;
        }
        if (i != 0) {
            j(new sb2(i, 3, this));
            return;
        }
        this.i = 3;
        if (this.f && this.g) {
            g();
        }
    }

    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    public final void c() {
        this.g = true;
        this.o = Integer.valueOf(N.Mu_fY_2N(((e) n3.i0(this.c)).a.a));
        if (this.f) {
            g();
        }
    }

    @Override // org.chromium.chrome.browser.password_manager.PasswordStoreBridge.a
    public final void d() {
        if (this.a == null) {
            return;
        }
        this.f = true;
        if (this.g) {
            g();
        }
    }

    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    public final void e() {
        if (this.a == null) {
            return;
        }
        this.f = true;
        if (this.g) {
            g();
        }
    }

    public final void g() {
        if (this.i == 1) {
            return;
        }
        ((e) n3.i0(this.c)).b.c(this);
        if (this.i == 2) {
            l();
        } else {
            j(new zx1(7, this));
        }
    }

    public final long h() {
        return Math.max(0L, (this.n + 1000) - SystemClock.elapsedRealtime());
    }

    public final void i() {
        j(null);
        Runnable runnable = this.l;
        if (runnable != null) {
            this.j.removeCallbacks(runnable);
        }
        this.l = null;
        Runnable runnable2 = this.m;
        if (runnable2 != null) {
            this.j.removeCallbacks(runnable2);
        }
        this.m = null;
        n3.X0("Settings.SafetyCheck.Start");
        oj2.U(0, 10, "Settings.SafetyCheck.Interactions");
        this.n = SystemClock.elapsedRealtime();
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.a.o(pd2.i, jCurrentTimeMillis);
        SharedPreferencesManager sharedPreferencesManager = this.p;
        sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.SafetyCheck.LastRunTimestamp");
        sharedPreferencesManager.c("Chrome.SafetyCheck.RunCounter");
        this.a.n(pd2.a, 1);
        this.a.n(pd2.c, 1);
        this.a.n(pd2.d, 1);
        eu1 eu1Var = new eu1(11, this);
        Runnable runnable3 = this.l;
        if (runnable3 != null) {
            this.j.removeCallbacks(runnable3);
        }
        this.l = eu1Var;
        this.j.postDelayed(eu1Var, h());
        this.i = 1;
        SettingsLauncher settingsLauncher = this.c;
        ((e) n3.i0(settingsLauncher)).a(this, false);
        N.MqdzTSiP(((e) n3.i0(settingsLauncher)).a.a);
        qd2 qd2Var = this.b;
        WeakReference weakReference = new WeakReference(this.q);
        rd2 rd2Var = (rd2) qd2Var;
        rd2Var.getClass();
        PostTask.c(d73.h, new l91(rd2Var, 24, weakReference));
    }

    public final void j(Runnable runnable) {
        Runnable runnable2 = this.k;
        if (runnable2 != null) {
            this.j.removeCallbacks(runnable2);
        }
        this.k = runnable;
        if (runnable != null) {
            this.j.postDelayed(runnable, h());
        }
    }

    public final void k() {
        Preference.d c0179d;
        int iH = this.a.h(pd2.a);
        int i = 1;
        if (iH == 6) {
            c0179d = new md2(i, this);
        } else {
            c0179d = (iH == 3 || iH == 2) ? new C0179d(2, this) : iH == 9 ? new Preference.d() { // from class: nd2
                @Override // androidx.preference.Preference.d
                public final boolean r(Preference preference) {
                    Context context = preference.e;
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setPackage("com.android.vending");
                    intent.putExtra("callerId", context.getPackageName());
                    context.startActivity(intent);
                    return true;
                }
            } : new sx1(i, this);
        }
        this.a.p(pd2.e, c0179d);
    }

    public final void l() {
        if (this.o.intValue() != 0) {
            this.a.n(pd2.b, this.o.intValue());
            this.a.n(pd2.a, 3);
            oj2.U(2, 10, "Settings.SafetyCheck.PasswordsResult");
        } else {
            if (this.i != 2 || this.e) {
                if (N.MDe7TasX(((e) n3.i0(this.c)).a.a) > 0) {
                    this.a.n(pd2.a, 2);
                    oj2.U(1, 10, "Settings.SafetyCheck.PasswordsResult");
                } else {
                    this.a.n(pd2.a, 5);
                    oj2.U(4, 10, "Settings.SafetyCheck.PasswordsResult");
                }
            } else {
                this.a.n(pd2.a, 0);
            }
        }
        this.i = 1;
        k();
    }

    @Override // org.chromium.chrome.browser.password_manager.PasswordStoreBridge.a
    public final void a() {
    }

    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    public final void f(int i, int i2) {
    }
}
