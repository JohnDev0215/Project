package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ITetheringStatsProvider extends IInterface {
    public static final int QUOTA_UNLIMITED = -1;

    public static class Default implements ITetheringStatsProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.ITetheringStatsProvider
        public NetworkStats getTetherStats(int i) throws RemoteException {
            return null;
        }

        @Override // android.net.ITetheringStatsProvider
        public void setInterfaceQuota(String str, long j) throws RemoteException {
        }
    }

    NetworkStats getTetherStats(int i) throws RemoteException;

    void setInterfaceQuota(String str, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements ITetheringStatsProvider {
        private static final String DESCRIPTOR = "android.net.ITetheringStatsProvider";
        static final int TRANSACTION_getTetherStats = 1;
        static final int TRANSACTION_setInterfaceQuota = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getTetherStats";
            }
            if (i != 2) {
                return null;
            }
            return "setInterfaceQuota";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITetheringStatsProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITetheringStatsProvider)) {
                return (ITetheringStatsProvider) iInterfaceQueryLocalInterface;
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
                NetworkStats tetherStats = getTetherStats(parcel.readInt());
                parcel2.writeNoException();
                if (tetherStats != null) {
                    parcel2.writeInt(1);
                    tetherStats.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
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
            setInterfaceQuota(parcel.readString(), parcel.readLong());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ITetheringStatsProvider {
            public static ITetheringStatsProvider sDefaultImpl;
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

            @Override // android.net.ITetheringStatsProvider
            public NetworkStats getTetherStats(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTetherStats(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ITetheringStatsProvider
            public void setInterfaceQuota(String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInterfaceQuota(str, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITetheringStatsProvider iTetheringStatsProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTetheringStatsProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTetheringStatsProvider;
            return true;
        }

        public static ITetheringStatsProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
