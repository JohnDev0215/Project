package w6;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: compiled from: IAvmServiceCommandListener.java */
/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceCommandListener.java */
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.ts.avm.IAvmServiceCommandListener");
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
                parcel2.writeString("com.ts.avm.IAvmServiceCommandListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmServiceCommandListener");
            R(parcel.readInt() != 0 ? CmdBean.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void R(CmdBean cmdBean) throws RemoteException;
}
