package d6;

import K5.A;
import K5.F;
import S8.AbstractC0727y;
import S8.E;
import S8.InterfaceC0725w;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import h8.AbstractC1386c;
import java.util.List;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i extends s7.i implements InterfaceC2785n {
    public j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13475m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j f13476n;
    public final /* synthetic */ String o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f13476n = jVar;
        this.o = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new i(this.f13476n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((i) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        j jVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f13475m;
        j jVar2 = this.f13476n;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                jVar2.B.setValue(LoadingInterface$LoadingState.Loading);
                F f10 = jVar2.f13482u;
                String str = this.o;
                this.l = jVar2;
                this.f13475m = 1;
                f10.getClass();
                Z8.e eVar = E.f8084a;
                obj = AbstractC0727y.F(Z8.d.f11010n, new A(f10, str, null), this);
                if (obj == enumC2303a) {
                    return enumC2303a;
                }
                jVar = jVar2;
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                jVar = this.l;
                AbstractC1386c.u(obj);
            }
            jVar.f13477A.setValue((List) obj);
            jVar2.B.setValue(LoadingInterface$LoadingState.Done);
        } catch (Exception e10) {
            B9.a aVar = Timber.f22577a;
            aVar.e("");
            aVar.a(e10);
            jVar2.B.setValue(LoadingInterface$LoadingState.Error);
        }
        return q.f19267a;
    }
}
