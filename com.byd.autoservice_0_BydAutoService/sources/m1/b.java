package m1;

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

        /* JADX INFO: renamed from: m1.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IBluetoothInterface.java */
        public static class C0039a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f3834a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f3835b;

            public C0039a(IBinder iBinder) {
                this.f3835b = iBinder;
            }

            @Override // m1.b
            public int A() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (!this.f3835b.transact(11, parcelObtain, parcelObtain2, 0) && a.G() != null) {
                        return a.G().A();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3835b;
            }

            @Override // m1.b
            public void w(m1.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f3835b.transact(27, parcelObtain, parcelObtain2, 0) || a.G() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.G().w(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // m1.b
            public boolean x() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (!this.f3835b.transact(1, parcelObtain, parcelObtain2, 0) && a.G() != null) {
                        return a.G().x();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b F(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.bluetoothservice.IBluetoothInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0039a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b G() {
            return C0039a.f3834a;
        }
    }

    int A() throws RemoteException;

    void w(m1.a aVar) throws RemoteException;

    boolean x() throws RemoteException;
}
