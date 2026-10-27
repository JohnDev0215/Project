package D;

import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: D.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0135q extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0.V[] f1098m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f1099n;
    public final /* synthetic */ C0.M o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ A7.x f1100p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ A7.x f1101q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r f1102r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0135q(C0.V[] vArr, List list, C0.M m10, A7.x xVar, A7.x xVar2, r rVar) {
        super(1);
        this.f1098m = vArr;
        this.f1099n = list;
        this.o = m10;
        this.f1100p = xVar;
        this.f1101q = xVar2;
        this.f1102r = rVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C0.U u4 = (C0.U) obj;
        C0.V[] vArr = this.f1098m;
        int length = vArr.length;
        int i7 = 0;
        int i10 = 0;
        while (i10 < length) {
            C0.V v10 = vArr[i10];
            A7.m.d(v10, "null cannot be cast to non-null type androidx.compose.ui.layout.Placeable");
            AbstractC0133o.b(u4, v10, (C0.J) this.f1099n.get(i7), this.o.getLayoutDirection(), this.f1100p.l, this.f1101q.l, this.f1102r.f1103a);
            i10++;
            i7++;
        }
        return m7.q.f19267a;
    }
}
