package com.bumptech.glide.load.engine.y;

import android.annotation.SuppressLint;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.load.engine.y.h;

/* JADX INFO: compiled from: LruResourceCache.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends com.bumptech.glide.o.g<com.bumptech.glide.load.c, s<?>> implements h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private h.a f3140e;

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
    public /* bridge */ /* synthetic */ s c(com.bumptech.glide.load.c cVar, s sVar) {
        return (s) super.k(cVar, sVar);
    }

    @Override // com.bumptech.glide.load.engine.y.h
    public /* bridge */ /* synthetic */ s d(com.bumptech.glide.load.c cVar) {
        return (s) super.l(cVar);
    }

    @Override // com.bumptech.glide.load.engine.y.h
    public void e(h.a aVar) {
        this.f3140e = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.o.g
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public int i(s<?> sVar) {
        return sVar == null ? super.i(null) : sVar.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.o.g
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public void j(com.bumptech.glide.load.c cVar, s<?> sVar) {
        h.a aVar = this.f3140e;
        if (aVar == null || sVar == null) {
            return;
        }
        aVar.b(sVar);
    }
}
