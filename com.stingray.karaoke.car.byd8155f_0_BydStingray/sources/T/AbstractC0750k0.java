package T;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: T.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0750k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final P f8265a;

    public AbstractC0750k0(InterfaceC2772a interfaceC2772a) {
        this.f8265a = new P(interfaceC2772a);
    }

    public abstract C0752l0 a(Object obj);

    public T0 b() {
        return this.f8265a;
    }

    public final T0 c(C0752l0 c0752l0, T0 t02) {
        T0 s02;
        T0 t03 = null;
        t03 = null;
        t03 = null;
        t03 = null;
        t03 = null;
        t03 = null;
        if (t02 instanceof H) {
            if (c0752l0.d) {
                H h10 = (H) t02;
                h10.f8184a.setValue(c0752l0.a());
                t03 = h10;
            }
        } else if (t02 instanceof S0) {
            if ((c0752l0.f8267b || c0752l0.f8268e != null) && !c0752l0.d) {
                S0 s03 = (S0) t02;
                if (A7.m.a(c0752l0.a(), s03.f8225a)) {
                    t03 = s03;
                }
            }
        } else if (t02 instanceof C0774z) {
            c0752l0.getClass();
            ((C0774z) t02).getClass();
        }
        if (t03 != null) {
            return t03;
        }
        if (c0752l0.d) {
            G0 g02 = c0752l0.c;
            if (g02 == null) {
                g02 = S.f8224q;
            }
            s02 = new H(C0735d.O(c0752l0.f8268e, g02));
        } else {
            s02 = new S0(c0752l0.a());
        }
        return s02;
    }
}
