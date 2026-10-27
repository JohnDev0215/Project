package com.ecarx.xui.adaptapi.bt1.pbap;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.bt1.pbap.IPbapCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IPbap extends IInterface {
    int getPbapConnectionState() throws RemoteException;

    String getPbapDownloadingAddress() throws RemoteException;

    boolean isPbapDownloading() throws RemoteException;

    boolean isPbapServiceReady() throws RemoteException;

    boolean registerPbapCallback(IPbapCallback iPbapCallback) throws RemoteException;

    boolean reqPbapDownload(String str, int i, int i2) throws RemoteException;

    boolean reqPbapDownloadInterrupt(String str) throws RemoteException;

    boolean reqPbapDownloadRange(String str, int i, int i2, int i3, int i4) throws RemoteException;

    boolean setPbapDownloadNotify(int i) throws RemoteException;

    boolean unregisterPbapCallback(IPbapCallback iPbapCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IPbap {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.bt1.pbap.IPbap";
        static final int TRANSACTION_getPbapConnectionState = 4;
        static final int TRANSACTION_getPbapDownloadingAddress = 6;
        static final int TRANSACTION_isPbapDownloading = 5;
        static final int TRANSACTION_isPbapServiceReady = 1;
        static final int TRANSACTION_registerPbapCallback = 2;
        static final int TRANSACTION_reqPbapDownload = 7;
        static final int TRANSACTION_reqPbapDownloadInterrupt = 9;
        static final int TRANSACTION_reqPbapDownloadRange = 8;
        static final int TRANSACTION_setPbapDownloadNotify = 10;
        static final int TRANSACTION_unregisterPbapCallback = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPbap asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPbap)) {
                return (IPbap) iInterfaceQueryLocalInterface;
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
                    boolean zIsPbapServiceReady = isPbapServiceReady();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPbapServiceReady ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterPbapCallback = registerPbapCallback(IPbapCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterPbapCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterPbapCallback = unregisterPbapCallback(IPbapCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterPbapCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pbapConnectionState = getPbapConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(pbapConnectionState);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPbapDownloading = isPbapDownloading();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPbapDownloading ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String pbapDownloadingAddress = getPbapDownloadingAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(pbapDownloadingAddress);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqPbapDownload = reqPbapDownload(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqPbapDownload ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqPbapDownloadRange = reqPbapDownloadRange(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqPbapDownloadRange ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqPbapDownloadInterrupt = reqPbapDownloadInterrupt(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqPbapDownloadInterrupt ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pbapDownloadNotify = setPbapDownloadNotify(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(pbapDownloadNotify ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPbap {
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

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean isPbapServiceReady() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean registerPbapCallback(IPbapCallback iPbapCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPbapCallback != null ? iPbapCallback.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean unregisterPbapCallback(IPbapCallback iPbapCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPbapCallback != null ? iPbapCallback.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public int getPbapConnectionState() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean isPbapDownloading() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public String getPbapDownloadingAddress() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean reqPbapDownload(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean reqPbapDownloadRange(String str, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean reqPbapDownloadInterrupt(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbap
            public boolean setPbapDownloadNotify(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
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
