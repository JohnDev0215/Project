package android.service.autofill;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.IInlineSuggestionUi;
import android.view.SurfaceControlViewHost;

/* JADX INFO: loaded from: classes3.dex */
public interface IInlineSuggestionUiCallback extends IInterface {

    public static class Default implements IInlineSuggestionUiCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onClick() throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onContent(IInlineSuggestionUi iInlineSuggestionUi, SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onError() throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onLongClick() throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onStartIntentSender(IntentSender intentSender) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUiCallback
        public void onTransferTouchFocusToImeWindow(IBinder iBinder, int i) throws RemoteException {
        }
    }

    void onClick() throws RemoteException;

    void onContent(IInlineSuggestionUi iInlineSuggestionUi, SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException;

    void onError() throws RemoteException;

    void onLongClick() throws RemoteException;

    void onStartIntentSender(IntentSender intentSender) throws RemoteException;

    void onTransferTouchFocusToImeWindow(IBinder iBinder, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IInlineSuggestionUiCallback {
        private static final String DESCRIPTOR = "android.service.autofill.IInlineSuggestionUiCallback";
        static final int TRANSACTION_onClick = 1;
        static final int TRANSACTION_onContent = 3;
        static final int TRANSACTION_onError = 4;
        static final int TRANSACTION_onLongClick = 2;
        static final int TRANSACTION_onStartIntentSender = 6;
        static final int TRANSACTION_onTransferTouchFocusToImeWindow = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onClick";
                case 2:
                    return "onLongClick";
                case 3:
                    return "onContent";
                case 4:
                    return "onError";
                case 5:
                    return "onTransferTouchFocusToImeWindow";
                case 6:
                    return "onStartIntentSender";
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

        public static IInlineSuggestionUiCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInlineSuggestionUiCallback)) {
                return (IInlineSuggestionUiCallback) iInterfaceQueryLocalInterface;
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
                    onClick();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLongClick();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onContent(IInlineSuggestionUi.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onError();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTransferTouchFocusToImeWindow(parcel.readStrongBinder(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStartIntentSender(parcel.readInt() != 0 ? IntentSender.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IInlineSuggestionUiCallback {
            public static IInlineSuggestionUiCallback sDefaultImpl;
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

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onClick() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onClick();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onLongClick() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLongClick();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onContent(IInlineSuggestionUi iInlineSuggestionUi, SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInlineSuggestionUi != null ? iInlineSuggestionUi.asBinder() : null);
                    if (surfacePackage != null) {
                        parcelObtain.writeInt(1);
                        surfacePackage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onContent(iInlineSuggestionUi, surfacePackage, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onError() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onTransferTouchFocusToImeWindow(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTransferTouchFocusToImeWindow(iBinder, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUiCallback
            public void onStartIntentSender(IntentSender intentSender) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intentSender != null) {
                        parcelObtain.writeInt(1);
                        intentSender.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartIntentSender(intentSender);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionUiCallback iInlineSuggestionUiCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInlineSuggestionUiCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInlineSuggestionUiCallback;
            return true;
        }

        public static IInlineSuggestionUiCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
