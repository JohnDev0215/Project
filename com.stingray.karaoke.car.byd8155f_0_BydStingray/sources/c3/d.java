package C3;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.io.IOException;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import r9.AbstractC2312b;
import r9.C2316f;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends i implements InterfaceC2785n {
    public final /* synthetic */ e l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = eVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        e eVar = this.l;
        synchronized (eVar) {
            if (!eVar.f989w || eVar.f990x) {
                return q.f19267a;
            }
            try {
                eVar.z();
            } catch (IOException unused) {
                eVar.f991y = true;
            }
            try {
                if (eVar.f986t >= 2000) {
                    eVar.F();
                }
            } catch (IOException unused2) {
                eVar.f992z = true;
                eVar.f987u = AbstractC2312b.b(new C2316f());
            }
            return q.f19267a;
        }
    }
}
