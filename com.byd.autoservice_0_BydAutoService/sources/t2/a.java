package t2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IAvmCallListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: t2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IAvmCallListener.java */
    public static abstract class AbstractBinderC0063a extends Binder implements a {
        public AbstractBinderC0063a() {
            attachInterface(this, "com.ts.avm.IAvmCallListener");
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
                parcel2.writeString("com.ts.avm.IAvmCallListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmCallListener");
            C(parcel.readString(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void C(String str, int i3, int i4) throws RemoteException;
}
