package com.amazon.mp3.cloudqueue;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.digitalmusicxp.enums.ActionStrategyTypeEnum;
import com.amazon.digitalmusicxp.enums.CompositeActionStrategyActionTypeEnum;
import com.amazon.digitalmusicxp.enums.ContentEncodingEnum;
import com.amazon.digitalmusicxp.enums.LoopModeEnum;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.PlayableEntitySeedIdTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.enums.ShuffleModeEnum;
import com.amazon.digitalmusicxp.enums.SingleActionStrategyActionTypeEnum;
import com.amazon.digitalmusicxp.enums.StationSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.TrackReferenceIdTypeEnum;
import com.amazon.digitalmusicxp.inputs.DeviceIdentifierInput;
import com.amazon.digitalmusicxp.inputs.LibraryAlbumSeedContextInput;
import com.amazon.digitalmusicxp.inputs.LibraryArtistSeedContextInput;
import com.amazon.digitalmusicxp.inputs.MetricTupleInput;
import com.amazon.digitalmusicxp.inputs.MetricsContextInput;
import com.amazon.digitalmusicxp.inputs.PlayableEntitySeedContextInput;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.inputs.QueueIdHistoricalPlaybackSeedContextInput;
import com.amazon.digitalmusicxp.inputs.QueueIdTransferSeedContextInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.inputs.StationSeedContextInput;
import com.amazon.digitalmusicxp.inputs.TrackListSeedContextInput;
import com.amazon.digitalmusicxp.inputs.TrackReferenceInput;
import com.amazon.digitalmusicxp.types.ActionStrategy;
import com.amazon.digitalmusicxp.types.CompositeActionStrategy;
import com.amazon.digitalmusicxp.types.DeviceIdentifier;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.digitalmusicxp.types.LibraryAlbumSeed;
import com.amazon.digitalmusicxp.types.LibraryArtistSeed;
import com.amazon.digitalmusicxp.types.PlayableEntitySeed;
import com.amazon.digitalmusicxp.types.QueueIdHistoricalPlaybackSeed;
import com.amazon.digitalmusicxp.types.QueueIdTransferSeed;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.digitalmusicxp.types.SingleActionStrategy;
import com.amazon.digitalmusicxp.types.SkipLimitIOTAction;
import com.amazon.digitalmusicxp.types.StartAt;
import com.amazon.digitalmusicxp.types.StationSeed;
import com.amazon.digitalmusicxp.types.TrackListSeed;
import com.amazon.digitalmusicxp.types.TrackReference;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.cloudqueue.seedcontextinput.ArtistStudioSeedContextInputProvider;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.EntityValidatorUtil;
import com.amazon.mp3.playback.augmentation.CloudQueueAugmentationKt;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.platform.playback.augmentation.SeedContextAugmentation;
import com.amazon.music.station.StationItem;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueDigitalXPUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bY\u0010ZJ\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007J*\u0010\u0012\u001a\u0004\u0018\u00010\u00112\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002J$\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\rH\u0007J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0007J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\u0016H\u0007J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u0011*\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\rH\u0007J\u000e\u0010\u001b\u001a\u0004\u0018\u00010\u0005*\u00020\u0011H\u0002J\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001dH\u0007J\u0018\u0010\"\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001dH\u0007J&\u0010(\u001a\u00020'2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010$\u001a\u00020#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%H\u0007J.\u0010-\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u00022\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\u0002H\u0007J\"\u00100\u001a\u0004\u0018\u00010\u00112\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007J\u0012\u00103\u001a\u0004\u0018\u0001022\u0006\u00101\u001a\u00020\u0003H\u0002J\u0012\u00105\u001a\u0004\u0018\u0001042\u0006\u00101\u001a\u00020\u0003H\u0002J\u0012\u00108\u001a\u00020#2\b\u00107\u001a\u0004\u0018\u000106H\u0007J$\u0010<\u001a\u00020#2\b\u00107\u001a\u0004\u0018\u0001092\u0010\b\u0002\u0010;\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010:H\u0007J\"\u0010@\u001a\u0004\u0018\u00010\u00112\b\u0010=\u001a\u0004\u0018\u00010\u00112\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>0\u0002H\u0007J\u0018\u0010B\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u0003H\u0007J@\u0010C\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u00022\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010F\u001a\u00020%2\u0006\u0010E\u001a\u00020DJ\u000e\u0010G\u001a\u00020D2\u0006\u0010&\u001a\u00020%J\u000e\u0010I\u001a\u00020H2\u0006\u0010$\u001a\u00020#J\u000e\u0010K\u001a\u00020#2\u0006\u0010J\u001a\u00020\u0011J\u0018\u0010M\u001a\u00020#2\b\u0010L\u001a\u0004\u0018\u00010\u00052\u0006\u0010J\u001a\u00020\u0011J\b\u0010O\u001a\u0004\u0018\u00010NJ\u001e\u0010S\u001a\n\u0012\u0004\u0012\u00020R\u0018\u00010\u00022\u000e\u0010Q\u001a\n\u0012\u0004\u0012\u00020P\u0018\u00010\u0002J\u001e\u0010U\u001a\n\u0012\u0004\u0012\u00020P\u0018\u00010\u00022\u000e\u0010T\u001a\n\u0012\u0004\u0012\u00020R\u0018\u00010\u0002R\u001c\u0010W\u001a\n V*\u0004\u0018\u00010\u00030\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010X¨\u0006["}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueueDigitalXPUtil;", "", "", "", "idsList", "Lcom/amazon/digitalmusicxp/types/QueueSeed;", "getQueueSeedByIds", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "uri", "getQueueSeedByContentUri", "asinList", "Lcom/amazon/digitalmusicxp/enums/PlayModeEnum;", ContextMappingConstants.PLAY_MODE, "Lcom/amazon/mp3/library/item/AbstractItem;", "collectionEntity", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "getQueueSeedContextInputsByAsins", "getQueueSeedContextInputByUri", "entity", "getQueueSeedContextInputByAbstractItem", "Lcom/amazon/music/station/StationItem;", "stationItem", "getQueueSeedContextInputByStationItem", "playModeEnum", "toQueueSeedContextInput", "toQueueSeed", "currentTrackAsin", "", "offsetInMs", "Lcom/amazon/digitalmusicxp/types/StartAt;", "getStartAtWithEntityAsin", "currentTrackLuid", "getStartAtWithEntityObjectId", "", "isShuffled", "Lcom/amazon/music/media/playback/RepeatMode;", "repeatMode", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "getPlayBackOptions", "Lcom/amazon/mp3/library/item/MusicTrack;", "trackList", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "primeTrackList", "getQueueSeedByTracks", "Lcom/amazon/digitalmusicxp/types/EntityReference;", "references", "getQueueSeedContextInputByEntityReferences", "identifier", "Lcom/amazon/digitalmusicxp/enums/TrackReferenceIdTypeEnum;", "getTrackReferenceIdTypeEnum", "Lcom/amazon/digitalmusicxp/enums/PlayableEntitySeedIdTypeEnum;", "getTrackPlayableEntitySeedIdTypeEnum", "Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "actionStrategy", "canPerformSingleActionStrategy", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "Lkotlin/Function0;", "hasNoSkipsRemaining", "canPerformActionStrategy", "seed", "Lcom/amazon/music/platform/playback/augmentation/SeedContextAugmentation;", "augmentations", "createOnlineQueueSeedWithSeedContext", "queueId", "getQueueSeedContextInput", "getQueueSeedContextInputsByAsin", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "loopMode", "getRepeatModeFromLoopMode", "getLoopModeFromRepeatMode", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "getShuffleMode", "queueSeedContextInput", "isOnDemandNonStationQueueSeed", "queueSeed", "isSameQueueSeedOnlineInteraction", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "getServiceTier", "Lcom/amazon/digitalmusicxp/enums/ContentEncodingEnum;", "contentEncodingEnumList", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "contentEncodingListConverter", "contentEncodingList", "contentEncodingEnumListConverter", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CloudQueueDigitalXPUtil {
    public static final CloudQueueDigitalXPUtil INSTANCE = new CloudQueueDigitalXPUtil();
    private static final String TAG = CloudQueueDigitalXPUtil.class.getSimpleName();

    /* JADX INFO: compiled from: CloudQueueDigitalXPUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;
        public static final /* synthetic */ int[] $EnumSwitchMapping$9;

        static {
            int[] iArr = new int[LoopModeEnum.values().length];
            iArr[LoopModeEnum.LOOP_ALL.ordinal()] = 1;
            iArr[LoopModeEnum.LOOP_ONE.ordinal()] = 2;
            iArr[LoopModeEnum.LOOP_OFF.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RepeatMode.values().length];
            iArr2[RepeatMode.REPEAT_ALL.ordinal()] = 1;
            iArr2[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            iArr2[RepeatMode.REPEAT_NONE.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[QueueSeedTypeEnum.values().length];
            iArr3[QueueSeedTypeEnum.ACCOUNT.ordinal()] = 1;
            iArr3[QueueSeedTypeEnum.SEARCH.ordinal()] = 2;
            iArr3[QueueSeedTypeEnum.SMART_LIBRARY_PLAYLIST.ordinal()] = 3;
            iArr3[QueueSeedTypeEnum.VIDEO_CHANNEL.ordinal()] = 4;
            iArr3[QueueSeedTypeEnum.SOURCE_DEVICE_TRANSFER.ordinal()] = 5;
            iArr3[QueueSeedTypeEnum.OPAQUE_TRACK_SEARCH.ordinal()] = 6;
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[PlayableEntitySeedIdTypeEnum.values().length];
            iArr4[PlayableEntitySeedIdTypeEnum.ASIN.ordinal()] = 1;
            iArr4[PlayableEntitySeedIdTypeEnum.OBJECT_ID.ordinal()] = 2;
            iArr4[PlayableEntitySeedIdTypeEnum.PLAYLIST_ID.ordinal()] = 3;
            $EnumSwitchMapping$3 = iArr4;
            int[] iArr5 = new int[TrackReferenceIdTypeEnum.values().length];
            iArr5[TrackReferenceIdTypeEnum.ASIN.ordinal()] = 1;
            iArr5[TrackReferenceIdTypeEnum.OBJECT_ID.ordinal()] = 2;
            $EnumSwitchMapping$4 = iArr5;
            int[] iArr6 = new int[ContentEncodingEnum.values().length];
            iArr6[ContentEncodingEnum.HD.ordinal()] = 1;
            iArr6[ContentEncodingEnum.UHD.ordinal()] = 2;
            iArr6[ContentEncodingEnum.IMMERSIVE.ordinal()] = 3;
            iArr6[ContentEncodingEnum.DOLBY_ATMOS.ordinal()] = 4;
            iArr6[ContentEncodingEnum.SONY_360.ordinal()] = 5;
            $EnumSwitchMapping$5 = iArr6;
            int[] iArr7 = new int[ContentEncoding.values().length];
            iArr7[ContentEncoding.HD.ordinal()] = 1;
            iArr7[ContentEncoding.UHD.ordinal()] = 2;
            iArr7[ContentEncoding.IMMERSIVE.ordinal()] = 3;
            iArr7[ContentEncoding.DOLBY_ATMOS.ordinal()] = 4;
            iArr7[ContentEncoding.SONY_360.ordinal()] = 5;
            iArr7[ContentEncoding.UNKNOWN.ordinal()] = 6;
            $EnumSwitchMapping$6 = iArr7;
            int[] iArr8 = new int[SingleActionStrategyActionTypeEnum.values().length];
            iArr8[SingleActionStrategyActionTypeEnum.BOOLEAN.ordinal()] = 1;
            iArr8[SingleActionStrategyActionTypeEnum.UPSELL.ordinal()] = 2;
            $EnumSwitchMapping$7 = iArr8;
            int[] iArr9 = new int[ActionStrategyTypeEnum.values().length];
            iArr9[ActionStrategyTypeEnum.SINGLE.ordinal()] = 1;
            iArr9[ActionStrategyTypeEnum.COMPOSITE.ordinal()] = 2;
            $EnumSwitchMapping$8 = iArr9;
            int[] iArr10 = new int[CompositeActionStrategyActionTypeEnum.values().length];
            iArr10[CompositeActionStrategyActionTypeEnum.SKIP_LIMIT_IOT.ordinal()] = 1;
            $EnumSwitchMapping$9 = iArr10;
        }
    }

    private CloudQueueDigitalXPUtil() {
    }

    @Deprecated(message = "Should get value based on tracks", replaceWith = @ReplaceWith(expression = "getQueueSeedByTracks", imports = {}))
    @JvmStatic
    public static final QueueSeed getQueueSeedByIds(List<String> idsList) {
        Intrinsics.checkNotNullParameter(idsList, "idsList");
        if (idsList.isEmpty()) {
            Log.warning(TAG, "getQueueSeedByIds: empty ids list", new Exception("logging exception to change to other queue seed type"));
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            return new QueueSeed(null, QueueSeedTypeEnum.QUEUE_ID_HISTORICAL_PLAYBACK, null, new QueueIdHistoricalPlaybackSeed(string), null, null, null, null, null, null, null, null, null, 8181, null);
        }
        if (idsList.size() == 1) {
            String str = (String) CollectionsKt___CollectionsKt.first((List) idsList);
            return new QueueSeed(null, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeed(PrimePlaylistUtil.isAsin(str) ? PlayableEntitySeedIdTypeEnum.ASIN : PlayableEntitySeedIdTypeEnum.OBJECT_ID, str), null, null, null, null, null, null, null, null, 8173, null);
        }
        PlayModeEnum playModeEnum = null;
        QueueSeedTypeEnum queueSeedTypeEnum = QueueSeedTypeEnum.TRACK_LIST;
        QueueIdTransferSeed queueIdTransferSeed = null;
        QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed = null;
        PlayableEntitySeed playableEntitySeed = null;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(idsList, 10));
        for (String str2 : idsList) {
            arrayList.add(new TrackReference(str2, PrimePlaylistUtil.isAsin(str2) ? TrackReferenceIdTypeEnum.ASIN : TrackReferenceIdTypeEnum.OBJECT_ID));
        }
        return new QueueSeed(playModeEnum, queueSeedTypeEnum, queueIdTransferSeed, queueIdHistoricalPlaybackSeed, playableEntitySeed, new TrackListSeed(arrayList), null, null, null, null, null, null, null, 8157, null);
    }

    @JvmStatic
    public static final QueueSeed getQueueSeedByContentUri(Context context, Uri uri) {
        PlayableEntitySeedIdTypeEnum playableEntitySeedIdTypeEnum;
        Intrinsics.checkNotNullParameter(context, "context");
        if (uri == null) {
            return null;
        }
        Uri nonTrackCollectionUri = MediaProvider.Tracks.getNonTrackCollectionUri(uri);
        if (nonTrackCollectionUri == null) {
            nonTrackCollectionUri = uri;
        }
        if (MediaProvider.Playlists.isPlaylist(nonTrackCollectionUri)) {
            String catalogPlaylistId = MediaProvider.Playlists.getCatalogPlaylistId(nonTrackCollectionUri);
            if (!Intrinsics.areEqual(catalogPlaylistId, "")) {
                if (EntityValidatorUtil.isStrictAsin(catalogPlaylistId)) {
                    playableEntitySeedIdTypeEnum = PlayableEntitySeedIdTypeEnum.ASIN;
                } else {
                    playableEntitySeedIdTypeEnum = PlayableEntitySeedIdTypeEnum.PLAYLIST_ID;
                }
                Intrinsics.checkNotNullExpressionValue(catalogPlaylistId, "catalogPlaylistId");
                return new QueueSeed(null, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeed(playableEntitySeedIdTypeEnum, catalogPlaylistId), null, null, null, null, null, null, null, null, 8173, null);
            }
            String playlistId = MediaProvider.Playlists.getPlaylistLuid(nonTrackCollectionUri);
            PlayableEntitySeedIdTypeEnum playableEntitySeedIdTypeEnum2 = PlayableEntitySeedIdTypeEnum.PLAYLIST_ID;
            Intrinsics.checkNotNullExpressionValue(playlistId, "playlistId");
            return new QueueSeed(null, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeed(playableEntitySeedIdTypeEnum2, playlistId), null, null, null, null, null, null, null, null, 8173, null);
        }
        if (MediaProvider.Artists.isArtist(nonTrackCollectionUri)) {
            Artist artist = new CirrusSourceLibraryItemFactory(context).getArtist(nonTrackCollectionUri);
            if (artist != null) {
                if (artist.getAsin() != null) {
                    String asin = artist.getAsin();
                    PlayableEntitySeedIdTypeEnum playableEntitySeedIdTypeEnum3 = PlayableEntitySeedIdTypeEnum.ASIN;
                    Intrinsics.checkNotNullExpressionValue(asin, "asin");
                    return new QueueSeed(null, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeed(playableEntitySeedIdTypeEnum3, asin), null, null, null, null, null, null, null, null, 8173, null);
                }
                String name = artist.getName();
                if (name == null) {
                    return null;
                }
                return new QueueSeed(null, QueueSeedTypeEnum.LIBRARY_ARTIST, null, null, null, null, null, new LibraryArtistSeed(name), null, null, null, null, null, 8061, null);
            }
            Log.warning(TAG, "artist is null, unable to generate QueueSeed for artist");
            return null;
        }
        if (MediaProvider.Albums.isAlbum(nonTrackCollectionUri)) {
            Album album = new CirrusSourceLibraryItemFactory(context).getAlbum(nonTrackCollectionUri);
            if (album == null) {
                return null;
            }
            if (album.getAsin() != null) {
                String asin2 = album.getAsin();
                PlayableEntitySeedIdTypeEnum playableEntitySeedIdTypeEnum4 = PlayableEntitySeedIdTypeEnum.ASIN;
                Intrinsics.checkNotNullExpressionValue(asin2, "asin");
                return new QueueSeed(null, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeed(playableEntitySeedIdTypeEnum4, asin2), null, null, null, null, null, null, null, null, 8173, null);
            }
            PlayModeEnum playModeEnum = null;
            QueueSeedTypeEnum queueSeedTypeEnum = QueueSeedTypeEnum.LIBRARY_ALBUM_ID;
            QueueIdTransferSeed queueIdTransferSeed = null;
            QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed = null;
            PlayableEntitySeed playableEntitySeed = null;
            TrackListSeed trackListSeed = null;
            String title = album.getTitle();
            if (title == null) {
                title = "";
            }
            String artistName = album.getArtistName();
            return new QueueSeed(playModeEnum, queueSeedTypeEnum, queueIdTransferSeed, queueIdHistoricalPlaybackSeed, playableEntitySeed, trackListSeed, new LibraryAlbumSeed(title, artistName != null ? artistName : ""), null, null, null, null, null, null, 8125, null);
        }
        Log.warning(TAG, Intrinsics.stringPlus("getQueueSeedByContentUri is not supported for contentUri=", nonTrackCollectionUri));
        return null;
    }

    private final QueueSeedContextInput getQueueSeedContextInputsByAsins(List<String> asinList, PlayModeEnum playMode, AbstractItem collectionEntity) {
        if (asinList.isEmpty()) {
            Log.warning(TAG, "getQueueSeedContextInputsByAsin: empty asins list");
            return null;
        }
        if (asinList.size() == 1) {
            return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput((String) CollectionsKt___CollectionsKt.first((List) asinList), PlayableEntitySeedIdTypeEnum.ASIN, null, 4, null), null, null, null, null, null, null, null, null, null, null, null, 65516, null);
        }
        MetricsContextInput metricsContextInput = new MetricsContextInput(CollectionsKt__CollectionsKt.emptyList());
        if (collectionEntity != null) {
            String metricsValue = SelectionSourceHelper.getSelectionSourceType(collectionEntity.getContentUri(), collectionEntity).getMetricsValue();
            Intrinsics.checkNotNullExpressionValue(metricsValue, "selectionSourceType.metricValue");
            MetricTupleInput metricTupleInput = new MetricTupleInput("selectionSourceType", metricsValue);
            String id = collectionEntity.getId();
            Intrinsics.checkNotNullExpressionValue(id, "it.id");
            metricsContextInput = new MetricsContextInput(CollectionsKt__CollectionsKt.listOf((Object[]) new MetricTupleInput[]{metricTupleInput, new MetricTupleInput("selectionSourceId", id)}));
        }
        QueueSeedTypeEnum queueSeedTypeEnum = QueueSeedTypeEnum.TRACK_LIST;
        QueueIdTransferSeedContextInput queueIdTransferSeedContextInput = null;
        QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput = null;
        PlayableEntitySeedContextInput playableEntitySeedContextInput = null;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(asinList, 10));
        Iterator<T> it = asinList.iterator();
        while (it.hasNext()) {
            arrayList.add(new TrackReferenceInput((String) it.next(), TrackReferenceIdTypeEnum.ASIN));
        }
        return new QueueSeedContextInput(playMode, queueSeedTypeEnum, queueIdTransferSeedContextInput, queueIdHistoricalPlaybackSeedContextInput, playableEntitySeedContextInput, new TrackListSeedContextInput(arrayList, metricsContextInput), null, null, null, null, null, null, null, null, null, null, 65500, null);
    }

    @JvmStatic
    public static final QueueSeedContextInput getQueueSeedContextInputByUri(Context context, Uri uri, PlayModeEnum playMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playMode, "playMode");
        if (uri == null) {
            return null;
        }
        return getQueueSeedContextInputByAbstractItem(new CirrusSourceLibraryItemFactory(context).getItem(uri), playMode);
    }

    @JvmStatic
    public static final QueueSeedContextInput getQueueSeedContextInputByAbstractItem(AbstractItem entity, PlayModeEnum playMode) {
        Intrinsics.checkNotNullParameter(playMode, "playMode");
        if (entity == null) {
            return null;
        }
        if (entity instanceof Album ? true : entity instanceof MusicTrack ? true : entity instanceof Artist) {
            if (!EntityValidatorUtil.isStrictAsin(entity.getAsin())) {
                return null;
            }
            String asin = entity.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "entity.asin");
            return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(asin, PlayableEntitySeedIdTypeEnum.ASIN, null, 4, null), null, null, null, null, 0 == true ? 1 : 0, null, 0 == true ? 1 : 0, null, null, null, null, 65516, null);
        }
        if (entity instanceof CatalogPlaylist) {
            CatalogPlaylist catalogPlaylist = (CatalogPlaylist) entity;
            if (catalogPlaylist.isSharedUserPlaylist() || !EntityValidatorUtil.isStrictAsin(catalogPlaylist.getAsin())) {
                String asin2 = catalogPlaylist.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin2, "entity.asin");
                return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(asin2, PlayableEntitySeedIdTypeEnum.PLAYLIST_ID, null, 4, null), null, null, null, null, 0 == true ? 1 : 0, null, 0 == true ? 1 : 0, null, null, null, null, 65516, null);
            }
            String asin3 = catalogPlaylist.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin3, "entity.asin");
            return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(asin3, PlayableEntitySeedIdTypeEnum.ASIN, null, 4, null), null, null, null, null, 0 == true ? 1 : 0, null, 0 == true ? 1 : 0, null, null, null, null, 65516, null);
        }
        if (entity instanceof Playlist) {
            Playlist playlist = (Playlist) entity;
            String luid = playlist.getLuid();
            if (luid == null) {
                return null;
            }
            if (!EntityValidatorUtil.isUUID(luid) && (playlist.getContentUri() == null || MediaProvider.isSmartPlaylist(playlist.getContentUri()))) {
                return null;
            }
            String luid2 = playlist.getLuid();
            Intrinsics.checkNotNullExpressionValue(luid2, "entity.luid");
            return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(luid2, PlayableEntitySeedIdTypeEnum.PLAYLIST_ID, null, 4, null), null, null, null, 0 == true ? 1 : 0, null, 0 == true ? 1 : 0, null, null, null, null, null, 65516, null);
        }
        Log.error(TAG, "unsupported item " + entity + ", cannot create QueueSeedContextInput");
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JvmStatic
    public static final QueueSeedContextInput getQueueSeedContextInputByStationItem(StationItem stationItem) {
        ArrayList arrayList;
        TrackReferenceInput trackReferenceInput;
        Intrinsics.checkNotNullParameter(stationItem, "stationItem");
        if (!TextUtils.isEmpty(stationItem.getKey())) {
            StationSeedTypeEnum stationSeedTypeEnum = StationSeedTypeEnum.STATION_KEY;
            String key = stationItem.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "stationItem.key");
            return new QueueSeedContextInput(PlayModeEnum.ON_DEMAND, QueueSeedTypeEnum.STATION_SEED, null, null, null, null, null, null, new StationSeedContextInput(stationSeedTypeEnum, key, null, 4, null), null, null, null, null, null, null, null, 65276, null);
        }
        String identifierType = stationItem.getIdentifierType();
        if (identifierType != null) {
            switch (identifierType.hashCode()) {
                case -2045565570:
                    if (identifierType.equals("STATION_SEED_ID")) {
                        StationSeedTypeEnum stationSeedTypeEnum2 = StationSeedTypeEnum.STATION_SEED_ID;
                        String identifier = stationItem.getIdentifier();
                        Intrinsics.checkNotNullExpressionValue(identifier, "stationItem.identifier");
                        return new QueueSeedContextInput(PlayModeEnum.ON_DEMAND, QueueSeedTypeEnum.STATION_SEED, null, null, null, null, null, null, new StationSeedContextInput(stationSeedTypeEnum2, identifier, null, 4, null), null, null, null, null, null, null, null, 65276, null);
                    }
                    break;
                case -898681708:
                    if (identifierType.equals("STATION_KEY")) {
                        StationSeedTypeEnum stationSeedTypeEnum3 = StationSeedTypeEnum.STATION_KEY;
                        String identifier2 = stationItem.getIdentifier();
                        Intrinsics.checkNotNullExpressionValue(identifier2, "stationItem.identifier");
                        return new QueueSeedContextInput(PlayModeEnum.ON_DEMAND, QueueSeedTypeEnum.STATION_SEED, null, null, null, null, null, null, new StationSeedContextInput(stationSeedTypeEnum3, identifier2, null, 4, null), null, null, null, null, null, null, null, 65276, null);
                    }
                    break;
                case -332570434:
                    if (identifierType.equals("PLAYLIST_SEED")) {
                        PlayableEntitySeedIdTypeEnum playableEntitySeedIdTypeEnum = EntityValidatorUtil.isStrictAsin(stationItem.getIdentifier()) ? PlayableEntitySeedIdTypeEnum.ASIN : PlayableEntitySeedIdTypeEnum.PLAYLIST_ID;
                        String identifier3 = stationItem.getIdentifier();
                        Intrinsics.checkNotNullExpressionValue(identifier3, "stationItem.identifier");
                        return new QueueSeedContextInput(PlayModeEnum.SIMILAR, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(identifier3, playableEntitySeedIdTypeEnum, null, 4, null), null, null, null, null, null, null, null, null, null, null, null, 65516, null);
                    }
                    break;
                case 217203550:
                    if (identifierType.equals("TRACK_LIST_SEED")) {
                        List<Track> identifiers = stationItem.getIdentifiers();
                        if (identifiers == null) {
                            arrayList = null;
                        } else {
                            arrayList = new ArrayList();
                            for (Track track : identifiers) {
                                String identifier4 = track.getIdentifier();
                                String identifierType2 = track.getIdentifierType();
                                if (!Intrinsics.areEqual(identifierType2, "ASIN")) {
                                    if (!Intrinsics.areEqual(identifierType2, "OBJECT_ID")) {
                                        Log.warning(TAG, Intrinsics.stringPlus("getQueueSeedContextInputByStationItem: unsupported trackReferenceType ", track));
                                    } else if (EntityValidatorUtil.isUUID(identifier4)) {
                                        Intrinsics.checkNotNullExpressionValue(identifier4, "identifier");
                                        trackReferenceInput = new TrackReferenceInput(identifier4, TrackReferenceIdTypeEnum.OBJECT_ID);
                                    }
                                    trackReferenceInput = null;
                                } else if (EntityValidatorUtil.isStrictAsin(identifier4)) {
                                    Intrinsics.checkNotNullExpressionValue(identifier4, "identifier");
                                    trackReferenceInput = new TrackReferenceInput(identifier4, TrackReferenceIdTypeEnum.ASIN);
                                } else {
                                    trackReferenceInput = null;
                                }
                                if (trackReferenceInput != null) {
                                    arrayList.add(trackReferenceInput);
                                }
                            }
                        }
                        if (arrayList == null || arrayList.isEmpty()) {
                            return null;
                        }
                        return new QueueSeedContextInput(PlayModeEnum.SIMILAR, QueueSeedTypeEnum.TRACK_LIST, null, null, null, new TrackListSeedContextInput(arrayList, new MetricsContextInput(CollectionsKt__CollectionsKt.emptyList())), null, null, null, null, null, null, null, null, null, null, 65500, null);
                    }
                    break;
                case 383164201:
                    if (identifierType.equals("PREFER_KEY_OVER_NEXT_TOKEN")) {
                        throw new RuntimeException("station key is null and token is unusable in cloud queue");
                    }
                    break;
                case 1674545413:
                    if (identifierType.equals("TRACK_SEED")) {
                        String identifier5 = stationItem.getIdentifier();
                        Intrinsics.checkNotNullExpressionValue(identifier5, "stationItem.identifier");
                        return new QueueSeedContextInput(PlayModeEnum.SIMILAR, QueueSeedTypeEnum.TRACK_LIST, null, null, null, new TrackListSeedContextInput(CollectionsKt__CollectionsJVMKt.listOf(new TrackReferenceInput(identifier5, TrackReferenceIdTypeEnum.ASIN)), new MetricsContextInput(CollectionsKt__CollectionsKt.emptyList())), null, null, null, null, null, null, null, null, null, null, 65500, null);
                    }
                    break;
            }
        }
        PlayableEntitySeedIdTypeEnum playableEntitySeedIdTypeEnum2 = EntityValidatorUtil.isStrictAsin(stationItem.getIdentifier()) ? PlayableEntitySeedIdTypeEnum.ASIN : EntityValidatorUtil.isPersonalizedPlaylistId(stationItem.getIdentifier()) ? PlayableEntitySeedIdTypeEnum.PLAYLIST_ID : PlayableEntitySeedIdTypeEnum.OBJECT_ID;
        String identifier6 = stationItem.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier6, "stationItem.identifier");
        return new QueueSeedContextInput(PlayModeEnum.SIMILAR, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(identifier6, playableEntitySeedIdTypeEnum2, null, 4, null), null, null, null, null, null, null, null, null, null, null, null, 65516, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ QueueSeedContextInput getQueueSeedContextInputsByAsin$default(CloudQueueDigitalXPUtil cloudQueueDigitalXPUtil, List list, List list2, PlayModeEnum playModeEnum, AbstractItem abstractItem, int i, Object obj) {
        if ((i & 1) != 0) {
            list = null;
        }
        if ((i & 2) != 0) {
            list2 = null;
        }
        if ((i & 8) != 0) {
            abstractItem = null;
        }
        return cloudQueueDigitalXPUtil.getQueueSeedContextInputsByAsin(list, list2, playModeEnum, abstractItem);
    }

    public final RepeatMode getRepeatModeFromLoopMode(LoopModeEnum loopMode) {
        Intrinsics.checkNotNullParameter(loopMode, "loopMode");
        int i = WhenMappings.$EnumSwitchMapping$0[loopMode.ordinal()];
        if (i == 1) {
            return RepeatMode.REPEAT_ALL;
        }
        if (i == 2) {
            return RepeatMode.REPEAT_ONE;
        }
        if (i == 3) {
            return RepeatMode.REPEAT_NONE;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final LoopModeEnum getLoopModeFromRepeatMode(RepeatMode repeatMode) {
        Intrinsics.checkNotNullParameter(repeatMode, "repeatMode");
        int i = WhenMappings.$EnumSwitchMapping$1[repeatMode.ordinal()];
        if (i == 1) {
            return LoopModeEnum.LOOP_ALL;
        }
        if (i == 2) {
            return LoopModeEnum.LOOP_ONE;
        }
        if (i == 3) {
            return LoopModeEnum.LOOP_OFF;
        }
        throw new NoWhenBranchMatchedException();
    }

    @JvmStatic
    public static final QueueSeedContextInput toQueueSeedContextInput(QueueSeed queueSeed, PlayModeEnum playModeEnum) {
        QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlaybackSeedContextInput;
        boolean zIsStrictAsin;
        PlayableEntitySeedContextInput playableEntitySeedContextInput;
        TrackListSeedContextInput trackListSeedContextInput;
        boolean zIsStrictAsin2;
        LibraryArtistSeedContextInput libraryArtistSeedContextInput;
        Intrinsics.checkNotNullParameter(queueSeed, "<this>");
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        switch (WhenMappings.$EnumSwitchMapping$2[queueSeed.getQueueSeedType().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                Log.warning(TAG, Intrinsics.stringPlus("toQueueSeedContextInput, unsupported queue seed type for conversion ", queueSeed.getQueueSeedType()));
                return null;
            default:
                QueueIdTransferSeed queueIdTransfer = queueSeed.getQueueIdTransfer();
                QueueIdTransferSeedContextInput queueIdTransferSeedContextInput = queueIdTransfer == null ? null : new QueueIdTransferSeedContextInput(queueIdTransfer.getQueueId(), new DeviceIdentifierInput(queueIdTransfer.getSourceDevice().getDeviceId(), queueIdTransfer.getSourceDevice().getDeviceType()), queueIdTransfer.getShouldManagePlayback(), null, 8, null);
                QueueIdHistoricalPlaybackSeed queueIdHistoricalPlayback = queueSeed.getQueueIdHistoricalPlayback();
                int i = 2;
                if (queueIdHistoricalPlayback == null) {
                    queueIdHistoricalPlaybackSeedContextInput = null;
                } else {
                    queueIdHistoricalPlaybackSeedContextInput = new QueueIdHistoricalPlaybackSeedContextInput(queueIdHistoricalPlayback.getQueueId(), b2 == true ? 1 : 0, i, b == true ? 1 : 0);
                }
                PlayableEntitySeed playableEntity = queueSeed.getPlayableEntity();
                if (playableEntity == null) {
                    playableEntitySeedContextInput = null;
                } else {
                    int i2 = WhenMappings.$EnumSwitchMapping$3[playableEntity.getIdType().ordinal()];
                    if (i2 == 1) {
                        zIsStrictAsin = EntityValidatorUtil.isStrictAsin(playableEntity.getId());
                    } else if (i2 == 2) {
                        zIsStrictAsin = EntityValidatorUtil.isUUID(playableEntity.getId());
                    } else {
                        if (i2 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        zIsStrictAsin = EntityValidatorUtil.isPersonalizedPlaylistId(playableEntity.getId()) || EntityValidatorUtil.isUUID(playableEntity.getId());
                    }
                    if (!zIsStrictAsin && queueSeed.getQueueSeedType() == QueueSeedTypeEnum.PLAYABLE_ENTITY_ID) {
                        return null;
                    }
                    playableEntitySeedContextInput = new PlayableEntitySeedContextInput(playableEntity.getId(), playableEntity.getIdType(), null, 4, null);
                }
                TrackListSeed trackList = queueSeed.getTrackList();
                if (trackList == null) {
                    trackListSeedContextInput = null;
                } else {
                    List<TrackReference> tracks = trackList.getTracks();
                    ArrayList arrayList = new ArrayList();
                    for (TrackReference trackReference : tracks) {
                        int i3 = WhenMappings.$EnumSwitchMapping$4[trackReference.getIdType().ordinal()];
                        if (i3 == 1) {
                            zIsStrictAsin2 = EntityValidatorUtil.isStrictAsin(trackReference.getId());
                        } else {
                            if (i3 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            zIsStrictAsin2 = EntityValidatorUtil.isUUID(trackReference.getId());
                        }
                        TrackReferenceInput trackReferenceInput = zIsStrictAsin2 ? new TrackReferenceInput(trackReference.getId(), trackReference.getIdType()) : null;
                        if (trackReferenceInput != null) {
                            arrayList.add(trackReferenceInput);
                        }
                    }
                    if (queueSeed.getQueueSeedType() == QueueSeedTypeEnum.TRACK_LIST && arrayList.isEmpty()) {
                        return null;
                    }
                    trackListSeedContextInput = new TrackListSeedContextInput(arrayList, new MetricsContextInput(CollectionsKt__CollectionsKt.emptyList()));
                }
                LibraryAlbumSeed libraryAlbum = queueSeed.getLibraryAlbum();
                LibraryAlbumSeedContextInput libraryAlbumSeedContextInput = libraryAlbum == null ? null : new LibraryAlbumSeedContextInput(libraryAlbum.getAlbumName(), libraryAlbum.getArtistName(), null, 4, null);
                LibraryArtistSeed libraryArtist = queueSeed.getLibraryArtist();
                if (libraryArtist == null) {
                    libraryArtistSeedContextInput = null;
                } else {
                    libraryArtistSeedContextInput = new LibraryArtistSeedContextInput(libraryArtist.getArtistName(), b4 == true ? 1 : 0, i, b3 == true ? 1 : 0);
                }
                StationSeed station = queueSeed.getStation();
                return new QueueSeedContextInput(playModeEnum == null ? queueSeed.getPlayMode() : playModeEnum, queueSeed.getQueueSeedType(), queueIdTransferSeedContextInput, queueIdHistoricalPlaybackSeedContextInput, playableEntitySeedContextInput, trackListSeedContextInput, libraryAlbumSeedContextInput, libraryArtistSeedContextInput, station != null ? new StationSeedContextInput(station.getSeedType(), station.getSeedId(), null, 4, null) : null, null, null, null, null, null, null, null, 65024, null);
        }
    }

    public final ShuffleModeEnum getShuffleMode(boolean isShuffled) {
        return isShuffled ? ShuffleModeEnum.SHUFFLE_ON : ShuffleModeEnum.SHUFFLE_OFF;
    }

    public final boolean isOnDemandNonStationQueueSeed(QueueSeedContextInput queueSeedContextInput) {
        Intrinsics.checkNotNullParameter(queueSeedContextInput, "queueSeedContextInput");
        return queueSeedContextInput.getPlayMode() == PlayModeEnum.ON_DEMAND && queueSeedContextInput.getQueueSeedType() != QueueSeedTypeEnum.STATION_SEED;
    }

    public final boolean isSameQueueSeedOnlineInteraction(QueueSeed queueSeed, QueueSeedContextInput queueSeedContextInput) {
        Intrinsics.checkNotNullParameter(queueSeedContextInput, "queueSeedContextInput");
        if (queueSeed != null && queueSeed.getPlayMode() == queueSeedContextInput.getPlayMode() && queueSeed.getQueueSeedType() == queueSeedContextInput.getQueueSeedType()) {
            return Intrinsics.areEqual(toQueueSeed(queueSeedContextInput), queueSeed);
        }
        return false;
    }

    private final QueueSeed toQueueSeed(QueueSeedContextInput queueSeedContextInput) {
        TrackListSeed trackListSeed;
        switch (WhenMappings.$EnumSwitchMapping$2[queueSeedContextInput.getQueueSeedType().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                Log.warning(TAG, Intrinsics.stringPlus("toQueueSeed, unsupported queue seed type for conversion ", queueSeedContextInput.getQueueSeedType()));
                return null;
            default:
                QueueIdTransferSeedContextInput queueIdTransfer = queueSeedContextInput.getQueueIdTransfer();
                QueueIdTransferSeed queueIdTransferSeed = queueIdTransfer == null ? null : new QueueIdTransferSeed(queueIdTransfer.getQueueId(), new DeviceIdentifier(queueIdTransfer.getSourceDevice().getDeviceId(), queueIdTransfer.getSourceDevice().getDeviceType()), queueIdTransfer.getShouldManagePlayback());
                QueueIdHistoricalPlaybackSeedContextInput queueIdHistoricalPlayback = queueSeedContextInput.getQueueIdHistoricalPlayback();
                QueueIdHistoricalPlaybackSeed queueIdHistoricalPlaybackSeed = queueIdHistoricalPlayback == null ? null : new QueueIdHistoricalPlaybackSeed(queueIdHistoricalPlayback.getQueueId());
                PlayableEntitySeedContextInput playableEntity = queueSeedContextInput.getPlayableEntity();
                PlayableEntitySeed playableEntitySeed = playableEntity == null ? null : new PlayableEntitySeed(playableEntity.getIdType(), playableEntity.getId());
                TrackListSeedContextInput trackList = queueSeedContextInput.getTrackList();
                if (trackList == null) {
                    trackListSeed = null;
                } else {
                    List<TrackReferenceInput> tracks = trackList.getTracks();
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
                    for (TrackReferenceInput trackReferenceInput : tracks) {
                        arrayList.add(new TrackReference(trackReferenceInput.getId(), trackReferenceInput.getIdType()));
                    }
                    trackListSeed = new TrackListSeed(arrayList);
                }
                LibraryAlbumSeedContextInput libraryAlbum = queueSeedContextInput.getLibraryAlbum();
                LibraryAlbumSeed libraryAlbumSeed = libraryAlbum == null ? null : new LibraryAlbumSeed(libraryAlbum.getAlbumName(), libraryAlbum.getArtistName());
                LibraryArtistSeedContextInput libraryArtist = queueSeedContextInput.getLibraryArtist();
                LibraryArtistSeed libraryArtistSeed = libraryArtist == null ? null : new LibraryArtistSeed(libraryArtist.getArtistName());
                StationSeedContextInput station = queueSeedContextInput.getStation();
                return new QueueSeed(queueSeedContextInput.getPlayMode(), queueSeedContextInput.getQueueSeedType(), queueIdTransferSeed, queueIdHistoricalPlaybackSeed, playableEntitySeed, trackListSeed, libraryAlbumSeed, libraryArtistSeed, null, station != null ? new StationSeed(station.getSeedType(), station.getSeedId()) : null, null, null, null, 7424, null);
        }
    }

    @JvmStatic
    public static final StartAt getStartAtWithEntityAsin(String currentTrackAsin, int offsetInMs) {
        Intrinsics.checkNotNullParameter(currentTrackAsin, "currentTrackAsin");
        return new StartAt("ASIN", currentTrackAsin, offsetInMs);
    }

    @JvmStatic
    public static final StartAt getStartAtWithEntityObjectId(String currentTrackLuid, int offsetInMs) {
        Intrinsics.checkNotNullParameter(currentTrackLuid, "currentTrackLuid");
        return new StartAt("OBJECT_ID", currentTrackLuid, offsetInMs);
    }

    public final ServiceTierEnum getServiceTier() {
        if (UserSubscriptionUtil.isHawkfire()) {
            return ServiceTierEnum.UNLIMITED;
        }
        if (UserSubscriptionUtil.getUserSubscription().isSonicRush()) {
            return ServiceTierEnum.SONIC_RUSH;
        }
        if (UserSubscriptionUtil.isPrime()) {
            return ServiceTierEnum.PRIME;
        }
        if (UserSubscriptionUtil.isNightwingOnly()) {
            return ServiceTierEnum.NIGHTWING;
        }
        return null;
    }

    public static /* synthetic */ PlaybackOptionsInput getPlayBackOptions$default(PlayModeEnum playModeEnum, boolean z, RepeatMode repeatMode, int i, Object obj) {
        if ((i & 4) != 0) {
            repeatMode = null;
        }
        return getPlayBackOptions(playModeEnum, z, repeatMode);
    }

    @JvmStatic
    public static final PlaybackOptionsInput getPlayBackOptions(PlayModeEnum playMode, boolean isShuffled, RepeatMode repeatMode) {
        LoopModeEnum loopModeEnum;
        ShuffleModeEnum shuffleModeEnum = (playMode == PlayModeEnum.AUGMENTED || playMode == PlayModeEnum.SIMILAR || !isShuffled) ? ShuffleModeEnum.SHUFFLE_OFF : ShuffleModeEnum.SHUFFLE_ON;
        ShuffleModeEnum shuffleModeEnum2 = shuffleModeEnum;
        int i = repeatMode == null ? -1 : WhenMappings.$EnumSwitchMapping$1[repeatMode.ordinal()];
        if (i == 1) {
            loopModeEnum = LoopModeEnum.LOOP_ALL;
        } else if (i == 2) {
            loopModeEnum = LoopModeEnum.LOOP_ONE;
        } else if (i == 3) {
            loopModeEnum = LoopModeEnum.LOOP_OFF;
        } else {
            loopModeEnum = LoopModeEnum.LOOP_OFF;
        }
        return new PlaybackOptionsInput(loopModeEnum, shuffleModeEnum2, null, null, 12, null);
    }

    public static /* synthetic */ QueueSeed getQueueSeedByTracks$default(List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = null;
        }
        if ((i & 2) != 0) {
            list2 = null;
        }
        return getQueueSeedByTracks(list, list2);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00ba  */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.amazon.digitalmusicxp.types.QueueSeed getQueueSeedByTracks(java.util.List<? extends com.amazon.mp3.library.item.MusicTrack> r34, java.util.List<? extends com.amazon.mp3.prime.browse.metadata.PrimeTrack> r35) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil.getQueueSeedByTracks(java.util.List, java.util.List):com.amazon.digitalmusicxp.types.QueueSeed");
    }

    @JvmStatic
    public static final QueueSeedContextInput getQueueSeedContextInputByEntityReferences(List<EntityReference> references, PlayModeEnum playMode) {
        Intrinsics.checkNotNullParameter(references, "references");
        if (references.size() == 1) {
            String identifier = ((EntityReference) CollectionsKt___CollectionsKt.first((List) references)).getIdentifier();
            PlayableEntitySeedIdTypeEnum trackPlayableEntitySeedIdTypeEnum = INSTANCE.getTrackPlayableEntitySeedIdTypeEnum(identifier);
            if (trackPlayableEntitySeedIdTypeEnum == null) {
                return null;
            }
            return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.PLAYABLE_ENTITY_ID, null, null, new PlayableEntitySeedContextInput(identifier, trackPlayableEntitySeedIdTypeEnum, null, 4, null), null, null, null, null, null, null, null, null, null, null, null, 65516, null);
        }
        ArrayList arrayList = new ArrayList();
        for (EntityReference entityReference : references) {
            TrackReferenceIdTypeEnum trackReferenceIdTypeEnum = INSTANCE.getTrackReferenceIdTypeEnum(entityReference.getIdentifier());
            TrackReferenceInput trackReferenceInput = trackReferenceIdTypeEnum == null ? null : new TrackReferenceInput(entityReference.getIdentifier(), trackReferenceIdTypeEnum);
            if (trackReferenceInput != null) {
                arrayList.add(trackReferenceInput);
            }
        }
        if (!arrayList.isEmpty()) {
            return new QueueSeedContextInput(playMode, QueueSeedTypeEnum.TRACK_LIST, null, null, null, new TrackListSeedContextInput(arrayList, new MetricsContextInput(CollectionsKt__CollectionsKt.emptyList())), null, null, null, null, null, null, null, null, null, null, 65500, null);
        }
        return null;
    }

    private final TrackReferenceIdTypeEnum getTrackReferenceIdTypeEnum(String identifier) {
        if (EntityValidatorUtil.isStrictAsin(identifier)) {
            return TrackReferenceIdTypeEnum.ASIN;
        }
        if (EntityValidatorUtil.isUUID(identifier)) {
            return TrackReferenceIdTypeEnum.OBJECT_ID;
        }
        Log.warning(TAG, Intrinsics.stringPlus("getQueueSeedContextInputByEntityReferences, invalid identifier=", identifier));
        return null;
    }

    private final PlayableEntitySeedIdTypeEnum getTrackPlayableEntitySeedIdTypeEnum(String identifier) {
        if (EntityValidatorUtil.isStrictAsin(identifier)) {
            return PlayableEntitySeedIdTypeEnum.ASIN;
        }
        if (EntityValidatorUtil.isUUID(identifier)) {
            return PlayableEntitySeedIdTypeEnum.OBJECT_ID;
        }
        Log.warning(TAG, Intrinsics.stringPlus("getTrackPlayableEntitySeedIdTypeEnum, invalid identifier=", identifier));
        return null;
    }

    @JvmStatic
    public static final boolean canPerformSingleActionStrategy(SingleActionStrategy actionStrategy) {
        Boolean booleanAction;
        if (actionStrategy == null) {
            return false;
        }
        int i = WhenMappings.$EnumSwitchMapping$7[actionStrategy.getActionType().ordinal()];
        if (i == 1) {
            booleanAction = actionStrategy.getBooleanAction();
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            booleanAction = Boolean.FALSE;
        }
        if (booleanAction == null) {
            return false;
        }
        return booleanAction.booleanValue();
    }

    public static /* synthetic */ boolean canPerformActionStrategy$default(ActionStrategy actionStrategy, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        return canPerformActionStrategy(actionStrategy, function0);
    }

    @JvmStatic
    public static final boolean canPerformActionStrategy(ActionStrategy actionStrategy, Function0<Boolean> hasNoSkipsRemaining) {
        boolean z = false;
        if (actionStrategy == null) {
            return false;
        }
        int i = WhenMappings.$EnumSwitchMapping$8[actionStrategy.getStrategyType().ordinal()];
        if (i == 1) {
            return canPerformSingleActionStrategy(actionStrategy.getSingleActionStrategy());
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        CompositeActionStrategy compositeActionStrategy = actionStrategy.getCompositeActionStrategy();
        if (compositeActionStrategy == null) {
            return false;
        }
        if (WhenMappings.$EnumSwitchMapping$9[compositeActionStrategy.getActionType().ordinal()] != 1) {
            throw new NoWhenBranchMatchedException();
        }
        if (hasNoSkipsRemaining != null && hasNoSkipsRemaining.invoke().booleanValue()) {
            z = true;
        }
        if (z) {
            SkipLimitIOTAction skipLimitIOTAction = compositeActionStrategy.getSkipLimitIOTAction();
            return canPerformSingleActionStrategy(skipLimitIOTAction != null ? skipLimitIOTAction.getNoSkipsRemainingAction() : null);
        }
        SkipLimitIOTAction skipLimitIOTAction2 = compositeActionStrategy.getSkipLimitIOTAction();
        return canPerformSingleActionStrategy(skipLimitIOTAction2 != null ? skipLimitIOTAction2.getSkipsRemainingActionStrategy() : null);
    }

    @JvmStatic
    public static final QueueSeedContextInput createOnlineQueueSeedWithSeedContext(QueueSeedContextInput seed, List<? extends SeedContextAugmentation> augmentations) {
        Intrinsics.checkNotNullParameter(augmentations, "augmentations");
        ArtistStudioSeedContextInputProvider artistStudioSeedContextInputProvider = ArtistStudioSeedContextInputProvider.INSTANCE;
        if (artistStudioSeedContextInputProvider.shouldProvideSeedContextInput(seed)) {
            return artistStudioSeedContextInputProvider.provideSeedContextInput(seed);
        }
        if (seed == null) {
            return null;
        }
        return CloudQueueAugmentationKt.augmentSeedContextWith(seed, augmentations);
    }

    @JvmStatic
    public static final QueueSeedContextInput getQueueSeedContextInput(Context context, String queueId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceType = accountCredentialStorage.getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceId, "deviceId");
        Intrinsics.checkNotNullExpressionValue(deviceType, "deviceType");
        return new QueueSeedContextInput(null, QueueSeedTypeEnum.QUEUE_ID_TRANSFER, new QueueIdTransferSeedContextInput(queueId, new DeviceIdentifierInput(deviceId, deviceType), Boolean.TRUE, null, 8, null), null, null, null, null, null, null, null, null, null, null, null, null, null, 65529, null);
    }

    public final List<ContentEncoding> contentEncodingListConverter(List<? extends ContentEncodingEnum> contentEncodingEnumList) {
        ContentEncoding contentEncoding;
        if (contentEncodingEnumList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentEncodingEnumList, 10));
        Iterator<T> it = contentEncodingEnumList.iterator();
        while (it.hasNext()) {
            int i = WhenMappings.$EnumSwitchMapping$5[((ContentEncodingEnum) it.next()).ordinal()];
            if (i == 1) {
                contentEncoding = ContentEncoding.HD;
            } else if (i == 2) {
                contentEncoding = ContentEncoding.UHD;
            } else if (i == 3) {
                contentEncoding = ContentEncoding.IMMERSIVE;
            } else if (i == 4) {
                contentEncoding = ContentEncoding.DOLBY_ATMOS;
            } else {
                if (i != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                contentEncoding = ContentEncoding.SONY_360;
            }
            arrayList.add(contentEncoding);
        }
        return arrayList;
    }

    public final List<ContentEncodingEnum> contentEncodingEnumListConverter(List<? extends ContentEncoding> contentEncodingList) {
        ContentEncodingEnum contentEncodingEnum;
        if (contentEncodingList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = contentEncodingList.iterator();
        while (it.hasNext()) {
            switch (WhenMappings.$EnumSwitchMapping$6[((ContentEncoding) it.next()).ordinal()]) {
                case 1:
                    contentEncodingEnum = ContentEncodingEnum.HD;
                    break;
                case 2:
                    contentEncodingEnum = ContentEncodingEnum.UHD;
                    break;
                case 3:
                    contentEncodingEnum = ContentEncodingEnum.IMMERSIVE;
                    break;
                case 4:
                    contentEncodingEnum = ContentEncodingEnum.DOLBY_ATMOS;
                    break;
                case 5:
                    contentEncodingEnum = ContentEncodingEnum.SONY_360;
                    break;
                case 6:
                    contentEncodingEnum = null;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (contentEncodingEnum != null) {
                arrayList.add(contentEncodingEnum);
            }
        }
        return arrayList;
    }

    public final QueueSeedContextInput getQueueSeedContextInputsByAsin(List<? extends MusicTrack> trackList, List<? extends PrimeTrack> primeTrackList, PlayModeEnum playMode, AbstractItem collectionEntity) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(playMode, "playMode");
        if (trackList == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            Iterator<T> it = trackList.iterator();
            while (it.hasNext()) {
                String asin = ((MusicTrack) it.next()).getAsin();
                if (asin != null) {
                    arrayList.add(asin);
                }
            }
        }
        if (arrayList == null) {
            if (primeTrackList == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList();
                Iterator<T> it2 = primeTrackList.iterator();
                while (it2.hasNext()) {
                    String asin2 = ((PrimeTrack) it2.next()).getAsin();
                    if (asin2 != null) {
                        arrayList.add(asin2);
                    }
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        CloudQueueDigitalXPUtil cloudQueueDigitalXPUtil = INSTANCE;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (EntityValidatorUtil.isStrictAsin((String) obj)) {
                arrayList2.add(obj);
            }
        }
        return cloudQueueDigitalXPUtil.getQueueSeedContextInputsByAsins(arrayList2, playMode, collectionEntity);
    }
}
