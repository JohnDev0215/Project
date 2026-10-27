package com.bumptech.glide.load.j;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.j.n;
import java.io.InputStream;

/* JADX INFO: compiled from: AssetUriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f2952a = 22;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AssetManager f2953b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final InterfaceC0094a<Data> f2954c;

    /* JADX INFO: renamed from: com.bumptech.glide.load.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AssetUriLoader.java */
    public interface InterfaceC0094a<Data> {
        com.bumptech.glide.load.i.d<Data> a(AssetManager assetManager, String str);
    }

    /* JADX INFO: compiled from: AssetUriLoader.java */
    public static class b implements o<Uri, ParcelFileDescriptor>, InterfaceC0094a<ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f2955a;

        public b(AssetManager assetManager) {
            this.f2955a = assetManager;
        }

        @Override // com.bumptech.glide.load.j.a.InterfaceC0094a
        public com.bumptech.glide.load.i.d<ParcelFileDescriptor> a(AssetManager assetManager, String str) {
            return new com.bumptech.glide.load.i.h(assetManager, str);
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, ParcelFileDescriptor> b(r rVar) {
            return new a(this.f2955a, this);
        }
    }

    /* JADX INFO: compiled from: AssetUriLoader.java */
    public static class c implements o<Uri, InputStream>, InterfaceC0094a<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f2956a;

        public c(AssetManager assetManager) {
            this.f2956a = assetManager;
        }

        @Override // com.bumptech.glide.load.j.a.InterfaceC0094a
        public com.bumptech.glide.load.i.d<InputStream> a(AssetManager assetManager, String str) {
            return new com.bumptech.glide.load.i.n(assetManager, str);
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, InputStream> b(r rVar) {
            return new a(this.f2956a, this);
        }
    }

    public a(AssetManager assetManager, InterfaceC0094a<Data> interfaceC0094a) {
        this.f2953b = assetManager;
        this.f2954c = interfaceC0094a;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.l.b(uri), this.f2954c.a(this.f2953b, uri.toString().substring(f2952a)));
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
    }
}
