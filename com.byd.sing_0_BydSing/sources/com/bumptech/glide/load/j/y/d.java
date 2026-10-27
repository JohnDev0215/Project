package com.bumptech.glide.load.j.y;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.j.n;
import com.bumptech.glide.load.j.o;
import com.bumptech.glide.load.j.r;
import com.bumptech.glide.load.resource.bitmap.u;
import java.io.InputStream;

/* JADX INFO: compiled from: MediaStoreVideoThumbLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3040a;

    /* JADX INFO: compiled from: MediaStoreVideoThumbLoader.java */
    public static class a implements o<Uri, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f3041a;

        public a(Context context) {
            this.f3041a = context;
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, InputStream> b(r rVar) {
            return new d(this.f3041a);
        }
    }

    public d(Context context) {
        this.f3040a = context.getApplicationContext();
    }

    private boolean e(com.bumptech.glide.load.e eVar) {
        Long l = (Long) eVar.c(u.f3156a);
        return l != null && l.longValue() == -1;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        if (com.bumptech.glide.load.i.p.b.d(i, i2) && e(eVar)) {
            return new n.a<>(new com.bumptech.glide.l.b(uri), com.bumptech.glide.load.i.p.c.g(this.f3040a, uri));
        }
        return null;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.i.p.b.c(uri);
    }
}
