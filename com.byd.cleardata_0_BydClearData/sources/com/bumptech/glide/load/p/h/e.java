package com.bumptech.glide.load.p.h;

import com.bumptech.glide.load.n.r;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.bumptech.glide.load.p.f.b<c> implements r {
    public e(c cVar) {
        super(cVar);
    }

    @Override // com.bumptech.glide.load.p.f.b, com.bumptech.glide.load.n.r
    public void a() {
        ((c) this.f2384b).e().prepareToDraw();
    }

    @Override // com.bumptech.glide.load.n.v
    public int c() {
        return ((c) this.f2384b).i();
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<c> d() {
        return c.class;
    }

    @Override // com.bumptech.glide.load.n.v
    public void f() {
        ((c) this.f2384b).stop();
        ((c) this.f2384b).k();
    }
}
