package com.amazon.mp3.library.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.adapter.AddAlbumDetailToPlaylistAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Hashtable;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class AddDetailTracksToPlaylistFragment extends TrackListFragment {
    private static final String[] ALBUM_HEADER_PROJECTION = {"title"};
    private static final String[] ARTIST_HEADER_PROJECTION = {"name"};
    private static final String[] GENRE_HEADER_PROJECTION = {"name"};
    private AddAlbumDetailToPlaylistAdapter mAdapter;
    private String mDuration;
    private PlaylistMgmt mPlaylistMgmt;
    private TextView mPlaylistName;
    private TextView mPlaylistTrackCount;
    private Uri mPlaylistUri;
    private boolean mPostPurchaseToastDisplayed;
    private String mTitle;
    private long mHeaderJob = -1;
    private HeaderMetadata mHeaderMetadata = null;
    PlaylistMgmt.OnPlaylistMgmt mMgmtCallback = new PlaylistMgmt.OnPlaylistMgmt() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.1
        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public Cursor onAddAllTracksCursor() {
            return null;
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onAddAllTracksCursorFinish() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onPlaylistChangedObserver() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public boolean onShuffleExist() {
            return false;
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public long onAddJob(Job job) {
            return AddDetailTracksToPlaylistFragment.this.addJob(job);
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onSetDuration(String duration) {
            AddDetailTracksToPlaylistFragment.this.setDuration(duration);
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onUpdateCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylist) {
            AddDetailTracksToPlaylistFragment.this.mAdapter.setCachePlaylist(cachePlaylist);
            if (AddDetailTracksToPlaylistFragment.this.getListView() != null) {
                ((BaseFragment) AddDetailTracksToPlaylistFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AddDetailTracksToPlaylistFragment.this.getListView().invalidateViews();
                    }
                });
            }
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onAddAllTracksJobFinish() {
            AddDetailTracksToPlaylistFragment.this.updatePlaylistInfo();
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onCachePlaylistJobFinish() {
            AddDetailTracksToPlaylistFragment.this.updatePlaylistInfo();
        }
    };
    private final View.OnClickListener mOnDoneClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            FragmentActivity activity = AddDetailTracksToPlaylistFragment.this.getActivity();
            if (activity != null) {
                activity.finish();
            }
        }
    };
    private final View.OnClickListener mOnAddAllClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            AddDetailTracksToPlaylistFragment.this.mPlaylistMgmt.processAddAllButton();
        }
    };
    private TrackListToPlaylistAdapter.OnAddButtonClickListener mOnAddButtonClickListener = new TrackListToPlaylistAdapter.OnAddButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.4
        @Override // com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter.OnAddButtonClickListener
        public void onClick(final Uri trackUri) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.4.1
                @Override // java.lang.Runnable
                public void run() {
                    AddDetailTracksToPlaylistFragment.this.mPlaylistMgmt.processAddButtonClick(trackUri, false);
                }
            }).start();
        }
    };
    private TrackListToPlaylistAdapter.OnDeleteButtonClickListener mOnDeleteButtonClickListener = new TrackListToPlaylistAdapter.OnDeleteButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.5
        @Override // com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter.OnDeleteButtonClickListener
        public void onClick(final long playlistTrackId, final long udo) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddDetailTracksToPlaylistFragment.5.1
                @Override // java.lang.Runnable
                public void run() {
                    AddDetailTracksToPlaylistFragment.this.mPlaylistMgmt.processDeleteButtonClick(playlistTrackId, udo);
                }
            }).start();
        }
    };

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean albumArtCacheEnabled() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle instanceState) {
        getActivity().setTheme(R.style.Theme_AmazonMP3_Playlist);
        super.onCreate(instanceState);
        PlaylistMgmt playlistMgmt = new PlaylistMgmt(null, getActivity(), getContentUri(), this.mMgmtCallback, new Intent().putExtras(getArguments()));
        this.mPlaylistMgmt = playlistMgmt;
        playlistMgmt.onCreate(instanceState);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle instanceState) {
        super.onActivityCreated(instanceState);
        initViews();
        init(instanceState);
        this.mPlaylistMgmt.setActivity(getActivity());
        this.mPlaylistMgmt.onActivityCreated(instanceState);
        if (!"cirrus".equals(MediaProvider.getSource(this.mPlaylistUri)) || PlaylistUtil.getPlaylistCount(getActivity(), this.mPlaylistUri) < PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist()) {
            return;
        }
        startActivity(DialogActivity.getStartIntent(getActivity(), R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
        getActivity().finish();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mPlaylistMgmt.onResume();
        checkShowPostPurchaseToast();
        UxMetricsLogger.registerApplicationAction(UxMetricsLogger.ApplicationAction.LIBRARY_ALBUM_DETAIL_COMPLETE);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mPlaylistMgmt.onPause();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mPlaylistMgmt.onDestroy();
        LibraryFilters.remove(getFilterId());
        super.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mPlaylistMgmt.onActivityResult(requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        return super.getContentUri().buildUpon().appendEncodedPath("tracks").build();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        HeaderMetadata headerMetadata = this.mHeaderMetadata;
        if (headerMetadata != null) {
            headerMetadata.saveState(outState);
        }
        PlaylistMgmt playlistMgmt = this.mPlaylistMgmt;
        if (playlistMgmt != null) {
            playlistMgmt.onSaveInstanceState(outState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        this.mHeaderJob = addJob(new HeaderMetadataJob());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        this.mPlaylistMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
        if (jobId == this.mHeaderJob) {
            this.mHeaderJob = -1L;
            HeaderMetadata headerMetadata = ((HeaderMetadataJob) job).mData;
            this.mHeaderMetadata = headerMetadata;
            initHeaderMetadata(headerMetadata);
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    public String getSelection() {
        return DbUtil.applyBinaryOperator(DeluxeContentUtil.getNonDeluxeSelection(), "AND", TrackListAdapter.getDefaultSelection());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    public String[] getSelectionArgs() {
        return DbUtil.mergeColumnArrays(DeluxeContentUtil.getNonDeluxeSelectionArgs(), TrackListAdapter.getDefaultSelectionArgs());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        super.onContentUriChanged();
        init(null);
        onSessionConnected(-1L);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSortOrder() {
        return CirrusMediaSource.getAlbumTrackSortOrder();
    }

    private void initViews() {
        ViewStub viewStub = (ViewStub) getActivity().findViewById(R.id.HeaderViewStub);
        viewStub.setLayoutResource(R.layout.add_detail_tracks_to_playlist_header);
        viewStub.inflate();
    }

    private void init(Bundle instanceState) {
        replaceViewStub();
        this.mPlaylistUri = this.mPlaylistMgmt.getPlaylistUri();
        updatePlaylistInfo();
        new UpdatePlaylistTrackLuids().execute(new Void[0]);
        if (instanceState != null) {
            this.mPostPurchaseToastDisplayed = instanceState.getBoolean("com.amazon.mp3.EXTRA_POST_PURCHASE_TOAST_DISPLAYED", false);
            if (instanceState.getBoolean("EXTRA_HAS_METADATA", false)) {
                HeaderMetadata headerMetadata = new HeaderMetadata();
                this.mHeaderMetadata = headerMetadata;
                headerMetadata.restoreFromBundle(instanceState);
                initHeaderMetadata(this.mHeaderMetadata);
            }
        }
        this.mHeaderJob = addJob(new HeaderMetadataJob());
    }

    private class UpdatePlaylistTrackLuids extends AsyncTask<Void, Void, Void> {
        private Set<String> mPlaylistTrackLuids;

        private UpdatePlaylistTrackLuids() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            if (AddDetailTracksToPlaylistFragment.this.mPlaylistMgmt == null) {
                return null;
            }
            this.mPlaylistTrackLuids = PlaylistUtil.getUdoPlaylistTracksLuids(AddDetailTracksToPlaylistFragment.this.getActivity(), AddDetailTracksToPlaylistFragment.this.mPlaylistMgmt.getPlaylistUri());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            AddDetailTracksToPlaylistFragment.this.mAdapter.setPlaylistTrackLuids(this.mPlaylistTrackLuids);
        }
    }

    private void replaceViewStub() {
        View viewFindViewById = getActivity().findViewById(R.id.HeaderBarContainer);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        View viewFindViewById2 = getActivity().findViewById(R.id.PlaylistHeaderViewStub);
        if (viewFindViewById2 != null) {
            ViewStub viewStub = (ViewStub) viewFindViewById2;
            viewStub.setLayoutResource(R.layout.library_add_album_or_playlist_to_playlist_header);
            View viewInflate = viewStub.inflate();
            this.mPlaylistName = (TextView) viewInflate.findViewById(R.id.PlaylistName);
            this.mPlaylistTrackCount = (TextView) viewInflate.findViewById(R.id.TrackCount);
            View viewFindViewById3 = viewInflate.findViewById(R.id.HeaderBarFilter);
            if (viewFindViewById3 != null) {
                viewFindViewById3.setVisibility(8);
            }
            View viewFindViewById4 = viewInflate.findViewById(R.id.DoneButton);
            if (viewFindViewById4 != null) {
                viewFindViewById4.setOnClickListener(this.mOnDoneClickListener);
                viewInflate.setVisibility(0);
            }
        }
        View viewFindViewById5 = getActivity().findViewById(R.id.AddAllButton);
        if (viewFindViewById5 != null) {
            if (UserSubscriptionUtil.isNightwingOnly()) {
                viewFindViewById5.setVisibility(8);
            } else {
                viewFindViewById5.setVisibility(0);
                viewFindViewById5.setOnClickListener(this.mOnAddAllClickListener);
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        AddAlbumDetailToPlaylistAdapter addAlbumDetailToPlaylistAdapter = new AddAlbumDetailToPlaylistAdapter(getApplication(), getContentUri(), this, this.mOnAddButtonClickListener, this.mOnDeleteButtonClickListener, false);
        this.mAdapter = addAlbumDetailToPlaylistAdapter;
        return addAlbumDetailToPlaylistAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaylistInfo() {
        new UpdatePlaylistInfoTask().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized String getDuration() {
        if (this.mDuration == null) {
            this.mDuration = PlaylistUtil.calculateSongsDuration(getApplication(), 0, 0);
        }
        return this.mDuration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setDuration(String duration) {
        this.mDuration = duration;
    }

    class UpdatePlaylistInfoTask extends AsyncTask<Void, Void, Void> {
        private String mDuration;
        private String mTitle;

        UpdatePlaylistInfoTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            this.mTitle = AddDetailTracksToPlaylistFragment.this.getApplication().getString(R.string.dmusic_title_add_to_playlist, PlaylistUtil.getPlaylistName(AddDetailTracksToPlaylistFragment.this.getApplication(), AddDetailTracksToPlaylistFragment.this.mPlaylistUri));
            this.mDuration = AddDetailTracksToPlaylistFragment.this.getDuration();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            if (!AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                AddDetailTracksToPlaylistFragment.this.mPlaylistName.setText(this.mTitle);
                AddDetailTracksToPlaylistFragment.this.mPlaylistTrackCount.setText(this.mDuration);
            } else {
                BauhausTextStyler bauhausTextStyler = new BauhausTextStyler();
                AddDetailTracksToPlaylistFragment.this.mPlaylistName.setText(bauhausTextStyler.styleText(this.mTitle));
                AddDetailTracksToPlaylistFragment.this.mPlaylistTrackCount.setText(bauhausTextStyler.styleText(this.mDuration));
            }
        }
    }

    private void initHeaderMetadata(HeaderMetadata data) {
        this.mTitle = data.mTitle;
        TextView textView = (TextView) getActivity().findViewById(R.id.TitleTextView);
        if (textView != null) {
            textView.setText(this.mTitle);
        }
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
            BauhausToastUtils.showDefaultToast(getActivity(), R.string.dmusic_library_post_purchase_toast, 0);
        }
    }

    private boolean startedFromPurchase() {
        return getArguments().getBoolean("com.amazon.mp3.EXTRA_STARTED_FROM_PURCHASE", false);
    }

    private static class HeaderMetadata {
        String mTitle;

        private HeaderMetadata() {
        }

        public void saveState(Bundle bundle) {
            bundle.putString("EXTRA_TITLE", this.mTitle);
            bundle.putBoolean("EXTRA_HAS_METADATA", true);
        }

        public void restoreFromBundle(Bundle bundle) {
            this.mTitle = bundle.getString("EXTRA_TITLE");
        }
    }

    private class HeaderMetadataJob extends Job {
        HeaderMetadata mData = new HeaderMetadata();

        public HeaderMetadataJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            String[] strArr;
            Cursor cursorQuery;
            Uri contentUri = AddDetailTracksToPlaylistFragment.super.getContentUri();
            if (MediaProvider.Albums.isAlbum(contentUri)) {
                strArr = AddDetailTracksToPlaylistFragment.ALBUM_HEADER_PROJECTION;
            } else {
                strArr = MediaProvider.Artists.isArtist(contentUri) ? AddDetailTracksToPlaylistFragment.ARTIST_HEADER_PROJECTION : MediaProvider.Genres.isGenre(contentUri) ? AddDetailTracksToPlaylistFragment.GENRE_HEADER_PROJECTION : null;
            }
            String[] strArr2 = strArr;
            if (strArr2 == null || (cursorQuery = getContext().getContentResolver().query(AddDetailTracksToPlaylistFragment.super.getContentUri(), strArr2, null, null, null)) == null) {
                return 3;
            }
            try {
                if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                    if (MediaProvider.Albums.isAlbum(contentUri)) {
                        this.mData.mTitle = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("title"));
                    } else if (MediaProvider.Artists.isArtist(contentUri)) {
                        this.mData.mTitle = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("name"));
                    } else if (MediaProvider.Genres.isGenre(contentUri)) {
                        this.mData.mTitle = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("name"));
                    }
                }
                return 1;
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.UNKNOWN;
    }
}
