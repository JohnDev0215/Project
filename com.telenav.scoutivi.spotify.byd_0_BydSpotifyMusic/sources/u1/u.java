package u1;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import u1.n;

/* JADX INFO: compiled from: StringLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class u<Data> implements n<String, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n<Uri, Data> f9645a;

    /* JADX INFO: compiled from: StringLoader.java */
    public static final class a implements o<String, AssetFileDescriptor> {
        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<String, AssetFileDescriptor> c(r rVar) {
            return new u(rVar.d(Uri.class, AssetFileDescriptor.class));
        }
    }

    /* JADX INFO: compiled from: StringLoader.java */
    public static class b implements o<String, ParcelFileDescriptor> {
        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<String, ParcelFileDescriptor> c(r rVar) {
            return new u(rVar.d(Uri.class, ParcelFileDescriptor.class));
        }
    }

    /* JADX INFO: compiled from: StringLoader.java */
    public static class c implements o<String, InputStream> {
        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<String, InputStream> c(r rVar) {
            return new u(rVar.d(Uri.class, InputStream.class));
        }
    }

    public u(n<Uri, Data> nVar) {
        this.f9645a = nVar;
    }

    private static Uri e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.charAt(0) == '/') {
            return f(str);
        }
        Uri uri = Uri.parse(str);
        return uri.getScheme() == null ? f(str) : uri;
    }

    private static Uri f(String str) {
        return Uri.fromFile(new File(str));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> b(String str, int i7, int i8, n1.i iVar) {
        Uri uriE = e(str);
        if (uriE == null || !this.f9645a.a(uriE)) {
            return null;
        }
        return this.f9645a.b(uriE, i7, i8, iVar);
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(String str) {
        return true;
    }
}
