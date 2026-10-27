package u1;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.InputStream;
import u1.n;

/* JADX INFO: compiled from: AssetUriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f9563c = 22;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AssetManager f9564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0160a<Data> f9565b;

    /* JADX INFO: renamed from: u1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AssetUriLoader.java */
    public interface InterfaceC0160a<Data> {
        o1.d<Data> a(AssetManager assetManager, String str);
    }

    /* JADX INFO: compiled from: AssetUriLoader.java */
    public static class b implements o<Uri, ParcelFileDescriptor>, InterfaceC0160a<ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f9566a;

        public b(AssetManager assetManager) {
            this.f9566a = assetManager;
        }

        @Override // u1.a.InterfaceC0160a
        public o1.d<ParcelFileDescriptor> a(AssetManager assetManager, String str) {
            return new o1.h(assetManager, str);
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, ParcelFileDescriptor> c(r rVar) {
            return new a(this.f9566a, this);
        }
    }

    /* JADX INFO: compiled from: AssetUriLoader.java */
    public static class c implements o<Uri, InputStream>, InterfaceC0160a<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AssetManager f9567a;

        public c(AssetManager assetManager) {
            this.f9567a = assetManager;
        }

        @Override // u1.a.InterfaceC0160a
        public o1.d<InputStream> a(AssetManager assetManager, String str) {
            return new o1.m(assetManager, str);
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, InputStream> c(r rVar) {
            return new a(this.f9567a, this);
        }
    }

    public a(AssetManager assetManager, InterfaceC0160a<Data> interfaceC0160a) {
        this.f9564a = assetManager;
        this.f9565b = interfaceC0160a;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> b(Uri uri, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(uri), this.f9565b.a(this.f9564a, uri.toString().substring(f9563c)));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri) {
        return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
    }
}
