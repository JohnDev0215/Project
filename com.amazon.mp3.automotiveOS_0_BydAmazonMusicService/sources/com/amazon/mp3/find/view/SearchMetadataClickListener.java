package com.amazon.mp3.find.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.customerprofile.CustomerProfileClickHandler;
import com.amazon.mp3.find.util.EntityTrackingIntentUtil;
import com.amazon.mp3.find.util.SearchClickEventUtil;
import com.amazon.mp3.find.util.SearchTrackPlaybackHandler;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.navigation.GenresDestinationHandler;
import com.amazon.mp3.navigation.SharedUserPlaylistDestinationHandler;
import com.amazon.mp3.ondemandplaylists.OnDemandPlaylistSynchronizer;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.providers.RecentsPlaybackProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.recents.RecentsFragment;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.search.view.SearchFragmentUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.PlaybackUtils;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.destination.GenresDestination;
import com.amazon.music.destination.SharedUserPlaylistDestination;
import com.amazon.music.explore.ExploreFactory;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.CommunityPlaylistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.CustomerProfileMetadata;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.LiveEventMetadata;
import com.amazon.music.views.library.metadata.LivestreamMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.metadata.SearchQueryMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchMetadataClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 >2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001>B\u0017\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u001aH\u0016J(\u0010\u001b\u001a\u00020\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020+H\u0002J\u0018\u0010,\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020-2\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\u0010\u0010.\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020/H\u0002J\u0010\u00100\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u000203H\u0002J\u0010\u00104\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u000205H\u0002J\u0018\u00106\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u0002072\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\u001c\u00108\u001a\u00020\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ$\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u00052\b\u0010;\u001a\u0004\u0018\u00010!2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/amazon/mp3/find/view/SearchMetadataClickListener;", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/library/item/CatalogContent;", "mFragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "mArtistMetadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "trackPlaybackUtil", "Lcom/amazon/mp3/find/util/SearchTrackPlaybackHandler;", "continueCatalogAction", "", "item", "canContinue", "", "onAlbumContentClicked", "contentMetadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "isOffline", "onArtistContentClicked", "onCommunityPlaylistContentClicked", "Lcom/amazon/music/views/library/metadata/CommunityPlaylistMetadata;", "onContentClicked", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "onContentListClicked", "contentMetadataList", "", "positionToStart", "", "contentListTitle", "", "onCustomerProfileContentClicked", "Lcom/amazon/music/views/library/metadata/CustomerProfileMetadata;", "onGenreContentClicked", "Lcom/amazon/music/views/library/metadata/GenreMetadata;", "onLiveEventContentClicked", "Lcom/amazon/music/views/library/metadata/LiveEventMetadata;", "onLivestreamContentClicked", "Lcom/amazon/music/views/library/metadata/LivestreamMetadata;", "onNavigationContentClicked", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "onPlaylistContentClicked", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "onRecentsContentClicked", "Lcom/amazon/music/views/library/metadata/RecentsMetadata;", "onSearchQueryContentClicked", "Lcom/amazon/music/views/library/metadata/SearchQueryMetadata;", "onStationContentClicked", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "onTrackContentClicked", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "onUserPlaylistContentClicked", "Lcom/amazon/music/views/library/metadata/UserPlaylistMetadata;", "playContentMetadata", "sendEntityRefreshEvent", "fragment", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchMetadataClickListener implements ContentMetadataOnClickListener, ContentAccessUtil.CatalogActionListener<CatalogContent> {
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(SearchMetadataClickListener.class.getName());
    private ArtistMetadata mArtistMetadata;
    private final Fragment mFragment;
    private final PageType pageType;
    private final SearchTrackPlaybackHandler trackPlaybackUtil;

    @JvmOverloads
    public SearchMetadataClickListener(Fragment mFragment, PageType pageType) {
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.mFragment = mFragment;
        this.pageType = pageType;
        this.trackPlaybackUtil = new SearchTrackPlaybackHandler(mFragment, pageType, SearchClickEventUtil.getPlaybackPageType(pageType, PlaybackPageType.PRIME_BROWSE_SEARCH));
    }

    public final void playContentMetadata(List<? extends ContentMetadata> contentMetadataList, int positionToStart) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        this.trackPlaybackUtil.playTrackMetadata(contentMetadataList, positionToStart, InteractionType.TAP);
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(CatalogContent item, boolean canContinue) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof Station) {
            if (canContinue) {
                PlaybackUtils.startStationPlayback$default(this.mFragment, (Station) item, PlaybackPageType.PRIME_BROWSE_SEARCH, false, false, 24, null);
            }
        } else if ((item instanceof PrimeArtist) && canContinue) {
            SearchFragmentUtils.navigateToArtistDetail(this.mFragment, this.mArtistMetadata);
            ArtistMetadata artistMetadata = this.mArtistMetadata;
            if (artistMetadata == null) {
                return;
            }
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), artistMetadata, this.mFragment instanceof SearchBrushFragment);
        }
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        playContentMetadata(contentMetadataList, positionToStart);
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentClicked(ContentMetadata contentMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        boolean zIsOffline;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        logger.debug(TAG + "::onContentClicked. contentMetadata: " + contentMetadata);
        if (this.mFragment.getContext() != null) {
            Context contextRequireContext = this.mFragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "mFragment.requireContext()");
            zIsOffline = SearchItemUtils.isOffline(contextRequireContext);
        } else {
            zIsOffline = false;
        }
        if (contentMetadata instanceof AlbumMetadata) {
            onAlbumContentClicked((AlbumMetadata) contentMetadata, zIsOffline);
            return;
        }
        if (contentMetadata instanceof ArtistMetadata) {
            onArtistContentClicked((ArtistMetadata) contentMetadata, zIsOffline);
            return;
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            onPlaylistContentClicked((PlaylistMetadata) contentMetadata, zIsOffline);
            return;
        }
        if (contentMetadata instanceof StationMetadata) {
            onStationContentClicked((StationMetadata) contentMetadata);
            return;
        }
        if (contentMetadata instanceof GenreMetadata) {
            onGenreContentClicked((GenreMetadata) contentMetadata);
            return;
        }
        if (contentMetadata instanceof RecentsMetadata) {
            onRecentsContentClicked((RecentsMetadata) contentMetadata);
            return;
        }
        if (contentMetadata instanceof CustomerProfileMetadata) {
            onCustomerProfileContentClicked((CustomerProfileMetadata) contentMetadata);
            return;
        }
        if (contentMetadata instanceof UserPlaylistMetadata) {
            onUserPlaylistContentClicked((UserPlaylistMetadata) contentMetadata, zIsOffline);
            return;
        }
        if (contentMetadata instanceof CommunityPlaylistMetadata) {
            onCommunityPlaylistContentClicked((CommunityPlaylistMetadata) contentMetadata, zIsOffline);
            return;
        }
        if (contentMetadata instanceof NavigationMetadata) {
            onNavigationContentClicked((NavigationMetadata) contentMetadata);
            return;
        }
        if (contentMetadata instanceof LiveEventMetadata) {
            onLiveEventContentClicked((LiveEventMetadata) contentMetadata);
            return;
        }
        if (contentMetadata instanceof LivestreamMetadata) {
            onLivestreamContentClicked((LivestreamMetadata) contentMetadata);
        } else if (contentMetadata instanceof SearchQueryMetadata) {
            onSearchQueryContentClicked((SearchQueryMetadata) contentMetadata);
        } else if (contentMetadata instanceof TrackMetadata) {
            onTrackContentClicked((TrackMetadata) contentMetadata);
        }
    }

    private final void onAlbumContentClicked(AlbumMetadata contentMetadata, boolean isOffline) {
        Context context;
        logger.debug(TAG + "::onAlbumContentClicked. uri=" + contentMetadata.getUri() + ", asin=" + ((Object) contentMetadata.getAsin()) + ", isOffline: " + isOffline);
        if (isOffline) {
            Context context2 = this.mFragment.getContext();
            if (context2 != null) {
                SearchFragmentUtils.changeScreenFragment$default(this.mFragment, SearchFragmentUtils.INSTANCE.getAlbumDetailsFragmentForOfflineMode(context2, contentMetadata), false, false, false, 28, null);
            }
        } else {
            boolean zUserIsPrimeOnly = SearchItemUtils.userIsPrimeOnly();
            boolean zIsContentHawkfireOnly = SearchItemUtils.isContentHawkfireOnly(contentMetadata.getSupportedTiers());
            PrimeAlbum primeAlbum = PrimeItemsTransformationUtil.convertToPrimeAlbum(contentMetadata);
            if (zUserIsPrimeOnly && zIsContentHawkfireOnly) {
                ContentAccessUtil.canContinueCatalogAction(this.mFragment.getActivity(), primeAlbum, ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION, ContentAccessUtil.ContentAccessOperation.STREAM);
            } else {
                Fragment fragment = this.mFragment;
                if (fragment != null && (context = fragment.getContext()) != null) {
                    Intrinsics.checkNotNullExpressionValue(primeAlbum, "primeAlbum");
                    SearchFragmentUtils.changeScreenFragment$default(this.mFragment, SearchFragmentUtils.getAlbumDetailsFragment(context, primeAlbum, true), false, false, false, 28, null);
                }
                EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), contentMetadata, this.mFragment instanceof SearchBrushFragment);
            }
        }
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, ActionType.SELECT_ALBUM, contentMetadata, null, null, 48, null);
    }

    private final void onArtistContentClicked(ArtistMetadata contentMetadata, boolean isOffline) {
        logger.debug(TAG + "::onArtistContentClicked. uri=" + contentMetadata.getUri() + ", asin=" + ((Object) contentMetadata.getAsin()) + ", isOffline: " + isOffline);
        this.mArtistMetadata = contentMetadata;
        boolean zUserIsPrimeOnly = SearchItemUtils.userIsPrimeOnly();
        boolean zIsContentHawkfireOnly = SearchItemUtils.isContentHawkfireOnly(contentMetadata.getSupportedTiers());
        if (zUserIsPrimeOnly && zIsContentHawkfireOnly) {
            ContentAccessUtil.checkCanContinueCatalogAction(this.mFragment.getActivity(), PrimeItemsTransformationUtil.convertToPrimeArtist(contentMetadata), ContentAccessUtil.ContentAccessOperation.STREAM, this);
        } else {
            SearchFragmentUtils.navigateToArtistDetail(this.mFragment, contentMetadata);
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), contentMetadata, this.mFragment instanceof SearchBrushFragment);
        }
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, ActionType.SELECT_ARTIST, contentMetadata, null, null, 48, null);
    }

    private final void onPlaylistContentClicked(PlaylistMetadata contentMetadata, boolean isOffline) {
        logger.debug(TAG + "::onPlaylistContentClicked. uri=" + contentMetadata.getUri() + ", asin=" + ((Object) contentMetadata.getAsin()) + ", isOffline: " + isOffline);
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, contentMetadata.getContentTypes().contains("VIDEO") ? ActionType.SELECT_VIDEO_PLAYLIST : ActionType.SELECT_PLAYLIST, contentMetadata, null, null, 48, null);
        if (isOffline) {
            Context context = this.mFragment.getContext();
            if (context == null) {
                return;
            }
            SearchFragmentUtils.changeScreenFragment$default(this.mFragment, SearchFragmentUtils.INSTANCE.getPlaylistDetailsFragmentForOfflineMode(context, contentMetadata), false, false, false, 28, null);
            return;
        }
        boolean zUserIsPrimeOnly = SearchItemUtils.userIsPrimeOnly();
        boolean zIsContentHawkfireOnly = SearchItemUtils.isContentHawkfireOnly(contentMetadata.getSupportedTiers());
        OnDemandPlaylistSynchronizer.INSTANCE.syncOnDemandPlaylists(contentMetadata);
        if (zUserIsPrimeOnly && zIsContentHawkfireOnly) {
            ContentAccessUtil.canContinueCatalogAction(this.mFragment.getActivity(), PrimeItemsTransformationUtil.convertToPrimePlaylist(contentMetadata), ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION, ContentAccessUtil.ContentAccessOperation.STREAM);
        } else {
            SearchFragmentUtils.navigateToPlaylistDetail$default(this.mFragment.getContext(), contentMetadata, null, 4, null);
            EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), contentMetadata, this.mFragment instanceof SearchBrushFragment);
        }
    }

    private final void onStationContentClicked(StationMetadata contentMetadata) {
        ContentAccessUtil.checkCanContinueCatalogAction(this.mFragment.getActivity(), PrimeItemsTransformationUtil.convertToStation(contentMetadata), ContentAccessUtil.ContentAccessOperation.STREAM, this);
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, ActionType.PLAY_STATION, contentMetadata, null, null, 48, null);
        EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), new StationItem(contentMetadata.getSupportedTiers(), contentMetadata.getExplicitMap(), contentMetadata.getTitle(), contentMetadata.getImageUrl(), null, null, contentMetadata.getKey(), null), true);
    }

    private final void onGenreContentClicked(GenreMetadata contentMetadata) {
        new GenresDestinationHandler().navigateTo(this.mFragment.getContext(), new GenresDestination(null, contentMetadata.getBrowseId(), null, null, null));
        sendEntityRefreshEvent(this.mFragment, contentMetadata.getBrowseId(), EntityIdType.GENRE_ID);
    }

    private final void onRecentsContentClicked(RecentsMetadata contentMetadata) {
        PageType pageType;
        RecentlyPlayedItem recentlyPlayedItem = RecentlyPlayedManager.getInstance(this.mFragment.getContext()).toRecentItem(contentMetadata);
        String cirrusMediaSource = LastViewedScreenUtil.getLastViewedSource(this.mFragment.getContext(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
        if (!(this.mFragment instanceof RecentsFragment)) {
            pageType = null;
        } else if (Intrinsics.areEqual("cirrus", cirrusMediaSource)) {
            pageType = PageType.MY_MUSIC_CLOUD_FRONT_PAGE;
        } else {
            pageType = PageType.MY_MUSIC_OFFLINE_FRONT_PAGE;
        }
        FragmentActivity fragmentActivityRequireActivity = this.mFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "mFragment.requireActivity()");
        RecentsPlaybackProvider recentsPlaybackProvider = new RecentsPlaybackProvider(fragmentActivityRequireActivity);
        Intrinsics.checkNotNullExpressionValue(recentlyPlayedItem, "recentlyPlayedItem");
        recentsPlaybackProvider.startPlayback(recentlyPlayedItem, pageType);
    }

    private final void onCustomerProfileContentClicked(CustomerProfileMetadata contentMetadata) {
        FragmentActivity fragmentActivityRequireActivity = this.mFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "mFragment.requireActivity()");
        new CustomerProfileClickHandler(fragmentActivityRequireActivity).onProfileClick(contentMetadata.getProfileId());
    }

    private final void onCommunityPlaylistContentClicked(CommunityPlaylistMetadata contentMetadata, boolean isOffline) {
        logger.debug(TAG + "::onCommunityPlaylistContentClicked. uri=" + contentMetadata.getUri() + ", isOwned=" + contentMetadata.getIsOwned() + " playlistId=" + contentMetadata.getPlaylistId() + ", isOffline: " + isOffline);
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, ActionType.SELECT_COMMUNITY_PLAYLIST, contentMetadata, null, null, 48, null);
        Context context = this.mFragment.getContext();
        if (context != null) {
            new SharedUserPlaylistDestinationHandler().navigateTo(context, new SharedUserPlaylistDestination(contentMetadata.getPlaylistId(), null, null, null));
        }
        EntityTrackingIntentUtil.sendEntityVisitIntentEvent(this.mFragment.getContext(), contentMetadata, this.mFragment instanceof SearchBrushFragment);
    }

    private final void onUserPlaylistContentClicked(UserPlaylistMetadata contentMetadata, boolean isOffline) {
        logger.debug(TAG + "::onUserPlaylistContentClicked. uri=" + contentMetadata.getUri() + ", isOwned=" + contentMetadata.getIsOwned() + " playlistId=" + contentMetadata.getPlaylistId() + ", isOffline: " + isOffline);
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, ActionType.SELECT_PLAYLIST, contentMetadata, null, null, 48, null);
        Context context = this.mFragment.getContext();
        if (context == null) {
            return;
        }
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(context, contentMetadata.getPlaylistId())) {
            new SharedUserPlaylistDestinationHandler().navigateTo(this.mFragment.getContext(), new SharedUserPlaylistDestination(contentMetadata.getPlaylistId(), null, null, null));
        } else {
            context.startActivity(LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.UdoPlaylists.getContentUriFromLuid(this.mFragment.getContext(), isOffline ? "cirrus-local" : "cirrus", contentMetadata.getPlaylistId())));
        }
    }

    private final void onNavigationContentClicked(NavigationMetadata contentMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        try {
            DeeplinksManager.get(AmazonApplication.getContext()).handle(Uri.parse(contentMetadata.getTarget()));
        } catch (Exception unused) {
            if (!TextUtils.isEmpty(contentMetadata.getTarget())) {
                SearchFragmentUtils.changeScreenFragment(this.mFragment, new BrushFragmentNavigation.Builder(contentMetadata.getTarget()).createFragment(), true, true, true);
            }
        }
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, contentMetadata, null, 8, null);
        Fragment fragment = this.mFragment;
        String target = contentMetadata.getTarget();
        String title = contentMetadata.getTitle();
        String subTitle = contentMetadata.getSubTitle();
        String string = contentMetadata.getUri().toString();
        Intrinsics.checkNotNullExpressionValue(string, "contentMetadata.uri.toString()");
        EntityTrackingIntentUtil.sendEntityVisitEvent(fragment, target, title, subTitle, string, contentMetadata.getId());
    }

    private final void onLiveEventContentClicked(LiveEventMetadata contentMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        DeeplinksManager.get(AmazonApplication.getContext()).handle(contentMetadata.getUri());
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, ActionType.GO_LIVE_EVENT, contentMetadata, null, null, 48, null);
    }

    private final void onLivestreamContentClicked(LivestreamMetadata contentMetadata) {
        ExploreFactory.openLiveStreamPage(this.mFragment.getContext(), contentMetadata.getStreamId());
        SearchClickEventUtil.sendClickEvent$default(this.mFragment, this.pageType, ActionType.SELECT_LIVE_STREAM, contentMetadata, null, null, 48, null);
    }

    private final void onSearchQueryContentClicked(SearchQueryMetadata contentMetadata) {
        Fragment fragment = this.mFragment;
        if (fragment instanceof SearchBrushFragment) {
            ((SearchBrushFragment) fragment).getViewModel().executeSearchFromMetadata(contentMetadata);
        }
    }

    private final void onTrackContentClicked(TrackMetadata contentMetadata) {
        playContentMetadata(CollectionsKt__CollectionsJVMKt.listOf(contentMetadata), 0);
    }

    private final void sendEntityRefreshEvent(Fragment fragment, String entityId, EntityIdType entityIdType) {
        if (fragment instanceof SearchBrushFragment) {
            SearchBrushFragment searchBrushFragment = (SearchBrushFragment) fragment;
            if (searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEARCH_HISTORY || searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEARCH_SUGGESTION || searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEARCH_RESULT || searchBrushFragment.getCurrentMode() == SearchExperienceMode.SEE_ALL) {
                EntityTrackingIntentUtil.INSTANCE.sendEntityRefreshIntentEvent(searchBrushFragment.getContext(), entityId, entityIdType);
            }
        }
    }
}
