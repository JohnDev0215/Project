package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class PR extends Binder implements QR {
    public PR() {
        attachInterface(this, "org.chromium.weblayer_private.interfaces.IObjectWrapper");
    }

    public static QR c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.weblayer_private.interfaces.IObjectWrapper");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof QR)) ? new OR(iBinder) : (QR) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel2.writeString("org.chromium.weblayer_private.interfaces.IObjectWrapper");
        return true;
    }
}
