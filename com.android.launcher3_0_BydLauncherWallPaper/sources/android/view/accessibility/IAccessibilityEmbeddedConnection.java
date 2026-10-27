package android.view.accessibility;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IAccessibilityEmbeddedConnection extends IInterface {

    public static class Default implements IAccessibilityEmbeddedConnection {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
        public IBinder associateEmbeddedHierarchy(IBinder iBinder, int i) throws RemoteException {
            return null;
        }

        @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
        public void disassociateEmbeddedHierarchy() throws RemoteException {
        }

        @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
        public void setScreenMatrix(float[] fArr) throws RemoteException {
        }
    }

    IBinder associateEmbeddedHierarchy(IBinder iBinder, int i) throws RemoteException;

    void disassociateEmbeddedHierarchy() throws RemoteException;

    void setScreenMatrix(float[] fArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IAccessibilityEmbeddedConnection {
        private static final String DESCRIPTOR = "android.view.accessibility.IAccessibilityEmbeddedConnection";
        static final int TRANSACTION_associateEmbeddedHierarchy = 1;
        static final int TRANSACTION_disassociateEmbeddedHierarchy = 2;
        static final int TRANSACTION_setScreenMatrix = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "associateEmbeddedHierarchy";
            }
            if (i == 2) {
                return "disassociateEmbeddedHierarchy";
            }
            if (i != 3) {
                return null;
            }
            return "setScreenMatrix";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccessibilityEmbeddedConnection asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAccessibilityEmbeddedConnection)) {
                return (IAccessibilityEmbeddedConnection) iInterfaceQueryLocalInterface;
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
                IBinder iBinderAssociateEmbeddedHierarchy = associateEmbeddedHierarchy(parcel.readStrongBinder(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(iBinderAssociateEmbeddedHierarchy);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                disassociateEmbeddedHierarchy();
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                setScreenMatrix(parcel.createFloatArray());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAccessibilityEmbeddedConnection {
            public static IAccessibilityEmbeddedConnection sDefaultImpl;
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

            @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
            public IBinder associateEmbeddedHierarchy(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().associateEmbeddedHierarchy(iBinder, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
            public void disassociateEmbeddedHierarchy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disassociateEmbeddedHierarchy();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
            public void setScreenMatrix(float[] fArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloatArray(fArr);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setScreenMatrix(fArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAccessibilityEmbeddedConnection iAccessibilityEmbeddedConnection) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAccessibilityEmbeddedConnection == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAccessibilityEmbeddedConnection;
            return true;
        }

        public static IAccessibilityEmbeddedConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
