package com.bumptech.glide.load.o;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.o.n;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class w<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f2280a = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", "content")));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c<Data> f2281b;

    public static final class a implements o<Uri, AssetFileDescriptor>, c<AssetFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f2282a;

        public a(ContentResolver contentResolver) {
            this.f2282a = contentResolver;
        }

        @Override // com.bumptech.glide.load.o.w.c
        public com.bumptech.glide.load.data.d<AssetFileDescriptor> a(Uri uri) {
            return new com.bumptech.glide.load.data.a(this.f2282a, uri);
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Uri, AssetFileDescriptor> b(r rVar) {
            return new w(this);
        }
    }

    public static class b implements o<Uri, ParcelFileDescriptor>, c<ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f2283a;

        public b(ContentResolver contentResolver) {
            this.f2283a = contentResolver;
        }

        @Override // com.bumptech.glide.load.o.w.c
        public com.bumptech.glide.load.data.d<ParcelFileDescriptor> a(Uri uri) {
            return new com.bumptech.glide.load.data.i(this.f2283a, uri);
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Uri, ParcelFileDescriptor> b(r rVar) {
            return new w(this);
        }
    }

    public interface c<Data> {
        com.bumptech.glide.load.data.d<Data> a(Uri uri);
    }

    public static class d implements o<Uri, InputStream>, c<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f2284a;

        public d(ContentResolver contentResolver) {
            this.f2284a = contentResolver;
        }

        @Override // com.bumptech.glide.load.o.w.c
        public com.bumptech.glide.load.data.d<InputStream> a(Uri uri) {
            return new com.bumptech.glide.load.data.n(this.f2284a, uri);
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Uri, InputStream> b(r rVar) {
            return new w(this);
        }
    }

    public w(c<Data> cVar) {
        this.f2281b = cVar;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Uri uri, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(uri), this.f2281b.a(uri));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return f2280a.contains(uri.getScheme());
    }
}
