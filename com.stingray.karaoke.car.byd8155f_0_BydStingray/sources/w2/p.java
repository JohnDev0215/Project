package w2;

import e2.g0;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0 f23267a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f23268b;

    public p(g0 g0Var, int[] iArr) {
        if (iArr.length == 0) {
            AbstractC1346c.q("ETSDefinition", "Empty tracks are not allowed", new IllegalArgumentException());
        }
        this.f23267a = g0Var;
        this.f23268b = iArr;
    }
}
