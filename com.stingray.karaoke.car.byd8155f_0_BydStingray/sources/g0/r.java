package g0;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import S8.W;
import h8.AbstractC1386c;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f14698m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.o f14699n;
    public final /* synthetic */ AtomicReference o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s7.i f14700p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public r(InterfaceC2782k interfaceC2782k, AtomicReference atomicReference, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f14699n = (A7.o) interfaceC2782k;
        this.o = atomicReference;
        this.f14700p = (s7.i) interfaceC2785n;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [s7.i, z7.n] */
    /* JADX WARN: Type inference failed for: r3v1, types: [A7.o, z7.k] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        r rVar = new r(this.f14699n, this.o, this.f14700p, interfaceC2189c);
        rVar.f14698m = obj;
        return rVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((r) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r9v9, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        q qVar;
        q qVar2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        AtomicReference atomicReference = this.o;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f14698m;
                qVar = new q(AbstractC0727y.o(interfaceC0725w.getCoroutineContext()), this.f14699n.b(interfaceC0725w));
                q qVar3 = (q) atomicReference.getAndSet(qVar);
                if (qVar3 != null) {
                    W w10 = qVar3.f14696a;
                    this.f14698m = qVar;
                    this.l = 1;
                    if (AbstractC0727y.g(w10, this) == enumC2303a) {
                        return enumC2303a;
                    }
                }
            } else {
                if (i7 != 1) {
                    if (i7 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    qVar2 = (q) this.f14698m;
                    try {
                        AbstractC1386c.u(obj);
                        while (!atomicReference.compareAndSet(qVar2, null) && atomicReference.get() == qVar2) {
                        }
                        return obj;
                    } catch (Throwable th) {
                        th = th;
                        while (!atomicReference.compareAndSet(qVar2, null)) {
                        }
                        throw th;
                    }
                }
                qVar = (q) this.f14698m;
                AbstractC1386c.u(obj);
            }
            ?? r92 = this.f14700p;
            Object obj2 = qVar.f14697b;
            this.f14698m = qVar;
            this.l = 2;
            obj = r92.invoke(obj2, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
            qVar2 = qVar;
            while (!atomicReference.compareAndSet(qVar2, null)) {
            }
            return obj;
        } catch (Throwable th2) {
            th = th2;
            qVar2 = qVar;
            while (!atomicReference.compareAndSet(qVar2, null) && atomicReference.get() == qVar2) {
            }
            throw th;
        }
    }
}
