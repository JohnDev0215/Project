package com.amazon.mp3.cloudqueue;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.net.Uri;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.LruCache;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.digitalmusicxp.enums.ActionStrategyTypeEnum;
import com.amazon.digitalmusicxp.enums.ContentEncodingEnum;
import com.amazon.digitalmusicxp.enums.DevicePlaybackStateEnum;
import com.amazon.digitalmusicxp.enums.LoopModeEnum;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.PlaybackAssetSourceTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueEntitySubtypeEnum;
import com.amazon.digitalmusicxp.enums.QueueInteractionPatternEnum;
import com.amazon.digitalmusicxp.enums.QueueSequenceSliceName;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.enums.ShuffleModeEnum;
import com.amazon.digitalmusicxp.enums.SingleActionStrategyActionTypeEnum;
import com.amazon.digitalmusicxp.enums.SupportedFeatureEnum;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.models.DevicePlaybackState;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.models.QueueCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.ActionStrategy;
import com.amazon.digitalmusicxp.types.ClientMetricsInfo;
import com.amazon.digitalmusicxp.types.CuePoints;
import com.amazon.digitalmusicxp.types.DeviceContext;
import com.amazon.digitalmusicxp.types.DeviceCurrentPlaybackState;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.digitalmusicxp.types.FixedSizeImage;
import com.amazon.digitalmusicxp.types.GenericQueueEntity;
import com.amazon.digitalmusicxp.types.ImageManifest;
import com.amazon.digitalmusicxp.types.ImageMetadata;
import com.amazon.digitalmusicxp.types.MetricsContextTuple;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.digitalmusicxp.types.PlaybackAssetSource;
import com.amazon.digitalmusicxp.types.QueueCapability;
import com.amazon.digitalmusicxp.types.QueueEntityCapability;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.digitalmusicxp.types.SingleActionStrategy;
import com.amazon.digitalmusicxp.types.StartAt;
import com.amazon.digitalmusicxp.types.TrackAlbum;
import com.amazon.digitalmusicxp.types.TrackArtist;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.basequeue.NoOpCloudQueueAppClient;
import com.amazon.mp3.basequeue.NoOpCloudQueueClient;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO;
import com.amazon.mp3.cloudqueue.CloudQueueAction;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueLibraryTrackMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.UrlMediaLinkProvider;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.ContentAccessType;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.connect.firefly.LikesService;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.PlayerPlaybackProvider;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.config.SequencerPersistenceProvider;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.casting.PlaybackCastedEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.playback.data.PlayQueueInteractionPattern;
import com.amazon.music.storeservice.model.TrackItem;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.hub.HubEventFilter;
import com.amplifyframework.hub.HubSubscriber;
import com.amplifyframework.hub.SubscriptionToken;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.visualon.OSMPUtils.voMimeTypes;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: CloudQueuePlaybackUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueuePlaybackUtil;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CloudQueuePlaybackUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CloudQueuePlaybackUtil.class.getSimpleName();
    private static final LruCache<String, Long> albumIdCache;
    private static SubscriptionToken amplifyDBSyncQueriesReadySubscriptionToken;
    private static final Lazy<Set<ContentAccessType>> cacheAllContentAccessTypes$delegate;
    private static final Map<Uri, String> cacheUdoPlaylistLuid;
    private static QueueEntityCapability defaultCapability;
    private static final PublishSubject<Companion.PlaybackContext> playbackObservable;
    private static final Lazy<String> serviceTier$delegate;
    private static final List<SupportedFeatureEnum> supportedFeaturesEnumsOnline;

    @JvmStatic
    @WorkerThread
    public static final void startOnlineQueueInteraction(String str, CloudQueueAppClient cloudQueueAppClient, Context context, QueueSeedContextInput queueSeedContextInput, PlaybackPageType playbackPageType, PlaybackListener playbackListener, ControlSource controlSource, Uri uri, boolean z, MediaCollectionId mediaCollectionId, UpsellInformation upsellInformation, boolean z2, StartAt startAt, PlaybackOptionsInput playbackOptionsInput, Boolean bool) {
        INSTANCE.startOnlineQueueInteraction(str, cloudQueueAppClient, context, queueSeedContextInput, playbackPageType, playbackListener, controlSource, uri, z, mediaCollectionId, upsellInformation, z2, startAt, playbackOptionsInput, bool);
    }

    /* JADX INFO: compiled from: CloudQueuePlaybackUtil.kt */
    @Metadata(bv = {}, d1 = {"\u0000è\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\u0092\u0002B\u000b\b\u0002¢\u0006\u0006\b\u0090\u0002\u0010\u0091\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002Jf\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\"\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fj\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e`\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0013H\u0002J\u0018\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\"\u0010\"\u001a\u00020!2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u000eH\u0002J\"\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u000eH\u0002J\u001a\u0010'\u001a\u00020&2\u0006\u0010#\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u000eH\u0002J*\u0010,\u001a\u00020+2\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020)2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u000eH\u0002J*\u00100\u001a\u00020+2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010.\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010/\u001a\u00020\rH\u0002J.\u00102\u001a\u00020&2\u0006\u00101\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010.\u001a\u00020-2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\rH\u0002J*\u00103\u001a\u00020&2\u0006\u0010(\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010/\u001a\u00020\r2\u0006\u0010.\u001a\u00020-H\u0002J$\u00108\u001a\u0004\u0018\u0001072\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010:\u001a\u000209H\u0002J0\u0010=\u001a\u0004\u0018\u00010&2\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0;H\u0002J.\u00102\u001a\u00020&2\u0006\u00101\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0;H\u0002J8\u00103\u001a\u00020&2\u0006\u0010(\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\b\u0010/\u001a\u0004\u0018\u00010\r2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0;H\u0002J \u0010B\u001a\u00020A2\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u001e\u0010F\u001a\u00020E*\u00020\u001c2\u0006\u0010C\u001a\u00020\r2\b\u0010D\u001a\u0004\u0018\u00010\rH\u0002J\n\u0010H\u001a\u0004\u0018\u00010GH\u0002J\b\u0010J\u001a\u00020IH\u0002J>\u0010N\u001a\u00020A2\u0006\u0010L\u001a\u00020K2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020\r2\b\b\u0002\u0010M\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u0018\u0010Q\u001a\u00020A2\u0006\u0010O\u001a\u00020\r2\u0006\u0010P\u001a\u00020\rH\u0002J(\u0010U\u001a\u00020T2\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010S\u001a\b\u0012\u0004\u0012\u00020K0R2\b\u0010*\u001a\u0004\u0018\u00010)H\u0003J\u0018\u0010V\u001a\u00020T2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010@\u001a\u00020\rH\u0002J0\u0010Z\u001a\u00020A2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\u00132\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010XH\u0002J0\u0010[\u001a\u00020A2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\u00132\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010XH\u0003J\"\u0010`\u001a\u00020A2\u000e\u0010]\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0\\2\b\u0010_\u001a\u0004\u0018\u00010^H\u0002J.\u0010d\u001a\u00020A2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020K0R2\u0006\u0010a\u001a\u00020T2\u0006\u0010b\u001a\u00020\n2\u0006\u0010c\u001a\u00020+H\u0002J\u001e\u0010f\u001a\u00020A2\u0014\u0010e\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\r0;H\u0002J&\u0010i\u001a\n\u0012\u0004\u0012\u00020h\u0018\u00010\\2\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0\\H\u0002J\b\u0010k\u001a\u00020jH\u0002J\u0010\u0010n\u001a\u00020m2\u0006\u0010l\u001a\u00020\rH\u0002J\u0012\u0010p\u001a\u00020\r2\b\u0010o\u001a\u0004\u0018\u00010\rH\u0002J\u0012\u0010s\u001a\u00020r2\b\u0010q\u001a\u0004\u0018\u00010\rH\u0002J\u0019\u0010v\u001a\u00020u2\b\u0010t\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\bv\u0010wJ(\u0010|\u001a\b\u0012\u0004\u0012\u00020{0\\2\u0006\u0010x\u001a\u00020\b2\u0006\u0010y\u001a\u00020\u000e2\b\u0010z\u001a\u0004\u0018\u00010\rH\u0002J\u001c\u0010\u007f\u001a\u0004\u0018\u00010~2\b\u0010}\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0005\b\u007f\u0010\u0080\u0001Ji\u0010\u0085\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0\\2\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\\2\u0006\u0010.\u001a\u00020-2\u0006\u0010?\u001a\u00020>2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010/\u001a\u00020\r2\u0012\b\u0002\u0010\u0083\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0082\u0001\u0018\u00010\\2\b\b\u0002\u0010M\u001a\u00020\u00132\t\b\u0002\u0010\u0084\u0001\u001a\u00020\nJ7\u0010\u0087\u0001\u001a\u00020+2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010.\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010/\u001a\u00020\r2\f\b\u0002\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0082\u0001J5\u0010\u008c\u0001\u001a\u00030\u008b\u00012\r\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\\2\u0007\u0010\u0089\u0001\u001a\u00020\n2\u0007\u0010\u008a\u0001\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u0013H\u0007J4\u0010\u0090\u0001\u001a\u00020\u00042\u0006\u00106\u001a\u00020{2\u0007\u0010\u008d\u0001\u001a\u00020\r2\u0006\u0010@\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\r2\u0007\u0010\u008f\u0001\u001a\u00020\u0006H\u0007J9\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\\2\f\u00106\u001a\b\u0012\u0004\u0012\u00020{0\\2\u0006\u0010@\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\r2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0006H\u0007J/\u0010\u0093\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\\2\r\u00106\u001a\t\u0012\u0005\u0012\u00030\u0092\u00010\\2\u0006\u0010@\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\rH\u0007J.\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\\2\f\u00106\u001a\b\u0012\u0004\u0012\u00020h0\\2\u0006\u0010@\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\rH\u0007J/\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\\2\r\u00106\u001a\t\u0012\u0005\u0012\u00030\u0095\u00010\\2\u0006\u0010@\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\rH\u0007J@\u0010\u0097\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0\\2\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u001c0\\2\u0006\u0010*\u001a\u00020)2\u0006\u0010.\u001a\u00020-2\u0006\u0010?\u001a\u00020>2\b\u0010 \u001a\u0004\u0018\u00010\u000eJ \u0010\u0098\u0001\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010@\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\rJ0\u0010\u009d\u0001\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0;2\u0010\u0010\u009a\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0099\u0001\u0018\u00010\\H\u0000¢\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001J)\u0010¢\u0001\u001a\u0002092\u0007\u0010\u009e\u0001\u001a\u00020\u00132\f\b\u0002\u0010\u009f\u0001\u001a\u0005\u0018\u00010\u0082\u0001H\u0000¢\u0006\u0006\b \u0001\u0010¡\u0001J\u0019\u0010¥\u0001\u001a\u00020\u00132\u0010\u0010¤\u0001\u001a\u000b\u0012\u0005\u0012\u00030£\u0001\u0018\u00010\\J±\u0001\u0010³\u0001\u001a\u00020A2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\r2\u0007\u0010L\u001a\u00030¦\u00012\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010¨\u0001\u001a\u00030§\u00012\u0006\u0010\u001f\u001a\u00020\u001e2\f\b\u0002\u0010ª\u0001\u001a\u0005\u0018\u00010©\u00012\u0006\u0010?\u001a\u00020>2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0007\u0010«\u0001\u001a\u00020\u00132\t\u0010¬\u0001\u001a\u0004\u0018\u0001072\b\u0010Y\u001a\u0004\u0018\u00010X2\u0007\u0010\u00ad\u0001\u001a\u00020\u00132\f\b\u0002\u0010¯\u0001\u001a\u0005\u0018\u00010®\u00012\f\b\u0002\u0010±\u0001\u001a\u0005\u0018\u00010°\u00012\u000b\b\u0002\u0010²\u0001\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0006\b³\u0001\u0010´\u0001JÖ\u0001\u0010¾\u0001\u001a\u00020A2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\r2\f\b\u0002\u0010µ\u0001\u001a\u0005\u0018\u00010§\u00012\f\b\u0002\u0010¨\u0001\u001a\u0005\u0018\u00010¶\u00012\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\t\b\u0002\u0010·\u0001\u001a\u00020\u00132\t\b\u0002\u0010¸\u0001\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020>2\t\b\u0002\u0010¹\u0001\u001a\u00020\u00132\n\b\u0002\u0010z\u001a\u0004\u0018\u00010\r2\u000b\b\u0002\u0010¬\u0001\u001a\u0004\u0018\u0001072\u0011\b\u0002\u0010º\u0001\u001a\n\u0012\u0004\u0012\u00020{\u0018\u00010\\2\u0011\b\u0002\u0010»\u0001\u001a\n\u0012\u0004\u0012\u00020h\u0018\u00010\\2\f\b\u0002\u0010ª\u0001\u001a\u0005\u0018\u00010©\u00012\b\u0010½\u0001\u001a\u00030¼\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u0013H\u0007J\u0081\u0001\u0010À\u0001\u001a\u00020A2\u0006\u0010L\u001a\u00020K2\u0007\u0010¿\u0001\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u000e2\t\b\u0002\u0010·\u0001\u001a\u00020\u00132\t\b\u0002\u0010¸\u0001\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020>2\t\b\u0002\u0010¹\u0001\u001a\u00020\u00132\n\b\u0002\u0010z\u001a\u0004\u0018\u00010\r2\u000b\b\u0002\u0010¬\u0001\u001a\u0004\u0018\u0001072\f\b\u0002\u0010ª\u0001\u001a\u0005\u0018\u00010©\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u0013J®\u0001\u0010Á\u0001\u001a\u00020\n2\u0007\u0010\u008e\u0001\u001a\u00020\r2\u0006\u0010/\u001a\u00020\r2\u0006\u0010L\u001a\u00020K2\u0006\u0010\u0012\u001a\u00020\u00112\r\u0010º\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010\\2\t\b\u0002\u0010¸\u0001\u001a\u00020\n2\t\b\u0002\u0010·\u0001\u001a\u00020\u00132\b\u0010¨\u0001\u001a\u00030¶\u00012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001f\u001a\u00020\u001e2\f\b\u0002\u0010ª\u0001\u001a\u0005\u0018\u00010©\u00012\b\b\u0002\u0010M\u001a\u00020\u00132\u0006\u0010?\u001a\u00020>2\t\b\u0002\u0010¹\u0001\u001a\u00020\u00132\u000b\b\u0002\u0010¬\u0001\u001a\u0004\u0018\u0001072\u0007\u0010\u00ad\u0001\u001a\u00020\u0013H\u0007J¨\u0001\u0010Í\u0001\u001a\u00020A2\u0015\u0010Ä\u0001\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0005\u0012\u00030Ã\u00010Â\u00012\u0006\u0010\u0012\u001a\u00020\u00112\u000f\u0010Å\u0001\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\\2\u000f\u0010Æ\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0\\2\u0007\u0010¸\u0001\u001a\u00020\n2\u0007\u0010·\u0001\u001a\u00020\u00132\u0007\u0010Ç\u0001\u001a\u00020\u00132\b\u0010É\u0001\u001a\u00030È\u00012\b\u0010Ë\u0001\u001a\u00030Ê\u00012\u0007\u0010Ì\u0001\u001a\u00020-2\u0006\u0010?\u001a\u00020>2\b\u0010 \u001a\u0004\u0018\u00010\u000e2\t\u0010¬\u0001\u001a\u0004\u0018\u0001072\u0007\u0010\u00ad\u0001\u001a\u00020\u0013H\u0007J+\u0010Ð\u0001\u001a\u00020A2\u0006\u0010L\u001a\u00020K2\u0006\u0010@\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010Ï\u0001\u001a\u00030Î\u0001H\u0007J\u0018\u0010Ñ\u0001\u001a\u00030Ã\u00012\f\u0010S\u001a\b\u0012\u0004\u0012\u00020K0RH\u0007J#\u0010Ô\u0001\u001a\u00030Ã\u00012\b\u0010Ò\u0001\u001a\u00030Ã\u00012\r\u0010Ó\u0001\u001a\b\u0012\u0004\u0012\u00020E0\\H\u0007J?\u0010Ù\u0001\u001a\u00020A2\r\u0010Õ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\\2\b\u0010Ö\u0001\u001a\u00030Ã\u00012\u0007\u0010×\u0001\u001a\u00020\u00132\t\u0010Ø\u0001\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0006\bÙ\u0001\u0010Ú\u0001J!\u0010Ý\u0001\u001a\u00020A2\u0006\u0010y\u001a\u00020\u000e2\u0007\u0010Û\u0001\u001a\u00020\u00132\u0007\u0010Ü\u0001\u001a\u00020\u0013J\u000f\u0010Þ\u0001\u001a\u00020A2\u0006\u0010\u0012\u001a\u00020\u0011J!\u0010ß\u0001\u001a\u00020A2\u000e\u0010]\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0\\2\b\u0010_\u001a\u0004\u0018\u00010^J\u001e\u0010à\u0001\u001a\u0002042\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u000b\b\u0002\u0010Ì\u0001\u001a\u0004\u0018\u00010-J\u0086\u0001\u0010â\u0001\u001a\u00020A2\u0006\u0010/\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00112\r\u0010º\u0001\u001a\b\u0012\u0004\u0012\u00020h0\\2\t\b\u0002\u0010¸\u0001\u001a\u00020\n2\t\b\u0002\u0010·\u0001\u001a\u00020\u00132\f\b\u0002\u0010¨\u0001\u001a\u0005\u0018\u00010¶\u00012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010?\u001a\u00020>2\t\b\u0002\u0010¹\u0001\u001a\u00020\u00132\b\u0010á\u0001\u001a\u00030¼\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u0013J\u001e\u0010F\u001a\u00020E*\u00020\u00042\u0006\u0010C\u001a\u00020\r2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\rJ&\u0010ã\u0001\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\r0;2\r\u0010º\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010\\H\u0007J\u001e\u0010æ\u0001\u001a\u0004\u0018\u00010\r2\b\u0010 \u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0006\bä\u0001\u0010å\u0001J2\u0010é\u0001\u001a\u0004\u0018\u0001072\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0;2\b\u0010 \u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0006\bç\u0001\u0010è\u0001J6\u0010ë\u0001\u001a\u00020-2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010@\u001a\u0004\u0018\u00010\r2\t\b\u0002\u0010ê\u0001\u001a\u00020\bJ!\u0010í\u0001\u001a\u00020-2\u0010\u0010ì\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0099\u0001\u0018\u00010\\2\u0006\u0010\u001f\u001a\u00020\u001eR(\u0010ô\u0001\u001a\n\u0012\u0005\u0012\u00030ï\u00010î\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bð\u0001\u0010ñ\u0001\u001a\u0006\bò\u0001\u0010ó\u0001R%\u0010ö\u0001\u001a\u000b õ\u0001*\u0004\u0018\u00010\r0\r8\u0006¢\u0006\u0010\n\u0006\bö\u0001\u0010÷\u0001\u001a\u0006\bø\u0001\u0010ù\u0001R\u0017\u0010ú\u0001\u001a\u00020~8\u0002X\u0082T¢\u0006\b\n\u0006\bú\u0001\u0010û\u0001R&\u0010ý\u0001\u001a\u0011\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010~0ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bý\u0001\u0010þ\u0001R\u001c\u0010\u0080\u0002\u001a\u0005\u0018\u00010ÿ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0002\u0010\u0081\u0002R&\u0010\u0083\u0002\u001a\u0011\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0082\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0083\u0002\u0010\u0084\u0002R\u001b\u0010\u0085\u0002\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0085\u0002\u0010\u0086\u0002R\u0017\u0010\u0087\u0002\u001a\u00020\n8\u0006X\u0086T¢\u0006\b\n\u0006\b\u0087\u0002\u0010\u0088\u0002R\u001f\u0010\u008b\u0002\u001a\n\u0012\u0005\u0012\u00030\u008a\u00020\u0089\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008b\u0002\u0010\u008c\u0002R\u001e\u0010\u008e\u0002\u001a\t\u0012\u0005\u0012\u00030\u008d\u00020\\8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008e\u0002\u0010\u008f\u0002¨\u0006\u0093\u0002"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueuePlaybackUtil$Companion;", "", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "getDefaultCapability", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "entity", "Lcom/amazon/mp3/prime/CTAPrimeCache;", "ctaPrimeCache", "Lcom/amazon/mp3/library/item/factory/CirrusSourceLibraryItemFactory;", "cirrusSourceLibraryItemFactory", "", "rowNumber", "Ljava/util/HashMap;", "", "Landroid/net/Uri;", "Lkotlin/collections/HashMap;", "asinToUriMap", "Landroid/content/Context;", "context", "", "isSpatialAudioEnabled", "isCarModePage", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertQueueEntityToBaseModel", "entityIdentifier", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "retrieveTrackUriFromEntityId", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "interlude", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "contentUri", "Lcom/amazon/mp3/cloudqueue/sequencer/InterludeMediaItem;", "convertGenericInterludeEntityToMediaItem", "ad", "Lcom/amazon/mp3/cloudqueue/sequencer/AdMediaItem;", "convertGenericAdvertisementEntityToMediaItem", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "getAdvertisementMediaCollectionInfo", "song", "Lcom/amazon/digitalmusicxp/models/Queue;", "queue", "Lcom/amazon/music/media/playback/MediaItem;", "convertGenericSongEntityToMediaItem", "Lcom/amazon/mp3/cloudqueue/model/MetricsContext;", "collectionMetricsContext", "queueTitle", "convertGenericLibrarySongEntityToMediaItem", voMimeTypes.VOBASE_TYPE_VIDEO, "getVideoMediaCollectionInfo", "getAudioMediaCollectionInfo", "Lcom/amazon/music/media/playback/MediaCollectionType;", "mediaCollectionType", "track", "Lcom/amazon/music/media/playback/MediaCollectionId;", "getMediaCollectionId", "Lcom/amazon/music/media/playback/access/MediaAccessInfo;", "getVideoMediaAccessInfo", "", "metricsContextMap", "getInterludeMediaCollectionInfo", "Lcom/amazon/music/media/playback/ControlSource;", "controlSource", "queueId", "", "populatePlayQueueOnDBSync", "capabilitySetId", "metricsContextId", "Lcom/amazon/digitalmusicxp/types/EntityReference;", "toEntityReference", "Lcom/amazon/digitalmusicxp/types/DeviceContext;", "getCastingTargetDeviceContext", "Lio/reactivex/Completable;", "restartAmplifyDB", "Lcom/amazon/digitalmusicxp/clients/CloudQueueClient;", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "isCustomerSelectedEntity", "populatePlayQueueFromAmplifyDB", ParserUtil.TAG_QUERY_PARAM_NAME, NotificationCompat.CATEGORY_MESSAGE, "emitPlaybackErrorMetrics", "Lio/reactivex/Observable;", "cloudQueueClientObservable", "Lcom/amazon/digitalmusicxp/models/DevicePlaybackState;", "getCurrentDevicePlaybackState", "getNewDummyDevicePlaybackState", "isStation", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "upsellInformation", "startNowPlayingScreen", "startNowPlayingScreenFromMainThread", "", "mediaItems", "Lcom/amazon/mp3/likes/LikesServiceProxy;", "likesService", "fetchMediaItemLikeStateBatch", "devicePlaybackState", "artworkSize", "mediaItem", "updateDeviceState", "trackToAlbumAsins", "insertTrackIntoCTAPrimeCache", "trackAsins", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "getPrimeTrackFromTrackAsin", "Lcom/amazon/mp3/cloudqueue/sequencer/UrlMediaLinkProvider;", "getInterludeMediaLinkProvider", "error", "Lcom/amazon/music/media/playback/PlaybackException$PlaybackError;", "checkErrorType", "luid", "getQueueEntityMetadataId", "imageURL", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "getDefaultImageMetadata", "durationInSecond", "Lcom/amazon/digitalmusicxp/types/CuePoints;", "getCuePoints", "(Ljava/lang/Integer;)Lcom/amazon/digitalmusicxp/types/CuePoints;", "cirrusSourceLibrary", "uri", "sortOrder", "Lcom/amazon/mp3/library/item/MusicTrack;", "getMusicTracksFromContentUri", "albumAsin", "", "getAlbumId", "(Ljava/lang/String;)Ljava/lang/Long;", "entities", "Lcom/amazon/mp3/prime/ContentCatalogStatus;", "contentCatalogStatusList", "startPosition", "convertQueueEntitiesToMediaItems", "contentCatalogStatus", "convertSongEntityToMediaItem", "dataList", "pageIndex", "pageSize", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convertQueueEntitiesIntoViewModel", "trackId", "customerId", "primeCache", "convertTrackToSingleQueueEntity", "convertTrackToQueueEntity", "Lcom/amazon/music/storeservice/model/TrackItem;", "convertTrackItemToQueueEntity", "convertPrimeTrackToQueueEntity", "Lcom/amazon/mp3/playlist/CatalogPlaylistTrack;", "convertPrimePlayListTrackToQueueEntity", "convertGenericQueueEntitiesToMediaItems", "convertGenericQueueEntityToQueueEntityMetadata", "Lcom/amazon/digitalmusicxp/types/MetricsContextTuple;", "tuples", "convertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/util/List;)Ljava/util/Map;", "convertMetricsTupleToMap", BrushV2Converter.KEY_IS_EXPLICIT, NotificationCompat.CATEGORY_STATUS, "getAudioMediaAccessInfo$DigitalMusicAndroid3P_automotiveProdRelease", "(ZLcom/amazon/mp3/prime/ContentCatalogStatus;)Lcom/amazon/music/media/playback/access/MediaAccessInfo;", "getAudioMediaAccessInfo", "Lcom/amazon/music/models/AssetQuality;", "assetQualities", "isTrackVideo", "Lcom/amazon/digitalmusicxp/clients/CloudQueueAppClient;", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "queueSeed", "Lcom/amazon/mp3/playback/PlaybackListener;", "playbackListener", "startFullScreen", "additionalMediaCollectionId", "isUserInitiated", "Lcom/amazon/digitalmusicxp/types/StartAt;", "startAt", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "playbackOptionsInput", "isLocalToOnlineCasting", "startOnlineQueueInteraction", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/clients/CloudQueueAppClient;Landroid/content/Context;Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Lcom/amazon/mp3/playback/PlaybackListener;Lcom/amazon/music/media/playback/ControlSource;Landroid/net/Uri;ZLcom/amazon/music/media/playback/MediaCollectionId;Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;ZLcom/amazon/digitalmusicxp/types/StartAt;Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;Ljava/lang/Boolean;)V", "queueSeedContextInput", "Lcom/amazon/digitalmusicxp/types/QueueSeed;", "isShuffle", "defaultPosition", "isFullScreen", "trackList", "primeTrackList", "Lcom/amazon/mp3/cloudqueue/NoOpCloudQueueClientHandler;", "noOpCloudQueueClientHandler", "startPlayback", "mContext", "startLocalQueueInteractionWithUri", "startLocalQueueInteraction", "", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "entry", "entityList", "mediaItemList", "isOnlineInteraction", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "loopMode", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "queueCapability", "metricsContext", "startCloudQueuePlayback", "Lcom/amazon/music/media/playback/config/SequencerPersistenceProvider;", "sequencerPersistenceProvider", "restoreLocalInteractionPlayback", "getCurrentPlayingQueueSequenceSlice", "originalSlice", "newIds", "copySequenceSlice", "tracks", "queueSequenceSlice", "addNext", "currentPlayingIndex", "addTrackToDataStore", "(Ljava/util/List;Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;ZLjava/lang/Integer;)V", "playNext", "showToast", "addTrackToCurrentQueue", "updateDevicePlaybackState", "fetchMediaItemLikeStates", "getMediaCollectionType", "playbackNoOpHandler", "startQueueInteractionWithPrimeTracks", "getTrackToAlbumAsinMap", "getSelectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease", "(Landroid/net/Uri;)Ljava/lang/String;", "getSelectionSourceIdFromUri", "getSelectionSourceMediaCollectionId$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/util/Map;Landroid/net/Uri;)Lcom/amazon/music/media/playback/MediaCollectionId;", "getSelectionSourceMediaCollectionId", "cirrusFactory", "getLocalInteractionMetricsContext", "metricsContextTuple", "getOnlineInteractionMetricsContext", "", "Lcom/amazon/music/ContentAccessType;", "cacheAllContentAccessTypes$delegate", "Lkotlin/Lazy;", "getCacheAllContentAccessTypes", "()Ljava/util/Set;", "cacheAllContentAccessTypes", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "PLAYBACK_WAIT_TIME", "J", "Landroidx/collection/LruCache;", "albumIdCache", "Landroidx/collection/LruCache;", "Lcom/amplifyframework/hub/SubscriptionToken;", "amplifyDBSyncQueriesReadySubscriptionToken", "Lcom/amplifyframework/hub/SubscriptionToken;", "", "cacheUdoPlaylistLuid", "Ljava/util/Map;", "defaultCapability", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "likesMaxBatchSize", "I", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/cloudqueue/CloudQueuePlaybackUtil$Companion$PlaybackContext;", "playbackObservable", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/digitalmusicxp/enums/SupportedFeatureEnum;", "supportedFeaturesEnumsOnline", "Ljava/util/List;", "<init>", "()V", "PlaybackContext", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {

        /* JADX INFO: compiled from: CloudQueuePlaybackUtil.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;

            static {
                int[] iArr = new int[QueueEntityIdTypeEnum.values().length];
                iArr[QueueEntityIdTypeEnum.CATALOG_TRACK_ID.ordinal()] = 1;
                iArr[QueueEntityIdTypeEnum.LIBRARY_TRACK_ID.ordinal()] = 2;
                iArr[QueueEntityIdTypeEnum.INTERLUDE_ID.ordinal()] = 3;
                iArr[QueueEntityIdTypeEnum.ADVERTISEMENT_ID.ordinal()] = 4;
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[PlaybackPageType.values().length];
                iArr2[PlaybackPageType.CAR_MODE_PRESET_LIST.ordinal()] = 1;
                iArr2[PlaybackPageType.CAR_MODE_NOW_PLAYING.ordinal()] = 2;
                iArr2[PlaybackPageType.CAR_MODE_BROWSE_SEARCH.ordinal()] = 3;
                $EnumSwitchMapping$1 = iArr2;
                int[] iArr3 = new int[PlayQueueInteractionPattern.values().length];
                iArr3[PlayQueueInteractionPattern.ONLINE.ordinal()] = 1;
                iArr3[PlayQueueInteractionPattern.LOCAL.ordinal()] = 2;
                $EnumSwitchMapping$2 = iArr3;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return CloudQueuePlaybackUtil.TAG;
        }

        private final Set<ContentAccessType> getCacheAllContentAccessTypes() {
            return (Set) CloudQueuePlaybackUtil.cacheAllContentAccessTypes$delegate.getValue();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ List convertQueueEntitiesToMediaItems$default(Companion companion, List list, MetricsContext metricsContext, ControlSource controlSource, Uri uri, String str, List list2, boolean z, int i, int i2, Object obj) {
            return companion.convertQueueEntitiesToMediaItems(list, metricsContext, controlSource, uri, str, (i2 & 32) != 0 ? null : list2, (i2 & 64) != 0 ? false : z, (i2 & 128) != 0 ? 0 : i);
        }

        public final List<MediaItem> convertQueueEntitiesToMediaItems(List<QueueEntityMetadata> entities, MetricsContext collectionMetricsContext, ControlSource controlSource, Uri contentUri, String queueTitle, List<? extends ContentCatalogStatus> contentCatalogStatusList, boolean isCustomerSelectedEntity, int startPosition) {
            MediaItem mediaItem;
            MediaItem mediaItemConvertSongEntityToMediaItem;
            Intrinsics.checkNotNullParameter(entities, "entities");
            Intrinsics.checkNotNullParameter(collectionMetricsContext, "collectionMetricsContext");
            Intrinsics.checkNotNullParameter(controlSource, "controlSource");
            Intrinsics.checkNotNullParameter(queueTitle, "queueTitle");
            int i = 0;
            if (!((contentCatalogStatusList == null || contentCatalogStatusList.isEmpty()) || contentCatalogStatusList.size() == entities.size())) {
                throw new IllegalArgumentException("contentCatalogStatusList should be either empty or has the same size as entities".toString());
            }
            boolean z = ControlSource.ALEXA.equals(controlSource) || ControlSource.GA.equals(controlSource);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
            for (Object obj : entities) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                QueueEntityMetadata queueEntityMetadata = (QueueEntityMetadata) obj;
                int i3 = WhenMappings.$EnumSwitchMapping$0[queueEntityMetadata.getIdentifierType().ordinal()];
                MediaItem mediaItem2 = null;
                if (i3 == 1) {
                    mediaItemConvertSongEntityToMediaItem = CloudQueuePlaybackUtil.INSTANCE.convertSongEntityToMediaItem(queueEntityMetadata, collectionMetricsContext, contentUri, queueTitle, contentCatalogStatusList == null ? null : (ContentCatalogStatus) CollectionsKt___CollectionsKt.getOrNull(contentCatalogStatusList, i));
                } else if (i3 == 2) {
                    mediaItemConvertSongEntityToMediaItem = CloudQueuePlaybackUtil.INSTANCE.convertGenericLibrarySongEntityToMediaItem(queueEntityMetadata, collectionMetricsContext, contentUri, queueTitle);
                } else {
                    Log.warning(CloudQueuePlaybackUtil.INSTANCE.getTAG(), Intrinsics.stringPlus("convertQueueEntitiesToMediaItems: unsupported type ", queueEntityMetadata.getIdentifierType()));
                    mediaItemConvertSongEntityToMediaItem = null;
                }
                if (mediaItemConvertSongEntityToMediaItem != null) {
                    mediaItemConvertSongEntityToMediaItem.setVoiceInitiated(z);
                    mediaItem2 = mediaItemConvertSongEntityToMediaItem;
                }
                arrayList.add(mediaItem2);
                i = i2;
            }
            if (isCustomerSelectedEntity && (mediaItem = (MediaItem) arrayList.get(startPosition)) != null) {
                mediaItem.setCustomerSelectedEntity(true);
            }
            return arrayList;
        }

        public static /* synthetic */ MediaItem convertSongEntityToMediaItem$default(Companion companion, QueueEntityMetadata queueEntityMetadata, MetricsContext metricsContext, Uri uri, String str, ContentCatalogStatus contentCatalogStatus, int i, Object obj) {
            if ((i & 16) != 0) {
                contentCatalogStatus = null;
            }
            return companion.convertSongEntityToMediaItem(queueEntityMetadata, metricsContext, uri, str, contentCatalogStatus);
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0099  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0164  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.amazon.music.media.playback.MediaItem convertSongEntityToMediaItem(com.amazon.digitalmusicxp.models.QueueEntityMetadata r36, com.amazon.mp3.cloudqueue.model.MetricsContext r37, android.net.Uri r38, java.lang.String r39, com.amazon.mp3.prime.ContentCatalogStatus r40) {
            /*
                Method dump skipped, instruction units count: 364
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.convertSongEntityToMediaItem(com.amazon.digitalmusicxp.models.QueueEntityMetadata, com.amazon.mp3.cloudqueue.model.MetricsContext, android.net.Uri, java.lang.String, com.amazon.mp3.prime.ContentCatalogStatus):com.amazon.music.media.playback.MediaItem");
        }

        private final QueueEntityCapability getDefaultCapability() {
            if (CloudQueuePlaybackUtil.defaultCapability == null) {
                CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribeOn(Schedulers.io()).blockingSubscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda8
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        CloudQueuePlaybackUtil.Companion.m702getDefaultCapability$lambda4((CloudQueueClient) obj);
                    }
                });
            }
            return CloudQueuePlaybackUtil.defaultCapability;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getDefaultCapability$lambda-4, reason: not valid java name */
        public static final void m702getDefaultCapability$lambda4(CloudQueueClient cloudQueueClient) {
            Intrinsics.checkNotNullParameter(cloudQueueClient, "cloudQueueClient");
            if (cloudQueueClient instanceof NoOpCloudQueueClient) {
                Log.error(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "CQ app client could not be initialized");
                return;
            }
            ServiceTierEnum serviceTier = CloudQueueDigitalXPUtil.INSTANCE.getServiceTier();
            if (serviceTier == null) {
                return;
            }
            Outcome<QueueEntityCapabilitySet> queueEntityCapabilitySet = cloudQueueClient.getQueueEntityCapabilitySet(serviceTier, QueueInteractionPatternEnum.LOCAL_QUEUE_CREATION_LOCAL_TRACK_CONSUMPTION.name());
            if (queueEntityCapabilitySet instanceof Outcome.Success) {
                QueueEntityCapability capabilities = ((QueueEntityCapabilitySet) ((Outcome.Success) queueEntityCapabilitySet).getValue()).getCapabilities();
                Companion companion = CloudQueuePlaybackUtil.INSTANCE;
                CloudQueuePlaybackUtil.defaultCapability = new QueueEntityCapability(capabilities.getNext(), capabilities.getPrevious(), capabilities.getScrubForward(), capabilities.getScrubBackward(), new ActionStrategy(ActionStrategyTypeEnum.SINGLE, new SingleActionStrategy(SingleActionStrategyActionTypeEnum.BOOLEAN, null, Boolean.FALSE, 2, null), null, 4, null), capabilities.getDisplayable(), capabilities.getLoopOne());
                return;
            }
            Log.error(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "no default value for QueueEntityCapability");
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @androidx.annotation.WorkerThread
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.amazon.music.views.library.models.PageGridViewModel convertQueueEntitiesIntoViewModel(java.util.List<com.amazon.digitalmusicxp.models.QueueEntityMetadata> r21, int r22, int r23, boolean r24) {
            /*
                Method dump skipped, instruction units count: 299
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.convertQueueEntitiesIntoViewModel(java.util.List, int, int, boolean):com.amazon.music.views.library.models.PageGridViewModel");
        }

        /* JADX WARN: Removed duplicated region for block: B:104:0x0229  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00a0  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00c4  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00e5  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00e8  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00f7  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0103  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0136  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x013e  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0149  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x014c  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0154  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x0157  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0167  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x016a  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0172  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0175  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x017b  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x017e  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x018a  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x018d  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x0195  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0198  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x01ef  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final com.amazon.music.views.library.models.base.BaseViewModel convertQueueEntityToBaseModel(com.amazon.digitalmusicxp.models.QueueEntityMetadata r55, com.amazon.mp3.prime.CTAPrimeCache r56, com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory r57, int r58, java.util.HashMap<java.lang.String, android.net.Uri> r59, android.content.Context r60, boolean r61, boolean r62) {
            /*
                Method dump skipped, instruction units count: 742
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.convertQueueEntityToBaseModel(com.amazon.digitalmusicxp.models.QueueEntityMetadata, com.amazon.mp3.prime.CTAPrimeCache, com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory, int, java.util.HashMap, android.content.Context, boolean, boolean):com.amazon.music.views.library.models.base.BaseViewModel");
        }

        private final Uri retrieveTrackUriFromEntityId(String entityIdentifier, QueueEntityIdTypeEnum entityType) {
            ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
            Uri trackUri = contentPlaybackUtils.getTrackUri(entityIdentifier);
            if (!Intrinsics.areEqual(trackUri, contentPlaybackUtils.getDEFAULT_URI())) {
                return trackUri;
            }
            if (entityType == QueueEntityIdTypeEnum.CATALOG_TRACK_ID) {
                Uri primeAdditionalTrackContentUri = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus-local", "primeplaylist", entityIdentifier);
                Intrinsics.checkNotNullExpressionValue(primeAdditionalTrackContentUri, "getPrimeAdditionalTrackC…AYLIST, entityIdentifier)");
                return primeAdditionalTrackContentUri;
            }
            if (entityType != QueueEntityIdTypeEnum.LIBRARY_TRACK_ID) {
                return trackUri;
            }
            Uri contentUri = MediaProvider.Tracks.getContentUri("cirrus", entityIdentifier);
            Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(CirrusMedi…CIRRUS, entityIdentifier)");
            return contentUri;
        }

        @WorkerThread
        public final QueueEntityMetadata convertTrackToSingleQueueEntity(MusicTrack track, String trackId, String queueId, String customerId, CTAPrimeCache primeCache) {
            Intrinsics.checkNotNullParameter(track, "track");
            Intrinsics.checkNotNullParameter(trackId, "trackId");
            Intrinsics.checkNotNullParameter(queueId, "queueId");
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            Intrinsics.checkNotNullParameter(primeCache, "primeCache");
            QueueEntityMetadata queueEntityMetadata = (QueueEntityMetadata) CollectionsKt___CollectionsKt.first((List) convertTrackToQueueEntity(CollectionsKt__CollectionsJVMKt.listOf(track), queueId, customerId, primeCache));
            return queueEntityMetadata.copy((4194299 & 1) != 0 ? queueEntityMetadata.customerId : null, (4194299 & 2) != 0 ? queueEntityMetadata.queueId : null, (4194299 & 4) != 0 ? queueEntityMetadata.id : trackId, (4194299 & 8) != 0 ? queueEntityMetadata.identifier : null, (4194299 & 16) != 0 ? queueEntityMetadata.identifierType : null, (4194299 & 32) != 0 ? queueEntityMetadata.queueEntitySubtype : null, (4194299 & 64) != 0 ? queueEntityMetadata.durationInMs : null, (4194299 & 128) != 0 ? queueEntityMetadata.title : null, (4194299 & 256) != 0 ? queueEntityMetadata.subTitle : null, (4194299 & 512) != 0 ? queueEntityMetadata.imageMetadata : null, (4194299 & 1024) != 0 ? queueEntityMetadata.parentalControls : null, (4194299 & 2048) != 0 ? queueEntityMetadata.album : null, (4194299 & 4096) != 0 ? queueEntityMetadata.concatenatedArtistName : null, (4194299 & 8192) != 0 ? queueEntityMetadata.artists : null, (4194299 & 16384) != 0 ? queueEntityMetadata.hasLyrics : null, (4194299 & 32768) != 0 ? queueEntityMetadata.cuePoints : null, (4194299 & 65536) != 0 ? queueEntityMetadata.playbackAssetSource : null, (4194299 & 131072) != 0 ? queueEntityMetadata.rating : null, (4194299 & 262144) != 0 ? queueEntityMetadata.availableContentEncodings : null, (4194299 & 524288) != 0 ? queueEntityMetadata.playableAsin : null, (4194299 & 1048576) != 0 ? queueEntityMetadata.createdAt : 0L, (4194299 & 2097152) != 0 ? queueEntityMetadata.updatedAt : 0L);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00dc  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00fe  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x013f  */
        @androidx.annotation.WorkerThread
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<com.amazon.digitalmusicxp.models.QueueEntityMetadata> convertTrackToQueueEntity(java.util.List<? extends com.amazon.mp3.library.item.MusicTrack> r35, java.lang.String r36, java.lang.String r37, com.amazon.mp3.prime.CTAPrimeCache r38) {
            /*
                Method dump skipped, instruction units count: 421
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.convertTrackToQueueEntity(java.util.List, java.lang.String, java.lang.String, com.amazon.mp3.prime.CTAPrimeCache):java.util.List");
        }

        public final List<MediaItem> convertGenericQueueEntitiesToMediaItems(List<GenericQueueEntity> entities, Queue queue, MetricsContext collectionMetricsContext, ControlSource controlSource, Uri contentUri) {
            MediaItem mediaItemConvertGenericSongEntityToMediaItem;
            Intrinsics.checkNotNullParameter(entities, "entities");
            Intrinsics.checkNotNullParameter(queue, "queue");
            Intrinsics.checkNotNullParameter(collectionMetricsContext, "collectionMetricsContext");
            Intrinsics.checkNotNullParameter(controlSource, "controlSource");
            PlaybackPageType playbackPageType = collectionMetricsContext.getPageType();
            boolean z = ControlSource.ALEXA.equals(controlSource) || ControlSource.GA.equals(controlSource);
            ArrayList arrayList = new ArrayList();
            for (Object obj : entities) {
                GenericQueueEntity genericQueueEntity = (GenericQueueEntity) obj;
                QueueEntityIdTypeEnum identifierType = genericQueueEntity.getIdentifierType();
                QueueEntityIdTypeEnum queueEntityIdTypeEnum = QueueEntityIdTypeEnum.INTERLUDE_ID;
                if ((identifierType == queueEntityIdTypeEnum && genericQueueEntity.getPlaybackAssetSource() != null) || genericQueueEntity.getIdentifierType() != queueEntityIdTypeEnum) {
                    arrayList.add(obj);
                }
            }
            ArrayList<GenericQueueEntity> arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                GenericQueueEntity genericQueueEntity2 = (GenericQueueEntity) obj2;
                if (!(genericQueueEntity2.getIdentifierType() == QueueEntityIdTypeEnum.LIBRARY_TRACK_ID && genericQueueEntity2.getPlayableAsin() == null)) {
                    arrayList2.add(obj2);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            for (GenericQueueEntity genericQueueEntity3 : arrayList2) {
                int i = WhenMappings.$EnumSwitchMapping$0[genericQueueEntity3.getIdentifierType().ordinal()];
                MediaItem mediaItem = null;
                if (i == 1) {
                    Companion companion = CloudQueuePlaybackUtil.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(playbackPageType, "playbackPageType");
                    mediaItemConvertGenericSongEntityToMediaItem = companion.convertGenericSongEntityToMediaItem(genericQueueEntity3, queue, playbackPageType, contentUri);
                } else if (i == 2) {
                    Companion companion2 = CloudQueuePlaybackUtil.INSTANCE;
                    QueueEntityMetadata queueEntityMetadataConvertGenericQueueEntityToQueueEntityMetadata = companion2.convertGenericQueueEntityToQueueEntityMetadata(genericQueueEntity3, queue.getId(), queue.getCustomerId());
                    String title = queue.getTitle();
                    if (title == null) {
                        title = "";
                    }
                    mediaItemConvertGenericSongEntityToMediaItem = companion2.convertGenericLibrarySongEntityToMediaItem(queueEntityMetadataConvertGenericQueueEntityToQueueEntityMetadata, collectionMetricsContext, contentUri, title);
                } else if (i == 3) {
                    Companion companion3 = CloudQueuePlaybackUtil.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(playbackPageType, "playbackPageType");
                    mediaItemConvertGenericSongEntityToMediaItem = companion3.convertGenericInterludeEntityToMediaItem(genericQueueEntity3, playbackPageType, contentUri);
                } else if (i == 4) {
                    Companion companion4 = CloudQueuePlaybackUtil.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(playbackPageType, "playbackPageType");
                    mediaItemConvertGenericSongEntityToMediaItem = companion4.convertGenericAdvertisementEntityToMediaItem(genericQueueEntity3, playbackPageType, contentUri);
                } else {
                    Log.warning(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "GenericQueueEntity has unsupported identifer type: " + genericQueueEntity3.getIdentifierType() + ", unable to convert to MediaItem");
                    mediaItemConvertGenericSongEntityToMediaItem = null;
                }
                if (mediaItemConvertGenericSongEntityToMediaItem != null) {
                    mediaItemConvertGenericSongEntityToMediaItem.setVoiceInitiated(z);
                    mediaItem = mediaItemConvertGenericSongEntityToMediaItem;
                }
                arrayList3.add(mediaItem);
            }
            return arrayList3;
        }

        private final InterludeMediaItem convertGenericInterludeEntityToMediaItem(GenericQueueEntity interlude, PlaybackPageType playbackPageType, Uri contentUri) {
            String value;
            Map<String, String> mapConvertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease = convertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease(interlude.getMetricsContext());
            PlaybackAssetSource playbackAssetSource = interlude.getPlaybackAssetSource();
            String str = "";
            if (playbackAssetSource != null && (value = playbackAssetSource.getValue()) != null) {
                str = value;
            }
            String title = interlude.getTitle();
            String subTitle = interlude.getSubTitle();
            Float durationInMs = interlude.getDurationInMs();
            long jFloatValue = durationInMs == null ? 0L : (long) durationInMs.floatValue();
            UrlMediaLinkProvider interludeMediaLinkProvider = getInterludeMediaLinkProvider();
            MediaCollectionInfo interludeMediaCollectionInfo = getInterludeMediaCollectionInfo(interlude, contentUri, mapConvertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease);
            FixedSizeImage fixedSizeImage = interlude.getImageMetadata().getFixedSizeImage();
            return new InterludeMediaItem(str, title, subTitle, jFloatValue, interludeMediaLinkProvider, interludeMediaCollectionInfo, playbackPageType, fixedSizeImage == null ? null : fixedSizeImage.getImageUrl(), mapConvertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease, interlude.getEntityReferenceId(), interlude.getCapabilities());
        }

        private final AdMediaItem convertGenericAdvertisementEntityToMediaItem(GenericQueueEntity ad, PlaybackPageType playbackPageType, Uri contentUri) {
            String value;
            String contentBlockRef;
            Uri uri;
            String str;
            GenericQueueEntity genericQueueEntity;
            PlaybackAssetSource playbackAssetSource = ad.getPlaybackAssetSource();
            String str2 = (playbackAssetSource == null || (value = playbackAssetSource.getValue()) == null) ? "" : value;
            String title = ad.getTitle();
            String concatenatedArtistName = ad.getConcatenatedArtistName();
            Float durationInMs = ad.getDurationInMs();
            long jFloatValue = durationInMs == null ? 0L : (long) durationInMs.floatValue();
            FixedSizeImage fixedSizeImage = ad.getImageMetadata().getFixedSizeImage();
            String imageUrl = fixedSizeImage == null ? null : fixedSizeImage.getImageUrl();
            String adChoiceClickUrl = ad.getImageMetadata().getAdChoiceClickUrl();
            ClientMetricsInfo clientMetricsInfo = ad.getClientMetricsInfo();
            if (clientMetricsInfo == null || (contentBlockRef = clientMetricsInfo.getContentBlockRef()) == null) {
                genericQueueEntity = ad;
                str = "";
                uri = contentUri;
            } else {
                uri = contentUri;
                str = contentBlockRef;
                genericQueueEntity = ad;
            }
            return new AdMediaItem(str2, title, concatenatedArtistName, jFloatValue, imageUrl, adChoiceClickUrl, str, getAdvertisementMediaCollectionInfo(genericQueueEntity, uri), Playback.getInstance().getPlaybackConfig().getMediaLinkProvider(), playbackPageType, convertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease(ad.getMetricsContext()), ad.getCapabilities());
        }

        private final MediaCollectionInfo getAdvertisementMediaCollectionInfo(GenericQueueEntity ad, Uri contentUri) {
            return new MediaCollectionInfo(ad.getTitle(), ad.getSubTitle(), new MediaCollectionId(MediaCollectionId.Type.ADVERTISEMENT, ad.getIdentifier()), getMediaCollectionType$default(this, contentUri, null, 2, null), new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withExplicit(true).withCastable(false).withContentAccessType(ContentAccessType.NIGHTWING).build(), new MediaCollectionId[0]);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00b8  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00da  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00dd  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00ee  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00f1  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final com.amazon.music.media.playback.MediaItem convertGenericSongEntityToMediaItem(com.amazon.digitalmusicxp.types.GenericQueueEntity r36, com.amazon.digitalmusicxp.models.Queue r37, com.amazon.music.metrics.mts.event.types.PlaybackPageType r38, android.net.Uri r39) {
            /*
                Method dump skipped, instruction units count: 295
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.convertGenericSongEntityToMediaItem(com.amazon.digitalmusicxp.types.GenericQueueEntity, com.amazon.digitalmusicxp.models.Queue, com.amazon.music.metrics.mts.event.types.PlaybackPageType, android.net.Uri):com.amazon.music.media.playback.MediaItem");
        }

        private final MediaItem convertGenericLibrarySongEntityToMediaItem(QueueEntityMetadata song, MetricsContext collectionMetricsContext, Uri contentUri, String queueTitle) {
            PlaybackAssetSource playbackAssetSource = song.getPlaybackAssetSource();
            PlaybackPageType playbackPageType = collectionMetricsContext.getPageType();
            if (playbackAssetSource != null && playbackAssetSource.getType() == PlaybackAssetSourceTypeEnum.PID) {
                Intrinsics.checkNotNullExpressionValue(playbackPageType, "playbackPageType");
                return new CloudQueueLibraryTrackMediaItem(song, playbackPageType, getVideoMediaCollectionInfo$default(this, song, contentUri, collectionMetricsContext, null, 8, null), collectionMetricsContext);
            }
            Intrinsics.checkNotNullExpressionValue(playbackPageType, "playbackPageType");
            return new CloudQueueLibraryTrackMediaItem(song, playbackPageType, getAudioMediaCollectionInfo(song, contentUri, queueTitle, collectionMetricsContext), collectionMetricsContext);
        }

        public final QueueEntityMetadata convertGenericQueueEntityToQueueEntityMetadata(GenericQueueEntity song, String queueId, String customerId) {
            Intrinsics.checkNotNullParameter(song, "song");
            Intrinsics.checkNotNullParameter(queueId, "queueId");
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            return new QueueEntityMetadata(customerId, queueId, song.getIdentifierType() == QueueEntityIdTypeEnum.LIBRARY_TRACK_ID ? song.getIdentifier() : song.getEntityReferenceId(), song.getIdentifier(), song.getIdentifierType(), song.getQueueEntitySubtype(), song.getDurationInMs(), song.getTitle(), song.getSubTitle(), song.getImageMetadata(), new ParentalControls(!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()), song.getAlbum(), null, song.getArtists(), song.getHasLyrics(), song.getCuePoints(), song.getPlaybackAssetSource(), song.getRating(), song.getAvailableContentEncodings(), song.getPlayableAsin(), System.currentTimeMillis(), System.currentTimeMillis(), 4096, null);
        }

        public final Map<String, String> convertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease(List<MetricsContextTuple> tuples) {
            if (tuples == null) {
                Map<String, String> mapEmptyMap = Collections.emptyMap();
                Intrinsics.checkNotNullExpressionValue(mapEmptyMap, "emptyMap()");
                return mapEmptyMap;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(tuples, 10)), 16));
            for (MetricsContextTuple metricsContextTuple : tuples) {
                linkedHashMap.put(metricsContextTuple.getKey(), metricsContextTuple.getValue());
            }
            return linkedHashMap;
        }

        static /* synthetic */ MediaCollectionInfo getVideoMediaCollectionInfo$default(Companion companion, QueueEntityMetadata queueEntityMetadata, Uri uri, MetricsContext metricsContext, String str, int i, Object obj) {
            if ((i & 8) != 0) {
                str = null;
            }
            return companion.getVideoMediaCollectionInfo(queueEntityMetadata, uri, metricsContext, str);
        }

        private final MediaCollectionInfo getVideoMediaCollectionInfo(QueueEntityMetadata video, Uri contentUri, MetricsContext collectionMetricsContext, String queueTitle) {
            MediaCollectionType mediaCollectionType = getMediaCollectionType(contentUri, collectionMetricsContext);
            MediaCollectionId mediaCollectionId = getMediaCollectionId(mediaCollectionType, video, contentUri);
            if (queueTitle == null || Intrinsics.areEqual(queueTitle, "")) {
                queueTitle = video.getTitle();
            }
            return new MediaCollectionInfo(queueTitle, video.getSubTitle(), mediaCollectionId, mediaCollectionType, getVideoMediaAccessInfo(), new MediaCollectionId[0]);
        }

        private final MediaCollectionInfo getAudioMediaCollectionInfo(QueueEntityMetadata song, Uri contentUri, String queueTitle, MetricsContext collectionMetricsContext) {
            MediaCollectionType mediaCollectionType = getMediaCollectionType(contentUri, collectionMetricsContext);
            MediaCollectionId mediaCollectionId = getMediaCollectionId(mediaCollectionType, song, contentUri);
            if (Intrinsics.areEqual(queueTitle, "")) {
                TrackAlbum album = song.getAlbum();
                queueTitle = album == null ? null : album.getName();
            }
            return new MediaCollectionInfo(queueTitle, null, mediaCollectionId, mediaCollectionType, MediaAccessInfo.fullAccess(true, true), new MediaCollectionId[0]);
        }

        private final MediaCollectionId getMediaCollectionId(MediaCollectionType mediaCollectionType, QueueEntityMetadata track, Uri contentUri) {
            TrackArtist trackArtist;
            MediaCollectionId mediaCollectionId;
            String strValueOf;
            if (Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.USER_PLAYLIST)) {
                if (contentUri == null) {
                    return null;
                }
                MediaCollectionId.Type type = MediaCollectionId.Type.SELECTION_SOURCE_ID;
                Map map = CloudQueuePlaybackUtil.cacheUdoPlaylistLuid;
                Object playlistLuid = map.get(contentUri);
                if (playlistLuid == null) {
                    playlistLuid = MediaProvider.Playlists.getPlaylistLuid(contentUri);
                    map.put(contentUri, playlistLuid);
                }
                return new MediaCollectionId(type, (String) playlistLuid);
            }
            if (Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.SHARED_PLAYLIST)) {
                return new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, MediaProvider.SharedUserPlaylists.getPlaylistKey(contentUri));
            }
            if (Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.AUTO_PLAYLIST)) {
                long playlistId = MediaProvider.SmartPlaylists.getPlaylistId(contentUri);
                if (playlistId == 0) {
                    strValueOf = "RECENTLY_PURCHASED";
                } else if (playlistId == 1) {
                    strValueOf = "RECENTLY_ADDED";
                } else if (playlistId == 2) {
                    strValueOf = "RECENTLY_DOWNLOADED";
                } else {
                    strValueOf = playlistId == 3 ? "RECENTLY_PLAYED_SONGS" : String.valueOf(playlistId);
                }
                return new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, strValueOf);
            }
            if (Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.PRIME_PLAYLIST) ? true : Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.UNLIMITED_PLAYLIST) ? true : Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.GOLDEN_PLAYLIST)) {
                return new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, MediaProvider.PrimePlaylists.getPlaylistAsin(contentUri));
            }
            if (Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.ALBUM)) {
                TrackAlbum album = track.getAlbum();
                String asin = album == null ? null : album.getAsin();
                if (asin == null) {
                    return null;
                }
                mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, asin);
            } else if (Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.ARTIST)) {
                List<TrackArtist> artists = track.getArtists();
                String asin2 = (artists == null || (trackArtist = (TrackArtist) CollectionsKt___CollectionsKt.firstOrNull((List) artists)) == null) ? null : trackArtist.getAsin();
                if (asin2 == null) {
                    return null;
                }
                mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, asin2);
            } else {
                if (Intrinsics.areEqual(mediaCollectionType, MediaCollectionType.SONGS)) {
                    if (MediaProvider.PrimeBrowseTracksCollection.FirstTrack.isCollectionFirstTrack(contentUri)) {
                        return new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, MediaProvider.PrimeBrowseTracksCollection.FirstTrack.getFirstTrackAsin(contentUri));
                    }
                    return new MediaCollectionId(MediaCollectionId.Type.ASIN, track.getIdentifier());
                }
                Log.warning(getTAG(), "unsupported MediaCollectionType=" + mediaCollectionType + " uri=" + contentUri + " track=" + track);
                return null;
            }
            return mediaCollectionId;
        }

        private final MediaAccessInfo getVideoMediaAccessInfo() {
            MediaAccessInfo mediaAccessInfoBuild = new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withExplicit(true).withCastable(true).withContentAccessType(ContentAccessType.HAWKFIRE).withContentAccessType(ContentAccessType.BOP).build();
            Intrinsics.checkNotNullExpressionValue(mediaAccessInfoBuild, "Builder()\n              …                 .build()");
            return mediaAccessInfoBuild;
        }

        public static /* synthetic */ MediaAccessInfo getAudioMediaAccessInfo$DigitalMusicAndroid3P_automotiveProdRelease$default(Companion companion, boolean z, ContentCatalogStatus contentCatalogStatus, int i, Object obj) {
            if ((i & 2) != 0) {
                contentCatalogStatus = null;
            }
            return companion.getAudioMediaAccessInfo$DigitalMusicAndroid3P_automotiveProdRelease(z, contentCatalogStatus);
        }

        public final MediaAccessInfo getAudioMediaAccessInfo$DigitalMusicAndroid3P_automotiveProdRelease(boolean isExplicit, ContentCatalogStatus status) {
            if (status != null && status != ContentCatalogStatus.UNKNOWN) {
                MediaAccessInfo mediaAccessInfoForEligibility = MediaAccessInfo.forEligibility(isExplicit, true, false, true, status.isOwned(), status.isPrime(), status.isSonicRush(), status.isHawkfire(), status.isBOP());
                Intrinsics.checkNotNullExpressionValue(mediaAccessInfoForEligibility, "forEligibility(isExplici…isHawkfire, status.isBOP)");
                return mediaAccessInfoForEligibility;
            }
            MediaAccessInfo mediaAccessInfoBuild = new MediaAccessInfo.Builder().withAvailableOffline(false).withAllowedOnline(true).withCastable(true).withExplicit(isExplicit).withContentAccessType(ContentAccessType.NIGHTWING).withContentAccessType(ContentAccessType.PRIME).build();
            Intrinsics.checkNotNullExpressionValue(mediaAccessInfoBuild, "Builder()\n              …                 .build()");
            return mediaAccessInfoBuild;
        }

        private final MediaCollectionInfo getInterludeMediaCollectionInfo(GenericQueueEntity interlude, Uri contentUri, Map<String, String> metricsContextMap) {
            return new MediaCollectionInfo(interlude.getTitle(), interlude.getSubTitle(), new MediaCollectionId(MediaCollectionId.Type.CUSTOM, interlude.getIdentifier()), getMediaCollectionType$default(this, contentUri, null, 2, null), new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withExplicit(true).withCastable(false).withContentAccessType(ContentAccessType.NIGHTWING).build(), getSelectionSourceMediaCollectionId$DigitalMusicAndroid3P_automotiveProdRelease(metricsContextMap, contentUri));
        }

        private final MediaCollectionInfo getVideoMediaCollectionInfo(GenericQueueEntity video, Uri contentUri, Map<String, String> metricsContextMap) {
            return new MediaCollectionInfo(video.getTitle(), video.getSubTitle(), new MediaCollectionId(MediaCollectionId.Type.ASIN, video.getIdentifier()), getMediaCollectionType$default(this, contentUri, null, 2, null), getVideoMediaAccessInfo(), getSelectionSourceMediaCollectionId$DigitalMusicAndroid3P_automotiveProdRelease(metricsContextMap, contentUri));
        }

        private final MediaCollectionInfo getAudioMediaCollectionInfo(GenericQueueEntity song, Uri contentUri, String queueTitle, Map<String, String> metricsContextMap) {
            if (queueTitle == null) {
                queueTitle = song.getTitle();
            }
            return new MediaCollectionInfo(queueTitle, song.getSubTitle(), new MediaCollectionId(MediaCollectionId.Type.CUSTOM, song.getIdentifier()), getMediaCollectionType$default(this, contentUri, null, 2, null), new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withExplicit(true).withCastable(false).withContentAccessType(ContentAccessType.NIGHTWING).build(), getSelectionSourceMediaCollectionId$DigitalMusicAndroid3P_automotiveProdRelease(metricsContextMap, contentUri));
        }

        public final boolean isTrackVideo(List<AssetQuality> assetQualities) {
            if (assetQualities != null) {
                if (!(VideoUtil.INSTANCE.getDefaultOrFirstAvailablePID(assetQualities).length() == 0)) {
                    return true;
                }
            }
            return false;
        }

        public static /* synthetic */ void startOnlineQueueInteraction$default(Companion companion, String str, CloudQueueAppClient cloudQueueAppClient, Context context, QueueSeedContextInput queueSeedContextInput, PlaybackPageType playbackPageType, PlaybackListener playbackListener, ControlSource controlSource, Uri uri, boolean z, MediaCollectionId mediaCollectionId, UpsellInformation upsellInformation, boolean z2, StartAt startAt, PlaybackOptionsInput playbackOptionsInput, Boolean bool, int i, Object obj) {
            companion.startOnlineQueueInteraction((i & 1) != 0 ? null : str, cloudQueueAppClient, context, queueSeedContextInput, playbackPageType, (i & 32) != 0 ? null : playbackListener, controlSource, uri, z, mediaCollectionId, upsellInformation, z2, (i & 4096) != 0 ? null : startAt, (i & 8192) != 0 ? null : playbackOptionsInput, (i & 16384) != 0 ? null : bool);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:109:0x03c3 A[LOOP:1: B:107:0x03bd->B:109:0x03c3, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:112:0x03ed  */
        /* JADX WARN: Removed duplicated region for block: B:113:0x03ef  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x03f8  */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0056  */
        /* JADX WARN: Type inference failed for: r1v23, types: [T, com.amazon.digitalmusicxp.models.QueueSequenceSlice] */
        @kotlin.jvm.JvmStatic
        @androidx.annotation.WorkerThread
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void startOnlineQueueInteraction(java.lang.String r57, com.amazon.digitalmusicxp.clients.CloudQueueAppClient r58, android.content.Context r59, com.amazon.digitalmusicxp.inputs.QueueSeedContextInput r60, com.amazon.music.metrics.mts.event.types.PlaybackPageType r61, com.amazon.mp3.playback.PlaybackListener r62, com.amazon.music.media.playback.ControlSource r63, android.net.Uri r64, boolean r65, com.amazon.music.media.playback.MediaCollectionId r66, com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation r67, boolean r68, com.amazon.digitalmusicxp.types.StartAt r69, com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput r70, java.lang.Boolean r71) {
            /*
                Method dump skipped, instruction units count: 1213
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.startOnlineQueueInteraction(java.lang.String, com.amazon.digitalmusicxp.clients.CloudQueueAppClient, android.content.Context, com.amazon.digitalmusicxp.inputs.QueueSeedContextInput, com.amazon.music.metrics.mts.event.types.PlaybackPageType, com.amazon.mp3.playback.PlaybackListener, com.amazon.music.media.playback.ControlSource, android.net.Uri, boolean, com.amazon.music.media.playback.MediaCollectionId, com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation, boolean, com.amazon.digitalmusicxp.types.StartAt, com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput, java.lang.Boolean):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: startOnlineQueueInteraction$lambda-28, reason: not valid java name */
        public static final void m716startOnlineQueueInteraction$lambda28(List list, AccountCredentialStorage accountCredentialStorage, Queue queue, Ref.ObjectRef queueSequenceSlice) {
            Intrinsics.checkNotNullParameter(queue, "$queue");
            Intrinsics.checkNotNullParameter(queueSequenceSlice, "$queueSequenceSlice");
            GenericQueueEntity genericQueueEntity = (GenericQueueEntity) CollectionsKt___CollectionsKt.firstOrNull(list);
            DeviceCurrentPlaybackState deviceCurrentPlaybackState = genericQueueEntity == null ? null : new DeviceCurrentPlaybackState(genericQueueEntity.getEntityReferenceId(), genericQueueEntity.getIdentifierType(), 0.0f, System.currentTimeMillis(), genericQueueEntity.getTitle(), genericQueueEntity.getSubTitle(), genericQueueEntity.getImageMetadata());
            String deviceId = accountCredentialStorage.getDeviceId();
            Intrinsics.checkNotNullExpressionValue(deviceId, "storage.deviceId");
            String deviceType = accountCredentialStorage.getDeviceType();
            Intrinsics.checkNotNullExpressionValue(deviceType, "storage.deviceType");
            final DevicePlaybackState devicePlaybackState = new DevicePlaybackState(deviceId, deviceType, queue.getId(), queue.getCustomerId(), UUID.randomUUID().toString(), ((QueueSequenceSlice) queueSequenceSlice.element).getSequenceName(), ((QueueSequenceSlice) queueSequenceSlice.element).getSequenceVersion(), deviceCurrentPlaybackState, LoopModeEnum.LOOP_OFF, CloudQueueDigitalXPUtil.INSTANCE.getShuffleMode(Intrinsics.areEqual(((QueueSequenceSlice) queueSequenceSlice.element).getSequenceName(), QueueSequenceSliceName.SHUFFLE.name())), null, null, DevicePlaybackStateEnum.PLAYING, null, System.currentTimeMillis(), System.currentTimeMillis(), 11264, null);
            CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().doOnNext(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda12
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m717startOnlineQueueInteraction$lambda28$lambda27(devicePlaybackState, (CloudQueueClient) obj);
                }
            }).subscribe();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: startOnlineQueueInteraction$lambda-28$lambda-27, reason: not valid java name */
        public static final void m717startOnlineQueueInteraction$lambda28$lambda27(DevicePlaybackState devicePlaybackState, CloudQueueClient cloudQueueClient) {
            Intrinsics.checkNotNullParameter(devicePlaybackState, "$devicePlaybackState");
            cloudQueueClient.changeDeviceState(devicePlaybackState);
        }

        private final void populatePlayQueueOnDBSync(final ControlSource controlSource, final String queueId, final PlaybackPageType playbackPageType) {
            SubscriptionToken subscriptionToken = CloudQueuePlaybackUtil.amplifyDBSyncQueriesReadySubscriptionToken;
            if (subscriptionToken != null) {
                Amplify.Hub.unsubscribe(subscriptionToken);
            }
            restartAmplifyDB().subscribe(new Action() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda10
                @Override // io.reactivex.functions.Action
                public final void run() {
                    CloudQueuePlaybackUtil.Companion.m703populatePlayQueueOnDBSync$lambda34(controlSource, queueId, playbackPageType);
                }
            }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda11
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m707populatePlayQueueOnDBSync$lambda35((Throwable) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: populatePlayQueueOnDBSync$lambda-34, reason: not valid java name */
        public static final void m703populatePlayQueueOnDBSync$lambda34(final ControlSource controlSource, final String queueId, final PlaybackPageType playbackPageType) {
            Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
            Intrinsics.checkNotNullParameter(queueId, "$queueId");
            Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            CloudQueuePlaybackUtil.amplifyDBSyncQueriesReadySubscriptionToken = Amplify.Hub.subscribe(HubChannel.DATASTORE, new HubEventFilter() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda16
                @Override // com.amplifyframework.hub.HubEventFilter
                public final boolean filter(HubEvent hubEvent) {
                    return CloudQueuePlaybackUtil.Companion.m704populatePlayQueueOnDBSync$lambda34$lambda31(hubEvent);
                }
            }, new HubSubscriber() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda17
                @Override // com.amplifyframework.hub.HubSubscriber
                public final void onEvent(HubEvent hubEvent) {
                    CloudQueuePlaybackUtil.Companion.m705populatePlayQueueOnDBSync$lambda34$lambda33(controlSource, queueId, playbackPageType, hubEvent);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: populatePlayQueueOnDBSync$lambda-34$lambda-31, reason: not valid java name */
        public static final boolean m704populatePlayQueueOnDBSync$lambda34$lambda31(HubEvent it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Intrinsics.areEqual(it.getName(), DataStoreChannelEventName.SYNC_QUERIES_READY.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: populatePlayQueueOnDBSync$lambda-34$lambda-33, reason: not valid java name */
        public static final void m705populatePlayQueueOnDBSync$lambda34$lambda33(final ControlSource controlSource, final String queueId, final PlaybackPageType playbackPageType, HubEvent it) {
            Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
            Intrinsics.checkNotNullParameter(queueId, "$queueId");
            Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
            Intrinsics.checkNotNullParameter(it, "it");
            CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda27
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m706populatePlayQueueOnDBSync$lambda34$lambda33$lambda32(controlSource, queueId, playbackPageType, (CloudQueueClient) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: populatePlayQueueOnDBSync$lambda-34$lambda-33$lambda-32, reason: not valid java name */
        public static final void m706populatePlayQueueOnDBSync$lambda34$lambda33$lambda32(ControlSource controlSource, String queueId, PlaybackPageType playbackPageType, CloudQueueClient client) {
            Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
            Intrinsics.checkNotNullParameter(queueId, "$queueId");
            Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(client, "client");
            populatePlayQueueFromAmplifyDB$default(companion, client, null, controlSource, queueId, false, playbackPageType, 18, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: populatePlayQueueOnDBSync$lambda-35, reason: not valid java name */
        public static final void m707populatePlayQueueOnDBSync$lambda35(Throwable th) {
            String tag = CloudQueuePlaybackUtil.INSTANCE.getTAG();
            Throwable cause = th.getCause();
            Log.error(tag, Intrinsics.stringPlus("Unable to restart the database : ", cause == null ? null : cause.getMessage()));
        }

        private final EntityReference toEntityReference(GenericQueueEntity genericQueueEntity, String str, String str2) {
            String entityReferenceId = genericQueueEntity.getEntityReferenceId();
            String identifier = genericQueueEntity.getIdentifier();
            QueueEntityIdTypeEnum identifierType = genericQueueEntity.getIdentifierType();
            if (str2 == null) {
                str2 = "";
            }
            return new EntityReference(entityReferenceId, identifier, identifierType, str, str2, null);
        }

        private final DeviceContext getCastingTargetDeviceContext() {
            CastingDevice castingDevice = CastingSessionManager.getInstance().getCastingDevice();
            if (castingDevice == null) {
                return null;
            }
            String targetId = castingDevice.getTargetId();
            Intrinsics.checkNotNullExpressionValue(targetId, "castingDevice.targetId");
            String deviceTypeId = castingDevice.getDeviceTypeId();
            Intrinsics.checkNotNullExpressionValue(deviceTypeId, "castingDevice.deviceTypeId");
            return new DeviceContext(targetId, deviceTypeId);
        }

        private final Completable restartAmplifyDB() {
            Completable completableCreate = Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda23
                @Override // io.reactivex.CompletableOnSubscribe
                public final void subscribe(CompletableEmitter completableEmitter) {
                    CloudQueuePlaybackUtil.Companion.m708restartAmplifyDB$lambda40(completableEmitter);
                }
            });
            Intrinsics.checkNotNullExpressionValue(completableCreate, "create { emitter ->\n    …         })\n            }");
            return completableCreate;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: restartAmplifyDB$lambda-40, reason: not valid java name */
        public static final void m708restartAmplifyDB$lambda40(final CompletableEmitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Amplify.DataStore.stop(new com.amplifyframework.core.Action() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda25
                @Override // com.amplifyframework.core.Action
                public final void call() {
                    CloudQueuePlaybackUtil.Companion.m709restartAmplifyDB$lambda40$lambda38(emitter);
                }
            }, new com.amplifyframework.core.Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda26
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m712restartAmplifyDB$lambda40$lambda39(emitter, (DataStoreException) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: restartAmplifyDB$lambda-40$lambda-38, reason: not valid java name */
        public static final void m709restartAmplifyDB$lambda40$lambda38(final CompletableEmitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "$emitter");
            CloudQueueFlexMetricsManager.trackEvent$default(CloudQueueFlexMetricsManager.Event.DATASTORE_STOPPED, null, null, null, true, true, 14, null);
            Log.info(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "Amplify Datastore is stopped successfully");
            Amplify.DataStore.start(new com.amplifyframework.core.Action() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda28
                @Override // com.amplifyframework.core.Action
                public final void call() {
                    CloudQueuePlaybackUtil.Companion.m710restartAmplifyDB$lambda40$lambda38$lambda36(emitter);
                }
            }, new com.amplifyframework.core.Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda29
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m711restartAmplifyDB$lambda40$lambda38$lambda37(emitter, (DataStoreException) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: restartAmplifyDB$lambda-40$lambda-38$lambda-36, reason: not valid java name */
        public static final void m710restartAmplifyDB$lambda40$lambda38$lambda36(CompletableEmitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "$emitter");
            CloudQueueFlexMetricsManager.trackEvent$default(CloudQueueFlexMetricsManager.Event.DATASTORE_STARTED, null, null, null, true, true, 14, null);
            Log.info(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "Amplify Datastore is started successfully");
            emitter.onComplete();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: restartAmplifyDB$lambda-40$lambda-38$lambda-37, reason: not valid java name */
        public static final void m711restartAmplifyDB$lambda40$lambda38$lambda37(CompletableEmitter emitter, DataStoreException it) {
            Intrinsics.checkNotNullParameter(emitter, "$emitter");
            Intrinsics.checkNotNullParameter(it, "it");
            CloudQueueFlexMetricsManager cloudQueueFlexMetricsManager = CloudQueueFlexMetricsManager.INSTANCE;
            CloudQueueFlexMetricsManager.Event event = CloudQueueFlexMetricsManager.Event.DATASTORE_STARTED;
            String message = it.getMessage();
            Throwable cause = it.getCause();
            CloudQueueFlexMetricsManager.trackEvent$default(event, null, message, cause == null ? null : cause.getMessage(), false, true, 2, null);
            String tag = CloudQueuePlaybackUtil.INSTANCE.getTAG();
            Throwable cause2 = it.getCause();
            Log.error(tag, Intrinsics.stringPlus("Failed to start Amplify Datastore : ", cause2 != null ? cause2.getMessage() : null));
            emitter.onError(it);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: restartAmplifyDB$lambda-40$lambda-39, reason: not valid java name */
        public static final void m712restartAmplifyDB$lambda40$lambda39(CompletableEmitter emitter, DataStoreException it) {
            Intrinsics.checkNotNullParameter(emitter, "$emitter");
            Intrinsics.checkNotNullParameter(it, "it");
            CloudQueueFlexMetricsManager cloudQueueFlexMetricsManager = CloudQueueFlexMetricsManager.INSTANCE;
            CloudQueueFlexMetricsManager.Event event = CloudQueueFlexMetricsManager.Event.DATASTORE_STOPPED;
            String message = it.getMessage();
            Throwable cause = it.getCause();
            CloudQueueFlexMetricsManager.trackEvent$default(event, null, message, cause == null ? null : cause.getMessage(), false, true, 2, null);
            String tag = CloudQueuePlaybackUtil.INSTANCE.getTAG();
            Throwable cause2 = it.getCause();
            Log.error(tag, Intrinsics.stringPlus("Failed to stop Amplify Datastore : ", cause2 != null ? cause2.getMessage() : null));
            emitter.onError(it);
        }

        static /* synthetic */ void populatePlayQueueFromAmplifyDB$default(Companion companion, CloudQueueClient cloudQueueClient, Uri uri, ControlSource controlSource, String str, boolean z, PlaybackPageType playbackPageType, int i, Object obj) {
            if ((i & 2) != 0) {
                uri = null;
            }
            Uri uri2 = uri;
            if ((i & 16) != 0) {
                z = false;
            }
            companion.populatePlayQueueFromAmplifyDB(cloudQueueClient, uri2, controlSource, str, z, playbackPageType);
        }

        private final void populatePlayQueueFromAmplifyDB(CloudQueueClient client, Uri contentUri, ControlSource controlSource, String queueId, boolean isCustomerSelectedEntity, PlaybackPageType playbackPageType) {
            String strName;
            if (client instanceof NoOpCloudQueueClient) {
                CloudQueueFlexMetricsManager.trackEvent$default(CloudQueueFlexMetricsManager.Event.NO_OP_CQ_CLIENT_VOICE, null, null, null, false, false, 62, null);
                Log.error(getTAG(), "Failed to initialize cloud queue client");
                return;
            }
            Outcome<Queue> queueById = client.getQueueById(queueId);
            if (queueById instanceof Outcome.Success) {
                Queue queue = (Queue) ((Outcome.Success) queueById).getValue();
                Outcome<DevicePlaybackState> devicePlaybackState = client.getDevicePlaybackState(queueId);
                if (!(devicePlaybackState instanceof Outcome.Success)) {
                    Log.warning(getTAG(), Intrinsics.stringPlus("Unable to get device playback state: ", ((Outcome.Failure) devicePlaybackState).getCause()));
                    return;
                }
                if (((DevicePlaybackState) ((Outcome.Success) devicePlaybackState).getValue()).getShuffleMode() == ShuffleModeEnum.SHUFFLE_ON) {
                    strName = QueueSequenceSliceName.SHUFFLE.name();
                } else {
                    strName = QueueSequenceSliceName.NATURAL.name();
                }
                Outcome<QueueSequenceSlice> queueSequenceSliceById = client.getQueueSequenceSliceById(queueId, strName);
                if (queueSequenceSliceById instanceof Outcome.Success) {
                    QueueSequenceSlice queueSequenceSlice = (QueueSequenceSlice) ((Outcome.Success) queueSequenceSliceById).getValue();
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it = queueSequenceSlice.getEntityReferences().iterator();
                    while (it.hasNext()) {
                        arrayList.add(((EntityReference) it.next()).getEntityReferenceId());
                    }
                    Outcome<List<QueueEntityMetadata>> queueEntityById = client.getQueueEntityById(arrayList);
                    if (queueEntityById instanceof Outcome.Success) {
                        List<QueueEntityMetadata> list = (List) ((Outcome.Success) queueEntityById).getValue();
                        CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
                        if (cloudQueueSequencer == null) {
                            Log.error(getTAG(), "Sequencer is null");
                            return;
                        }
                        List<MediaItem> queue2 = cloudQueueSequencer.getQueue();
                        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(queue2, 10)), 16));
                        for (Object obj : queue2) {
                            linkedHashMap.put(((MediaItem) obj).getMediaItemId().getId(), obj);
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (QueueEntityMetadata queueEntityMetadata : list) {
                            if (!linkedHashMap.containsKey(queueEntityMetadata.getIdentifier())) {
                                arrayList2.add(queueEntityMetadata);
                            }
                        }
                        if (arrayList2.isEmpty()) {
                            return;
                        }
                        String metricsContext = ((EntityReference) CollectionsKt___CollectionsKt.first((List) queueSequenceSlice.getEntityReferences())).getMetricsContext();
                        Outcome<com.amazon.digitalmusicxp.models.MetricsContext> metricsContextById = client.getMetricsContextById(metricsContext);
                        if (!(metricsContextById instanceof Outcome.Success)) {
                            Log.error(getTAG(), Intrinsics.stringPlus("Unable to find metricsContext with metricsContextId : ", metricsContext));
                            return;
                        }
                        MetricsContext onlineInteractionMetricsContext = getOnlineInteractionMetricsContext(((com.amazon.digitalmusicxp.models.MetricsContext) ((Outcome.Success) metricsContextById).getValue()).getMetricsContext(), playbackPageType);
                        String title = queue.getTitle();
                        if (title == null) {
                            title = "";
                        }
                        cloudQueueSequencer.addTracksToPlayQueue(CollectionsKt___CollectionsKt.filterNotNull(convertQueueEntitiesToMediaItems$default(this, arrayList2, onlineInteractionMetricsContext, controlSource, contentUri, title, null, isCustomerSelectedEntity, 0, CBORConstants.PREFIX_TYPE_OBJECT, null)), false, arrayList2);
                        cloudQueueSequencer.updateSequencerWithAOMLocalQueueInteractionLogic();
                        return;
                    }
                    Log.warning(getTAG(), "Not able to find queue entity metadata with queue id: " + queueId + ", because - " + ((Outcome.Failure) queueEntityById).getCause());
                    return;
                }
                Log.warning(getTAG(), "Not able to find queue slice with queue id: " + queueId + " and queue slice name: " + strName + ", because - " + ((Outcome.Failure) queueSequenceSliceById).getCause());
                return;
            }
            Log.warning(getTAG(), "Not able to find queue with id: " + queueId + ", because - " + ((Outcome.Failure) queueById).getCause());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void startPlayback$default(Companion companion, String str, QueueSeedContextInput queueSeedContextInput, QueueSeed queueSeed, Context context, Uri uri, boolean z, int i, PlaybackPageType playbackPageType, ControlSource controlSource, boolean z2, String str2, MediaCollectionId mediaCollectionId, List list, List list2, PlaybackListener playbackListener, NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, boolean z3, int i2, Object obj) {
            companion.startPlayback((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : queueSeedContextInput, (i2 & 4) != 0 ? null : queueSeed, context, (i2 & 16) != 0 ? null : uri, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? 0 : i, playbackPageType, controlSource, (i2 & 512) != 0 ? true : z2, (i2 & 1024) != 0 ? null : str2, (i2 & 2048) != 0 ? null : mediaCollectionId, (i2 & 4096) != 0 ? null : list, (i2 & 8192) != 0 ? null : list2, (i2 & 16384) != 0 ? null : playbackListener, noOpCloudQueueClientHandler, z3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v17, types: [T, com.amazon.digitalmusicxp.enums.PlayModeEnum] */
        @JvmStatic
        public final void startPlayback(final String queueTitle, QueueSeedContextInput queueSeedContextInput, final QueueSeed queueSeed, final Context context, Uri contentUri, final boolean isShuffle, final int defaultPosition, final PlaybackPageType playbackPageType, final ControlSource controlSource, final boolean isFullScreen, final String sortOrder, final MediaCollectionId additionalMediaCollectionId, final List<? extends MusicTrack> trackList, final List<? extends PrimeTrack> primeTrackList, final PlaybackListener playbackListener, final NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, final boolean isUserInitiated) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
            Intrinsics.checkNotNullParameter(controlSource, "controlSource");
            Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "noOpCloudQueueClientHandler");
            CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            final Uri uriRemoveTracksFromUriIfNeeded = contentUri == null ? null : ContentPlaybackUtils.INSTANCE.removeTracksFromUriIfNeeded(contentUri);
            int i = 1;
            PlayQueueInteractionPattern playQueueInteractionPattern = new EligibilityUtil(cachedCapabilityActionProviderImpl, i, z3 ? 1 : 0).getPlayQueueInteractionPattern(uriRemoveTracksFromUriIfNeeded, context);
            Log.debug(getTAG(), Intrinsics.stringPlus("startPlayback playQueueInteractionPattern=", playQueueInteractionPattern));
            int i2 = playQueueInteractionPattern == null ? -1 : WhenMappings.$EnumSwitchMapping$2[playQueueInteractionPattern.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda6
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            CloudQueuePlaybackUtil.Companion.m720startPlayback$lambda51(noOpCloudQueueClientHandler, trackList, queueSeed, primeTrackList, uriRemoveTracksFromUriIfNeeded, context, isShuffle, defaultPosition, playbackPageType, controlSource, isFullScreen, sortOrder, additionalMediaCollectionId, isUserInitiated, playbackListener, queueTitle, (CloudQueueClient) obj);
                        }
                    }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda7
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            CloudQueuePlaybackUtil.Companion.m721startPlayback$lambda52(playbackListener, (Throwable) obj);
                        }
                    });
                    return;
                } else {
                    emitPlaybackErrorMetrics(PlaybackError.INITIATE_FAILURE.name(), "Unable to initiate playback since playQueueInteractionPattern is null");
                    ContextKt.runOnUiThread$default(context, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$startPlayback$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Context context2) {
                            invoke2(context2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Context it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(context);
                            bauhausDialogBuilder.setTitle(R.string.dmusic_playback_error_media_player_notification_header);
                            bauhausDialogBuilder.setMessage(R.string.dmusic_playback_cq_interaction_pattern_error_buydrm_player_dialog_detail);
                            bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
                            bauhausDialogBuilder.create().show();
                        }
                    }, 1, null);
                    return;
                }
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            final boolean zIsStation = MediaProvider.Station.isStation(uriRemoveTracksFromUriIfNeeded);
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            if (zIsStation) {
                objectRef.element = queueSeedContextInput;
            } else {
                if (uriRemoveTracksFromUriIfNeeded != null) {
                    AbstractItem libraryItem = new CirrusSourceLibraryItemFactory(context).getItem(uriRemoveTracksFromUriIfNeeded);
                    if (libraryItem != null) {
                        EligibilityUtil eligibilityUtil = new EligibilityUtil(z2 ? 1 : 0, i, z ? 1 : 0);
                        Intrinsics.checkNotNullExpressionValue(libraryItem, "libraryItem");
                        objectRef2.element = eligibilityUtil.getPlayMode(libraryItem);
                    }
                } else {
                    Log.warning(getTAG(), "content URI is null, unable to fetch play mode");
                }
                T t = objectRef2.element;
                if (t != 0) {
                    Intrinsics.checkNotNull(t);
                    QueueSeedContextInput queueSeedContextInputByUri = CloudQueueDigitalXPUtil.getQueueSeedContextInputByUri(context, uriRemoveTracksFromUriIfNeeded, (PlayModeEnum) t);
                    T queueSeedContextInputsByAsin$default = queueSeedContextInputByUri;
                    if (queueSeedContextInputByUri == null) {
                        CloudQueueDigitalXPUtil cloudQueueDigitalXPUtil = CloudQueueDigitalXPUtil.INSTANCE;
                        T t2 = objectRef2.element;
                        Intrinsics.checkNotNull(t2);
                        queueSeedContextInputsByAsin$default = CloudQueueDigitalXPUtil.getQueueSeedContextInputsByAsin$default(cloudQueueDigitalXPUtil, trackList, primeTrackList, (PlayModeEnum) t2, null, 8, null);
                    }
                    objectRef.element = queueSeedContextInputsByAsin$default;
                } else {
                    Log.warning(getTAG(), "play mode is null, unable to create Queue Seeed Context Input");
                }
            }
            final Uri uri = uriRemoveTracksFromUriIfNeeded;
            CloudQueueClientSingleton.INSTANCE.getCloudQueueAppClientObservable().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m718startPlayback$lambda45(zIsStation, objectRef2, noOpCloudQueueClientHandler, uri, objectRef, isShuffle, queueTitle, context, playbackPageType, playbackListener, controlSource, isFullScreen, additionalMediaCollectionId, isUserInitiated, queueSeed, (CloudQueueAppClient) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda5
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m719startPlayback$lambda46(playbackListener, (Throwable) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: startPlayback$lambda-45, reason: not valid java name */
        public static final void m718startPlayback$lambda45(boolean z, Ref.ObjectRef playMode, NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, Uri uri, Ref.ObjectRef seedContextInput, boolean z2, String str, Context context, PlaybackPageType playbackPageType, PlaybackListener playbackListener, ControlSource controlSource, boolean z3, MediaCollectionId mediaCollectionId, boolean z4, QueueSeed queueSeed, CloudQueueAppClient cloudQueueAppClient) {
            T t;
            Intrinsics.checkNotNullParameter(playMode, "$playMode");
            Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
            Intrinsics.checkNotNullParameter(seedContextInput, "$seedContextInput");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
            Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
            Intrinsics.checkNotNullParameter(cloudQueueAppClient, "cloudQueueAppClient");
            if (cloudQueueAppClient instanceof NoOpCloudQueueAppClient) {
                if (z || PlayModeEnum.AUGMENTED != playMode.element) {
                    noOpCloudQueueClientHandler.handlePlayback();
                    return;
                }
                return;
            }
            if (uri != null && (t = seedContextInput.element) != 0) {
                startOnlineQueueInteraction$default(CloudQueuePlaybackUtil.INSTANCE, str, cloudQueueAppClient, context, (QueueSeedContextInput) t, playbackPageType, playbackListener, controlSource, uri, z3, mediaCollectionId, null, z4, null, CloudQueueDigitalXPUtil.getPlayBackOptions$default(((QueueSeedContextInput) t).getPlayMode(), z2, null, 4, null), null, 20480, null);
                return;
            }
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Log.error(companion.getTAG(), "fail to start playback from queueSeed : " + queueSeed + ' ');
            companion.emitPlaybackErrorMetrics(PlaybackError.INITIATE_FAILURE.name(), "fail to start playback from queueSeed : " + queueSeed + ' ');
            if (playbackListener == null) {
                return;
            }
            PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.NULL_QUEUE_SEED_CONTEXT_INPUT, null, 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: startPlayback$lambda-46, reason: not valid java name */
        public static final void m719startPlayback$lambda46(PlaybackListener playbackListener, Throwable th) {
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Log.error(companion.getTAG(), "failed to start cloud queue app", th);
            companion.emitPlaybackErrorMetrics(PlaybackError.CLOUDQUEUE_APP_ERROR.name(), Intrinsics.stringPlus("failed to start cloud queue app : ", th.getMessage()));
            if (playbackListener == null) {
                return;
            }
            playbackListener.onPlaybackFailure(PlaybackError.CLOUDQUEUE_APP_CLIENT_CREATION_FAILURE, new Exception(th));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: startPlayback$lambda-51, reason: not valid java name */
        public static final void m720startPlayback$lambda51(NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, List list, QueueSeed queueSeed, List list2, Uri uri, Context context, boolean z, int i, PlaybackPageType playbackPageType, ControlSource controlSource, boolean z2, String str, MediaCollectionId mediaCollectionId, boolean z3, PlaybackListener playbackListener, String str2, CloudQueueClient cloudQueueClient) {
            String str3;
            Integer numValueOf;
            String str4;
            Intrinsics.checkNotNullParameter(noOpCloudQueueClientHandler, "$noOpCloudQueueClientHandler");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
            Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
            Intrinsics.checkNotNullParameter(cloudQueueClient, "cloudQueueClient");
            if (cloudQueueClient instanceof NoOpCloudQueueClient) {
                noOpCloudQueueClientHandler.handlePlayback();
                return;
            }
            Integer numValueOf2 = null;
            if (!(list == null || list.isEmpty())) {
                if (queueSeed == null) {
                    str4 = "fail to start playback from queueSeed : ";
                } else {
                    Companion companion = CloudQueuePlaybackUtil.INSTANCE;
                    String customerId = AccountDetailUtil.get(context).getCustomerId();
                    String str5 = str2 == null ? "" : str2;
                    Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
                    str4 = "fail to start playback from queueSeed : ";
                    numValueOf2 = Integer.valueOf(startLocalQueueInteraction$default(companion, customerId, str5, cloudQueueClient, context, list, i, z, queueSeed, uri, playbackPageType, playbackListener, false, controlSource, z2, mediaCollectionId, z3, 2048, null));
                }
                if (numValueOf2 == null) {
                    Companion companion2 = CloudQueuePlaybackUtil.INSTANCE;
                    String tag = companion2.getTAG();
                    StringBuilder sb = new StringBuilder();
                    String str6 = str4;
                    sb.append(str6);
                    sb.append(queueSeed);
                    sb.append(' ');
                    Log.error(tag, sb.toString());
                    companion2.emitPlaybackErrorMetrics(PlaybackError.INITIATE_FAILURE.name(), str6 + queueSeed + ' ');
                    if (playbackListener == null) {
                        return;
                    }
                    PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.NULL_QUEUE_SEED, null, 2, null);
                    return;
                }
                return;
            }
            if (list2 == null || list2.isEmpty()) {
                if (uri != null) {
                    startLocalQueueInteractionWithUri$default(CloudQueuePlaybackUtil.INSTANCE, cloudQueueClient, context, uri, z, i, playbackPageType, controlSource, z2, str, mediaCollectionId, null, z3, 1024, null);
                    return;
                }
                Companion companion3 = CloudQueuePlaybackUtil.INSTANCE;
                Log.error(companion3.getTAG(), Intrinsics.stringPlus("fail to parse uri, unable to start cloudqueue : ", uri));
                companion3.emitPlaybackErrorMetrics(PlaybackError.INVALID_CONTENT.name(), Intrinsics.stringPlus("fail to parse uri, unable to start cloudqueue : ", uri));
                if (playbackListener == null) {
                    return;
                }
                PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.NULL_CONTENT_URI, null, 2, null);
                return;
            }
            if (queueSeed == null) {
                numValueOf = null;
                str3 = "fail to start playback from queueSeed : ";
            } else {
                Companion companion4 = CloudQueuePlaybackUtil.INSTANCE;
                String customerId2 = AccountDetailUtil.get(context).getCustomerId();
                String str7 = str2 != null ? str2 : "";
                Intrinsics.checkNotNullExpressionValue(customerId2, "customerId");
                str3 = "fail to start playback from queueSeed : ";
                numValueOf = Integer.valueOf(startLocalQueueInteraction$default(companion4, customerId2, str7, cloudQueueClient, context, list2, i, z, queueSeed, uri, playbackPageType, playbackListener, false, controlSource, z2, mediaCollectionId, z3, 2048, null));
            }
            if (numValueOf == null) {
                Companion companion5 = CloudQueuePlaybackUtil.INSTANCE;
                String tag2 = companion5.getTAG();
                StringBuilder sb2 = new StringBuilder();
                String str8 = str3;
                sb2.append(str8);
                sb2.append(queueSeed);
                sb2.append(' ');
                Log.error(tag2, sb2.toString());
                companion5.emitPlaybackErrorMetrics(PlaybackError.INITIATE_FAILURE.name(), str8 + queueSeed + ' ');
                if (playbackListener == null) {
                    return;
                }
                PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.NULL_QUEUE_SEED, null, 2, null);
            }
        }

        @WorkerThread
        public final List<QueueEntityMetadata> convertPrimePlayListTrackToQueueEntity(List<? extends CatalogPlaylistTrack> track, String queueId, String customerId) {
            PlaybackAssetSource playbackAssetSource;
            Intrinsics.checkNotNullParameter(track, "track");
            Intrinsics.checkNotNullParameter(queueId, "queueId");
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(track, 10));
            Iterator it = track.iterator();
            while (it.hasNext()) {
                CatalogPlaylistTrack catalogPlaylistTrack = (CatalogPlaylistTrack) it.next();
                Companion companion = CloudQueuePlaybackUtil.INSTANCE;
                boolean zIsTrackVideo = companion.isTrackVideo(catalogPlaylistTrack.getAssetQualities());
                if (zIsTrackVideo) {
                    PlaybackAssetSourceTypeEnum playbackAssetSourceTypeEnum = PlaybackAssetSourceTypeEnum.PID;
                    VideoUtil videoUtil = VideoUtil.INSTANCE;
                    List<AssetQuality> assetQualities = catalogPlaylistTrack.getAssetQualities();
                    Intrinsics.checkNotNullExpressionValue(assetQualities, "track.assetQualities");
                    playbackAssetSource = new PlaybackAssetSource(playbackAssetSourceTypeEnum, videoUtil.getDefaultOrFirstAvailablePID(assetQualities));
                } else {
                    playbackAssetSource = null;
                }
                PlaybackAssetSource playbackAssetSource2 = playbackAssetSource;
                QueueEntitySubtypeEnum queueEntitySubtypeEnum = zIsTrackVideo ? QueueEntitySubtypeEnum.VIDEO : QueueEntitySubtypeEnum.AUDIO;
                String artistAsin = catalogPlaylistTrack.getArtistAsin();
                Intrinsics.checkNotNullExpressionValue(artistAsin, "track.artistAsin");
                String artistName = catalogPlaylistTrack.getArtistName();
                Intrinsics.checkNotNullExpressionValue(artistName, "track.artistName");
                List listListOf = CollectionsKt__CollectionsJVMKt.listOf(new TrackArtist(artistAsin, "", artistName, CollectionsKt__CollectionsKt.emptyList()));
                String queueEntityMetadataId = companion.getQueueEntityMetadataId(catalogPlaylistTrack.getLuid());
                String asin = catalogPlaylistTrack.getAsin();
                ImageMetadata defaultImageMetadata = companion.getDefaultImageMetadata(catalogPlaylistTrack.getAlbumArtUrl());
                long jCurrentTimeMillis = System.currentTimeMillis();
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                boolean zHasLyrics = catalogPlaylistTrack.hasLyrics();
                Iterator it2 = it;
                ParentalControls parentalControls = new ParentalControls(catalogPlaylistTrack.isExplicit());
                String artistName2 = catalogPlaylistTrack.getArtistName();
                String albumAsin = catalogPlaylistTrack.getAlbumAsin();
                ArrayList arrayList2 = arrayList;
                String albumName = catalogPlaylistTrack.getAlbumName();
                Intrinsics.checkNotNullExpressionValue(albumName, "track.albumName");
                TrackAlbum trackAlbum = new TrackAlbum(artistName2, listListOf, albumAsin, albumName);
                CuePoints cuePoints = companion.getCuePoints(Integer.valueOf(catalogPlaylistTrack.getDurationSeconds()));
                List<ContentEncodingEnum> listContentEncodingEnumListConverter = CloudQueueDigitalXPUtil.INSTANCE.contentEncodingEnumListConverter(catalogPlaylistTrack.getContentEncoding());
                String asin2 = catalogPlaylistTrack.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin, "asin");
                arrayList = arrayList2;
                arrayList.add(new QueueEntityMetadata(customerId, queueId, queueEntityMetadataId, asin, QueueEntityIdTypeEnum.CATALOG_TRACK_ID, queueEntitySubtypeEnum, Float.valueOf(catalogPlaylistTrack.getDurationSeconds() * 1000), catalogPlaylistTrack.getTitle(), catalogPlaylistTrack.getAlbumName(), defaultImageMetadata, parentalControls, trackAlbum, null, listListOf, Boolean.valueOf(zHasLyrics), cuePoints, playbackAssetSource2, null, listContentEncodingEnumListConverter, asin2, jCurrentTimeMillis, jCurrentTimeMillis2, 135168, null));
                it = it2;
            }
            return arrayList;
        }

        @WorkerThread
        public final List<QueueEntityMetadata> convertTrackItemToQueueEntity(List<? extends TrackItem> track, String queueId, String customerId) {
            Intrinsics.checkNotNullParameter(track, "track");
            Intrinsics.checkNotNullParameter(queueId, "queueId");
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(track, 10));
            Iterator it = track.iterator();
            while (it.hasNext()) {
                TrackItem trackItem = (TrackItem) it.next();
                String asin = trackItem.getArtist().getAsin();
                Intrinsics.checkNotNullExpressionValue(asin, "track.artist.asin");
                String name = trackItem.getArtist().getName();
                Intrinsics.checkNotNullExpressionValue(name, "track.artist.name");
                List listListOf = CollectionsKt__CollectionsJVMKt.listOf(new TrackArtist(asin, "", name, CollectionsKt__CollectionsKt.emptyList()));
                Companion companion = CloudQueuePlaybackUtil.INSTANCE;
                String queueEntityMetadataId = companion.getQueueEntityMetadataId(trackItem.getObjectId());
                String asin2 = trackItem.getAsin();
                String str = asin2 == null ? "" : asin2;
                ImageMetadata defaultImageMetadata = companion.getDefaultImageMetadata(trackItem.getAlbum().getImage());
                long jCurrentTimeMillis = System.currentTimeMillis();
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                Boolean boolIsHasLyrics = trackItem.isHasLyrics();
                Boolean boolIsHasExplicitLanguage = trackItem.getParentalControls().isHasExplicitLanguage();
                Intrinsics.checkNotNullExpressionValue(boolIsHasExplicitLanguage, "track.parentalControls.isHasExplicitLanguage");
                ParentalControls parentalControls = new ParentalControls(boolIsHasExplicitLanguage.booleanValue());
                String name2 = trackItem.getArtist().getName();
                Iterator it2 = it;
                String asin3 = trackItem.getAlbum().getAsin();
                String title = trackItem.getAlbum().getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "track.album.title");
                arrayList.add(new QueueEntityMetadata(customerId, queueId, queueEntityMetadataId, str, QueueEntityIdTypeEnum.CATALOG_TRACK_ID, null, Float.valueOf(1000 * trackItem.getDuration().intValue()), trackItem.getTitle(), trackItem.getAlbum().getTitle(), defaultImageMetadata, parentalControls, new TrackAlbum(name2, listListOf, asin3, title), null, listListOf, boolIsHasLyrics, companion.getCuePoints(trackItem.getDuration()), null, null, CloudQueueDigitalXPUtil.INSTANCE.contentEncodingEnumListConverter(ContentEncodingDecoder.loadFrom(trackItem.getContentEncoding())), trackItem.getAsin(), jCurrentTimeMillis, jCurrentTimeMillis2, 200736, null));
                it = it2;
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: startPlayback$lambda-52, reason: not valid java name */
        public static final void m721startPlayback$lambda52(PlaybackListener playbackListener, Throwable th) {
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Log.error(companion.getTAG(), "failed to start cloud queue", th);
            companion.emitPlaybackErrorMetrics(PlaybackError.CLOUDQUEUE_APP_ERROR.name(), Intrinsics.stringPlus("failed to start cloud queue : ", th.getMessage()));
            if (playbackListener == null) {
                return;
            }
            playbackListener.onPlaybackFailure(PlaybackError.CLOUDQUEUE_APP_CLIENT_CREATION_FAILURE, new Exception(th));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00f0  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00f3  */
        @androidx.annotation.WorkerThread
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<com.amazon.digitalmusicxp.models.QueueEntityMetadata> convertPrimeTrackToQueueEntity(java.util.List<? extends com.amazon.mp3.prime.browse.metadata.PrimeTrack> r33, java.lang.String r34, java.lang.String r35) {
            /*
                Method dump skipped, instruction units count: 325
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.convertPrimeTrackToQueueEntity(java.util.List, java.lang.String, java.lang.String):java.util.List");
        }

        public final void startQueueInteractionWithPrimeTracks(String queueTitle, Context context, List<? extends PrimeTrack> trackList, int defaultPosition, boolean isShuffle, QueueSeed queueSeed, Uri contentUri, PlaybackPageType playbackPageType, ControlSource controlSource, boolean isFullScreen, NoOpCloudQueueClientHandler playbackNoOpHandler, boolean isUserInitiated) {
            QueueSeed queueSeedByIds;
            Intrinsics.checkNotNullParameter(queueTitle, "queueTitle");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(trackList, "trackList");
            Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
            Intrinsics.checkNotNullParameter(controlSource, "controlSource");
            Intrinsics.checkNotNullParameter(playbackNoOpHandler, "playbackNoOpHandler");
            if (queueSeed != null) {
                queueSeedByIds = queueSeed;
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = trackList.iterator();
                while (it.hasNext()) {
                    String asin = ((PrimeTrack) it.next()).getAsin();
                    if (asin != null) {
                        arrayList.add(asin);
                    }
                }
                QueueSeed queueSeedByContentUri = CloudQueueDigitalXPUtil.getQueueSeedByContentUri(context, contentUri);
                queueSeedByIds = queueSeedByContentUri == null ? CloudQueueDigitalXPUtil.getQueueSeedByIds(arrayList) : queueSeedByContentUri;
            }
            startPlayback$default(this, queueTitle, null, queueSeedByIds, context, contentUri, isShuffle, defaultPosition, playbackPageType, controlSource, isFullScreen, null, null, null, trackList, null, playbackNoOpHandler, isUserInitiated, 23554, null);
        }

        private final void emitPlaybackErrorMetrics(String tag, String msg) {
            if (Feature.error_180_flex_event_enabled.isEnabled()) {
                CloudQueueFlexMetricsManager.trackEventV2$default(CloudQueueFlexMetricsManager.Event.PLAYBACK_EXCEPTION, tag, msg, null, false, false, 40, null);
            }
        }

        public static /* synthetic */ void startLocalQueueInteractionWithUri$default(Companion companion, CloudQueueClient cloudQueueClient, Context context, Uri uri, boolean z, int i, PlaybackPageType playbackPageType, ControlSource controlSource, boolean z2, String str, MediaCollectionId mediaCollectionId, PlaybackListener playbackListener, boolean z3, int i2, Object obj) {
            companion.startLocalQueueInteractionWithUri(cloudQueueClient, context, uri, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? 0 : i, playbackPageType, controlSource, (i2 & 128) != 0 ? true : z2, (i2 & 256) != 0 ? null : str, (i2 & 512) != 0 ? null : mediaCollectionId, (i2 & 1024) != 0 ? null : playbackListener, z3);
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x025e  */
        /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void startLocalQueueInteractionWithUri(com.amazon.digitalmusicxp.clients.CloudQueueClient r49, android.content.Context r50, android.net.Uri r51, boolean r52, int r53, com.amazon.music.metrics.mts.event.types.PlaybackPageType r54, com.amazon.music.media.playback.ControlSource r55, boolean r56, java.lang.String r57, com.amazon.music.media.playback.MediaCollectionId r58, com.amazon.mp3.playback.PlaybackListener r59, boolean r60) {
            /*
                Method dump skipped, instruction units count: 613
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.startLocalQueueInteractionWithUri(com.amazon.digitalmusicxp.clients.CloudQueueClient, android.content.Context, android.net.Uri, boolean, int, com.amazon.music.metrics.mts.event.types.PlaybackPageType, com.amazon.music.media.playback.ControlSource, boolean, java.lang.String, com.amazon.music.media.playback.MediaCollectionId, com.amazon.mp3.playback.PlaybackListener, boolean):void");
        }

        public static /* synthetic */ int startLocalQueueInteraction$default(Companion companion, String str, String str2, CloudQueueClient cloudQueueClient, Context context, List list, int i, boolean z, QueueSeed queueSeed, Uri uri, PlaybackPageType playbackPageType, PlaybackListener playbackListener, boolean z2, ControlSource controlSource, boolean z3, MediaCollectionId mediaCollectionId, boolean z4, int i2, Object obj) {
            return companion.startLocalQueueInteraction(str, str2, cloudQueueClient, context, list, (i2 & 32) != 0 ? 0 : i, (i2 & 64) != 0 ? false : z, queueSeed, (i2 & 256) != 0 ? null : uri, playbackPageType, (i2 & 1024) != 0 ? null : playbackListener, (i2 & 2048) != 0 ? false : z2, controlSource, (i2 & 8192) != 0 ? true : z3, (i2 & 16384) != 0 ? null : mediaCollectionId, z4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:101:0x048e  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x04b1 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00e3  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0119  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x01fa  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x02c0  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x02c3  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x02e5 A[LOOP:1: B:69:0x02df->B:71:0x02e5, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0347  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0379  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x037e  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0399  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x039d  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x03cf  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0443  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x048b  */
        /* JADX WARN: Type inference failed for: r1v58, types: [T, com.amazon.digitalmusicxp.models.Queue] */
        /* JADX WARN: Type inference failed for: r6v2, types: [T, com.amazon.digitalmusicxp.models.Queue] */
        @android.annotation.SuppressLint({"CheckResult"})
        @androidx.annotation.VisibleForTesting(otherwise = 2)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int startLocalQueueInteraction(java.lang.String r62, java.lang.String r63, final com.amazon.digitalmusicxp.clients.CloudQueueClient r64, final android.content.Context r65, final java.util.List<? extends java.lang.Object> r66, final int r67, final boolean r68, com.amazon.digitalmusicxp.types.QueueSeed r69, final android.net.Uri r70, com.amazon.music.metrics.mts.event.types.PlaybackPageType r71, com.amazon.mp3.playback.PlaybackListener r72, boolean r73, final com.amazon.music.media.playback.ControlSource r74, boolean r75, final com.amazon.music.media.playback.MediaCollectionId r76, final boolean r77) {
            /*
                Method dump skipped, instruction units count: 1319
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil.Companion.startLocalQueueInteraction(java.lang.String, java.lang.String, com.amazon.digitalmusicxp.clients.CloudQueueClient, android.content.Context, java.util.List, int, boolean, com.amazon.digitalmusicxp.types.QueueSeed, android.net.Uri, com.amazon.music.metrics.mts.event.types.PlaybackPageType, com.amazon.mp3.playback.PlaybackListener, boolean, com.amazon.music.media.playback.ControlSource, boolean, com.amazon.music.media.playback.MediaCollectionId, boolean):int");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: startLocalQueueInteraction$lambda-58, reason: not valid java name */
        public static final void m713startLocalQueueInteraction$lambda58(List trackList, CloudQueueClient client, QueueSequenceSlice slice, DevicePlaybackState devicePlaybackState) {
            Intrinsics.checkNotNullParameter(trackList, "$trackList");
            Intrinsics.checkNotNullParameter(client, "$client");
            Intrinsics.checkNotNullParameter(slice, "$slice");
            Intrinsics.checkNotNullParameter(devicePlaybackState, "$devicePlaybackState");
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            companion.insertTrackIntoCTAPrimeCache(companion.getTrackToAlbumAsinMap(trackList));
            client.createNewQueueSequenceSlice(slice);
            client.changeDeviceState(devicePlaybackState);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: startLocalQueueInteraction$lambda-59, reason: not valid java name */
        public static final void m714startLocalQueueInteraction$lambda59(Ref.ObjectRef queue, QueueSequenceSlice slice, ObservableEmitter it) {
            Intrinsics.checkNotNullParameter(queue, "$queue");
            Intrinsics.checkNotNullParameter(slice, "$slice");
            Intrinsics.checkNotNullParameter(it, "it");
            it.onNext(new CloudQueuePlaybackUtil$Companion$startLocalQueueInteraction$4$1(queue, slice));
            it.onComplete();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: startLocalQueueInteraction$lambda-60, reason: not valid java name */
        public static final void m715startLocalQueueInteraction$lambda60(Context context, List queueEntityList, List mediaItemList, int i, boolean z, DevicePlaybackState devicePlaybackState, QueueCapabilitySet queueCapabilitySet, MetricsContext metricsContext, ControlSource controlSource, Uri uri, MediaCollectionId mediaCollectionId, boolean z2, Map.Entry entry) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(queueEntityList, "$queueEntityList");
            Intrinsics.checkNotNullParameter(mediaItemList, "$mediaItemList");
            Intrinsics.checkNotNullParameter(devicePlaybackState, "$devicePlaybackState");
            Intrinsics.checkNotNullParameter(queueCapabilitySet, "$queueCapabilitySet");
            Intrinsics.checkNotNullParameter(metricsContext, "$metricsContext");
            Intrinsics.checkNotNullParameter(controlSource, "$controlSource");
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(entry, "entry");
            companion.startCloudQueuePlayback(entry, context, queueEntityList, mediaItemList, i, z, false, devicePlaybackState.getLoopMode(), queueCapabilitySet.getCapabilities(), metricsContext, controlSource, uri, mediaCollectionId, z2);
        }

        @WorkerThread
        public final void startCloudQueuePlayback(Map.Entry<Queue, QueueSequenceSlice> entry, Context context, List<QueueEntityMetadata> entityList, List<? extends MediaItem> mediaItemList, int defaultPosition, boolean isShuffle, boolean isOnlineInteraction, LoopModeEnum loopMode, QueueCapability queueCapability, MetricsContext metricsContext, ControlSource controlSource, Uri contentUri, MediaCollectionId additionalMediaCollectionId, boolean isUserInitiated) {
            CloudQueueSequencer cloudQueueSequencer;
            SequencerPlaybackProvider sequencerPlaybackProvider;
            PlaybackController playbackController;
            Playback playback;
            Intrinsics.checkNotNullParameter(entry, "entry");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(mediaItemList, "mediaItemList");
            Intrinsics.checkNotNullParameter(loopMode, "loopMode");
            Intrinsics.checkNotNullParameter(queueCapability, "queueCapability");
            Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
            Intrinsics.checkNotNullParameter(controlSource, "controlSource");
            Playback playback2 = Playback.getInstance();
            PlaybackController playbackController2 = playback2.getPlaybackController(controlSource);
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
            long jCurrentTimeMillis = System.currentTimeMillis();
            Log.info(getTAG(), Intrinsics.stringPlus("Created Play Queue id:", entry.getKey().getId()));
            Queue key = entry.getKey();
            QueueSequenceSlice value = entry.getValue();
            String deviceType = accountCredentialStorage.getDeviceType();
            String deviceId = accountCredentialStorage.getDeviceId();
            Intrinsics.checkNotNullExpressionValue(deviceId, "deviceId");
            Intrinsics.checkNotNullExpressionValue(deviceType, "deviceType");
            CloudQueueSequencer cloudQueueSequencer2 = new CloudQueueSequencer(isOnlineInteraction, key, value, mediaItemList, defaultPosition, deviceId, deviceType, entityList, queueCapability, metricsContext, controlSource, contentUri, additionalMediaCollectionId, isUserInitiated);
            PlaybackProvider playbackProvider = playback2.getPlaybackProvider();
            cloudQueueSequencer2.setShuffled(isShuffle, true);
            playback2.getPlaybackConfig().setOnlineInteraction(isOnlineInteraction);
            if (playbackProvider instanceof SequencerPlaybackProvider) {
                sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
                cloudQueueSequencer = cloudQueueSequencer2;
                playbackController = playbackController2;
                playback = playback2;
            } else {
                cloudQueueSequencer = cloudQueueSequencer2;
                PlayerPlaybackProvider defaultPlaybackProvider = playback2.getPlaybackConfig().getDefaultPlaybackProvider(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context));
                if (defaultPlaybackProvider == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.media.playback.SequencerPlaybackProvider");
                }
                sequencerPlaybackProvider = (SequencerPlaybackProvider) defaultPlaybackProvider;
                playbackController = playbackController2;
                playbackController.stop(ChangeReason.NEW_SOURCE);
                playback = playback2;
                playback.setPlaybackProvider(sequencerPlaybackProvider);
            }
            playback.getPlaybackConfig().setOnlineInteraction(isOnlineInteraction);
            sequencerPlaybackProvider.startSequencer(cloudQueueSequencer, playbackController.getControlSource(), System.currentTimeMillis());
            if (isShuffle) {
                playbackController.setShuffled(isShuffle);
                cloudQueueSequencer.shuffle(isShuffle);
            }
            if (CloudQueueDigitalXPUtil.canPerformActionStrategy$default(queueCapability.getLoopAll(), null, 2, null)) {
                playbackController.setRepeatMode(CloudQueueDigitalXPUtil.INSTANCE.getRepeatModeFromLoopMode(loopMode));
            }
            CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
            if (castingSessionManager.isConnected() && castingSessionManager.getCastingDevice() != null) {
                MetricsHolder.getManager().handleEvent(new PlaybackCastedEvent(castingSessionManager.getCastingDevice().getCategory().getName(), System.currentTimeMillis() - jCurrentTimeMillis, 1L));
            }
            String tag = getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("successfully added ");
            sb.append(entityList == null ? 0 : entityList.size());
            sb.append(" tracks and started the queue");
            Log.info(tag, sb.toString());
        }

        @WorkerThread
        public final void restoreLocalInteractionPlayback(CloudQueueClient client, String queueId, Context context, SequencerPersistenceProvider sequencerPersistenceProvider) {
            String strName;
            SequencerPlaybackProvider sequencerPlaybackProvider;
            ArrayList arrayList;
            ArrayList arrayList2;
            ArrayList arrayList3;
            PlaybackAssetSource playbackAssetSource;
            QueueEntitySubtypeEnum queueEntitySubtypeEnum;
            List listListOf;
            CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory;
            TrackAlbum trackAlbum;
            TrackArtist trackArtist;
            Intrinsics.checkNotNullParameter(client, "client");
            Intrinsics.checkNotNullParameter(queueId, "queueId");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sequencerPersistenceProvider, "sequencerPersistenceProvider");
            Log.info(getTAG(), "Restoring local interaction playback");
            Outcome<Queue> queueById = client.getQueueById(queueId);
            if (queueById instanceof Outcome.Success) {
                Queue queue = (Queue) ((Outcome.Success) queueById).getValue();
                Outcome<DevicePlaybackState> devicePlaybackState = client.getDevicePlaybackState(queueId);
                if (devicePlaybackState instanceof Outcome.Success) {
                    DevicePlaybackState devicePlaybackState2 = (DevicePlaybackState) ((Outcome.Success) devicePlaybackState).getValue();
                    DeviceCurrentPlaybackState deviceCurrentPlaybackState = devicePlaybackState2.getDeviceCurrentPlaybackState();
                    String referenceId = deviceCurrentPlaybackState == null ? null : deviceCurrentPlaybackState.getReferenceId();
                    boolean z = devicePlaybackState2.getShuffleMode() == ShuffleModeEnum.SHUFFLE_ON;
                    if (z) {
                        strName = QueueSequenceSliceName.SHUFFLE.name();
                    } else {
                        strName = QueueSequenceSliceName.NATURAL.name();
                    }
                    Outcome<QueueSequenceSlice> queueSequenceSliceById = client.getQueueSequenceSliceById(queueId, strName);
                    if (queueSequenceSliceById instanceof Outcome.Success) {
                        QueueSequenceSlice queueSequenceSlice = (QueueSequenceSlice) ((Outcome.Success) queueSequenceSliceById).getValue();
                        int i = 0;
                        int i2 = 0;
                        for (Object obj : queueSequenceSlice.getEntityReferences()) {
                            int i3 = i + 1;
                            if (i < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                            }
                            if (Intrinsics.areEqual(((EntityReference) obj).getEntityReferenceId(), referenceId)) {
                                i2 = i;
                            }
                            i = i3;
                        }
                        String customerId = AccountDetailUtil.get(context).getCustomerId();
                        CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory2 = new CirrusSourceLibraryItemFactory(context);
                        ArrayList arrayList4 = new ArrayList();
                        ArrayList arrayList5 = new ArrayList();
                        CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(context);
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        List<EntityReference> entityReferences = queueSequenceSlice.getEntityReferences();
                        ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entityReferences, 10));
                        for (EntityReference entityReference : entityReferences) {
                            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
                            MusicTrack trackIncludingPrimePlaylistTrack = cirrusSourceLibraryItemFactory2.getTrackIncludingPrimePlaylistTrack(companion.retrieveTrackUriFromEntityId(entityReference.getIdentifier(), entityReference.getIdentifierType()));
                            boolean zIsTrackVideo = companion.isTrackVideo(trackIncludingPrimePlaylistTrack.getAssetQualities());
                            if (zIsTrackVideo) {
                                PlaybackAssetSourceTypeEnum playbackAssetSourceTypeEnum = PlaybackAssetSourceTypeEnum.PID;
                                arrayList = arrayList6;
                                VideoUtil videoUtil = VideoUtil.INSTANCE;
                                arrayList2 = arrayList5;
                                List<AssetQuality> assetQualities = trackIncludingPrimePlaylistTrack.getAssetQualities();
                                arrayList3 = arrayList4;
                                Intrinsics.checkNotNullExpressionValue(assetQualities, "musicTrack.assetQualities");
                                playbackAssetSource = new PlaybackAssetSource(playbackAssetSourceTypeEnum, videoUtil.getDefaultOrFirstAvailablePID(assetQualities));
                            } else {
                                arrayList = arrayList6;
                                arrayList2 = arrayList5;
                                arrayList3 = arrayList4;
                                if (trackIncludingPrimePlaylistTrack.getLocalUri() != null) {
                                    PlaybackAssetSourceTypeEnum playbackAssetSourceTypeEnum2 = PlaybackAssetSourceTypeEnum.URL;
                                    String localUri = trackIncludingPrimePlaylistTrack.getLocalUri();
                                    Intrinsics.checkNotNullExpressionValue(localUri, "musicTrack.localUri");
                                    playbackAssetSource = new PlaybackAssetSource(playbackAssetSourceTypeEnum2, localUri);
                                } else {
                                    playbackAssetSource = null;
                                }
                            }
                            if (zIsTrackVideo) {
                                queueEntitySubtypeEnum = QueueEntitySubtypeEnum.VIDEO;
                            } else {
                                queueEntitySubtypeEnum = QueueEntitySubtypeEnum.AUDIO;
                            }
                            QueueEntitySubtypeEnum queueEntitySubtypeEnum2 = queueEntitySubtypeEnum;
                            if (trackIncludingPrimePlaylistTrack.getArtistAsin() == null || trackIncludingPrimePlaylistTrack.getArtistName() == null) {
                                listListOf = null;
                            } else {
                                String artistAsin = trackIncludingPrimePlaylistTrack.getArtistAsin();
                                Intrinsics.checkNotNullExpressionValue(artistAsin, "musicTrack.artistAsin");
                                String artistName = trackIncludingPrimePlaylistTrack.getArtistName();
                                Intrinsics.checkNotNullExpressionValue(artistName, "musicTrack.artistName");
                                listListOf = CollectionsKt__CollectionsJVMKt.listOf(new TrackArtist(artistAsin, null, artistName, CollectionsKt__CollectionsKt.emptyList(), 2, null));
                            }
                            if (trackIncludingPrimePlaylistTrack.getAlbumName() == null) {
                                cirrusSourceLibraryItemFactory = cirrusSourceLibraryItemFactory2;
                                trackAlbum = null;
                            } else {
                                String artistName2 = trackIncludingPrimePlaylistTrack.getArtistName();
                                String albumAsin = trackIncludingPrimePlaylistTrack.getAlbumAsin();
                                String albumName = trackIncludingPrimePlaylistTrack.getAlbumName();
                                cirrusSourceLibraryItemFactory = cirrusSourceLibraryItemFactory2;
                                Intrinsics.checkNotNullExpressionValue(albumName, "musicTrack.albumName");
                                trackAlbum = new TrackAlbum(artistName2, listListOf, albumAsin, albumName);
                            }
                            String entityReferenceId = entityReference.getEntityReferenceId();
                            String identifier = entityReference.getIdentifier();
                            QueueEntityIdTypeEnum identifierType = entityReference.getIdentifierType();
                            float duration = 1000 * trackIncludingPrimePlaylistTrack.getDuration();
                            String title = trackIncludingPrimePlaylistTrack.getTitle();
                            String albumName2 = trackIncludingPrimePlaylistTrack.getAlbumName();
                            String albumArtUrl = trackIncludingPrimePlaylistTrack.getAlbumArtUrl();
                            if (albumArtUrl == null) {
                                albumArtUrl = cTAPrimeCache.getArtworkUrlForAsin(trackIncludingPrimePlaylistTrack.getAlbumAsin());
                            }
                            ImageMetadata defaultImageMetadata = companion.getDefaultImageMetadata(albumArtUrl);
                            ParentalControls parentalControls = new ParentalControls(trackIncludingPrimePlaylistTrack.isExplicit());
                            String name = (listListOf == null || (trackArtist = (TrackArtist) CollectionsKt___CollectionsKt.firstOrNull(listListOf)) == null) ? null : trackArtist.getName();
                            boolean zHasLyrics = trackIncludingPrimePlaylistTrack.hasLyrics();
                            CuePoints cuePoints = companion.getCuePoints(Integer.valueOf((int) trackIncludingPrimePlaylistTrack.getDuration()));
                            List<ContentEncodingEnum> listContentEncodingEnumListConverter = CloudQueueDigitalXPUtil.INSTANCE.contentEncodingEnumListConverter(trackIncludingPrimePlaylistTrack.getContentEncodings());
                            String asin = trackIncludingPrimePlaylistTrack.getAsin();
                            Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
                            ArrayList arrayList7 = arrayList;
                            ArrayList arrayList8 = arrayList2;
                            ArrayList arrayList9 = arrayList3;
                            arrayList9.add(new QueueEntityMetadata(customerId, queueId, entityReferenceId, identifier, identifierType, queueEntitySubtypeEnum2, Float.valueOf(duration), title, albumName2, defaultImageMetadata, parentalControls, trackAlbum, name, listListOf, Boolean.valueOf(zHasLyrics), cuePoints, playbackAssetSource, null, listContentEncodingEnumListConverter, asin, jCurrentTimeMillis, jCurrentTimeMillis));
                            ContentCatalogStatus contentCatalogStatus = trackIncludingPrimePlaylistTrack.getContentCatalogStatus();
                            Intrinsics.checkNotNullExpressionValue(contentCatalogStatus, "musicTrack.contentCatalogStatus");
                            arrayList7.add(Boolean.valueOf(arrayList8.add(contentCatalogStatus)));
                            arrayList6 = arrayList7;
                            arrayList5 = arrayList8;
                            arrayList4 = arrayList9;
                            cirrusSourceLibraryItemFactory2 = cirrusSourceLibraryItemFactory;
                            cTAPrimeCache = cTAPrimeCache;
                            customerId = customerId;
                            z = z;
                        }
                        ArrayList arrayList10 = arrayList5;
                        ArrayList arrayList11 = arrayList4;
                        boolean z2 = z;
                        PlaybackPageType playbackPageType = PlaybackPageType.HOME_SCREEN_WIDGET;
                        ControlSource controlSource = ControlSource.APP_UI;
                        MetricsContext localInteractionMetricsContext$default = getLocalInteractionMetricsContext$default(this, context, null, playbackPageType, queueId, null, 16, null);
                        Intrinsics.checkNotNullExpressionValue(controlSource, "controlSource");
                        String title2 = queue.getTitle();
                        if (title2 == null) {
                            title2 = "";
                        }
                        List listConvertQueueEntitiesToMediaItems$default = convertQueueEntitiesToMediaItems$default(this, arrayList11, localInteractionMetricsContext$default, controlSource, null, title2, arrayList10, false, 0, 192, null);
                        CloudQueueDigitalXPUtil cloudQueueDigitalXPUtil = CloudQueueDigitalXPUtil.INSTANCE;
                        ServiceTierEnum serviceTier = cloudQueueDigitalXPUtil.getServiceTier();
                        if (serviceTier == null) {
                            Log.warning(getTAG(), "serviceTier is null");
                            return;
                        }
                        List<QueueSeed> queueSeeds = queue.getQueueSeeds();
                        if (queueSeeds == null) {
                            Log.warning(getTAG(), Intrinsics.stringPlus("Not able to retrieve queue seed from queue: ", queueId));
                            return;
                        }
                        Outcome<QueueCapabilitySet> queueCapabilitySet = client.getQueueCapabilitySet(serviceTier, queueSeeds.get(0).getQueueSeedType(), null);
                        if (!(queueCapabilitySet instanceof Outcome.Success)) {
                            Log.warning(getTAG(), Intrinsics.stringPlus("Not able to retrieve queue capability set from queue: ", queueId));
                            return;
                        }
                        QueueCapability capabilities = ((QueueCapabilitySet) ((Outcome.Success) queueCapabilitySet).getValue()).getCapabilities();
                        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
                        String deviceType = accountCredentialStorage.getDeviceType();
                        String deviceId = accountCredentialStorage.getDeviceId();
                        Intrinsics.checkNotNullExpressionValue(deviceId, "deviceId");
                        Intrinsics.checkNotNullExpressionValue(deviceType, "deviceType");
                        CloudQueueSequencer cloudQueueSequencer = new CloudQueueSequencer(false, queue, queueSequenceSlice, listConvertQueueEntitiesToMediaItems$default, i2, deviceId, deviceType, arrayList11, capabilities, localInteractionMetricsContext$default, controlSource, null, null, false);
                        Playback playback = Playback.getInstance();
                        PlaybackProvider playbackProvider = playback.getPlaybackProvider();
                        PlaybackController playbackController = playback.getPlaybackController(controlSource);
                        if (playbackProvider instanceof SequencerPlaybackProvider) {
                            sequencerPlaybackProvider = (SequencerPlaybackProvider) playbackProvider;
                        } else {
                            PlayerPlaybackProvider defaultPlaybackProvider = playback.getPlaybackConfig().getDefaultPlaybackProvider(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context));
                            if (defaultPlaybackProvider == null) {
                                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.media.playback.SequencerPlaybackProvider");
                            }
                            sequencerPlaybackProvider = (SequencerPlaybackProvider) defaultPlaybackProvider;
                            playbackController.stop(ChangeReason.NEW_SOURCE);
                            playback.setPlaybackProvider(sequencerPlaybackProvider);
                        }
                        sequencerPlaybackProvider.setSequencerAndPosition(cloudQueueSequencer, deviceCurrentPlaybackState == null ? 0L : (long) deviceCurrentPlaybackState.getProgressInMs(), null, controlSource);
                        if (z2) {
                            playbackController.setShuffled(z2);
                            cloudQueueSequencer.shuffle(z2);
                        }
                        if (CloudQueueDigitalXPUtil.canPerformActionStrategy$default(capabilities.getLoopAll(), null, 2, null)) {
                            playbackController.setRepeatMode(cloudQueueDigitalXPUtil.getRepeatModeFromLoopMode(devicePlaybackState2.getLoopMode()));
                        }
                        sequencerPersistenceProvider.markRestored();
                        Log.info(getTAG(), "successfully added " + arrayList11.size() + " tracks and started the queue");
                        return;
                    }
                    Log.warning(getTAG(), "Not able to find queue slice with queue id: " + queueId + " and queue slice name: " + strName);
                    return;
                }
                Log.warning(getTAG(), Intrinsics.stringPlus("Not able to find device playback state with queue id: ", queueId));
                return;
            }
            Log.warning(getTAG(), Intrinsics.stringPlus("Not able to find queue with id: ", queueId));
        }

        @WorkerThread
        private final DevicePlaybackState getCurrentDevicePlaybackState(final Context context, Observable<CloudQueueClient> cloudQueueClientObservable, final Queue queue) {
            Object objBlockingSingle = cloudQueueClientObservable.concatMap(new Function() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda9
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return CloudQueuePlaybackUtil.Companion.m698getCurrentDevicePlaybackState$lambda66(queue, context, (CloudQueueClient) obj);
                }
            }).blockingSingle();
            Intrinsics.checkNotNullExpressionValue(objBlockingSingle, "cloudQueueClientObservab…       }.blockingSingle()");
            return (DevicePlaybackState) objBlockingSingle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getCurrentDevicePlaybackState$lambda-66, reason: not valid java name */
        public static final ObservableSource m698getCurrentDevicePlaybackState$lambda66(final Queue queue, final Context context, final CloudQueueClient it) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(it, "it");
            return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda19
                @Override // io.reactivex.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    CloudQueuePlaybackUtil.Companion.m699getCurrentDevicePlaybackState$lambda66$lambda65(queue, it, context, observableEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getCurrentDevicePlaybackState$lambda-66$lambda-65, reason: not valid java name */
        public static final void m699getCurrentDevicePlaybackState$lambda66$lambda65(Queue queue, CloudQueueClient it, Context context, ObservableEmitter observableEmitter) {
            Intrinsics.checkNotNullParameter(it, "$it");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(observableEmitter, "observableEmitter");
            if (queue != null) {
                try {
                    Outcome<DevicePlaybackState> devicePlaybackState = it.getDevicePlaybackState(queue.getId());
                    if (devicePlaybackState instanceof Outcome.Success) {
                        observableEmitter.onNext(((Outcome.Success) devicePlaybackState).getValue());
                    } else {
                        observableEmitter.onNext(CloudQueuePlaybackUtil.INSTANCE.getNewDummyDevicePlaybackState(context, queue.getId()));
                    }
                    observableEmitter.onComplete();
                    return;
                } catch (Exception e) {
                    observableEmitter.onError(e);
                    return;
                }
            }
            observableEmitter.onError(new IllegalArgumentException("request for playbackState object when cloud queue is not started"));
        }

        private final DevicePlaybackState getNewDummyDevicePlaybackState(Context context, String queueId) {
            String customerId = AccountDetailUtil.get(context).getCustomerId();
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
            String deviceId = accountCredentialStorage.getDeviceId();
            Intrinsics.checkNotNullExpressionValue(deviceId, "storage.deviceId");
            String deviceType = accountCredentialStorage.getDeviceType();
            Intrinsics.checkNotNullExpressionValue(deviceType, "storage.deviceType");
            Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
            return new DevicePlaybackState(deviceId, deviceType, queueId, customerId, UUID.randomUUID().toString(), QueueSequenceSliceName.NATURAL.name(), 0, null, LoopModeEnum.LOOP_OFF, ShuffleModeEnum.SHUFFLE_OFF, null, null, DevicePlaybackStateEnum.PLAYING, null, System.currentTimeMillis(), System.currentTimeMillis(), 11264, null);
        }

        static /* synthetic */ void startNowPlayingScreen$default(Companion companion, Context context, boolean z, Uri uri, UpsellInformation upsellInformation, int i, Object obj) {
            if ((i & 4) != 0) {
                uri = null;
            }
            if ((i & 8) != 0) {
                upsellInformation = null;
            }
            companion.startNowPlayingScreen(context, z, uri, upsellInformation);
        }

        private final void startNowPlayingScreen(Context context, boolean isStation, Uri contentUri, UpsellInformation upsellInformation) {
            CloudQueuePlaybackUtil.playbackObservable.onNext(new PlaybackContext(context, isStation, contentUri, upsellInformation));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: compiled from: CloudQueuePlaybackUtil.kt */
        @Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u000e\u0010\u0010R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/cloudqueue/CloudQueuePlaybackUtil$Companion$PlaybackContext;", "", "", "toString", "", "hashCode", "other", "", "equals", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "isStation", "Z", "()Z", "Landroid/net/Uri;", "contentUri", "Landroid/net/Uri;", "getContentUri", "()Landroid/net/Uri;", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "upsellInformation", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "getUpsellInformation", "()Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "<init>", "(Landroid/content/Context;ZLandroid/net/Uri;Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
        static final /* data */ class PlaybackContext {
            private final Uri contentUri;
            private final Context context;
            private final boolean isStation;
            private final UpsellInformation upsellInformation;

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof PlaybackContext)) {
                    return false;
                }
                PlaybackContext playbackContext = (PlaybackContext) other;
                return Intrinsics.areEqual(this.context, playbackContext.context) && this.isStation == playbackContext.isStation && Intrinsics.areEqual(this.contentUri, playbackContext.contentUri) && Intrinsics.areEqual(this.upsellInformation, playbackContext.upsellInformation);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [int] */
            /* JADX WARN: Type inference failed for: r1v7 */
            /* JADX WARN: Type inference failed for: r1v8 */
            public int hashCode() {
                int iHashCode = this.context.hashCode() * 31;
                boolean z = this.isStation;
                ?? r1 = z;
                if (z) {
                    r1 = 1;
                }
                int i = (iHashCode + r1) * 31;
                Uri uri = this.contentUri;
                int iHashCode2 = (i + (uri == null ? 0 : uri.hashCode())) * 31;
                UpsellInformation upsellInformation = this.upsellInformation;
                return iHashCode2 + (upsellInformation != null ? upsellInformation.hashCode() : 0);
            }

            public String toString() {
                return "PlaybackContext(context=" + this.context + ", isStation=" + this.isStation + ", contentUri=" + this.contentUri + ", upsellInformation=" + this.upsellInformation + ')';
            }

            public PlaybackContext(Context context, boolean z, Uri uri, UpsellInformation upsellInformation) {
                Intrinsics.checkNotNullParameter(context, "context");
                this.context = context;
                this.isStation = z;
                this.contentUri = uri;
                this.upsellInformation = upsellInformation;
            }

            public final Context getContext() {
                return this.context;
            }

            /* JADX INFO: renamed from: isStation, reason: from getter */
            public final boolean getIsStation() {
                return this.isStation;
            }

            public final Uri getContentUri() {
                return this.contentUri;
            }

            public final UpsellInformation getUpsellInformation() {
                return this.upsellInformation;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @MainThread
        public final void startNowPlayingScreenFromMainThread(Context context, boolean isStation, Uri contentUri, UpsellInformation upsellInformation) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
            intentForContentUri.putExtra("com.amazon.music.station.EXTRA_SHOW_STATION_LOADING", isStation);
            if (upsellInformation != null) {
                intentForContentUri.putExtra("com.amazon.music.playback.EXTRA_SHOW_UNLIMITED_UPSELL", upsellInformation);
            }
            if (contentUri != null) {
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", contentUri);
            }
            if (Intrinsics.areEqual(context, context.getApplicationContext())) {
                intentForContentUri.addFlags(268435456);
            } else {
                FragmentActivity fragmentActivity = (FragmentActivity) context;
                if (fragmentActivity.getIntent() != null && fragmentActivity.getIntent().getExtras() != null && !fragmentActivity.isFinishing()) {
                    DeeplinkIntentKey deeplinkIntentKey = DeeplinkIntentKey.KEY_ASSOCIATE_TAG;
                    intentForContentUri.putExtra(deeplinkIntentKey.toString(), fragmentActivity.getIntent().getStringExtra(deeplinkIntentKey.toString()));
                    DeeplinkIntentKey deeplinkIntentKey2 = DeeplinkIntentKey.KEY_REF_MARKER;
                    intentForContentUri.putExtra(deeplinkIntentKey2.toString(), fragmentActivity.getIntent().getStringExtra(deeplinkIntentKey2.toString()));
                }
            }
            context.startActivity(intentForContentUri);
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            }
        }

        @WorkerThread
        public final QueueSequenceSlice getCurrentPlayingQueueSequenceSlice(Observable<CloudQueueClient> cloudQueueClientObservable) {
            Intrinsics.checkNotNullParameter(cloudQueueClientObservable, "cloudQueueClientObservable");
            Object objBlockingSingle = cloudQueueClientObservable.concatMap(new Function() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda18
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return CloudQueuePlaybackUtil.Companion.m700getCurrentPlayingQueueSequenceSlice$lambda70((CloudQueueClient) obj);
                }
            }).blockingSingle();
            Intrinsics.checkNotNullExpressionValue(objBlockingSingle, "cloudQueueClientObservab…       }.blockingSingle()");
            return (QueueSequenceSlice) objBlockingSingle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getCurrentPlayingQueueSequenceSlice$lambda-70, reason: not valid java name */
        public static final ObservableSource m700getCurrentPlayingQueueSequenceSlice$lambda70(final CloudQueueClient it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda24
                @Override // io.reactivex.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    CloudQueuePlaybackUtil.Companion.m701getCurrentPlayingQueueSequenceSlice$lambda70$lambda69(it, observableEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getCurrentPlayingQueueSequenceSlice$lambda-70$lambda-69, reason: not valid java name */
        public static final void m701getCurrentPlayingQueueSequenceSlice$lambda70$lambda69(CloudQueueClient it, ObservableEmitter observableEmitter) {
            Intrinsics.checkNotNullParameter(it, "$it");
            Intrinsics.checkNotNullParameter(observableEmitter, "observableEmitter");
            QueueSequenceSlice currentPlayingQueueSequenceSlice = CloudQueueSequencer.INSTANCE.getCurrentPlayingQueueSequenceSlice();
            if (currentPlayingQueueSequenceSlice != null) {
                Outcome<QueueSequenceSlice> queueSequenceSliceById = it.getQueueSequenceSliceById(currentPlayingQueueSequenceSlice.getQueueId(), currentPlayingQueueSequenceSlice.getSequenceName());
                if (queueSequenceSliceById instanceof Outcome.Success) {
                    observableEmitter.onNext(((Outcome.Success) queueSequenceSliceById).getValue());
                    observableEmitter.onComplete();
                    return;
                } else {
                    observableEmitter.onNext(currentPlayingQueueSequenceSlice);
                    observableEmitter.onComplete();
                    Log.error(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "unable to get current play queue data, use cached one");
                    return;
                }
            }
            observableEmitter.onError(new IllegalArgumentException("request for queue slice object when cloud queue is not started"));
        }

        @WorkerThread
        public final QueueSequenceSlice copySequenceSlice(QueueSequenceSlice originalSlice, List<EntityReference> newIds) {
            Intrinsics.checkNotNullParameter(originalSlice, "originalSlice");
            Intrinsics.checkNotNullParameter(newIds, "newIds");
            return originalSlice.copy((383 & 1) != 0 ? originalSlice.queueId : null, (383 & 2) != 0 ? originalSlice.customerId : null, (383 & 4) != 0 ? originalSlice.id : null, (383 & 8) != 0 ? originalSlice.sequenceName : null, (383 & 16) != 0 ? originalSlice.sequenceVersion : 0, (383 & 32) != 0 ? originalSlice.sliceOrdinal : 0, (383 & 64) != 0 ? originalSlice.isLastSlice : false, (383 & 128) != 0 ? originalSlice.entityReferences : newIds, (383 & 256) != 0 ? originalSlice.createdAt : 0L, (383 & 512) != 0 ? originalSlice.updatedAt : System.currentTimeMillis());
        }

        @Deprecated(message = "Don't use it", replaceWith = @ReplaceWith(expression = "updateLocalQueueSequenceSlice and updateSliceToDatastore", imports = {}))
        public final void addTrackToDataStore(List<QueueEntityMetadata> tracks, QueueSequenceSlice queueSequenceSlice, boolean addNext, Integer currentPlayingIndex) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            Intrinsics.checkNotNullParameter(queueSequenceSlice, "queueSequenceSlice");
            CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
            if (cloudQueueSequencer == null) {
                return;
            }
            cloudQueueSequencer.updateLocalQueueSequenceSlice(new CloudQueueAction.Add(tracks, addNext));
            cloudQueueSequencer.updateSliceToDatastore();
        }

        public final void addTrackToCurrentQueue(final Uri uri, final boolean playNext, final boolean showToast) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            if (CloudQueueSequencer.INSTANCE.blockQueueMutation()) {
                Log.warning(getTAG(), "addTrackToCurrentQueue: queue mutation is blocked");
            } else {
                ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        CloudQueuePlaybackUtil.Companion.m695addTrackToCurrentQueue$lambda75(uri, playNext, showToast);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: addTrackToCurrentQueue$lambda-75, reason: not valid java name */
        public static final void m695addTrackToCurrentQueue$lambda75(Uri uri, final boolean z, boolean z2) {
            AbstractItem item;
            List<QueueEntityMetadata> listConvertPrimePlayListTrackToQueueEntity;
            Intrinsics.checkNotNullParameter(uri, "$uri");
            Uri nonTrackCollectionUri = MediaProvider.Tracks.getNonTrackCollectionUri(uri);
            if (nonTrackCollectionUri == null) {
                nonTrackCollectionUri = uri;
            }
            CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory(AmazonApplication.getApplication());
            if (MediaProvider.Albums.isAlbum(nonTrackCollectionUri)) {
                item = cirrusSourceLibraryItemFactory.getAlbum("cirrus", MediaProvider.Albums.getAlbumId(nonTrackCollectionUri));
                Intrinsics.checkNotNullExpressionValue(item, "{\n                    va…lbumId)\n                }");
            } else {
                item = cirrusSourceLibraryItemFactory.getItem(nonTrackCollectionUri);
                Intrinsics.checkNotNullExpressionValue(item, "{\n                    li…entUri)\n                }");
            }
            String defaultSortOrder = NowPlayingQueueUtil.getInstance().getDefaultSortOrder(nonTrackCollectionUri);
            ArrayList arrayList = new ArrayList();
            if (!(item instanceof CatalogPlaylist)) {
                if (item instanceof Album ? true : item instanceof Playlist ? true : item instanceof Artist) {
                    arrayList.addAll(CloudQueuePlaybackUtil.INSTANCE.getMusicTracksFromContentUri(cirrusSourceLibraryItemFactory, nonTrackCollectionUri, defaultSortOrder));
                } else {
                    arrayList.add(item);
                }
            } else if (Intrinsics.areEqual(defaultSortOrder, "order_num")) {
                Collection<CatalogPlaylistTrack> tracksForPrimePlaylist = cirrusSourceLibraryItemFactory.getTracksForPrimePlaylist((CatalogPlaylist) item);
                Intrinsics.checkNotNullExpressionValue(tracksForPrimePlaylist, "library.getTracksForPrim…laylist(collectionEntity)");
                arrayList.addAll(CollectionsKt___CollectionsKt.sortedWith(tracksForPrimePlaylist, new Comparator() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$addTrackToCurrentQueue$lambda-75$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((CatalogPlaylistTrack) t).getTrackNumber()), Integer.valueOf(((CatalogPlaylistTrack) t2).getTrackNumber()));
                    }
                }));
            } else {
                Collection<CatalogPlaylistTrack> tracksForPrimePlaylist2 = cirrusSourceLibraryItemFactory.getTracksForPrimePlaylist((CatalogPlaylist) item);
                Intrinsics.checkNotNullExpressionValue(tracksForPrimePlaylist2, "library.getTracksForPrim…laylist(collectionEntity)");
                arrayList.addAll(tracksForPrimePlaylist2);
            }
            if (arrayList.isEmpty()) {
                Log.warning(CloudQueuePlaybackUtil.INSTANCE.getTAG(), Intrinsics.stringPlus("addTrackToCurrentQueue: no tracks found from ", nonTrackCollectionUri));
                return;
            }
            CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
            if (cloudQueueSequencer == null) {
                return;
            }
            CollectionsKt__CollectionsKt.emptyList();
            CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(AmazonApplication.getApplication());
            Class<?> cls = arrayList.get(0).getClass();
            if (Intrinsics.areEqual(cls, MusicTrack.class)) {
                listConvertPrimePlayListTrackToQueueEntity = CloudQueuePlaybackUtil.INSTANCE.convertTrackToQueueEntity(arrayList, cloudQueueSequencer.getQueue().getId(), cloudQueueSequencer.getQueue().getCustomerId(), cTAPrimeCache);
            } else if (Intrinsics.areEqual(cls, TrackItem.class)) {
                listConvertPrimePlayListTrackToQueueEntity = CloudQueuePlaybackUtil.INSTANCE.convertTrackItemToQueueEntity(arrayList, cloudQueueSequencer.getQueue().getId(), cloudQueueSequencer.getQueue().getCustomerId());
            } else if (Intrinsics.areEqual(cls, PrimeTrack.class)) {
                listConvertPrimePlayListTrackToQueueEntity = CloudQueuePlaybackUtil.INSTANCE.convertPrimeTrackToQueueEntity(arrayList, cloudQueueSequencer.getQueue().getId(), cloudQueueSequencer.getQueue().getCustomerId());
            } else {
                if (!Intrinsics.areEqual(cls, CatalogPlaylistTrack.class)) {
                    Log.warning(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "addTrackToCurrentQueue: unsupported track type " + arrayList + "[0]");
                    return;
                }
                listConvertPrimePlayListTrackToQueueEntity = CloudQueuePlaybackUtil.INSTANCE.convertPrimePlayListTrackToQueueEntity(arrayList, cloudQueueSequencer.getQueue().getId(), cloudQueueSequencer.getQueue().getCustomerId());
            }
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            cloudQueueSequencer.addTrackToPlayback(CollectionsKt___CollectionsKt.filterNotNull(convertQueueEntitiesToMediaItems$default(companion, listConvertPrimePlayListTrackToQueueEntity, cloudQueueSequencer.getCollectionMetricsContext(), cloudQueueSequencer.getControlSource(), cloudQueueSequencer.getContentUri(), cloudQueueSequencer.getQueueTitle(), null, false, 0, 224, null)), z);
            QueueSequenceSlice currentPlayingQueueSequenceSlice = companion.getCurrentPlayingQueueSequenceSlice(CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable());
            String id = cloudQueueSequencer.getCurrentMediaItem().getMediaItemId(MediaItemId.Type.ASIN).getId();
            Intrinsics.checkNotNullExpressionValue(id, "it.currentMediaItem.getM…MediaItemId.Type.ASIN).id");
            companion.addTrackToDataStore(listConvertPrimePlayListTrackToQueueEntity, currentPlayingQueueSequenceSlice, z, cloudQueueSequencer.getCurrentPlayingIndex(id));
            companion.insertTrackIntoCTAPrimeCache(companion.getTrackToAlbumAsinMap(arrayList));
            if (z2) {
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda15
                    @Override // java.lang.Runnable
                    public final void run() {
                        CloudQueuePlaybackUtil.Companion.m696addTrackToCurrentQueue$lambda75$lambda74$lambda73(z);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: addTrackToCurrentQueue$lambda-75$lambda-74$lambda-73, reason: not valid java name */
        public static final void m696addTrackToCurrentQueue$lambda75$lambda74$lambda73(boolean z) {
            BauhausToastUtils.showTextToast(AmazonApplication.getApplication(), z ? R.string.dmusic_now_playing_queue_toast_play_next_text : R.string.dmusic_now_playing_queue_toast_add_to_play_queue_text, 0);
        }

        public final void updateDevicePlaybackState(Context context) {
            AmplifyPageModelDAO amplifyDao;
            Observable<List<QueueEntityMetadata>> observableFetchItemsObservable;
            Observable<List<QueueEntityMetadata>> observableSubscribeOn;
            Observable<List<QueueEntityMetadata>> observableObserveOn;
            Maybe<List<QueueEntityMetadata>> maybeSingleElement;
            Intrinsics.checkNotNullParameter(context, "context");
            MediaItem currentMediaItem = Playback.getInstance().getPlaybackController(ControlSource.APP_UI).getCurrentMediaItem();
            CloudQueueSequencer.Companion companion = CloudQueueSequencer.INSTANCE;
            CloudQueueSequencer cloudQueueSequencer = companion.getCloudQueueSequencer();
            String identifier = null;
            Queue queue = cloudQueueSequencer == null ? null : cloudQueueSequencer.getQueue();
            if (cloudQueueSequencer == null || queue == null || currentMediaItem == null || CastingUtil.isCastingToAlexa()) {
                return;
            }
            try {
                final Observable<CloudQueueClient> cloudQueueClientObservable = CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable();
                DevicePlaybackState currentDevicePlaybackState = getCurrentDevicePlaybackState(context, cloudQueueClientObservable, queue);
                final DevicePlaybackState devicePlaybackStateCopy = currentDevicePlaybackState.copy((27743 & 1) != 0 ? currentDevicePlaybackState.deviceId : null, (27743 & 2) != 0 ? currentDevicePlaybackState.deviceType : null, (27743 & 4) != 0 ? currentDevicePlaybackState.queueId : null, (27743 & 8) != 0 ? currentDevicePlaybackState.customerId : null, (27743 & 16) != 0 ? currentDevicePlaybackState.id : null, (27743 & 32) != 0 ? currentDevicePlaybackState.sequenceName : null, (27743 & 64) != 0 ? currentDevicePlaybackState.sequenceVersion : cloudQueueSequencer.getSequenceVersion(), (27743 & 128) != 0 ? currentDevicePlaybackState.deviceCurrentPlaybackState : null, (27743 & 256) != 0 ? currentDevicePlaybackState.loopMode : null, (27743 & 512) != 0 ? currentDevicePlaybackState.shuffleMode : null, (27743 & 1024) != 0 ? currentDevicePlaybackState.djMode : null, (27743 & 2048) != 0 ? currentDevicePlaybackState.playbackContinuationMode : null, (27743 & 4096) != 0 ? currentDevicePlaybackState.playbackState : null, (27743 & 8192) != 0 ? currentDevicePlaybackState.ipAddress : null, (27743 & 16384) != 0 ? currentDevicePlaybackState.createdAt : 0L, (27743 & 32768) != 0 ? currentDevicePlaybackState.updatedAt : 0L);
                final int dimension = (int) context.getResources().getDimension(R.dimen.artwork_dimen_featured);
                if (currentMediaItem.isInterlude()) {
                    updateDeviceState(cloudQueueClientObservable, devicePlaybackStateCopy, dimension, currentMediaItem);
                    return;
                }
                QueueSequenceSlice slice = cloudQueueSequencer.getSlice();
                String asin = MediaItemHelper.getAsin(currentMediaItem);
                if (asin == null) {
                    throw new Exception("mediaItem asin is null");
                }
                Integer currentPlayingIndex = cloudQueueSequencer.getCurrentPlayingIndex(asin);
                if (currentPlayingIndex != null && slice != null) {
                    EntityReference entityReference = (EntityReference) CollectionsKt___CollectionsKt.getOrNull(slice.getEntityReferences(), currentPlayingIndex.intValue());
                    if (entityReference != null) {
                        identifier = entityReference.getIdentifier();
                    }
                    if (identifier != null && !Intrinsics.areEqual(identifier, asin)) {
                        List<String> listListOf = CollectionsKt__CollectionsJVMKt.listOf(identifier);
                        CloudQueueSequencer cloudQueueSequencer2 = companion.getCloudQueueSequencer();
                        if (cloudQueueSequencer2 != null && (amplifyDao = cloudQueueSequencer2.getAmplifyDao()) != null && (observableFetchItemsObservable = amplifyDao.fetchItemsObservable(listListOf)) != null && (observableSubscribeOn = observableFetchItemsObservable.subscribeOn(Schedulers.computation())) != null && (observableObserveOn = observableSubscribeOn.observeOn(Schedulers.computation())) != null && (maybeSingleElement = observableObserveOn.singleElement()) != null) {
                            maybeSingleElement.subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda1
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) {
                                    CloudQueuePlaybackUtil.Companion.m722updateDevicePlaybackState$lambda78(cloudQueueClientObservable, devicePlaybackStateCopy, dimension, (List) obj);
                                }
                            }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda2
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) {
                                    CloudQueuePlaybackUtil.Companion.m723updateDevicePlaybackState$lambda79((Throwable) obj);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    updateDeviceState(cloudQueueClientObservable, devicePlaybackStateCopy, dimension, currentMediaItem);
                    return;
                }
                updateDeviceState(cloudQueueClientObservable, devicePlaybackStateCopy, dimension, currentMediaItem);
            } catch (Exception e) {
                Log.error(getTAG(), "Error while updating device playback state", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: updateDevicePlaybackState$lambda-78, reason: not valid java name */
        public static final void m722updateDevicePlaybackState$lambda78(Observable cloudQueueClientObservable, DevicePlaybackState devicePlaybackState, int i, List list) {
            QueueEntityMetadata queueEntityMetadata;
            CloudQueueSequencer cloudQueueSequencer;
            Intrinsics.checkNotNullParameter(cloudQueueClientObservable, "$cloudQueueClientObservable");
            Intrinsics.checkNotNullParameter(devicePlaybackState, "$devicePlaybackState");
            if (list == null || (queueEntityMetadata = (QueueEntityMetadata) CollectionsKt___CollectionsKt.firstOrNull(list)) == null || (cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer()) == null) {
                return;
            }
            Companion companion = CloudQueuePlaybackUtil.INSTANCE;
            companion.updateDeviceState(cloudQueueClientObservable, devicePlaybackState, i, convertSongEntityToMediaItem$default(companion, queueEntityMetadata, cloudQueueSequencer.getCollectionMetricsContext(), cloudQueueSequencer.getContentUri(), cloudQueueSequencer.getQueueTitle(), null, 16, null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: updateDevicePlaybackState$lambda-79, reason: not valid java name */
        public static final void m723updateDevicePlaybackState$lambda79(Throwable th) {
            Log.error(CloudQueuePlaybackUtil.INSTANCE.getTAG(), "Error fetching queue Entity", th);
        }

        public final void fetchMediaItemLikeStates(List<? extends MediaItem> mediaItems, LikesServiceProxy likesService) {
            Intrinsics.checkNotNullParameter(mediaItems, "mediaItems");
            Iterator it = CollectionsKt___CollectionsKt.chunked(CollectionsKt___CollectionsKt.filterNotNull(mediaItems), 20).iterator();
            while (it.hasNext()) {
                CloudQueuePlaybackUtil.INSTANCE.fetchMediaItemLikeStateBatch((List) it.next(), likesService);
            }
        }

        private final void fetchMediaItemLikeStateBatch(final List<? extends MediaItem> mediaItems, final LikesServiceProxy likesService) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() throws Exception {
                    CloudQueuePlaybackUtil.Companion.m697fetchMediaItemLikeStateBatch$lambda84(mediaItems, likesService);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: fetchMediaItemLikeStateBatch$lambda-84, reason: not valid java name */
        public static final void m697fetchMediaItemLikeStateBatch$lambda84(List mediaItems, LikesServiceProxy likesServiceProxy) throws Exception {
            LikesService likesService;
            Intrinsics.checkNotNullParameter(mediaItems, "$mediaItems");
            ArrayList arrayList = new ArrayList();
            Iterator it = mediaItems.iterator();
            while (it.hasNext()) {
                String asin = MediaItemHelper.getAsin((MediaItem) it.next());
                if (asin != null) {
                    arrayList.add(asin);
                }
            }
            List<LikeState> likeStates = null;
            if (likesServiceProxy != null && (likesService = likesServiceProxy.get()) != null) {
                likeStates = likesService.getLikeStates(arrayList);
            }
            int i = 0;
            if (likeStates != null && mediaItems.size() == likeStates.size()) {
                for (Object obj : mediaItems) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    MediaItem mediaItem = (MediaItem) obj;
                    if (mediaItem != null) {
                        mediaItem.initializeLikeState(likeStates.get(i));
                    }
                    MediaItem mediaItem2 = (MediaItem) mediaItems.get(i);
                    if (mediaItem2 != null) {
                        mediaItem2.setCanLike(true);
                    }
                    i = i2;
                }
            }
        }

        private final void updateDeviceState(Observable<CloudQueueClient> cloudQueueClientObservable, DevicePlaybackState devicePlaybackState, int artworkSize, MediaItem mediaItem) {
            String luidOrOwnedId;
            QueueEntityIdTypeEnum queueEntityIdTypeEnum;
            if (mediaItem instanceof InterludeMediaItem) {
                luidOrOwnedId = ((InterludeMediaItem) mediaItem).getInterludeId();
            } else {
                luidOrOwnedId = MediaItemHelper.getLuidOrOwnedId(mediaItem);
            }
            String str = luidOrOwnedId;
            if (str == null) {
                Log.error(getTAG(), Intrinsics.stringPlus("updateDeviceState: referenceId is null, mediaItem=", mediaItem));
                return;
            }
            PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
            long positionMillis = playbackController.getPositionMillis();
            boolean zIsStreaming = playbackController.isStreaming();
            boolean zIsShuffled = playbackController.isShuffled();
            CloudQueueDigitalXPUtil cloudQueueDigitalXPUtil = CloudQueueDigitalXPUtil.INSTANCE;
            RepeatMode repeatMode = playbackController.getRepeatMode();
            Intrinsics.checkNotNullExpressionValue(repeatMode, "playbackController.repeatMode");
            LoopModeEnum loopModeFromRepeatMode = cloudQueueDigitalXPUtil.getLoopModeFromRepeatMode(repeatMode);
            Uri uriCreateImageUri = mediaItem.createImageUri(artworkSize, artworkSize, new MediaItem.ImageType[0]);
            String string = uriCreateImageUri == null ? null : uriCreateImageUri.toString();
            ImageMetadata imageMetadata = string == null ? null : new ImageMetadata(null, new FixedSizeImage(null, null, null, string, 7, null), null, null, 13, null);
            if (mediaItem.isInterlude()) {
                queueEntityIdTypeEnum = QueueEntityIdTypeEnum.INTERLUDE_ID;
            } else {
                queueEntityIdTypeEnum = mediaItem.isOwned() ? QueueEntityIdTypeEnum.LIBRARY_TRACK_ID : QueueEntityIdTypeEnum.CATALOG_TRACK_ID;
            }
            final DevicePlaybackState devicePlaybackStateCopy = devicePlaybackState.copy((27743 & 1) != 0 ? devicePlaybackState.deviceId : null, (27743 & 2) != 0 ? devicePlaybackState.deviceType : null, (27743 & 4) != 0 ? devicePlaybackState.queueId : null, (27743 & 8) != 0 ? devicePlaybackState.customerId : null, (27743 & 16) != 0 ? devicePlaybackState.id : null, (27743 & 32) != 0 ? devicePlaybackState.sequenceName : (zIsShuffled ? QueueSequenceSliceName.SHUFFLE : QueueSequenceSliceName.NATURAL).name(), (27743 & 64) != 0 ? devicePlaybackState.sequenceVersion : 0, (27743 & 128) != 0 ? devicePlaybackState.deviceCurrentPlaybackState : new DeviceCurrentPlaybackState(str, queueEntityIdTypeEnum, positionMillis, System.currentTimeMillis(), mediaItem.getName(), mediaItem.getAlbumName(), imageMetadata), (27743 & 256) != 0 ? devicePlaybackState.loopMode : loopModeFromRepeatMode, (27743 & 512) != 0 ? devicePlaybackState.shuffleMode : cloudQueueDigitalXPUtil.getShuffleMode(zIsShuffled), (27743 & 1024) != 0 ? devicePlaybackState.djMode : null, (27743 & 2048) != 0 ? devicePlaybackState.playbackContinuationMode : null, (27743 & 4096) != 0 ? devicePlaybackState.playbackState : zIsStreaming ? DevicePlaybackStateEnum.PLAYING : DevicePlaybackStateEnum.STOPPED, (27743 & 8192) != 0 ? devicePlaybackState.ipAddress : null, (27743 & 16384) != 0 ? devicePlaybackState.createdAt : 0L, (27743 & 32768) != 0 ? devicePlaybackState.updatedAt : System.currentTimeMillis());
            cloudQueueClientObservable.doOnNext(new Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$$ExternalSyntheticLambda13
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    CloudQueuePlaybackUtil.Companion.m724updateDeviceState$lambda86(devicePlaybackStateCopy, (CloudQueueClient) obj);
                }
            }).subscribe();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: updateDeviceState$lambda-86, reason: not valid java name */
        public static final void m724updateDeviceState$lambda86(DevicePlaybackState newDevicePlaybackState, CloudQueueClient cloudQueueClient) {
            Intrinsics.checkNotNullParameter(newDevicePlaybackState, "$newDevicePlaybackState");
            cloudQueueClient.changeDeviceState(newDevicePlaybackState);
        }

        private final void insertTrackIntoCTAPrimeCache(Map<String, String> trackToAlbumAsins) {
            AmazonApplication context = AmazonApplication.getApplication();
            if (ConnectivityUtil.hasAnyInternetConnection(context)) {
                CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(context);
                ArrayList arrayList = new ArrayList();
                Set<Map.Entry<String, String>> setEntrySet = trackToAlbumAsins.entrySet();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Iterator<T> it = setEntrySet.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    String str = (String) entry.getValue();
                    Object arrayList2 = linkedHashMap.get(str);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        linkedHashMap.put(str, arrayList2);
                    }
                    ((List) arrayList2).add((String) entry.getKey());
                }
                for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                    String str2 = (String) entry2.getKey();
                    List list = (List) entry2.getValue();
                    if (cTAPrimeCache.getArtworkUrlForAsin(str2) == null) {
                        arrayList.addAll(list);
                    }
                }
                Intrinsics.checkNotNullExpressionValue(context, "context");
                List<PrimeTrack> primeTrackFromTrackAsin = getPrimeTrackFromTrackAsin(context, CollectionsKt___CollectionsKt.distinct(arrayList));
                if (primeTrackFromTrackAsin != null) {
                    int iInsertPrimeTracks = 0;
                    try {
                        for (PrimeTrack primeTrack : primeTrackFromTrackAsin) {
                            iInsertPrimeTracks += cTAPrimeCache.insertPrimeTracks(primeTrack.getAlbumAsin(), primeTrack.getAlbum(), primeTrack.getAlbumArtist(), CollectionsKt__CollectionsJVMKt.listOf(primeTrack));
                        }
                        Log.debug(getTAG(), "insertTrackIntoCTAPrimeCache, " + arrayList.size() + " asins, totalInsertion=" + iInsertPrimeTracks + " tracks");
                        return;
                    } catch (SQLiteDatabaseLockedException e) {
                        Log.error(getTAG(), "Failed to insert a track to a cache db", e);
                        return;
                    }
                }
                Log.error(getTAG(), Intrinsics.stringPlus("Failed to create PrimeTrack with ASIN: ", arrayList));
            }
        }

        private final List<PrimeTrack> getPrimeTrackFromTrackAsin(final Context context, List<String> trackAsins) {
            List listFlatten = CollectionsKt__IterablesKt.flatten(CollectionsKt___CollectionsKt.chunked(trackAsins, 50, new Function1<List<? extends String>, List<? extends TrackItem>>() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$getPrimeTrackFromTrackAsin$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ List<? extends TrackItem> invoke(List<? extends String> list) {
                    return invoke2((List<String>) list);
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final List<TrackItem> invoke2(List<String> asinList) {
                    Intrinsics.checkNotNullParameter(asinList, "asinList");
                    List<TrackItem> tracksFromAsins = new TrackRequester(context).getTracksFromAsins(asinList, false);
                    return tracksFromAsins == null ? CollectionsKt__CollectionsKt.emptyList() : tracksFromAsins;
                }
            }));
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listFlatten, 10));
            Iterator it = listFlatten.iterator();
            while (it.hasNext()) {
                arrayList.add(PrimeItemsTransformationUtil.toPrimeTrack((TrackItem) it.next()));
            }
            return arrayList;
        }

        private final UrlMediaLinkProvider getInterludeMediaLinkProvider() {
            return new UrlMediaLinkProvider();
        }

        private final PlaybackException.PlaybackError checkErrorType(String error) {
            if (StringsKt__StringsKt.contains$default((CharSequence) error, (CharSequence) "unauthorized", false, 2, (Object) null)) {
                return PlaybackException.PlaybackError.TOU;
            }
            if (StringsKt__StringsKt.contains$default((CharSequence) error, (CharSequence) "timeout", false, 2, (Object) null)) {
                return PlaybackException.PlaybackError.StationCannotContinue;
            }
            if (StringsKt__StringsKt.contains$default((CharSequence) error, (CharSequence) "Unable to resolve host", false, 2, (Object) null)) {
                return PlaybackException.PlaybackError.NetworkFailure;
            }
            return PlaybackException.PlaybackError.UnknownFailure;
        }

        public static /* synthetic */ MediaCollectionType getMediaCollectionType$default(Companion companion, Uri uri, MetricsContext metricsContext, int i, Object obj) {
            if ((i & 2) != 0) {
                metricsContext = null;
            }
            return companion.getMediaCollectionType(uri, metricsContext);
        }

        public final MediaCollectionType getMediaCollectionType(Uri contentUri, MetricsContext metricsContext) {
            if (contentUri == null) {
                MediaCollectionType SONGS = MediaCollectionType.SONGS;
                Intrinsics.checkNotNullExpressionValue(SONGS, "SONGS");
                return SONGS;
            }
            SelectionSourceType selectionSourceType = metricsContext == null ? null : metricsContext.getSelectionSourceType();
            if (selectionSourceType == null) {
                selectionSourceType = SelectionSourceHelper.getSelectionSourceType(contentUri);
            }
            MediaCollectionType mediaCollectionType = SequencerHelper.getMediaCollectionType(selectionSourceType);
            Intrinsics.checkNotNullExpressionValue(mediaCollectionType, "getMediaCollectionType(selectionSourceType)");
            return mediaCollectionType;
        }

        private final String getQueueEntityMetadataId(String luid) {
            if (!(luid == null || luid.length() == 0) && !IdGenerator.isPrimeLuid(luid) && !IdGenerator.isLocalLuid(luid) && StringsKt__StringsKt.contains$default((CharSequence) luid, '-', false, 2, (Object) null)) {
                return luid;
            }
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "{\n                    //…tring()\n                }");
            return string;
        }

        private final ImageMetadata getDefaultImageMetadata(String imageURL) {
            if (imageURL == null) {
                imageURL = "";
            }
            ImageManifest imageManifest = null;
            return new ImageMetadata(null, new FixedSizeImage(null, null, null, imageURL, 7, null), imageManifest, null, 13, null);
        }

        private final CuePoints getCuePoints(Integer durationInSecond) {
            return new CuePoints(0, durationInSecond == null ? 0 : durationInSecond.intValue() * 1000);
        }

        private final List<MusicTrack> getMusicTracksFromContentUri(CirrusSourceLibraryItemFactory cirrusSourceLibrary, Uri uri, String sortOrder) {
            Uri uriBuild = uri.buildUpon().appendPath("tracks").build();
            ArrayList arrayList = new ArrayList();
            Cursor cursorQuery = MediaProvider.getInstance().query(uriBuild, cirrusSourceLibrary.getTrackProjection(), null, null, sortOrder);
            Log.debug(getTAG(), "getMusicTracksFromContentUri collectionUri=" + uriBuild + " sortOrder=" + ((Object) sortOrder));
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            MusicTrack track = cirrusSourceLibrary.getTrack(cursorQuery);
                            Intrinsics.checkNotNullExpressionValue(track, "cirrusSourceLibrary.getTrack(cursor)");
                            arrayList.add(track);
                        } while (cursorQuery.moveToNext());
                        DbUtil.closeCursor(cursorQuery);
                        return CollectionsKt___CollectionsKt.toList(arrayList);
                    }
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }
            Log.warning(getTAG(), Intrinsics.stringPlus("getMusicTracksFromContentUri failed to find collectionUri: ", uriBuild));
            return CollectionsKt__CollectionsKt.emptyList();
        }

        public static /* synthetic */ void startQueueInteractionWithPrimeTracks$default(Companion companion, String str, Context context, List list, int i, boolean z, QueueSeed queueSeed, Uri uri, PlaybackPageType playbackPageType, ControlSource controlSource, boolean z2, NoOpCloudQueueClientHandler noOpCloudQueueClientHandler, boolean z3, int i2, Object obj) {
            ControlSource controlSource2;
            int i3 = (i2 & 8) != 0 ? 0 : i;
            boolean z4 = (i2 & 16) != 0 ? false : z;
            QueueSeed queueSeed2 = (i2 & 32) != 0 ? null : queueSeed;
            Uri uri2 = (i2 & 64) != 0 ? null : uri;
            if ((i2 & 256) != 0) {
                ControlSource APP_UI = ControlSource.APP_UI;
                Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
                controlSource2 = APP_UI;
            } else {
                controlSource2 = controlSource;
            }
            companion.startQueueInteractionWithPrimeTracks(str, context, list, i3, z4, queueSeed2, uri2, playbackPageType, controlSource2, (i2 & 512) != 0 ? true : z2, noOpCloudQueueClientHandler, z3);
        }

        public final EntityReference toEntityReference(QueueEntityMetadata queueEntityMetadata, String capabilitySetId, String str) {
            Intrinsics.checkNotNullParameter(queueEntityMetadata, "<this>");
            Intrinsics.checkNotNullParameter(capabilitySetId, "capabilitySetId");
            String id = queueEntityMetadata.getId();
            String identifier = queueEntityMetadata.getIdentifier();
            QueueEntityIdTypeEnum identifierType = queueEntityMetadata.getIdentifierType();
            if (str == null) {
                str = "";
            }
            return new EntityReference(id, identifier, identifierType, capabilitySetId, str, null);
        }

        @VisibleForTesting(otherwise = 2)
        public final Map<String, String> getTrackToAlbumAsinMap(List<? extends Object> trackList) {
            Pair pair;
            Intrinsics.checkNotNullParameter(trackList, "trackList");
            if (trackList.isEmpty()) {
                Map<String, String> mapEmptyMap = Collections.emptyMap();
                Intrinsics.checkNotNullExpressionValue(mapEmptyMap, "emptyMap()");
                return mapEmptyMap;
            }
            Class<?> cls = trackList.get(0).getClass();
            Map<String, String> map = null;
            if (Intrinsics.areEqual(cls, MusicTrack.class)) {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = trackList.iterator();
                while (it.hasNext()) {
                    MusicTrack musicTrack = (MusicTrack) it.next();
                    String asin = musicTrack.getAsin();
                    Pair pair2 = asin == null ? null : TuplesKt.to(asin, musicTrack.getAlbumAsin());
                    if (pair2 != null) {
                        arrayList.add(pair2);
                    }
                }
                map = MapsKt__MapsKt.toMap(arrayList);
            } else if (Intrinsics.areEqual(cls, TrackItem.class)) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it2 = trackList.iterator();
                while (it2.hasNext()) {
                    TrackItem trackItem = (TrackItem) it2.next();
                    String asin2 = trackItem.getAsin();
                    if (asin2 == null) {
                        pair = null;
                    } else {
                        com.amazon.music.storeservice.model.Album album = trackItem.getAlbum();
                        pair = TuplesKt.to(asin2, album == null ? null : album.getAsin());
                    }
                    if (pair != null) {
                        arrayList2.add(pair);
                    }
                }
                map = MapsKt__MapsKt.toMap(arrayList2);
            } else if (Intrinsics.areEqual(cls, PrimeTrack.class)) {
                ArrayList arrayList3 = new ArrayList();
                Iterator<T> it3 = trackList.iterator();
                while (it3.hasNext()) {
                    PrimeTrack primeTrack = (PrimeTrack) it3.next();
                    String asin3 = primeTrack.getAsin();
                    Pair pair3 = asin3 == null ? null : TuplesKt.to(asin3, primeTrack.getAlbumAsin());
                    if (pair3 != null) {
                        arrayList3.add(pair3);
                    }
                }
                map = MapsKt__MapsKt.toMap(arrayList3);
            } else if (Intrinsics.areEqual(cls, CatalogPlaylistTrack.class)) {
                ArrayList arrayList4 = new ArrayList();
                Iterator<T> it4 = trackList.iterator();
                while (it4.hasNext()) {
                    CatalogPlaylistTrack catalogPlaylistTrack = (CatalogPlaylistTrack) it4.next();
                    String asin4 = catalogPlaylistTrack.getAsin();
                    Pair pair4 = asin4 == null ? null : TuplesKt.to(asin4, catalogPlaylistTrack.getAlbumAsin());
                    if (pair4 != null) {
                        arrayList4.add(pair4);
                    }
                }
                map = MapsKt__MapsKt.toMap(arrayList4);
            } else {
                Log.warning(getTAG(), Intrinsics.stringPlus("getTrackToAlbumAsinMap unsupported track type ", trackList.get(0)));
            }
            if (map != null) {
                return map;
            }
            Map<String, String> mapEmptyMap2 = Collections.emptyMap();
            Intrinsics.checkNotNullExpressionValue(mapEmptyMap2, "emptyMap()");
            return mapEmptyMap2;
        }

        public final String getSelectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease(Uri contentUri) {
            String catalogPlaylistId;
            if (MediaProvider.Station.isStation(contentUri)) {
                if (MediaProvider.Station.isStationFromAnything(contentUri)) {
                    return MediaProvider.Station.getStationFromAnythingSeedId(contentUri);
                }
                return MediaProvider.Station.getKey(contentUri);
            }
            if (!MediaProvider.Playlists.isPlaylist(contentUri) || (catalogPlaylistId = MediaProvider.Playlists.getCatalogPlaylistId(contentUri)) == null) {
                return null;
            }
            return catalogPlaylistId.length() == 0 ? MediaProvider.Playlists.getPlaylistLuid(contentUri) : catalogPlaylistId;
        }

        public final MediaCollectionId getSelectionSourceMediaCollectionId$DigitalMusicAndroid3P_automotiveProdRelease(Map<String, String> metricsContextMap, Uri contentUri) {
            Intrinsics.checkNotNullParameter(metricsContextMap, "metricsContextMap");
            String selectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease = metricsContextMap.get("selectionSourceId");
            if (selectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease == null) {
                selectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease = getSelectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease(contentUri);
            }
            if (selectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease == null) {
                return null;
            }
            return new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, selectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease);
        }

        public static /* synthetic */ MetricsContext getLocalInteractionMetricsContext$default(Companion companion, Context context, Uri uri, PlaybackPageType playbackPageType, String str, CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory, int i, Object obj) {
            if ((i & 16) != 0) {
                cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory(context);
            }
            return companion.getLocalInteractionMetricsContext(context, uri, playbackPageType, str, cirrusSourceLibraryItemFactory);
        }

        public final MetricsContext getLocalInteractionMetricsContext(Context context, Uri contentUri, PlaybackPageType playbackPageType, String queueId, CirrusSourceLibraryItemFactory cirrusFactory) {
            Boolean onDemand;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
            Intrinsics.checkNotNullParameter(cirrusFactory, "cirrusFactory");
            if (contentUri == null) {
                return new MetricsContext(null, null, playbackPageType, Boolean.FALSE, null);
            }
            Uri uriRemoveTracksFromUriIfNeeded = ContentPlaybackUtils.INSTANCE.removeTracksFromUriIfNeeded(contentUri);
            AbstractItem item = cirrusFactory.getItem(uriRemoveTracksFromUriIfNeeded);
            EligibilityUtil eligibilityUtil = new EligibilityUtil(null, 1, 0 == true ? 1 : 0);
            boolean zBooleanValue = false;
            if (item != null && (onDemand = eligibilityUtil.getOnDemand(item)) != null) {
                zBooleanValue = onDemand.booleanValue();
            }
            if (item == null) {
                item = null;
            }
            return new MetricsContext(getSelectionSourceIdFromUri$DigitalMusicAndroid3P_automotiveProdRelease(uriRemoveTracksFromUriIfNeeded), SelectionSourceHelper.getSelectionSourceType(uriRemoveTracksFromUriIfNeeded, item), playbackPageType, Boolean.valueOf(zBooleanValue), queueId);
        }

        public final MetricsContext getOnlineInteractionMetricsContext(List<MetricsContextTuple> metricsContextTuple, PlaybackPageType playbackPageType) {
            Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
            MetricsContext metricsContextFrom = MetricsContext.from(convertMetricsTupleToMap$DigitalMusicAndroid3P_automotiveProdRelease(metricsContextTuple));
            metricsContextFrom.setPageType(playbackPageType);
            Intrinsics.checkNotNullExpressionValue(metricsContextFrom, "from(metricsContextMap).…ackPageType\n            }");
            return metricsContextFrom;
        }

        private final Long getAlbumId(String albumAsin) {
            Uri contentUri = MediaProvider.Albums.getContentUri("cirrus");
            if (albumAsin == null) {
                return null;
            }
            Long l = (Long) CloudQueuePlaybackUtil.albumIdCache.get(albumAsin);
            if (l != null) {
                return l;
            }
            Long albumOrArtistIdByAsin = CirrusMediaSource.getAlbumOrArtistIdByAsin(AmazonApplication.getApplication().getApplicationContext(), contentUri, albumAsin);
            if (albumOrArtistIdByAsin != null) {
            }
            return albumOrArtistIdByAsin;
        }
    }

    static {
        PublishSubject<Companion.PlaybackContext> publishSubjectCreate = PublishSubject.create();
        publishSubjectCreate.debounce(300L, TimeUnit.MILLISECONDS).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CloudQueuePlaybackUtil.m685playbackObservable$lambda1$lambda0((CloudQueuePlaybackUtil.Companion.PlaybackContext) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create<PlaybackContext?>…)\n            }\n        }");
        playbackObservable = publishSubjectCreate;
        cacheUdoPlaylistLuid = new LinkedHashMap();
        cacheAllContentAccessTypes$delegate = LazyKt__LazyJVMKt.lazy(new Function0<Set<? extends ContentAccessType>>() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$cacheAllContentAccessTypes$2
            @Override // kotlin.jvm.functions.Function0
            public final Set<? extends ContentAccessType> invoke() {
                ContentAccessType[] allContentAccessTypeValues = ContentAccessUtil.getAllContentAccessTypeValues();
                Intrinsics.checkNotNullExpressionValue(allContentAccessTypeValues, "getAllContentAccessTypeValues()");
                return ArraysKt___ArraysKt.toSet(allContentAccessTypeValues);
            }
        });
        supportedFeaturesEnumsOnline = CollectionsKt__CollectionsKt.listOf((Object[]) new SupportedFeatureEnum[]{SupportedFeatureEnum.VIDEO, SupportedFeatureEnum.VISUAL_LIKES, SupportedFeatureEnum.SONIC_RUSH, SupportedFeatureEnum.FREE_TIER_MEEL});
        albumIdCache = new LruCache<>(10);
        serviceTier$delegate = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil$Companion$serviceTier$2
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                ServiceTierEnum serviceTier = CloudQueueDigitalXPUtil.INSTANCE.getServiceTier();
                if (serviceTier == null) {
                    return null;
                }
                return serviceTier.name();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playbackObservable$lambda-1$lambda-0, reason: not valid java name */
    public static final void m685playbackObservable$lambda1$lambda0(Companion.PlaybackContext playbackContext) {
        INSTANCE.startNowPlayingScreenFromMainThread(playbackContext.getContext(), playbackContext.getIsStation(), playbackContext.getContentUri(), playbackContext.getUpsellInformation());
    }
}
