package android.media.tv;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.InputChannel;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ITvInputClient extends IInterface {

    public static class Default implements ITvInputClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.ITvInputClient
        public void onChannelRetuned(Uri uri, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onContentAllowed(int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onContentBlocked(String str, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onError(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onLayoutSurface(int i, int i2, int i3, int i4, int i5) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onRecordingStopped(Uri uri, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onSessionCreated(String str, IBinder iBinder, InputChannel inputChannel, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onSessionEvent(String str, Bundle bundle, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onSessionReleased(int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onTimeShiftCurrentPositionChanged(long j, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onTimeShiftStartPositionChanged(long j, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onTimeShiftStatusChanged(int i, int i2) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onTrackSelected(int i, String str, int i2) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onTracksChanged(List<TvTrackInfo> list, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onTuned(int i, Uri uri) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onVideoAvailable(int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputClient
        public void onVideoUnavailable(int i, int i2) throws RemoteException {
        }
    }

    void onChannelRetuned(Uri uri, int i) throws RemoteException;

    void onContentAllowed(int i) throws RemoteException;

    void onContentBlocked(String str, int i) throws RemoteException;

    void onError(int i, int i2) throws RemoteException;

    void onLayoutSurface(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void onRecordingStopped(Uri uri, int i) throws RemoteException;

    void onSessionCreated(String str, IBinder iBinder, InputChannel inputChannel, int i) throws RemoteException;

    void onSessionEvent(String str, Bundle bundle, int i) throws RemoteException;

    void onSessionReleased(int i) throws RemoteException;

    void onTimeShiftCurrentPositionChanged(long j, int i) throws RemoteException;

    void onTimeShiftStartPositionChanged(long j, int i) throws RemoteException;

    void onTimeShiftStatusChanged(int i, int i2) throws RemoteException;

    void onTrackSelected(int i, String str, int i2) throws RemoteException;

    void onTracksChanged(List<TvTrackInfo> list, int i) throws RemoteException;

    void onTuned(int i, Uri uri) throws RemoteException;

    void onVideoAvailable(int i) throws RemoteException;

    void onVideoUnavailable(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements ITvInputClient {
        private static final String DESCRIPTOR = "android.media.tv.ITvInputClient";
        static final int TRANSACTION_onChannelRetuned = 4;
        static final int TRANSACTION_onContentAllowed = 9;
        static final int TRANSACTION_onContentBlocked = 10;
        static final int TRANSACTION_onError = 17;
        static final int TRANSACTION_onLayoutSurface = 11;
        static final int TRANSACTION_onRecordingStopped = 16;
        static final int TRANSACTION_onSessionCreated = 1;
        static final int TRANSACTION_onSessionEvent = 3;
        static final int TRANSACTION_onSessionReleased = 2;
        static final int TRANSACTION_onTimeShiftCurrentPositionChanged = 14;
        static final int TRANSACTION_onTimeShiftStartPositionChanged = 13;
        static final int TRANSACTION_onTimeShiftStatusChanged = 12;
        static final int TRANSACTION_onTrackSelected = 6;
        static final int TRANSACTION_onTracksChanged = 5;
        static final int TRANSACTION_onTuned = 15;
        static final int TRANSACTION_onVideoAvailable = 7;
        static final int TRANSACTION_onVideoUnavailable = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onSessionCreated";
                case 2:
                    return "onSessionReleased";
                case 3:
                    return "onSessionEvent";
                case 4:
                    return "onChannelRetuned";
                case 5:
                    return "onTracksChanged";
                case 6:
                    return "onTrackSelected";
                case 7:
                    return "onVideoAvailable";
                case 8:
                    return "onVideoUnavailable";
                case 9:
                    return "onContentAllowed";
                case 10:
                    return "onContentBlocked";
                case 11:
                    return "onLayoutSurface";
                case 12:
                    return "onTimeShiftStatusChanged";
                case 13:
                    return "onTimeShiftStartPositionChanged";
                case 14:
                    return "onTimeShiftCurrentPositionChanged";
                case 15:
                    return "onTuned";
                case 16:
                    return "onRecordingStopped";
                case 17:
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

        public static ITvInputClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITvInputClient)) {
                return (ITvInputClient) iInterfaceQueryLocalInterface;
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
                    onSessionCreated(parcel.readString(), parcel.readStrongBinder(), parcel.readInt() != 0 ? InputChannel.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionReleased(parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionEvent(parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onChannelRetuned(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTracksChanged(parcel.createTypedArrayList(TvTrackInfo.CREATOR), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTrackSelected(parcel.readInt(), parcel.readString(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVideoAvailable(parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVideoUnavailable(parcel.readInt(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onContentAllowed(parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onContentBlocked(parcel.readString(), parcel.readInt());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLayoutSurface(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTimeShiftStatusChanged(parcel.readInt(), parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTimeShiftStartPositionChanged(parcel.readLong(), parcel.readInt());
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTimeShiftCurrentPositionChanged(parcel.readLong(), parcel.readInt());
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTuned(parcel.readInt(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRecordingStopped(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    onError(parcel.readInt(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITvInputClient {
            public static ITvInputClient sDefaultImpl;
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

            @Override // android.media.tv.ITvInputClient
            public void onSessionCreated(String str, IBinder iBinder, InputChannel inputChannel, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (inputChannel != null) {
                        parcelObtain.writeInt(1);
                        inputChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionCreated(str, iBinder, inputChannel, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onSessionReleased(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionReleased(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onSessionEvent(String str, Bundle bundle, int i) throws RemoteException {
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
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSessionEvent(str, bundle, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onChannelRetuned(Uri uri, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChannelRetuned(uri, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onTracksChanged(List<TvTrackInfo> list, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTracksChanged(list, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onTrackSelected(int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTrackSelected(i, str, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onVideoAvailable(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVideoAvailable(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onVideoUnavailable(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVideoUnavailable(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onContentAllowed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onContentAllowed(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onContentBlocked(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onContentBlocked(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onLayoutSurface(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLayoutSurface(i, i2, i3, i4, i5);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onTimeShiftStatusChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTimeShiftStatusChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onTimeShiftStartPositionChanged(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTimeShiftStartPositionChanged(j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onTimeShiftCurrentPositionChanged(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTimeShiftCurrentPositionChanged(j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onTuned(int i, Uri uri) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTuned(i, uri);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onRecordingStopped(Uri uri, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRecordingStopped(uri, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputClient
            public void onError(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvInputClient iTvInputClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTvInputClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTvInputClient;
            return true;
        }

        public static ITvInputClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
