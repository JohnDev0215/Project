package Q;

import T.C0761q;
import n0.AbstractC1861K;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public abstract class A0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5488a = 16;

    public static C0544z0 a(long j3, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, C0761q c0761q, int i7) {
        long j21 = (i7 & 4) != 0 ? C1891t.f19451j : j11;
        float f10 = S.n.f7706a;
        long jC = AbstractC0520n.c(11, c0761q);
        long jC2 = (i7 & 64) != 0 ? AbstractC0520n.c(24, c0761q) : j14;
        long jC3 = AbstractC0520n.c(39, c0761q);
        long jN = (i7 & 256) != 0 ? AbstractC1861K.n(C1891t.b(1.0f, AbstractC0520n.c(35, c0761q)), ((C0516l) c0761q.k(AbstractC0520n.f5997a)).f5969p) : j15;
        long jN2 = (i7 & 512) != 0 ? AbstractC1861K.n(C1891t.b(0.12f, AbstractC0520n.c(18, c0761q)), ((C0516l) c0761q.k(AbstractC0520n.f5997a)).f5969p) : j16;
        long j22 = (i7 & 1024) != 0 ? C1891t.f19451j : j17;
        long jB = C1891t.b(0.38f, AbstractC0520n.c(18, c0761q));
        T.R0 r02 = AbstractC0520n.f5997a;
        return new C0544z0(j3, j10, j21, jC, j12, j13, jC2, jC3, jN, jN2, j22, AbstractC1861K.n(jB, ((C0516l) c0761q.k(r02)).f5969p), (i7 & 4096) != 0 ? AbstractC1861K.n(C1891t.b(0.38f, AbstractC0520n.c(18, c0761q)), ((C0516l) c0761q.k(r02)).f5969p) : j18, (i7 & 8192) != 0 ? AbstractC1861K.n(C1891t.b(0.12f, AbstractC0520n.c(39, c0761q)), ((C0516l) c0761q.k(r02)).f5969p) : j19, (i7 & 16384) != 0 ? AbstractC1861K.n(C1891t.b(0.12f, AbstractC0520n.c(18, c0761q)), ((C0516l) c0761q.k(r02)).f5969p) : j20, AbstractC1861K.n(C1891t.b(0.38f, AbstractC0520n.c(39, c0761q)), ((C0516l) c0761q.k(r02)).f5969p));
    }
}
