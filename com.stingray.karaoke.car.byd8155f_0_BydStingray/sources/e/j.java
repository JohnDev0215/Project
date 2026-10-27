package E;

import S0.AbstractC0703o;
import S0.N;
import S0.O;
import T.C0761q;
import android.graphics.Typeface;
import l3.P;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class j extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1358m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f1359n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(Object obj, int i7) {
        super(4);
        this.f1358m = i7;
        this.f1359n = obj;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f1358m) {
            case 0:
                C0147d c0147d = (C0147d) obj;
                ((Number) obj2).intValue();
                C0761q c0761q = (C0761q) obj3;
                int iIntValue = ((Number) obj4).intValue();
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(c0147d) ? 4 : 2;
                }
                if ((iIntValue & 131) == 130 && c0761q.x()) {
                    c0761q.K();
                } else {
                    ((b0.b) this.f1359n).g(c0147d, c0761q, Integer.valueOf(iIntValue & 14));
                }
                return m7.q.f19267a;
            default:
                int i7 = ((S0.w) obj3).f7860a;
                int i10 = ((S0.x) obj4).f7861a;
                V0.c cVar = (V0.c) this.f1359n;
                O oB = ((S0.q) cVar.f9292p).b((AbstractC0703o) obj, (S0.A) obj2, i7, i10);
                if (oB instanceof N) {
                    Object obj5 = ((N) oB).l;
                    A7.m.d(obj5, "null cannot be cast to non-null type android.graphics.Typeface");
                    return (Typeface) obj5;
                }
                P p10 = new P(oB, cVar.f9297u);
                cVar.f9297u = p10;
                Object obj6 = p10.o;
                A7.m.d(obj6, "null cannot be cast to non-null type android.graphics.Typeface");
                return (Typeface) obj6;
        }
    }
}
