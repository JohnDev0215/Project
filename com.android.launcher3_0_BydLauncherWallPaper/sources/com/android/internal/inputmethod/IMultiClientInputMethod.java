package com.android.internal.inputmethod;

import android.content.ContentResolver;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations;

/* JADX INFO: loaded from: classes4.dex */
public interface IMultiClientInputMethod extends IInterface {

    public static class Default implements IMultiClientInputMethod {
        @Override // com.android.internal.inputmethod.IMultiClientInputMethod
        public void addClient(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethod
        public void initialize(IMultiClientInputMethodPrivilegedOperations iMultiClientInputMethodPrivilegedOperations) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethod
        public void removeClient(int i) throws RemoteException {
        }
    }

    void addClient(int i, int i2, int i3, int i4) throws RemoteException;

    void initialize(IMultiClientInputMethodPrivilegedOperations iMultiClientInputMethodPrivilegedOperations) throws RemoteException;

    void removeClient(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMultiClientInputMethod {
        private static final String DESCRIPTOR = "com.android.internal.inputmethod.IMultiClientInputMethod";
        static final int TRANSACTION_addClient = 2;
        static final int TRANSACTION_initialize = 1;
        static final int TRANSACTION_removeClient = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return ContentResolver.SYNC_EXTRAS_INITIALIZE;
            }
            if (i == 2) {
                return "addClient";
            }
            if (i != 3) {
                return null;
            }
            return "removeClient";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMultiClientInputMethod asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMultiClientInputMethod)) {
                return (IMultiClientInputMethod) iInterfaceQueryLocalInterface;
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
                initialize(IMultiClientInputMethodPrivilegedOperations.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                addClient(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                removeClient(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMultiClientInputMethod {
            public static IMultiClientInputMethod sDefaultImpl;
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

            @Override // com.android.internal.inputmethod.IMultiClientInputMethod
            public void initialize(IMultiClientInputMethodPrivilegedOperations iMultiClientInputMethodPrivilegedOperations) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMultiClientInputMethodPrivilegedOperations != null ? iMultiClientInputMethodPrivilegedOperations.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().initialize(iMultiClientInputMethodPrivilegedOperations);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethod
            public void addClient(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addClient(i, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethod
            public void removeClient(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeClient(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMultiClientInputMethod iMultiClientInputMethod) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMultiClientInputMethod == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMultiClientInputMethod;
            return true;
        }

        public static IMultiClientInputMethod getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
