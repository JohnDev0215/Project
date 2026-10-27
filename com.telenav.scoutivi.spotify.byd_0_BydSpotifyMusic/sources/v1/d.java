package v1;

import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import n1.i;
import u1.n;
import u1.o;
import u1.r;
import x1.a0;

/* JADX INFO: compiled from: MediaStoreVideoThumbLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f9669a;

    /* JADX INFO: compiled from: MediaStoreVideoThumbLoader.java */
    public static class a implements o<Uri, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f9670a;

        public a(Context context) {
            this.f9670a = context;
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, InputStream> c(r rVar) {
            return new d(this.f9670a);
        }
    }

    public d(Context context) {
        this.f9669a = context.getApplicationContext();
    }

    private boolean e(i iVar) {
        Long l7 = (Long) iVar.c(a0.f9739d);
        return l7 != null && l7.longValue() == -1;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> b(Uri uri, int i7, int i8, i iVar) {
        if (p1.b.d(i7, i8) && e(iVar)) {
            return new n.a<>(new j2.d(uri), p1.c.g(this.f9669a, uri));
        }
        return null;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri) {
        return p1.b.c(uri);
    }
}
