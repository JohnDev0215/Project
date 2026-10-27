package T1;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8510a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f8511b;
    public t c;
    public t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8512e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8513f;

    public q(t tVar) {
        this.f8511b = tVar;
        this.c = tVar;
    }

    public final void a() {
        this.f8510a = 1;
        this.c = this.f8511b;
        this.f8513f = 0;
    }

    public final boolean b() {
        U1.a aVarC = this.c.f8521b.c();
        int iB = aVarC.b(6);
        return !(iB == 0 || ((ByteBuffer) aVarC.o).get(iB + aVarC.l) == 0) || this.f8512e == 65039;
    }
}
