package com.bumptech.glide.load.o;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.bumptech.glide.load.o.n;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class u<Data> implements n<String, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n<Uri, Data> f2276a;

    public static final class a implements o<String, AssetFileDescriptor> {
        @Override // com.bumptech.glide.load.o.o
        public n<String, AssetFileDescriptor> b(r rVar) {
            return new u(rVar.d(Uri.class, AssetFileDescriptor.class));
        }
    }

    public static class b implements o<String, ParcelFileDescriptor> {
        @Override // com.bumptech.glide.load.o.o
        public n<String, ParcelFileDescriptor> b(r rVar) {
            return new u(rVar.d(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static class c implements o<String, InputStream> {
        @Override // com.bumptech.glide.load.o.o
        public n<String, InputStream> b(r rVar) {
            return new u(rVar.d(Uri.class, InputStream.class));
        }
    }

    public u(n<Uri, Data> nVar) {
        this.f2276a = nVar;
    }

    private static Uri e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.charAt(0) != '/') {
            Uri uri = Uri.parse(str);
            if (uri.getScheme() != null) {
                return uri;
            }
        }
        return f(str);
    }

    private static Uri f(String str) {
        return Uri.fromFile(new File(str));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(String str, int i, int i2, com.bumptech.glide.load.i iVar) {
        Uri uriE = e(str);
        if (uriE == null || !this.f2276a.b(uriE)) {
            return null;
        }
        return this.f2276a.a(uriE, i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(String str) {
        return true;
    }
}
