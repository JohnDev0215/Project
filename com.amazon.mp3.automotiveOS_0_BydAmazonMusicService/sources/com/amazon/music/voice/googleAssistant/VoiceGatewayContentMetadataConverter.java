package com.amazon.music.voice.googleAssistant;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.platform.data.mediagateway.Item;
import com.amazon.music.platform.data.mediagateway.MediaContainer;
import com.amazon.music.platform.data.mediagateway.MediaItem;
import com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaContent;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaIdentifierType;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: VoiceGatewayContentMetadataConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0019\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J \u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u000bH\u0002J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u000fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/voice/googleAssistant/VoiceGatewayContentMetadataConverter;", "", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContainer;", "mediaItem", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "convertPlaylist", "convertAlbum", "", "isSFA", "convertStation", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaItem;", "convertTrack", "convertArtist", "convertTrackList", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "recommendForGoogleAssistant", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "<init>", "(Landroid/content/Context;Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;)V", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final class VoiceGatewayContentMetadataConverter {
    private final ContentPlaybackUtil contentPlaybackUtil;
    private final Context context;
    private static final String TAG = VoiceGatewayContentMetadataConverter.class.getSimpleName();
    private static final Logger LOG = LoggerFactory.getLogger(Reflection.getOrCreateKotlinClass(VoiceGatewayContentMetadataConverter.class).getSimpleName());
    private static final List<MediaType> mediaItemTypes = CollectionsKt__CollectionsKt.listOf((Object[]) new MediaType[]{MediaType.MUSIC_TRACK, MediaType.ARTIST});

    /* JADX INFO: compiled from: VoiceGatewayContentMetadataConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            iArr[MediaType.MUSIC_TRACK.ordinal()] = 1;
            iArr[MediaType.ARTIST.ordinal()] = 2;
            iArr[MediaType.MUSIC_ALBUM.ordinal()] = 3;
            iArr[MediaType.PLAYLIST.ordinal()] = 4;
            iArr[MediaType.STATION.ordinal()] = 5;
            iArr[MediaType.STATION_FOR_ANYTHING.ordinal()] = 6;
            iArr[MediaType.MUSIC_TRACK_LIST.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VoiceGatewayContentMetadataConverter(Context context, ContentPlaybackUtil contentPlaybackUtil) {
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        this.context = context;
        this.contentPlaybackUtil = contentPlaybackUtil;
    }

    public final List<ContentMetadata> convert(RecommendForAssistant$MediaContent recommendForGoogleAssistant) {
        int i;
        Intrinsics.checkNotNullParameter(recommendForGoogleAssistant, "recommendForGoogleAssistant");
        MediaItem mediaItem = recommendForGoogleAssistant instanceof MediaItem ? (MediaItem) recommendForGoogleAssistant : null;
        MediaType mediaType = mediaItem == null ? null : mediaItem.getMediaType();
        if (CollectionsKt___CollectionsKt.contains(mediaItemTypes, mediaType)) {
            i = mediaType != null ? WhenMappings.$EnumSwitchMapping$0[mediaType.ordinal()] : -1;
            if (i == 1) {
                return convertTrack(mediaItem);
            }
            if (i == 2) {
                return convertArtist(mediaItem);
            }
            LOG.debug(TAG, Intrinsics.stringPlus("Unsupported mediaItem type received from gateway: ", mediaItem));
            return null;
        }
        MediaContainer mediaContainer = recommendForGoogleAssistant instanceof MediaContainer ? (MediaContainer) recommendForGoogleAssistant : null;
        MediaType mediaType2 = mediaContainer == null ? null : mediaContainer.getMediaType();
        if (mediaContainer == null) {
            LOG.error(TAG, "Cannot convert null media container");
            return null;
        }
        i = mediaType2 != null ? WhenMappings.$EnumSwitchMapping$0[mediaType2.ordinal()] : -1;
        if (i == 3) {
            return convertAlbum(mediaContainer);
        }
        if (i == 4) {
            return convertPlaylist(mediaContainer);
        }
        if (i == 5) {
            return convertStation(mediaContainer, isSFA(mediaContainer));
        }
        if (i == 6) {
            return convertStation(mediaContainer, isSFA(mediaContainer));
        }
        if (i == 7) {
            return convertTrackList(mediaContainer);
        }
        LOG.error(TAG, "Unsupported entity type received from gateway");
        return null;
    }

    private final List<ContentMetadata> convertPlaylist(MediaContainer mediaItem) {
        if (mediaItem.getMediaIdentifierType() == MediaIdentifierType.ASIN) {
            String identifier = mediaItem.getIdentifier();
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            Set setEmptySet = SetsKt__SetsKt.emptySet();
            Uri playlistUri = this.contentPlaybackUtil.getPlaylistUri(mediaItem.getIdentifier());
            if (playlistUri == null) {
                playlistUri = Uri.EMPTY;
            }
            Uri uri = playlistUri;
            Intrinsics.checkNotNullExpressionValue(uri, "mediaItem.identifier.let…istUri(it) } ?: Uri.EMPTY");
            return CollectionsKt__CollectionsJVMKt.listOf(new PlaylistMetadata(null, uri, null, identifier, null, null, null, null, null, null, null, setEmptySet, listEmptyList, false, null, null, null, null, false, null, false, null, null, false, null, 33548277, null));
        }
        boolean z = mediaItem.getMediaIdentifierType() != MediaIdentifierType.SHARED_PLAYLIST_ID;
        String identifier2 = mediaItem.getIdentifier();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        return CollectionsKt__CollectionsJVMKt.listOf(new UserPlaylistMetadata(null, EMPTY, null, null, null, null, null, null, z, identifier2, null, null, null, false, 15613, null));
    }

    private final List<ContentMetadata> convertAlbum(MediaContainer mediaItem) {
        String identifier = mediaItem.getIdentifier();
        Set setEmptySet = SetsKt__SetsKt.emptySet();
        Uri EMPTY = Uri.EMPTY;
        Boolean isOwned = mediaItem.getIsOwned();
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        return CollectionsKt__CollectionsJVMKt.listOf(new AlbumMetadata(null, EMPTY, null, identifier, null, null, null, null, null, null, isOwned, setEmptySet, false, null, null, null, null, null, null, null, 1045493, null));
    }

    private final List<ContentMetadata> convertStation(MediaContainer mediaItem, boolean isSFA) {
        Uri stationUri;
        String identifier = mediaItem.getIdentifier();
        if (isSFA) {
            LOG.debug(TAG, "Getting station uri with seed id for SFA station");
            stationUri = this.contentPlaybackUtil.getStationUriWithSeed(identifier);
        } else {
            stationUri = this.contentPlaybackUtil.getStationUri(identifier);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(new StationMetadata(null, stationUri, null, identifier, null, null, null, null, null, MapsKt__MapsKt.emptyMap(), SetsKt__SetsKt.emptySet(), null, null, null, 14837, null));
    }

    private final List<ContentMetadata> convertTrack(MediaItem mediaItem) {
        String identifier = mediaItem.getIdentifier();
        String identifier2 = mediaItem.getIdentifier();
        Uri trackUri = identifier2 == null ? null : this.contentPlaybackUtil.getTrackUri(identifier2);
        if (trackUri == null) {
            trackUri = Uri.EMPTY;
        }
        Uri uri = trackUri;
        Intrinsics.checkNotNullExpressionValue(uri, "mediaItem.identifier?.le…ackUri(it) } ?: Uri.EMPTY");
        return CollectionsKt__CollectionsJVMKt.listOf(new TrackMetadata(null, uri, null, identifier, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1048587, 15, null));
    }

    private final List<ContentMetadata> convertArtist(MediaItem mediaItem) {
        String identifier = mediaItem.getIdentifier();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        return CollectionsKt__CollectionsJVMKt.listOf(new ArtistMetadata(null, EMPTY, null, null, identifier, null, null, null, null, null, null, false, null, null, false, null, null, false, false, null, 1048557, null));
    }

    private final List<ContentMetadata> convertTrackList(MediaContainer mediaItem) {
        ArrayList arrayList = new ArrayList();
        List<Item> items = mediaItem.getItems();
        if (items == null) {
            LOG.error(TAG, "Cannot convert null track list");
            return null;
        }
        for (Item item : items) {
            String id = item.getId();
            Uri trackUri = this.contentPlaybackUtil.getTrackUri(item.getId());
            if (trackUri == null) {
                trackUri = Uri.EMPTY;
            }
            Intrinsics.checkNotNullExpressionValue(trackUri, "track.id.let { contentPl…ackUri(it) } ?: Uri.EMPTY");
            arrayList.add(new TrackMetadata(null, trackUri, null, id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1048587, 15, null));
        }
        return arrayList;
    }

    private final boolean isSFA(MediaContainer mediaItem) {
        return mediaItem.getMediaIdentifierType() == MediaIdentifierType.STATION_SEED_ID;
    }
}
