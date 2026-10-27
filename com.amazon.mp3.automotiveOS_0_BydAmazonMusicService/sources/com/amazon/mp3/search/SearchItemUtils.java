package com.amazon.mp3.search;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.Target;
import com.amazon.music.destination.TargetType;
import com.amazon.music.find.model.EntityMetaData;
import com.amazon.music.find.model.search.SearchAlbum;
import com.amazon.music.find.model.search.SearchArtist;
import com.amazon.music.find.model.search.SearchAssetType;
import com.amazon.music.find.model.search.SearchContentCatalogStatus;
import com.amazon.music.find.model.search.SearchContentOwnershipStatus;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.model.search.SearchPlaylist;
import com.amazon.music.find.model.search.SearchSports;
import com.amazon.music.find.model.search.SearchStation;
import com.amazon.music.find.model.search.SearchTrack;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.CommunityPlaylistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.LivestreamMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchItemUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b>\u0010=J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0007J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0007J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0007J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0014H\u0007J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0018H\u0007J\u001a\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0018H\u0007J\b\u0010 \u001a\u00020\u0016H\u0007J\b\u0010!\u001a\u00020\u0016H\u0007J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\"H\u0007J\u0012\u0010(\u001a\u0004\u0018\u00010'2\u0006\u0010&\u001a\u00020%H\u0007J\u0012\u0010+\u001a\u0004\u0018\u00010'2\u0006\u0010*\u001a\u00020)H\u0007J\u0012\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010&\u001a\u00020%H\u0007J\b\u0010.\u001a\u00020\u001aH\u0007J\b\u0010/\u001a\u00020\u001aH\u0007J\b\u00100\u001a\u00020\u001aH\u0007J\b\u00101\u001a\u00020\u001aH\u0007J\u0010\u00102\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020%H\u0007J\u0018\u00102\u001a\u00020\u001a2\u000e\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u000103H\u0007J\u0016\u00107\u001a\b\u0012\u0004\u0012\u000204032\u0006\u00106\u001a\u00020\u0012H\u0007R\"\u0010:\u001a\n 9*\u0004\u0018\u000108088\u0002X\u0083\u0004¢\u0006\f\n\u0004\b:\u0010;\u0012\u0004\b<\u0010=¨\u0006?"}, d2 = {"Lcom/amazon/mp3/search/SearchItemUtils;", "", "Lcom/amazon/music/find/model/search/SearchItem;", "searchItem", "Lcom/amazon/mp3/library/cache/image/loader/ImageLoaderFactory$ItemType;", "getArtworkType", "Lcom/amazon/mp3/util/MetricsUtil$MetricsItemType;", "getMetricsItemType", "Lcom/amazon/music/find/model/search/SearchAssetType;", "assetType", "Lcom/amazon/mp3/prime/PrimePlaylistTracksTable$AssetType;", "convertAssetType", "Lcom/amazon/music/find/model/search/SearchContentOwnershipStatus;", "ownershipStatus", "Lcom/amazon/mp3/prime/ContentOwnershipStatus;", "convertOwnershipStatus", "Lcom/amazon/music/find/model/search/SearchContentCatalogStatus;", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/mp3/prime/ContentCatalogStatus;", "convertContentCatalogStatus", "Lcom/amazon/music/find/model/search/SearchLibrarySource;", PageUriUtils.SOURCE_QUERY_PARAM, "", "convertLibrarySourceToInt", "", "convertLibrarySourceToMediaSource", "", "isSourceLibrary", ContextMappingConstants.CONTENT_ENCODING, "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "convertStringToContentEncoding", "getTrueValue", "getContentCatalogStatusPrevCatalogOrNotBoundary", "Landroid/content/Context;", "context", "isOffline", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Lcom/amazon/music/find/model/EntityMetaData;", "getEntityInfoFromContentMetaData", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "navigationMetadata", "getEntityInfoFromNavigationMetaData", "Lcom/amazon/music/destination/Target;", "getTarget", "userIsNightwingOnly", "userIsPrimeOnly", "userIsHawkfire", "userIsKatana", "isContentHawkfireOnly", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "contentCatalogStatus", "catalogStatusToContentAccessSet", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "getLogger$annotations", "()V", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchItemUtils {
    public static final SearchItemUtils INSTANCE = new SearchItemUtils();
    private static final Logger logger = LoggerFactory.getLogger(SearchItemUtils.class.getName());

    /* JADX INFO: compiled from: SearchItemUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            int[] iArr = new int[SearchAssetType.values().length];
            iArr[SearchAssetType.VIDEO.ordinal()] = 1;
            iArr[SearchAssetType.AUDIO.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SearchContentOwnershipStatus.values().length];
            iArr2[SearchContentOwnershipStatus.OWNED.ordinal()] = 1;
            iArr2[SearchContentOwnershipStatus.ADDED.ordinal()] = 2;
            iArr2[SearchContentOwnershipStatus.NOT_IN_LIBRARY.ordinal()] = 3;
            iArr2[SearchContentOwnershipStatus.UNKNOWN.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[SearchContentCatalogStatus.values().length];
            iArr3[SearchContentCatalogStatus.NON_CATALOG.ordinal()] = 1;
            iArr3[SearchContentCatalogStatus.HAWKFIRE.ordinal()] = 2;
            iArr3[SearchContentCatalogStatus.PRIME.ordinal()] = 3;
            iArr3[SearchContentCatalogStatus.PREVIOUSLY_CATALOG.ordinal()] = 4;
            iArr3[SearchContentCatalogStatus.UNKNOWN.ordinal()] = 5;
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[SearchLibrarySource.values().length];
            iArr4[SearchLibrarySource.CLOUD.ordinal()] = 1;
            iArr4[SearchLibrarySource.DEVICE.ordinal()] = 2;
            $EnumSwitchMapping$3 = iArr4;
            int[] iArr5 = new int[ContentCatalogStatus.values().length];
            iArr5[ContentCatalogStatus.HAWKFIRE.ordinal()] = 1;
            iArr5[ContentCatalogStatus.PRIME.ordinal()] = 2;
            iArr5[ContentCatalogStatus.SONIC_RUSH.ordinal()] = 3;
            $EnumSwitchMapping$4 = iArr5;
        }
    }

    @JvmStatic
    public static final int getContentCatalogStatusPrevCatalogOrNotBoundary() {
        return 400;
    }

    private SearchItemUtils() {
    }

    @JvmStatic
    public static final ImageLoaderFactory.ItemType getArtworkType(SearchItem searchItem) {
        Intrinsics.checkNotNullParameter(searchItem, "searchItem");
        if (searchItem instanceof SearchAlbum) {
            return ImageLoaderFactory.ItemType.ALBUM;
        }
        if (searchItem instanceof SearchArtist) {
            return ImageLoaderFactory.ItemType.ARTIST;
        }
        if (searchItem instanceof SearchPlaylist) {
            return ImageLoaderFactory.ItemType.PLAYLIST_UDO;
        }
        if (searchItem instanceof SearchTrack) {
            return ImageLoaderFactory.ItemType.ALBUM;
        }
        return null;
    }

    @JvmStatic
    public static final MetricsUtil.MetricsItemType getMetricsItemType(SearchItem searchItem) {
        Intrinsics.checkNotNullParameter(searchItem, "searchItem");
        if (searchItem instanceof SearchSports) {
            return MetricsUtil.MetricsItemType.SOCCER;
        }
        if (searchItem instanceof SearchStation) {
            return MetricsUtil.MetricsItemType.STATION;
        }
        if (searchItem instanceof SearchAlbum) {
            return MetricsUtil.MetricsItemType.ALBUM;
        }
        if (searchItem instanceof SearchArtist) {
            return MetricsUtil.MetricsItemType.ARTIST;
        }
        if (searchItem instanceof SearchPlaylist) {
            return MetricsUtil.MetricsItemType.PLAYLIST;
        }
        if (searchItem instanceof SearchTrack) {
            return MetricsUtil.MetricsItemType.TRACK;
        }
        return null;
    }

    @JvmStatic
    public static final PrimePlaylistTracksTable.AssetType convertAssetType(SearchAssetType assetType) {
        Intrinsics.checkNotNullParameter(assetType, "assetType");
        int i = WhenMappings.$EnumSwitchMapping$0[assetType.ordinal()];
        if (i == 1) {
            return PrimePlaylistTracksTable.AssetType.VIDEO;
        }
        if (i == 2) {
            return PrimePlaylistTracksTable.AssetType.AUDIO;
        }
        throw new NoWhenBranchMatchedException();
    }

    @JvmStatic
    public static final ContentOwnershipStatus convertOwnershipStatus(SearchContentOwnershipStatus ownershipStatus) {
        Intrinsics.checkNotNullParameter(ownershipStatus, "ownershipStatus");
        int i = WhenMappings.$EnumSwitchMapping$1[ownershipStatus.ordinal()];
        if (i == 1) {
            return ContentOwnershipStatus.OWNED;
        }
        if (i == 2) {
            return ContentOwnershipStatus.ADDED;
        }
        if (i == 3) {
            return ContentOwnershipStatus.NOT_IN_LIBRARY;
        }
        if (i == 4) {
            return ContentOwnershipStatus.UNKNOWN;
        }
        throw new NoWhenBranchMatchedException();
    }

    @JvmStatic
    public static final ContentCatalogStatus convertContentCatalogStatus(SearchContentCatalogStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        int i = WhenMappings.$EnumSwitchMapping$2[status.ordinal()];
        if (i == 1) {
            return ContentCatalogStatus.NON_CATALOG;
        }
        if (i == 2) {
            return ContentCatalogStatus.HAWKFIRE;
        }
        if (i == 3) {
            return ContentCatalogStatus.PRIME;
        }
        if (i == 4) {
            return ContentCatalogStatus.PREVIOUSLY_CATALOG;
        }
        if (i == 5) {
            return ContentCatalogStatus.UNKNOWN;
        }
        throw new NoWhenBranchMatchedException();
    }

    @JvmStatic
    public static final int convertLibrarySourceToInt(SearchLibrarySource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int i = WhenMappings.$EnumSwitchMapping$3[source.ordinal()];
        if (i == 1) {
            return MediaProvider.CirrusBaseColumns.Source.convertToSource("cirrus");
        }
        if (i == 2) {
            return MediaProvider.CirrusBaseColumns.Source.convertToSource("cirrus-local");
        }
        throw new NoWhenBranchMatchedException();
    }

    @JvmStatic
    public static final String convertLibrarySourceToMediaSource(SearchLibrarySource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int i = WhenMappings.$EnumSwitchMapping$3[source.ordinal()];
        if (i == 1) {
            return "cirrus";
        }
        if (i == 2) {
            return "cirrus-local";
        }
        throw new NoWhenBranchMatchedException();
    }

    @JvmStatic
    public static final boolean isSourceLibrary(String source) {
        if (Intrinsics.areEqual(source, "cirrus")) {
            return true;
        }
        return Intrinsics.areEqual(source, "cirrus-local");
    }

    @JvmStatic
    public static final List<ContentEncoding> convertStringToContentEncoding(String contentEncoding) {
        return ContentEncodingDecoder.stringtoContentEncoding(contentEncoding);
    }

    @JvmStatic
    public static final int getTrueValue() {
        return Integer.parseInt("1");
    }

    @JvmStatic
    public static final boolean isOffline(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !ConnectivityUtil.hasAnyInternetConnection(context);
    }

    @JvmStatic
    public static final EntityMetaData getEntityInfoFromContentMetaData(ContentMetadata contentMetadata) {
        EntityType entityType;
        EntityIdType entityIdType;
        String str;
        EntityIdType entityIdType2;
        String streamId;
        EntityType entityType2;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (contentMetadata instanceof TrackMetadata) {
            entityIdType2 = EntityIdType.ASIN;
            streamId = ((TrackMetadata) contentMetadata).getAsin();
            entityType2 = EntityType.TRACK;
        } else if (contentMetadata instanceof PlaylistMetadata) {
            entityIdType2 = EntityIdType.ASIN;
            streamId = ((PlaylistMetadata) contentMetadata).getAsin();
            entityType2 = EntityType.PRIME_PLAYLIST;
        } else if (contentMetadata instanceof AlbumMetadata) {
            entityIdType2 = EntityIdType.ASIN;
            streamId = ((AlbumMetadata) contentMetadata).getAsin();
            entityType2 = EntityType.ALBUM;
        } else if (contentMetadata instanceof StationMetadata) {
            entityIdType2 = EntityIdType.STATION_KEY;
            streamId = ((StationMetadata) contentMetadata).getKey();
            entityType2 = EntityType.PRIME_STATION;
        } else if (contentMetadata instanceof ArtistMetadata) {
            entityIdType2 = EntityIdType.ASIN;
            streamId = ((ArtistMetadata) contentMetadata).getAsin();
            entityType2 = EntityType.ARTIST;
        } else if (contentMetadata instanceof UserPlaylistMetadata) {
            entityIdType2 = EntityIdType.PLAYLIST_ID;
            streamId = ((UserPlaylistMetadata) contentMetadata).getPlaylistId();
            entityType2 = EntityType.USER_PLAYLIST;
        } else if (contentMetadata instanceof CommunityPlaylistMetadata) {
            entityIdType2 = EntityIdType.CPL_PLAYLIST_ID;
            streamId = ((CommunityPlaylistMetadata) contentMetadata).getPlaylistId();
            entityType2 = EntityType.COMMUNITY_PLAYLIST;
        } else {
            if (!(contentMetadata instanceof LivestreamMetadata)) {
                entityType = null;
                entityIdType = null;
                str = null;
                if (str == null && entityIdType != null) {
                    return new EntityMetaData(entityType, entityIdType, str, null, 8, null);
                }
            }
            entityIdType2 = EntityIdType.LIVE_STREAM_ID;
            streamId = ((LivestreamMetadata) contentMetadata).getStreamId();
            entityType2 = EntityType.LIVE_STREAM;
        }
        str = streamId;
        entityIdType = entityIdType2;
        entityType = entityType2;
        return str == null ? null : null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType] */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazon.music.views.library.metadata.NavigationMetadata, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.amazon.music.views.library.metadata.NavigationMetadata] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.amazon.music.find.model.EntityMetaData getEntityInfoFromNavigationMetaData(com.amazon.music.views.library.metadata.NavigationMetadata r6) {
        /*
            java.lang.String r0 = "navigationMetadata"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = r6.getTarget()
            r1 = 0
            android.content.Context r2 = com.amazon.mp3.AmazonApplication.getContext()     // Catch: java.lang.Exception -> L8a
            com.amazon.music.deeplink.DeeplinksManager r2 = com.amazon.music.deeplink.DeeplinksManager.get(r2)     // Catch: java.lang.Exception -> L8a
            android.net.Uri r3 = android.net.Uri.parse(r0)     // Catch: java.lang.Exception -> L8a
            com.amazon.music.destination.core.Destination r2 = r2.getDestination(r3)     // Catch: java.lang.Exception -> L8a
            boolean r3 = r2 instanceof com.amazon.music.destination.ProgramsDestination     // Catch: java.lang.Exception -> L8a
            if (r3 == 0) goto L2d
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_COMPETITION     // Catch: java.lang.Exception -> L8a
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r3 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.COMPETITION_ID     // Catch: java.lang.Exception -> L2a
            com.amazon.music.destination.ProgramsDestination r2 = (com.amazon.music.destination.ProgramsDestination) r2     // Catch: java.lang.Exception -> L8c
            java.lang.String r6 = r2.getCompetitionId()     // Catch: java.lang.Exception -> L8c
            goto Lb3
        L2a:
            r3 = r1
            goto L8c
        L2d:
            boolean r3 = r2 instanceof com.amazon.music.destination.ProgramDetailsDestination     // Catch: java.lang.Exception -> L8a
            r4 = 2
            r5 = 0
            if (r3 == 0) goto L5f
            java.lang.String r3 = "matches"
            boolean r3 = kotlin.text.StringsKt.contains$default(r0, r3, r5, r4, r1)     // Catch: java.lang.Exception -> L8a
            if (r3 == 0) goto L3e
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_MATCH     // Catch: java.lang.Exception -> L8a
            goto L56
        L3e:
            java.lang.String r3 = "conferences"
            boolean r3 = kotlin.text.StringsKt.contains$default(r0, r3, r5, r4, r1)     // Catch: java.lang.Exception -> L8a
            if (r3 == 0) goto L49
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_CONFERENCE     // Catch: java.lang.Exception -> L8a
            goto L56
        L49:
            java.lang.String r3 = "shows"
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r3, r5, r4, r1)     // Catch: java.lang.Exception -> L8a
            if (r0 == 0) goto L55
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_SHOW     // Catch: java.lang.Exception -> L8a
            goto L56
        L55:
            r0 = r1
        L56:
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r3 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.PROGRAM_ID     // Catch: java.lang.Exception -> L2a
            com.amazon.music.destination.ProgramDetailsDestination r2 = (com.amazon.music.destination.ProgramDetailsDestination) r2     // Catch: java.lang.Exception -> L8c
            java.lang.String r6 = r2.getProgramId()     // Catch: java.lang.Exception -> L8c
            goto Lb3
        L5f:
            boolean r2 = r2 instanceof com.amazon.music.destination.PodcastDestination     // Catch: java.lang.Exception -> L8a
            if (r2 == 0) goto L86
            java.lang.String r2 = "podcasts"
            boolean r2 = kotlin.text.StringsKt.contains$default(r0, r2, r5, r4, r1)     // Catch: java.lang.Exception -> L8a
            if (r2 == 0) goto L86
            java.lang.String r2 = "episodes"
            boolean r0 = kotlin.text.StringsKt.contains$default(r0, r2, r5, r4, r1)     // Catch: java.lang.Exception -> L8a
            if (r0 == 0) goto L7d
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_PODCAST_EPISODE     // Catch: java.lang.Exception -> L8a
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r3 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.PODCAST_EPISODE_ID     // Catch: java.lang.Exception -> L2a
            java.lang.String r6 = r6.getId()     // Catch: java.lang.Exception -> L8c
            goto Lb3
        L7d:
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_PODCAST_SHOW     // Catch: java.lang.Exception -> L8a
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r3 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.PODCAST_SHOW_ID     // Catch: java.lang.Exception -> L2a
            java.lang.String r6 = r6.getId()     // Catch: java.lang.Exception -> L8c
            goto Lb3
        L86:
            r6 = r1
            r0 = r6
            r3 = r0
            goto Lb3
        L8a:
            r0 = r1
            r3 = r0
        L8c:
            org.slf4j.Logger r2 = com.amazon.mp3.search.SearchItemUtils.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unable to retrieve uiClick entity info from navigation metadata. Target="
            r4.append(r5)
            java.lang.String r5 = r6.getTarget()
            r4.append(r5)
            java.lang.String r5 = ", ViewId="
            r4.append(r5)
            java.lang.String r6 = r6.getViewId()
            r4.append(r6)
            java.lang.String r6 = r4.toString()
            r2.error(r6)
            r6 = r1
        Lb3:
            if (r6 == 0) goto Lbd
            if (r3 == 0) goto Lbd
            com.amazon.music.find.model.EntityMetaData r2 = new com.amazon.music.find.model.EntityMetaData
            r2.<init>(r1, r3, r6, r0)
            r1 = r2
        Lbd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.search.SearchItemUtils.getEntityInfoFromNavigationMetaData(com.amazon.music.views.library.metadata.NavigationMetadata):com.amazon.music.find.model.EntityMetaData");
    }

    @JvmStatic
    public static final Target getTarget(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (contentMetadata instanceof AlbumMetadata) {
            return new Target(TargetType.ALBUM, ((AlbumMetadata) contentMetadata).getAsin());
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            return new Target(TargetType.PLAYLIST, ((PlaylistMetadata) contentMetadata).getAsin());
        }
        if (contentMetadata instanceof TrackMetadata) {
            return new Target(TargetType.TRACK, ((TrackMetadata) contentMetadata).getAsin());
        }
        if (contentMetadata instanceof StationMetadata) {
            return new Target(TargetType.STATION, ((StationMetadata) contentMetadata).getKey());
        }
        if (contentMetadata instanceof ArtistMetadata) {
            return new Target(TargetType.ARTIST, ((ArtistMetadata) contentMetadata).getAsin());
        }
        return null;
    }

    @JvmStatic
    public static final boolean userIsNightwingOnly() {
        return UserSubscriptionUtil.getUserSubscription().isNightwingOnly();
    }

    @JvmStatic
    public static final boolean userIsPrimeOnly() {
        return UserSubscriptionUtil.getUserSubscription().isPrimeOnly();
    }

    @JvmStatic
    public static final boolean userIsHawkfire() {
        return UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices();
    }

    @JvmStatic
    public static final boolean userIsKatana() {
        return UserSubscriptionUtil.getUserSubscription().isKatana();
    }

    @JvmStatic
    public static final boolean isContentHawkfireOnly(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (contentMetadata instanceof AlbumMetadata) {
            return isContentHawkfireOnly(((AlbumMetadata) contentMetadata).getSupportedTiers());
        }
        if (contentMetadata instanceof ArtistMetadata) {
            return isContentHawkfireOnly(((ArtistMetadata) contentMetadata).getSupportedTiers());
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            return isContentHawkfireOnly(((PlaylistMetadata) contentMetadata).getSupportedTiers());
        }
        if (contentMetadata instanceof StationMetadata) {
            return isContentHawkfireOnly(((StationMetadata) contentMetadata).getSupportedTiers());
        }
        if (contentMetadata instanceof TrackMetadata) {
            return isContentHawkfireOnly(((TrackMetadata) contentMetadata).getSupportedTiers());
        }
        return false;
    }

    @JvmStatic
    public static final boolean isContentHawkfireOnly(Set<? extends ContentAccessType> supportedTiers) {
        return (supportedTiers == null || !supportedTiers.contains(ContentAccessType.HAWKFIRE) || supportedTiers.contains(ContentAccessType.PRIME) || supportedTiers.contains(ContentAccessType.OWNED)) ? false : true;
    }

    @JvmStatic
    public static final Set<ContentAccessType> catalogStatusToContentAccessSet(ContentCatalogStatus contentCatalogStatus) {
        Intrinsics.checkNotNullParameter(contentCatalogStatus, "contentCatalogStatus");
        int i = WhenMappings.$EnumSwitchMapping$4[contentCatalogStatus.ordinal()];
        if (i == 1) {
            return new CatalogStatusTiers(false, true, false, false, false, false, false, 125, null).toSet();
        }
        if (i == 2) {
            return new CatalogStatusTiers(true, false, false, false, false, false, false, GeneratorAdapter.AND, null).toSet();
        }
        if (i == 3) {
            return new CatalogStatusTiers(false, false, false, false, true, false, false, 111, null).toSet();
        }
        return new CatalogStatusTiers(false, false, false, false, false, false, false, WorkQueueKt.MASK, null).toSet();
    }
}
