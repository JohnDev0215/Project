package W8;

import S8.InterfaceC0725w;
import V8.InterfaceC0817f;
import h8.AbstractC1386c;
import java.util.concurrent.atomic.AtomicInteger;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0817f[] f9931m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f9932n;
    public final /* synthetic */ AtomicInteger o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ U8.l f9933p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(InterfaceC0817f[] interfaceC0817fArr, int i7, AtomicInteger atomicInteger, U8.l lVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9931m = interfaceC0817fArr;
        this.f9932n = i7;
        this.o = atomicInteger;
        this.f9933p = lVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new r(this.f9931m, this.f9932n, this.o, this.f9933p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((r) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        AtomicInteger atomicInteger = this.o;
        U8.l lVar = this.f9933p;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                InterfaceC0817f[] interfaceC0817fArr = this.f9931m;
                int i10 = this.f9932n;
                InterfaceC0817f interfaceC0817f = interfaceC0817fArr[i10];
                q qVar = new q(lVar, i10);
                this.l = 1;
                if (interfaceC0817f.b(qVar, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            if (atomicInteger.decrementAndGet() == 0) {
                lVar.o(null, false);
            }
            return m7.q.f19267a;
        } finally {
            if (atomicInteger.decrementAndGet() == 0) {
                lVar.o(null, false);
            }
        }
    }
}
