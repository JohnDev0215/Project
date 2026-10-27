package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: renamed from: Tq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0357Tq extends Binder implements InterfaceC0375Uq {
    public AbstractBinderC0357Tq() {
        attachInterface(this, "org.chromium.android_webview.common.services.IDeveloperUiService");
    }

    public static InterfaceC0375Uq c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.android_webview.common.services.IDeveloperUiService");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0375Uq)) ? new C0339Sq(iBinder) : (InterfaceC0375Uq) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.android_webview.common.services.IDeveloperUiService");
            return true;
        }
        parcel.enforceInterface("org.chromium.android_webview.common.services.IDeveloperUiService");
        ((BinderC1073ks) this).q(parcel.readHashMap(getClass().getClassLoader()));
        parcel2.writeNoException();
        return true;
    }
}
