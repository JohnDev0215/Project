package android.service.contentsuggestions;

import android.app.contentsuggestions.ClassificationsRequest;
import android.app.contentsuggestions.IClassificationsCallback;
import android.app.contentsuggestions.ISelectionsCallback;
import android.app.contentsuggestions.SelectionsRequest;
import android.graphics.GraphicBuffer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IContentSuggestionsService extends IInterface {

    public static class Default implements IContentSuggestionsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void classifyContentSelections(ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) throws RemoteException {
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void notifyInteraction(String str, Bundle bundle) throws RemoteException {
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void provideContextImage(int i, GraphicBuffer graphicBuffer, int i2, Bundle bundle) throws RemoteException {
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void suggestContentSelections(SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) throws RemoteException {
        }
    }

    void classifyContentSelections(ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) throws RemoteException;

    void notifyInteraction(String str, Bundle bundle) throws RemoteException;

    void provideContextImage(int i, GraphicBuffer graphicBuffer, int i2, Bundle bundle) throws RemoteException;

    void suggestContentSelections(SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentSuggestionsService {
        private static final String DESCRIPTOR = "android.service.contentsuggestions.IContentSuggestionsService";
        static final int TRANSACTION_classifyContentSelections = 3;
        static final int TRANSACTION_notifyInteraction = 4;
        static final int TRANSACTION_provideContextImage = 1;
        static final int TRANSACTION_suggestContentSelections = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "provideContextImage";
            }
            if (i == 2) {
                return "suggestContentSelections";
            }
            if (i == 3) {
                return "classifyContentSelections";
            }
            if (i != 4) {
                return null;
            }
            return "notifyInteraction";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContentSuggestionsService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentSuggestionsService)) {
                return (IContentSuggestionsService) iInterfaceQueryLocalInterface;
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
                provideContextImage(parcel.readInt(), parcel.readInt() != 0 ? GraphicBuffer.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                suggestContentSelections(parcel.readInt() != 0 ? SelectionsRequest.CREATOR.createFromParcel(parcel) : null, ISelectionsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                classifyContentSelections(parcel.readInt() != 0 ? ClassificationsRequest.CREATOR.createFromParcel(parcel) : null, IClassificationsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyInteraction(parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IContentSuggestionsService {
            public static IContentSuggestionsService sDefaultImpl;
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

            @Override // android.service.contentsuggestions.IContentSuggestionsService
            public void provideContextImage(int i, GraphicBuffer graphicBuffer, int i2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (graphicBuffer != null) {
                        parcelObtain.writeInt(1);
                        graphicBuffer.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
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
                    Stub.getDefaultImpl().provideContextImage(i, graphicBuffer, i2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentsuggestions.IContentSuggestionsService
            public void suggestContentSelections(SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (selectionsRequest != null) {
                        parcelObtain.writeInt(1);
                        selectionsRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iSelectionsCallback != null ? iSelectionsCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().suggestContentSelections(selectionsRequest, iSelectionsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentsuggestions.IContentSuggestionsService
            public void classifyContentSelections(ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (classificationsRequest != null) {
                        parcelObtain.writeInt(1);
                        classificationsRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iClassificationsCallback != null ? iClassificationsCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().classifyContentSelections(classificationsRequest, iClassificationsCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.contentsuggestions.IContentSuggestionsService
            public void notifyInteraction(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyInteraction(str, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentSuggestionsService iContentSuggestionsService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentSuggestionsService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentSuggestionsService;
            return true;
        }

        public static IContentSuggestionsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
