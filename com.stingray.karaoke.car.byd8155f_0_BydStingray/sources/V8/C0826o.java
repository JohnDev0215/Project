package V8;

import N1.C0436q;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: V8.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0826o extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9638m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0817f f9639n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0826o(InterfaceC0817f interfaceC0817f, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9639n = interfaceC0817f;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0826o c0826o = new C0826o(this.f9639n, interfaceC2189c);
        c0826o.f9638m = obj;
        return c0826o;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0826o) create((U8.x) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0436q c0436q = new C0436q((U8.x) this.f9638m, 1);
            this.l = 1;
            if (this.f9639n.b(c0436q, this) == enumC2303a) {
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
