package com.ts.lib.settings.vradapter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface ILocalVrSettingsCallback extends IInterface {

    public static class Default implements ILocalVrSettingsCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsCallback
        public void onLocalVrAppStateChanged(int i) throws RemoteException {
        }
    }

    void onLocalVrAppStateChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ILocalVrSettingsCallback {
        private static final String DESCRIPTOR = "com.ts.lib.settings.vradapter.ILocalVrSettingsCallback";
        static final int TRANSACTION_onLocalVrAppStateChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocalVrSettingsCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILocalVrSettingsCallback)) {
                return (ILocalVrSettingsCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onLocalVrAppStateChanged(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ILocalVrSettingsCallback {
            public static ILocalVrSettingsCallback sDefaultImpl;
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

            @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsCallback
            public void onLocalVrAppStateChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLocalVrAppStateChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocalVrSettingsCallback iLocalVrSettingsCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLocalVrSettingsCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLocalVrSettingsCallback;
            return true;
        }

        public static ILocalVrSettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
