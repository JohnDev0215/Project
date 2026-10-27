package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IBiometricServiceReceiverInternal extends IInterface {

    public static class Default implements IBiometricServiceReceiverInternal {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onAcquired(int i, String str) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onAuthenticationFailed() throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onAuthenticationSucceeded(boolean z, byte[] bArr, boolean z2) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onDeviceCredentialPressed() throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onDialogDismissed(int i, byte[] bArr) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onError(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onSystemEvent(int i) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
        public void onTryAgainPressed() throws RemoteException {
        }
    }

    void onAcquired(int i, String str) throws RemoteException;

    void onAuthenticationFailed() throws RemoteException;

    void onAuthenticationSucceeded(boolean z, byte[] bArr, boolean z2) throws RemoteException;

    void onDeviceCredentialPressed() throws RemoteException;

    void onDialogDismissed(int i, byte[] bArr) throws RemoteException;

    void onError(int i, int i2, int i3, int i4) throws RemoteException;

    void onSystemEvent(int i) throws RemoteException;

    void onTryAgainPressed() throws RemoteException;

    public static abstract class Stub extends Binder implements IBiometricServiceReceiverInternal {
        private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricServiceReceiverInternal";
        static final int TRANSACTION_onAcquired = 4;
        static final int TRANSACTION_onAuthenticationFailed = 2;
        static final int TRANSACTION_onAuthenticationSucceeded = 1;
        static final int TRANSACTION_onDeviceCredentialPressed = 7;
        static final int TRANSACTION_onDialogDismissed = 5;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onSystemEvent = 8;
        static final int TRANSACTION_onTryAgainPressed = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onAuthenticationSucceeded";
                case 2:
                    return "onAuthenticationFailed";
                case 3:
                    return "onError";
                case 4:
                    return "onAcquired";
                case 5:
                    return "onDialogDismissed";
                case 6:
                    return "onTryAgainPressed";
                case 7:
                    return "onDeviceCredentialPressed";
                case 8:
                    return "onSystemEvent";
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

        public static IBiometricServiceReceiverInternal asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBiometricServiceReceiverInternal)) {
                return (IBiometricServiceReceiverInternal) iInterfaceQueryLocalInterface;
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
                    onAuthenticationSucceeded(parcel.readInt() != 0, parcel.createByteArray(), parcel.readInt() != 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAuthenticationFailed();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onError(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAcquired(parcel.readInt(), parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDialogDismissed(parcel.readInt(), parcel.createByteArray());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTryAgainPressed();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDeviceCredentialPressed();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSystemEvent(parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBiometricServiceReceiverInternal {
            public static IBiometricServiceReceiverInternal sDefaultImpl;
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

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onAuthenticationSucceeded(boolean z, byte[] bArr, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAuthenticationSucceeded(z, bArr, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onAuthenticationFailed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAuthenticationFailed();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onError(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onAcquired(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAcquired(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onDialogDismissed(int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDialogDismissed(i, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onTryAgainPressed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTryAgainPressed();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onDeviceCredentialPressed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceCredentialPressed();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricServiceReceiverInternal
            public void onSystemEvent(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSystemEvent(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBiometricServiceReceiverInternal == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBiometricServiceReceiverInternal;
            return true;
        }

        public static IBiometricServiceReceiverInternal getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
