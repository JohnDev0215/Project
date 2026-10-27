package android.service.autofill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.ISurfacePackageResultCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IInlineSuggestionUi extends IInterface {

    public static class Default implements IInlineSuggestionUi {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.autofill.IInlineSuggestionUi
        public void getSurfacePackage(ISurfacePackageResultCallback iSurfacePackageResultCallback) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUi
        public void releaseSurfaceControlViewHost() throws RemoteException {
        }
    }

    void getSurfacePackage(ISurfacePackageResultCallback iSurfacePackageResultCallback) throws RemoteException;

    void releaseSurfaceControlViewHost() throws RemoteException;

    public static abstract class Stub extends Binder implements IInlineSuggestionUi {
        private static final String DESCRIPTOR = "android.service.autofill.IInlineSuggestionUi";
        static final int TRANSACTION_getSurfacePackage = 1;
        static final int TRANSACTION_releaseSurfaceControlViewHost = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getSurfacePackage";
            }
            if (i != 2) {
                return null;
            }
            return "releaseSurfaceControlViewHost";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInlineSuggestionUi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInlineSuggestionUi)) {
                return (IInlineSuggestionUi) iInterfaceQueryLocalInterface;
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
                getSurfacePackage(ISurfacePackageResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                releaseSurfaceControlViewHost();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInlineSuggestionUi {
            public static IInlineSuggestionUi sDefaultImpl;
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

            @Override // android.service.autofill.IInlineSuggestionUi
            public void getSurfacePackage(ISurfacePackageResultCallback iSurfacePackageResultCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSurfacePackageResultCallback != null ? iSurfacePackageResultCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getSurfacePackage(iSurfacePackageResultCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUi
            public void releaseSurfaceControlViewHost() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().releaseSurfaceControlViewHost();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionUi iInlineSuggestionUi) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInlineSuggestionUi == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInlineSuggestionUi;
            return true;
        }

        public static IInlineSuggestionUi getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
