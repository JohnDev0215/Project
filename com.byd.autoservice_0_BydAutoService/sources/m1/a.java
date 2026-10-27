package m1;

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

    /* JADX INFO: renamed from: m1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IBTServiceCallBack.java */
    public static abstract class AbstractBinderC0038a extends Binder implements a {
        public AbstractBinderC0038a() {
            attachInterface(this, "com.byd.bluetoothservice.IBTServiceCallBack");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
            if (i3 == 1598968902) {
                parcel2.writeString("com.byd.bluetoothservice.IBTServiceCallBack");
                return true;
            }
            switch (i3) {
                case 1:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    d(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    i(parcel.readInt());
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
                    h(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    g(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    e(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    b(parcel.createTypedArrayList(BtCallInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    r(parcel.readInt() != 0 ? BtCallInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    B(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    q(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    s(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    v(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBTServiceCallBack");
                    a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i3, parcel, parcel2, i4);
            }
        }
    }

    void B(int i3) throws RemoteException;

    void a(int i3) throws RemoteException;

    void b(List<BtCallInfo> list) throws RemoteException;

    void c(String str, String str2, String str3, String str4) throws RemoteException;

    void d(int i3) throws RemoteException;

    void e(int i3) throws RemoteException;

    void f(int i3) throws RemoteException;

    void g(long j3) throws RemoteException;

    void h(long j3) throws RemoteException;

    void i(int i3) throws RemoteException;

    void q(boolean z3) throws RemoteException;

    void r(BtCallInfo btCallInfo) throws RemoteException;

    void s(int i3) throws RemoteException;

    void v(boolean z3) throws RemoteException;
}
