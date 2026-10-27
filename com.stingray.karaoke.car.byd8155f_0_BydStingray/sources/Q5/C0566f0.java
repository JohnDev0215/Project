package Q5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0566f0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ E.D f6787m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6788n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0566f0(E.D d, int i7, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f6787m = d;
        this.f6788n = i7;
        this.o = interfaceC2782k;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0566f0(this.f6787m, this.f6788n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0566f0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        int i10 = this.f6788n;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            io.sentry.internal.debugmeta.c cVar = E.D.f1301x;
            if (this.f6787m.f(i10, 0, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        this.o.b(new Integer(i10));
        return m7.q.f19267a;
    }
}
