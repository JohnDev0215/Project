package org.chromium.chrome.browser.tasks.tab_management;

import androidx.recyclerview.widget.RecyclerView;
import defpackage.f52;
import defpackage.on0;
import defpackage.u33;
import defpackage.ya1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabStripSnapshotter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g1 {
    public static final HashSet f = CollectionUtil.e(u0.d, u0.f, u0.e, u0.k);
    public final Callback<Object> a;
    public final ya1 b;
    public final RecyclerView c;
    public final u33 d;
    public final org.chromium.ui.modelutil.g e;

    /* JADX INFO: compiled from: TabStripSnapshotter.java */
    public static class a {
        public final h0.c a;
        public final h0.d b;
        public final boolean c;
        public final boolean d;

        public a(PropertyModel propertyModel) {
            this.a = (h0.c) propertyModel.j(u0.d);
            this.b = (h0.d) propertyModel.j(u0.f);
            this.c = propertyModel.k(u0.e);
            this.d = propertyModel.k(u0.k);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Objects.equals(this.a, aVar.a) && Objects.equals(this.b, aVar.b) && this.c == aVar.c && this.d == aVar.d;
        }

        public final int hashCode() {
            return Objects.hash(this.a, this.b, Boolean.valueOf(this.c), Boolean.valueOf(this.d));
        }
    }

    /* JADX INFO: compiled from: TabStripSnapshotter.java */
    public static class b {
        public final int a;
        public final ArrayList b;

        public b(ya1 ya1Var, int i) {
            this.a = i;
            this.b = new ArrayList(ya1Var.size());
            for (int i2 = 0; i2 < ya1Var.size(); i2++) {
                this.b.add(new a(ya1Var.get(i2).b));
            }
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a != bVar.a) {
                return false;
            }
            return this.b.equals(bVar.b);
        }

        public final int hashCode() {
            return Objects.hash(this.b, Integer.valueOf(this.a));
        }
    }

    public g1(on0 on0Var, n0 n0Var, RecyclerView recyclerView) {
        this.a = on0Var;
        this.b = n0Var;
        this.c = recyclerView;
        u33 u33Var = new u33(this);
        this.d = u33Var;
        recyclerView.addOnScrollListener(u33Var);
        this.e = new org.chromium.ui.modelutil.g(new f52(13, this), n0Var, f);
    }
}
