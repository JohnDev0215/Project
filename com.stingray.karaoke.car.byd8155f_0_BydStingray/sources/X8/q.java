package X8;

import S8.AbstractC0704a;
import S8.AbstractC0727y;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import s7.InterfaceC2411d;

/* JADX INFO: loaded from: classes.dex */
public class q extends AbstractC0704a implements InterfaceC2411d {
    public final InterfaceC2189c o;

    public q(InterfaceC2189c interfaceC2189c, InterfaceC2194h interfaceC2194h) {
        super(interfaceC2194h, true);
        this.o = interfaceC2189c;
    }

    @Override // S8.d0
    public final boolean S() {
        return true;
    }

    @Override // s7.InterfaceC2411d
    public final InterfaceC2411d getCallerFrame() {
        InterfaceC2189c interfaceC2189c = this.o;
        if (interfaceC2189c instanceof InterfaceC2411d) {
            return (InterfaceC2411d) interfaceC2189c;
        }
        return null;
    }

    @Override // S8.d0
    public void s(Object obj) {
        a.j(AbstractC0727y.y(obj), h6.e.k(this.o));
    }

    @Override // S8.d0
    public void t(Object obj) {
        this.o.resumeWith(AbstractC0727y.y(obj));
    }
}
