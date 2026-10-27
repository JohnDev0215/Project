package m3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.k;
import d2.h;
import d2.m;
import g2.f;

/* JADX INFO: compiled from: GlideRequests.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends k {
    public c(com.bumptech.glide.c cVar, h hVar, m mVar, Context context) {
        super(cVar, hVar, mVar, context);
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public <ResourceType> b<ResourceType> b(Class<ResourceType> cls) {
        return new b<>(this.f4001e, this, cls, this.f4002g);
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public b<Bitmap> c() {
        return (b) super.c();
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public b<Drawable> m() {
        return (b) super.m();
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public b<b2.c> n() {
        return (b) super.n();
    }

    @Override // com.bumptech.glide.k
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public b<Drawable> s(Uri uri) {
        return (b) super.s(uri);
    }

    @Override // com.bumptech.glide.k
    protected void x(f fVar) {
        if (fVar instanceof a) {
            super.x(fVar);
        } else {
            super.x(new a().a(fVar));
        }
    }
}
