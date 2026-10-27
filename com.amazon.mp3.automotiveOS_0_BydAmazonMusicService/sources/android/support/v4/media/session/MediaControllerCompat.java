package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.KeyEvent;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.BundleCompat;
import androidx.media.AudioAttributesCompat;
import androidx.versionedparcelable.ParcelUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {
    private final MediaControllerImpl mImpl;

    @SuppressLint({"BanConcurrentHashMap"})
    private final ConcurrentHashMap<Callback, Boolean> mRegisteredCallbacks = new ConcurrentHashMap<>();
    private final MediaSessionCompat.Token mToken;

    interface MediaControllerImpl {
        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);

        MediaMetadataCompat getMetadata();

        PlaybackStateCompat getPlaybackState();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        int getShuffleMode();

        TransportControls getTransportControls();

        void registerCallback(Callback callback, Handler handler);

        void unregisterCallback(Callback callback);
    }

    static void validateCustomAction(String str, Bundle bundle) {
        if (str == null) {
            return;
        }
        if (str.equals("android.support.v4.media.session.action.FOLLOW") || str.equals("android.support.v4.media.session.action.UNFOLLOW")) {
            if (bundle == null || !bundle.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")) {
                throw new IllegalArgumentException("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action " + str + ".");
            }
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        MediaSessionCompat.Token sessionToken = mediaSessionCompat.getSessionToken();
        this.mToken = sessionToken;
        if (Build.VERSION.SDK_INT >= 29) {
            this.mImpl = new MediaControllerImplApi29(context, sessionToken);
        } else {
            this.mImpl = new MediaControllerImplApi21(context, sessionToken);
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat.Token token) {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.mToken = token;
        this.mImpl = new MediaControllerImplApi21(context, token);
    }

    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        return this.mImpl.dispatchMediaButtonEvent(keyEvent);
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }

    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }

    public int getRatingType() {
        return this.mImpl.getRatingType();
    }

    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.mToken;
    }

    public void registerCallback(@NonNull Callback callback) {
        registerCallback(callback, null);
    }

    public void registerCallback(@NonNull Callback callback, Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.mRegisteredCallbacks.putIfAbsent(callback, Boolean.TRUE) != null) {
            Log.w("MediaControllerCompat", "the callback has already been registered");
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        callback.setHandler(handler);
        this.mImpl.registerCallback(callback, handler);
    }

    public void unregisterCallback(@NonNull Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.mRegisteredCallbacks.remove(callback) == null) {
            Log.w("MediaControllerCompat", "the callback has never been registered");
            return;
        }
        try {
            this.mImpl.unregisterCallback(callback);
        } finally {
            callback.setHandler(null);
        }
    }

    public static abstract class Callback implements IBinder.DeathRecipient {
        final MediaController.Callback mCallbackFwk = new MediaControllerCallbackApi21(this);
        MessageHandler mHandler;
        IMediaControllerCallback mIControllerCallback;

        public void onAudioInfoChanged(PlaybackInfo playbackInfo) {
        }

        public void onCaptioningEnabledChanged(boolean z) {
        }

        public void onExtrasChanged(Bundle bundle) {
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
        }

        public void onRepeatModeChanged(int i) {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String str, Bundle bundle) {
        }

        public void onSessionReady() {
        }

        public void onShuffleModeChanged(int i) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public IMediaControllerCallback getIControllerCallback() {
            return this.mIControllerCallback;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            postToHandler(8, null, null);
        }

        void setHandler(Handler handler) {
            if (handler == null) {
                MessageHandler messageHandler = this.mHandler;
                if (messageHandler != null) {
                    messageHandler.mRegistered = false;
                    messageHandler.removeCallbacksAndMessages(null);
                    this.mHandler = null;
                    return;
                }
                return;
            }
            MessageHandler messageHandler2 = new MessageHandler(handler.getLooper());
            this.mHandler = messageHandler2;
            messageHandler2.mRegistered = true;
        }

        void postToHandler(int i, Object obj, Bundle bundle) {
            MessageHandler messageHandler = this.mHandler;
            if (messageHandler != null) {
                Message messageObtainMessage = messageHandler.obtainMessage(i, obj);
                messageObtainMessage.setData(bundle);
                messageObtainMessage.sendToTarget();
            }
        }

        @RequiresApi(21)
        private static class MediaControllerCallbackApi21 extends MediaController.Callback {
            private final WeakReference<Callback> mCallback;

            MediaControllerCallbackApi21(Callback callback) {
                this.mCallback = new WeakReference<>(callback);
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    IMediaControllerCallback iMediaControllerCallback = callback.mIControllerCallback;
                    callback.onSessionEvent(str, bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                Callback callback = this.mCallback.get();
                if (callback == null || callback.mIControllerCallback != null) {
                    return;
                }
                callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(playbackState));
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List<MediaSession.QueueItem> list) {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.onExtrasChanged(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.onAudioInfoChanged(new PlaybackInfo(playbackInfo.getPlaybackType(), AudioAttributesCompat.wrap(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
                }
            }
        }

        private static class StubCompat extends IMediaControllerCallback.Stub {
            private final WeakReference<Callback> mCallback;

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onShuffleModeChangedRemoved(boolean z) throws RemoteException {
            }

            StubCompat(Callback callback) {
                this.mCallback = new WeakReference<>(callback);
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onEvent(String str, Bundle bundle) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(1, str, bundle);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onSessionDestroyed() throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(8, null, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(3, mediaMetadataCompat, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(5, list, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(6, charSequence, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onCaptioningEnabledChanged(boolean z) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(11, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onRepeatModeChanged(int i) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(9, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onShuffleModeChanged(int i) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(12, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(7, bundle, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(4, parcelableVolumeInfo != null ? new PlaybackInfo(parcelableVolumeInfo.volumeType, parcelableVolumeInfo.audioStream, parcelableVolumeInfo.controlType, parcelableVolumeInfo.maxVolume, parcelableVolumeInfo.currentVolume) : null, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void onSessionReady() throws RemoteException {
                Callback callback = this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(13, null, null);
                }
            }
        }

        private class MessageHandler extends Handler {
            boolean mRegistered;

            MessageHandler(Looper looper) {
                super(looper);
                this.mRegistered = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.mRegistered) {
                    switch (message.what) {
                        case 1:
                            Bundle data = message.getData();
                            MediaSessionCompat.ensureClassLoader(data);
                            Callback.this.onSessionEvent((String) message.obj, data);
                            break;
                        case 2:
                            Callback.this.onPlaybackStateChanged((PlaybackStateCompat) message.obj);
                            break;
                        case 3:
                            Callback.this.onMetadataChanged((MediaMetadataCompat) message.obj);
                            break;
                        case 4:
                            Callback.this.onAudioInfoChanged((PlaybackInfo) message.obj);
                            break;
                        case 5:
                            Callback.this.onQueueChanged((List) message.obj);
                            break;
                        case 6:
                            Callback.this.onQueueTitleChanged((CharSequence) message.obj);
                            break;
                        case 7:
                            Bundle bundle = (Bundle) message.obj;
                            MediaSessionCompat.ensureClassLoader(bundle);
                            Callback.this.onExtrasChanged(bundle);
                            break;
                        case 8:
                            Callback.this.onSessionDestroyed();
                            break;
                        case 9:
                            Callback.this.onRepeatModeChanged(((Integer) message.obj).intValue());
                            break;
                        case 11:
                            Callback.this.onCaptioningEnabledChanged(((Boolean) message.obj).booleanValue());
                            break;
                        case 12:
                            Callback.this.onShuffleModeChanged(((Integer) message.obj).intValue());
                            break;
                        case 13:
                            Callback.this.onSessionReady();
                            break;
                    }
                }
            }
        }
    }

    public static abstract class TransportControls {
        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String str, Bundle bundle);

        public abstract void prepareFromMediaId(String str, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long j);

        public abstract void sendCustomAction(String str, Bundle bundle);

        public void setPlaybackSpeed(float f) {
        }

        public abstract void setRepeatMode(int i);

        public abstract void setShuffleMode(int i);

        public abstract void stop();

        TransportControls() {
        }
    }

    public static final class PlaybackInfo {
        private final AudioAttributesCompat mAudioAttrsCompat;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int i, int i2, int i3, int i4, int i5) {
            this(i, new AudioAttributesCompat.Builder().setLegacyStreamType(i2).build(), i3, i4, i5);
        }

        PlaybackInfo(int i, @NonNull AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.mPlaybackType = i;
            this.mAudioAttrsCompat = audioAttributesCompat;
            this.mVolumeControl = i2;
            this.mMaxVolume = i3;
            this.mCurrentVolume = i4;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        @Deprecated
        public int getAudioStream() {
            return this.mAudioAttrsCompat.getLegacyStreamType();
        }

        public int getVolumeControl() {
            return this.mVolumeControl;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }
    }

    @RequiresApi(21)
    static class MediaControllerImplApi21 implements MediaControllerImpl {
        protected final MediaController mControllerFwk;
        final MediaSessionCompat.Token mSessionToken;
        final Object mLock = new Object();

        @GuardedBy("mLock")
        private final List<Callback> mPendingCallbacks = new ArrayList();
        private HashMap<Callback, ExtraCallback> mCallbackMap = new HashMap<>();

        MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.mSessionToken = token;
            this.mControllerFwk = new MediaController(context, (MediaSession.Token) token.getToken());
            if (token.getExtraBinder() == null) {
                requestExtraBinder();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public final void registerCallback(Callback callback, Handler handler) {
            this.mControllerFwk.registerCallback(callback.mCallbackFwk, handler);
            synchronized (this.mLock) {
                if (this.mSessionToken.getExtraBinder() != null) {
                    ExtraCallback extraCallback = new ExtraCallback(callback);
                    this.mCallbackMap.put(callback, extraCallback);
                    callback.mIControllerCallback = extraCallback;
                    try {
                        this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
                        callback.postToHandler(13, null, null);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                } else {
                    callback.mIControllerCallback = null;
                    this.mPendingCallbacks.add(callback);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public final void unregisterCallback(Callback callback) {
            this.mControllerFwk.unregisterCallback(callback.mCallbackFwk);
            synchronized (this.mLock) {
                if (this.mSessionToken.getExtraBinder() != null) {
                    try {
                        ExtraCallback extraCallbackRemove = this.mCallbackMap.remove(callback);
                        if (extraCallbackRemove != null) {
                            callback.mIControllerCallback = null;
                            this.mSessionToken.getExtraBinder().unregisterCallbackListener(extraCallbackRemove);
                        }
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                    }
                }
                this.mPendingCallbacks.remove(callback);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            return this.mControllerFwk.dispatchMediaButtonEvent(keyEvent);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public TransportControls getTransportControls() {
            MediaController.TransportControls transportControls = this.mControllerFwk.getTransportControls();
            if (Build.VERSION.SDK_INT >= 29) {
                return new TransportControlsApi29(transportControls);
            }
            return new TransportControlsApi24(transportControls);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public PlaybackStateCompat getPlaybackState() {
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getPlaybackState();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                }
            }
            PlaybackState playbackState = this.mControllerFwk.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.fromPlaybackState(playbackState);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public MediaMetadataCompat getMetadata() {
            MediaMetadata metadata = this.mControllerFwk.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.fromMediaMetadata(metadata);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public int getRatingType() {
            return this.mControllerFwk.getRatingType();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public int getRepeatMode() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return -1;
            }
            try {
                return this.mSessionToken.getExtraBinder().getRepeatMode();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public int getShuffleMode() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return -1;
            }
            try {
                return this.mSessionToken.getExtraBinder().getShuffleMode();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
        public PendingIntent getSessionActivity() {
            return this.mControllerFwk.getSessionActivity();
        }

        public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.mControllerFwk.sendCommand(str, bundle, resultReceiver);
        }

        private void requestExtraBinder() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @GuardedBy("mLock")
        void processPendingCallbacksLocked() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return;
            }
            for (Callback callback : this.mPendingCallbacks) {
                ExtraCallback extraCallback = new ExtraCallback(callback);
                this.mCallbackMap.put(callback, extraCallback);
                callback.mIControllerCallback = extraCallback;
                try {
                    this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
                    callback.postToHandler(13, null, null);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                }
            }
            this.mPendingCallbacks.clear();
        }

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> mMediaControllerImpl;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.mMediaControllerImpl = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.mMediaControllerImpl.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.mLock) {
                    mediaControllerImplApi21.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.mSessionToken.setSession2Token(ParcelUtils.getVersionedParcelable(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                    mediaControllerImplApi21.processPendingCallbacksLocked();
                }
            }
        }

        private static class ExtraCallback extends Callback.StubCompat {
            ExtraCallback(Callback callback) {
                super(callback);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
            public void onSessionDestroyed() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback.StubCompat, android.support.v4.media.session.IMediaControllerCallback
            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                throw new AssertionError();
            }
        }
    }

    @RequiresApi(29)
    static class MediaControllerImplApi29 extends MediaControllerImplApi21 {
        MediaControllerImplApi29(Context context, MediaSessionCompat.Token token) {
            super(context, token);
        }
    }

    @RequiresApi(21)
    static class TransportControlsApi21 extends TransportControls {
        protected final MediaController.TransportControls mControlsFwk;

        TransportControlsApi21(MediaController.TransportControls transportControls) {
            this.mControlsFwk = transportControls;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void prepareFromMediaId(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", str);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", bundle2);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void play() {
            this.mControlsFwk.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void pause() {
            this.mControlsFwk.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void stop() {
            this.mControlsFwk.stop();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void seekTo(long j) {
            this.mControlsFwk.seekTo(j);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void fastForward() {
            this.mControlsFwk.fastForward();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void rewind() {
            this.mControlsFwk.rewind();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void setPlaybackSpeed(float f) {
            if (f == 0.0f) {
                throw new IllegalArgumentException("speed must not be zero");
            }
            Bundle bundle = new Bundle();
            bundle.putFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", f);
            sendCustomAction("android.support.v4.media.session.action.SET_PLAYBACK_SPEED", bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void setRepeatMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void setShuffleMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void playFromMediaId(String str, Bundle bundle) {
            this.mControlsFwk.playFromMediaId(str, bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void sendCustomAction(String str, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(str, bundle);
            this.mControlsFwk.sendCustomAction(str, bundle);
        }
    }

    @RequiresApi(23)
    static class TransportControlsApi23 extends TransportControlsApi21 {
        TransportControlsApi23(MediaController.TransportControls transportControls) {
            super(transportControls);
        }
    }

    @RequiresApi(24)
    static class TransportControlsApi24 extends TransportControlsApi23 {
        TransportControlsApi24(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControlsApi21, android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void prepareFromMediaId(String str, Bundle bundle) {
            this.mControlsFwk.prepareFromMediaId(str, bundle);
        }
    }

    @RequiresApi(29)
    static class TransportControlsApi29 extends TransportControlsApi24 {
        TransportControlsApi29(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.TransportControlsApi21, android.support.v4.media.session.MediaControllerCompat.TransportControls
        public void setPlaybackSpeed(float f) {
            if (f == 0.0f) {
                throw new IllegalArgumentException("speed must not be zero");
            }
            this.mControlsFwk.setPlaybackSpeed(f);
        }
    }
}
