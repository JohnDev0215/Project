package defpackage;

import androidx.appcompat.app.AppCompatDelegate;
import defpackage.fy2;
import defpackage.on1;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: GlobalNightModeStateController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zs0 implements on1, fy2.a, ApplicationStatus.d {
    public final fy2 f;
    public final SharedPreferencesManager g;
    public final i32 h;
    public Boolean j;
    public boolean l;
    public final b<on1.a> e = new b<>();
    public final ql0 i = new ql0(15, this);
    public final pq2 k = new pq2(21, this);

    public zs0(fy2 fy2Var, i32 i32Var, SharedPreferencesManager sharedPreferencesManager) {
        this.f = fy2Var;
        this.g = sharedPreferencesManager;
        this.h = i32Var;
        g();
        int stateForApplication = ApplicationStatus.getStateForApplication();
        if (stateForApplication == 1 || stateForApplication == 2) {
            f();
        }
        ApplicationStatus.e(this);
    }

    @Override // fy2.a
    public final void a() {
        g();
    }

    @Override // defpackage.on1
    public final void b(on1.a aVar) {
        this.e.a(aVar);
    }

    @Override // defpackage.on1
    public final boolean d() {
        Boolean bool = this.j;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // defpackage.on1
    public final void e(on1.a aVar) {
        this.e.c(aVar);
    }

    public final void f() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.f.a.a(this);
        this.h.a.a(this.i);
        this.g.a(this.k);
        g();
    }

    public final void g() {
        boolean z = this.h.d;
        int iD = SharedPreferencesManager.getInstance().d(-1, "ui_theme_setting");
        if (iD == -1) {
            iD = 0;
        }
        boolean z2 = (iD == 0 && (z || this.f.b)) || iD == 2;
        Boolean bool = this.j;
        if (bool == null || z2 != bool.booleanValue()) {
            Boolean boolValueOf = Boolean.valueOf(z2);
            this.j = boolValueOf;
            AppCompatDelegate.B(boolValueOf.booleanValue() ? 2 : 1);
            Iterator<on1.a> it = this.e.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((on1.a) aVar.next()).X0();
                }
            }
            oj2.O("Android.DarkTheme.EnabledState", this.j.booleanValue());
            oj2.U(iD, 3, "Android.DarkTheme.Preference.State");
            if (this.j.booleanValue()) {
                oj2.U(iD != 2 ? z ? 1 : 2 : 0, 3, "Android.DarkTheme.EnabledReason");
            }
        }
    }

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i) {
        if (i == 1) {
            f();
            return;
        }
        if (i == 3 && this.l) {
            this.l = false;
            this.f.a.c(this);
            this.h.a.c(this.i);
            this.g.k(this.k);
        }
    }
}
