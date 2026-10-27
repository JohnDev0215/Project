package com.bumptech.glide.load.o;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.o.n;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class a<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f2206a = 22;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AssetManager f2207b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final InterfaceC0074a<Data> f2208c;

    /* JADX INFO: renamed from: com.bumptech.glide.load.o.a$a, reason: collision with other inner class name */
    public interface InterfaceC0074a<Data> {
        com.bumptech.glide.load.data.d<Data> a(AssetManager assetManager, String str);
    }

    public static class b implements o<Uri, ParcelFileDescriptor>, InterfaceC0074a<ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f2209a;

        public b(AssetManager assetManager) {
            this.f2209a = assetManager;
        }

        @Override // com.bumptech.glide.load.o.a.InterfaceC0074a
        public com.bumptech.glide.load.data.d<ParcelFileDescriptor> a(AssetManager assetManager, String str) {
            return new com.bumptech.glide.load.data.h(assetManager, str);
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Uri, ParcelFileDescriptor> b(r rVar) {
            return new a(this.f2209a, this);
        }
    }

    public static class c implements o<Uri, InputStream>, InterfaceC0074a<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f2210a;

        public c(AssetManager assetManager) {
            this.f2210a = assetManager;
        }

        @Override // com.bumptech.glide.load.o.a.InterfaceC0074a
        public com.bumptech.glide.load.data.d<InputStream> a(AssetManager assetManager, String str) {
            return new com.bumptech.glide.load.data.m(assetManager, str);
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Uri, InputStream> b(r rVar) {
            return new a(this.f2210a, this);
        }
    }

    public a(AssetManager assetManager, InterfaceC0074a<Data> interfaceC0074a) {
        this.f2207b = assetManager;
        this.f2208c = interfaceC0074a;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Uri uri, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(uri), this.f2208c.a(this.f2207b, uri.toString().substring(f2206a)));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
    }
}
