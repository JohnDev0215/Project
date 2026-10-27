package com.byd.naviserviceapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface INaviStateCallback extends IInterface {

    public static class Default implements INaviStateCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onDayNightModeChange(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onFavoriteStatusUpdate(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onMapOverViewChange(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onMapSettingUpdate(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onMapStatusUpdate(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onNaviOrientationChange(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onNaviStatusUpdate(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onOpenHistoryDestination(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onOpenRouteOption(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onPOIDetailStatusUpdate(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onTrafficStatusUpdate(int stateCode) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviStateCallback
        public void onZoomStatusUpdate(int stateCode) throws RemoteException {
        }
    }

    void onDayNightModeChange(int stateCode) throws RemoteException;

    void onFavoriteStatusUpdate(int stateCode) throws RemoteException;

    void onMapOverViewChange(int stateCode) throws RemoteException;

    void onMapSettingUpdate(int stateCode) throws RemoteException;

    void onMapStatusUpdate(int stateCode) throws RemoteException;

    void onNaviOrientationChange(int stateCode) throws RemoteException;

    void onNaviStatusUpdate(int stateCode) throws RemoteException;

    void onOpenHistoryDestination(int stateCode) throws RemoteException;

    void onOpenRouteOption(int stateCode) throws RemoteException;

    void onPOIDetailStatusUpdate(int stateCode) throws RemoteException;

    void onTrafficStatusUpdate(int stateCode) throws RemoteException;

    void onZoomStatusUpdate(int stateCode) throws RemoteException;

    public static abstract class Stub extends Binder implements INaviStateCallback {
        private static final String DESCRIPTOR = "com.byd.naviserviceapi.INaviStateCallback";
        static final int TRANSACTION_onDayNightModeChange = 5;
        static final int TRANSACTION_onFavoriteStatusUpdate = 7;
        static final int TRANSACTION_onMapOverViewChange = 9;
        static final int TRANSACTION_onMapSettingUpdate = 8;
        static final int TRANSACTION_onMapStatusUpdate = 2;
        static final int TRANSACTION_onNaviOrientationChange = 6;
        static final int TRANSACTION_onNaviStatusUpdate = 1;
        static final int TRANSACTION_onOpenHistoryDestination = 10;
        static final int TRANSACTION_onOpenRouteOption = 11;
        static final int TRANSACTION_onPOIDetailStatusUpdate = 12;
        static final int TRANSACTION_onTrafficStatusUpdate = 3;
        static final int TRANSACTION_onZoomStatusUpdate = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviStateCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INaviStateCallback)) {
                return (INaviStateCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onNaviStatusUpdate(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onMapStatusUpdate(data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onTrafficStatusUpdate(data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onZoomStatusUpdate(data.readInt());
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onDayNightModeChange(data.readInt());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onNaviOrientationChange(data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onFavoriteStatusUpdate(data.readInt());
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    onMapSettingUpdate(data.readInt());
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    onMapOverViewChange(data.readInt());
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    onOpenHistoryDestination(data.readInt());
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    onOpenRouteOption(data.readInt());
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    onPOIDetailStatusUpdate(data.readInt());
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements INaviStateCallback {
            public static INaviStateCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onNaviStatusUpdate(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviStatusUpdate(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onMapStatusUpdate(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapStatusUpdate(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onTrafficStatusUpdate(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTrafficStatusUpdate(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onZoomStatusUpdate(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onZoomStatusUpdate(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onDayNightModeChange(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDayNightModeChange(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onNaviOrientationChange(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviOrientationChange(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onFavoriteStatusUpdate(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFavoriteStatusUpdate(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onMapSettingUpdate(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapSettingUpdate(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onMapOverViewChange(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapOverViewChange(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onOpenHistoryDestination(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOpenHistoryDestination(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onOpenRouteOption(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOpenRouteOption(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviStateCallback
            public void onPOIDetailStatusUpdate(int stateCode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(stateCode);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPOIDetailStatusUpdate(stateCode);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INaviStateCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INaviStateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
