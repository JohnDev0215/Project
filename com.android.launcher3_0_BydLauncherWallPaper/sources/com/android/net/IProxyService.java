package com.android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IProxyService extends IInterface {

    public static class Default implements IProxyService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.net.IProxyService
        public String resolvePacFile(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.net.IProxyService
        public void setPacFile(String str) throws RemoteException {
        }

        @Override // com.android.net.IProxyService
        public void startPacSystem() throws RemoteException {
        }

        @Override // com.android.net.IProxyService
        public void stopPacSystem() throws RemoteException {
        }
    }

    String resolvePacFile(String str, String str2) throws RemoteException;

    void setPacFile(String str) throws RemoteException;

    void startPacSystem() throws RemoteException;

    void stopPacSystem() throws RemoteException;

    public static abstract class Stub extends Binder implements IProxyService {
        private static final String DESCRIPTOR = "com.android.net.IProxyService";
        static final int TRANSACTION_resolvePacFile = 1;
        static final int TRANSACTION_setPacFile = 2;
        static final int TRANSACTION_startPacSystem = 3;
        static final int TRANSACTION_stopPacSystem = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "resolvePacFile";
            }
            if (i == 2) {
                return "setPacFile";
            }
            if (i == 3) {
                return "startPacSystem";
            }
            if (i != 4) {
                return null;
            }
            return "stopPacSystem";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.android.net.IProxyService");
        }

        public static IProxyService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.android.net.IProxyService");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IProxyService)) {
                return (IProxyService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.android.net.IProxyService");
                String strResolvePacFile = resolvePacFile(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strResolvePacFile);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.android.net.IProxyService");
                setPacFile(parcel.readString());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.android.net.IProxyService");
                startPacSystem();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface("com.android.net.IProxyService");
                stopPacSystem();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.net.IProxyService");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IProxyService {
            public static IProxyService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.android.net.IProxyService";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.net.IProxyService
            public String resolvePacFile(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.android.net.IProxyService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resolvePacFile(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.net.IProxyService
            public void setPacFile(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.android.net.IProxyService");
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setPacFile(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.net.IProxyService
            public void startPacSystem() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.android.net.IProxyService");
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startPacSystem();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.net.IProxyService
            public void stopPacSystem() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.android.net.IProxyService");
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopPacSystem();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProxyService iProxyService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProxyService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProxyService;
            return true;
        }

        public static IProxyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
