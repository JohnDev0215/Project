package p;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class b52 implements Parcelable {
    public static final Parcelable.Creator<b52> CREATOR = new bg1(4);
    public z21 c;

    public b52(Parcel parcel) {
        z21 z21Var;
        IBinder strongBinder = parcel.readStrongBinder();
        int i = z42.c;
        if (strongBinder == null) {
            z21Var = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface(z21.a);
            if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof z21)) {
                y21 y21Var = new y21();
                y21Var.b = strongBinder;
                z21Var = y21Var;
            } else {
                z21Var = (z21) iInterfaceQueryLocalInterface;
            }
        }
        this.c = z21Var;
    }

    public void a(int i, Bundle bundle) {
    }

    public final void b(int i, Bundle bundle) {
        z21 z21Var = this.c;
        if (z21Var != null) {
            try {
                z21Var.e0(i, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            try {
                if (this.c == null) {
                    this.c = new z42(this);
                }
                parcel.writeStrongBinder(this.c.asBinder());
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
