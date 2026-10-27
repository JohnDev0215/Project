package N1;

import Q7.C0668v;
import S8.AbstractC0727y;
import S8.C0718o;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.concurrent.atomic.AtomicInteger;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class L extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4476m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Q f4477n;
    public final /* synthetic */ s7.i o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public L(Q q4, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4477n = q4;
        this.o = (s7.i) interfaceC2785n;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        L l = new L(this.f4477n, this.o, interfaceC2189c);
        l.f4476m = obj;
        return l;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((L) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f4476m;
            C0718o c0718o = new C0718o(true);
            c0718o.Q(null);
            Q q4 = this.f4477n;
            U u4 = new U(this.o, c0718o, q4.f4492r.a(), interfaceC0725w.getCoroutineContext());
            B8.t tVar = q4.f4496v;
            Object objP = ((U8.l) tVar.f809p).p(u4);
            if (objP instanceof U8.q) {
                U8.q qVar = objP instanceof U8.q ? (U8.q) objP : null;
                Throwable th = qVar != null ? qVar.f9269a : null;
                if (th == null) {
                    throw new C0668v("Channel was closed normally");
                }
                throw th;
            }
            if (objP instanceof U8.r) {
                throw new IllegalStateException("Check failed.");
            }
            if (((AtomicInteger) ((C0420a) tVar.f807m).l).getAndIncrement() == 0) {
                AbstractC0727y.w((InterfaceC0725w) tVar.f808n, null, null, new X(tVar, null), 3);
            }
            this.l = 1;
            obj = c0718o.u(this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
