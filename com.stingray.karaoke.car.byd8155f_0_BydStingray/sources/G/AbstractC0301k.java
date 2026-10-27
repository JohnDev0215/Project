package G;

import E.C0149f;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: G.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0301k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f2596a = 2500;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f2597b = 1500;
    public static final float c = 50;

    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.List] */
    public static final boolean a(C0149f c0149f, int i7) {
        int iB = c0149f.b();
        E.u uVar = (E.u) AbstractC1998m.i0(c0149f.f1351a.h().f1415j);
        return i7 <= (uVar != null ? uVar.f1422a : 0) && iB <= i7;
    }
}
