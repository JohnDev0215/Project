package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: compiled from: IsReadyToPayService.java */
/* JADX INFO: loaded from: classes.dex */
public final class u21 implements v21 {
    public final IBinder e;

    public u21(IBinder iBinder) {
        this.e = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.e;
    }

    @Override // defpackage.v21
    public final void g(w21 w21Var) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("org.chromium.IsReadyToPayService");
            parcelObtain.writeStrongInterface(w21Var);
            this.e.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
