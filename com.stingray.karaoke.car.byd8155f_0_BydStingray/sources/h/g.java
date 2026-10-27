package H;

import A.C0024l;
import E0.AbstractC0156f;
import E0.d0;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f2756m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0024l f2757n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(i iVar, C0024l c0024l, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2756m = iVar;
        this.f2757n = c0024l;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.f2756m, this.f2757n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        a jVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            i iVar = this.f2756m;
            if (iVar.f14695x) {
                if (iVar.l.f14695x) {
                    jVar = (a) AbstractC0156f.j(iVar, i.f2761A);
                    if (jVar == null) {
                        jVar = new j(iVar);
                    }
                } else {
                    jVar = null;
                }
                if (jVar != null) {
                    d0 d0VarS = AbstractC0156f.s(iVar);
                    this.l = 1;
                    if (jVar.k0(d0VarS, this.f2757n, this) == enumC2303a) {
                        return enumC2303a;
                    }
                }
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return q.f19267a;
    }
}
