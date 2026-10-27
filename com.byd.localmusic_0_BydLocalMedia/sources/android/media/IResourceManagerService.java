package android.media;

import android.media.IResourceManagerClient;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IResourceManagerService extends IInterface {
    public static final String kPolicySupportsMultipleSecureCodecs = "supports-multiple-secure-codecs";
    public static final String kPolicySupportsSecureWithNonSecureCodec = "supports-secure-with-non-secure-codec";

    public static class Default implements IResourceManagerService {
        @Override // android.media.IResourceManagerService
        public void addResource(int i, int i2, long j, IResourceManagerClient iResourceManagerClient, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IResourceManagerService
        public void config(MediaResourcePolicyParcel[] mediaResourcePolicyParcelArr) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void markClientForPendingRemoval(int i, long j) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void overridePid(int i, int i2) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public boolean reclaimResource(int i, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException {
            return false;
        }

        @Override // android.media.IResourceManagerService
        public void reclaimResourcesFromClientsPendingRemoval(int i) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void removeClient(int i, long j) throws RemoteException {
        }

        @Override // android.media.IResourceManagerService
        public void removeResource(int i, long j, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException {
        }
    }

    void addResource(int i, int i2, long j, IResourceManagerClient iResourceManagerClient, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException;

    void config(MediaResourcePolicyParcel[] mediaResourcePolicyParcelArr) throws RemoteException;

    void markClientForPendingRemoval(int i, long j) throws RemoteException;

    void overridePid(int i, int i2) throws RemoteException;

    boolean reclaimResource(int i, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException;

    void reclaimResourcesFromClientsPendingRemoval(int i) throws RemoteException;

    void removeClient(int i, long j) throws RemoteException;

    void removeResource(int i, long j, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IResourceManagerService {
        private static final String DESCRIPTOR = "android.media.IResourceManagerService";
        static final int TRANSACTION_addResource = 2;
        static final int TRANSACTION_config = 1;
        static final int TRANSACTION_markClientForPendingRemoval = 7;
        static final int TRANSACTION_overridePid = 6;
        static final int TRANSACTION_reclaimResource = 5;
        static final int TRANSACTION_reclaimResourcesFromClientsPendingRemoval = 8;
        static final int TRANSACTION_removeClient = 4;
        static final int TRANSACTION_removeResource = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "config";
                case 2:
                    return "addResource";
                case 3:
                    return "removeResource";
                case 4:
                    return "removeClient";
                case 5:
                    return "reclaimResource";
                case 6:
                    return "overridePid";
                case 7:
                    return "markClientForPendingRemoval";
                case 8:
                    return "reclaimResourcesFromClientsPendingRemoval";
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

        public static IResourceManagerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IResourceManagerService)) {
                return (IResourceManagerService) iInterfaceQueryLocalInterface;
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
                    config((MediaResourcePolicyParcel[]) parcel.createTypedArray(MediaResourcePolicyParcel.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    addResource(parcel.readInt(), parcel.readInt(), parcel.readLong(), IResourceManagerClient.Stub.asInterface(parcel.readStrongBinder()), (MediaResourceParcel[]) parcel.createTypedArray(MediaResourceParcel.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeResource(parcel.readInt(), parcel.readLong(), (MediaResourceParcel[]) parcel.createTypedArray(MediaResourceParcel.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeClient(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReclaimResource = reclaimResource(parcel.readInt(), (MediaResourceParcel[]) parcel.createTypedArray(MediaResourceParcel.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(zReclaimResource ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    overridePid(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    markClientForPendingRemoval(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    reclaimResourcesFromClientsPendingRemoval(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IResourceManagerService {
            public static IResourceManagerService sDefaultImpl;
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

            @Override // android.media.IResourceManagerService
            public void config(MediaResourcePolicyParcel[] mediaResourcePolicyParcelArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(mediaResourcePolicyParcelArr, 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().config(mediaResourcePolicyParcelArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void addResource(int i, int i2, long j, IResourceManagerClient iResourceManagerClient, MediaResourceParcel[] mediaResourceParcelArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeStrongBinder(iResourceManagerClient != null ? iResourceManagerClient.asBinder() : null);
                    parcelObtain.writeTypedArray(mediaResourceParcelArr, 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addResource(i, i2, j, iResourceManagerClient, mediaResourceParcelArr);
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

            @Override // android.media.IResourceManagerService
            public void removeResource(int i, long j, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeTypedArray(mediaResourceParcelArr, 0);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeResource(i, j, mediaResourceParcelArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void removeClient(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeClient(i, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public boolean reclaimResource(int i, MediaResourceParcel[] mediaResourceParcelArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedArray(mediaResourceParcelArr, 0);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reclaimResource(i, mediaResourceParcelArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void overridePid(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overridePid(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void markClientForPendingRemoval(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().markClientForPendingRemoval(i, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IResourceManagerService
            public void reclaimResourcesFromClientsPendingRemoval(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reclaimResourcesFromClientsPendingRemoval(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IResourceManagerService iResourceManagerService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iResourceManagerService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iResourceManagerService;
            return true;
        }

        public static IResourceManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
