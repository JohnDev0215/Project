package y;

import S8.C0722t;
import S8.InterfaceC0725w;
import b9.InterfaceC1014a;
import h8.AbstractC1386c;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2189c;
import q7.InterfaceC2192f;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends s7.i implements InterfaceC2785n {
    public InterfaceC1014a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23959m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f23960n;
    public n0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23961p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f23962q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ j0 f23963r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ n0 f23964s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ s7.i f23965t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f23966u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public m0(j0 j0Var, n0 n0Var, InterfaceC2785n interfaceC2785n, Object obj, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23963r = j0Var;
        this.f23964s = n0Var;
        this.f23965t = (s7.i) interfaceC2785n;
        this.f23966u = obj;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        m0 m0Var = new m0(this.f23963r, this.f23964s, this.f23965t, this.f23966u, interfaceC2189c);
        m0Var.f23962q = obj;
        return m0Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r6v1, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        k0 k0Var;
        n0 n0Var;
        Object obj2;
        ?? r62;
        InterfaceC1014a interfaceC1014a;
        n0 n0Var2;
        Throwable th;
        k0 k0Var2;
        InterfaceC1014a interfaceC1014a2;
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        ?? r12 = this.f23961p;
        try {
            try {
                if (r12 == 0) {
                    AbstractC1386c.u(obj);
                    InterfaceC2192f interfaceC2192fF = ((InterfaceC0725w) this.f23962q).getCoroutineContext().F(C0722t.f8137m);
                    A7.m.c(interfaceC2192fF);
                    k0Var = new k0(this.f23963r, (S8.W) interfaceC2192fF);
                    n0Var = this.f23964s;
                    n0.a(n0Var, k0Var);
                    this.f23962q = k0Var;
                    b9.c cVar = n0Var.f23974b;
                    this.l = cVar;
                    s7.i iVar = this.f23965t;
                    this.f23959m = iVar;
                    Object obj3 = this.f23966u;
                    this.f23960n = obj3;
                    this.o = n0Var;
                    this.f23961p = 1;
                    if (cVar.c(null, this) == enumC2303a) {
                        return enumC2303a;
                    }
                    obj2 = obj3;
                    interfaceC1014a = cVar;
                    r62 = iVar;
                } else {
                    if (r12 != 1) {
                        if (r12 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        n0Var2 = (n0) this.f23959m;
                        InterfaceC1014a interfaceC1014a3 = this.l;
                        k0Var2 = (k0) this.f23962q;
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
                    n0 n0Var3 = this.o;
                    obj2 = this.f23960n;
                    InterfaceC2785n interfaceC2785n = (InterfaceC2785n) this.f23959m;
                    InterfaceC1014a interfaceC1014a4 = this.l;
                    k0 k0Var3 = (k0) this.f23962q;
                    AbstractC1386c.u(obj);
                    n0Var = n0Var3;
                    k0Var = k0Var3;
                    r62 = interfaceC2785n;
                    interfaceC1014a = interfaceC1014a4;
                }
                this.f23962q = k0Var;
                this.l = interfaceC1014a;
                this.f23959m = n0Var;
                this.f23960n = null;
                this.o = null;
                this.f23961p = 2;
                Object objInvoke = r62.invoke(obj2, this);
                if (objInvoke == enumC2303a) {
                    return enumC2303a;
                }
                n0Var2 = n0Var;
                obj = objInvoke;
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
