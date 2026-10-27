package defpackage;

/* JADX INFO: renamed from: Xl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0424Xl {
    public static final InterfaceC0388Vl a;
    public static final InterfaceC0388Vl b;

    static {
        InterfaceC0388Vl interfaceC0388Vl;
        try {
            interfaceC0388Vl = (InterfaceC0388Vl) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            interfaceC0388Vl = null;
        }
        a = interfaceC0388Vl;
        b = new C0406Wl();
    }
}
