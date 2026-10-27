package com.amazon.mp3.playback.casting;

import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.nowplaying.LibraryMediaItem;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.voice.MusicPlayQueueFactory;
import com.amazon.music.casting.session.things.MetadataThingShadow;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.data.entity.EntityItem;

/* JADX INFO: loaded from: classes3.dex */
public class CastingMediaItem extends LibraryMediaItem {
    private static final String TAG = "CastingMediaItem";
    private ContentType mContentType;
    private int mRating;
    private MetadataThingShadow.Track mTrack;

    public enum CastingPlaybackFormat {
        ATMOS,
        RA360
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getRating(final int rating) {
        return rating != 1 ? rating != 2 ? "NEUTRAL" : "THUMBS_DOWN" : "THUMBS_UP";
    }

    private static int getRating(final String rating) {
        if (rating == null) {
            return -1;
        }
        switch (rating) {
        }
        return -1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastingMediaItem(final MetadataThingShadow.Track thingTrack, final MusicTrack track) {
        super(track, new PlaybackInitiationInfo(DirectedPlayStatus.UNKNOWN, false, PlaybackPageType.UNKNOWN), null);
        MediaCollectionId mediaCollectionId = null;
        this.mTrack = thingTrack;
        addMediaItemId(new MediaItemId(MediaItemId.Type.MPQS, thingTrack.trackId));
        if (isInterlude()) {
            addMediaItemId(new MediaItemId(MediaItemId.Type.URL, track.getAlbumArtUrl()));
        }
        MetadataThingShadow.Track.QueueMetadata queueMetadata = this.mTrack.queueMetadata;
        if (queueMetadata != null && !StringUtil.isNullOrWhiteSpaces(queueMetadata.queueId)) {
            mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.MPQS, this.mTrack.queueMetadata.queueId);
        }
        setSourceMediaCollectionInfo(new MediaCollectionInfo(Playback.getInstance().getPlaybackConfig().getContext().getResources().getString(R.string.alexa_now_playing_collection_name), null, mediaCollectionId, null, getMediaAccessInfo(), new MediaCollectionId[0]));
        update(this.mTrack);
    }

