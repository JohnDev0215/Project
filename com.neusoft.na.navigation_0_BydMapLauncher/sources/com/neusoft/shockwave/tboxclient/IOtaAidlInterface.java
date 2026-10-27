package com.neusoft.shockwave.tboxclient;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface;
import com.neusoft.shockwave.tboxclient.IOtaSessionCallbackAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaAidlInterface extends IInterface {
    int getOtaBaseSoftwareVersionCode() throws RemoteException;

    String getOtaBaseSoftwareVersionName() throws RemoteException;

    String getOtaChangelog() throws RemoteException;

    int getPackageSize() throws RemoteException;

    boolean installPackage(IOtaSessionAidlInterface iOtaSessionAidlInterface, String str) throws RemoteException;

    boolean installPackageByTime(IOtaSessionAidlInterface iOtaSessionAidlInterface, String str, int i) throws RemoteException;

    boolean isNewVersionFound() throws RemoteException;

    boolean isOtaPreOdered() throws RemoteException;

    void otaSuspendedByUser() throws RemoteException;

    IOtaSessionAidlInterface requestOta(IOtaSessionCallbackAidlInterface iOtaSessionCallbackAidlInterface) throws RemoteException;

    public static abstract class Stub extends Binder implements IOtaAidlInterface {
        private static final String DESCRIPTOR = "com.neusoft.shockwave.tboxclient.IOtaAidlInterface";
        static final int TRANSACTION_getOtaBaseSoftwareVersionCode = 1;
        static final int TRANSACTION_getOtaBaseSoftwareVersionName = 2;
        static final int TRANSACTION_getOtaChangelog = 3;
        static final int TRANSACTION_getPackageSize = 10;
        static final int TRANSACTION_installPackage = 4;
        static final int TRANSACTION_installPackageByTime = 5;
        static final int TRANSACTION_isNewVersionFound = 6;
        static final int TRANSACTION_isOtaPreOdered = 7;
        static final int TRANSACTION_otaSuspendedByUser = 8;
        static final int TRANSACTION_requestOta = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOtaAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOtaAidlInterface)) {
                return (IOtaAidlInterface) iInterfaceQueryLocalInterface;
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
                    int otaBaseSoftwareVersionCode = getOtaBaseSoftwareVersionCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(otaBaseSoftwareVersionCode);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String otaBaseSoftwareVersionName = getOtaBaseSoftwareVersionName();
                    parcel2.writeNoException();
                    parcel2.writeString(otaBaseSoftwareVersionName);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String otaChangelog = getOtaChangelog();
                    parcel2.writeNoException();
                    parcel2.writeString(otaChangelog);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zInstallPackage = installPackage(IOtaSessionAidlInterface.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zInstallPackage ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zInstallPackageByTime = installPackageByTime(IOtaSessionAidlInterface.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zInstallPackageByTime ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsNewVersionFound = isNewVersionFound();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsNewVersionFound ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsOtaPreOdered = isOtaPreOdered();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOtaPreOdered ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    otaSuspendedByUser();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    IOtaSessionAidlInterface iOtaSessionAidlInterfaceRequestOta = requestOta(IOtaSessionCallbackAidlInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iOtaSessionAidlInterfaceRequestOta != null ? iOtaSessionAidlInterfaceRequestOta.asBinder() : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int packageSize = getPackageSize();
                    parcel2.writeNoException();
                    parcel2.writeInt(packageSize);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IOtaAidlInterface {
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

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public int getOtaBaseSoftwareVersionCode() throws RemoteException {
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

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public String getOtaBaseSoftwareVersionName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public String getOtaChangelog() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public boolean installPackage(IOtaSessionAidlInterface iOtaSessionAidlInterface, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOtaSessionAidlInterface != null ? iOtaSessionAidlInterface.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public boolean installPackageByTime(IOtaSessionAidlInterface iOtaSessionAidlInterface, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOtaSessionAidlInterface != null ? iOtaSessionAidlInterface.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public boolean isNewVersionFound() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public boolean isOtaPreOdered() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public void otaSuspendedByUser() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public IOtaSessionAidlInterface requestOta(IOtaSessionCallbackAidlInterface iOtaSessionCallbackAidlInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOtaSessionCallbackAidlInterface != null ? iOtaSessionCallbackAidlInterface.asBinder() : null);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return IOtaSessionAidlInterface.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaAidlInterface
            public int getPackageSize() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
