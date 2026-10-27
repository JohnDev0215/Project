package com.bumptech.glide.load.o;

import android.net.Uri;
import com.bumptech.glide.load.o.n;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class x<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f2285a = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n<g, Data> f2286b;

    public static class a implements o<Uri, InputStream> {
        @Override // com.bumptech.glide.load.o.o
        public n<Uri, InputStream> b(r rVar) {
            return new x(rVar.d(g.class, InputStream.class));
        }
    }

    public x(n<g, Data> nVar) {
        this.f2286b = nVar;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Uri uri, int i, int i2, com.bumptech.glide.load.i iVar) {
        return this.f2286b.a(new g(uri.toString()), i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return f2285a.contains(uri.getScheme());
    }
}
