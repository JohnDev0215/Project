package com.bumptech.glide.load.i;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.i.n;
import java.io.InputStream;

/* JADX INFO: compiled from: ResourceLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class s<Data> implements n<Integer, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n<Uri, Data> f3242a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f3243b;

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static final class a implements o<Integer, AssetFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f3244a;

        public a(Resources resources) {
            this.f3244a = resources;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Integer, AssetFileDescriptor> b(r rVar) {
            return new s(this.f3244a, rVar.d(Uri.class, AssetFileDescriptor.class));
        }
    }

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static class b implements o<Integer, ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f3245a;

        public b(Resources resources) {
            this.f3245a = resources;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Integer, ParcelFileDescriptor> b(r rVar) {
            return new s(this.f3245a, rVar.d(Uri.class, ParcelFileDescriptor.class));
        }
    }

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static class c implements o<Integer, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f3246a;

        public c(Resources resources) {
            this.f3246a = resources;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Integer, InputStream> b(r rVar) {
            return new s(this.f3246a, rVar.d(Uri.class, InputStream.class));
        }
    }

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static class d implements o<Integer, Uri> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f3247a;

        public d(Resources resources) {
            this.f3247a = resources;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Integer, Uri> b(r rVar) {
            return new s(this.f3247a, v.c());
        }
    }

    public s(Resources resources, n<Uri, Data> nVar) {
        this.f3243b = resources;
        this.f3242a = nVar;
    }

    private Uri d(Integer num) {
        try {
            return Uri.parse("android.resource://" + this.f3243b.getResourcePackageName(num.intValue()) + '/' + this.f3243b.getResourceTypeName(num.intValue()) + '/' + this.f3243b.getResourceEntryName(num.intValue()));
        } catch (Resources.NotFoundException e2) {
            if (!Log.isLoggable("ResourceLoader", 5)) {
                return null;
            }
            Log.w("ResourceLoader", "Received invalid resource id: " + num, e2);
            return null;
        }
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(Integer num, int i, int i2, com.bumptech.glide.load.e eVar) {
        Uri uriD = d(num);
        if (uriD == null) {
            return null;
        }
        return this.f3242a.a(uriD, i, i2, eVar);
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean b(Integer num) {
        return true;
    }
}
