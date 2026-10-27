package android.view;

import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDisplayWindowListener extends IInterface {

    public static class Default implements IDisplayWindowListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IDisplayWindowListener
        public void onDisplayAdded(int i) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onDisplayConfigurationChanged(int i, Configuration configuration) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onDisplayRemoved(int i) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onFixedRotationFinished(int i) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onFixedRotationStarted(int i, int i2) throws RemoteException {
        }
    }

    void onDisplayAdded(int i) throws RemoteException;

    void onDisplayConfigurationChanged(int i, Configuration configuration) throws RemoteException;

    void onDisplayRemoved(int i) throws RemoteException;

    void onFixedRotationFinished(int i) throws RemoteException;

    void onFixedRotationStarted(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDisplayWindowListener {
        private static final String DESCRIPTOR = "android.view.IDisplayWindowListener";
        static final int TRANSACTION_onDisplayAdded = 1;
        static final int TRANSACTION_onDisplayConfigurationChanged = 2;
        static final int TRANSACTION_onDisplayRemoved = 3;
        static final int TRANSACTION_onFixedRotationFinished = 5;
        static final int TRANSACTION_onFixedRotationStarted = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onDisplayAdded";
            }
            if (i == 2) {
                return "onDisplayConfigurationChanged";
            }
            if (i == 3) {
                return "onDisplayRemoved";
            }
            if (i == 4) {
                return "onFixedRotationStarted";
            }
            if (i != 5) {
                return null;
            }
            return "onFixedRotationFinished";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDisplayWindowListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDisplayWindowListener)) {
                return (IDisplayWindowListener) iInterfaceQueryLocalInterface;
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
                onDisplayAdded(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onDisplayConfigurationChanged(parcel.readInt(), parcel.readInt() != 0 ? Configuration.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onDisplayRemoved(parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onFixedRotationStarted(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onFixedRotationFinished(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDisplayWindowListener {
            public static IDisplayWindowListener sDefaultImpl;
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

            @Override // android.view.IDisplayWindowListener
            public void onDisplayAdded(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayAdded(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onDisplayConfigurationChanged(int i, Configuration configuration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (configuration != null) {
                        parcelObtain.writeInt(1);
                        configuration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayConfigurationChanged(i, configuration);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onDisplayRemoved(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayRemoved(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onFixedRotationStarted(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFixedRotationStarted(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onFixedRotationFinished(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFixedRotationFinished(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayWindowListener iDisplayWindowListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDisplayWindowListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDisplayWindowListener;
            return true;
        }

        public static IDisplayWindowListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
