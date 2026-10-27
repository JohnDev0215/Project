package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: renamed from: hR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0886hR extends Binder implements InterfaceC0942iR {
    public static InterfaceC0942iR c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.weblayer_private.interfaces.IClientDownload");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0942iR)) ? new C0830gR(iBinder) : (InterfaceC0942iR) iInterfaceQueryLocalInterface;
    }
}
