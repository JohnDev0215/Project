package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: TabContextObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class cz2 {
    public final a a;
    public final b b;
    public final y13 c;

    /* JADX INFO: compiled from: TabContextObserver.java */
    public class a extends j23 {
        public a(y13 y13Var) {
            super(y13Var);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void Z(TabImpl tabImpl) {
            cz2.this.a();
        }
    }

    /* JADX INFO: compiled from: TabContextObserver.java */
    public class b implements t13 {
        public b() {
        }

        @Override // defpackage.t13
        public final void C(Tab tab) {
            cz2.this.a();
        }

        @Override // defpackage.t13
        public final void f1(Tab tab, int i, int i2, boolean z) {
            cz2.this.a();
        }

        @Override // defpackage.t13
        public final void h1(int i, int i2, Tab tab) {
            cz2.this.a();
        }

        @Override // defpackage.t13
        public final void t(Tab tab, boolean z, boolean z2) {
            cz2.this.a();
        }
    }

    public cz2(y13 y13Var) {
        this.a = new a(y13Var);
        b bVar = new b();
        this.b = bVar;
        y13Var.u().a(bVar);
        this.c = y13Var;
    }

    public abstract void a();
}
