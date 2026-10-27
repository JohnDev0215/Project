package com.amazon.mp3.library.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.PlayerControlsView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.AlbumTrackListAdapter;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.dialog.PostPurchaseDialog;
import com.amazon.mp3.library.fragment.TrackListFragment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.job.AddPrimeTrackJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playlist.EntityPlaylistUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.PrimeTracksCache;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.prime.browse.SimilarRecommendationsFragment;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.cta.AlbumMetadata;
import com.amazon.mp3.prime.cta.PrimeAlbumJob;
import com.amazon.mp3.prime.cta.SetPrimeUIStateJob;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.store.util.ImageCache;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.DefaultStringType;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.ImageProcessor;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeUtil;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.ShowHideRowView;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.AlbumShareProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumDetailFragment extends TrackListFragment {
    private ActionBarView mActionBarView;
    private String mAlbumAsin;
    private List<ContentEncoding> mAlbumContentEncoding;
    private AlbumMetadata mAlbumMetadata;
    private String mAlbumName;
    private String mAlbumUrl;
    private String mArtistAsin;
    private String mArtistId;
    private String mArtistName;
    private List<ContentCatalogStatus> mContentStatuses;
    private String mCurrentlyLoadedUniqueAsin;
    private long mHeaderJob;
    private HeaderMetadata mHeaderMetadata;
    private ImageProcessor mImageProcessor;
    private boolean mIsExplicit;
    private boolean mLoadExternalContents;
    private boolean mNeedToUpdateContentUri;
    private boolean mPostPurchaseToastDisplayed;
    private ShowHideRowView mShowHideRowView;
    private boolean mShowHideRowViewAdded;
    private BasePrimeFragment mSimilarRecommendationsFragment;
    private int mTrackCount;
    private ImageCache mUrlImageCache;
    private int maxCatalogStatus;
    private int maxOwnershipStatus;
    private int minCatalogStatus;
    private int minOwnershipStatus;
    private static final String TAG = AlbumDetailFragment.class.getSimpleName();
    private static final String SIMILAR_RECS_TAG = SimilarRecommendationsFragment.class.getSimpleName();
    private static final String[] HEADER_PROJECTION = {"title", "artist", "artist_id", "artist_asin", "album_art_id", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "track_count", "content_encoding"};
    private static final String SELECTION = DeluxeContentUtil.getNonDeluxeSelection();
    private static final String[] SELECTION_ARGS = DeluxeContentUtil.getNonDeluxeSelectionArgs();
    private long mAlbumArtJob = -1;
    private long mPrimeAlbumJobId = -1;
    private long mAddAllPrimeTracksJobId = -1;
    private long mAddAllPrimeTracksThenDownloadJobId = -1;
    private boolean fromBrowse = false;
    private boolean mShouldShowRowViewAdded = true;
    private final Set<Long> mAddPrimeTrackJobIds = new HashSet();
    private boolean mRelaunchPrimeAlbumJob = false;
    private boolean isCopyrightFooterAdded = false;
    private Album mAlbum = null;
    private AlbumRemoteMetadataListener mAlbumRemoteMetadataListener = new AlbumRemoteMetadataListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.1
        @Override // com.amazon.mp3.library.fragment.AlbumRemoteMetadataListener
        public void onAlbumMetadataAvailable(final AlbumMetadata albumMetadata) {
            AlbumDetailFragment.this.mAlbumMetadata = albumMetadata;
            if (AlbumDetailFragment.this.getAdapter() instanceof TrackListAdapter) {
                ((TrackListAdapter) AlbumDetailFragment.this.getAdapter()).setTracksMetadata(AlbumDetailFragment.this.mAlbumMetadata.getTracksMetadata());
            }
            Log.debug(AlbumDetailFragment.TAG, "Setting the albumMetadata : " + albumMetadata);
        }
    };
    private final View.OnClickListener mOnShowHidePrimeClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.8
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            AlbumDetailFragment.this.mLoadExternalContents = !r5.mLoadExternalContents;
            AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
            albumDetailFragment.addJob(new SetPrimeUIStateJob(albumDetailFragment.getActivity(), AlbumDetailFragment.this.mAlbumAsin, AlbumDetailFragment.this.mLoadExternalContents ? PrimeTracksCache.PrimeUIState.SHOW : PrimeTracksCache.PrimeUIState.HIDE));
            AlbumDetailFragment albumDetailFragment2 = AlbumDetailFragment.this;
            if (albumDetailFragment2.mPlayerControlsView != null) {
                long albumId = MediaProvider.Albums.getAlbumId(albumDetailFragment2.getContentUri());
                AlbumDetailFragment albumDetailFragment3 = AlbumDetailFragment.this;
                albumDetailFragment3.mPlayerControlsView.setContentUri(MediaProvider.Albums.Tracks.getContentUri(albumDetailFragment3.getSourceId(), albumId));
            }
            AlbumDetailFragment.this.requeryCursorInBackground();
        }
    };
    private final View.OnClickListener mOnAddAllPrimeSongsClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.9
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            UserInteractionAppEvent.builder(v.getResources().getResourceName(v.getId())).publish();
            AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
            albumDetailFragment.mAddAllPrimeTracksJobId = albumDetailFragment.addAllPrimeTracksToLibrary();
            ((TrackListAdapter) AlbumDetailFragment.this.getAdapter()).onAddAllPrimeTracks(null);
            MetricsLogger.clickedAddPrimeContent(LinkType.ALBUM, AlbumDetailFragment.this.mAlbumAsin, SelectionSourceType.ALBUM, AlbumDetailFragment.this.mAlbumAsin);
        }
    };

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean albumArtCacheEnabled() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    public boolean getHasOptionsMenu() {
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getLandscapeLayout() {
        return R.layout.library_album_detail;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldEmitDefaultUiPageViewMetric() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean shouldSaveLastViewedSource() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldUseFastScroll() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        checkShowPostPurchaseToast();
        checkShowPostPurchaseDialog();
        UxMetricsLogger.registerApplicationAction(UxMetricsLogger.ApplicationAction.LIBRARY_ALBUM_DETAIL_COMPLETE);
        notifyDataSetChanged();
        setLastViewedScreen();
    }

    private void setLastViewedScreen() {
        if (!TextUtils.isEmpty(getArguments().getString("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN"))) {
            LastViewedScreenUtil.setLastViewed(getContext(), LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
        } else {
            LastViewedScreenUtil.setLastViewed(getContext(), LastViewedScreenUtil.LastViewedScreen.LIBRARY);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        init(viewOnCreateView, savedInstanceState);
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.mShowHideRowView == null || !this.mShowHideRowViewAdded) {
            return;
        }
        ((ListView) getListView()).addFooterView(this.mShowHideRowView);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void initHomeNav() {
        requestHomeButtonAsUp();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
        this.isCopyrightFooterAdded = false;
        if (this.mSimilarRecommendationsFragment == null || getChildFragmentManager().findFragmentByTag(SIMILAR_RECS_TAG) == null) {
            return;
        }
        getChildFragmentManager().beginTransaction().remove(this.mSimilarRecommendationsFragment).commitAllowingStateLoss();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        Uri contentUri = super.getContentUri();
        if (contentUri == null) {
            return null;
        }
        return contentUri.buildUpon().appendEncodedPath("tracks").build();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", this.mAlbumArtJob);
        outState.putBoolean("com.amazon.mp3.EXTRA_POST_PURCHASE_TOAST_DISPLAYED", this.mPostPurchaseToastDisplayed);
        HeaderMetadata headerMetadata = this.mHeaderMetadata;
        if (headerMetadata != null) {
            headerMetadata.saveState(outState);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_album_detail, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(final Menu menu) {
        if (getActivity() == null) {
            Log.warning(TAG, "Can't show menu for \"" + this.mAlbumName + "\" because fragment is detached");
            return;
        }
        super.onPrepareOptionsMenu(menu);
        if (TextUtils.isEmpty(this.mAlbumAsin) || !AmazonApplication.getCapabilities().isSharingSupported()) {
            menu.removeItem(R.id.MenuAlbumShare);
        }
        if (TextUtils.isEmpty(this.mArtistAsin) || AmazonApplication.getCapabilities().shouldHideArtistLink()) {
            menu.removeItem(R.id.MenuGoToArtist);
        }
        if (TextUtils.isEmpty(this.mArtistAsin) || !AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuAlbumGoToEntityPlaylist);
        }
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            switch (item.getItemId()) {
                case R.id.MenuMoreBy /* 2131427597 */:
                    if (ShopLinkUtil.isArtistBlacklisted(this.mArtistName) || !AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(getActivity())) {
                        menu.removeItem(R.id.MenuMoreBy);
                    } else {
                        item.setTitle(getString(R.string.dmusic_context_shop_for, this.mArtistName));
                    }
                    break;
                case R.id.MenuMoreIn /* 2131427598 */:
                    if (ShopLinkUtil.isAlbumBlacklisted(this.mAlbumName) || !AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(getActivity())) {
                        menu.removeItem(R.id.MenuMoreIn);
                    } else {
                        item.setTitle(getString(R.string.dmusic_context_shop_for, this.mAlbumName));
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.MenuAlbumGoToEntityPlaylist /* 2131427548 */:
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_ENTITY_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEntityId(this.mAlbumAsin).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).build());
                String str = this.mAlbumAsin;
                if (str != null) {
                    this.mNavigationProvider.changeScreenFragment(EntityPlaylistUtil.buildEntityPlaylistFetchFragment(str, PlaylistSeedEntityType.ALBUM), true, true, true);
                }
                return true;
            case R.id.MenuAlbumShare /* 2131427553 */:
                String musicDomain = EndpointsProvider.get().getMusicDomain();
                String artworkUrlForAsin = this.mAlbumUrl;
                if (artworkUrlForAsin == null) {
                    artworkUrlForAsin = new CTAPrimeCache(getActivity()).getArtworkUrlForAsin(this.mAlbumAsin);
                }
                new AlbumShareProvider(getActivity(), musicDomain, this.mAlbumAsin, this.mAlbumName, this.mArtistName, artworkUrlForAsin).startShare();
                return true;
            case R.id.MenuGoToArtist /* 2131427586 */:
                FragmentActivity activity = getActivity();
                activity.startActivity(LibraryActivityFactory.getArtistDetailsIntent(activity, this.mArtistName, this.mArtistAsin, MediaProvider.Artists.getContentUri(getSourceId(), Long.parseLong(this.mArtistId)), getSourceId()));
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.VIEW_ARTIST_OVERFLOW).withInteractionType(InteractionType.TAP).withPageType(PageType.DETAIL_ALBUM).withEventTime(System.currentTimeMillis()).build());
                return true;
            case R.id.MenuMoreBy /* 2131427597 */:
                FragmentActivity activity2 = getActivity();
                activity2.startActivity(ShopLinkUtil.getArtistExploreIntent(activity2, this.mArtistId, this.mArtistName, ItemWrapper$ItemType.ARTIST));
                MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
                return true;
            case R.id.MenuMoreIn /* 2131427598 */:
                HTMLStoreActivityFactory.start(getActivity(), HTMLStoreActivityFactory.bundleForAlbum(this.mAlbumAsin, ""));
                MetricsLogger.clickedOnStoreLink(StoreLinkType.ALBUM);
                return true;
            default:
                return true;
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        AlbumTrackListAdapter albumTrackListAdapter = new AlbumTrackListAdapter(getActivity(), getContentUri(), this, false, this.mShowCompressedView);
        albumTrackListAdapter.setOnPrimeAddButtonClickListener(new TrackListAdapter.OnPrimeAddButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.2
            @Override // com.amazon.mp3.library.adapter.TrackListAdapter.OnPrimeAddButtonClickListener
            public void onClick(MusicTrack track) {
                AlbumDetailFragment.this.onPrimeAddButtonClick(track);
            }
        });
        if (AmazonApplication.getCapabilities().isDownloadTrackButtonEnabled()) {
            albumTrackListAdapter.showDownloadButton(true);
        }
        albumTrackListAdapter.initNightwingSettings();
        return albumTrackListAdapter;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        this.mHeaderJob = addJob(new HeaderMetadataJob());
        initAlbum();
        if (containsNonAddedTracks(c)) {
            if (UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) {
                removeAddAllPrimeSongsRowView();
            } else {
                addAddAllPrimeSongsRowView();
            }
            createShowHideRowView();
            return;
        }
        removeAddAllPrimeSongsRowView();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected boolean computeGroupDownloadStateWithNonLibTracks() {
        return this.mLoadExternalContents;
    }

    private boolean containsNonAddedTracks(Cursor cursor) {
        if (cursor == null) {
            return false;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int i = cursor.getInt(cursor.getColumnIndex("ownership_status"));
            int i2 = cursor.getInt(cursor.getColumnIndex("prime_status"));
            if (i == ContentOwnershipStatus.NOT_IN_LIBRARY.getValue() && (i2 == ContentCatalogStatus.PRIME.getValue() || i2 == ContentCatalogStatus.HAWKFIRE.getValue())) {
                return true;
            }
            cursor.moveToNext();
        }
        return false;
    }

    private void initAlbum() {
        String str;
        String uniqueValue = PrimeUtil.getUniqueValue(getCursor(), "album_asin");
        String str2 = TAG;
        if (TextUtils.isEmpty(uniqueValue)) {
            str = "No unique asin";
        } else {
            str = "Unique asin: " + uniqueValue;
        }
        Log.debug(str2, str);
        if (!TextUtils.isEmpty(uniqueValue)) {
            this.mAlbumAsin = uniqueValue;
            if (this.mIsRemote && !uniqueValue.equals(this.mCurrentlyLoadedUniqueAsin)) {
                this.mCurrentlyLoadedUniqueAsin = uniqueValue;
                initializePrimeTracks(uniqueValue);
            } else if (this.mAlbumMetadata == null && !this.mIsRemote && ConnectivityUtil.hasAnyInternetConnection(getContext())) {
                this.mPrimeAlbumJobId = addJob(new PrimeAlbumJob(getActivity(), uniqueValue, false, this.mAlbumRemoteMetadataListener));
            }
        }
        setupSFAButton();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void showEmptyUI() {
        super.showEmptyUI();
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setEnabled(false);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void hideEmptyUI() {
        super.hideEmptyUI();
        if (this.mPlayerControlsView != null) {
            updatePlayerControlsViewState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPrimeAddButtonClick(MusicTrack track) {
        String asin = track.getAsin();
        this.mAddPrimeTrackJobIds.add(Long.valueOf(addJob(new AddPrimeTrackJob(getActivity(), asin))));
        MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, asin, SelectionSourceType.ALBUM, this.mAlbumAsin);
    }

    private void initializePrimeTracks(String albumAsin) {
        if (!UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace()) {
            Log.verbose(TAG, "Preferred marketplace is not prime supported");
        } else if (this.mPrimeAlbumJobId == -1) {
            this.mPrimeAlbumJobId = addJob(new PrimeAlbumJob(getActivity(), albumAsin, true, this.mAlbumRemoteMetadataListener));
        } else {
            this.mRelaunchPrimeAlbumJob = true;
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobProgress(long jobId, Job job, Bundle progress) {
        if (jobId == this.mHeaderJob) {
            this.mHeaderMetadata = ((HeaderMetadataJob) job).mData;
        }
        super.onJobProgress(jobId, job, progress);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (getActivity() != null) {
            if (jobId == this.mHeaderJob) {
                initHeaderMetadata(getView(), this.mHeaderMetadata);
                this.mHeaderJob = -1L;
                if (this.mAlbumName != null || this.mArtistName != null) {
                    setHasOptionsMenu(getHasOptionsMenu());
                }
            } else if (jobId == this.mPrimeAlbumJobId) {
                onPrimeAlbumJobFinished(job, resultCode);
            } else if (jobId == this.mAddAllPrimeTracksJobId) {
                onAddAllPrimeTrackJobFinished(job, resultCode, true);
            } else if (jobId == this.mAddAllPrimeTracksThenDownloadJobId) {
                onAddAllTracksThenDownloadJobFinished(job, resultCode);
            } else if (this.mAddPrimeTrackJobIds.contains(Long.valueOf(jobId))) {
                onAddPrimeTrackJobFinished(jobId, resultCode);
            }
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    private void onAddPrimeTrackJobFinished(final long jobId, final int resultCode) {
        this.mAddPrimeTrackJobIds.remove(Long.valueOf(jobId));
        if (resultCode == 1) {
            getActivity();
            BauhausToastUtils.showTextToast(getActivity(), getResources().getString(R.string.dmusic_prime_song_added_to_library), 0);
            int trackCount = this.mShowHideRowView.getTrackCount() - 1;
            this.mShowHideRowView.setTrackCount(trackCount);
            if (trackCount <= 0) {
                removeShowHideRowView();
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onItemClick(AbsListView listView, View view, int position, long id) {
        if (UserSubscriptionUtil.isNightwingOnly()) {
            AlbumMetadata albumMetadata = this.mAlbumMetadata;
            if (albumMetadata != null && albumMetadata.getTracksMetadata() != null) {
                this.mAlbumMetadata.getTracksMetadata().size();
            }
            BadgeableListAdapter.BaseRowViewHolder baseRowViewHolder = (BadgeableListAdapter.BaseRowViewHolder) view.getTag();
            if (baseRowViewHolder == null) {
                return;
            }
            AbstractItem abstractItem = baseRowViewHolder.mLibraryItem;
            setClickedItem(abstractItem);
            if (isPurchasedTrack(abstractItem)) {
                super.onItemClick(listView, view, position, id);
                return;
            }
            if ((abstractItem instanceof MusicTrack) && abstractItem.isExplicit() && AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
                handleDisabledContentError(ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED, abstractItem);
                return;
            } else {
                UpsellManager.getInstance().raiseNonBlockingUpsell(getContext(), UpsellUtil.getUpsellInformation(baseRowViewHolder.mAsin, UpsellReason.PLAY, UpsellSourceEntity.ALBUM_PAGE), MediaCollectionType.AMF_STATION_SEED);
                return;
            }
        }
        super.onItemClick(listView, view, position, id);
    }

    private boolean isPurchasedTrack(AbstractItem contentItem) {
        boolean zIsSourceLocal = isSourceLocal();
        if (contentItem == null) {
            return false;
        }
        return (!zIsSourceLocal && contentItem.isAvailable()) || (zIsSourceLocal && contentItem.isAvailableOffline());
    }

    private void removeShowHideRowView() {
        if (this.mShowHideRowViewAdded) {
            this.mShowHideRowViewAdded = false;
            ((ListView) getListView()).removeFooterView(this.mShowHideRowView);
        }
    }

    private void addShowHideRowView() {
        if (this.mShowHideRowViewAdded || !this.mShouldShowRowViewAdded || isAlwaysShowNonLibContent()) {
            return;
        }
        this.mShowHideRowViewAdded = true;
        ((ListView) getListView()).addFooterView(this.mShowHideRowView);
    }

    private void removeAddAllPrimeSongsRowView() {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.hideAddAllButton();
        }
    }

    private void addAddAllPrimeSongsRowView() {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.showAddAllButton();
            this.mPlayerControlsView.setAddAllButtonOnClickListener(this.mOnAddAllPrimeSongsClickListener);
        }
    }

    private boolean onAddAllPrimeTrackJobFinished(Job job, int resultCode, boolean showToast) {
        this.mAddAllPrimeTracksJobId = -1L;
        if (resultCode != 1) {
            return false;
        }
        getActivity();
        if (showToast) {
            int tracksAddedCount = ((AddPrimeTrackJob) job).getTracksAddedCount();
            BauhausToastUtils.showTextToast(getActivity(), getResources().getQuantityString(R.plurals.dmusic_all_prime_songs_added_to_library, tracksAddedCount, Integer.valueOf(tracksAddedCount)), 0);
        }
        requeryCursorInBackground();
        this.mShowHideRowView.setTrackCount(0);
        removeShowHideRowView();
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onDeletePrimeTrackFinished() {
        ShowHideRowView showHideRowView = this.mShowHideRowView;
        if (showHideRowView != null) {
            int trackCount = showHideRowView.getTrackCount() + 1;
            this.mShowHideRowView.setTrackCount(trackCount);
            if (trackCount == 1) {
                addShowHideRowView();
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void startGroupDownload(String reqeustId, Uri uri, boolean downloadNonAdded, NotificationInfo notificationInfo) {
        if (downloadNonAdded && showingPrimeNonAddedTracks()) {
            this.mAddAllPrimeTracksThenDownloadJobId = addAllPrimeTracksToLibrary();
        } else {
            startDownload(this.mGroupTracksUri.toString(), this.mGroupTracksUri, showingPrimeNonAddedTracks(), notificationInfo);
        }
    }

    private void onAddAllTracksThenDownloadJobFinished(Job job, int resultCode) {
        if (onAddAllPrimeTrackJobFinished(job, resultCode, false)) {
            startDownload(this.mGroupTracksUri.toString(), this.mGroupTracksUri, showingPrimeNonAddedTracks(), new NotificationInfo(this.mAlbumName, this.mArtistName, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), getAlbumId()).toString()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long addAllPrimeTracksToLibrary() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
            return -1L;
        }
        Cursor cursor = getCursor();
        int columnIndex = cursor.getColumnIndex("asin");
        int columnIndex2 = cursor.getColumnIndex("ownership_status");
        cursor.moveToFirst();
        HashSet hashSet = new HashSet();
        do {
            String string = cursor.getString(columnIndex);
            int i = cursor.getInt(columnIndex2);
            if (!TextUtils.isEmpty(string) && !ContentOwnershipStatus.fromValue(i).isInLibrary()) {
                hashSet.add(string);
            }
        } while (cursor.moveToNext());
        return addJob(new AddPrimeTrackJob(getActivity(), hashSet));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        super.onContentUriChanged();
        init(getView(), null);
        onSessionConnected(-1L);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSelection() {
        if (this.mLoadExternalContents) {
            return null;
        }
        return super.getSelection();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String[] getSelectionArgs() {
        if (this.mLoadExternalContents) {
            return null;
        }
        return super.getSelectionArgs();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSortOrder() {
        return CirrusMediaSource.getAlbumTrackSortOrder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getAlbumId() {
        try {
            return Long.parseLong(super.getContentUri().getPathSegments().get(3));
        } catch (IndexOutOfBoundsException e) {
            Log.error(TAG, "Could not get albumId. Index out of bounds." + e);
            return 0L;
        } catch (NullPointerException e2) {
            Log.error(TAG, "Could not get albumId. Null pointer exception." + e2);
            return 0L;
        } catch (NumberFormatException e3) {
            Log.error(TAG, "Could not get albumId. Number format exception." + e3);
            return 0L;
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        View viewInflate = View.inflate(getActivity(), R.layout.library_album_detail_header, null);
        viewInflate.setEnabled(false);
        viewInflate.setOnClickListener(null);
        ((ListView) getListView()).addHeaderView(viewInflate);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected int getAlbumArtSize() {
        return getApplication().getResources().getDimensionPixelSize(R.dimen.detail_view_art_size);
    }

    private void init(View rootView, Bundle instanceState) {
        Bundle arguments = getArguments();
        String string = arguments.getString("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN");
        getFieldsForUiPageViewMetric(string);
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            ((NowPlayingFragmentActivity) getActivity()).showActionBar();
            this.mActionBarView = new ActionBarView(getActivity(), "");
        } else {
            this.mActionBarView = new ActionBarView(getActivity(), this.mAlbumName);
        }
        setHeaderView(this.mActionBarView);
        requestHomeButtonAsUp();
        this.mImageProcessor = new RoundedCornerImageProcessor(getContext());
        if (!TextUtils.isEmpty(string)) {
            this.fromBrowse = true;
            this.mLoadExternalContents = true;
            this.mShouldShowRowViewAdded = false;
            this.mUrlImageCache = new ImageCache(getActivity(), getAlbumArtSize(), getAlbumArtSize(), Bitmap.Config.RGB_565);
            this.mAlbumAsin = string;
            initializePrimeTracks(string);
        } else {
            this.mLoadExternalContents = false;
            this.mShouldShowRowViewAdded = true;
            if (instanceState != null) {
                this.mAlbumArtJob = instanceState.getLong("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", -1L);
                this.mPostPurchaseToastDisplayed = instanceState.getBoolean("com.amazon.mp3.EXTRA_POST_PURCHASE_TOAST_DISPLAYED", false);
                if (instanceState.getBoolean("EXTRA_HAS_METADATA", false)) {
                    HeaderMetadata headerMetadata = new HeaderMetadata();
                    this.mHeaderMetadata = headerMetadata;
                    headerMetadata.restoreFromBundle(instanceState, getActivity());
                    initHeaderMetadata(rootView, this.mHeaderMetadata);
                }
            } else {
                this.mAlbumArtJob = -1L;
            }
            this.mHeaderJob = addJob(new HeaderMetadataJob());
        }
        if (instanceState != null) {
            this.mTrackCount = instanceState.getInt("EXTRA_TRACK_COUNT");
        }
        if ((getAdapter() instanceof AlbumTrackListAdapter) && this.fromBrowse) {
            AlbumTrackListAdapter albumTrackListAdapter = (AlbumTrackListAdapter) getAdapter();
            albumTrackListAdapter.showPopularityBar(true);
            albumTrackListAdapter.setOpenedFromBrowseHome(true);
            if (UserSubscriptionUtil.isNightwingOnly()) {
                albumTrackListAdapter.initNightwingSettings(this.mShowCompressedView, this.mTrackCount);
            }
        }
        if (this.fromBrowse && UserSubscriptionUtil.isNightwingOnly()) {
            initNightwingDetailPlayerControlsFragment(rootView);
        } else if (arguments.getBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", false)) {
            initDetailPlayerControlsFragment(rootView);
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getEmptyLayoutID() {
        return this.mLoadExternalContents ? R.layout.common_progress_spinner : super.getEmptyLayoutID();
    }

    private void getFieldsForUiPageViewMetric(final String albumAsin) {
        String str;
        String string;
        String str2;
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string2 = arguments.getString(DeeplinkIntentKey.KEY_REF_MARKER.toString());
            string = arguments.getString(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
            str = string2;
        } else {
            str = null;
            string = null;
        }
        if (albumAsin != null) {
            str2 = "ASIN";
        } else {
            albumAsin = Long.toString(getAlbumId());
            str2 = "CD_OBJECT_ID";
        }
        sendUiPageViewMetric(getPageType(), albumAsin, str2, str, string);
    }

    private void initHeaderMetadata(View rootView, HeaderMetadata data) {
        String str;
        if (data == null || rootView == null || !isAdded()) {
            return;
        }
        if (data.mAlbumName != null && ChildUserUtil.INSTANCE.isChildUser(getContext())) {
            this.mAlbumName = StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(data.mAlbumName);
        } else {
            this.mAlbumName = data.mAlbumName;
        }
        this.mArtistName = data.mArtistName;
        this.mArtistId = data.mArtistId;
        this.mArtistAsin = data.mArtistAsin;
        this.mIsAvailable = data.mIsAvailable;
        this.mTrackCount = data.mTrackCount;
        this.mAlbumContentEncoding = data.mContentEncoding;
        TextView textView = (TextView) rootView.findViewById(R.id.AlbumName);
        if (textView != null) {
            textView.setText(this.mAlbumName);
        }
        if (getActivity() instanceof MusicHomeActivity) {
            this.mActionBarView.setTitles(this.mAlbumName, null);
        }
        TextView textView2 = (TextView) rootView.findViewById(R.id.ArtistName);
        if (textView2 != null) {
            textView2.setText(this.mArtistName);
        }
        updateAlbumArtwork(data.mAlbumArt);
        updateKatanaBadge();
        if (this.mPlayerControlsView != null) {
            updatePlayerControlsViewState();
        }
        if (!this.mNeedToUpdateContentUri || (str = this.mAlbumName) == null || this.mArtistName == null) {
            return;
        }
        this.mNeedToUpdateContentUri = false;
        Uri contentUri = MediaProvider.Albums.Tracks.getContentUri(getSourceId(), IdGenerator.generateAlbumId(this.mAlbumAsin, str));
        this.mGroupTracksUri = contentUri;
        if (this.mPlayerControlsView != null) {
            Log.verbose(TAG, "updating content uri of the player controls to: " + contentUri);
            this.mPlayerControlsView.setContentUri(contentUri);
        }
    }

    private void updateKatanaBadge() {
        BadgeView badgeView;
        View view = getView();
        if (view == null) {
            return;
        }
        BadgeView badgeView2 = (BadgeView) view.findViewById(R.id.KatanaAlbumDetailBadge);
        BadgeView badgeView3 = (BadgeView) view.findViewById(R.id.ImmersiveAlbumDetailBadge);
        List<ContentEncoding> albumContentEncoding = getAlbumContentEncoding();
        if (badgeView2 != null) {
            badgeView2.populate(ContentEncodingDecoder.getEncodingBadgeModel(albumContentEncoding, getContext()));
        }
        if (badgeView3 != null) {
            badgeView3.populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(albumContentEncoding, getContext(), 8));
        }
        if (albumContentEncoding == null || !albumContentEncoding.contains(ContentEncoding.SONY_360) || !albumContentEncoding.contains(ContentEncoding.DOLBY_ATMOS) || (badgeView = (BadgeView) view.findViewById(R.id.ImmersiveAlbumDetailExtraBadge)) == null) {
            return;
        }
        badgeView.populate(ContentEncodingDecoder.getExtraAlbumAtmosContentBadgeModel(albumContentEncoding, getContext(), 8, true));
    }

    private List<ContentEncoding> getAlbumContentEncoding() {
        AlbumMetadata albumMetadata;
        if (isOpenedFromBrowseHome() && (albumMetadata = this.mAlbumMetadata) != null) {
            return albumMetadata.getContentEncoding();
        }
        if (isBadgingInLibrarySupported()) {
            return this.mAlbumContentEncoding;
        }
        return null;
    }

    private boolean isOpenedFromBrowseHome() {
        return this.fromBrowse && !isSourceLocal();
    }

    private void initDownloadButtonClickListener() {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setDownloadListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initDownloadButtonClickListener$0(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initDownloadButtonClickListener$0(View view) {
        if (this.mShouldBlockPlaybackAndDownload) {
            UpsellUtil.showBlockingUpsell(getActivity(), this.mAlbumAsin, UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.ALBUM);
            return;
        }
        Uri uriGenerateUri = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), MediaProvider.Albums.getAlbumId(getContentUri()));
        Resources resources = getResources();
        int i = this.mTrackCount;
        handleDownloadBtnClick(showingPrimeNonAddedTracks(), new NotificationInfo(this.mAlbumName, resources.getQuantityString(R.plurals.dmusic_download_str_to_download, i, Integer.valueOf(i)), uriGenerateUri.toString()));
        this.mPlayerControlsView.setDownloadingState(getGroupDownloadState());
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.DOWNLOAD_ALL).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEntityId(this.mAlbumAsin).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).build());
    }

    private void initNightwingDetailPlayerControlsFragment(View rootView) {
        RelativeLayout relativeLayout;
        PlayerControlsView playerControlsView = new PlayerControlsView(getActivity(), createNowPlayingIntent(), PlayerControlsView.BigButtonConfig.BIG_SFA_BUTTON, PlaybackPageType.ALBUM_DETAIL, null);
        this.mPlayerControlsView = playerControlsView;
        playerControlsView.setBigButtonListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                StationUtils.startSFA(AlbumDetailFragment.this.getContext(), AlbumDetailFragment.this.mAlbumAsin, "ALBUM_SEED", PlaybackPageType.ALBUM_DETAIL, ControlSource.APP_UI);
                StationUtils.sendStartSFAUiClickMetrics(AlbumDetailFragment.this.mAlbumAsin, EntityIdType.SFA_ALBUM_SEED);
            }
        });
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.playlist_detail_page_nightwing_controls);
        this.mPlayerControlsView.setPadding(0, 0, 0, dimensionPixelOffset);
        if (ScreenUtil.isPortrait()) {
            relativeLayout = (RelativeLayout) rootView.findViewById(R.id.DetailPlayerControlsFragment);
        } else {
            this.mPlayerControlsView.setPadding(0, dimensionPixelOffset, 0, dimensionPixelOffset);
            relativeLayout = (RelativeLayout) rootView.findViewById(R.id.ArtworkPlayerControlsFragment);
        }
        relativeLayout.addView(this.mPlayerControlsView);
        if (MediaProvider.Albums.getAlbumId(getContentUri()) == 0) {
            Log.verbose(TAG, "Album Id is 0.  Need to update player controls with valid album id when metadata is loaded.");
            this.mNeedToUpdateContentUri = true;
        }
    }

    private void initDetailPlayerControlsFragment(View rootView) {
        RelativeLayout relativeLayout;
        boolean z = !isSourceLocal();
        PlayerControlsView playerControlsView = new PlayerControlsView(getActivity(), createNowPlayingIntent(), z, getPlaybackPageType());
        this.mPlayerControlsView = playerControlsView;
        playerControlsView.setOffline(!z);
        this.mPlayerControlsView.setTrackInformationFetcher(this.mFetcher);
        this.mPlayerControlsView.setSelectionProvider(this.mSelectionProvider);
        initDownloadButtonClickListener();
        this.mPlayerControlsView.setDownloadProgressListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                NotificationInfo notificationInfo = new NotificationInfo(AlbumDetailFragment.this.mAlbumName, AlbumDetailFragment.this.mArtistName, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), MediaProvider.Albums.getAlbumId(AlbumDetailFragment.this.getContentUri())).toString());
                AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
                albumDetailFragment.handleDownloadBtnClick(albumDetailFragment.showingPrimeNonAddedTracks(), notificationInfo);
            }
        });
        updatePlayerControlsViewState();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                UpsellReason upsellReason;
                ActionType actionType = null;
                if (R.id.PlayButton == v.getId()) {
                    actionType = ActionType.PLAY_ALBUM;
                    upsellReason = UpsellReason.PLAY;
                } else if (R.id.ShuffleButton == v.getId()) {
                    actionType = ActionType.PLAY_SHUFFLE_ALL;
                    upsellReason = UpsellReason.SHUFFLE_ALL;
                } else {
                    upsellReason = null;
                }
                AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
                albumDetailFragment.sendUiClickMetric(actionType, albumDetailFragment.mAlbumAsin, Long.toString(AlbumDetailFragment.this.getAlbumId()), -1);
                AlbumDetailFragment albumDetailFragment2 = AlbumDetailFragment.this;
                if (albumDetailFragment2.mShouldBlockPlaybackAndDownload) {
                    UpsellUtil.showBlockingUpsell(albumDetailFragment2.getActivity(), AlbumDetailFragment.this.mAlbumAsin, upsellReason, UpsellSourceEntity.ALBUM);
                }
            }
        };
        this.mPlayerControlsView.setPlayListener(onClickListener);
        this.mPlayerControlsView.setShuffleListener(onClickListener);
        if (ScreenUtil.isPortrait()) {
            relativeLayout = (RelativeLayout) rootView.findViewById(R.id.DetailPlayerControlsFragment);
        } else {
            relativeLayout = (RelativeLayout) rootView.findViewById(R.id.ArtworkPlayerControlsFragment);
        }
        relativeLayout.addView(this.mPlayerControlsView);
        if (MediaProvider.Albums.getAlbumId(getContentUri()) == 0) {
            Log.verbose(TAG, "Album Id is 0.  Need to update player controls with valid album id when metadata is loaded.");
            this.mNeedToUpdateContentUri = true;
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        refreshGroupDownloadState();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onGroupDownloadStateUpdated() {
        if (this.mPlayerControlsView != null) {
            int groupDownloadState = getGroupDownloadState();
            if (groupDownloadState == 0 && UserSubscriptionUtil.getUserSubscription().isKatana()) {
                isRedownloadRequired();
            }
            this.mPlayerControlsView.setDownloadingState(groupDownloadState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onGroupProgressUpdated(int progress) {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setDownloadingProgress(progress);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getDeleteContentMessage() {
        AbstractItem clickedItem = getClickedItem();
        return clickedItem.isAvailableOffline() ? R.string.dmusic_album_toast_deleting_track_device : (clickedItem.isNotOwned() && clickedItem.isInLibrary()) ? R.string.dmusic_album_toast_removing_track_cloud : R.string.dmusic_album_toast_deleting_track_cloud;
    }

    private Intent createNowPlayingIntent() {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getActivity(), MediaProvider.NowPlaying.CONTENT_URI);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_SORT_ORDER", getSortOrder());
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", getContentUri());
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_POSITION", 0);
        return intentForContentUri;
    }

    private void showPostPurchaseDialog(final Activity activity, final boolean isAlbumPurchase) {
        new PostPurchaseDialog(activity, isAlbumPurchase).create().show();
    }

    private static boolean suppressPostPurchaseDialog(final Context context) {
        return SettingsUtil.getPrefs(context).getBoolean(context.getString(R.string.setting_key_suppress_post_purchase_dialog), false);
    }

    private void checkShowPostPurchaseDialog() {
        if (!startedFromPurchase() || suppressPostPurchaseDialog(getContext()) || isAutomaticDownloadsOn()) {
            return;
        }
        boolean z = false;
        if (getArguments().containsKey("com.amazon.mp3.EXTRA_IS_ALBUM_PURCHASE") && getArguments().getBoolean("com.amazon.mp3.EXTRA_IS_ALBUM_PURCHASE", false)) {
            z = true;
        }
        showPostPurchaseDialog(getActivity(), z);
    }

    private boolean isAutomaticDownloadsOn() {
        return SettingsUtil.getPrefs(getActivity()).getBoolean(getActivity().getString(R.string.setting_key_cirrus_download_pref), false);
    }

    private void checkShowPostPurchaseToast() {
        if (this.mPostPurchaseToastDisplayed) {
            return;
        }
        this.mPostPurchaseToastDisplayed = true;
        if (!startedFromPurchase() || SettingsUtil.getCirrusDownloadPreference(getApplication())) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            BauhausToastUtils.showTextToast(getActivity(), R.string.dmusic_library_post_purchase_toast, 0);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void showRedownloadIcon() {
        this.mPlayerControlsView.updateDownloadButtonToShow(2);
        initDownloadButtonClickListener();
        setGroupDownloadState(5);
        this.mPlayerControlsView.setDownloadingState(getGroupDownloadState());
    }

    private boolean startedFromPurchase() {
        return getArguments().getBoolean("com.amazon.mp3.EXTRA_STARTED_FROM_PURCHASE", false);
    }

    private class HeaderMetadata {
        Drawable mAlbumArt;
        String mAlbumName;
        String mArtistAsin;
        String mArtistId;
        String mArtistName;
        List<ContentEncoding> mContentEncoding;
        boolean mIsAvailable;
        boolean mIsPrime;
        int mTrackCount;

        private HeaderMetadata() {
        }

        public void saveState(Bundle bundle) {
            bundle.putString("EXTRA_ALBUM_NAME", this.mAlbumName);
            bundle.putString("EXTRA_ARTIST_NAME", this.mArtistName);
            bundle.putString("EXTRA_ARTIST_ID", this.mArtistId);
            bundle.putString("EXTRA_ARTIST_ASIN", this.mArtistAsin);
            bundle.putString("EXTRA_CONTENT_ENCODING", ContentEncodingDecoder.contentEncodingToString(this.mContentEncoding));
            bundle.putInt("EXTRA_TRACK_COUNT", this.mTrackCount);
            bundle.putBoolean("EXTRA_HAS_METADATA", true);
            bundle.putBoolean("EXTRA_PRIME_STATUS", this.mIsPrime);
            bundle.putBoolean("EXTRA_IS_AVAILABLE", this.mIsAvailable);
        }

        public void restoreFromBundle(Bundle bundle, Context context) {
            this.mAlbumName = bundle.getString("EXTRA_ALBUM_NAME");
            this.mArtistName = bundle.getString("EXTRA_ARTIST_NAME");
            this.mArtistId = bundle.getString("EXTRA_ARTIST_ID");
            this.mArtistAsin = bundle.getString("EXTRA_ARTIST_ASIN");
            this.mContentEncoding = ContentEncodingDecoder.stringtoContentEncoding(bundle.getString("EXTRA_CONTENT_ENCODING"));
            this.mTrackCount = bundle.getInt("EXTRA_TRACK_COUNT", 0);
            this.mIsPrime = bundle.getBoolean("EXTRA_PRIME_STATUS", false);
            this.mIsAvailable = bundle.getBoolean("EXTRA_IS_AVAILABLE", true);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.detail_view_art_size);
            CacheManager cacheManager = CacheManager.getInstance();
            String string = Long.toString(AlbumDetailFragment.this.getAlbumId());
            ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.ALBUM;
            if (cacheManager.contains(itemType, dimensionPixelSize, string)) {
                this.mAlbumArt = cacheManager.getOnCurrentThread(itemType, dimensionPixelSize, string, true);
            }
        }
    }

    private void onPrimeAlbumJobFinished(Job job, int resultCode) {
        PrimeAlbumJob primeAlbumJob = (PrimeAlbumJob) job;
        this.mPrimeAlbumJobId = -1L;
        if (this.mRelaunchPrimeAlbumJob) {
            initializePrimeTracks(this.mAlbumAsin);
            this.mRelaunchPrimeAlbumJob = false;
        }
        this.mAlbumUrl = primeAlbumJob.getAlbumArtUrl();
        if (resultCode == 3) {
            if (primeAlbumJob.getFailureCause() != null) {
                Log.error(TAG, "Prime Album Job failed: " + primeAlbumJob.getFailureCause().getMessage());
            }
            Cursor cursor = getCursor();
            if (cursor != null && cursor.getCount() == 0) {
                hideEmptyUI();
                showPrimeAlbumNotFoundErrorDialog();
                return;
            }
        }
        createShowHideRowView();
        boolean zHasPrimeContent = primeAlbumJob.hasPrimeContent();
        if (!isSourceLocal() && (zHasPrimeContent || MediaProvider.Albums.getAlbumId(getContentUri()) < 1)) {
            resetContent(primeAlbumJob.getAlbumUri(), "cirrus");
            if (this.mUrlImageCache != null) {
                loadArtworkForAlbum(primeAlbumJob.getAlbumArtUrl());
            }
            if (primeAlbumJob.getPrimeUIState() == PrimeTracksCache.PrimeUIState.SHOW) {
                this.mShowHideRowView.setMode(ShowHideRowView.Mode.HIDE);
                this.mLoadExternalContents = true;
                if (UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) {
                    removeAddAllPrimeSongsRowView();
                } else {
                    addAddAllPrimeSongsRowView();
                }
            } else {
                this.mShowHideRowView.setMode(ShowHideRowView.Mode.SHOW);
            }
            this.mShowHideRowView.setTrackCount(primeAlbumJob.getPrimeTrackCacheCount());
            if (this.mPlayerControlsView != null) {
                this.mPlayerControlsView.setContentUri(MediaProvider.Albums.Tracks.getContentUri(getSourceId(), MediaProvider.Albums.getAlbumId(getContentUri())));
            }
            addShowHideRowView();
            requeryCursorInBackground();
        } else {
            removeShowHideRowView();
        }
        AlbumMetadata albumMetadata = this.mAlbumMetadata;
        int size = (albumMetadata == null || albumMetadata.getTracksMetadata() == null) ? 0 : this.mAlbumMetadata.getTracksMetadata().size();
        if (getAdapter() instanceof AlbumTrackListAdapter) {
            AlbumTrackListAdapter albumTrackListAdapter = (AlbumTrackListAdapter) getAdapter();
            boolean zIsOpenedFromBrowseHome = isOpenedFromBrowseHome();
            albumTrackListAdapter.setOpenedFromBrowseHome(zIsOpenedFromBrowseHome);
            if (UserSubscriptionUtil.isNightwingOnly() && zIsOpenedFromBrowseHome) {
                albumTrackListAdapter.initNightwingSettings(this.mShowCompressedView, size);
            }
        }
        updateCopyrightInfo();
        updateKatanaBadge();
        updateSimilarityWidget();
        new TrackListFragment.HandleDeeplinkTask(new QueryAlbumByAsin(getContext())).execute(new Void[0]);
    }

    private void updateSimilarityWidget() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        String str = SIMILAR_RECS_TAG;
        if (childFragmentManager.findFragmentByTag(str) == null) {
            this.mSimilarRecommendationsFragment = new SimilarRecommendationsFragment.Builder(new Bundle()).setDataType(PaginatedDataType.ALBUM).setAsin(this.mAlbumAsin).setPageType(getPageType()).build();
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setId(View.generateViewId());
            ((ListView) getListView()).addFooterView(linearLayout);
            getChildFragmentManager().beginTransaction().add(linearLayout.getId(), this.mSimilarRecommendationsFragment, str).commit();
        }
    }

    private void showPrimeAlbumNotFoundErrorDialog() {
        String string;
        String string2;
        String string3;
        final FragmentActivity activity = getActivity();
        if (ConnectivityUtil.hasAnyInternetConnection(getContext())) {
            if (ChildUserUtil.INSTANCE.isChildUser(getContext())) {
                string = getString(R.string.dmusic_dialog_prime_kids_content_not_found_title);
                string2 = getString(R.string.dmusic_dialog_prime_kids_content_not_found_message);
                string3 = getString(R.string.dmusic_dialog_prime_kids_content_not_found_button);
            } else {
                string = getString(R.string.dmusic_dialog_prime_album_not_found_title);
                string2 = getString(R.string.dmusic_dialog_prime_album_not_found_message);
                string3 = getString(R.string.dmusic_dialog_prime_not_found_button);
            }
            new OneButtonErrorDialog(getActivity(), string, string2, new ErrorDialogButton(string3, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    AlbumDetailFragment.this.mNavigationProvider.changeScreenFragment(PrimeTabFragmentHost.newInstance(new Bundle()), false, true, false);
                }
            })).show();
            return;
        }
        DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.7
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                if (activity.getSupportFragmentManager().popBackStackImmediate()) {
                    return;
                }
                activity.finish();
            }
        };
        NoConnectionDialog noConnectionDialogNewInstance = NoConnectionDialog.newInstance(false);
        noConnectionDialogNewInstance.setListener(onDismissListener);
        noConnectionDialogNewInstance.show(getActivity().getSupportFragmentManager(), "NoConnectionDialog");
    }

    private void createShowHideRowView() {
        if (this.mShowHideRowView == null) {
            ShowHideRowView showHideRowView = new ShowHideRowView(getActivity());
            this.mShowHideRowView = showHideRowView;
            showHideRowView.setOnClickListener(this.mOnShowHidePrimeClickListener);
        }
    }

    private void loadArtworkForAlbum(final String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment.10
            private Drawable mAlbumArt;

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                this.mAlbumArt = AlbumDetailFragment.this.mUrlImageCache.cacheOnCurrentThread(url);
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void result) {
                AlbumDetailFragment.this.updateAlbumArtwork(this.mAlbumArt);
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAlbumArtwork(Drawable albumArt) {
        ImageView imageView;
        FragmentActivity activity = getActivity();
        if (activity == null || (imageView = (ImageView) activity.findViewById(R.id.AlbumDetailArtwork)) == null || albumArt == null) {
            return;
        }
        this.mImageProcessor.process((BitmapDrawable) albumArt, imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBadgingInLibrarySupported() {
        return !isOpenedFromBrowseHome() && AmazonApplication.getCapabilities().isAlbumBadgingInLibrarySupported();
    }

    private void updateCopyrightInfo() {
        AlbumMetadata albumMetadata = this.mAlbumMetadata;
        if (albumMetadata == null || TextUtils.isEmpty(albumMetadata.getCopyright()) || this.isCopyrightFooterAdded) {
            return;
        }
        this.isCopyrightFooterAdded = true;
        View viewInflate = View.inflate(getActivity(), R.layout.single_text_view_layout, null);
        viewInflate.setEnabled(false);
        viewInflate.setOnClickListener(null);
        ((TextView) viewInflate.findViewById(R.id.albumCopyright)).setText(this.mAlbumMetadata.getCopyright());
        ((ListView) getListView()).addFooterView(viewInflate);
    }

    private void setupSFAButton() {
        if (this.mPlayerControlsView != null) {
            if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
                this.mPlayerControlsView.disableSFA();
            } else if (TextUtils.isEmpty(this.mAlbumAsin)) {
                this.mPlayerControlsView.hideSFAButton();
            } else {
                this.mPlayerControlsView.setSFAListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumDetailFragment$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setupSFAButton$1(view);
                    }
                });
                this.mPlayerControlsView.showSFAButton();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$1(View view) {
        StationUtils.startSFA(getActivity(), this.mAlbumAsin, "ALBUM_SEED", PlaybackPageType.ALBUM_DETAIL, ControlSource.APP_UI);
        StationUtils.sendStartSFAUiClickMetrics(this.mAlbumAsin, EntityIdType.SFA_ALBUM_SEED);
    }

    private class HeaderMetadataJob extends Job {
        final HeaderMetadata mData;

        private HeaderMetadataJob() {
            this.mData = new HeaderMetadata();
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            String strApplyBinaryOperator = AlbumDetailFragment.SELECTION;
            String[] strArrMergeColumnArrays = AlbumDetailFragment.SELECTION_ARGS;
            boolean z = false;
            if (AlbumDetailFragment.this.isBadgingInLibrarySupported()) {
                strApplyBinaryOperator = DbUtil.applyBinaryOperator("ownership_status<?", "AND", strApplyBinaryOperator);
                strArrMergeColumnArrays = DbUtil.mergeColumnArrays(new String[]{String.valueOf(300)}, strArrMergeColumnArrays);
            }
            Cursor cursorQuery = getContext().getContentResolver().query(AlbumDetailFragment.super.getContentUri(), AlbumDetailFragment.HEADER_PROJECTION, strApplyBinaryOperator, strArrMergeColumnArrays, null);
            if (cursorQuery == null) {
                return 3;
            }
            try {
                if (cursorQuery.moveToFirst()) {
                    this.mData.mAlbumName = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("title"));
                    DefaultStringType defaultStringType = DefaultStringType.ALBUM;
                    if (defaultStringType.isPlatformOrCirrusDefault(this.mData.mAlbumName)) {
                        this.mData.mAlbumName = defaultStringType.getDefault();
                    }
                    this.mData.mArtistName = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist"));
                    DefaultStringType defaultStringType2 = DefaultStringType.ARTIST;
                    if (defaultStringType2.isPlatformOrCirrusDefault(this.mData.mArtistName)) {
                        this.mData.mArtistName = defaultStringType2.getDefault();
                    }
                    this.mData.mArtistId = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist_id"));
                    this.mData.mArtistAsin = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist_asin"));
                    AlbumDetailFragment.this.maxCatalogStatus = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX));
                    AlbumDetailFragment.this.minCatalogStatus = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN));
                    AlbumDetailFragment.this.maxOwnershipStatus = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX));
                    AlbumDetailFragment.this.minOwnershipStatus = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN));
                    Album album = new Album(AmazonApplication.getLibraryItemFactory(), AlbumDetailFragment.super.getContentUri());
                    int i = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX));
                    int i2 = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("content_catalog_status_max_non_previous"));
                    album.setMaxContentCatalogStatusValue(i);
                    album.setMaxNonPreviousContentCatalogStatusValue(i2);
                    album.setMinContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN)));
                    album.setMinContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN)));
                    album.setMaxContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX)));
                    AlbumDetailFragment.this.mContentStatuses = Arrays.asList(ContentCatalogStatus.fromValue(i2), ContentCatalogStatus.fromValue(i));
                    AlbumDetailFragment.this.mIsExplicit = album.isExplicit();
                    if (AmazonApplication.getCapabilities().isAlbumBadgingInLibrarySupported()) {
                        album.setContentEncodings(ContentEncodingDecoder.getAggregatedContentEncoding(cursorQuery.getString(cursorQuery.getColumnIndex("content_encoding"))));
                    }
                    this.mData.mIsAvailable = album.isAvailable();
                    this.mData.mTrackCount = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("track_count"));
                    HeaderMetadata headerMetadata = this.mData;
                    if (isPurePrime(cursorQuery) && Branding.shouldShowPrimeBranding()) {
                        z = true;
                    }
                    headerMetadata.mIsPrime = z;
                    this.mData.mContentEncoding = album.getContentEncodings();
                }
                DbUtil.closeCursor(cursorQuery);
                broadcastProgress(null);
                this.mData.mAlbumArt = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, AlbumDetailFragment.this.getAlbumArtSize(), Long.toString(AlbumDetailFragment.this.getAlbumId()), true);
                broadcastProgress(null);
                return 1;
            } catch (Throwable th) {
                DbUtil.closeCursor(cursorQuery);
                throw th;
            }
        }

        private boolean isPurePrime(Cursor cursor) {
            if (cursor != null) {
                return ContentCatalogStatus.fromValue(cursor.getInt(cursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN))).isPrime() && (ContentOwnershipStatus.fromValue(cursor.getInt(cursor.getColumnIndex(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN))).isOwned() ^ true);
            }
            return false;
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.ALBUM_DETAIL;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return PageType.DETAIL_ALBUM;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getCollectionName() {
        return this.mAlbumName;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected boolean shouldDisplayContextMenuForPosition(int position) {
        Cursor cursor = getCursor();
        return cursor != null && position <= cursor.getCount() && super.shouldDisplayContextMenuForPosition(position);
    }
}
