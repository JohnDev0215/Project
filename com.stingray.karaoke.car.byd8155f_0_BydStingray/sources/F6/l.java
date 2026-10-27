package f6;

import S8.InterfaceC0725w;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f14629m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(o oVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f14629m = oVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new l(this.f14629m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((l) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        o oVar = this.f14629m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                this.l = 1;
                if (o.p(oVar, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            oVar.l(LoadingInterface$LoadingState.Done);
        } catch (Throwable unused) {
            oVar.l(LoadingInterface$LoadingState.Error);
        }
        return q.f19267a;
    }
}
