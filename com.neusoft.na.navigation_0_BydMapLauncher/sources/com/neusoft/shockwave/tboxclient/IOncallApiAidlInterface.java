package com.neusoft.shockwave.tboxclient;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.neusoft.shockwave.tboxclient.INotifyXCallInfoforAPIInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IOncallApiAidlInterface extends IInterface {
    void end() throws RemoteException;

    int getDuration() throws RemoteException;

    int getStartCause() throws RemoteException;

    int getStatus() throws RemoteException;

    int getType() throws RemoteException;

    boolean isCallback() throws RemoteException;

    boolean isECallInCallbackMode() throws RemoteException;

    void requestOnCallInfoCallback(INotifyXCallInfoforAPIInterface iNotifyXCallInfoforAPIInterface) throws RemoteException;

    void startCall(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IOncallApiAidlInterface {
        private static final String DESCRIPTOR = "com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface";
        static final int TRANSACTION_end = 5;
        static final int TRANSACTION_getDuration = 3;
        static final int TRANSACTION_getStartCause = 6;
        static final int TRANSACTION_getStatus = 1;
        static final int TRANSACTION_getType = 2;
        static final int TRANSACTION_isCallback = 4;
        static final int TRANSACTION_isECallInCallbackMode = 9;
        static final int TRANSACTION_requestOnCallInfoCallback = 7;
        static final int TRANSACTION_startCall = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOncallApiAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOncallApiAidlInterface)) {
                return (IOncallApiAidlInterface) iInterfaceQueryLocalInterface;
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
                    int status = getStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(status);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int type = getType();
                    parcel2.writeNoException();
                    parcel2.writeInt(type);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int duration = getDuration();
                    parcel2.writeNoException();
                    parcel2.writeInt(duration);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsCallback = isCallback();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCallback ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    end();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int startCause = getStartCause();
                    parcel2.writeNoException();
                    parcel2.writeInt(startCause);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestOnCallInfoCallback(INotifyXCallInfoforAPIInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    startCall(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsECallInCallbackMode = isECallInCallbackMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsECallInCallbackMode ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IOncallApiAidlInterface {
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

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public int getStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public int getType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public int getDuration() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public boolean isCallback() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public void end() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public int getStartCause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public void requestOnCallInfoCallback(INotifyXCallInfoforAPIInterface iNotifyXCallInfoforAPIInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iNotifyXCallInfoforAPIInterface != null ? iNotifyXCallInfoforAPIInterface.asBinder() : null);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public void startCall(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOncallApiAidlInterface
            public boolean isECallInCallbackMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
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
