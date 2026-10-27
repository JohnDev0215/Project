package K6;

import H7.H;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y.y0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ y0 f3701m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f3702n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(y0 y0Var, float f10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3701m = y0Var;
        this.f3702n = f10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.f3701m, this.f3702n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            int i10 = (int) this.f3702n;
            this.l = 1;
            if (H.O(this.f3701m, i10 - r1.f24028a.i(), this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return q.f19267a;
    }
}
