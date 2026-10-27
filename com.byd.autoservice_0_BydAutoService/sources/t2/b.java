package t2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: compiled from: IAvmServiceCommandListener.java */
/* JADX INFO: loaded from: classes.dex */
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
        public boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
            if (i3 != 1) {
                if (i3 != 1598968902) {
                    return super.onTransact(i3, parcel, parcel2, i4);
                }
                parcel2.writeString("com.ts.avm.IAvmServiceCommandListener");
                return true;
            }
            parcel.enforceInterface("com.ts.avm.IAvmServiceCommandListener");
            o(parcel.readInt() != 0 ? CmdBean.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void o(CmdBean cmdBean) throws RemoteException;
}
