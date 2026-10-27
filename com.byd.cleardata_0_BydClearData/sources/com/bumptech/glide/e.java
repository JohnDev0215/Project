package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import com.bumptech.glide.c;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class e extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final l<?, ?> f1935a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.b f1936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final i f1937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.r.j.c f1938d;
    private final c.a e;
    private final List<com.bumptech.glide.r.e<Object>> f;
    private final Map<Class<?>, l<?, ?>> g;
    private final com.bumptech.glide.load.n.k h;
    private final boolean i;
    private final int j;
    private com.bumptech.glide.r.f k;

    public e(Context context, com.bumptech.glide.load.n.a0.b bVar, i iVar, com.bumptech.glide.r.j.c cVar, c.a aVar, Map<Class<?>, l<?, ?>> map, List<com.bumptech.glide.r.e<Object>> list, com.bumptech.glide.load.n.k kVar, boolean z, int i) {
        super(context.getApplicationContext());
        this.f1936b = bVar;
        this.f1937c = iVar;
        this.f1938d = cVar;
        this.e = aVar;
        this.f = list;
        this.g = map;
        this.h = kVar;
        this.i = z;
        this.j = i;
    }

    public com.bumptech.glide.load.n.a0.b a() {
        return this.f1936b;
    }

    public List<com.bumptech.glide.r.e<Object>> b() {
        return this.f;
    }

    public synchronized com.bumptech.glide.r.f c() {
        if (this.k == null) {
            this.k = this.e.a().H();
        }
        return this.k;
    }

    public <T> l<?, T> d(Class<T> cls) {
        l<?, T> lVar = (l) this.g.get(cls);
        if (lVar == null) {
            for (Map.Entry<Class<?>, l<?, ?>> entry : this.g.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    lVar = (l) entry.getValue();
                }
            }
        }
        return lVar == null ? (l<?, T>) f1935a : lVar;
    }

    public com.bumptech.glide.load.n.k e() {
        return this.h;
    }

    public int f() {
        return this.j;
    }

    public i g() {
        return this.f1937c;
    }

    public boolean h() {
        return this.i;
    }
}
