package android.app.trust;

import android.app.trust.ITrustListener;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ITrustManager extends IInterface {

    public static class Default implements ITrustManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.trust.ITrustManager
        public void clearAllBiometricRecognized(BiometricSourceType biometricSourceType) throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public boolean isDeviceLocked(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.trust.ITrustManager
        public boolean isDeviceSecure(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.trust.ITrustManager
        public boolean isTrustUsuallyManaged(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.trust.ITrustManager
        public void registerTrustListener(ITrustListener iTrustListener) throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public void reportEnabledTrustAgentsChanged(int i) throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public void reportKeyguardShowingChanged() throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public void reportUnlockAttempt(boolean z, int i) throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public void reportUnlockLockout(int i, int i2) throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public void setDeviceLockedForUser(int i, boolean z) throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public void unlockedByBiometricForUser(int i, BiometricSourceType biometricSourceType) throws RemoteException {
        }

        @Override // android.app.trust.ITrustManager
        public void unregisterTrustListener(ITrustListener iTrustListener) throws RemoteException {
        }
    }

    void clearAllBiometricRecognized(BiometricSourceType biometricSourceType) throws RemoteException;

    boolean isDeviceLocked(int i) throws RemoteException;

    boolean isDeviceSecure(int i) throws RemoteException;

    boolean isTrustUsuallyManaged(int i) throws RemoteException;

    void registerTrustListener(ITrustListener iTrustListener) throws RemoteException;

    void reportEnabledTrustAgentsChanged(int i) throws RemoteException;

    void reportKeyguardShowingChanged() throws RemoteException;

    void reportUnlockAttempt(boolean z, int i) throws RemoteException;

    void reportUnlockLockout(int i, int i2) throws RemoteException;

    void setDeviceLockedForUser(int i, boolean z) throws RemoteException;

    void unlockedByBiometricForUser(int i, BiometricSourceType biometricSourceType) throws RemoteException;

    void unregisterTrustListener(ITrustListener iTrustListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrustManager {
        private static final String DESCRIPTOR = "android.app.trust.ITrustManager";
        static final int TRANSACTION_clearAllBiometricRecognized = 12;
        static final int TRANSACTION_isDeviceLocked = 8;
        static final int TRANSACTION_isDeviceSecure = 9;
        static final int TRANSACTION_isTrustUsuallyManaged = 10;
        static final int TRANSACTION_registerTrustListener = 4;
        static final int TRANSACTION_reportEnabledTrustAgentsChanged = 3;
        static final int TRANSACTION_reportKeyguardShowingChanged = 6;
        static final int TRANSACTION_reportUnlockAttempt = 1;
        static final int TRANSACTION_reportUnlockLockout = 2;
        static final int TRANSACTION_setDeviceLockedForUser = 7;
        static final int TRANSACTION_unlockedByBiometricForUser = 11;
        static final int TRANSACTION_unregisterTrustListener = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "reportUnlockAttempt";
                case 2:
                    return "reportUnlockLockout";
                case 3:
                    return "reportEnabledTrustAgentsChanged";
                case 4:
                    return "registerTrustListener";
                case 5:
                    return "unregisterTrustListener";
                case 6:
                    return "reportKeyguardShowingChanged";
                case 7:
                    return "setDeviceLockedForUser";
                case 8:
                    return "isDeviceLocked";
                case 9:
                    return "isDeviceSecure";
                case 10:
                    return "isTrustUsuallyManaged";
                case 11:
                    return "unlockedByBiometricForUser";
                case 12:
                    return "clearAllBiometricRecognized";
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

        public static ITrustManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITrustManager)) {
                return (ITrustManager) iInterfaceQueryLocalInterface;
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
                    reportUnlockAttempt(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportUnlockLockout(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportEnabledTrustAgentsChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerTrustListener(ITrustListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterTrustListener(ITrustListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportKeyguardShowingChanged();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDeviceLockedForUser(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDeviceLocked = isDeviceLocked(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDeviceLocked ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDeviceSecure = isDeviceSecure(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDeviceSecure ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsTrustUsuallyManaged = isTrustUsuallyManaged(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTrustUsuallyManaged ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    unlockedByBiometricForUser(parcel.readInt(), parcel.readInt() != 0 ? BiometricSourceType.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearAllBiometricRecognized(parcel.readInt() != 0 ? BiometricSourceType.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITrustManager {
            public static ITrustManager sDefaultImpl;
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

            @Override // android.app.trust.ITrustManager
            public void reportUnlockAttempt(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportUnlockAttempt(z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void reportUnlockLockout(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportUnlockLockout(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void reportEnabledTrustAgentsChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportEnabledTrustAgentsChanged(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void registerTrustListener(ITrustListener iTrustListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTrustListener != null ? iTrustListener.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTrustListener(iTrustListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void unregisterTrustListener(ITrustListener iTrustListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTrustListener != null ? iTrustListener.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterTrustListener(iTrustListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void reportKeyguardShowingChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportKeyguardShowingChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void setDeviceLockedForUser(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDeviceLockedForUser(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public boolean isDeviceLocked(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeviceLocked(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public boolean isDeviceSecure(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeviceSecure(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public boolean isTrustUsuallyManaged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTrustUsuallyManaged(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void unlockedByBiometricForUser(int i, BiometricSourceType biometricSourceType) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (biometricSourceType != null) {
                        parcelObtain.writeInt(1);
                        biometricSourceType.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unlockedByBiometricForUser(i, biometricSourceType);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.ITrustManager
            public void clearAllBiometricRecognized(BiometricSourceType biometricSourceType) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (biometricSourceType != null) {
                        parcelObtain.writeInt(1);
                        biometricSourceType.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearAllBiometricRecognized(biometricSourceType);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITrustManager iTrustManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTrustManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTrustManager;
            return true;
        }

        public static ITrustManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
