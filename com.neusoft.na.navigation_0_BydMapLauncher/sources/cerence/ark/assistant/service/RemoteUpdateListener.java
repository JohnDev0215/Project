package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cerence.ark.assistant.framework.updater.UpdateInfo;
import cerence.ark.assistant.framework.updater.UpdateResultCode;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface RemoteUpdateListener extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.RemoteUpdateListener";

    public static class Default implements RemoteUpdateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onAborted(UpdateResultCode updateResultCode, String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onCheckUpdateFinished(UpdateResultCode updateResultCode, boolean z, List<UpdateInfo> list) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onCheckUpdateStarted() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onConfirmed(UpdateResultCode updateResultCode, String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onDataDeleted(UpdateResultCode updateResultCode, String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onDownloadFinished(UpdateResultCode updateResultCode, String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onDownloadProgress(String str, long j, long j2, boolean z) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onDownloadStarted(String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onRecoveryFinished(UpdateResultCode updateResultCode, String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onRecoveryStarted() throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onUpdateFinished(UpdateResultCode updateResultCode, String str) throws RemoteException {
        }

        @Override // cerence.ark.assistant.service.RemoteUpdateListener
        public void onUpdateInstallable(UpdateInfo updateInfo, boolean z) throws RemoteException {
        }
    }

    void onAborted(UpdateResultCode updateResultCode, String str) throws RemoteException;

    void onCheckUpdateFinished(UpdateResultCode updateResultCode, boolean z, List<UpdateInfo> list) throws RemoteException;

    void onCheckUpdateStarted() throws RemoteException;

    void onConfirmed(UpdateResultCode updateResultCode, String str) throws RemoteException;

    void onDataDeleted(UpdateResultCode updateResultCode, String str) throws RemoteException;

    void onDownloadFinished(UpdateResultCode updateResultCode, String str) throws RemoteException;

    void onDownloadProgress(String str, long j, long j2, boolean z) throws RemoteException;

    void onDownloadStarted(String str) throws RemoteException;

    void onRecoveryFinished(UpdateResultCode updateResultCode, String str) throws RemoteException;

    void onRecoveryStarted() throws RemoteException;

    void onUpdateFinished(UpdateResultCode updateResultCode, String str) throws RemoteException;

    void onUpdateInstallable(UpdateInfo updateInfo, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements RemoteUpdateListener {
        static final int TRANSACTION_onAborted = 8;
        static final int TRANSACTION_onCheckUpdateFinished = 2;
        static final int TRANSACTION_onCheckUpdateStarted = 1;
        static final int TRANSACTION_onConfirmed = 10;
        static final int TRANSACTION_onDataDeleted = 9;
        static final int TRANSACTION_onDownloadFinished = 5;
        static final int TRANSACTION_onDownloadProgress = 4;
        static final int TRANSACTION_onDownloadStarted = 3;
        static final int TRANSACTION_onRecoveryFinished = 12;
        static final int TRANSACTION_onRecoveryStarted = 11;
        static final int TRANSACTION_onUpdateFinished = 7;
        static final int TRANSACTION_onUpdateInstallable = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, RemoteUpdateListener.DESCRIPTOR);
        }

        public static RemoteUpdateListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(RemoteUpdateListener.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof RemoteUpdateListener)) {
                return (RemoteUpdateListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(RemoteUpdateListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(RemoteUpdateListener.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    onCheckUpdateStarted();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    onCheckUpdateFinished((UpdateResultCode) _Parcel.readTypedObject(parcel, UpdateResultCode.CREATOR), parcel.readInt() != 0, parcel.createTypedArrayList(UpdateInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    onDownloadStarted(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    onDownloadProgress(parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    onDownloadFinished((UpdateResultCode) _Parcel.readTypedObject(parcel, UpdateResultCode.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    onUpdateInstallable((UpdateInfo) _Parcel.readTypedObject(parcel, UpdateInfo.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    onUpdateFinished((UpdateResultCode) _Parcel.readTypedObject(parcel, UpdateResultCode.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    onAborted((UpdateResultCode) _Parcel.readTypedObject(parcel, UpdateResultCode.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    onDataDeleted((UpdateResultCode) _Parcel.readTypedObject(parcel, UpdateResultCode.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    onConfirmed((UpdateResultCode) _Parcel.readTypedObject(parcel, UpdateResultCode.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    onRecoveryStarted();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    onRecoveryFinished((UpdateResultCode) _Parcel.readTypedObject(parcel, UpdateResultCode.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements RemoteUpdateListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return RemoteUpdateListener.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onCheckUpdateStarted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onCheckUpdateFinished(UpdateResultCode updateResultCode, boolean z, List<UpdateInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateResultCode, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    _Parcel.writeTypedList(parcelObtain, list, 0);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onDownloadStarted(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onDownloadProgress(String str, long j, long j2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onDownloadFinished(UpdateResultCode updateResultCode, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateResultCode, 0);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onUpdateInstallable(UpdateInfo updateInfo, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateInfo, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onUpdateFinished(UpdateResultCode updateResultCode, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateResultCode, 0);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onAborted(UpdateResultCode updateResultCode, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateResultCode, 0);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onDataDeleted(UpdateResultCode updateResultCode, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateResultCode, 0);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onConfirmed(UpdateResultCode updateResultCode, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateResultCode, 0);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onRecoveryStarted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.RemoteUpdateListener
            public void onRecoveryFinished(UpdateResultCode updateResultCode, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(RemoteUpdateListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, updateResultCode, 0);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
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
