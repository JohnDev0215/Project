package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cerence.ark.assistant.framework.updater.DownloadStatus;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface RemoteUpdater extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.RemoteUpdater";

    public static class Default implements RemoteUpdater {
        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult abortUpdate(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult acceptDownload(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult acceptInstall(String str) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult checkUpdate(List<String> list, boolean z, boolean z2) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult deleteUpdate(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public String getCurrentVersion(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public List<DownloadStatus> getDownloadStatus(List<String> list) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult rejectDownload(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult rejectInstall(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult resumeUpdate(String str) throws RemoteException {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdater
        public RemoteResult stopUpdate() throws RemoteException {
            return null;
        }
    }

    RemoteResult abortUpdate(String str) throws RemoteException;

    RemoteResult acceptDownload(String str) throws RemoteException;

    RemoteResult acceptInstall(String str) throws RemoteException;

    RemoteResult checkUpdate(List<String> list, boolean z, boolean z2) throws RemoteException;

    RemoteResult deleteUpdate(String str) throws RemoteException;

    String getCurrentVersion(String str) throws RemoteException;

    List<DownloadStatus> getDownloadStatus(List<String> list) throws RemoteException;

    RemoteResult rejectDownload(String str) throws RemoteException;

    RemoteResult rejectInstall(String str) throws RemoteException;

    RemoteResult resumeUpdate(String str) throws RemoteException;

    RemoteResult stopUpdate() throws RemoteException;

    public static abstract class Stub extends Binder implements RemoteUpdater {
        static final int TRANSACTION_abortUpdate = 5;
        static final int TRANSACTION_acceptDownload = 8;
        static final int TRANSACTION_acceptInstall = 10;
        static final int TRANSACTION_checkUpdate = 4;
        static final int TRANSACTION_deleteUpdate = 3;
        static final int TRANSACTION_getCurrentVersion = 1;
        static final int TRANSACTION_getDownloadStatus = 2;
        static final int TRANSACTION_rejectDownload = 9;
        static final int TRANSACTION_rejectInstall = 11;
        static final int TRANSACTION_resumeUpdate = 7;
        static final int TRANSACTION_stopUpdate = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, RemoteUpdater.DESCRIPTOR);
        }

        public static RemoteUpdater asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(RemoteUpdater.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof RemoteUpdater)) {
                return (RemoteUpdater) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(RemoteUpdater.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(RemoteUpdater.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    String currentVersion = getCurrentVersion(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(currentVersion);
                    return true;
                case 2:
                    List<DownloadStatus> downloadStatus = getDownloadStatus(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    _Parcel.writeTypedList(parcel2, downloadStatus, 1);
                    return true;
                case 3:
                    RemoteResult remoteResultDeleteUpdate = deleteUpdate(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultDeleteUpdate, 1);
                    return true;
                case 4:
                    RemoteResult remoteResultCheckUpdate = checkUpdate(parcel.createStringArrayList(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultCheckUpdate, 1);
                    return true;
                case 5:
                    RemoteResult remoteResultAbortUpdate = abortUpdate(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultAbortUpdate, 1);
                    return true;
                case 6:
                    RemoteResult remoteResultStopUpdate = stopUpdate();
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultStopUpdate, 1);
                    return true;
                case 7:
                    RemoteResult remoteResultResumeUpdate = resumeUpdate(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultResumeUpdate, 1);
                    return true;
                case 8:
                    RemoteResult remoteResultAcceptDownload = acceptDownload(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultAcceptDownload, 1);
                    return true;
                case 9:
                    RemoteResult remoteResultRejectDownload = rejectDownload(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultRejectDownload, 1);
                    return true;
                case 10:
                    RemoteResult remoteResultAcceptInstall = acceptInstall(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultAcceptInstall, 1);
                    return true;
                case 11:
                    RemoteResult remoteResultRejectInstall = rejectInstall(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, remoteResultRejectInstall, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements RemoteUpdater {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return RemoteUpdater.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public String getCurrentVersion(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public List<DownloadStatus> getDownloadStatus(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(DownloadStatus.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult deleteUpdate(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult checkUpdate(List<String> list, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    int i = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult abortUpdate(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult stopUpdate() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult resumeUpdate(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult acceptDownload(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult rejectDownload(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult acceptInstall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdater
            public RemoteResult rejectInstall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdater.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (RemoteResult) _Parcel.readTypedObject(parcelObtain2, RemoteResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                writeTypedObject(parcel, list.get(i2), i);
            }
        }
    }
}
