package com.amazon.mp3.find.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.find.util.CarModeSearchTrackPlaybackHandler;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackUtils;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeSearchMetadataPlayHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 52\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004:\u00015B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010 \u001a\u00020\nH\u0016J&\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\"H\u0016J\u0010\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u000200H\u0016J\u0018\u00101\u001a\u00020\"2\u0006\u0010/\u001a\u0002022\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u00103\u001a\u00020\"2\u0006\u00104\u001a\u00020\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0015X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u0015X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u0015X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017¨\u00066"}, d2 = {"Lcom/amazon/mp3/find/view/CarModeSearchMetadataPlayHandler;", "Lcom/amazon/mp3/find/view/ContentMetadataActionHandler;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/library/item/CatalogContent;", "Lcom/amazon/mp3/find/view/BaseSearchMetadataPlayHandler;", "fragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "playlistTask", "Lcom/amazon/mp3/task/GetPrimePlaylistTracksTask$Task;", "getPlaylistTask", "()Lcom/amazon/mp3/task/GetPrimePlaylistTracksTask$Task;", "primeCollectionTask", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "getPrimeCollectionTask", "()Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "shouldNavigateAfterPlayback", "", "getShouldNavigateAfterPlayback", "()Z", "shouldPlayFullscreen", "getShouldPlayFullscreen", "shouldTrackEntitiesForBlendedSearchHistory", "getShouldTrackEntitiesForBlendedSearchHistory", "trackPlaybackUtil", "Lcom/amazon/mp3/find/util/CarModeSearchTrackPlaybackHandler;", "useDefaultFallbackHandling", "getUseDefaultFallbackHandling", "getPlaybackPageType", "handle", "", "contentMetadataList", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "positionToStart", "", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "navigateAfterPlayback", "playPodcastMetadata", "podcastMetadata", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "playStationMetadata", "contentMetadata", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "playTrackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "playbackFallbackHandling", "item", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeSearchMetadataPlayHandler extends BaseSearchMetadataPlayHandler {
    private static final String TAG = CarModeSearchMetadataPlayHandler.class.getSimpleName();
    private final Fragment fragment;
    private final PageType pageType;
    private final PlaybackPageType playbackPageType;
    private final GetPrimePlaylistTracksTask.Task playlistTask;
    private final PrimeCollectionTask.Task primeCollectionTask;
    private final boolean shouldNavigateAfterPlayback;
    private final boolean shouldPlayFullscreen;
    private final boolean shouldTrackEntitiesForBlendedSearchHistory;
    private final CarModeSearchTrackPlaybackHandler trackPlaybackUtil;
    private final boolean useDefaultFallbackHandling;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeSearchMetadataPlayHandler(Fragment fragment, PageType pageType, PlaybackPageType playbackPageType) {
        super(fragment, pageType);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.fragment = fragment;
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
        this.primeCollectionTask = PrimeCollectionTask.Task.PLAYBACK;
        this.playlistTask = GetPrimePlaylistTracksTask.Task.PLAYBACK;
        this.shouldNavigateAfterPlayback = true;
        this.trackPlaybackUtil = new CarModeSearchTrackPlaybackHandler(fragment, pageType, getPlaybackPageType());
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public boolean getShouldPlayFullscreen() {
        return this.shouldPlayFullscreen;
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public PrimeCollectionTask.Task getPrimeCollectionTask() {
        return this.primeCollectionTask;
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public GetPrimePlaylistTracksTask.Task getPlaylistTask() {
        return this.playlistTask;
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public boolean getUseDefaultFallbackHandling() {
        return this.useDefaultFallbackHandling;
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public boolean getShouldNavigateAfterPlayback() {
        return this.shouldNavigateAfterPlayback;
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public boolean getShouldTrackEntitiesForBlendedSearchHistory() {
        return this.shouldTrackEntitiesForBlendedSearchHistory;
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public PlaybackPageType getPlaybackPageType() {
        return this.playbackPageType;
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler, com.amazon.mp3.find.view.ContentMetadataActionHandler
    public void handle(List<? extends ContentMetadata> contentMetadataList, int positionToStart, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        if (contentMetadataList.get(positionToStart) instanceof TrackMetadata) {
            this.trackPlaybackUtil.playTrackMetadata(contentMetadataList, positionToStart, interactionType);
            if (getShouldNavigateAfterPlayback()) {
                navigateAfterPlayback();
                return;
            }
            return;
        }
        Log.error(TAG, "Playback is not supported for this type of ContentMetadataList, skipping playback execution");
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public void playTrackMetadata(TrackMetadata contentMetadata, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        this.trackPlaybackUtil.playTrackMetadata(CollectionsKt__CollectionsJVMKt.listOf(contentMetadata), 0, interactionType);
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public void playStationMetadata(StationMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Station station = PrimeItemsTransformationUtil.convertToStation(contentMetadata);
        PlaybackUtils playbackUtils = PlaybackUtils.INSTANCE;
        Fragment fragment = this.fragment;
        PlaybackPageType playbackPageType = getPlaybackPageType();
        Intrinsics.checkNotNullExpressionValue(station, "station");
        PlaybackUtils.startStationPlayback(fragment, station, playbackPageType, false, false);
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public void playPodcastMetadata(PodcastMetadata podcastMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(podcastMetadata, "podcastMetadata");
        DeeplinksManager.get(this.fragment.requireContext()).handle(DeeplinksUtils.getPodcastPlaybackDeeplink(podcastMetadata.getTarget()));
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public void playbackFallbackHandling(CatalogContent item) {
        Intrinsics.checkNotNullParameter(item, "item");
        PlaybackUtils.startSFA(this.fragment, item, getPlaybackPageType(), getShouldPlayFullscreen());
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public void navigateAfterPlayback() {
        FragmentActivity activity = this.fragment.getActivity();
        if (activity == null) {
            return;
        }
        CarModeUtility.INSTANCE.navigateToBrushFragment(activity, false, false);
    }
}
