package com.ecarx.xui.adaptapi.bt1.a2dp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.bt1.a2dp.IA2dpCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IA2dp extends IInterface {
    String getA2dpConnectedAddress() throws RemoteException;

    int getA2dpConnectionState() throws RemoteException;

    boolean isA2dpConnected() throws RemoteException;

    boolean isA2dpServiceReady() throws RemoteException;

    void pauseA2dpRender() throws RemoteException;

    boolean registerA2dpCallback(IA2dpCallback iA2dpCallback) throws RemoteException;

    boolean reqA2dpConnect(String str) throws RemoteException;

    boolean reqA2dpDisconnect(String str) throws RemoteException;

    boolean setA2dpLocalVolume(float f) throws RemoteException;

    void startA2dpRender() throws RemoteException;

    boolean unregisterA2dpCallback(IA2dpCallback iA2dpCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IA2dp {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp";
        static final int TRANSACTION_getA2dpConnectedAddress = 6;
        static final int TRANSACTION_getA2dpConnectionState = 4;
        static final int TRANSACTION_isA2dpConnected = 5;
        static final int TRANSACTION_isA2dpServiceReady = 1;
        static final int TRANSACTION_pauseA2dpRender = 9;
        static final int TRANSACTION_registerA2dpCallback = 2;
        static final int TRANSACTION_reqA2dpConnect = 7;
        static final int TRANSACTION_reqA2dpDisconnect = 8;
        static final int TRANSACTION_setA2dpLocalVolume = 11;
        static final int TRANSACTION_startA2dpRender = 10;
        static final int TRANSACTION_unregisterA2dpCallback = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IA2dp asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IA2dp)) {
                return (IA2dp) iInterfaceQueryLocalInterface;
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
                    boolean zIsA2dpServiceReady = isA2dpServiceReady();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsA2dpServiceReady ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterA2dpCallback = registerA2dpCallback(IA2dpCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterA2dpCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterA2dpCallback = unregisterA2dpCallback(IA2dpCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterA2dpCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int a2dpConnectionState = getA2dpConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(a2dpConnectionState);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsA2dpConnected = isA2dpConnected();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsA2dpConnected ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String a2dpConnectedAddress = getA2dpConnectedAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(a2dpConnectedAddress);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqA2dpConnect = reqA2dpConnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqA2dpConnect ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqA2dpDisconnect = reqA2dpDisconnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqA2dpDisconnect ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseA2dpRender();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    startA2dpRender();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean a2dpLocalVolume = setA2dpLocalVolume(parcel.readFloat());
                    parcel2.writeNoException();
                    parcel2.writeInt(a2dpLocalVolume ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IA2dp {
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

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public boolean isA2dpServiceReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public boolean registerA2dpCallback(IA2dpCallback iA2dpCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iA2dpCallback != null ? iA2dpCallback.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public boolean unregisterA2dpCallback(IA2dpCallback iA2dpCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iA2dpCallback != null ? iA2dpCallback.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public int getA2dpConnectionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public boolean isA2dpConnected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public String getA2dpConnectedAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public boolean reqA2dpConnect(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public boolean reqA2dpDisconnect(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public void pauseA2dpRender() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public void startA2dpRender() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp
            public boolean setA2dpLocalVolume(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
