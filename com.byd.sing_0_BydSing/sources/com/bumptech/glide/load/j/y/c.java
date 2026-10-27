package com.bumptech.glide.load.j.y;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.j.n;
import com.bumptech.glide.load.j.o;
import com.bumptech.glide.load.j.r;
import java.io.InputStream;

/* JADX INFO: compiled from: MediaStoreImageThumbLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3038a;

    /* JADX INFO: compiled from: MediaStoreImageThumbLoader.java */
    public static class a implements o<Uri, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f3039a;

        public a(Context context) {
            this.f3039a = context;
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, InputStream> b(r rVar) {
            return new c(this.f3039a);
        }
    }

    public c(Context context) {
        this.f3038a = context.getApplicationContext();
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        if (com.bumptech.glide.load.i.p.b.d(i, i2)) {
            return new n.a<>(new com.bumptech.glide.l.b(uri), com.bumptech.glide.load.i.p.c.d(this.f3038a, uri));
        }
        return null;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.i.p.b.a(uri);
    }
}
