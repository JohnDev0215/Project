package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IUiModeManager extends IInterface {

    public static class Default implements IUiModeManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IUiModeManager
        public void disableCarMode(int i) throws RemoteException {
        }

        @Override // android.app.IUiModeManager
        public void disableCarModeByCallingPackage(int i, String str) throws RemoteException {
        }

        @Override // android.app.IUiModeManager
        public void enableCarMode(int i, int i2, String str) throws RemoteException {
        }

        @Override // android.app.IUiModeManager
        public int getCurrentModeType() throws RemoteException {
            return 0;
        }

        @Override // android.app.IUiModeManager
        public long getCustomNightModeEnd() throws RemoteException {
            return 0L;
        }

        @Override // android.app.IUiModeManager
        public long getCustomNightModeStart() throws RemoteException {
            return 0L;
        }

        @Override // android.app.IUiModeManager
        public int getNightMode() throws RemoteException {
            return 0;
        }

        @Override // android.app.IUiModeManager
        public boolean isNightModeLocked() throws RemoteException {
            return false;
        }

        @Override // android.app.IUiModeManager
        public boolean isUiModeLocked() throws RemoteException {
            return false;
        }

        @Override // android.app.IUiModeManager
        public void setCustomNightModeEnd(long j) throws RemoteException {
        }

        @Override // android.app.IUiModeManager
        public void setCustomNightModeStart(long j) throws RemoteException {
        }

        @Override // android.app.IUiModeManager
        public void setNightMode(int i) throws RemoteException {
        }

        @Override // android.app.IUiModeManager
        public boolean setNightModeActivated(boolean z) throws RemoteException {
            return false;
        }
    }

    void disableCarMode(int i) throws RemoteException;

    void disableCarModeByCallingPackage(int i, String str) throws RemoteException;

    void enableCarMode(int i, int i2, String str) throws RemoteException;

    int getCurrentModeType() throws RemoteException;

    long getCustomNightModeEnd() throws RemoteException;

    long getCustomNightModeStart() throws RemoteException;

    int getNightMode() throws RemoteException;

    boolean isNightModeLocked() throws RemoteException;

    boolean isUiModeLocked() throws RemoteException;

    void setCustomNightModeEnd(long j) throws RemoteException;

    void setCustomNightModeStart(long j) throws RemoteException;

    void setNightMode(int i) throws RemoteException;

    boolean setNightModeActivated(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IUiModeManager {
        private static final String DESCRIPTOR = "android.app.IUiModeManager";
        static final int TRANSACTION_disableCarMode = 2;
        static final int TRANSACTION_disableCarModeByCallingPackage = 3;
        static final int TRANSACTION_enableCarMode = 1;
        static final int TRANSACTION_getCurrentModeType = 4;
        static final int TRANSACTION_getCustomNightModeEnd = 12;
        static final int TRANSACTION_getCustomNightModeStart = 10;
        static final int TRANSACTION_getNightMode = 6;
        static final int TRANSACTION_isNightModeLocked = 8;
        static final int TRANSACTION_isUiModeLocked = 7;
        static final int TRANSACTION_setCustomNightModeEnd = 13;
        static final int TRANSACTION_setCustomNightModeStart = 11;
        static final int TRANSACTION_setNightMode = 5;
        static final int TRANSACTION_setNightModeActivated = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "enableCarMode";
                case 2:
                    return "disableCarMode";
                case 3:
                    return "disableCarModeByCallingPackage";
                case 4:
                    return "getCurrentModeType";
                case 5:
                    return "setNightMode";
                case 6:
                    return "getNightMode";
                case 7:
                    return "isUiModeLocked";
                case 8:
                    return "isNightModeLocked";
                case 9:
                    return "setNightModeActivated";
                case 10:
                    return "getCustomNightModeStart";
                case 11:
                    return "setCustomNightModeStart";
                case 12:
                    return "getCustomNightModeEnd";
                case 13:
                    return "setCustomNightModeEnd";
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

        public static IUiModeManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUiModeManager)) {
                return (IUiModeManager) iInterfaceQueryLocalInterface;
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
                    enableCarMode(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableCarMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableCarModeByCallingPackage(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentModeType = getCurrentModeType();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentModeType);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNightMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int nightMode = getNightMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(nightMode);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsUiModeLocked = isUiModeLocked();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsUiModeLocked ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNightModeLocked = isNightModeLocked();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNightModeLocked ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nightModeActivated = setNightModeActivated(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(nightModeActivated ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    long customNightModeStart = getCustomNightModeStart();
                    parcel2.writeNoException();
                    parcel2.writeLong(customNightModeStart);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCustomNightModeStart(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    long customNightModeEnd = getCustomNightModeEnd();
                    parcel2.writeNoException();
                    parcel2.writeLong(customNightModeEnd);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCustomNightModeEnd(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IUiModeManager {
            public static IUiModeManager sDefaultImpl;
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

            @Override // android.app.IUiModeManager
            public void enableCarMode(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableCarMode(i, i2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public void disableCarMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableCarMode(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public void disableCarModeByCallingPackage(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableCarModeByCallingPackage(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public int getCurrentModeType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentModeType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public void setNightMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNightMode(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public int getNightMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public boolean isUiModeLocked() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUiModeLocked();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public boolean isNightModeLocked() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNightModeLocked();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public boolean setNightModeActivated(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightModeActivated(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public long getCustomNightModeStart() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCustomNightModeStart();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public void setCustomNightModeStart(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCustomNightModeStart(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public long getCustomNightModeEnd() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCustomNightModeEnd();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiModeManager
            public void setCustomNightModeEnd(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCustomNightModeEnd(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUiModeManager iUiModeManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUiModeManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUiModeManager;
            return true;
        }

        public static IUiModeManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
