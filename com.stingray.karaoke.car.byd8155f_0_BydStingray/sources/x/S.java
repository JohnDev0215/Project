package x;

import S8.C0722t;
import S8.InterfaceC0725w;
import b9.InterfaceC1014a;
import h8.AbstractC1386c;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2189c;
import q7.InterfaceC2192f;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class S extends s7.i implements InterfaceC2785n {
    public InterfaceC1014a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23446m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public T f23447n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f23448p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f23449q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T f23450r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ s7.i f23451s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public S(int i7, T t10, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23449q = i7;
        this.f23450r = t10;
        this.f23451s = (s7.i) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.k] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        S s6 = new S(this.f23449q, this.f23450r, this.f23451s, interfaceC2189c);
        s6.f23448p = obj;
        return s6;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((S) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [z7.k] */
    /* JADX WARN: Type inference failed for: r3v6 */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Q q4;
        T t10;
        ?? r32;
        InterfaceC1014a interfaceC1014a;
        T t11;
        Throwable th;
        Q q10;
        InterfaceC1014a interfaceC1014a2;
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        ?? r12 = this.o;
        try {
            try {
                if (r12 == 0) {
                    AbstractC1386c.u(obj);
                    InterfaceC2192f interfaceC2192fF = ((InterfaceC0725w) this.f23448p).getCoroutineContext().F(C0722t.f8137m);
                    A7.m.c(interfaceC2192fF);
                    q4 = new Q(this.f23449q, (S8.W) interfaceC2192fF);
                    while (true) {
                        t10 = this.f23450r;
                        AtomicReference atomicReference3 = t10.f23452a;
                        Q q11 = (Q) atomicReference3.get();
                        if (q11 != null && AbstractC2641j.a(q4.f23444a, q11.f23444a) < 0) {
                            throw new CancellationException("Current mutation had a higher priority");
                        }
                        while (!atomicReference3.compareAndSet(q11, q4)) {
                            if (atomicReference3.get() != q11) {
                                break;
                            }
                        }
                        if (q11 != null) {
                            q11.f23445b.g(new T.Q("Mutation interrupted", 2));
                        }
                        this.f23448p = q4;
                        b9.c cVar = t10.f23453b;
                        this.l = cVar;
                        s7.i iVar = this.f23451s;
                        this.f23446m = iVar;
                        this.f23447n = t10;
                        this.o = 1;
                        if (cVar.c(null, this) == enumC2303a) {
                            return enumC2303a;
                        }
                        r32 = iVar;
                        interfaceC1014a = cVar;
                    }
                } else {
                    if (r12 != 1) {
                        if (r12 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        t11 = (T) this.f23446m;
                        InterfaceC1014a interfaceC1014a3 = this.l;
                        q10 = (Q) this.f23448p;
                        try {
                            AbstractC1386c.u(obj);
                            interfaceC1014a2 = interfaceC1014a3;
                            atomicReference2 = t11.f23452a;
                            while (!atomicReference2.compareAndSet(q10, null) && atomicReference2.get() == q10) {
                            }
                            ((b9.c) interfaceC1014a2).e(null);
                            return obj;
                        } catch (Throwable th2) {
                            th = th2;
                            atomicReference = t11.f23452a;
                            while (!atomicReference.compareAndSet(q10, null)) {
                            }
                            throw th;
                        }
                    }
                    T t12 = this.f23447n;
                    InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f23446m;
                    InterfaceC1014a interfaceC1014a4 = this.l;
                    Q q12 = (Q) this.f23448p;
                    AbstractC1386c.u(obj);
                    t10 = t12;
                    q4 = q12;
                    r32 = interfaceC2782k;
                    interfaceC1014a = interfaceC1014a4;
                }
                this.f23448p = q4;
                this.l = interfaceC1014a;
                this.f23446m = t10;
                this.f23447n = null;
                this.o = 2;
                Object objB = r32.b(this);
                if (objB == enumC2303a) {
                    return enumC2303a;
                }
                t11 = t10;
                obj = objB;
                q10 = q4;
                interfaceC1014a2 = interfaceC1014a;
                atomicReference2 = t11.f23452a;
                while (!atomicReference2.compareAndSet(q10, null)) {
                }
                ((b9.c) interfaceC1014a2).e(null);
                return obj;
            } catch (Throwable th3) {
                t11 = t10;
                th = th3;
                q10 = q4;
                atomicReference = t11.f23452a;
                while (!atomicReference.compareAndSet(q10, null) && atomicReference.get() == q10) {
                }
                throw th;
            }
        } catch (Throwable th4) {
            ((b9.c) r12).e(null);
            throw th4;
        }
    }
}
