package g2;

import d5.C1078G;
import d5.I;
import d5.d0;
import h2.AbstractC1343A;
import java.util.List;

/* JADX INFO: renamed from: g2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1290c {
    public static final C1290c c;
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f14800e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final I f14801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f14802b;

    static {
        C1078G c1078g = I.f13384m;
        c = new C1290c(d0.f13415p, 0L);
        int i7 = AbstractC1343A.f15195a;
        d = Integer.toString(0, 36);
        f14800e = Integer.toString(1, 36);
    }

    public C1290c(List list, long j3) {
        this.f14801a = I.w(list);
        this.f14802b = j3;
    }
}
