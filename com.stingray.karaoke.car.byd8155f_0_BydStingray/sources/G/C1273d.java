package g;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: renamed from: g.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1273d implements Parcelable {
    public static final Parcelable.Creator<C1273d> CREATOR = new android.support.v4.media.a(8);
    public InterfaceC1271b l;

    public void a(int i7, Bundle bundle) {
    }

    public final void d(int i7, Bundle bundle) {
        InterfaceC1271b interfaceC1271b = this.l;
        if (interfaceC1271b != null) {
            try {
                interfaceC1271b.k1(i7, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        synchronized (this) {
            try {
                if (this.l == null) {
                    this.l = new BinderC1272c(this);
                }
                parcel.writeStrongBinder(this.l.asBinder());
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
