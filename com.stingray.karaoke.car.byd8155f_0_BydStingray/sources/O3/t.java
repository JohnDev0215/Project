package o3;

import S8.InterfaceC0725w;
import T.C0730a0;
import T.X;
import h8.AbstractC1386c;
import java.util.List;
import n3.C1945j;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.C2634f0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class t extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f20498m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f20499n;
    public final /* synthetic */ C0730a0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(C2634f0 c2634f0, X x8, C0730a0 c0730a0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20498m = c2634f0;
        this.f20499n = x8;
        this.o = c0730a0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new t(this.f20498m, this.f20499n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((t) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C1945j c1945j = (C1945j) ((List) this.f20499n.getValue()).get(((List) r4.getValue()).size() - 2);
            float fI = this.o.i();
            this.l = 1;
            if (this.f20498m.h1(fI, c1945j, this) == enumC2303a) {
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
