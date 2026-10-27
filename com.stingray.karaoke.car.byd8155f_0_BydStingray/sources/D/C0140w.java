package D;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: D.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0140w extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0.V[] f1113m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0141x f1114n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0.M f1115p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int[] f1116q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0140w(C0.V[] vArr, C0141x c0141x, int i7, C0.M m10, int[] iArr) {
        super(1);
        this.f1113m = vArr;
        this.f1114n = c0141x;
        this.o = i7;
        this.f1115p = m10;
        this.f1116q = iArr;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C0.U u4 = (C0.U) obj;
        C0.V[] vArr = this.f1113m;
        int length = vArr.length;
        int i7 = 0;
        int i10 = 0;
        while (i7 < length) {
            C0.V v10 = vArr[i7];
            int i11 = i10 + 1;
            A7.m.c(v10);
            Object objI = v10.i();
            O o = objI instanceof O ? (O) objI : null;
            a1.k layoutDirection = this.f1115p.getLayoutDirection();
            C0141x c0141x = this.f1114n;
            c0141x.getClass();
            AbstractC0120b abstractC0120b = o != null ? o.c : null;
            int i12 = this.o;
            C0.U.d(u4, v10, abstractC0120b != null ? abstractC0120b.c(i12 - v10.l, layoutDirection) : c0141x.f1118b.a(0, i12 - v10.l, layoutDirection), this.f1116q[i10]);
            i7++;
            i10 = i11;
        }
        return m7.q.f19267a;
    }
}
