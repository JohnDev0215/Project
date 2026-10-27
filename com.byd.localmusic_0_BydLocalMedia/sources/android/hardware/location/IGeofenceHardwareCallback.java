package android.hardware.location;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IGeofenceHardwareCallback extends IInterface {

    public static class Default implements IGeofenceHardwareCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.location.IGeofenceHardwareCallback
        public void onGeofenceAdd(int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.location.IGeofenceHardwareCallback
        public void onGeofencePause(int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.location.IGeofenceHardwareCallback
        public void onGeofenceRemove(int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.location.IGeofenceHardwareCallback
        public void onGeofenceResume(int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.location.IGeofenceHardwareCallback
        public void onGeofenceTransition(int i, int i2, Location location, long j, int i3) throws RemoteException {
        }
    }

    void onGeofenceAdd(int i, int i2) throws RemoteException;

    void onGeofencePause(int i, int i2) throws RemoteException;

    void onGeofenceRemove(int i, int i2) throws RemoteException;

    void onGeofenceResume(int i, int i2) throws RemoteException;

    void onGeofenceTransition(int i, int i2, Location location, long j, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IGeofenceHardwareCallback {
        private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardwareCallback";
        static final int TRANSACTION_onGeofenceAdd = 2;
        static final int TRANSACTION_onGeofencePause = 4;
        static final int TRANSACTION_onGeofenceRemove = 3;
        static final int TRANSACTION_onGeofenceResume = 5;
        static final int TRANSACTION_onGeofenceTransition = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onGeofenceTransition";
            }
            if (i == 2) {
                return "onGeofenceAdd";
            }
            if (i == 3) {
                return "onGeofenceRemove";
            }
            if (i == 4) {
                return "onGeofencePause";
            }
            if (i != 5) {
                return null;
            }
            return "onGeofenceResume";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGeofenceHardwareCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGeofenceHardwareCallback)) {
                return (IGeofenceHardwareCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onGeofenceTransition(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Location.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onGeofenceAdd(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onGeofenceRemove(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onGeofencePause(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onGeofenceResume(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IGeofenceHardwareCallback {
            public static IGeofenceHardwareCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.hardware.location.IGeofenceHardwareCallback
            public void onGeofenceTransition(int i, int i2, Location location, long j, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (location != null) {
                        parcelObtain.writeInt(1);
                        location.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGeofenceTransition(i, i2, location, j, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IGeofenceHardwareCallback
            public void onGeofenceAdd(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGeofenceAdd(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IGeofenceHardwareCallback
            public void onGeofenceRemove(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGeofenceRemove(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IGeofenceHardwareCallback
            public void onGeofencePause(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGeofencePause(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.location.IGeofenceHardwareCallback
            public void onGeofenceResume(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGeofenceResume(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGeofenceHardwareCallback iGeofenceHardwareCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGeofenceHardwareCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGeofenceHardwareCallback;
            return true;
        }

        public static IGeofenceHardwareCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
