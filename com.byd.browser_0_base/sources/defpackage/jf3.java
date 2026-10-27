package defpackage;

import defpackage.o23;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: TrustedWebActivityBrowserControlsVisibilityManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class jf3 {
    public final o23 a;
    public final a30 b;
    public final u40 c;
    public final mu d;
    public boolean e;
    public final boolean f;
    public final boolean g;
    public int h = 3;
    public final a i = new a();

    /* JADX INFO: compiled from: TrustedWebActivityBrowserControlsVisibilityManager.java */
    public class a extends o23.b {
        public a() {
        }

        @Override // o23.b
        public final void A1(Tab tab) {
            jf3 jf3Var = jf3.this;
            jf3Var.b();
            jf3Var.c();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void R0(TabImpl tabImpl) {
            jf3 jf3Var = jf3.this;
            jf3Var.b();
            jf3Var.c();
        }
    }

    public jf3(o23 o23Var, a30 a30Var, u40 u40Var, mu muVar, oj ojVar) {
        this.a = o23Var;
        this.b = a30Var;
        this.c = u40Var;
        this.d = muVar;
        js3 js3VarM = ojVar.M();
        this.g = js3VarM != null;
        this.f = js3VarM != null && js3VarM.g == 2;
    }

    public final boolean a(Tab tab) {
        if (this.e) {
            return (tab != null && CriticalPersistedTabData.m(tab).p != -1) && this.g;
        }
        return true;
    }

    public final void b() {
        Tab tab = this.b.b;
        int i = (tab == null || n3.o0(tab.c()) != 5) ? ((this.e && this.f) || a(tab)) ? 3 : 2 : 1;
        if (this.h == i) {
            return;
        }
        this.h = i;
        u40 u40Var = this.c;
        g30 g30Var = u40Var.i;
        if (i != g30Var.i) {
            g30Var.i = i;
            g30Var.t();
        }
        p61<mj> p61Var = u40Var.f;
        if (i == 2) {
            u40Var.l = p61Var.get().c(u40Var.l);
        } else {
            p61Var.get().q(u40Var.l);
        }
        if (this.h == 3) {
            u40Var.f.get().i().x();
        }
    }

    public final void c() {
        boolean z = a(this.b.b) || this.h == 2;
        mu muVar = this.d;
        if (muVar.c == z) {
            return;
        }
        muVar.c = z;
        g gVar = muVar.b;
        if (gVar == null) {
            return;
        }
        gVar.k.a.setCloseButtonImageResource(z ? muVar.a : null);
    }
}
