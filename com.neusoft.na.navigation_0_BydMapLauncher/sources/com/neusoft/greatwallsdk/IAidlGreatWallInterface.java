package com.neusoft.greatwallsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.neusoft.greatwallsdk.IAidlGreatWallListener;
import com.neusoft.greatwallsdk.IAidlRequestCallback;
import com.neusoft.greatwallsdk.IAidlSetSurfaceResultCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IAidlGreatWallInterface extends IInterface {

    public static class Default implements IAidlGreatWallInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
        public void async_request(String str, IAidlRequestCallback iAidlRequestCallback) throws RemoteException {
        }

        @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
        public void cancelListener(IAidlGreatWallListener iAidlGreatWallListener) throws RemoteException {
        }

        @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
        public void setListener(IAidlGreatWallListener iAidlGreatWallListener) throws RemoteException {
        }

        @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
        public void setSurface(Surface surface, IAidlSetSurfaceResultCallback iAidlSetSurfaceResultCallback) throws RemoteException {
        }

        @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
        public String sync_request(String str) throws RemoteException {
            return null;
        }
    }

    void async_request(String str, IAidlRequestCallback iAidlRequestCallback) throws RemoteException;

    void cancelListener(IAidlGreatWallListener iAidlGreatWallListener) throws RemoteException;

    void setListener(IAidlGreatWallListener iAidlGreatWallListener) throws RemoteException;

    void setSurface(Surface surface, IAidlSetSurfaceResultCallback iAidlSetSurfaceResultCallback) throws RemoteException;

    String sync_request(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IAidlGreatWallInterface {
        private static final String DESCRIPTOR = "com.neusoft.greatwallsdk.IAidlGreatWallInterface";
        static final int TRANSACTION_async_request = 2;
        static final int TRANSACTION_cancelListener = 3;
        static final int TRANSACTION_setListener = 4;
        static final int TRANSACTION_setSurface = 5;
        static final int TRANSACTION_sync_request = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAidlGreatWallInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAidlGreatWallInterface)) {
                return (IAidlGreatWallInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String strSync_request = sync_request(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strSync_request);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                async_request(parcel.readString(), IAidlRequestCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                cancelListener(IAidlGreatWallListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                setListener(IAidlGreatWallListener.Stub.asInterface(parcel.readStrongBinder()));
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
            Surface surface = parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null;
            setSurface(surface, IAidlSetSurfaceResultCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            if (surface != null) {
                parcel2.writeInt(1);
                surface.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IAidlGreatWallInterface {
            public static IAidlGreatWallInterface sDefaultImpl;
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

            @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
            public String sync_request(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sync_request(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
            public void async_request(String str, IAidlRequestCallback iAidlRequestCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iAidlRequestCallback != null ? iAidlRequestCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().async_request(str, iAidlRequestCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
            public void cancelListener(IAidlGreatWallListener iAidlGreatWallListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAidlGreatWallListener != null ? iAidlGreatWallListener.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelListener(iAidlGreatWallListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
            public void setListener(IAidlGreatWallListener iAidlGreatWallListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAidlGreatWallListener != null ? iAidlGreatWallListener.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setListener(iAidlGreatWallListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.greatwallsdk.IAidlGreatWallInterface
            public void setSurface(Surface surface, IAidlSetSurfaceResultCallback iAidlSetSurfaceResultCallback) throws RemoteException {
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
                    parcelObtain.writeStrongBinder(iAidlSetSurfaceResultCallback != null ? iAidlSetSurfaceResultCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSurface(surface, iAidlSetSurfaceResultCallback);
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
        }

        public static boolean setDefaultImpl(IAidlGreatWallInterface iAidlGreatWallInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAidlGreatWallInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAidlGreatWallInterface;
            return true;
        }

        public static IAidlGreatWallInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
