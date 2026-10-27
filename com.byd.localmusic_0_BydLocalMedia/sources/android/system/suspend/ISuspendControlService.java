package android.system.suspend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.system.suspend.ISuspendCallback;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
public interface ISuspendControlService extends IInterface {

    public static class Default implements ISuspendControlService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.system.suspend.ISuspendControlService
        public boolean enableAutosuspend() throws RemoteException {
            return false;
        }

        @Override // android.system.suspend.ISuspendControlService
        public boolean forceSuspend() throws RemoteException {
            return false;
        }

        @Override // android.system.suspend.ISuspendControlService
        public WakeLockInfo[] getWakeLockStats() throws RemoteException {
            return null;
        }

        @Override // android.system.suspend.ISuspendControlService
        public boolean registerCallback(ISuspendCallback iSuspendCallback) throws RemoteException {
            return false;
        }
    }

    boolean enableAutosuspend() throws RemoteException;

    boolean forceSuspend() throws RemoteException;

    WakeLockInfo[] getWakeLockStats() throws RemoteException;

    boolean registerCallback(ISuspendCallback iSuspendCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISuspendControlService {
        private static final String DESCRIPTOR = "android$system$suspend$ISuspendControlService".replace(Typography.dollar, '.');
        static final int TRANSACTION_enableAutosuspend = 1;
        static final int TRANSACTION_forceSuspend = 3;
        static final int TRANSACTION_getWakeLockStats = 4;
        static final int TRANSACTION_registerCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISuspendControlService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISuspendControlService)) {
                return (ISuspendControlService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String str = DESCRIPTOR;
            if (i == 1) {
                parcel.enforceInterface(str);
                boolean zEnableAutosuspend = enableAutosuspend();
                parcel2.writeNoException();
                parcel2.writeInt(zEnableAutosuspend ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(str);
                boolean zRegisterCallback = registerCallback(ISuspendCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(zRegisterCallback ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(str);
                boolean zForceSuspend = forceSuspend();
                parcel2.writeNoException();
                parcel2.writeInt(zForceSuspend ? 1 : 0);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(str);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(str);
            WakeLockInfo[] wakeLockStats = getWakeLockStats();
            parcel2.writeNoException();
            parcel2.writeTypedArray(wakeLockStats, 1);
            return true;
        }

        private static class Proxy implements ISuspendControlService {
            public static ISuspendControlService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.system.suspend.ISuspendControlService
            public boolean enableAutosuspend() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableAutosuspend();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.system.suspend.ISuspendControlService
            public boolean registerCallback(ISuspendCallback iSuspendCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSuspendCallback != null ? iSuspendCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(iSuspendCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.system.suspend.ISuspendControlService
            public boolean forceSuspend() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().forceSuspend();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.system.suspend.ISuspendControlService
            public WakeLockInfo[] getWakeLockStats() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWakeLockStats();
                    }
                    parcelObtain2.readException();
                    return (WakeLockInfo[]) parcelObtain2.createTypedArray(WakeLockInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISuspendControlService iSuspendControlService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSuspendControlService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSuspendControlService;
            return true;
        }

        public static ISuspendControlService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
