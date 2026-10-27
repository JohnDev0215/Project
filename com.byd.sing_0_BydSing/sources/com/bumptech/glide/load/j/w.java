package com.bumptech.glide.load.j;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.j.n;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: UriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class w<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f3026a = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", "content")));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c<Data> f3027b;

    /* JADX INFO: compiled from: UriLoader.java */
    public static final class a implements o<Uri, AssetFileDescriptor>, c<AssetFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f3028a;

        public a(ContentResolver contentResolver) {
            this.f3028a = contentResolver;
        }

        @Override // com.bumptech.glide.load.j.w.c
        public com.bumptech.glide.load.i.d<AssetFileDescriptor> a(Uri uri) {
            return new com.bumptech.glide.load.i.a(this.f3028a, uri);
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, AssetFileDescriptor> b(r rVar) {
            return new w(this);
        }
    }

    /* JADX INFO: compiled from: UriLoader.java */
    public static class b implements o<Uri, ParcelFileDescriptor>, c<ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f3029a;

        public b(ContentResolver contentResolver) {
            this.f3029a = contentResolver;
        }

        @Override // com.bumptech.glide.load.j.w.c
        public com.bumptech.glide.load.i.d<ParcelFileDescriptor> a(Uri uri) {
            return new com.bumptech.glide.load.i.i(this.f3029a, uri);
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, ParcelFileDescriptor> b(r rVar) {
            return new w(this);
        }
    }

    /* JADX INFO: compiled from: UriLoader.java */
    public interface c<Data> {
        com.bumptech.glide.load.i.d<Data> a(Uri uri);
    }

    /* JADX INFO: compiled from: UriLoader.java */
    public static class d implements o<Uri, InputStream>, c<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f3030a;

        public d(ContentResolver contentResolver) {
            this.f3030a = contentResolver;
        }

        @Override // com.bumptech.glide.load.j.w.c
        public com.bumptech.glide.load.i.d<InputStream> a(Uri uri) {
            return new com.bumptech.glide.load.i.o(this.f3030a, uri);
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, InputStream> b(r rVar) {
            return new w(this);
        }
    }

    public w(c<Data> cVar) {
        this.f3027b = cVar;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.l.b(uri), this.f3027b.a(uri));
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return f3026a.contains(uri.getScheme());
    }
}
