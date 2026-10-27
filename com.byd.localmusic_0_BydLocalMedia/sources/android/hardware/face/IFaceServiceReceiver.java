package android.hardware.face;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IFaceServiceReceiver extends IInterface {

    public static class Default implements IFaceServiceReceiver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAcquired(long j, int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAuthenticationFailed(long j) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAuthenticationSucceeded(long j, Face face, int i, boolean z) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onEnrollResult(long j, int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onEnumerated(long j, int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onError(long j, int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onFeatureGet(boolean z, int i, boolean z2) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onFeatureSet(boolean z, int i) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onRemoved(long j, int i, int i2) throws RemoteException {
        }
    }

    void onAcquired(long j, int i, int i2) throws RemoteException;

    void onAuthenticationFailed(long j) throws RemoteException;

    void onAuthenticationSucceeded(long j, Face face, int i, boolean z) throws RemoteException;

    void onEnrollResult(long j, int i, int i2) throws RemoteException;

    void onEnumerated(long j, int i, int i2) throws RemoteException;

    void onError(long j, int i, int i2) throws RemoteException;

    void onFeatureGet(boolean z, int i, boolean z2) throws RemoteException;

    void onFeatureSet(boolean z, int i) throws RemoteException;

    void onRemoved(long j, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IFaceServiceReceiver {
        private static final String DESCRIPTOR = "android.hardware.face.IFaceServiceReceiver";
        static final int TRANSACTION_onAcquired = 2;
        static final int TRANSACTION_onAuthenticationFailed = 4;
        static final int TRANSACTION_onAuthenticationSucceeded = 3;
        static final int TRANSACTION_onEnrollResult = 1;
        static final int TRANSACTION_onEnumerated = 7;
        static final int TRANSACTION_onError = 5;
        static final int TRANSACTION_onFeatureGet = 9;
        static final int TRANSACTION_onFeatureSet = 8;
        static final int TRANSACTION_onRemoved = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onEnrollResult";
                case 2:
                    return "onAcquired";
                case 3:
                    return "onAuthenticationSucceeded";
                case 4:
                    return "onAuthenticationFailed";
                case 5:
                    return "onError";
                case 6:
                    return "onRemoved";
                case 7:
                    return "onEnumerated";
                case 8:
                    return "onFeatureSet";
                case 9:
                    return "onFeatureGet";
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

        public static IFaceServiceReceiver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IFaceServiceReceiver)) {
                return (IFaceServiceReceiver) iInterfaceQueryLocalInterface;
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
                    onEnrollResult(parcel.readLong(), parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAcquired(parcel.readLong(), parcel.readInt(), parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAuthenticationSucceeded(parcel.readLong(), parcel.readInt() != 0 ? Face.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAuthenticationFailed(parcel.readLong());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onError(parcel.readLong(), parcel.readInt(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRemoved(parcel.readLong(), parcel.readInt(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onEnumerated(parcel.readLong(), parcel.readInt(), parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onFeatureSet(parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onFeatureGet(parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IFaceServiceReceiver {
            public static IFaceServiceReceiver sDefaultImpl;
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

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onEnrollResult(long j, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEnrollResult(j, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onAcquired(long j, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAcquired(j, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onAuthenticationSucceeded(long j, Face face, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (face != null) {
                        parcelObtain.writeInt(1);
                        face.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAuthenticationSucceeded(j, face, i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onAuthenticationFailed(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAuthenticationFailed(j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onError(long j, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(j, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onRemoved(long j, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRemoved(j, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onEnumerated(long j, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEnumerated(j, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onFeatureSet(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFeatureSet(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onFeatureGet(boolean z, int i, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFeatureGet(z, i, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFaceServiceReceiver iFaceServiceReceiver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iFaceServiceReceiver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iFaceServiceReceiver;
            return true;
        }

        public static IFaceServiceReceiver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
