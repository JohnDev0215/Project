package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;

/* JADX INFO: renamed from: Wq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0411Wq extends Binder implements InterfaceC0429Xq {
    public AbstractBinderC0411Wq() {
        attachInterface(this, "org.chromium.android_webview.common.services.IVariationsSeedServer");
    }

    public static InterfaceC0429Xq c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.android_webview.common.services.IVariationsSeedServer");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0429Xq)) ? new C0393Vq(iBinder) : (InterfaceC0429Xq) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.android_webview.common.services.IVariationsSeedServer");
            ((BinderC1294os) this).s(parcel.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), AbstractBinderC0465Zq.c(parcel.readStrongBinder()));
            return true;
        }
        if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel2.writeString("org.chromium.android_webview.common.services.IVariationsSeedServer");
        return true;
    }
}
