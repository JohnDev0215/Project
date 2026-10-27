package android.service.contentcapture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.provider.Telephony;

/* JADX INFO: loaded from: classes3.dex */
public interface IDataShareReadAdapter extends IInterface {

    public static class Default implements IDataShareReadAdapter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.contentcapture.IDataShareReadAdapter
        public void error(int i) throws RemoteException {
        }

        @Override // android.service.contentcapture.IDataShareReadAdapter
        public void finish() throws RemoteException {
        }

        @Override // android.service.contentcapture.IDataShareReadAdapter
        public void start(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }
    }

    void error(int i) throws RemoteException;

    void finish() throws RemoteException;

    void start(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataShareReadAdapter {
        private static final String DESCRIPTOR = "android.service.contentcapture.IDataShareReadAdapter";
        static final int TRANSACTION_error = 2;
        static final int TRANSACTION_finish = 3;
        static final int TRANSACTION_start = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return Telephony.BaseMmsColumns.START;
            }
            if (i == 2) {
                return "error";
            }
            if (i != 3) {
                return null;
            }
            return "finish";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDataShareReadAdapter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDataShareReadAdapter)) {
                return (IDataShareReadAdapter) iInterfaceQueryLocalInterface;
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
                start(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                error(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                finish();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDataShareReadAdapter {
            public static IDataShareReadAdapter sDefaultImpl;
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

            @Override // android.service.contentcapture.IDataShareReadAdapter
            public void start(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().start(parcelFileDescriptor);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IDataShareReadAdapter
            public void error(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().error(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentcapture.IDataShareReadAdapter
            public void finish() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().finish();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataShareReadAdapter iDataShareReadAdapter) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDataShareReadAdapter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDataShareReadAdapter;
            return true;
        }

        public static IDataShareReadAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
