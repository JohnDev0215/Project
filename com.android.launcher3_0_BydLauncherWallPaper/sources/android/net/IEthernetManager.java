package android.net;

import android.net.IEthernetServiceListener;
import android.net.ITetheredInterfaceCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IEthernetManager extends IInterface {

    public static class Default implements IEthernetManager {
        @Override // android.net.IEthernetManager
        public void addListener(IEthernetServiceListener iEthernetServiceListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.IEthernetManager
        public String[] getAvailableInterfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.IEthernetManager
        public IpConfiguration getConfiguration(String str) throws RemoteException {
            return null;
        }

        @Override // android.net.IEthernetManager
        public boolean isAvailable(String str) throws RemoteException {
            return false;
        }

        @Override // android.net.IEthernetManager
        public void releaseTetheredInterface(ITetheredInterfaceCallback iTetheredInterfaceCallback) throws RemoteException {
        }

        @Override // android.net.IEthernetManager
        public void removeListener(IEthernetServiceListener iEthernetServiceListener) throws RemoteException {
        }

        @Override // android.net.IEthernetManager
        public void requestTetheredInterface(ITetheredInterfaceCallback iTetheredInterfaceCallback) throws RemoteException {
        }

        @Override // android.net.IEthernetManager
        public void setConfiguration(String str, IpConfiguration ipConfiguration) throws RemoteException {
        }

        @Override // android.net.IEthernetManager
        public void setIncludeTestInterfaces(boolean z) throws RemoteException {
        }
    }

    void addListener(IEthernetServiceListener iEthernetServiceListener) throws RemoteException;

    String[] getAvailableInterfaces() throws RemoteException;

    IpConfiguration getConfiguration(String str) throws RemoteException;

    boolean isAvailable(String str) throws RemoteException;

    void releaseTetheredInterface(ITetheredInterfaceCallback iTetheredInterfaceCallback) throws RemoteException;

    void removeListener(IEthernetServiceListener iEthernetServiceListener) throws RemoteException;

    void requestTetheredInterface(ITetheredInterfaceCallback iTetheredInterfaceCallback) throws RemoteException;

    void setConfiguration(String str, IpConfiguration ipConfiguration) throws RemoteException;

    void setIncludeTestInterfaces(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IEthernetManager {
        private static final String DESCRIPTOR = "android.net.IEthernetManager";
        static final int TRANSACTION_addListener = 5;
        static final int TRANSACTION_getAvailableInterfaces = 1;
        static final int TRANSACTION_getConfiguration = 2;
        static final int TRANSACTION_isAvailable = 4;
        static final int TRANSACTION_releaseTetheredInterface = 9;
        static final int TRANSACTION_removeListener = 6;
        static final int TRANSACTION_requestTetheredInterface = 8;
        static final int TRANSACTION_setConfiguration = 3;
        static final int TRANSACTION_setIncludeTestInterfaces = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getAvailableInterfaces";
                case 2:
                    return "getConfiguration";
                case 3:
                    return "setConfiguration";
                case 4:
                    return "isAvailable";
                case 5:
                    return "addListener";
                case 6:
                    return "removeListener";
                case 7:
                    return "setIncludeTestInterfaces";
                case 8:
                    return "requestTetheredInterface";
                case 9:
                    return "releaseTetheredInterface";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IEthernetManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IEthernetManager)) {
                return (IEthernetManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] availableInterfaces = getAvailableInterfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(availableInterfaces);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IpConfiguration configuration = getConfiguration(parcel.readString());
                    parcel2.writeNoException();
                    if (configuration != null) {
                        parcel2.writeInt(1);
                        configuration.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setConfiguration(parcel.readString(), parcel.readInt() != 0 ? IpConfiguration.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAvailable = isAvailable(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvailable ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    addListener(IEthernetServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeListener(IEthernetServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setIncludeTestInterfaces(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestTetheredInterface(ITetheredInterfaceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseTetheredInterface(ITetheredInterfaceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IEthernetManager {
            public static IEthernetManager sDefaultImpl;
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

            @Override // android.net.IEthernetManager
            public String[] getAvailableInterfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableInterfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public IpConfiguration getConfiguration(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfiguration(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? IpConfiguration.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public void setConfiguration(String str, IpConfiguration ipConfiguration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (ipConfiguration != null) {
                        parcelObtain.writeInt(1);
                        ipConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConfiguration(str, ipConfiguration);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public boolean isAvailable(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAvailable(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public void addListener(IEthernetServiceListener iEthernetServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iEthernetServiceListener != null ? iEthernetServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addListener(iEthernetServiceListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public void removeListener(IEthernetServiceListener iEthernetServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iEthernetServiceListener != null ? iEthernetServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeListener(iEthernetServiceListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public void setIncludeTestInterfaces(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIncludeTestInterfaces(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public void requestTetheredInterface(ITetheredInterfaceCallback iTetheredInterfaceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTetheredInterfaceCallback != null ? iTetheredInterfaceCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestTetheredInterface(iTetheredInterfaceCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.IEthernetManager
            public void releaseTetheredInterface(ITetheredInterfaceCallback iTetheredInterfaceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTetheredInterfaceCallback != null ? iTetheredInterfaceCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseTetheredInterface(iTetheredInterfaceCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEthernetManager iEthernetManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iEthernetManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iEthernetManager;
            return true;
        }

        public static IEthernetManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
