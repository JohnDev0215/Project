package c.a.c;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAuthResultCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IAuthResultCallback.java */
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.byd.logtools.IAuthResultCallback");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 != 1) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("com.byd.logtools.IAuthResultCallback");
                return true;
            }
            parcel.enforceInterface("com.byd.logtools.IAuthResultCallback");
            g(parcel.readInt(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void g(int i2, int i3, String str) throws RemoteException;
}
