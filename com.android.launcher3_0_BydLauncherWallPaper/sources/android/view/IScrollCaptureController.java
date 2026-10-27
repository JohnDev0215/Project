package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IScrollCaptureClient;

/* JADX INFO: loaded from: classes3.dex */
public interface IScrollCaptureController extends IInterface {

    public static class Default implements IScrollCaptureController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IScrollCaptureController
        public void onCaptureBufferSent(long j, Rect rect) throws RemoteException {
        }

        @Override // android.view.IScrollCaptureController
        public void onCaptureStarted() throws RemoteException {
        }

        @Override // android.view.IScrollCaptureController
        public void onClientConnected(IScrollCaptureClient iScrollCaptureClient, Rect rect, Point point) throws RemoteException {
        }

        @Override // android.view.IScrollCaptureController
        public void onClientUnavailable() throws RemoteException {
        }

        @Override // android.view.IScrollCaptureController
        public void onConnectionClosed() throws RemoteException {
        }
    }

    void onCaptureBufferSent(long j, Rect rect) throws RemoteException;

    void onCaptureStarted() throws RemoteException;

    void onClientConnected(IScrollCaptureClient iScrollCaptureClient, Rect rect, Point point) throws RemoteException;

    void onClientUnavailable() throws RemoteException;

    void onConnectionClosed() throws RemoteException;

    public static abstract class Stub extends Binder implements IScrollCaptureController {
        private static final String DESCRIPTOR = "android.view.IScrollCaptureController";
        static final int TRANSACTION_onCaptureBufferSent = 4;
        static final int TRANSACTION_onCaptureStarted = 3;
        static final int TRANSACTION_onClientConnected = 1;
        static final int TRANSACTION_onClientUnavailable = 2;
        static final int TRANSACTION_onConnectionClosed = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onClientConnected";
            }
            if (i == 2) {
                return "onClientUnavailable";
            }
            if (i == 3) {
                return "onCaptureStarted";
            }
            if (i == 4) {
                return "onCaptureBufferSent";
            }
            if (i != 5) {
                return null;
            }
            return "onConnectionClosed";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IScrollCaptureController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IScrollCaptureController)) {
                return (IScrollCaptureController) iInterfaceQueryLocalInterface;
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
                onClientConnected(IScrollCaptureClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Point.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onClientUnavailable();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onCaptureStarted();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onCaptureBufferSent(parcel.readLong(), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onConnectionClosed();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IScrollCaptureController {
            public static IScrollCaptureController sDefaultImpl;
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

            @Override // android.view.IScrollCaptureController
            public void onClientConnected(IScrollCaptureClient iScrollCaptureClient, Rect rect, Point point) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iScrollCaptureClient != null ? iScrollCaptureClient.asBinder() : null);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (point != null) {
                        parcelObtain.writeInt(1);
                        point.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onClientConnected(iScrollCaptureClient, rect, point);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IScrollCaptureController
            public void onClientUnavailable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onClientUnavailable();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IScrollCaptureController
            public void onCaptureStarted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCaptureStarted();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IScrollCaptureController
            public void onCaptureBufferSent(long j, Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCaptureBufferSent(j, rect);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IScrollCaptureController
            public void onConnectionClosed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectionClosed();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScrollCaptureController iScrollCaptureController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iScrollCaptureController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iScrollCaptureController;
            return true;
        }

        public static IScrollCaptureController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
