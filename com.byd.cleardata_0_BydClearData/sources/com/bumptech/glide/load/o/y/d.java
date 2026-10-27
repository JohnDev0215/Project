package com.bumptech.glide.load.o.y;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.o.n;
import com.bumptech.glide.load.o.o;
import com.bumptech.glide.load.o.r;
import com.bumptech.glide.load.p.d.x;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class d implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2294a;

    public static class a implements o<Uri, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f2295a;

        public a(Context context) {
            this.f2295a = context;
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Uri, InputStream> b(r rVar) {
            return new d(this.f2295a);
        }
    }

    public d(Context context) {
        this.f2294a = context.getApplicationContext();
    }

    private boolean e(i iVar) {
        Long l = (Long) iVar.c(x.f2373a);
        return l != null && l.longValue() == -1;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> a(Uri uri, int i, int i2, i iVar) {
        if (com.bumptech.glide.load.data.o.b.d(i, i2) && e(iVar)) {
            return new n.a<>(new com.bumptech.glide.s.b(uri), com.bumptech.glide.load.data.o.c.g(this.f2294a, uri));
        }
        return null;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.data.o.b.c(uri);
    }
}
