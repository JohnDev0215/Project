package defpackage;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: renamed from: ag, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0503ag extends AbstractBinderC0401Wg implements InterfaceC0559bg {
    public AbstractBinderC0503ag() {
        super("com.google.android.gms.dynamic.IObjectWrapper");
    }

    public static InterfaceC0559bg v(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
        return iInterfaceQueryLocalInterface instanceof InterfaceC0559bg ? (InterfaceC0559bg) iInterfaceQueryLocalInterface : new C0671dg(iBinder);
    }
}
