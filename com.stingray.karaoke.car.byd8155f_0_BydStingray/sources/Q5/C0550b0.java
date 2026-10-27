package Q5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0550b0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ E.D f6706m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6707n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0550b0(E.D d, int i7, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f6706m = d;
        this.f6707n = i7;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0550b0(this.f6706m, this.f6707n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0550b0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            if (AbstractC0727y.i(50L, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                if (i7 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
                return m7.q.f19267a;
            }
            AbstractC1386c.u(obj);
        }
        this.l = 2;
        if (this.f6706m.j(this.f6707n, 0, this) == enumC2303a) {
            return enumC2303a;
        }
        return m7.q.f19267a;
    }
}
