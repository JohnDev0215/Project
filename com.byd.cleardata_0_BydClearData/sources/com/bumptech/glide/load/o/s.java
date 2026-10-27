package com.bumptech.glide.load.o;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.o.n;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class s<Data> implements n<Integer, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n<Uri, Data> f2269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f2270b;

    public static final class a implements o<Integer, AssetFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f2271a;

        public a(Resources resources) {
            this.f2271a = resources;
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Integer, AssetFileDescriptor> b(r rVar) {
            return new s(this.f2271a, rVar.d(Uri.class, AssetFileDescriptor.class));
        }
    }

    public static class b implements o<Integer, ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f2272a;

        public b(Resources resources) {
            this.f2272a = resources;
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Integer, ParcelFileDescriptor> b(r rVar) {
            return new s(this.f2272a, rVar.d(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static class c implements o<Integer, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f2273a;

        public c(Resources resources) {
            this.f2273a = resources;
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Integer, InputStream> b(r rVar) {
            return new s(this.f2273a, rVar.d(Uri.class, InputStream.class));
        }
    }

    public static class d implements o<Integer, Uri> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f2274a;

        public d(Resources resources) {
            this.f2274a = resources;
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Integer, Uri> b(r rVar) {
            return new s(this.f2274a, v.c());
        }
    }

    public s(Resources resources, n<Uri, Data> nVar) {
        this.f2270b = resources;
        this.f2269a = nVar;
    }

    private Uri d(Integer num) {
        try {
            return Uri.parse("android.resource://" + this.f2270b.getResourcePackageName(num.intValue()) + '/' + this.f2270b.getResourceTypeName(num.intValue()) + '/' + this.f2270b.getResourceEntryName(num.intValue()));
        } catch (Resources.NotFoundException e) {
            if (!Log.isLoggable("ResourceLoader", 5)) {
                return null;
            }
            Log.w("ResourceLoader", "Received invalid resource id: " + num, e);
            return null;
        }
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Integer num, int i, int i2, com.bumptech.glide.load.i iVar) {
        Uri uriD = d(num);
        if (uriD == null) {
            return null;
        }
        return this.f2269a.a(uriD, i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean b(Integer num) {
        return true;
    }
}
