package com.bumptech.glide.load.i;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.i.n;
import java.io.InputStream;

/* JADX INFO: compiled from: AssetUriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f3174a = 22;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AssetManager f3175b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final InterfaceC0070a<Data> f3176c;

    /* JADX INFO: renamed from: com.bumptech.glide.load.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AssetUriLoader.java */
    public interface InterfaceC0070a<Data> {
        com.bumptech.glide.load.data.d<Data> a(AssetManager assetManager, String str);
    }

    /* JADX INFO: compiled from: AssetUriLoader.java */
    public static class b implements o<Uri, ParcelFileDescriptor>, InterfaceC0070a<ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f3177a;

        public b(AssetManager assetManager) {
            this.f3177a = assetManager;
        }

        @Override // com.bumptech.glide.load.i.a.InterfaceC0070a
        public com.bumptech.glide.load.data.d<ParcelFileDescriptor> a(AssetManager assetManager, String str) {
            return new com.bumptech.glide.load.data.h(assetManager, str);
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Uri, ParcelFileDescriptor> b(r rVar) {
            return new a(this.f3177a, this);
        }
    }

    /* JADX INFO: compiled from: AssetUriLoader.java */
    public static class c implements o<Uri, InputStream>, InterfaceC0070a<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f3178a;

        public c(AssetManager assetManager) {
            this.f3178a = assetManager;
        }

        @Override // com.bumptech.glide.load.i.a.InterfaceC0070a
        public com.bumptech.glide.load.data.d<InputStream> a(AssetManager assetManager, String str) {
            return new com.bumptech.glide.load.data.m(assetManager, str);
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Uri, InputStream> b(r rVar) {
            return new a(this.f3178a, this);
        }
    }

    public a(AssetManager assetManager, InterfaceC0070a<Data> interfaceC0070a) {
        this.f3175b = assetManager;
        this.f3176c = interfaceC0070a;
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.n.b(uri), this.f3176c.a(this.f3175b, uri.toString().substring(f3174a)));
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
    }
}
