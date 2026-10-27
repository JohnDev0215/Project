package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import org.chromium.weblayer_private.interfaces.ICookieChangedCallbackClient;

/* JADX INFO: renamed from: nR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1220nR extends Binder implements ICookieChangedCallbackClient {
    public static ICookieChangedCallbackClient c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.weblayer_private.interfaces.ICookieChangedCallbackClient");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICookieChangedCallbackClient)) ? new C1165mR(iBinder) : (ICookieChangedCallbackClient) iInterfaceQueryLocalInterface;
    }
}
