package com.amazon.music.media.playback.config;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.config.ImagePlaceholderProvider;
import com.amazon.music.media.playback.config.MediaItemImageManager;
import com.amazon.music.media.playback.config.MediaNotificationFactory;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.playbacknotification.MediaType;
import com.amazon.music.playbacknotification.PlaybackNotificationBuilder;
import com.amazon.music.playbacknotification.PlaybackNotificationError;
import com.amazon.music.playbacknotification.PlaybackNotificationInterface;
import com.amazon.music.playbacknotification.PlaybackNotificationManager;
import com.amazon.music.playbacknotification.PlaybackNotificationModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultMediaNotificationFactory implements MediaNotificationFactory {
    private final MediaItemImageManager.OnBitmapChangedListener bitmapChangedListener;
    private final PlaybackConfig config;
    private final Context context;
    private final PlaybackController controller;
    private MediaNotificationFactory.ForceUpdateHandler forceUpdateHandler;
    private final MediaItemImageManager imageManager;
    private boolean isChildUser;
    private MediaItem mediaItem;
    private final PlaybackNotificationInterface notificationInterface;
    private final MediaItemImageManager prefetchImageManager;
    private boolean suppressImageUpdates;

    @Override // com.amazon.music.media.playback.config.MediaNotificationFactory
    public int getNotificationId() {
        return 1738;
    }

    @Override // com.amazon.music.media.playback.config.MediaNotificationFactory
    public void setMediaIntentProvider(MediaNotificationFactory.MediaIntentProvider mediaIntentProvider) {
    }

    public DefaultMediaNotificationFactory(Context context, PlaybackController playbackController, PlaybackConfig playbackConfig, boolean z) {
        PlaybackNotificationInterface playbackNotificationInterface = new PlaybackNotificationInterface() { // from class: com.amazon.music.media.playback.config.DefaultMediaNotificationFactory.1
            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public PlaybackNotificationError getPlaybackError() {
                return null;
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean hasPlaybackError() {
                return false;
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean nowPlayingHasSongs() {
                return true;
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void stopForeground() {
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void play() {
                DefaultMediaNotificationFactory.this.controller.togglePlaying();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void pause() {
                DefaultMediaNotificationFactory.this.controller.togglePlaying();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void next() {
                DefaultMediaNotificationFactory.this.controller.skipNext();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void prev() {
                DefaultMediaNotificationFactory.this.controller.previousOrRestart();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void thumbsUp() {
                DefaultMediaNotificationFactory.this.controller.toggleThumbsUp();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void thumbsDown() {
                DefaultMediaNotificationFactory.this.controller.toggleThumbsDown();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public void addTrackToLibrary() {
                LibraryAccessProvider libraryAccessProvider = DefaultMediaNotificationFactory.this.config.getLibraryAccessProvider();
                MediaItem currentMediaItem = DefaultMediaNotificationFactory.this.controller.getCurrentMediaItem();
                if (libraryAccessProvider == null || currentMediaItem == null) {
                    return;
                }
                libraryAccessProvider.addToLibrary(currentMediaItem);
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean isPlaying() {
                return DefaultMediaNotificationFactory.this.controller.getPlayStatus().willPlay();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean isThumbedUp() {
                return DefaultMediaNotificationFactory.this.controller.getCurrentItemRating() == 1;
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean isThumbedDown() {
                return DefaultMediaNotificationFactory.this.controller.getCurrentItemRating() == 2;
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean isMediaInLibrary() {
                LibraryAccessProvider libraryAccessProvider = DefaultMediaNotificationFactory.this.config.getLibraryAccessProvider();
                MediaItem currentMediaItem = DefaultMediaNotificationFactory.this.controller.getCurrentMediaItem();
                return (libraryAccessProvider == null || currentMediaItem == null || (!libraryAccessProvider.isAvailableOffline(currentMediaItem) && !libraryAccessProvider.isInLibrary(currentMediaItem))) ? false : true;
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean hasNext() {
                return DefaultMediaNotificationFactory.this.controller.canSkipNext();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean canRate() {
                return DefaultMediaNotificationFactory.this.controller.canRateCurrentItem();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean canSkipPrevious() {
                return DefaultMediaNotificationFactory.this.controller.canSkipPrevious();
            }

            @Override // com.amazon.music.playbacknotification.PlaybackNotificationInterface
            public boolean canSkipNext() {
                return DefaultMediaNotificationFactory.this.controller.canSkipNext();
            }
        };
        this.notificationInterface = playbackNotificationInterface;
        MediaItemImageManager.OnBitmapChangedListener onBitmapChangedListener = new MediaItemImageManager.OnBitmapChangedListener() { // from class: com.amazon.music.media.playback.config.DefaultMediaNotificationFactory.2
            @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
            public void onBitmapChanged(MediaItemImageManager mediaItemImageManager) {
                if (DefaultMediaNotificationFactory.this.suppressImageUpdates || DefaultMediaNotificationFactory.this.forceUpdateHandler == null) {
                    return;
                }
                DefaultMediaNotificationFactory.this.forceUpdateHandler.forceUpdate();
            }
        };
        this.bitmapChangedListener = onBitmapChangedListener;
        if (playbackController.getControlSource() != ControlSource.NOTIFICATION) {
            StrictMode.crashIfStrict("The PlaybackController for DefaultMediaNotificationFactory should use ControlSource.NOTIFICATION");
        }
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.controller = playbackController;
        this.config = playbackConfig;
        this.isChildUser = z;
        MediaItemImageManager mediaItemImageManager = new MediaItemImageManager(playbackConfig, 300, 300, new MediaItem.ImageType[0]);
        this.imageManager = mediaItemImageManager;
        mediaItemImageManager.addOnBitmapChangedListener(onBitmapChangedListener);
        MediaItemImageManager mediaItemImageManager2 = new MediaItemImageManager(playbackConfig, 300, 300, new MediaItem.ImageType[0]);
        this.prefetchImageManager = mediaItemImageManager2;
        mediaItemImageManager2.addOnBitmapChangedListener(new MediaItemImageManager.OnBitmapChangedListener() { // from class: com.amazon.music.media.playback.config.DefaultMediaNotificationFactory.3
            @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
            public void onBitmapChanged(MediaItemImageManager mediaItemImageManager3) {
            }
        });
        new PlaybackNotificationManager(applicationContext, playbackNotificationInterface);
    }

    @Override // com.amazon.music.media.playback.config.MediaNotificationFactory
    public boolean isAllDataReady(@NonNull PlaybackController playbackController) {
        this.imageManager.setPlaybackConfig(this.controller.getPlayback().getPlaybackConfig());
        return this.imageManager.getMediaItem() == this.controller.getCurrentMediaItem() && this.imageManager.getPlaceholderType() != ImagePlaceholderProvider.PlaceholderType.PLACEHOLDER;
    }

    @Override // com.amazon.music.media.playback.config.MediaNotificationFactory
    public final void setForceUpdateHandler(MediaNotificationFactory.ForceUpdateHandler forceUpdateHandler) {
        this.forceUpdateHandler = forceUpdateHandler;
    }

    @Override // com.amazon.music.media.playback.config.MediaNotificationFactory
    public final MediaNotificationFactory.ForceUpdateHandler getForceUpdateHandler() {
        return this.forceUpdateHandler;
    }

    @Override // com.amazon.music.media.playback.config.MediaNotificationFactory
    @NonNull
    public NotificationCompat.Builder createNotification(@NonNull PlaybackController playbackController) {
        int iIndexOf;
        this.suppressImageUpdates = true;
        this.imageManager.setPlaybackConfig(this.controller.getPlayback().getPlaybackConfig());
        MediaItem currentMediaItem = this.controller.getCurrentMediaItem();
        if (currentMediaItem != null) {
            this.mediaItem = currentMediaItem;
            this.imageManager.setMediaItem(currentMediaItem);
            List<MediaItem> currentQueue = this.controller.getCurrentQueue();
            if (currentQueue != null && (iIndexOf = currentQueue.indexOf(currentMediaItem) + 1) > 0 && iIndexOf < currentQueue.size()) {
                this.prefetchImageManager.setMediaItem(currentQueue.get(iIndexOf));
            }
        }
        PlaybackNotificationBuilder playbackNotificationBuilder = new PlaybackNotificationBuilder(this.context, createPlaybackNotificationModel(), this.notificationInterface);
        this.suppressImageUpdates = false;
        NotificationCompat.Builder builderCreateNotificationBuilder = playbackNotificationBuilder.createNotificationBuilder();
        if (this.isChildUser) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_child", true);
            builderCreateNotificationBuilder.setExtras(bundle);
        }
        return builderCreateNotificationBuilder;
    }

    private PlaybackNotificationModel createPlaybackNotificationModel() {
        String subtitle;
        String name;
        String albumName;
        MediaType mediaType;
        MediaItem mediaItem;
        MediaItem mediaItem2 = this.mediaItem;
        if (mediaItem2 != null) {
            name = mediaItem2.getName();
            subtitle = this.mediaItem.getArtistName();
            albumName = this.mediaItem.getAlbumName();
        } else {
            MediaCollectionInfo mediaCollectionInfo = this.controller.getMediaCollectionInfo();
            String name2 = mediaCollectionInfo == null ? "" : mediaCollectionInfo.getName();
            subtitle = mediaCollectionInfo == null ? "" : mediaCollectionInfo.getSubtitle();
            name = name2;
            albumName = "";
        }
        MediaItem currentMediaItem = this.controller.getCurrentMediaItem();
        MediaItem mediaItem3 = this.mediaItem;
        if (mediaItem3 != null && mediaItem3.getMediaItemId(MediaItemId.Type.PID) != null) {
            mediaType = MediaType.VIDEO;
        } else if (this.controller.canRateMediaItems()) {
            mediaType = MediaType.STATION;
        } else if (this.controller.isLive() || ((mediaItem = this.mediaItem) != null && mediaItem.getType() != null && this.mediaItem.getType().isSports())) {
            mediaType = MediaType.POLDI;
        } else if (this.mediaItem != null && currentMediaItem != null && MediaItem.Type.PODCAST_CONTENT.equals(currentMediaItem.getType())) {
            mediaType = MediaType.PODCAST;
        } else {
            mediaType = MediaType.LIBRARY;
        }
        PlaybackNotificationModel.Builder builder = PlaybackNotificationModel.builder(name, subtitle, this.config.getClickAction(), null, mediaType);
        if (this.isChildUser) {
            builder.withContentHeader(removeExplicitLabelFromAlbumTitle(albumName));
        } else {
            builder.withContentHeader(albumName);
        }
        builder.withToken(this.controller.getPlayback().getMediaSession().getSessionToken());
        builder.withArtworkBitmap(this.imageManager.getBitmap());
        return builder.build();
    }

    public static String removeExplicitLabelFromAlbumTitle(String str) {
        if (str.contains("[Explicit]")) {
            str = str.replace("[Explicit]", "");
        }
        return str.trim();
    }
}
