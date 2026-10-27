package android.content.pm.dex;

import android.content.pm.dex.ISnapshotRuntimeProfileCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IArtManager extends IInterface {

    public static class Default implements IArtManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.dex.IArtManager
        public boolean isRuntimeProfilingEnabled(int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.dex.IArtManager
        public void snapshotRuntimeProfile(int i, String str, String str2, ISnapshotRuntimeProfileCallback iSnapshotRuntimeProfileCallback, String str3) throws RemoteException {
        }
    }

    boolean isRuntimeProfilingEnabled(int i, String str) throws RemoteException;

    void snapshotRuntimeProfile(int i, String str, String str2, ISnapshotRuntimeProfileCallback iSnapshotRuntimeProfileCallback, String str3) throws RemoteException;

    public static abstract class Stub extends Binder implements IArtManager {
        private static final String DESCRIPTOR = "android.content.pm.dex.IArtManager";
        static final int TRANSACTION_isRuntimeProfilingEnabled = 2;
        static final int TRANSACTION_snapshotRuntimeProfile = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "snapshotRuntimeProfile";
            }
            if (i != 2) {
                return null;
            }
            return "isRuntimeProfilingEnabled";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IArtManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IArtManager)) {
                return (IArtManager) iInterfaceQueryLocalInterface;
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
                snapshotRuntimeProfile(parcel.readInt(), parcel.readString(), parcel.readString(), ISnapshotRuntimeProfileCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zIsRuntimeProfilingEnabled = isRuntimeProfilingEnabled(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(zIsRuntimeProfilingEnabled ? 1 : 0);
            return true;
        }

        private static class Proxy implements IArtManager {
            public static IArtManager sDefaultImpl;
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

            @Override // android.content.pm.dex.IArtManager
            public void snapshotRuntimeProfile(int i, String str, String str2, ISnapshotRuntimeProfileCallback iSnapshotRuntimeProfileCallback, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iSnapshotRuntimeProfileCallback != null ? iSnapshotRuntimeProfileCallback.asBinder() : null);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().snapshotRuntimeProfile(i, str, str2, iSnapshotRuntimeProfileCallback, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.dex.IArtManager
            public boolean isRuntimeProfilingEnabled(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRuntimeProfilingEnabled(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IArtManager iArtManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iArtManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iArtManager;
            return true;
        }

        public static IArtManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
