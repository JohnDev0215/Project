package k6;

import H7.H;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: k6.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1695c extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1696d f18243m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f18244n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1695c(C1696d c1696d, boolean z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f18243m = c1696d;
        this.f18244n = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1695c(this.f18243m, this.f18244n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1695c) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            K5.t tVar = this.f18243m.c;
            this.l = 1;
            Object objX = H.x(tVar.a(tVar.f3657a), new K5.m(this.f18244n, null), this);
            if (objX != enumC2303a) {
                objX = qVar;
            }
            if (objX == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
