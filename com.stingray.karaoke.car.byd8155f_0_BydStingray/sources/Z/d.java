package z;

import h8.AbstractC1386c;
import m7.q;
import q0.C2162a;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f24231m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f24232n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(m mVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f24232n = mVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        d dVar = new d(this.f24232n, interfaceC2189c);
        dVar.f24231m = obj;
        return dVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        q qVar = q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2722B c2722b = (C2722B) this.f24231m;
            C2162a c2162a = new C2162a(this.f24232n, 12);
            this.l = 1;
            Object objW = C7.a.w(c2722b, new e(c2162a, null), this);
            if (objW != enumC2303a) {
                objW = qVar;
            }
            if (objW == enumC2303a) {
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
