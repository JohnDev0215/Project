package P;

import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.X;
import n0.C1891t;
import x.AbstractC2614C;
import x.C0;

/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0 f5257a = new C0(15, AbstractC2614C.c, 2);

    public static final f a(boolean z6, float f10, long j3, C0761q c0761q, int i7) {
        X xR = C0735d.R(new C1891t(j3), c0761q);
        boolean z10 = true;
        boolean z11 = (((i7 & 14) ^ 6) > 4 && c0761q.g(z6)) || (i7 & 6) == 4;
        if ((((i7 & 112) ^ 48) <= 32 || !c0761q.c(f10)) && (i7 & 48) != 32) {
            z10 = false;
        }
        boolean z12 = z11 | z10;
        Object objG = c0761q.G();
        if (z12 || objG == C0753m.f8270a) {
            objG = new f(z6, f10, xR);
            c0761q.a0(objG);
        }
        return (f) objG;
    }
}
