package defpackage;

/* JADX INFO: renamed from: gm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0844gm {
    public static final InterfaceC0732em a;
    public static final InterfaceC0732em b;

    static {
        InterfaceC0732em interfaceC0732em;
        try {
            interfaceC0732em = (InterfaceC0732em) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            interfaceC0732em = null;
        }
        a = interfaceC0732em;
        b = new C0788fm();
    }
}
