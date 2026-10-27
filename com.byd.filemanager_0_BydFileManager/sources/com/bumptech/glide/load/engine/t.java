package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.i.n;
import java.io.File;
import java.util.List;

/* JADX INFO: compiled from: ResourceCacheGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class t implements e, d.a<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e.a f3075b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f<?> f3076c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3077d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3078e = -1;
    private com.bumptech.glide.load.c f;
    private List<com.bumptech.glide.load.i.n<File, ?>> g;
    private int h;
    private volatile n.a<?> i;
    private File j;
    private u k;

    t(f<?> fVar, e.a aVar) {
        this.f3076c = fVar;
        this.f3075b = aVar;
    }

    private boolean b() {
        return this.h < this.g.size();
    }

    @Override // com.bumptech.glide.load.engine.e
    public boolean a() {
        List<com.bumptech.glide.load.c> listC = this.f3076c.c();
        boolean z = false;
        if (listC.isEmpty()) {
            return false;
        }
        List<Class<?>> listM = this.f3076c.m();
        if (listM.isEmpty()) {
            if (File.class.equals(this.f3076c.q())) {
                return false;
            }
            throw new IllegalStateException("Failed to find any load path from " + this.f3076c.i() + " to " + this.f3076c.q());
        }
        while (true) {
            if (this.g != null && b()) {
                this.i = null;
                while (!z && b()) {
                    List<com.bumptech.glide.load.i.n<File, ?>> list = this.g;
                    int i = this.h;
                    this.h = i + 1;
                    this.i = list.get(i).a(this.j, this.f3076c.s(), this.f3076c.f(), this.f3076c.k());
                    if (this.i != null && this.f3076c.t(this.i.f3223c.a())) {
                        this.i.f3223c.e(this.f3076c.l(), this);
                        z = true;
                    }
                }
                return z;
            }
            int i2 = this.f3078e + 1;
            this.f3078e = i2;
            if (i2 >= listM.size()) {
                int i3 = this.f3077d + 1;
                this.f3077d = i3;
                if (i3 >= listC.size()) {
                    return false;
                }
                this.f3078e = 0;
            }
            com.bumptech.glide.load.c cVar = listC.get(this.f3077d);
            Class<?> cls = listM.get(this.f3078e);
            this.k = new u(this.f3076c.b(), cVar, this.f3076c.o(), this.f3076c.s(), this.f3076c.f(), this.f3076c.r(cls), cls, this.f3076c.k());
            File fileB = this.f3076c.d().b(this.k);
            this.j = fileB;
            if (fileB != null) {
                this.f = cVar;
                this.g = this.f3076c.j(fileB);
                this.h = 0;
            }
        }
    }

    @Override // com.bumptech.glide.load.engine.e
    public void cancel() {
        n.a<?> aVar = this.i;
        if (aVar != null) {
            aVar.f3223c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.d.a
    public void d(Exception exc) {
        this.f3075b.d(this.k, exc, this.i.f3223c, DataSource.RESOURCE_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.data.d.a
    public void f(Object obj) {
        this.f3075b.c(this.f, obj, this.i.f3223c, DataSource.RESOURCE_DISK_CACHE, this.k);
    }
}
