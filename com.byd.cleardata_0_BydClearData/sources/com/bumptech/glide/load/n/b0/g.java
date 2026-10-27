package com.bumptech.glide.load.n.b0;

import android.annotation.SuppressLint;
import com.bumptech.glide.load.n.b0.h;
import com.bumptech.glide.load.n.v;

/* JADX INFO: loaded from: classes.dex */
public class g extends com.bumptech.glide.t.g<com.bumptech.glide.load.g, v<?>> implements h {
    private h.a e;

    public g(long j) {
        super(j);
    }

    @Override // com.bumptech.glide.load.n.b0.h
    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (i >= 40) {
            b();
        } else if (i >= 20 || i == 15) {
            m(h() / 2);
        }
    }

    @Override // com.bumptech.glide.load.n.b0.h
    public /* bridge */ /* synthetic */ v c(com.bumptech.glide.load.g gVar, v vVar) {
        return (v) super.k(gVar, vVar);
    }

    @Override // com.bumptech.glide.load.n.b0.h
    public /* bridge */ /* synthetic */ v d(com.bumptech.glide.load.g gVar) {
        return (v) super.l(gVar);
    }

    @Override // com.bumptech.glide.load.n.b0.h
    public void e(h.a aVar) {
        this.e = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.t.g
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public int i(v<?> vVar) {
        return vVar == null ? super.i(null) : vVar.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.t.g
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public void j(com.bumptech.glide.load.g gVar, v<?> vVar) {
        h.a aVar = this.e;
        if (aVar == null || vVar == null) {
            return;
        }
        aVar.b(vVar);
    }
}
