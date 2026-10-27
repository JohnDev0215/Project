package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import com.bumptech.glide.b;
import com.bumptech.glide.load.engine.i;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GlideContext.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final g<?, ?> f2650a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f2651b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Registry f2652c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.request.h.b f2653d;
    private final b.a e;
    private final List<com.bumptech.glide.request.d<Object>> f;
    private final Map<Class<?>, g<?, ?>> g;
    private final i h;
    private final boolean i;
    private final int j;
    private com.bumptech.glide.request.e k;

    public d(Context context, com.bumptech.glide.load.engine.x.b bVar, Registry registry, com.bumptech.glide.request.h.b bVar2, b.a aVar, Map<Class<?>, g<?, ?>> map, List<com.bumptech.glide.request.d<Object>> list, i iVar, boolean z, int i) {
        super(context.getApplicationContext());
        this.f2651b = bVar;
        this.f2652c = registry;
        this.f2653d = bVar2;
        this.e = aVar;
        this.f = list;
        this.g = map;
        this.h = iVar;
        this.i = z;
        this.j = i;
    }

    public com.bumptech.glide.load.engine.x.b a() {
        return this.f2651b;
    }

    public List<com.bumptech.glide.request.d<Object>> b() {
        return this.f;
    }

    public synchronized com.bumptech.glide.request.e c() {
        if (this.k == null) {
            this.k = this.e.a().F();
        }
        return this.k;
    }

    public <T> g<?, T> d(Class<T> cls) {
        g<?, T> gVar = (g) this.g.get(cls);
        if (gVar == null) {
            for (Map.Entry<Class<?>, g<?, ?>> entry : this.g.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    gVar = (g) entry.getValue();
                }
            }
        }
        return gVar == null ? (g<?, T>) f2650a : gVar;
    }

    public i e() {
        return this.h;
    }

    public int f() {
        return this.j;
    }

    public Registry g() {
        return this.f2652c;
    }

    public boolean h() {
        return this.i;
    }
}
