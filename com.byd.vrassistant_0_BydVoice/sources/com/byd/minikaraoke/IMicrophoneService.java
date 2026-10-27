package com.byd.minikaraoke;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.minikaraoke.IBufferCallback;
import com.byd.minikaraoke.IConnectionStateListener;
import com.byd.minikaraoke.IErrorListener;

/* JADX INFO: loaded from: classes.dex */
public interface IMicrophoneService extends IInterface {

    public static class Default implements IMicrophoneService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public boolean decreaseMicVolume() throws RemoteException {
            return false;
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public boolean getMicConnectionState() throws RemoteException {
            return false;
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public int getMicVolume() throws RemoteException {
            return 0;
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public boolean increaseMicVolume() throws RemoteException {
            return false;
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public void registerErrorListener(IErrorListener iErrorListener) throws RemoteException {
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public void registerMicrophoneConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException {
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public int sendBuffer(byte[] bArr) throws RemoteException {
            return 0;
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public void setBufferCallback(IBufferCallback iBufferCallback) throws RemoteException {
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public boolean setMicVolume(int i) throws RemoteException {
            return false;
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public void unregisterErrorListener(IErrorListener iErrorListener) throws RemoteException {
        }

        @Override // com.byd.minikaraoke.IMicrophoneService
        public void unregisterMicrophoneConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException {
        }
    }

    boolean decreaseMicVolume() throws RemoteException;

    boolean getMicConnectionState() throws RemoteException;

    int getMicVolume() throws RemoteException;

    boolean increaseMicVolume() throws RemoteException;

    void registerErrorListener(IErrorListener iErrorListener) throws RemoteException;

    void registerMicrophoneConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException;

    int sendBuffer(byte[] bArr) throws RemoteException;

    void setBufferCallback(IBufferCallback iBufferCallback) throws RemoteException;

    boolean setMicVolume(int i) throws RemoteException;

    void unregisterErrorListener(IErrorListener iErrorListener) throws RemoteException;

    void unregisterMicrophoneConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IMicrophoneService {
        private static final String DESCRIPTOR = "com.byd.minikaraoke.IMicrophoneService";
        static final int TRANSACTION_decreaseMicVolume = 8;
        static final int TRANSACTION_getMicConnectionState = 11;
        static final int TRANSACTION_getMicVolume = 10;
        static final int TRANSACTION_increaseMicVolume = 7;
        static final int TRANSACTION_registerErrorListener = 5;
        static final int TRANSACTION_registerMicrophoneConnectionStateListener = 1;
        static final int TRANSACTION_sendBuffer = 4;
        static final int TRANSACTION_setBufferCallback = 3;
        static final int TRANSACTION_setMicVolume = 9;
        static final int TRANSACTION_unregisterErrorListener = 6;
        static final int TRANSACTION_unregisterMicrophoneConnectionStateListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMicrophoneService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMicrophoneService)) {
                return (IMicrophoneService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
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
                    registerMicrophoneConnectionStateListener(IConnectionStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMicrophoneConnectionStateListener(IConnectionStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBufferCallback(IBufferCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendBuffer = sendBuffer(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendBuffer);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerErrorListener(IErrorListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterErrorListener(IErrorListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIncreaseMicVolume = increaseMicVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIncreaseMicVolume ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDecreaseMicVolume = decreaseMicVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(zDecreaseMicVolume ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean micVolume = setMicVolume(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micVolume ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micVolume2 = getMicVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(micVolume2);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean micConnectionState = getMicConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(micConnectionState ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMicrophoneService {
            public static IMicrophoneService sDefaultImpl;
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

            @Override // com.byd.minikaraoke.IMicrophoneService
            public void registerMicrophoneConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iConnectionStateListener != null ? iConnectionStateListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMicrophoneConnectionStateListener(iConnectionStateListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public void unregisterMicrophoneConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iConnectionStateListener != null ? iConnectionStateListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMicrophoneConnectionStateListener(iConnectionStateListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public void setBufferCallback(IBufferCallback iBufferCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBufferCallback != null ? iBufferCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBufferCallback(iBufferCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public int sendBuffer(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendBuffer(bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public void registerErrorListener(IErrorListener iErrorListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iErrorListener != null ? iErrorListener.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerErrorListener(iErrorListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public void unregisterErrorListener(IErrorListener iErrorListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iErrorListener != null ? iErrorListener.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterErrorListener(iErrorListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public boolean increaseMicVolume() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().increaseMicVolume();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public boolean decreaseMicVolume() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().decreaseMicVolume();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public boolean setMicVolume(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMicVolume(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public int getMicVolume() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicVolume();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.minikaraoke.IMicrophoneService
            public boolean getMicConnectionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicConnectionState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMicrophoneService iMicrophoneService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMicrophoneService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMicrophoneService;
            return true;
        }

        public static IMicrophoneService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
