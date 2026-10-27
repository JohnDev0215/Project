package com.amazon.mp3.library.fragment;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction;
import com.amazon.mp3.fragment.FragmentTabActivity;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.activity.NumberOfPlaylistsLimitReachedActivity;
import com.amazon.mp3.library.adapter.EmptyFilterAdapter;
import com.amazon.mp3.library.adapter.PlaylistsListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.db.HeaderCursor;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.job.DeletePlaylistJob;
import com.amazon.mp3.library.job.RenamePlaylistJob;
import com.amazon.mp3.library.job.UnfollowPrimePlaylistJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.PlaylistDatabaseCallback;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.CreatePlaylistTask;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.CreatePlaylistDialogFragment;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.recentlyplayed.Recents;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.weblab.ExperimentWeblabs;
import com.amazon.music.curate.provider.PlaylistIntents;
import com.amazon.music.curate.view.UserPlaylistFragment;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.recents.RecentsException;
import com.amazon.music.recents.model.ActivityType;
import com.amazon.music.subscription.UserSubscription;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistsListFragment extends ArtListFragment {
    private static final HashMap<Class<?>, Integer> CLASS_TO_PROGRESS_TEXT_MAP;
    private PlaylistsListAdapter mAdapter;
    private CacheManager mCacheManager;
    private int mClickedPosition;
    private PlaylistContextMenuProvider mContextMenuProvider;
    private boolean mIsRemote;
    private PlaylistMgmt mPlaylistMgmt;
    private Uri mPlaylistUri;
    private Subscription mRecentActivitySubscription;
    private static final String TAG = PlaylistsListFragment.class.getSimpleName();
    private static final int CONFIRM_DELETE_PLAYLIST_ID = UniqueCodeUtil.nextUniqueCode();
    private static final int CONFIRM_DELETE_PRIME_PLAYLIST_ID = UniqueCodeUtil.nextUniqueCode();
    private static final String[] PROJECTION = {"_id", "name", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "download_state", "track_count", "visibility"};
    private static final EnumSet<MediaProvider.SmartPlaylists.AvailableSmartPlaylists> SHOWN_SMART_PLAYLISTS = EnumSet.of(MediaProvider.SmartPlaylists.AvailableSmartPlaylists.LATEST_PURCHASES, MediaProvider.SmartPlaylists.AvailableSmartPlaylists.LATESET_UPLOADS, MediaProvider.SmartPlaylists.AvailableSmartPlaylists.RECENTLY_ADDED);
    private long mJob = -1;
    private PlaylistContextMenuProvider.PlaylistContextMenuProviderListener mProviderListener = new PlaylistContextMenuProvider.PlaylistContextMenuProviderListener() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.1
        @Override // com.amazon.mp3.dialog.RenamePlaylistDialog.OnPlaylistRenamedListener
        public void onRenameCompleted(String title, Playlist playlist) {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
        public void removeDownloadPlaylistMenuItem(ContextMenu menu) {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
        public boolean canStartNewTask() {
            return PlaylistsListFragment.this.mJob == -1;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
        public void deleteContent(int position, AbstractItem item) {
            PlaylistsListFragment.this.deletePlaylist(position);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
        public void removeDeletePlaylistMenuItem(ContextMenu menu) {
            menu.removeItem(R.id.MenuPlaylistDelete);
            menu.removeItem(R.id.MenuPlaylistContextDeleteDevice);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
        public ContextMenuPageType getContextMenuPageType() {
            return ContextMenuPageType.LONG_PRESS;
        }
    };
    private Object playlistCreateSubscriber = new Object() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.2
        @Subscribe
        public void onEvent(final CreatePlaylistDialogFragment.Result createPlaylistResult) {
            if (createPlaylistResult != null) {
                PlaylistsListFragment.this.createOrRenamePlaylist(createPlaylistResult);
            }
        }
    };
    private PlaylistDatabaseCallback mPlaylistDatabaseCallback = new PlaylistDatabaseCallback() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.3
        @Override // com.amazon.mp3.library.service.sync.PlaylistDatabaseCallback
        public void onUpdated(int numberOfUpdatedTracks) {
            PlaylistsListFragment.this.requeryCursorInBackground();
            PlaylistSyncHelper.deregisterCallback();
        }
    };
    private BroadcastReceiver mSyncPlaylistBroadcastReceiver = new AnonymousClass4();
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.5
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            boolean zPlayStateUpdated = PlaylistsListFragment.this.playStateUpdated();
            boolean zMediaCollectionUpdated = PlaylistsListFragment.this.mediaCollectionUpdated();
            if (PlaylistsListFragment.this.mAdapter != null) {
                if (zPlayStateUpdated || zMediaCollectionUpdated) {
                    PlaylistsListFragment.this.mAdapter.notifyDataSetChanged();
                }
            }
        }
    };
    private BroadcastReceiver mCacheClearedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ((intent.getIntExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", 0) & 8) != 0) {
                PlaylistsListFragment.this.requeryCursorInBackground();
            }
        }
    };
    private View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.7
        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View unused, ContextMenu.ContextMenuInfo menuInfo) {
            if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                PlaylistsListFragment playlistsListFragment = PlaylistsListFragment.this;
                playlistsListFragment.mContextMenuId = adapterContextMenuInfo.id;
                playlistsListFragment.mContextMenuProvider.onCreateContextMenu(PlaylistsListFragment.this.getActivity(), menu, adapterContextMenuInfo.targetView.getTag(), adapterContextMenuInfo.position, PlaylistsListFragment.this.getSourceId());
                PlaylistsListFragment.this.mContextMenuProvider.setPageType(PlaylistsListFragment.this.getPageType());
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHOW_OVERFLOW).withInteractionType(InteractionType.TAP).withPageType(PlaylistsListFragment.this.getPageType()).withEventTime(System.currentTimeMillis()).build());
            }
        }
    };
    private PlaylistsListAdapter.OnPlaylistAddAllButtonClickListener mPlaylistAddAllListener = new PlaylistsListAdapter.OnPlaylistAddAllButtonClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.8
        @Override // com.amazon.mp3.library.adapter.PlaylistsListAdapter.OnPlaylistAddAllButtonClickListener
        public void onClick(int position) {
            PlaylistsListFragment.this.mPlaylistMgmt.processAddAllButton(PlaylistsListFragment.this.getPlaylistTrackUris(position));
        }
    };
    private final PlaylistMgmt.OnPlaylistMgmt mOnPlaylistMgmt = new PlaylistMgmt.OnPlaylistMgmt() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment.9
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
            return PlaylistsListFragment.this.addJob(job);
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
            if (PlaylistsListFragment.this.getActivity() instanceof PlaylistMgmt.OnPlaylistMgmt) {
                return (PlaylistMgmt.OnPlaylistMgmt) PlaylistsListFragment.this.getActivity();
            }
            return null;
        }
    };

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected String getArtColumnName() {
        return "_id";
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getEmptyLayoutID() {
        return R.layout.empty_playlist_list_layout;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldPrefetchArt() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldUseFastScroll() {
        return false;
    }

    static {
        HashMap<Class<?>, Integer> map = new HashMap<>();
        CLASS_TO_PROGRESS_TEXT_MAP = map;
        map.put(DeletePlaylistJob.class, Integer.valueOf(R.string.dmusic_playlist_progress_deleting));
        map.put(RenamePlaylistJob.class, Integer.valueOf(R.string.dmusic_playlist_progress_renaming));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.PlaylistsListFragment$4, reason: invalid class name */
    class AnonymousClass4 extends BroadcastReceiver {
        AnonymousClass4() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SyncService.startSync(context, 3);
                }
            }, TimeUnit.SECONDS.toMillis(5L));
        }
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mSyncPlaylistBroadcastReceiver, new IntentFilter(PlaylistIntents.getActionSyncPlaylist()));
        PlaylistMgmt playlistMgmt = new PlaylistMgmt(getActivity(), getActivity(), null, this.mOnPlaylistMgmt, new Intent().putExtras(getArguments()));
        this.mPlaylistMgmt = playlistMgmt;
        playlistMgmt.onCreate(instanceState);
        this.mPlaybackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        EventBus.getDefault().register(this.playlistCreateSubscriber);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        registerReceiver(this.mCacheClearedReceiver, new IntentFilter("com.amazon.mp3.ACTION_CACHE_CLEARED"));
        init();
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle instanceState) {
        super.onActivityCreated(instanceState);
        this.mPlaylistMgmt.onActivityCreated(instanceState);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        resetCursorIfEmpty();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        PlaylistsListAdapter playlistsListAdapter = this.mAdapter;
        if (playlistsListAdapter != null) {
            if (isVisibleToUser) {
                playlistsListAdapter.onResume(getActivity());
            } else {
                playlistsListAdapter.onPauseOrDestroy(getActivity());
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
        LibraryFilters.remove(getFilterId());
        changeCursor(null);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        unregisterReceiver(this.mCacheClearedReceiver);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mSyncPlaylistBroadcastReceiver);
        super.onDestroy();
        PlaylistsListAdapter playlistsListAdapter = this.mAdapter;
        if (playlistsListAdapter != null) {
            playlistsListAdapter.onPauseOrDestroy(getActivity());
        }
        PlaylistMgmt playlistMgmt = this.mPlaylistMgmt;
        if (playlistMgmt != null) {
            playlistMgmt.onDestroy();
        }
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        PlaylistSyncHelper.deregisterCallback();
        EventBus.getDefault().unregister(this.playlistCreateSubscriber);
        unsubscribeRecentActivitySubscription();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mAdapter.onPauseOrDestroy(getActivity());
        this.mPlaylistMgmt.onPause();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.fragment.BaseFragment
    public void onDeactivated() {
        super.onDeactivated();
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

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        super.onSourceToggled(source);
        String str = source == ActionBarSourceToggleView.Source.CLOUD ? "cirrus" : "cirrus-local";
        Uri filterContentUri = MediaProvider.Playlists.getFilterContentUri(str, "");
        resetContent(filterContentUri, str);
        this.mIsRemote = CirrusMediaSource.matchCloud(filterContentUri);
        this.mAdapter.setContentUri(filterContentUri);
        changeCursor(null);
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onItemClick(AbsListView listView, View view, int position, long id) {
        PlaylistsListAdapter.RowViewHolder rowViewHolder = (PlaylistsListAdapter.RowViewHolder) view.getTag();
        if (rowViewHolder == null || isHeader(rowViewHolder.mPlaylistType)) {
            return;
        }
        super.onItemClick(listView, view, position, id);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected ContextMenuProvider<PlaylistsListAdapter.RowViewHolder> getContextMenuProvider() {
        return this.mContextMenuProvider;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (adapterContextMenuInfo == null) {
            return false;
        }
        this.mClickedPosition = adapterContextMenuInfo.position - getHeaderViewsCount();
        return super.onContextItemSelected(item);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            isVisible();
            if (!isVisible() && (getActivity() instanceof FragmentTabActivity)) {
                ((FragmentTabActivity) getActivity()).getTabHost().setSelectedTab(0);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        this.mPlaylistMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
        hideProgressDialog();
        this.mJob = -1L;
        if (job instanceof UnfollowPrimePlaylistJob) {
            if (resultCode == 3) {
                UnfollowPrimePlaylistJob unfollowPrimePlaylistJob = (UnfollowPrimePlaylistJob) job;
                Log.error(TAG, "Error unfollowing prime playlist luid: %s, error log: %s", unfollowPrimePlaylistJob.getPrimePlaylistLuid(), unfollowPrimePlaylistJob.getErrorLog());
            } else {
                requeryCursorInBackground();
            }
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onAvailableItemClick(AbsListView listView, View view, int position, long id) {
        String luid;
        EntityIdType entityIdType;
        int headerViewsCount = position - getHeaderViewsCount();
        if (id == -1) {
            if (headerViewsCount == 0) {
                if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(getContentUri()))) {
                    startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
                    return;
                }
                if (PlaylistUtil.maxNumberOfPlaylistsReached(getActivity())) {
                    startActivity(NumberOfPlaylistsLimitReachedActivity.getStartIntent());
                    return;
                }
                PlaylistSyncHelper.registerCallback(this.mPlaylistDatabaseCallback);
                CreatePlaylistDialogFragment.create(getPageType()).build().show(getActivity().getSupportFragmentManager(), CreatePlaylistDialogFragment.class.getSimpleName());
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.CREATE_PLAYLIST_ATTEMPT).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEventTime(System.currentTimeMillis()).build());
                return;
            }
            return;
        }
        Uri playlistTrackUris = getPlaylistTrackUris(headerViewsCount);
        ((TextView) view.findViewById(R.id.TrackCount)).getText().toString();
        Playlist playlist = (Playlist) getClickedItem();
        if (isPrime(playlist.getType())) {
            Cursor cursor = getCursor();
            cursor.moveToPosition(headerViewsCount);
            String sourceId = getSourceId();
            String string = ((TextView) view.findViewById(R.id.PlaylistName)).getText().toString();
            luid = cursor.getString(cursor.getColumnIndex("asin"));
            Bundle bundle = new Bundle();
            bundle.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", sourceId);
            bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", MediaProvider.Playlists.getCatalogContentUri(cursor, sourceId, luid));
            bundle.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            this.mNavigationProvider.changeScreenFragment(PlaylistDetailFactory.createFragment(luid, string, false, bundle), true, false, true);
            entityIdType = EntityIdType.ASIN;
        } else {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getApplication(), playlistTrackUris, this.mPlaylistUri);
            if (this.mPlaylistUri != null) {
                intentForContentUri.setFlags(intentForContentUri.getFlags() & (-67108865));
                startActivity(intentForContentUri);
            } else {
                navigateToPlaylistDetail(intentForContentUri, playlistTrackUris);
            }
            luid = playlist.getLuid();
            entityIdType = EntityIdType.PLAYLIST_ID;
        }
        UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(ActionType.SELECT_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEventTime(System.currentTimeMillis());
        if (StringUtils.isNotBlank(luid)) {
            builderWithEventTime.withEntityIdType(entityIdType).withEntityId(luid);
        }
        MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
    }

    private void navigateToPlaylistDetail(final Intent playlistIntent, final Uri playlistUri) {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistsListFragment.lambda$navigateToPlaylistDetail$0(playlistUri, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$navigateToPlaylistDetail$1(playlistUri, playlistIntent, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$navigateToPlaylistDetail$0(Uri uri, Subscriber subscriber) {
        subscriber.onNext(MediaProvider.Playlists.getPlaylistLuid(uri));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$navigateToPlaylistDetail$1(Uri uri, Intent intent, String str) {
        Fragment fragmentCreateFragment;
        NavigationProvider navigationProvider;
        if (str == null) {
            String str2 = TAG;
            Object[] objArr = new Object[1];
            objArr[0] = uri == null ? "NULL" : uri.toString();
            Log.error(str2, "playlist luid not available, playlistUri : %s", objArr);
            return;
        }
        if (!str.startsWith("localPlaylist") && !str.startsWith("smart") && bauhausSupported()) {
            fragmentCreateFragment = UserPlaylistFragment.INSTANCE.builder().withPlaylistId(str).build();
        } else {
            fragmentCreateFragment = PlaylistDetailFactory.createFragment(intent, true, null);
        }
        if (isDestroyed() || (navigationProvider = this.mNavigationProvider) == null) {
            return;
        }
        navigationProvider.changeScreenFragment(fragmentCreateFragment, true, false, true);
    }

    private boolean bauhausSupported() {
        return (PlatformUtil.isFireOS() && WeblabTreatment.T1 == ExperimentWeblabs.DM_CURATE_PLAYLIST_DETAIL_BAUHAUS_TABLET.getTreatment()) || (!PlatformUtil.isFireOS() && WeblabTreatment.T1 == ExperimentWeblabs.DM_CURATE_PLAYLIST_DETAIL_BAUHAUS.getTreatment());
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        requeryCursorInBackground();
        super.onContentUriChanged();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        if (this.mPlaylistUri == null && c != null && LibraryFilters.get(getFilterId()).length() == 0) {
            c = new HeaderCursor(c, 1, true);
        }
        changeCursor(c);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected Cursor query() {
        Uri uri = this.mPlaylistUri;
        if (uri == null) {
            Uri.Builder builderAppendQueryParameter = getContentUri().buildUpon().appendQueryParameter("include_playlist_headers", "true");
            if (AmazonApplication.getCapabilities().isRecentTracksSyncSupported()) {
                SHOWN_SMART_PLAYLISTS.add(MediaProvider.SmartPlaylists.AvailableSmartPlaylists.RECENTLY_PLAYED_TRACKS);
            } else {
                SHOWN_SMART_PLAYLISTS.remove(MediaProvider.SmartPlaylists.AvailableSmartPlaylists.RECENTLY_PLAYED_TRACKS);
            }
            MediaProvider.SmartPlaylists.setSmartPlaylistsToQuery(builderAppendQueryParameter, SHOWN_SMART_PLAYLISTS);
            return CursorListFragment.createCursor(getApplication(), builderAppendQueryParameter.build(), PROJECTION);
        }
        String str = uri.getPathSegments().size() > 4 ? this.mPlaylistUri.getPathSegments().get(4) : "-1";
        Uri.Builder builderBuildUpon = getContentUri().buildUpon();
        builderBuildUpon.appendQueryParameter("udo_playlist_exclude_id", str);
        builderBuildUpon.appendQueryParameter("include_playlist_headers", "true");
        return CursorListFragment.createCursor(getApplication(), builderBuildUpon.build(), PROJECTION);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getEmptyFilterListAdapter() {
        return new EmptyFilterAdapter(getActivity(), getCurrentFilter(), R.string.dmusic_library_empty_filter_no_playlists_found);
    }

    private void init() {
        this.mIsRemote = CirrusMediaSource.matchCloud(getContentUri());
        this.mPlaylistUri = (Uri) getArguments().getParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI");
        if (this.mAdapter == null) {
            this.mAdapter = new PlaylistsListAdapter(getActivity(), getContentUri(), this.mPlaylistUri, this, Branding.shouldShowPrimeBranding());
        }
        this.mAdapter.setPlaylistAddAllButtonClickListener(this.mPlaylistAddAllListener);
        this.mContextMenuProvider = new PlaylistContextMenuProvider((BaseActivity) getActivity(), getHeaderViewsCount(), this.mProviderListener, Boolean.valueOf((UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) ? false : true).booleanValue());
        if (this.mPlaylistUri == null) {
            getListView().setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        } else {
            getListView().setOnCreateContextMenuListener(null);
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        if (this.mFromTabHost) {
            return;
        }
        super.initListViewHeaderForPortrait();
    }

    private boolean isHeader(String playlistType) {
        return "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist.header".equals(playlistType) || "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist.header".equals(playlistType) || "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist.header".equals(playlistType);
    }

    private boolean isPrime(String type) {
        return "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist".equals(type);
    }

    private String getAsin(int position) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        return cursor.getString(cursor.getColumnIndex("asin"));
    }

    private String getLuid(int position) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        return cursor.getString(cursor.getColumnIndex("luid"));
    }

    private void resetCursorIfEmpty() {
        Cursor cursor = getCursor();
        if (cursor == null || cursor.getCount() == 0) {
            requeryCursorInBackground();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getPlaylistTrackUris(int position) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        String string = cursor.getString(cursor.getColumnIndex(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE));
        long j = cursor.getLong(cursor.getColumnIndex("_id"));
        if ("vnd.android.cursor.item/vnd.amazonmp3.udoplaylist".equals(string)) {
            return MediaProvider.UdoPlaylists.Tracks.getContentUri(getSourceId(), j);
        }
        if ("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist".equals(string)) {
            return MediaProvider.SmartPlaylists.Tracks.getContentUri(getSourceId(), j);
        }
        if ("vnd.android.cursor.item/vnd.amazonmp3.primeplaylist".equals(string)) {
            return MediaProvider.PrimePlaylists.getContentUri(getSourceId(), j);
        }
        throw new IllegalArgumentException("Invalid playlist type: " + string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createOrRenamePlaylist(final CreatePlaylistDialogFragment.Result result) {
        if (result.mPlaylistId == null) {
            new CreatePlaylistTask(getActivity(), getSourceId(), result.mPlaylistName, new CreatePlaylistTask.OnPlaylistCreatedListener() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment$$ExternalSyntheticLambda0
                @Override // com.amazon.mp3.library.util.CreatePlaylistTask.OnPlaylistCreatedListener
                public final void onPlaylistCreated(CreatePlaylistTask.Result result2, boolean z) {
                    this.f$0.lambda$createOrRenamePlaylist$2(result, result2, z);
                }
            }).execute(new Void[0]);
            showProgressDialog(R.string.dmusic_playlist_progress_creating, 500);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createOrRenamePlaylist$2(CreatePlaylistDialogFragment.Result result, CreatePlaylistTask.Result result2, boolean z) {
        hideProgressDialog();
        if (z) {
            UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
            boolean zIsFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
            if (((userSubscription.isHawkfireHome() || !userSubscription.hasSubscription()) && !zIsFreeTierUserPlaylistEditingEnabled) || !this.mIsRemote) {
                PlaylistUtil.startEditPlaylist(getActivity(), result2.mPlaylistUri, true);
            } else if (bauhausSupported()) {
                this.mNavigationProvider.changeScreenFragment(UserPlaylistFragment.INSTANCE.builder().withPlaylistId(result2.mPlaylistLuid).build(), true, false, true);
            } else {
                showPlaylistCreateExperiment(result2.mPlaylistLuid, result.mPlaylistName, result2.mPlaylistUri);
            }
        }
    }

    private void unsubscribeRecentActivitySubscription() {
        Subscription subscription = this.mRecentActivitySubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.mRecentActivitySubscription.unsubscribe();
    }

    private void showPlaylistCreateExperiment(final String playlistAsin, final String playlistName, final Uri playlistUri) {
        unsubscribeRecentActivitySubscription();
        this.mRecentActivitySubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$showPlaylistCreateExperiment$3((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$showPlaylistCreateExperiment$4(playlistAsin, playlistName, playlistUri, (Recents.RecentsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.library.fragment.PlaylistsListFragment$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$showPlaylistCreateExperiment$5(playlistUri, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPlaylistCreateExperiment$3(Subscriber subscriber) {
        try {
            subscriber.onNext(Recents.get((Application) getApplication()).withActivityTypes(ActivityType.PLAYED).withRemoveDuplicateTracks(true).withFilterSubscription(true).getRecents());
            subscriber.onCompleted();
        } catch (RecentsException e) {
            Log.error(TAG, "Failed to get recent tracks list", e);
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPlaylistCreateExperiment$4(String str, String str2, Uri uri, Recents.RecentsResponse recentsResponse) {
        if (!recentsResponse.getTracks().isEmpty()) {
            this.mNavigationProvider.changeScreenFragment(CuratePlaylistFragment.create().withId(str).withName(str2).build(), true, false, true);
        } else {
            PlaylistUtil.startEditPlaylist(getActivity(), uri, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPlaylistCreateExperiment$5(Uri uri, Throwable th) {
        PlaylistUtil.startEditPlaylist(getActivity(), uri, true);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected void onArtReceived(Context context, Intent intent) {
        PlaylistsListAdapter playlistsListAdapter;
        if (intent == null || intent.getExtras() == null || (playlistsListAdapter = this.mAdapter) == null) {
            return;
        }
        playlistsListAdapter.notifyImageCached(intent.getStringExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SOURCE"), intent.getIntExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SIZE", -1));
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected synchronized CacheManager getAlbumArtCache() {
        if (this.mCacheManager == null) {
            this.mCacheManager = CacheManager.getInstance();
        }
        return this.mCacheManager;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected String getHeaderText() {
        return getString(R.string.dmusic_library_playlists_tab);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.PLAYLIST_UDO;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getDeleteContentMessage() {
        return getClickedItem().isRemote() ? R.string.dmusic_playlist_toast_deleting_playlist_cloud : R.string.dmusic_playlist_toast_deleting_playlist_device;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.DeleteContentHandler
    public void deleteContent() {
        deletePlaylist(this.mClickedPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deletePlaylist(int position) {
        if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(getContentUri()))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
            return;
        }
        Playlist playlist = (Playlist) getClickedItem();
        if (!isPrime(playlist.getType())) {
            if (this.mJob == -1) {
                new DeleteUserPlaylistAction(getActivity(), playlist.getContentUri(), playlist.getName()).perform();
            }
        } else {
            new PlaylistUnfollowAction(getActivity(), getAsin(position), getLuid(position), CirrusMediaSource.matchCloud(playlist.getContentUri())).unfollowPlaylist();
        }
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        if (this.mPlaylistUri != null) {
            return PageType.ADD_TO_PLAYLIST_SELECT_PLAYLIST;
        }
        if (this.mIsRemote) {
            return PageType.CLOUD_LIBRARY_PLAYLISTS;
        }
        return PageType.OFFLINE_LIBRARY_PLAYLISTS;
    }
}
