package x;

import S8.C0712i;
import S8.InterfaceC0725w;
import b9.InterfaceC1014a;
import e0.C1184x;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class z0 extends s7.i implements InterfaceC2785n {
    public InterfaceC1014a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2634f0 f23672m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23673n;
    public final /* synthetic */ C2634f0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(C2634f0 c2634f0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = c2634f0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new z0(this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((z0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, m7.f] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        C2634f0 c2634f0;
        InterfaceC1014a interfaceC1014a;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f23673n;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2634f0 c2634f02 = this.o;
            c2634f02.getClass();
            ((C1184x) B0.f23389a.getValue()).d(c2634f02, o0.f23586p, c2634f02.f23523s);
            b9.c cVar = c2634f02.f23526v;
            this.l = cVar;
            this.f23672m = c2634f02;
            this.f23673n = 1;
            if (cVar.c(null, this) == enumC2303a) {
                return enumC2303a;
            }
            c2634f0 = c2634f02;
            interfaceC1014a = cVar;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c2634f0 = this.f23672m;
            interfaceC1014a = this.l;
            AbstractC1386c.u(obj);
        }
        try {
            c2634f0.f23520p = c2634f0.Q0();
            C0712i c0712i = c2634f0.f23525u;
            if (c0712i != null) {
                c0712i.resumeWith(c2634f0.Q0());
            }
            c2634f0.f23525u = null;
            ((b9.c) interfaceC1014a).e(null);
            return m7.q.f19267a;
        } catch (Throwable th) {
            ((b9.c) interfaceC1014a).e(null);
            throw th;
        }
    }
}
