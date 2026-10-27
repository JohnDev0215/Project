package com.byd.minikaraoke;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IConnectionStateListener extends IInterface {

    public static class Default implements IConnectionStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.minikaraoke.IConnectionStateListener
        public void onMicrophoneConnectionStateChanged(boolean z) throws RemoteException {
        }
    }

    void onMicrophoneConnectionStateChanged(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IConnectionStateListener {
        private static final String DESCRIPTOR = "com.byd.minikaraoke.IConnectionStateListener";
        static final int TRANSACTION_onMicrophoneConnectionStateChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConnectionStateListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IConnectionStateListener)) {
                return (IConnectionStateListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onMicrophoneConnectionStateChanged(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IConnectionStateListener {
            public static IConnectionStateListener sDefaultImpl;
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

            @Override // com.byd.minikaraoke.IConnectionStateListener
            public void onMicrophoneConnectionStateChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMicrophoneConnectionStateChanged(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectionStateListener iConnectionStateListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iConnectionStateListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iConnectionStateListener;
            return true;
        }

        public static IConnectionStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
