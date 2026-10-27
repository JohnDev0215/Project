package android.media.tv;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ITvRemoteServiceInput extends IInterface {

    public static class Default implements ITvRemoteServiceInput {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void clearInputBridge(IBinder iBinder) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void closeInputBridge(IBinder iBinder) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void openGamepadBridge(IBinder iBinder, String str) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void openInputBridge(IBinder iBinder, String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendGamepadAxisValue(IBinder iBinder, int i, float f) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendGamepadKeyDown(IBinder iBinder, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendGamepadKeyUp(IBinder iBinder, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendKeyDown(IBinder iBinder, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendKeyUp(IBinder iBinder, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendPointerDown(IBinder iBinder, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendPointerSync(IBinder iBinder) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendPointerUp(IBinder iBinder, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendTimestamp(IBinder iBinder, long j) throws RemoteException {
        }
    }

    void clearInputBridge(IBinder iBinder) throws RemoteException;

    void closeInputBridge(IBinder iBinder) throws RemoteException;

    void openGamepadBridge(IBinder iBinder, String str) throws RemoteException;

    void openInputBridge(IBinder iBinder, String str, int i, int i2, int i3) throws RemoteException;

    void sendGamepadAxisValue(IBinder iBinder, int i, float f) throws RemoteException;

    void sendGamepadKeyDown(IBinder iBinder, int i) throws RemoteException;

    void sendGamepadKeyUp(IBinder iBinder, int i) throws RemoteException;

    void sendKeyDown(IBinder iBinder, int i) throws RemoteException;

    void sendKeyUp(IBinder iBinder, int i) throws RemoteException;

    void sendPointerDown(IBinder iBinder, int i, int i2, int i3) throws RemoteException;

    void sendPointerSync(IBinder iBinder) throws RemoteException;

    void sendPointerUp(IBinder iBinder, int i) throws RemoteException;

    void sendTimestamp(IBinder iBinder, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements ITvRemoteServiceInput {
        private static final String DESCRIPTOR = "android.media.tv.ITvRemoteServiceInput";
        static final int TRANSACTION_clearInputBridge = 3;
        static final int TRANSACTION_closeInputBridge = 2;
        static final int TRANSACTION_openGamepadBridge = 10;
        static final int TRANSACTION_openInputBridge = 1;
        static final int TRANSACTION_sendGamepadAxisValue = 13;
        static final int TRANSACTION_sendGamepadKeyDown = 11;
        static final int TRANSACTION_sendGamepadKeyUp = 12;
        static final int TRANSACTION_sendKeyDown = 5;
        static final int TRANSACTION_sendKeyUp = 6;
        static final int TRANSACTION_sendPointerDown = 7;
        static final int TRANSACTION_sendPointerSync = 9;
        static final int TRANSACTION_sendPointerUp = 8;
        static final int TRANSACTION_sendTimestamp = 4;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "openInputBridge";
                case 2:
                    return "closeInputBridge";
                case 3:
                    return "clearInputBridge";
                case 4:
                    return "sendTimestamp";
                case 5:
                    return "sendKeyDown";
                case 6:
                    return "sendKeyUp";
                case 7:
                    return "sendPointerDown";
                case 8:
                    return "sendPointerUp";
                case 9:
                    return "sendPointerSync";
                case 10:
                    return "openGamepadBridge";
                case 11:
                    return "sendGamepadKeyDown";
                case 12:
                    return "sendGamepadKeyUp";
                case 13:
                    return "sendGamepadAxisValue";
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

        public static ITvRemoteServiceInput asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITvRemoteServiceInput)) {
                return (ITvRemoteServiceInput) iInterfaceQueryLocalInterface;
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
                    openInputBridge(parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeInputBridge(parcel.readStrongBinder());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearInputBridge(parcel.readStrongBinder());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendTimestamp(parcel.readStrongBinder(), parcel.readLong());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendKeyDown(parcel.readStrongBinder(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendKeyUp(parcel.readStrongBinder(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendPointerDown(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendPointerUp(parcel.readStrongBinder(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendPointerSync(parcel.readStrongBinder());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    openGamepadBridge(parcel.readStrongBinder(), parcel.readString());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendGamepadKeyDown(parcel.readStrongBinder(), parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendGamepadKeyUp(parcel.readStrongBinder(), parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendGamepadAxisValue(parcel.readStrongBinder(), parcel.readInt(), parcel.readFloat());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITvRemoteServiceInput {
            public static ITvRemoteServiceInput sDefaultImpl;
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

            @Override // android.media.tv.ITvRemoteServiceInput
            public void openInputBridge(IBinder iBinder, String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().openInputBridge(iBinder, str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void closeInputBridge(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().closeInputBridge(iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void clearInputBridge(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().clearInputBridge(iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendTimestamp(IBinder iBinder, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendTimestamp(iBinder, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendKeyDown(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendKeyDown(iBinder, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendKeyUp(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendKeyUp(iBinder, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendPointerDown(IBinder iBinder, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendPointerDown(iBinder, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendPointerUp(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendPointerUp(iBinder, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendPointerSync(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendPointerSync(iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void openGamepadBridge(IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().openGamepadBridge(iBinder, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendGamepadKeyDown(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendGamepadKeyDown(iBinder, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendGamepadKeyUp(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendGamepadKeyUp(iBinder, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendGamepadAxisValue(IBinder iBinder, int i, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendGamepadAxisValue(iBinder, i, f);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvRemoteServiceInput iTvRemoteServiceInput) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTvRemoteServiceInput == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTvRemoteServiceInput;
            return true;
        }

        public static ITvRemoteServiceInput getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
