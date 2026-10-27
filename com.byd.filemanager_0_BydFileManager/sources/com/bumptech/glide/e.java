package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import com.bumptech.glide.c;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GlideContext.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final i<?, ?> f2852a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f2853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Registry f2854c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.request.h.c f2855d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.a f2856e;
    private final List<com.bumptech.glide.request.d<Object>> f;
    private final Map<Class<?>, i<?, ?>> g;
    private final com.bumptech.glide.load.engine.i h;
    private final boolean i;
    private final int j;
    private com.bumptech.glide.request.e k;

    public e(Context context, com.bumptech.glide.load.engine.x.b bVar, Registry registry, com.bumptech.glide.request.h.c cVar, c.a aVar, Map<Class<?>, i<?, ?>> map, List<com.bumptech.glide.request.d<Object>> list, com.bumptech.glide.load.engine.i iVar, boolean z, int i) {
        super(context.getApplicationContext());
        this.f2853b = bVar;
        this.f2854c = registry;
        this.f2855d = cVar;
        this.f2856e = aVar;
        this.f = list;
        this.g = map;
        this.h = iVar;
        this.i = z;
        this.j = i;
    }

    public com.bumptech.glide.load.engine.x.b a() {
        return this.f2853b;
    }

    public List<com.bumptech.glide.request.d<Object>> b() {
        return this.f;
    }

    public synchronized com.bumptech.glide.request.e c() {
        if (this.k == null) {
            this.k = this.f2856e.a().L();
        }
        return this.k;
    }

    public <T> i<?, T> d(Class<T> cls) {
        i<?, T> iVar = (i) this.g.get(cls);
        if (iVar == null) {
            for (Map.Entry<Class<?>, i<?, ?>> entry : this.g.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    iVar = (i) entry.getValue();
                }
            }
        }
        return iVar == null ? (i<?, T>) f2852a : iVar;
    }

    public com.bumptech.glide.load.engine.i e() {
        return this.h;
    }

    public int f() {
        return this.j;
    }

    public Registry g() {
        return this.f2854c;
    }

    public boolean h() {
        return this.i;
    }
}
