package android.hardware.biometrics;

import android.hardware.biometrics.IBiometricAuthenticator;
import android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback;
import android.hardware.biometrics.IBiometricServiceReceiver;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IBiometricService extends IInterface {

    public static class Default implements IBiometricService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void authenticate(IBinder iBinder, long j, int i, IBiometricServiceReceiver iBiometricServiceReceiver, String str, Bundle bundle, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public int canAuthenticate(String str, int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void cancelAuthentication(IBinder iBinder, String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public long[] getAuthenticatorIds(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public boolean hasEnrolledBiometrics(int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void onReadyForAuthentication(int i, boolean z, int i2) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void registerAuthenticator(int i, int i2, int i3, IBiometricAuthenticator iBiometricAuthenticator) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback iBiometricEnabledOnKeyguardCallback, int i) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void resetLockout(byte[] bArr) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricService
        public void setActiveUser(int i) throws RemoteException {
        }
    }

    void authenticate(IBinder iBinder, long j, int i, IBiometricServiceReceiver iBiometricServiceReceiver, String str, Bundle bundle, int i2, int i3, int i4) throws RemoteException;

    int canAuthenticate(String str, int i, int i2, int i3) throws RemoteException;

    void cancelAuthentication(IBinder iBinder, String str, int i, int i2, int i3) throws RemoteException;

    long[] getAuthenticatorIds(int i) throws RemoteException;

    boolean hasEnrolledBiometrics(int i, String str) throws RemoteException;

    void onReadyForAuthentication(int i, boolean z, int i2) throws RemoteException;

    void registerAuthenticator(int i, int i2, int i3, IBiometricAuthenticator iBiometricAuthenticator) throws RemoteException;

    void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback iBiometricEnabledOnKeyguardCallback, int i) throws RemoteException;

    void resetLockout(byte[] bArr) throws RemoteException;

    void setActiveUser(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IBiometricService {
        private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricService";
        static final int TRANSACTION_authenticate = 1;
        static final int TRANSACTION_canAuthenticate = 3;
        static final int TRANSACTION_cancelAuthentication = 2;
        static final int TRANSACTION_getAuthenticatorIds = 10;
        static final int TRANSACTION_hasEnrolledBiometrics = 4;
        static final int TRANSACTION_onReadyForAuthentication = 8;
        static final int TRANSACTION_registerAuthenticator = 5;
        static final int TRANSACTION_registerEnabledOnKeyguardCallback = 6;
        static final int TRANSACTION_resetLockout = 9;
        static final int TRANSACTION_setActiveUser = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "authenticate";
                case 2:
                    return "cancelAuthentication";
                case 3:
                    return "canAuthenticate";
                case 4:
                    return "hasEnrolledBiometrics";
                case 5:
                    return "registerAuthenticator";
                case 6:
                    return "registerEnabledOnKeyguardCallback";
                case 7:
                    return "setActiveUser";
                case 8:
                    return "onReadyForAuthentication";
                case 9:
                    return "resetLockout";
                case 10:
                    return "getAuthenticatorIds";
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

        public static IBiometricService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBiometricService)) {
                return (IBiometricService) iInterfaceQueryLocalInterface;
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
                    authenticate(parcel.readStrongBinder(), parcel.readLong(), parcel.readInt(), IBiometricServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelAuthentication(parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCanAuthenticate = canAuthenticate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCanAuthenticate);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasEnrolledBiometrics = hasEnrolledBiometrics(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasEnrolledBiometrics ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerAuthenticator(parcel.readInt(), parcel.readInt(), parcel.readInt(), IBiometricAuthenticator.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setActiveUser(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReadyForAuthentication(parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetLockout(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    long[] authenticatorIds = getAuthenticatorIds(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLongArray(authenticatorIds);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBiometricService {
            public static IBiometricService sDefaultImpl;
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

            @Override // android.hardware.biometrics.IBiometricService
            public void authenticate(IBinder iBinder, long j, int i, IBiometricServiceReceiver iBiometricServiceReceiver, String str, Bundle bundle, int i2, int i3, int i4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBiometricServiceReceiver != null ? iBiometricServiceReceiver.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().authenticate(iBinder, j, i, iBiometricServiceReceiver, str, bundle, i2, i3, i4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void cancelAuthentication(IBinder iBinder, String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelAuthentication(iBinder, str, i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public int canAuthenticate(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAuthenticate(str, i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public boolean hasEnrolledBiometrics(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasEnrolledBiometrics(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void registerAuthenticator(int i, int i2, int i3, IBiometricAuthenticator iBiometricAuthenticator) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStrongBinder(iBiometricAuthenticator != null ? iBiometricAuthenticator.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerAuthenticator(i, i2, i3, iBiometricAuthenticator);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback iBiometricEnabledOnKeyguardCallback, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBiometricEnabledOnKeyguardCallback != null ? iBiometricEnabledOnKeyguardCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(iBiometricEnabledOnKeyguardCallback, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void setActiveUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setActiveUser(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void onReadyForAuthentication(int i, boolean z, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReadyForAuthentication(i, z, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public void resetLockout(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetLockout(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricService
            public long[] getAuthenticatorIds(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthenticatorIds(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createLongArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBiometricService iBiometricService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBiometricService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBiometricService;
            return true;
        }

        public static IBiometricService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
