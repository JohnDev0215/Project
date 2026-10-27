package android.content.rollback;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IRollbackManager extends IInterface {

    public static class Default implements IRollbackManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.rollback.IRollbackManager
        public void blockRollbackManager(long j) throws RemoteException {
        }

        @Override // android.content.rollback.IRollbackManager
        public void commitRollback(int i, ParceledListSlice parceledListSlice, String str, IntentSender intentSender) throws RemoteException {
        }

        @Override // android.content.rollback.IRollbackManager
        public void expireRollbackForPackage(String str) throws RemoteException {
        }

        @Override // android.content.rollback.IRollbackManager
        public ParceledListSlice getAvailableRollbacks() throws RemoteException {
            return null;
        }

        @Override // android.content.rollback.IRollbackManager
        public ParceledListSlice getRecentlyCommittedRollbacks() throws RemoteException {
            return null;
        }

        @Override // android.content.rollback.IRollbackManager
        public void notifyStagedApkSession(int i, int i2) throws RemoteException {
        }

        @Override // android.content.rollback.IRollbackManager
        public int notifyStagedSession(int i) throws RemoteException {
            return 0;
        }

        @Override // android.content.rollback.IRollbackManager
        public void reloadPersistedData() throws RemoteException {
        }

        @Override // android.content.rollback.IRollbackManager
        public void snapshotAndRestoreUserData(String str, int[] iArr, int i, long j, String str2, int i2) throws RemoteException {
        }
    }

    void blockRollbackManager(long j) throws RemoteException;

    void commitRollback(int i, ParceledListSlice parceledListSlice, String str, IntentSender intentSender) throws RemoteException;

    void expireRollbackForPackage(String str) throws RemoteException;

    ParceledListSlice getAvailableRollbacks() throws RemoteException;

    ParceledListSlice getRecentlyCommittedRollbacks() throws RemoteException;

    void notifyStagedApkSession(int i, int i2) throws RemoteException;

    int notifyStagedSession(int i) throws RemoteException;

    void reloadPersistedData() throws RemoteException;

    void snapshotAndRestoreUserData(String str, int[] iArr, int i, long j, String str2, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRollbackManager {
        private static final String DESCRIPTOR = "android.content.rollback.IRollbackManager";
        static final int TRANSACTION_blockRollbackManager = 9;
        static final int TRANSACTION_commitRollback = 3;
        static final int TRANSACTION_expireRollbackForPackage = 6;
        static final int TRANSACTION_getAvailableRollbacks = 1;
        static final int TRANSACTION_getRecentlyCommittedRollbacks = 2;
        static final int TRANSACTION_notifyStagedApkSession = 8;
        static final int TRANSACTION_notifyStagedSession = 7;
        static final int TRANSACTION_reloadPersistedData = 5;
        static final int TRANSACTION_snapshotAndRestoreUserData = 4;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getAvailableRollbacks";
                case 2:
                    return "getRecentlyCommittedRollbacks";
                case 3:
                    return "commitRollback";
                case 4:
                    return "snapshotAndRestoreUserData";
                case 5:
                    return "reloadPersistedData";
                case 6:
                    return "expireRollbackForPackage";
                case 7:
                    return "notifyStagedSession";
                case 8:
                    return "notifyStagedApkSession";
                case 9:
                    return "blockRollbackManager";
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

        public static IRollbackManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRollbackManager)) {
                return (IRollbackManager) iInterfaceQueryLocalInterface;
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
                    ParceledListSlice availableRollbacks = getAvailableRollbacks();
                    parcel2.writeNoException();
                    if (availableRollbacks != null) {
                        parcel2.writeInt(1);
                        availableRollbacks.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice recentlyCommittedRollbacks = getRecentlyCommittedRollbacks();
                    parcel2.writeNoException();
                    if (recentlyCommittedRollbacks != null) {
                        parcel2.writeInt(1);
                        recentlyCommittedRollbacks.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    commitRollback(parcel.readInt(), parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    snapshotAndRestoreUserData(parcel.readString(), parcel.createIntArray(), parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    reloadPersistedData();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    expireRollbackForPackage(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNotifyStagedSession = notifyStagedSession(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iNotifyStagedSession);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyStagedApkSession(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    blockRollbackManager(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRollbackManager {
            public static IRollbackManager sDefaultImpl;
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

            @Override // android.content.rollback.IRollbackManager
            public ParceledListSlice getAvailableRollbacks() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableRollbacks();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public ParceledListSlice getRecentlyCommittedRollbacks() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecentlyCommittedRollbacks();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public void commitRollback(int i, ParceledListSlice parceledListSlice, String str, IntentSender intentSender) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commitRollback(i, parceledListSlice, str, intentSender);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public void snapshotAndRestoreUserData(String str, int[] iArr, int i, long j, String str2, int i2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().snapshotAndRestoreUserData(str, iArr, i, j, str2, i2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public void reloadPersistedData() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reloadPersistedData();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public void expireRollbackForPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().expireRollbackForPackage(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public int notifyStagedSession(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyStagedSession(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public void notifyStagedApkSession(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyStagedApkSession(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.rollback.IRollbackManager
            public void blockRollbackManager(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().blockRollbackManager(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRollbackManager iRollbackManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRollbackManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRollbackManager;
            return true;
        }

        public static IRollbackManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
