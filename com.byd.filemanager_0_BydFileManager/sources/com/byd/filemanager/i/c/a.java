package com.byd.filemanager.i.c;

import com.byd.filemanager.i.b.b;
import com.byd.filemanager.i.d.a;

/* JADX INFO: compiled from: IPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<V extends com.byd.filemanager.i.d.a, M extends com.byd.filemanager.i.b.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected V f3570a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected M f3571b = (M) c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f3572c;

    public a(V v) {
        this.f3570a = v;
    }

    public void a(V v) {
        com.byd.filemanager.k.b.b("attachView");
        this.f3570a = v;
    }

    public void b() {
        com.byd.filemanager.k.b.b("detachView");
        this.f3570a = null;
        M m = this.f3571b;
        if (m != null) {
            m.a();
            this.f3571b = null;
        }
    }

    public abstract M c();

    public boolean d() {
        return this.f3572c;
    }

    public void e(boolean z) {
        this.f3572c = z;
    }
}
