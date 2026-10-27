package p;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public abstract class k23 implements IInterface {
    public final IBinder b;
    public final String c;

    public k23(IBinder iBinder, String str) {
        this.b = iBinder;
        this.c = str;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.b;
    }

    public final Parcel g0() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.c);
        return parcelObtain;
    }

    public final void h0(Parcel parcel, int i) {
        try {
            this.b.transact(i, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
