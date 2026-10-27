package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.PlaybackMediaItemInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface INowPlayingCallback extends IInterface {
    void onArtworkUpdate(NowPlayingInfo nowPlayingInfo) throws RemoteException;

    void onNowPlayingInfoChanged(NowPlayingInfo nowPlayingInfo) throws RemoteException;

    void onPlaybackListUpdate(int i, int i2, List<PlaybackMediaItemInfo> list) throws RemoteException;

    public static class Default implements INowPlayingCallback {
        @Override // com.ts.carplay.common.aidl.INowPlayingCallback
        public void onNowPlayingInfoChanged(NowPlayingInfo info) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.INowPlayingCallback
        public void onPlaybackListUpdate(int mediaItemCount, int mediaItemStartIndex, List<PlaybackMediaItemInfo> list) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.INowPlayingCallback
        public void onArtworkUpdate(NowPlayingInfo info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements INowPlayingCallback {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.INowPlayingCallback";
        static final int TRANSACTION_onArtworkUpdate = 3;
        static final int TRANSACTION_onNowPlayingInfoChanged = 1;
        static final int TRANSACTION_onPlaybackListUpdate = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INowPlayingCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INowPlayingCallback)) {
                return (INowPlayingCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            NowPlayingInfo _arg0;
            NowPlayingInfo _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = NowPlayingInfo.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onNowPlayingInfoChanged(_arg0);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg03 = data.readInt();
                int _arg1 = data.readInt();
                List<PlaybackMediaItemInfo> _arg2 = data.createTypedArrayList(PlaybackMediaItemInfo.CREATOR);
                onPlaybackListUpdate(_arg03, _arg1, _arg2);
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
            if (data.readInt() != 0) {
                _arg02 = NowPlayingInfo.CREATOR.createFromParcel(data);
            } else {
                _arg02 = null;
            }
            onArtworkUpdate(_arg02);
            return true;
        }

        private static class Proxy implements INowPlayingCallback {
            public static INowPlayingCallback sDefaultImpl;
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

            @Override // com.ts.carplay.common.aidl.INowPlayingCallback
            public void onNowPlayingInfoChanged(NowPlayingInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNowPlayingInfoChanged(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.INowPlayingCallback
            public void onPlaybackListUpdate(int mediaItemCount, int mediaItemStartIndex, List<PlaybackMediaItemInfo> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mediaItemCount);
                    _data.writeInt(mediaItemStartIndex);
                    _data.writeTypedList(list);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaybackListUpdate(mediaItemCount, mediaItemStartIndex, list);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.INowPlayingCallback
            public void onArtworkUpdate(NowPlayingInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onArtworkUpdate(info);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INowPlayingCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static INowPlayingCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
