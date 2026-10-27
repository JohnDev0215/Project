package b.b.c;

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

        /* JADX INFO: renamed from: b.b.c.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IBluetoothInterface.java */
        public static class C0027a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f590a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f591b;

            public C0027a(IBinder iBinder) {
                this.f591b = iBinder;
            }

            @Override // b.b.c.b
            public void L(b.b.c.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.f591b.transact(27, parcelObtain, parcelObtain2, 0) || a.e0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.e0().L(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.b.c.b
            public int V() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (!this.f591b.transact(11, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().V();
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
                return this.f591b;
            }

            @Override // b.b.c.b
            public boolean z() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBluetoothInterface");
                    if (!this.f591b.transact(1, parcelObtain, parcelObtain2, 0) && a.e0() != null) {
                        return a.e0().z();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.bluetoothservice.IBluetoothInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0027a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b e0() {
            return C0027a.f590a;
        }
    }

    void L(b.b.c.a aVar) throws RemoteException;

    int V() throws RemoteException;

    boolean z() throws RemoteException;
}
