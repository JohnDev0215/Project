package com.amazon.mp3.library.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.Card;
import com.amazon.mp3.card.CardFactory;
import com.amazon.mp3.card.prime.AlbumCardAdapter;
import com.amazon.mp3.card.prime.PlaylistCardAdapter;
import com.amazon.mp3.card.prime.PrimeCard;
import com.amazon.mp3.card.prime.StationCardAdapter;
import com.amazon.mp3.card.prime.TrackCardAdapter;
import com.amazon.mp3.card.prime.manager.AlbumCardManager;
import com.amazon.mp3.card.prime.manager.PlaylistCardManager;
import com.amazon.mp3.card.prime.manager.StationCardManager;
import com.amazon.mp3.card.prime.manager.TrackCardManager;
import com.amazon.mp3.eux.EUXProvider;
import com.amazon.mp3.library.fragment.BasePrimeFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.RecommendationsFactory;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.view.refinements.ViewLoadingMonitor;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.recommendation.rx.RxRecommendation;
import com.amazon.music.recommendation.widgets.BrowseWidgetRequest;
import com.amazon.music.recommendation.widgets.RecommendationWidget;
import com.amazon.music.recommendation.widgets.RecommendationWidgetResult;
import com.amazon.music.recommendation.widgets.RecommendationWidgetType;
import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import com.amazon.music.storeservice.model.RecommendedPlaylistItem;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.RecommendedTrackItem;
import com.amazon.music.widget.eux.adapters.EUXBrowseRecommendationsAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class RecommendationsTabFragment extends BasePrimeFragment {
    private static final String TAG = RecommendationsTabFragment.class.getSimpleName();
    private View mCardContainer;
    private View mEmptyLayout;
    private View mErrorLayout;
    private View mLoadingView;
    private Observable<RecommendationWidgetResult> mMetadataRequest;
    private boolean mPausedSinceVisible;
    private FrameLayout mRootView;
    private Subscription mSubscription;
    private ViewLoadingMonitor mViewMonitor;
    private CardFactory cardFactory = new CardFactory();
    private List<Card> mRecommendationCards = new ArrayList();

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(View.OnTouchListener listener) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RecommendationsTabFragment newInstance() {
        return new RecommendationsTabFragment();
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected String getFragmentTag() {
        return TAG;
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected MediaProvider.PrimeBrowseTracksCollection.ContentSource getPageContentSource() {
        return MediaProvider.PrimeBrowseTracksCollection.ContentSource.RECOMMENDED;
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.recommendations_tab_layout, container, false);
        this.mRootView = frameLayout;
        View viewFindViewById = frameLayout.findViewById(R.id.card_container);
        this.mCardContainer = viewFindViewById;
        viewFindViewById.setVisibility(8);
        this.mListView = (ScrollView) this.mRootView.findViewById(R.id.list_view);
        View viewFindViewById2 = this.mRootView.findViewById(R.id.loading_spinner);
        this.mLoadingView = viewFindViewById2;
        viewFindViewById2.setVisibility(0);
        View viewFindViewById3 = this.mRootView.findViewById(R.id.empty_layout);
        this.mErrorLayout = viewFindViewById3;
        viewFindViewById3.setVisibility(8);
        createViewMonitor();
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("com.amazon.mp3.STANDALONE_FRAGMENT_KEY")) {
            FragmentActivity activity = getActivity();
            int i = arguments.getInt("com.amazon.mp3.STANDALONE_FRAGMENT_TITLE_KEY", 0);
            if (i != 0 && (activity instanceof NavigationActivity)) {
                Resources resources = activity.getResources();
                NavigationActivity navigationActivity = (NavigationActivity) activity;
                navigationActivity.setHeaderView(new ActionBarView(getActivity(), resources.getString(i), false, true));
                navigationActivity.requestHomeButtonAsBack();
            }
            this.mPausedSinceVisible = true;
        }
        init();
        return this.mRootView;
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getCacheMap().save(TAG, this.mMetadataRequest);
        Subscription subscription = this.mSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.mSubscription = null;
        }
        this.mListView = null;
        this.mMetadataRequest = null;
        this.mRootView = null;
        this.mLoadingView = null;
        this.mEmptyLayout = null;
        this.mErrorLayout = null;
        destroyViewMonitor();
        this.mViewMonitor = null;
        destroyCards();
        this.mCardContainer = null;
    }

    private void destroyViewMonitor() {
        this.mViewMonitor.setOnViewsLoadingListener(null);
        this.mViewMonitor.deregisterAllViews();
    }

    private void destroyCards() {
        LinearLayout linearLayout = (LinearLayout) this.mCardContainer;
        for (Card card : this.mRecommendationCards) {
            card.setOnItemClickListener(null);
            card.setFooterClickListener(null);
            if (linearLayout != null) {
                linearLayout.removeView(card);
            }
        }
        this.mRecommendationCards.clear();
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getActivity().getApplicationContext();
        if (this.mPausedSinceVisible) {
            sendUiPageViewEvent();
            this.mPausedSinceVisible = false;
        }
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mPausedSinceVisible = getUserVisibleHint();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            sendUiPageViewEvent();
        } else {
            this.mPausedSinceVisible = false;
        }
    }

    private void sendUiPageViewEvent() {
        Bundle arguments = getArguments();
        arguments.putString("UI_PAGE_VIEW_PAGE_ID", "browseRecommended");
        EventBus.getDefault().post(arguments);
    }

    private void init() {
        Observable<RecommendationWidgetResult> observable = (Observable) getCacheMap().read(TAG);
        if (observable != null) {
            this.mMetadataRequest = observable;
        } else {
            createMetadataRequest(getMaxItemsCount());
        }
        subscribeMetadataRequest();
        setListView(this.mListView);
    }

    private void createViewMonitor() {
        ViewLoadingMonitor viewLoadingMonitor = new ViewLoadingMonitor();
        this.mViewMonitor = viewLoadingMonitor;
        viewLoadingMonitor.setOnViewsLoadingListener(new ViewLoadingMonitor.OnViewsLoadingListener() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.1
            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onViewsLoading() {
                RecommendationsTabFragment.this.mLoadingView.setVisibility(0);
                RecommendationsTabFragment.this.mCardContainer.setVisibility(8);
                RecommendationsTabFragment.this.mErrorLayout.setVisibility(8);
            }

            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onAllViewsLoaded() {
                RecommendationsTabFragment.this.mLoadingView.setVisibility(8);
                RecommendationsTabFragment.this.mCardContainer.setVisibility(0);
                RecommendationsTabFragment.this.mErrorLayout.setVisibility(8);
                RecommendationsTabFragment.this.scrollToLastPosition();
            }

            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onViewsError() {
                RecommendationsTabFragment.this.mLoadingView.setVisibility(8);
                RecommendationsTabFragment.this.mErrorLayout.setVisibility(0);
                RecommendationsTabFragment.this.mCardContainer.setVisibility(8);
            }
        });
        this.mViewMonitor.registerView(this.mCardContainer);
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected void refreshData() {
        createMetadataRequest(getMaxItemsCount());
    }

    @Override // com.amazon.mp3.library.fragment.BasePrimeFragment
    protected PageType getPageType() {
        return PageType.BROWSE_RECOMMENDED;
    }

    private void createMetadataRequest(final int maxItemsCount) {
        BootstrapSingletonTask.get(getContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1<Void>() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.2
            @Override // rx.functions.Action1
            public void call(final Void aVoid) {
                RxRecommendation rxRecommendationCreateRxBrowseRecommendation = RecommendationsFactory.createRxBrowseRecommendation(RecommendationsTabFragment.this.getActivity());
                BrowseWidgetRequest browseWidgetRequest = new BrowseWidgetRequest(maxItemsCount, new BrowseContentSelectionProvider().getRecommendationsContentSelection(RecommendationsTabFragment.this.getActivity()), AccountCredentialStorage.get().getDeviceId(), AccountCredentialStorage.get().getDeviceType()) { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.2.1
                    {
                        setExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled());
                    }
                };
                RecommendationsTabFragment.this.mMetadataRequest = rxRecommendationCreateRxBrowseRecommendation.getBrowseRecommendationWidgetsResult(browseWidgetRequest);
                RecommendationsTabFragment.this.subscribeMetadataRequest();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.3
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(RecommendationsTabFragment.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subscribeMetadataRequest() {
        Observable<RecommendationWidgetResult> observable = this.mMetadataRequest;
        if (observable == null) {
            return;
        }
        this.mSubscription = observable.cache().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RecommendationWidgetResult>() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.4
            @Override // rx.Observer
            public void onCompleted() {
                Log.debug(RecommendationsTabFragment.TAG, "Metadata Request On Completed");
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                if (RecommendationsTabFragment.this.mViewMonitor != null) {
                    RecommendationsTabFragment.this.mViewMonitor.notifyViewState(RecommendationsTabFragment.this.mCardContainer, ViewLoadingMonitor.ViewState.ERROR);
                    RecommendationsTabFragment.this.populateRecommendationCards(Arrays.asList(new RecommendationWidget[0]));
                }
                Log.error(RecommendationsTabFragment.TAG, "Metadata Request On Error: ", e);
            }

            @Override // rx.Observer
            public void onNext(RecommendationWidgetResult result) {
                if (result.getRecommendations().isEmpty()) {
                    RecommendationsTabFragment.this.showEmptyView();
                } else if (RecommendationsTabFragment.this.mViewMonitor != null) {
                    RecommendationsTabFragment.this.mViewMonitor.notifyViewState(RecommendationsTabFragment.this.mCardContainer, ViewLoadingMonitor.ViewState.LOADING);
                    RecommendationsTabFragment.this.populateRecommendationCards(result.getRecommendations());
                    RecommendationsTabFragment.this.mViewMonitor.notifyViewState(RecommendationsTabFragment.this.mCardContainer, ViewLoadingMonitor.ViewState.LOADED);
                }
                RecommendationsTabFragment.this.onListViewCreated();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getRecommendationWidgetId(final RecommendationWidget widget) {
        return widget.getWidgetId() == null ? "" : widget.getWidgetId();
    }

    private List<Map<String, String>> getRecommendationContentInfo(final RecommendationWidget widget) {
        if (widget.getInfo() == null || widget.getInfo().getContentInfoMap() == null) {
            return null;
        }
        return Collections.singletonList(widget.getInfo().getContentInfoMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyView() {
        this.mLoadingView.setVisibility(8);
        this.mErrorLayout.setVisibility(8);
        this.mCardContainer.setVisibility(8);
        this.mEmptyLayout = getEmptyView();
        this.mEmptyLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.mRootView.addView(this.mEmptyLayout, 0);
        this.mEmptyLayout.bringToFront();
        this.mEmptyLayout.setVisibility(0);
    }

    private View getEmptyView() {
        return new EUXBrowseRecommendationsAdapter(getContext()).getView(EUXProvider.buildEUXConfiguration(getContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateRecommendationCards(final List<RecommendationWidget> result) {
        LinearLayout linearLayout = (LinearLayout) this.mCardContainer;
        destroyCards();
        Iterator<RecommendationWidget> it = result.iterator();
        while (it.hasNext()) {
            Card cardBuildRecommendationCard = buildRecommendationCard(it.next());
            if (cardBuildRecommendationCard != null) {
                this.mRecommendationCards.add(cardBuildRecommendationCard);
                linearLayout.addView(cardBuildRecommendationCard);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.RecommendationsTabFragment$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType;

        static {
            int[] iArr = new int[RecommendationWidgetType.values().length];
            $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType = iArr;
            try {
                iArr[RecommendationWidgetType.STATIONS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.PLAYLISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.ALBUMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.ALBUMS_UPSELL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.TRACKS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Card buildRecommendationCard(RecommendationWidget recommendation) {
        int i = AnonymousClass9.$SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[recommendation.getType().ordinal()];
        if (i == 1) {
            if (!AmazonApplication.getCapabilities().shouldShowStationsRecommendations()) {
                return null;
            }
            Card<Station, StationCardAdapter> cardBuildStationsCard = this.cardFactory.buildStationsCard(getContext());
            populateRecommendedStations(cardBuildStationsCard, recommendation);
            return cardBuildStationsCard;
        }
        if (i == 2) {
            PrimeCard<PrimePlaylist> primeCardBuildPlaylistsCard = this.cardFactory.buildPlaylistsCard(getContext());
            populateRecommendedPlaylists(primeCardBuildPlaylistsCard, recommendation);
            return primeCardBuildPlaylistsCard;
        }
        if (i == 3) {
            PrimeCard<PrimeAlbum> primeCardBuildAlbumsCard = this.cardFactory.buildAlbumsCard(getContext());
            populateRecommendedAlbums(primeCardBuildAlbumsCard, recommendation);
            return primeCardBuildAlbumsCard;
        }
        if (i == 4) {
            PrimeCard<PrimeAlbum> primeCardBuildAlbumsUpsellCard = this.cardFactory.buildAlbumsUpsellCard(getContext());
            populateRecommendedAlbums(primeCardBuildAlbumsUpsellCard, recommendation);
            return primeCardBuildAlbumsUpsellCard;
        }
        if (i != 5) {
            return null;
        }
        PrimeCard<PrimeTrack> primeCardBuildTracksCard = this.cardFactory.buildTracksCard(getContext());
        populateRecommendedTracks(primeCardBuildTracksCard, recommendation);
        return primeCardBuildTracksCard;
    }

    private void populateRecommendedTracks(final PrimeCard<PrimeTrack> card, final RecommendationWidget<RecommendedTrackItem> widget) {
        List<PrimeTrack> primeTrackListFromRecommendations = PrimeItemsTransformationUtil.toPrimeTrackListFromRecommendations(widget.getItems());
        List listReduceListToCardSize = BasePrimeFragment.reduceListToCardSize(primeTrackListFromRecommendations, 5);
        final List<Map<String, String>> recommendationContentInfo = getRecommendationContentInfo(widget);
        BasePrimeFragment.AddButtonListener addButtonListener = new BasePrimeFragment.AddButtonListener();
        TrackCardAdapter trackCardAdapter = new TrackCardAdapter(getActivity(), listReduceListToCardSize, addButtonListener, false, true, false);
        addButtonListener.setAdapter(trackCardAdapter);
        card.setOnItemClickListener(new TrackCardManager(this, this.mNetworkErrorDialogHandler, card, primeTrackListFromRecommendations, recommendationContentInfo, "BROWSE_RECOMMENDED"));
        card.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RecommendationsTabFragment.this.onMoreFooterClicked(PaginatedDataType.TRACK, PaginatedRankType.RECOMMENDED, recommendationContentInfo, String.valueOf(UniqueCodeUtil.nextUniqueCode()), card.getHeaderText().toString(), null, RecommendationsTabFragment.this.getRecommendationWidgetId(widget));
            }
        });
        card.populate(trackCardAdapter, primeTrackListFromRecommendations.size() >= 5);
    }

    private void populateRecommendedAlbums(final PrimeCard<PrimeAlbum> card, final RecommendationWidget<RecommendedAlbumItem> widget) {
        int integer = getActivity().getResources().getInteger(R.integer.grid_tile_num_columns) * 2;
        List<PrimeAlbum> primeAlbumListFromRecommendations = PrimeItemsTransformationUtil.toPrimeAlbumListFromRecommendations(widget.getItems());
        List listReduceListToCardSize = BasePrimeFragment.reduceListToCardSize(primeAlbumListFromRecommendations, integer);
        final List<Map<String, String>> recommendationContentInfo = getRecommendationContentInfo(widget);
        AlbumCardAdapter albumCardAdapterBuild = new AlbumCardAdapter.Builder(getActivity(), listReduceListToCardSize).useGrid().showRecommendationReason().build();
        card.setOnItemClickListener(new AlbumCardManager(this, this.mNetworkErrorDialogHandler, card, recommendationContentInfo, "BROWSE_RECOMMENDED"));
        card.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RecommendationsTabFragment.this.onMoreFooterClicked(PaginatedDataType.ALBUM, PaginatedRankType.RECOMMENDED, recommendationContentInfo, String.valueOf(UniqueCodeUtil.nextUniqueCode()), card.getHeaderText().toString(), null, RecommendationsTabFragment.this.getRecommendationWidgetId(widget));
            }
        });
        card.populate(albumCardAdapterBuild, primeAlbumListFromRecommendations.size() >= integer);
    }

    private void populateRecommendedPlaylists(final PrimeCard<PrimePlaylist> card, final RecommendationWidget<RecommendedPlaylistItem> widget) {
        List<PrimePlaylist> primePlaylistListFromRecommendations = PrimeItemsTransformationUtil.toPrimePlaylistListFromRecommendations(widget.getItems());
        List listReduceListToCardSize = BasePrimeFragment.reduceListToCardSize(primePlaylistListFromRecommendations, 5);
        final List<Map<String, String>> recommendationContentInfo = getRecommendationContentInfo(widget);
        PlaylistCardAdapter playlistCardAdapter = new PlaylistCardAdapter(getActivity(), listReduceListToCardSize, false);
        card.setOnItemClickListener(new PlaylistCardManager(this, (MusicHomeActivity) getActivity(), this.mNetworkErrorDialogHandler, card, recommendationContentInfo, "BROWSE_RECOMMENDED"));
        card.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RecommendationsTabFragment.this.onMoreFooterClicked(PaginatedDataType.PLAYLIST, PaginatedRankType.RECOMMENDED, recommendationContentInfo, String.valueOf(UniqueCodeUtil.nextUniqueCode()), card.getHeaderText().toString(), null, RecommendationsTabFragment.this.getRecommendationWidgetId(widget));
            }
        });
        card.populate(playlistCardAdapter, primePlaylistListFromRecommendations.size() >= 5);
    }

    private void populateRecommendedStations(final Card<Station, StationCardAdapter> card, final RecommendationWidget<RecommendedStationItem> widget) {
        int integer = getActivity().getResources().getInteger(R.integer.grid_tile_num_columns) * 2;
        List<Station> stationListFromRecommendations = PrimeItemsTransformationUtil.toStationListFromRecommendations(widget.getItems());
        List listReduceListToCardSize = BasePrimeFragment.reduceListToCardSize(stationListFromRecommendations, integer);
        final List<Map<String, String>> recommendationContentInfo = getRecommendationContentInfo(widget);
        StationCardAdapter stationCardAdapter = new StationCardAdapter(getActivity(), listReduceListToCardSize, true, true);
        card.setOnItemClickListener(new StationCardManager(this, this.mNetworkErrorDialogHandler, PlaybackPageType.UNKNOWN, recommendationContentInfo, "BROWSE_RECOMMENDED"));
        card.setFooterClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.RecommendationsTabFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RecommendationsTabFragment.this.onMoreFooterClicked(PaginatedDataType.STATION, PaginatedRankType.RECOMMENDED, recommendationContentInfo, String.valueOf(UniqueCodeUtil.nextUniqueCode()), card.getHeaderText().toString(), null, RecommendationsTabFragment.this.getRecommendationWidgetId(widget));
            }
        });
        card.populate(stationCardAdapter, stationListFromRecommendations.size() >= integer);
    }

    private int getMaxItemsCount() {
        return Math.max(10, Math.max(5, 5));
    }

    public static class Builder extends BasePrimeFragment.BaseBuilder {
        public Builder(@NonNull Bundle bundle) {
            super(bundle);
        }

        @Override // com.amazon.mp3.library.fragment.BasePrimeFragment.BaseBuilder
        public BasePrimeFragment build() {
            RecommendationsTabFragment recommendationsTabFragmentNewInstance = RecommendationsTabFragment.newInstance();
            recommendationsTabFragmentNewInstance.setArguments(this.bundle);
            return recommendationsTabFragmentNewInstance;
        }
    }
}
