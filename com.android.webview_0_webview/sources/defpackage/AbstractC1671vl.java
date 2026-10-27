package defpackage;

/* JADX INFO: renamed from: vl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1671vl {
    public static final AbstractC1561tl a = new C1616ul();
    public static final AbstractC1561tl b;

    static {
        AbstractC1561tl abstractC1561tl;
        try {
            abstractC1561tl = (AbstractC1561tl) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            abstractC1561tl = null;
        }
        b = abstractC1561tl;
    }
}
