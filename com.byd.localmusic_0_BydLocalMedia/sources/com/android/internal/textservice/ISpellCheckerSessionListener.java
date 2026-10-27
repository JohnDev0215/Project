package com.android.internal.textservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SuggestionsInfo;

/* JADX INFO: loaded from: classes4.dex */
public interface ISpellCheckerSessionListener extends IInterface {

    public static class Default implements ISpellCheckerSessionListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.textservice.ISpellCheckerSessionListener
        public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) throws RemoteException {
        }

        @Override // com.android.internal.textservice.ISpellCheckerSessionListener
        public void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) throws RemoteException {
        }
    }

    void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) throws RemoteException;

    void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ISpellCheckerSessionListener {
        private static final String DESCRIPTOR = "com.android.internal.textservice.ISpellCheckerSessionListener";
        static final int TRANSACTION_onGetSentenceSuggestions = 2;
        static final int TRANSACTION_onGetSuggestions = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onGetSuggestions";
            }
            if (i != 2) {
                return null;
            }
            return "onGetSentenceSuggestions";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISpellCheckerSessionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISpellCheckerSessionListener)) {
                return (ISpellCheckerSessionListener) iInterfaceQueryLocalInterface;
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
                onGetSuggestions((SuggestionsInfo[]) parcel.createTypedArray(SuggestionsInfo.CREATOR));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetSentenceSuggestions((SentenceSuggestionsInfo[]) parcel.createTypedArray(SentenceSuggestionsInfo.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISpellCheckerSessionListener {
            public static ISpellCheckerSessionListener sDefaultImpl;
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

            @Override // com.android.internal.textservice.ISpellCheckerSessionListener
            public void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(suggestionsInfoArr, 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetSuggestions(suggestionsInfoArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.textservice.ISpellCheckerSessionListener
            public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(sentenceSuggestionsInfoArr, 0);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetSentenceSuggestions(sentenceSuggestionsInfoArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISpellCheckerSessionListener iSpellCheckerSessionListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSpellCheckerSessionListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSpellCheckerSessionListener;
            return true;
        }

        public static ISpellCheckerSessionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
