package android.media.session;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;

/* JADX INFO: loaded from: classes2.dex */
public interface ISessionCallback extends IInterface {

    public static class Default implements ISessionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.session.ISessionCallback
        public void onAdjustVolume(String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onCommand(String str, int i, int i2, String str2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onCustomAction(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onFastForward(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onMediaButton(String str, int i, int i2, Intent intent, int i3, ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onMediaButtonFromController(String str, int i, int i2, Intent intent) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onNext(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPause(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlay(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlayFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlayFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPlayFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepare(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepareFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepareFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrepareFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onPrevious(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onRate(String str, int i, int i2, Rating rating) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onRewind(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSeekTo(String str, int i, int i2, long j) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSetPlaybackSpeed(String str, int i, int i2, float f) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSetVolumeTo(String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onSkipToTrack(String str, int i, int i2, long j) throws RemoteException {
        }

        @Override // android.media.session.ISessionCallback
        public void onStop(String str, int i, int i2) throws RemoteException {
        }
    }

    void onAdjustVolume(String str, int i, int i2, int i3) throws RemoteException;

    void onCommand(String str, int i, int i2, String str2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException;

    void onCustomAction(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onFastForward(String str, int i, int i2) throws RemoteException;

    void onMediaButton(String str, int i, int i2, Intent intent, int i3, ResultReceiver resultReceiver) throws RemoteException;

    void onMediaButtonFromController(String str, int i, int i2, Intent intent) throws RemoteException;

    void onNext(String str, int i, int i2) throws RemoteException;

    void onPause(String str, int i, int i2) throws RemoteException;

    void onPlay(String str, int i, int i2) throws RemoteException;

    void onPlayFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPlayFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPlayFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException;

    void onPrepare(String str, int i, int i2) throws RemoteException;

    void onPrepareFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPrepareFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException;

    void onPrepareFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException;

    void onPrevious(String str, int i, int i2) throws RemoteException;

    void onRate(String str, int i, int i2, Rating rating) throws RemoteException;

    void onRewind(String str, int i, int i2) throws RemoteException;

    void onSeekTo(String str, int i, int i2, long j) throws RemoteException;

    void onSetPlaybackSpeed(String str, int i, int i2, float f) throws RemoteException;

    void onSetVolumeTo(String str, int i, int i2, int i3) throws RemoteException;

    void onSkipToTrack(String str, int i, int i2, long j) throws RemoteException;

    void onStop(String str, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements ISessionCallback {
        private static final String DESCRIPTOR = "android.media.session.ISessionCallback";
        static final int TRANSACTION_onAdjustVolume = 23;
        static final int TRANSACTION_onCommand = 1;
        static final int TRANSACTION_onCustomAction = 22;
        static final int TRANSACTION_onFastForward = 17;
        static final int TRANSACTION_onMediaButton = 2;
        static final int TRANSACTION_onMediaButtonFromController = 3;
        static final int TRANSACTION_onNext = 15;
        static final int TRANSACTION_onPause = 13;
        static final int TRANSACTION_onPlay = 8;
        static final int TRANSACTION_onPlayFromMediaId = 9;
        static final int TRANSACTION_onPlayFromSearch = 10;
        static final int TRANSACTION_onPlayFromUri = 11;
        static final int TRANSACTION_onPrepare = 4;
        static final int TRANSACTION_onPrepareFromMediaId = 5;
        static final int TRANSACTION_onPrepareFromSearch = 6;
        static final int TRANSACTION_onPrepareFromUri = 7;
        static final int TRANSACTION_onPrevious = 16;
        static final int TRANSACTION_onRate = 20;
        static final int TRANSACTION_onRewind = 18;
        static final int TRANSACTION_onSeekTo = 19;
        static final int TRANSACTION_onSetPlaybackSpeed = 21;
        static final int TRANSACTION_onSetVolumeTo = 24;
        static final int TRANSACTION_onSkipToTrack = 12;
        static final int TRANSACTION_onStop = 14;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onCommand";
                case 2:
                    return "onMediaButton";
                case 3:
                    return "onMediaButtonFromController";
                case 4:
                    return "onPrepare";
                case 5:
                    return "onPrepareFromMediaId";
                case 6:
                    return "onPrepareFromSearch";
                case 7:
                    return "onPrepareFromUri";
                case 8:
                    return "onPlay";
                case 9:
                    return "onPlayFromMediaId";
                case 10:
                    return "onPlayFromSearch";
                case 11:
                    return "onPlayFromUri";
                case 12:
                    return "onSkipToTrack";
                case 13:
                    return "onPause";
                case 14:
                    return "onStop";
                case 15:
                    return "onNext";
                case 16:
                    return "onPrevious";
                case 17:
                    return "onFastForward";
                case 18:
                    return "onRewind";
                case 19:
                    return "onSeekTo";
                case 20:
                    return "onRate";
                case 21:
                    return "onSetPlaybackSpeed";
                case 22:
                    return "onCustomAction";
                case 23:
                    return "onAdjustVolume";
                case 24:
                    return "onSetVolumeTo";
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

        public static ISessionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISessionCallback)) {
                return (ISessionCallback) iInterfaceQueryLocalInterface;
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
                    onCommand(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMediaButton(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMediaButtonFromController(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Intent.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrepare(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrepareFromMediaId(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrepareFromSearch(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrepareFromUri(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlay(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlayFromMediaId(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlayFromSearch(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPlayFromUri(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSkipToTrack(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readLong());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPause(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStop(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNext(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrevious(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    onFastForward(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRewind(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSeekTo(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readLong());
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Rating.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSetPlaybackSpeed(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readFloat());
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCustomAction(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAdjustVolume(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSetVolumeTo(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISessionCallback {
            public static ISessionCallback sDefaultImpl;
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

            @Override // android.media.session.ISessionCallback
            public void onCommand(String str, int i, int i2, String str2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCommand(str, i, i2, str2, bundle, resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onMediaButton(String str, int i, int i2, Intent intent, int i3, ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i3);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMediaButton(str, i, i2, intent, i3, resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onMediaButtonFromController(String str, int i, int i2, Intent intent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (intent != null) {
                        parcelObtain.writeInt(1);
                        intent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMediaButtonFromController(str, i, i2, intent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepare(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrepare(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepareFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrepareFromMediaId(str, i, i2, str2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepareFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrepareFromSearch(str, i, i2, str2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrepareFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrepareFromUri(str, i, i2, uri, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlay(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPlay(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlayFromMediaId(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPlayFromMediaId(str, i, i2, str2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlayFromSearch(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPlayFromSearch(str, i, i2, str2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPlayFromUri(String str, int i, int i2, Uri uri, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPlayFromUri(str, i, i2, uri, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSkipToTrack(String str, int i, int i2, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSkipToTrack(str, i, i2, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPause(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPause(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onStop(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStop(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onNext(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNext(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onPrevious(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrevious(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onFastForward(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFastForward(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onRewind(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRewind(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSeekTo(String str, int i, int i2, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSeekTo(str, i, i2, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onRate(String str, int i, int i2, Rating rating) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (rating != null) {
                        parcelObtain.writeInt(1);
                        rating.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRate(str, i, i2, rating);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSetPlaybackSpeed(String str, int i, int i2, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetPlaybackSpeed(str, i, i2, f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onCustomAction(String str, int i, int i2, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(22, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCustomAction(str, i, i2, str2, bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onAdjustVolume(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(23, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAdjustVolume(str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.session.ISessionCallback
            public void onSetVolumeTo(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(24, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSetVolumeTo(str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISessionCallback iSessionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSessionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSessionCallback;
            return true;
        }

        public static ISessionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
