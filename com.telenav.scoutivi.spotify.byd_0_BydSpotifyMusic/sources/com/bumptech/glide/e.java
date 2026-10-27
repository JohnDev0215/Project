package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.ImageView;
import com.bumptech.glide.c;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GlideContext.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends ContextWrapper {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static final l<?, ?> f3969k = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r1.b f3970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f3971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final h2.f f3972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.a f3973d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<g2.e<Object>> f3974e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Map<Class<?>, l<?, ?>> f3975f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final q1.k f3976g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final boolean f3977h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f3978i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private g2.f f3979j;

    public e(Context context, r1.b bVar, i iVar, h2.f fVar, c.a aVar, Map<Class<?>, l<?, ?>> map, List<g2.e<Object>> list, q1.k kVar, boolean z6, int i7) {
        super(context.getApplicationContext());
        this.f3970a = bVar;
        this.f3971b = iVar;
        this.f3972c = fVar;
        this.f3973d = aVar;
        this.f3974e = list;
        this.f3975f = map;
        this.f3976g = kVar;
        this.f3977h = z6;
        this.f3978i = i7;
    }

    public <X> h2.i<ImageView, X> a(ImageView imageView, Class<X> cls) {
        return this.f3972c.a(imageView, cls);
    }

    public r1.b b() {
        return this.f3970a;
    }

    public List<g2.e<Object>> c() {
        return this.f3974e;
    }

    public synchronized g2.f d() {
        if (this.f3979j == null) {
            this.f3979j = this.f3973d.a().K();
        }
        return this.f3979j;
    }

    public <T> l<?, T> e(Class<T> cls) {
        l<?, T> lVar = (l) this.f3975f.get(cls);
        if (lVar == null) {
            for (Map.Entry<Class<?>, l<?, ?>> entry : this.f3975f.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    lVar = (l) entry.getValue();
                }
            }
        }
        return lVar == null ? (l<?, T>) f3969k : lVar;
    }

    public q1.k f() {
        return this.f3976g;
    }

    public int g() {
        return this.f3978i;
    }

    public i h() {
        return this.f3971b;
    }

    public boolean i() {
        return this.f3977h;
    }
}
