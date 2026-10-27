package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.IGraphicsStatsCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IGraphicsStats extends IInterface {

    public static class Default implements IGraphicsStats {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IGraphicsStats
        public ParcelFileDescriptor requestBufferForProcess(String str, IGraphicsStatsCallback iGraphicsStatsCallback) throws RemoteException {
            return null;
        }
    }

    ParcelFileDescriptor requestBufferForProcess(String str, IGraphicsStatsCallback iGraphicsStatsCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IGraphicsStats {
        private static final String DESCRIPTOR = "android.view.IGraphicsStats";
        static final int TRANSACTION_requestBufferForProcess = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "requestBufferForProcess";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGraphicsStats asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGraphicsStats)) {
                return (IGraphicsStats) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            ParcelFileDescriptor parcelFileDescriptorRequestBufferForProcess = requestBufferForProcess(parcel.readString(), IGraphicsStatsCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            if (parcelFileDescriptorRequestBufferForProcess != null) {
                parcel2.writeInt(1);
                parcelFileDescriptorRequestBufferForProcess.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IGraphicsStats {
            public static IGraphicsStats sDefaultImpl;
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

            @Override // android.view.IGraphicsStats
            public ParcelFileDescriptor requestBufferForProcess(String str, IGraphicsStatsCallback iGraphicsStatsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iGraphicsStatsCallback != null ? iGraphicsStatsCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestBufferForProcess(str, iGraphicsStatsCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGraphicsStats iGraphicsStats) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGraphicsStats == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGraphicsStats;
            return true;
        }

        public static IGraphicsStats getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
