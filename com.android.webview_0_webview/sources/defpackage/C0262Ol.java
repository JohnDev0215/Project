package defpackage;

import java.util.List;

/* JADX INFO: renamed from: Ol, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0262Ol extends AbstractC0280Pl {
    public C0262Ol(AbstractC0226Ml abstractC0226Ml) {
        super(null);
    }

    public static InterfaceC0101Fl d(Object obj, long j) {
        return (InterfaceC0101Fl) AbstractC0066Dm.n(obj, j);
    }

    @Override // defpackage.AbstractC0280Pl
    public void a(Object obj, long j) {
        ((AbstractC0351Tk) d(obj, j)).s = false;
    }

    @Override // defpackage.AbstractC0280Pl
    public void b(Object obj, Object obj2, long j) {
        InterfaceC0101Fl interfaceC0101FlD = d(obj, j);
        InterfaceC0101Fl interfaceC0101FlD2 = d(obj2, j);
        int size = interfaceC0101FlD.size();
        int size2 = interfaceC0101FlD2.size();
        if (size > 0 && size2 > 0) {
            if (!((AbstractC0351Tk) interfaceC0101FlD).s) {
                interfaceC0101FlD = ((C0191Kl) interfaceC0101FlD).g(size2 + size);
            }
            interfaceC0101FlD.addAll(interfaceC0101FlD2);
        }
        if (size > 0) {
            interfaceC0101FlD2 = interfaceC0101FlD;
        }
        AbstractC0066Dm.f.q(obj, j, interfaceC0101FlD2);
    }

    @Override // defpackage.AbstractC0280Pl
    public List c(Object obj, long j) {
        InterfaceC0101Fl interfaceC0101FlD = d(obj, j);
        if (((AbstractC0351Tk) interfaceC0101FlD).s) {
            return interfaceC0101FlD;
        }
        int size = interfaceC0101FlD.size();
        InterfaceC0101Fl interfaceC0101FlG = ((C0191Kl) interfaceC0101FlD).g(size == 0 ? 10 : size * 2);
        AbstractC0066Dm.f.q(obj, j, interfaceC0101FlG);
        return interfaceC0101FlG;
    }
}
