package com.bumptech.glide.load.j.g;

import com.bumptech.glide.load.engine.o;

/* JADX INFO: compiled from: GifDrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends com.bumptech.glide.load.j.e.b<c> implements o {
    public e(c cVar) {
        super(cVar);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
        ((c) this.f3292b).stop();
        ((c) this.f3292b).k();
    }

    @Override // com.bumptech.glide.load.j.e.b, com.bumptech.glide.load.engine.o
    public void b() {
        ((c) this.f3292b).e().prepareToDraw();
    }

    @Override // com.bumptech.glide.load.engine.s
    public int c() {
        return ((c) this.f3292b).i();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<c> d() {
        return c.class;
    }
}
