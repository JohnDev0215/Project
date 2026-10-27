package android.service.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.trust.ITrustAgentServiceCallback;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ITrustAgentService extends IInterface {

    public static class Default implements ITrustAgentService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.trust.ITrustAgentService
        public void onConfigure(List<PersistableBundle> list, IBinder iBinder) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onDeviceLocked() throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onDeviceUnlocked() throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onEscrowTokenAdded(byte[] bArr, long j, UserHandle userHandle) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onEscrowTokenRemoved(long j, boolean z) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onTokenStateReceived(long j, int i) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onTrustTimeout() throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onUnlockAttempt(boolean z) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onUnlockLockout(int i) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void setCallback(ITrustAgentServiceCallback iTrustAgentServiceCallback) throws RemoteException {
        }
    }

    void onConfigure(List<PersistableBundle> list, IBinder iBinder) throws RemoteException;

    void onDeviceLocked() throws RemoteException;

    void onDeviceUnlocked() throws RemoteException;

    void onEscrowTokenAdded(byte[] bArr, long j, UserHandle userHandle) throws RemoteException;

    void onEscrowTokenRemoved(long j, boolean z) throws RemoteException;

    void onTokenStateReceived(long j, int i) throws RemoteException;

    void onTrustTimeout() throws RemoteException;

    void onUnlockAttempt(boolean z) throws RemoteException;

    void onUnlockLockout(int i) throws RemoteException;

    void setCallback(ITrustAgentServiceCallback iTrustAgentServiceCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrustAgentService {
        private static final String DESCRIPTOR = "android.service.trust.ITrustAgentService";
        static final int TRANSACTION_onConfigure = 6;
        static final int TRANSACTION_onDeviceLocked = 4;
        static final int TRANSACTION_onDeviceUnlocked = 5;
        static final int TRANSACTION_onEscrowTokenAdded = 8;
        static final int TRANSACTION_onEscrowTokenRemoved = 10;
        static final int TRANSACTION_onTokenStateReceived = 9;
        static final int TRANSACTION_onTrustTimeout = 3;
        static final int TRANSACTION_onUnlockAttempt = 1;
        static final int TRANSACTION_onUnlockLockout = 2;
        static final int TRANSACTION_setCallback = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onUnlockAttempt";
                case 2:
                    return "onUnlockLockout";
                case 3:
                    return "onTrustTimeout";
                case 4:
                    return "onDeviceLocked";
                case 5:
                    return "onDeviceUnlocked";
                case 6:
                    return "onConfigure";
                case 7:
                    return "setCallback";
                case 8:
                    return "onEscrowTokenAdded";
                case 9:
                    return "onTokenStateReceived";
                case 10:
                    return "onEscrowTokenRemoved";
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

        public static ITrustAgentService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITrustAgentService)) {
                return (ITrustAgentService) iInterfaceQueryLocalInterface;
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
                    onUnlockAttempt(parcel.readInt() != 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUnlockLockout(parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTrustTimeout();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDeviceLocked();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDeviceUnlocked();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConfigure(parcel.createTypedArrayList(PersistableBundle.CREATOR), parcel.readStrongBinder());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCallback(ITrustAgentServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onEscrowTokenAdded(parcel.createByteArray(), parcel.readLong(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTokenStateReceived(parcel.readLong(), parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onEscrowTokenRemoved(parcel.readLong(), parcel.readInt() != 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITrustAgentService {
            public static ITrustAgentService sDefaultImpl;
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

            @Override // android.service.trust.ITrustAgentService
            public void onUnlockAttempt(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUnlockAttempt(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onUnlockLockout(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUnlockLockout(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onTrustTimeout() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTrustTimeout();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onDeviceLocked() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceLocked();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onDeviceUnlocked() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceUnlocked();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onConfigure(List<PersistableBundle> list, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConfigure(list, iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void setCallback(ITrustAgentServiceCallback iTrustAgentServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTrustAgentServiceCallback != null ? iTrustAgentServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setCallback(iTrustAgentServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onEscrowTokenAdded(byte[] bArr, long j, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeLong(j);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEscrowTokenAdded(bArr, j, userHandle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onTokenStateReceived(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTokenStateReceived(j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onEscrowTokenRemoved(long j, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEscrowTokenRemoved(j, z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITrustAgentService iTrustAgentService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTrustAgentService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTrustAgentService;
            return true;
        }

        public static ITrustAgentService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
