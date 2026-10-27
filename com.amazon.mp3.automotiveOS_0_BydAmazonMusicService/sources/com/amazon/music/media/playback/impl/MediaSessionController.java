package com.amazon.music.media.playback.impl;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.VolumeProviderCompat;
import androidx.media.utils.MediaConstants;
import androidx.mediarouter.media.MediaItemMetadata;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnMediaButtonListener;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackErrorListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCookie;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.config.ImagePlaceholderProvider;
import com.amazon.music.media.playback.config.LibraryAccessProvider;
import com.amazon.music.media.playback.config.MediaItemImageManager;
import com.amazon.music.media.playback.config.MediaSessionResources;
import com.amazon.music.media.playback.config.OnMediaBrowseHandler;
import com.amazon.music.media.playback.config.OnMediaSearchHandler;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.RateLimiter;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.util.StringUtil;
import com.google.android.gms.cast.MediaStatus;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MediaSessionController {
    private static final String EXTRA_COMPONENT_NAME;
    private static final long SCRUB_ACTIVATE_DELAY_MILLIS;
    private static final long SEEK_INCREMENT_MILLIS;
    private static final Logger logger = LoggerFactory.getLogger("MediaSessionController");
    private final Context applicationContext;
    private boolean hasShuffledEnabled;
    private MediaItemImageManager imageManager;
    private boolean lastCanPlay;
    private long lastDuration;
    private MediaItem lastItem;
    private int lastMediaMetadataRating;
    private RepeatMode lastRepeatMode;
    private float lastRunSpeed;
    private Uri lastUpdateImageUri;
    private LibraryAccessProvider libraryAccessProvider;
    private ComponentName mediaBrowserComponentName;
    private OnMediaButtonListener mediaButtonListener;
    private final MediaSessionCompat mediaSession;
    private final OnMetadataChangedListener metadataListener;
    private final OnPlayStateChangedListener onPlayStateChangedListener;
    private final OnPlaybackErrorListener onPlaybackErrorListener;
    private final OnPlaybackEventListener onPlaybackEventListener;
    private PlayStatus playStatus;
    private final PlaybackController playbackController;
    private List<MediaItem> queueItems;
    private String queueTitle;
    private final RateLimiter rateLimiter;
    private long scrubActivateTime;
    private VolumeProviderCompat volumeProvider;
    private int lastCustomCommandRating = 0;
    private boolean actionDownReceived = false;
    private boolean isItemUpdatedInLibrary = false;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        SEEK_INCREMENT_MILLIS = timeUnit.toMillis(1L);
        SCRUB_ACTIVATE_DELAY_MILLIS = timeUnit.toMillis(2L);
        if (Build.VERSION.SDK_INT >= 26) {
            EXTRA_COMPONENT_NAME = "android.intent.extra.COMPONENT_NAME";
        } else {
            EXTRA_COMPONENT_NAME = "android.intent.extra.COMPONENT_NAME";
        }
    }

    public MediaSessionController(Context context, PlaybackController playbackController) {
        OnMetadataChangedListener onMetadataChangedListener = new OnMetadataChangedListener() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.1
            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMetadataChanged(MediaItem mediaItem) {
                MediaSessionController.this.updateMetadata();
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
                if (mediaCollectionInfo == null) {
                    MediaSessionController.this.queueTitle = null;
                } else {
                    MediaSessionController.this.queueTitle = mediaCollectionInfo.getName();
                }
                MediaSessionController.this.updateMetadata();
            }
        };
        this.metadataListener = onMetadataChangedListener;
        OnPlayStateChangedListener onPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.5
            private boolean previouslyPlaying = false;

            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public void onPlayStateChanged() {
                MediaSessionController.logger.info("Update media session metadata");
                MediaSessionController.this.updateIsActive();
                MediaSessionController.this.triggerUpdate();
                boolean zWillPlay = MediaSessionController.this.playbackController.getPlayStatus().willPlay();
                if (!this.previouslyPlaying && zWillPlay) {
                    Intent intent = new Intent("com.amazon.alexa.externalmediaplayer.CONNECT");
                    intent.putExtra(MediaSessionController.EXTRA_COMPONENT_NAME, MediaSessionController.this.getMediaBrowserComponentName());
                    MediaSessionController.logger.debug("Sending Alexa Connect Broadcast");
                    MediaSessionController.this.applicationContext.sendOrderedBroadcast(intent, "com.amazon.alexa.externalmediaplayer.permission.WAKE_UP");
                }
                this.previouslyPlaying = zWillPlay;
            }
        };
        this.onPlayStateChangedListener = onPlayStateChangedListener;
        OnPlaybackEventListener onPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.6
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public void onPlaybackEvent(int i, ControlSource controlSource) {
                if (i == 1) {
                    MediaSessionController.this.updatePlaybackState(10, "STATE_SKIPPING_TO_NEXT", null);
                } else {
                    if (i != 2) {
                        return;
                    }
                    MediaSessionController.this.updatePlaybackState(9, "STATE_SKIPPING_TO_PREVIOUS", null);
                }
            }
        };
        this.onPlaybackEventListener = onPlaybackEventListener;
        OnPlaybackErrorListener onPlaybackErrorListener = new OnPlaybackErrorListener() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.7
            @Override // com.amazon.music.media.playback.OnPlaybackErrorListener
            public void onPlaybackError(PlaybackException playbackException) {
                MediaSessionController.this.updatePlaybackState(7, "STATE_ERROR", playbackException.getPlaybackError());
            }
        };
        this.onPlaybackErrorListener = onPlaybackErrorListener;
        PlaybackController playbackController2 = (PlaybackController) Validate.notNull(playbackController);
        this.playbackController = playbackController2;
        this.applicationContext = context.getApplicationContext();
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, logger.getName());
        this.mediaSession = mediaSessionCompat;
        mediaSessionCompat.setFlags(3);
        mediaSessionCompat.setCallback(new MediaSessionCallback());
        mediaSessionCompat.setRatingType(2);
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setClass(context, MediaButtonReceiver.class);
        mediaSessionCompat.setMediaButtonReceiver(PendingIntent.getBroadcast(context, 0, intent, voOSType.VOOSMP_SRC_FFAUDIO_MIDI));
        MediaItemImageManager mediaItemImageManager = new MediaItemImageManager(playbackController.getPlayback().getPlaybackConfig(), 500, 500, new MediaItem.ImageType[0]);
        this.imageManager = mediaItemImageManager;
        mediaItemImageManager.addOnBitmapChangedListener(new MediaItemImageManager.OnBitmapChangedListener() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.2
            @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
            public void onBitmapChanged(MediaItemImageManager mediaItemImageManager2) {
                MediaSessionController.this.triggerUpdate();
            }
        });
        this.libraryAccessProvider = playbackController.getPlayback().getPlaybackConfig().getLibraryAccessProvider();
        this.rateLimiter = new RateLimiter(new Runnable() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.3
            @Override // java.lang.Runnable
            public void run() {
                MediaSessionController.this.updateMediaSession();
            }
        }, 500L, 500L);
        updateMediaSession();
        playbackController2.addOnPlayStateChangedListener(onPlayStateChangedListener);
        playbackController2.addOnMetadataChangedListener(onMetadataChangedListener);
        playbackController2.addOnPlaybackEventListener(onPlaybackEventListener);
        playbackController2.addOnPlaybackErrorListener(onPlaybackErrorListener);
        AndroidUtils.addForegroundListener(new AndroidUtils.ForegroundListener() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.4
            @Override // com.amazon.music.media.playback.util.AndroidUtils.ForegroundListener
            public void onApplicationInForegroundChanged(boolean z) {
                MediaSessionController.this.updateIsActive();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerUpdate() {
        this.rateLimiter.onDataChanged(this.imageManager.getPlaceholderType() != ImagePlaceholderProvider.PlaceholderType.PLACEHOLDER);
    }

    public void setMediaButtonListener(OnMediaButtonListener onMediaButtonListener) {
        this.mediaButtonListener = onMediaButtonListener;
    }

    public MediaSessionCompat getMediaSession() {
        return this.mediaSession;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ComponentName getMediaBrowserComponentName() {
        if (this.mediaBrowserComponentName == null) {
            Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
            intent.setPackage(this.applicationContext.getPackageName());
            List<ResolveInfo> listQueryIntentServices = this.applicationContext.getPackageManager().queryIntentServices(intent, 128);
            if (listQueryIntentServices == null || listQueryIntentServices.size() != 1 || listQueryIntentServices.get(0) == null) {
                StrictMode.crashIfStrict("Multiple or No MediaBrowserService found in the app manifest, need to register one and one only");
            }
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            this.mediaBrowserComponentName = new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name);
        }
        return this.mediaBrowserComponentName;
    }

    private class MediaSessionCallback extends MediaSessionCompat.Callback {
        private MediaSessionCallback() {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onCustomAction(String str, Bundle bundle) {
            MediaSessionController.logger.info("media session callback - onCustomAction(" + str + ")");
            if ("SHUFFLE_ACTION".equals(str)) {
                MediaSessionController.this.playbackController.setShuffled(!MediaSessionController.this.playbackController.isShuffled());
            } else if ("waze.thumbDown".equals(str)) {
                boolean zCanSkipWithSkipLimiter = MediaSessionController.this.canSkipWithSkipLimiter();
                MediaSessionController.logger.info("onCustomAction: toggleThumbsDown,  canSkip is" + zCanSkipWithSkipLimiter);
                MediaSessionController.this.playbackController.toggleThumbsDown();
            } else if ("waze.thumbUp".equals(str)) {
                MediaSessionController.this.playbackController.toggleThumbsUp();
            } else if ("waze.save".equals(str)) {
                MediaSessionController.this.isItemUpdatedInLibrary = true;
                if (MediaSessionController.this.libraryAccessProvider.isInLibrary(MediaSessionController.this.playbackController.getCurrentMediaItem())) {
                    MediaSessionController.this.libraryAccessProvider.removeFromLibrary(MediaSessionController.this.playbackController.getCurrentMediaItem());
                } else {
                    MediaSessionController.this.libraryAccessProvider.addToLibrary(MediaSessionController.this.playbackController.getCurrentMediaItem());
                }
            } else if ("REPEAT_ACTION".equals(str)) {
                MediaSessionController.this.playbackController.toggleRepeatMode();
            }
            MediaSessionController.this.updatePlaybackState(0, null, null);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlay() {
            if (ignoreEvents(true)) {
                return;
            }
            MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().getPlaybackPersistenceProvider().restore();
            MediaSessionController.logger.info("media session callback - play");
            MediaSessionController.this.playbackController.normalSpeed();
            MediaSessionController.this.playbackController.play();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPause() {
            if (ignoreEvents(true)) {
                return;
            }
            MediaSessionController.logger.info("media session callback - pause");
            MediaSessionController.this.playbackController.normalSpeed();
            MediaSessionController.this.playbackController.pause();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onStop() {
            if (ignoreEvents(true)) {
                return;
            }
            MediaSessionController.logger.info("media session callback - stop");
            MediaSessionController.this.playbackController.normalSpeed();
            MediaSessionController.this.playbackController.pause();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToNext() {
            if (ignoreEvents(true)) {
                return;
            }
            MediaSessionController.logger.info("media session callback - skip next");
            if (MediaSessionController.this.canSkipWithSkipLimiter()) {
                MediaSessionController.this.playbackController.skipNext();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToPrevious() {
            if (ignoreEvents(true)) {
                return;
            }
            MediaSessionController.logger.info("media session callback - skip previous");
            MediaSessionController.this.playbackController.previousOrRestart();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSetShuffleMode(int i) {
            boolean z = true;
            if (i != 1 && i != 2) {
                z = false;
            }
            MediaSessionController.logger.info("media session callback - set shuffle to " + z);
            MediaSessionController.this.playbackController.setShuffled(z);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSeekTo(long j) {
            if (ignoreEvents(true)) {
                return;
            }
            MediaSessionController.logger.info("media session callback - seek to " + j);
            MediaSessionController.this.playbackController.seek(j, 0);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSetRepeatMode(int i) {
            RepeatMode repeatMode;
            if (i == 1) {
                repeatMode = RepeatMode.REPEAT_ONE;
            } else if (i == 2 || i == 3) {
                repeatMode = RepeatMode.REPEAT_ALL;
            } else {
                repeatMode = RepeatMode.REPEAT_NONE;
            }
            MediaSessionController.logger.info("media session callback - set repeat mode to " + repeatMode.name());
            MediaSessionController.this.playbackController.setRepeatMode(repeatMode);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPrepareFromSearch(String str, Bundle bundle) {
            MediaSessionController.logger.info("onPrepareFromSearch: " + str);
            OnMediaSearchHandler onMediaSearchHandler = MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().getOnMediaSearchHandler();
            if (onMediaSearchHandler != null) {
                onMediaSearchHandler.onMediaSearch(str, bundle, false);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlayFromSearch(String str, Bundle bundle) {
            MediaSessionController.logger.info("onPlayFromSearch: " + str);
            OnMediaSearchHandler onMediaSearchHandler = MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().getOnMediaSearchHandler();
            if (onMediaSearchHandler != null) {
                onMediaSearchHandler.onMediaSearch(str, bundle, true);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPrepareFromMediaId(String str, Bundle bundle) {
            MediaSessionController.logger.info("onPrepareFromMediaId: " + str);
            OnMediaBrowseHandler onMediaBrowseHandler = MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().getOnMediaBrowseHandler();
            if (onMediaBrowseHandler != null) {
                onMediaBrowseHandler.onMediaBrowse(str, bundle, false);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlayFromMediaId(String str, Bundle bundle) {
            MediaSessionController.logger.info("onPlayFromMediaId: " + str);
            OnMediaBrowseHandler onMediaBrowseHandler = MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().getOnMediaBrowseHandler();
            if (onMediaBrowseHandler != null) {
                onMediaBrowseHandler.onMediaBrowse(str, bundle, true);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlayFromUri(Uri uri, Bundle bundle) {
            MediaSessionController.logger.info("onPlayFromUri: " + uri.toString());
            OnMediaBrowseHandler onMediaBrowseHandler = MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().getOnMediaBrowseHandler();
            if (onMediaBrowseHandler != null) {
                onMediaBrowseHandler.onMediaBrowse(uri, bundle, true);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPrepareFromUri(Uri uri, Bundle bundle) {
            MediaSessionController.logger.info("onPrepareFromUri: " + uri.toString());
            OnMediaBrowseHandler onMediaBrowseHandler = MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().getOnMediaBrowseHandler();
            if (onMediaBrowseHandler != null) {
                onMediaBrowseHandler.onMediaBrowse(uri, bundle, false);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public boolean onMediaButtonEvent(Intent intent) {
            int keyCode;
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            MediaSessionController.logger.info("onMediaButtonEvent: " + keyEvent);
            if (ignoreEvents(false)) {
                MediaSessionController.logger.info("ignoring media button");
                return true;
            }
            int action = keyEvent.getAction();
            int keyCode2 = keyEvent.getKeyCode();
            if (action != 1 || MediaSessionController.this.actionDownReceived) {
                if (action == 1) {
                    MediaSessionController.this.actionDownReceived = false;
                } else if (action == 0) {
                    MediaSessionController.this.actionDownReceived = true;
                }
                if (MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().isCustomKeyHandlingRequired()) {
                    int keyCode3 = keyEvent.getKeyCode();
                    if (keyCode3 != 85 && keyCode3 != 126 && keyCode3 != 127) {
                        switch (keyCode3) {
                            case 87:
                                if (MediaSessionController.this.mediaButtonListener != null) {
                                    MediaSessionController.this.mediaButtonListener.skipNext();
                                }
                                break;
                            case 88:
                                if (MediaSessionController.this.mediaButtonListener != null) {
                                    MediaSessionController.this.mediaButtonListener.skipPrevious();
                                }
                                break;
                            case 89:
                            case 90:
                                handlePossibleSkips(keyEvent);
                                break;
                        }
                        return true;
                    }
                    handlePlayButton(keyEvent);
                    return true;
                }
                if (keyEvent.getAction() == 1 && ((keyCode = keyEvent.getKeyCode()) == 89 || keyCode == 90)) {
                    MediaSessionController.this.playbackController.normalSpeed();
                }
                return super.onMediaButtonEvent(intent);
            }
            MediaSessionController.this.mediaSession.getController().dispatchMediaButtonEvent(new KeyEvent(0, keyCode2));
            MediaSessionController.this.mediaSession.getController().dispatchMediaButtonEvent(new KeyEvent(1, keyCode2));
            return true;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            MediaSessionController.logger.info("onCommand: " + str);
            super.onCommand(str, bundle, resultReceiver);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToQueueItem(long j) {
            if (MediaSessionController.this.playbackController.getPlayback().getPlaybackConfig().isOnlineInteraction()) {
                MediaSessionController.logger.info("onSkipToQueueItem: called while queue is online interaction. This breaks augmented shuffle");
                return;
            }
            MediaSessionController.logger.info("onSkipToQueueItem: " + j);
            long j2 = 12884901888L & j;
            if (j2 == AudioFormat.WIDE_RIGHT) {
                MediaSessionController.this.playbackController.skipToIndex((int) j);
                return;
            }
            if (j2 == AudioFormat.SURROUND_DIRECT_LEFT) {
                MediaSessionController.this.playbackController.skip((int) j);
                return;
            }
            MediaSessionController.logger.warn("Illegal id: " + j);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSetRating(RatingCompat ratingCompat) {
            if (ignoreEvents(true)) {
                return;
            }
            MediaSessionController.logger.info("onSetRating: " + ratingCompat);
            if (ratingCompat.getRatingStyle() != 2) {
                return;
            }
            if (!ratingCompat.isRated()) {
                MediaSessionController.this.playbackController.setCurrentItemRating(0);
                return;
            }
            if (ratingCompat.isThumbUp()) {
                MediaSessionController.this.playbackController.setCurrentItemRating(1);
                return;
            }
            if (MediaSessionController.this.playbackController.getCurrentItemRating() != 2) {
                boolean zCanSkipWithSkipLimiter = MediaSessionController.this.canSkipWithSkipLimiter();
                MediaSessionController.logger.info("onSetRating: toggleThumbsDown,  canSkip is" + zCanSkipWithSkipLimiter);
                MediaSessionController.this.playbackController.toggleThumbsDown();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onRewind() {
            MediaSessionController.logger.info("onRewind");
            MediaSessionController.this.playbackController.rewind();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onFastForward() {
            MediaSessionController.logger.info("onFastForward");
            MediaSessionController.this.playbackController.fastForward();
        }

        private void handlePossibleSkips(KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            int action = keyEvent.getAction();
            if (action == 0) {
                handleActionDown(keyCode);
            } else {
                if (action != 1) {
                    return;
                }
                handleActionUp(keyCode);
            }
        }

        private void handlePlayButton(KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                if (MediaSessionController.this.playbackController.getPlayStatus().willPlay()) {
                    onPause();
                } else {
                    onPlay();
                }
            }
        }

        private void handleActionDown(int i) {
            if (MediaSessionController.this.scrubActivateTime == 0) {
                MediaSessionController.this.scrubActivateTime = Clock.now() + MediaSessionController.SCRUB_ACTIVATE_DELAY_MILLIS;
            } else if (Clock.now() >= MediaSessionController.this.scrubActivateTime) {
                if (i == 89) {
                    MediaSessionController.this.playbackController.rewind();
                } else {
                    if (i != 90) {
                        return;
                    }
                    MediaSessionController.this.playbackController.fastForward();
                }
            }
        }

        private void handleActionUp(int i) {
            if (Clock.now() < MediaSessionController.this.scrubActivateTime) {
                skip(i);
            }
            MediaSessionController.this.playbackController.normalSpeed();
            MediaSessionController.this.scrubActivateTime = 0L;
        }

        private void skip(int i) {
            if (i == 89) {
                onSkipToPrevious();
            } else {
                if (i != 90) {
                    return;
                }
                onSkipToNext();
            }
        }

        private boolean ignoreEvents(boolean z) {
            return !MediaSessionController.this.mediaSession.isActive() || (!z && MediaSessionController.this.playbackController.getAudioFocusStatus() == -2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIsActive() {
        int audioFocusStatus;
        boolean zIsActive = this.mediaSession.isActive();
        boolean z = true;
        boolean z2 = AndroidUtils.isApplicationInForeground() || !this.playbackController.getPlayback().getPlaybackConfig().isMediaSessionLimited();
        if (z2 || !zIsActive || ((audioFocusStatus = this.playbackController.getAudioFocusStatus()) != -3 && audioFocusStatus != -2 && audioFocusStatus != 1)) {
            z = z2;
        }
        if (z != this.mediaSession.isActive()) {
            this.mediaSession.setActive(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMediaSession() {
        updateIsActive();
        updatePlaybackState(0, null, null);
        updateMetadata();
        updateVolumeProvider();
        updateRepeatMode();
        updateShuffleMode();
        updateMediaSessionQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"WrongConstant"})
    public void updatePlaybackState(int i, @Nullable String str, @Nullable PlaybackException.PlaybackError playbackError) {
        PlayStatus playStatus;
        PlayStatus playStatus2 = this.playbackController.getPlayStatus();
        float playbackSpeed = this.playbackController.getPlaybackSpeed();
        if (playbackSpeed == 1.0f && playStatus2 != PlayStatus.RENDERING) {
            playbackSpeed = 0.0f;
        }
        if (playbackError == null && !hasPlayBackStateChanged(playStatus2) && this.lastRunSpeed == playbackSpeed) {
            return;
        }
        this.lastRunSpeed = playbackSpeed;
        this.hasShuffledEnabled = this.playbackController.isShuffled();
        this.lastCustomCommandRating = this.playbackController.getCurrentItemRating();
        this.lastCanPlay = this.playbackController.canPlay();
        this.lastRepeatMode = this.playbackController.getRepeatMode();
        if (this.playbackController.getCurrentMediaItem() != this.lastItem) {
            MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
            this.lastItem = currentMediaItem;
            if (currentMediaItem != null && playStatus2 != PlayStatus.USER_PAUSED && playStatus2 != (playStatus = PlayStatus.LOADING)) {
                triggerUpdate();
                playStatus2 = playStatus;
            }
        }
        this.playStatus = playStatus2;
        PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder();
        long j = 240640;
        if (this.playbackController.canPlay()) {
            j = 241152 | (playStatus2.willPlay() ? 3L : 4L);
            if (this.playbackController.canSeek()) {
                j |= 256;
            }
            if (this.playbackController.canFastForward()) {
                j |= 64;
            }
            if (this.playbackController.canRewind()) {
                j |= 8;
            }
            if (this.playbackController.canSkipToIndex()) {
                j |= 4096;
            }
            if (this.playbackController.canSkipNext()) {
                j |= 32;
            }
            if (this.playbackController.canSkipPrevious() || this.playbackController.canRestartItem()) {
                j |= 16;
            }
            if (this.playbackController.canRateCurrentItem()) {
                j |= 128;
            }
            if (this.playbackController.canShuffle()) {
                j = j | 524288 | 2097152;
            }
            if (this.playbackController.getAvailableRepeatModes().size() > 1) {
                j |= MediaStatus.COMMAND_STREAM_TRANSFER;
            }
        } else if (this.lastItem != null) {
            j = 241156;
        }
        builder.setActions(j);
        if (i <= 0) {
            if (playStatus2 == PlayStatus.LOADING) {
                i = 11;
                str = "STATE_SKIPPING_TO_QUEUE_ITEM";
            } else if (playbackSpeed > 1.0f) {
                i = 4;
                str = "STATE_FAST_FORWARDING";
            } else if (playbackSpeed < 0.0f) {
                i = 5;
                str = "STATE_REWINDING";
            } else if (!playStatus2.willPlay()) {
                str = "STATE_PAUSED";
                i = 2;
            } else if (playStatus2 == PlayStatus.RENDERING) {
                i = 3;
                str = "STATE_PLAYING";
            } else {
                i = 6;
                str = "STATE_BUFFERING";
            }
        }
        long positionMillis = this.playbackController.getPositionMillis();
        Logger logger2 = logger;
        logger2.info("setState(" + str + ", " + positionMillis + ", " + playbackSpeed + ")");
        builder.setState(i, positionMillis, playbackSpeed);
        if (playbackError != null) {
            logger2.info("setErrorMessage with error code - " + playbackError.getErrorCode() + ", description - " + playbackError.getErrorDescription());
            builder.setErrorMessage(playbackError.getErrorCode(), playbackError.getErrorDescription());
        }
        addRepeatAction(builder);
        addRatingAction(builder);
        addAddToLibraryAction(builder);
        addShuffleAction(builder);
        Bundle bundle = new Bundle();
        bundle.putBoolean("waze.state.isThumbUp", this.playbackController.getCurrentItemRating() == 1);
        bundle.putBoolean("waze.state.isThumbDown", this.playbackController.getCurrentItemRating() == 2);
        MediaItem mediaItem = this.lastItem;
        if (mediaItem != null && this.libraryAccessProvider != null) {
            if (!mediaItem.canRate()) {
                ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.impl.MediaSessionController.8
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MediaSessionController.this.isItemUpdatedInLibrary) {
                            return;
                        }
                        MediaSessionController.this.libraryAccessProvider.updateLibraryWithItem(MediaSessionController.this.lastItem);
                    }
                });
                bundle.putBoolean("waze.state.isSaved", !this.libraryAccessProvider.canAddToLibrary(this.lastItem));
            } else {
                bundle.remove("waze.state.isSaved");
            }
        }
        this.isItemUpdatedInLibrary = false;
        addAppVersion(bundle);
        addPlaybackSourceId(bundle, this.playbackController.getCurrentMediaItem());
        addPlayerCookie(bundle);
        builder.setExtras(bundle);
        this.mediaSession.setPlaybackState(builder.build());
    }

    private void addPlaybackSourceId(Bundle bundle, MediaItem mediaItem) {
        List<MediaCollectionInfo> collections;
        if (mediaItem == null || (collections = mediaItem.getCollections()) == null || collections.isEmpty()) {
            return;
        }
        MediaCollectionInfo mediaCollectionInfo = collections.get(0);
        MediaCollectionId id = mediaCollectionInfo.getId(MediaCollectionId.Type.ALEXA_PLAYBACK_SESSION_ID);
        if (id == null) {
            id = mediaCollectionInfo.getId(MediaCollectionId.Type.PLAYBACK_SESSION_ID);
        }
        if (id == null) {
            return;
        }
        logger.debug(String.format("Setting the Playback session id %s", id.getId()));
        bundle.putString("com.amazon.alexa.externalmediaplayer.playbackSessionId", id.getId());
    }

    private void addAppVersion(Bundle bundle) {
        try {
            bundle.putString("com.amazon.alexa.externalmediaplayer.playerVersion", this.applicationContext.getPackageManager().getPackageInfo(this.applicationContext.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            logger.error("Error setting the package version in the extras", (Throwable) e);
        }
    }

    private void addPlayerCookie(Bundle bundle) {
        String cookie = PlayerCookie.VERSION3.getCookie();
        logger.debug(String.format("Setting playerCookie to %s", cookie));
        bundle.putString("com.amazon.alexa.externalmediaplayer.playerCookie", cookie);
    }

    private boolean hasPlayBackStateChanged(PlayStatus playStatus) {
        PlayStatus playStatus2 = this.playStatus;
        if (playStatus2 == null) {
            return true;
        }
        return (playStatus2.equals(playStatus) ^ true) || (this.lastItem != this.playbackController.getCurrentMediaItem()) || (this.hasShuffledEnabled != this.playbackController.isShuffled()) || (this.lastCustomCommandRating != this.playbackController.getCurrentItemRating()) || (this.lastCanPlay != this.playbackController.canPlay()) || this.isItemUpdatedInLibrary || (this.lastRepeatMode != this.playbackController.getRepeatMode());
    }

    private boolean isStationPlaying() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || currentMediaItem.getSourceMediaCollectionInfo() == null || currentMediaItem.getSourceMediaCollectionInfo().getType() == null) {
            return false;
        }
        MediaCollectionType type = currentMediaItem.getSourceMediaCollectionInfo().getType();
        return type.equals(MediaCollectionType.PRIME_STATION) || type.equals(MediaCollectionType.PRIME_STATION_SEED) || type.equals(MediaCollectionType.UNLIMITED_STATION) || type.equals(MediaCollectionType.UNLIMITED_STATION_SEED) || type.equals(MediaCollectionType.AMF_STATION) || type.equals(MediaCollectionType.AMF_STATION_SEED);
    }

    private void addRatingAction(@Nonnull PlaybackStateCompat.Builder builder) {
        MediaSessionResources mediaSessionResources = this.playbackController.getPlayback().getPlaybackConfig().getMediaSessionResources();
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (mediaSessionResources != null && currentMediaItem != null && currentMediaItem.canRate() && mediaSessionResources.isThumbsDownResourcesReady() && mediaSessionResources.isThumbsUpResourcesReady()) {
            if (1 == currentMediaItem.getRating()) {
                builder.addCustomAction("waze.thumbDown", mediaSessionResources.getThumbsDownActionName(), mediaSessionResources.getThumbsDownNormalDrawable());
                builder.addCustomAction("waze.thumbUp", mediaSessionResources.getThumbsUpUnrateActionName(), mediaSessionResources.getThumbsUpSelectedDrawable());
            } else if (2 == currentMediaItem.getRating()) {
                builder.addCustomAction("waze.thumbDown", mediaSessionResources.getThumbsDownUnrateActionName(), mediaSessionResources.getThumbsDownSelectedDrawable());
                builder.addCustomAction("waze.thumbUp", mediaSessionResources.getThumbsUpActionName(), mediaSessionResources.getThumbsUpNormalDrawable());
            } else {
                builder.addCustomAction("waze.thumbDown", mediaSessionResources.getThumbsDownActionName(), mediaSessionResources.getThumbsDownNormalDrawable());
                builder.addCustomAction("waze.thumbUp", mediaSessionResources.getThumbsUpActionName(), mediaSessionResources.getThumbsUpNormalDrawable());
            }
        }
    }

    private void addShuffleAction(@Nonnull PlaybackStateCompat.Builder builder) {
        MediaSessionResources mediaSessionResources = this.playbackController.getPlayback().getPlaybackConfig().getMediaSessionResources();
        if (this.playbackController.getCurrentMediaItem() == null || isStationPlaying() || mediaSessionResources == null || !this.playbackController.canShuffle() || !mediaSessionResources.isShuffleResourcesReady()) {
            return;
        }
        if (this.playbackController.isShuffled()) {
            builder.addCustomAction("SHUFFLE_ACTION", mediaSessionResources.getUnshuffleActionName(), mediaSessionResources.getShuffleSelectedDrawable());
        } else {
            builder.addCustomAction("SHUFFLE_ACTION", mediaSessionResources.getShuffleActionName(), mediaSessionResources.getShuffleNormalDrawable());
        }
    }

    private void addRepeatAction(@Nonnull PlaybackStateCompat.Builder builder) {
        MediaSessionResources mediaSessionResources = this.playbackController.getPlayback().getPlaybackConfig().getMediaSessionResources();
        if (mediaSessionResources == null || !mediaSessionResources.isRepeatModeResourcesReady() || this.playbackController.getAvailableRepeatModes().size() <= 2) {
            return;
        }
        if (this.playbackController.getRepeatMode() == RepeatMode.REPEAT_ALL) {
            builder.addCustomAction("REPEAT_ACTION", mediaSessionResources.getRepeatAllModeActionName(), mediaSessionResources.getRepeatAllModeDrawable());
        } else if (this.playbackController.getRepeatMode() == RepeatMode.REPEAT_ONE) {
            builder.addCustomAction("REPEAT_ACTION", mediaSessionResources.getRepeatOneModeActionName(), mediaSessionResources.getRepeatOneModeDrawable());
        } else {
            builder.addCustomAction("REPEAT_ACTION", mediaSessionResources.getRepeatNoneModeActionName(), mediaSessionResources.getRepeatNoneModeDrawable());
        }
    }

    private void addAddToLibraryAction(@Nonnull PlaybackStateCompat.Builder builder) {
        PlaybackConfig playbackConfig = this.playbackController.getPlayback().getPlaybackConfig();
        MediaSessionResources mediaSessionResources = playbackConfig.getMediaSessionResources();
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (!playbackConfig.isShowAddToLibraryIcon() || this.libraryAccessProvider == null || currentMediaItem == null || currentMediaItem.canRate()) {
            return;
        }
        if (playbackConfig.isShowAddIconBasedOnLibraryProvider() && this.libraryAccessProvider.isInLibrary(currentMediaItem)) {
            return;
        }
        builder.addCustomAction("waze.save", mediaSessionResources.getAddToLibraryActionName(), mediaSessionResources.getAddToLibraryDrawable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMetadata() {
        RatingCompat ratingCompatNewUnratedRating;
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        int currentItemRating = this.playbackController.getCurrentItemRating();
        long durationMillis = this.playbackController.getDurationMillis();
        if (hasMetadataChanged()) {
            this.lastItem = currentMediaItem;
            this.lastMediaMetadataRating = currentItemRating;
            this.lastDuration = durationMillis;
            MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
            if (currentMediaItem != null) {
                builder.putString(MediaItemMetadata.KEY_TITLE, currentMediaItem.getName()).putString("android.media.metadata.ALBUM", currentMediaItem.getAlbumName()).putString(MediaItemMetadata.KEY_ARTIST, currentMediaItem.getArtistName()).putString(MediaItemMetadata.KEY_ALBUM_ARTIST, currentMediaItem.getArtistName()).putString("android.media.metadata.MEDIA_ID", currentMediaItem.getMediaItemId().getId());
                if (currentMediaItem.getDurationMillis() != -1) {
                    builder.putLong(MediaItemMetadata.KEY_DURATION, currentMediaItem.getDurationMillis());
                }
                if (currentItemRating == 0) {
                    ratingCompatNewUnratedRating = RatingCompat.newUnratedRating(2);
                } else if (currentItemRating == 1) {
                    ratingCompatNewUnratedRating = RatingCompat.newThumbRating(true);
                } else {
                    ratingCompatNewUnratedRating = currentItemRating != 2 ? null : RatingCompat.newThumbRating(false);
                }
                if (ratingCompatNewUnratedRating != null) {
                    builder.putRating("android.media.metadata.USER_RATING", ratingCompatNewUnratedRating);
                }
            }
            this.imageManager.setMediaItem(currentMediaItem);
            Uri imageUri = this.imageManager.getImageUri();
            if (imageUri != null) {
                builder.putString("android.media.metadata.ALBUM_ART_URI", imageUri.toString());
                this.lastUpdateImageUri = imageUri;
            }
            if (currentMediaItem != null && currentMediaItem.getType() == MediaItem.Type.AD_CONTENT) {
                builder.putLong(MediaConstants.METADATA_KEY_IS_ADVERTISEMENT, 1L);
            }
            updateEMPTrackId(currentMediaItem, builder);
            this.mediaSession.setMetadata(builder.build());
        }
    }

    private void updateEMPTrackId(MediaItem mediaItem, MediaMetadataCompat.Builder builder) {
        String mediaItemIdString;
        String str;
        if (mediaItem == null) {
            return;
        }
        MediaItem.Type type = mediaItem.getType();
        if (MediaItem.Type.AD_CONTENT.equals(type)) {
            mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.URL, "");
            str = "ad";
        } else if (MediaItem.Type.OWNED_CONTENT.equals(type)) {
            mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.OWNED, "");
            str = "objectId";
        } else {
            mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, "");
            str = "asin";
        }
        String str2 = !StringUtil.isNullOrEmpty(mediaItemIdString) ? String.format("%s://%s/", str, mediaItemIdString) : "INVALID";
        builder.putString("com.amazon.alexa.externalmediaplayer.metadata.TRACK_ID", str2);
        logger.debug("Setting EMP media session metadata for trackId:" + str2);
    }

    private boolean hasMetadataChanged() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        int currentItemRating = this.playbackController.getCurrentItemRating();
        long durationMillis = this.playbackController.getDurationMillis();
        MediaItem mediaItem = this.lastItem;
        return (mediaItem == null || mediaItem == null || !mediaItem.equals(currentMediaItem)) || ((this.lastDuration > durationMillis ? 1 : (this.lastDuration == durationMillis ? 0 : -1)) != 0) || (this.lastMediaMetadataRating != currentItemRating) || (this.imageManager.getImageUri() != this.lastUpdateImageUri);
    }

    private void updateVolumeProvider() {
        VolumeProviderCompat volumeProvider = this.playbackController.getVolumeProvider();
        if (this.volumeProvider != volumeProvider) {
            this.volumeProvider = volumeProvider;
            if (volumeProvider != null) {
                this.mediaSession.setPlaybackToRemote(volumeProvider);
            } else {
                this.mediaSession.setPlaybackToLocal(3);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.impl.MediaSessionController$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$RepeatMode;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            $SwitchMap$com$amazon$music$media$playback$RepeatMode = iArr;
            try {
                iArr[RepeatMode.REPEAT_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$RepeatMode[RepeatMode.REPEAT_ALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void updateRepeatMode() {
        int i = AnonymousClass9.$SwitchMap$com$amazon$music$media$playback$RepeatMode[this.playbackController.getRepeatMode().ordinal()];
        if (i == 1) {
            this.mediaSession.setRepeatMode(0);
        } else if (i == 2) {
            this.mediaSession.setRepeatMode(1);
        } else {
            if (i != 3) {
                return;
            }
            this.mediaSession.setRepeatMode(2);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void updateShuffleMode() {
        this.mediaSession.setShuffleMode(this.playbackController.isShuffled() ? 1 : 0);
    }

    private void updateMediaSessionQueue() {
        long j;
        PlaybackConfig playbackConfig = this.playbackController.getPlayback().getPlaybackConfig();
        if (playbackConfig == null) {
            return;
        }
        ArrayList arrayList = null;
        if (playbackConfig.getPreviewCount() <= 0) {
            this.mediaSession.setQueue(null);
            return;
        }
        List<MediaItem> currentQueue = this.playbackController.getCurrentQueue();
        List<MediaItem> list = this.queueItems;
        if (list == null || !list.equals(currentQueue)) {
            this.queueItems = currentQueue;
            MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
            int currentIndex = this.playbackController.getCurrentIndex();
            int iIndexOf = currentQueue.indexOf(currentMediaItem);
            int iMax = Math.max(0, iIndexOf - 1);
            int iMin = ((iIndexOf + 1) - iMax) + Math.min((currentQueue.size() - iIndexOf) - 1, playbackConfig.getPreviewCount());
            long j2 = 0;
            if (iIndexOf < 0) {
                iMin = 0;
                j = 0;
            } else if (currentIndex >= 0) {
                j2 = (currentIndex + iMax) - iIndexOf;
                j = AudioFormat.WIDE_RIGHT;
            } else {
                j2 = iMax - iIndexOf;
                j = AudioFormat.SURROUND_DIRECT_LEFT;
            }
            if (iMin > 0) {
                arrayList = new ArrayList(iMin);
                for (int i = 0; i < iMin; i++) {
                    MediaItem mediaItem = currentQueue.get(iMax + i);
                    MediaDescriptionCompat.Builder builder = new MediaDescriptionCompat.Builder();
                    builder.setTitle(mediaItem.getName()).setSubtitle(mediaItem.getArtistName()).setIconUri(mediaItem.getImageUri(500, 500, new MediaItem.ImageType[0]));
                    arrayList.add(new MediaSessionCompat.QueueItem(builder.build(), ((((long) i) + j2) & 4294967295L) | j));
                }
            }
            this.mediaSession.setQueue(arrayList);
            if (TextUtils.isEmpty(this.queueTitle)) {
                return;
            }
            this.mediaSession.setQueueTitle(this.queueTitle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canSkipWithSkipLimiter() {
        SkipLimitProvider skipLimitProvider;
        PlaybackConfig playbackConfig = this.playbackController.getPlayback().getPlaybackConfig();
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || currentMediaItem.getType().isOwned() || (skipLimitProvider = playbackConfig.getSkipLimitProvider()) == null) {
            return true;
        }
        skipLimitProvider.onSkip();
        if (skipLimitProvider.getCount() < 0) {
            return false;
        }
        skipLimitProvider.decrement();
        if (skipLimitProvider.getCount() == 0) {
            PlaybackStateCompat playbackState = this.mediaSession.getController().getPlaybackState();
            PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder(playbackState);
            builder.setActions(playbackState.getActions() & (-33));
            this.mediaSession.setPlaybackState(builder.build());
        }
        return true;
    }
}
