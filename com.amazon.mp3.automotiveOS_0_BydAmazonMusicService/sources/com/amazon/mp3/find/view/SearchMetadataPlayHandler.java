package com.amazon.mp3.find.view;

import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.find.util.SearchClickEventUtil;
import com.amazon.mp3.find.util.SearchTrackPlaybackHandler;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.search.view.SearchFragmentUtils;
import com.amazon.mp3.util.PlaybackUtils;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchMetadataPlayHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/find/view/SearchMetadataPlayHandler;", "Lcom/amazon/mp3/find/view/ContentMetadataActionHandler;", "Lcom/amazon/mp3/find/view/BaseSearchMetadataPlayHandler;", "fragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "trackPlaybackUtil", "Lcom/amazon/mp3/find/util/SearchTrackPlaybackHandler;", "getPlaybackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playNavigationMetadata", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "playPodcast", "playPodcastMetadata", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "playStationMetadata", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "playTrackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchMetadataPlayHandler extends BaseSearchMetadataPlayHandler {
    private final Fragment fragment;
    private final PageType pageType;
    private final SearchTrackPlaybackHandler trackPlaybackUtil;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchMetadataPlayHandler(Fragment fragment, PageType pageType) {
        super(fragment, pageType);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.fragment = fragment;
        this.pageType = pageType;
        this.trackPlaybackUtil = new SearchTrackPlaybackHandler(fragment, pageType, getPlaybackPageType());
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public PlaybackPageType getPlaybackPageType() {
        return SearchClickEventUtil.getPlaybackPageType(this.pageType, PlaybackPageType.PRIME_BROWSE_SEARCH);
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
        Fragment fragment = this.fragment;
        Intrinsics.checkNotNullExpressionValue(station, "station");
        PlaybackUtils.startStationPlayback$default(fragment, station, PlaybackPageType.PRIME_BROWSE_SEARCH, false, false, 24, null);
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public void playPodcastMetadata(PodcastMetadata contentMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        DeeplinksManager.get(AmazonApplication.getContext()).handle(Uri.parse(contentMetadata.getTarget()));
    }

    @Override // com.amazon.mp3.find.view.BaseSearchMetadataPlayHandler
    public void playNavigationMetadata(NavigationMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        String target = contentMetadata.getTarget();
        if (DeeplinksUtils.isPodcastShow(target) || DeeplinksUtils.isPodcastEpisode(target)) {
            playPodcast(contentMetadata);
        }
    }

    private final void playPodcast(NavigationMetadata contentMetadata) {
        try {
            DeeplinksManager.get(AmazonApplication.getContext()).handle(DeeplinksUtils.getPodcastPlaybackDeeplink(contentMetadata.getTarget()));
        } catch (Exception unused) {
            if (TextUtils.isEmpty(contentMetadata.getTarget())) {
                return;
            }
            BrushFragment brushFragmentCreateFragment = new BrushFragmentNavigation.Builder(contentMetadata.getTarget()).createFragment();
            SearchFragmentUtils.changeScreenFragment(brushFragmentCreateFragment, brushFragmentCreateFragment, true, true, true);
        }
    }
}
