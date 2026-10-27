package defpackage;

import java.util.ArrayList;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabModelSelectorTabModelObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public class h23 implements t13 {
    public final y13 e;
    public e23 f;

    /* JADX INFO: compiled from: TabModelSelectorTabModelObserver.java */
    public class a implements e23 {
        public a() {
        }

        @Override // defpackage.e23
        public final void b() {
            h23 h23Var = h23.this;
            h23Var.e.l(this);
            h23Var.f = null;
            h23Var.c();
        }

        @Override // defpackage.e23
        public final void i(Tab tab) {
            throw new IllegalStateException("onChange should have happened and unregistered this listener.");
        }
    }

    public h23(y13 y13Var) {
        this.e = y13Var;
        if (!y13Var.y().isEmpty()) {
            c();
            return;
        }
        a aVar = new a();
        this.f = aVar;
        y13Var.m(aVar);
    }

    public final void a() {
        e23 e23Var = this.f;
        y13 y13Var = this.e;
        if (e23Var != null) {
            y13Var.l(e23Var);
            this.f = null;
        }
        ArrayList arrayListY = y13Var.y();
        for (int i = 0; i < arrayListY.size(); i++) {
            ((TabModel) arrayListY.get(i)).l(this);
        }
    }

    public final void c() {
        ArrayList arrayListY = this.e.y();
        for (int i = 0; i < arrayListY.size(); i++) {
            ((TabModel) arrayListY.get(i)).j(this);
        }
        b();
    }

    public void b() {
    }
}
