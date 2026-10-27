package u1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import o1.d;
import u1.n;

/* JADX INFO: compiled from: MediaStoreFileLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements n<Uri, File> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f9602a;

    /* JADX INFO: compiled from: MediaStoreFileLoader.java */
    public static final class a implements o<Uri, File> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f9603a;

        public a(Context context) {
            this.f9603a = context;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, File> c(r rVar) {
            return new k(this.f9603a);
        }
    }

    /* JADX INFO: compiled from: MediaStoreFileLoader.java */
    private static class b implements o1.d<File> {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private static final String[] f9604h = {"_data"};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Context f9605e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final Uri f9606g;

        b(Context context, Uri uri) {
            this.f9605e = context;
            this.f9606g = uri;
        }

        @Override // o1.d
        public Class<File> a() {
            return File.class;
        }

        @Override // o1.d
        public void b() {
        }

        @Override // o1.d
        public void c(com.bumptech.glide.g gVar, d.a<? super File> aVar) {
            Cursor cursorQuery = this.f9605e.getContentResolver().query(this.f9606g, f9604h, null, null, null);
            if (cursorQuery != null) {
                try {
                    string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                } finally {
                    cursorQuery.close();
                }
            }
            if (!TextUtils.isEmpty(string)) {
                aVar.f(new File(string));
                return;
            }
            aVar.d(new FileNotFoundException("Failed to find file path for: " + this.f9606g));
        }

        @Override // o1.d
        public void cancel() {
        }

        @Override // o1.d
        public n1.a e() {
            return n1.a.LOCAL;
        }
    }

    public k(Context context) {
        this.f9602a = context;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<File> b(Uri uri, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(uri), new b(this.f9602a, uri));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri) {
        return p1.b.b(uri);
    }
}
