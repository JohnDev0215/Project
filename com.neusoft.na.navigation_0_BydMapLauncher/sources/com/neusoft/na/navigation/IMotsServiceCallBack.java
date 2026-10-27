package com.neusoft.na.navigation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IMotsServiceCallBack extends IInterface {

    public static class Default implements IMotsServiceCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.navigation.IMotsServiceCallBack
        public void onDayNightModeChanged(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceCallBack
        public void onPlaySoundEffect() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceCallBack
        public void onWebViewShowHmi() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceCallBack
        public void onXWalkInitFailed() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceCallBack
        public void onXWalkViewShowHmi() throws RemoteException {
        }
    }

    void onDayNightModeChanged(int i) throws RemoteException;

    void onPlaySoundEffect() throws RemoteException;

    void onWebViewShowHmi() throws RemoteException;

    void onXWalkInitFailed() throws RemoteException;

    void onXWalkViewShowHmi() throws RemoteException;

    public static abstract class Stub extends Binder implements IMotsServiceCallBack {
        private static final String DESCRIPTOR = "com.neusoft.na.navigation.IMotsServiceCallBack";
        static final int TRANSACTION_onDayNightModeChanged = 5;
        static final int TRANSACTION_onPlaySoundEffect = 4;
        static final int TRANSACTION_onWebViewShowHmi = 3;
        static final int TRANSACTION_onXWalkInitFailed = 1;
        static final int TRANSACTION_onXWalkViewShowHmi = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMotsServiceCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMotsServiceCallBack)) {
                return (IMotsServiceCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onXWalkInitFailed();
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onXWalkViewShowHmi();
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onWebViewShowHmi();
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onPlaySoundEffect();
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onDayNightModeChanged(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IMotsServiceCallBack {
            public static IMotsServiceCallBack sDefaultImpl;
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

            @Override // com.neusoft.na.navigation.IMotsServiceCallBack
            public void onXWalkInitFailed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onXWalkInitFailed();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceCallBack
            public void onXWalkViewShowHmi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onXWalkViewShowHmi();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceCallBack
            public void onWebViewShowHmi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWebViewShowHmi();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceCallBack
            public void onPlaySoundEffect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaySoundEffect();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceCallBack
            public void onDayNightModeChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDayNightModeChanged(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMotsServiceCallBack iMotsServiceCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMotsServiceCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMotsServiceCallBack;
            return true;
        }

        public static IMotsServiceCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
