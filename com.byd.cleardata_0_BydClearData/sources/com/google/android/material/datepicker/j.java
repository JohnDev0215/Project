package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;

/* JADX INFO: loaded from: classes.dex */
class j extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int f3071b = p.l().getMaximum(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final i f3072c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final d<?> f3073d;
    c e;
    final a f;

    j(i iVar, d<?> dVar, a aVar) {
        this.f3072c = iVar;
        this.f3073d = dVar;
        this.f = aVar;
    }

    private void e(Context context) {
        if (this.e == null) {
            this.e = new c(context);
        }
    }

    int a(int i) {
        return b() + (i - 1);
    }

    int b() {
        return this.f3072c.x();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i) {
        if (i < this.f3072c.x() || i > h()) {
            return null;
        }
        return Long.valueOf(this.f3072c.y(i(i)));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005e  */
    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.j.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    boolean f(int i) {
        return i % this.f3072c.f == 0;
    }

    boolean g(int i) {
        return (i + 1) % this.f3072c.f == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3072c.g + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.f3072c.f;
    }

    int h() {
        return (this.f3072c.x() + this.f3072c.g) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    int i(int i) {
        return (i - this.f3072c.x()) + 1;
    }

    boolean j(int i) {
        return i >= b() && i <= h();
    }
}
