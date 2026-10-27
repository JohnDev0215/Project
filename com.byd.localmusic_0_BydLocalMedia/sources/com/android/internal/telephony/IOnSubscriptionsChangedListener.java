package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IOnSubscriptionsChangedListener extends IInterface {

    public static class Default implements IOnSubscriptionsChangedListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.IOnSubscriptionsChangedListener
        public void onSubscriptionsChanged() throws RemoteException {
        }
    }

    void onSubscriptionsChanged() throws RemoteException;

    public static abstract class Stub extends Binder implements IOnSubscriptionsChangedListener {
        private static final String DESCRIPTOR = "com.android.internal.telephony.IOnSubscriptionsChangedListener";
        static final int TRANSACTION_onSubscriptionsChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onSubscriptionsChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOnSubscriptionsChangedListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOnSubscriptionsChangedListener)) {
                return (IOnSubscriptionsChangedListener) iInterfaceQueryLocalInterface;
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
                onSubscriptionsChanged();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IOnSubscriptionsChangedListener {
            public static IOnSubscriptionsChangedListener sDefaultImpl;
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

            @Override // com.android.internal.telephony.IOnSubscriptionsChangedListener
            public void onSubscriptionsChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSubscriptionsChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOnSubscriptionsChangedListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOnSubscriptionsChangedListener;
            return true;
        }

        public static IOnSubscriptionsChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
