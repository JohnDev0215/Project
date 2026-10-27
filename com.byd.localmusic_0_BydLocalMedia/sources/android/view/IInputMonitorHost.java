package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IInputMonitorHost extends IInterface {

    public static class Default implements IInputMonitorHost {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IInputMonitorHost
        public void dispose() throws RemoteException {
        }

        @Override // android.view.IInputMonitorHost
        public void pilferPointers() throws RemoteException {
        }
    }

    void dispose() throws RemoteException;

    void pilferPointers() throws RemoteException;

    public static abstract class Stub extends Binder implements IInputMonitorHost {
        private static final String DESCRIPTOR = "android.view.IInputMonitorHost";
        static final int TRANSACTION_dispose = 2;
        static final int TRANSACTION_pilferPointers = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "pilferPointers";
            }
            if (i != 2) {
                return null;
            }
            return "dispose";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInputMonitorHost asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInputMonitorHost)) {
                return (IInputMonitorHost) iInterfaceQueryLocalInterface;
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
                pilferPointers();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                dispose();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInputMonitorHost {
            public static IInputMonitorHost sDefaultImpl;
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

            @Override // android.view.IInputMonitorHost
            public void pilferPointers() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().pilferPointers();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IInputMonitorHost
            public void dispose() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispose();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputMonitorHost iInputMonitorHost) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInputMonitorHost == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInputMonitorHost;
            return true;
        }

        public static IInputMonitorHost getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
