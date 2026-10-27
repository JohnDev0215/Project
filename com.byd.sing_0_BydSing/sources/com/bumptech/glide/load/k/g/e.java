package com.bumptech.glide.load.k.g;

import com.bumptech.glide.load.engine.o;

/* JADX INFO: compiled from: GifDrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends com.bumptech.glide.load.k.e.b<c> implements o {
    public e(c cVar) {
        super(cVar);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
        ((c) this.f3063b).stop();
        ((c) this.f3063b).k();
    }

    @Override // com.bumptech.glide.load.engine.s
    public int b() {
        return ((c) this.f3063b).i();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<c> c() {
        return c.class;
    }

    @Override // com.bumptech.glide.load.k.e.b, com.bumptech.glide.load.engine.o
    public void initialize() {
        ((c) this.f3063b).e().prepareToDraw();
    }
}
