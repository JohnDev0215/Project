package com.amazon.mp3.find.util;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.ondemandplaylists.OnDemandPlaylistSynchronizer;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.reactnative.ReactExperienceNavigationRouteHelper;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.search.view.SearchFragmentUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.PlaybackUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: BaseSearchTrackPlaybackHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\b\u0010\b&\u0018\u0000 h2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001hB\u001f\u0012\u0006\u00107\u001a\u00020\u000b\u0012\u0006\u0010:\u001a\u000209\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\bf\u0010gJ\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002J\u0012\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002JB\u0010\u0016\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0002J>\u0010 \u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0005H\u0002JB\u0010!\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0002J\u001a\u0010$\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u001b2\u0006\u0010#\u001a\u00020\u0005H\u0002J\u0010\u0010%\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002H\u0002J<\u0010*\u001a\u00020\t2\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0017\u0018\u00010&2\u0006\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u0018\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+2\b\u0010\"\u001a\u0004\u0018\u00010\u001bH\u0002J4\u0010.\u001a\u00020\u00052\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0017\u0018\u00010&2\u0006\u0010(\u001a\u00020\u00022\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\b\u0010/\u001a\u00020\u0012H\u0014J&\u00102\u001a\u00020\t2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010)\u001a\u00020\u00172\u0006\u00101\u001a\u000200H\u0016J\u0018\u00105\u001a\u00020\t2\u0006\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u0005H\u0016J.\u00106\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u00022\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010)\u001a\u00020\u00172\u0006\u00101\u001a\u000200H\u0016R\u0014\u00107\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010<R\u001a\u0010=\u001a\u00020\u00058\u0016X\u0096D¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u001a\u0010A\u001a\u00020\u00058\u0016X\u0096D¢\u0006\f\n\u0004\bA\u0010>\u001a\u0004\bB\u0010@R\u001a\u0010C\u001a\u00020\u00058\u0016X\u0096D¢\u0006\f\n\u0004\bC\u0010>\u001a\u0004\bD\u0010@R\u001a\u0010E\u001a\u00020\u00058\u0016X\u0096D¢\u0006\f\n\u0004\bE\u0010>\u001a\u0004\bF\u0010@R\u001a\u0010G\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020\u00058\u0016X\u0096D¢\u0006\f\n\u0004\bK\u0010>\u001a\u0004\bL\u0010@R\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010RR*\u0010S\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR0\u0010Z\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0017\u0018\u00010Y8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R$\u0010`\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b`\u0010a\u001a\u0004\bb\u0010c\"\u0004\bd\u0010e¨\u0006i"}, d2 = {"Lcom/amazon/mp3/find/util/BaseSearchTrackPlaybackHandler;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "relatedEligibility", "", "shouldStartPlaybackWithRelatedContext", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "trackMetadata", "", "sendEntityVisitEntityForNestedTrack", "Landroidx/fragment/app/Fragment;", "fragment", "", "tracks", "track", "", "startTime", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "taskType", "startNestedTrackPlayback", "", "position", "queueSize", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadataList", "Lcom/amazon/mp3/library/provider/StateProvider$Listener;", "stateChangedListener", "enableCircularQueue", "createCircularQueue", "routeToTrackDetailPageAndStartPlayback", "contentMetadata", "isOffline", "shouldStartNoOpCloudQueuePlayback", "playbackFallbackHandling", "", "trackList", "currentTrack", "positionToStart", "saveTrackPlaybackState", "", "Lcom/amazon/music/ContentAccessType;", "getSupportedTiers", "hawkfireContentIsOutOfTier", "getPlaybackPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "playTrackMetadata", "item", "canContinue", "continueCatalogAction", "startPlayback", "mFragment", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "useDefaultFallbackHandling", "Z", "getUseDefaultFallbackHandling", "()Z", "shouldEnableCircularQueue", "getShouldEnableCircularQueue", "shouldPlayFullscreen", "getShouldPlayFullscreen", "shouldShowTrackDetailsPage", "getShouldShowTrackDetailsPage", "primeCollectionTask", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "getPrimeCollectionTask", "()Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "shouldTrackEntitiesForBlendedSearchHistory", "getShouldTrackEntitiesForBlendedSearchHistory", "Lcom/amazon/mp3/library/adapter/DefaultTrackStateProvider;", "mDefaultTrackStateProvider", "Lcom/amazon/mp3/library/adapter/DefaultTrackStateProvider;", "Lcom/amazon/mp3/util/EligibilityUtil;", "eligibilityUtil", "Lcom/amazon/mp3/util/EligibilityUtil;", "mTracks", "Ljava/util/List;", "getMTracks", "()Ljava/util/List;", "setMTracks", "(Ljava/util/List;)V", "", "mTrackPositions", "Ljava/util/Map;", "getMTrackPositions", "()Ljava/util/Map;", "setMTrackPositions", "(Ljava/util/Map;)V", "mTrackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "getMTrackMetadata", "()Lcom/amazon/music/views/library/metadata/TrackMetadata;", "setMTrackMetadata", "(Lcom/amazon/music/views/library/metadata/TrackMetadata;)V", "<init>", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseSearchTrackPlaybackHandler implements ContentAccessUtil.CatalogActionListener<PrimeTrack> {
    private static final Logger logger;
    private final EligibilityUtil eligibilityUtil;
    private final DefaultTrackStateProvider mDefaultTrackStateProvider;
    private final Fragment mFragment;
    private TrackMetadata mTrackMetadata;
    private Map<PrimeTrack, Integer> mTrackPositions;
    private List<PrimeTrack> mTracks;
    private final PageType pageType;
    private final PlaybackPageType playbackPageType;
    private final PrimeCollectionTask.Task primeCollectionTask;
    private final boolean shouldEnableCircularQueue;
    private final boolean shouldPlayFullscreen;
    private final boolean shouldShowTrackDetailsPage;
    private final boolean shouldTrackEntitiesForBlendedSearchHistory;
    private final boolean useDefaultFallbackHandling;

    public BaseSearchTrackPlaybackHandler(Fragment mFragment, PageType pageType, PlaybackPageType playbackPageType) {
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.mFragment = mFragment;
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
        this.useDefaultFallbackHandling = true;
        this.shouldPlayFullscreen = true;
        this.primeCollectionTask = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
        this.shouldTrackEntitiesForBlendedSearchHistory = true;
        this.mDefaultTrackStateProvider = new DefaultTrackStateProvider(mFragment.requireContext());
        this.eligibilityUtil = new EligibilityUtil(null, 1, 0 == true ? 1 : 0);
    }

    public boolean getUseDefaultFallbackHandling() {
        return this.useDefaultFallbackHandling;
    }

    public boolean getShouldEnableCircularQueue() {
        return this.shouldEnableCircularQueue;
    }

    public boolean getShouldPlayFullscreen() {
        return this.shouldPlayFullscreen;
    }

    public boolean getShouldShowTrackDetailsPage() {
        return this.shouldShowTrackDetailsPage;
    }

    public PrimeCollectionTask.Task getPrimeCollectionTask() {
        return this.primeCollectionTask;
    }

    public boolean getShouldTrackEntitiesForBlendedSearchHistory() {
        return this.shouldTrackEntitiesForBlendedSearchHistory;
    }

    public final List<PrimeTrack> getMTracks() {
        return this.mTracks;
    }

    public final Map<PrimeTrack, Integer> getMTrackPositions() {
        return this.mTrackPositions;
    }

    public final TrackMetadata getMTrackMetadata() {
        return this.mTrackMetadata;
    }

    static {
        Logger logger2 = LoggerFactory.getLogger((Class<?>) BaseSearchTrackPlaybackHandler.class);
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(BaseSearchTrac…ybackHandler::class.java)");
        logger = logger2;
    }

    protected PlaybackPageType getPlaybackPageType() {
        return this.playbackPageType;
    }

    public void playTrackMetadata(final List<? extends ContentMetadata> contentMetadataList, final int positionToStart, final InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        Fragment fragment = this.mFragment;
        PageType pageType = this.pageType;
        ContentMetadata contentMetadata = contentMetadataList.get(positionToStart);
        SearchClickEventUtil.handleTrackClickEvent(fragment, pageType, contentMetadata instanceof TrackMetadata ? (TrackMetadata) contentMetadata : null, Integer.valueOf(positionToStart), interactionType);
        boolean z = false;
        boolean z2 = positionToStart >= 0 && positionToStart < contentMetadataList.size();
        if (contentMetadataList.isEmpty() || !z2) {
            return;
        }
        int iMin = Math.min(PrimeTracksLoadedCallbacks.getMaxQueueSize(), contentMetadataList.size());
        this.mTracks = new ArrayList(iMin);
        this.mTrackPositions = new HashMap();
        OnDemandPlaylistSynchronizer onDemandPlaylistSynchronizer = OnDemandPlaylistSynchronizer.INSTANCE;
        ContentMetadata contentMetadata2 = contentMetadataList.get(positionToStart);
        onDemandPlaylistSynchronizer.syncOnDemandPlaylists(contentMetadata2 instanceof TrackMetadata ? (TrackMetadata) contentMetadata2 : null);
        StateProvider.Listener<PrimeTrack> listener = new StateProvider.Listener() { // from class: com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler$$ExternalSyntheticLambda0
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public final void onStateChanged(Object obj, int i, int i2) {
                BaseSearchTrackPlaybackHandler.m993playTrackMetadata$lambda0(this.f$0, positionToStart, contentMetadataList, interactionType, (PrimeTrack) obj, i, i2);
            }
        };
        boolean zIsEnabled = FindFeatureGating.TRACK_SEARCH_SFA.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
        Fragment fragment2 = this.mFragment;
        SearchBrushFragment searchBrushFragment = fragment2 instanceof SearchBrushFragment ? (SearchBrushFragment) fragment2 : null;
        boolean z3 = searchBrushFragment != null && searchBrushFragment.isTrackSearch();
        if (!getShouldEnableCircularQueue() || (zIsEnabled && z3)) {
            z = true;
        }
        createCircularQueue(positionToStart, iMin, contentMetadataList, listener, !z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playTrackMetadata$lambda-0, reason: not valid java name */
    public static final void m993playTrackMetadata$lambda0(BaseSearchTrackPlaybackHandler this$0, int i, List contentMetadataList, InteractionType interactionType, PrimeTrack currentTrack, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentMetadataList, "$contentMetadataList");
        Intrinsics.checkNotNullParameter(interactionType, "$interactionType");
        List<PrimeTrack> list = this$0.mTracks;
        if ((list == null || list.isEmpty()) || !this$0.mFragment.isAdded() || this$0.mFragment.getActivity() == null) {
            return;
        }
        Map<PrimeTrack, Integer> map = this$0.mTrackPositions;
        Intrinsics.checkNotNullExpressionValue(currentTrack, "currentTrack");
        this$0.saveTrackPlaybackState(map, currentTrack, i, contentMetadataList);
        this$0.startPlayback(currentTrack, contentMetadataList, i, interactionType);
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(final PrimeTrack item, boolean canContinue) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(item, "item");
        if (canContinue) {
            if (getShouldShowTrackDetailsPage()) {
                routeToTrackDetailPageAndStartPlayback$default(this, this.mFragment, this.mTracks, item, 0L, PlaybackPageType.TRACK_DETAIL, null, 32, null);
            } else {
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseSearchTrackPlaybackHandler.m992continueCatalogAction$lambda1(this.f$0, item);
                    }
                });
            }
        } else if (!getUseDefaultFallbackHandling()) {
            playbackFallbackHandling(item);
        }
        if (getShouldTrackEntitiesForBlendedSearchHistory()) {
            if (canContinue || SearchItemUtils.userIsNightwingOnly()) {
                sendEntityVisitEntityForNestedTrack(this.mTrackMetadata);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: continueCatalogAction$lambda-1, reason: not valid java name */
    public static final void m992continueCatalogAction$lambda1(BaseSearchTrackPlaybackHandler this$0, PrimeTrack item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.startNestedTrackPlayback(this$0.mFragment, this$0.mTracks, item, 0L, this$0.getPlaybackPageType(), this$0.getPrimeCollectionTask());
    }

    private final boolean shouldStartPlaybackWithRelatedContext(EntityEligibilitiesProvider relatedEligibility) {
        Map<String, Eligibility> eligibilities;
        Eligibility eligibility = (relatedEligibility == null || (eligibilities = relatedEligibility.getEligibilities()) == null) ? null : eligibilities.get("PLAYMODES");
        PlaymodeEligibility playmodeEligibility = eligibility instanceof PlaymodeEligibility ? (PlaymodeEligibility) eligibility : null;
        return playmodeEligibility != null && playmodeEligibility.isOnDemand();
    }

    private final void sendEntityVisitEntityForNestedTrack(TrackMetadata trackMetadata) {
        ContentMetadata containerMetadata = trackMetadata == null ? null : trackMetadata.getContainerMetadata();
        EntityEligibilitiesProvider entityEligibilitiesProvider = containerMetadata instanceof EntityEligibilitiesProvider ? (EntityEligibilitiesProvider) containerMetadata : null;
        if (shouldStartPlaybackWithRelatedContext(entityEligibilitiesProvider)) {
            if (entityEligibilitiesProvider instanceof PlaylistMetadata) {
                EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), (PlaylistMetadata) entityEligibilitiesProvider, this.mFragment instanceof SearchBrushFragment);
            }
        } else {
            if (trackMetadata == null) {
                return;
            }
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), trackMetadata, this.mFragment instanceof SearchBrushFragment);
        }
    }

    private final void startNestedTrackPlayback(Fragment fragment, List<PrimeTrack> tracks, PrimeTrack track, long startTime, PlaybackPageType playbackPageType, PrimeCollectionTask.Task taskType) {
        EntityEligibilitiesProvider parentEligibilities = track.getParentEligibilities();
        if (shouldStartPlaybackWithRelatedContext(parentEligibilities)) {
            if (parentEligibilities instanceof PlaylistMetadata) {
                String asin = track.getAsin();
                if (asin == null) {
                    return;
                }
                SearchFragmentUtils.navigateToPlaylistDetail(fragment.getContext(), (PlaylistMetadata) parentEligibilities, asin);
                return;
            }
            logger.info("Unsupported or no parent EntityEligibilitiesProvider. Starting playback for only track.");
        }
        PlaybackUtils.startTrackPlayback(fragment, tracks, track, startTime, playbackPageType, taskType);
    }

    private final void createCircularQueue(int position, int queueSize, List<? extends ContentMetadata> contentMetadataList, StateProvider.Listener<PrimeTrack> stateChangedListener, boolean enableCircularQueue) {
        TrackMetadata trackMetadata = (TrackMetadata) CollectionsKt___CollectionsKt.getOrNull(contentMetadataList, position);
        String assetType = trackMetadata == null ? null : trackMetadata.getAssetType();
        int i = position;
        int i2 = 0;
        while (i2 < queueSize) {
            if (i == contentMetadataList.size()) {
                i = 0;
            }
            ContentMetadata contentMetadata = contentMetadataList.get(i);
            if (contentMetadata != null) {
                if (contentMetadata instanceof TrackMetadata) {
                    TrackMetadata trackMetadata2 = (TrackMetadata) contentMetadata;
                    if (Intrinsics.areEqual(trackMetadata2.getAssetType(), assetType)) {
                        PrimeTrack primeTrackConvertToPrimeTrack = PrimeItemsTransformationUtil.convertToPrimeTrack(trackMetadata2);
                        Intrinsics.checkNotNullExpressionValue(primeTrackConvertToPrimeTrack, "convertToPrimeTrack(metadata)");
                        if (i == position) {
                            this.mDefaultTrackStateProvider.requestState(primeTrackConvertToPrimeTrack, stateChangedListener);
                        } else {
                            this.mDefaultTrackStateProvider.requestState(primeTrackConvertToPrimeTrack, (StateProvider.Listener<PrimeTrack>) null);
                        }
                        List<PrimeTrack> mTracks = getMTracks();
                        if (mTracks != null) {
                            mTracks.add(primeTrackConvertToPrimeTrack);
                        }
                        Map<PrimeTrack, Integer> mTrackPositions = getMTrackPositions();
                        if (mTrackPositions != null) {
                            mTrackPositions.put(primeTrackConvertToPrimeTrack, Integer.valueOf(i));
                        }
                    }
                }
                i++;
                i2++;
            }
            if (!enableCircularQueue) {
                return;
            }
        }
    }

    static /* synthetic */ void routeToTrackDetailPageAndStartPlayback$default(BaseSearchTrackPlaybackHandler baseSearchTrackPlaybackHandler, Fragment fragment, List list, PrimeTrack primeTrack, long j, PlaybackPageType playbackPageType, PrimeCollectionTask.Task task, int i, Object obj) throws ExecutionException, InterruptedException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: routeToTrackDetailPageAndStartPlayback");
        }
        baseSearchTrackPlaybackHandler.routeToTrackDetailPageAndStartPlayback(fragment, list, primeTrack, j, playbackPageType, (i & 32) != 0 ? PrimeCollectionTask.Task.PLAYBACK : task);
    }

    private final void routeToTrackDetailPageAndStartPlayback(Fragment fragment, List<PrimeTrack> tracks, PrimeTrack track, long startTime, PlaybackPageType playbackPageType, PrimeCollectionTask.Task taskType) throws ExecutionException, InterruptedException {
        if (FMPMFeatureGating.SHOW_TRACK_DETAILS_REACT_NATIVE_PAGE.isEnabled()) {
            String asin = track.getAsin();
            FragmentActivity fragmentActivityRequireActivity = this.mFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "mFragment.requireActivity()");
            ReactExperienceNavigationRouteHelper.navigateToTrackDetailPageRoute(asin, fragmentActivityRequireActivity);
        } else {
            SearchFragmentUtils.navigateToTrackDetail(this.mFragment.getContext(), this.mTrackMetadata);
        }
        PlaybackUtils.startTrackPlayback(fragment, tracks, track, startTime, playbackPageType, taskType);
    }

    public void startPlayback(PrimeTrack currentTrack, List<? extends ContentMetadata> contentMetadataList, int positionToStart, InteractionType interactionType) {
        boolean zIsOffline;
        TrackMetadata trackMetadata;
        Intrinsics.checkNotNullParameter(currentTrack, "currentTrack");
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        Map<PrimeTrack, Integer> map = this.mTrackPositions;
        Integer num = map == null ? null : map.get(currentTrack);
        ContentMetadata contentMetadata = num != null ? contentMetadataList.get(num.intValue()) : null;
        boolean z = contentMetadata instanceof TrackMetadata;
        if (z) {
            this.mTrackMetadata = (TrackMetadata) contentMetadata;
        }
        TrackMetadata trackMetadata2 = z ? (TrackMetadata) contentMetadata : null;
        Long trackId = trackMetadata2 != null ? trackMetadata2.getTrackId() : null;
        if (this.mFragment.getContext() != null) {
            Context contextRequireContext = this.mFragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "mFragment.requireContext()");
            zIsOffline = SearchItemUtils.isOffline(contextRequireContext);
        } else {
            zIsOffline = false;
        }
        if (trackId != null) {
            trackId.longValue();
            if (shouldStartNoOpCloudQueuePlayback(contentMetadata, zIsOffline)) {
                Context context = this.mFragment.getContext();
                if (context != null && (trackMetadata = (TrackMetadata) contentMetadata) != null) {
                    PlaybackUtils.INSTANCE.startLibraryTrackPlayback(context, trackMetadata);
                }
                EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), (TrackMetadata) contentMetadata, this.mFragment instanceof SearchBrushFragment);
                return;
            }
        }
        if (getShouldShowTrackDetailsPage()) {
            ContentAccessUtil.checkCanContinueCatalogAction(this.mFragment.getActivity(), currentTrack, ContentAccessUtil.ContentAccessOperation.BROWSE, this, getUseDefaultFallbackHandling());
        } else {
            ContentAccessUtil.checkCanContinueCatalogAction(this.mFragment.getActivity(), currentTrack, ContentAccessUtil.ContentAccessOperation.STREAM, this, getUseDefaultFallbackHandling());
        }
    }

    private final boolean shouldStartNoOpCloudQueuePlayback(ContentMetadata contentMetadata, boolean isOffline) {
        TrackMetadata trackMetadata = contentMetadata instanceof TrackMetadata ? (TrackMetadata) contentMetadata : null;
        boolean zAreEqual = trackMetadata == null ? false : Intrinsics.areEqual(trackMetadata.getIsOwned(), Boolean.TRUE);
        boolean z = SearchItemUtils.userIsHawkfire() || SearchItemUtils.userIsKatana();
        if (isOffline) {
            return zAreEqual || z;
        }
        return false;
    }

    private final void playbackFallbackHandling(PrimeTrack track) {
        PlaybackUtils.startSFA(this.mFragment, track, getPlaybackPageType(), getShouldPlayFullscreen());
    }

    private final void saveTrackPlaybackState(Map<PrimeTrack, Integer> trackList, PrimeTrack currentTrack, int positionToStart, List<? extends ContentMetadata> contentMetadataList) {
        boolean zHawkfireContentIsOutOfTier = hawkfireContentIsOutOfTier(trackList, currentTrack, contentMetadataList);
        Fragment fragment = this.mFragment;
        if (!(fragment instanceof SearchBrushFragment) || zHawkfireContentIsOutOfTier) {
            return;
        }
        ((SearchBrushFragment) fragment).getViewModel().saveTrackPlaybackState(contentMetadataList, positionToStart);
    }

    private final Set<ContentAccessType> getSupportedTiers(ContentMetadata contentMetadata) {
        TrackMetadata trackMetadata = contentMetadata instanceof TrackMetadata ? (TrackMetadata) contentMetadata : null;
        Set<ContentAccessType> supportedTiers = trackMetadata == null ? null : trackMetadata.getSupportedTiers();
        if (!(supportedTiers == null || supportedTiers.isEmpty())) {
            return supportedTiers;
        }
        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(contentMetadata == null ? null : contentMetadata.getUri());
        Set<ContentAccessType> supportedTiers2 = track != null ? track.getSupportedTiers() : null;
        return supportedTiers2 == null ? SetsKt__SetsKt.emptySet() : supportedTiers2;
    }

    private final boolean hawkfireContentIsOutOfTier(Map<PrimeTrack, Integer> trackList, PrimeTrack currentTrack, List<? extends ContentMetadata> contentMetadataList) {
        boolean z = SearchItemUtils.userIsNightwingOnly() || SearchItemUtils.userIsPrimeOnly();
        Integer num = trackList == null ? null : trackList.get(currentTrack);
        return z && SearchItemUtils.isContentHawkfireOnly(getSupportedTiers(num != null ? contentMetadataList.get(num.intValue()) : null));
    }
}
