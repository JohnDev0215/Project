package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.AvailableNetworkInfo;
import com.android.internal.telephony.ISetOpportunisticDataCallback;
import com.android.internal.telephony.IUpdateAvailableNetworksCallback;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IOns extends IInterface {

    public static class Default implements IOns {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.IOns
        public int getPreferredDataSubscriptionId(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.IOns
        public boolean isEnabled(String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.IOns
        public boolean setEnable(boolean z, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.IOns
        public void setPreferredDataSubscriptionId(int i, boolean z, ISetOpportunisticDataCallback iSetOpportunisticDataCallback, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IOns
        public void updateAvailableNetworks(List<AvailableNetworkInfo> list, IUpdateAvailableNetworksCallback iUpdateAvailableNetworksCallback, String str) throws RemoteException {
        }
    }

    int getPreferredDataSubscriptionId(String str, String str2) throws RemoteException;

    boolean isEnabled(String str) throws RemoteException;

    boolean setEnable(boolean z, String str) throws RemoteException;

    void setPreferredDataSubscriptionId(int i, boolean z, ISetOpportunisticDataCallback iSetOpportunisticDataCallback, String str) throws RemoteException;

    void updateAvailableNetworks(List<AvailableNetworkInfo> list, IUpdateAvailableNetworksCallback iUpdateAvailableNetworksCallback, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IOns {
        private static final String DESCRIPTOR = "com.android.internal.telephony.IOns";
        static final int TRANSACTION_getPreferredDataSubscriptionId = 4;
        static final int TRANSACTION_isEnabled = 2;
        static final int TRANSACTION_setEnable = 1;
        static final int TRANSACTION_setPreferredDataSubscriptionId = 3;
        static final int TRANSACTION_updateAvailableNetworks = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "setEnable";
            }
            if (i == 2) {
                return "isEnabled";
            }
            if (i == 3) {
                return "setPreferredDataSubscriptionId";
            }
            if (i == 4) {
                return "getPreferredDataSubscriptionId";
            }
            if (i != 5) {
                return null;
            }
            return "updateAvailableNetworks";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOns asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOns)) {
                return (IOns) iInterfaceQueryLocalInterface;
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
                parcel.enforceInterface(DESCRIPTOR);
                boolean enable = setEnable(parcel.readInt() != 0, parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(enable ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zIsEnabled = isEnabled(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zIsEnabled ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                setPreferredDataSubscriptionId(parcel.readInt(), parcel.readInt() != 0, ISetOpportunisticDataCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                int preferredDataSubscriptionId = getPreferredDataSubscriptionId(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(preferredDataSubscriptionId);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            updateAvailableNetworks(parcel.createTypedArrayList(AvailableNetworkInfo.CREATOR), IUpdateAvailableNetworksCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IOns {
            public static IOns sDefaultImpl;
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

            @Override // com.android.internal.telephony.IOns
            public boolean setEnable(boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnable(z, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IOns
            public boolean isEnabled(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEnabled(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IOns
            public void setPreferredDataSubscriptionId(int i, boolean z, ISetOpportunisticDataCallback iSetOpportunisticDataCallback, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iSetOpportunisticDataCallback != null ? iSetOpportunisticDataCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPreferredDataSubscriptionId(i, z, iSetOpportunisticDataCallback, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IOns
            public int getPreferredDataSubscriptionId(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredDataSubscriptionId(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IOns
            public void updateAvailableNetworks(List<AvailableNetworkInfo> list, IUpdateAvailableNetworksCallback iUpdateAvailableNetworksCallback, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeStrongBinder(iUpdateAvailableNetworksCallback != null ? iUpdateAvailableNetworksCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateAvailableNetworks(list, iUpdateAvailableNetworksCallback, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOns iOns) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOns == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOns;
            return true;
        }

        public static IOns getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
