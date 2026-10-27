package defpackage;

/* JADX INFO: renamed from: yb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1826yb extends ClassLoader {
    public C1826yb(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override // java.lang.ClassLoader
    public Class findClass(String str) throws ClassNotFoundException {
        if (str == null) {
            throw new ClassNotFoundException("This ClassLoader should only be used for the androidx.webkit support library");
        }
        if (str.startsWith("org.chromium.support_lib_")) {
            return super.findClass(str);
        }
        throw new ClassNotFoundException("This ClassLoader should only be used for the androidx.webkit support library");
    }
}
