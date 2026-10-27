package android.car;

import android.car.IAppFocusListener;
import android.car.IAppFocusOwnershipCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAppFocus extends IInterface {

    public static class Default implements IAppFocus {
        @Override // android.car.IAppFocus
        public void abandonAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.IAppFocus
        public int[] getActiveAppTypes() throws RemoteException {
            return null;
        }

        @Override // android.car.IAppFocus
        public boolean isOwningFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException {
            return false;
        }

        @Override // android.car.IAppFocus
        public void registerFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException {
        }

        @Override // android.car.IAppFocus
        public int requestAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException {
            return 0;
        }

        @Override // android.car.IAppFocus
        public void unregisterFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException {
        }
    }

    void abandonAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException;

    int[] getActiveAppTypes() throws RemoteException;

    boolean isOwningFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException;

    void registerFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException;

    int requestAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException;

    void unregisterFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppFocus {
        private static final String DESCRIPTOR = "android.car.IAppFocus";
        static final int TRANSACTION_abandonAppFocus = 6;
        static final int TRANSACTION_getActiveAppTypes = 3;
        static final int TRANSACTION_isOwningFocus = 4;
        static final int TRANSACTION_registerFocusListener = 1;
        static final int TRANSACTION_requestAppFocus = 5;
        static final int TRANSACTION_unregisterFocusListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppFocus asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppFocus)) {
                return (IAppFocus) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerFocusListener(IAppFocusListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterFocusListener(IAppFocusListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] activeAppTypes = getActiveAppTypes();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(activeAppTypes);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsOwningFocus = isOwningFocus(IAppFocusOwnershipCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOwningFocus ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRequestAppFocus = requestAppFocus(IAppFocusOwnershipCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRequestAppFocus);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    abandonAppFocus(IAppFocusOwnershipCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAppFocus {
            public static IAppFocus sDefaultImpl;
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

            @Override // android.car.IAppFocus
            public void registerFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppFocusListener != null ? iAppFocusListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerFocusListener(iAppFocusListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public void unregisterFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppFocusListener != null ? iAppFocusListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterFocusListener(iAppFocusListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public int[] getActiveAppTypes() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveAppTypes();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public boolean isOwningFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppFocusOwnershipCallback != null ? iAppFocusOwnershipCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOwningFocus(iAppFocusOwnershipCallback, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public int requestAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppFocusOwnershipCallback != null ? iAppFocusOwnershipCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestAppFocus(iAppFocusOwnershipCallback, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public void abandonAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppFocusOwnershipCallback != null ? iAppFocusOwnershipCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abandonAppFocus(iAppFocusOwnershipCallback, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppFocus iAppFocus) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppFocus == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppFocus;
            return true;
        }

        public static IAppFocus getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
