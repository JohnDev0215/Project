package org.chromium.ui.modelutil;

import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.widget.BaseAdapter;
import defpackage.v62;
import defpackage.ya1;
import defpackage.za1;
import org.chromium.ui.modelutil.d;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: ModelListAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public class f extends BaseAdapter {
    public final ya1 e;
    public final SparseArray<Pair<za1, j.a>> f = new SparseArray<>();

    /* JADX INFO: compiled from: ModelListAdapter.java */
    public class a implements d.a<Void> {
        public a() {
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void b(int i, int i2) {
            f.this.notifyDataSetChanged();
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void f(int i, int i2) {
            f.this.notifyDataSetChanged();
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void i(int i, int i2, Object obj) {
            f.this.notifyDataSetChanged();
        }

        @Override // org.chromium.ui.modelutil.d.a
        public final void j(int i, int i2) {
            f.this.notifyDataSetChanged();
        }
    }

    public f(ya1 ya1Var) {
        this.e = ya1Var;
        ya1Var.g(new a());
    }

    public final <T extends View> void a(int i, za1<T> za1Var, j.a<PropertyModel, T, v62> aVar) {
        this.f.put(i, new Pair<>(za1Var, aVar));
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.e.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getItemViewType(int i) {
        return this.e.get(i).a;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e7  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View getView(int r8, android.view.View r9, android.view.ViewGroup r10) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.modelutil.f.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getViewTypeCount() {
        return Math.max(1, this.f.size());
    }
}
