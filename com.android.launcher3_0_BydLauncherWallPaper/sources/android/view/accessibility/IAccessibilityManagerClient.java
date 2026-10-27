package android.view.accessibility;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IAccessibilityManagerClient extends IInterface {

    public static class Default implements IAccessibilityManagerClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.accessibility.IAccessibilityManagerClient
        public void notifyServicesStateChanged(long j) throws RemoteException {
        }

        @Override // android.view.accessibility.IAccessibilityManagerClient
        public void setRelevantEventTypes(int i) throws RemoteException {
        }

        @Override // android.view.accessibility.IAccessibilityManagerClient
        public void setState(int i) throws RemoteException {
        }
    }

    void notifyServicesStateChanged(long j) throws RemoteException;

    void setRelevantEventTypes(int i) throws RemoteException;

    void setState(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IAccessibilityManagerClient {
        private static final String DESCRIPTOR = "android.view.accessibility.IAccessibilityManagerClient";
        static final int TRANSACTION_notifyServicesStateChanged = 2;
        static final int TRANSACTION_setRelevantEventTypes = 3;
        static final int TRANSACTION_setState = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setState";
            }
            if (i == 2) {
                return "notifyServicesStateChanged";
            }
            if (i != 3) {
                return null;
            }
            return "setRelevantEventTypes";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccessibilityManagerClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAccessibilityManagerClient)) {
                return (IAccessibilityManagerClient) iInterfaceQueryLocalInterface;
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
                setState(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyServicesStateChanged(parcel.readLong());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                setRelevantEventTypes(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAccessibilityManagerClient {
            public static IAccessibilityManagerClient sDefaultImpl;
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

            @Override // android.view.accessibility.IAccessibilityManagerClient
            public void setState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setState(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityManagerClient
            public void notifyServicesStateChanged(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyServicesStateChanged(j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityManagerClient
            public void setRelevantEventTypes(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setRelevantEventTypes(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAccessibilityManagerClient iAccessibilityManagerClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAccessibilityManagerClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAccessibilityManagerClient;
            return true;
        }

        public static IAccessibilityManagerClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
