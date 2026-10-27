package com.byd.filemanager.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.h;
import com.bumptech.glide.manager.l;

/* JADX INFO: compiled from: GlideRequests.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends h {
    public c(com.bumptech.glide.c cVar, com.bumptech.glide.manager.h hVar, l lVar, Context context) {
        super(cVar, hVar, lVar, context);
    }

    @Override // com.bumptech.glide.h
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public <ResourceType> b<ResourceType> l(Class<ResourceType> cls) {
        return new b<>(this.f2863e, this, cls, this.f);
    }

    @Override // com.bumptech.glide.h
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public b<Bitmap> m() {
        return (b) super.m();
    }

    @Override // com.bumptech.glide.h
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public b<Drawable> n() {
        return (b) super.n();
    }

    @Override // com.bumptech.glide.h
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public b<Drawable> s(Uri uri) {
        return (b) super.s(uri);
    }

    @Override // com.bumptech.glide.h
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public b<Drawable> t(Object obj) {
        return (b) super.t(obj);
    }

    @Override // com.bumptech.glide.h
    protected void y(com.bumptech.glide.request.e eVar) {
        if (eVar instanceof a) {
            super.y(eVar);
        } else {
            super.y(new a().a(eVar));
        }
    }
}
