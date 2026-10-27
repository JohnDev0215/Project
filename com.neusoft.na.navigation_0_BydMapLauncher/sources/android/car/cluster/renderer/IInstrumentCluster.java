package android.car.cluster.renderer;

import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public interface IInstrumentCluster extends IInterface {

    public static class Default implements IInstrumentCluster {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public IInstrumentClusterNavigation getNavigationService() throws RemoteException {
            return null;
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void onKeyEvent(KeyEvent keyEvent) throws RemoteException {
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void setNavigationContextOwner(int i, int i2) throws RemoteException {
        }
    }

    IInstrumentClusterNavigation getNavigationService() throws RemoteException;

    void onKeyEvent(KeyEvent keyEvent) throws RemoteException;

    void setNavigationContextOwner(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IInstrumentCluster {
        private static final String DESCRIPTOR = "android.car.cluster.renderer.IInstrumentCluster";
        static final int TRANSACTION_getNavigationService = 1;
        static final int TRANSACTION_onKeyEvent = 3;
        static final int TRANSACTION_setNavigationContextOwner = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstrumentCluster asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInstrumentCluster)) {
                return (IInstrumentCluster) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                IInstrumentClusterNavigation navigationService = getNavigationService();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(navigationService != null ? navigationService.asBinder() : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setNavigationContextOwner(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onKeyEvent(parcel.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInstrumentCluster {
            public static IInstrumentCluster sDefaultImpl;
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

            @Override // android.car.cluster.renderer.IInstrumentCluster
            public IInstrumentClusterNavigation getNavigationService() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNavigationService();
                    }
                    parcelObtain2.readException();
                    return IInstrumentClusterNavigation.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentCluster
            public void setNavigationContextOwner(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setNavigationContextOwner(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentCluster
            public void onKeyEvent(KeyEvent keyEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keyEvent != null) {
                        parcelObtain.writeInt(1);
                        keyEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onKeyEvent(keyEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInstrumentCluster iInstrumentCluster) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInstrumentCluster == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInstrumentCluster;
            return true;
        }

        public static IInstrumentCluster getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
