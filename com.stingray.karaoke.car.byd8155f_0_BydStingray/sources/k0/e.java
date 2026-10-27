package K0;

import L0.r;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e extends s7.i implements InterfaceC2785n {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3491m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ float f3492n;
    public final /* synthetic */ f o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(f fVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = fVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        e eVar = new e(this.o, interfaceC2189c);
        eVar.f3492n = ((Number) obj).floatValue();
        return eVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((e) create(Float.valueOf(((Number) obj).floatValue()), (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        boolean z6;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f3491m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            float f10 = this.f3492n;
            f fVar = this.o;
            Object obj2 = fVar.f3493a.d.l.get(L0.i.f3889e);
            if (obj2 == null) {
                obj2 = null;
            }
            InterfaceC2785n interfaceC2785n = (InterfaceC2785n) obj2;
            if (interfaceC2785n == null) {
                l9.d.i0("Required value was null.");
                throw null;
            }
            boolean z10 = ((L0.h) fVar.f3493a.d.d(r.f3952p)).c;
            if (z10) {
                f10 = -f10;
            }
            m0.c cVar = new m0.c(h6.e.a(0.0f, f10));
            this.l = z10;
            this.f3491m = 1;
            obj = interfaceC2785n.invoke(cVar, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
            z6 = z10;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z6 = this.l;
            AbstractC1386c.u(obj);
        }
        long j3 = ((m0.c) obj).f18880a;
        return new Float(z6 ? -m0.c.e(j3) : m0.c.e(j3));
    }
}
