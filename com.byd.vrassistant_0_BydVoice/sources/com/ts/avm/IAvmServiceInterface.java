package com.ts.avm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.avm.IAvmServiceCommandListener;
import com.ts.avm.IAvmServiceListener;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: loaded from: classes3.dex */
public interface IAvmServiceInterface extends IInterface {

    public static class Default implements IAvmServiceInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int getAvmStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int registerCommandListener(IAvmServiceCommandListener iAvmServiceCommandListener) throws RemoteException {
            return 0;
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void sendCommand(CmdBean cmdBean) throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void startAvm() throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public void stopAvm() throws RemoteException {
        }

        @Override // com.ts.avm.IAvmServiceInterface
        public int unregisterAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException {
            return 0;
        }
    }

    int getAvmStatus() throws RemoteException;

    int registerAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException;

    int registerCommandListener(IAvmServiceCommandListener iAvmServiceCommandListener) throws RemoteException;

    void sendCommand(CmdBean cmdBean) throws RemoteException;

    void startAvm() throws RemoteException;

    void stopAvm() throws RemoteException;

    int unregisterAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IAvmServiceInterface {
        private static final String DESCRIPTOR = "com.ts.avm.IAvmServiceInterface";
        static final int TRANSACTION_getAvmStatus = 1;
        static final int TRANSACTION_registerAvmStatusListener = 2;
        static final int TRANSACTION_registerCommandListener = 7;
        static final int TRANSACTION_sendCommand = 6;
        static final int TRANSACTION_startAvm = 4;
        static final int TRANSACTION_stopAvm = 5;
        static final int TRANSACTION_unregisterAvmStatusListener = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAvmServiceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAvmServiceInterface)) {
                return (IAvmServiceInterface) iInterfaceQueryLocalInterface;
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
                    int avmStatus = getAvmStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(avmStatus);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRegisterAvmStatusListener = registerAvmStatusListener(IAvmServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iRegisterAvmStatusListener);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUnregisterAvmStatusListener = unregisterAvmStatusListener(IAvmServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iUnregisterAvmStatusListener);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    startAvm();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopAvm();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendCommand(parcel.readInt() != 0 ? CmdBean.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRegisterCommandListener = registerCommandListener(IAvmServiceCommandListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iRegisterCommandListener);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAvmServiceInterface {
            public static IAvmServiceInterface sDefaultImpl;
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

            @Override // com.ts.avm.IAvmServiceInterface
            public int getAvmStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvmStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int registerAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAvmServiceListener != null ? iAvmServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAvmStatusListener(iAvmServiceListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int unregisterAvmStatusListener(IAvmServiceListener iAvmServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAvmServiceListener != null ? iAvmServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterAvmStatusListener(iAvmServiceListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void startAvm() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startAvm();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void stopAvm() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopAvm();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public void sendCommand(CmdBean cmdBean) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cmdBean != null) {
                        parcelObtain.writeInt(1);
                        cmdBean.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendCommand(cmdBean);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ts.avm.IAvmServiceInterface
            public int registerCommandListener(IAvmServiceCommandListener iAvmServiceCommandListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAvmServiceCommandListener != null ? iAvmServiceCommandListener.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCommandListener(iAvmServiceCommandListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAvmServiceInterface iAvmServiceInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAvmServiceInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAvmServiceInterface;
            return true;
        }

        public static IAvmServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
