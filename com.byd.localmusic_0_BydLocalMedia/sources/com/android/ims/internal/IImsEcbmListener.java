package com.android.ims.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IImsEcbmListener extends IInterface {

    public static class Default implements IImsEcbmListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.ims.internal.IImsEcbmListener
        public void enteredECBM() throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsEcbmListener
        public void exitedECBM() throws RemoteException {
        }
    }

    void enteredECBM() throws RemoteException;

    void exitedECBM() throws RemoteException;

    public static abstract class Stub extends Binder implements IImsEcbmListener {
        private static final String DESCRIPTOR = "com.android.ims.internal.IImsEcbmListener";
        static final int TRANSACTION_enteredECBM = 1;
        static final int TRANSACTION_exitedECBM = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "enteredECBM";
            }
            if (i != 2) {
                return null;
            }
            return "exitedECBM";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IImsEcbmListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsEcbmListener)) {
                return (IImsEcbmListener) iInterfaceQueryLocalInterface;
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
                enteredECBM();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                exitedECBM();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IImsEcbmListener {
            public static IImsEcbmListener sDefaultImpl;
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

            @Override // com.android.ims.internal.IImsEcbmListener
            public void enteredECBM() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().enteredECBM();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsEcbmListener
            public void exitedECBM() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().exitedECBM();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsEcbmListener iImsEcbmListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsEcbmListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsEcbmListener;
            return true;
        }

        public static IImsEcbmListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
