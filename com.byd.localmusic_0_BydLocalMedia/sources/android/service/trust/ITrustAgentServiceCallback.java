package android.service.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public interface ITrustAgentServiceCallback extends IInterface {

    public static class Default implements ITrustAgentServiceCallback {
        @Override // android.service.trust.ITrustAgentServiceCallback
        public void addEscrowToken(byte[] bArr, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void grantTrust(CharSequence charSequence, long j, int i) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void isEscrowTokenActive(long j, int i) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void onConfigureCompleted(boolean z, IBinder iBinder) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void removeEscrowToken(long j, int i) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void revokeTrust() throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void setManagingTrust(boolean z) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void showKeyguardErrorMessage(CharSequence charSequence) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentServiceCallback
        public void unlockUserWithToken(long j, byte[] bArr, int i) throws RemoteException {
        }
    }

    void addEscrowToken(byte[] bArr, int i) throws RemoteException;

    void grantTrust(CharSequence charSequence, long j, int i) throws RemoteException;

    void isEscrowTokenActive(long j, int i) throws RemoteException;

    void onConfigureCompleted(boolean z, IBinder iBinder) throws RemoteException;

    void removeEscrowToken(long j, int i) throws RemoteException;

    void revokeTrust() throws RemoteException;

    void setManagingTrust(boolean z) throws RemoteException;

    void showKeyguardErrorMessage(CharSequence charSequence) throws RemoteException;

    void unlockUserWithToken(long j, byte[] bArr, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrustAgentServiceCallback {
        private static final String DESCRIPTOR = "android.service.trust.ITrustAgentServiceCallback";
        static final int TRANSACTION_addEscrowToken = 5;
        static final int TRANSACTION_grantTrust = 1;
        static final int TRANSACTION_isEscrowTokenActive = 6;
        static final int TRANSACTION_onConfigureCompleted = 4;
        static final int TRANSACTION_removeEscrowToken = 7;
        static final int TRANSACTION_revokeTrust = 2;
        static final int TRANSACTION_setManagingTrust = 3;
        static final int TRANSACTION_showKeyguardErrorMessage = 9;
        static final int TRANSACTION_unlockUserWithToken = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "grantTrust";
                case 2:
                    return "revokeTrust";
                case 3:
                    return "setManagingTrust";
                case 4:
                    return "onConfigureCompleted";
                case 5:
                    return "addEscrowToken";
                case 6:
                    return "isEscrowTokenActive";
                case 7:
                    return "removeEscrowToken";
                case 8:
                    return "unlockUserWithToken";
                case 9:
                    return "showKeyguardErrorMessage";
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

        public static ITrustAgentServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITrustAgentServiceCallback)) {
                return (ITrustAgentServiceCallback) iInterfaceQueryLocalInterface;
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
                    grantTrust(parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeTrust();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setManagingTrust(parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConfigureCompleted(parcel.readInt() != 0, parcel.readStrongBinder());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    addEscrowToken(parcel.createByteArray(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    isEscrowTokenActive(parcel.readLong(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeEscrowToken(parcel.readLong(), parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    unlockUserWithToken(parcel.readLong(), parcel.createByteArray(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    showKeyguardErrorMessage(parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITrustAgentServiceCallback {
            public static ITrustAgentServiceCallback sDefaultImpl;
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

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void grantTrust(CharSequence charSequence, long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().grantTrust(charSequence, j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void revokeTrust() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().revokeTrust();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void setManagingTrust(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setManagingTrust(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void onConfigureCompleted(boolean z, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConfigureCompleted(z, iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void addEscrowToken(byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addEscrowToken(bArr, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void isEscrowTokenActive(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().isEscrowTokenActive(j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void removeEscrowToken(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeEscrowToken(j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void unlockUserWithToken(long j, byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unlockUserWithToken(j, bArr, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentServiceCallback
            public void showKeyguardErrorMessage(CharSequence charSequence) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showKeyguardErrorMessage(charSequence);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITrustAgentServiceCallback iTrustAgentServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTrustAgentServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTrustAgentServiceCallback;
            return true;
        }

        public static ITrustAgentServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
