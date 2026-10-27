package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;

/* JADX INFO: renamed from: Qq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0303Qq extends Binder implements InterfaceC0321Rq {
    public AbstractBinderC0303Qq() {
        attachInterface(this, "org.chromium.android_webview.common.services.ICrashReceiverService");
    }

    public static InterfaceC0321Rq c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.android_webview.common.services.ICrashReceiverService");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0321Rq)) ? new C0285Pq(iBinder) : (InterfaceC0321Rq) iInterfaceQueryLocalInterface;
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
            parcel2.writeString("org.chromium.android_webview.common.services.ICrashReceiverService");
            return true;
        }
        parcel.enforceInterface("org.chromium.android_webview.common.services.ICrashReceiverService");
        ((BinderC1017js) this).e((ParcelFileDescriptor[]) parcel.createTypedArray(ParcelFileDescriptor.CREATOR), parcel.readArrayList(getClass().getClassLoader()));
        parcel2.writeNoException();
        return true;
    }
}
