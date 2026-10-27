package w6;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAvmServiceListener.java */
/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceListener.java */
    public static abstract class a extends Binder implements d {
        public a() {
            attachInterface(this, "com.ts.avm.IAvmServiceListener");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 != 1) {
                if (i8 != 1598968902) {
                    return super.onTransact(i8, parcel, parcel2, i9);
                }
                parcel2.writeString("com.ts.avm.IAvmServiceListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmServiceListener");
            o(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void o(int i8, String str) throws RemoteException;
}
