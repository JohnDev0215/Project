package A;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.concurrent.CancellationException;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0028n extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f211m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0030o f212n;
    public final /* synthetic */ v1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0012f f213p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0028n(C0030o c0030o, v1 v1Var, InterfaceC0012f interfaceC0012f, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f212n = c0030o;
        this.o = v1Var;
        this.f213p = interfaceC0012f;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0028n c0028n = new C0028n(this.f212n, this.o, this.f213p, interfaceC2189c);
        c0028n.f211m = obj;
        return c0028n;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0028n) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0030o c0030o = this.f212n;
        try {
            try {
                if (i7 == 0) {
                    AbstractC1386c.u(obj);
                    S8.W wO = AbstractC0727y.o(((InterfaceC0725w) this.f211m).getCoroutineContext());
                    c0030o.f228H = true;
                    S0 s02 = c0030o.f230z;
                    y.j0 j0Var = y.j0.l;
                    C0026m c0026m = new C0026m(this.o, c0030o, this.f213p, wO, null);
                    this.l = 1;
                    if (s02.e(j0Var, c0026m, this) == enumC2303a) {
                        return enumC2303a;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    AbstractC1386c.u(obj);
                }
                c0030o.f223C.e();
                c0030o.f228H = false;
                c0030o.f223C.b(null);
                c0030o.f226F = false;
                return m7.q.f19267a;
            } catch (CancellationException e10) {
                throw e10;
            }
        } catch (Throwable th) {
            c0030o.f228H = false;
            c0030o.f223C.b(null);
            c0030o.f226F = false;
            throw th;
        }
    }
}
