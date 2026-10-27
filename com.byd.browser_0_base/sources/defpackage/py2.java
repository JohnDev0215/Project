package defpackage;

import J.N;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: TabBrowserControlsConstraintsHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class py2 implements lk3 {
    public final TabImpl e;
    public final lj1 f = new lj1(28, this);
    public long g;
    public lj h;

    public py2(TabImpl tabImpl) {
        this.e = tabImpl;
        tabImpl.x(new oy2(this));
        if (!tabImpl.isInitialized() || TabImpl.S(tabImpl)) {
            return;
        }
        f();
    }

    public static py2 a(Tab tab) {
        return (py2) tab.B().b(py2.class);
    }

    public static void d(Tab tab, boolean z) {
        if (tab == null || a(tab) == null) {
            return;
        }
        a(tab).c(1, z);
    }

    public final int b() {
        lj ljVar = this.h;
        if (ljVar == null) {
            return 3;
        }
        return ((Integer) ljVar.f).intValue();
    }

    public final void c(int i, boolean z) {
        int iB = b();
        if (iB == 2 && i == 1) {
            return;
        }
        if (iB == 1 && i == 2) {
            return;
        }
        if (this.g == 0) {
            this.g = N.MnC9A38r(this);
        }
        N.MQyQC7UK(this.g, this, this.e.g, iB, i, z);
    }

    @Override // defpackage.lk3
    public final void destroy() {
        long j = this.g;
        if (j != 0) {
            N.M3JHMcaQ(j, this);
        }
    }

    public final void e() {
        if (this.e.isFrozen()) {
            return;
        }
        c(3, b() != 2);
    }

    public final void f() {
        lj ljVar = this.h;
        lj1 lj1Var = this.f;
        if (ljVar != null) {
            ljVar.d(lj1Var);
        }
        TabImpl tabImpl = this.e;
        lj ljVarC = tabImpl.x.c(tabImpl);
        this.h = ljVarC;
        if (ljVarC != null) {
            ljVarC.p(lj1Var);
        }
    }
}
