package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.telecom.IInternalServiceRetriever;
import com.android.internal.telecom.ITelecomService;

/* JADX INFO: loaded from: classes4.dex */
public interface ITelecomLoader extends IInterface {

    public static class Default implements ITelecomLoader {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomLoader
        public ITelecomService createTelecomService(IInternalServiceRetriever iInternalServiceRetriever) throws RemoteException {
            return null;
        }
    }

    ITelecomService createTelecomService(IInternalServiceRetriever iInternalServiceRetriever) throws RemoteException;

    public static abstract class Stub extends Binder implements ITelecomLoader {
        private static final String DESCRIPTOR = "com.android.internal.telecom.ITelecomLoader";
        static final int TRANSACTION_createTelecomService = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "createTelecomService";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITelecomLoader asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITelecomLoader)) {
                return (ITelecomLoader) iInterfaceQueryLocalInterface;
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
            ITelecomService iTelecomServiceCreateTelecomService = createTelecomService(IInternalServiceRetriever.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeStrongBinder(iTelecomServiceCreateTelecomService != null ? iTelecomServiceCreateTelecomService.asBinder() : null);
            return true;
        }

        private static class Proxy implements ITelecomLoader {
            public static ITelecomLoader sDefaultImpl;
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

            @Override // com.android.internal.telecom.ITelecomLoader
            public ITelecomService createTelecomService(IInternalServiceRetriever iInternalServiceRetriever) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInternalServiceRetriever != null ? iInternalServiceRetriever.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createTelecomService(iInternalServiceRetriever);
                    }
                    parcelObtain2.readException();
                    return ITelecomService.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITelecomLoader iTelecomLoader) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTelecomLoader == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTelecomLoader;
            return true;
        }

        public static ITelecomLoader getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
