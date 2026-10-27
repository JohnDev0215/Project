package t2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAvmServiceListener.java */
/* JADX INFO: loaded from: classes.dex */
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
        public boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
            if (i3 != 1) {
                if (i3 != 1598968902) {
                    return super.onTransact(i3, parcel, parcel2, i4);
                }
                parcel2.writeString("com.ts.avm.IAvmServiceListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmServiceListener");
            m(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void m(int i3, String str) throws RemoteException;
}
