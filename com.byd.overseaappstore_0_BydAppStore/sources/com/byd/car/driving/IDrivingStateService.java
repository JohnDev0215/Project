package com.byd.car.driving;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.car.driving.IDrivingStateListener;

/* JADX INFO: loaded from: classes.dex */
public interface IDrivingStateService extends IInterface {

    public static class Default implements IDrivingStateService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.car.driving.IDrivingStateService
        public int getDrivingState() throws RemoteException {
            return 0;
        }

        @Override // com.byd.car.driving.IDrivingStateService
        public void registerDrivingStateListener(IDrivingStateListener iDrivingStateListener) throws RemoteException {
        }

        @Override // com.byd.car.driving.IDrivingStateService
        public void unregisterDrivingStateListener(IDrivingStateListener iDrivingStateListener) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IDrivingStateService {
        private static final String DESCRIPTOR = "com.byd.car.driving.IDrivingStateService";
        public static final int TRANSACTION_getDrivingState = 1;
        public static final int TRANSACTION_registerDrivingStateListener = 2;
        public static final int TRANSACTION_unregisterDrivingStateListener = 3;

        public static class Proxy implements IDrivingStateService {
            public static IDrivingStateService sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.driving.IDrivingStateService
            public int getDrivingState() throws RemoteException {
                int drivingState;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        drivingState = parcelObtain2.readInt();
                    } else {
                        drivingState = Stub.getDefaultImpl().getDrivingState();
                    }
                    return drivingState;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.car.driving.IDrivingStateService
            public void registerDrivingStateListener(IDrivingStateListener iDrivingStateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDrivingStateListener != null ? iDrivingStateListener.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerDrivingStateListener(iDrivingStateListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.driving.IDrivingStateService
            public void unregisterDrivingStateListener(IDrivingStateListener iDrivingStateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDrivingStateListener != null ? iDrivingStateListener.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterDrivingStateListener(iDrivingStateListener);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDrivingStateService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IDrivingStateService)) ? new Proxy(iBinder) : (IDrivingStateService) iInterfaceQueryLocalInterface;
        }

        public static IDrivingStateService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDrivingStateService iDrivingStateService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDrivingStateService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDrivingStateService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int drivingState = getDrivingState();
                parcel2.writeNoException();
                parcel2.writeInt(drivingState);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                registerDrivingStateListener(IDrivingStateListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterDrivingStateListener(IDrivingStateListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    int getDrivingState() throws RemoteException;

    void registerDrivingStateListener(IDrivingStateListener iDrivingStateListener) throws RemoteException;

    void unregisterDrivingStateListener(IDrivingStateListener iDrivingStateListener) throws RemoteException;
}
