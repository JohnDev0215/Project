package android.media.tv;

import android.media.tv.ITvInputSession;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ITvInputSessionCallback extends IInterface {

    public static class Default implements ITvInputSessionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onChannelRetuned(Uri uri) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onContentAllowed() throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onContentBlocked(String str) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onError(int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onLayoutSurface(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onRecordingStopped(Uri uri) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onSessionCreated(ITvInputSession iTvInputSession, IBinder iBinder) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onSessionEvent(String str, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onTimeShiftCurrentPositionChanged(long j) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onTimeShiftStartPositionChanged(long j) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onTimeShiftStatusChanged(int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onTrackSelected(int i, String str) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onTracksChanged(List<TvTrackInfo> list) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onTuned(Uri uri) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onVideoAvailable() throws RemoteException {
        }

        @Override // android.media.tv.ITvInputSessionCallback
        public void onVideoUnavailable(int i) throws RemoteException {
        }
    }

    void onChannelRetuned(Uri uri) throws RemoteException;

    void onContentAllowed() throws RemoteException;

    void onContentBlocked(String str) throws RemoteException;

    void onError(int i) throws RemoteException;

    void onLayoutSurface(int i, int i2, int i3, int i4) throws RemoteException;

    void onRecordingStopped(Uri uri) throws RemoteException;

    void onSessionCreated(ITvInputSession iTvInputSession, IBinder iBinder) throws RemoteException;

    void onSessionEvent(String str, Bundle bundle) throws RemoteException;

    void onTimeShiftCurrentPositionChanged(long j) throws RemoteException;

    void onTimeShiftStartPositionChanged(long j) throws RemoteException;

    void onTimeShiftStatusChanged(int i) throws RemoteException;

    void onTrackSelected(int i, String str) throws RemoteException;

    void onTracksChanged(List<TvTrackInfo> list) throws RemoteException;

    void onTuned(Uri uri) throws RemoteException;

    void onVideoAvailable() throws RemoteException;

    void onVideoUnavailable(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ITvInputSessionCallback {
        private static final String DESCRIPTOR = "android.media.tv.ITvInputSessionCallback";
        static final int TRANSACTION_onChannelRetuned = 3;
        static final int TRANSACTION_onContentAllowed = 8;
        static final int TRANSACTION_onContentBlocked = 9;
        static final int TRANSACTION_onError = 16;
        static final int TRANSACTION_onLayoutSurface = 10;
        static final int TRANSACTION_onRecordingStopped = 15;
        static final int TRANSACTION_onSessionCreated = 1;
        static final int TRANSACTION_onSessionEvent = 2;
        static final int TRANSACTION_onTimeShiftCurrentPositionChanged = 13;
        static final int TRANSACTION_onTimeShiftStartPositionChanged = 12;
        static final int TRANSACTION_onTimeShiftStatusChanged = 11;
        static final int TRANSACTION_onTrackSelected = 5;
        static final int TRANSACTION_onTracksChanged = 4;
        static final int TRANSACTION_onTuned = 14;
        static final int TRANSACTION_onVideoAvailable = 6;
        static final int TRANSACTION_onVideoUnavailable = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onSessionCreated";
                case 2:
                    return "onSessionEvent";
                case 3:
                    return "onChannelRetuned";
                case 4:
                    return "onTracksChanged";
                case 5:
                    return "onTrackSelected";
                case 6:
                    return "onVideoAvailable";
                case 7:
                    return "onVideoUnavailable";
                case 8:
                    return "onContentAllowed";
                case 9:
                    return "onContentBlocked";
                case 10:
                    return "onLayoutSurface";
                case 11:
                    return "onTimeShiftStatusChanged";
                case 12:
                    return "onTimeShiftStartPositionChanged";
                case 13:
                    return "onTimeShiftCurrentPositionChanged";
                case 14:
                    return "onTuned";
                case 15:
                    return "onRecordingStopped";
                case 16:
                    return "onError";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITvInputSessionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITvInputSessionCallback)) {
                return (ITvInputSessionCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionCreated(ITvInputSession.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionEvent(parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onChannelRetuned(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTracksChanged(parcel.createTypedArrayList(TvTrackInfo.CREATOR));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTrackSelected(parcel.readInt(), parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVideoAvailable();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVideoUnavailable(parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onContentAllowed();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onContentBlocked(parcel.readString());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLayoutSurface(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTimeShiftStatusChanged(parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTimeShiftStartPositionChanged(parcel.readLong());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTimeShiftCurrentPositionChanged(parcel.readLong());
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTuned(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRecordingStopped(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onError(parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITvInputSessionCallback {
            public static ITvInputSessionCallback sDefaultImpl;
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

            @Override // android.media.tv.ITvInputSessionCallback
            public void onSessionCreated(ITvInputSession iTvInputSession, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iTvInputSession != null ? iTvInputSession.asBinder() : null);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionCreated(iTvInputSession, iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onSessionEvent(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionEvent(str, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onChannelRetuned(Uri uri) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChannelRetuned(uri);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onTracksChanged(List<TvTrackInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTracksChanged(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onTrackSelected(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTrackSelected(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onVideoAvailable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVideoAvailable();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onVideoUnavailable(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVideoUnavailable(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onContentAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onContentAllowed();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onContentBlocked(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onContentBlocked(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onLayoutSurface(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLayoutSurface(i, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onTimeShiftStatusChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTimeShiftStatusChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onTimeShiftStartPositionChanged(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTimeShiftStartPositionChanged(j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onTimeShiftCurrentPositionChanged(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTimeShiftCurrentPositionChanged(j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onTuned(Uri uri) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTuned(uri);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onRecordingStopped(Uri uri) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRecordingStopped(uri);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputSessionCallback
            public void onError(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvInputSessionCallback iTvInputSessionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTvInputSessionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTvInputSessionCallback;
            return true;
        }

        public static ITvInputSessionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
