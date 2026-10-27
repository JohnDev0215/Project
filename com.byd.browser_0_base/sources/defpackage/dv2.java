package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: StripLayoutHelperManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class dv2 extends h23 {
    public final /* synthetic */ zu2 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public dv2(zu2 zu2Var, y13 y13Var) {
        super(y13Var);
        this.g = zu2Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        if (tab.getId() == i2) {
            return;
        }
        this.g.k(tab.isIncognito()).C(SystemClock.uptimeMillis(), tab.getId(), i2, false);
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        boolean zIsIncognito = tab.isIncognito();
        zu2 zu2Var = this.g;
        vu2 vu2VarK = zu2Var.k(zIsIncognito);
        long jUptimeMillis = SystemClock.uptimeMillis();
        int id = tab.getId();
        Tab tabP = z7.p(vu2VarK.c);
        vu2VarK.A(jUptimeMillis, id, -1, (tabP == null ? -1 : tabP.getId()) == id, true, false);
        zu2Var.s();
    }

    @Override // defpackage.t13
    public final void V0(Tab tab) {
        boolean zIsIncognito = tab.isIncognito();
        zu2 zu2Var = this.g;
        vu2 vu2VarK = zu2Var.k(zIsIncognito);
        SystemClock.uptimeMillis();
        vu2VarK.z(tab.getId());
        zu2Var.s();
    }

    @Override // defpackage.t13
    public final void X0(boolean z) {
        zu2 zu2Var = this.g;
        vu2 vu2VarK = zu2Var.k(z);
        vu2VarK.c(true);
        ((h61) vu2VarK.a).T(null);
        zu2Var.s();
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        zu2 zu2Var = this.g;
        zu2Var.k(tab.isIncognito()).A(SystemClock.uptimeMillis(), tab.getId(), zu2Var.f.k(), i != 5 || (zu2Var.f.r() && tab.isIncognito()), false, i == 3);
    }

    @Override // defpackage.t13
    public final void h0(Tab tab) {
        boolean zIsIncognito = tab.isIncognito();
        zu2 zu2Var = this.g;
        vu2 vu2VarK = zu2Var.k(zIsIncognito);
        SystemClock.uptimeMillis();
        vu2VarK.z(tab.getId());
        zu2Var.s();
    }

    @Override // defpackage.t13
    public final void h1(int i, int i2, Tab tab) {
        vu2 vu2VarK = this.g.k(tab.isIncognito());
        SystemClock.uptimeMillis();
        int id = tab.getId();
        if (i > i2) {
            i++;
        }
        vu2VarK.p(id, i2, i, false);
        vu2VarK.K();
        ((h61) vu2VarK.a).T(null);
    }

    @Override // defpackage.t13
    public final void i1(Tab tab) {
        zu2 zu2Var = this.g;
        if (zu2Var.A.f()) {
            zu2Var.A.get().b(tab.getId());
        }
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        boolean zIsIncognito = tab.isIncognito();
        zu2 zu2Var = this.g;
        vu2 vu2VarK = zu2Var.k(zIsIncognito);
        SystemClock.uptimeMillis();
        vu2VarK.z(tab.getId());
        zu2Var.s();
    }
}
