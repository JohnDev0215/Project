package C;

import S8.InterfaceC0725w;
import T.X;
import V8.S;
import h8.AbstractC1386c;
import java.util.ArrayList;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ k f829m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f830n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(k kVar, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f829m = kVar;
        this.f830n = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.f829m, this.f830n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            return q.f19267a;
        }
        AbstractC1386c.u(obj);
        ArrayList arrayList = new ArrayList();
        S s6 = this.f829m.f832a;
        f fVar = new f(arrayList, 0, this.f830n);
        this.l = 1;
        s6.b(fVar, this);
        return enumC2303a;
    }
}
