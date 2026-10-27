package android.app;

import android.app.backup.IBackupCallback;
import android.app.backup.IBackupManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBackupAgent extends IInterface {

    public static class Default implements IBackupAgent {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IBackupAgent
        public void doBackup(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, ParcelFileDescriptor parcelFileDescriptor3, long j, IBackupCallback iBackupCallback, int i) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void doFullBackup(ParcelFileDescriptor parcelFileDescriptor, long j, int i, IBackupManager iBackupManager, int i2) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void doMeasureFullBackup(long j, int i, IBackupManager iBackupManager, int i2) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void doQuotaExceeded(long j, long j2, IBackupCallback iBackupCallback) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void doRestore(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void doRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3, int i2, IBackupManager iBackupManager) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void doRestoreFinished(int i, IBackupManager iBackupManager) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void doRestoreWithExcludedKeys(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager, List<String> list) throws RemoteException {
        }

        @Override // android.app.IBackupAgent
        public void fail(String str) throws RemoteException {
        }
    }

    void doBackup(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, ParcelFileDescriptor parcelFileDescriptor3, long j, IBackupCallback iBackupCallback, int i) throws RemoteException;

    void doFullBackup(ParcelFileDescriptor parcelFileDescriptor, long j, int i, IBackupManager iBackupManager, int i2) throws RemoteException;

    void doMeasureFullBackup(long j, int i, IBackupManager iBackupManager, int i2) throws RemoteException;

    void doQuotaExceeded(long j, long j2, IBackupCallback iBackupCallback) throws RemoteException;

    void doRestore(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager) throws RemoteException;

    void doRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3, int i2, IBackupManager iBackupManager) throws RemoteException;

    void doRestoreFinished(int i, IBackupManager iBackupManager) throws RemoteException;

    void doRestoreWithExcludedKeys(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager, List<String> list) throws RemoteException;

    void fail(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IBackupAgent {
        private static final String DESCRIPTOR = "android.app.IBackupAgent";
        static final int TRANSACTION_doBackup = 1;
        static final int TRANSACTION_doFullBackup = 4;
        static final int TRANSACTION_doMeasureFullBackup = 5;
        static final int TRANSACTION_doQuotaExceeded = 6;
        static final int TRANSACTION_doRestore = 2;
        static final int TRANSACTION_doRestoreFile = 7;
        static final int TRANSACTION_doRestoreFinished = 8;
        static final int TRANSACTION_doRestoreWithExcludedKeys = 3;
        static final int TRANSACTION_fail = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "doBackup";
                case 2:
                    return "doRestore";
                case 3:
                    return "doRestoreWithExcludedKeys";
                case 4:
                    return "doFullBackup";
                case 5:
                    return "doMeasureFullBackup";
                case 6:
                    return "doQuotaExceeded";
                case 7:
                    return "doRestoreFile";
                case 8:
                    return "doRestoreFinished";
                case 9:
                    return "fail";
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

        public static IBackupAgent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBackupAgent)) {
                return (IBackupAgent) iInterfaceQueryLocalInterface;
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
                    doBackup(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), IBackupCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    doRestore(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    doRestoreWithExcludedKeys(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IBackupManager.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArrayList());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    doFullBackup(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt(), IBackupManager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    doMeasureFullBackup(parcel.readLong(), parcel.readInt(), IBackupManager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    doQuotaExceeded(parcel.readLong(), parcel.readLong(), IBackupCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    doRestoreFile(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt(), IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    doRestoreFinished(parcel.readInt(), IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    fail(parcel.readString());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBackupAgent {
            public static IBackupAgent sDefaultImpl;
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

            @Override // android.app.IBackupAgent
            public void doBackup(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, ParcelFileDescriptor parcelFileDescriptor3, long j, IBackupCallback iBackupCallback, int i) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelFileDescriptor2 != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelFileDescriptor3 != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor3.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeStrongBinder(iBackupCallback != null ? iBackupCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().doBackup(parcelFileDescriptor, parcelFileDescriptor2, parcelFileDescriptor3, j, iBackupCallback, i);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.app.IBackupAgent
            public void doRestore(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    if (parcelFileDescriptor2 != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBackupManager != null ? iBackupManager.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doRestore(parcelFileDescriptor, j, parcelFileDescriptor2, i, iBackupManager);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IBackupAgent
            public void doRestoreWithExcludedKeys(ParcelFileDescriptor parcelFileDescriptor, long j, ParcelFileDescriptor parcelFileDescriptor2, int i, IBackupManager iBackupManager, List<String> list) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    if (parcelFileDescriptor2 != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBackupManager != null ? iBackupManager.asBinder() : null);
                    parcelObtain.writeStringList(list);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().doRestoreWithExcludedKeys(parcelFileDescriptor, j, parcelFileDescriptor2, i, iBackupManager, list);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.app.IBackupAgent
            public void doFullBackup(ParcelFileDescriptor parcelFileDescriptor, long j, int i, IBackupManager iBackupManager, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBackupManager != null ? iBackupManager.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doFullBackup(parcelFileDescriptor, j, i, iBackupManager, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IBackupAgent
            public void doMeasureFullBackup(long j, int i, IBackupManager iBackupManager, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBackupManager != null ? iBackupManager.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doMeasureFullBackup(j, i, iBackupManager, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IBackupAgent
            public void doQuotaExceeded(long j, long j2, IBackupCallback iBackupCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeStrongBinder(iBackupCallback != null ? iBackupCallback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doQuotaExceeded(j, j2, iBackupCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IBackupAgent
            public void doRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, int i, String str, String str2, long j2, long j3, int i2, IBackupManager iBackupManager) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeLong(j3);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iBackupManager != null ? iBackupManager.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().doRestoreFile(parcelFileDescriptor, j, i, str, str2, j2, j3, i2, iBackupManager);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.app.IBackupAgent
            public void doRestoreFinished(int i, IBackupManager iBackupManager) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBackupManager != null ? iBackupManager.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doRestoreFinished(i, iBackupManager);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IBackupAgent
            public void fail(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().fail(str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBackupAgent iBackupAgent) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBackupAgent == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBackupAgent;
            return true;
        }

        public static IBackupAgent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
