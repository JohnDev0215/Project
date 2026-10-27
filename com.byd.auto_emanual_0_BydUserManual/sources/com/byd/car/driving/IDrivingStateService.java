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
        public void registerDrivingStateListener(IDrivingStateListener listener) throws RemoteException {
        }

        @Override // com.byd.car.driving.IDrivingStateService
        public void unregisterDrivingStateListener(IDrivingStateListener listener) throws RemoteException {
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

            public Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.car.driving.IDrivingStateService
            public int getDrivingState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDrivingState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.car.driving.IDrivingStateService
            public void registerDrivingStateListener(IDrivingStateListener listener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerDrivingStateListener(listener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.car.driving.IDrivingStateService
            public void unregisterDrivingStateListener(IDrivingStateListener listener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterDrivingStateListener(listener);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDrivingStateService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IDrivingStateService)) ? new Proxy(obj) : (IDrivingStateService) iInterfaceQueryLocalInterface;
        }

        public static IDrivingStateService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDrivingStateService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int drivingState = getDrivingState();
                reply.writeNoException();
                reply.writeInt(drivingState);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                registerDrivingStateListener(IDrivingStateListener.Stub.asInterface(data.readStrongBinder()));
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                unregisterDrivingStateListener(IDrivingStateListener.Stub.asInterface(data.readStrongBinder()));
                return true;
            }
            if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            }
            reply.writeString(DESCRIPTOR);
            return true;
        }
    }

    int getDrivingState() throws RemoteException;

    void registerDrivingStateListener(IDrivingStateListener listener) throws RemoteException;

    void unregisterDrivingStateListener(IDrivingStateListener listener) throws RemoteException;
}
