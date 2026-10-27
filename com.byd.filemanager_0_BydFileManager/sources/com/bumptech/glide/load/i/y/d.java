package com.bumptech.glide.load.i.y;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.i.n;
import com.bumptech.glide.load.i.o;
import com.bumptech.glide.load.i.r;
import com.bumptech.glide.load.resource.bitmap.w;
import java.io.InputStream;

/* JADX INFO: compiled from: MediaStoreVideoThumbLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3267a;

    /* JADX INFO: compiled from: MediaStoreVideoThumbLoader.java */
    public static class a implements o<Uri, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f3268a;

        public a(Context context) {
            this.f3268a = context;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Uri, InputStream> b(r rVar) {
            return new d(this.f3268a);
        }
    }

    public d(Context context) {
        this.f3267a = context.getApplicationContext();
    }

    private boolean e(com.bumptech.glide.load.e eVar) {
        Long l = (Long) eVar.c(w.f3395a);
        return l != null && l.longValue() == -1;
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        if (com.bumptech.glide.load.data.o.b.d(i, i2) && e(eVar)) {
            return new n.a<>(new com.bumptech.glide.n.b(uri), com.bumptech.glide.load.data.o.c.g(this.f3267a, uri));
        }
        return null;
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.data.o.b.c(uri);
    }
}
