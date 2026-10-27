package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IMediaSession extends IInterface {
    void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) throws RemoteException;

    void adjustVolume(int i, int i2, String str) throws RemoteException;

    void fastForward() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    long getFlags() throws RemoteException;

    PendingIntent getLaunchPendingIntent() throws RemoteException;

    MediaMetadataCompat getMetadata() throws RemoteException;

    String getPackageName() throws RemoteException;

    PlaybackStateCompat getPlaybackState() throws RemoteException;

    List<MediaSessionCompat.QueueItem> getQueue() throws RemoteException;

    CharSequence getQueueTitle() throws RemoteException;

    int getRatingType() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    Bundle getSessionInfo() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    String getTag() throws RemoteException;

    ParcelableVolumeInfo getVolumeAttributes() throws RemoteException;

    boolean isCaptioningEnabled() throws RemoteException;

    boolean isShuffleModeEnabledRemoved() throws RemoteException;

    boolean isTransportControlEnabled() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playFromMediaId(String str, Bundle bundle) throws RemoteException;

    void playFromSearch(String str, Bundle bundle) throws RemoteException;

    void playFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void prepare() throws RemoteException;

    void prepareFromMediaId(String str, Bundle bundle) throws RemoteException;

    void prepareFromSearch(String str, Bundle bundle) throws RemoteException;

    void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void previous() throws RemoteException;

    void rate(RatingCompat ratingCompat) throws RemoteException;

    void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void removeQueueItemAt(int i) throws RemoteException;

    void rewind() throws RemoteException;

    void seekTo(long j) throws RemoteException;

    void sendCommand(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) throws RemoteException;

    void sendCustomAction(String str, Bundle bundle) throws RemoteException;

    boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException;

    void setCaptioningEnabled(boolean z) throws RemoteException;

    void setPlaybackSpeed(float f) throws RemoteException;

    void setRepeatMode(int i) throws RemoteException;

    void setShuffleMode(int i) throws RemoteException;

    void setShuffleModeEnabledRemoved(boolean z) throws RemoteException;

    void setVolumeTo(int i, int i2, String str) throws RemoteException;

    void skipToQueueItem(long j) throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.support.v4.media.session.IMediaSession
        public abstract /* synthetic */ int getRatingType() throws RemoteException;

        @Override // android.support.v4.media.session.IMediaSession
        public abstract /* synthetic */ int getRepeatMode() throws RemoteException;

        @Override // android.support.v4.media.session.IMediaSession
        public abstract /* synthetic */ int getShuffleMode() throws RemoteException;

        @Override // android.support.v4.media.session.IMediaSession
        public abstract /* synthetic */ void setPlaybackSpeed(float f) throws RemoteException;

        @Override // android.support.v4.media.session.IMediaSession
        public abstract /* synthetic */ void setRepeatMode(int i) throws RemoteException;

        @Override // android.support.v4.media.session.IMediaSession
        public abstract /* synthetic */ void setShuffleMode(int i) throws RemoteException;

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaSession)) {
                return (IMediaSession) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("android.support.v4.media.session.IMediaSession");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    sendCommand(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zSendMediaButton = sendMediaButton(parcel.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zSendMediaButton ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    registerCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zIsTransportControlEnabled = isTransportControlEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsTransportControlEnabled ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String packageName = getPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(packageName);
                    return true;
                case 7:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String tag = getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    return true;
                case 8:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PendingIntent launchPendingIntent = getLaunchPendingIntent();
                    parcel2.writeNoException();
                    if (launchPendingIntent != null) {
                        parcel2.writeInt(1);
                        launchPendingIntent.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    long flags = getFlags();
                    parcel2.writeNoException();
                    parcel2.writeLong(flags);
                    return true;
                case 10:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    ParcelableVolumeInfo volumeAttributes = getVolumeAttributes();
                    parcel2.writeNoException();
                    if (volumeAttributes != null) {
                        parcel2.writeInt(1);
                        volumeAttributes.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    adjustVolume(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setVolumeTo(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    play();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    playFromMediaId(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    playFromSearch(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    playFromUri(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    skipToQueueItem(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    stop();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    next();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    previous();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    fastForward();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    rewind();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    seekTo(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    rate(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    sendCustomAction(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    MediaMetadataCompat metadata = getMetadata();
                    parcel2.writeNoException();
                    if (metadata != null) {
                        parcel2.writeInt(1);
                        metadata.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PlaybackStateCompat playbackState = getPlaybackState();
                    parcel2.writeNoException();
                    if (playbackState != null) {
                        parcel2.writeInt(1);
                        playbackState.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    List<MediaSessionCompat.QueueItem> queue = getQueue();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(queue);
                    return true;
                case 30:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    CharSequence queueTitle = getQueueTitle();
                    parcel2.writeNoException();
                    if (queueTitle != null) {
                        parcel2.writeInt(1);
                        TextUtils.writeToParcel(queueTitle, parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle extras = getExtras();
                    parcel2.writeNoException();
                    if (extras != null) {
                        parcel2.writeInt(1);
                        extras.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int ratingType = getRatingType();
                    parcel2.writeNoException();
                    parcel2.writeInt(ratingType);
                    return true;
                case 33:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepare();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepareFromMediaId(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepareFromSearch(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    prepareFromUri(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int repeatMode = getRepeatMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(repeatMode);
                    return true;
                case 38:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zIsShuffleModeEnabledRemoved = isShuffleModeEnabledRemoved();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsShuffleModeEnabledRemoved ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setRepeatMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setShuffleModeEnabledRemoved(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    addQueueItem(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    addQueueItemAt(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    removeQueueItem(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    removeQueueItemAt(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zIsCaptioningEnabled = isCaptioningEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsCaptioningEnabled ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setCaptioningEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int shuffleMode = getShuffleMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(shuffleMode);
                    return true;
                case 48:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setShuffleMode(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    setPlaybackSpeed(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle sessionInfo = getSessionInfo();
                    parcel2.writeNoException();
                    if (sessionInfo != null) {
                        parcel2.writeInt(1);
                        sessionInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 51:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    rateWithExtras(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaSession {
            public static IMediaSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (keyEvent != null) {
                        parcelObtain.writeInt(1);
                        keyEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendMediaButton(keyEvent);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallbackListener(iMediaControllerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCallbackListener(iMediaControllerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PendingIntent getLaunchPendingIntent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaunchPendingIntent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public MediaMetadataCompat getMetadata() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetadata();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MediaMetadataCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlaybackState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRatingType() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRatingType();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRepeatMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRepeatMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getShuffleMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShuffleMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepareFromMediaId(str, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void play() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().play();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playFromMediaId(str, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void pause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pause();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void stop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stop();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void fastForward() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().fastForward();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rewind() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rewind();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void seekTo(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().seekTo(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setPlaybackSpeed(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeFloat(f);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPlaybackSpeed(f);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setRepeatMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRepeatMode(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShuffleMode(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendCustomAction(str, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static IMediaSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
