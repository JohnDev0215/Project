package v1;

import java.io.InputStream;
import java.net.URL;
import n1.i;
import u1.g;
import u1.n;
import u1.o;
import u1.r;

/* JADX INFO: compiled from: UrlLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements n<URL, InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n<g, InputStream> f9671a;

    /* JADX INFO: compiled from: UrlLoader.java */
    public static class a implements o<URL, InputStream> {
        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<URL, InputStream> c(r rVar) {
            return new e(rVar.d(g.class, InputStream.class));
        }
    }

    public e(n<g, InputStream> nVar) {
        this.f9671a = nVar;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> b(URL url, int i7, int i8, i iVar) {
        return this.f9671a.b(new g(url), i7, i8, iVar);
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(URL url) {
        return true;
    }
}
