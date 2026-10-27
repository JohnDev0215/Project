package K0;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class a extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f f3477m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Runnable f3478n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(f fVar, Runnable runnable, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3477m = fVar;
        this.f3478n = runnable;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new a(this.f3477m, this.f3478n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((a) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        q qVar = q.f19267a;
        f fVar = this.f3477m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            j jVar = fVar.f3495e;
            this.l = 1;
            Object objB = jVar.b(0.0f - jVar.f3501b, this);
            if (objB != enumC2303a) {
                objB = qVar;
            }
            if (objB == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        l lVar = fVar.c;
        lVar.f3503a.setValue(Boolean.FALSE);
        this.f3478n.run();
        return qVar;
    }
}
