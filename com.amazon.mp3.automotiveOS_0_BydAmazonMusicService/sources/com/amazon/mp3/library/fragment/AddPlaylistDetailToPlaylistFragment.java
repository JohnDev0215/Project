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
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Hashtable;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class AddPlaylistDetailToPlaylistFragment extends PlaylistDetailEditFragment {
    private TrackListToPlaylistAdapter mAdapter;
    private String mDuration;
    private TextView mEditedPlaylistName;
    private long mPlaylistId;
    private PlaylistMgmt mPlaylistMgmt;
    private TextView mPlaylistTrackCount;
    private Uri mPlaylistUri;
    private PlaylistMgmt.OnPlaylistMgmt mOnPlaylistMgmt = new PlaylistMgmt.OnPlaylistMgmt() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.1
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
        public void onUpdateCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylist) {
            AddPlaylistDetailToPlaylistFragment.this.mAdapter.setCachePlaylist(cachePlaylist);
            if (AddPlaylistDetailToPlaylistFragment.this.getListView() != null) {
                ((BaseFragment) AddPlaylistDetailToPlaylistFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AddPlaylistDetailToPlaylistFragment.this.getListView().invalidateViews();
                    }
                });
            }
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onSetDuration(String duration) {
            AddPlaylistDetailToPlaylistFragment.this.setDuration(duration);
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onCachePlaylistJobFinish() {
            AddPlaylistDetailToPlaylistFragment.this.updatePlaylistInfos();
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public long onAddJob(Job job) {
            return AddPlaylistDetailToPlaylistFragment.this.addJob(job);
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onAddAllTracksJobFinish() {
            AddPlaylistDetailToPlaylistFragment.this.updatePlaylistInfos();
        }
    };
    private final View.OnClickListener mOnDoneClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            FragmentActivity activity = AddPlaylistDetailToPlaylistFragment.this.getActivity();
            if (activity != null) {
                activity.finish();
            }
        }
    };
    private final View.OnClickListener mOnAddAllClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            AddPlaylistDetailToPlaylistFragment.this.mPlaylistMgmt.processAddAllButton();
        }
    };
    private TrackListToPlaylistAdapter.OnAddButtonClickListener mOnAddButtonClickListener = new TrackListToPlaylistAdapter.OnAddButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.4
        @Override // com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter.OnAddButtonClickListener
        public void onClick(final Uri trackUri) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.4.1
                @Override // java.lang.Runnable
                public void run() {
                    AddPlaylistDetailToPlaylistFragment.this.mPlaylistMgmt.processAddButtonClick(trackUri, false);
                }
            }).start();
        }
    };
    private TrackListToPlaylistAdapter.OnDeleteButtonClickListener mOnDeleteButtonClickListener = new TrackListToPlaylistAdapter.OnDeleteButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.5
        @Override // com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter.OnDeleteButtonClickListener
        public void onClick(final long playlistTrackId, final long udo) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddPlaylistDetailToPlaylistFragment.5.1
                @Override // java.lang.Runnable
                public void run() {
                    AddPlaylistDetailToPlaylistFragment.this.mPlaylistMgmt.processDeleteButtonClick(playlistTrackId, udo);
                }
            }).start();
        }
    };

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getLandscapeLayout() {
        return R.layout.library_playlist_detail_edit_fragment;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected boolean showDownloadButton() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    void setTheme(Bundle instanceState) {
        getActivity().setTheme(R.style.Theme_AmazonMP3_Playlist);
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        setTheme(instanceState);
        super.onCreate(instanceState);
        PlaylistMgmt playlistMgmt = new PlaylistMgmt(null, getActivity(), getContentUri(), this.mOnPlaylistMgmt, new Intent().putExtras(getArguments()));
        this.mPlaylistMgmt = playlistMgmt;
        playlistMgmt.onCreate(instanceState);
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle instanceState) {
        super.onActivityCreated(instanceState);
        this.mPlaylistMgmt.setActivity(getActivity());
        this.mPlaylistMgmt.onActivityCreated(instanceState);
        this.mPlaylistUri = this.mPlaylistMgmt.getPlaylistUri();
        this.mPlaylistId = this.mPlaylistMgmt.getPlaylistId();
        initViews();
        new UpdatePlaylistTrackLuids().execute(new Void[0]);
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getContentViewLayoutId() {
        return (ScreenUtil.isPortrait() || getLandscapeLayout() == 0) ? R.layout.library_playlist_detail_fragment : getLandscapeLayout();
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PlaylistMgmt playlistMgmt = this.mPlaylistMgmt;
        if (playlistMgmt != null) {
            playlistMgmt.onSaveInstanceState(outState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mPlaylistMgmt.onResume();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mPlaylistMgmt.onPause();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPlaylistMgmt.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        TrackListToPlaylistAdapter trackListToPlaylistAdapter = new TrackListToPlaylistAdapter(getApplication(), getContentUri(), this, this.mOnAddButtonClickListener, this.mOnDeleteButtonClickListener, false);
        this.mAdapter = trackListToPlaylistAdapter;
        return trackListToPlaylistAdapter;
    }

    private void initViews() {
        View viewFindViewById = getActivity().findViewById(R.id.HeaderBarContainer);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        View viewFindViewById2 = getActivity().findViewById(R.id.PlaylistHeaderViewStub);
        if (viewFindViewById2 != null) {
            ViewStub viewStub = (ViewStub) viewFindViewById2;
            viewStub.setLayoutResource(R.layout.library_add_album_or_playlist_to_playlist_header);
            View viewInflate = viewStub.inflate();
            this.mEditedPlaylistName = (TextView) viewInflate.findViewById(R.id.PlaylistName);
            this.mPlaylistTrackCount = (TextView) viewInflate.findViewById(R.id.TrackCount);
            View viewFindViewById3 = viewInflate.findViewById(R.id.HeaderBarFilter);
            if (viewFindViewById3 != null) {
                viewFindViewById3.setVisibility(8);
            }
            View viewFindViewById4 = viewInflate.findViewById(R.id.DoneButton);
            if (viewFindViewById4 != null) {
                viewFindViewById4.setOnClickListener(this.mOnDoneClickListener);
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

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected void updateHeaderButtons(Cursor c) {
        super.updateHeaderButtons(c);
        View view = this.mHeaderButton;
        if (view != null) {
            view.setVisibility(8);
        }
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

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected void updatePlaylistInfos() {
        new UpdatePlaylistInfoTask().execute(new Void[0]);
        super.updatePlaylistInfos();
    }

    class UpdatePlaylistInfoTask extends AsyncTask<Void, Void, Void> {
        private String mDuration;
        private String mFocusedPlaylistTitle;
        private String mScratchPlaylistTitle;

        UpdatePlaylistInfoTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            this.mFocusedPlaylistTitle = AmazonApplication.getLibraryItemFactory().getPlaylist(AddPlaylistDetailToPlaylistFragment.this.getSinglePlaylistUri()).getName();
            this.mScratchPlaylistTitle = AmazonApplication.getContext().getString(R.string.dmusic_title_add_to_playlist, AddPlaylistDetailToPlaylistFragment.this.getCurrentPlaylistName());
            this.mDuration = AddPlaylistDetailToPlaylistFragment.this.getDuration();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            TextView textView = AddPlaylistDetailToPlaylistFragment.this.mPlaylistName;
            if (textView != null) {
                textView.setText(this.mFocusedPlaylistTitle);
            }
            if (AddPlaylistDetailToPlaylistFragment.this.mEditedPlaylistName != null) {
                AddPlaylistDetailToPlaylistFragment.this.mEditedPlaylistName.setText(this.mScratchPlaylistTitle);
            }
            if (AddPlaylistDetailToPlaylistFragment.this.mPlaylistTrackCount != null) {
                AddPlaylistDetailToPlaylistFragment.this.mPlaylistTrackCount.setText(this.mDuration);
            }
        }
    }

    private class UpdatePlaylistTrackLuids extends AsyncTask<Void, Void, Void> {
        private Set<String> mPlaylistTrackLuids;

        private UpdatePlaylistTrackLuids() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            this.mPlaylistTrackLuids = PlaylistUtil.getUdoPlaylistTracksLuids(AddPlaylistDetailToPlaylistFragment.this.getApplication(), AddPlaylistDetailToPlaylistFragment.this.mPlaylistUri);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            AddPlaylistDetailToPlaylistFragment.this.mAdapter.setPlaylistTrackLuids(this.mPlaylistTrackLuids);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentPlaylistName() {
        Cursor cursorQuery = getApplication().getContentResolver().query(MediaProvider.UdoPlaylists.getContentUri(getSourceId(), this.mPlaylistId), new String[]{"name"}, "_id=?", new String[]{String.valueOf(this.mPlaylistId)}, null);
        String string = (cursorQuery == null || !cursorQuery.moveToFirst()) ? null : cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("name"));
        DbUtil.closeCursor(cursorQuery);
        return string;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSelection() {
        if (isSourceLocal() || MediaProvider.isSmartPlaylist(getContentUri())) {
            return null;
        }
        return TrackListAdapter.getPlaylistSelection();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String[] getSelectionArgs() {
        if (isSourceLocal() || MediaProvider.isSmartPlaylist(getContentUri())) {
            return null;
        }
        return TrackListAdapter.getPlaylistSelectionArgs();
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        this.mPlaylistMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mPlaylistMgmt.onActivityResult(requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean requireNetworkConnection() {
        return "cirrus".equals(MediaProvider.getSource(this.mPlaylistUri));
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.UNKNOWN;
    }
}
