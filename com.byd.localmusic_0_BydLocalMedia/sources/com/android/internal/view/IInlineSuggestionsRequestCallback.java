package com.android.internal.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.autofill.AutofillId;
import android.view.inputmethod.InlineSuggestionsRequest;
import com.android.internal.view.IInlineSuggestionsResponseCallback;

/* JADX INFO: loaded from: classes4.dex */
public interface IInlineSuggestionsRequestCallback extends IInterface {

    public static class Default implements IInlineSuggestionsRequestCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInlineSuggestionsRequest(InlineSuggestionsRequest inlineSuggestionsRequest, IInlineSuggestionsResponseCallback iInlineSuggestionsResponseCallback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInlineSuggestionsSessionInvalidated() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInlineSuggestionsUnsupported() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodFinishInput() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodFinishInputView() throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodShowInputRequested(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodStartInput(AutofillId autofillId) throws RemoteException {
        }

        @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
        public void onInputMethodStartInputView() throws RemoteException {
        }
    }

    void onInlineSuggestionsRequest(InlineSuggestionsRequest inlineSuggestionsRequest, IInlineSuggestionsResponseCallback iInlineSuggestionsResponseCallback) throws RemoteException;

    void onInlineSuggestionsSessionInvalidated() throws RemoteException;

    void onInlineSuggestionsUnsupported() throws RemoteException;

    void onInputMethodFinishInput() throws RemoteException;

    void onInputMethodFinishInputView() throws RemoteException;

    void onInputMethodShowInputRequested(boolean z) throws RemoteException;

    void onInputMethodStartInput(AutofillId autofillId) throws RemoteException;

    void onInputMethodStartInputView() throws RemoteException;

    public static abstract class Stub extends Binder implements IInlineSuggestionsRequestCallback {
        private static final String DESCRIPTOR = "com.android.internal.view.IInlineSuggestionsRequestCallback";
        static final int TRANSACTION_onInlineSuggestionsRequest = 2;
        static final int TRANSACTION_onInlineSuggestionsSessionInvalidated = 8;
        static final int TRANSACTION_onInlineSuggestionsUnsupported = 1;
        static final int TRANSACTION_onInputMethodFinishInput = 7;
        static final int TRANSACTION_onInputMethodFinishInputView = 6;
        static final int TRANSACTION_onInputMethodShowInputRequested = 4;
        static final int TRANSACTION_onInputMethodStartInput = 3;
        static final int TRANSACTION_onInputMethodStartInputView = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onInlineSuggestionsUnsupported";
                case 2:
                    return "onInlineSuggestionsRequest";
                case 3:
                    return "onInputMethodStartInput";
                case 4:
                    return "onInputMethodShowInputRequested";
                case 5:
                    return "onInputMethodStartInputView";
                case 6:
                    return "onInputMethodFinishInputView";
                case 7:
                    return "onInputMethodFinishInput";
                case 8:
                    return "onInlineSuggestionsSessionInvalidated";
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

        public static IInlineSuggestionsRequestCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInlineSuggestionsRequestCallback)) {
                return (IInlineSuggestionsRequestCallback) iInterfaceQueryLocalInterface;
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
                    onInlineSuggestionsUnsupported();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInlineSuggestionsRequest(parcel.readInt() != 0 ? InlineSuggestionsRequest.CREATOR.createFromParcel(parcel) : null, IInlineSuggestionsResponseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInputMethodStartInput(parcel.readInt() != 0 ? AutofillId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInputMethodShowInputRequested(parcel.readInt() != 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInputMethodStartInputView();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInputMethodFinishInputView();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInputMethodFinishInput();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInlineSuggestionsSessionInvalidated();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IInlineSuggestionsRequestCallback {
            public static IInlineSuggestionsRequestCallback sDefaultImpl;
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

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInlineSuggestionsUnsupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInlineSuggestionsUnsupported();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInlineSuggestionsRequest(InlineSuggestionsRequest inlineSuggestionsRequest, IInlineSuggestionsResponseCallback iInlineSuggestionsResponseCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inlineSuggestionsRequest != null) {
                        parcelObtain.writeInt(1);
                        inlineSuggestionsRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iInlineSuggestionsResponseCallback != null ? iInlineSuggestionsResponseCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInlineSuggestionsRequest(inlineSuggestionsRequest, iInlineSuggestionsResponseCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodStartInput(AutofillId autofillId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (autofillId != null) {
                        parcelObtain.writeInt(1);
                        autofillId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputMethodStartInput(autofillId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodShowInputRequested(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputMethodShowInputRequested(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodStartInputView() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputMethodStartInputView();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodFinishInputView() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputMethodFinishInputView();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInputMethodFinishInput() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputMethodFinishInput();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInlineSuggestionsRequestCallback
            public void onInlineSuggestionsSessionInvalidated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInlineSuggestionsSessionInvalidated();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionsRequestCallback iInlineSuggestionsRequestCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInlineSuggestionsRequestCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInlineSuggestionsRequestCallback;
            return true;
        }

        public static IInlineSuggestionsRequestCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
