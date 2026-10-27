package com.android.internal.location;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.WorkSource;
import com.android.internal.location.ILocationProviderManager;

/* JADX INFO: loaded from: classes4.dex */
public interface ILocationProvider extends IInterface {

    public static class Default implements ILocationProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.location.ILocationProvider
        public void sendExtraCommand(String str, Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.location.ILocationProvider
        public void setLocationProviderManager(ILocationProviderManager iLocationProviderManager) throws RemoteException {
        }

        @Override // com.android.internal.location.ILocationProvider
        public void setRequest(ProviderRequest providerRequest, WorkSource workSource) throws RemoteException {
        }
    }

    void sendExtraCommand(String str, Bundle bundle) throws RemoteException;

    void setLocationProviderManager(ILocationProviderManager iLocationProviderManager) throws RemoteException;

    void setRequest(ProviderRequest providerRequest, WorkSource workSource) throws RemoteException;

    public static abstract class Stub extends Binder implements ILocationProvider {
        private static final String DESCRIPTOR = "com.android.internal.location.ILocationProvider";
        static final int TRANSACTION_sendExtraCommand = 3;
        static final int TRANSACTION_setLocationProviderManager = 1;
        static final int TRANSACTION_setRequest = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setLocationProviderManager";
            }
            if (i == 2) {
                return "setRequest";
            }
            if (i != 3) {
                return null;
            }
            return "sendExtraCommand";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILocationProvider)) {
                return (ILocationProvider) iInterfaceQueryLocalInterface;
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
                setLocationProviderManager(ILocationProviderManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setRequest(parcel.readInt() != 0 ? ProviderRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? WorkSource.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                sendExtraCommand(parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ILocationProvider {
            public static ILocationProvider sDefaultImpl;
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

            @Override // com.android.internal.location.ILocationProvider
            public void setLocationProviderManager(ILocationProviderManager iLocationProviderManager) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iLocationProviderManager != null ? iLocationProviderManager.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setLocationProviderManager(iLocationProviderManager);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.location.ILocationProvider
            public void setRequest(ProviderRequest providerRequest, WorkSource workSource) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (providerRequest != null) {
                        parcelObtain.writeInt(1);
                        providerRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (workSource != null) {
                        parcelObtain.writeInt(1);
                        workSource.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setRequest(providerRequest, workSource);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.location.ILocationProvider
            public void sendExtraCommand(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendExtraCommand(str, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationProvider iLocationProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLocationProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLocationProvider;
            return true;
        }

        public static ILocationProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
