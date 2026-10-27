package android.service.wallpaper;

import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes3.dex */
public interface IWallpaperEngine extends IInterface {

    public static class Default implements IWallpaperEngine {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void destroy() throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void dispatchPointer(MotionEvent motionEvent) throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle) throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void requestWallpaperColors() throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void scalePreview(Rect rect) throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setDesiredSize(int i, int i2) throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setDisplayPadding(Rect rect) throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setInAmbientMode(boolean z, long j) throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setVisibility(boolean z) throws RemoteException {
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setZoomOut(float f) throws RemoteException {
        }
    }

    void destroy() throws RemoteException;

    void dispatchPointer(MotionEvent motionEvent) throws RemoteException;

    void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle) throws RemoteException;

    void requestWallpaperColors() throws RemoteException;

    void scalePreview(Rect rect) throws RemoteException;

    void setDesiredSize(int i, int i2) throws RemoteException;

    void setDisplayPadding(Rect rect) throws RemoteException;

    void setInAmbientMode(boolean z, long j) throws RemoteException;

    void setVisibility(boolean z) throws RemoteException;

    void setZoomOut(float f) throws RemoteException;

    public static abstract class Stub extends Binder implements IWallpaperEngine {
        private static final String DESCRIPTOR = "android.service.wallpaper.IWallpaperEngine";
        static final int TRANSACTION_destroy = 8;
        static final int TRANSACTION_dispatchPointer = 5;
        static final int TRANSACTION_dispatchWallpaperCommand = 6;
        static final int TRANSACTION_requestWallpaperColors = 7;
        static final int TRANSACTION_scalePreview = 10;
        static final int TRANSACTION_setDesiredSize = 1;
        static final int TRANSACTION_setDisplayPadding = 2;
        static final int TRANSACTION_setInAmbientMode = 4;
        static final int TRANSACTION_setVisibility = 3;
        static final int TRANSACTION_setZoomOut = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setDesiredSize";
                case 2:
                    return "setDisplayPadding";
                case 3:
                    return "setVisibility";
                case 4:
                    return "setInAmbientMode";
                case 5:
                    return "dispatchPointer";
                case 6:
                    return "dispatchWallpaperCommand";
                case 7:
                    return "requestWallpaperColors";
                case 8:
                    return "destroy";
                case 9:
                    return "setZoomOut";
                case 10:
                    return "scalePreview";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWallpaperEngine asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWallpaperEngine)) {
                return (IWallpaperEngine) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
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
                    setDesiredSize(parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDisplayPadding(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVisibility(parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInAmbientMode(parcel.readInt() != 0, parcel.readLong());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchPointer(parcel.readInt() != 0 ? MotionEvent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    dispatchWallpaperCommand(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestWallpaperColors();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroy();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setZoomOut(parcel.readFloat());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    scalePreview(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWallpaperEngine {
            public static IWallpaperEngine sDefaultImpl;
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

            @Override // android.service.wallpaper.IWallpaperEngine
            public void setDesiredSize(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setDesiredSize(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void setDisplayPadding(Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setDisplayPadding(rect);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void setVisibility(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setVisibility(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void setInAmbientMode(boolean z, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setInAmbientMode(z, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void dispatchPointer(MotionEvent motionEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (motionEvent != null) {
                        parcelObtain.writeInt(1);
                        motionEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchPointer(motionEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchWallpaperCommand(str, i, i2, i3, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void requestWallpaperColors() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestWallpaperColors();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void destroy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().destroy();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void setZoomOut(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setZoomOut(f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.wallpaper.IWallpaperEngine
            public void scalePreview(Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().scalePreview(rect);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWallpaperEngine iWallpaperEngine) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWallpaperEngine == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWallpaperEngine;
            return true;
        }

        public static IWallpaperEngine getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
