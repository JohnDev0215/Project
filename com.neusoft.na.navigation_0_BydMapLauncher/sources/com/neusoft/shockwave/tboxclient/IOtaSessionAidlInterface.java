package com.neusoft.shockwave.tboxclient;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaSessionAidlInterface extends IInterface {
    boolean cancel() throws RemoteException;

    boolean couldBeginInstallRightNow() throws RemoteException;

    int getOtaConsumingTime() throws RemoteException;

    int getOtaProgress() throws RemoteException;

    boolean ifSystemWillRebootAfterOta() throws RemoteException;

    boolean isInstallationStarted() throws RemoteException;

    public static abstract class Stub extends Binder implements IOtaSessionAidlInterface {
        private static final String DESCRIPTOR = "com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface";
        static final int TRANSACTION_cancel = 1;
        static final int TRANSACTION_couldBeginInstallRightNow = 2;
        static final int TRANSACTION_getOtaConsumingTime = 6;
        static final int TRANSACTION_getOtaProgress = 3;
        static final int TRANSACTION_ifSystemWillRebootAfterOta = 4;
        static final int TRANSACTION_isInstallationStarted = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOtaSessionAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOtaSessionAidlInterface)) {
                return (IOtaSessionAidlInterface) iInterfaceQueryLocalInterface;
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
                    boolean zCancel = cancel();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancel ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCouldBeginInstallRightNow = couldBeginInstallRightNow();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCouldBeginInstallRightNow ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int otaProgress = getOtaProgress();
                    parcel2.writeNoException();
                    parcel2.writeInt(otaProgress);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIfSystemWillRebootAfterOta = ifSystemWillRebootAfterOta();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIfSystemWillRebootAfterOta ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInstallationStarted = isInstallationStarted();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInstallationStarted ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int otaConsumingTime = getOtaConsumingTime();
                    parcel2.writeNoException();
                    parcel2.writeInt(otaConsumingTime);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IOtaSessionAidlInterface {
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

            @Override // com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface
            public boolean cancel() throws RemoteException {
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

            @Override // com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface
            public boolean couldBeginInstallRightNow() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface
            public int getOtaProgress() throws RemoteException {
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

            @Override // com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface
            public boolean ifSystemWillRebootAfterOta() throws RemoteException {
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

            @Override // com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface
            public boolean isInstallationStarted() throws RemoteException {
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

            @Override // com.neusoft.shockwave.tboxclient.IOtaSessionAidlInterface
            public int getOtaConsumingTime() throws RemoteException {
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
        }
    }
}
