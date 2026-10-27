package u1;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import u1.n;

/* JADX INFO: compiled from: UriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class w<Data> implements n<Uri, Data> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Set<String> f9649b = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", "content")));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c<Data> f9650a;

    /* JADX INFO: compiled from: UriLoader.java */
    public static final class a implements o<Uri, AssetFileDescriptor>, c<AssetFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f9651a;

        public a(ContentResolver contentResolver) {
            this.f9651a = contentResolver;
        }

        @Override // u1.w.c
        public o1.d<AssetFileDescriptor> a(Uri uri) {
            return new o1.a(this.f9651a, uri);
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, AssetFileDescriptor> c(r rVar) {
            return new w(this);
        }
    }

    /* JADX INFO: compiled from: UriLoader.java */
    public static class b implements o<Uri, ParcelFileDescriptor>, c<ParcelFileDescriptor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f9652a;

        public b(ContentResolver contentResolver) {
            this.f9652a = contentResolver;
        }

        @Override // u1.w.c
        public o1.d<ParcelFileDescriptor> a(Uri uri) {
            return new o1.i(this.f9652a, uri);
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, ParcelFileDescriptor> c(r rVar) {
            return new w(this);
        }
    }

    /* JADX INFO: compiled from: UriLoader.java */
    public interface c<Data> {
        o1.d<Data> a(Uri uri);
    }

    /* JADX INFO: compiled from: UriLoader.java */
    public static class d implements o<Uri, InputStream>, c<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ContentResolver f9653a;

        public d(ContentResolver contentResolver) {
            this.f9653a = contentResolver;
        }

        @Override // u1.w.c
        public o1.d<InputStream> a(Uri uri) {
            return new o1.n(this.f9653a, uri);
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, InputStream> c(r rVar) {
            return new w(this);
        }
    }

    public w(c<Data> cVar) {
        this.f9650a = cVar;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> b(Uri uri, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(uri), this.f9650a.a(uri));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri) {
        return f9649b.contains(uri.getScheme());
    }
}
