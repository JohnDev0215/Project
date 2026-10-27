package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: TabGroupTitleEditor.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class h03 {
    public final y13 a;
    public final a b;
    public final b c;

    /* JADX INFO: compiled from: TabGroupTitleEditor.java */
    public class a implements t13 {
        public a() {
        }

        @Override // defpackage.t13
        public final void i1(Tab tab) {
            int i = CriticalPersistedTabData.m(tab).q;
            h03 h03Var = h03.this;
            if (((g03) h03Var.a.u().c()).N(i).size() == 1) {
                h03Var.a(i);
            }
        }
    }

    /* JADX INFO: compiled from: TabGroupTitleEditor.java */
    public class b extends kh0 {
        public b() {
        }

        @Override // defpackage.kh0, g03.a
        public final void a(int i, Tab tab) {
            int i2 = CriticalPersistedTabData.m(tab).q;
            h03 h03Var = h03.this;
            String strB = h03Var.b(i2);
            String strB2 = h03Var.b(i);
            if (strB == null) {
                return;
            }
            h03Var.a(CriticalPersistedTabData.m(tab).q);
            if (strB2 == null) {
                h03Var.c(i, strB);
            }
        }

        @Override // defpackage.kh0, g03.a
        public final void b(int i, Tab tab) {
            h03 h03Var = h03.this;
            g03 g03Var = (g03) h03Var.a.u().c();
            String strB = h03Var.b(CriticalPersistedTabData.m(tab).q);
            if (strB == null) {
                return;
            }
            if (g03Var.B(tab.getId()).size() == 2) {
                h03Var.a(CriticalPersistedTabData.m(tab).q);
            } else if (CriticalPersistedTabData.m(tab).q != i) {
                h03Var.a(CriticalPersistedTabData.m(tab).q);
                h03Var.c(i, strB);
            }
        }
    }

    public h03(y13 y13Var) {
        this.a = y13Var;
        a aVar = new a();
        this.b = aVar;
        b bVar = new b();
        this.c = bVar;
        y13Var.u().a(aVar);
        ((g03) y13Var.u().d(false)).K(bVar);
        ((g03) y13Var.u().d(true)).K(bVar);
    }

    public abstract void a(int i);

    public abstract String b(int i);

    public abstract void c(int i, String str);

    public abstract void d(Tab tab, String str);
}
