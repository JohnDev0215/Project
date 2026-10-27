package com.android.ims.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.ims.internal.IImsExternalCallStateListener;

/* JADX INFO: loaded from: classes4.dex */
public interface IImsMultiEndpoint extends IInterface {

    public static class Default implements IImsMultiEndpoint {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.ims.internal.IImsMultiEndpoint
        public void requestImsExternalCallStateInfo() throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsMultiEndpoint
        public void setListener(IImsExternalCallStateListener iImsExternalCallStateListener) throws RemoteException {
        }
    }

    void requestImsExternalCallStateInfo() throws RemoteException;

    void setListener(IImsExternalCallStateListener iImsExternalCallStateListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsMultiEndpoint {
        private static final String DESCRIPTOR = "com.android.ims.internal.IImsMultiEndpoint";
        static final int TRANSACTION_requestImsExternalCallStateInfo = 2;
        static final int TRANSACTION_setListener = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setListener";
            }
            if (i != 2) {
                return null;
            }
            return "requestImsExternalCallStateInfo";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IImsMultiEndpoint asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsMultiEndpoint)) {
                return (IImsMultiEndpoint) iInterfaceQueryLocalInterface;
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
                setListener(IImsExternalCallStateListener.Stub.asInterface(parcel.readStrongBinder()));
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
            requestImsExternalCallStateInfo();
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IImsMultiEndpoint {
            public static IImsMultiEndpoint sDefaultImpl;
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

            @Override // com.android.ims.internal.IImsMultiEndpoint
            public void setListener(IImsExternalCallStateListener iImsExternalCallStateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iImsExternalCallStateListener != null ? iImsExternalCallStateListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setListener(iImsExternalCallStateListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsMultiEndpoint
            public void requestImsExternalCallStateInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestImsExternalCallStateInfo();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsMultiEndpoint iImsMultiEndpoint) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsMultiEndpoint == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsMultiEndpoint;
            return true;
        }

        public static IImsMultiEndpoint getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
