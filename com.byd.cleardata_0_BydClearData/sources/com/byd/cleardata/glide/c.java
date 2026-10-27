package com.byd.cleardata.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.k;
import com.bumptech.glide.o.h;
import com.bumptech.glide.o.m;

/* JADX INFO: loaded from: classes.dex */
public class c extends k {
    public c(com.bumptech.glide.c cVar, h hVar, m mVar, Context context) {
        super(cVar, hVar, mVar, context);
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public <ResourceType> b<ResourceType> l(Class<ResourceType> cls) {
        return new b<>(this.e, this, cls, this.f);
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public b<Bitmap> m() {
        return (b) super.m();
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public b<Drawable> n() {
        return (b) super.n();
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public b<Drawable> s(Uri uri) {
        return (b) super.s(uri);
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public b<Drawable> t(Object obj) {
        return (b) super.t(obj);
    }

    @Override // com.bumptech.glide.k
    protected void y(com.bumptech.glide.r.f fVar) {
        if (!(fVar instanceof a)) {
            fVar = new a().a(fVar);
        }
        super.y(fVar);
    }
}
