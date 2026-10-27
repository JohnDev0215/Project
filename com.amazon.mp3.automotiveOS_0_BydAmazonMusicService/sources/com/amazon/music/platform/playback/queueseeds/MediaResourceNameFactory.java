package com.amazon.music.platform.playback.queueseeds;

import com.amazon.mrn.MediaResourceName;
import com.amazon.mrn.MediaResourceNameProvider;
import com.amazon.mrn.exceptions.UnsupportedMrnVersion;
import com.amazon.mrn.v1.MediaResourceNameV1;
import com.amazon.mrn.v1.MrnIdentifierType;
import com.amazon.mrn.v1.MrnMediaType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityId;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.identifiers.PlaylistId;
import com.amazon.music.platform.data.identifiers.QueueId;
import com.amazon.music.platform.data.identifiers.StationKey;
import com.amazon.music.platform.data.identifiers.StationSeedId;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaResourceNameFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/MediaResourceNameFactory;", "", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MediaResourceNameFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: MediaResourceNameFactory.kt */
    @Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\"\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rJ&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\rJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00132\b\u0010\f\u001a\u0004\u0018\u00010\u0006¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/MediaResourceNameFactory$Companion;", "", "Lcom/amazon/mrn/v1/MrnMediaType;", "mediaType", "Lcom/amazon/music/platform/data/entity/EntityItemType;", "fromMediaType", "", "identifier", "Lcom/amazon/mrn/v1/MrnIdentifierType;", "mediaIdentifierType", "Lcom/amazon/music/platform/data/entity/EntityId;", "fromIdentifiers", "mrnString", "Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", ContextMappingConstants.PLAY_MODE, "", "Lcom/amazon/music/platform/playback/queueseeds/QueueSeed;", "from", "item", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {

        /* JADX INFO: compiled from: MediaResourceNameFactory.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[MrnMediaType.values().length];
                iArr[MrnMediaType.MUSIC_ALBUM.ordinal()] = 1;
                iArr[MrnMediaType.PLAYLIST.ordinal()] = 2;
                iArr[MrnMediaType.STATION.ordinal()] = 3;
                iArr[MrnMediaType.ARTIST.ordinal()] = 4;
                iArr[MrnMediaType.QUEUE_ID.ordinal()] = 5;
                iArr[MrnMediaType.MUSIC_TRACK.ordinal()] = 6;
                iArr[MrnMediaType.EPISODE.ordinal()] = 7;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<QueueSeed> from(String mrnString, PlayMode playMode) {
            Intrinsics.checkNotNullParameter(mrnString, "mrnString");
            Intrinsics.checkNotNullParameter(playMode, "playMode");
            return from(mrnString, null, playMode);
        }

        public final List<QueueSeed> from(String mrnString, String item, PlayMode playMode) throws UnsupportedMrnVersion {
            Intrinsics.checkNotNullParameter(mrnString, "mrnString");
            Intrinsics.checkNotNullParameter(playMode, "playMode");
            EntityItemIdentifier entityItemIdentifierFrom = from(mrnString);
            if (entityItemIdentifierFrom == null) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            QueueSeed queueSeedFrom = EntityItemSeedFactory.INSTANCE.from(entityItemIdentifierFrom, from(item), playMode);
            if (queueSeedFrom != null) {
                return CollectionsKt__CollectionsJVMKt.listOf(queueSeedFrom);
            }
            return CollectionsKt__CollectionsKt.emptyList();
        }

        public final EntityItemIdentifier from(String mrnString) throws UnsupportedMrnVersion {
            if (mrnString == null) {
                return null;
            }
            MediaResourceName mediaResourceNameFromString = MediaResourceNameProvider.INSTANCE.fromString(mrnString);
            Intrinsics.checkNotNull(mediaResourceNameFromString, "null cannot be cast to non-null type com.amazon.mrn.v1.MediaResourceNameV1");
            MediaResourceNameV1 mediaResourceNameV1 = (MediaResourceNameV1) mediaResourceNameFromString;
            EntityItemType entityItemTypeFromMediaType = fromMediaType(mediaResourceNameV1.getMediaType());
            EntityId entityIdFromIdentifiers = fromIdentifiers(mediaResourceNameV1.getIdentifier().toString(), mediaResourceNameV1.getMediaIdentifierType(), mediaResourceNameV1.getMediaType());
            if (entityItemTypeFromMediaType == null || entityIdFromIdentifiers == null) {
                return null;
            }
            return new EntityItemIdentifier(entityIdFromIdentifiers, entityItemTypeFromMediaType);
        }

        private final EntityItemType fromMediaType(MrnMediaType mediaType) {
            switch (WhenMappings.$EnumSwitchMapping$0[mediaType.ordinal()]) {
                case 1:
                    return CollectionType.Album.INSTANCE;
                case 2:
                    return CollectionType.Playlist.INSTANCE;
                case 3:
                    return CollectionType.Station.INSTANCE;
                case 4:
                    return CollectionType.Artist.INSTANCE;
                case 5:
                    return CollectionType.Queue.INSTANCE;
                case 6:
                    return SingleType.Track.INSTANCE;
                case 7:
                    return SingleType.Podcast.INSTANCE;
                default:
                    return null;
            }
        }

        private final EntityId fromIdentifiers(String identifier, MrnIdentifierType mediaIdentifierType, MrnMediaType mediaType) {
            if (mediaIdentifierType == MrnIdentifierType.ASIN) {
                return new Asin(identifier);
            }
            if (mediaIdentifierType == MrnIdentifierType.OBJECT_ID) {
                return new ObjectId(identifier);
            }
            if (mediaIdentifierType == MrnIdentifierType.PLAYLIST_ID && mediaType == MrnMediaType.PLAYLIST) {
                return new PlaylistId(identifier);
            }
            if (mediaIdentifierType == MrnIdentifierType.UUID && (mediaType == MrnMediaType.QUEUE_ID || mediaType == MrnMediaType.EPISODE)) {
                return new QueueId(identifier);
            }
            if (mediaIdentifierType == MrnIdentifierType.STATION_SEED_ID && mediaType == MrnMediaType.STATION) {
                return new StationSeedId(identifier);
            }
            if (mediaIdentifierType == MrnIdentifierType.STATION_KEY && mediaType == MrnMediaType.STATION) {
                return new StationKey(identifier);
            }
            return null;
        }
    }
}
