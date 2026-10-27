package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import java.io.File;
import java.util.List;

/* JADX INFO: compiled from: ResourceCacheGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class t implements e, d.a<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e.a f2831b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f<?> f2832c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2833d;
    private int e = -1;
    private com.bumptech.glide.load.c f;
    private List<com.bumptech.glide.load.j.n<File, ?>> g;
    private int h;
    private volatile n.a<?> i;
    private File j;
    private u k;

    t(f<?> fVar, e.a aVar) {
        this.f2832c = fVar;
        this.f2831b = aVar;
    }

    private boolean a() {
        return this.h < this.g.size();
    }

    @Override // com.bumptech.glide.load.i.d.a
    public void c(Exception exc) {
        this.f2831b.b(this.k, exc, this.i.f2998c, DataSource.RESOURCE_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.engine.e
    public void cancel() {
        n.a<?> aVar = this.i;
        if (aVar != null) {
            aVar.f2998c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.i.d.a
    public void d(Object obj) {
        this.f2831b.c(this.f, obj, this.i.f2998c, DataSource.RESOURCE_DISK_CACHE, this.k);
    }

    @Override // com.bumptech.glide.load.engine.e
    public boolean e() {
        List<com.bumptech.glide.load.c> listC = this.f2832c.c();
        boolean z = false;
        if (listC.isEmpty()) {
            return false;
        }
        List<Class<?>> listM = this.f2832c.m();
        if (listM.isEmpty()) {
            if (File.class.equals(this.f2832c.q())) {
                return false;
            }
            throw new IllegalStateException("Failed to find any load path from " + this.f2832c.i() + " to " + this.f2832c.q());
        }
        while (true) {
            if (this.g != null && a()) {
                this.i = null;
                while (!z && a()) {
                    List<com.bumptech.glide.load.j.n<File, ?>> list = this.g;
                    int i = this.h;
                    this.h = i + 1;
                    this.i = list.get(i).a(this.j, this.f2832c.s(), this.f2832c.f(), this.f2832c.k());
                    if (this.i != null && this.f2832c.t(this.i.f2998c.a())) {
                        this.i.f2998c.f(this.f2832c.l(), this);
                        z = true;
                    }
                }
                return z;
            }
            int i2 = this.e + 1;
            this.e = i2;
            if (i2 >= listM.size()) {
                int i3 = this.f2833d + 1;
                this.f2833d = i3;
                if (i3 >= listC.size()) {
                    return false;
                }
                this.e = 0;
            }
            com.bumptech.glide.load.c cVar = listC.get(this.f2833d);
            Class<?> cls = listM.get(this.e);
            this.k = new u(this.f2832c.b(), cVar, this.f2832c.o(), this.f2832c.s(), this.f2832c.f(), this.f2832c.r(cls), cls, this.f2832c.k());
            File fileB = this.f2832c.d().b(this.k);
            this.j = fileB;
            if (fileB != null) {
                this.f = cVar;
                this.g = this.f2832c.j(fileB);
                this.h = 0;
            }
        }
    }
}
