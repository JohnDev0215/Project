package l3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: INFCInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: INFCInterface.java */
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.byd.digitalkey.INFCInterface");
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
                parcel2.writeString("com.byd.digitalkey.INFCInterface");
                return true;
            }
            parcel.enforceInterface("com.byd.digitalkey.INFCInterface");
            N(parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void N(String str) throws RemoteException;
}
