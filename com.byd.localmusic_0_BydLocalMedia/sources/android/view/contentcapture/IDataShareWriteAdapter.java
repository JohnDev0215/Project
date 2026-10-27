package android.view.contentcapture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDataShareWriteAdapter extends IInterface {

    public static class Default implements IDataShareWriteAdapter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void error(int i) throws RemoteException {
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void finish() throws RemoteException {
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void rejected() throws RemoteException {
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void write(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }
    }

    void error(int i) throws RemoteException;

    void finish() throws RemoteException;

    void rejected() throws RemoteException;

    void write(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataShareWriteAdapter {
        private static final String DESCRIPTOR = "android.view.contentcapture.IDataShareWriteAdapter";
        static final int TRANSACTION_error = 2;
        static final int TRANSACTION_finish = 4;
        static final int TRANSACTION_rejected = 3;
        static final int TRANSACTION_write = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "write";
            }
            if (i == 2) {
                return "error";
            }
            if (i == 3) {
                return "rejected";
            }
            if (i != 4) {
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

        public static IDataShareWriteAdapter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDataShareWriteAdapter)) {
                return (IDataShareWriteAdapter) iInterfaceQueryLocalInterface;
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
                write(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                error(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                rejected();
                return true;
            }
            if (i == 4) {
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

        private static class Proxy implements IDataShareWriteAdapter {
            public static IDataShareWriteAdapter sDefaultImpl;
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

            @Override // android.view.contentcapture.IDataShareWriteAdapter
            public void write(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
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
                    Stub.getDefaultImpl().write(parcelFileDescriptor);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IDataShareWriteAdapter
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

            @Override // android.view.contentcapture.IDataShareWriteAdapter
            public void rejected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().rejected();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.contentcapture.IDataShareWriteAdapter
            public void finish() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().finish();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataShareWriteAdapter iDataShareWriteAdapter) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDataShareWriteAdapter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDataShareWriteAdapter;
            return true;
        }

        public static IDataShareWriteAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
