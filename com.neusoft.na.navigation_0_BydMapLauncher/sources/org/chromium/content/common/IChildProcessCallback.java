package org.chromium.content.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes3.dex */
public interface IChildProcessCallback extends IInterface {
    void establishSurfacePeer(int i, Surface surface, int i2, int i3) throws RemoteException;

    SurfaceWrapper getSurfaceTextureSurface(int i) throws RemoteException;

    SurfaceWrapper getViewSurface(int i) throws RemoteException;

    void onDownloadStarted(boolean z, int i) throws RemoteException;

    void registerSurfaceTextureSurface(int i, int i2, Surface surface) throws RemoteException;

    void unregisterSurfaceTextureSurface(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IChildProcessCallback {
        private static final String DESCRIPTOR = "org.chromium.content.common.IChildProcessCallback";
        static final int TRANSACTION_establishSurfacePeer = 1;
        static final int TRANSACTION_getSurfaceTextureSurface = 5;
        static final int TRANSACTION_getViewSurface = 2;
        static final int TRANSACTION_onDownloadStarted = 6;
        static final int TRANSACTION_registerSurfaceTextureSurface = 3;
        static final int TRANSACTION_unregisterSurfaceTextureSurface = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IChildProcessCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IChildProcessCallback)) {
                return (IChildProcessCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    establishSurfacePeer(parcel.readInt(), parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    SurfaceWrapper viewSurface = getViewSurface(parcel.readInt());
                    parcel2.writeNoException();
                    if (viewSurface != null) {
                        parcel2.writeInt(1);
                        viewSurface.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerSurfaceTextureSurface(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterSurfaceTextureSurface(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    SurfaceWrapper surfaceTextureSurface = getSurfaceTextureSurface(parcel.readInt());
                    parcel2.writeNoException();
                    if (surfaceTextureSurface != null) {
                        parcel2.writeInt(1);
                        surfaceTextureSurface.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDownloadStarted(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IChildProcessCallback {
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

            @Override // org.chromium.content.common.IChildProcessCallback
            public void establishSurfacePeer(int i, Surface surface, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public SurfaceWrapper getViewSurface(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SurfaceWrapper.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public void registerSurfaceTextureSurface(int i, int i2, Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public void unregisterSurfaceTextureSurface(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public SurfaceWrapper getSurfaceTextureSurface(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SurfaceWrapper.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.content.common.IChildProcessCallback
            public void onDownloadStarted(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
