package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.service.job.Job;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class AddTrackListToPlaylistFragment extends TrackListFragment {
    private TrackListToPlaylistAdapter mAdapter;
    private long mGetPlaylistTrackLuidsJobId = -1;
    private TrackListToPlaylistAdapter.OnAddButtonClickListener mOnAddButtonClickListener = new TrackListToPlaylistAdapter.OnAddButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AddTrackListToPlaylistFragment.1
        @Override // com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter.OnAddButtonClickListener
        public void onClick(final Uri trackUri) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddTrackListToPlaylistFragment.1.1
                @Override // java.lang.Runnable
                public void run() {
                    AddTrackListToPlaylistFragment.this.mPlaylistMgmt.processAddButtonClick(trackUri, false);
                }
            }).start();
        }
    };
    private TrackListToPlaylistAdapter.OnDeleteButtonClickListener mOnDeleteButtonClickListener = new TrackListToPlaylistAdapter.OnDeleteButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AddTrackListToPlaylistFragment.2
        @Override // com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter.OnDeleteButtonClickListener
        public void onClick(final long playlistTrackId, final long udo) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddTrackListToPlaylistFragment.2.1
                @Override // java.lang.Runnable
                public void run() {
                    AddTrackListToPlaylistFragment.this.mPlaylistMgmt.processDeleteButtonClick(playlistTrackId, udo);
                }
            }).start();
        }
    };
    private PlaylistMgmt mPlaylistMgmt;
    private Uri mPlaylistUri;

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle instanceState) {
        super.onActivityCreated(instanceState);
        init();
        if (!"cirrus".equals(MediaProvider.getSource(this.mPlaylistMgmt.getPlaylistUri())) || PlaylistUtil.getPlaylistCount(getActivity(), this.mPlaylistUri) < PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist()) {
            return;
        }
        startActivity(DialogActivity.getStartIntent(getActivity(), R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
        getActivity().finish();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
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
        LibraryFilters.remove(getFilterId());
        super.onDestroy();
        this.mPlaylistMgmt.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        TrackListToPlaylistAdapter trackListToPlaylistAdapter = new TrackListToPlaylistAdapter(getApplication(), getContentUri(), this, this.mOnAddButtonClickListener, this.mOnDeleteButtonClickListener, false);
        this.mAdapter = trackListToPlaylistAdapter;
        return trackListToPlaylistAdapter;
    }

    private void init() {
        hideSearchBar();
        this.mGetPlaylistTrackLuidsJobId = addJob(new GetPlaylistTrackLuidsJob(getApplication(), this.mPlaylistMgmt.getPlaylistUri()));
    }

    private void hideSearchBar() {
        View viewFindViewById;
        View viewFindViewById2 = getActivity().findViewById(R.id.PlaylistHeaderViewStub);
        if (viewFindViewById2 == null || (viewFindViewById = viewFindViewById2.findViewById(R.id.HeaderBarFilter)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean requireNetworkConnection() {
        return "cirrus".equals(MediaProvider.getSource(this.mPlaylistUri));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        if (this.mGetPlaylistTrackLuidsJobId == jobId) {
            this.mGetPlaylistTrackLuidsJobId = -1L;
            TrackListToPlaylistAdapter trackListToPlaylistAdapter = this.mAdapter;
            if (trackListToPlaylistAdapter != null) {
                trackListToPlaylistAdapter.setPlaylistTrackLuids(((GetPlaylistTrackLuidsJob) job).mPlaylistTrackLuids);
            }
        }
    }

    private static class GetPlaylistTrackLuidsJob extends Job {
        private final Context mContext;
        private Set<String> mPlaylistTrackLuids = Collections.emptySet();
        private Uri mPlaylistUri;

        public GetPlaylistTrackLuidsJob(Context context, Uri playlistUri) {
            this.mContext = context;
            this.mPlaylistUri = playlistUri;
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            this.mPlaylistTrackLuids = PlaylistUtil.getUdoPlaylistTracksLuids(this.mContext, this.mPlaylistUri);
            return 1;
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.UNKNOWN;
    }
}
