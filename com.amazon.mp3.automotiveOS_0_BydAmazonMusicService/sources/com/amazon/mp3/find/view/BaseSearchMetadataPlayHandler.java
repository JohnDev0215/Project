package com.amazon.mp3.find.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.find.util.EntityTrackingIntentUtil;
import com.amazon.mp3.find.util.SearchClickEventUtil;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.PlaybackUtils;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.find.model.search.PlaylistType;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.find.viewmodels.SearchAction;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.CommunityPlaylistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseSearchMetadataPlayHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0014H\u0016J\u0018\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020)2\u0006\u0010&\u001a\u00020\u0014H\u0016J\u0018\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0014H\u0016J\u0018\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020.2\u0006\u0010&\u001a\u00020\u0014H\u0016J\b\u0010/\u001a\u000200H&J\u0018\u00101\u001a\u00020#2\u0006\u0010\t\u001a\u00020\n2\u0006\u00102\u001a\u000203H\u0016J&\u00101\u001a\u00020#2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\n052\u0006\u00106\u001a\u0002072\u0006\u00102\u001a\u000203H\u0016J\u0018\u00108\u001a\u00020#2\u0006\u0010\t\u001a\u00020\n2\u0006\u00102\u001a\u000203H\u0002J\b\u00109\u001a\u00020#H\u0016J\u0010\u0010:\u001a\u00020#2\u0006\u0010\t\u001a\u00020;H\u0016J\u0010\u0010<\u001a\u00020#2\u0006\u0010\t\u001a\u00020=H\u0016J\u0010\u0010>\u001a\u00020#2\u0006\u0010\t\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020#2\u0006\u0010\t\u001a\u00020AH\u0016J\u0010\u0010B\u001a\u00020#2\u0006\u0010\t\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u00020#2\u0006\u0010\t\u001a\u00020EH\u0016J\u0010\u0010F\u001a\u00020#2\u0006\u0010\t\u001a\u00020GH\u0016J\u0018\u0010H\u001a\u00020#2\u0006\u0010\t\u001a\u00020I2\u0006\u00102\u001a\u000203H\u0016J\u0010\u0010J\u001a\u00020#2\u0006\u0010\t\u001a\u00020KH\u0016J\u0010\u0010L\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0003H\u0016J\u0010\u0010M\u001a\u00020#2\u0006\u0010\t\u001a\u00020\nH\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0014X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u0014X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u0014X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016¨\u0006N"}, d2 = {"Lcom/amazon/mp3/find/view/BaseSearchMetadataPlayHandler;", "Lcom/amazon/mp3/find/view/ContentMetadataActionHandler;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/library/item/CatalogContent;", "fragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "playlistTask", "Lcom/amazon/mp3/task/GetPrimePlaylistTracksTask$Task;", "getPlaylistTask", "()Lcom/amazon/mp3/task/GetPrimePlaylistTracksTask$Task;", "primeCollectionTask", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "getPrimeCollectionTask", "()Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "shouldNavigateAfterPlayback", "", "getShouldNavigateAfterPlayback", "()Z", "shouldPlayFullscreen", "getShouldPlayFullscreen", "shouldTrackEntitiesForBlendedSearchHistory", "getShouldTrackEntitiesForBlendedSearchHistory", "startPlaybackUtil", "Lcom/amazon/mp3/playback/start/StartPlaybackUtil;", "useDefaultFallbackHandling", "getUseDefaultFallbackHandling", "canHandle", "action", "Lcom/amazon/music/find/viewmodels/SearchAction;", "continueAlbumPlayback", "", "album", "Lcom/amazon/mp3/prime/browse/metadata/PrimeAlbum;", "canContinue", "continueArtistPlayback", "artist", "Lcom/amazon/mp3/prime/browse/metadata/PrimeArtist;", "continueCatalogAction", "item", "continuePlaylistPlayback", "primePlaylist", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "getPlaybackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "handle", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "contentMetadataList", "", "positionToStart", "", "handleClickMetric", "navigateAfterPlayback", "playAlbumMetadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "playArtistMetadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "playCommunityPlaylistMetadata", "Lcom/amazon/music/views/library/metadata/CommunityPlaylistMetadata;", "playNavigationMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "playPlaylistMetadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "playPodcastMetadata", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "playStationMetadata", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "playTrackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "playUserPlaylistMetadata", "Lcom/amazon/music/views/library/metadata/UserPlaylistMetadata;", "playbackFallbackHandling", "trackEntityForBlendedSearchHistory", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseSearchMetadataPlayHandler implements ContentMetadataActionHandler, ContentAccessUtil.CatalogActionListener<CatalogContent> {
    private ContentMetadata contentMetadata;
    private final Fragment fragment;
    private final PageType pageType;
    private final GetPrimePlaylistTracksTask.Task playlistTask;
    private final PrimeCollectionTask.Task primeCollectionTask;
    private final boolean shouldNavigateAfterPlayback;
    private final boolean shouldPlayFullscreen;
    private final boolean shouldTrackEntitiesForBlendedSearchHistory;
    private final StartPlaybackUtil startPlaybackUtil;
    private final boolean useDefaultFallbackHandling;

    /* JADX INFO: compiled from: BaseSearchMetadataPlayHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlaylistType.values().length];
            iArr[PlaylistType.COMMUNITY_PLAYLIST.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public abstract PlaybackPageType getPlaybackPageType();

    @Override // com.amazon.mp3.find.view.ContentMetadataActionHandler
    public void handle(List<? extends ContentMetadata> contentMetadataList, int positionToStart, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
    }

    public void navigateAfterPlayback() {
    }

    public void playNavigationMetadata(NavigationMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
    }

    public abstract void playPodcastMetadata(PodcastMetadata contentMetadata);

    public abstract void playStationMetadata(StationMetadata contentMetadata);

    public abstract void playTrackMetadata(TrackMetadata contentMetadata, InteractionType interactionType);

    public void playbackFallbackHandling(CatalogContent item) {
        Intrinsics.checkNotNullParameter(item, "item");
    }

    public BaseSearchMetadataPlayHandler(Fragment fragment, PageType pageType) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.fragment = fragment;
        this.pageType = pageType;
        this.shouldPlayFullscreen = true;
        this.primeCollectionTask = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
        this.playlistTask = GetPrimePlaylistTracksTask.Task.PLAYBACK_FULLSCREEN;
        this.useDefaultFallbackHandling = true;
        this.shouldTrackEntitiesForBlendedSearchHistory = true;
        this.startPlaybackUtil = StartPlaybackUtil.INSTANCE;
    }

    public boolean getShouldPlayFullscreen() {
        return this.shouldPlayFullscreen;
    }

    public PrimeCollectionTask.Task getPrimeCollectionTask() {
        return this.primeCollectionTask;
    }

    public GetPrimePlaylistTracksTask.Task getPlaylistTask() {
        return this.playlistTask;
    }

    public boolean getUseDefaultFallbackHandling() {
        return this.useDefaultFallbackHandling;
    }

    public boolean getShouldNavigateAfterPlayback() {
        return this.shouldNavigateAfterPlayback;
    }

    public boolean getShouldTrackEntitiesForBlendedSearchHistory() {
        return this.shouldTrackEntitiesForBlendedSearchHistory;
    }

    @Override // com.amazon.mp3.find.view.ContentMetadataActionHandler
    public void handle(ContentMetadata contentMetadata, InteractionType interactionType) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        this.contentMetadata = contentMetadata;
        if (contentMetadata instanceof TrackMetadata) {
            playTrackMetadata((TrackMetadata) contentMetadata, interactionType);
        } else if (contentMetadata instanceof StationMetadata) {
            playStationMetadata((StationMetadata) contentMetadata);
        } else if (contentMetadata instanceof AlbumMetadata) {
            playAlbumMetadata((AlbumMetadata) contentMetadata);
        } else if (contentMetadata instanceof ArtistMetadata) {
            playArtistMetadata((ArtistMetadata) contentMetadata);
        } else if (contentMetadata instanceof PlaylistMetadata) {
            playPlaylistMetadata((PlaylistMetadata) contentMetadata);
        } else if (contentMetadata instanceof UserPlaylistMetadata) {
            playUserPlaylistMetadata((UserPlaylistMetadata) contentMetadata);
        } else if (contentMetadata instanceof CommunityPlaylistMetadata) {
            playCommunityPlaylistMetadata((CommunityPlaylistMetadata) contentMetadata);
        } else if (contentMetadata instanceof NavigationMetadata) {
            playNavigationMetadata((NavigationMetadata) contentMetadata);
        } else if (contentMetadata instanceof PodcastMetadata) {
            playPodcastMetadata((PodcastMetadata) contentMetadata);
        }
        if (getShouldNavigateAfterPlayback()) {
            navigateAfterPlayback();
        }
        if (getShouldTrackEntitiesForBlendedSearchHistory()) {
            if (contentMetadata instanceof StationMetadata ? true : contentMetadata instanceof NavigationMetadata) {
                trackEntityForBlendedSearchHistory(contentMetadata);
            }
        }
        handleClickMetric(contentMetadata, interactionType);
    }

    @Override // com.amazon.mp3.find.view.ContentMetadataActionHandler
    public boolean canHandle(SearchAction action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action == SearchAction.PLAY || action == SearchAction.PLAY_FULL;
    }

    public void playAlbumMetadata(AlbumMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        ContentAccessUtil.checkCanContinueCatalogAction(this.fragment.getActivity(), PrimeItemsTransformationUtil.convertToPrimeAlbum(contentMetadata), ContentAccessUtil.ContentAccessOperation.STREAM, this, getUseDefaultFallbackHandling());
    }

    public void playArtistMetadata(ArtistMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        ContentAccessUtil.checkCanContinueCatalogAction(this.fragment.getActivity(), PrimeItemsTransformationUtil.convertToPrimeArtist(contentMetadata), ContentAccessUtil.ContentAccessOperation.STREAM, this, getUseDefaultFallbackHandling());
    }

    public void playPlaylistMetadata(PlaylistMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        ContentAccessUtil.checkCanContinueCatalogAction(this.fragment.getActivity(), PrimeItemsTransformationUtil.convertToPrimePlaylist(contentMetadata), ContentAccessUtil.ContentAccessOperation.STREAM, this, getUseDefaultFallbackHandling());
    }

    public void playCommunityPlaylistMetadata(CommunityPlaylistMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        ContentAccessUtil.checkCanContinueCatalogAction(this.fragment.getActivity(), PrimeItemsTransformationUtil.convertToPrimePlaylist(contentMetadata), ContentAccessUtil.ContentAccessOperation.STREAM, this, getUseDefaultFallbackHandling());
    }

    public void playUserPlaylistMetadata(UserPlaylistMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        ContentAccessUtil.checkCanContinueCatalogAction(this.fragment.getActivity(), PrimeItemsTransformationUtil.convertToPrimePlaylist(contentMetadata), ContentAccessUtil.ContentAccessOperation.STREAM, this, getUseDefaultFallbackHandling());
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(CatalogContent item, boolean canContinue) {
        ContentMetadata contentMetadata;
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof PrimeAlbum) {
            continueAlbumPlayback((PrimeAlbum) item, canContinue);
        } else if (item instanceof PrimeArtist) {
            continueArtistPlayback((PrimeArtist) item, canContinue);
        } else if (item instanceof PrimePlaylist) {
            continuePlaylistPlayback((PrimePlaylist) item, canContinue);
        }
        if (getShouldTrackEntitiesForBlendedSearchHistory()) {
            if ((canContinue || SearchItemUtils.userIsNightwingOnly()) && (contentMetadata = this.contentMetadata) != null) {
                trackEntityForBlendedSearchHistory(contentMetadata);
            }
        }
    }

    public void continueAlbumPlayback(PrimeAlbum album, boolean canContinue) {
        Intrinsics.checkNotNullParameter(album, "album");
        if (canContinue) {
            PlaybackUtils.startAlbumPlayback(this.fragment, album, getPrimeCollectionTask());
        } else {
            if (getUseDefaultFallbackHandling()) {
                return;
            }
            playbackFallbackHandling(album);
        }
    }

    public void continueArtistPlayback(PrimeArtist artist, boolean canContinue) {
        Intrinsics.checkNotNullParameter(artist, "artist");
        if (canContinue) {
            FragmentActivity fragmentActivityRequireActivity = this.fragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "fragment.requireActivity()");
            PlaybackUtils.startArtistPlayback(fragmentActivityRequireActivity, artist, getPrimeCollectionTask());
        } else {
            if (getUseDefaultFallbackHandling()) {
                return;
            }
            playbackFallbackHandling(artist);
        }
    }

    public void continuePlaylistPlayback(PrimePlaylist primePlaylist, boolean canContinue) {
        Intrinsics.checkNotNullParameter(primePlaylist, "primePlaylist");
        if (canContinue) {
            if (WhenMappings.$EnumSwitchMapping$0[primePlaylist.getPlaylistType().ordinal()] == 1) {
                FragmentActivity fragmentActivityRequireActivity = this.fragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "fragment.requireActivity()");
                PlaybackUtils.startCommunityPlaylistPlayback$default(fragmentActivityRequireActivity, primePlaylist, getPlaybackPageType(), getPlaylistTask(), false, 16, null);
                return;
            } else if (primePlaylist.getAsin() != null) {
                FragmentActivity fragmentActivityRequireActivity2 = this.fragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "fragment.requireActivity()");
                PlaybackUtils.startPlaylistPlayback(fragmentActivityRequireActivity2, primePlaylist, getPlaylistTask());
                return;
            } else {
                FragmentActivity fragmentActivityRequireActivity3 = this.fragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity3, "fragment.requireActivity()");
                PlaybackUtils.startUserPlaylistPlayback(fragmentActivityRequireActivity3, primePlaylist, getShouldPlayFullscreen(), getPlaybackPageType(), this.pageType);
                return;
            }
        }
        if (getUseDefaultFallbackHandling()) {
            return;
        }
        playbackFallbackHandling(primePlaylist);
    }

    private final void trackEntityForBlendedSearchHistory(ContentMetadata contentMetadata) {
        if (contentMetadata instanceof AlbumMetadata) {
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.fragment.getContext(), (AlbumMetadata) contentMetadata, this.fragment instanceof SearchBrushFragment);
            return;
        }
        if (contentMetadata instanceof ArtistMetadata) {
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.fragment.getContext(), (ArtistMetadata) contentMetadata, this.fragment instanceof SearchBrushFragment);
            return;
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.fragment.getContext(), (PlaylistMetadata) contentMetadata, this.fragment instanceof SearchBrushFragment);
            return;
        }
        if (contentMetadata instanceof CommunityPlaylistMetadata) {
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.fragment.getContext(), (CommunityPlaylistMetadata) contentMetadata, this.fragment instanceof SearchBrushFragment);
            return;
        }
        if (contentMetadata instanceof StationMetadata) {
            StationMetadata stationMetadata = (StationMetadata) contentMetadata;
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.fragment.getContext(), new StationItem(stationMetadata.getSupportedTiers(), stationMetadata.getExplicitMap(), stationMetadata.getTitle(), stationMetadata.getImageUrl(), null, null, stationMetadata.getKey(), null), this.fragment instanceof SearchBrushFragment);
            return;
        }
        if (contentMetadata instanceof NavigationMetadata) {
            Fragment fragment = this.fragment;
            NavigationMetadata navigationMetadata = (NavigationMetadata) contentMetadata;
            String target = navigationMetadata.getTarget();
            String title = navigationMetadata.getTitle();
            String subTitle = navigationMetadata.getSubTitle();
            String string = contentMetadata.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(string, "contentMetadata.uri.toString()");
            EntityTrackingIntentUtil.sendEntityVisitEvent(fragment, target, title, subTitle, string, ((NavigationMetadata) contentMetadata).getId());
            return;
        }
        if (contentMetadata instanceof PodcastMetadata) {
            Fragment fragment2 = this.fragment;
            PodcastMetadata podcastMetadata = (PodcastMetadata) contentMetadata;
            String target2 = podcastMetadata.getTarget();
            String title2 = podcastMetadata.getTitle();
            String subTitle2 = podcastMetadata.getSubTitle();
            String imageUrl = podcastMetadata.getImageUrl();
            if (imageUrl == null) {
                imageUrl = "";
            }
            EntityTrackingIntentUtil.sendEntityVisitEvent(fragment2, target2, title2, subTitle2, imageUrl, podcastMetadata.getVariantId());
        }
    }

    private final void handleClickMetric(ContentMetadata contentMetadata, InteractionType interactionType) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        ActionType actionType;
        if (contentMetadata instanceof AlbumMetadata) {
            actionType = ActionType.SELECT_ALBUM;
        } else if (contentMetadata instanceof ArtistMetadata) {
            actionType = ActionType.SELECT_ARTIST;
        } else if (contentMetadata instanceof PlaylistMetadata) {
            if (((PlaylistMetadata) contentMetadata).getContentTypes().contains("VIDEO")) {
                actionType = ActionType.SELECT_VIDEO_PLAYLIST;
            } else {
                actionType = ActionType.SELECT_PLAYLIST;
            }
        } else if (contentMetadata instanceof UserPlaylistMetadata) {
            actionType = ActionType.SELECT_PLAYLIST;
        } else if (contentMetadata instanceof CommunityPlaylistMetadata) {
            actionType = ActionType.SELECT_COMMUNITY_PLAYLIST;
        } else if (contentMetadata instanceof StationMetadata) {
            actionType = ActionType.PLAY_STATION;
        } else if (!(contentMetadata instanceof PodcastMetadata)) {
            actionType = null;
        } else if (DeeplinksUtils.isPodcastEpisode(((PodcastMetadata) contentMetadata).getTarget())) {
            actionType = ActionType.SELECT_PODCAST_EPISODE;
        } else {
            actionType = ActionType.SELECT_PODCAST_SHOW;
        }
        ActionType actionType2 = actionType;
        if (actionType2 != null) {
            SearchClickEventUtil.sendClickEvent$default(this.fragment, this.pageType, actionType2, contentMetadata, null, interactionType, 16, null);
        }
        if (contentMetadata instanceof NavigationMetadata) {
            SearchClickEventUtil.sendClickEvent(this.fragment, this.pageType, (NavigationMetadata) contentMetadata, interactionType);
        }
    }
}
