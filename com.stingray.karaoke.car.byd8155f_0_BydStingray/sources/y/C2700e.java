package y;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: y.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2700e extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractC2705j f23898m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C.m f23899n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2700e(AbstractC2705j abstractC2705j, C.m mVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23898m = abstractC2705j;
        this.f23899n = mVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C2700e(this.f23898m, this.f23899n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2700e) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C.k kVar = this.f23898m.f23926A;
            if (kVar != null) {
                this.l = 1;
                if (kVar.a(this.f23899n, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
