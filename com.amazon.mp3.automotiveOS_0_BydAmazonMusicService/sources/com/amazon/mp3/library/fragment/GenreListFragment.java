package com.amazon.mp3.library.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.eux.EUXProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.GenreContextMenuProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.EmptyFilterAdapter;
import com.amazon.mp3.library.adapter.GenreListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.job.DeleteTrackJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.adapters.EUXAdapter;
import com.amazon.music.widget.eux.adapters.EUXGenreAdapter;
import com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes3.dex */
public class GenreListFragment extends ArtListFragment {
    private static final int CONFIRM_DELETE_GENRE_ID = UniqueCodeUtil.nextUniqueCode();
    private ContextMenuProvider<GenreListAdapter.RowViewHolder> mContextMenuProvider;
    private int mFirstVisible;
    private boolean mIsLocal;
    private PlaylistMgmt mPlaylistMgmt;
    private Uri mPlaylistUri;
    private GenreListAdapter mAdapter = null;
    private Cursor mCursor = null;
    private boolean mListScrolling = false;
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.library.fragment.GenreListFragment.1
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            boolean zPlayStateUpdated = GenreListFragment.this.playStateUpdated();
            boolean zMediaCollectionUpdated = GenreListFragment.this.mediaCollectionUpdated();
            if (zPlayStateUpdated || zMediaCollectionUpdated) {
                GenreListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }
    };
    private Runnable mOnScrollSettledRunnable = new Runnable() { // from class: com.amazon.mp3.library.fragment.GenreListFragment.2
        @Override // java.lang.Runnable
        public void run() {
            GenreListFragment.this.mListScrolling = false;
            CacheManager.getInstance().unpause();
            CursorAdapter adapter = GenreListFragment.this.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    };
    private View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.GenreListFragment.3
        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View unused, ContextMenu.ContextMenuInfo menuInfo) {
            if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                GenreListFragment genreListFragment = GenreListFragment.this;
                genreListFragment.mContextMenuId = adapterContextMenuInfo.id;
                genreListFragment.mContextMenuProvider.onCreateContextMenu(GenreListFragment.this.getActivity(), menu, adapterContextMenuInfo.targetView.getTag(), adapterContextMenuInfo.position, GenreListFragment.this.getSourceId());
                GenreListFragment.this.mContextMenuProvider.setPageType(GenreListFragment.this.getPageType());
            }
        }
    };
    private GenreListAdapter.OnPlaylistAddAllButtonClickListener mPlaylistAddAllListener = new GenreListAdapter.OnPlaylistAddAllButtonClickListener() { // from class: com.amazon.mp3.library.fragment.GenreListFragment.4
        @Override // com.amazon.mp3.library.adapter.GenreListAdapter.OnPlaylistAddAllButtonClickListener
        public void onClick(long genreId) {
            GenreListFragment.this.mPlaylistMgmt.processAddAllButton(GenreListFragment.this.getGenreTrackContentUri(genreId));
        }
    };
    private final PlaylistMgmt.OnPlaylistMgmt mOnPlaylistMgmt = new PlaylistMgmt.OnPlaylistMgmt() { // from class: com.amazon.mp3.library.fragment.GenreListFragment.5
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
            return GenreListFragment.this.addJob(job);
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
            if (GenreListFragment.this.getActivity() instanceof PlaylistMgmt.OnPlaylistMgmt) {
                return (PlaylistMgmt.OnPlaylistMgmt) GenreListFragment.this.getActivity();
            }
            return null;
        }
    };

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected String getArtColumnName() {
        return "_id";
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldPrefetchArt() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected ContextMenuProvider<GenreListAdapter.RowViewHolder> getContextMenuProvider() {
        return this.mContextMenuProvider;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        PlaylistMgmt playlistMgmt = new PlaylistMgmt(getActivity(), getActivity(), null, this.mOnPlaylistMgmt, new Intent().putExtras(getArguments()));
        this.mPlaylistMgmt = playlistMgmt;
        playlistMgmt.onCreate(instanceState);
        this.mPlaybackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return viewOnCreateView;
    }

    private void init() {
        this.mPlaylistUri = (Uri) getArguments().getParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI");
        if (this.mAdapter == null) {
            this.mAdapter = new GenreListAdapter(getActivity(), getContentUri(), this.mPlaylistUri, this.mUseGridView, this);
        }
        this.mAdapter.setPlaylistAddAllButtonClickListener(this.mPlaylistAddAllListener);
        this.mContextMenuProvider = new GenreContextMenuProvider((BaseActivity) getActivity());
        this.mIsLocal = isSourceLocal();
        if (this.mPlaylistUri == null) {
            getListView().setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        } else {
            getListView().setOnCreateContextMenuListener(null);
        }
        checkFastScrollerRequired(this.mCursor, 25);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        if (this.mFromTabHost) {
            return;
        }
        super.initListViewHeaderForPortrait();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle instanceState) {
        super.onActivityCreated(instanceState);
        getActivity();
        this.mPlaylistMgmt.onActivityCreated(instanceState);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        GenreListAdapter genreListAdapter = this.mAdapter;
        if (genreListAdapter != null) {
            if (isVisibleToUser) {
                genreListAdapter.onResume(getActivity());
            } else {
                genreListAdapter.onPauseOrDestroy(getActivity());
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        if (albumArtCacheEnabled()) {
            getAlbumArtCache().unpause();
        }
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
        GenreListAdapter genreListAdapter = this.mAdapter;
        if (genreListAdapter != null) {
            genreListAdapter.onPauseOrDestroy(getActivity());
        }
        this.mPlaylistMgmt.onDestroy();
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
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
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mPlaylistMgmt.onSaveInstanceState(outState);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mPlaylistMgmt.onActivityResult(requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mPlaylistMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        if (firstVisibleItem == this.mFirstVisible) {
            return;
        }
        this.mFirstVisible = firstVisibleItem;
        if (!this.mListScrolling) {
            CacheManager.getInstance().pause();
            this.mListScrolling = true;
        }
        this.mHandler.removeCallbacks(this.mOnScrollSettledRunnable);
        this.mHandler.postDelayed(this.mOnScrollSettledRunnable, 500L);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        super.onSourceToggled(source);
        String str = source == ActionBarSourceToggleView.Source.CLOUD ? "cirrus" : "cirrus-local";
        Uri filterContentUri = MediaProvider.Genres.getFilterContentUri(str, "");
        resetContent(filterContentUri, str);
        this.mIsLocal = isSourceLocal();
        this.mAdapter.setContentUri(getActivity(), filterContentUri);
        changeCursor(null);
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void showEmptyUI() {
        showEmptyView(getEmptyView(), false);
    }

    private View getEmptyView() {
        EUXAdapter eUXGenreAdapter;
        EUXConfiguration eUXConfigurationBuildEUXConfiguration = EUXProvider.buildEUXConfiguration(getContext());
        if (this.mIsLocal) {
            eUXGenreAdapter = new EUXOfflineContentAdapter(getContext());
        } else {
            eUXGenreAdapter = new EUXGenreAdapter(getContext());
        }
        return eUXGenreAdapter.getView(eUXConfigurationBuildEUXConfiguration);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getEmptyFilterListAdapter() {
        return new EmptyFilterAdapter(getActivity(), getCurrentFilter(), R.string.dmusic_library_empty_filter_no_genres_found);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        requeryCursorInBackground();
        super.onContentUriChanged();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onAvailableItemClick(AbsListView listView, View view, int position, long id) {
        if (id != -1) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getActivity(), MediaProvider.Genres.getContentUri(getSourceId(), id), this.mPlaylistUri);
            String string = ((TextView) view.findViewById(R.id.GenreName)).getText().toString();
            String string2 = ((TextView) view.findViewById(R.id.Counts)).getText().toString();
            Uri uri = this.mPlaylistUri;
            if (uri != null) {
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", uri);
                intentForContentUri.putExtra("com.amazon.mp3.library.TITLE", string);
                intentForContentUri.putExtra("com.amazon.mp3.library.SUBTITLE", string2);
                startActivity(intentForContentUri);
                return;
            }
            GenreDetailFragment genreDetailFragmentNewInstance = GenreDetailFragment.newInstance(string, string2, MediaProvider.getSource((Uri) intentForContentUri.getExtras().getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI")));
            FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out);
            fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, genreDetailFragmentNewInstance, genreDetailFragmentNewInstance.getClass().getSimpleName()).addToBackStack(genreDetailFragmentNewInstance.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getGenreTrackContentUri(long id) {
        return MediaProvider.Genres.Tracks.getContentUri(getSourceId(), id);
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

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected CacheManager getAlbumArtCache() {
        return CacheManager.getInstance();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected String getHeaderText() {
        return getString(R.string.dmusic_library_genres_tab);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.GENRE;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getAdapter() {
        return this.mAdapter;
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
        return CursorListFragment.createCursor(getApplication(), getContentUri(), AmazonApplication.getLibraryItemFactory().getGenreProjection(), str, strArr, null);
    }

    private Dialog buildConfirmDeleteGenreDialog(final Uri tracksUri, final int trackCount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.dmusic_button_yes, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.GenreListFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BauhausToastUtils.showTextToast(GenreListFragment.this.getActivity(), R.string.dmusic_genre_toast_deleting_track_cloud, 1);
                GenreListFragment.this.addJob(new DeleteTrackJob(tracksUri));
            }
        });
        builder.setNegativeButton(R.string.dmusic_button_no, (DialogInterface.OnClickListener) null);
        builder.setTitle(R.string.dmusic_context_delete_cloud);
        builder.setMessage(getResources().getQuantityString(R.plurals.dmusic_genre_confirm_delete_cloud_msg, trackCount, Integer.valueOf(trackCount)));
        return builder.create();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void deleteCloudContent() {
        showDialog(CONFIRM_DELETE_GENRE_ID, buildConfirmDeleteGenreDialog(getClickedItem().getContentUri(), (int) ((Genre) getClickedItem()).getTrackCount()));
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getDeleteContentMessage() {
        return getClickedItem().isAvailableOffline() ? R.string.dmusic_genre_toast_deleting_track_device : R.string.dmusic_genre_toast_deleting_track_cloud;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        if (this.mPlaylistUri != null) {
            return PageType.ADD_TO_PLAYLIST_GENRES;
        }
        if (this.mIsLocal) {
            return PageType.OFFLINE_LIBRARY_GENRES;
        }
        return PageType.CLOUD_LIBRARY_GENRES;
    }
}
