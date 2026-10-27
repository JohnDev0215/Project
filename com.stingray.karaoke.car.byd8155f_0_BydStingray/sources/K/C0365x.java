package K;

import F0.C0229t0;
import F0.R0;
import N0.C0411f;
import T.C0754m0;
import T.C0768u;
import T0.C0775a;
import T0.C0780f;
import a.AbstractC0904a;
import java.util.List;
import l0.InterfaceC1716g;
import n7.AbstractC1999n;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0365x extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3451m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0331b0 f3452n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0365x(C0331b0 c0331b0, int i7) {
        super(1);
        this.f3451m = i7;
        this.f3452n = c0331b0;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        InterfaceC2782k interfaceC2782k;
        m7.q qVar;
        R0 r02;
        boolean z6 = false;
        m7.q qVar2 = null;
        m7.q qVar3 = m7.q.f19267a;
        C0331b0 c0331b0 = this.f3452n;
        switch (this.f3451m) {
            case 0:
                C0.r rVar = (C0.r) obj;
                G0 g0D = c0331b0.d();
                if (g0D != null) {
                    g0D.c = rVar;
                }
                return qVar3;
            case 1:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                c0331b0.f3232q.setValue(bool);
                return qVar3;
            case 2:
                List list = (List) obj;
                if (c0331b0.d() != null) {
                    G0 g0D2 = c0331b0.d();
                    A7.m.c(g0D2);
                    list.add(g0D2.f3161a);
                    z6 = true;
                }
                return Boolean.valueOf(z6);
            case 3:
                C0411f c0411f = (C0411f) obj;
                T0.E e10 = c0331b0.f3222e;
                C0365x c0365x = c0331b0.f3235t;
                if (e10 != null) {
                    T0.y yVarN = c0331b0.d.n(AbstractC1999n.D(new C0780f(), new C0775a(c0411f, 1)));
                    e10.a(null, yVarN);
                    c0365x.b(yVarN);
                    qVar2 = qVar3;
                }
                if (qVar2 == null) {
                    String str = c0411f.l;
                    int length = str.length();
                    c0365x.b(new T0.y(str, AbstractC0904a.q(length, length), 4));
                }
                return Boolean.TRUE;
            case 4:
                int i7 = ((T0.l) obj).f8458a;
                Y y10 = c0331b0.f3233r;
                y10.getClass();
                if (T0.l.a(i7, 7)) {
                    interfaceC2782k = y10.a().f3207a;
                } else if (T0.l.a(i7, 2)) {
                    interfaceC2782k = y10.a().f3208b;
                } else if (T0.l.a(i7, 6)) {
                    interfaceC2782k = y10.a().c;
                } else if (T0.l.a(i7, 5)) {
                    interfaceC2782k = y10.a().d;
                } else if (T0.l.a(i7, 3)) {
                    interfaceC2782k = y10.a().f3209e;
                } else if (T0.l.a(i7, 4)) {
                    interfaceC2782k = y10.a().f3210f;
                } else {
                    if (!(T0.l.a(i7, 1) ? true : T0.l.a(i7, 0))) {
                        throw new IllegalStateException("invalid ImeAction");
                    }
                    interfaceC2782k = null;
                }
                if (interfaceC2782k != null) {
                    interfaceC2782k.b(y10);
                    qVar = qVar3;
                } else {
                    qVar = null;
                }
                if (qVar == null) {
                    if (T0.l.a(i7, 6)) {
                        InterfaceC1716g interfaceC1716g = y10.c;
                        if (interfaceC1716g == null) {
                            A7.m.j("focusManager");
                            throw null;
                        }
                        ((androidx.compose.ui.focus.b) interfaceC1716g).e(1);
                    } else if (T0.l.a(i7, 5)) {
                        InterfaceC1716g interfaceC1716g2 = y10.c;
                        if (interfaceC1716g2 == null) {
                            A7.m.j("focusManager");
                            throw null;
                        }
                        ((androidx.compose.ui.focus.b) interfaceC1716g2).e(2);
                    } else if (T0.l.a(i7, 7) && (r02 = y10.f3204a) != null) {
                        ((C0229t0) r02).a();
                    }
                }
                return qVar3;
            default:
                T0.y yVar = (T0.y) obj;
                String str2 = yVar.f8481a.l;
                C0411f c0411f2 = c0331b0.f3227j;
                if (!A7.m.a(str2, c0411f2 != null ? c0411f2.l : null)) {
                    c0331b0.f3228k.setValue(P.l);
                }
                long j3 = N0.K.f4356b;
                c0331b0.f(j3);
                c0331b0.e(j3);
                c0331b0.f3234s.b(yVar);
                C0754m0 c0754m0 = c0331b0.f3221b;
                C0768u c0768u = c0754m0.f8272b;
                if (c0768u != null) {
                    c0768u.p(c0754m0, null);
                }
                return qVar3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0365x(C0331b0 c0331b0, L0.j jVar) {
        super(1);
        this.f3451m = 3;
        this.f3452n = c0331b0;
    }
}
