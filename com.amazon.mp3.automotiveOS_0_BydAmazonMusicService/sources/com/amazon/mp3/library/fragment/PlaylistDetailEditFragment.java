package com.amazon.mp3.library.fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.activity.EmptySmartPlaylistActivity;
import com.amazon.mp3.library.activity.SavePlaylistActivity;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PlaylistDetailEditFragment extends TrackListFragment {
    protected View mHeaderButton;
    private boolean mIsRemote;
    private boolean mIsSmart;
    protected TextView mPlaylistName;
    private String mPlaylistNameString;
    private boolean mUdo;
    private static final String[] HEADER_PROJECTION = {"name"};
    private static final String[] COUNT_PROJECTION = {"download_state"};
    private boolean mCanRenamePlaylist = true;
    private long mPlaylistArtJob = -1;
    private int mHeaderDownloadState = 5;
    private HeaderMetadata mHeaderMetadata = null;
    private String mSongsCountAndDuration = null;
    private long mUpdateSongsCountAndDurationJobId = -1;
    private long mGetPlaylistNameJobId = -1;
    private Handler mBackgroundHandler = AmazonApplication.getBackgroundHandler();
    private ContentObserver mCursorChangedObserver = new ContentObserver(this.mHandler) { // from class: com.amazon.mp3.library.fragment.PlaylistDetailEditFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            PlaylistDetailEditFragment.this.updateHeader();
        }
    };
    private DataSetObserver mCursorDatasetObserver = new DataSetObserver() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailEditFragment.2
        @Override // android.database.DataSetObserver
        public void onChanged() {
            PlaylistDetailEditFragment.this.updateHeader();
        }
    };

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getContentViewLayoutId() {
        return R.layout.library_playlist_detail_fragment;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSortOrder() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected boolean shouldBringEmptyUIToFront() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldPrefetchArt() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected boolean shouldRemoveAddToPlaylistButton() {
        return true;
    }

    protected boolean showDownloadButton() {
        return true;
    }

    protected void updateCountAndDuration(String trackcount) {
    }

    protected void updatePlaylistName(final boolean canRenamePlaylist) {
    }

    void setTheme(Bundle instanceState) {
        Bundle arguments = getArguments();
        int i = R.style.Theme_AmazonMP3_Library_Device;
        if (arguments != null && CirrusMediaSource.matchCloud((Uri) arguments.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI"))) {
            i = 2132083455;
        }
        getActivity().setTheme(i);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        setTheme(instanceState);
        super.onCreate(instanceState);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
        setShowEmptyList();
        if (this.mGetPlaylistNameJobId == -1) {
            this.mGetPlaylistNameJobId = addJob(new GetPlaylistTitleJob());
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateHeader();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.fragment.BaseFragment
    public void onDeactivated() {
        super.onDeactivated();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void removeItemRemoveFromPlaylist(Menu menu) {
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(getContentUri())) {
            menu.removeItem(R.id.MenuTrackContextDeleteFromPlaylist);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void removeFromPlaylist(final Uri trackUri) {
        this.mBackgroundHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailEditFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$removeFromPlaylist$0(trackUri);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeFromPlaylist$0(Uri uri) {
        if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(getContentUri()))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
            return;
        }
        Uri editPlaylistUri = PlaylistUtil.getEditPlaylistUri(PlaylistUtil.withAppendedScratchArgument(getSinglePlaylistUri().buildUpon().appendEncodedPath("tracks").build()));
        CirrusPlaylistScratch.create(getApplication(), PlaylistUtil.getPlaylistId(editPlaylistUri));
        Iterator<Uri> it = PlaylistUtil.getPlaylistTrackUrisFromTrack(getActivity(), editPlaylistUri, uri).iterator();
        if (it.hasNext()) {
            doRemoveTrackFromPlaylist(it.next());
        }
        startActivityForResult(SavePlaylistActivity.getStartIntent(getApplication(), editPlaylistUri), SavePlaylistActivity.REQUEST_CODE);
    }

    private void doRemoveTrackFromPlaylist(Uri trackUri) {
        final Uri uriBuild = trackUri.buildUpon().appendQueryParameter("udo", String.valueOf(this.mUdo)).build();
        if (CirrusMediaSource.match(uriBuild)) {
            uriBuild = PlaylistUtil.withAppendedScratchArgument(uriBuild);
        }
        this.mBackgroundHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailEditFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$doRemoveTrackFromPlaylist$1(uriBuild);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doRemoveTrackFromPlaylist$1(Uri uri) {
        getContentResolver().delete(uri, null, null);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        Uri contentUri = super.getContentUri();
        return !"tracks".equals(contentUri.getLastPathSegment()) ? contentUri.buildUpon().appendEncodedPath("tracks").build() : contentUri;
    }

    protected Uri getSinglePlaylistUri() {
        List<String> pathSegments = getContentUri().getPathSegments();
        String str = pathSegments.get(1);
        String str2 = pathSegments.get(3);
        long j = Long.parseLong(pathSegments.get(4));
        if ("smart".equals(str2)) {
            return MediaProvider.SmartPlaylists.getContentUri(str, j);
        }
        if ("udo".equals(str2)) {
            Uri contentUri = MediaProvider.UdoPlaylists.getContentUri(str, j);
            this.mUdo = true;
            return contentUri;
        }
        throw new IllegalArgumentException("Unknown playlist type!");
    }

    protected String getPlaylistName() {
        return this.mPlaylistNameString;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorChanged(Cursor newCursor, Cursor oldCursor) {
        super.onCursorChanged(newCursor, oldCursor);
        if (oldCursor != null) {
            oldCursor.unregisterContentObserver(this.mCursorChangedObserver);
            oldCursor.unregisterDataSetObserver(this.mCursorDatasetObserver);
        }
        if (newCursor != null) {
            newCursor.registerContentObserver(this.mCursorChangedObserver);
            newCursor.registerDataSetObserver(this.mCursorDatasetObserver);
        }
        if (checkShowEmptySmartPlaylistActivity()) {
            return;
        }
        updateHeader();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", this.mPlaylistArtJob);
        HeaderMetadata headerMetadata = this.mHeaderMetadata;
        if (headerMetadata != null) {
            headerMetadata.saveState(outState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onSyncServiceStopped() {
        super.onSyncServiceStopped();
        checkShowEmptySmartPlaylistActivity();
    }

    protected boolean checkShowEmptySmartPlaylistActivity() {
        return checkShowEmptySmartPlaylistActivity(true);
    }

    protected boolean checkShowEmptySmartPlaylistActivity(boolean suppressTransition) {
        Cursor cursor;
        if (!this.mIsRemote || !this.mIsSmart || SyncService.isRunning() || (cursor = getCursor()) == null || cursor.getCount() != 0) {
            return false;
        }
        getActivity().finish();
        startActivity(EmptySmartPlaylistActivity.getStartIntent(getApplication(), getSourceId(), getContentUri()));
        if (!suppressTransition) {
            return true;
        }
        getActivity().overridePendingTransition(0, 0);
        return true;
    }

    protected long getPlaylistId() {
        return PlaylistUtil.getPlaylistId(getContentUri());
    }

    protected void updateHeaderButtons(Cursor c) {
        boolean z = c == null || c.getCount() == 0;
        if (getActivity() == null) {
            return;
        }
        View view = this.mHeaderButton;
        if (view != null && this.mUdo && (view instanceof Button)) {
            ((Button) view).setText(z ? R.string.dmusic_library_udo_playlists_add : R.string.dmusic_library_udo_playlists_edit);
        }
        updateHeaderButtonBackgrounds();
    }

    private void updateHeaderButtonBackgrounds() {
        View view = this.mHeaderButton;
        if (view != null) {
            view.setBackgroundResource(R.drawable.btn_secondary);
        }
    }

    protected void hideHeaderButtonIfDownloading() {
        int i = this.mHeaderDownloadState;
        boolean z = i > 0 && i < 5;
        View view = this.mHeaderButton;
        if (view == null || !this.mUdo) {
            return;
        }
        view.setVisibility(z ? 8 : 0);
        updateHeaderButtonBackgrounds();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void updateEmptyView(View emptyView, boolean isLoading) {
        super.updateEmptyView(emptyView, isLoading);
        if (emptyView == null || !(emptyView instanceof TextView)) {
            return;
        }
        ((TextView) emptyView).setText(isLoading ? R.string.dmusic_playlist_loading_playlist : R.string.dmusic_playlist_empty);
    }

    protected void startCountAndDurationJob() {
        if (this.mUpdateSongsCountAndDurationJobId != -1 || getCursor() == null) {
            return;
        }
        this.mUpdateSongsCountAndDurationJobId = addJob(new UpdateHeaderInformationJob());
    }

    protected void updatePlaylistInfos() {
        startCountAndDurationJob();
    }

    private class HeaderMetadata {
        int mDownloadState;
        Drawable mPlaylistArt;
        String mSongsCountAndDuration;
        int mTrackCount;

        private HeaderMetadata() {
        }

        public void saveState(Bundle bundle) {
            bundle.putString("EXTRA_SONGS_INFO", this.mSongsCountAndDuration);
            bundle.putInt("EXTRA_DOWNLOAD_STATE", this.mDownloadState);
            bundle.putInt("EXTRA_TRACK_COUNT", this.mTrackCount);
            bundle.putBoolean("EXTRA_HAS_METADATA", true);
        }

        public void restoreFromBundle(Bundle bundle, Context context) {
            this.mSongsCountAndDuration = bundle.getString("EXTRA_SONGS_INFO");
            this.mDownloadState = bundle.getInt("EXTRA_DOWNLOAD_STATE", 5);
            this.mTrackCount = bundle.getInt("EXTRA_TRACK_COUNT", 0);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.detail_view_art_size);
            ImageLoaderFactory.ItemType itemType = PlaylistDetailEditFragment.this.mUdo ? ImageLoaderFactory.ItemType.PLAYLIST_UDO : ImageLoaderFactory.ItemType.PLAYLIST_SMART;
            CacheManager cacheManager = CacheManager.getInstance();
            String source = MediaProvider.getSource(PlaylistDetailEditFragment.this.getContentUri());
            String string = Long.toString(MediaProvider.Playlists.getPlaylistId(PlaylistDetailEditFragment.this.getContentUri()));
            if (cacheManager.contains(itemType, source, dimensionPixelSize, string)) {
                this.mPlaylistArt = cacheManager.getOnCurrentThread(itemType, source, dimensionPixelSize, string, true);
            }
        }
    }

    private class GetPlaylistTitleJob extends Job {
        private GetPlaylistTitleJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Uri singlePlaylistUri = PlaylistDetailEditFragment.this.getSinglePlaylistUri();
            Cursor cursorQuery = PlaylistDetailEditFragment.this.getActivity().getContentResolver().query(singlePlaylistUri, PlaylistDetailEditFragment.HEADER_PROJECTION, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                        PlaylistDetailEditFragment.this.mPlaylistNameString = cursorQuery.getString(cursorQuery.getColumnIndex("name"));
                    }
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }
            Application context = getContext();
            if (context == null) {
                return 0;
            }
            PlaylistDetailEditFragment.this.mCanRenamePlaylist = PlaylistUtil.canRenamePlaylist(context, singlePlaylistUri);
            return 0;
        }
    }

    private class UpdateHeaderInformationJob extends Job {
        HeaderMetadata mData;

        public UpdateHeaderInformationJob() {
            this.mData = new HeaderMetadata();
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Cursor cursor = PlaylistDetailEditFragment.this.getCursor();
            int count = cursor != null ? cursor.getCount() : 0;
            Cursor cursorQuery = PlaylistDetailEditFragment.this.getActivity().getContentResolver().query(PlaylistDetailEditFragment.this.getContentUri(), PlaylistDetailEditFragment.COUNT_PROJECTION, "download_state in ( 0 )", null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() == count) {
                        PlaylistDetailEditFragment.this.mHeaderDownloadState = 0;
                    }
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }
            ((BaseFragment) PlaylistDetailEditFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailEditFragment.UpdateHeaderInformationJob.1
                @Override // java.lang.Runnable
                public void run() {
                    if (PlaylistDetailEditFragment.this.showDownloadButton()) {
                        PlaylistDetailEditFragment.this.hideHeaderButtonIfDownloading();
                    }
                }
            });
            PlaylistUtil.PlaylistTrackInfo playlistTrackInfoCreatePlaylistInfo = PlaylistUtil.createPlaylistInfo(PlaylistDetailEditFragment.this.getActivity(), PlaylistDetailEditFragment.this.getSinglePlaylistUri(), CirrusMediaSource.match(PlaylistDetailEditFragment.this.getContentUri()));
            PlaylistDetailEditFragment playlistDetailEditFragment = PlaylistDetailEditFragment.this;
            playlistDetailEditFragment.mSongsCountAndDuration = PlaylistUtil.calculateSongsDuration(playlistDetailEditFragment.getActivity(), playlistTrackInfoCreatePlaylistInfo.mTrackCount, playlistTrackInfoCreatePlaylistInfo.mCalcDuration);
            this.mData.mDownloadState = PlaylistDetailEditFragment.this.mHeaderDownloadState;
            HeaderMetadata headerMetadata = this.mData;
            headerMetadata.mTrackCount = count;
            headerMetadata.mSongsCountAndDuration = PlaylistDetailEditFragment.this.mSongsCountAndDuration;
            return 0;
        }
    }

    private void initHeaderMetadata(HeaderMetadata data) {
        ImageView imageView;
        this.mHeaderDownloadState = data.mDownloadState;
        String str = data.mSongsCountAndDuration;
        this.mSongsCountAndDuration = str;
        updateCountAndDuration(str);
        if (data.mPlaylistArt == null || (imageView = (ImageView) getActivity().findViewById(R.id.PlaylistDetailImage)) == null) {
            return;
        }
        imageView.setImageDrawable(data.mPlaylistArt);
    }

    protected void updateHeader() {
        updatePlaylistInfos();
        updateHeaderButtons(getCursor());
    }

    private void init(Bundle instanceState) {
        Uri contentUri = getContentUri();
        this.mUdo = DefaultUriMatcher.match(contentUri) == 16;
        this.mIsRemote = CirrusMediaSource.matchCloud(contentUri);
        this.mIsSmart = MediaProvider.isSmartPlaylist(contentUri);
        doInit(instanceState);
    }

    protected void doInit(Bundle instanceState) {
        initHeaderViewStub();
        this.mPlaylistName = (TextView) getActivity().findViewById(R.id.HeaderViewStub).findViewById(R.id.ThePlaylistName);
        if (checkShowEmptySmartPlaylistActivity(false) || instanceState == null || !instanceState.getBoolean("EXTRA_HAS_METADATA", false)) {
            return;
        }
        HeaderMetadata headerMetadata = new HeaderMetadata();
        this.mHeaderMetadata = headerMetadata;
        headerMetadata.restoreFromBundle(instanceState, getActivity());
        initHeaderMetadata(this.mHeaderMetadata);
    }

    private void initHeaderViewStub() {
        ViewStub viewStub = (ViewStub) getActivity().findViewById(R.id.HeaderViewStub);
        viewStub.setLayoutResource(R.layout.library_playlist_detail_edit_header);
        viewStub.inflate();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (this.mUpdateSongsCountAndDurationJobId == jobId) {
            this.mUpdateSongsCountAndDurationJobId = -1L;
            if (this.mSongsCountAndDuration == null) {
                this.mSongsCountAndDuration = PlaylistUtil.calculateSongsDuration(getActivity(), 0, 0);
            }
            updateCountAndDuration(this.mSongsCountAndDuration);
            UpdateHeaderInformationJob updateHeaderInformationJob = (UpdateHeaderInformationJob) job;
            if (updateHeaderInformationJob != null) {
                this.mHeaderMetadata = updateHeaderInformationJob.mData;
            }
        } else if (this.mGetPlaylistNameJobId == jobId) {
            this.mGetPlaylistNameJobId = -1L;
            updatePlaylistName(this.mCanRenamePlaylist);
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected void onArtReceived(Context context, Intent intent) {
        super.onArtReceived(context, intent);
        if (intent != null) {
            intent.getExtras();
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.UNKNOWN;
    }
}
