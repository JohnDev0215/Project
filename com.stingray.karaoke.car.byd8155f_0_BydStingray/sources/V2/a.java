package V2;

import B2.AbstractC0062b;
import e2.M;
import h2.t;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f9328b;
    public int c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f9329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t f9330f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final t f9331g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9332h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9333i;

    public a(t tVar, t tVar2, boolean z6) throws M {
        this.f9331g = tVar;
        this.f9330f = tVar2;
        this.f9329e = z6;
        tVar2.H(12);
        this.f9327a = tVar2.z();
        tVar.H(12);
        this.f9333i = tVar.z();
        AbstractC0062b.c("first_chunk must be 1", tVar.h() == 1);
        this.f9328b = -1;
    }

    public final boolean a() {
        int i7 = this.f9328b + 1;
        this.f9328b = i7;
        if (i7 == this.f9327a) {
            return false;
        }
        boolean z6 = this.f9329e;
        t tVar = this.f9330f;
        this.d = z6 ? tVar.A() : tVar.x();
        if (this.f9328b == this.f9332h) {
            t tVar2 = this.f9331g;
            this.c = tVar2.z();
            tVar2.I(4);
            int i10 = this.f9333i - 1;
            this.f9333i = i10;
            this.f9332h = i10 > 0 ? tVar2.z() - 1 : -1;
        }
        return true;
    }
}
