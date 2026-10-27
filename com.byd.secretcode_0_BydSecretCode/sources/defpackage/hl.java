package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAvmCallListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface hl extends IInterface {

    /* JADX INFO: compiled from: IAvmCallListener.java */
    public static abstract class a extends Binder implements hl {
        public a() {
            attachInterface(this, "com.ts.avm.IAvmCallListener");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.ts.avm.IAvmCallListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmCallListener");
            i(parcel.readString(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void i(String str, int i, int i2) throws RemoteException;
}
