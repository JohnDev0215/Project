package com.android.internal.inputmethod;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.InputChannel;
import com.android.internal.inputmethod.IMultiClientInputMethodSession;
import com.android.internal.view.IInputMethodSession;

/* JADX INFO: loaded from: classes4.dex */
public interface IMultiClientInputMethodPrivilegedOperations extends IInterface {

    public static class Default implements IMultiClientInputMethodPrivilegedOperations {
        @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
        public void acceptClient(int i, IInputMethodSession iInputMethodSession, IMultiClientInputMethodSession iMultiClientInputMethodSession, InputChannel inputChannel) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
        public IBinder createInputMethodWindowToken(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
        public void deleteInputMethodWindowToken(IBinder iBinder) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
        public boolean isUidAllowedOnDisplay(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
        public void reportImeWindowTarget(int i, int i2, IBinder iBinder) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
        public void setActive(int i, boolean z) throws RemoteException {
        }
    }

    void acceptClient(int i, IInputMethodSession iInputMethodSession, IMultiClientInputMethodSession iMultiClientInputMethodSession, InputChannel inputChannel) throws RemoteException;

    IBinder createInputMethodWindowToken(int i) throws RemoteException;

    void deleteInputMethodWindowToken(IBinder iBinder) throws RemoteException;

    boolean isUidAllowedOnDisplay(int i, int i2) throws RemoteException;

    void reportImeWindowTarget(int i, int i2, IBinder iBinder) throws RemoteException;

    void setActive(int i, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IMultiClientInputMethodPrivilegedOperations {
        private static final String DESCRIPTOR = "com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations";
        static final int TRANSACTION_acceptClient = 3;
        static final int TRANSACTION_createInputMethodWindowToken = 1;
        static final int TRANSACTION_deleteInputMethodWindowToken = 2;
        static final int TRANSACTION_isUidAllowedOnDisplay = 5;
        static final int TRANSACTION_reportImeWindowTarget = 4;
        static final int TRANSACTION_setActive = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "createInputMethodWindowToken";
                case 2:
                    return "deleteInputMethodWindowToken";
                case 3:
                    return "acceptClient";
                case 4:
                    return "reportImeWindowTarget";
                case 5:
                    return "isUidAllowedOnDisplay";
                case 6:
                    return "setActive";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMultiClientInputMethodPrivilegedOperations asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMultiClientInputMethodPrivilegedOperations)) {
                return (IMultiClientInputMethodPrivilegedOperations) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
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
                    IBinder iBinderCreateInputMethodWindowToken = createInputMethodWindowToken(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iBinderCreateInputMethodWindowToken);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteInputMethodWindowToken(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    acceptClient(parcel.readInt(), IInputMethodSession.Stub.asInterface(parcel.readStrongBinder()), IMultiClientInputMethodSession.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? InputChannel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportImeWindowTarget(parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUidAllowedOnDisplay = isUidAllowedOnDisplay(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUidAllowedOnDisplay ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setActive(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMultiClientInputMethodPrivilegedOperations {
            public static IMultiClientInputMethodPrivilegedOperations sDefaultImpl;
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

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
            public IBinder createInputMethodWindowToken(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createInputMethodWindowToken(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
            public void deleteInputMethodWindowToken(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteInputMethodWindowToken(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
            public void acceptClient(int i, IInputMethodSession iInputMethodSession, IMultiClientInputMethodSession iMultiClientInputMethodSession, InputChannel inputChannel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iInputMethodSession != null ? iInputMethodSession.asBinder() : null);
                    parcelObtain.writeStrongBinder(iMultiClientInputMethodSession != null ? iMultiClientInputMethodSession.asBinder() : null);
                    if (inputChannel != null) {
                        parcelObtain.writeInt(1);
                        inputChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acceptClient(i, iInputMethodSession, iMultiClientInputMethodSession, inputChannel);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
            public void reportImeWindowTarget(int i, int i2, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportImeWindowTarget(i, i2, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
            public boolean isUidAllowedOnDisplay(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUidAllowedOnDisplay(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations
            public void setActive(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setActive(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMultiClientInputMethodPrivilegedOperations iMultiClientInputMethodPrivilegedOperations) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMultiClientInputMethodPrivilegedOperations == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMultiClientInputMethodPrivilegedOperations;
            return true;
        }

        public static IMultiClientInputMethodPrivilegedOperations getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
