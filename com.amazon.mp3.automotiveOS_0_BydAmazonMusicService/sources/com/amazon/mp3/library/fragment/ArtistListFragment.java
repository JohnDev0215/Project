package com.amazon.mp3.library.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.catalog.fragment.provider.InfoProviderLibraryImpl;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.eux.EUXProvider;
import com.amazon.mp3.fragment.contextmenu.ArtistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.ArtistListAdapter;
import com.amazon.mp3.library.adapter.EmptyFilterAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.job.DeleteTrackJob;
import com.amazon.mp3.library.mylibrary.LibraryPreferences;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.view.BauhausRibbon;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.adapters.EUXAdapter;
import com.amazon.music.widget.eux.adapters.EUXArtistsAdapter;
import com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter;
import java.util.Hashtable;
import org.apache.commons.lang3.StringUtils;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistListFragment extends ArtListFragment {
    private BaseViewsFilterActionBarManager mActionbarManager;
    private BauhausRibbon mBauhausRibbon;
    private ArtistContextMenuProvider mContextMenuProvider;
    private boolean mIsLocal;
    private InfoProviderLibraryImpl mLibraryInfoProvider;
    private PlaylistMgmt mPlaylistMgmt;
    private Uri mPlaylistUri;
    private static final String[] PROJECTION = {"_id", "name", "sort_name", "album_count", "track_count", "download_state", "download_status"};
    private static final int CONFIRM_DELETE_ARTIST_ID = UniqueCodeUtil.nextUniqueCode();
    private ArtistListAdapter mAdapter = null;
    private Cursor mCursor = null;
    private boolean mNavigatedToSkyfireADP = false;
    private int mActionBarHeight = 0;
    private boolean mLaunchedFromFollowedArtistsFragment = false;
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.library.fragment.ArtistListFragment.1
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            boolean zPlayStateUpdated = ArtistListFragment.this.playStateUpdated();
            boolean zMediaCollectionUpdated = ArtistListFragment.this.mediaCollectionUpdated();
            if (ArtistListFragment.this.mAdapter != null) {
                if (zPlayStateUpdated || zMediaCollectionUpdated) {
                    ArtistListFragment.this.mAdapter.notifyDataSetChanged();
                }
            }
        }
    };
    private View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.ArtistListFragment.2
        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View unused, ContextMenu.ContextMenuInfo menuInfo) {
            if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                ArtistListFragment artistListFragment = ArtistListFragment.this;
                artistListFragment.mContextMenuId = adapterContextMenuInfo.id;
                artistListFragment.mContextMenuProvider.onCreateContextMenu(ArtistListFragment.this.getActivity(), menu, adapterContextMenuInfo.targetView.getTag(), adapterContextMenuInfo.position, ArtistListFragment.this.getSourceId());
                ArtistListFragment.this.mContextMenuProvider.setPageType(ArtistListFragment.this.getPageType());
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHOW_OVERFLOW).withInteractionType(InteractionType.TAP).withPageType(ArtistListFragment.this.getPageType()).withEventTime(System.currentTimeMillis()).build());
            }
        }
    };
    private ArtistListAdapter.OnPlaylistAddAllButtonClickListener mPlaylistAddAllListener = new ArtistListAdapter.OnPlaylistAddAllButtonClickListener() { // from class: com.amazon.mp3.library.fragment.ArtistListFragment.3
        @Override // com.amazon.mp3.library.adapter.ArtistListAdapter.OnPlaylistAddAllButtonClickListener
        public void onClick(long artistId) {
            ArtistListFragment.this.mPlaylistMgmt.processAddAllButton(ArtistListFragment.this.getArtistTracksContentUri(artistId));
        }
    };
    private final PlaylistMgmt.OnPlaylistMgmt mOnPlaylistMgmt = new PlaylistMgmt.OnPlaylistMgmt() { // from class: com.amazon.mp3.library.fragment.ArtistListFragment.4
        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public Cursor onAddAllTracksCursor() {
            return null;
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onAddAllTracksCursorFinish() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onCachePlaylistJobFinish() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onPlaylistChangedObserver() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public boolean onShuffleExist() {
            return false;
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onUpdateCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylist) {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public long onAddJob(Job job) {
            return ArtistListFragment.this.addJob(job);
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onSetDuration(String duration) {
            PlaylistMgmt.OnPlaylistMgmt onPlaylistMgmt = getOnPlaylistMgmt();
            if (onPlaylistMgmt != null) {
                onPlaylistMgmt.onSetDuration(duration);
            }
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onAddAllTracksJobFinish() {
            PlaylistMgmt.OnPlaylistMgmt onPlaylistMgmt = getOnPlaylistMgmt();
            if (onPlaylistMgmt != null) {
                onPlaylistMgmt.onAddAllTracksJobFinish();
            }
        }

        private PlaylistMgmt.OnPlaylistMgmt getOnPlaylistMgmt() {
            if (ArtistListFragment.this.getActivity() instanceof PlaylistMgmt.OnPlaylistMgmt) {
                return (PlaylistMgmt.OnPlaylistMgmt) ArtistListFragment.this.getActivity();
            }
            return null;
        }
    };

    private boolean shouldShowLibraryArtistBanner(int bannerDismissCount) {
        return bannerDismissCount < 3;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected String getArtColumnName() {
        return "_id";
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        PlaylistMgmt playlistMgmt = new PlaylistMgmt(getActivity(), getActivity(), null, this.mOnPlaylistMgmt, new Intent().putExtras(getArguments()));
        this.mPlaylistMgmt = playlistMgmt;
        playlistMgmt.onCreate(instanceState);
        this.mPlaybackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        InfoProviderLibraryImpl infoProviderLibraryImpl = new InfoProviderLibraryImpl(this.mActionBarHeight, null);
        this.mLibraryInfoProvider = infoProviderLibraryImpl;
        infoProviderLibraryImpl.setDisplayTitle(true);
        this.mLibraryInfoProvider.setTitleText(getString(R.string.dmusic_library_artists_v2_title));
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        this.mNavigatedToSkyfireADP = false;
        this.mBauhausRibbon = (BauhausRibbon) viewOnCreateView.findViewById(R.id.bauhaus_ribbon);
        init();
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldEmitDefaultUiPageViewMetric() {
        return !this.mNavigatedToSkyfireADP;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle instanceState) {
        super.onActivityCreated(instanceState);
        this.mPlaylistMgmt.onActivityCreated(instanceState);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
        LibraryFilters.remove(getFilterId());
        this.mCursor = null;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ArtistListAdapter artistListAdapter = this.mAdapter;
        if (artistListAdapter != null) {
            artistListAdapter.onPauseOrDestroy(getActivity());
        }
        this.mPlaylistMgmt.onDestroy();
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        ArtistListAdapter artistListAdapter = this.mAdapter;
        if (artistListAdapter != null) {
            if (isVisibleToUser) {
                artistListAdapter.onResume(getActivity());
            } else {
                artistListAdapter.onPauseOrDestroy(getActivity());
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mAdapter.onPauseOrDestroy(getActivity());
        this.mPlaylistMgmt.onPause();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mAdapter.onResume(getActivity());
        this.mPlaylistMgmt.onResume();
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.LIBRARY);
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mPlaylistMgmt.onSaveInstanceState(outState);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mPlaylistMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        super.onSourceToggled(source);
        String str = source == ActionBarSourceToggleView.Source.CLOUD ? "cirrus" : "cirrus-local";
        Uri filterContentUri = MediaProvider.Artists.getFilterContentUri(str, "");
        resetContent(filterContentUri, str);
        this.mIsLocal = isSourceLocal();
        this.mAdapter.setContentUri(getActivity(), filterContentUri);
        changeCursor(null);
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void showEmptyUI() {
        MusicHomeActivity musicHomeActivity;
        if (this.mLaunchedFromFollowedArtistsFragment && (musicHomeActivity = (MusicHomeActivity) getActivity()) != null) {
            FragmentController fragmentController = musicHomeActivity.getFragmentController();
            if (fragmentController == null) {
                fragmentController = new FragmentController(musicHomeActivity, R.id.music_screen_container);
            }
            fragmentController.popBackStack();
            return;
        }
        showEmptyView(getEmptyView(), false);
    }

    private View getEmptyView() {
        EUXAdapter eUXArtistsAdapter;
        EUXConfiguration eUXConfigurationBuildEUXConfiguration = EUXProvider.buildEUXConfiguration(getContext());
        if (this.mIsLocal) {
            eUXArtistsAdapter = new EUXOfflineContentAdapter(getContext());
        } else {
            eUXArtistsAdapter = new EUXArtistsAdapter(getContext());
        }
        return eUXArtistsAdapter.getView(eUXConfigurationBuildEUXConfiguration);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected ContextMenuProvider<ArtistListAdapter.RowViewHolder> getContextMenuProvider() {
        return this.mContextMenuProvider;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getEmptyFilterListAdapter() {
        return new EmptyFilterAdapter(getActivity(), getCurrentFilter(), R.string.dmusic_library_empty_filter_no_artists_found);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        requeryCursorInBackground();
        super.onContentUriChanged();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onAvailableItemClick(AbsListView listView, View view, int position, long id) {
        if (id != -1) {
            Uri contentUri = MediaProvider.Artists.getContentUri(getSourceId(), id);
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getActivity(), contentUri, this.mPlaylistUri);
            Uri uri = this.mPlaylistUri;
            if (uri != null) {
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", uri);
                startActivity(intentForContentUri);
            } else {
                String string = ((TextView) view.findViewById(R.id.ArtistName)).getText().toString();
                String asin = getClickedItem().getAsin();
                Fragment fragmentNewInstance = null;
                if (LibraryArtistDetailFragment.shouldDisplayBauhausLibraryPage(getContext(), contentUri)) {
                    Bundle bundle = new Bundle();
                    if ("cirrus".equals(getSourceId())) {
                        bundle.putString("SELECTED_FILTER", RefinementFilterType.LIBRARY.name());
                    }
                    bundle.putLong(Contexts.LibraryArtistDetail.artistId, id);
                    bundle.putString("ARTIST_ASIN", asin);
                    bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
                    MusicHomeActivity musicHomeActivity = (MusicHomeActivity) getActivity();
                    if (this.mLaunchedFromFollowedArtistsFragment && musicHomeActivity != null) {
                        FragmentController fragmentController = musicHomeActivity.getFragmentController();
                        if (fragmentController == null) {
                            fragmentController = new FragmentController(musicHomeActivity, R.id.music_screen_container);
                        }
                        fragmentController.changeScreenFragment(LibraryArtistDetailFragment.newInstance(bundle), true, false);
                    } else {
                        fragmentNewInstance = LibraryArtistDetailFragment.newInstance(bundle);
                    }
                } else if (ConnectivityUtil.hasAnyInternetConnection(getContext()) && AmazonApplication.getCapabilities().isBauhausLibraryArtistDPEnabled() && AmazonApplication.getCapabilities().shouldBrowseBeSupported() && !TextUtils.isEmpty(asin)) {
                    if (DMSFNavigationProvider.openArtistDetailPage(asin, null, null, null)) {
                        this.mNavigatedToSkyfireADP = true;
                    } else {
                        fragmentNewInstance = new BrushFragmentNavigation.Builder("artist").withTitle(string).withAsin(asin).createFragment();
                    }
                } else {
                    fragmentNewInstance = ArtistDetailsFragment.newInstance(getActivity(), string, getSourceId());
                }
                if (fragmentNewInstance != null) {
                    FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out);
                    fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, fragmentNewInstance, fragmentNewInstance.getClass().getSimpleName()).addToBackStack(fragmentNewInstance.getClass().getSimpleName()).commitAllowingStateLoss();
                }
            }
            Artist artist = (Artist) getClickedItem();
            UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(ActionType.SELECT_ARTIST).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEventTime(System.currentTimeMillis());
            if (StringUtils.isNotBlank(artist.getAsin())) {
                builderWithEventTime.withEntityId(artist.getAsin()).withEntityIdType(EntityIdType.ASIN);
            }
            MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onMediaScannerFinished() {
        super.onMediaScannerFinished();
        if (this.mIsLocal) {
            requeryCursorInBackground();
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        this.mCursor = c;
        changeCursor(c);
        checkFastScrollerRequired(this.mCursor, 25);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected Cursor query() {
        String str;
        String[] strArr;
        if (isSourceLocal()) {
            str = null;
            strArr = null;
        } else {
            str = "ownership_status < ? AND playlist_track_status = ?";
            strArr = new String[]{String.valueOf(300), String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())};
        }
        return CursorListFragment.createCursor(getApplication(), getContentUri(), PROJECTION, str, strArr, null);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getAdapter() {
        return this.mAdapter;
    }

    private void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mPlaylistUri = (Uri) arguments.getParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI");
            this.mLaunchedFromFollowedArtistsFragment = arguments.getBoolean("LibraryFollowedArtistFragment", false);
        }
        if (this.mAdapter == null) {
            this.mAdapter = new ArtistListAdapter(getActivity(), getContentUri(), this.mPlaylistUri, this.mUseGridView, this);
        }
        if (this.mLaunchedFromFollowedArtistsFragment) {
            setUpAdapterForLibraryArtistsV2(this.mAdapter);
        }
        this.mAdapter.setPlaylistAddAllButtonClickListener(this.mPlaylistAddAllListener);
        this.mContextMenuProvider = new ArtistContextMenuProvider((BaseActivity) getActivity());
        this.mIsLocal = isSourceLocal();
        if (this.mPlaylistUri == null && !this.mLaunchedFromFollowedArtistsFragment) {
            getListView().setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        } else {
            getListView().setOnCreateContextMenuListener(null);
        }
        checkFastScrollerRequired(this.mCursor, 25);
    }

    private void setUpAdapterForLibraryArtistsV2(final ArtistListAdapter adapter) {
        if (getListView() != null && (getListView() instanceof ListView)) {
            ((ListView) getListView()).setDivider(null);
        }
        StyleSheetProvider.getStyleSheetObservable().observeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.ArtistListFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setUpAdapterForLibraryArtistsV2$0(adapter, (StyleSheet) obj);
            }
        });
        adapter.setLaunchedFromFollowedArtistsFragment(true);
        initializeActionBarManagerForLibraryArtistsV2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpAdapterForLibraryArtistsV2$0(ArtistListAdapter artistListAdapter, StyleSheet styleSheet) {
        if (styleSheet != null) {
            artistListAdapter.setStyleSheet(styleSheet);
            setUpBauhausRibbon(styleSheet);
        }
    }

    private void initializeActionBarManagerForLibraryArtistsV2() {
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = new BaseViewsFilterActionBarManager(this, this.mLibraryInfoProvider, null, (ActionBarProvider) getActivity());
        this.mActionbarManager = baseViewsFilterActionBarManager;
        baseViewsFilterActionBarManager.shouldShowFilterButton();
        this.mActionbarManager.shouldShowExposedRefinementList(false);
        this.mActionbarManager.shouldShowPageComponentInTopAppBar();
        this.mActionbarManager.hideContextMenuOverflowButton();
        this.mActionbarManager.forceShowTitle();
    }

    private void setUpBauhausRibbon(final StyleSheet styleSheet) {
        if (this.mBauhausRibbon != null) {
            final int libraryArtistsBannerDismissCount = LibraryPreferences.INSTANCE.readLibraryArtistsBannerDismissCount(requireContext());
            if (shouldShowLibraryArtistBanner(libraryArtistsBannerDismissCount)) {
                this.mBauhausRibbon.initViewStyling(styleSheet);
                this.mBauhausRibbon.setRibbonTextAlignment(2);
                this.mBauhausRibbon.setRibbonTextStyle(styleSheet, FontStyleKey.SECONDARY);
                this.mBauhausRibbon.setRoundedBackground();
                this.mBauhausRibbon.setRibbonText(getString(R.string.dmusic_library_artists_v2_educational_banner));
                this.mBauhausRibbon.setRibbonDismissIcon(Integer.valueOf(R.drawable.ic_action_cancel_inline));
                this.mBauhausRibbon.setRibbonNavigationIcon(null);
                this.mBauhausRibbon.setDismissButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.ArtistListFragment$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setUpBauhausRibbon$1(libraryArtistsBannerDismissCount, view);
                    }
                });
                showBauhausRibbon();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpBauhausRibbon$1(int i, View view) {
        LibraryPreferences.INSTANCE.writeLibraryArtistsBannerDismissCount(requireContext(), i + 1);
        dismissBauhausRibbon();
    }

    private void showBauhausRibbon() {
        if (this.mBauhausRibbon != null) {
            MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.BANNER).withContentName(ContentName.LIBRARY_BANNER).withImpressionTimestamp(System.currentTimeMillis()).withPageType(PageType.CLOUD_LIBRARY_ARTISTS.getMetricsValue()).build());
            this.mBauhausRibbon.setVisibility(0);
        }
    }

    private void dismissBauhausRibbon() {
        if (this.mBauhausRibbon != null) {
            MetricsLogger.sendEvent(UiClickEvent.builder(ActionType.DISMISS_LIBRARY_ARTISTS_BANNER).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEventTime(System.currentTimeMillis()).withContentInfo(MetricsLogger.getContentInfo(ContentName.LIBRARY_BANNER.getMetricsValue())).build());
            this.mBauhausRibbon.setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        if (this.mFromTabHost) {
            return;
        }
        super.initListViewHeaderForPortrait();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getArtistTracksContentUri(long id) {
        return MediaProvider.Artists.Tracks.getContentUri(getSourceId(), id);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected CacheManager getAlbumArtCache() {
        return CacheManager.getInstance();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected String getHeaderText() {
        return getString(R.string.dmusic_library_artists_tab);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.ARTIST;
    }

    private Dialog buildConfirmDeleteArtistDialog(final Uri tracksUri, final int trackCount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.dmusic_button_yes, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.ArtistListFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BauhausToastUtils.showTextToast(ArtistListFragment.this.getActivity(), ArtistListFragment.this.getDeleteContentMessage(), 1);
                ArtistListFragment.this.addJob(new DeleteTrackJob(tracksUri));
            }
        });
        builder.setNegativeButton(R.string.dmusic_button_no, (DialogInterface.OnClickListener) null);
        builder.setTitle(R.string.dmusic_context_delete_cloud);
        builder.setMessage(getResources().getQuantityString(R.plurals.dmusic_artist_confirm_delete_cloud_msg, trackCount, Integer.valueOf(trackCount)));
        return builder.create();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void deleteCloudContent() {
        Artist artist = (Artist) getClickedItem();
        showDialog(CONFIRM_DELETE_ARTIST_ID, buildConfirmDeleteArtistDialog(artist.getContentUri(), (int) artist.getTrackCount()));
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getDeleteContentMessage() {
        return getClickedItem().isAvailableOffline() ? R.string.dmusic_artist_toast_deleting_track_device : R.string.dmusic_artist_toast_deleting_track_cloud;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        if (this.mPlaylistUri != null) {
            return PageType.ADD_TO_PLAYLIST_ARTISTS;
        }
        if (this.mIsLocal) {
            return PageType.OFFLINE_LIBRARY_ARTISTS;
        }
        return PageType.CLOUD_LIBRARY_ARTISTS;
    }
}
