package com.amazon.music.media.playback.player.impl;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.collection.LruCache;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.player.AbstractPlayer;
import com.amazon.music.media.playback.player.PlayRequest;
import com.amazon.music.media.playback.player.PlayRequestStatus;
import com.amazon.music.media.playback.player.impl.MediaItemPlayer;
import com.amazon.music.media.playback.util.Clock;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class MultiplexPlayer extends AbstractPlayer {
    private MediaItemPlayer currentPlayer;
    private MediaItemPlayer.Listener mediaItemPlayerListener;
    private MediaItemPlayer.Factory playerFactory;
    private final LruCache<MediaItem, MediaItemPlayer> prefetchPlayers;

    public MultiplexPlayer(@NonNull Context context, @NonNull MediaItemPlayer.Factory factory) {
        super(context, factory.getName(), null, true, true, true);
        this.mediaItemPlayerListener = new MediaItemPlayer.Listener() { // from class: com.amazon.music.media.playback.player.impl.MultiplexPlayer.1
            @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer.Listener
            public void onPlayRequestStatusChanged(MediaItemPlayer mediaItemPlayer, PlayRequestStatus playRequestStatus, PlayRequestStatus playRequestStatus2, long j, PlaybackException playbackException, ChangeReason changeReason) {
                ((AbstractPlayer) MultiplexPlayer.this).logger.info("onPlayRequestStatusChanged(" + playRequestStatus + "->" + playRequestStatus2 + ", error: " + playbackException + ")" + mediaItemPlayer.getLoggerSuffix());
                long jNow = Clock.now();
                MultiplexPlayer.this.setPlayRequestStatus(mediaItemPlayer.getPlayRequest(), playRequestStatus2, j, jNow, mediaItemPlayer.getAmountCached(), playbackException, changeReason, null);
                if (MultiplexPlayer.this.currentPlayer == mediaItemPlayer) {
                    ((AbstractPlayer) MultiplexPlayer.this).logger.info("onPlayRequestStatusChanged is for currentPlayer.");
                    if (playRequestStatus2 != PlayRequestStatus.TERMINATED) {
                        MultiplexPlayer.this.updatePlayStatus(changeReason, null);
                        return;
                    }
                    PlayRequest currentPlayRequest = MultiplexPlayer.this.getCurrentPlayRequest();
                    if (MultiplexPlayer.this.isLoopMediaItem()) {
                        MultiplexPlayer.this.setPlayRequestStatus(currentPlayRequest, PlayRequestStatus.NOT_STARTED, 0L, jNow, -1.0f, null, changeReason, null);
                        mediaItemPlayer.seek(0L, changeReason);
                        if (MultiplexPlayer.this.getPlayStatus().shouldBePlaying()) {
                            mediaItemPlayer.play(changeReason);
                        }
                        MultiplexPlayer.this.notifyRepeatingMediaItem();
                        return;
                    }
                    MultiplexPlayer multiplexPlayer = MultiplexPlayer.this;
                    multiplexPlayer.setCurrentIndex(multiplexPlayer.getCurrentIndex() + 1, null, ChangeReason.MEDIA_ITEM_END, jNow);
                    MultiplexPlayer.this.updatePlayers(changeReason);
                }
            }

            @Override // com.amazon.music.media.playback.player.impl.MediaItemPlayer.Listener
            public void onMediaLinkSelected(MediaItemPlayer mediaItemPlayer, MediaLink mediaLink) {
                if (mediaItemPlayer.getPlayRequest() != null) {
                    MultiplexPlayer.this.setMediaLink(mediaItemPlayer.getPlayRequest(), mediaLink);
                }
            }
        };
        this.prefetchPlayers = new LruCache<MediaItem, MediaItemPlayer>(1) { // from class: com.amazon.music.media.playback.player.impl.MultiplexPlayer.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.collection.LruCache
            public void entryRemoved(boolean z, MediaItem mediaItem, MediaItemPlayer mediaItemPlayer, MediaItemPlayer mediaItemPlayer2) {
                ((AbstractPlayer) MultiplexPlayer.this).logger.info("prefetchPlayers.entryRemoved(" + mediaItem + ")" + mediaItemPlayer.getLoggerSuffix());
                super.entryRemoved(z, mediaItem, mediaItemPlayer, mediaItemPlayer2);
                mediaItemPlayer.close();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.collection.LruCache
            public MediaItemPlayer create(MediaItem mediaItem) {
                ((AbstractPlayer) MultiplexPlayer.this).logger.info("prefetchPlayers.create(" + mediaItem + ")");
                MediaItemPlayer mediaItemPlayerCreateMediaItemPlayer = MultiplexPlayer.this.playerFactory.createMediaItemPlayer(mediaItem, MultiplexPlayer.this.mediaItemPlayerListener);
                if (mediaItemPlayerCreateMediaItemPlayer != null) {
                    mediaItemPlayerCreateMediaItemPlayer.setPlaybackConfig(MultiplexPlayer.this.getPlaybackConfig());
                    mediaItemPlayerCreateMediaItemPlayer.setBitRateSelection(MultiplexPlayer.this.getBitRateSelection());
                    mediaItemPlayerCreateMediaItemPlayer.setSpatialEnabled(MultiplexPlayer.this.isSpatialEnabled());
                }
                return mediaItemPlayerCreateMediaItemPlayer;
            }
        };
        this.playerFactory = factory;
    }

    public void updatePrefetchSize() {
        if (getPlaybackConfig() == null) {
            return;
        }
        int prefetchCount = getPlaybackConfig().getPrefetchCount() + 1;
        if (this.prefetchPlayers.maxSize() != prefetchCount) {
            this.prefetchPlayers.resize(prefetchCount);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConfigurationChanged() {
        super.onConfigurationChanged();
        updatePrefetchSize();
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onCurrentVolumeChanged(float f) {
        MediaItemPlayer mediaItemPlayer = this.currentPlayer;
        if (mediaItemPlayer != null) {
            mediaItemPlayer.setVolume(f);
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void seek(long j, ChangeReason changeReason, ControlSource controlSource) {
        this.logger.info("seek(" + j + ")");
        MediaItemPlayer mediaItemPlayer = this.currentPlayer;
        if (mediaItemPlayer != null) {
            mediaItemPlayer.seek(j, changeReason);
        }
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onConnect() {
        this.logger.info("onConnect()");
        updatePrefetchSize();
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onDisconnect() {
        this.logger.info("seek()");
        this.prefetchPlayers.evictAll();
        this.currentPlayer = null;
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void onQueueChanged(int i, int i2, int i3, int i4, int i5, ChangeReason changeReason, ControlSource controlSource) {
        this.logger.info("onQueueChanged(" + i + ", " + i2 + ", " + i3 + ", " + i4 + ", " + i5 + ", " + changeReason + ", " + controlSource + ")");
        updatePlayers(changeReason);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPlay(ChangeReason changeReason, ControlSource controlSource, long j) {
        this.logger.info("doPlay()");
        MediaItemPlayer mediaItemPlayer = this.currentPlayer;
        if (mediaItemPlayer != null) {
            mediaItemPlayer.play(changeReason);
        } else {
            setPlayStatus(PlayStatus.LOADING, null, changeReason, controlSource, j);
        }
        updatePlayers(changeReason);
    }

    @Override // com.amazon.music.media.playback.player.AbstractPlayer
    protected void doPause(boolean z, ChangeReason changeReason, ControlSource controlSource) {
        this.logger.info("doPlay()");
        MediaItemPlayer mediaItemPlayer = this.currentPlayer;
        if (mediaItemPlayer != null) {
            mediaItemPlayer.pause();
        }
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canPlay(Uri uri) {
        return this.playerFactory.canPlay(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayers(ChangeReason changeReason) {
        this.logger.info("updatePlayers() changeReason: " + changeReason);
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        MediaItemPlayer mediaItemPlayer = this.currentPlayer;
        if (mediaItemPlayer != null && mediaItemPlayer.getPlayRequest() != currentPlayRequest) {
            this.logger.info("currentPlayer has changed, so pausing the old one.");
            MediaItemPlayer mediaItemPlayer2 = this.currentPlayer;
            this.currentPlayer = null;
            mediaItemPlayer2.pause();
        }
        if (currentPlayRequest != null && this.currentPlayer == null) {
            this.logger.info("We have a new currentRequest, so creating currentPlayer.");
            MediaItemPlayer mediaItemPlayer3 = this.prefetchPlayers.get(currentPlayRequest.getMediaItem());
            this.currentPlayer = mediaItemPlayer3;
            mediaItemPlayer3.clearError();
            this.currentPlayer.setPlayRequest(currentPlayRequest, changeReason);
            this.currentPlayer.setBitRateSelection(getBitRateSelection());
            this.currentPlayer.setSpatialEnabled(isSpatialEnabled());
            setMediaLink(currentPlayRequest, this.currentPlayer.getMediaLink(false));
            this.currentPlayer.setVolume(getCurrentVolume());
            this.currentPlayer.prefetch();
            if (getPlayStatus().shouldBePlaying()) {
                this.logger.info("state is " + getPlayStatus() + ", so calling currentPlayer.play().");
                this.currentPlayer.play(changeReason);
            } else {
                this.logger.info("state is " + getPlayStatus() + ", so NOT calling currentPlayer.play().");
            }
        }
        int iMaxSize = this.prefetchPlayers.maxSize();
        if (getPlayStatus().shouldBePlaying() && iMaxSize > 1) {
            Map<MediaItem, MediaItemPlayer> mapSnapshot = this.prefetchPlayers.snapshot();
            for (int i = 0; i < iMaxSize; i++) {
                PlayRequest playRequest = getPlayRequest(getCurrentIndex() + i);
                if (playRequest == null) {
                    break;
                }
                if (mapSnapshot.containsKey(playRequest.getMediaItem())) {
                    this.prefetchPlayers.get(playRequest.getMediaItem());
                }
            }
            int simultaneousPrefetchCount = getPlaybackConfig().getSimultaneousPrefetchCount();
            int i2 = 0;
            for (int i3 = 0; i3 < iMaxSize && i2 < simultaneousPrefetchCount; i3++) {
                PlayRequest playRequest2 = getPlayRequest(getCurrentIndex() + i3);
                if (playRequest2 == null) {
                    break;
                }
                MediaItemPlayer mediaItemPlayer4 = this.prefetchPlayers.get(playRequest2.getMediaItem());
                if (!mediaItemPlayer4.isFullyFetched() && mediaItemPlayer4.getError() == null) {
                    mediaItemPlayer4.prefetch();
                    i2++;
                }
            }
        } else if (!getPlayStatus().shouldBePlaying() && this.currentPlayer != null) {
            this.logger.info("would have paused currentPlayer because state is " + getPlayStatus());
        }
        updatePlayStatus(changeReason, null);
    }
}
