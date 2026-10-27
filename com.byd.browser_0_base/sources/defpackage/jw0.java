package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import defpackage.kw0;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: IPaymentDetailsUpdateService.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class jw0 extends Binder implements IInterface {
    public jw0() {
        attachInterface(this, "org.chromium.components.payments.IPaymentDetailsUpdateService");
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("org.chromium.components.payments.IPaymentDetailsUpdateService");
        }
        if (i == 1598968902) {
            parcel2.writeString("org.chromium.components.payments.IPaymentDetailsUpdateService");
            return true;
        }
        if (i == 1) {
            PostTask.d(rh3.a, new gu(Binder.getCallingUid(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null), kw0.a.A(parcel.readStrongBinder())));
        } else if (i == 2) {
            PostTask.d(rh3.a, new vd(Binder.getCallingUid(), parcel.readString(), kw0.a.A(parcel.readStrongBinder())));
        } else {
            if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            PostTask.d(rh3.a, new uu2(Binder.getCallingUid(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null), kw0.a.A(parcel.readStrongBinder())));
        }
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
