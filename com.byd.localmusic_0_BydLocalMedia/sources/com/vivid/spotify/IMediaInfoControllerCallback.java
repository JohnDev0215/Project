package com.vivid.spotify;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes5.dex */
public interface IMediaInfoControllerCallback extends IInterface {
    public static final String KEY_IS_EPISODE_COLLECTED = "KEY_IS_EPISODE_COLLECTED";
    public static final String KEY_IS_TRACK_COLLECTED = "KEY_IS_TRACK_COLLECTED";
    public static final String KEY_PLAY_STATE = "KEY_PLAY_STATE";

    public static class Default implements IMediaInfoControllerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.vivid.spotify.IMediaInfoControllerCallback
        public void onMetadataChanged(String str, String str2, String str3, long j) throws RemoteException {
        }

        @Override // com.vivid.spotify.IMediaInfoControllerCallback
        public void onPlaybackStateChanged(Bundle bundle) throws RemoteException {
        }

        @Override // com.vivid.spotify.IMediaInfoControllerCallback
        public void onPositionChanged(long j) throws RemoteException {
        }
    }

    void onMetadataChanged(String str, String str2, String str3, long j) throws RemoteException;

    void onPlaybackStateChanged(Bundle bundle) throws RemoteException;

    void onPositionChanged(long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaInfoControllerCallback {
        private static final String DESCRIPTOR = "com.vivid.spotify.IMediaInfoControllerCallback";
        static final int TRANSACTION_onMetadataChanged = 2;
        static final int TRANSACTION_onPlaybackStateChanged = 1;
        static final int TRANSACTION_onPositionChanged = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaInfoControllerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaInfoControllerCallback)) {
                return (IMediaInfoControllerCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onPlaybackStateChanged(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onMetadataChanged(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onPositionChanged(parcel.readLong());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IMediaInfoControllerCallback {
            public static IMediaInfoControllerCallback sDefaultImpl;
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

            @Override // com.vivid.spotify.IMediaInfoControllerCallback
            public void onPlaybackStateChanged(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaybackStateChanged(bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.IMediaInfoControllerCallback
            public void onMetadataChanged(String str, String str2, String str3, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMetadataChanged(str, str2, str3, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.IMediaInfoControllerCallback
            public void onPositionChanged(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPositionChanged(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaInfoControllerCallback iMediaInfoControllerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaInfoControllerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaInfoControllerCallback;
            return true;
        }

        public static IMediaInfoControllerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
