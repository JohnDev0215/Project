package defpackage;

/* JADX INFO: renamed from: yl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1836yl implements InterfaceC0460Zl {
    public static final C1836yl a = new C1836yl();

    @Override // defpackage.InterfaceC0460Zl
    public boolean a(Class cls) {
        return AbstractC0065Dl.class.isAssignableFrom(cls);
    }

    @Override // defpackage.InterfaceC0460Zl
    public InterfaceC0442Yl b(Class cls) {
        if (!AbstractC0065Dl.class.isAssignableFrom(cls)) {
            StringBuilder sbF = Z7.f("Unsupported message type: ");
            sbF.append(cls.getName());
            throw new IllegalArgumentException(sbF.toString());
        }
        try {
            return (InterfaceC0442Yl) AbstractC0065Dl.d(cls.asSubclass(AbstractC0065Dl.class)).c(EnumC0047Cl.BUILD_MESSAGE_INFO, null, null);
        } catch (Exception e) {
            StringBuilder sbF2 = Z7.f("Unable to get message info for ");
            sbF2.append(cls.getName());
            throw new RuntimeException(sbF2.toString(), e);
        }
    }
}
