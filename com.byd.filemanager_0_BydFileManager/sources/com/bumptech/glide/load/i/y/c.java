package com.bumptech.glide.load.i.y;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.i.n;
import com.bumptech.glide.load.i.o;
import com.bumptech.glide.load.i.r;
import java.io.InputStream;

/* JADX INFO: compiled from: MediaStoreImageThumbLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3265a;

    /* JADX INFO: compiled from: MediaStoreImageThumbLoader.java */
    public static class a implements o<Uri, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f3266a;

        public a(Context context) {
            this.f3266a = context;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Uri, InputStream> b(r rVar) {
            return new c(this.f3266a);
        }
    }

    public c(Context context) {
        this.f3265a = context.getApplicationContext();
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        if (com.bumptech.glide.load.data.o.b.d(i, i2)) {
            return new n.a<>(new com.bumptech.glide.n.b(uri), com.bumptech.glide.load.data.o.c.f(this.f3265a, uri));
        }
        return null;
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.data.o.b.a(uri);
    }
}
