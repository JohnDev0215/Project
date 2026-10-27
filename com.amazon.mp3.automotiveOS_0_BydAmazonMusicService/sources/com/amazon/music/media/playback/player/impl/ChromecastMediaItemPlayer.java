package com.amazon.music.media.playback.player.impl;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.OAuthProviderMAPR5;
import com.amazon.music.chromecast.ChromecastController;
import com.amazon.music.chromecast.CustomData;
import com.amazon.music.chromecast.PlaybackListener;
import com.amazon.music.chromecast.SimpleListener;
import com.amazon.music.chromecast.StreamType;
import com.amazon.music.chromecast.TrackInformation;
import com.amazon.music.find.api.RemoteSearchApi;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MarketplaceResolver;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.config.ClientIdentification;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.media.playback.player.PlayRequestStatus;
import com.amazon.music.media.playback.player.impl.MediaItemPlayer;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastMediaItemPlayer extends MediaItemPlayer {
    private boolean albumArtLoaded;
    private final int instanceId;
    private final ChromecastController mChromecastController;
    private boolean mediaLinksLoaded;
    private String oAuthToken;
    private long seekRequest;
    private boolean trackLoadedOnChromecast;
    private boolean trackLoadingOnChromecast;
    public static final MediaItemPlayer.Factory FACTORY = new MediaItemPlayer.Factory() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.1
        @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer.Factory
        public boolean canPlay(Uri uri) {
            return true;
        }

        @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer.Factory
        public String getName() {
            return "CHROMECAST_MEDIAPLAYER";
        }

        @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer.Factory
        public MediaItemPlayer createMediaItemPlayer(@NonNull MediaItem mediaItem, @NonNull MediaItemPlayer.Listener listener) {
            return new ChromecastMediaItemPlayer(mediaItem, listener);
        }
    };
    private static int instanceCount = 0;
    private static final List<String> TYPES_SD = ImmutableList.of("SD", "LD");
    private static final List<String> TYPES_SD_PLUS_3D = ImmutableList.of("SD", "LD", "3D");
    private static final List<String> TYPES_HD = ImmutableList.of(RemoteSearchApi.HD_ASSET_QUALITY, "SD", "LD");
    private static final List<String> TYPES_HD_PLUS_3D = ImmutableList.of(RemoteSearchApi.HD_ASSET_QUALITY, "SD", "LD", "3D");

    public ChromecastMediaItemPlayer(@NonNull MediaItem mediaItem, @NonNull MediaItemPlayer.Listener listener) {
        super(mediaItem, listener);
        this.mediaLinksLoaded = false;
        this.albumArtLoaded = false;
        this.trackLoadedOnChromecast = false;
        this.trackLoadingOnChromecast = false;
        this.seekRequest = 0L;
        this.mChromecastController = ChromecastController.getInstance();
        int i = instanceCount + 1;
        instanceCount = i;
        this.instanceId = i;
        this.logger.debug("ASDF: ChromecastMediaItemPlayer constructor instanceId:" + i);
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public void setVolume(float f) {
        this.logger.debug("ASDF: ignoring setVolume()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public void prefetch() {
        this.logger.debug("ASDF: prefetch()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        clearError();
        loadMediaLinks();
        loadAlbumArtUri();
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public void flush() {
        this.logger.debug("ASDF: flush()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        this.mediaLinksLoaded = false;
        this.albumArtLoaded = false;
        this.trackLoadingOnChromecast = false;
        this.trackLoadedOnChromecast = false;
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public boolean isFullyFetched() {
        this.logger.debug("ASDF: isFullyFetched()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        return false;
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public float getAmountCached() {
        this.logger.debug("ASDF: getAmountCached()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        return !this.trackLoadedOnChromecast ? 0.0f : -1.0f;
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public void play(ChangeReason changeReason) {
        this.logger.debug("ASDF: play()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        clearError();
        if (this.trackLoadedOnChromecast) {
            this.logger.debug("ASDF: play() & prepared" + getLoggerSuffix() + " instanceId:" + this.instanceId);
            setPlayRequestStatus(PlayRequestStatus.RENDERING);
            startPlaybackOnChromecast();
            return;
        }
        if (!this.mediaLinksLoaded) {
            this.logger.debug("ASDF: play(), no prefetch" + getLoggerSuffix() + " instanceId:" + this.instanceId);
            setPlayRequestStatus(getPlayRequestStatus().hasStarted() ? PlayRequestStatus.BUFFERING : PlayRequestStatus.PREPARING, null, changeReason);
            loadMediaLinks();
            return;
        }
        this.logger.debug("ASDF: play(), not loaded" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        setPlayRequestStatus(getPlayRequestStatus().hasStarted() ? PlayRequestStatus.BUFFERING : PlayRequestStatus.PREPARING, null, changeReason);
        loadTrackOnChromecast();
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public void pause() {
        this.logger.debug("ASDF: pause()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        pausePlaybackOnChromecast();
        setPlayRequestStatus(getPlayRequestStatus().onPause());
        if (getPlayRequestStatus() == PlayRequestStatus.TERMINATED) {
            flush();
        }
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public void seek(long j, ChangeReason changeReason) {
        this.logger.debug("ASDF: seek(" + j + ")" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        if (this.trackLoadedOnChromecast) {
            this.logger.debug("ASDF: sending seek signal to Chromecast" + getLoggerSuffix() + " instanceId:" + this.instanceId);
            boolean zShouldBePlaying = getPlayRequestStatus().shouldBePlaying();
            setPlayRequestStatus(getPlayRequestStatus().onSeek(), null, changeReason);
            seekPlaybackOnChromecast(j, zShouldBePlaying);
            getPositionTracker().setPosition(j);
            return;
        }
        if (getPositionTracker() != null) {
            getPositionTracker().setPosition(j);
        }
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    public long getPositionMillis() {
        this.logger.debug("ASDF: getPositionMillis()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        if (this.trackLoadedOnChromecast && getPlayRequestStatus() != PlayRequestStatus.SEEKING) {
            try {
                long position = this.mChromecastController.getPosition();
                this.logger.debug("ASDF: getPositionMillis()= " + position + " " + getLoggerSuffix() + " instanceId:" + this.instanceId);
                getPositionTracker().setPosition(position);
                return position;
            } catch (Exception unused) {
                this.logger.warn("Error getting position from ChromecastController" + getLoggerSuffix() + " instanceId:" + this.instanceId);
            }
        }
        this.logger.debug("ASDF: getPositionMillis()2= " + getPositionTracker().getCurrentPosition() + " " + getLoggerSuffix() + " instanceId:" + this.instanceId);
        return getPositionTracker().getCurrentPosition();
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    protected void onMediaLinksChanged() {
        this.logger.debug("ASDF: onMediaLinksChanged()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        this.trackLoadedOnChromecast = false;
        this.mediaLinksLoaded = true;
        if (shouldBePlaying()) {
            loadTrackOnChromecast();
        }
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    protected boolean supports(@NonNull MediaLink mediaLink) {
        this.logger.debug("ASDF: supports()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        return getSupportedLinkTypes().contains(mediaLink.getType());
    }

    @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer
    protected void onErrorFetchingMediaLinks(PlaybackException playbackException) {
        this.logger.debug("ASDF: onErrorFetchingMediaLinks()" + getLoggerSuffix() + " instanceId:" + this.instanceId, (Throwable) playbackException);
        if (!shouldBePlaying() || this.trackLoadedOnChromecast) {
            return;
        }
        onError(playbackException);
    }

    private void loadMediaLinks() {
        if (this.mediaLinksLoaded || this.mediaItem.isFetchingMediaLinks()) {
            return;
        }
        if (getMediaLink(true) == null) {
            restoreFailedMediaLinks();
            if (!this.mediaItem.canFetchMediaLinks()) {
                this.logger.debug("ASDF: loadMediaLinks() can't fetch MediaLinks" + getLoggerSuffix() + " instanceId:" + this.instanceId);
                PlaybackException.Config configMetricsName = new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION).metricsName("Chromecast-NoLinks");
                StringBuilder sb = new StringBuilder();
                sb.append("No usable MediaLinks.");
                sb.append(getLoggerSuffix());
                onError(new PlaybackException(configMetricsName.description(sb.toString()).mediaItem(this.mediaItem).autoRetry(true)));
                return;
            }
            this.logger.debug("ASDF: loadMediaLinks() - fetch MediaLinks instanceId:" + this.instanceId);
            this.mediaItem.fetchMediaLinks(BitRateSelection.AUTO, true, getSupportedLinkTypes());
            return;
        }
        this.logger.debug("ASDF: loadMediaLinks() - MediaLink exists." + getLoggerSuffix() + " instanceId:" + this.instanceId);
        onMediaLinksChanged();
    }

    private void loadAlbumArtUri() {
        if (this.albumArtLoaded) {
            return;
        }
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.2
            @Override // java.lang.Runnable
            public void run() {
                ChromecastMediaItemPlayer.this.logger.debug("Loading album art uri. " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                ChromecastMediaItemPlayer.this.mediaItem.createImageUri(1400, 1400, new MediaItem.ImageType[0]);
                ChromecastMediaItemPlayer.this.albumArtLoaded = true;
            }
        });
    }

    private TrackInformation getTrackInformation() {
        MediaLink mediaLink = getMediaLink(true);
        if (mediaLink == null || mediaLink.getUri() == null || mediaLink.getType() == null) {
            this.logger.error("Unable to construct TrackInformation, invalid MediaLink");
            return null;
        }
        long durationMillis = this.mediaItem.getDurationMillis();
        String name = this.mediaItem.getName();
        String artistName = this.mediaItem.getArtistName();
        String albumName = this.mediaItem.getAlbumName();
        Uri imageUri = this.mediaItem.getImageUri(1400, 1400, new MediaItem.ImageType[0]);
        String string = mediaLink.getUri().toString();
        StreamType streamTypeValueOf = StreamType.valueOf(mediaLink.getType().getMetricsName());
        String string2 = imageUri != null ? imageUri.toString() : null;
        this.logger.debug("ASDF: trackTitle: " + name + " instanceId:" + this.instanceId);
        this.logger.debug("ASDF: trackStreamingUrl: " + string + " instanceId:" + this.instanceId);
        this.logger.debug("ASDF: trackArtistName: " + artistName + " instanceId:" + this.instanceId);
        this.logger.debug("ASDF: trackAlbumName: " + albumName + " instanceId:" + this.instanceId);
        this.logger.debug("ASDF: trackDuration: " + durationMillis + " instanceId:" + this.instanceId);
        this.logger.debug("ASDF: trackAlbumArtUrl: " + string2 + " instanceId:" + this.instanceId);
        this.logger.debug("ASDF: streamType: " + streamTypeValueOf + " instanceId:" + this.instanceId);
        return new TrackInformation(name, artistName, albumName, durationMillis, string, string2, streamTypeValueOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadTrackOnChromecast() {
        this.logger.debug("ASDF: loadTrackOnChromecast() " + getLoggerSuffix() + " instanceId:" + this.instanceId);
        if (this.trackLoadingOnChromecast) {
            this.logger.debug("Track already loading.");
            return;
        }
        if (this.trackLoadedOnChromecast) {
            this.logger.debug("Track already loaded.");
            return;
        }
        if (!this.mediaLinksLoaded) {
            this.logger.warn("ASDF: mediaLink is not loaded. Aborting... " + getLoggerSuffix() + " instanceId:" + this.instanceId);
            return;
        }
        this.trackLoadingOnChromecast = true;
        if (this.oAuthToken == null) {
            fetchOAuthToken();
            return;
        }
        CustomData customDataBuildCustomData = buildCustomData();
        TrackInformation trackInformation = getTrackInformation();
        if (trackInformation == null) {
            setPlayRequestStatus(PlayRequestStatus.TERMINATED, new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION).metricsName("Chromecast-NoLinks").description("MediaItem has no valid MediaLinks").mediaItem(this.mediaItem).autoRetry(false)));
            return;
        }
        long currentPosition = getPositionTracker() == null ? 0L : getPositionTracker().getCurrentPosition();
        this.oAuthToken = null;
        this.logger.warn("ASDF: Loading track  " + getLoggerSuffix() + " position: " + currentPosition + " instanceId:" + this.instanceId);
        this.mChromecastController.loadTrack(trackInformation, customDataBuildCustomData, false, currentPosition, new PlaybackListener() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.3
            @Override // com.amazon.music.chromecast.PlaybackListener
            public void onLoadSuccess() {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: onLoadSuccess() " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                if (ChromecastMediaItemPlayer.this.mediaItem.getDurationMillis() <= 0) {
                    ChromecastMediaItemPlayer chromecastMediaItemPlayer = ChromecastMediaItemPlayer.this;
                    chromecastMediaItemPlayer.mediaItem.setDurationMillis(chromecastMediaItemPlayer.mChromecastController.getDuration());
                }
                ChromecastMediaItemPlayer.this.trackLoadedOnChromecast = true;
                ChromecastMediaItemPlayer.this.trackLoadingOnChromecast = false;
                if (ChromecastMediaItemPlayer.this.shouldBePlaying()) {
                    ChromecastMediaItemPlayer.this.startPlaybackOnChromecast();
                } else {
                    ChromecastMediaItemPlayer.this.pausePlaybackOnChromecast();
                }
                ChromecastMediaItemPlayer chromecastMediaItemPlayer2 = ChromecastMediaItemPlayer.this;
                chromecastMediaItemPlayer2.setPlayRequestStatus(chromecastMediaItemPlayer2.getPlayRequestStatus().onBufferingComplete());
                if (ChromecastMediaItemPlayer.this.mediaItem.getMediaItemId() != null) {
                    ChromecastMediaItemPlayer.this.logger.debug("ASDF: sending playback started flex event " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                    ChromecastMediaItemPlayer.this.sendFlexEvent(FlexEvent.builder("chromecastTrackPlaybackStarted").withFlexStr1(ChromecastMediaItemPlayer.this.mediaItem.getMediaItemId().getId()).withFlexStr2(ChromecastMediaItemPlayer.this.mediaItem.getName()).build());
                }
            }

            @Override // com.amazon.music.chromecast.PlaybackListener
            public void onLoadError(int i) {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: onLoadError() " + i + " " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                ChromecastMediaItemPlayer.this.trackLoadedOnChromecast = false;
                ChromecastMediaItemPlayer.this.trackLoadingOnChromecast = false;
                if (i == 2100) {
                    PlayRequestStatus playRequestStatus = ChromecastMediaItemPlayer.this.getPlayRequestStatus();
                    PlayRequestStatus playRequestStatus2 = PlayRequestStatus.TERMINATED;
                    if (playRequestStatus != playRequestStatus2) {
                        ChromecastMediaItemPlayer.this.linkFailed();
                        if (ChromecastMediaItemPlayer.this.getMediaLink(false) != null) {
                            ChromecastMediaItemPlayer.this.logger.debug("ASDF: onLoadError()1 " + i + " " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                            ChromecastMediaItemPlayer.this.loadTrackOnChromecast();
                            return;
                        }
                        ChromecastMediaItemPlayer.this.logger.error("ASDF: onLoadError()2 " + i + " " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                        ChromecastMediaItemPlayer chromecastMediaItemPlayer = ChromecastMediaItemPlayer.this;
                        PlaybackException.Config configMetricsName = new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION).metricsName("Chromecast-LoadingLinks");
                        StringBuilder sb = new StringBuilder();
                        sb.append("Exception loading track on Chromecast: ");
                        sb.append(i);
                        chromecastMediaItemPlayer.setPlayRequestStatus(playRequestStatus2, new PlaybackException(configMetricsName.description(sb.toString()).mediaItem(ChromecastMediaItemPlayer.this.mediaItem).autoRetry(true)));
                    }
                }
            }

            @Override // com.amazon.music.chromecast.PlaybackListener
            public void onPlaybackComplete() {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: onPlaybackComplete() " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                ChromecastMediaItemPlayer.this.flush();
                ChromecastMediaItemPlayer.this.getPositionTracker().pause(ChromecastMediaItemPlayer.this.getPositionMillis());
                ChromecastMediaItemPlayer.this.setPlayRequestStatus(PlayRequestStatus.TERMINATED, null, ChangeReason.MEDIA_ITEM_END);
                MediaItem mediaItem = ChromecastMediaItemPlayer.this.mediaItem;
                if (mediaItem == null || mediaItem.getMediaItemId() == null) {
                    return;
                }
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: sending playback completion flex event " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                ChromecastMediaItemPlayer.this.sendFlexEvent(FlexEvent.builder("chromecastTrackPlaybackCompleted").withFlexStr1(ChromecastMediaItemPlayer.this.mediaItem.getMediaItemId().getId()).withFlexStr2(ChromecastMediaItemPlayer.this.mediaItem.getName()).build());
            }

            @Override // com.amazon.music.chromecast.PlaybackListener
            public void onRemotePlay() {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: onRemotePlay() getPlayRequestStatus() " + ChromecastMediaItemPlayer.this.getPlayRequestStatus() + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                if (ChromecastMediaItemPlayer.this.getPlayRequestStatus() == PlayRequestStatus.PAUSED) {
                    ChromecastMediaItemPlayer.this.setPlayRequestStatus(PlayRequestStatus.RENDERING);
                }
                if (ChromecastMediaItemPlayer.this.getPositionTracker() != null) {
                    ChromecastMediaItemPlayer.this.getPositionTracker().start(ChromecastMediaItemPlayer.this.getPositionMillis());
                }
            }

            @Override // com.amazon.music.chromecast.PlaybackListener
            public void onRemotePause() {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: onRemotePause() getPlayRequestStatus(): " + ChromecastMediaItemPlayer.this.getPlayRequestStatus() + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                if (ChromecastMediaItemPlayer.this.getPlayRequestStatus() == PlayRequestStatus.RENDERING) {
                    ChromecastMediaItemPlayer chromecastMediaItemPlayer = ChromecastMediaItemPlayer.this;
                    chromecastMediaItemPlayer.setPlayRequestStatus(chromecastMediaItemPlayer.getPlayRequestStatus().onPause());
                }
                if (ChromecastMediaItemPlayer.this.getPositionTracker() != null) {
                    ChromecastMediaItemPlayer.this.getPositionTracker().pause(ChromecastMediaItemPlayer.this.getPositionMillis());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pausePlaybackOnChromecast() {
        this.logger.debug("ASDF: pausePlaybackOnChromecast()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        if (!this.trackLoadedOnChromecast) {
            this.logger.debug("ASDF: pausePlaybackOnChromecast() - track not loaded. " + getLoggerSuffix() + " instanceId:" + this.instanceId);
            return;
        }
        if (getPositionTracker() != null) {
            getPositionTracker().pause(getPositionMillis());
        }
        if (this.mChromecastController.isConnected()) {
            this.mChromecastController.pause(new SimpleListener() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.4
                @Override // com.amazon.music.chromecast.SimpleListener
                public void onSuccess() {
                    ChromecastMediaItemPlayer.this.logger.debug("ASDF: onSuccess() for pausePlaybackOnChromecast()" + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                }

                @Override // com.amazon.music.chromecast.SimpleListener
                public void onError(int i) {
                    ChromecastMediaItemPlayer.this.logger.debug("ASDF: onError() for pausePlaybackOnChromecast(): " + i + " " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                    ChromecastMediaItemPlayer.this.flush();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlaybackOnChromecast() {
        this.logger.debug("ASDF: startPlaybackOnChromecast()" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        if (!this.trackLoadedOnChromecast) {
            this.logger.warn("ASDF: startPlaybackOnChromecast() - track not loaded. " + getLoggerSuffix() + " instanceId:" + this.instanceId);
            return;
        }
        this.mChromecastController.play(new SimpleListener() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.5
            @Override // com.amazon.music.chromecast.SimpleListener
            public void onSuccess() {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: onSuccess() for startPlaybackOnChromecast() " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                ChromecastMediaItemPlayer.this.getPositionTracker().start();
            }

            @Override // com.amazon.music.chromecast.SimpleListener
            public void onError(int i) {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: onError() for startPlaybackOnChromecast() " + i + " " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                ChromecastMediaItemPlayer.this.getPositionTracker().pause();
                ChromecastMediaItemPlayer chromecastMediaItemPlayer = ChromecastMediaItemPlayer.this;
                chromecastMediaItemPlayer.setPlayRequestStatus(chromecastMediaItemPlayer.getPlayRequestStatus().onError());
            }
        });
    }

    private void seekPlaybackOnChromecast(final long j, final boolean z) {
        this.logger.debug("ASDF: seekPlaybackOnChromecast(" + j + ")" + getLoggerSuffix() + " instanceId:" + this.instanceId);
        if (!this.trackLoadedOnChromecast) {
            this.logger.debug("ASDF: seekPlaybackOnChromecast() - track not loaded. " + getLoggerSuffix() + " instanceId:" + this.instanceId);
            return;
        }
        final long j2 = this.seekRequest + 1;
        this.seekRequest = j2;
        long position = this.mChromecastController.getPosition();
        getPositionTracker().pause();
        if (position != j) {
            this.mChromecastController.seek(j, new SimpleListener() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.6
                @Override // com.amazon.music.chromecast.SimpleListener
                public void onSuccess() {
                    ChromecastMediaItemPlayer.this.logger.debug("ASDF: onSuccess() for seekPlaybackOnChromecast() " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                    if (ChromecastMediaItemPlayer.this.seekRequest == j2) {
                        if (ChromecastMediaItemPlayer.this.getPlayRequestStatus() == PlayRequestStatus.SEEKING) {
                            ChromecastMediaItemPlayer.this.setPlayRequestStatus(z ? PlayRequestStatus.RENDERING : PlayRequestStatus.PAUSED, null);
                        }
                        if (ChromecastMediaItemPlayer.this.getPlayRequestStatus().shouldBePlaying()) {
                            ChromecastMediaItemPlayer.this.getPositionTracker().start(j);
                        }
                    }
                }

                @Override // com.amazon.music.chromecast.SimpleListener
                public void onError(int i) {
                    if (i == 2103) {
                        ChromecastMediaItemPlayer.this.logger.debug("seek already in progress");
                        return;
                    }
                    ChromecastMediaItemPlayer.this.logger.debug("ASDF: onError() for seekPlaybackOnChromecast() " + i + " " + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFlexEvent(FlexEvent flexEvent) {
        this.logger.debug("ASDF: sendFlexEvent() - name: " + flexEvent.getEventName() + ", attributes: " + StringUtils.toString(flexEvent.getEventAttributes()) + " instanceId:" + this.instanceId);
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        if (playbackConfig == null) {
            this.logger.debug("ASDF: no playback config() - name: " + flexEvent.getEventName() + ", attributes: " + StringUtils.toString(flexEvent.getEventAttributes()) + " instanceId:" + this.instanceId);
            return;
        }
        MTSMetricsSender mTSMetricsSender = playbackConfig.getMTSMetricsSender();
        if (mTSMetricsSender != null) {
            this.logger.debug("ASDF: sendFlexEvent()2 - name: " + flexEvent.getEventName() + ", attributes: " + StringUtils.toString(flexEvent.getEventAttributes()));
            mTSMetricsSender.sendMetricsEvent(flexEvent);
        }
    }

    private void fetchOAuthToken() {
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.7
            @Override // java.lang.Runnable
            public void run() {
                ChromecastMediaItemPlayer.this.logger.debug("ASDF: Fetching OAuthToken" + ChromecastMediaItemPlayer.this.getLoggerSuffix() + " instanceId:" + ChromecastMediaItemPlayer.this.instanceId);
                if (ChromecastMediaItemPlayer.this.retrieveOAuthToken()) {
                    ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ChromecastMediaItemPlayer.this.trackLoadingOnChromecast = false;
                            ChromecastMediaItemPlayer.this.loadTrackOnChromecast();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean retrieveOAuthToken() {
        try {
            this.oAuthToken = new OAuthProviderMAPR5(getPlaybackConfig().getContext()).getOAuthInfo().accessToken;
            return true;
        } catch (AuthenticationException unused) {
            this.logger.debug("ASDF: retrieveOAuthToken() failed");
            final PlaybackException playbackException = new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION).description("Problem retrieving OAuthToken").mediaItem(this.mediaItem).autoRetry(true));
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer.8
                @Override // java.lang.Runnable
                public void run() {
                    ChromecastMediaItemPlayer.this.onError(playbackException);
                }
            });
            return false;
        }
    }

    private CustomData buildCustomData() {
        String mediaItemIdString;
        ClientIdentification clientIdentification = getPlaybackConfig().getClientIdentification();
        MediaItem.Type type = this.mediaItem.getType();
        if (MediaItem.Type.AD_CONTENT.equals(type)) {
            mediaItemIdString = MediaItem.getMediaItemIdString(this.mediaItem, MediaItemId.Type.URL, "");
        } else if (MediaItem.Type.OWNED_CONTENT.equals(type)) {
            mediaItemIdString = MediaItem.getMediaItemIdString(this.mediaItem, MediaItemId.Type.OWNED, "");
        } else {
            mediaItemIdString = MediaItem.getMediaItemIdString(this.mediaItem, MediaItemId.Type.ASIN, "");
        }
        Marketplace marketplaceLookupByObfuscatedId = MarketplaceResolver.lookupByObfuscatedId(clientIdentification.getMarketplaceID());
        String chromecastDeviceId = this.mChromecastController.getChromecastDeviceId();
        this.logger.info("chromecast customData: itemId: " + mediaItemIdString);
        return CustomData.builder().withItemId(mediaItemIdString).withToken(this.oAuthToken).withClientId(clientIdentification.getClientID()).withClientAppVersion(clientIdentification.getAppVersion()).withCustomerId(clientIdentification.getCustomerID()).withDeviceId(clientIdentification.getDeviceID()).withDeviceTypeId(clientIdentification.getDeviceTypeID()).withMarketplaceRegion(marketplaceLookupByObfuscatedId.getRegion()).withChromecastDeviceId(chromecastDeviceId).withEnabledTrackTypes(enabledTrackTypes()).build();
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.player.impl.ChromecastMediaItemPlayer$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection;

        static {
            int[] iArr = new int[BitRateSelection.values().length];
            $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection = iArr;
            try {
                iArr[BitRateSelection.HIGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.LOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @NonNull
    public static List<String> enabledTrackTypes(BitRateSelection bitRateSelection, boolean z) {
        int i = AnonymousClass9.$SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[bitRateSelection.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? z ? TYPES_SD_PLUS_3D : TYPES_SD : z ? TYPES_HD_PLUS_3D : TYPES_HD;
    }

    private List<String> enabledTrackTypes() {
        return enabledTrackTypes(getBitRateSelection(), isSpatialEnabled());
    }

    private List<MediaLink.Type> getSupportedLinkTypes() {
        List<StreamType> supportedStreamTypes = ChromecastController.getInstance().getSupportedStreamTypes();
        ArrayList arrayList = new ArrayList();
        Iterator<StreamType> it = supportedStreamTypes.iterator();
        while (it.hasNext()) {
            arrayList.add(MediaLink.Type.getType(it.next().name()));
        }
        return arrayList;
    }
}
