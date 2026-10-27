package com.amazon.mp3.recentlyplayed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.dialog.fragment.ContentUnavailableDialogFragment;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.GenreListAdapter;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.fragment.AlbumDeletedHandler;
import com.amazon.mp3.library.fragment.AlbumDetailFragment;
import com.amazon.mp3.library.fragment.DeleteContentFragment;
import com.amazon.mp3.library.fragment.DeleteContentHandler;
import com.amazon.mp3.library.fragment.DownloadSettingDialogFragment;
import com.amazon.mp3.library.fragment.EmptyRecentlyPlayedFragment;
import com.amazon.mp3.library.fragment.GenreDetailFragment;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.fragment.VisibleListView;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.InactivityProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.provider.source.nowplaying.StationTrackProvider;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.prime.station.StationsFactory;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedAdapter;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.provider.RecentlyPlayedProvider;
import com.amazon.mp3.recentlyplayed.sharing.RecentlyPlayedShareProviderHelper;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ArtworkSizeUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.share.ShareProvider;
import com.amazon.music.station.StationItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedFragment<T extends CatalogContent> extends LibraryBaseFragment implements AdapterView.OnItemClickListener, DeleteContentHandler, AlbumDeletedHandler, RecentlyPlayedAdapter.OnCancelDownloadButtonClickListener, VisibleListView, RecentStationsUpgradeHandler {
    private static final String TAG = RecentlyPlayedFragment.class.getSimpleName();
    private AccountJobController mAccountJobs;
    private RecentlyPlayedAdapter mAdapter;
    private DeleteContentFragment mDeleteContentFragment;
    private boolean mDownloadQueried;
    private Fragment mEmptyFragment;
    private GridView mGridView;
    private View mGridViewContainer;
    private List<RecentlyPlayedItem> mItems;
    private AccountJobController.JobFinishedCallback mJobFinishedCallback;
    private LibraryPagerAdapter.OnListViewCreatedListener mListViewCreatedListener;
    private View mLoadingView;
    private View.OnCreateContextMenuListener mOnCreateContextMenuListener;
    private RecentlyPlayedProvider.Receiver mReceiver;
    private RecentlyPlayedItemBinder mRecentlyPlayedItemBinder;
    private RecentlyPlayedItem mRecentlyPlayedItemToDelete;
    private RecentlyPlayedProvider mRecentlyPlayedProvider;
    private boolean mSavedIsPlaying;
    private MediaItem mSavedMediaItem;
    private int mSavedPosition;
    private RecentlyPlayedItem mSelectedContextMenuItem;
    private Set<Uri> mUrisToMonitor = new HashSet();
    private Map<Uri, Integer> mDownloadStateMap = new HashMap();
    private long mGenreJob = -1;
    private final PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.1
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            if (NowPlayingUtil.isPlayingChanged(RecentlyPlayedFragment.this.mSavedMediaItem, RecentlyPlayedFragment.this.mSavedIsPlaying)) {
                RecentlyPlayedFragment recentlyPlayedFragment = RecentlyPlayedFragment.this;
                recentlyPlayedFragment.mSavedMediaItem = recentlyPlayedFragment.mPlaybackController.getCurrentMediaItem();
                RecentlyPlayedFragment.this.mSavedIsPlaying = PlayNotificationIcon.shouldAnimate();
                RecentlyPlayedFragment.this.mAdapter.notifyDataSetChanged();
            }
        }
    };
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.2
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
                RecentlyPlayedFragment.this.refreshView(true);
            }
        }
    };
    private DownloadListener mDownloadListener = new DownloadListener(new HashSet()) { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.7
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Group group, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Item item, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Item item) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Group group) {
            RecentlyPlayedFragment.this.mDownloadStateMap.put(RecentlyPlayedFragment.this.convertToGroupCollectionUri(s), Integer.valueOf(MusicDownloader.toAppDownloadState(downloadState)));
        }
    };

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean shouldSaveLastViewedSource() {
        return false;
    }

    public RecentlyPlayedFragment() {
        this.mReceiver = new RecentlyPlayedReceiver();
        this.mOnCreateContextMenuListener = new RecentlyPlayedContextMenuListener();
    }

    public static RecentlyPlayedFragment newInstance() {
        RecentlyPlayedFragment recentlyPlayedFragment = new RecentlyPlayedFragment();
        recentlyPlayedFragment.setArguments(new Bundle());
        return recentlyPlayedFragment;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mFromTabHost) {
            SharedPreferences.Editor editorEdit = getActivity().getSharedPreferences(getSharedPrefName(), 0).edit();
            int firstVisiblePosition = this.mGridView.getFirstVisiblePosition();
            this.mSavedPosition = firstVisiblePosition;
            editorEdit.putInt("key_list_saved_position", firstVisiblePosition);
            editorEdit.apply();
        }
        MusicDownloader.getInstance(getContext()).unregisterDownloadListener(this.mDownloadListener);
    }

    private String getSharedPrefName() {
        return "com.amazon.mp3_" + getClass().getSimpleName();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mAccountJobs = new AccountJobController(this, activity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mAccountJobs.destroy();
        this.mAccountJobs = null;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mRecentlyPlayedProvider = new RecentlyPlayedProvider(this.mReceiver);
        this.mFromTabHost = getArguments().getBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", false);
        this.mPlaybackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (savedInstanceState == null) {
            this.mDeleteContentFragment = new DeleteContentFragment();
            childFragmentManager.beginTransaction().add(this.mDeleteContentFragment, "TAG_DELETE_CONTENT_FRAGMENT").commitAllowingStateLoss();
        } else {
            this.mDeleteContentFragment = (DeleteContentFragment) childFragmentManager.findFragmentByTag("TAG_DELETE_CONTENT_FRAGMENT");
            Fragment fragmentFindFragmentByTag = getChildFragmentManager().findFragmentByTag("TAG_RECENTLY_PLAYED_DISABLED_DIALOG");
            if (fragmentFindFragmentByTag instanceof ContentUnavailableDialogFragment) {
                ContentUnavailableDialogFragment contentUnavailableDialogFragment = (ContentUnavailableDialogFragment) fragmentFindFragmentByTag;
                contentUnavailableDialogFragment.setDeleteHandler(this);
                contentUnavailableDialogFragment.setItemName(this.mRecentlyPlayedItemToDelete.getLibraryItemName(getResources()));
            }
            this.mEmptyFragment = getChildFragmentManager().findFragmentByTag("TAG_EMPTY_FRAGMENT");
            ArrayList parcelableArrayList = savedInstanceState.getParcelableArrayList("SAVE_RECENTLY_PLAYED_ITEMS");
            if (parcelableArrayList != null) {
                setItems(parcelableArrayList);
            }
        }
        this.mDeleteContentFragment.setAlbumDeletedHandler(this);
        setHeaderView(new ActionBarView(getActivity(), getString(R.string.dmusic_recently_played), false, true));
    }

    public AbsListView getListView() {
        return this.mGridView;
    }

    @Override // com.amazon.mp3.library.fragment.VisibleListView
    public void scrollToTop() {
        this.mGridView.smoothScrollToPositionFromTop(0, 0);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        refreshView();
        MusicDownloader.getInstance(getContext()).registerDownloadListener(this.mDownloadListener);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mItems != null) {
            outState.putParcelableArrayList("SAVE_RECENTLY_PLAYED_ITEMS", new ArrayList<>(this.mItems));
        }
        GridView gridView = this.mGridView;
        if (gridView != null) {
            outState.putInt("key_list_saved_position", gridView.getFirstVisiblePosition());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recently_played, container, false);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mGridView = (GridView) view.findViewById(R.id.GridView);
        this.mGridViewContainer = view.findViewById(R.id.GridViewContainer);
        this.mLoadingView = view.findViewById(R.id.LoadingScreen);
        this.mGridView.setOnItemClickListener(this);
        this.mGridView.setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        RecentlyPlayedAdapter recentlyPlayedAdapter = this.mAdapter;
        if (recentlyPlayedAdapter != null) {
            this.mGridView.setAdapter((ListAdapter) recentlyPlayedAdapter);
        }
        if (savedInstanceState != null && savedInstanceState.containsKey("key_list_saved_position")) {
            this.mSavedPosition = savedInstanceState.getInt("key_list_saved_position");
        } else if (this.mFromTabHost) {
            this.mSavedPosition = getActivity().getSharedPreferences(getSharedPrefName(), 0).getInt("key_list_saved_position", 0);
        } else {
            this.mSavedPosition = 0;
        }
        if (InactivityProvider.isInactive(getActivity())) {
            return;
        }
        this.mGridView.setSelection(this.mSavedPosition);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void initHomeNav() {
        if (!AmazonApplication.getCapabilities().isRecentTracksSyncSupported()) {
            super.initHomeNav();
        } else {
            requestHomeButtonAsUp();
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public void onDeactivated() {
        super.onDeactivated();
        if (this.mDownloadQueried) {
            this.mDownloadQueried = false;
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        super.onDestroy();
        RecentlyPlayedItemBinder recentlyPlayedItemBinder = this.mRecentlyPlayedItemBinder;
        if (recentlyPlayedItemBinder != null) {
            recentlyPlayedItemBinder.clearBinding();
        }
        GridView gridView = this.mGridView;
        if (gridView != null) {
            gridView.setOnTouchListener(null);
        }
        removeHeaderView();
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.recentlyplayed.RecentlyPlayedAdapter.OnCancelDownloadButtonClickListener
    public void onCancelDownloadClick(RecentlyPlayedItem item) {
        Uri contentUri = item.getContentUri();
        if (!contentUri.getLastPathSegment().equals("tracks")) {
            contentUri.buildUpon().appendPath("tracks").build();
        }
        MusicDownloader.getInstance(getContext()).cancel(contentUri.toString());
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentAccessUtil.ContentAccessOperation contentAccessOperation;
        final RecentlyPlayedItem recentlyPlayedItem = (RecentlyPlayedItem) parent.getItemAtPosition(position);
        MetricsLogger.sendUiClickMetricForRecentlyPlayedItem(recentlyPlayedItem);
        if ("cirrus".equals(recentlyPlayedItem.getSource())) {
            contentAccessOperation = ContentAccessUtil.ContentAccessOperation.STREAM;
        } else {
            contentAccessOperation = ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE;
        }
        ContentUnavailableReason catalogContentUnavailableReason = ContentAccessUtil.getCatalogContentUnavailableReason(recentlyPlayedItem, contentAccessOperation);
        if (catalogContentUnavailableReason == null) {
            ContentAccessUtil.checkContinueActionWithoutEnabilityCheck(getActivity(), recentlyPlayedItem, contentAccessOperation, (ContentAccessUtil.CatalogActionListener) new ContentAccessUtil.CatalogActionListener<T>() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.3
                @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
                public void continueCatalogAction(final T item, boolean canContinue) {
                    if (canContinue) {
                        RecentlyPlayedFragment.this.startRecentlyPlayed(recentlyPlayedItem);
                    }
                }
            });
        } else {
            handleRecentlyPlayedDisabled(recentlyPlayedItem, catalogContentUnavailableReason, contentAccessOperation);
        }
    }

    @Override // com.amazon.mp3.library.fragment.DeleteContentHandler
    public void deleteContent() {
        RecentlyPlayedItem recentlyPlayedItem = this.mRecentlyPlayedItemToDelete;
        if (recentlyPlayedItem != null) {
            this.mDeleteContentFragment.deleteAlbum(recentlyPlayedItem.getSource(), this.mRecentlyPlayedItemToDelete.getContentUri().buildUpon().appendEncodedPath("tracks").build(), this.mRecentlyPlayedItemToDelete.getTitle());
            this.mRecentlyPlayedItemToDelete = null;
        }
    }

    @Override // com.amazon.mp3.library.fragment.AlbumDeletedHandler
    public void onAlbumDeleted(int result) {
        this.mRecentlyPlayedProvider.getRecentlyPlayed();
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        if (jobId == this.mGenreJob) {
            this.mGenreJob = -1L;
            startActivity(((GenreJob) job).mIntent);
        } else if (job instanceof QueryDownloadStateJob) {
            onQueryDownloadStateJobFinished(job);
        }
        this.mAccountJobs.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mAccountJobs.onActivityResult(getSession(), requestCode, resultCode, data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshView(boolean forceUiThread) {
        if (getActivity() == null || !forceUiThread) {
            refreshView();
        } else {
            getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    RecentlyPlayedFragment.this.refreshView();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshView() {
        this.mRecentlyPlayedProvider.getRecentlyPlayed();
        LibraryPagerAdapter.OnListViewCreatedListener onListViewCreatedListener = this.mListViewCreatedListener;
        if (onListViewCreatedListener != null) {
            onListViewCreatedListener.onListViewCreated(getListView());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setItems(final List<RecentlyPlayedItem> items) {
        this.mItems = items;
        updateEmptyFragment(items);
        updateGridView(items);
        this.mLoadingView.setVisibility(8);
    }

    private void updateGridView(final List<RecentlyPlayedItem> items) {
        if (items.isEmpty()) {
            this.mGridViewContainer.setVisibility(8);
            return;
        }
        this.mGridViewContainer.setVisibility(0);
        RecentlyPlayedAdapter recentlyPlayedAdapter = this.mAdapter;
        if (recentlyPlayedAdapter == null) {
            initAdapter(items);
            this.mGridView.setAdapter((ListAdapter) this.mAdapter);
        } else {
            recentlyPlayedAdapter.setNotifyOnChange(false);
            this.mAdapter.clear();
            this.mAdapter.addAll(items);
            this.mAdapter.notifyDataSetChanged();
        }
        this.mUrisToMonitor.clear();
        Iterator<RecentlyPlayedItem> it = items.iterator();
        while (it.hasNext()) {
            this.mUrisToMonitor.add(it.next().getContentUri());
        }
        queryDownloadStates(this.mUrisToMonitor);
        startQueryingDownloadProgress();
    }

    private void updateEmptyFragment(List<RecentlyPlayedItem> items) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (items.isEmpty()) {
            this.mEmptyFragment = EmptyRecentlyPlayedFragment.newInstance();
            childFragmentManager.beginTransaction().replace(R.id.EmptyRecentlyPlayedFragmentContainer, this.mEmptyFragment, "TAG_EMPTY_FRAGMENT").commitAllowingStateLoss();
        } else if (this.mEmptyFragment != null) {
            childFragmentManager.beginTransaction().remove(this.mEmptyFragment).commitAllowingStateLoss();
            childFragmentManager.executePendingTransactions();
            this.mEmptyFragment = null;
        }
    }

    private void initAdapter(final List<RecentlyPlayedItem> items) {
        int gridArtworkSize = ArtworkSizeUtil.getGridArtworkSize(getActivity());
        this.mRecentlyPlayedItemBinder = new RecentlyPlayedItemBinder(getActivity());
        RecentlyPlayedAdapter recentlyPlayedAdapter = new RecentlyPlayedAdapter(getActivity(), items, this.mRecentlyPlayedItemBinder, gridArtworkSize, Branding.shouldShowPrimeBranding());
        this.mAdapter = recentlyPlayedAdapter;
        recentlyPlayedAdapter.setDownloadStateMap(this.mDownloadStateMap);
        this.mAdapter.setOnCancelDownloadClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecentlyPlayed(RecentlyPlayedItem item) {
        RecentsPlaybackHelper.startRecentlyPlayed(item, getActivity(), this);
    }

    @Override // com.amazon.mp3.recentlyplayed.RecentStationsUpgradeHandler
    public void doStationsUpgrade(final Intent intent, final String seedId, final String providerState, final RecentlyPlayedItem item) {
        final FragmentActivity activity = getActivity();
        this.mLoadingView.setVisibility(0);
        Observable.create(new Observable.OnSubscribe<StationItem>() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.6
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super StationItem> subscriber) {
                try {
                    StationStorageUtil.doUpgrade(activity);
                    subscriber.onNext(StationsFactory.getInstance(RecentlyPlayedFragment.this.getContext()).getStationManager().getStationItemBySeedId(seedId));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).timeout(3L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe((Subscriber) new Subscriber<StationItem>() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.5
            @Override // rx.Observer
            public void onCompleted() {
                RecentlyPlayedFragment.this.mLoadingView.setVisibility(8);
                RecentlyPlayedFragment.this.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                RecentlyPlayedFragment.this.mLoadingView.setVisibility(8);
                BauhausToastUtils.showTextToast(activity, R.string.dmusic_no_connection_title, 0);
            }

            @Override // rx.Observer
            public void onNext(final StationItem stationItem) {
                try {
                    Uri contentUri = MediaProvider.Station.getContentUri(stationItem.getKey());
                    if (!StringUtil.isNullOrWhiteSpaces(providerState)) {
                        JSONObject jSONObject = new JSONObject(providerState);
                        JSONObject jSONObject2 = new JSONObject();
                        StationTrackProvider.stationItemToJson(jSONObject2, stationItem, contentUri);
                        jSONObject.put("provider", jSONObject2);
                        NowPlayingManager.getInstance().restore(jSONObject, true);
                    } else {
                        item.setContentUri(contentUri);
                        RecentsPlaybackHelper.handleRemoteRecentPlayback(activity, item);
                    }
                } catch (JSONException unused) {
                }
            }
        });
    }

    private void handleRecentlyPlayedDisabled(RecentlyPlayedItem item, ContentUnavailableReason reason, ContentAccessUtil.ContentAccessOperation contentAccessOperation) {
        if (reason == ContentUnavailableReason.DEVICE_NOT_PRIME_AUTHORIZED) {
            this.mJobFinishedCallback = new AccountJobCallback(item);
            this.mAccountJobs.checkImmediate(getSession(), this.mJobFinishedCallback);
        } else {
            showContentEnabilityDialog(item, reason, contentAccessOperation);
        }
    }

    private void showContentEnabilityDialog(RecentlyPlayedItem item, ContentUnavailableReason reason, ContentAccessUtil.ContentAccessOperation contentAccessOperation) {
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(getContext(), reason, this, item.getLibraryItemName(getResources()), item, contentAccessOperation);
        if (dialogForDisabledReason != null) {
            this.mRecentlyPlayedItemToDelete = item;
            dialogForDisabledReason.show(getChildFragmentManager(), "TAG_RECENTLY_PLAYED_DISABLED_DIALOG");
        }
    }

    private class AccountJobCallback implements AccountJobController.JobFinishedCallback {
        private RecentlyPlayedItem mItem;

        public AccountJobCallback(RecentlyPlayedItem item) {
            this.mItem = item;
        }

        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onSuccess() {
            if (this.mItem.getContentUnavailableReason() != null) {
                PrimeContentUtil.tryToAutoResolveEnabilityProblem(this.mItem, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment.AccountJobCallback.1
                    @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
                    public void resultReceived(boolean contentAvailable, ContentUnavailableReason reasonUnavailable) {
                    }
                });
            }
            RecentlyPlayedFragment.this.startRecentlyPlayed(this.mItem);
            RecentlyPlayedFragment.this.mJobFinishedCallback = null;
        }

        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onFailure() {
            RecentlyPlayedFragment.this.mJobFinishedCallback = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowDownloadMenuItem(RecentlyPlayedItem item) {
        return item.getItemType() != RecentlyPlayedItem.Type.ALL_SONGS && item.getItemType() != RecentlyPlayedItem.Type.STATION && item.getSource().equals("cirrus") && isContentAvailableToDownload(item);
    }

    private boolean isContentAvailableToDownload(final RecentlyPlayedItem item) {
        ContentUnavailableReason contentUnavailableReason = item.getContentUnavailableReason();
        return contentUnavailableReason == null || contentUnavailableReason == ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldEnableDownloadMenuItem(Uri uri) {
        Integer num = this.mDownloadStateMap.get(uri);
        return num == null || num.intValue() == -1 || num.intValue() == 5;
    }

    private void startDownload(Uri contentUri, NotificationInfo notificationInfo, boolean downloadNonAddedPrimeTracks) {
        Uri uriBuild = !MediaProvider.Tracks.isTrackCollection(contentUri, new String[0]) ? contentUri.buildUpon().appendPath("tracks").build() : contentUri;
        String string = uriBuild.toString();
        if (DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(getActivity())) {
            DownloadSettingDialogFragment.showSettingsDialog(getActivity(), string, uriBuild, notificationInfo, false);
            return;
        }
        BauhausToastUtils.showTextToast(getActivity(), R.string.dmusic_download_queue_started, 1);
        this.mDownloadStateMap.put(contentUri, 1);
        notifyIfNeeded(contentUri);
        this.mDownloadListener.addRequestId(string);
        MusicDownloader.getInstance(getContext()).download(string, uriBuild, notificationInfo, downloadNonAddedPrimeTracks, true);
    }

    private boolean notifyIfNeeded(Uri uri) {
        RecentlyPlayedAdapter recentlyPlayedAdapter;
        if (!this.mUrisToMonitor.contains(uri) || (recentlyPlayedAdapter = this.mAdapter) == null) {
            return false;
        }
        recentlyPlayedAdapter.notifyDataSetChanged();
        return true;
    }

    private void queryDownloadStates(Set<Uri> uris) {
        ArrayList arrayList = new ArrayList();
        for (Uri uri : uris) {
            if (!this.mDownloadStateMap.containsKey(uri)) {
                arrayList.add(uri);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        addJob(new QueryDownloadStateJob(arrayList));
    }

    private void startQueryingDownloadProgress() {
        this.mDownloadQueried = true;
    }

    private void onQueryDownloadStateJobFinished(Job job) {
        Map<Uri, Integer> downloadStates = ((QueryDownloadStateJob) job).getDownloadStates();
        this.mDownloadStateMap.putAll(downloadStates);
        notifyIfNeeded(downloadStates.keySet());
    }

    private void notifyIfNeeded(Set<Uri> uris) {
        Iterator<Uri> it = uris.iterator();
        while (it.hasNext() && !notifyIfNeeded(it.next())) {
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        String stringExtra;
        RecentlyPlayedItem recentlyPlayedItem = this.mSelectedContextMenuItem;
        this.mSelectedContextMenuItem = null;
        int itemId = item.getItemId();
        if (itemId == R.id.MenuDownload) {
            if (!ConnectivityUtil.hasAnyInternetConnection()) {
                startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
            } else {
                Uri contentUri = recentlyPlayedItem.getContentUri();
                startDownload(contentUri, new NotificationInfo(recentlyPlayedItem.getTitle(), null, getImageCacheUri(contentUri, recentlyPlayedItem.getItemType())), recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM && recentlyPlayedItem.getCatalogStatus().isHawkfire() && !recentlyPlayedItem.getOwnershipStatus().isInLibrary());
            }
            return true;
        }
        if (itemId == R.id.MenuShare) {
            ShareProvider shareProvider = RecentlyPlayedShareProviderHelper.getShareProvider(recentlyPlayedItem, getActivity());
            if (shareProvider != null) {
                shareProvider.startShare();
            }
            return true;
        }
        if (itemId == R.id.MenuViewDetails) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getActivity(), recentlyPlayedItem.getContentUri());
            if (AlbumDetailFragment.class.getSimpleName().equals(intentForContentUri.getStringExtra("com.amazon.mp3.fragment.extra"))) {
                intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", recentlyPlayedItem.getCollectionAsin());
                intentForContentUri.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
            } else {
                if (GenreDetailFragment.class.getSimpleName().equals(intentForContentUri.getStringExtra("com.amazon.mp3.fragment.extra"))) {
                    this.mGenreJob = addJob(new GenreJob(intentForContentUri, recentlyPlayedItem.getContentUri()));
                    return true;
                }
                if (BrushFragment.class.getSimpleName().equals(intentForContentUri.getStringExtra("com.amazon.mp3.fragment.extra")) && (stringExtra = intentForContentUri.getStringExtra("brush_uri_extra")) != null && stringExtra.contains("artist")) {
                    intentForContentUri.putExtra("brush_asin_extra", recentlyPlayedItem.getCollectionAsin());
                }
            }
            startActivity(intentForContentUri);
            getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.fragment_fade_out);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.RecentlyPlayedFragment$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;

        static {
            int[] iArr = new int[RecentlyPlayedItem.Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr;
            try {
                iArr[RecentlyPlayedItem.Type.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.GENRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.SMART_PLAYLIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_PLAYLIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private String getImageCacheUri(Uri uri, RecentlyPlayedItem.Type type) {
        switch (AnonymousClass8.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[type.ordinal()]) {
            case 1:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ARTIST.intValue(), MediaProvider.Artists.getArtistId(uri)).toString();
            case 2:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), MediaProvider.Albums.getAlbumId(uri)).toString();
            case 3:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.GENRE.intValue(), MediaProvider.Genres.getGenreId(uri)).toString();
            case 4:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO.intValue(), MediaProvider.Playlists.getPlaylistId(uri)).toString();
            case 5:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_SMART.intValue(), MediaProvider.SmartPlaylists.getPlaylistId(uri)).toString();
            case 6:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), MediaProvider.Playlists.getPlaylistId(uri)).toString();
            default:
                return null;
        }
    }

    private class GenreJob extends Job {
        private Genre mGenre;
        private final Uri mGenreContentUri;
        private Intent mIntent;

        private GenreJob(Intent intent, Uri genreContentUri) {
            if (!MediaProvider.Genres.isGenre(genreContentUri)) {
                throw new IllegalArgumentException("Not a genre content URI");
            }
            this.mGenreContentUri = genreContentUri;
            this.mIntent = intent;
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Genre genre = AmazonApplication.getLibraryItemFactory().getGenre(this.mGenreContentUri);
            this.mGenre = genre;
            this.mIntent.putExtra("com.amazon.mp3.library.activity.EXTRA_GENRE_COUNT", GenreListAdapter.formatCountText(RecentlyPlayedFragment.this.getActivity(), (int) genre.getAlbumCount(), (int) this.mGenre.getTrackCount()));
            this.mIntent.putExtra("com.amazon.mp3.library.activity.EXTRA_GENRE_NAME", this.mGenre.getName());
            return 1;
        }
    }

    private class RecentlyPlayedContextMenuListener implements View.OnCreateContextMenuListener {
        private RecentlyPlayedContextMenuListener() {
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            RecentlyPlayedItem recentlyPlayedItem;
            RecentlyPlayedItem.Type itemType;
            if (!(menuInfo instanceof AdapterView.AdapterContextMenuInfo) || (itemType = (recentlyPlayedItem = (RecentlyPlayedItem) RecentlyPlayedFragment.this.mAdapter.getItem(((AdapterView.AdapterContextMenuInfo) menuInfo).position)).getItemType()) == RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS || itemType == RecentlyPlayedItem.Type.ALEXA_RECENTS || itemType == RecentlyPlayedItem.Type.CASTING_RECENTS || "vnd.android.cursor.item/vnd.amazonmp3.track".equals(DefaultUriMatcher.getType(recentlyPlayedItem.getContentUri()))) {
                return;
            }
            RecentlyPlayedFragment.this.mSelectedContextMenuItem = recentlyPlayedItem;
            RecentlyPlayedFragment.this.getActivity().getMenuInflater().inflate(R.menu.recently_played_item_menu, menu);
            BauhausTextStyler bauhausTextStyler = new BauhausTextStyler();
            menu.setHeaderTitle(bauhausTextStyler.styleText(recentlyPlayedItem.getTitle()));
            if (RecentlyPlayedFragment.this.shouldShowDownloadMenuItem(recentlyPlayedItem)) {
                if (!RecentlyPlayedFragment.this.shouldEnableDownloadMenuItem(recentlyPlayedItem.getContentUri())) {
                    menu.findItem(R.id.MenuDownload).setEnabled(false);
                }
            } else {
                menu.findItem(R.id.MenuDownload).setVisible(false);
            }
            if (!recentlyPlayedItem.shouldShowShareMenuItem()) {
                menu.findItem(R.id.MenuShare).setVisible(false);
            } else {
                recentlyPlayedItem.updateShareMenuItemTitle(menu);
            }
            if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.STATION) {
                menu.findItem(R.id.MenuViewDetails).setVisible(false);
            }
            bauhausTextStyler.styleMenu(menu);
        }
    }

    private class RecentlyPlayedReceiver implements RecentlyPlayedProvider.Receiver {
        private RecentlyPlayedReceiver() {
        }

        @Override // com.amazon.mp3.api.data.DataReceiver
        public Context getContext() {
            return RecentlyPlayedFragment.this.getActivity();
        }

        @Override // com.amazon.mp3.api.data.DataReceiver
        public LoaderManager getLoaderManager() {
            return RecentlyPlayedFragment.this.getLoaderManager();
        }

        @Override // com.amazon.mp3.recentlyplayed.provider.RecentlyPlayedProvider.Receiver
        public void onRecentlyPlayedLoaded(RecentlyPlayedRequestInfo data) {
            RecentlyPlayedFragment.this.setItems(data.getRecentlyPlayed());
        }
    }

    private static class QueryDownloadStateJob extends Job {
        private Collection<Uri> mContentUris;
        private Map<Uri, Integer> mDownloadStateMap = new HashMap();

        public QueryDownloadStateJob(Collection<Uri> contentUris) {
            this.mContentUris = contentUris;
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Cursor cursorQuery;
            String[] strArr = {"download_state"};
            for (Uri uri : this.mContentUris) {
                if (MediaProvider.PrimePlaylists.isPrimePlaylist(uri)) {
                    CatalogPlaylist primePlaylist = AmazonApplication.getLibraryItemFactory().getPrimePlaylist(uri);
                    if (primePlaylist != null) {
                        this.mDownloadStateMap.put(uri, Integer.valueOf(primePlaylist.getDownloadState()));
                    }
                } else if (!MediaProvider.PrimeBrowseTracksCollection.isCollection(uri.buildUpon().appendPath("tracks").build()) && !MediaProvider.Station.isStation(uri) && !MediaProvider.AlexaRecentlyPlayed.isAlexaRecentlyPlayedCollection(uri) && !MediaProvider.CastingRecentlyPlayed.isCastingRecentlyPlayedCollection(uri) && (cursorQuery = getContext().getContentResolver().query(uri, strArr, "ownership_status < 300", null, null)) != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("download_state"));
                            this.mDownloadStateMap.put(uri, Integer.valueOf(i));
                            Log.debug(RecentlyPlayedFragment.TAG, "Retrieved download state for uri = %s, download state = %d", uri, Integer.valueOf(i));
                        }
                    } finally {
                        DbUtil.closeCursor(cursorQuery);
                    }
                }
            }
            return 0;
        }

        public Map<Uri, Integer> getDownloadStates() {
            return this.mDownloadStateMap;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri convertToGroupCollectionUri(String uriString) {
        return MediaProvider.Tracks.getNonTrackCollectionUri(Uri.parse(uriString));
    }
}
