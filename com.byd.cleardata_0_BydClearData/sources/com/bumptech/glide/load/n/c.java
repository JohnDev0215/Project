package com.bumptech.glide.load.n;

import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.n.f;
import com.bumptech.glide.load.o.n;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class c implements f, d.a<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.load.g> f2089b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g<?> f2090c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f.a f2091d;
    private int e;
    private com.bumptech.glide.load.g f;
    private List<com.bumptech.glide.load.o.n<File, ?>> g;
    private int h;
    private volatile n.a<?> i;
    private File j;

    c(g<?> gVar, f.a aVar) {
        this(gVar.c(), gVar, aVar);
    }

    c(List<com.bumptech.glide.load.g> list, g<?> gVar, f.a aVar) {
        this.e = -1;
        this.f2089b = list;
        this.f2090c = gVar;
        this.f2091d = aVar;
    }

    private boolean b() {
        return this.h < this.g.size();
    }

    @Override // com.bumptech.glide.load.n.f
    public boolean a() {
        while (true) {
            boolean z = false;
            if (this.g != null && b()) {
                this.i = null;
                while (!z && b()) {
                    List<com.bumptech.glide.load.o.n<File, ?>> list = this.g;
                    int i = this.h;
                    this.h = i + 1;
                    this.i = list.get(i).a(this.j, this.f2090c.s(), this.f2090c.f(), this.f2090c.k());
                    if (this.i != null && this.f2090c.t(this.i.f2252c.a())) {
                        this.i.f2252c.e(this.f2090c.l(), this);
                        z = true;
                    }
                }
                return z;
            }
            int i2 = this.e + 1;
            this.e = i2;
            if (i2 >= this.f2089b.size()) {
                return false;
            }
            com.bumptech.glide.load.g gVar = this.f2089b.get(this.e);
            File fileB = this.f2090c.d().b(new d(gVar, this.f2090c.o()));
            this.j = fileB;
            if (fileB != null) {
                this.f = gVar;
                this.g = this.f2090c.j(fileB);
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
        this.f2091d.d(this.f, exc, this.i.f2252c, com.bumptech.glide.load.a.DATA_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.data.d.a
    public void f(Object obj) {
        this.f2091d.c(this.f, obj, this.i.f2252c, com.bumptech.glide.load.a.DATA_DISK_CACHE, this.f);
    }
}
