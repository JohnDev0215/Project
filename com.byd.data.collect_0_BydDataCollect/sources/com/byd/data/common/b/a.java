package com.byd.data.common.b;

import com.byd.data.common.c.d.a;
import com.byd.data.common.http.ApiRetrofit;
import e.a.e;

/* JADX INFO: compiled from: BaseHttpPresent.java */
/* JADX INFO: loaded from: classes.dex */
public class a<V extends com.byd.data.common.c.d.a> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e.a.k.a f3291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public V f3292c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected String f3290a = getClass().getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected com.byd.data.common.http.c.a f3293d = ApiRetrofit.h().d();

    public a(V v) {
        this.f3292c = v;
    }

    public void a(e<?> eVar, c cVar) {
        if (this.f3291b == null) {
            this.f3291b = new e.a.k.a();
        }
        this.f3291b.c((e.a.k.b) eVar.i(e.a.q.a.a()).d(e.a.j.b.a.a()).j(cVar));
    }
}
