package x2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.info.BtCallInfo;
import java.util.List;

/* JADX INFO: compiled from: IBTServiceCallBack.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: x2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IBTServiceCallBack.java */
    public static abstract class AbstractBinderC0142a extends Binder implements a {
        public AbstractBinderC0142a() {
            attachInterface(this, "com.byd.bluetoothservice.IBTServiceCallBack");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1598968902) {
                parcel2.writeString("com.byd.bluetoothservice.IBTServiceCallBack");
                return true;
            }
            switch (i8) {
                case 1:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    d(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    k(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    e(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    b(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    j(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    i(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    h(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    CallState_Change(parcel.createTypedArrayList(BtCallInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    U(parcel.readInt() != 0 ? BtCallInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    t0(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    s(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    W(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    w(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    SyncContactsState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i8, parcel, parcel2, i9);
            }
        }
    }

    void CallState_Change(List<BtCallInfo> list) throws RemoteException;

    void SyncContactsState(int i8) throws RemoteException;

    void U(BtCallInfo btCallInfo) throws RemoteException;

    void W(int i8) throws RemoteException;

    void b(String str, String str2, String str3, String str4) throws RemoteException;

    void d(int i8) throws RemoteException;

    void e(int i8) throws RemoteException;

    void h(int i8) throws RemoteException;

    void i(long j8) throws RemoteException;

    void j(long j8) throws RemoteException;

    void k(int i8) throws RemoteException;

    void s(boolean z7) throws RemoteException;

    void t0(int i8) throws RemoteException;

    void w(boolean z7) throws RemoteException;
}
