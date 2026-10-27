package com.amazon.mp3.library.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.PlayerControlsView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.GroupedTrackListAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.ImageProcessor;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GenreDetailFragment extends TrackListFragment {
    private static final String[] AVAILABILITY_PROJECTION = {SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX};
    private ActionBarView mActionBarView;
    private String mAlbumAndSongCount;
    private Drawable mAlbumArtwork;
    private int mArtWorkSize;
    private Drawable mCompositeOrRegularArtwork;
    private Uri mGenreContentUri;
    private String mGenreName;
    private ImageProcessor mImageProcessor;
    private long mAvailabilityCheckJob = -1;
    private long mGenreArtJob = -1;

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getLandscapeLayout() {
        return R.layout.library_genre_detail;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean shouldSaveLastViewedSource() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldUseFastScroll() {
        return false;
    }

    public static GenreDetailFragment newInstance(String artistName, String albumAndSongCount, String sourceId) {
        Uri contentUri = MediaProvider.Genres.getContentUri(sourceId, IdGenerator.generateGenreId(artistName));
        Bundle bundle = new Bundle();
        bundle.putString("ARG_GENRE_NAME", artistName);
        bundle.putString("ARG_ALBUM_AND_SONG_COUNT", albumAndSongCount);
        bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
        bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        GenreDetailFragment genreDetailFragment = new GenreDetailFragment();
        genreDetailFragment.setArguments(bundle);
        return genreDetailFragment;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void initHomeNav() {
        requestHomeButtonAsUp();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        notifyDataSetChanged();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        return super.getContentUri().buildUpon().appendEncodedPath("tracks").build();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String[] getProjection() {
        return AmazonApplication.getLibraryItemFactory().getAlbumTrackSortProjection();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSortOrder() {
        return CirrusMediaSource.getAlbumTrackSortOrder();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        View viewInflate = View.inflate(getActivity(), R.layout.library_genre_detail_header, null);
        viewInflate.setEnabled(false);
        viewInflate.setOnClickListener(null);
        ((ListView) getListView()).addHeaderView(viewInflate);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorChanged(Cursor newCursor, Cursor oldCursor) {
        super.onCursorChanged(newCursor, oldCursor);
        if (newCursor != null) {
            addAvailabilityCheckJob();
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getCollectionName() {
        return this.mGenreName;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        addAvailabilityCheckJob();
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

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (jobId == this.mGenreArtJob) {
            this.mGenreArtJob = -1L;
            if (ScreenUtil.isPortrait()) {
                ((ImageView) getView().findViewById(R.id.GenreDetailPlaceHolder)).setVisibility(8);
            }
            if (this.mCompositeOrRegularArtwork != null) {
                this.mImageProcessor.process((BitmapDrawable) this.mCompositeOrRegularArtwork, (ImageView) getView().findViewById(R.id.CompositeOrRegularArtwork));
            }
            if (this.mAlbumArtwork != null) {
                this.mImageProcessor.process((BitmapDrawable) this.mAlbumArtwork, (ImageView) getView().findViewById(R.id.AlbumArtwork));
            }
        } else if (jobId == this.mAvailabilityCheckJob) {
            this.mAvailabilityCheckJob = -1L;
            if (this.mPlayerControlsView != null) {
                updatePlayerControlsViewState();
            }
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        return new GroupedTrackListAdapter(getActivity(), getContentUri(), this, false);
    }

    private void init() {
        this.mGenreContentUri = (Uri) getArguments().getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        this.mGenreArtJob = addJob(new GenreArtJob());
        this.mArtWorkSize = ScreenUtil.getDeviceWidth() / 2;
        this.mGenreName = getArguments().getString("ARG_GENRE_NAME");
        this.mAlbumAndSongCount = getArguments().getString("ARG_ALBUM_AND_SONG_COUNT");
        ActionBarView actionBarView = new ActionBarView(getActivity(), this.mGenreName, this.mAlbumAndSongCount);
        this.mActionBarView = actionBarView;
        setHeaderView(actionBarView);
        requestHomeButtonAsUp();
        this.mImageProcessor = new RoundedCornerImageProcessor(getContext());
        initDetailPlayerControlsFragment();
        NavigationAppEvent.builder(PageType.DETAIL_GENRE.getMetricsValue()).withOrientation(ScreenUtil.getScreenOrientation(getContext())).withItemIdentifier(this.mGenreName).withItemIdentifierType("GENRE_NAME").publish();
    }

    private void initDetailPlayerControlsFragment() {
        RelativeLayout relativeLayout;
        boolean zIsSourceLocal = isSourceLocal();
        PlayerControlsView playerControlsView = new PlayerControlsView(getActivity(), createNowPlayingIntent(), !zIsSourceLocal, getPlaybackPageType());
        this.mPlayerControlsView = playerControlsView;
        playerControlsView.setOffline(zIsSourceLocal);
        this.mPlayerControlsView.setTrackInformationFetcher(this.mFetcher);
        this.mPlayerControlsView.setSelectionProvider(this.mSelectionProvider);
        this.mPlayerControlsView.setDownloadListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.GenreDetailFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                GenreDetailFragment.this.setGroupDownloadState(-2);
                Uri contentUri = GenreDetailFragment.this.getContentUri();
                GenreDetailFragment.this.startDownload(contentUri.toString(), contentUri, false, new NotificationInfo(GenreDetailFragment.this.mGenreName, null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.GENRE.intValue(), MediaProvider.Genres.getGenreId(GenreDetailFragment.this.getContentUri())).toString()));
                GenreDetailFragment genreDetailFragment = GenreDetailFragment.this;
                genreDetailFragment.mPlayerControlsView.setDownloadingState(genreDetailFragment.getGroupDownloadState());
            }
        });
        this.mPlayerControlsView.setDownloadProgressListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.GenreDetailFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                GenreDetailFragment.this.handleDownloadBtnClick(false, new NotificationInfo(GenreDetailFragment.this.mGenreName, null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.GENRE.intValue(), MediaProvider.Genres.getGenreId(GenreDetailFragment.this.getContentUri())).toString()));
            }
        });
        this.mPlayerControlsView.setEnabled(this.mIsAvailable);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.GenreDetailFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                ActionType actionType;
                if (R.id.PlayButton == v.getId()) {
                    actionType = ActionType.PLAY_GENRE;
                } else {
                    actionType = R.id.ShuffleButton == v.getId() ? ActionType.PLAY_SHUFFLE_ALL : null;
                }
                GenreDetailFragment genreDetailFragment = GenreDetailFragment.this;
                genreDetailFragment.sendUiClickMetric(actionType, genreDetailFragment.mGenreName, EntityIdType.GENRE_NAME, (Integer) (-1));
            }
        };
        this.mPlayerControlsView.setPlayListener(onClickListener);
        this.mPlayerControlsView.setShuffleListener(onClickListener);
        if (ScreenUtil.isPortrait()) {
            relativeLayout = (RelativeLayout) getView().findViewById(R.id.DetailPlayerControlsFragment);
        } else {
            relativeLayout = (RelativeLayout) getView().findViewById(R.id.ArtworkPlayerControlsFragment);
        }
        relativeLayout.addView(this.mPlayerControlsView);
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

    private Intent createNowPlayingIntent() {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getActivity(), MediaProvider.NowPlaying.CONTENT_URI);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_SORT_ORDER", getSortOrder());
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", getContentUri());
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_POSITION", 0);
        return intentForContentUri;
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
            Cursor cursorQuery = getContext().getContentResolver().query(GenreDetailFragment.this.mGenreContentUri, GenreDetailFragment.AVAILABILITY_PROJECTION, null, null, null);
            if (cursorQuery == null) {
                return 3;
            }
            try {
                if (cursorQuery.moveToFirst()) {
                    Genre genre = new Genre();
                    genre.setContentUri(GenreDetailFragment.this.mGenreContentUri);
                    genre.setMinContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN)));
                    genre.setMaxNonPreviousContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("content_catalog_status_max_non_previous")));
                    genre.setMaxContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX)));
                    genre.setMinContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN)));
                    genre.setMaxContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX)));
                    GenreDetailFragment.this.mIsAvailable = genre.isAvailable();
                }
                cursorQuery.close();
                return 1;
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
    }

    private class GenreArtJob extends Job {
        String albumIdOfLastListElement;
        List<Long> albumIdsList;
        int numberOfAlbumsInGenre;

        private GenreArtJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            init();
            if (ScreenUtil.isPortrait()) {
                initPortraitArtwork();
                return 1;
            }
            initLandscapeArtwork();
            return 1;
        }

        private void initPortraitArtwork() {
            if (this.numberOfAlbumsInGenre >= 5) {
                GenreDetailFragment.this.mCompositeOrRegularArtwork = getCompositeArtwork();
                GenreDetailFragment.this.mAlbumArtwork = getAlbumArtwork(this.albumIdOfLastListElement);
                return;
            }
            GenreDetailFragment.this.mCompositeOrRegularArtwork = getAlbumArtwork(this.albumIdOfLastListElement);
            if (this.albumIdsList.size() <= 1) {
                ((BaseFragment) GenreDetailFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.GenreDetailFragment.GenreArtJob.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GenreArtJob.this.handleSingleAlbumArtCase();
                    }
                });
                return;
            }
            GenreDetailFragment.this.mAlbumArtwork = getAlbumArtwork(Long.toString(this.albumIdsList.get(this.numberOfAlbumsInGenre - 2).longValue()));
        }

        private void initLandscapeArtwork() {
            GenreDetailFragment.this.mAlbumArtwork = this.numberOfAlbumsInGenre >= 5 ? getCompositeArtwork() : getAlbumArtwork(this.albumIdOfLastListElement);
        }

        private void init() {
            List<Long> albumIds = PlaylistUtil.getAlbumIds(GenreDetailFragment.this.getContentUri());
            this.albumIdsList = albumIds;
            int size = albumIds.size();
            this.numberOfAlbumsInGenre = size;
            this.albumIdOfLastListElement = Long.toString(this.albumIdsList.get(size - 1).longValue());
        }

        private Drawable getCompositeArtwork() {
            return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.GENRE, GenreDetailFragment.this.getSourceId(), GenreDetailFragment.this.mArtWorkSize, Long.toString(MediaProvider.Genres.getGenreId(GenreDetailFragment.this.mGenreContentUri)), true);
        }

        private Drawable getAlbumArtwork(String artId) {
            return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, GenreDetailFragment.this.getSourceId(), GenreDetailFragment.this.mArtWorkSize, artId, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleSingleAlbumArtCase() {
            View view = GenreDetailFragment.this.getView();
            if (view != null) {
                view.findViewById(R.id.AlbumArtwork).setVisibility(8);
                ImageView imageView = (ImageView) view.findViewById(R.id.CompositeOrRegularArtwork);
                imageView.setMaxHeight(GenreDetailFragment.this.mArtWorkSize);
                imageView.setMaxWidth(GenreDetailFragment.this.mArtWorkSize);
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.GENRE_DETAIL;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return PageType.DETAIL_GENRE;
    }
}
