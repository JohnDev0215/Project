package x;

import T.C0735d;
import T.C0738e0;
import T.Q0;

/* JADX INFO: renamed from: x.I, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2620I implements Q0 {
    public Number l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Number f23410m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final D0 f23411n;
    public final C0738e0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public p0 f23412p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f23413q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f23414r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f23415s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ L f23416t;

    public C2620I(L l, Number number, Number number2, D0 d02, C2619H c2619h) {
        this.f23416t = l;
        this.l = number;
        this.f23410m = number2;
        this.f23411n = d02;
        this.o = C0735d.O(number, T.S.f8224q);
        this.f23412p = new p0(c2619h, d02, this.l, this.f23410m, null);
    }

    @Override // T.Q0
    public final Object getValue() {
        return this.o.getValue();
    }
}
