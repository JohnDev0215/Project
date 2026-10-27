package defpackage;

import java.util.Objects;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class JI {
    public static InterfaceC0656dK a(OJ oj) {
        if (oj.z() == null) {
            return null;
        }
        Objects.requireNonNull((CoreImpl) oj.z());
        return new WatcherImpl();
    }

    public static boolean b(int i) {
        return (i & 1) > 0;
    }

    public static boolean c(int i) {
        return (i & 2) > 0;
    }
}
