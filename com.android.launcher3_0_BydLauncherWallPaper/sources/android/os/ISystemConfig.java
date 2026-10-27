package android.os;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface ISystemConfig extends IInterface {

    public static class Default implements ISystemConfig {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.ISystemConfig
        public List<String> getDisabledUntilUsedPreinstalledCarrierApps() throws RemoteException {
            return null;
        }

        @Override // android.os.ISystemConfig
        public Map getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries() throws RemoteException {
            return null;
        }

        @Override // android.os.ISystemConfig
        public Map getDisabledUntilUsedPreinstalledCarrierAssociatedApps() throws RemoteException {
            return null;
        }
    }

    List<String> getDisabledUntilUsedPreinstalledCarrierApps() throws RemoteException;

    Map getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries() throws RemoteException;

    Map getDisabledUntilUsedPreinstalledCarrierAssociatedApps() throws RemoteException;

    public static abstract class Stub extends Binder implements ISystemConfig {
        private static final String DESCRIPTOR = "android.os.ISystemConfig";
        static final int TRANSACTION_getDisabledUntilUsedPreinstalledCarrierApps = 1;
        static final int TRANSACTION_getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries = 3;
        static final int TRANSACTION_getDisabledUntilUsedPreinstalledCarrierAssociatedApps = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getDisabledUntilUsedPreinstalledCarrierApps";
            }
            if (i == 2) {
                return "getDisabledUntilUsedPreinstalledCarrierAssociatedApps";
            }
            if (i != 3) {
                return null;
            }
            return "getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISystemConfig asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISystemConfig)) {
                return (ISystemConfig) iInterfaceQueryLocalInterface;
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
                List<String> disabledUntilUsedPreinstalledCarrierApps = getDisabledUntilUsedPreinstalledCarrierApps();
                parcel2.writeNoException();
                parcel2.writeStringList(disabledUntilUsedPreinstalledCarrierApps);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                Map disabledUntilUsedPreinstalledCarrierAssociatedApps = getDisabledUntilUsedPreinstalledCarrierAssociatedApps();
                parcel2.writeNoException();
                parcel2.writeMap(disabledUntilUsedPreinstalledCarrierAssociatedApps);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            Map disabledUntilUsedPreinstalledCarrierAssociatedAppEntries = getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries();
            parcel2.writeNoException();
            parcel2.writeMap(disabledUntilUsedPreinstalledCarrierAssociatedAppEntries);
            return true;
        }

        private static class Proxy implements ISystemConfig {
            public static ISystemConfig sDefaultImpl;
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

            @Override // android.os.ISystemConfig
            public List<String> getDisabledUntilUsedPreinstalledCarrierApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisabledUntilUsedPreinstalledCarrierApps();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.ISystemConfig
            public Map getDisabledUntilUsedPreinstalledCarrierAssociatedApps() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisabledUntilUsedPreinstalledCarrierAssociatedApps();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.ISystemConfig
            public Map getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisabledUntilUsedPreinstalledCarrierAssociatedAppEntries();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISystemConfig iSystemConfig) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSystemConfig == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSystemConfig;
            return true;
        }

        public static ISystemConfig getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
