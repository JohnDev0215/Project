package com.amazon.mp3.catalog.fragment;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.home.HomeFragmentUtils;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.recents.RecentsFragment;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.Target;
import com.amazon.music.destination.TargetType;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.podcast.PodcastDeeplinks;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: DefaultMetadataClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\b\u0016\u0018\u0000 E2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001EB#\b\u0007\u0012\u0006\u0010.\u001a\u00020\u0013\u0012\u0006\u00103\u001a\u000202\u0012\b\b\u0002\u00106\u001a\u000205¢\u0006\u0004\bC\u0010DJ\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J6\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fH\u0002J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002JG\u0010\u001d\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0002J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010!\u001a\u00020 H\u0002J\u001a\u0010#\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0002J\u0018\u0010%\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\bH\u0002J\u0018\u0010(\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\bH\u0016J(\u0010+\u001a\u00020\u00112\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010)\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010\u0018H\u0016J\u001c\u0010,\u001a\u00020\u00112\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010)\u001a\u00020\nJ\u0010\u0010-\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u001a\u0010.\u001a\u00020\u00138\u0004X\u0084\u0004¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0017\u00106\u001a\u0002058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u001e\u0010>\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010=8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010B¨\u0006F"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/DefaultMetadataClickListener;", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Lcom/amazon/music/destination/Target;", "getTarget", "", "shouldHandlePlayBackContainer", "", "position", "queueSize", "", "contentMetadataList", "Lcom/amazon/mp3/library/provider/StateProvider$Listener;", "stateChangedListener", "", "createCircularQueue", "Landroidx/fragment/app/Fragment;", "fragment", "navigateToLibraryFragment", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "", "blockRef", ContextMappingConstants.ENTITY_ID, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "sendClickEvent", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;)V", ImagesContract.URL, "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "albumMetadata", "isEligibleForReduceFrictionAlbumUpsell", "sendPodcastClickEvent", "isEpisode", "getPodcastActionType", "item", "canContinue", "continueCatalogAction", "positionToStart", "contentListTitle", "onContentListClicked", "beginPlayback", "onContentClicked", "mFragment", "Landroidx/fragment/app/Fragment;", "getMFragment", "()Landroidx/fragment/app/Fragment;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "taskType", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "getTaskType", "()Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "Lcom/amazon/mp3/library/adapter/DefaultTrackStateProvider;", "mDefaultTrackStateProvider", "Lcom/amazon/mp3/library/adapter/DefaultTrackStateProvider;", "", "mTracks", "Ljava/util/List;", "", "mStartTime", "J", "<init>", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class DefaultMetadataClickListener implements ContentMetadataOnClickListener, ContentAccessUtil.CatalogActionListener<PrimeTrack> {
    private final DefaultTrackStateProvider mDefaultTrackStateProvider;
    private final Fragment mFragment;
    private long mStartTime;
    private List<PrimeTrack> mTracks;
    private final PageType pageType;
    private final PrimeCollectionTask.Task taskType;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public DefaultMetadataClickListener(Fragment mFragment, PageType pageType) {
        this(mFragment, pageType, null, 4, null);
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
    }

    @JvmOverloads
    public DefaultMetadataClickListener(Fragment mFragment, PageType pageType, PrimeCollectionTask.Task taskType) {
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        this.mFragment = mFragment;
        this.pageType = pageType;
        this.taskType = taskType;
        this.mDefaultTrackStateProvider = new DefaultTrackStateProvider(mFragment.requireContext());
    }

    protected final Fragment getMFragment() {
        return this.mFragment;
    }

    public /* synthetic */ DefaultMetadataClickListener(Fragment fragment, PageType pageType, PrimeCollectionTask.Task task, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragment, pageType, (i & 4) != 0 ? PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN : task);
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(PrimeTrack item, boolean canContinue) {
        LoaderManager supportLoaderManager;
        Intrinsics.checkNotNullParameter(item, "item");
        if (canContinue) {
            PrimeTracksLoadedCallbacks primeTracksLoadedCallbacks = new PrimeTracksLoadedCallbacks(this.mFragment.getActivity(), this.mTracks, 0, PlaybackPageType.SONGS_LIST, this.mStartTime, this.taskType);
            FragmentActivity activity = this.mFragment.getActivity();
            if (activity == null || (supportLoaderManager = activity.getSupportLoaderManager()) == null) {
                return;
            }
            supportLoaderManager.initLoader(0, null, primeTracksLoadedCallbacks);
        }
    }

    private final Target getTarget(ContentMetadata contentMetadata) {
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

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (contentMetadataList.isEmpty()) {
            return;
        }
        if (CastingUtil.isCasting() && !CastingUtil.isSupportedContent((List<ContentMetadata>) contentMetadataList)) {
            CastingUtil.showCastingContentUnavailable(this.mFragment.getContext());
        } else if (shouldHandlePlayBackContainer(contentMetadataList.get(positionToStart))) {
            sendClickEvent$default(this, ActionType.PLAY, contentMetadataList.get(positionToStart).getViewId(), null, null, null, 28, null);
        } else {
            beginPlayback(contentMetadataList, positionToStart);
        }
    }

    private final boolean shouldHandlePlayBackContainer(ContentMetadata contentMetadata) {
        String asin;
        if (contentMetadata instanceof TrackMetadata) {
            TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
            EntityEligibilitiesProvider parentEligibilities = trackMetadata == null ? null : trackMetadata.getParentEligibilities();
            if ((parentEligibilities instanceof PlaylistMetadata) && (asin = trackMetadata.getAsin()) != null) {
                HomeFragmentUtils.navigateToPlaylistDetail(getMFragment().getContext(), (PlaylistMetadata) parentEligibilities, asin);
                return true;
            }
        }
        return false;
    }

    public final void beginPlayback(final List<? extends ContentMetadata> contentMetadataList, final int positionToStart) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        this.mStartTime = Clock.now();
        if (!UserSubscriptionUtil.isNightwingOnly()) {
            Playback.getInstance().getPlaybackController(ControlSource.APP_UI).stop(ChangeReason.NEW_SOURCE);
        }
        int iMin = Math.min(PrimeTracksLoadedCallbacks.getMaxQueueSize(), contentMetadataList.size());
        PlayQueueSequencer.getInstance().getBackgroundExecutor().execute(new Runnable() { // from class: com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultMetadataClickListener.m474beginPlayback$lambda1();
            }
        });
        this.mTracks = new ArrayList(iMin);
        createCircularQueue(positionToStart, iMin, contentMetadataList, new StateProvider.Listener() { // from class: com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public final void onStateChanged(Object obj, int i, int i2) {
                DefaultMetadataClickListener.m475beginPlayback$lambda2(this.f$0, contentMetadataList, positionToStart, (PrimeTrack) obj, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: beginPlayback$lambda-1, reason: not valid java name */
    public static final void m474beginPlayback$lambda1() {
        PlayQueueSequencer.getInstance().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: beginPlayback$lambda-2, reason: not valid java name */
    public static final void m475beginPlayback$lambda2(DefaultMetadataClickListener this$0, List contentMetadataList, int i, PrimeTrack primeTrack, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentMetadataList, "$contentMetadataList");
        List<PrimeTrack> list = this$0.mTracks;
        if ((list == null || list.isEmpty()) || !this$0.mFragment.isAdded() || this$0.mFragment.getActivity() == null) {
            return;
        }
        List<PrimeTrack> list2 = this$0.mTracks;
        PrimeTrack primeTrack2 = list2 == null ? null : (PrimeTrack) CollectionsKt___CollectionsKt.firstOrNull((List) list2);
        if (primeTrack2 != null && primeTrack2.getAssetType().equals(PrimePlaylistTracksTable.AssetType.VIDEO)) {
            this$0.sendClickEvent(ActionType.PLAY_VIDEO, ((ContentMetadata) contentMetadataList.get(i)).getViewId(), primeTrack2.getAsin(), Integer.valueOf(i), EntityIdType.ASIN);
        } else {
            this$0.sendClickEvent(ActionType.PLAY_SONG, ((ContentMetadata) contentMetadataList.get(i)).getViewId(), primeTrack2 == null ? null : primeTrack2.getAsin(), Integer.valueOf(i), EntityIdType.ASIN);
        }
        ContentAccessUtil.checkCanContinueCatalogAction(this$0.mFragment.getActivity(), primeTrack2, ContentAccessUtil.ContentAccessOperation.STREAM, this$0);
    }

    private final void createCircularQueue(int position, int queueSize, List<? extends ContentMetadata> contentMetadataList, StateProvider.Listener<PrimeTrack> stateChangedListener) {
        int i = position;
        int i2 = 0;
        while (i2 < queueSize) {
            if (i == contentMetadataList.size()) {
                i = 0;
            }
            ContentMetadata contentMetadata = contentMetadataList.get(i);
            if (contentMetadata != null) {
                if (contentMetadata instanceof TrackMetadata) {
                    PrimeTrack primeTrackConvertToPrimeTrack = PrimeItemsTransformationUtil.convertToPrimeTrack((TrackMetadata) contentMetadata);
                    Intrinsics.checkNotNullExpressionValue(primeTrackConvertToPrimeTrack, "convertToPrimeTrack(metadata)");
                    if (i == position) {
                        this.mDefaultTrackStateProvider.requestState(primeTrackConvertToPrimeTrack, stateChangedListener);
                    } else {
                        this.mDefaultTrackStateProvider.requestState(primeTrackConvertToPrimeTrack, (StateProvider.Listener<PrimeTrack>) null);
                    }
                    List<PrimeTrack> list = this.mTracks;
                    if (list != null) {
                        list.add(primeTrackConvertToPrimeTrack);
                    }
                }
                i++;
                i2++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:178:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02ba  */
    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onContentClicked(final com.amazon.music.views.library.metadata.ContentMetadata r20) throws java.util.concurrent.ExecutionException, java.lang.InterruptedException, java.net.MalformedURLException, com.amazon.music.deeplink.UnknownDeeplinkException, java.net.URISyntaxException, java.io.UnsupportedEncodingException, com.amazon.music.router.UnknownDestinationException {
        /*
            Method dump skipped, instruction units count: 1314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener.onContentClicked(com.amazon.music.views.library.metadata.ContentMetadata):void");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener$onContentClicked$3, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultMetadataClickListener.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener$onContentClicked$3", f = "DefaultMetadataClickListener.kt", i = {}, l = {416}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ContentMetadata $contentMetadata;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(ContentMetadata contentMetadata, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$contentMetadata = contentMetadata;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultMetadataClickListener.this.new AnonymousClass3(this.$contentMetadata, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            PageType pageType;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Context context = DefaultMetadataClickListener.this.getMFragment().getContext();
                if (context != null) {
                    ContentMetadata contentMetadata = this.$contentMetadata;
                    DefaultMetadataClickListener defaultMetadataClickListener = DefaultMetadataClickListener.this;
                    RecentlyPlayedItem recentItem = RecentlyPlayedManager.getInstance(context).toRecentItem((RecentsMetadata) contentMetadata);
                    String cirrusMediaSource = LastViewedScreenUtil.getLastViewedSource(context, LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
                    if (!(defaultMetadataClickListener.getMFragment() instanceof RecentsFragment)) {
                        pageType = null;
                    } else if (Intrinsics.areEqual("cirrus", cirrusMediaSource)) {
                        pageType = PageType.MY_MUSIC_CLOUD_FRONT_PAGE;
                    } else {
                        pageType = PageType.MY_MUSIC_OFFLINE_FRONT_PAGE;
                    }
                    PageType pageType2 = pageType;
                    HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    DefaultMetadataClickListener$onContentClicked$3$1$1 defaultMetadataClickListener$onContentClicked$3$1$1 = new DefaultMetadataClickListener$onContentClicked$3$1$1(defaultMetadataClickListener, recentItem, pageType2, contentMetadata, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main2, defaultMetadataClickListener$onContentClicked$3$1$1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void navigateToLibraryFragment(Fragment fragment) {
        FragmentActivity activity = this.mFragment.getActivity();
        if (activity == null) {
            return;
        }
        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out).replace(R.id.music_screen_container, fragment).addToBackStack(fragment.getClass().getSimpleName()).commitAllowingStateLoss();
    }

    static /* synthetic */ void sendClickEvent$default(DefaultMetadataClickListener defaultMetadataClickListener, ActionType actionType, String str, String str2, Integer num, EntityIdType entityIdType, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendClickEvent");
        }
        defaultMetadataClickListener.sendClickEvent(actionType, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : entityIdType);
    }

    private final void sendClickEvent(ActionType actionType, String blockRef, String entityId, Integer position, EntityIdType entityIdType) {
        MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
        UiClickEvent.Builder builderWithPageType = UiClickEvent.builder(actionType).withBlockRef(blockRef).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(this.pageType);
        if (entityId != null) {
            builderWithPageType.withEntityId(entityId);
        }
        if (position != null) {
            builderWithPageType.withEntityPos(position.intValue());
        }
        if (entityIdType != null) {
            builderWithPageType.withEntityIdType(entityIdType);
        }
        MetricsHolder.getManager().handleEvent(builderWithPageType.build());
    }

    private final void sendClickEvent(String url, String blockRef) {
        if (PodcastDeeplinks.isPodcastDeeplink(url)) {
            sendPodcastClickEvent(url, blockRef);
        } else {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(url, blockRef).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }

    private final boolean isEligibleForReduceFrictionAlbumUpsell(AlbumMetadata albumMetadata) {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Set<ContentAccessType> supportedTiers = albumMetadata.getSupportedTiers();
        return AccountDetailUtil.get(AmazonApplication.getContext()).isUserInHawkfireMarketplace() && supportedTiers.contains(ContentAccessType.HAWKFIRE) && ((userSubscription.isNightwingOnly() && !supportedTiers.contains(ContentAccessType.NIGHTWING) && this.pageType == PageType.BROWSE_HOME) || (userSubscription.isPrimeOnly() && !supportedTiers.contains(ContentAccessType.PRIME)));
    }

    private final void sendPodcastClickEvent(String url, String blockRef) {
        ActionType podcastActionType = getPodcastActionType(url, PodcastDeeplinks.isPodcastEpisodeDeeplink(url));
        if (PodcastDeeplinks.isPodcastSaves(url) || PodcastDeeplinks.isPodcastJumpBackIn(url)) {
            HashMap map = new HashMap();
            map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            map.put("contentName", PodcastDeeplinks.isPodcastSaves(url) ? "podcastsSavesShoveler" : "podcastsJumpBackInShoveler");
            if (blockRef == null) {
                blockRef = PodcastDeeplinks.getEpisodeId(url);
            }
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(podcastActionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(PageType.BROWSE_HOME).withEntityId(blockRef).withEntityIdType(EntityIdType.PODCAST_EPISODE_ID).withEntityType(EntityType.PODCAST_EPISODE).withContentInfo(CollectionsKt__CollectionsJVMKt.listOf(map)).build());
            return;
        }
        if (blockRef != null) {
            sendClickEvent$default(this, podcastActionType, blockRef, null, null, null, 28, null);
            return;
        }
        HashMap map2 = new HashMap();
        map2.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map2.put("contentName", "podcastsFollowsShoveler");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(podcastActionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(PageType.BROWSE_HOME).withEntityType(EntityType.PODCAST_SHOW).withEntityId(PodcastDeeplinks.getPodcastId(url)).withEntityIdType(EntityIdType.PODCAST_SHOW_ID).withContentInfo(CollectionsKt__CollectionsJVMKt.listOf(map2)).build());
    }

    private final ActionType getPodcastActionType(String url, boolean isEpisode) {
        boolean zIsPodcastPlayback = PodcastDeeplinks.isPodcastPlayback(url);
        if (isEpisode) {
            if (zIsPodcastPlayback) {
                return ActionType.PLAY_PODCAST_EPISODE;
            }
            return ActionType.SELECT_PODCAST_EPISODE;
        }
        if (zIsPodcastPlayback) {
            return ActionType.PLAY_PODCAST_SHOW;
        }
        return ActionType.SELECT_PODCAST_SHOW;
    }
}
