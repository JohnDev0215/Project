package N5;

import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class T extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4615m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T(Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4615m = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        T t10 = new T(this.f4615m, interfaceC2189c);
        t10.l = obj;
        return t10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        T t10 = (T) create((ActionOwner) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        t10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.f4615m.o = (ActionOwner) this.l;
        return m7.q.f19267a;
    }
}
