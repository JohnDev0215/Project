package com.android.internal.view.inline;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;

/* JADX INFO: loaded from: classes4.dex */
public interface IInlineContentCallback extends IInterface {

    public static class Default implements IInlineContentCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onClick() throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onContent(SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onLongClick() throws RemoteException {
        }
    }

    void onClick() throws RemoteException;

    void onContent(SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException;

    void onLongClick() throws RemoteException;

    public static abstract class Stub extends Binder implements IInlineContentCallback {
        private static final String DESCRIPTOR = "com.android.internal.view.inline.IInlineContentCallback";
        static final int TRANSACTION_onClick = 2;
        static final int TRANSACTION_onContent = 1;
        static final int TRANSACTION_onLongClick = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onContent";
            }
            if (i == 2) {
                return "onClick";
            }
            if (i != 3) {
                return null;
            }
            return "onLongClick";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInlineContentCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInlineContentCallback)) {
                return (IInlineContentCallback) iInterfaceQueryLocalInterface;
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
                onContent(parcel.readInt() != 0 ? SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onClick();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onLongClick();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IInlineContentCallback {
            public static IInlineContentCallback sDefaultImpl;
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

            @Override // com.android.internal.view.inline.IInlineContentCallback
            public void onContent(SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surfacePackage != null) {
                        parcelObtain.writeInt(1);
                        surfacePackage.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onContent(surfacePackage, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentCallback
            public void onClick() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onClick();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentCallback
            public void onLongClick() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLongClick();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineContentCallback iInlineContentCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInlineContentCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInlineContentCallback;
            return true;
        }

        public static IInlineContentCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
