package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: renamed from: wR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1714wR extends Binder implements InterfaceC1769xR {
    public static InterfaceC1769xR c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.weblayer_private.interfaces.IDownloadCallbackClient");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1769xR)) ? new C1659vR(iBinder) : (InterfaceC1769xR) iInterfaceQueryLocalInterface;
    }
}
