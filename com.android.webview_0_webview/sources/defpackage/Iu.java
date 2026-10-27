package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Iu extends Binder implements Ju {
    public Iu() {
        attachInterface(this, "org.chromium.base.process_launcher.IParentProcess");
    }

    public static Ju c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.base.process_launcher.IParentProcess");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof Ju)) ? new Hu(iBinder) : (Ju) iInterfaceQueryLocalInterface;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IParentProcess");
            ((BinderC1019ju) this).r(parcel.readInt());
            return true;
        }
        if (i != 2) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("org.chromium.base.process_launcher.IParentProcess");
            return true;
        }
        parcel.enforceInterface("org.chromium.base.process_launcher.IParentProcess");
        ((BinderC1019ju) this).b();
        parcel2.writeNoException();
        return true;
    }
}
