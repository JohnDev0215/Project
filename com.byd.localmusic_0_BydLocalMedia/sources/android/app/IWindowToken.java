package android.app;

import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IWindowToken extends IInterface {

    public static class Default implements IWindowToken {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IWindowToken
        public void onConfigurationChanged(Configuration configuration, int i) throws RemoteException {
        }

        @Override // android.app.IWindowToken
        public void onWindowTokenRemoved() throws RemoteException {
        }
    }

    void onConfigurationChanged(Configuration configuration, int i) throws RemoteException;

    void onWindowTokenRemoved() throws RemoteException;

    public static abstract class Stub extends Binder implements IWindowToken {
        private static final String DESCRIPTOR = "android.app.IWindowToken";
        static final int TRANSACTION_onConfigurationChanged = 1;
        static final int TRANSACTION_onWindowTokenRemoved = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onConfigurationChanged";
            }
            if (i != 2) {
                return null;
            }
            return "onWindowTokenRemoved";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindowToken)) {
                return (IWindowToken) iInterfaceQueryLocalInterface;
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
                onConfigurationChanged(parcel.readInt() != 0 ? Configuration.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onWindowTokenRemoved();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IWindowToken {
            public static IWindowToken sDefaultImpl;
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

            @Override // android.app.IWindowToken
            public void onConfigurationChanged(Configuration configuration, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (configuration != null) {
                        parcelObtain.writeInt(1);
                        configuration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConfigurationChanged(configuration, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IWindowToken
            public void onWindowTokenRemoved() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWindowTokenRemoved();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowToken iWindowToken) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindowToken == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindowToken;
            return true;
        }

        public static IWindowToken getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
