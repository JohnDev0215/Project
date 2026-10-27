package android.service.carrier;

import android.content.ContentValues;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IApnSourceService extends IInterface {

    public static class Default implements IApnSourceService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.carrier.IApnSourceService
        public ContentValues[] getApns(int i) throws RemoteException {
            return null;
        }
    }

    ContentValues[] getApns(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IApnSourceService {
        private static final String DESCRIPTOR = "android.service.carrier.IApnSourceService";
        static final int TRANSACTION_getApns = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "getApns";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IApnSourceService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IApnSourceService)) {
                return (IApnSourceService) iInterfaceQueryLocalInterface;
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
            ContentValues[] apns = getApns(parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeTypedArray(apns, 1);
            return true;
        }

        private static class Proxy implements IApnSourceService {
            public static IApnSourceService sDefaultImpl;
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

            @Override // android.service.carrier.IApnSourceService
            public ContentValues[] getApns(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApns(i);
                    }
                    parcelObtain2.readException();
                    return (ContentValues[]) parcelObtain2.createTypedArray(ContentValues.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IApnSourceService iApnSourceService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iApnSourceService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iApnSourceService;
            return true;
        }

        public static IApnSourceService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
