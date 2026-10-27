package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ILocationListener extends IInterface {

    public static class Default implements ILocationListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.location.ILocationListener
        public void onLocationChanged(Location location) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onProviderDisabled(String str) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onProviderEnabled(String str) throws RemoteException {
        }

        @Override // android.location.ILocationListener
        public void onRemoved() throws RemoteException {
        }
    }

    void onLocationChanged(Location location) throws RemoteException;

    void onProviderDisabled(String str) throws RemoteException;

    void onProviderEnabled(String str) throws RemoteException;

    void onRemoved() throws RemoteException;

    public static abstract class Stub extends Binder implements ILocationListener {
        private static final String DESCRIPTOR = "android.location.ILocationListener";
        static final int TRANSACTION_onLocationChanged = 1;
        static final int TRANSACTION_onProviderDisabled = 3;
        static final int TRANSACTION_onProviderEnabled = 2;
        static final int TRANSACTION_onRemoved = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onLocationChanged";
            }
            if (i == 2) {
                return "onProviderEnabled";
            }
            if (i == 3) {
                return "onProviderDisabled";
            }
            if (i != 4) {
                return null;
            }
            return "onRemoved";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILocationListener)) {
                return (ILocationListener) iInterfaceQueryLocalInterface;
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
                onLocationChanged(parcel.readInt() != 0 ? Location.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onProviderEnabled(parcel.readString());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onProviderDisabled(parcel.readString());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onRemoved();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ILocationListener {
            public static ILocationListener sDefaultImpl;
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

            @Override // android.location.ILocationListener
            public void onLocationChanged(Location location) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (location != null) {
                        parcelObtain.writeInt(1);
                        location.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLocationChanged(location);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onProviderEnabled(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProviderEnabled(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onProviderDisabled(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProviderDisabled(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationListener
            public void onRemoved() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRemoved();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationListener iLocationListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLocationListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLocationListener;
            return true;
        }

        public static ILocationListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
