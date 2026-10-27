package com.android.internal.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IVoiceInteractionSessionListener extends IInterface {

    public static class Default implements IVoiceInteractionSessionListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionSessionListener
        public void onSetUiHints(Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionSessionListener
        public void onVoiceSessionHidden() throws RemoteException {
        }

        @Override // com.android.internal.app.IVoiceInteractionSessionListener
        public void onVoiceSessionShown() throws RemoteException {
        }
    }

    void onSetUiHints(Bundle bundle) throws RemoteException;

    void onVoiceSessionHidden() throws RemoteException;

    void onVoiceSessionShown() throws RemoteException;

    public static abstract class Stub extends Binder implements IVoiceInteractionSessionListener {
        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractionSessionListener";
        static final int TRANSACTION_onSetUiHints = 3;
        static final int TRANSACTION_onVoiceSessionHidden = 2;
        static final int TRANSACTION_onVoiceSessionShown = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onVoiceSessionShown";
            }
            if (i == 2) {
                return "onVoiceSessionHidden";
            }
            if (i != 3) {
                return null;
            }
            return "onSetUiHints";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVoiceInteractionSessionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IVoiceInteractionSessionListener)) {
                return (IVoiceInteractionSessionListener) iInterfaceQueryLocalInterface;
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
                onVoiceSessionShown();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onVoiceSessionHidden();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onSetUiHints(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IVoiceInteractionSessionListener {
            public static IVoiceInteractionSessionListener sDefaultImpl;
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

            @Override // com.android.internal.app.IVoiceInteractionSessionListener
            public void onVoiceSessionShown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVoiceSessionShown();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionSessionListener
            public void onVoiceSessionHidden() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVoiceSessionHidden();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionSessionListener
            public void onSetUiHints(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetUiHints(bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoiceInteractionSessionListener iVoiceInteractionSessionListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iVoiceInteractionSessionListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVoiceInteractionSessionListener;
            return true;
        }

        public static IVoiceInteractionSessionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
