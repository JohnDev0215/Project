package defpackage;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabModelSelectorBase.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z13 implements t13 {
    public final /* synthetic */ a23 e;

    public z13(a23 a23Var) {
        this.e = a23Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        this.e.E();
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        a23 a23Var = this.e;
        a23Var.E();
        Iterator<e23> it = a23Var.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((e23) aVar.next()).i(tab);
            }
        }
    }

    @Override // defpackage.t13
    public final void h1(int i, int i2, Tab tab) {
        this.e.E();
    }
}
