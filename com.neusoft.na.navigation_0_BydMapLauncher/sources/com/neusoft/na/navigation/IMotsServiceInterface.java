package com.neusoft.na.navigation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.neusoft.na.navigation.IMotsServiceCallBack;

/* JADX INFO: loaded from: classes2.dex */
public interface IMotsServiceInterface extends IInterface {

    public static class Default implements IMotsServiceInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public int getDayNightMode() throws RemoteException {
            return 0;
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public int getMotsStatus() throws RemoteException {
            return 0;
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public int getWebViewMode() throws RemoteException {
            return 0;
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void haspermissions() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void maskGone() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void notifySurfaceSizeChanged() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void onKeyCode(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void onMainCreate() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void onMainDestroy() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void onMainPause() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void onMainResume() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void setCallBack(IMotsServiceCallBack iMotsServiceCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void setClusterSurface(Surface surface) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void setInMultiWindowMode(boolean z) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void setSurface(Surface surface) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void setWidgetSurface(Surface surface) throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void timeZoneChanged() throws RemoteException {
        }

        @Override // com.neusoft.na.navigation.IMotsServiceInterface
        public void unSetCallBack() throws RemoteException {
        }
    }

    int getDayNightMode() throws RemoteException;

    int getMotsStatus() throws RemoteException;

    int getWebViewMode() throws RemoteException;

    void haspermissions() throws RemoteException;

    void maskGone() throws RemoteException;

    void notifySurfaceSizeChanged() throws RemoteException;

    void onKeyCode(int i) throws RemoteException;

    void onMainCreate() throws RemoteException;

    void onMainDestroy() throws RemoteException;

    void onMainPause() throws RemoteException;

    void onMainResume() throws RemoteException;

    void setCallBack(IMotsServiceCallBack iMotsServiceCallBack) throws RemoteException;

    void setClusterSurface(Surface surface) throws RemoteException;

    void setInMultiWindowMode(boolean z) throws RemoteException;

    void setSurface(Surface surface) throws RemoteException;

    void setWidgetSurface(Surface surface) throws RemoteException;

    void timeZoneChanged() throws RemoteException;

    void unSetCallBack() throws RemoteException;

    public static abstract class Stub extends Binder implements IMotsServiceInterface {
        private static final String DESCRIPTOR = "com.neusoft.na.navigation.IMotsServiceInterface";
        static final int TRANSACTION_getDayNightMode = 13;
        static final int TRANSACTION_getMotsStatus = 11;
        static final int TRANSACTION_getWebViewMode = 1;
        static final int TRANSACTION_haspermissions = 2;
        static final int TRANSACTION_maskGone = 18;
        static final int TRANSACTION_notifySurfaceSizeChanged = 8;
        static final int TRANSACTION_onKeyCode = 16;
        static final int TRANSACTION_onMainCreate = 3;
        static final int TRANSACTION_onMainDestroy = 6;
        static final int TRANSACTION_onMainPause = 5;
        static final int TRANSACTION_onMainResume = 4;
        static final int TRANSACTION_setCallBack = 9;
        static final int TRANSACTION_setClusterSurface = 15;
        static final int TRANSACTION_setInMultiWindowMode = 17;
        static final int TRANSACTION_setSurface = 7;
        static final int TRANSACTION_setWidgetSurface = 14;
        static final int TRANSACTION_timeZoneChanged = 12;
        static final int TRANSACTION_unSetCallBack = 10;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMotsServiceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMotsServiceInterface)) {
                return (IMotsServiceInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Surface surface;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int webViewMode = getWebViewMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(webViewMode);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    haspermissions();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMainCreate();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMainResume();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMainPause();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMainDestroy();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    surface = parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null;
                    setSurface(surface);
                    parcel2.writeNoException();
                    if (surface != null) {
                        parcel2.writeInt(1);
                        surface.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySurfaceSizeChanged();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCallBack(IMotsServiceCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    unSetCallBack();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int motsStatus = getMotsStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(motsStatus);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    timeZoneChanged();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dayNightMode = getDayNightMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(dayNightMode);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    surface = parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null;
                    setWidgetSurface(surface);
                    parcel2.writeNoException();
                    if (surface != null) {
                        parcel2.writeInt(1);
                        surface.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    surface = parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null;
                    setClusterSurface(surface);
                    parcel2.writeNoException();
                    if (surface != null) {
                        parcel2.writeInt(1);
                        surface.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onKeyCode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInMultiWindowMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    maskGone();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMotsServiceInterface {
            public static IMotsServiceInterface sDefaultImpl;
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

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public int getWebViewMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWebViewMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void haspermissions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().haspermissions();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void onMainCreate() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMainCreate();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void onMainResume() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMainResume();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void onMainPause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMainPause();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void onMainDestroy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMainDestroy();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void setSurface(Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSurface(surface);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        surface.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void notifySurfaceSizeChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySurfaceSizeChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void setCallBack(IMotsServiceCallBack iMotsServiceCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMotsServiceCallBack != null ? iMotsServiceCallBack.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallBack(iMotsServiceCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void unSetCallBack() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unSetCallBack();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public int getMotsStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMotsStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void timeZoneChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().timeZoneChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public int getDayNightMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDayNightMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void setWidgetSurface(Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWidgetSurface(surface);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        surface.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void setClusterSurface(Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setClusterSurface(surface);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        surface.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void onKeyCode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyCode(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void setInMultiWindowMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInMultiWindowMode(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.navigation.IMotsServiceInterface
            public void maskGone() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().maskGone();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMotsServiceInterface iMotsServiceInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMotsServiceInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMotsServiceInterface;
            return true;
        }

        public static IMotsServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
