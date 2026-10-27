package com.amazon.music.media.playback.metrics;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.google.android.gms.common.internal.ImagesContract;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackEvent extends MetricsEvent {
    public static final MetricsProperty<Boolean> PROPERTY_CUSTOMER_SELECTED_ENTITY;
    public static final MetricsProperty<Boolean> PROPERTY_DIRECTED_PLAY;
    public static final MetricsProperty<Boolean> PROPERTY_LOCAL;
    public static final MetricsProperty<Boolean> PROPERTY_PLAYBACK_EXPECTED;
    private final float amountCached;
    private final ChangeReason changeReason;
    private final ControlSource controlSource;
    private final PlaybackException exception;
    private final MediaItem item;
    private final MediaLink link;
    private final long positionMillis;
    private final long transferSpeedBytesPerSecond;
    public static final MetricsProperty<MediaItem> PROPERTY_MEDIA_ITEM = new MetricsProperty<>("mediaItem", MediaItem.class);
    public static final MetricsProperty<PlaybackPageType> PROPERTY_PAGE_TYPE = new MetricsProperty<>("pageType", PlaybackPageType.class);
    public static final MetricsProperty<MediaLink> PROPERTY_MEDIA_LINK = new MetricsProperty<>("mediaLink", MediaLink.class);

    static {
        Boolean bool = Boolean.FALSE;
        PROPERTY_PLAYBACK_EXPECTED = new MetricsProperty<>("playbackExpected", Boolean.class, bool);
        PROPERTY_DIRECTED_PLAY = new MetricsProperty<>("directPlay", Boolean.class, bool);
        PROPERTY_CUSTOMER_SELECTED_ENTITY = new MetricsProperty<>("isCustomerSelectedEntity", Boolean.class, bool);
        PROPERTY_LOCAL = new MetricsProperty<>(ImagesContract.LOCAL, Boolean.class, bool);
    }

    public static class Builder {
        private ChangeReason changeReason;
        private ControlSource controlSource;
        private final PlaybackEventType eventType;
        private PlaybackException exception;
        private MediaItem item;
        private MediaLink link;
        private long positionMillis = -1;
        private long eventTimeMillis = Long.MIN_VALUE;
        private boolean eventTimeSet = false;
        private float amountCached = -1.0f;
        private long transferSpeedBytesPerSecond = 0;

        public Builder(PlaybackEventType playbackEventType) {
            if (playbackEventType.getMetricsEventClass() != PlaybackEvent.class) {
                throw new IllegalArgumentException("EventType " + playbackEventType + " is not a PlaybackEvent type");
            }
            this.eventType = (PlaybackEventType) Validate.notNull(playbackEventType);
        }

        public Builder mediaItem(MediaItem mediaItem) {
            this.item = mediaItem;
            return this;
        }

        public Builder mediaLink(MediaLink mediaLink) {
            this.link = mediaLink;
            return this;
        }

        public Builder controlSource(ControlSource controlSource) {
            this.controlSource = controlSource;
            return this;
        }

        public Builder changeReason(ChangeReason changeReason) {
            this.changeReason = changeReason;
            return this;
        }

        public Builder positionMillis(long j) {
            this.positionMillis = j;
            return this;
        }

        public Builder playbackException(PlaybackException playbackException) {
            this.exception = playbackException;
            return this;
        }

        public Builder eventTimeMillis(long j) {
            this.eventTimeMillis = j;
            this.eventTimeSet = true;
            return this;
        }

        public Builder amountCached(float f) {
            this.amountCached = f;
            return this;
        }

        @NonNull
        public PlaybackEvent build() {
            return new PlaybackEvent(this);
        }
    }

    protected PlaybackEvent(Builder builder) {
        super(builder.eventType, builder.eventTimeSet ? builder.eventTimeMillis : Clock.now());
        this.item = builder.item;
        this.link = builder.link;
        this.controlSource = builder.controlSource;
        this.changeReason = builder.changeReason;
        this.positionMillis = builder.positionMillis;
        this.amountCached = builder.amountCached;
        this.exception = builder.exception;
        this.transferSpeedBytesPerSecond = builder.transferSpeedBytesPerSecond;
        if (StrictMode.isStrictMode()) {
            builder.eventType.validate(this);
        }
    }

    @Override // com.amazon.music.media.playback.metrics.MetricsEvent
    @NonNull
    public PlaybackEventType getEventType() {
        return (PlaybackEventType) super.getEventType();
    }

    public MediaItem getMediaItem() {
        return this.item;
    }

    public MediaLink getMediaLink() {
        return this.link;
    }

    public ChangeReason getChangeReason() {
        return this.changeReason;
    }

    public long getPositionMillis() {
        return this.positionMillis;
    }

    public float getAmountCached() {
        return this.amountCached;
    }

    public long getTransferSpeedBytesPerSecond() {
        return this.transferSpeedBytesPerSecond;
    }

    public PlaybackException getPlaybackException() {
        return this.exception;
    }

    @Override // com.amazon.music.media.playback.metrics.MetricsEvent
    public String toString() {
        return "PlaybackEvent{eventType=" + getEventType() + ", eventTimeMillis=" + getEventTimeMillis() + ", item=" + this.item + ", link=" + this.link + ", controlSource=" + this.controlSource + ", changeReason=" + this.changeReason + ", positionMillis=" + this.positionMillis + ", exception=" + this.exception + '}';
    }
}
