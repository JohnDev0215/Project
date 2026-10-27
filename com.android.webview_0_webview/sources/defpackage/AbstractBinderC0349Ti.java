package defpackage;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: renamed from: Ti, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0349Ti extends AbstractBinderC0401Wg implements InterfaceC0367Ui {
    public static InterfaceC0367Ui v(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return iInterfaceQueryLocalInterface instanceof InterfaceC0367Ui ? (InterfaceC0367Ui) iInterfaceQueryLocalInterface : new C0403Wi(iBinder);
    }
}
