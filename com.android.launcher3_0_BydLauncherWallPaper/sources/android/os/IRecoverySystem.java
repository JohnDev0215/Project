package android.os;

import android.content.IntentSender;
import android.os.IRecoverySystemProgressListener;

/* JADX INFO: loaded from: classes2.dex */
public interface IRecoverySystem extends IInterface {

    public static class Default implements IRecoverySystem {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IRecoverySystem
        public boolean clearBcb() throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean clearLskf() throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public void rebootRecoveryWithCommand(String str) throws RemoteException {
        }

        @Override // android.os.IRecoverySystem
        public boolean rebootWithLskf(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean requestLskf(String str, IntentSender intentSender) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean setupBcb(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean uncrypt(String str, IRecoverySystemProgressListener iRecoverySystemProgressListener) throws RemoteException {
            return false;
        }
    }

    boolean clearBcb() throws RemoteException;

    boolean clearLskf() throws RemoteException;

    void rebootRecoveryWithCommand(String str) throws RemoteException;

    boolean rebootWithLskf(String str, String str2) throws RemoteException;

    boolean requestLskf(String str, IntentSender intentSender) throws RemoteException;

    boolean setupBcb(String str) throws RemoteException;

    boolean uncrypt(String str, IRecoverySystemProgressListener iRecoverySystemProgressListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecoverySystem {
        private static final String DESCRIPTOR = "android.os.IRecoverySystem";
        static final int TRANSACTION_clearBcb = 3;
        static final int TRANSACTION_clearLskf = 6;
        static final int TRANSACTION_rebootRecoveryWithCommand = 4;
        static final int TRANSACTION_rebootWithLskf = 7;
        static final int TRANSACTION_requestLskf = 5;
        static final int TRANSACTION_setupBcb = 2;
        static final int TRANSACTION_uncrypt = 1;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "uncrypt";
                case 2:
                    return "setupBcb";
                case 3:
                    return "clearBcb";
                case 4:
                    return "rebootRecoveryWithCommand";
                case 5:
                    return "requestLskf";
                case 6:
                    return "clearLskf";
                case 7:
                    return "rebootWithLskf";
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

        public static IRecoverySystem asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRecoverySystem)) {
                return (IRecoverySystem) iInterfaceQueryLocalInterface;
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
                    boolean zUncrypt = uncrypt(parcel.readString(), IRecoverySystemProgressListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUncrypt ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean z = setupBcb(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(z ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zClearBcb = clearBcb();
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearBcb ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    rebootRecoveryWithCommand(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestLskf = requestLskf(parcel.readString(), parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestLskf ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zClearLskf = clearLskf();
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearLskf ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRebootWithLskf = rebootWithLskf(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRebootWithLskf ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRecoverySystem {
            public static IRecoverySystem sDefaultImpl;
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

            @Override // android.os.IRecoverySystem
            public boolean uncrypt(String str, IRecoverySystemProgressListener iRecoverySystemProgressListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iRecoverySystemProgressListener != null ? iRecoverySystemProgressListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().uncrypt(str, iRecoverySystemProgressListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public boolean setupBcb(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setupBcb(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public boolean clearBcb() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearBcb();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public void rebootRecoveryWithCommand(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rebootRecoveryWithCommand(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public boolean requestLskf(String str, IntentSender intentSender) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestLskf(str, intentSender);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public boolean clearLskf() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearLskf();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public boolean rebootWithLskf(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().rebootWithLskf(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecoverySystem iRecoverySystem) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRecoverySystem == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecoverySystem;
            return true;
        }

        public static IRecoverySystem getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
