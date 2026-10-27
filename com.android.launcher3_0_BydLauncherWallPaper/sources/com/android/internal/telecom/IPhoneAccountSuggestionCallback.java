package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telecom.PhoneAccountSuggestion;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IPhoneAccountSuggestionCallback extends IInterface {

    public static class Default implements IPhoneAccountSuggestionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.IPhoneAccountSuggestionCallback
        public void suggestPhoneAccounts(String str, List<PhoneAccountSuggestion> list) throws RemoteException {
        }
    }

    void suggestPhoneAccounts(String str, List<PhoneAccountSuggestion> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IPhoneAccountSuggestionCallback {
        private static final String DESCRIPTOR = "com.android.internal.telecom.IPhoneAccountSuggestionCallback";
        static final int TRANSACTION_suggestPhoneAccounts = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "suggestPhoneAccounts";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPhoneAccountSuggestionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPhoneAccountSuggestionCallback)) {
                return (IPhoneAccountSuggestionCallback) iInterfaceQueryLocalInterface;
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
                suggestPhoneAccounts(parcel.readString(), parcel.createTypedArrayList(PhoneAccountSuggestion.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IPhoneAccountSuggestionCallback {
            public static IPhoneAccountSuggestionCallback sDefaultImpl;
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

            @Override // com.android.internal.telecom.IPhoneAccountSuggestionCallback
            public void suggestPhoneAccounts(String str, List<PhoneAccountSuggestion> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().suggestPhoneAccounts(str, list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPhoneAccountSuggestionCallback iPhoneAccountSuggestionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPhoneAccountSuggestionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPhoneAccountSuggestionCallback;
            return true;
        }

        public static IPhoneAccountSuggestionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
