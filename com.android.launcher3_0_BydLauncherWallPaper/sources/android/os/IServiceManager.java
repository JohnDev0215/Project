package android.os;

import android.os.IClientCallback;
import android.os.IServiceCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IServiceManager extends IInterface {
    public static final int DUMP_FLAG_PRIORITY_ALL = 15;
    public static final int DUMP_FLAG_PRIORITY_CRITICAL = 1;
    public static final int DUMP_FLAG_PRIORITY_DEFAULT = 8;
    public static final int DUMP_FLAG_PRIORITY_HIGH = 2;
    public static final int DUMP_FLAG_PRIORITY_NORMAL = 4;
    public static final int DUMP_FLAG_PROTO = 16;

    public static class Default implements IServiceManager {
        @Override // android.os.IServiceManager
        public void addService(String str, IBinder iBinder, boolean z, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IServiceManager
        public IBinder checkService(String str) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public IBinder getService(String str) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public boolean isDeclared(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IServiceManager
        public String[] listServices(int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public void registerClientCallback(String str, IBinder iBinder, IClientCallback iClientCallback) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public void registerForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public void tryUnregisterService(String str, IBinder iBinder) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public void unregisterForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException {
        }
    }

    void addService(String str, IBinder iBinder, boolean z, int i) throws RemoteException;

    IBinder checkService(String str) throws RemoteException;

    IBinder getService(String str) throws RemoteException;

    boolean isDeclared(String str) throws RemoteException;

    String[] listServices(int i) throws RemoteException;

    void registerClientCallback(String str, IBinder iBinder, IClientCallback iClientCallback) throws RemoteException;

    void registerForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException;

    void tryUnregisterService(String str, IBinder iBinder) throws RemoteException;

    void unregisterForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IServiceManager {
        private static final String DESCRIPTOR = "android.os.IServiceManager";
        static final int TRANSACTION_addService = 3;
        static final int TRANSACTION_checkService = 2;
        static final int TRANSACTION_getService = 1;
        static final int TRANSACTION_isDeclared = 7;
        static final int TRANSACTION_listServices = 4;
        static final int TRANSACTION_registerClientCallback = 8;
        static final int TRANSACTION_registerForNotifications = 5;
        static final int TRANSACTION_tryUnregisterService = 9;
        static final int TRANSACTION_unregisterForNotifications = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getService";
                case 2:
                    return "checkService";
                case 3:
                    return "addService";
                case 4:
                    return "listServices";
                case 5:
                    return "registerForNotifications";
                case 6:
                    return "unregisterForNotifications";
                case 7:
                    return "isDeclared";
                case 8:
                    return "registerClientCallback";
                case 9:
                    return "tryUnregisterService";
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

        public static IServiceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IServiceManager)) {
                return (IServiceManager) iInterfaceQueryLocalInterface;
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
                    IBinder service = getService(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(service);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder iBinderCheckService = checkService(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iBinderCheckService);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    addService(parcel.readString(), parcel.readStrongBinder(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrListServices = listServices(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrListServices);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerForNotifications(parcel.readString(), IServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterForNotifications(parcel.readString(), IServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDeclared = isDeclared(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDeclared ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerClientCallback(parcel.readString(), parcel.readStrongBinder(), IClientCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    tryUnregisterService(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IServiceManager {
            public static IServiceManager sDefaultImpl;
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

            @Override // android.os.IServiceManager
            public IBinder getService(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getService(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public IBinder checkService(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkService(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void addService(String str, IBinder iBinder, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addService(str, iBinder, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public String[] listServices(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listServices(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void registerForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iServiceCallback != null ? iServiceCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerForNotifications(str, iServiceCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void unregisterForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iServiceCallback != null ? iServiceCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterForNotifications(str, iServiceCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public boolean isDeclared(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeclared(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void registerClientCallback(String str, IBinder iBinder, IClientCallback iClientCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerClientCallback(str, iBinder, iClientCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void tryUnregisterService(String str, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tryUnregisterService(str, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IServiceManager iServiceManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iServiceManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iServiceManager;
            return true;
        }

        public static IServiceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
