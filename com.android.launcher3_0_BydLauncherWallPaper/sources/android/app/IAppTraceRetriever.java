package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAppTraceRetriever extends IInterface {

    public static class Default implements IAppTraceRetriever {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IAppTraceRetriever
        public ParcelFileDescriptor getTraceFileDescriptor(String str, int i, int i2) throws RemoteException {
            return null;
        }
    }

    ParcelFileDescriptor getTraceFileDescriptor(String str, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppTraceRetriever {
        private static final String DESCRIPTOR = "android.app.IAppTraceRetriever";
        static final int TRANSACTION_getTraceFileDescriptor = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "getTraceFileDescriptor";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppTraceRetriever asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppTraceRetriever)) {
                return (IAppTraceRetriever) iInterfaceQueryLocalInterface;
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
            ParcelFileDescriptor traceFileDescriptor = getTraceFileDescriptor(parcel.readString(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            if (traceFileDescriptor != null) {
                parcel2.writeInt(1);
                traceFileDescriptor.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IAppTraceRetriever {
            public static IAppTraceRetriever sDefaultImpl;
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

            @Override // android.app.IAppTraceRetriever
            public ParcelFileDescriptor getTraceFileDescriptor(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTraceFileDescriptor(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppTraceRetriever iAppTraceRetriever) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppTraceRetriever == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppTraceRetriever;
            return true;
        }

        public static IAppTraceRetriever getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
