package v1;

import java.io.InputStream;
import n1.h;
import n1.i;
import o1.j;
import u1.g;
import u1.m;
import u1.n;
import u1.o;
import u1.r;

/* JADX INFO: compiled from: HttpGlideUrlLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements n<g, InputStream> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h<Integer> f9662b = h.f("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m<g, g> f9663a;

    /* JADX INFO: renamed from: v1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HttpGlideUrlLoader.java */
    public static class C0165a implements o<g, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final m<g, g> f9664a = new m<>(500);

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<g, InputStream> c(r rVar) {
            return new a(this.f9664a);
        }
    }

    public a(m<g, g> mVar) {
        this.f9663a = mVar;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<InputStream> b(g gVar, int i7, int i8, i iVar) {
        m<g, g> mVar = this.f9663a;
        if (mVar != null) {
            g gVarA = mVar.a(gVar, 0, 0);
            if (gVarA == null) {
                this.f9663a.b(gVar, 0, 0, gVar);
            } else {
                gVar = gVarA;
            }
        }
        return new n.a<>(gVar, new j(gVar, ((Integer) iVar.c(f9662b)).intValue()));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(g gVar) {
        return true;
    }
}
