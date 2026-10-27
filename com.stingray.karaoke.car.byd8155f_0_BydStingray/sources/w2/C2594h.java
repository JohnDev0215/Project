package w2;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import e2.h0;
import e2.k0;
import e2.l0;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: w2.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2594h extends k0 {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f23200C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f23201D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f23202E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f23203F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f23204G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f23205H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f23206I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final SparseArray f23207J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final SparseBooleanArray f23208K;

    /* JADX WARN: Removed duplicated region for block: B:46:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C2594h(android.content.Context r9) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w2.C2594h.<init>(android.content.Context):void");
    }

    @Override // e2.k0
    public final void a(h0 h0Var) {
        this.f14137A.put(h0Var.f14118a, h0Var);
    }

    @Override // e2.k0
    public final l0 b() {
        return new C2595i(this);
    }

    @Override // e2.k0
    public final k0 c() {
        super.c();
        return this;
    }

    @Override // e2.k0
    public final k0 d(int i7) {
        super.d(i7);
        return this;
    }

    @Override // e2.k0
    public final k0 g() {
        this.f14155v = -3;
        return this;
    }

    @Override // e2.k0
    public final k0 h(h0 h0Var) {
        super.h(h0Var);
        return this;
    }

    @Override // e2.k0
    public final k0 i() {
        super.i();
        return this;
    }

    @Override // e2.k0
    public final k0 j(String[] strArr) {
        super.j(strArr);
        return this;
    }

    @Override // e2.k0
    public final k0 k() {
        this.f14154u = 0;
        return this;
    }

    @Override // e2.k0
    public final k0 l(int i7) {
        super.l(i7);
        return this;
    }

    @Override // e2.k0
    public final k0 m(int i7, int i10) {
        super.m(i7, i10);
        return this;
    }

    public final void n() {
        this.f23200C = true;
        this.f23201D = true;
        this.f23202E = true;
        this.f23203F = true;
        this.f23204G = true;
        this.f23205H = true;
        this.f23206I = true;
    }

    public C2594h(C2595i c2595i) {
        e(c2595i);
        this.f23200C = c2595i.f23226i0;
        this.f23201D = c2595i.f23227j0;
        this.f23202E = c2595i.f23228k0;
        this.f23203F = c2595i.f23229l0;
        this.f23204G = c2595i.f23230m0;
        this.f23205H = c2595i.f23231n0;
        this.f23206I = c2595i.f23232o0;
        SparseArray sparseArray = new SparseArray();
        int i7 = 0;
        while (true) {
            SparseArray sparseArray2 = c2595i.f23233p0;
            if (i7 < sparseArray2.size()) {
                sparseArray.put(sparseArray2.keyAt(i7), new HashMap((Map) sparseArray2.valueAt(i7)));
                i7++;
            } else {
                this.f23207J = sparseArray;
                this.f23208K = c2595i.f23234q0.clone();
                return;
            }
        }
    }

    public C2594h() {
        this.f23207J = new SparseArray();
        this.f23208K = new SparseBooleanArray();
        n();
    }
}
