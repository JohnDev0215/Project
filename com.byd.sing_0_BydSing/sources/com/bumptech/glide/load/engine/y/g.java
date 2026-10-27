package com.bumptech.glide.load.engine.y;

import android.annotation.SuppressLint;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.load.engine.y.h;

/* JADX INFO: compiled from: LruResourceCache.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends com.bumptech.glide.m.g<com.bumptech.glide.load.c, s<?>> implements h {
    private h.a e;

    public g(long j) {
        super(j);
    }

    @Override // com.bumptech.glide.load.engine.y.h
    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (i >= 40) {
            b();
        } else if (i >= 20 || i == 15) {
            m(h() / 2);
        }
    }

    @Override // com.bumptech.glide.load.engine.y.h
    public void c(h.a aVar) {
        this.e = aVar;
    }

    @Override // com.bumptech.glide.load.engine.y.h
    public /* bridge */ /* synthetic */ s d(com.bumptech.glide.load.c cVar, s sVar) {
        return (s) super.k(cVar, sVar);
    }

    @Override // com.bumptech.glide.load.engine.y.h
    public /* bridge */ /* synthetic */ s e(com.bumptech.glide.load.c cVar) {
        return (s) super.l(cVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.m.g
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public int i(s<?> sVar) {
        return sVar == null ? super.i(null) : sVar.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.m.g
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public void j(com.bumptech.glide.load.c cVar, s<?> sVar) {
        h.a aVar = this.e;
        if (aVar == null || sVar == null) {
            return;
        }
        aVar.a(sVar);
    }
}
