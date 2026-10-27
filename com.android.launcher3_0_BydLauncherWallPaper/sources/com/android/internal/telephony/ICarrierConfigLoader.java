package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface ICarrierConfigLoader extends IInterface {

    public static class Default implements ICarrierConfigLoader {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.ICarrierConfigLoader
        public PersistableBundle getConfigForSubId(int i, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ICarrierConfigLoader
        public PersistableBundle getConfigForSubIdWithFeature(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ICarrierConfigLoader
        public String getDefaultCarrierServicePackageName() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ICarrierConfigLoader
        public void notifyConfigChangedForSubId(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ICarrierConfigLoader
        public void overrideConfig(int i, PersistableBundle persistableBundle, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ICarrierConfigLoader
        public void updateConfigForPhoneId(int i, String str) throws RemoteException {
        }
    }

    PersistableBundle getConfigForSubId(int i, String str) throws RemoteException;

    PersistableBundle getConfigForSubIdWithFeature(int i, String str, String str2) throws RemoteException;

    String getDefaultCarrierServicePackageName() throws RemoteException;

    void notifyConfigChangedForSubId(int i) throws RemoteException;

    void overrideConfig(int i, PersistableBundle persistableBundle, boolean z) throws RemoteException;

    void updateConfigForPhoneId(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarrierConfigLoader {
        private static final String DESCRIPTOR = "com.android.internal.telephony.ICarrierConfigLoader";
        static final int TRANSACTION_getConfigForSubId = 1;
        static final int TRANSACTION_getConfigForSubIdWithFeature = 2;
        static final int TRANSACTION_getDefaultCarrierServicePackageName = 6;
        static final int TRANSACTION_notifyConfigChangedForSubId = 4;
        static final int TRANSACTION_overrideConfig = 3;
        static final int TRANSACTION_updateConfigForPhoneId = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getConfigForSubId";
                case 2:
                    return "getConfigForSubIdWithFeature";
                case 3:
                    return "overrideConfig";
                case 4:
                    return "notifyConfigChangedForSubId";
                case 5:
                    return "updateConfigForPhoneId";
                case 6:
                    return "getDefaultCarrierServicePackageName";
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

        public static ICarrierConfigLoader asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarrierConfigLoader)) {
                return (ICarrierConfigLoader) iInterfaceQueryLocalInterface;
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
                    PersistableBundle configForSubId = getConfigForSubId(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (configForSubId != null) {
                        parcel2.writeInt(1);
                        configForSubId.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    PersistableBundle configForSubIdWithFeature = getConfigForSubIdWithFeature(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (configForSubIdWithFeature != null) {
                        parcel2.writeInt(1);
                        configForSubIdWithFeature.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    overrideConfig(parcel.readInt(), parcel.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyConfigChangedForSubId(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateConfigForPhoneId(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String defaultCarrierServicePackageName = getDefaultCarrierServicePackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(defaultCarrierServicePackageName);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICarrierConfigLoader {
            public static ICarrierConfigLoader sDefaultImpl;
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

            @Override // com.android.internal.telephony.ICarrierConfigLoader
            public PersistableBundle getConfigForSubId(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigForSubId(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ICarrierConfigLoader
            public PersistableBundle getConfigForSubIdWithFeature(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigForSubIdWithFeature(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PersistableBundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ICarrierConfigLoader
            public void overrideConfig(int i, PersistableBundle persistableBundle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    int i2 = 1;
                    if (persistableBundle != null) {
                        parcelObtain.writeInt(1);
                        persistableBundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overrideConfig(i, persistableBundle, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ICarrierConfigLoader
            public void notifyConfigChangedForSubId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyConfigChangedForSubId(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ICarrierConfigLoader
            public void updateConfigForPhoneId(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateConfigForPhoneId(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ICarrierConfigLoader
            public String getDefaultCarrierServicePackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultCarrierServicePackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarrierConfigLoader iCarrierConfigLoader) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarrierConfigLoader == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarrierConfigLoader;
            return true;
        }

        public static ICarrierConfigLoader getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
