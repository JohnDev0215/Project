package b.b.c;

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

    /* JADX INFO: renamed from: b.b.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IBTServiceCallBack.java */
    public static abstract class AbstractBinderC0026a extends Binder implements a {
        public AbstractBinderC0026a() {
            attachInterface(this, "com.byd.bluetoothservice.IBTServiceCallBack");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.byd.bluetoothservice.IBTServiceCallBack");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    e(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    o(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    f(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    c(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    n(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    m(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    k(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    b(parcel.createTypedArrayList(BtCallInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    H(parcel.readInt() != 0 ? BtCallInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    Y(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    t(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    J(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    w(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    j(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void H(BtCallInfo btCallInfo) throws RemoteException;

    void J(int i) throws RemoteException;

    void Y(int i) throws RemoteException;

    void b(List<BtCallInfo> list) throws RemoteException;

    void c(String str, String str2, String str3, String str4) throws RemoteException;

    void e(int i) throws RemoteException;

    void f(int i) throws RemoteException;

    void j(int i) throws RemoteException;

    void k(int i) throws RemoteException;

    void m(long j) throws RemoteException;

    void n(long j) throws RemoteException;

    void o(int i) throws RemoteException;

    void t(boolean z) throws RemoteException;

    void w(boolean z) throws RemoteException;
}
