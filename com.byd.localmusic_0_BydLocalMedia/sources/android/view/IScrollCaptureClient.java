package android.view;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IScrollCaptureClient extends IInterface {

    public static class Default implements IScrollCaptureClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IScrollCaptureClient
        public void endCapture() throws RemoteException {
        }

        @Override // android.view.IScrollCaptureClient
        public void requestImage(Rect rect) throws RemoteException {
        }

        @Override // android.view.IScrollCaptureClient
        public void startCapture(Surface surface) throws RemoteException {
        }
    }

    void endCapture() throws RemoteException;

    void requestImage(Rect rect) throws RemoteException;

    void startCapture(Surface surface) throws RemoteException;

    public static abstract class Stub extends Binder implements IScrollCaptureClient {
        private static final String DESCRIPTOR = "android.view.IScrollCaptureClient";
        static final int TRANSACTION_endCapture = 3;
        static final int TRANSACTION_requestImage = 2;
        static final int TRANSACTION_startCapture = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "startCapture";
            }
            if (i == 2) {
                return "requestImage";
            }
            if (i != 3) {
                return null;
            }
            return "endCapture";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IScrollCaptureClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IScrollCaptureClient)) {
                return (IScrollCaptureClient) iInterfaceQueryLocalInterface;
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
                startCapture(parcel.readInt() != 0 ? Surface.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                requestImage(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                endCapture();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IScrollCaptureClient {
            public static IScrollCaptureClient sDefaultImpl;
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

            @Override // android.view.IScrollCaptureClient
            public void startCapture(Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startCapture(surface);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IScrollCaptureClient
            public void requestImage(Rect rect) throws RemoteException {
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
                    Stub.getDefaultImpl().requestImage(rect);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IScrollCaptureClient
            public void endCapture() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().endCapture();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScrollCaptureClient iScrollCaptureClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iScrollCaptureClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iScrollCaptureClient;
            return true;
        }

        public static IScrollCaptureClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
