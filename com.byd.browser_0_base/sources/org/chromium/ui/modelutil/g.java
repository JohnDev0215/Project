package org.chromium.ui.modelutil;

import defpackage.f52;
import defpackage.v62;
import defpackage.xa1;
import defpackage.ya1;
import java.util.HashSet;
import java.util.Set;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.ui.modelutil.d;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: ModelListPropertyChangeFilter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class g implements d.a<Void>, k.a<v62> {
    public final Runnable e;
    public final ya1 f;
    public final Set<v62> g;
    public Set<PropertyModel> h = new HashSet();

    public g(f52 f52Var, n0 n0Var, HashSet hashSet) {
        this.e = f52Var;
        this.f = n0Var;
        this.g = hashSet;
        n0Var.g(this);
        b(0, n0Var.size());
    }

    @Override // org.chromium.ui.modelutil.k.a
    public final void a(k<v62> kVar, v62 v62Var) {
        if (this.g.contains(v62Var)) {
            this.e.run();
        }
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void b(int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            xa1 xa1Var = this.f.get(i + i3);
            xa1Var.b.a(this);
            this.h.add(xa1Var.b);
        }
        this.e.run();
    }

    public final void c(Set<PropertyModel> set) {
        for (PropertyModel propertyModel : this.h) {
            if (!set.contains(propertyModel)) {
                propertyModel.d(this);
            }
        }
        this.h = set;
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void j(int i, int i2) {
        HashSet hashSet = new HashSet();
        int i3 = 0;
        while (true) {
            ya1 ya1Var = this.f;
            if (i3 >= ya1Var.size()) {
                c(hashSet);
                this.e.run();
                return;
            } else {
                hashSet.add(ya1Var.get(i3).b);
                i3++;
            }
        }
    }
}
