package y4;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: ICPAAInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {

    /* JADX INFO: compiled from: ICPAAInterface.java */
    public static abstract class a extends Binder implements c {
        public a() {
            attachInterface(this, "com.byd.uereport.ICPAAInterface");
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
                parcel2.writeString("com.byd.uereport.ICPAAInterface");
                return true;
            }
            parcel.enforceInterface("com.byd.uereport.ICPAAInterface");
            n0(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }
    }

    void n0(boolean z7) throws RemoteException;
}
