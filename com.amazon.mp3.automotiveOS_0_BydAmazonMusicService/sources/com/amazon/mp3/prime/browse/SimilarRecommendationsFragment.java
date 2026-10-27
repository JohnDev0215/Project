package com.amazon.mp3.prime.browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.Card;
import com.amazon.mp3.card.prime.AlbumCardAdapter;
import com.amazon.mp3.card.prime.PlaylistCardAdapter;
import com.amazon.mp3.card.prime.PrimeCard;
import com.amazon.mp3.card.prime.StationCardAdapter;
import com.amazon.mp3.card.prime.TrackCardAdapter;
import com.amazon.mp3.card.prime.manager.AlbumCardManager;
import com.amazon.mp3.card.prime.manager.PlaylistCardManager;
import com.amazon.mp3.card.prime.manager.StationCardManager;
import com.amazon.mp3.card.prime.manager.TrackCardManager;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.BasePrimeFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.view.refinements.ViewLoadingMonitor;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.recommendation.RecommendationResult;
import com.amazon.music.recommendation.SimilarityRequest;
import com.amazon.music.recommendation.rx.RxRecommendation;
import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import com.amazon.music.storeservice.model.RecommendedPlaylistItem;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.RecommendedTrackItem;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class SimilarRecommendationsFragment extends BasePrimeFragment implements OverflowMenuReceiver {
    private static final String TAG = SimilarRecommendationsFragment.class.getSimpleName();
    private ActionBarProvider mActionBarProvider;
    private LinearLayout mCardContainer;
    private PaginatedDataType mDataType;
    private View mEmptyLayout;
    private View mLoadingView;
    private Observable<RecommendationResult> mMetadataRequest;
    private PageType mPageType;
    private PrimeCard<PrimeAlbum> mSimilarAlbumsCard;
    private PrimeCard<PrimePlaylist> mSimilarPlaylistsCard;
    private PrimeCard<PrimeTrack> mSimilarSongsCard;
    private Card<Station, StationCardAdapter> mSimilarStationsCard;
    private String mSimilarToAsin;
    private Subscription mSubscription;
    private ViewLoadingMonitor mViewMonitor;

    /* JADX INFO: Access modifiers changed from: private */
    public static SimilarRecommendationsFragment newInstance() {
        return new SimilarRecommendationsFragment();
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected String getFragmentTag() {
        return TAG + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + this.mSimilarToAsin;
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected MediaProvider.PrimeBrowseTracksCollection.ContentSource getPageContentSource() {
        return MediaProvider.PrimeBrowseTracksCollection.ContentSource.SIMILAR;
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        this.mActionBarProvider = provider;
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        PageType pageType;
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSimilarToAsin = arguments.getString("SIMILAR_RECS_FRAGMENT_ASIN_KEY", null);
            this.mDataType = (PaginatedDataType) arguments.getSerializable("SIMILAR_RECS_FRAGMENT_DATA_TYPE_KEY");
            if (arguments.getSerializable("SIMILAR_RECS_FRAGMENT_PAGE_TYPE_KEY") == null) {
                pageType = PageType.BROWSE_SIMILARITY_RECOMMENDATIONS;
            } else {
                pageType = (PageType) arguments.getSerializable("SIMILAR_RECS_FRAGMENT_PAGE_TYPE_KEY");
            }
            this.mPageType = pageType;
        }
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onResume() {
        ActionBarView actionBarView;
        super.onResume();
        String string = getString(R.string.dmusic_similar_recs_context_menu_title);
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            ((NowPlayingFragmentActivity) getActivity()).showActionBar();
            actionBarView = new ActionBarView(getActivity(), "");
        } else {
            actionBarView = new ActionBarView(getActivity(), string);
        }
        ActionBarProvider actionBarProvider = this.mActionBarProvider;
        if (actionBarProvider != null) {
            actionBarProvider.setHeaderView(actionBarView);
            this.mActionBarProvider.requestHomeButtonAsBack();
        }
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.similar_recommendations_layout, container, false);
        this.mSimilarAlbumsCard = (PrimeCard) viewInflate.findViewById(R.id.similar_albums);
        this.mSimilarStationsCard = (Card) viewInflate.findViewById(R.id.similar_stations);
        this.mSimilarSongsCard = (PrimeCard) viewInflate.findViewById(R.id.similar_songs);
        this.mSimilarPlaylistsCard = (PrimeCard) viewInflate.findViewById(R.id.similar_playlists);
        this.mSimilarAlbumsCard.setHeaderText(getString(R.string.dmusic_similar_albums_for_you));
        this.mSimilarSongsCard.setHeaderText(getString(R.string.dmusic_similar_songs_for_you));
        this.mSimilarPlaylistsCard.setHeaderText(getString(R.string.dmusic_similar_playlists_for_you));
        this.mSimilarStationsCard.setHeaderText(getString(R.string.dmusic_similar_stations_for_you));
        if (PageType.DETAIL_ALBUM.equals(this.mPageType)) {
            setAlbumDetailPageCardHeaderStyling(this.mSimilarAlbumsCard);
            setAlbumDetailPageCardHeaderStyling(this.mSimilarStationsCard);
            setAlbumDetailPageCardHeaderStyling(this.mSimilarSongsCard);
            setAlbumDetailPageCardHeaderStyling(this.mSimilarPlaylistsCard);
        }
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            setHeaderFontStyle(this.mSimilarAlbumsCard);
            setHeaderFontStyle(this.mSimilarStationsCard);
            setHeaderFontStyle(this.mSimilarSongsCard);
            setHeaderFontStyle(this.mSimilarPlaylistsCard);
        }
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.card_container);
        this.mCardContainer = linearLayout;
        linearLayout.setVisibility(8);
        this.mListView = (ScrollView) viewInflate.findViewById(R.id.list_view);
        View viewFindViewById = viewInflate.findViewById(R.id.loading_spinner);
        this.mLoadingView = viewFindViewById;
        viewFindViewById.setVisibility(0);
        View viewFindViewById2 = viewInflate.findViewById(R.id.empty_layout);
        this.mEmptyLayout = viewFindViewById2;
        viewFindViewById2.setVisibility(8);
        orderCardsByDataType();
        createViewMonitor();
        init();
        return viewInflate;
    }

    private void setHeaderFontStyle(final Card card) {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SimilarRecommendationsFragment.lambda$setHeaderFontStyle$0(card, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setHeaderFontStyle$0(Card card, StyleSheet styleSheet) {
        card.setHeaderFontStyle(styleSheet.getFontStyle(FontStyleKey.HEADLINE_4));
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getCacheMap().save(getFragmentTag(), this.mMetadataRequest);
        this.mSubscription.unsubscribe();
        this.mViewMonitor.deregisterAllViews();
        this.mViewMonitor.setOnViewsLoadingListener(null);
        this.mSimilarStationsCard.setFooterClickListener(null);
        this.mSimilarStationsCard.setOnItemClickListener(null);
        this.mSimilarPlaylistsCard.setFooterClickListener(null);
        this.mSimilarPlaylistsCard.setOnItemClickListener(null);
        this.mSimilarSongsCard.setFooterClickListener(null);
        this.mSimilarSongsCard.setOnItemClickListener(null);
        this.mSimilarAlbumsCard.setFooterClickListener(null);
        this.mSimilarAlbumsCard.setOnItemClickListener(null);
    }

    private void setAlbumDetailPageCardHeaderStyling(View card) {
        TextView textView = (TextView) card.findViewById(R.id.subheader);
        View viewFindViewById = card.findViewById(R.id.header_container);
        View viewFindViewById2 = card.findViewById(R.id.header);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.spacing_20dp);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.spacing_40dp);
        textView.setPadding(0, 0, 0, 0);
        textView.setHeight(0);
        viewFindViewById.setPadding(0, dimensionPixelSize2 - viewFindViewById2.getPaddingTop(), 0, dimensionPixelSize);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType;

        static {
            int[] iArr = new int[PaginatedDataType.values().length];
            $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType = iArr;
            try {
                iArr[PaginatedDataType.STATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.TRACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.ALBUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void orderCardsByDataType() {
        View view;
        int i = AnonymousClass7.$SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[this.mDataType.ordinal()];
        if (i == 1) {
            view = this.mSimilarStationsCard;
        } else if (i == 2) {
            view = this.mSimilarSongsCard;
        } else if (i == 3) {
            view = this.mSimilarAlbumsCard;
        } else {
            view = i != 4 ? null : this.mSimilarPlaylistsCard;
        }
        this.mCardContainer.removeView(view);
        this.mCardContainer.addView(view, 0);
    }

    private void init() {
        Observable<RecommendationResult> observable = (Observable) getCacheMap().read(getFragmentTag());
        if (observable != null) {
            this.mMetadataRequest = observable;
        } else {
            createMetadataRequest();
        }
        subscribeMetadataRequest();
        setListView(this.mListView);
    }

    private void createViewMonitor() {
        ViewLoadingMonitor viewLoadingMonitor = new ViewLoadingMonitor();
        this.mViewMonitor = viewLoadingMonitor;
        viewLoadingMonitor.setOnViewsLoadingListener(new ViewLoadingMonitor.OnViewsLoadingListener() { // from class: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment.1
            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onViewsLoading() {
                SimilarRecommendationsFragment.this.mLoadingView.setVisibility(0);
                SimilarRecommendationsFragment.this.mCardContainer.setVisibility(8);
                SimilarRecommendationsFragment.this.mEmptyLayout.setVisibility(8);
            }

            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onAllViewsLoaded() {
                SimilarRecommendationsFragment.this.mLoadingView.setVisibility(8);
                SimilarRecommendationsFragment.this.mCardContainer.setVisibility(0);
                SimilarRecommendationsFragment.this.mEmptyLayout.setVisibility(8);
                SimilarRecommendationsFragment.this.scrollToLastPosition();
            }

            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onViewsError() {
                SimilarRecommendationsFragment.this.mLoadingView.setVisibility(8);
                SimilarRecommendationsFragment.this.mCardContainer.setVisibility(8);
                if (PageType.DETAIL_ALBUM.equals(SimilarRecommendationsFragment.this.mPageType)) {
                    SimilarRecommendationsFragment.this.mEmptyLayout.setVisibility(8);
                } else {
                    SimilarRecommendationsFragment.this.mEmptyLayout.setVisibility(0);
                }
            }
        });
        this.mViewMonitor.registerView(this.mSimilarAlbumsCard);
        this.mViewMonitor.registerView(this.mSimilarPlaylistsCard);
        this.mViewMonitor.registerView(this.mSimilarSongsCard);
        if (AmazonApplication.getCapabilities().shouldShowStationsRecommendations()) {
            this.mViewMonitor.registerView(this.mSimilarStationsCard);
        }
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected void refreshData() {
        createMetadataRequest();
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected PageType getPageType() {
        return this.mPageType;
    }

    private void createMetadataRequest() {
        FragmentActivity activity = getActivity();
        if (ActivityKt.isAvailable(activity)) {
            RxRecommendation rxRecommendationCreateRxBrowseRecommendation = RecommendationsFactory.createRxBrowseRecommendation(activity);
            SimilarityRequest explicitFilterEnabled = new SimilarityRequest(getMaxItemsCount(), this.mSimilarToAsin, new BrowseContentSelectionProvider().getRecommendationsContentSelection(activity), AccountCredentialStorage.get().getDeviceId(), AccountCredentialStorage.get().getDeviceType()).setExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
            ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
            if (childUserUtil.isChildUser(activity)) {
                explicitFilterEnabled.setMusicRequestIdentityContextToken(childUserUtil.getMusicRequestIdentityContextToken(activity));
            }
            this.mMetadataRequest = rxRecommendationCreateRxBrowseRecommendation.getSimilarityRecommendationResult(explicitFilterEnabled);
        }
    }

    private void subscribeMetadataRequest() {
        this.mSubscription = this.mMetadataRequest.cache().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new rx.Observer<RecommendationResult>() { // from class: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment.2
            @Override // rx.Observer
            public void onCompleted() {
                Log.debug(SimilarRecommendationsFragment.TAG, "Metadata Request On Completed");
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ViewLoadingMonitor viewLoadingMonitor = SimilarRecommendationsFragment.this.mViewMonitor;
                PrimeCard primeCard = SimilarRecommendationsFragment.this.mSimilarPlaylistsCard;
                ViewLoadingMonitor.ViewState viewState = ViewLoadingMonitor.ViewState.ERROR;
                viewLoadingMonitor.notifyViewState(primeCard, viewState);
                SimilarRecommendationsFragment.this.mViewMonitor.notifyViewState(SimilarRecommendationsFragment.this.mSimilarAlbumsCard, viewState);
                SimilarRecommendationsFragment.this.mViewMonitor.notifyViewState(SimilarRecommendationsFragment.this.mSimilarSongsCard, viewState);
                SimilarRecommendationsFragment.this.mViewMonitor.notifyViewState(SimilarRecommendationsFragment.this.mSimilarPlaylistsCard, viewState);
                Log.debug(SimilarRecommendationsFragment.TAG, "Metadata Request On Error: " + e);
            }

            @Override // rx.Observer
            public void onNext(RecommendationResult result) {
                if (SimilarRecommendationsFragment.this.isRecommentationEmpty(result)) {
                    SimilarRecommendationsFragment.this.handleEmptyRecommentation();
                } else {
                    SimilarRecommendationsFragment.this.populateSimilarityCards(result);
                    SimilarRecommendationsFragment.this.onListViewCreated();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEmptyRecommentation() {
        this.mLoadingView.setVisibility(8);
        this.mCardContainer.setVisibility(8);
        if (PageType.DETAIL_ALBUM.equals(this.mPageType)) {
            this.mEmptyLayout.setVisibility(8);
        } else {
            ((TextView) this.mEmptyLayout.findViewById(R.id.error_service_text)).setText(R.string.dmusic_library_empty_filter_no_music_found);
            this.mEmptyLayout.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRecommentationEmpty(RecommendationResult recommendation) {
        return recommendation.getAlbums().isEmpty() && recommendation.getPlaylists().isEmpty() && recommendation.getStations().isEmpty() && recommendation.getTracks().isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateSimilarityCards(RecommendationResult result) {
        populateSimilarTracks(result.getTracks());
        populateSimilarAlbums(result.getAlbums());
        populateSimilarPlaylists(result.getPlaylists());
        if (AmazonApplication.getCapabilities().shouldShowStationsRecommendations()) {
            populateSimilarStations(result.getStations());
        }
    }

    private void emitUIContentView(ContentName contentName, int index, int numItems) {
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.UI_COLLECTION_VIEW).withContentName(contentName).withContainerIndex(index).withNumItemsAccessible(numItems).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    private void populateSimilarTracks(final List<RecommendedTrackItem> trackList) {
        List<PrimeTrack> primeTrackListFromRecommendations = PrimeItemsTransformationUtil.toPrimeTrackListFromRecommendations(trackList);
        List listReduceListToCardSize = BasePrimeFragment.reduceListToCardSize(primeTrackListFromRecommendations, 5);
        BasePrimeFragment.AddButtonListener addButtonListener = new BasePrimeFragment.AddButtonListener();
        TrackCardAdapter trackCardAdapter = new TrackCardAdapter(getActivity(), listReduceListToCardSize, addButtonListener, false, false, Branding.shouldShowPrimeBranding());
        addButtonListener.setAdapter(trackCardAdapter);
        this.mSimilarSongsCard.setOnItemClickListener(new TrackCardManager(this, this.mNetworkErrorDialogHandler, this.mSimilarSongsCard, primeTrackListFromRecommendations, null, getPageType().name()));
        this.mSimilarSongsCard.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SimilarRecommendationsFragment.this.onMoreFooterClicked(PaginatedDataType.TRACK, PaginatedRankType.SIMILAR, String.valueOf(UniqueCodeUtil.nextUniqueCode()), SimilarRecommendationsFragment.this.mSimilarSongsCard.getHeaderText().toString(), SimilarRecommendationsFragment.this.mSimilarToAsin);
            }
        });
        this.mSimilarSongsCard.populate(trackCardAdapter, primeTrackListFromRecommendations.size() >= 5);
        this.mViewMonitor.notifyViewState(this.mSimilarSongsCard, ViewLoadingMonitor.ViewState.LOADED);
    }

    private void populateSimilarAlbums(final List<RecommendedAlbumItem> albumList) {
        int integer = getActivity().getResources().getInteger(R.integer.grid_tile_num_columns) * 2;
        List<PrimeAlbum> primeAlbumListFromRecommendations = PrimeItemsTransformationUtil.toPrimeAlbumListFromRecommendations(albumList);
        AlbumCardAdapter albumCardAdapterBuild = new AlbumCardAdapter.Builder(getActivity(), BasePrimeFragment.reduceListToCardSize(primeAlbumListFromRecommendations, integer)).useGrid().build();
        if (getActivity() instanceof NowPlayingFragmentActivity) {
        }
        NetworkErrorDialog.INetworkErrorDialogHandler iNetworkErrorDialogHandler = this.mNetworkErrorDialogHandler;
        PrimeCard<PrimeAlbum> primeCard = this.mSimilarAlbumsCard;
        String strName = getPageType().name();
        PageType pageType = PageType.DETAIL_ALBUM;
        this.mSimilarAlbumsCard.setOnItemClickListener(new AlbumCardManager(this, iNetworkErrorDialogHandler, primeCard, null, strName, pageType.equals(this.mPageType)));
        this.mSimilarAlbumsCard.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SimilarRecommendationsFragment.this.onMoreFooterClicked(PaginatedDataType.ALBUM, PaginatedRankType.SIMILAR, String.valueOf(UniqueCodeUtil.nextUniqueCode()), SimilarRecommendationsFragment.this.mSimilarAlbumsCard.getHeaderText().toString(), SimilarRecommendationsFragment.this.mSimilarToAsin);
            }
        });
        this.mSimilarAlbumsCard.populate(albumCardAdapterBuild, primeAlbumListFromRecommendations.size() >= integer);
        this.mViewMonitor.notifyViewState(this.mSimilarAlbumsCard, ViewLoadingMonitor.ViewState.LOADED);
        if (!pageType.equals(this.mPageType) || primeAlbumListFromRecommendations.size() <= 0) {
            return;
        }
        emitUIContentView(ContentName.SIMILAR_ALBUMS_RECOMMENDER, 2, Math.min(primeAlbumListFromRecommendations.size(), integer));
    }

    private void populateSimilarPlaylists(final List<RecommendedPlaylistItem> playlistList) {
        List<PrimePlaylist> primePlaylistListFromRecommendations = PrimeItemsTransformationUtil.toPrimePlaylistListFromRecommendations(playlistList);
        PlaylistCardAdapter playlistCardAdapter = new PlaylistCardAdapter(getActivity(), BasePrimeFragment.reduceListToCardSize(primePlaylistListFromRecommendations, 5), false);
        this.mSimilarPlaylistsCard.setOnItemClickListener(new PlaylistCardManager(this, getActivity() instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) getActivity() : (MusicHomeActivity) getActivity(), this.mNetworkErrorDialogHandler, this.mSimilarPlaylistsCard));
        this.mSimilarPlaylistsCard.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SimilarRecommendationsFragment.this.onMoreFooterClicked(PaginatedDataType.PLAYLIST, PaginatedRankType.SIMILAR, String.valueOf(UniqueCodeUtil.nextUniqueCode()), SimilarRecommendationsFragment.this.mSimilarPlaylistsCard.getHeaderText().toString(), SimilarRecommendationsFragment.this.mSimilarToAsin);
            }
        });
        this.mSimilarPlaylistsCard.populate(playlistCardAdapter, primePlaylistListFromRecommendations.size() >= 5);
        this.mViewMonitor.notifyViewState(this.mSimilarPlaylistsCard, ViewLoadingMonitor.ViewState.LOADED);
    }

    private void populateSimilarStations(final List<RecommendedStationItem> stationList) {
        int integer = getActivity().getResources().getInteger(R.integer.grid_tile_num_columns) * 2;
        StationCardAdapter stationCardAdapter = new StationCardAdapter(getActivity(), BasePrimeFragment.reduceListToCardSize(PrimeItemsTransformationUtil.toStationListFromRecommendations(stationList), integer), true, false);
        this.mSimilarStationsCard.setOnItemClickListener(new StationCardManager(this, this.mNetworkErrorDialogHandler, PlaybackPageType.UNKNOWN));
        this.mSimilarStationsCard.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.browse.SimilarRecommendationsFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SimilarRecommendationsFragment.this.onMoreFooterClicked(PaginatedDataType.STATION, PaginatedRankType.SIMILAR, String.valueOf(UniqueCodeUtil.nextUniqueCode()), SimilarRecommendationsFragment.this.mSimilarStationsCard.getHeaderText().toString(), SimilarRecommendationsFragment.this.mSimilarToAsin);
            }
        });
        this.mSimilarStationsCard.populate(stationCardAdapter, stationList.size() >= integer);
        this.mViewMonitor.notifyViewState(this.mSimilarStationsCard, ViewLoadingMonitor.ViewState.LOADED);
    }

    private int getMaxItemsCount() {
        return Math.max(10, Math.max(5, 5));
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    @Nullable
    public String getScreenTitle() {
        return getString(R.string.dmusic_similar_recs_context_menu_title);
    }

    public static class Builder extends BasePrimeFragment.BaseBuilder {
        public Builder(@NonNull Bundle bundle) {
            super(bundle);
        }

        public Builder setAsin(final String asin) {
            this.bundle.putString("SIMILAR_RECS_FRAGMENT_ASIN_KEY", asin);
            return this;
        }

        public Builder setDataType(final PaginatedDataType dataType) {
            this.bundle.putSerializable("SIMILAR_RECS_FRAGMENT_DATA_TYPE_KEY", dataType);
            return this;
        }

        public Builder setPageType(final PageType pageType) {
            this.bundle.putSerializable("SIMILAR_RECS_FRAGMENT_PAGE_TYPE_KEY", pageType);
            return this;
        }

        @Override // com.amazon.mp3.library.fragment.BasePrimeFragment.BaseBuilder
        public BasePrimeFragment build() {
            SimilarRecommendationsFragment similarRecommendationsFragmentNewInstance = SimilarRecommendationsFragment.newInstance();
            similarRecommendationsFragmentNewInstance.setArguments(this.bundle);
            return similarRecommendationsFragmentNewInstance;
        }
    }
}
