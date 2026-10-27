package com.android.internal.inputmethod;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.inputmethod.IInputContentUriToken;

/* JADX INFO: loaded from: classes4.dex */
public interface IInputMethodPrivilegedOperations extends IInterface {

    public static class Default implements IInputMethodPrivilegedOperations {
        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void applyImeVisibility(IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public IInputContentUriToken createInputContentUriToken(Uri uri, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void hideMySoftInput(int i) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void notifyUserAction() throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void reportFullscreenMode(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void reportPreRendered(EditorInfo editorInfo) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void reportStartInput(IBinder iBinder) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void setImeWindowStatus(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void setInputMethod(String str) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void setInputMethodAndSubtype(String str, InputMethodSubtype inputMethodSubtype) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public boolean shouldOfferSwitchingToNextInputMethod() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void showMySoftInput(int i) throws RemoteException {
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public boolean switchToNextInputMethod(boolean z) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public boolean switchToPreviousInputMethod() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
        public void updateStatusIcon(String str, int i) throws RemoteException {
        }
    }

    void applyImeVisibility(IBinder iBinder, boolean z) throws RemoteException;

    IInputContentUriToken createInputContentUriToken(Uri uri, String str) throws RemoteException;

    void hideMySoftInput(int i) throws RemoteException;

    void notifyUserAction() throws RemoteException;

    void reportFullscreenMode(boolean z) throws RemoteException;

    void reportPreRendered(EditorInfo editorInfo) throws RemoteException;

    void reportStartInput(IBinder iBinder) throws RemoteException;

    void setImeWindowStatus(int i, int i2) throws RemoteException;

    void setInputMethod(String str) throws RemoteException;

    void setInputMethodAndSubtype(String str, InputMethodSubtype inputMethodSubtype) throws RemoteException;

    boolean shouldOfferSwitchingToNextInputMethod() throws RemoteException;

    void showMySoftInput(int i) throws RemoteException;

    boolean switchToNextInputMethod(boolean z) throws RemoteException;

    boolean switchToPreviousInputMethod() throws RemoteException;

    void updateStatusIcon(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IInputMethodPrivilegedOperations {
        private static final String DESCRIPTOR = "com.android.internal.inputmethod.IInputMethodPrivilegedOperations";
        static final int TRANSACTION_applyImeVisibility = 15;
        static final int TRANSACTION_createInputContentUriToken = 3;
        static final int TRANSACTION_hideMySoftInput = 7;
        static final int TRANSACTION_notifyUserAction = 13;
        static final int TRANSACTION_reportFullscreenMode = 4;
        static final int TRANSACTION_reportPreRendered = 14;
        static final int TRANSACTION_reportStartInput = 2;
        static final int TRANSACTION_setImeWindowStatus = 1;
        static final int TRANSACTION_setInputMethod = 5;
        static final int TRANSACTION_setInputMethodAndSubtype = 6;
        static final int TRANSACTION_shouldOfferSwitchingToNextInputMethod = 12;
        static final int TRANSACTION_showMySoftInput = 8;
        static final int TRANSACTION_switchToNextInputMethod = 11;
        static final int TRANSACTION_switchToPreviousInputMethod = 10;
        static final int TRANSACTION_updateStatusIcon = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setImeWindowStatus";
                case 2:
                    return "reportStartInput";
                case 3:
                    return "createInputContentUriToken";
                case 4:
                    return "reportFullscreenMode";
                case 5:
                    return "setInputMethod";
                case 6:
                    return "setInputMethodAndSubtype";
                case 7:
                    return "hideMySoftInput";
                case 8:
                    return "showMySoftInput";
                case 9:
                    return "updateStatusIcon";
                case 10:
                    return "switchToPreviousInputMethod";
                case 11:
                    return "switchToNextInputMethod";
                case 12:
                    return "shouldOfferSwitchingToNextInputMethod";
                case 13:
                    return "notifyUserAction";
                case 14:
                    return "reportPreRendered";
                case 15:
                    return "applyImeVisibility";
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

        public static IInputMethodPrivilegedOperations asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInputMethodPrivilegedOperations)) {
                return (IInputMethodPrivilegedOperations) iInterfaceQueryLocalInterface;
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
                    setImeWindowStatus(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportStartInput(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IInputContentUriToken iInputContentUriTokenCreateInputContentUriToken = createInputContentUriToken(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iInputContentUriTokenCreateInputContentUriToken != null ? iInputContentUriTokenCreateInputContentUriToken.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportFullscreenMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInputMethod(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInputMethodAndSubtype(parcel.readString(), parcel.readInt() != 0 ? InputMethodSubtype.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideMySoftInput(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    showMySoftInput(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateStatusIcon(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSwitchToPreviousInputMethod = switchToPreviousInputMethod();
                    parcel2.writeNoException();
                    parcel2.writeInt(zSwitchToPreviousInputMethod ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSwitchToNextInputMethod = switchToNextInputMethod(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zSwitchToNextInputMethod ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldOfferSwitchingToNextInputMethod = shouldOfferSwitchingToNextInputMethod();
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldOfferSwitchingToNextInputMethod ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyUserAction();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportPreRendered(parcel.readInt() != 0 ? EditorInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyImeVisibility(parcel.readStrongBinder(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IInputMethodPrivilegedOperations {
            public static IInputMethodPrivilegedOperations sDefaultImpl;
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

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void setImeWindowStatus(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setImeWindowStatus(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void reportStartInput(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportStartInput(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public IInputContentUriToken createInputContentUriToken(Uri uri, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createInputContentUriToken(uri, str);
                    }
                    parcelObtain2.readException();
                    return IInputContentUriToken.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void reportFullscreenMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportFullscreenMode(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void setInputMethod(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInputMethod(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void setInputMethodAndSubtype(String str, InputMethodSubtype inputMethodSubtype) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (inputMethodSubtype != null) {
                        parcelObtain.writeInt(1);
                        inputMethodSubtype.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInputMethodAndSubtype(str, inputMethodSubtype);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void hideMySoftInput(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideMySoftInput(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void showMySoftInput(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showMySoftInput(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void updateStatusIcon(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateStatusIcon(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public boolean switchToPreviousInputMethod() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchToPreviousInputMethod();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public boolean switchToNextInputMethod(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchToNextInputMethod(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public boolean shouldOfferSwitchingToNextInputMethod() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldOfferSwitchingToNextInputMethod();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void notifyUserAction() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyUserAction();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void reportPreRendered(EditorInfo editorInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (editorInfo != null) {
                        parcelObtain.writeInt(1);
                        editorInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportPreRendered(editorInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.inputmethod.IInputMethodPrivilegedOperations
            public void applyImeVisibility(IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyImeVisibility(iBinder, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputMethodPrivilegedOperations iInputMethodPrivilegedOperations) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInputMethodPrivilegedOperations == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInputMethodPrivilegedOperations;
            return true;
        }

        public static IInputMethodPrivilegedOperations getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
