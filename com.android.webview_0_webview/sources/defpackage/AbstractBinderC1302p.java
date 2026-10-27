package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.Objects;

/* JADX INFO: renamed from: p, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1302p extends Binder implements InterfaceC1357q {
    public AbstractBinderC1302p() {
        attachInterface(this, "android.support.v4.os.IResultReceiver");
    }

    public static InterfaceC1357q c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1357q)) ? new C1247o(iBinder) : (InterfaceC1357q) iInterfaceQueryLocalInterface;
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
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
        parcel.enforceInterface("android.support.v4.os.IResultReceiver");
        int i3 = parcel.readInt();
        Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
        BinderC1466s binderC1466s = (BinderC1466s) this;
        Objects.requireNonNull(binderC1466s.a);
        binderC1466s.a.a(i3, bundle);
        return true;
    }
}
