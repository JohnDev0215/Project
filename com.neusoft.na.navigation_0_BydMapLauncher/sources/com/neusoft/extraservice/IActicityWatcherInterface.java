package com.neusoft.extraservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.neusoft.extraservice.IActivityWatcherCallBackInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IActicityWatcherInterface extends IInterface {
    void registerActivityWatcher(IActivityWatcherCallBackInterface iActivityWatcherCallBackInterface) throws RemoteException;

    void unregisterActivityWatcher(IActivityWatcherCallBackInterface iActivityWatcherCallBackInterface) throws RemoteException;

    public static abstract class Stub extends Binder implements IActicityWatcherInterface {
        private static final String DESCRIPTOR = "com.neusoft.extraservice.IActicityWatcherInterface";
        static final int TRANSACTION_registerActivityWatcher = 1;
        static final int TRANSACTION_unregisterActivityWatcher = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IActicityWatcherInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IActicityWatcherInterface)) {
                return (IActicityWatcherInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                registerActivityWatcher(IActivityWatcherCallBackInterface.Stub.asInterface(parcel.readStrongBinder()));
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
            unregisterActivityWatcher(IActivityWatcherCallBackInterface.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IActicityWatcherInterface {
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

            @Override // com.neusoft.extraservice.IActicityWatcherInterface
            public void registerActivityWatcher(IActivityWatcherCallBackInterface iActivityWatcherCallBackInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iActivityWatcherCallBackInterface != null ? iActivityWatcherCallBackInterface.asBinder() : null);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.extraservice.IActicityWatcherInterface
            public void unregisterActivityWatcher(IActivityWatcherCallBackInterface iActivityWatcherCallBackInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iActivityWatcherCallBackInterface != null ? iActivityWatcherCallBackInterface.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
