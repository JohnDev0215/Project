package I5;

import S8.InterfaceC0725w;
import T.X;
import com.stingray.karaoke.car.MainActivity;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class x extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ X l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ X f3025m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3026n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(X x8, X x9, MainActivity mainActivity, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = x8;
        this.f3025m = x9;
        this.f3026n = mainActivity;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new x(this.l, this.f3025m, this.f3026n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        x xVar = (x) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        xVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        if (((Boolean) this.l.getValue()).booleanValue()) {
            n7.z.b(null, false, new b0.b(-1177446084, new t(this.f3025m, this.f3026n), true), 11);
        }
        return m7.q.f19267a;
    }
}
