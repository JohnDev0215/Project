package androidx.lifecycle;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ I f11921m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11922n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(I i7, Object obj, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f11921m = i7;
        this.f11922n = obj;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new H(this.f11921m, this.f11922n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((H) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        I i10 = this.f11921m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0971i c0971i = i10.f11923a;
            this.l = 1;
            c0971i.j(this);
            if (qVar == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        i10.f11923a.i(this.f11922n);
        return qVar;
    }
}
