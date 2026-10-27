package v1;

import android.net.Uri;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import n1.i;
import u1.g;
import u1.n;
import u1.o;
import u1.r;

/* JADX INFO: compiled from: HttpUriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements n<Uri, InputStream> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Set<String> f9665b = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n<g, InputStream> f9666a;

    /* JADX INFO: compiled from: HttpUriLoader.java */
    public static class a implements o<Uri, InputStream> {
        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Uri, InputStream> c(r rVar) {
            return new b(rVar.d(g.class, InputStream.class));
        }
    }

    public b(n<g, InputStream> nVar) {
        this.f9666a = nVar;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> b(Uri uri, int i7, int i8, i iVar) {
        return this.f9666a.b(new g(uri.toString()), i7, i8, iVar);
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Uri uri) {
        return f9665b.contains(uri.getScheme());
    }
}
