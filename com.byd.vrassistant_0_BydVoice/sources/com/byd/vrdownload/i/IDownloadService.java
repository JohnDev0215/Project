package com.byd.vrdownload.i;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.vrdownload.i.IDownloadCallback;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.GetLanguagesUpdate;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDownloadService extends IInterface {

    public static class Default implements IDownloadService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public AidlResult cancel(int i) throws RemoteException {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public void downloadLanguagePkg(String str) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public List<DownloadTask> getAllTasks() throws RemoteException {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public String getDownloadVersion() throws RemoteException {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public String getDownloadVersionType() throws RemoteException {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public void getLanguageOTA(GetLanguagesUpdate getLanguagesUpdate) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public long getSofar(int i) throws RemoteException {
            return 0L;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public byte getStatus(int i) throws RemoteException {
            return (byte) 0;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public long getTotal(int i) throws RemoteException {
            return 0L;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public boolean isIdle() throws RemoteException {
            return false;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public AidlResult pause(int i) throws RemoteException {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public void pauseAllTasks() throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public void registerCallback(IDownloadCallback iDownloadCallback, String str) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public void removeFile(String str) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public void resume(int i) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadService
        public void unregisterCallback(IDownloadCallback iDownloadCallback) throws RemoteException {
        }
    }

    AidlResult cancel(int i) throws RemoteException;

    void downloadLanguagePkg(String str) throws RemoteException;

    List<DownloadTask> getAllTasks() throws RemoteException;

    String getDownloadVersion() throws RemoteException;

    String getDownloadVersionType() throws RemoteException;

    void getLanguageOTA(GetLanguagesUpdate getLanguagesUpdate) throws RemoteException;

    long getSofar(int i) throws RemoteException;

    byte getStatus(int i) throws RemoteException;

    long getTotal(int i) throws RemoteException;

    boolean isIdle() throws RemoteException;

    AidlResult pause(int i) throws RemoteException;

    void pauseAllTasks() throws RemoteException;

    void registerCallback(IDownloadCallback iDownloadCallback, String str) throws RemoteException;

    void removeFile(String str) throws RemoteException;

    void resume(int i) throws RemoteException;

    void unregisterCallback(IDownloadCallback iDownloadCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadService {
        private static final String DESCRIPTOR = "com.byd.vrdownload.i.IDownloadService";
        static final int TRANSACTION_cancel = 8;
        static final int TRANSACTION_downloadLanguagePkg = 5;
        static final int TRANSACTION_getAllTasks = 13;
        static final int TRANSACTION_getDownloadVersion = 15;
        static final int TRANSACTION_getDownloadVersionType = 16;
        static final int TRANSACTION_getLanguageOTA = 3;
        static final int TRANSACTION_getSofar = 10;
        static final int TRANSACTION_getStatus = 12;
        static final int TRANSACTION_getTotal = 11;
        static final int TRANSACTION_isIdle = 14;
        static final int TRANSACTION_pause = 7;
        static final int TRANSACTION_pauseAllTasks = 9;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_removeFile = 4;
        static final int TRANSACTION_resume = 6;
        static final int TRANSACTION_unregisterCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDownloadService)) {
                return (IDownloadService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
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
                    registerCallback(IDownloadCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(IDownloadCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    getLanguageOTA(parcel.readInt() != 0 ? GetLanguagesUpdate.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeFile(parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    downloadLanguagePkg(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    resume(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    AidlResult aidlResultPause = pause(parcel.readInt());
                    parcel2.writeNoException();
                    if (aidlResultPause != null) {
                        parcel2.writeInt(1);
                        aidlResultPause.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    AidlResult aidlResultCancel = cancel(parcel.readInt());
                    parcel2.writeNoException();
                    if (aidlResultCancel != null) {
                        parcel2.writeInt(1);
                        aidlResultCancel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseAllTasks();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    long sofar = getSofar(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(sofar);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    long total = getTotal(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(total);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte status = getStatus(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByte(status);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DownloadTask> allTasks = getAllTasks();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(allTasks);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsIdle = isIdle();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsIdle ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String downloadVersion = getDownloadVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(downloadVersion);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    String downloadVersionType = getDownloadVersionType();
                    parcel2.writeNoException();
                    parcel2.writeString(downloadVersionType);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDownloadService {
            public static IDownloadService sDefaultImpl;
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

            @Override // com.byd.vrdownload.i.IDownloadService
            public void registerCallback(IDownloadCallback iDownloadCallback, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDownloadCallback != null ? iDownloadCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback(iDownloadCallback, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public void unregisterCallback(IDownloadCallback iDownloadCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDownloadCallback != null ? iDownloadCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(iDownloadCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public void getLanguageOTA(GetLanguagesUpdate getLanguagesUpdate) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (getLanguagesUpdate != null) {
                        parcelObtain.writeInt(1);
                        getLanguagesUpdate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getLanguageOTA(getLanguagesUpdate);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public void removeFile(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeFile(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public void downloadLanguagePkg(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().downloadLanguagePkg(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public void resume(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resume(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public AidlResult pause(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pause(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AidlResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public AidlResult cancel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancel(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AidlResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public void pauseAllTasks() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().pauseAllTasks();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public long getSofar(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSofar(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public long getTotal(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotal(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public byte getStatus(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatus(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readByte();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public List<DownloadTask> getAllTasks() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllTasks();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(DownloadTask.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public boolean isIdle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isIdle();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public String getDownloadVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadService
            public String getDownloadVersionType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadVersionType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadService iDownloadService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDownloadService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadService;
            return true;
        }

        public static IDownloadService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
