package defpackage;

/* JADX INFO: renamed from: mx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1189mx extends ClassLoader {
    public final /* synthetic */ ClassLoader a;
    public final /* synthetic */ ClassLoader b;

    public C1189mx(C1244nx c1244nx, ClassLoader classLoader, ClassLoader classLoader2) {
        this.a = classLoader;
        this.b = classLoader2;
    }

    @Override // java.lang.ClassLoader
    public Class findClass(String str) {
        try {
            return this.a.loadClass(str);
        } catch (ClassNotFoundException unused) {
            return this.b.loadClass(str);
        }
    }
}
