package defpackage;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabModelSelectorTabRegistrationObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k23 {
    public final a a;
    public final SparseArray<Tab> b = new SparseArray<>();
    public final org.chromium.base.b<b> c = new org.chromium.base.b<>();
    public final LinkedHashSet<Tab> d = new LinkedHashSet<>();
    public boolean e;

    /* JADX INFO: compiled from: TabModelSelectorTabRegistrationObserver.java */
    public class a extends h23 {
        public final /* synthetic */ y13 g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(y13 y13Var, y13 y13Var2) {
            super(y13Var);
            this.g = y13Var2;
        }

        @Override // defpackage.t13
        public final void C(Tab tab) {
            k23.this.b.remove(tab.getId());
        }

        @Override // defpackage.t13
        public final void V0(Tab tab) {
            k23 k23Var = k23.this;
            if (k23Var.b.get(tab.getId()) != null) {
                k23Var.b.remove(tab.getId());
                k23Var.c(tab);
            }
        }

        @Override // defpackage.h23
        public final void b() {
            k23 k23Var = k23.this;
            k23Var.e = true;
            ArrayList arrayListY = this.g.y();
            for (int i = 0; i < arrayListY.size(); i++) {
                w03 w03VarK = ((TabModel) arrayListY.get(i)).k();
                for (int i2 = 0; i2 < w03VarK.getCount(); i2++) {
                    k23.a(k23Var, w03VarK.getTabAt(i2));
                }
            }
        }

        @Override // defpackage.t13
        public final void f1(Tab tab, int i, int i2, boolean z) {
            k23.a(k23.this, tab);
        }

        @Override // defpackage.t13
        public final void n0(Tab tab) {
            k23.this.c(tab);
        }

        @Override // defpackage.t13
        public final void t(Tab tab, boolean z, boolean z2) {
            k23.this.b.put(tab.getId(), tab);
        }
    }

    /* JADX INFO: compiled from: TabModelSelectorTabRegistrationObserver.java */
    public interface b {
        void a(Tab tab);

        void b(Tab tab);
    }

    public k23(y13 y13Var) {
        this.a = new a(y13Var, y13Var);
    }

    public static void a(k23 k23Var, Tab tab) {
        if (!k23Var.d.add(tab)) {
            return;
        }
        Iterator<b> it = k23Var.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).b(tab);
            }
        }
    }

    public final void b(b bVar) {
        this.c.a(bVar);
        if (this.e) {
            Iterator<Tab> it = this.d.iterator();
            while (it.hasNext()) {
                bVar.b(it.next());
            }
        }
    }

    public final void c(Tab tab) {
        if (!this.d.remove(tab)) {
            return;
        }
        Iterator<b> it = this.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).a(tab);
            }
        }
    }
}
