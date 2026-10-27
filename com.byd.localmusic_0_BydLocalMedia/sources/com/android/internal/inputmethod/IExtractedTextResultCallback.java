package com.android.internal.inputmethod;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.inputmethod.ExtractedText;

/* JADX INFO: loaded from: classes4.dex */
public interface IExtractedTextResultCallback extends IInterface {

    public static class Default implements IExtractedTextResultCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.inputmethod.IExtractedTextResultCallback
        public void onResult(ExtractedText extractedText) throws RemoteException {
        }
    }

    void onResult(ExtractedText extractedText) throws RemoteException;

    public static abstract class Stub extends Binder implements IExtractedTextResultCallback {
        private static final String DESCRIPTOR = "com.android.internal.inputmethod.IExtractedTextResultCallback";
        static final int TRANSACTION_onResult = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onResult";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExtractedTextResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IExtractedTextResultCallback)) {
                return (IExtractedTextResultCallback) iInterfaceQueryLocalInterface;
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
                onResult(parcel.readInt() != 0 ? ExtractedText.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IExtractedTextResultCallback {
            public static IExtractedTextResultCallback sDefaultImpl;
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

            @Override // com.android.internal.inputmethod.IExtractedTextResultCallback
            public void onResult(ExtractedText extractedText) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (extractedText != null) {
                        parcelObtain.writeInt(1);
                        extractedText.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResult(extractedText);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExtractedTextResultCallback iExtractedTextResultCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iExtractedTextResultCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iExtractedTextResultCallback;
            return true;
        }

        public static IExtractedTextResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
