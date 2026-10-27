package com.byd.cleardata.f.c;

import com.byd.cleardata.f.b.b;
import com.byd.cleardata.f.d.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<V extends com.byd.cleardata.f.d.a, M extends com.byd.cleardata.f.b.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected V f2616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected M f2617b = (M) c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f2618c;

    public b(V v) {
        this.f2616a = v;
    }

    public void a(V v) {
        com.byd.cleardata.i.c.b("attachView");
        this.f2616a = v;
    }

    public void b() {
        com.byd.cleardata.i.c.b("detachView " + getClass().getSimpleName());
        this.f2616a = null;
        M m = this.f2617b;
        if (m != null) {
            m.a();
            this.f2617b = null;
        }
    }

    public abstract M c();

    public boolean d() {
        return this.f2618c;
    }

    public void e(boolean z) {
        this.f2618c = z;
    }
}
