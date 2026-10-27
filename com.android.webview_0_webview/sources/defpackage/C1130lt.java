package defpackage;

import android.os.Process;
import java.util.HashMap;

/* JADX INFO: renamed from: lt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1130lt {
    public final long a = Process.myTid();
    public HashMap b = new HashMap();

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Neither key nor object of UserDataHost can be null.");
        }
    }

    public final void b() {
        if (this.a != Process.myTid()) {
            throw new IllegalStateException("UserData must only be used on a single thread.");
        }
        if (this.b == null) {
            throw new IllegalStateException("Operation is not allowed after destroy().");
        }
    }

    public InterfaceC1074kt c(Class cls) {
        b();
        a(cls != null);
        return (InterfaceC1074kt) cls.cast(this.b.get(cls));
    }
}
