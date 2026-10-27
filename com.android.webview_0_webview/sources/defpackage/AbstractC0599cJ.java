package defpackage;

/* JADX INFO: renamed from: cJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0599cJ {
    public final InterfaceC0710eJ a(VJ vj, int i) {
        C1431rJ c1431rJ = new C1431rJ(vj);
        LJ ljZ = vj.z();
        InterfaceC0710eJ interfaceC0710eJD = d(ljZ, new II(ljZ, c1431rJ));
        SI si = new SI();
        si.s.add(interfaceC0710eJD);
        c1431rJ.s.w = si;
        c1431rJ.G();
        ((C0487aJ) interfaceC0710eJD.w()).v = i;
        return interfaceC0710eJD;
    }

    public final InterfaceC1212nJ b(InterfaceC0822gJ interfaceC0822gJ, C0934iJ c0934iJ) {
        return c(interfaceC0822gJ, c0934iJ.e());
    }

    public InterfaceC1212nJ c(InterfaceC0822gJ interfaceC0822gJ, VJ vj) {
        C1431rJ c1431rJ = new C1431rJ(vj);
        LJ ljZ = vj.z();
        c1431rJ.x(interfaceC0822gJ);
        c1431rJ.A(e(ljZ, interfaceC0822gJ));
        c1431rJ.G();
        return c1431rJ;
    }

    public abstract InterfaceC0710eJ d(LJ lj, InterfaceC1157mJ interfaceC1157mJ);

    public abstract AbstractC0766fJ e(LJ lj, InterfaceC0822gJ interfaceC0822gJ);

    public abstract String f();

    public abstract int g();
}
