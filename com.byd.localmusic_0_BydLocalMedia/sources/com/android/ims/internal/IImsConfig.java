package com.android.ims.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.ims.ImsConfigListener;

/* JADX INFO: loaded from: classes4.dex */
public interface IImsConfig extends IInterface {

    public static class Default implements IImsConfig {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.ims.internal.IImsConfig
        public void getFeatureValue(int i, int i2, ImsConfigListener imsConfigListener) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsConfig
        public String getProvisionedStringValue(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.ims.internal.IImsConfig
        public int getProvisionedValue(int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.ims.internal.IImsConfig
        public void getVideoQuality(ImsConfigListener imsConfigListener) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsConfig
        public boolean getVolteProvisioned() throws RemoteException {
            return false;
        }

        @Override // com.android.ims.internal.IImsConfig
        public void setFeatureValue(int i, int i2, int i3, ImsConfigListener imsConfigListener) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsConfig
        public int setProvisionedStringValue(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // com.android.ims.internal.IImsConfig
        public int setProvisionedValue(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // com.android.ims.internal.IImsConfig
        public void setVideoQuality(int i, ImsConfigListener imsConfigListener) throws RemoteException {
        }
    }

    void getFeatureValue(int i, int i2, ImsConfigListener imsConfigListener) throws RemoteException;

    String getProvisionedStringValue(int i) throws RemoteException;

    int getProvisionedValue(int i) throws RemoteException;

    void getVideoQuality(ImsConfigListener imsConfigListener) throws RemoteException;

    boolean getVolteProvisioned() throws RemoteException;

    void setFeatureValue(int i, int i2, int i3, ImsConfigListener imsConfigListener) throws RemoteException;

    int setProvisionedStringValue(int i, String str) throws RemoteException;

    int setProvisionedValue(int i, int i2) throws RemoteException;

    void setVideoQuality(int i, ImsConfigListener imsConfigListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsConfig {
        private static final String DESCRIPTOR = "com.android.ims.internal.IImsConfig";
        static final int TRANSACTION_getFeatureValue = 5;
        static final int TRANSACTION_getProvisionedStringValue = 2;
        static final int TRANSACTION_getProvisionedValue = 1;
        static final int TRANSACTION_getVideoQuality = 8;
        static final int TRANSACTION_getVolteProvisioned = 7;
        static final int TRANSACTION_setFeatureValue = 6;
        static final int TRANSACTION_setProvisionedStringValue = 4;
        static final int TRANSACTION_setProvisionedValue = 3;
        static final int TRANSACTION_setVideoQuality = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getProvisionedValue";
                case 2:
                    return "getProvisionedStringValue";
                case 3:
                    return "setProvisionedValue";
                case 4:
                    return "setProvisionedStringValue";
                case 5:
                    return "getFeatureValue";
                case 6:
                    return "setFeatureValue";
                case 7:
                    return "getVolteProvisioned";
                case 8:
                    return "getVideoQuality";
                case 9:
                    return "setVideoQuality";
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

        public static IImsConfig asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsConfig)) {
                return (IImsConfig) iInterfaceQueryLocalInterface;
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
                    int provisionedValue = getProvisionedValue(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(provisionedValue);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String provisionedStringValue = getProvisionedStringValue(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(provisionedStringValue);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int provisionedValue2 = setProvisionedValue(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(provisionedValue2);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int provisionedStringValue2 = setProvisionedStringValue(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(provisionedStringValue2);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    getFeatureValue(parcel.readInt(), parcel.readInt(), ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFeatureValue(parcel.readInt(), parcel.readInt(), parcel.readInt(), ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean volteProvisioned = getVolteProvisioned();
                    parcel2.writeNoException();
                    parcel2.writeInt(volteProvisioned ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getVideoQuality(ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVideoQuality(parcel.readInt(), ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IImsConfig {
            public static IImsConfig sDefaultImpl;
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

            @Override // com.android.ims.internal.IImsConfig
            public int getProvisionedValue(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProvisionedValue(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public String getProvisionedStringValue(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProvisionedStringValue(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public int setProvisionedValue(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setProvisionedValue(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public int setProvisionedStringValue(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setProvisionedStringValue(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public void getFeatureValue(int i, int i2, ImsConfigListener imsConfigListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(imsConfigListener != null ? imsConfigListener.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getFeatureValue(i, i2, imsConfigListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public void setFeatureValue(int i, int i2, int i3, ImsConfigListener imsConfigListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStrongBinder(imsConfigListener != null ? imsConfigListener.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setFeatureValue(i, i2, i3, imsConfigListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public boolean getVolteProvisioned() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolteProvisioned();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public void getVideoQuality(ImsConfigListener imsConfigListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(imsConfigListener != null ? imsConfigListener.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getVideoQuality(imsConfigListener);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsConfig
            public void setVideoQuality(int i, ImsConfigListener imsConfigListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(imsConfigListener != null ? imsConfigListener.asBinder() : null);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setVideoQuality(i, imsConfigListener);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsConfig iImsConfig) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsConfig == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsConfig;
            return true;
        }

        public static IImsConfig getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
