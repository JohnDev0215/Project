package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.telecom.IDeviceIdleControllerAdapter;

/* JADX INFO: loaded from: classes4.dex */
public interface IInternalServiceRetriever extends IInterface {

    public static class Default implements IInternalServiceRetriever {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.IInternalServiceRetriever
        public IDeviceIdleControllerAdapter getDeviceIdleController() throws RemoteException {
            return null;
        }
    }

    IDeviceIdleControllerAdapter getDeviceIdleController() throws RemoteException;

    public static abstract class Stub extends Binder implements IInternalServiceRetriever {
        private static final String DESCRIPTOR = "com.android.internal.telecom.IInternalServiceRetriever";
        static final int TRANSACTION_getDeviceIdleController = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "getDeviceIdleController";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInternalServiceRetriever asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInternalServiceRetriever)) {
                return (IInternalServiceRetriever) iInterfaceQueryLocalInterface;
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
            IDeviceIdleControllerAdapter deviceIdleController = getDeviceIdleController();
            parcel2.writeNoException();
            parcel2.writeStrongBinder(deviceIdleController != null ? deviceIdleController.asBinder() : null);
            return true;
        }

        private static class Proxy implements IInternalServiceRetriever {
            public static IInternalServiceRetriever sDefaultImpl;
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

            @Override // com.android.internal.telecom.IInternalServiceRetriever
            public IDeviceIdleControllerAdapter getDeviceIdleController() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceIdleController();
                    }
                    parcelObtain2.readException();
                    return IDeviceIdleControllerAdapter.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInternalServiceRetriever iInternalServiceRetriever) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInternalServiceRetriever == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInternalServiceRetriever;
            return true;
        }

        public static IInternalServiceRetriever getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
