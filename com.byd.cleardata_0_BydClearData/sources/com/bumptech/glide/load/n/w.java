package com.bumptech.glide.load.n;

import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.n.f;
import com.bumptech.glide.load.o.n;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class w implements f, d.a<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f.a f2193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g<?> f2194c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2195d;
    private int e = -1;
    private com.bumptech.glide.load.g f;
    private List<com.bumptech.glide.load.o.n<File, ?>> g;
    private int h;
    private volatile n.a<?> i;
    private File j;
    private x k;

    w(g<?> gVar, f.a aVar) {
        this.f2194c = gVar;
        this.f2193b = aVar;
    }

    private boolean b() {
        return this.h < this.g.size();
    }

    @Override // com.bumptech.glide.load.n.f
    public boolean a() {
        List<com.bumptech.glide.load.g> listC = this.f2194c.c();
        boolean z = false;
        if (listC.isEmpty()) {
            return false;
        }
        List<Class<?>> listM = this.f2194c.m();
        if (listM.isEmpty()) {
            if (File.class.equals(this.f2194c.q())) {
                return false;
            }
            throw new IllegalStateException("Failed to find any load path from " + this.f2194c.i() + " to " + this.f2194c.q());
        }
        while (true) {
            if (this.g != null && b()) {
                this.i = null;
                while (!z && b()) {
                    List<com.bumptech.glide.load.o.n<File, ?>> list = this.g;
                    int i = this.h;
                    this.h = i + 1;
                    this.i = list.get(i).a(this.j, this.f2194c.s(), this.f2194c.f(), this.f2194c.k());
                    if (this.i != null && this.f2194c.t(this.i.f2252c.a())) {
                        this.i.f2252c.e(this.f2194c.l(), this);
                        z = true;
                    }
                }
                return z;
            }
            int i2 = this.e + 1;
            this.e = i2;
            if (i2 >= listM.size()) {
                int i3 = this.f2195d + 1;
                this.f2195d = i3;
                if (i3 >= listC.size()) {
                    return false;
                }
                this.e = 0;
            }
            com.bumptech.glide.load.g gVar = listC.get(this.f2195d);
            Class<?> cls = listM.get(this.e);
            this.k = new x(this.f2194c.b(), gVar, this.f2194c.o(), this.f2194c.s(), this.f2194c.f(), this.f2194c.r(cls), cls, this.f2194c.k());
            File fileB = this.f2194c.d().b(this.k);
            this.j = fileB;
            if (fileB != null) {
                this.f = gVar;
                this.g = this.f2194c.j(fileB);
                this.h = 0;
            }
        }
    }

    @Override // com.bumptech.glide.load.n.f
    public void cancel() {
        n.a<?> aVar = this.i;
        if (aVar != null) {
            aVar.f2252c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.d.a
    public void d(Exception exc) {
        this.f2193b.d(this.k, exc, this.i.f2252c, com.bumptech.glide.load.a.RESOURCE_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.data.d.a
    public void f(Object obj) {
        this.f2193b.c(this.f, obj, this.i.f2252c, com.bumptech.glide.load.a.RESOURCE_DISK_CACHE, this.k);
    }
}
