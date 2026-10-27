package com.byd.vrdownload.i;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.LanguageOTAResult;

/* JADX INFO: loaded from: classes2.dex */
public interface IDownloadCallback extends IInterface {

    public static class Default implements IDownloadCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public AidlResult checkSpaceQuota(long j) throws RemoteException {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void deleteLanguage(String str) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void getLanguageOTA(LanguageOTAResult languageOTAResult) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void installLanguage(String str) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onDownload(DownloadTask downloadTask) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onDownloadProgress(DownloadTask downloadTask, long j, long j2) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onStartDownloadResult(AidlResult aidlResult) throws RemoteException {
        }
    }

    AidlResult checkSpaceQuota(long j) throws RemoteException;

    void deleteLanguage(String str) throws RemoteException;

    void getLanguageOTA(LanguageOTAResult languageOTAResult) throws RemoteException;

    void installLanguage(String str) throws RemoteException;

    void onDownload(DownloadTask downloadTask) throws RemoteException;

    void onDownloadProgress(DownloadTask downloadTask, long j, long j2) throws RemoteException;

    void onStartDownloadResult(AidlResult aidlResult) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadCallback {
        private static final String DESCRIPTOR = "com.byd.vrdownload.i.IDownloadCallback";
        static final int TRANSACTION_checkSpaceQuota = 1;
        static final int TRANSACTION_deleteLanguage = 6;
        static final int TRANSACTION_getLanguageOTA = 2;
        static final int TRANSACTION_installLanguage = 7;
        static final int TRANSACTION_onDownload = 3;
        static final int TRANSACTION_onDownloadProgress = 5;
        static final int TRANSACTION_onStartDownloadResult = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDownloadCallback)) {
                return (IDownloadCallback) iInterfaceQueryLocalInterface;
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
                    AidlResult aidlResultCheckSpaceQuota = checkSpaceQuota(parcel.readLong());
                    parcel2.writeNoException();
                    if (aidlResultCheckSpaceQuota != null) {
                        parcel2.writeInt(1);
                        aidlResultCheckSpaceQuota.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getLanguageOTA(parcel.readInt() != 0 ? LanguageOTAResult.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownload(parcel.readInt() != 0 ? DownloadTask.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStartDownloadResult(parcel.readInt() != 0 ? AidlResult.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadProgress(parcel.readInt() != 0 ? DownloadTask.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readLong());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteLanguage(parcel.readString());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    installLanguage(parcel.readString());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDownloadCallback {
            public static IDownloadCallback sDefaultImpl;
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

            @Override // com.byd.vrdownload.i.IDownloadCallback
            public AidlResult checkSpaceQuota(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkSpaceQuota(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AidlResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadCallback
            public void getLanguageOTA(LanguageOTAResult languageOTAResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (languageOTAResult != null) {
                        parcelObtain.writeInt(1);
                        languageOTAResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getLanguageOTA(languageOTAResult);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadCallback
            public void onDownload(DownloadTask downloadTask) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadTask != null) {
                        parcelObtain.writeInt(1);
                        downloadTask.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDownload(downloadTask);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadCallback
            public void onStartDownloadResult(AidlResult aidlResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (aidlResult != null) {
                        parcelObtain.writeInt(1);
                        aidlResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartDownloadResult(aidlResult);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadCallback
            public void onDownloadProgress(DownloadTask downloadTask, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadTask != null) {
                        parcelObtain.writeInt(1);
                        downloadTask.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDownloadProgress(downloadTask, j, j2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadCallback
            public void deleteLanguage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().deleteLanguage(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.vrdownload.i.IDownloadCallback
            public void installLanguage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().installLanguage(str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadCallback iDownloadCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDownloadCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadCallback;
            return true;
        }

        public static IDownloadCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
