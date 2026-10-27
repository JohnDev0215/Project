package w6;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAvmCallListener.java */
/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: w6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IAvmCallListener.java */
    public static abstract class AbstractBinderC0138a extends Binder implements a {
        public AbstractBinderC0138a() {
            attachInterface(this, "com.ts.avm.IAvmCallListener");
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
                parcel2.writeString("com.ts.avm.IAvmCallListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmCallListener");
            v0(parcel.readString(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void v0(String str, int i8, int i9) throws RemoteException;
}
