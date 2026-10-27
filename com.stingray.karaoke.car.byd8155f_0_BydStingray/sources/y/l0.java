package y;

import S8.C0722t;
import S8.InterfaceC0725w;
import b9.InterfaceC1014a;
import h8.AbstractC1386c;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2189c;
import q7.InterfaceC2192f;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l0 extends s7.i implements InterfaceC2785n {
    public InterfaceC1014a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23951m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public n0 f23952n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f23953p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ j0 f23954q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ n0 f23955r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ s7.i f23956s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public l0(j0 j0Var, n0 n0Var, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23954q = j0Var;
        this.f23955r = n0Var;
        this.f23956s = (s7.i) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.k] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        l0 l0Var = new l0(this.f23954q, this.f23955r, this.f23956s, interfaceC2189c);
        l0Var.f23953p = obj;
        return l0Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((l0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [z7.k] */
    /* JADX WARN: Type inference failed for: r3v6 */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        k0 k0Var;
        n0 n0Var;
        ?? r32;
        InterfaceC1014a interfaceC1014a;
        n0 n0Var2;
        Throwable th;
        k0 k0Var2;
        InterfaceC1014a interfaceC1014a2;
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        ?? r12 = this.o;
        try {
            try {
                if (r12 == 0) {
                    AbstractC1386c.u(obj);
                    InterfaceC2192f interfaceC2192fF = ((InterfaceC0725w) this.f23953p).getCoroutineContext().F(C0722t.f8137m);
                    A7.m.c(interfaceC2192fF);
                    k0Var = new k0(this.f23954q, (S8.W) interfaceC2192fF);
                    n0Var = this.f23955r;
                    n0.a(n0Var, k0Var);
                    this.f23953p = k0Var;
                    b9.c cVar = n0Var.f23974b;
                    this.l = cVar;
                    s7.i iVar = this.f23956s;
                    this.f23951m = iVar;
                    this.f23952n = n0Var;
                    this.o = 1;
                    if (cVar.c(null, this) == enumC2303a) {
                        return enumC2303a;
                    }
                    r32 = iVar;
                    interfaceC1014a = cVar;
                } else {
                    if (r12 != 1) {
                        if (r12 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        n0Var2 = (n0) this.f23951m;
                        InterfaceC1014a interfaceC1014a3 = this.l;
                        k0Var2 = (k0) this.f23953p;
                        try {
                            AbstractC1386c.u(obj);
                            interfaceC1014a2 = interfaceC1014a3;
                            atomicReference2 = n0Var2.f23973a;
                            while (!atomicReference2.compareAndSet(k0Var2, null) && atomicReference2.get() == k0Var2) {
                            }
                            ((b9.c) interfaceC1014a2).e(null);
                            return obj;
                        } catch (Throwable th2) {
                            th = th2;
                            atomicReference = n0Var2.f23973a;
                            while (!atomicReference.compareAndSet(k0Var2, null)) {
                            }
                            throw th;
                        }
                    }
                    n0 n0Var3 = this.f23952n;
                    InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f23951m;
                    InterfaceC1014a interfaceC1014a4 = this.l;
                    k0 k0Var3 = (k0) this.f23953p;
                    AbstractC1386c.u(obj);
                    n0Var = n0Var3;
                    k0Var = k0Var3;
                    r32 = interfaceC2782k;
                    interfaceC1014a = interfaceC1014a4;
                }
                this.f23953p = k0Var;
                this.l = interfaceC1014a;
                this.f23951m = n0Var;
                this.f23952n = null;
                this.o = 2;
                Object objB = r32.b(this);
                if (objB == enumC2303a) {
                    return enumC2303a;
                }
                n0Var2 = n0Var;
                obj = objB;
                k0Var2 = k0Var;
                interfaceC1014a2 = interfaceC1014a;
                atomicReference2 = n0Var2.f23973a;
                while (!atomicReference2.compareAndSet(k0Var2, null)) {
                }
                ((b9.c) interfaceC1014a2).e(null);
                return obj;
            } catch (Throwable th3) {
                n0Var2 = n0Var;
                th = th3;
                k0Var2 = k0Var;
                atomicReference = n0Var2.f23973a;
                while (!atomicReference.compareAndSet(k0Var2, null) && atomicReference.get() == k0Var2) {
                }
                throw th;
            }
        } catch (Throwable th4) {
            ((b9.c) r12).e(null);
            throw th4;
        }
    }
}
