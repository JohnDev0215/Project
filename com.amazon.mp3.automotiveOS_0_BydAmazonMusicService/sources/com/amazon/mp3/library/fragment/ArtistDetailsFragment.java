package com.amazon.mp3.library.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.PlayerControlsView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.GroupedTrackListAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ArtistHeroImageLoader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playlist.EntityPlaylistUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.ArtistShareProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistDetailsFragment extends TrackListFragment {
    private ActionBarView mActionBarView;
    private CacheManager mArtCache;
    private String mArtistAsin;
    private String mArtistId;
    private String mArtistName;
    private Uri mContentUri;
    private long mHeaderJob;
    private HeaderMetadata mHeaderMetadata;
    private Drawable mHeroImage;
    private ImageView mHeroImageView;
    private boolean mShouldShowHeroImageAnimation;
    private boolean mShouldUseHeroMode;
    private static final String TAG = ArtistDetailsFragment.class.getSimpleName();
    private static final String[] AVAILABILITY_PROJECTION = {SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX};
    private long mGetHeroImageJob = -1;
    private long mAvailabilityCheckJob = -1;
    private Runnable mHeroImageAnimationRunnable = new Runnable() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment.4
        @Override // java.lang.Runnable
        public void run() {
            if (ArtistDetailsFragment.this.mHeroImage == null) {
                ArtistDetailsFragment.this.mShouldShowHeroImageAnimation = true;
            }
        }
    };

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    public boolean getHasOptionsMenu() {
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getLandscapeLayout() {
        return R.layout.library_artist_detail;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean shouldSaveLastViewedSource() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldUseFastScroll() {
        return false;
    }

    public static ArtistDetailsFragment newInstance(Context context, String artistName, String sourceId) {
        Uri contentUri = MediaProvider.Artists.getContentUri(sourceId, IdGenerator.generateArtistId(artistName));
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        ArtistDetailsFragment artistDetailsFragment = new ArtistDetailsFragment();
        artistDetailsFragment.setArguments(bundle);
        return artistDetailsFragment;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        notifyDataSetChanged();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        HeaderMetadata headerMetadata = this.mHeaderMetadata;
        if (headerMetadata != null) {
            headerMetadata.saveState(outState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void initHomeNav() {
        requestHomeButtonAsUp();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mArtCache = null;
        super.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getCollectionName() {
        return this.mArtistName;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        removeHeaderView();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_artist_detail, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(final Menu menu) {
        if (getActivity() == null) {
            Log.warning(TAG, "Can't show menu for \"" + this.mArtistName + "\" because fragment is detached");
            return;
        }
        super.onPrepareOptionsMenu(menu);
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() == R.id.MenuMoreBy) {
                if (ShopLinkUtil.isArtistBlacklisted(this.mArtistName) || !AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(requireContext())) {
                    menu.removeItem(R.id.MenuMoreBy);
                } else {
                    String str = this.mArtistName;
                    if (str != null) {
                        item.setTitle(getString(R.string.dmusic_context_shop_for, str));
                    }
                }
            }
        }
        if (TextUtils.isEmpty(this.mArtistAsin) || ShopLinkUtil.isArtistBlacklisted(this.mArtistName) || !AmazonApplication.getCapabilities().isSharingSupported()) {
            menu.removeItem(R.id.MenuArtistShare);
        }
        if (TextUtils.isEmpty(this.mArtistAsin) || !AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuArtistGoToEntityPlaylist);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        String str;
        String str2;
        super.onOptionsItemSelected(item);
        Context context = getContext();
        if (context == null) {
            return false;
        }
        int itemId = item.getItemId();
        if (itemId == R.id.MenuArtistGoToEntityPlaylist) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_ENTITY_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEntityId(this.mArtistAsin).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).build());
            String str3 = this.mArtistAsin;
            if (str3 != null) {
                this.mNavigationProvider.changeScreenFragment(EntityPlaylistUtil.buildEntityPlaylistFetchFragment(str3, PlaylistSeedEntityType.ARTIST), true, true, true);
            }
        } else if (itemId == R.id.MenuArtistShare) {
            String musicDomain = EndpointsProvider.get().getMusicDomain();
            if (this.mArtistName != null && this.mArtistAsin != null) {
                new ArtistShareProvider(context, musicDomain, this.mArtistAsin, this.mArtistName, null).startShare();
            }
        } else if (itemId == R.id.MenuMoreBy && (str = this.mArtistId) != null && (str2 = this.mArtistName) != null) {
            context.startActivity(ShopLinkUtil.getArtistExploreIntent(context, str, str2, ItemWrapper$ItemType.ARTIST));
            MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
        }
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        Uri contentUri = super.getContentUri();
        return !contentUri.getLastPathSegment().equals("tracks") ? contentUri.buildUpon().appendEncodedPath("tracks").build() : contentUri;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String[] getProjection() {
        return AmazonApplication.getLibraryItemFactory().getAlbumTrackSortProjection();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSortOrder() {
        return CirrusMediaSource.getAlbumTrackSortOrder();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment
    protected CacheManager getAlbumArtCache() {
        if (this.mArtCache == null) {
            this.mArtCache = CacheManager.getInstance();
        }
        return this.mArtCache;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        Context context = getContext();
        if (context != null) {
            View viewInflate = View.inflate(context, R.layout.library_artist_detail_header, null);
            viewInflate.setEnabled(false);
            viewInflate.setOnClickListener(null);
            ((ListView) getListView()).addHeaderView(viewInflate);
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
        Drawable drawable;
        if (jobId == this.mGetHeroImageJob) {
            this.mGetHeroImageJob = -1L;
            ImageView imageView = this.mHeroImageView;
            if (imageView != null && (drawable = this.mHeroImage) != null) {
                imageView.setImageDrawable(drawable);
                this.mHeroImageView.setVisibility(0);
                if (this.mShouldShowHeroImageAnimation) {
                    setHeroImageAnimation();
                }
            }
        } else if (jobId == this.mAvailabilityCheckJob) {
            this.mAvailabilityCheckJob = -1L;
            if (this.mPlayerControlsView != null) {
                updatePlayerControlsViewState();
            }
        } else if (jobId == this.mHeaderJob) {
            initHeaderMetadata(getView(), this.mHeaderMetadata);
            this.mHeaderJob = -1L;
            if (this.mArtistName != null) {
                setHasOptionsMenu(getHasOptionsMenu());
            }
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        GroupedTrackListAdapter groupedTrackListAdapter = new GroupedTrackListAdapter(context, getContentUri(), this, false);
        groupedTrackListAdapter.initNightwingSettings();
        return groupedTrackListAdapter;
    }

    private void init(Bundle instanceState) {
        Context context;
        Bundle arguments = getArguments();
        if (arguments != null) {
            Uri uri = (Uri) arguments.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI");
            this.mContentUri = uri;
            this.mArtistId = String.valueOf(MediaProvider.Artists.getArtistId(uri));
            prepareUiPageViewMetric();
            if (instanceState != null && (context = getContext()) != null && instanceState.getBoolean("EXTRA_HAS_METADATA", false)) {
                HeaderMetadata headerMetadata = new HeaderMetadata();
                this.mHeaderMetadata = headerMetadata;
                headerMetadata.restoreFromBundle(instanceState, context);
                initHeaderMetadata(getView(), this.mHeaderMetadata);
            }
            if (this.mArtistAsin == null) {
                this.mHeaderJob = addJob(new HeaderMetadataJob());
            } else {
                setHasOptionsMenu(getHasOptionsMenu());
            }
            View view = getView();
            if (view != null) {
                this.mHeroImageView = (ImageView) view.findViewById(R.id.ArtistDetailImage);
            }
            this.mGetHeroImageJob = addJob(new GetHeroImageJob());
            this.mHandler.postDelayed(this.mHeroImageAnimationRunnable, 1000L);
            initDetailPlayerControlsFragment();
        }
    }

    private void initHeaderMetadata(View rootView, HeaderMetadata data) {
        if (data == null || rootView == null || !isAdded()) {
            return;
        }
        this.mArtistName = data.mArtistName;
        this.mArtistId = data.mArtistId;
        this.mArtistAsin = data.mArtistAsin;
        initActionBarText();
    }

    private void prepareUiPageViewMetric() {
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
        String str3 = this.mArtistAsin;
        if (str3 != null) {
            str2 = "ASIN";
        } else {
            str3 = this.mArtistId;
            str2 = Contexts.LibraryArtistDetail.artistId;
        }
        sendUiPageViewMetric(getPageType(), str3, str2, str, string);
    }

    private void initActionBarText() {
        int i;
        int i2;
        HeaderMetadata headerMetadata = this.mHeaderMetadata;
        if (headerMetadata != null) {
            i = headerMetadata.mAlbumCount;
            i2 = headerMetadata.mSongCount;
            this.mArtistName = headerMetadata.mArtistName;
        } else {
            i = 0;
            i2 = 0;
        }
        Resources resources = getResources();
        String str = resources.getQuantityString(R.plurals.dmusic_library_albums_count, i, Integer.valueOf(i)) + ", " + resources.getQuantityString(R.plurals.dmusic_library_songs_count, i2, Integer.valueOf(i2));
        if (getContext() != null) {
            ActionBarView actionBarView = new ActionBarView(getActivity(), this.mArtistName, str);
            this.mActionBarView = actionBarView;
            setHeaderView(actionBarView);
            requestHomeButtonAsUp();
        }
    }

    private void initDetailPlayerControlsFragment() {
        RelativeLayout relativeLayout;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        boolean zIsSourceLocal = isSourceLocal();
        PlayerControlsView playerControlsView = new PlayerControlsView(activity, createNowPlayingIntent(activity), !zIsSourceLocal, getPlaybackPageType());
        this.mPlayerControlsView = playerControlsView;
        playerControlsView.setOffline(zIsSourceLocal);
        this.mPlayerControlsView.setTrackInformationFetcher(this.mFetcher);
        this.mPlayerControlsView.setSelectionProvider(this.mSelectionProvider);
        this.mPlayerControlsView.setDownloadListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                ArtistDetailsFragment artistDetailsFragment = ArtistDetailsFragment.this;
                if (artistDetailsFragment.mShouldBlockPlaybackAndDownload) {
                    UpsellUtil.showBlockingUpsell(artistDetailsFragment.getActivity(), ArtistDetailsFragment.this.mArtistAsin, UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.ARTIST);
                    return;
                }
                Uri contentUri = artistDetailsFragment.getContentUri();
                ArtistDetailsFragment.this.startDownload(contentUri.toString(), contentUri, false, new NotificationInfo(ArtistDetailsFragment.this.mArtistName, null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ARTIST.intValue(), MediaProvider.Artists.getArtistId(ArtistDetailsFragment.this.getContentUri())).toString()));
                ArtistDetailsFragment artistDetailsFragment2 = ArtistDetailsFragment.this;
                artistDetailsFragment2.mPlayerControlsView.setDownloadingState(artistDetailsFragment2.getGroupDownloadState());
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                UpsellReason upsellReason;
                ActionType actionType = null;
                if (R.id.PlayButton == v.getId()) {
                    actionType = ActionType.PLAY_ARTIST;
                    upsellReason = UpsellReason.PLAY;
                } else if (R.id.ShuffleButton == v.getId()) {
                    actionType = ActionType.PLAY_SHUFFLE_ALL;
                    upsellReason = UpsellReason.SHUFFLE_ALL;
                } else {
                    upsellReason = null;
                }
                ArtistDetailsFragment artistDetailsFragment = ArtistDetailsFragment.this;
                artistDetailsFragment.sendUiClickMetric(actionType, artistDetailsFragment.mArtistAsin, ArtistDetailsFragment.this.mArtistId, -1);
                ArtistDetailsFragment artistDetailsFragment2 = ArtistDetailsFragment.this;
                if (artistDetailsFragment2.mShouldBlockPlaybackAndDownload) {
                    UpsellUtil.showBlockingUpsell(artistDetailsFragment2.getActivity(), ArtistDetailsFragment.this.mArtistAsin, upsellReason, UpsellSourceEntity.ARTIST);
                }
            }
        };
        this.mPlayerControlsView.setPlayListener(onClickListener);
        this.mPlayerControlsView.setShuffleListener(onClickListener);
        this.mPlayerControlsView.setDownloadProgressListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                ArtistDetailsFragment.this.handleDownloadBtnClick(false, new NotificationInfo(ArtistDetailsFragment.this.mArtistName, null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ARTIST.intValue(), MediaProvider.Artists.getArtistId(ArtistDetailsFragment.this.getContentUri())).toString()));
            }
        });
        updatePlayerControlsViewState();
        View view = getView();
        if (view != null) {
            if (ScreenUtil.isPortrait()) {
                relativeLayout = (RelativeLayout) view.findViewById(R.id.DetailPlayerControlsFragment);
            } else {
                relativeLayout = (RelativeLayout) view.findViewById(R.id.ArtworkPlayerControlsFragment);
            }
            relativeLayout.addView(this.mPlayerControlsView);
        }
        setupSFAButton(this.mArtistAsin);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        refreshGroupDownloadState();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onGroupDownloadStateUpdated() {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setDownloadingState(getGroupDownloadState());
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onGroupProgressUpdated(int progress) {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setDownloadingProgress(progress);
        }
    }

    private Intent createNowPlayingIntent(final Activity activity) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_SORT_ORDER", getSortOrder());
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", getContentUri());
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_POSITION", 0);
        return intentForContentUri;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        addAvailabilityCheckJob();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorChanged(Cursor newCursor, Cursor oldCursor) {
        super.onCursorChanged(newCursor, oldCursor);
        if (newCursor != null) {
            addAvailabilityCheckJob();
        }
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

    private void addAvailabilityCheckJob() {
        if (-1 == this.mAvailabilityCheckJob) {
            this.mAvailabilityCheckJob = addJob(new AvailabilityCheckJob());
        }
    }

    private class AvailabilityCheckJob extends Job {
        private AvailabilityCheckJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Cursor cursorQuery;
            ContentResolver contentResolver = getContext().getContentResolver();
            if (contentResolver == null || ArtistDetailsFragment.this.mContentUri == null || (cursorQuery = contentResolver.query(ArtistDetailsFragment.this.mContentUri, ArtistDetailsFragment.AVAILABILITY_PROJECTION, null, null, null)) == null) {
                return 3;
            }
            try {
                if (cursorQuery.moveToFirst()) {
                    Artist artist = new Artist();
                    artist.setContentUri(ArtistDetailsFragment.this.mContentUri);
                    artist.setMinContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN)));
                    artist.setMaxNonPreviousContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("content_catalog_status_max_non_previous")));
                    artist.setMaxContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX)));
                    artist.setMinContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN)));
                    artist.setMaxContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX)));
                    ArtistDetailsFragment.this.mIsAvailable = artist.isAvailable();
                }
                cursorQuery.close();
                return 1;
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
    }

    private class GetHeroImageJob extends Job {
        private GetHeroImageJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            ArtistHeroImageLoader artistHeroImageLoader = (ArtistHeroImageLoader) ImageLoaderFactory.getInstance().getImageLoader(ImageLoaderFactory.ItemType.ARTIST_HERO);
            if (ArtistDetailsFragment.this.mArtistId == null) {
                return 1;
            }
            ArtistDetailsFragment artistDetailsFragment = ArtistDetailsFragment.this;
            artistDetailsFragment.mShouldUseHeroMode = artistHeroImageLoader.has16x9HeroImage(artistDetailsFragment.mArtistId);
            if (!ArtistDetailsFragment.this.mShouldUseHeroMode) {
                return 1;
            }
            ArtistDetailsFragment.this.mHeroImage = getHeroImage();
            return 1;
        }

        private Drawable getHeroImage() {
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.artist_detail_hero_width);
            Bundle arguments = ArtistDetailsFragment.this.getArguments();
            if (arguments != null && ArtistDetailsFragment.this.mContentUri != null) {
                ArtistDetailsFragment artistDetailsFragment = ArtistDetailsFragment.this;
                artistDetailsFragment.mHeroImage = artistDetailsFragment.getAlbumArtCache().getOnCurrentThread(ImageLoaderFactory.ItemType.ARTIST_HERO, arguments.getString("com.amazon.mp3.library.EXTRA_SOURCE_ID"), dimensionPixelSize, Long.toString(MediaProvider.Artists.getArtistId(ArtistDetailsFragment.this.mContentUri)), true);
            }
            return ArtistDetailsFragment.this.mHeroImage;
        }
    }

    private void setHeroImageAnimation() {
        if (this.mHeroImageView != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(500L);
            this.mHeroImageView.startAnimation(alphaAnimation);
            this.mHeroImageView.setBackgroundResource(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupSFAButton(final String artistAsin) {
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setupSFAButton$0();
                }
            });
        }
        if (TextUtils.isEmpty(artistAsin)) {
            ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setupSFAButton$1();
                }
            });
        } else {
            this.mPlayerControlsView.setSFAListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setupSFAButton$2(artistAsin, view);
                }
            });
            ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.ArtistDetailsFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setupSFAButton$3();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$0() {
        this.mPlayerControlsView.disableSFA();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$1() {
        this.mPlayerControlsView.hideSFAButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$2(String str, View view) {
        StationUtils.startSFA(getActivity(), str, "ARTIST_SEED", PlaybackPageType.ARTIST_DETAIL, ControlSource.APP_UI);
        StationUtils.sendStartSFAUiClickMetrics(str, EntityIdType.SFA_ARTIST_SEED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$3() {
        this.mPlayerControlsView.showSFAButton();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.ARTIST_DETAIL;
    }

    private class HeaderMetadataJob extends Job {
        final HeaderMetadata mData;

        private HeaderMetadataJob() {
            this.mData = new HeaderMetadata();
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Artist artist = AmazonApplication.getLibraryItemFactory().getArtist(ArtistDetailsFragment.this.mContentUri);
            this.mData.mArtistAsin = artist == null ? null : artist.getAsin();
            this.mData.mArtistName = artist == null ? null : artist.getName();
            this.mData.mArtistId = ArtistDetailsFragment.this.mArtistId;
            this.mData.mAlbumCount = artist == null ? 0 : (int) artist.getAlbumCount();
            this.mData.mSongCount = artist != null ? (int) artist.getTrackCount() : 0;
            broadcastProgress(null);
            ArtistDetailsFragment.this.setupSFAButton(this.mData.mArtistAsin);
            return 1;
        }
    }

    private class HeaderMetadata {
        int mAlbumCount;
        Drawable mArtistArt;
        String mArtistAsin;
        String mArtistId;
        String mArtistName;
        int mSongCount;

        private HeaderMetadata() {
        }

        public void saveState(Bundle bundle) {
            bundle.putString("EXTRA_ARTIST_NAME", this.mArtistName);
            bundle.putString("EXTRA_ARTIST_ID", this.mArtistId);
            bundle.putString("EXTRA_ARTIST_ASIN", this.mArtistAsin);
            bundle.putInt("EXTRA_ALBUM_COUNT", this.mAlbumCount);
            bundle.putInt("EXTRA_SONG_COUNT", this.mSongCount);
            bundle.putBoolean("EXTRA_HAS_METADATA", true);
        }

        public void restoreFromBundle(Bundle bundle, Context context) {
            this.mArtistName = bundle.getString("EXTRA_ARTIST_NAME");
            this.mArtistId = bundle.getString("EXTRA_ARTIST_ID");
            this.mArtistAsin = bundle.getString("EXTRA_ARTIST_ASIN");
            this.mAlbumCount = bundle.getInt("EXTRA_ALBUM_COUNT");
            this.mSongCount = bundle.getInt("EXTRA_SONG_COUNT", 0);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.detail_view_art_size);
            CacheManager cacheManager = CacheManager.getInstance();
            String str = this.mArtistId;
            if (str != null) {
                ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.ARTIST_HERO;
                if (cacheManager.contains(itemType, dimensionPixelSize, str)) {
                    this.mArtistArt = cacheManager.getOnCurrentThread(itemType, dimensionPixelSize, this.mArtistId, true);
                }
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return PageType.DETAIL_ARTIST;
    }
}
