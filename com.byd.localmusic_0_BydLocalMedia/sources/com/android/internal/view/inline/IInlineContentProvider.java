package com.android.internal.view.inline;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.view.inline.IInlineContentCallback;

/* JADX INFO: loaded from: classes4.dex */
public interface IInlineContentProvider extends IInterface {

    public static class Default implements IInlineContentProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.view.inline.IInlineContentProvider
        public void onSurfacePackageReleased() throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentProvider
        public void provideContent(int i, int i2, IInlineContentCallback iInlineContentCallback) throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentProvider
        public void requestSurfacePackage() throws RemoteException {
        }
    }

    void onSurfacePackageReleased() throws RemoteException;

    void provideContent(int i, int i2, IInlineContentCallback iInlineContentCallback) throws RemoteException;

    void requestSurfacePackage() throws RemoteException;

    public static abstract class Stub extends Binder implements IInlineContentProvider {
        private static final String DESCRIPTOR = "com.android.internal.view.inline.IInlineContentProvider";
        static final int TRANSACTION_onSurfacePackageReleased = 3;
        static final int TRANSACTION_provideContent = 1;
        static final int TRANSACTION_requestSurfacePackage = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "provideContent";
            }
            if (i == 2) {
                return "requestSurfacePackage";
            }
            if (i != 3) {
                return null;
            }
            return "onSurfacePackageReleased";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInlineContentProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInlineContentProvider)) {
                return (IInlineContentProvider) iInterfaceQueryLocalInterface;
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
                provideContent(parcel.readInt(), parcel.readInt(), IInlineContentCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                requestSurfacePackage();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onSurfacePackageReleased();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInlineContentProvider {
            public static IInlineContentProvider sDefaultImpl;
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

            @Override // com.android.internal.view.inline.IInlineContentProvider
            public void provideContent(int i, int i2, IInlineContentCallback iInlineContentCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iInlineContentCallback != null ? iInlineContentCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().provideContent(i, i2, iInlineContentCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentProvider
            public void requestSurfacePackage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestSurfacePackage();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentProvider
            public void onSurfacePackageReleased() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSurfacePackageReleased();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineContentProvider iInlineContentProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInlineContentProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInlineContentProvider;
            return true;
        }

        public static IInlineContentProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
