package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.ThreadedRenderer;

/* JADX INFO: loaded from: classes.dex */
public interface ITransientNotification extends IInterface {

    public static class Default implements ITransientNotification {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.ITransientNotification
        public void hide() throws RemoteException {
        }

        @Override // android.app.ITransientNotification
        public void show(IBinder iBinder) throws RemoteException {
        }
    }

    void hide() throws RemoteException;

    void show(IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements ITransientNotification {
        private static final String DESCRIPTOR = "android.app.ITransientNotification";
        static final int TRANSACTION_hide = 2;
        static final int TRANSACTION_show = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return ThreadedRenderer.OVERDRAW_PROPERTY_SHOW;
            }
            if (i != 2) {
                return null;
            }
            return "hide";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITransientNotification asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITransientNotification)) {
                return (ITransientNotification) iInterfaceQueryLocalInterface;
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
                show(parcel.readStrongBinder());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                hide();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ITransientNotification {
            public static ITransientNotification sDefaultImpl;
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

            @Override // android.app.ITransientNotification
            public void show(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().show(iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ITransientNotification
            public void hide() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hide();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITransientNotification iTransientNotification) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTransientNotification == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTransientNotification;
            return true;
        }

        public static ITransientNotification getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
