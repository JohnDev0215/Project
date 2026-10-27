package defpackage;

import android.os.StrictMode;
import org.chromium.base.BuildInfo;

/* JADX INFO: renamed from: iP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0940iP {
    public static AbstractC0940iP a;

    static {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = !BuildInfo.a() ? StrictMode.allowThreadDiskReads() : null;
        try {
            a = (AbstractC0940iP) Class.forName("org.chromium.ui.touchless.TouchlessEventHandlerInternal").newInstance();
            if (threadPolicyAllowThreadDiskReads == null) {
                return;
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException unused) {
            a = null;
            if (threadPolicyAllowThreadDiskReads == null) {
                return;
            }
        } catch (Throwable th) {
            if (threadPolicyAllowThreadDiskReads != null) {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            }
            throw th;
        }
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
    }

    public static void a(InterfaceC0884hP interfaceC0884hP) {
    }

    public static void b(boolean z, boolean z2, boolean z3, boolean z4) {
        throw null;
    }

    public static void c(boolean z) {
        throw null;
    }

    public static boolean d() {
        return false;
    }

    public static boolean e(int i) {
        throw null;
    }

    public static void f(InterfaceC0884hP interfaceC0884hP) {
    }
}
