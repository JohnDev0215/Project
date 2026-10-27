package defpackage;

/* JADX INFO: renamed from: Rl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0316Rl implements InterfaceC0460Zl {
    public InterfaceC0460Zl[] a;

    public C0316Rl(InterfaceC0460Zl... interfaceC0460ZlArr) {
        this.a = interfaceC0460ZlArr;
    }

    @Override // defpackage.InterfaceC0460Zl
    public boolean a(Class cls) {
        for (InterfaceC0460Zl interfaceC0460Zl : this.a) {
            if (interfaceC0460Zl.a(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.InterfaceC0460Zl
    public InterfaceC0442Yl b(Class cls) {
        for (InterfaceC0460Zl interfaceC0460Zl : this.a) {
            if (interfaceC0460Zl.a(cls)) {
                return interfaceC0460Zl.b(cls);
            }
        }
        StringBuilder sbF = Z7.f("No factory is available for message type: ");
        sbF.append(cls.getName());
        throw new UnsupportedOperationException(sbF.toString());
    }
}
