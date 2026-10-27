package R;

import S8.C0722t;
import S8.InterfaceC0725w;
import S8.W;
import b9.InterfaceC1014a;
import h8.AbstractC1386c;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2189c;
import q7.InterfaceC2192f;
import r7.EnumC2303a;
import y.j0;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C extends s7.i implements InterfaceC2785n {
    public InterfaceC1014a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f7231m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public D f7232n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f7233p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ j0 f7234q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ D f7235r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ s7.i f7236s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C(j0 j0Var, D d, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7234q = j0Var;
        this.f7235r = d;
        this.f7236s = (s7.i) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.k] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C c = new C(this.f7234q, this.f7235r, this.f7236s, interfaceC2189c);
        c.f7233p = obj;
        return c;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [z7.k] */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        B b10;
        D d;
        ?? r42;
        InterfaceC1014a interfaceC1014a;
        D d10;
        Throwable th;
        B b11;
        InterfaceC1014a interfaceC1014a2;
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        ?? r12 = this.o;
        try {
            try {
                if (r12 == 0) {
                    AbstractC1386c.u(obj);
                    InterfaceC2192f interfaceC2192fF = ((InterfaceC0725w) this.f7233p).getCoroutineContext().F(C0722t.f8137m);
                    A7.m.c(interfaceC2192fF);
                    b10 = new B(this.f7234q, (W) interfaceC2192fF);
                    while (true) {
                        d = this.f7235r;
                        AtomicReference atomicReference3 = d.f7237a;
                        B b12 = (B) atomicReference3.get();
                        if (b12 != null && b10.f7229a.compareTo(b12.f7229a) < 0) {
                            throw new CancellationException("Current mutation had a higher priority");
                        }
                        while (!atomicReference3.compareAndSet(b12, b10)) {
                            if (atomicReference3.get() != b12) {
                                break;
                            }
                        }
                        if (b12 != null) {
                            b12.f7230b.g(null);
                        }
                        this.f7233p = b10;
                        b9.c cVar = d.f7238b;
                        this.l = cVar;
                        s7.i iVar = this.f7236s;
                        this.f7231m = iVar;
                        this.f7232n = d;
                        this.o = 1;
                        if (cVar.c(null, this) == enumC2303a) {
                            return enumC2303a;
                        }
                        r42 = iVar;
                        interfaceC1014a = cVar;
                    }
                } else {
                    if (r12 != 1) {
                        if (r12 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        d10 = (D) this.f7231m;
                        InterfaceC1014a interfaceC1014a3 = this.l;
                        b11 = (B) this.f7233p;
                        try {
                            AbstractC1386c.u(obj);
                            interfaceC1014a2 = interfaceC1014a3;
                            atomicReference2 = d10.f7237a;
                            while (!atomicReference2.compareAndSet(b11, null) && atomicReference2.get() == b11) {
                            }
                            ((b9.c) interfaceC1014a2).e(null);
                            return obj;
                        } catch (Throwable th2) {
                            th = th2;
                            atomicReference = d10.f7237a;
                            while (!atomicReference.compareAndSet(b11, null)) {
                            }
                            throw th;
                        }
                    }
                    D d11 = this.f7232n;
                    InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f7231m;
                    InterfaceC1014a interfaceC1014a4 = this.l;
                    B b13 = (B) this.f7233p;
                    AbstractC1386c.u(obj);
                    d = d11;
                    b10 = b13;
                    r42 = interfaceC2782k;
                    interfaceC1014a = interfaceC1014a4;
                }
                this.f7233p = b10;
                this.l = interfaceC1014a;
                this.f7231m = d;
                this.f7232n = null;
                this.o = 2;
                Object objB = r42.b(this);
                if (objB == enumC2303a) {
                    return enumC2303a;
                }
                d10 = d;
                obj = objB;
                b11 = b10;
                interfaceC1014a2 = interfaceC1014a;
                atomicReference2 = d10.f7237a;
                while (!atomicReference2.compareAndSet(b11, null)) {
                }
                ((b9.c) interfaceC1014a2).e(null);
                return obj;
            } catch (Throwable th3) {
                d10 = d;
                th = th3;
                b11 = b10;
                atomicReference = d10.f7237a;
                while (!atomicReference.compareAndSet(b11, null) && atomicReference.get() == b11) {
                }
                throw th;
            }
        } catch (Throwable th4) {
            ((b9.c) r12).e(null);
            throw th4;
        }
    }
}
