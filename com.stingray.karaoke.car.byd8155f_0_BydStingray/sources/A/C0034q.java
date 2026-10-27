package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.concurrent.CancellationException;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.AbstractC2631e;
import x.C2648n;
import x.C2657x;
import x.C2658y;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0034q extends s7.i implements InterfaceC2785n {
    public A7.w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2648n f253m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f254n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r f255p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ N0 f256q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0034q(float f10, r rVar, N0 n02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = f10;
        this.f255p = rVar;
        this.f256q = n02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0034q(this.o, this.f255p, this.f256q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0034q) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        float f10;
        C2648n c2648n;
        A7.w wVar;
        Object objD;
        Object obj2 = EnumC2303a.l;
        int i7 = this.f254n;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            f10 = this.o;
            if (Math.abs(f10) > 1.0f) {
                A7.w wVar2 = new A7.w();
                wVar2.l = f10;
                A7.w wVar3 = new A7.w();
                C2648n c2648nB = AbstractC2631e.b(f10, 28);
                try {
                    r rVar = this.f255p;
                    C2658y c2658y = rVar.f261a;
                    C0032p c0032p = new C0032p(wVar3, this.f256q, wVar2, rVar);
                    this.l = wVar2;
                    this.f253m = c2648nB;
                    this.f254n = 1;
                    objD = AbstractC2631e.d(c2648nB, new C2657x(c2658y, x.E0.f23396a, c2648nB.f23569m.getValue(), c2648nB.f23570n), Long.MIN_VALUE, c0032p, this);
                    if (objD != obj2) {
                        objD = m7.q.f19267a;
                    }
                } catch (CancellationException unused) {
                    c2648n = c2648nB;
                    wVar = wVar2;
                    wVar.l = ((Number) c2648n.l.f23395b.b(c2648n.f23570n)).floatValue();
                }
                if (objD == obj2) {
                    return obj2;
                }
                wVar = wVar2;
                f10 = wVar.l;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c2648n = this.f253m;
            wVar = this.l;
            try {
                AbstractC1386c.u(obj);
            } catch (CancellationException unused2) {
                wVar.l = ((Number) c2648n.l.f23395b.b(c2648n.f23570n)).floatValue();
            }
            f10 = wVar.l;
        }
        return new Float(f10);
    }
}
