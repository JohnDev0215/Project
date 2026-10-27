package u1;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.InputStream;
import u1.n;

/* JADX INFO: compiled from: ResourceLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class s<Data> implements n<Integer, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n<Uri, Data> f9638a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f9639b;

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static final class a implements o<Integer, AssetFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f9640a;

        public a(Resources resources) {
            this.f9640a = resources;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Integer, AssetFileDescriptor> c(r rVar) {
            return new s(this.f9640a, rVar.d(Uri.class, AssetFileDescriptor.class));
        }
    }

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static class b implements o<Integer, ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f9641a;

        public b(Resources resources) {
            this.f9641a = resources;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Integer, ParcelFileDescriptor> c(r rVar) {
            return new s(this.f9641a, rVar.d(Uri.class, ParcelFileDescriptor.class));
        }
    }

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static class c implements o<Integer, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f9642a;

        public c(Resources resources) {
            this.f9642a = resources;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Integer, InputStream> c(r rVar) {
            return new s(this.f9642a, rVar.d(Uri.class, InputStream.class));
        }
    }

    /* JADX INFO: compiled from: ResourceLoader.java */
    public static class d implements o<Integer, Uri> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Resources f9643a;

        public d(Resources resources) {
            this.f9643a = resources;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Integer, Uri> c(r rVar) {
            return new s(this.f9643a, v.c());
        }
    }

    public s(Resources resources, n<Uri, Data> nVar) {
        this.f9639b = resources;
        this.f9638a = nVar;
    }

    private Uri d(Integer num) {
        try {
            return Uri.parse("android.resource://" + this.f9639b.getResourcePackageName(num.intValue()) + '/' + this.f9639b.getResourceTypeName(num.intValue()) + '/' + this.f9639b.getResourceEntryName(num.intValue()));
        } catch (Resources.NotFoundException e7) {
            if (!Log.isLoggable("ResourceLoader", 5)) {
                return null;
            }
            Log.w("ResourceLoader", "Received invalid resource id: " + num, e7);
            return null;
        }
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> b(Integer num, int i7, int i8, n1.i iVar) {
        Uri uriD = d(num);
        if (uriD == null) {
            return null;
        }
        return this.f9638a.b(uriD, i7, i8, iVar);
    }

    @Override // u1.n
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean a(Integer num) {
        return true;
    }
}
