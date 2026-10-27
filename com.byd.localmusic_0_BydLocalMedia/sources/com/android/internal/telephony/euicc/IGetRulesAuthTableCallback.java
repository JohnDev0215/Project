package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.euicc.EuiccRulesAuthTable;

/* JADX INFO: loaded from: classes4.dex */
public interface IGetRulesAuthTableCallback extends IInterface {

    public static class Default implements IGetRulesAuthTableCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.euicc.IGetRulesAuthTableCallback
        public void onComplete(int i, EuiccRulesAuthTable euiccRulesAuthTable) throws RemoteException {
        }
    }

    void onComplete(int i, EuiccRulesAuthTable euiccRulesAuthTable) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetRulesAuthTableCallback {
        private static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IGetRulesAuthTableCallback";
        static final int TRANSACTION_onComplete = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onComplete";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGetRulesAuthTableCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGetRulesAuthTableCallback)) {
                return (IGetRulesAuthTableCallback) iInterfaceQueryLocalInterface;
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
                onComplete(parcel.readInt(), parcel.readInt() != 0 ? EuiccRulesAuthTable.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IGetRulesAuthTableCallback {
            public static IGetRulesAuthTableCallback sDefaultImpl;
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

            @Override // com.android.internal.telephony.euicc.IGetRulesAuthTableCallback
            public void onComplete(int i, EuiccRulesAuthTable euiccRulesAuthTable) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (euiccRulesAuthTable != null) {
                        parcelObtain.writeInt(1);
                        euiccRulesAuthTable.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onComplete(i, euiccRulesAuthTable);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetRulesAuthTableCallback iGetRulesAuthTableCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGetRulesAuthTableCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGetRulesAuthTableCallback;
            return true;
        }

        public static IGetRulesAuthTableCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
