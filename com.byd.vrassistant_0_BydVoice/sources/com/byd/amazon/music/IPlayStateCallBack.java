package com.byd.amazon.music;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.amazon.music.param.PlayState;

/* JADX INFO: loaded from: classes.dex */
public interface IPlayStateCallBack extends IInterface {

    public static class Default implements IPlayStateCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.amazon.music.IPlayStateCallBack
        public void onState(PlayState playState) throws RemoteException {
        }
    }

    void onState(PlayState playState) throws RemoteException;

    public static abstract class Stub extends Binder implements IPlayStateCallBack {
        private static final String DESCRIPTOR = "com.byd.amazon.music.IPlayStateCallBack";
        static final int TRANSACTION_onState = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPlayStateCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPlayStateCallBack)) {
                return (IPlayStateCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onState(parcel.readInt() != 0 ? PlayState.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IPlayStateCallBack {
            public static IPlayStateCallBack sDefaultImpl;
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

            @Override // com.byd.amazon.music.IPlayStateCallBack
            public void onState(PlayState playState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playState != null) {
                        parcelObtain.writeInt(1);
                        playState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onState(playState);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlayStateCallBack iPlayStateCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPlayStateCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPlayStateCallBack;
            return true;
        }

        public static IPlayStateCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
