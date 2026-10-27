package com.amazon.mp3.cloudqueue.sequencer;

import android.net.Uri;
import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.digitalmusicxp.types.PlaybackAssetSource;
import com.amazon.digitalmusicxp.types.TrackAlbum;
import com.amazon.digitalmusicxp.types.TrackArtist;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.library.util.EntityValidatorUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: CloudQueueLibraryTrackMediaItem.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0002J\u001a\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0002J(\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0001H\u0002J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0002J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueLibraryTrackMediaItem;", "Lcom/amazon/music/media/playback/MediaItem;", "queueEntity", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "sourceCollection", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "metricsContext", "Lcom/amazon/mp3/cloudqueue/model/MetricsContext;", "(Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Lcom/amazon/music/media/playback/MediaCollectionInfo;Lcom/amazon/mp3/cloudqueue/model/MetricsContext;)V", "mTrackType", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "addAlbumId", "", "albumId", "", "mediaItem", "addAsin", "asin", "addCollectionId", "name", "collectionType", "Lcom/amazon/music/media/playback/MediaCollectionType;", "addLuid", "luid", "configureOffline", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CloudQueueLibraryTrackMediaItem extends MediaItem {
    private QueueEntityIdTypeEnum mTrackType;

    /* JADX WARN: Illegal instructions before constructor call */
    public CloudQueueLibraryTrackMediaItem(QueueEntityMetadata queueEntity, PlaybackPageType playbackPageType, MediaCollectionInfo mediaCollectionInfo, MetricsContext metricsContext) {
        String asin;
        Intrinsics.checkNotNullParameter(queueEntity, "queueEntity");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        String title = queueEntity.getTitle();
        Float durationInMs = queueEntity.getDurationInMs();
        long jFloatValue = durationInMs == null ? 0L : (long) durationInMs.floatValue();
        MediaItem.Type type = MediaItem.Type.OWNED_CONTENT;
        MediaAccessInfo.Builder builderWithCastable = new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(true).withCastable(true);
        ParentalControls parentalControls = queueEntity.getParentalControls();
        super(null, title, jFloatValue, type, builderWithCastable.withExplicit(parentalControls == null ? false : parentalControls.getHasExplicitLanguage()).withContentAccessType(ContentAccessType.OWNED).build(), null, Playback.getInstance().getPlaybackConfig().getMediaLinkProvider(), Playback.getInstance().getPlaybackConfig().getMediaItemImageUriProvider(), playbackPageType, false, mediaCollectionInfo, new MediaCollectionInfo[0]);
        setMetricsContext(metricsContext.toMap());
        List<TrackArtist> artists = queueEntity.getArtists();
        TrackArtist trackArtist = artists == null ? null : artists.get(0);
        TrackAlbum album = queueEntity.getAlbum();
        String str = "";
        String name = (trackArtist == null || (name = trackArtist.getName()) == null) ? "" : name;
        String asin2 = (trackArtist == null || (asin2 = trackArtist.getAsin()) == null) ? "" : asin2;
        MediaCollectionType ARTIST = MediaCollectionType.ARTIST;
        Intrinsics.checkNotNullExpressionValue(ARTIST, "ARTIST");
        addCollectionId(name, asin2, ARTIST, this);
        String name2 = (album == null || (name2 = album.getName()) == null) ? "" : name2;
        if (album != null && (asin = album.getAsin()) != null) {
            str = asin;
        }
        MediaCollectionType ALBUM = MediaCollectionType.ALBUM;
        Intrinsics.checkNotNullExpressionValue(ALBUM, "ALBUM");
        addCollectionId(name2, str, ALBUM, this);
        addAlbumId("0", this);
        QueueEntityIdTypeEnum identifierType = queueEntity.getIdentifierType();
        this.mTrackType = identifierType;
        if (identifierType == QueueEntityIdTypeEnum.LIBRARY_TRACK_ID) {
            addLuid(queueEntity.getId(), this);
        }
        addAsin(queueEntity.getPlayableAsin(), this);
        configureOffline(this, queueEntity);
    }

    private final void addCollectionId(String name, String asin, MediaCollectionType collectionType, MediaItem mediaItem) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        MediaCollectionId mediaCollectionId = !StringUtils.isEmpty(asin) ? new MediaCollectionId(MediaCollectionId.Type.ASIN, asin) : null;
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        MediaAccessInfo mediaAccessInfoFullAccess = MediaAccessInfo.fullAccess(mediaAccessInfo.isAvailableOffline(), mediaAccessInfo.isAllowedOnline());
        if (this.mTrackType == QueueEntityIdTypeEnum.LIBRARY_TRACK_ID) {
            mediaAccessInfoFullAccess.getContentAccessTypes().add(ContentAccessType.OWNED);
        }
        mediaItem.addMediaCollectionInfo(new MediaCollectionInfo(name, null, mediaCollectionId, collectionType, mediaAccessInfoFullAccess, new MediaCollectionId[0]));
    }

    private final void addAlbumId(String albumId, MediaItem mediaItem) {
        if (StringUtils.isEmpty(albumId)) {
            return;
        }
        MediaCollectionId mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.ALBUM_ART_ID, albumId);
        MediaCollectionInfo mediaCollectionInfo = mediaItem.getMediaCollectionInfo(MediaCollectionType.ALBUM, null);
        if (mediaCollectionInfo == null) {
            return;
        }
        mediaCollectionInfo.addId(mediaCollectionId);
    }

    private final void addAsin(String asin, MediaItem mediaItem) {
        if (EntityValidatorUtil.isStrictAsin(asin)) {
            mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.ASIN, asin));
        }
    }

    private final void addLuid(String luid, MediaItem mediaItem) {
        if (StringUtils.isEmpty(luid)) {
            return;
        }
        mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.OWNED, luid));
    }

    private final void configureOffline(MediaItem mediaItem, QueueEntityMetadata queueEntity) {
        String value;
        PlaybackAssetSource playbackAssetSource = queueEntity.getPlaybackAssetSource();
        String string = "";
        if (playbackAssetSource != null && (value = playbackAssetSource.getValue()) != null) {
            string = value;
        }
        if (StringUtils.isEmpty(string)) {
            return;
        }
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        if (StringsKt__StringsJVMKt.startsWith$default(string, separator, false, 2, null)) {
            string = Uri.fromFile(new File(string)).toString();
            Intrinsics.checkNotNullExpressionValue(string, "fromFile(File(localUriString)).toString()");
        }
        mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.LOCAL_URI, string));
    }
}
