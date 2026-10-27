package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: renamed from: Zq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0465Zq extends Binder implements InterfaceC0514ar {
    public AbstractBinderC0465Zq() {
        attachInterface(this, "org.chromium.android_webview.common.services.IVariationsSeedServerCallback");
    }

    public static InterfaceC0514ar c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.android_webview.common.services.IVariationsSeedServerCallback");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0514ar)) ? new C0447Yq(iBinder) : (InterfaceC0514ar) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.android_webview.common.services.IVariationsSeedServerCallback");
            ((BinderC1621uq) this).j(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            return true;
        }
        if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel2.writeString("org.chromium.android_webview.common.services.IVariationsSeedServerCallback");
        return true;
    }
}
