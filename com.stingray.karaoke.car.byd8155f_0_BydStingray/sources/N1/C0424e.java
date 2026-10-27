package N1;

import h8.AbstractC1386c;
import java.util.List;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0424e extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4519m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f4520n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0424e(List list, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4520n = list;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0424e c0424e = new C0424e(this.f4520n, interfaceC2189c);
        c0424e.f4519m = obj;
        return c0424e;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0424e) create((C0431l) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0431l c0431l = (C0431l) this.f4519m;
            this.l = 1;
            if (com.bumptech.glide.c.D(this.f4520n, c0431l, this) == enumC2303a) {
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
