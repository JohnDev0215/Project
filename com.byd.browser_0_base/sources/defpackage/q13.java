package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TabModelFilterProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q13 implements e23 {
    public List<p13> e = Collections.emptyList();
    public final ArrayList f = new ArrayList();

    public final void a(t13 t13Var) {
        if (this.e.isEmpty()) {
            this.f.add(t13Var);
            return;
        }
        for (int i = 0; i < this.e.size(); i++) {
            this.e.get(i).f.a(t13Var);
        }
    }

    public final p13 c() {
        for (int i = 0; i < this.e.size(); i++) {
            if (this.e.get(i).e.isActiveModel()) {
                return this.e.get(i);
            }
        }
        return null;
    }

    public final p13 d(boolean z) {
        for (int i = 0; i < this.e.size(); i++) {
            if (this.e.get(i).isIncognito() == z) {
                return this.e.get(i);
            }
        }
        return null;
    }

    public final void e(t13 t13Var) {
        if (this.e.isEmpty()) {
            ArrayList arrayList = this.f;
            if (!arrayList.isEmpty()) {
                arrayList.remove(t13Var);
                return;
            }
        }
        for (int i = 0; i < this.e.size(); i++) {
            this.e.get(i).f.c(t13Var);
        }
    }

    @Override // defpackage.e23
    public final void g() {
        Iterator<p13> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().G();
        }
    }
}
