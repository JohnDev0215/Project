package v1;

import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import n1.i;
import u1.n;
import u1.o;
import u1.r;

/* JADX INFO: compiled from: MediaStoreImageThumbLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f9667a;

    /* JADX INFO: compiled from: MediaStoreImageThumbLoader.java */
    public static class a implements o<Uri, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f9668a;

        public a(Context context) {
            this.f9668a = context;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, InputStream> c(r rVar) {
            return new c(this.f9668a);
        }
    }

    public c(Context context) {
        this.f9667a = context.getApplicationContext();
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> b(Uri uri, int i7, int i8, i iVar) {
        if (p1.b.d(i7, i8)) {
            return new n.a<>(new j2.d(uri), p1.c.f(this.f9667a, uri));
        }
        return null;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri) {
        return p1.b.a(uri);
    }
}
