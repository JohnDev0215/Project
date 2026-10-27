package android.app.contentsuggestions;

import android.app.contentsuggestions.IClassificationsCallback;
import android.app.contentsuggestions.ISelectionsCallback;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.os.IResultReceiver;

/* JADX INFO: loaded from: classes.dex */
public interface IContentSuggestionsManager extends IInterface {

    public static class Default implements IContentSuggestionsManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void classifyContentSelections(int i, ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void isEnabled(int i, IResultReceiver iResultReceiver) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void notifyInteraction(int i, String str, Bundle bundle) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void provideContextBitmap(int i, Bitmap bitmap, Bundle bundle) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void provideContextImage(int i, int i2, Bundle bundle) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void suggestContentSelections(int i, SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) throws RemoteException {
        }
    }

    void classifyContentSelections(int i, ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) throws RemoteException;

    void isEnabled(int i, IResultReceiver iResultReceiver) throws RemoteException;

    void notifyInteraction(int i, String str, Bundle bundle) throws RemoteException;

    void provideContextBitmap(int i, Bitmap bitmap, Bundle bundle) throws RemoteException;

    void provideContextImage(int i, int i2, Bundle bundle) throws RemoteException;

    void suggestContentSelections(int i, SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentSuggestionsManager {
        private static final String DESCRIPTOR = "android.app.contentsuggestions.IContentSuggestionsManager";
        static final int TRANSACTION_classifyContentSelections = 4;
        static final int TRANSACTION_isEnabled = 6;
        static final int TRANSACTION_notifyInteraction = 5;
        static final int TRANSACTION_provideContextBitmap = 2;
        static final int TRANSACTION_provideContextImage = 1;
        static final int TRANSACTION_suggestContentSelections = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "provideContextImage";
                case 2:
                    return "provideContextBitmap";
                case 3:
                    return "suggestContentSelections";
                case 4:
                    return "classifyContentSelections";
                case 5:
                    return "notifyInteraction";
                case 6:
                    return "isEnabled";
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

        public static IContentSuggestionsManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentSuggestionsManager)) {
                return (IContentSuggestionsManager) iInterfaceQueryLocalInterface;
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
                    provideContextImage(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    provideContextBitmap(parcel.readInt(), parcel.readInt() != 0 ? Bitmap.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    suggestContentSelections(parcel.readInt(), parcel.readInt() != 0 ? SelectionsRequest.CREATOR.createFromParcel(parcel) : null, ISelectionsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    classifyContentSelections(parcel.readInt(), parcel.readInt() != 0 ? ClassificationsRequest.CREATOR.createFromParcel(parcel) : null, IClassificationsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyInteraction(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    isEnabled(parcel.readInt(), IResultReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IContentSuggestionsManager {
            public static IContentSuggestionsManager sDefaultImpl;
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

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void provideContextImage(int i, int i2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().provideContextImage(i, i2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void provideContextBitmap(int i, Bitmap bitmap, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (bitmap != null) {
                        parcelObtain.writeInt(1);
                        bitmap.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().provideContextBitmap(i, bitmap, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void suggestContentSelections(int i, SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (selectionsRequest != null) {
                        parcelObtain.writeInt(1);
                        selectionsRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iSelectionsCallback != null ? iSelectionsCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().suggestContentSelections(i, selectionsRequest, iSelectionsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void classifyContentSelections(int i, ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (classificationsRequest != null) {
                        parcelObtain.writeInt(1);
                        classificationsRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iClassificationsCallback != null ? iClassificationsCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().classifyContentSelections(i, classificationsRequest, iClassificationsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void notifyInteraction(int i, String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyInteraction(i, str, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void isEnabled(int i, IResultReceiver iResultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().isEnabled(i, iResultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentSuggestionsManager iContentSuggestionsManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentSuggestionsManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentSuggestionsManager;
            return true;
        }

        public static IContentSuggestionsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
