package O;

import T.X;
import androidx.compose.foundation.MagnifierElement;
import y.i0;
import y.o0;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class P extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4936m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ a1.b f4937n;
    public final /* synthetic */ X o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ P(a1.b bVar, X x8, int i7) {
        super(1);
        this.f4936m = i7;
        this.f4937n = bVar;
        this.o = x8;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        X x8 = this.o;
        a1.b bVar = this.f4937n;
        switch (this.f4936m) {
            case 0:
                long j3 = ((a1.g) obj).f11032a;
                x8.setValue(new a1.j(H7.H.c(bVar.O(a1.g.b(j3)), bVar.O(a1.g.a(j3)))));
                return m7.q.f19267a;
            default:
                A.F f10 = new A.F((InterfaceC2772a) obj, 27);
                P p10 = new P(bVar, x8, 0);
                L0.u uVar = i0.f23924a;
                return new MagnifierElement(f10, null, p10, Float.NaN, true, 9205357640488583168L, Float.NaN, Float.NaN, true, o0.o);
        }
    }
}
