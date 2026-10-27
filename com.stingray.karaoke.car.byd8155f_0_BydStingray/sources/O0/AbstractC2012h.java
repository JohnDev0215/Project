package o0;

import u.AbstractC2469i;
import u.C2476p;

/* JADX INFO: renamed from: o0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2012h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2476p f20202a;

    static {
        C2021q c2021q = C2008d.c;
        int i7 = c2021q.c;
        C2009e c2009e = new C2009e(c2021q, c2021q, 1);
        C2016l c2016l = C2008d.f20195t;
        int i10 = c2016l.c << 6;
        int i11 = c2021q.c;
        int i12 = i10 | i11;
        C2011g c2011g = new C2011g(c2021q, c2016l, 0);
        int i13 = (i11 << 6) | c2016l.c;
        C2011g c2011g2 = new C2011g(c2016l, c2021q, 0);
        C2476p c2476p = AbstractC2469i.f22602a;
        C2476p c2476p2 = new C2476p();
        c2476p2.i(i7 | (i7 << 6), c2009e);
        c2476p2.i(i12, c2011g);
        c2476p2.i(i13, c2011g2);
        f20202a = c2476p2;
    }
}
