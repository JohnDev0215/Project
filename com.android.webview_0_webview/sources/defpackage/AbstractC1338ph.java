package defpackage;

import android.os.Binder;

/* JADX INFO: renamed from: ph, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC1338ph {
    public static Object a(InterfaceC1283oh interfaceC1283oh) {
        try {
            return interfaceC1283oh.a();
        } catch (SecurityException unused) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return interfaceC1283oh.a();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }
}
