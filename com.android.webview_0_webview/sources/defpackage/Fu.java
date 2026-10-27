package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Fu extends Binder implements Gu {
    public Fu() {
        attachInterface(this, "org.chromium.base.process_launcher.IChildProcessService");
    }

    public static Gu c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.base.process_launcher.IChildProcessService");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof Gu)) ? new Eu(iBinder) : (Gu) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            boolean zO = ((BinderC1900zu) this).o(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(zO ? 1 : 0);
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            ((BinderC1900zu) this).h(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, Iu.c(parcel.readStrongBinder()), parcel.createBinderArrayList());
            return true;
        }
        if (i == 3) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            ((BinderC1900zu) this).m();
            return true;
        }
        if (i == 4) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            ((BinderC1900zu) this).l(parcel.readInt());
            return true;
        }
        if (i == 5) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            ((BinderC1900zu) this).k();
            return true;
        }
        if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel2.writeString("org.chromium.base.process_launcher.IChildProcessService");
        return true;
    }
}
