package android.content.pm;

import android.content.pm.IDataLoaderStatusListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Telephony;

/* JADX INFO: loaded from: classes.dex */
public interface IDataLoader extends IInterface {

    public static class Default implements IDataLoader {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.IDataLoader
        public void create(int i, DataLoaderParamsParcel dataLoaderParamsParcel, FileSystemControlParcel fileSystemControlParcel, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void destroy(int i) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void prepareImage(int i, InstallationFileParcel[] installationFileParcelArr, String[] strArr) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void start(int i) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void stop(int i) throws RemoteException {
        }
    }

    void create(int i, DataLoaderParamsParcel dataLoaderParamsParcel, FileSystemControlParcel fileSystemControlParcel, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException;

    void destroy(int i) throws RemoteException;

    void prepareImage(int i, InstallationFileParcel[] installationFileParcelArr, String[] strArr) throws RemoteException;

    void start(int i) throws RemoteException;

    void stop(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataLoader {
        private static final String DESCRIPTOR = "android.content.pm.IDataLoader";
        static final int TRANSACTION_create = 1;
        static final int TRANSACTION_destroy = 4;
        static final int TRANSACTION_prepareImage = 5;
        static final int TRANSACTION_start = 2;
        static final int TRANSACTION_stop = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "create";
            }
            if (i == 2) {
                return Telephony.BaseMmsColumns.START;
            }
            if (i == 3) {
                return "stop";
            }
            if (i == 4) {
                return "destroy";
            }
            if (i != 5) {
                return null;
            }
            return "prepareImage";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDataLoader asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDataLoader)) {
                return (IDataLoader) iInterfaceQueryLocalInterface;
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
                create(parcel.readInt(), parcel.readInt() != 0 ? DataLoaderParamsParcel.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? FileSystemControlParcel.CREATOR.createFromParcel(parcel) : null, IDataLoaderStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                start(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                stop(parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                destroy(parcel.readInt());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                prepareImage(parcel.readInt(), (InstallationFileParcel[]) parcel.createTypedArray(InstallationFileParcel.CREATOR), parcel.createStringArray());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDataLoader {
            public static IDataLoader sDefaultImpl;
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

            @Override // android.content.pm.IDataLoader
            public void create(int i, DataLoaderParamsParcel dataLoaderParamsParcel, FileSystemControlParcel fileSystemControlParcel, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (dataLoaderParamsParcel != null) {
                        parcelObtain.writeInt(1);
                        dataLoaderParamsParcel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (fileSystemControlParcel != null) {
                        parcelObtain.writeInt(1);
                        fileSystemControlParcel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iDataLoaderStatusListener != null ? iDataLoaderStatusListener.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().create(i, dataLoaderParamsParcel, fileSystemControlParcel, iDataLoaderStatusListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void start(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().start(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void stop(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stop(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void destroy(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().destroy(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void prepareImage(int i, InstallationFileParcel[] installationFileParcelArr, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(installationFileParcelArr, 0);
                    parcelObtain.writeStringArray(strArr);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().prepareImage(i, installationFileParcelArr, strArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataLoader iDataLoader) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDataLoader == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDataLoader;
            return true;
        }

        public static IDataLoader getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
