package com.byd.amazon.music;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.amazon.music.bean.MusicItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IMusicItemsCallBack extends IInterface {

    public static class Default implements IMusicItemsCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicItemsCallBack
        public void onError(String str, String str2) throws RemoteException {
        }

        @Override // com.byd.amazon.music.IMusicItemsCallBack
        public void onResult(String str, List<MusicItem> list) throws RemoteException {
        }
    }

    void onError(String str, String str2) throws RemoteException;

    void onResult(String str, List<MusicItem> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicItemsCallBack {
        private static final String DESCRIPTOR = "com.byd.amazon.music.IMusicItemsCallBack";
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicItemsCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMusicItemsCallBack)) {
                return (IMusicItemsCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onResult(parcel.readString(), parcel.createTypedArrayList(MusicItem.CREATOR));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readString(), parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMusicItemsCallBack {
            public static IMusicItemsCallBack sDefaultImpl;
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

            @Override // com.byd.amazon.music.IMusicItemsCallBack
            public void onResult(String str, List<MusicItem> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResult(str, list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicItemsCallBack
            public void onError(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicItemsCallBack iMusicItemsCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMusicItemsCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMusicItemsCallBack;
            return true;
        }

        public static IMusicItemsCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
