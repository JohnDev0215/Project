package x2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: IBluetoothInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IBluetoothInterface.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: x2.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IBluetoothInterface.java */
        public static class C0143a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f9078a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f9079b;

            public C0143a(IBinder iBinder) {
                this.f9079b = iBinder;
            }

            @Override // x2.b
            public boolean B() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (!this.f9079b.transact(1, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        return a.B0().B();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // x2.b
            public void Q() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (this.f9079b.transact(26, parcelObtain, parcelObtain2, 0) || a.B0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.B0().Q();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // x2.b
            public void Z(x2.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f9079b.transact(27, parcelObtain, parcelObtain2, 0) || a.B0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.B0().Z(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f9079b;
            }

            @Override // x2.b
            public int k0() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (!this.f9079b.transact(25, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        return a.B0().k0();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // x2.b
            public int q0() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (!this.f9079b.transact(11, parcelObtain, parcelObtain2, 0) && a.B0() != null) {
                        return a.B0().q0();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b A0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.bluetoothservice.IBluetoothInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0143a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b B0() {
            return C0143a.f9078a;
        }
    }

    boolean B() throws RemoteException;

    void Q() throws RemoteException;

    void Z(x2.a aVar) throws RemoteException;

    int k0() throws RemoteException;

    int q0() throws RemoteException;
}
