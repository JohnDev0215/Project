package org.chromium.ui.modelutil;

import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.v62;
import defpackage.ya1;
import defpackage.za1;
import org.chromium.ui.modelutil.d;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: SimpleRecyclerViewAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public class m extends RecyclerView.e<b> {
    public final ya1 h;
    public final SparseArray<Pair<za1, j.a>> i = new SparseArray<>();

    /* JADX INFO: compiled from: SimpleRecyclerViewAdapter.java */
    public class a implements d.a<Void> {
        public a() {
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void b(int i, int i2) {
            m.this.q(i, i2);
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void f(int i, int i2) {
            m.this.p(i, i2);
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void i(int i, int i2, Object obj) {
            m.this.e.d(i, i2, null);
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void j(int i, int i2) {
            try {
                m.this.r(i, i2);
            } catch (NullPointerException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: SimpleRecyclerViewAdapter.java */
    public class b extends RecyclerView.a0 {
        public PropertyModel A;
        public j<PropertyModel, View, v62> y;
        public final j.a<PropertyModel, View, v62> z;

        public b(View view, j.a aVar) {
            super(view);
            this.z = aVar;
        }

        public final void u(PropertyModel propertyModel) {
            j<PropertyModel, View, v62> jVar = this.y;
            if (jVar != null) {
                jVar.c();
            }
            this.A = propertyModel;
            if (propertyModel == null) {
                return;
            }
            this.y = j.b(propertyModel, this.e, this.z);
        }
    }

    public m(ya1 ya1Var) {
        this.h = ya1Var;
        ya1Var.g(new a());
    }

    public View D(int i, ViewGroup viewGroup) {
        return ((za1) this.i.get(i).first).a(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public void t(b bVar, int i) {
        bVar.u(this.h.get(i).b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public b v(int i, RecyclerView recyclerView) {
        return new b(D(i, recyclerView), (j.a) this.i.get(i).second);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public void z(b bVar) {
        bVar.u(null);
    }

    public final <T extends View> void H(int i, za1<T> za1Var, j.a<PropertyModel, T, v62> aVar) {
        this.i.put(i, new Pair<>(za1Var, aVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.h.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int m(int i) {
        return this.h.get(i).a;
    }
}
