package I5;

import H7.H;
import S8.InterfaceC0725w;
import com.stingray.karaoke.car.MainActivity;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class w extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3024m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(MainActivity mainActivity, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3024m = mainActivity;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new w(this.f3024m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((w) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            K5.t tVar = this.f3024m.f13002g0;
            if (tVar == null) {
                A7.m.j("dataStoreManager");
                throw null;
            }
            this.l = 1;
            Object objX = H.x(tVar.a(tVar.f3657a), new K5.n(2, null), this);
            if (objX != enumC2303a) {
                objX = qVar;
            }
            if (objX == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
