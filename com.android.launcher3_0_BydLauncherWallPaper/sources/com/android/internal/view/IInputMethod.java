package com.android.internal.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.view.InputChannel;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.inputmethod.IInputMethodPrivilegedOperations;
import com.android.internal.view.IInlineSuggestionsRequestCallback;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethodSession;
import com.android.internal.view.IInputSessionCallback;

/* JADX INFO: loaded from: classes4.dex */
public interface IInputMethod extends IInterface {

    public static class Default implements IInputMethod {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.view.IInputMethod
        public void bindInput(InputBinding inputBinding) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void changeInputMethodSubtype(InputMethodSubtype inputMethodSubtype) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void createSession(InputChannel inputChannel, IInputSessionCallback iInputSessionCallback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void hideSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void initializeInternal(IBinder iBinder, int i, IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void onCreateInlineSuggestionsRequest(InlineSuggestionsRequestInfo inlineSuggestionsRequestInfo, IInlineSuggestionsRequestCallback iInlineSuggestionsRequestCallback) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void revokeSession(IInputMethodSession iInputMethodSession) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void setSessionEnabled(IInputMethodSession iInputMethodSession, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void showSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void startInput(IBinder iBinder, IInputContext iInputContext, int i, EditorInfo editorInfo, boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.android.internal.view.IInputMethod
        public void unbindInput() throws RemoteException {
        }
    }

    void bindInput(InputBinding inputBinding) throws RemoteException;

    void changeInputMethodSubtype(InputMethodSubtype inputMethodSubtype) throws RemoteException;

    void createSession(InputChannel inputChannel, IInputSessionCallback iInputSessionCallback) throws RemoteException;

    void hideSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException;

    void initializeInternal(IBinder iBinder, int i, IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) throws RemoteException;

    void onCreateInlineSuggestionsRequest(InlineSuggestionsRequestInfo inlineSuggestionsRequestInfo, IInlineSuggestionsRequestCallback iInlineSuggestionsRequestCallback) throws RemoteException;

    void revokeSession(IInputMethodSession iInputMethodSession) throws RemoteException;

    void setSessionEnabled(IInputMethodSession iInputMethodSession, boolean z) throws RemoteException;

    void showSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException;

    void startInput(IBinder iBinder, IInputContext iInputContext, int i, EditorInfo editorInfo, boolean z, boolean z2) throws RemoteException;

    void unbindInput() throws RemoteException;

    public static abstract class Stub extends Binder implements IInputMethod {
        private static final String DESCRIPTOR = "com.android.internal.view.IInputMethod";
        static final int TRANSACTION_bindInput = 3;
        static final int TRANSACTION_changeInputMethodSubtype = 11;
        static final int TRANSACTION_createSession = 6;
        static final int TRANSACTION_hideSoftInput = 10;
        static final int TRANSACTION_initializeInternal = 1;
        static final int TRANSACTION_onCreateInlineSuggestionsRequest = 2;
        static final int TRANSACTION_revokeSession = 8;
        static final int TRANSACTION_setSessionEnabled = 7;
        static final int TRANSACTION_showSoftInput = 9;
        static final int TRANSACTION_startInput = 5;
        static final int TRANSACTION_unbindInput = 4;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "initializeInternal";
                case 2:
                    return "onCreateInlineSuggestionsRequest";
                case 3:
                    return "bindInput";
                case 4:
                    return "unbindInput";
                case 5:
                    return "startInput";
                case 6:
                    return "createSession";
                case 7:
                    return "setSessionEnabled";
                case 8:
                    return "revokeSession";
                case 9:
                    return "showSoftInput";
                case 10:
                    return "hideSoftInput";
                case 11:
                    return "changeInputMethodSubtype";
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

        public static IInputMethod asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInputMethod)) {
                return (IInputMethod) iInterfaceQueryLocalInterface;
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
                    initializeInternal(parcel.readStrongBinder(), parcel.readInt(), IInputMethodPrivilegedOperations.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCreateInlineSuggestionsRequest(parcel.readInt() != 0 ? InlineSuggestionsRequestInfo.CREATOR.createFromParcel(parcel) : null, IInlineSuggestionsRequestCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    bindInput(parcel.readInt() != 0 ? InputBinding.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    unbindInput();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    startInput(parcel.readStrongBinder(), IInputContext.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? EditorInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    createSession(parcel.readInt() != 0 ? InputChannel.CREATOR.createFromParcel(parcel) : null, IInputSessionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSessionEnabled(IInputMethodSession.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeSession(IInputMethodSession.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    showSoftInput(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideSoftInput(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    changeInputMethodSubtype(parcel.readInt() != 0 ? InputMethodSubtype.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IInputMethod {
            public static IInputMethod sDefaultImpl;
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

            @Override // com.android.internal.view.IInputMethod
            public void initializeInternal(IBinder iBinder, int i, IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iInputMethodPrivilegedOperations != null ? iInputMethodPrivilegedOperations.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().initializeInternal(iBinder, i, iInputMethodPrivilegedOperations);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void onCreateInlineSuggestionsRequest(InlineSuggestionsRequestInfo inlineSuggestionsRequestInfo, IInlineSuggestionsRequestCallback iInlineSuggestionsRequestCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inlineSuggestionsRequestInfo != null) {
                        parcelObtain.writeInt(1);
                        inlineSuggestionsRequestInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iInlineSuggestionsRequestCallback != null ? iInlineSuggestionsRequestCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCreateInlineSuggestionsRequest(inlineSuggestionsRequestInfo, iInlineSuggestionsRequestCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void bindInput(InputBinding inputBinding) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputBinding != null) {
                        parcelObtain.writeInt(1);
                        inputBinding.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().bindInput(inputBinding);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void unbindInput() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unbindInput();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void startInput(IBinder iBinder, IInputContext iInputContext, int i, EditorInfo editorInfo, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iInputContext != null ? iInputContext.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (editorInfo != null) {
                        parcelObtain.writeInt(1);
                        editorInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startInput(iBinder, iInputContext, i, editorInfo, z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void createSession(InputChannel inputChannel, IInputSessionCallback iInputSessionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputChannel != null) {
                        parcelObtain.writeInt(1);
                        inputChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iInputSessionCallback != null ? iInputSessionCallback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createSession(inputChannel, iInputSessionCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void setSessionEnabled(IInputMethodSession iInputMethodSession, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodSession != null ? iInputMethodSession.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setSessionEnabled(iInputMethodSession, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void revokeSession(IInputMethodSession iInputMethodSession) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInputMethodSession != null ? iInputMethodSession.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().revokeSession(iInputMethodSession);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void showSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showSoftInput(iBinder, i, resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void hideSoftInput(IBinder iBinder, int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideSoftInput(iBinder, i, resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.IInputMethod
            public void changeInputMethodSubtype(InputMethodSubtype inputMethodSubtype) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputMethodSubtype != null) {
                        parcelObtain.writeInt(1);
                        inputMethodSubtype.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().changeInputMethodSubtype(inputMethodSubtype);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputMethod iInputMethod) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInputMethod == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInputMethod;
            return true;
        }

        public static IInputMethod getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
