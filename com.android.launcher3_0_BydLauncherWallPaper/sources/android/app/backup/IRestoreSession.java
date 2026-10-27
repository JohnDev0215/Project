package android.app.backup;

import android.app.backup.IBackupManagerMonitor;
import android.app.backup.IRestoreObserver;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IRestoreSession extends IInterface {

    public static class Default implements IRestoreSession {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.backup.IRestoreSession
        public void endRestoreSession() throws RemoteException {
        }

        @Override // android.app.backup.IRestoreSession
        public int getAvailableRestoreSets(IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
            return 0;
        }

        @Override // android.app.backup.IRestoreSession
        public int restoreAll(long j, IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
            return 0;
        }

        @Override // android.app.backup.IRestoreSession
        public int restorePackage(String str, IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
            return 0;
        }

        @Override // android.app.backup.IRestoreSession
        public int restorePackages(long j, IRestoreObserver iRestoreObserver, String[] strArr, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
            return 0;
        }
    }

    void endRestoreSession() throws RemoteException;

    int getAvailableRestoreSets(IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException;

    int restoreAll(long j, IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException;

    int restorePackage(String str, IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException;

    int restorePackages(long j, IRestoreObserver iRestoreObserver, String[] strArr, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException;

    public static abstract class Stub extends Binder implements IRestoreSession {
        private static final String DESCRIPTOR = "android.app.backup.IRestoreSession";
        static final int TRANSACTION_endRestoreSession = 5;
        static final int TRANSACTION_getAvailableRestoreSets = 1;
        static final int TRANSACTION_restoreAll = 2;
        static final int TRANSACTION_restorePackage = 4;
        static final int TRANSACTION_restorePackages = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getAvailableRestoreSets";
            }
            if (i == 2) {
                return "restoreAll";
            }
            if (i == 3) {
                return "restorePackages";
            }
            if (i == 4) {
                return "restorePackage";
            }
            if (i != 5) {
                return null;
            }
            return "endRestoreSession";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRestoreSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRestoreSession)) {
                return (IRestoreSession) iInterfaceQueryLocalInterface;
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
                int availableRestoreSets = getAvailableRestoreSets(IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(availableRestoreSets);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int iRestoreAll = restoreAll(parcel.readLong(), IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(iRestoreAll);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                int iRestorePackages = restorePackages(parcel.readLong(), IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray(), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(iRestorePackages);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                int iRestorePackage = restorePackage(parcel.readString(), IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(iRestorePackage);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            endRestoreSession();
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IRestoreSession {
            public static IRestoreSession sDefaultImpl;
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

            @Override // android.app.backup.IRestoreSession
            public int getAvailableRestoreSets(IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRestoreObserver != null ? iRestoreObserver.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBackupManagerMonitor != null ? iBackupManagerMonitor.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableRestoreSets(iRestoreObserver, iBackupManagerMonitor);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IRestoreSession
            public int restoreAll(long j, IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeStrongBinder(iRestoreObserver != null ? iRestoreObserver.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBackupManagerMonitor != null ? iBackupManagerMonitor.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().restoreAll(j, iRestoreObserver, iBackupManagerMonitor);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IRestoreSession
            public int restorePackages(long j, IRestoreObserver iRestoreObserver, String[] strArr, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeStrongBinder(iRestoreObserver != null ? iRestoreObserver.asBinder() : null);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeStrongBinder(iBackupManagerMonitor != null ? iBackupManagerMonitor.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().restorePackages(j, iRestoreObserver, strArr, iBackupManagerMonitor);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IRestoreSession
            public int restorePackage(String str, IRestoreObserver iRestoreObserver, IBackupManagerMonitor iBackupManagerMonitor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iRestoreObserver != null ? iRestoreObserver.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBackupManagerMonitor != null ? iBackupManagerMonitor.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().restorePackage(str, iRestoreObserver, iBackupManagerMonitor);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IRestoreSession
            public void endRestoreSession() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().endRestoreSession();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRestoreSession iRestoreSession) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRestoreSession == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRestoreSession;
            return true;
        }

        public static IRestoreSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
