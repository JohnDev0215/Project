package U8;

import S8.AbstractC0704a;
import S8.AbstractC0727y;
import S8.C0720q;
import S8.X;
import S8.c0;
import S8.d0;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final class w extends AbstractC0704a implements x, p {
    public final l o;

    public w(InterfaceC2194h interfaceC2194h, l lVar) {
        super(interfaceC2194h, true);
        this.o = lVar;
    }

    @Override // U8.A
    public final Object a(Object obj, InterfaceC2189c interfaceC2189c) {
        return this.o.a(obj, interfaceC2189c);
    }

    @Override // U8.z
    public final Object c(s7.i iVar) {
        return this.o.c(iVar);
    }

    @Override // U8.z
    public final Object d(W8.s sVar) throws Throwable {
        l lVar = this.o;
        lVar.getClass();
        Object objG = l.G(lVar, sVar);
        EnumC2303a enumC2303a = EnumC2303a.l;
        return objG;
    }

    @Override // U8.z
    public final B8.t e() {
        return this.o.e();
    }

    @Override // U8.z
    public final B8.t f() {
        return this.o.f();
    }

    @Override // S8.d0, S8.W
    public final void g(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        Object obj = d0.l.get(this);
        if (obj instanceof C0720q) {
            return;
        }
        if ((obj instanceof c0) && ((c0) obj).e()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new X(B(), null, this);
        }
        x(cancellationException);
    }

    @Override // S8.AbstractC0704a
    public final void g0(Throwable th, boolean z6) throws IllegalAccessException, InvocationTargetException {
        if (this.o.o(th, false) || z6) {
            return;
        }
        AbstractC0727y.q(th, this.f8101n);
    }

    @Override // U8.z
    public final Object h() {
        return this.o.h();
    }

    @Override // S8.AbstractC0704a
    public final void h0(Object obj) {
        C7.a.A(this.o);
    }

    @Override // U8.z
    public final d iterator() {
        l lVar = this.o;
        lVar.getClass();
        return new d(lVar);
    }

    @Override // U8.A
    public final Object p(Object obj) {
        return this.o.p(obj);
    }

    @Override // S8.d0
    public final void x(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        this.o.o(cancellationException, true);
        w(cancellationException);
    }
}
