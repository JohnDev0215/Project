package android.view.accessibility;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IWindowMagnificationConnectionCallback extends IInterface {

    public static class Default implements IWindowMagnificationConnectionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
        public void onChangeMagnificationMode(int i, int i2) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
        public void onWindowMagnifierBoundsChanged(int i, Rect rect) throws RemoteException {
        }
    }

    void onChangeMagnificationMode(int i, int i2) throws RemoteException;

    void onWindowMagnifierBoundsChanged(int i, Rect rect) throws RemoteException;

    public static abstract class Stub extends Binder implements IWindowMagnificationConnectionCallback {
        private static final String DESCRIPTOR = "android.view.accessibility.IWindowMagnificationConnectionCallback";
        static final int TRANSACTION_onChangeMagnificationMode = 2;
        static final int TRANSACTION_onWindowMagnifierBoundsChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onWindowMagnifierBoundsChanged";
            }
            if (i != 2) {
                return null;
            }
            return "onChangeMagnificationMode";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowMagnificationConnectionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindowMagnificationConnectionCallback)) {
                return (IWindowMagnificationConnectionCallback) iInterfaceQueryLocalInterface;
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
                onWindowMagnifierBoundsChanged(parcel.readInt(), parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onChangeMagnificationMode(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IWindowMagnificationConnectionCallback {
            public static IWindowMagnificationConnectionCallback sDefaultImpl;
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

            @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
            public void onWindowMagnifierBoundsChanged(int i, Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWindowMagnifierBoundsChanged(i, rect);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
            public void onChangeMagnificationMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChangeMagnificationMode(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowMagnificationConnectionCallback iWindowMagnificationConnectionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindowMagnificationConnectionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindowMagnificationConnectionCallback;
            return true;
        }

        public static IWindowMagnificationConnectionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
