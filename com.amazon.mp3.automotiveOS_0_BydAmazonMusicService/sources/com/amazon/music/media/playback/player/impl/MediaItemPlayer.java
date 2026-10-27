package com.amazon.music.media.playback.player.impl;

import android.net.Uri;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemListener;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.PlayRequest;
import com.amazon.music.media.playback.player.PlayRequestStatus;
import com.amazon.music.media.playback.player.PositionTracker;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MediaItemPlayer {
    private BitRateSelection bitRateSelection;
    private PlayRequest curPlayRequest;
    private PlaybackException error;
    private final Listener listener;
    protected final MediaItem mediaItem;
    private MediaLink mediaLink;
    private final MediaItemListener mediaLinksListener;
    private PlaybackConfig playbackConfig;
    private boolean spatialEnabled;
    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private final Set<MediaLink> failedLinks = new HashSet();
    private final Comparator<MediaLink> linkComparator = new Comparator<MediaLink>() { // from class: com.amazon.music.media.playback.player.impl.MediaItemPlayer.1
        @Override // java.util.Comparator
        public int compare(MediaLink mediaLink, MediaLink mediaLink2) {
            return MediaItemPlayer.this.compareMediaLinks(mediaLink, mediaLink2);
        }
    };

    public interface Factory {
        boolean canPlay(Uri uri);

        MediaItemPlayer createMediaItemPlayer(@NonNull MediaItem mediaItem, @NonNull Listener listener);

        String getName();
    }

    public interface Listener {
        void onMediaLinkSelected(MediaItemPlayer mediaItemPlayer, MediaLink mediaLink);

        void onPlayRequestStatusChanged(MediaItemPlayer mediaItemPlayer, PlayRequestStatus playRequestStatus, PlayRequestStatus playRequestStatus2, long j, PlaybackException playbackException, ChangeReason changeReason);
    }

    public abstract void flush();

    public abstract float getAmountCached();

    public abstract long getPositionMillis();

    public abstract boolean isFullyFetched();

    protected abstract void onErrorFetchingMediaLinks(PlaybackException playbackException);

    protected abstract void onMediaLinksChanged();

    public abstract void pause();

    public abstract void play(ChangeReason changeReason);

    public abstract void prefetch();

    public abstract void seek(long j, ChangeReason changeReason);

    public abstract void setVolume(float f);

    protected boolean supports(@NonNull MediaLink mediaLink) {
        return true;
    }

    public MediaItemPlayer(@NonNull MediaItem mediaItem, @NonNull Listener listener) {
        MediaItemListener mediaItemListener = new MediaItemListener() { // from class: com.amazon.music.media.playback.player.impl.MediaItemPlayer.2
            @Override // com.amazon.music.media.playback.MediaItemListener
            public void onMediaLinksChanged(MediaItem mediaItem2) {
                if (!MediaItemPlayer.this.hasUsableLink()) {
                    onErrorFetchingMediaLinks(mediaItem2, new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.COLLECTION).metricsName("NoUsableLinks").mediaItem(mediaItem2).description("None of the returned MediaLinks were usable.").autoRetry(true)));
                } else {
                    MediaItemPlayer.this.onMediaLinksChanged();
                }
            }

            @Override // com.amazon.music.media.playback.MediaItemListener
            public void onErrorFetchingMediaLinks(MediaItem mediaItem2, PlaybackException playbackException) {
                MediaItemPlayer.this.onErrorFetchingMediaLinks(playbackException);
            }
        };
        this.mediaLinksListener = mediaItemListener;
        this.mediaItem = mediaItem;
        this.listener = (Listener) Validate.notNull(listener);
        mediaItem.addMediaItemListener(mediaItemListener);
    }

    public BitRateSelection getBitRateSelection() {
        return this.bitRateSelection;
    }

    public void setBitRateSelection(BitRateSelection bitRateSelection) {
        this.bitRateSelection = bitRateSelection;
    }

    public boolean isSpatialEnabled() {
        return this.spatialEnabled;
    }

    public void setSpatialEnabled(boolean z) {
        this.spatialEnabled = z;
    }

    public void setPlayRequest(@NonNull PlayRequest playRequest, ChangeReason changeReason) {
        this.logger.info("setPlayRequest(" + playRequest + ")");
        PlayRequest playRequest2 = this.curPlayRequest;
        if (playRequest2 != playRequest) {
            if (playRequest2 != null) {
                pause();
            }
            this.curPlayRequest = playRequest;
            seek(playRequest.getPositionTracker().getCurrentPosition(), changeReason);
            if (playRequest.getPlayRequestStatus().shouldBePlaying()) {
                play(changeReason);
            }
        }
    }

    public PlayRequest getPlayRequest() {
        return this.curPlayRequest;
    }

    public PositionTracker getPositionTracker() {
        PlayRequest playRequest = this.curPlayRequest;
        if (playRequest == null) {
            return null;
        }
        return playRequest.getPositionTracker();
    }

    public final void clearError() {
        this.error = null;
    }

    public final PlaybackException getError() {
        return this.error;
    }

    public boolean shouldBePlaying() {
        return getPlayRequestStatus().shouldBePlaying();
    }

    @CallSuper
    public void close() {
        this.mediaItem.removeMediaItemListener(this.mediaLinksListener);
        flush();
    }

    protected int compareMediaLinks(@NonNull MediaLink mediaLink, @NonNull MediaLink mediaLink2) {
        BitRateSelection bitRateSelection;
        BitRateSelection bitRateSelection2;
        BitRateSelection bitRateSelection3 = getBitRateSelection();
        BitRateSelection bitRateSelection4 = mediaLink.getBitRateSelection();
        BitRateSelection bitRateSelection5 = mediaLink2.getBitRateSelection();
        if (bitRateSelection4 == bitRateSelection5) {
            return 0;
        }
        if (bitRateSelection3 == bitRateSelection4) {
            return -1;
        }
        if (bitRateSelection3 == bitRateSelection5) {
            return 1;
        }
        BitRateSelection bitRateSelection6 = BitRateSelection.HIGH;
        if (bitRateSelection3 == bitRateSelection6) {
            BitRateSelection bitRateSelection7 = BitRateSelection.AUTO;
            if (bitRateSelection4 == bitRateSelection7 || bitRateSelection5 == (bitRateSelection2 = BitRateSelection.LOW)) {
                return -1;
            }
            if (bitRateSelection5 == bitRateSelection7 || bitRateSelection4 == bitRateSelection2) {
                return 1;
            }
        } else {
            BitRateSelection bitRateSelection8 = BitRateSelection.AUTO;
            if (bitRateSelection3 == bitRateSelection8) {
                BitRateSelection bitRateSelection9 = BitRateSelection.MEDIUM;
                if (bitRateSelection4 == bitRateSelection9 || bitRateSelection5 == (bitRateSelection = BitRateSelection.LOW)) {
                    return -1;
                }
                if (bitRateSelection5 == bitRateSelection9 || bitRateSelection4 == bitRateSelection) {
                    return 1;
                }
            } else {
                if (bitRateSelection4 == bitRateSelection8 || bitRateSelection5 == bitRateSelection6) {
                    return -1;
                }
                if (bitRateSelection5 == bitRateSelection8 || bitRateSelection4 == bitRateSelection6) {
                    return 1;
                }
            }
        }
        return 0;
    }

    protected MediaLink getMediaLink(boolean z) {
        MediaLink nextMediaLink = getNextMediaLink(z);
        this.mediaLink = nextMediaLink;
        this.listener.onMediaLinkSelected(this, nextMediaLink);
        return this.mediaLink;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasUsableLink() {
        return getNextMediaLink(false) != null;
    }

    private MediaLink getNextMediaLink(boolean z) {
        this.logger.info("getNextMediaLink(" + z + ")");
        MediaLink mediaLink = this.mediaLink;
        if (mediaLink != null) {
            return mediaLink;
        }
        List<MediaLink> mediaLinks = this.mediaItem.getMediaLinks();
        this.logger.info("mediaItem.getMediaLinks() => " + StringUtils.toString(mediaLinks));
        if (mediaLinks.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(mediaLinks);
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MediaLink mediaLink2 = (MediaLink) arrayList.get(size);
            if (mediaLink2 == null || mediaLink2.isExpired() || !supports(mediaLink2)) {
                Logger logger = this.logger;
                StringBuilder sb = new StringBuilder();
                sb.append("removing ");
                sb.append(mediaLink2);
                sb.append(" because ");
                sb.append(mediaLink2 == null ? "null" : mediaLink2.isExpired() ? "expired" : "not supported.");
                logger.info(sb.toString());
                arrayList.remove(size);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.removeAll(this.failedLinks);
        Collections.sort(arrayList, this.linkComparator);
        this.logger.info("remaining after failed links removed: " + StringUtils.toString(arrayList));
        if (!arrayList.isEmpty()) {
            return (MediaLink) arrayList.get(0);
        }
        if (!z || this.failedLinks.isEmpty()) {
            return null;
        }
        restoreFailedMediaLinks();
        return getNextMediaLink(false);
    }

    protected void linkFailed() {
        this.logger.info("linkFailed()");
        this.failedLinks.add(this.mediaLink);
        this.mediaLink = null;
    }

    protected void restoreFailedMediaLinks() {
        this.logger.info("restoreFailedMediaLinks()");
        this.failedLinks.clear();
    }

    public final PlayRequestStatus getPlayRequestStatus() {
        PlayRequest playRequest = this.curPlayRequest;
        return playRequest == null ? PlayRequestStatus.NOT_STARTED : playRequest.getPlayRequestStatus();
    }

    protected final void onError(PlaybackException playbackException) {
        setPlayRequestStatus(getPlayRequestStatus().onError(), playbackException);
    }

    protected final void setPlayRequestStatus(@NonNull PlayRequestStatus playRequestStatus) {
        setPlayRequestStatus(playRequestStatus, null, null);
    }

    protected final void setPlayRequestStatus(@NonNull PlayRequestStatus playRequestStatus, PlaybackException playbackException) {
        setPlayRequestStatus(playRequestStatus, playbackException, null);
    }

    protected final void setPlayRequestStatus(@NonNull PlayRequestStatus playRequestStatus, PlaybackException playbackException, ChangeReason changeReason) {
        this.logger.info("setPlayRequestStatus(" + playRequestStatus + ")", (Throwable) playbackException);
        if (playRequestStatus == null) {
            StrictMode.validationFailed(playRequestStatus, "status is null");
            return;
        }
        if (playbackException != null) {
            this.error = playbackException;
        }
        PlayRequestStatus playRequestStatus2 = getPlayRequestStatus();
        if (playRequestStatus2 == playRequestStatus && playbackException == null) {
            return;
        }
        if (playbackException == null) {
            this.logger.info("setPlayRequestStatus(" + playRequestStatus + ")" + getLoggerSuffix());
        } else {
            this.logger.error("setPlayRequestStatus(" + playRequestStatus + ", " + playbackException + ")" + getLoggerSuffix(), (Throwable) playbackException);
        }
        if (this.curPlayRequest != null) {
            this.listener.onPlayRequestStatusChanged(this, playRequestStatus2, playRequestStatus, getPositionMillis(), playbackException, changeReason);
            return;
        }
        if (playRequestStatus.shouldBePlaying()) {
            StrictMode.crashIfStrict("Attempting to set PlayRequestStatus to " + playRequestStatus + ", but no PlayRequest has been set.", playbackException);
        }
    }

    public final String getRequestString() {
        PlayRequest playRequest = this.curPlayRequest;
        return playRequest != null ? playRequest.toString() : this.mediaItem.toString();
    }

    public final String getLoggerSuffix() {
        return " - " + getRequestString();
    }

    public PlaybackConfig getPlaybackConfig() {
        return this.playbackConfig;
    }

    public void setPlaybackConfig(PlaybackConfig playbackConfig) {
        this.playbackConfig = playbackConfig;
    }
}
