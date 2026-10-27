package android.service.storage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.storage.StorageVolume;

/* JADX INFO: loaded from: classes3.dex */
public interface IExternalStorageService extends IInterface {

    public static class Default implements IExternalStorageService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.storage.IExternalStorageService
        public void endSession(String str, RemoteCallback remoteCallback) throws RemoteException {
        }

        @Override // android.service.storage.IExternalStorageService
        public void notifyVolumeStateChanged(String str, StorageVolume storageVolume, RemoteCallback remoteCallback) throws RemoteException {
        }

        @Override // android.service.storage.IExternalStorageService
        public void startSession(String str, int i, ParcelFileDescriptor parcelFileDescriptor, String str2, String str3, RemoteCallback remoteCallback) throws RemoteException {
        }
    }

    void endSession(String str, RemoteCallback remoteCallback) throws RemoteException;

    void notifyVolumeStateChanged(String str, StorageVolume storageVolume, RemoteCallback remoteCallback) throws RemoteException;

    void startSession(String str, int i, ParcelFileDescriptor parcelFileDescriptor, String str2, String str3, RemoteCallback remoteCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IExternalStorageService {
        private static final String DESCRIPTOR = "android.service.storage.IExternalStorageService";
        static final int TRANSACTION_endSession = 2;
        static final int TRANSACTION_notifyVolumeStateChanged = 3;
        static final int TRANSACTION_startSession = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "startSession";
            }
            if (i == 2) {
                return "endSession";
            }
            if (i != 3) {
                return null;
            }
            return "notifyVolumeStateChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExternalStorageService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IExternalStorageService)) {
                return (IExternalStorageService) iInterfaceQueryLocalInterface;
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
                startSession(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                endSession(parcel.readString(), parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyVolumeStateChanged(parcel.readString(), parcel.readInt() != 0 ? StorageVolume.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IExternalStorageService {
            public static IExternalStorageService sDefaultImpl;
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

            @Override // android.service.storage.IExternalStorageService
            public void startSession(String str, int i, ParcelFileDescriptor parcelFileDescriptor, String str2, String str3, RemoteCallback remoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startSession(str, i, parcelFileDescriptor, str2, str3, remoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.storage.IExternalStorageService
            public void endSession(String str, RemoteCallback remoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().endSession(str, remoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.storage.IExternalStorageService
            public void notifyVolumeStateChanged(String str, StorageVolume storageVolume, RemoteCallback remoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (storageVolume != null) {
                        parcelObtain.writeInt(1);
                        storageVolume.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyVolumeStateChanged(str, storageVolume, remoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExternalStorageService iExternalStorageService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iExternalStorageService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iExternalStorageService;
            return true;
        }

        public static IExternalStorageService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
