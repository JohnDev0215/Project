package android.service.autofill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.service.autofill.IInlineSuggestionUiCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IInlineSuggestionRenderService extends IInterface {

    public static class Default implements IInlineSuggestionRenderService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.autofill.IInlineSuggestionRenderService
        public void destroySuggestionViews(int i, int i2) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionRenderService
        public void getInlineSuggestionsRendererInfo(RemoteCallback remoteCallback) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionRenderService
        public void renderSuggestion(IInlineSuggestionUiCallback iInlineSuggestionUiCallback, InlinePresentation inlinePresentation, int i, int i2, IBinder iBinder, int i3, int i4, int i5) throws RemoteException {
        }
    }

    void destroySuggestionViews(int i, int i2) throws RemoteException;

    void getInlineSuggestionsRendererInfo(RemoteCallback remoteCallback) throws RemoteException;

    void renderSuggestion(IInlineSuggestionUiCallback iInlineSuggestionUiCallback, InlinePresentation inlinePresentation, int i, int i2, IBinder iBinder, int i3, int i4, int i5) throws RemoteException;

    public static abstract class Stub extends Binder implements IInlineSuggestionRenderService {
        private static final String DESCRIPTOR = "android.service.autofill.IInlineSuggestionRenderService";
        static final int TRANSACTION_destroySuggestionViews = 3;
        static final int TRANSACTION_getInlineSuggestionsRendererInfo = 2;
        static final int TRANSACTION_renderSuggestion = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "renderSuggestion";
            }
            if (i == 2) {
                return "getInlineSuggestionsRendererInfo";
            }
            if (i != 3) {
                return null;
            }
            return "destroySuggestionViews";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInlineSuggestionRenderService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInlineSuggestionRenderService)) {
                return (IInlineSuggestionRenderService) iInterfaceQueryLocalInterface;
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
                renderSuggestion(IInlineSuggestionUiCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? InlinePresentation.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                getInlineSuggestionsRendererInfo(parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                destroySuggestionViews(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInlineSuggestionRenderService {
            public static IInlineSuggestionRenderService sDefaultImpl;
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

            @Override // android.service.autofill.IInlineSuggestionRenderService
            public void renderSuggestion(IInlineSuggestionUiCallback iInlineSuggestionUiCallback, InlinePresentation inlinePresentation, int i, int i2, IBinder iBinder, int i3, int i4, int i5) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInlineSuggestionUiCallback != null ? iInlineSuggestionUiCallback.asBinder() : null);
                    if (inlinePresentation != null) {
                        parcelObtain.writeInt(1);
                        inlinePresentation.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().renderSuggestion(iInlineSuggestionUiCallback, inlinePresentation, i, i2, iBinder, i3, i4, i5);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.service.autofill.IInlineSuggestionRenderService
            public void getInlineSuggestionsRendererInfo(RemoteCallback remoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getInlineSuggestionsRendererInfo(remoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionRenderService
            public void destroySuggestionViews(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().destroySuggestionViews(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionRenderService iInlineSuggestionRenderService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInlineSuggestionRenderService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInlineSuggestionRenderService;
            return true;
        }

        public static IInlineSuggestionRenderService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
