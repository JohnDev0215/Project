package g.a.e.b.e;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f5640a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g.a.b.n f5641b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5642c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5643d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5644e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5645f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5646g;

    public i(g.a.b.n nVar) {
        Objects.requireNonNull(nVar, "digest == null");
        this.f5641b = nVar;
        int iH = t.h(nVar);
        this.f5642c = iH;
        this.f5643d = 16;
        int iCeil = (int) Math.ceil(((double) (iH * 8)) / ((double) t.n(16)));
        this.f5645f = iCeil;
        int iFloor = ((int) Math.floor(t.n((16 - 1) * iCeil) / t.n(16))) + 1;
        this.f5646g = iFloor;
        int i2 = iCeil + iFloor;
        this.f5644e = i2;
        h hVarB = h.b(nVar.getAlgorithmName(), iH, 16, i2);
        this.f5640a = hVarB;
        if (hVarB != null) {
            return;
        }
        throw new IllegalArgumentException("cannot find OID for digest algorithm: " + nVar.getAlgorithmName());
    }

    public g.a.b.n a() {
        return this.f5641b;
    }

    public int b() {
        return this.f5642c;
    }

    public int c() {
        return this.f5644e;
    }

    public int d() {
        return this.f5643d;
    }
}
