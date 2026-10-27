package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.i.n;
import java.io.File;
import java.util.List;

/* JADX INFO: compiled from: DataCacheGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class b implements e, d.a<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.load.c> f3002b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f<?> f3003c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final e.a f3004d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3005e;
    private com.bumptech.glide.load.c f;
    private List<com.bumptech.glide.load.i.n<File, ?>> g;
    private int h;
    private volatile n.a<?> i;
    private File j;

    b(f<?> fVar, e.a aVar) {
        this(fVar.c(), fVar, aVar);
    }

    private boolean b() {
        return this.h < this.g.size();
    }

    @Override // com.bumptech.glide.load.engine.e
    public boolean a() {
        while (true) {
            boolean z = false;
            if (this.g != null && b()) {
                this.i = null;
                while (!z && b()) {
                    List<com.bumptech.glide.load.i.n<File, ?>> list = this.g;
                    int i = this.h;
                    this.h = i + 1;
                    this.i = list.get(i).a(this.j, this.f3003c.s(), this.f3003c.f(), this.f3003c.k());
                    if (this.i != null && this.f3003c.t(this.i.f3223c.a())) {
                        this.i.f3223c.e(this.f3003c.l(), this);
                        z = true;
                    }
                }
                return z;
            }
            int i2 = this.f3005e + 1;
            this.f3005e = i2;
            if (i2 >= this.f3002b.size()) {
                return false;
            }
            com.bumptech.glide.load.c cVar = this.f3002b.get(this.f3005e);
            File fileB = this.f3003c.d().b(new c(cVar, this.f3003c.o()));
            this.j = fileB;
            if (fileB != null) {
                this.f = cVar;
                this.g = this.f3003c.j(fileB);
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
        this.f3004d.d(this.f, exc, this.i.f3223c, DataSource.DATA_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.data.d.a
    public void f(Object obj) {
        this.f3004d.c(this.f, obj, this.i.f3223c, DataSource.DATA_DISK_CACHE, this.f);
    }

    b(List<com.bumptech.glide.load.c> list, f<?> fVar, e.a aVar) {
        this.f3005e = -1;
        this.f3002b = list;
        this.f3003c = fVar;
        this.f3004d = aVar;
    }
}
