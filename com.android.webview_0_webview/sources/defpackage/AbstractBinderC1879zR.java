package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: renamed from: zR, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1879zR extends Binder implements AR {
    public static AR c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.weblayer_private.interfaces.IErrorPageCallbackClient");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof AR)) ? new C1824yR(iBinder) : (AR) iInterfaceQueryLocalInterface;
    }
}