    public boolean update(final MetadataThingShadow.Track track) {
        this.mTrack = track;
        Log.info(TAG, String.format("quality for track %s returned by MPQS is %s", track.name, track.bestAvailableQuality));
        ContentType contentTypeFromEntryValue = ContentType.fromEntryValue(track.bestAvailableQuality);
        int rating = getRating(track.rating);
        if (rating != -1 && rating != this.mRating) {
            this.mRating = rating;
            return true;
        }
        if (this.mContentType == contentTypeFromEntryValue) {
            return false;
        }
        this.mContentType = contentTypeFromEntryValue;
        setBestAvailablePlaybackAttributes(contentTypeFromEntryValue.mBestAvailableAttributes);
        return true;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public int getRating() {
        return this.mRating;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public void setRating(final int rating, final long position) {
        if (this.mRating != rating) {
            this.mRating = rating;
            updateLikeStateFromRating(rating);
            rateTrack(MediaItem.getMediaItemIdString(this, MediaItemId.Type.MPQS, ""), rating, position);
        }
    }

    private static void rateTrack(final String mpqsTrackId, final int rating, final long positionMillis) {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.mp3.playback.casting.CastingMediaItem.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(final Void... params) {
                long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                String rating2 = CastingMediaItem.getRating(rating);
                Log.debug(CastingMediaItem.TAG, "Submitting rateEntity: " + rating2 + ", " + mpqsTrackId + ", " + positionMillis + ", " + jCurrentTimeMillis);
                MusicPlayQueueFactory.rateEntity(Playback.getInstance().getPlaybackConfig().getContext(), rating2, mpqsTrackId, (int) positionMillis, jCurrentTimeMillis);
                return null;
            }
        }.execute(new Void[0]);
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean isInterlude() {
        String str = this.mTrack.identifierType;
        return str != null && str.startsWith("INTERLUDE");
    }

    @Override // com.amazon.music.media.playback.MediaItem
    protected Uri getAlbumImageUri(int width, int height) {
        if (isInterlude()) {
            return Uri.parse(getMediaItemId(MediaItemId.Type.URL).getId());
        }
        return super.getAlbumImageUri(width, height);
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canRate() {
        Boolean bool;
        CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.getCloudQueueSequencer();
        if (cloudQueueSequencer != null) {
            EntityItem currentMediaItem = cloudQueueSequencer.getCurrentMediaItem();
            if (currentMediaItem instanceof QueueEntityCapabilityQueryable) {
                QueueEntityCapabilityQueryable queueEntityCapabilityQueryable = (QueueEntityCapabilityQueryable) currentMediaItem;
                QueueEntityCapabilityTranslator queueEntityCapabilityTranslator = QueueEntityCapabilityTranslator.INSTANCE;
                return QueueEntityCapabilityTranslator.getAvailabilityFromCapability(queueEntityCapabilityQueryable, QueueEntityCapabilityTranslator.QueueEntityCapability.LOOP_ONE) == ActionAvailability.UNAVAILABLE && QueueEntityCapabilityTranslator.getAvailabilityFromCapability(queueEntityCapabilityQueryable, QueueEntityCapabilityTranslator.QueueEntityCapability.RATE) == ActionAvailability.AVAILABLE;
            }
        }
        return (isInterlude() || (bool = this.mTrack.canRateTrack) == null || !bool.booleanValue()) ? false : true;
    }

    public void onRatingSet(final int rating) {
        this.mRating = rating;
    }

    @Override // com.amazon.music.media.playback.MediaItem
    public boolean canSeek() {
        MetadataThingShadow.Track track;
        Boolean bool;
        return (isInterlude() || (bool = (track = this.mTrack).canScrubBackward) == null || track.canScrubForward == null || !bool.booleanValue() || !this.mTrack.canScrubForward.booleanValue()) ? false : true;
    }

    public CastingPlaybackFormat getPlaybackFormat() {
        String str = this.mTrack.playbackFormat;
        if (str == null) {
            return null;
        }
        CastingPlaybackFormat castingPlaybackFormat = CastingPlaybackFormat.ATMOS;
        if (str.equalsIgnoreCase(castingPlaybackFormat.name())) {
            return castingPlaybackFormat;
        }
        String str2 = this.mTrack.playbackFormat;
        CastingPlaybackFormat castingPlaybackFormat2 = CastingPlaybackFormat.RA360;
        if (str2.equalsIgnoreCase(castingPlaybackFormat2.name())) {
            return castingPlaybackFormat2;
        }
        return null;
    }

    private enum ContentType {
        THREE_DIMENSIONAL("THREE_DIMENSIONAL", new PlaybackAttributes(PlaybackAudioQuality.THREE_DIMENSIONAL, -1, -1, -1)),
        HI_RES("HI_RES", new PlaybackAttributes(PlaybackAudioQuality.UHD192, -1, -1, -1)),
        STD_RES("STD_RES", new PlaybackAttributes(PlaybackAudioQuality.HIGH, -1, -1, -1)),
        HI_FI("HI_FI", new PlaybackAttributes(PlaybackAudioQuality.HD44, -1, -1, -1)),
        UNKNOWN("UNKNOWN", PlaybackAttributes.UNKNOWN);

        private final PlaybackAttributes mBestAvailableAttributes;
        private final String mEntryValue;

        ContentType(final String entryValue, final PlaybackAttributes bestAvailableAttributes) {
            this.mEntryValue = entryValue;
            this.mBestAvailableAttributes = bestAvailableAttributes;
        }

        public String getEntryValue() {
            return this.mEntryValue;
        }

        public static ContentType fromEntryValue(@Nullable String entryValue) {
            for (ContentType contentType : values()) {
                if (contentType.getEntryValue().equals(entryValue)) {
                    return contentType;
                }
            }
            return UNKNOWN;
        }
    }
}
