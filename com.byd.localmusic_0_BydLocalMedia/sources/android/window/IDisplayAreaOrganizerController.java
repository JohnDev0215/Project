package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.window.IDisplayAreaOrganizer;

/* JADX INFO: loaded from: classes3.dex */
public interface IDisplayAreaOrganizerController extends IInterface {

    public static class Default implements IDisplayAreaOrganizerController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.window.IDisplayAreaOrganizerController
        public void registerOrganizer(IDisplayAreaOrganizer iDisplayAreaOrganizer, int i) throws RemoteException {
        }

        @Override // android.window.IDisplayAreaOrganizerController
        public void unregisterOrganizer(IDisplayAreaOrganizer iDisplayAreaOrganizer) throws RemoteException {
        }
    }

    void registerOrganizer(IDisplayAreaOrganizer iDisplayAreaOrganizer, int i) throws RemoteException;

    void unregisterOrganizer(IDisplayAreaOrganizer iDisplayAreaOrganizer) throws RemoteException;

    public static abstract class Stub extends Binder implements IDisplayAreaOrganizerController {
        private static final String DESCRIPTOR = "android.window.IDisplayAreaOrganizerController";
        static final int TRANSACTION_registerOrganizer = 1;
        static final int TRANSACTION_unregisterOrganizer = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "registerOrganizer";
            }
            if (i != 2) {
                return null;
            }
            return "unregisterOrganizer";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDisplayAreaOrganizerController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDisplayAreaOrganizerController)) {
                return (IDisplayAreaOrganizerController) iInterfaceQueryLocalInterface;
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
                registerOrganizer(IDisplayAreaOrganizer.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            unregisterOrganizer(IDisplayAreaOrganizer.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IDisplayAreaOrganizerController {
            public static IDisplayAreaOrganizerController sDefaultImpl;
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

            @Override // android.window.IDisplayAreaOrganizerController
            public void registerOrganizer(IDisplayAreaOrganizer iDisplayAreaOrganizer, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayAreaOrganizer != null ? iDisplayAreaOrganizer.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerOrganizer(iDisplayAreaOrganizer, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.IDisplayAreaOrganizerController
            public void unregisterOrganizer(IDisplayAreaOrganizer iDisplayAreaOrganizer) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayAreaOrganizer != null ? iDisplayAreaOrganizer.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterOrganizer(iDisplayAreaOrganizer);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayAreaOrganizerController iDisplayAreaOrganizerController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDisplayAreaOrganizerController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDisplayAreaOrganizerController;
            return true;
        }

        public static IDisplayAreaOrganizerController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
