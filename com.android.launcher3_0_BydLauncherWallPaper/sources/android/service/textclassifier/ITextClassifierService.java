package android.service.textclassifier;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.textclassifier.ITextClassifierCallback;
import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationSessionId;
import android.view.textclassifier.TextClassifierEvent;
import android.view.textclassifier.TextLanguage;
import android.view.textclassifier.TextLinks;
import android.view.textclassifier.TextSelection;

/* JADX INFO: loaded from: classes3.dex */
public interface ITextClassifierService extends IInterface {

    public static class Default implements ITextClassifierService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onClassifyText(TextClassificationSessionId textClassificationSessionId, TextClassification.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onConnectedStateChanged(int i) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onCreateTextClassificationSession(TextClassificationContext textClassificationContext, TextClassificationSessionId textClassificationSessionId) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onDestroyTextClassificationSession(TextClassificationSessionId textClassificationSessionId) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onDetectLanguage(TextClassificationSessionId textClassificationSessionId, TextLanguage.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onGenerateLinks(TextClassificationSessionId textClassificationSessionId, TextLinks.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onSelectionEvent(TextClassificationSessionId textClassificationSessionId, SelectionEvent selectionEvent) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onSuggestConversationActions(TextClassificationSessionId textClassificationSessionId, ConversationActions.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onSuggestSelection(TextClassificationSessionId textClassificationSessionId, TextSelection.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
        }

        @Override // android.service.textclassifier.ITextClassifierService
        public void onTextClassifierEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent textClassifierEvent) throws RemoteException {
        }
    }

    void onClassifyText(TextClassificationSessionId textClassificationSessionId, TextClassification.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException;

    void onConnectedStateChanged(int i) throws RemoteException;

    void onCreateTextClassificationSession(TextClassificationContext textClassificationContext, TextClassificationSessionId textClassificationSessionId) throws RemoteException;

    void onDestroyTextClassificationSession(TextClassificationSessionId textClassificationSessionId) throws RemoteException;

    void onDetectLanguage(TextClassificationSessionId textClassificationSessionId, TextLanguage.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException;

    void onGenerateLinks(TextClassificationSessionId textClassificationSessionId, TextLinks.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException;

    void onSelectionEvent(TextClassificationSessionId textClassificationSessionId, SelectionEvent selectionEvent) throws RemoteException;

    void onSuggestConversationActions(TextClassificationSessionId textClassificationSessionId, ConversationActions.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException;

    void onSuggestSelection(TextClassificationSessionId textClassificationSessionId, TextSelection.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException;

    void onTextClassifierEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent textClassifierEvent) throws RemoteException;

    public static abstract class Stub extends Binder implements ITextClassifierService {
        private static final String DESCRIPTOR = "android.service.textclassifier.ITextClassifierService";
        static final int TRANSACTION_onClassifyText = 2;
        static final int TRANSACTION_onConnectedStateChanged = 10;
        static final int TRANSACTION_onCreateTextClassificationSession = 6;
        static final int TRANSACTION_onDestroyTextClassificationSession = 7;
        static final int TRANSACTION_onDetectLanguage = 8;
        static final int TRANSACTION_onGenerateLinks = 3;
        static final int TRANSACTION_onSelectionEvent = 4;
        static final int TRANSACTION_onSuggestConversationActions = 9;
        static final int TRANSACTION_onSuggestSelection = 1;
        static final int TRANSACTION_onTextClassifierEvent = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onSuggestSelection";
                case 2:
                    return "onClassifyText";
                case 3:
                    return "onGenerateLinks";
                case 4:
                    return "onSelectionEvent";
                case 5:
                    return "onTextClassifierEvent";
                case 6:
                    return "onCreateTextClassificationSession";
                case 7:
                    return "onDestroyTextClassificationSession";
                case 8:
                    return "onDetectLanguage";
                case 9:
                    return "onSuggestConversationActions";
                case 10:
                    return "onConnectedStateChanged";
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

        public static ITextClassifierService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITextClassifierService)) {
                return (ITextClassifierService) iInterfaceQueryLocalInterface;
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
                    onSuggestSelection(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextSelection.Request.CREATOR.createFromParcel(parcel) : null, ITextClassifierCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onClassifyText(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextClassification.Request.CREATOR.createFromParcel(parcel) : null, ITextClassifierCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onGenerateLinks(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextLinks.Request.CREATOR.createFromParcel(parcel) : null, ITextClassifierCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSelectionEvent(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? SelectionEvent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTextClassifierEvent(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextClassifierEvent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCreateTextClassificationSession(parcel.readInt() != 0 ? TextClassificationContext.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDestroyTextClassificationSession(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDetectLanguage(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TextLanguage.Request.CREATOR.createFromParcel(parcel) : null, ITextClassifierCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSuggestConversationActions(parcel.readInt() != 0 ? TextClassificationSessionId.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ConversationActions.Request.CREATOR.createFromParcel(parcel) : null, ITextClassifierCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConnectedStateChanged(parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITextClassifierService {
            public static ITextClassifierService sDefaultImpl;
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

            @Override // android.service.textclassifier.ITextClassifierService
            public void onSuggestSelection(TextClassificationSessionId textClassificationSessionId, TextSelection.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (request != null) {
                        parcelObtain.writeInt(1);
                        request.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iTextClassifierCallback != null ? iTextClassifierCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSuggestSelection(textClassificationSessionId, request, iTextClassifierCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onClassifyText(TextClassificationSessionId textClassificationSessionId, TextClassification.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (request != null) {
                        parcelObtain.writeInt(1);
                        request.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iTextClassifierCallback != null ? iTextClassifierCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onClassifyText(textClassificationSessionId, request, iTextClassifierCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onGenerateLinks(TextClassificationSessionId textClassificationSessionId, TextLinks.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (request != null) {
                        parcelObtain.writeInt(1);
                        request.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iTextClassifierCallback != null ? iTextClassifierCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGenerateLinks(textClassificationSessionId, request, iTextClassifierCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onSelectionEvent(TextClassificationSessionId textClassificationSessionId, SelectionEvent selectionEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (selectionEvent != null) {
                        parcelObtain.writeInt(1);
                        selectionEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSelectionEvent(textClassificationSessionId, selectionEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onTextClassifierEvent(TextClassificationSessionId textClassificationSessionId, TextClassifierEvent textClassifierEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (textClassifierEvent != null) {
                        parcelObtain.writeInt(1);
                        textClassifierEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTextClassifierEvent(textClassificationSessionId, textClassifierEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onCreateTextClassificationSession(TextClassificationContext textClassificationContext, TextClassificationSessionId textClassificationSessionId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationContext != null) {
                        parcelObtain.writeInt(1);
                        textClassificationContext.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCreateTextClassificationSession(textClassificationContext, textClassificationSessionId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onDestroyTextClassificationSession(TextClassificationSessionId textClassificationSessionId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDestroyTextClassificationSession(textClassificationSessionId);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onDetectLanguage(TextClassificationSessionId textClassificationSessionId, TextLanguage.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (request != null) {
                        parcelObtain.writeInt(1);
                        request.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iTextClassifierCallback != null ? iTextClassifierCallback.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDetectLanguage(textClassificationSessionId, request, iTextClassifierCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onSuggestConversationActions(TextClassificationSessionId textClassificationSessionId, ConversationActions.Request request, ITextClassifierCallback iTextClassifierCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (textClassificationSessionId != null) {
                        parcelObtain.writeInt(1);
                        textClassificationSessionId.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (request != null) {
                        parcelObtain.writeInt(1);
                        request.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iTextClassifierCallback != null ? iTextClassifierCallback.asBinder() : null);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSuggestConversationActions(textClassificationSessionId, request, iTextClassifierCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.textclassifier.ITextClassifierService
            public void onConnectedStateChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectedStateChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITextClassifierService iTextClassifierService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTextClassifierService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTextClassifierService;
            return true;
        }

        public static ITextClassifierService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
