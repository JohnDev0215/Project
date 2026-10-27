package com.android.internal.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IVoiceInteractionSessionShowCallback extends IInterface {

    public static class Default implements IVoiceInteractionSessionShowCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionSessionShowCallback
        public void onFailed() throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionSessionShowCallback
        public void onShown() throws RemoteException {
        }
    }

    void onFailed() throws RemoteException;

    void onShown() throws RemoteException;

    public static abstract class Stub extends Binder implements IVoiceInteractionSessionShowCallback {
        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractionSessionShowCallback";
        static final int TRANSACTION_onFailed = 1;
        static final int TRANSACTION_onShown = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onFailed";
            }
            if (i != 2) {
                return null;
            }
            return "onShown";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVoiceInteractionSessionShowCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVoiceInteractionSessionShowCallback)) {
                return (IVoiceInteractionSessionShowCallback) iInterfaceQueryLocalInterface;
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
                onFailed();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onShown();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IVoiceInteractionSessionShowCallback {
            public static IVoiceInteractionSessionShowCallback sDefaultImpl;
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

            @Override // com.android.internal.app.IVoiceInteractionSessionShowCallback
            public void onFailed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFailed();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionSessionShowCallback
            public void onShown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onShown();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoiceInteractionSessionShowCallback iVoiceInteractionSessionShowCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iVoiceInteractionSessionShowCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVoiceInteractionSessionShowCallback;
            return true;
        }

        public static IVoiceInteractionSessionShowCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
