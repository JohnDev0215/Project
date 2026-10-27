package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.androidauto.sdk.aidl.data.MediaMetadata;

/* JADX INFO: loaded from: classes.dex */
public interface IMediaDataCallback extends IInterface {
    void onMediaMetadataChanged(MediaMetadata mediaMetadata) throws RemoteException;

    void onMediaProgressChanged(int i) throws RemoteException;

    void onMusicStatusChanged(int i) throws RemoteException;

    public static class Default implements IMediaDataCallback {
        @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
        public void onMusicStatusChanged(int status) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
        public void onMediaMetadataChanged(MediaMetadata metadata) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
        public void onMediaProgressChanged(int progress) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMediaDataCallback {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.IMediaDataCallback";
        static final int TRANSACTION_onMediaMetadataChanged = 2;
        static final int TRANSACTION_onMediaProgressChanged = 3;
        static final int TRANSACTION_onMusicStatusChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaDataCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IMediaDataCallback)) {
                return (IMediaDataCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            MediaMetadata _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                onMusicStatusChanged(_arg02);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = MediaMetadata.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onMediaMetadataChanged(_arg0);
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            int _arg03 = data.readInt();
            onMediaProgressChanged(_arg03);
            return true;
        }

        private static class Proxy implements IMediaDataCallback {
            public static IMediaDataCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
            public void onMusicStatusChanged(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMusicStatusChanged(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
            public void onMediaMetadataChanged(MediaMetadata metadata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metadata != null) {
                        _data.writeInt(1);
                        metadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaMetadataChanged(metadata);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
            public void onMediaProgressChanged(int progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(progress);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaProgressChanged(progress);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaDataCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IMediaDataCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
