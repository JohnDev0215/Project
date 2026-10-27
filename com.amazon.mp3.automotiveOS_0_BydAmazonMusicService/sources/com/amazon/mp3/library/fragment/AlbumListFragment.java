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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.eux.EUXProvider;
import com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.AlbumListAdapter;
import com.amazon.mp3.library.adapter.EmptyFilterAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.job.DeleteTrackJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.adapters.EUXAdapter;
import com.amazon.music.widget.eux.adapters.EUXAlbumAdapter;
import com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter;
import java.util.Hashtable;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumListFragment extends ArtListFragment {
    private AlbumListAdapter mAdapter;
    private ContextMenuProvider<AlbumListAdapter.RowViewHolder> mContextMenuProvider;
    private Cursor mCursor;
    private boolean mIsRemote;
    private PlaylistMgmt mPlaylistMgmt;
    private Uri mPlaylistUri;
    private static final String LOGTAG = AlbumListFragment.class.getSimpleName();
    private static final int CONFIRM_DELETE_ALBUM_ID = UniqueCodeUtil.nextUniqueCode();
    private AlbumContextMenuProvider.AlbumContextMenuProviderListener mProviderListener = new AlbumContextMenuProvider.AlbumContextMenuProviderListener() { // from class: com.amazon.mp3.library.fragment.AlbumListFragment.1
        @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
        public void removeDeleteAlbumMenuItem(ContextMenu menu) {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
        public void removeDownloadAlbumMenuItem(ContextMenu menu) {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
        public boolean shouldDisplayAddToPlaylist() {
            return true;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
        public ContextMenuPageType getContextMenuPageType() {
            return ContextMenuPageType.LIBRARY;
        }
    };
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.library.fragment.AlbumListFragment.2
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            boolean zPlayStateUpdated = AlbumListFragment.this.playStateUpdated();
            boolean zMediaCollectionUpdated = AlbumListFragment.this.mediaCollectionUpdated();
            if (zPlayStateUpdated || zMediaCollectionUpdated) {
                AlbumListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }
    };
    private View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.AlbumListFragment.3
        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View unused, ContextMenu.ContextMenuInfo menuInfo) {
            if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                AlbumListFragment albumListFragment = AlbumListFragment.this;
                albumListFragment.mContextMenuId = adapterContextMenuInfo.id;
                albumListFragment.mContextMenuProvider.onCreateContextMenu(AlbumListFragment.this.getActivity(), menu, adapterContextMenuInfo.targetView.getTag(), adapterContextMenuInfo.position, AlbumListFragment.this.getSourceId());
                AlbumListFragment.this.mContextMenuProvider.setPageType(AlbumListFragment.this.getPageType());
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHOW_OVERFLOW).withInteractionType(InteractionType.TAP).withPageType(AlbumListFragment.this.getPageType()).withEventTime(System.currentTimeMillis()).build());
            }
        }
    };
    private AlbumListAdapter.OnPlaylistAddAllButtonClickListener mPlaylistAddAllListener = new AlbumListAdapter.OnPlaylistAddAllButtonClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumListFragment.4
        @Override // com.amazon.mp3.library.adapter.AlbumListAdapter.OnPlaylistAddAllButtonClickListener
        public void onClick(long albumId) {
            AlbumListFragment.this.mPlaylistMgmt.processAddAllButton(AlbumListFragment.this.getAlbumTracksContentUri(albumId));
        }
    };
    private final PlaylistMgmt.OnPlaylistMgmt mOnPlaylistMgmt = new PlaylistMgmt.OnPlaylistMgmt() { // from class: com.amazon.mp3.library.fragment.AlbumListFragment.5
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
            return AlbumListFragment.this.addJob(job);
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
            if (AlbumListFragment.this.getActivity() instanceof PlaylistMgmt.OnPlaylistMgmt) {
                return (PlaylistMgmt.OnPlaylistMgmt) AlbumListFragment.this.getActivity();
            }
            return null;
        }
    };

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
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        AlbumListAdapter albumListAdapter = this.mAdapter;
        if (albumListAdapter != null) {
            if (isVisibleToUser) {
                albumListAdapter.onResume(getActivity());
            } else {
                albumListAdapter.onPauseOrDestroy(getActivity());
            }
        }
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
        this.mPlaylistMgmt.onActivityCreated(instanceState);
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
        AlbumListAdapter albumListAdapter = this.mAdapter;
        if (albumListAdapter != null) {
            albumListAdapter.onPauseOrDestroy(getActivity());
        }
        this.mPlaylistMgmt.onDestroy();
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
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

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        super.onSourceToggled(source);
        boolean z = source == ActionBarSourceToggleView.Source.CLOUD;
        this.mIsRemote = z;
        String str = z ? "cirrus" : "cirrus-local";
        Uri filterContentUri = MediaProvider.Albums.getFilterContentUri(str, "");
        resetContent(filterContentUri, str);
        this.mAdapter.setContentUri(filterContentUri);
        changeCursor(null);
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void showEmptyUI() {
        showEmptyView(getEmptyView(), false);
    }

    private View getEmptyView() {
        EUXAdapter eUXOfflineContentAdapter;
        EUXConfiguration eUXConfigurationBuildEUXConfiguration = EUXProvider.buildEUXConfiguration(getContext());
        if (this.mIsRemote) {
            eUXOfflineContentAdapter = new EUXAlbumAdapter(getContext());
        } else {
            eUXOfflineContentAdapter = new EUXOfflineContentAdapter(getContext());
        }
        return eUXOfflineContentAdapter.getView(eUXConfigurationBuildEUXConfiguration);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected ContextMenuProvider<AlbumListAdapter.RowViewHolder> getContextMenuProvider() {
        return this.mContextMenuProvider;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onAvailableItemClick(AbsListView listView, View view, int position, long id) {
        Fragment albumDetailFragment;
        if (id != -1) {
            Uri contentUri = MediaProvider.Albums.getContentUri(getSourceId(), id);
            String asin = ((Album) getClickedItem()).getAsin();
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getApplication(), contentUri, this.mPlaylistUri);
            if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
                intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", asin);
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
                if (!this.mIsRemote) {
                    intentForContentUri.putExtra("EXTRA_OFFLINE_LIBRARY_NAVIGATION", true);
                }
            } else {
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
                intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", asin);
            }
            if (this.mPlaylistUri != null) {
                intentForContentUri.setFlags(intentForContentUri.getFlags() & (-67108865));
                startActivity(intentForContentUri);
            } else {
                if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
                    albumDetailFragment = new com.amazon.mp3.detailpages.album.AlbumDetailFragment();
                } else {
                    albumDetailFragment = new AlbumDetailFragment();
                }
                albumDetailFragment.setArguments(intentForContentUri.getExtras());
                FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out);
                fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, albumDetailFragment);
                fragmentTransactionBeginTransaction.addToBackStack(com.amazon.mp3.detailpages.album.AlbumDetailFragment.class.getSimpleName());
                fragmentTransactionBeginTransaction.commit();
            }
            UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(ActionType.SELECT_ALBUM).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEventTime(System.currentTimeMillis());
            if (StringUtils.isNotBlank(asin)) {
                builderWithEventTime.withEntityId(asin).withEntityIdType(EntityIdType.ASIN);
            }
            MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        requeryCursorInBackground();
        super.onContentUriChanged();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onMediaScannerFinished() {
        super.onMediaScannerFinished();
        if (this.mIsRemote) {
            return;
        }
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected CacheManager getAlbumArtCache() {
        return CacheManager.getInstance();
    }

    protected String getAlbumSortOrder() {
        return CirrusMediaSource.getAlbumSortOrder();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getEmptyFilterListAdapter() {
        return new EmptyFilterAdapter(getActivity(), getCurrentFilter(), R.string.dmusic_library_empty_filter_no_albums_found);
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
        return CursorListFragment.createCursor(getApplication(), getContentUri(), AmazonApplication.getLibraryItemFactory().getAlbumProjection(), str, strArr, getAlbumSortOrder());
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mPlaylistMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getAlbumTracksContentUri(long id) {
        return MediaProvider.Albums.Tracks.getContentUri(getSourceId(), id);
    }

    private void init() {
        this.mPlaylistUri = (Uri) getArguments().getParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI");
        if (this.mAdapter == null) {
            this.mAdapter = new AlbumListAdapter(getActivity(), getContentUri(), this.mPlaylistUri, this.mUseGridView, this, Branding.shouldShowPrimeBranding());
        }
        this.mAdapter.setPlaylistAddAllButtonClickListener(this.mPlaylistAddAllListener);
        this.mContextMenuProvider = new AlbumContextMenuProvider((BaseActivity) getActivity(), this.mProviderListener);
        if (this.mPlaylistUri == null) {
            getListView().setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        } else {
            getListView().setOnCreateContextMenuListener(null);
        }
        this.mIsRemote = CirrusMediaSource.matchCloud(getContentUri());
        checkFastScrollerRequired(this.mCursor, 25);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected String getHeaderText() {
        return getString(R.string.dmusic_library_albums_tab);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.ALBUM;
    }

    private Dialog buildConfirmDeleteAlbumDialog(final AbstractItem album) {
        int i;
        if (Long.parseLong(album.getId()) == -1) {
            throw new IllegalArgumentException("invalid album id!");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (album.isNotOwned()) {
            i = R.string.dmusic_button_remove;
            builder.setTitle(R.string.dmusic_context_remove_from_library);
            builder.setMessage(getString(R.string.dmusic_album_confirm_remove_library_msg, ((Album) album).getTitle()));
        } else {
            i = R.string.dmusic_button_delete;
            builder.setTitle(R.string.dmusic_context_delete_cloud);
            builder.setMessage(getString(R.string.dmusic_album_confirm_delete_cloud_msg, ((Album) album).getTitle()));
        }
        builder.setPositiveButton(i, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AlbumListFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BauhausToastUtils.showTextToast(AlbumListFragment.this.getActivity(), AlbumListFragment.this.getDeleteContentMessage(), 1);
                AlbumListFragment.this.addJob(new DeleteTrackJob(AlbumListFragment.this.getClickedItem().getContentUri()));
                if (album.isNotOwned() && album.getDownloadState() == 0) {
                    DeleteUtil.deleteTracksFromDevice(AlbumListFragment.this.getApplication(), MediaProvider.Albums.Tracks.getContentUri("cirrus-local", Long.parseLong(album.getId())));
                }
            }
        });
        builder.setNegativeButton(R.string.dmusic_button_cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void deleteCloudContent() {
        showDialog(CONFIRM_DELETE_ALBUM_ID, buildConfirmDeleteAlbumDialog(getClickedItem()));
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getDeleteContentMessage() {
        AbstractItem clickedItem = getClickedItem();
        return clickedItem.isAvailableOffline() ? R.string.dmusic_album_toast_deleting_album_device : (clickedItem.isNotOwned() && clickedItem.isInLibrary()) ? R.string.dmusic_album_toast_removing_album_cloud : R.string.dmusic_album_toast_deleting_album_cloud;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        if (this.mPlaylistUri != null) {
            return PageType.ADD_TO_PLAYLIST_ALBUMS;
        }
        if (this.mIsRemote) {
            return PageType.CLOUD_LIBRARY_ALBUMS;
        }
        return PageType.OFFLINE_LIBRARY_ALBUMS;
    }
}
