package N1;

import S8.AbstractC0727y;
import S8.EnumC0726x;
import S8.k0;
import V8.C0830t;
import V8.C0832v;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0438t extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4553m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Q f4554n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0438t(Q q4, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4554n = q4;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0438t c0438t = new C0438t(this.f4554n, interfaceC2189c);
        c0438t.f4553m = obj;
        return c0438t;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0438t) create((U8.x) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            U8.x xVar = (U8.x) this.f4553m;
            EnumC0726x enumC0726x = EnumC0726x.f8141m;
            Q q4 = this.f4554n;
            k0 k0VarW = AbstractC0727y.w(xVar, null, enumC0726x, new C0437s(q4, null), 1);
            C0830t c0830t = new C0830t(new C0832v(new C0434o(k0VarW, null), 0, q4.f4490p), new C0435p(0, k0VarW, null));
            C0436q c0436q = new C0436q(xVar, 0);
            this.l = 1;
            if (c0830t.b(c0436q, this) == enumC2303a) {
                return enumC2303a;
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
