package com.amazon.mp3.library.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.actionbar.view.PlayerControlsView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.DeluxeContentLauncherActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.download.DownloadProgressListener;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.MusicDownloaderKt;
import com.amazon.mp3.download.OfflineTrackCorruptedListener;
import com.amazon.mp3.download.generator.GroupGenerator;
import com.amazon.mp3.elf.ExplicitLanguageUtil;
import com.amazon.mp3.eux.EUXProvider;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.EmptyFilterAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.db.CursorSelector;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.job.AddPrimeTrackJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueHelper;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.externalstorage.DeviceStorageConfiguration;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.widget.eux.EUXConfiguration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes3.dex */
public class TrackListFragment extends ArtListFragment {
    private static final String TAG = TrackListFragment.class.getSimpleName();
    private TrackListAdapter mAdapter;
    private CacheManager mArtCache;
    private int mContextMenuFlags;
    private TrackContextMenuProvider mContextMenuProvider;
    protected Uri mGroupTracksUri;
    protected boolean mIsAvailable;
    protected boolean mIsRemote;
    private boolean mIsShuffleAllButtonEnabled;
    private TrackListAdapter.RowViewHolder mListViewClickHolder;
    private int mListViewClickPosition;
    private Uri mListViewUri;
    protected PlayerControlsView mPlayerControlsView;
    private boolean mSavedIsShuffled;
    private boolean mShuffleAll;
    protected View mShuffleContainer;
    private Random mShuffleRandom = new Random();
    public boolean mShowCompressedView = true;
    public boolean mIsShowAllTracksButtonAdded = false;
    protected boolean mShouldBlockPlaybackAndDownload = false;
    protected String mScrollLuid = null;
    private int mGroupDownloadState = 5;
    private final TPATrackPlayReceiver mTPATrackPlayReceiver = new TPATrackPlayReceiver();
    protected Set<Long> mAddPrimeTrackJobIds = new HashSet();
    private Hashtable<Long, DownloadInfo> mAddPrimeTrackThenDownloadJobIds = new Hashtable<>();
    protected CompositeSubscription mEXUSubscription = new CompositeSubscription();
    protected TrackContextMenuProvider.TrackContextMenuProviderListener mProviderListener = new TrackContextMenuProvider.TrackContextMenuProviderListener() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.1
        @Override // com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
        public void onRemoveContentConfirm() {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public void removeTrackFromPlayQueue(int trackPos, boolean isShuffled) {
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public boolean shouldRemoveTrackDownloadItem() {
            return false;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public boolean shouldRemoveVideoPlayItem(Boolean isVideoTrack) {
            return true;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public void removeTrackFromPlaylist(Uri trackUri) {
            TrackListFragment.this.removeFromPlaylist(trackUri);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public void removeFromPlaylistMenuItem(ContextMenu menu) {
            TrackListFragment.this.removeItemRemoveFromPlaylist(menu);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.OnAddLibraryContentListener
        public void onAddContentComplete(@NonNull Uri uri) {
            TrackListFragment.this.onAddPrimeTrackFinished();
        }

        @Override // com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
        public void onRemoveContentComplete(Uri uri) {
            TrackListFragment.this.onDeletePrimeTrackFinished();
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public boolean shouldRemoveAlbumDetailItem() {
            return (TrackListFragment.this.mContextMenuFlags & 1) != 0;
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public boolean shouldRemoveAddToPlaylist() {
            return TrackListFragment.this.shouldRemoveAddToPlaylistButton();
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public boolean isNotAddedPrime(int position, AbstractItem libraryItem) {
            return TrackListFragment.this.isPrimeNotAdded(position);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public void addPrimeTrack(String asin, String luid) {
            TrackListFragment trackListFragment = TrackListFragment.this;
            trackListFragment.mAddPrimeTrackJobIds.add(Long.valueOf(trackListFragment.addJob(new AddPrimeTrackJob(trackListFragment.getActivity(), asin))));
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public void addAndDownloadPrimeTrack(int position, Uri uri, AbstractItem libraryItem) {
            TrackListFragment.this.addPrimeTrackToLibraryForDownloadByPosition(position, uri, libraryItem);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public void removeFromPlayQueueMenuItem(ContextMenu menu) {
            TrackListFragment.this.removeItemRemoveFromPlayQueue(menu);
        }

        @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
        public ContextMenuPageType getContextMenuPageType() {
            return ContextMenuPageType.DETAIL;
        }
    };
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.2
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
                TrackListFragment.this.refreshView();
            }
        }
    };
    protected final PlayerControlsView.TrackInformationFetcher mFetcher = new PlayerControlsView.TrackInformationFetcher() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.5
        @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.TrackInformationFetcher
        public int getTrackPositionForPlayback(final boolean shuffled) {
            return TrackListFragment.this.selectTrackPosition(shuffled);
        }

        @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.TrackInformationFetcher
        public MusicTrack getTrack(final int position) {
            return TrackListFragment.this.pickTrackItem(position);
        }

        @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.TrackInformationFetcher
        public List<MusicTrack> getAllTracks() {
            return TrackListFragment.this.mAdapter.getAllTracks();
        }

        @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.TrackInformationFetcher
        public String getCollectionName() {
            return TrackListFragment.this.getCollectionName();
        }
    };
    protected final PlayerControlsView.SelectionProvider mSelectionProvider = new PlayerControlsView.SelectionProvider() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.6
        @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.SelectionProvider
        public String getSelectionString() {
            return TrackListFragment.this.getSelection();
        }

        @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.SelectionProvider
        public String[] getSelectionArgsArray() {
            return TrackListFragment.this.getSelectionArgs();
        }
    };
    protected View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.10
        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            if (menuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                TrackListFragment trackListFragment = TrackListFragment.this;
                trackListFragment.mContextMenuId = adapterContextMenuInfo.id;
                trackListFragment.mContextMenuProvider.setPageType(TrackListFragment.this.getPageType());
                if (TrackListFragment.this.shouldDisplayContextMenuForPosition(adapterContextMenuInfo.position)) {
                    TrackListFragment.this.onCreateListViewContextMenu(menu, v, adapterContextMenuInfo);
                }
                TrackListFragment.this.sendUiClickMetric(ActionType.SHOW_OVERFLOW);
            }
        }
    };
    protected TrackListAdapter.TrackRowDownloadClickListener mTrackRowDownloadClickListener = new TrackListAdapter.TrackRowDownloadClickListener() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.13
        @Override // com.amazon.mp3.library.adapter.TrackListAdapter.TrackRowDownloadClickListener
        public void onDownloadingRowClick(TrackListAdapter.RowViewHolder holder) {
            MusicDownloader.getInstance(TrackListFragment.this.getActivity()).cancel(holder.mDownloadId);
        }

        @Override // com.amazon.mp3.library.adapter.TrackListAdapter.TrackRowDownloadClickListener
        public void onFailedRowClick(TrackListAdapter.RowViewHolder holder) {
            MusicDownloader.showRetryDialog(TrackListFragment.this.getActivity(), holder.mTrackUri, holder.mDownloadId);
        }
    };
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.14
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            boolean zShuffleStateUpdated = TrackListFragment.this.shuffleStateUpdated();
            boolean zPlayStateUpdated = TrackListFragment.this.playStateUpdated();
            boolean zMediaCollectionUpdated = TrackListFragment.this.mediaCollectionUpdated();
            if ((zShuffleStateUpdated || zPlayStateUpdated || zMediaCollectionUpdated) && TrackListFragment.this.mAdapter != null) {
                TrackListFragment.this.mAdapter.notifyDataSetChanged();
            }
        }
    };
    private DownloadProgressListener mGroupDownloadListener = new DownloadProgressListener(new HashSet()) { // from class: com.amazon.mp3.library.fragment.TrackListFragment.18
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Item item, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Group group, final float percentageDownloaded) {
            ((BaseFragment) TrackListFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.18.1
                @Override // java.lang.Runnable
                public void run() {
                    TrackListFragment.this.onGroupProgressUpdated((int) percentageDownloaded);
                }
            });
        }
    };
    private OfflineTrackCorruptedListener mOfflineTrackCorruptedListener = new OfflineTrackCorruptedListener() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.19
        @Override // com.amazon.mp3.download.OfflineTrackCorruptedListener
        public void onOfflineTrackCorrupted(final String asin) {
            TrackListFragment.this.requeryCursorInBackground();
        }
    };

    protected boolean computeGroupDownloadStateWithNonLibTracks() {
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected String getArtColumnName() {
        return "album_id";
    }

    protected String getCollectionName() {
        return "";
    }

    protected View getEmptyView(EUXConfiguration euxConfiguration) {
        return null;
    }

    protected void handleDownloadedBtnClick() {
    }

    protected void onAddPrimeTrackFinished() {
    }

    protected void onDeletePrimeTrackFinished() {
    }

    protected void onGroupDownloadStateUpdated() {
    }

    protected void onGroupProgressUpdated(int progress) {
    }

    protected void removeFromPlaylist(Uri trackUri) {
    }

    protected boolean shouldRemoveAddToPlaylistButton() {
        return false;
    }

    protected void showRedownloadIcon() {
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
        this.mSavedIsShuffled = this.mPlaybackController.isShuffled();
        this.mPlaybackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        MusicDownloaderKt.INSTANCE.registerOfflineTrackCorruptedListener(this.mOfflineTrackCorruptedListener);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        this.mIsShuffleAllButtonEnabled = arguments.getBoolean("com.amazon.mp3.library.EXTRA_SHOW_SHUFFLE", false);
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        if (!TextUtils.isEmpty(arguments.getString("com.amazon.mp3.library.activity.EXTRA_TRACK_LUID"))) {
            this.mScrollLuid = getArguments().getString("com.amazon.mp3.library.activity.EXTRA_TRACK_LUID");
        }
        init();
        if (this.mIsShuffleAllButtonEnabled) {
            this.mShuffleContainer = View.inflate(getActivity(), R.layout.library_track_shuffle_row, null);
            ((ListView) getListView()).addHeaderView(this.mShuffleContainer);
            this.mShuffleContainer.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(final View v) {
                    boolean zIsSourceLocal = TrackListFragment.this.isSourceLocal();
                    int iSelectTrackPosition = TrackListFragment.this.selectTrackPosition(true);
                    if (!zIsSourceLocal && UserSubscriptionUtil.isNightwingOnly()) {
                        UpsellUtil.showBlockingUpsell(TrackListFragment.this.getActivity(), TrackListFragment.this.pickTrackItem(iSelectTrackPosition).getAsin(), UpsellReason.SHUFFLE_ALL, UpsellSourceEntity.TRACK);
                        return;
                    }
                    TrackListFragment.this.sendUiClickMetric(ActionType.PLAY_SHUFFLE_ALL);
                    PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, TrackListFragment.this.getPlaybackPageType());
                    TrackListFragment trackListFragment = TrackListFragment.this;
                    trackListFragment.initiatePlayback(trackListFragment.pickTrackItem(iSelectTrackPosition), iSelectTrackPosition, true, playbackMetricInformation, true);
                }
            });
        }
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mAdapter.onPauseOrDestroy(getActivity());
        this.mScrollLuid = null;
        AddRemovePrimeManager addRemovePrimeManager = AddRemovePrimeManager.getInstance(getActivity());
        if (!addRemovePrimeManager.isAddQueueEmpty() || !addRemovePrimeManager.isRemoveQueueEmpty()) {
            SyncService.startSync(getActivity(), 1042);
        }
        MusicDownloader.getInstance(getApplication()).unregisterDownloadListener(this.mGroupDownloadListener);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        registerReceiver(this.mTPATrackPlayReceiver, new IntentFilter("com.amazon.mp3.ACTION_TPA_PLAY_TRACK"));
        this.mAdapter.onResume(getActivity());
        notifyDataSetChanged();
        MusicDownloader.getInstance(getApplication()).registerDownloadListener(this.mGroupDownloadListener);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mShowCompressedView = savedInstanceState.getBoolean("com.amazon.mp3.SHOW_COMPRESSED_VIEW");
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("com.amazon.mp3.SHOW_COMPRESSED_VIEW", this.mShowCompressedView);
    }

    protected void isRedownloadRequired() {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.TrackListFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$isRedownloadRequired$0((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber) new Subscriber<Boolean>() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.4
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                Log.error(TrackListFragment.TAG, "Failed to get the redownload status: " + throwable.getMessage());
            }

            @Override // rx.Observer
            public void onNext(final Boolean showRedownloadIcon) {
                if (showRedownloadIcon.booleanValue()) {
                    TrackListFragment.this.showRedownloadIcon();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$isRedownloadRequired$0(Subscriber subscriber) {
        boolean z = false;
        Group groupGenerate = new GroupGenerator(getContext(), getContentUri(), null, null, null, getStorageLocationFileManager()).generate(false, new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), null);
        if (groupGenerate != null && groupGenerate.size() > 0) {
            z = true;
        }
        subscriber.onNext(Boolean.valueOf(z));
        subscriber.onCompleted();
    }

    protected boolean isAlwaysShowNonLibContent() {
        return getArguments().getBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", false);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        TrackListAdapter trackListAdapter = this.mAdapter;
        if (trackListAdapter != null) {
            if (isVisibleToUser) {
                trackListAdapter.onResume(getActivity());
            } else {
                trackListAdapter.onPauseOrDestroy(getActivity());
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
        LibraryFilters.remove(getFilterId());
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mArtCache = null;
        unregisterReceiver(this.mTPATrackPlayReceiver);
        TrackListAdapter trackListAdapter = this.mAdapter;
        if (trackListAdapter != null) {
            trackListAdapter.onPauseOrDestroy(getActivity());
        }
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        MusicDownloaderKt.INSTANCE.unregisterOfflineTrackCorruptedListener(this.mOfflineTrackCorruptedListener);
        super.onDestroy();
        CompositeSubscription compositeSubscription = this.mEXUSubscription;
        if (compositeSubscription == null || compositeSubscription.isUnsubscribed()) {
            return;
        }
        this.mEXUSubscription.unsubscribe();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldPrefetchArt() {
        TrackListAdapter trackListAdapter = this.mAdapter;
        if (trackListAdapter == null || trackListAdapter.isEmpty()) {
            return false;
        }
        return super.shouldPrefetchArt();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        super.onSourceToggled(source);
        String str = source == ActionBarSourceToggleView.Source.CLOUD ? "cirrus" : "cirrus-local";
        Uri filterContentUri = MediaProvider.Tracks.getFilterContentUri(str, "");
        resetContent(filterContentUri, str);
        this.mIsRemote = CirrusMediaSource.matchCloud(filterContentUri);
        this.mAdapter.setContentUri(filterContentUri);
        changeCursor(null);
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected ContextMenuProvider<TrackListAdapter.RowViewHolder> getContextMenuProvider() {
        return this.mContextMenuProvider;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            MetricsLogger.deluxeVideoStreamingTerminated(data.getStringExtra(DeluxeContentUtil.EXTRA_ASIN), data.getStringExtra(DeluxeContentUtil.EXTRA_LUID));
        } else if (requestCode == 2) {
            MetricsLogger.deluxeBookletReadingCompleted(data.getStringExtra(DeluxeContentUtil.EXTRA_ASIN), data.getStringExtra(DeluxeContentUtil.EXTRA_LUID));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onCreateListViewContextMenu(ContextMenu menu, View unused, AdapterView.AdapterContextMenuInfo menuInfo) {
        if (menuInfo == null) {
            return;
        }
        Cursor cursor = getCursor();
        int headerViewsCount = menuInfo.position - getHeaderViewsCount();
        cursor.moveToPosition(headerViewsCount);
        getContextMenuProvider().onCreateContextMenu(getActivity(), menu, menuInfo.targetView.getTag(), headerViewsCount, getSourceId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int selectTrackPosition(boolean shuffled) {
        Cursor cursor;
        int count;
        TrackListAdapter trackListAdapter = this.mAdapter;
        if (trackListAdapter == null || (cursor = trackListAdapter.getCursor()) == null || (count = cursor.getCount()) == 0) {
            return -1;
        }
        if (shuffled) {
            return this.mShuffleRandom.nextInt(count);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MusicTrack pickTrackItem(int position) {
        Cursor cursor;
        TrackListAdapter trackListAdapter = this.mAdapter;
        if (trackListAdapter == null || (cursor = trackListAdapter.getCursor()) == null || cursor.getCount() == 0) {
            return null;
        }
        int position2 = cursor.getPosition();
        cursor.moveToPosition(position);
        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursor);
        cursor.moveToPosition(position2);
        return track;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onContentUriChanged() {
        TrackListAdapter trackListAdapter = this.mAdapter;
        if (trackListAdapter != null) {
            trackListAdapter.setContentUri(getContentUri());
        }
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setContentUri(getContentUri());
        }
        requeryCursorInBackground();
        setUpGroupUri();
        super.onContentUriChanged();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onAvailableItemClick(AbsListView absListView, View view, int position, long id) {
        this.mListViewClickHolder = (TrackListAdapter.RowViewHolder) view.getTag();
        this.mListViewClickPosition = position - getHeaderViewsCount();
        sendUiClickMetric(ActionType.PLAY_SONG, this.mListViewClickHolder.mLibraryItem.getAsin(), this.mListViewClickHolder.mLibraryItem.getId(), position);
        TrackListAdapter.RowViewHolder rowViewHolder = this.mListViewClickHolder;
        if (rowViewHolder == null || rowViewHolder.mTrackUri == null) {
            return;
        }
        initiatePlayback((MusicTrack) this.mListViewClickHolder.mLibraryItem, this.mListViewClickPosition, isShuffleEnabled(), new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, getPlaybackPageType()), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPrimeNotAdded(int position) {
        if (!this.mAdapter.isCursorReady()) {
            throw new IllegalStateException("The cursor is not ready");
        }
        Cursor cursor = getCursor();
        if (cursor == null) {
            throw new IllegalStateException("The cursor is null");
        }
        if (cursor.moveToPosition(position)) {
            return getContentCatalogStatus(cursor).isHawkfire() && !getContentOwnershipStatus(cursor).isInLibrary();
        }
        throw new IllegalStateException("Unable to move to posiiton: " + position);
    }

    private ContentOwnershipStatus getContentOwnershipStatus(final Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("ownership_status");
        if (columnIndex >= cursor.getColumnCount()) {
            throw new IllegalStateException("The ownershipIndex is out of cursor columns bounds: " + columnIndex);
        }
        return ContentOwnershipStatus.fromValue(cursor.getInt(columnIndex));
    }

    private ContentCatalogStatus getContentCatalogStatus(final Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("prime_status");
        if (columnIndex >= cursor.getColumnCount()) {
            throw new IllegalStateException("The catalogStatusIndex is out of cursor columns bounds: " + columnIndex);
        }
        return ContentCatalogStatus.fromValue(cursor.getInt(columnIndex));
    }

    protected void addPrimeTrackToLibraryForDownloadByPosition(int position, Uri uri, AbstractItem libraryItem) {
        Cursor cursor = getCursor();
        if (cursor == null || !cursor.moveToPosition(position)) {
            return;
        }
        addPrimeTrackToLibraryThenDownload(cursor.getString(cursor.getColumnIndex("asin")), cursor.getString(cursor.getColumnIndex("artist")), cursor.getString(cursor.getColumnIndex("title")), uri, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), IdGenerator.generateAlbumId(cursor.getString(cursor.getColumnIndex("album_asin")), cursor.getString(cursor.getColumnIndex("album")))));
    }

    protected void addPrimeTrackToLibraryThenDownload(String asin, String trackArtist, String trackTitle, Uri contentUri, Uri imageUri) {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
            return;
        }
        this.mAddPrimeTrackThenDownloadJobIds.put(Long.valueOf(createPrimeTrackAddJob(asin)), new DownloadInfo(asin, contentUri, showingPrimeNonAddedTracks(), new NotificationInfo(trackTitle, trackArtist, imageUri.toString())));
    }

    protected void onAddPrimeTrackThenDownloadJobFinished(int resultCode, DownloadInfo info) {
        if (onAddPrimeTrackJobFinished(resultCode, false)) {
            startDownload(info.getRequestId(), info.getContentUri(), info.isDownloadNonAddedPrimeTracks(), info.getNotificationInfo());
        }
    }

    protected boolean onAddPrimeTrackJobFinished(int resultCode, boolean showToast) {
        if (resultCode == 1) {
            if (showToast) {
                BauhausToastUtils.showTextToast(getActivity(), getResources().getString(R.string.dmusic_prime_song_added_to_library), 0);
            }
            requeryCursorInBackground();
            return true;
        }
        Log.verbose(TAG, "Failed to add prime track");
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        if (this.mAddPrimeTrackJobIds.contains(Long.valueOf(jobId))) {
            this.mAddPrimeTrackJobIds.remove(Long.valueOf(jobId));
            onAddPrimeTrackJobFinished(resultCode, true);
        } else if (this.mAddPrimeTrackThenDownloadJobIds.containsKey(Long.valueOf(jobId))) {
            DownloadInfo downloadInfo = this.mAddPrimeTrackThenDownloadJobIds.get(Long.valueOf(jobId));
            this.mAddPrimeTrackThenDownloadJobIds.remove(Long.valueOf(jobId));
            onAddPrimeTrackThenDownloadJobFinished(resultCode, downloadInfo);
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    protected long createPrimeTrackAddJob(String asin) {
        Log.verbose(TAG, "Add prime track with asin: " + asin);
        return addJob(new AddPrimeTrackJob(getActivity(), asin));
    }

    protected class HandleDeeplinkTask extends AsyncTask<Void, Void, Integer> {
        private final FindUriByAsinJob.QueryByAsin mQueryByAsin;

        protected HandleDeeplinkTask(FindUriByAsinJob.QueryByAsin queryByAsin) {
            this.mQueryByAsin = queryByAsin;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(final Void... params) {
            String string = TrackListFragment.this.getArguments().getString("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN");
            if (string == null) {
                return 0;
            }
            return Integer.valueOf(this.mQueryByAsin.findTrackIndexInCollectionByAsin(TrackListFragment.this.getContentUri(), string));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Integer trackPosition) {
            MusicTrack track;
            if (trackPosition.intValue() < 0) {
                return;
            }
            Bundle arguments = TrackListFragment.this.getArguments();
            String string = arguments.getString("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN");
            arguments.remove("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN");
            TrackListFragment.this.highlightAndScrollTo(string, trackPosition.intValue());
            if (((Action) arguments.getSerializable("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION")) == null || (track = TrackListFragment.this.mFetcher.getTrack(trackPosition.intValue())) == null) {
                return;
            }
            TrackListFragment.this.setClickedItem(track);
            boolean zShouldDisplayNowPlayingScreen = shouldDisplayNowPlayingScreen();
            if (track.isAvailable()) {
                TrackListFragment.this.initiatePlayback(track, trackPosition.intValue(), false, new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, TrackListFragment.this.getPlaybackPageType()), zShouldDisplayNowPlayingScreen);
            } else {
                TrackListFragment.this.handleDisabledContentError(ContentAccessUtil.getPlayCatalogContentUnavailableReason(track), track);
            }
        }

        private boolean shouldDisplayNowPlayingScreen() {
            Bundle arguments = TrackListFragment.this.getArguments();
            Action action = (Action) arguments.getSerializable("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION");
            arguments.remove("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION");
            return action != Action.PLAY;
        }
    }

    public void highlightAndScrollTo(final String trackAsin, final int trackPosition) {
        if (trackAsin == null) {
            return;
        }
        this.mAdapter.setTrackAsinHighlight(trackAsin);
        getListView().post(new Runnable() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.7
            @Override // java.lang.Runnable
            public void run() {
                TrackListFragment.this.getListView().smoothScrollToPositionFromTop(trackPosition, 0);
            }
        });
    }

    public void sendUiClickMetric(final ActionType actionType) {
        sendUiClickMetric(actionType, (String) null, (String) null, -1);
    }

    public void sendUiClickMetric(final ActionType actionType, final String asin, final String id, final int position) {
        EntityIdType entityIdType;
        if (asin != null) {
            entityIdType = EntityIdType.ASIN;
        } else {
            entityIdType = EntityIdType.CD_OBJECT_ID;
            asin = id;
        }
        sendUiClickMetric(actionType, asin, entityIdType, Integer.valueOf(position));
    }

    public void sendUiClickMetric(final ActionType actionType, final String entityId, final EntityIdType entityIdType, final Integer position) {
        UiClickEvent.Builder builderWithEntityIdType = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEventTime(System.currentTimeMillis()).withEntityId(entityId).withEntityIdType(entityIdType);
        if (position != null) {
            builderWithEntityIdType.withEntityPos(position.intValue());
        }
        MetricsHolder.getManager().handleEvent(builderWithEntityIdType.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void initiatePlayback(final MusicTrack track, final int position, final boolean shuffleAll, final PlaybackMetricInformation metricInformation, final boolean displayNowPlayingScreen) {
        Uri contentUri = getContentUri();
        this.mListViewUri = contentUri;
        this.mListViewClickPosition = position;
        this.mShuffleAll = shuffleAll;
        if (CirrusMediaSource.matchCloud(contentUri)) {
            String extension = track.getExtension();
            if (DeluxeContentUtil.isDeluxeContent(extension)) {
                handleDeluxeContent(track, extension);
                return;
            }
        }
        boolean z = this.mAdapter.getCount() >= 1000;
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (!z && AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            StartPlaybackUtil.startCollectionPlayback(getActivity(), getCollectionName(), this.mListViewUri, this.mAdapter.getAllTracks(), position, shuffleAll, displayNowPlayingScreen, null, metricInformation.getPageType(), false, ControlSource.APP_UI, null, false, null, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.8
                @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                public void handlePlayback() {
                    TrackListFragment trackListFragment = TrackListFragment.this;
                    trackListFragment.startPlayback(position, trackListFragment.mListViewUri, shuffleAll, track, metricInformation, displayNowPlayingScreen);
                }
            });
        } else {
            startPlayback(position, this.mListViewUri, shuffleAll, track, metricInformation, displayNowPlayingScreen);
        }
    }

    private void handleDeluxeContent(MusicTrack track, String extension) {
        Intent intent = new Intent(AmazonApplication.getContext(), (Class<?>) DeluxeContentLauncherActivity.class);
        Uri contentUri = track.getContentUri();
        if (contentUri == null) {
            Log.warning(TAG, "Received invalid track uri for deluxe content.");
            return;
        }
        intent.putExtra(DeluxeContentUtil.EXTRA_URI_KEY, contentUri.toString());
        int i = AnonymousClass21.$SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[DeluxeContentUtil.getMediaType(extension).ordinal()];
        if (i == 1) {
            intent.putExtra(DeluxeContentUtil.EXTRA_REQUEST_KEY, DeluxeContentUtil.ContentType.VIDEO);
            startActivityForResult(intent, 1);
        } else {
            if (i != 2) {
                return;
            }
            intent.putExtra(DeluxeContentUtil.EXTRA_TITLE_KEY, track.getTitle());
            intent.putExtra(DeluxeContentUtil.EXTRA_EXTENSION_KEY, track.getExtension());
            intent.putExtra(DeluxeContentUtil.EXTRA_REQUEST_KEY, DeluxeContentUtil.ContentType.DOCUMENT);
            startActivityForResult(intent, 2);
        }
    }

    protected String getSortOrder() {
        return CirrusMediaSource.getTrackSortOrder();
    }

    protected String[] getProjection() {
        return TrackListAdapter.getDefaultProjection();
    }

    protected String getSelection() {
        if (isSourceLocal()) {
            return null;
        }
        return TrackListAdapter.getDefaultSelection();
    }

    protected String[] getSelectionArgs() {
        if (isSourceLocal()) {
            return null;
        }
        return TrackListAdapter.getDefaultSelectionArgs();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected CacheManager getAlbumArtCache() {
        if (this.mArtCache == null) {
            this.mArtCache = CacheManager.getInstance();
        }
        return this.mArtCache;
    }

    protected TrackListAdapter createTrackListAdapter() {
        return new TrackListAdapter(getActivity(), getContentUri(), this, Branding.shouldShowPrimeBranding());
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getEmptyFilterListAdapter() {
        return new EmptyFilterAdapter(getActivity(), getCurrentFilter(), R.string.dmusic_library_empty_filter_no_tracks_found);
    }

    private void init() {
        setHeaderTitle(getString(R.string.dmusic_library_songs_tab));
        this.mContextMenuFlags = getArguments().getInt("com.amazon.mp3.library.EXTRA_CONTEXT_MENU_FLAGS", 0);
        if (this.mAdapter == null) {
            this.mAdapter = createTrackListAdapter();
        }
        this.mAdapter.setTrackRowDownloadClickListener(this.mTrackRowDownloadClickListener);
        this.mAdapter.initNightwingSettings();
        this.mContextMenuProvider = new TrackContextMenuProvider((BaseActivity) getActivity(), getHeaderViewsCount(), this.mProviderListener);
        getListView().setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        this.mIsRemote = CirrusMediaSource.matchCloud(getContentUri());
        this.mIsShowAllTracksButtonAdded = false;
        setUpGroupUri();
    }

    protected StorageLocationFileManager getStorageLocationFileManager() {
        return StorageLocationFileManager.create(getContext(), new DeviceStorageConfiguration(getContext(), Environment.DIRECTORY_MUSIC), Environment.DIRECTORY_MUSIC);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        if (this.mFromTabHost) {
            return;
        }
        super.initListViewHeaderForPortrait();
    }

    private void setUpGroupUri() {
        Uri contentUri = getContentUri();
        this.mGroupTracksUri = contentUri;
        if (!MediaProvider.Tracks.isTrackCollection(contentUri, new String[0])) {
            this.mGroupTracksUri = this.mGroupTracksUri.buildUpon().appendEncodedPath("tracks").build();
        }
        this.mGroupDownloadListener.addRequestId(this.mGroupTracksUri.toString());
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected Cursor query() {
        Cursor properCursor = CursorSelector.getProperCursor(CursorListFragment.createCursor(getActivity(), getContentUri(), getProjection(), getSelection(), getSelectionArgs(), getSortOrder()));
        if (this.mScrollLuid != null) {
            int position = properCursor.getPosition();
            int columnIndex = properCursor.getColumnIndex("luid");
            final int i = 0;
            if (columnIndex != -1 && properCursor.moveToFirst()) {
                while (true) {
                    if (this.mScrollLuid.equals(properCursor.getString(columnIndex))) {
                        this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.9
                            @Override // java.lang.Runnable
                            public void run() {
                                TrackListFragment.this.getListView().setSelection(i);
                            }
                        }, 250L);
                        break;
                    }
                    i++;
                    if (!properCursor.moveToNext()) {
                        break;
                    }
                }
                properCursor.moveToPosition(position);
            }
        }
        return properCursor;
    }

    protected void handleDownloadBtnClick(final boolean downloadNonAddedPrimeTracks, final NotificationInfo notificationInfo) {
        int i = this.mGroupDownloadState;
        if (i != -2) {
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        FragmentActivity activity = getActivity();
                        Uri uri = this.mGroupTracksUri;
                        MusicDownloader.showRetryDialog(activity, uri, uri.toString());
                        return;
                    } else if (i != 3 && i != 4) {
                        this.mGroupDownloadState = -2;
                        startGroupDownload(this.mGroupTracksUri.toString(), this.mGroupTracksUri, showingPrimeNonAddedTracks(), notificationInfo);
                        return;
                    }
                }
                MusicDownloader.getInstance(getActivity()).cancel(this.mGroupTracksUri.toString());
                return;
            }
            handleDownloadedBtnClick();
        }
    }

    protected void startGroupDownload(String requestId, Uri groupTracksUri, boolean showingPrimeNonAddedTracks, NotificationInfo notificationInfo) {
        startDownload(requestId, groupTracksUri, showingPrimeNonAddedTracks, notificationInfo);
    }

    protected void setGroupDownloadState(final int groupDownloadState) {
        this.mGroupDownloadState = groupDownloadState;
    }

    protected boolean showingPrimeNonAddedTracks() {
        Cursor cursor = getCursor();
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("prime_status");
            int columnIndex2 = cursor.getColumnIndex("ownership_status");
            do {
                ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(cursor.getInt(columnIndex));
                ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(cursor.getInt(columnIndex2));
                if (contentCatalogStatusFromValue.isHawkfire() && !contentOwnershipStatusFromValue.isInLibrary()) {
                    return true;
                }
            } while (cursor.moveToNext());
            return false;
        }
        Log.debug(TAG, "Unable to move cursor to first");
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        changeCursor(c);
        checkFastScrollerRequired(c, 50);
    }

    private boolean isShuffleEnabled() {
        return this.mPlaybackController.isShuffled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlayback(int listViewPosition, Uri contentUri, boolean shuffle, MusicTrack track, PlaybackMetricInformation metricInformation, boolean displayNowPlayingScreen) {
        long jNow = Clock.now();
        if (MediaItemHelper.isCurrentlyPlayingItem(track.getAsin())) {
            this.mPlaybackController.play();
        } else if (MediaItemHelper.isCurrentlyPlayingCollection(contentUri) && this.mPlaybackController.canSkipToIndex() && !new NowPlayingQueueHelper(getContext()).isUnlimitedCollection(contentUri)) {
            if (UserSubscriptionUtil.getUserSubscription().isPrimeOnly() && PlayQueueSequencer.getInstance().getCurrentMediaItem() != null && PlayQueueSequencer.getInstance().getCurrentMediaItem().getType() == MediaItem.Type.AD_CONTENT) {
                PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, contentUri, getSelection(), getSelectionArgs(), null, listViewPosition, shuffle, metricInformation, track, jNow);
            }
            if (this.mPlaybackController.isShuffled()) {
                this.mPlaybackController.setShuffled(false);
                this.mPlaybackController.skipToIndex(findIndexInCurrentQueue(track, listViewPosition));
                this.mPlaybackController.setShuffled(true);
            } else {
                this.mPlaybackController.skipToIndex(findIndexInCurrentQueue(track, listViewPosition));
            }
            this.mPlaybackController.play();
        } else {
            PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, contentUri, getSelection(), getSelectionArgs(), null, listViewPosition, shuffle, metricInformation, track, jNow);
        }
        if (displayNowPlayingScreen) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(getActivity(), MediaProvider.NowPlaying.CONTENT_URI);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_SORT_ORDER", getSortOrder());
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", contentUri);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_POSITION", listViewPosition);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_SHUFFLE", shuffle);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_PLAYING", true);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK", track);
            startActivity(intentForContentUri);
            getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
        }
    }

    private int findIndexInCurrentQueue(final MusicTrack selectedTrack, final int listViewPosition) {
        String title = selectedTrack.getTitle();
        Sequencer sequencer = ((SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider()).getSequencer();
        if (sequencer != null) {
            for (int i = 0; i < sequencer.getNumMediaItems(); i++) {
                if (sequencer.getMediaItem(i) != null && sequencer.getMediaItem(i).getName().equals(title)) {
                    return i;
                }
            }
        }
        return listViewPosition;
    }

    protected void removeItemRemoveFromPlaylist(Menu menu) {
        menu.removeItem(R.id.MenuTrackContextDeleteFromPlaylist);
    }

    protected void removeItemRemoveFromPlayQueue(Menu menu) {
        menu.removeItem(R.id.MenuTrackRemoveFromPlayQueue);
    }

    public int getGroupDownloadState() {
        return this.mGroupDownloadState;
    }

    public void refreshGroupDownloadState() {
        if (getView() == null || isSourceLocal()) {
            return;
        }
        updateGroupState();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorChanged(Cursor newCursor, Cursor oldCursor) {
        super.onCursorChanged(newCursor, oldCursor);
        if (newCursor == null || isSourceLocal() || getView() == null) {
            return;
        }
        updateGroupState();
    }

    private void updateGroupState() {
        if (this.mIsRemote) {
            final MusicDownloader musicDownloader = MusicDownloader.getInstance(getContext());
            musicDownloader.getRequestProvider().getRequests(new RequestProvider.RequestProviderCallback() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.11
                @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
                public void requestsSubmitted(final Set<RequestItem> requests) {
                    boolean z;
                    Iterator<RequestItem> it = requests.iterator();
                    while (true) {
                        z = true;
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else if (it.next().getRequestId().equals(TrackListFragment.this.mGroupTracksUri.toString())) {
                            Group group = musicDownloader.getGroup(TrackListFragment.this.mGroupTracksUri.toString());
                            if (group == null) {
                                TrackListFragment.this.mGroupDownloadState = 0;
                            } else {
                                TrackListFragment trackListFragment = TrackListFragment.this;
                                trackListFragment.mGroupDownloadState = trackListFragment.toAppDownloadState(group.getDownloadState());
                            }
                        }
                    }
                    if (!z) {
                        TrackListFragment trackListFragment2 = TrackListFragment.this;
                        if (trackListFragment2.queryIsTrackGroupDownloaded(trackListFragment2.getContext())) {
                            TrackListFragment.this.mGroupDownloadState = 0;
                        } else {
                            TrackListFragment.this.mGroupDownloadState = 5;
                        }
                    }
                    TrackListFragment.this.groupDownloadStateUpdated();
                }
            });
        }
    }

    protected boolean queryIsTrackGroupDownloaded(Context context) {
        String str = DeluxeContentUtil.getNonDeluxeSelection() + " AND prime_status<?";
        LinkedList linkedList = new LinkedList(Arrays.asList(DeluxeContentUtil.getNonDeluxeSelectionArgs()));
        linkedList.add(String.valueOf(400));
        if (isFreeTierUserPlaylistEditingEnabled()) {
            str = str + " AND ownership_status<?";
            linkedList.add(String.valueOf(200));
        } else if (!computeGroupDownloadStateWithNonLibTracks()) {
            str = str + " AND ownership_status<?";
            linkedList.add(String.valueOf(300));
        }
        Cursor cursorCreateCursor = CursorListFragment.createCursor(context, getContentUri(), new String[]{"download_state"}, str, (String[]) linkedList.toArray(new String[linkedList.size()]), null);
        int i = Integer.MIN_VALUE;
        while (cursorCreateCursor != null) {
            try {
                if (!cursorCreateCursor.moveToNext()) {
                    break;
                }
                int i2 = cursorCreateCursor.getInt(cursorCreateCursor.getColumnIndex("download_state"));
                if (i < i2) {
                    i = i2;
                }
            } finally {
                cursorCreateCursor.close();
            }
        }
        boolean z = i == 0;
        if (cursorCreateCursor != null) {
        }
        return z;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.TrackListFragment$21, reason: invalid class name */
    static /* synthetic */ class AnonymousClass21 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;

        static {
            int[] iArr = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr;
            try {
                iArr[DownloadState.QUEUED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_USER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_POLICY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.CANCELED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_PRIORITY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[DeluxeContentUtil.ContentType.values().length];
            $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType = iArr2;
            try {
                iArr2[DeluxeContentUtil.ContentType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[DeluxeContentUtil.ContentType.DOCUMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toAppDownloadState(DownloadState state) {
        switch (AnonymousClass21.$SwitchMap$com$amazon$music$downloads$DownloadState[state.ordinal()]) {
            case 1:
                return 1;
            case 2:
            case 3:
                return 3;
            case 4:
            default:
                return 5;
            case 5:
            case 6:
                return 4;
            case 7:
                return 0;
            case 8:
                return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void groupDownloadStateUpdated() {
        this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.12
            @Override // java.lang.Runnable
            public void run() {
                TrackListFragment.this.onGroupDownloadStateUpdated();
            }
        });
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onMediaScannerFinished() {
        super.onMediaScannerFinished();
        if (this.mIsRemote) {
            return;
        }
        requeryCursorInBackground();
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getDeleteContentMessage() {
        AbstractItem clickedItem = getClickedItem();
        return clickedItem.isAvailableOffline() ? R.string.dmusic_track_toast_deleting_track_device : (clickedItem.isNotOwned() && clickedItem.isInLibrary()) ? R.string.dmusic_track_toast_removing_track_cloud : R.string.dmusic_track_toast_deleting_track_cloud;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shuffleStateUpdated() {
        boolean z = this.mSavedIsShuffled != this.mPlaybackController.isShuffled();
        if (z) {
            requeryCursorInBackground();
        }
        this.mSavedIsShuffled = this.mPlaybackController.isShuffled();
        return z;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected String getHeaderText() {
        return getString(R.string.dmusic_library_songs_tab);
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.ALBUM;
    }

    private class TPATrackPlayReceiver extends BroadcastReceiver {
        private TPATrackPlayReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.amazon.mp3.ACTION_TPA_PLAY_TRACK".equals(intent.getAction())) {
                setSelectedRowPosition(intent.getIntExtra("com.amazon.mp3.EXTRAS_TPA_PLAY_TRACK_NUMBER", 0));
            }
        }

        private void setSelectedRowPosition(final int trackPosition) {
            ((BaseFragment) TrackListFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.TPATrackPlayReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    TrackListFragment.this.getListView().setSelection(trackPosition);
                    TPATrackPlayReceiver.this.invokeListItemClick(trackPosition);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invokeListItemClick(final int trackPosition) {
            ((BaseFragment) TrackListFragment.this).mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.TPATrackPlayReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    View childAt = TrackListFragment.this.getListView().getChildAt(trackPosition == 0 ? 0 : 1);
                    TrackListFragment trackListFragment = TrackListFragment.this;
                    trackListFragment.onItemClick(trackListFragment.getListView(), childAt, trackPosition, 0L);
                }
            }, 1000L);
        }
    }

    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.SONGS_LIST;
    }

    protected Observable<EUXConfiguration> getEXUConfigurationObservable() {
        return Observable.create(new Observable.OnSubscribe<EUXConfiguration>() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.15
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super EUXConfiguration> subscriber) {
                subscriber.onNext(EUXProvider.buildEUXConfiguration(TrackListFragment.this.getContext()));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    public void showEmptyUI() {
        if (this.mIsShuffleAllButtonEnabled) {
            this.mShuffleContainer.setVisibility(8);
        }
        this.mEXUSubscription.add(getEXUConfigurationObservable().subscribe(new Action1<EUXConfiguration>() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.16
            @Override // rx.functions.Action1
            public void call(final EUXConfiguration configuration) {
                View emptyView = TrackListFragment.this.getEmptyView(configuration);
                if (emptyView == null) {
                    TrackListFragment.super.showEmptyUI();
                } else {
                    TrackListFragment.this.showEmptyView(emptyView, false);
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.17
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.error(TrackListFragment.TAG, "error retreiving eux configuration", throwable);
                TrackListFragment.super.showEmptyUI();
            }
        }));
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void hideEmptyUI() {
        super.hideEmptyUI();
        if (this.mIsShuffleAllButtonEnabled) {
            this.mShuffleContainer.setVisibility(0);
        }
    }

    private static class DownloadInfo {
        final Uri mContentUri;
        final boolean mDownloadNonAddedPrimeTracks;
        final NotificationInfo mNotificationInfo;
        final String mRequestId;

        public DownloadInfo(String requestId, Uri contentUri, boolean downloadNonAddedPrimeTracks, NotificationInfo notificationInfo) {
            this.mRequestId = requestId;
            this.mContentUri = contentUri;
            this.mDownloadNonAddedPrimeTracks = downloadNonAddedPrimeTracks;
            this.mNotificationInfo = notificationInfo;
        }

        public String getRequestId() {
            return this.mRequestId;
        }

        public Uri getContentUri() {
            return this.mContentUri;
        }

        public boolean isDownloadNonAddedPrimeTracks() {
            return this.mDownloadNonAddedPrimeTracks;
        }

        public NotificationInfo getNotificationInfo() {
            return this.mNotificationInfo;
        }
    }

    @Deprecated
    public void sendUiPageViewMetric(final String identifier, final String itemIdentifier, final String itemIdentifierType) {
        sendUiPageViewMetric(identifier, itemIdentifier, itemIdentifierType, (String) null, (String) null);
    }

    @Deprecated
    public void sendUiPageViewMetric(final String identifier, final String itemIdentifier, final String itemIdentifierType, final String refMarker, final String associateTag) {
        NavigationAppEvent.builder(identifier).withOrientation(ScreenUtil.getScreenOrientation(getContext())).withItemIdentifier(itemIdentifier).withItemIdentifierType(itemIdentifierType).withRefMarker(refMarker).withAssociateTag(associateTag).publish();
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        if (this.mIsRemote) {
            return PageType.CLOUD_LIBRARY_SONGS;
        }
        return PageType.OFFLINE_LIBRARY_SONGS;
    }

    protected void updatePlayerControlsViewState() {
        updatePlayerControlsViewState(((TrackListAdapter) getAdapter()).getCursor(), this.mIsAvailable);
    }

    protected void updatePlayerControlsViewState(final Cursor cursor, final boolean isAvailable) {
        if (this.mPlayerControlsView == null) {
            return;
        }
        if (UserSubscriptionUtil.isNightwingOnly() && getContentUri() != null && MediaProvider.SmartPlaylists.getPlaylistId(getContentUri()) == 1) {
            this.mPlayerControlsView.setVisibility(8);
            return;
        }
        this.mIsRemote = CirrusMediaSource.matchCloud(getContentUri());
        boolean z = isAvailable && !isEmpty() && TrackUtil.containsAtLeastOneAvailableTrack(cursor, this.mIsRemote);
        boolean zIsFreeTierUserPlaylistEditingEnabled = isFreeTierUserPlaylistEditingEnabled();
        if (zIsFreeTierUserPlaylistEditingEnabled) {
            this.mPlayerControlsView.setEnabledWithoutSFA(z);
        } else {
            this.mPlayerControlsView.setEnabled(z);
        }
        boolean z2 = zIsFreeTierUserPlaylistEditingEnabled && !z;
        this.mShouldBlockPlaybackAndDownload = z2;
        this.mPlayerControlsView.setShouldBlockPlaybackAndDownload(z2);
        if (z && shouldDisablePlaybackForExplicit(cursor)) {
            this.mPlayerControlsView.disablePlaybackControlState();
        }
    }

    private boolean isFreeTierUserPlaylistEditingEnabled() {
        return UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    }

    private boolean shouldDisablePlaybackForExplicit(final Cursor cursor) {
        if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
            return ExplicitLanguageUtil.containsOnlyExplicitContent(cursor);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshView() {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.TrackListFragment.20
            @Override // java.lang.Runnable
            public void run() {
                ((BaseFragment) TrackListFragment.this).mHandler.postDelayed(TrackListFragment.this.mOnScrollSettledRunnable, 1000L);
            }
        });
    }
}
