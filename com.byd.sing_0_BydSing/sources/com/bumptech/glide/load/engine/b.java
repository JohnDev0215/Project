package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import java.io.File;
import java.util.List;

/* JADX INFO: compiled from: DataCacheGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class b implements e, d.a<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.load.c> f2768b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f<?> f2769c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final e.a f2770d;
    private int e;
    private com.bumptech.glide.load.c f;
    private List<com.bumptech.glide.load.j.n<File, ?>> g;
    private int h;
    private volatile n.a<?> i;
    private File j;

    b(f<?> fVar, e.a aVar) {
        this(fVar.c(), fVar, aVar);
    }

    private boolean a() {
        return this.h < this.g.size();
    }

    @Override // com.bumptech.glide.load.i.d.a
    public void c(Exception exc) {
        this.f2770d.b(this.f, exc, this.i.f2998c, DataSource.DATA_DISK_CACHE);
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
        this.f2770d.c(this.f, obj, this.i.f2998c, DataSource.DATA_DISK_CACHE, this.f);
    }

    @Override // com.bumptech.glide.load.engine.e
    public boolean e() {
        while (true) {
            boolean z = false;
            if (this.g != null && a()) {
                this.i = null;
                while (!z && a()) {
                    List<com.bumptech.glide.load.j.n<File, ?>> list = this.g;
                    int i = this.h;
                    this.h = i + 1;
                    this.i = list.get(i).a(this.j, this.f2769c.s(), this.f2769c.f(), this.f2769c.k());
                    if (this.i != null && this.f2769c.t(this.i.f2998c.a())) {
                        this.i.f2998c.f(this.f2769c.l(), this);
                        z = true;
                    }
                }
                return z;
            }
            int i2 = this.e + 1;
            this.e = i2;
            if (i2 >= this.f2768b.size()) {
                return false;
            }
            com.bumptech.glide.load.c cVar = this.f2768b.get(this.e);
            File fileB = this.f2769c.d().b(new c(cVar, this.f2769c.o()));
            this.j = fileB;
            if (fileB != null) {
                this.f = cVar;
                this.g = this.f2769c.j(fileB);
                this.h = 0;
            }
        }
    }

    b(List<com.bumptech.glide.load.c> list, f<?> fVar, e.a aVar) {
        this.e = -1;
        this.f2768b = list;
        this.f2769c = fVar;
        this.f2770d = aVar;
    }
}
