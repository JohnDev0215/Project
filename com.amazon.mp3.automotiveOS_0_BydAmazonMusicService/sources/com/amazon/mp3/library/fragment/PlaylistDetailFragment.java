package com.amazon.mp3.library.fragment;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mls.config.internal.core.util.Strings;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.PlayerControlsView;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.helper.UdoPlaylistDownloadHelper;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.activity.SavePlaylistActivity;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.job.AddPrimeTrackJob;
import com.amazon.mp3.library.job.DeletePlaylistJob;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.api.PlaylistFactory;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.sharing.ShareProviderActionHandler;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.voice.hints.guicontext.GUIMetadataNotReadyException;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.ShareProvider;
import com.amazon.music.share.UserPlaylistShareProvider;
import com.amazon.music.widget.eux.EUXConfiguration;
import com.amazon.music.widget.eux.adapters.EUXOfflineContentAdapter;
import com.amazon.music.widget.eux.adapters.EUXRecentlyAddedAdapter;
import com.amazon.music.widget.eux.adapters.EUXRecentlyPurchasedAdapter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistDetailFragment extends TrackListFragment {
    private ActionBarView mActionBarView;
    private Drawable mAlbumArtwork;
    private Drawable mCompositePlaylistArtwork;
    private boolean mFromTabHost;
    private boolean mIsRecentlyPlayedPlaylist;
    private boolean mIsRemote;
    private boolean mIsSmart;
    private String mOverridePlaylistNameString;
    private Playlist mPlaylist;
    private Drawable mPlaylistBannerArtwork;
    private String mPlaylistDescriptionString;
    private String mPlaylistNameString;
    private String mPlaylistTagName;
    private PlaylistUtil.PlaylistTrackInfo mPlaylistTrackInfo;
    private boolean mUdo;
    private String mUiPageViewIdentifier;
    private Subscription sideLoadedSubscription;
    private static final String TAG = PlaylistDetailFragment.class.getSimpleName();
    private static final int CONFIRM_DELETE_PLAYLIST_ID = UniqueCodeUtil.nextUniqueCode();
    private static final String[] AVAILABILITY_PROJECTION = {SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX};
    private static int sArtworkSize = 0;
    private long mPlaylistArtJob = -1;
    private long mHeaderMetaDataJob = -1;
    private long mAvailabilityCheckJob = -1;
    private long mDeletePlaylistJob = -1;
    private long mPlaylistDescriptionJob = -1;
    private boolean mShowHeroImage = true;
    private boolean mShowPlayerControls = true;
    private Handler mBackgroundHandler = AmazonApplication.getBackgroundHandler();
    private boolean avatarFlag = false;
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_BENEFITS) && PlaylistDetailFragment.this.isActivated()) {
                PlaylistDetailFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PlaylistDetailFragment.this.refreshRedownloadRibbon();
                    }
                });
            }
        }
    };
    private ContentObserver mCursorChangedObserver = new ContentObserver(AmazonApplication.getBackgroundHandler()) { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.3
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            PlaylistDetailFragment.this.updateHeader();
        }
    };
    private DataSetObserver mCursorDatasetObserver = new DataSetObserver() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.4
        @Override // android.database.DataSetObserver
        public void onChanged() {
            PlaylistDetailFragment.this.updateHeader();
        }
    };

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getEmptyLayoutID() {
        return R.layout.library_playlist_detail_empty;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSortOrder() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldEmitDefaultUiPageViewMetric() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean shouldSaveLastViewedSource() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected boolean shouldUseFastScroll() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void showRedownloadIcon() {
        this.mPlayerControlsView.updateDownloadButtonToShow(2);
        initDownloadButtonClickListener();
        setGroupDownloadState(5);
        this.mPlayerControlsView.setDownloadingState(getPlaylistDownloadState());
    }

    private int getPlaylistDownloadState() {
        if (!isSmartPlaylist() && PlaylistUtil.isUserPlaylistDownloaded(getPlaylistId())) {
            setGroupDownloadState(0);
            return 0;
        }
        int groupDownloadState = super.getGroupDownloadState();
        if (isSmartPlaylist() || groupDownloadState != 0) {
            return groupDownloadState;
        }
        return 5;
    }

    public static PlaylistDetailFragment newInstance(Intent intent, boolean forceHeroImage) {
        return newInstance(intent, forceHeroImage, true);
    }

    public static PlaylistDetailFragment newInstance(final Intent intent, final boolean forceHeroImage, final String fragmentTitleOverride) {
        PlaylistDetailFragment playlistDetailFragmentNewInstance = newInstance(intent, forceHeroImage);
        if (fragmentTitleOverride != null) {
            playlistDetailFragmentNewInstance.overridePlaylistNameString(fragmentTitleOverride);
        }
        return playlistDetailFragmentNewInstance;
    }

    public static PlaylistDetailFragment newInstance(Intent intent, boolean forceHeroImage, boolean playerControls) {
        PlaylistDetailFragment playlistDetailFragment = new PlaylistDetailFragment();
        intent.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        intent.putExtra("show_hero", forceHeroImage);
        intent.putExtra("show_player_controls", playerControls);
        intent.putExtra("ui_page_view_identifier_key", getUiPageViewIdentifier(intent));
        playlistDetailFragment.setArguments(intent.getExtras());
        return playlistDetailFragment;
    }

    public static String getUiPageViewIdentifier(final Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
            return PageType.DETAIL_USER_PLAYLIST.getMetricsValue();
        }
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(uri)) {
            return PageType.DETAIL_AUTO_PLAYLIST.getMetricsValue();
        }
        Log.warning(TAG, "ContentUri is not as expected. contentUri = " + uri);
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        Bundle arguments = getArguments();
        this.mShowHeroImage = arguments.getBoolean("show_hero");
        this.mShowPlayerControls = arguments.getBoolean("show_player_controls");
        this.mUiPageViewIdentifier = arguments.getString("ui_page_view_identifier_key", "");
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
        this.mPlaylistTagName = getArguments().getString("com.amazon.mp3.EXTRA_PLAYLIST_DETAIL_TAB_NAME", "");
        this.mFromTabHost = !r3.equals("");
        this.mPlaylist = new Playlist(AmazonApplication.getLibraryItemFactory(), getContentUri());
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected String getSharedPrefName() {
        return "com.amazon.mp3_" + this.mPlaylistTagName;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(getHasOptionsMenu());
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    public boolean getHasOptionsMenu() {
        if (this.mIsSmart) {
            return super.getHasOptionsMenu();
        }
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
        if (this.mFromTabHost) {
            this.mActionBarView = new ActionBarView(getActivity(), this.mTabHostName);
        } else {
            if (!TextUtils.isEmpty(this.mUiPageViewIdentifier)) {
                NavigationAppEvent.builder(this.mUiPageViewIdentifier).useIdentifierAsPageType().withOrientation(ScreenUtil.getScreenOrientation(getContext())).withItemIdentifier(getPlaylistLuid()).withItemIdentifierType("PLAYLIST_ID").publish();
            }
            this.mActionBarView = new ActionBarView(getActivity(), null);
        }
        setHeaderView(this.mActionBarView);
        requestHomeButtonAsUp();
        if (this.mShowPlayerControls) {
            initDetailPlayerControlsFragment();
        }
    }

    public View getActionBarView() {
        return this.mActionBarView;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void initHomeNav() {
        requestHomeButtonAsUp();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateHeader();
        notifyDataSetChanged();
        if (this.mFromTabHost) {
            if (TextUtils.isEmpty(this.mPlaylistTagName) || !this.mPlaylistTagName.equalsIgnoreCase("PLAYLIST_RECENTLY_DOWNLOADED_TAB_NAME")) {
                return;
            }
            refreshRedownloadRibbon();
            return;
        }
        LastViewedScreenUtil.setLastViewed(getContext(), LastViewedScreenUtil.LastViewedScreen.LIBRARY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshRedownloadRibbon() {
        if (SettingsUtil.shouldShowRedownloadUIUntilAcknowledged(getContext())) {
            Log.debug(TAG, "Should show Redownload UI unless already shown and user already acknowledged, as per shared preference.");
            showRedownloadDialogAndRibbon();
        } else if (SettingsUtil.getBooleanPref(getContext(), R.string.setting_key_redownload_ribbon_dismissed)) {
            hideRedownloadRibbon();
        }
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.fragment.BaseFragment
    public void onDeactivated() {
        super.onDeactivated();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
        Subscription subscription = this.sideLoadedSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getCollectionName() {
        return this.mPlaylist.getName() == null ? this.mPlaylistNameString : this.mPlaylist.getName();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void removeItemRemoveFromPlaylist(Menu menu) {
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(getContentUri())) {
            menu.removeItem(R.id.MenuTrackContextDeleteFromPlaylist);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        TrackListAdapter trackListAdapter = new TrackListAdapter(getActivity(), getContentUri(), this, false);
        trackListAdapter.setOnPrimeAddButtonClickListener(new TrackListAdapter.OnPrimeAddButtonClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda8
            @Override // com.amazon.mp3.library.adapter.TrackListAdapter.OnPrimeAddButtonClickListener
            public final void onClick(MusicTrack musicTrack) {
                this.f$0.lambda$createTrackListAdapter$0(musicTrack);
            }
        });
        if (AmazonApplication.getCapabilities().isDownloadTrackButtonEnabled()) {
            trackListAdapter.showDownloadButton(true);
        }
        trackListAdapter.initNightwingSettings();
        return trackListAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createTrackListAdapter$0(MusicTrack musicTrack) {
        this.mAddPrimeTrackJobIds.add(Long.valueOf(addJob(new AddPrimeTrackJob(getActivity(), musicTrack.getAsin()))));
        MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, musicTrack.getAsin(), SelectionSourceType.AUTO_PLAYLIST, null);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void removeFromPlaylist(final Uri trackUri) {
        this.mBackgroundHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$removeFromPlaylist$1(trackUri);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeFromPlaylist$1(Uri uri) {
        if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(getContentUri()))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
            return;
        }
        if ("cirrus".equals(MediaProvider.getSource(getSinglePlaylistUri())) && PlaylistUtil.getPlaylistCount(getActivity(), getSinglePlaylistUri()) >= PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist()) {
            startActivity(DialogActivity.getStartIntent(getActivity(), R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
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
        this.mBackgroundHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$doRemoveTrackFromPlaylist$2(uriBuild);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doRemoveTrackFromPlaylist$2(Uri uri) {
        getContentResolver().delete(uri, null, null);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        Uri contentUri = super.getContentUri();
        if (contentUri == null) {
            return null;
        }
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

    protected void overridePlaylistNameString(final String name) {
        if (name != null) {
            this.mOverridePlaylistNameString = name;
        }
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
            updateHeader();
            addAvailabilityCheckJob();
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("com.amazon.mp3.library.service.job.EXTRA_JOB_ID", this.mPlaylistArtJob);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onSyncServiceStopped() {
        super.onSyncServiceStopped();
    }

    protected long getPlaylistId() {
        return PlaylistUtil.getPlaylistId(getContentUri());
    }

    protected String getPlaylistLuid() {
        return PlaylistUtil.getPlaylistLuid(getContext(), getContentUri());
    }

    protected void updateHeader() {
        if (this.mPlaylistArtJob == -1 && this.mShowHeroImage) {
            this.mPlaylistArtJob = addJob(new PlaylistArtJob());
        }
        if (this.mHeaderMetaDataJob == -1) {
            this.mHeaderMetaDataJob = addJob(new HeaderMetaDataJob());
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected View getEmptyView(EUXConfiguration euxConfiguration) {
        if (this.mIsSmart && !TextUtils.isEmpty(this.mPlaylistNameString)) {
            if (getApplication().getString(R.string.dmusic_library_smart_playlist_latest_purchases).equals(this.mPlaylistNameString) && AmazonApplication.getCapabilities().isStoreSupported()) {
                return new EUXRecentlyPurchasedAdapter(getContext()).getView(euxConfiguration);
            }
            if (getApplication().getString(R.string.dmusic_library_smart_playlist_latest_uploads).equals(this.mPlaylistNameString)) {
                return new EUXRecentlyAddedAdapter(getContext()).getView(euxConfiguration);
            }
            if (getApplication().getString(R.string.dmusic_library_smart_playlist_recently_added).equals(this.mPlaylistNameString)) {
                return new EUXOfflineContentAdapter(getContext()).getView(euxConfiguration);
            }
            Log.warning(TAG, "Trying to get an empty layout for a smart playlist that's not in Recent Activity tabs");
        }
        return null;
    }

    private void setVisibility(View view, String viewId, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
            return;
        }
        Log.error(TAG, "ViewId " + viewId + " is null");
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSelection() {
        if (isSourceLocal()) {
            return null;
        }
        return PlaylistUtil.getSelection(getContentUri());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String[] getSelectionArgs() {
        if (isSourceLocal()) {
            return null;
        }
        return PlaylistUtil.getSelectionArgs(getContentUri());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void showEmptyUI() {
        View view = getView();
        if (view != null) {
            if (ScreenUtil.isPortrait()) {
                setVisibility(view.findViewById(R.id.ImageFrameLayoutContainer), "ImageFrameLayoutContainer", 8);
                setVisibility(view.findViewById(R.id.DetailPlayerControlsFragment), "DetailPlayerControlsFragment", 8);
            } else {
                setVisibility(view.findViewById(R.id.ArtworkPlayerControlsFragment), "ArtworkPlayerControlsFragment", 8);
                setVisibility(view.findViewById(R.id.DetailPlayerControlsFragment), "DetailPlayerControlsFragment", 8);
                setVisibility(view.findViewById(R.id.PlaylistAlbumImage), "PlaylistAlbumImage", 8);
                setVisibility(view.findViewById(R.id.leftViews), "leftViews", 8);
            }
            setVisibility(view.findViewById(R.id.DescriptionContainer), "DescriptionContainer", 8);
        }
        this.mEXUSubscription.add(getEXUConfigurationObservable().subscribe(new Action1<EUXConfiguration>() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.2
            @Override // rx.functions.Action1
            public void call(final EUXConfiguration configuration) {
                View emptyView = PlaylistDetailFragment.this.getEmptyView(configuration);
                if (emptyView != null) {
                    PlaylistDetailFragment.this.showEmptyView(emptyView, false);
                } else {
                    PlaylistDetailFragment.this.showEmptyView(View.inflate(PlaylistDetailFragment.this.getActivity(), PlaylistDetailFragment.this.getEmptyLayoutID(), null), true);
                }
            }
        }));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void hideEmptyUI() {
        super.hideEmptyUI();
        if (!isEmptyViewDisplayed()) {
            init(null);
        }
        View view = getView();
        if (view == null || !ScreenUtil.isPortrait()) {
            return;
        }
        View viewFindViewById = view.findViewById(R.id.ImageFrameLayoutContainer);
        if (this.mShowHeroImage) {
            setVisibility(viewFindViewById, "ImageFrameLayoutContainer", 0);
        } else {
            setVisibility(viewFindViewById, "ImageFrameLayoutContainer", 8);
        }
        setVisibility(view.findViewById(R.id.DetailPlayerControlsFragment), "DetailPlayerControlsFragment", 0);
    }

    private void init(Bundle instanceState) {
        Uri contentUri = getContentUri();
        this.mUdo = DefaultUriMatcher.match(contentUri) == 16;
        this.mIsRemote = CirrusMediaSource.matchCloud(contentUri);
        this.mIsSmart = MediaProvider.isSmartPlaylist(contentUri);
        this.mIsRecentlyPlayedPlaylist = isRecentlyPlayedPlaylist();
    }

    private boolean isRecentlyPlayedPlaylist() {
        return this.mIsSmart && 3 == MediaProvider.SmartPlaylists.getPlaylistId(getContentUri());
    }

    private boolean isSmartPlaylist() {
        return MediaProvider.isSmartPlaylist(getContentUri());
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getLandscapeLayout() {
        return isSmartPlaylist() ? R.layout.library_smart_playlist_detail_fragment : R.layout.library_playlist_detail_fragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getArtworkSize() {
        if (sArtworkSize == 0) {
            sArtworkSize = ScreenUtil.getDeviceWidth() / 2;
        }
        return sArtworkSize;
    }

    private class HeaderMetaDataJob extends Job {
        private HeaderMetaDataJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Uri singlePlaylistUri = PlaylistDetailFragment.this.getSinglePlaylistUri();
            Application context = getContext();
            PlaylistDetailFragment.this.mPlaylistNameString = PlaylistUtil.getPlaylistName(context, singlePlaylistUri);
            PlaylistDetailFragment.this.mPlaylistTrackInfo = PlaylistUtil.createPlaylistInfo(context, singlePlaylistUri, CirrusMediaSource.match(singlePlaylistUri));
            PlaylistDetailFragment.this.setupSFAButton();
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public void setupSFAButton() {
        PlaylistUtil.PlaylistTrackInfo playlistTrackInfo;
        final Uri singlePlaylistUri = getSinglePlaylistUri();
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(singlePlaylistUri) || (playlistTrackInfo = this.mPlaylistTrackInfo) == null || playlistTrackInfo.mTrackCount <= 0 || ((MediaProvider.PrimePlaylists.isPrimePlaylist(singlePlaylistUri) && TextUtils.isEmpty(this.mPlaylist.getAsin())) || !StationUtils.isCustomerAllowedToPlaySFAStation())) {
            if (this.mPlayerControlsView != null) {
                ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setupSFAButton$3();
                    }
                });
            }
        } else {
            if (MediaProvider.UdoPlaylists.isUdoPlaylist(singlePlaylistUri)) {
                this.sideLoadedSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda4
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.lambda$setupSFAButton$4(singlePlaylistUri, (Subscriber) obj);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda5
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.lambda$setupSFAButton$5((Boolean) obj);
                    }
                }, new Action1() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda6
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        PlaylistDetailFragment.lambda$setupSFAButton$6((Throwable) obj);
                    }
                });
                return;
            }
            PlayerControlsView playerControlsView = this.mPlayerControlsView;
            if (playerControlsView != null) {
                setClickListenerForSFAButton(playerControlsView);
                ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setupSFAButton$7();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$3() {
        this.mPlayerControlsView.disableSFA();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$4(Uri uri, Subscriber subscriber) {
        if (getContext() != null) {
            subscriber.onNext(Boolean.valueOf(PlaylistUtil.hasAsinsInUdoPlaylist(getContext(), uri)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$5(Boolean bool) {
        if (this.mPlayerControlsView == null) {
            return;
        }
        if (!bool.booleanValue()) {
            this.mPlayerControlsView.disableSFA();
        } else {
            setClickListenerForSFAButton(this.mPlayerControlsView);
            this.mPlayerControlsView.showSFAButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupSFAButton$6(Throwable th) {
        Log.error(TAG, "Error determining if the playlist has a list of asins for SFA support");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSFAButton$7() {
        this.mPlayerControlsView.showSFAButton();
    }

    @WorkerThread
    private void setClickListenerForSFAButton(@NonNull final PlayerControlsView mPlayerControlsView) {
        mPlayerControlsView.setSFAListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setClickListenerForSFAButton$9(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setClickListenerForSFAButton$9(View view) {
        Uri singlePlaylistUri = getSinglePlaylistUri();
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(singlePlaylistUri)) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setClickListenerForSFAButton$8();
                }
            });
        } else if (MediaProvider.PrimePlaylists.isPrimePlaylist(singlePlaylistUri)) {
            StationUtils.startSFA(getActivity(), this.mPlaylist.getAsin(), "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
            StationUtils.sendStartSFAUiClickMetrics(this.mPlaylist.getAsin(), EntityIdType.SFA_PLAYLIST_SEED, getPageType());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setClickListenerForSFAButton$8() {
        List<String> asinListFromTrackLuidSet = StationUtils.getAsinListFromTrackLuidSet(getActivity(), this.mPlaylistTrackInfo.mCachePlaylist.keySet());
        StationUtils.startSFA(getActivity(), StationUtils.convertAsinsToTracks(asinListFromTrackLuidSet), "TRACK_LIST_SEED", PlaybackPageType.PLAYLIST_DETAIL);
        StationUtils.sendStartSFAUiClickMetrics(StringUtil.join(",", asinListFromTrackLuidSet), EntityIdType.SFA_TRACK_LIST_SEED, getPageType());
    }

    private class PlaylistArtJob extends Job {
        private PlaylistArtJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Uri contentUri = PlaylistDetailFragment.this.getContentUri();
            String source = MediaProvider.getSource(contentUri);
            if (PlaylistDetailFragment.this.mUdo) {
                PlaylistDetailFragment.this.mPlaylistBannerArtwork = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PLAYLIST_UDO_BANNER, source, ScreenUtil.getDeviceWidth(), Long.toString(PlaylistUtil.getPlaylistId(contentUri)), true);
                if (PlaylistDetailFragment.this.mPlaylistBannerArtwork != null) {
                    return 1;
                }
            }
            int artworkSize = PlaylistDetailFragment.this.getArtworkSize();
            List<Long> albumIds = PlaylistUtil.getAlbumIds(contentUri);
            int size = albumIds.size();
            if (size > 0) {
                PlaylistDetailFragment.this.mAlbumArtwork = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, source, artworkSize, Long.toString(albumIds.get(albumIds.size() - 1).longValue()), true);
            }
            if (size > 4) {
                PlaylistDetailFragment.this.mCompositePlaylistArtwork = CacheManager.getInstance().getOnCurrentThread(PlaylistDetailFragment.this.mUdo ? ImageLoaderFactory.ItemType.PLAYLIST_UDO : ImageLoaderFactory.ItemType.PLAYLIST_SMART, source, artworkSize, Long.toString(MediaProvider.Playlists.getPlaylistId(PlaylistDetailFragment.this.getContentUri())), true);
            } else if (size > 1) {
                PlaylistDetailFragment.this.mCompositePlaylistArtwork = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, source, artworkSize, Long.toString(albumIds.get(albumIds.size() - 2).longValue()), true);
            } else {
                PlaylistDetailFragment.this.mCompositePlaylistArtwork = null;
            }
            return 1;
        }
    }

    private class PlaylistDescriptionJob extends Job {
        private PlaylistDescriptionJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Uri singlePlaylistUri = PlaylistDetailFragment.this.getSinglePlaylistUri();
            Application context = getContext();
            if (context == null) {
                return 1;
            }
            PlaylistDetailFragment.this.mPlaylistDescriptionString = PlaylistUtil.getPlaylistDescriptionFromUri(context, singlePlaylistUri);
            return 1;
        }
    }

    private void addAvailabilityCheckJob() {
        if (-1 == this.mAvailabilityCheckJob) {
            this.mAvailabilityCheckJob = addJob(new AvailabilityCheckJob());
        }
    }

    private void addPlaylistDescriptionJob() {
        if (this.mPlaylistDescriptionJob == -1) {
            this.mPlaylistDescriptionJob = addJob(new PlaylistDescriptionJob());
        }
    }

    private class AvailabilityCheckJob extends Job {
        private AvailabilityCheckJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            Uri singlePlaylistUri = PlaylistDetailFragment.this.getSinglePlaylistUri();
            Cursor cursorQuery = getContext().getContentResolver().query(PlaylistDetailFragment.this.getSinglePlaylistUri(), PlaylistDetailFragment.AVAILABILITY_PROJECTION, null, null, null);
            if (cursorQuery == null) {
                return 3;
            }
            try {
                if (cursorQuery.moveToFirst()) {
                    Playlist playlist = new Playlist();
                    playlist.setContentUri(singlePlaylistUri);
                    playlist.setMinContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN)));
                    playlist.setMaxNonPreviousContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("content_catalog_status_max_non_previous")));
                    playlist.setMaxContentCatalogStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX)));
                    playlist.setMinContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN)));
                    playlist.setMaxContentOwnershipStatusValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX)));
                    PlaylistDetailFragment.this.mIsAvailable = playlist.isAvailable();
                }
                cursorQuery.close();
                return 1;
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorRequeried(Cursor c) {
        super.onCursorRequeried(c);
        updateHeader();
        addAvailabilityCheckJob();
    }

    private void updateArtworkForPortrait() {
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.PlaylistDetailImage);
        ImageView imageView2 = (ImageView) getActivity().findViewById(R.id.PlaylistAlbumImage);
        ImageView imageView3 = (ImageView) getActivity().findViewById(R.id.PlaylistDetailPlaceHolder);
        if (this.mPlaylistBannerArtwork != null) {
            if (imageView != null) {
                imageView.setVisibility(0);
                imageView.setImageDrawable(this.mPlaylistBannerArtwork);
            }
            this.mPlaylistBannerArtwork = null;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            if (imageView3 != null) {
                imageView3.setVisibility(8);
                return;
            }
            return;
        }
        if (this.mCompositePlaylistArtwork != null) {
            if (imageView != null) {
                imageView.setVisibility(0);
                imageView.setImageDrawable(this.mCompositePlaylistArtwork);
            }
            this.mCompositePlaylistArtwork = null;
        } else {
            imageView.setVisibility(8);
        }
        if (this.mAlbumArtwork == null || !ScreenUtil.isPortrait()) {
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            if (imageView3 != null) {
                imageView3.setVisibility(0);
                return;
            }
            return;
        }
        if (imageView2 != null) {
            imageView2.setImageDrawable(this.mAlbumArtwork);
            imageView2.setMaxWidth(sArtworkSize);
            imageView2.setMaxHeight(sArtworkSize);
            imageView2.setVisibility(0);
            if (this.mCompositePlaylistArtwork == null) {
                this.mAlbumArtwork = BitmapUtil.convertBitmapToDrawable(getResources(), BitmapUtil.generateBoxBlurredBitmapForDrawable(this.mAlbumArtwork, 2, 2));
                ImageView imageView4 = (ImageView) getActivity().findViewById(R.id.bkgPlaylistImage);
                if (imageView4 != null) {
                    imageView4.setBackground(this.mAlbumArtwork);
                }
            }
        }
        this.mAlbumArtwork = null;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
    }

    private void updateArtWorkForLandscape() {
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.PlaylistAlbumImage);
        View viewFindViewById = getActivity().findViewById(R.id.ArtworkPlayerControlsFragment);
        View viewFindViewById2 = getActivity().findViewById(R.id.DetailPlayerControlsFragment);
        if (imageView != null) {
            imageView.setVisibility(this.mShowHeroImage ? 0 : 8);
            Drawable drawable = this.mPlaylistBannerArtwork;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            } else {
                Drawable drawable2 = this.mCompositePlaylistArtwork;
                if (drawable2 != null) {
                    imageView.setImageDrawable(drawable2);
                } else {
                    Drawable drawable3 = this.mAlbumArtwork;
                    if (drawable3 != null) {
                        imageView.setImageDrawable(drawable3);
                    }
                }
            }
        }
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(this.mShowHeroImage ? 0 : 8);
        }
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(this.mShowHeroImage ? 8 : 0);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (jobId == this.mPlaylistArtJob) {
            this.mPlaylistArtJob = -1L;
            if (ScreenUtil.isPortrait()) {
                updateArtworkForPortrait();
            } else {
                updateArtWorkForLandscape();
            }
        } else if (jobId == this.mHeaderMetaDataJob) {
            this.mHeaderMetaDataJob = -1L;
            initActionBarText();
        } else if (jobId == this.mDeletePlaylistJob) {
            this.mDeletePlaylistJob = -1L;
            removeHeaderView();
            setHasOptionsMenu(false);
            activity.onBackPressed();
        } else if (jobId == this.mAvailabilityCheckJob) {
            this.mAvailabilityCheckJob = -1L;
            if (this.mPlayerControlsView != null) {
                if (!isSourceLocal() && UserSubscriptionUtil.isNightwingOnly() && getContentUri() != null && !areAllPlaylistTracksOwned() && MediaProvider.SmartPlaylists.getPlaylistId(getContentUri()) != 0) {
                    this.mPlayerControlsView.disablePlaybackControlState();
                    boolean zIsFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
                    this.mShouldBlockPlaybackAndDownload = zIsFreeTierUserPlaylistEditingEnabled;
                    this.mPlayerControlsView.setShouldBlockPlaybackAndDownload(zIsFreeTierUserPlaylistEditingEnabled);
                } else {
                    updatePlayerControlsViewState();
                }
            }
            addPlaylistDescriptionJob();
        } else if (jobId == this.mPlaylistDescriptionJob) {
            this.mPlaylistDescriptionJob = -1L;
            updatePlaylistDescriptionView();
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    private boolean areAllPlaylistTracksOwned() {
        Playlist playlist = this.mPlaylist;
        return playlist != null && playlist.isAllOwned();
    }

    private void initActionBarText() {
        if (this.mPlaylistTrackInfo == null) {
            return;
        }
        FragmentActivity activity = getActivity();
        PlaylistUtil.PlaylistTrackInfo playlistTrackInfo = this.mPlaylistTrackInfo;
        String strCalculateSongsDuration = PlaylistUtil.calculateSongsDuration(activity, playlistTrackInfo.mTrackCount, playlistTrackInfo.mCalcDuration);
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView instanceof ActionBarView) {
            String str = this.mOverridePlaylistNameString;
            if (str != null) {
                actionBarView.setTitles(str, strCalculateSongsDuration);
            } else {
                actionBarView.setTitles(this.mPlaylistNameString, strCalculateSongsDuration);
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.ArtListFragment
    protected void onArtReceived(Context context, Intent intent) {
        super.onArtReceived(context, intent);
        if (intent != null) {
            intent.getExtras();
        }
    }

    private void initDownloadButtonClickListener() {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setDownloadListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(final View v) {
                    PlaylistDetailFragment.this.downloadPlaylist();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadPlaylist() {
        if (this.mShouldBlockPlaybackAndDownload) {
            UpsellUtil.showBlockingUpsell(getActivity(), Long.toString(getPlaylistId()), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
            return;
        }
        if (getGroupDownloadState() == 0 && !PlaylistUtil.isUserPlaylistDownloaded(getPlaylistId())) {
            new UdoPlaylistDownloadHelper(getContext()).updateDownloadState(getSinglePlaylistUri(), 0);
            this.mPlayerControlsView.setDownloadingState(0);
            Log.debug(TAG, "Unify a playlist without redownloading tracks");
        } else {
            setGroupDownloadState(-2);
            Uri contentUri = getContentUri();
            startDownload(contentUri.toString(), contentUri, false, new NotificationInfo(this.mPlaylistNameString, null, ImageCacheUri.generateUri((MediaProvider.SmartPlaylists.isSmartPlaylist(contentUri) ? ImageLoaderFactory.ItemType.PLAYLIST_SMART : ImageLoaderFactory.ItemType.PLAYLIST_UDO).intValue(), MediaProvider.Playlists.getPlaylistId(getContentUri())).toString()));
            this.mPlayerControlsView.setDownloadingState(getGroupDownloadState());
        }
    }

    private void initDetailPlayerControlsFragment() {
        RelativeLayout relativeLayout;
        boolean zIsSourceLocal = isSourceLocal();
        Uri contentUri = getContentUri();
        PlayerControlsView playerControlsView = new PlayerControlsView(getActivity(), createNowPlayingIntent(), (contentUri != null && isSmartPlaylist() && (zIsSourceLocal || MediaProvider.SmartPlaylists.getPlaylistId(contentUri) == 3)) ? false : true, getPlaybackPageType(), this.mShowHeroImage && !ScreenUtil.isPortrait(), UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled() && !this.mIsSmart && !zIsSourceLocal);
        this.mPlayerControlsView = playerControlsView;
        playerControlsView.setOffline(zIsSourceLocal);
        this.mPlayerControlsView.setTrackInformationFetcher(this.mFetcher);
        if (zIsSourceLocal && !isSmartPlaylist()) {
            this.mPlayerControlsView.setDownloadingState(0);
            setGroupDownloadState(0);
        }
        PlayerControlsView.SelectionProvider selectionProvider = new PlayerControlsView.SelectionProvider() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.6
            @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.SelectionProvider
            public String getSelectionString() {
                if (PlaylistDetailFragment.this.mIsRecentlyPlayedPlaylist) {
                    return null;
                }
                return "ownership_status < ?";
            }

            @Override // com.amazon.mp3.actionbar.view.PlayerControlsView.SelectionProvider
            public String[] getSelectionArgsArray() {
                if (PlaylistDetailFragment.this.mIsRecentlyPlayedPlaylist) {
                    return null;
                }
                return new String[]{String.valueOf(300)};
            }
        };
        if (this.mIsSmart) {
            this.mPlayerControlsView.setSelectionProvider(selectionProvider);
        }
        initDownloadButtonClickListener();
        this.mPlayerControlsView.setDownloadProgressListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                PlaylistDetailFragment.this.handleDownloadBtnClick(false, new NotificationInfo(PlaylistDetailFragment.this.mPlaylistNameString, null, ImageCacheUri.generateUri((MediaProvider.SmartPlaylists.isSmartPlaylist(PlaylistDetailFragment.this.getContentUri()) ? ImageLoaderFactory.ItemType.PLAYLIST_SMART : ImageLoaderFactory.ItemType.PLAYLIST_UDO).intValue(), MediaProvider.Playlists.getPlaylistId(PlaylistDetailFragment.this.getContentUri())).toString()));
            }
        });
        updatePlayerControlsViewState();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                UpsellReason upsellReason;
                ActionType actionType = null;
                if (R.id.PlayButton == v.getId()) {
                    actionType = ActionType.PLAY_PLAYLIST;
                    upsellReason = UpsellReason.PLAY;
                } else if (R.id.ShuffleButton == v.getId()) {
                    actionType = ActionType.PLAY_SHUFFLE_ALL;
                    upsellReason = UpsellReason.SHUFFLE_ALL;
                } else {
                    upsellReason = null;
                }
                PlaylistDetailFragment playlistDetailFragment = PlaylistDetailFragment.this;
                playlistDetailFragment.sendUiClickMetric(actionType, playlistDetailFragment.getPlaylistLuid(), EntityIdType.PLAYLIST_ID, (Integer) (-1));
                PlaylistDetailFragment playlistDetailFragment2 = PlaylistDetailFragment.this;
                if (playlistDetailFragment2.mShouldBlockPlaybackAndDownload) {
                    UpsellUtil.showBlockingUpsell(playlistDetailFragment2.getActivity(), Long.toString(PlaylistDetailFragment.this.getPlaylistId()), upsellReason, UpsellSourceEntity.PLAYLIST);
                }
            }
        };
        this.mPlayerControlsView.setPlayListener(onClickListener);
        this.mPlayerControlsView.setShuffleListener(onClickListener);
        this.mPlayerControlsView.setShareListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                PlaylistDetailFragment.this.sharePlaylist();
            }
        });
        if (this.mShowHeroImage && !ScreenUtil.isPortrait()) {
            relativeLayout = (RelativeLayout) getView().findViewById(R.id.ArtworkPlayerControlsFragment);
        } else {
            relativeLayout = (RelativeLayout) getView().findViewById(R.id.DetailPlayerControlsFragment);
        }
        if (relativeLayout != null) {
            relativeLayout.addView(this.mPlayerControlsView);
        } else {
            Log.error(TAG, "Cannot add mPlayerControlsView since playerControlLayout is null!");
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void handleDownloadBtnClick(final boolean downloadNonAddedPrimeTracks, final NotificationInfo notificationInfo) {
        if (isSourceLocal()) {
            handleDownloadedBtnClick();
        } else {
            super.handleDownloadBtnClick(downloadNonAddedPrimeTracks, notificationInfo);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void handleDownloadedBtnClick() {
        if (isSmartPlaylist() || this.mDeletePlaylistJob != -1) {
            return;
        }
        showDialog(CONFIRM_DELETE_PLAYLIST_ID, buildConfirmDeletePlaylistDialog(false, getPlaylistName(), getPlaylistId()));
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        refreshGroupDownloadState();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onGroupDownloadStateUpdated() {
        if (this.mPlayerControlsView != null) {
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$onGroupDownloadStateUpdated$10((Subscriber) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$onGroupDownloadStateUpdated$11((Integer) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistDetailFragment.lambda$onGroupDownloadStateUpdated$12((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGroupDownloadStateUpdated$10(Subscriber subscriber) {
        int playlistDownloadState = getPlaylistDownloadState();
        if (playlistDownloadState == 0 && UserSubscriptionUtil.getUserSubscription().isKatana() && MediaProvider.UdoPlaylists.isUdoPlaylist(getContentUri())) {
            isRedownloadRequired();
        }
        subscriber.onNext(Integer.valueOf(playlistDownloadState));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGroupDownloadStateUpdated$11(Integer num) {
        this.mPlayerControlsView.setDownloadingState(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onGroupDownloadStateUpdated$12(Throwable th) {
        Log.error(TAG, "Error setting download state");
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

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        View viewInflate = View.inflate(getActivity(), R.layout.library_playlist_detail_header, null);
        viewInflate.setEnabled(false);
        viewInflate.setOnClickListener(null);
        ((ListView) getListView()).addHeaderView(viewInflate);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForLandscape() {
        View viewInflate = View.inflate(getActivity(), R.layout.library_playlist_detail_header, null);
        viewInflate.setEnabled(false);
        viewInflate.setOnClickListener(null);
        ((ListView) getListView()).addHeaderView(viewInflate);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_playlist_detail, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(final Menu menu) {
        if (getActivity() == null) {
            Log.warning(TAG, "Can't show menu for \"" + this.mPlaylistNameString + "\" because fragment is detached");
            return;
        }
        super.onPrepareOptionsMenu(menu);
        Capabilities capabilities = AmazonApplication.getCapabilities();
        if (!capabilities.isSharingSupported() || "cirrus-local".equals(MediaProvider.getSource(getContentUri()))) {
            menu.removeItem(R.id.MenuPlaylistShare);
        }
        if (!capabilities.isCustomerProfileSupported()) {
            menu.removeItem(R.id.MenuPlaylistMakePublic);
            menu.removeItem(R.id.MenuPlaylistMakePrivate);
        }
        Uri singlePlaylistUri = getSinglePlaylistUri();
        Context context = getContext();
        if (context != null && !PlaylistUtil.canRemovePlaylist(context, singlePlaylistUri)) {
            menu.removeItem(R.id.MenuPlaylistDelete);
        }
        if (!this.avatarFlag || !this.mUdo) {
            menu.removeItem(R.id.MenuPlaylistMakePrivate);
            menu.removeItem(R.id.MenuPlaylistMakePublic);
        } else {
            int i = AnonymousClass11.$SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[PlaylistUtil.getPlaylistVisibilityStateFromUri(getActivity().getApplicationContext(), singlePlaylistUri).ordinal()];
            if (i == 1) {
                menu.removeItem(R.id.MenuPlaylistMakePublic);
            } else if (i == 2) {
                menu.removeItem(R.id.MenuPlaylistMakePrivate);
            } else {
                menu.removeItem(R.id.MenuPlaylistMakePublic);
                menu.removeItem(R.id.MenuPlaylistMakePrivate);
            }
        }
        if (UserSubscriptionUtil.isNightwingOnly() && !AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) {
            menu.removeItem(R.id.MenuPlaylistEdit);
        }
        if (!UserSubscriptionUtil.isNightwingOnly() || !AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled() || !this.mIsRemote || getPlaylistDownloadState() == 0) {
            menu.removeItem(R.id.MenuPlaylistDownload);
        } else if (this.mShouldBlockPlaybackAndDownload) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuPlaylistDownload), getActivity());
        } else {
            StringUtil.setDefaultTextStyle(menu.findItem(R.id.MenuPlaylistDownload), getActivity());
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.PlaylistDetailFragment$11, reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState;

        static {
            int[] iArr = new int[MediaProvider.Playlists.VisibilityState.values().length];
            $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState = iArr;
            try {
                iArr[MediaProvider.Playlists.VisibilityState.PUBLIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[MediaProvider.Playlists.VisibilityState.PRIVATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[MediaProvider.Playlists.VisibilityState.NO_STATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (itemId != R.id.MenuPlaylistShare) {
            switch (itemId) {
                case R.id.MenuPlaylistDelete /* 2131427621 */:
                    deletePlayList();
                    return true;
                case R.id.MenuPlaylistDownload /* 2131427622 */:
                    downloadPlaylist();
                    break;
                case R.id.MenuPlaylistEdit /* 2131427623 */:
                    editPlaylist();
                    sendUiClickMetric(ActionType.EDIT_PLAYLIST);
                    return true;
                case R.id.MenuPlaylistMakePrivate /* 2131427624 */:
                    PlaylistUtil.setPlaylistVisibility(getActivity(), getSinglePlaylistUri(), MediaProvider.Playlists.VisibilityState.PRIVATE);
                    return true;
                case R.id.MenuPlaylistMakePublic /* 2131427625 */:
                    PlaylistUtil.setPlaylistVisibility(getActivity(), getSinglePlaylistUri(), MediaProvider.Playlists.VisibilityState.PUBLIC);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
        sharePlaylist();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sharePlaylist() {
        ShareProvider shareProviderBuildPlaylistShareProvider = buildPlaylistShareProvider();
        if (shareProviderBuildPlaylistShareProvider != null) {
            shareProviderBuildPlaylistShareProvider.startShare();
        }
    }

    private ShareProvider buildPlaylistShareProvider() {
        FragmentActivity activity = getActivity();
        if (!ConnectivityUtil.checkConnectivityAndShowDialog(activity)) {
            return null;
        }
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(getSinglePlaylistUri());
        return new UserPlaylistShareProvider(activity, musicDomain, playlist.getLuid(), playlist.getName(), playlist.getVersion(), PlaylistFactory.createPlaylistLibrary(activity), ShareProviderActionHandler.newInstance(), null);
    }

    public void deletePlayList() {
        if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(getContentUri()))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
        } else if (this.mDeletePlaylistJob == -1) {
            showDialog(CONFIRM_DELETE_PLAYLIST_ID, buildConfirmDeletePlaylistDialog(getPlaylistName(), getPlaylistId()));
        }
    }

    protected int getDeletePlayListContentMessage() {
        return this.mIsRemote ? R.string.dmusic_playlist_toast_deleting_playlist_cloud : R.string.dmusic_playlist_toast_deleting_playlist_device;
    }

    private Dialog buildConfirmDeletePlaylistDialog(final String name, final long id) {
        return buildConfirmDeletePlaylistDialog(this.mIsRemote, name, id);
    }

    private Dialog buildConfirmDeletePlaylistDialog(final boolean deleteCloudPlaylist, final String name, final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String string = getString(deleteCloudPlaylist ? R.string.playlist_removed_content_description : R.string.playlist_removed_from_device_content_description);
        final String string2 = getString(R.string.removal_fail_content_description);
        builder.setPositiveButton(deleteCloudPlaylist ? R.string.dmusic_button_delete : R.string.dmusic_button_remove, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BauhausToastUtils.showTextToast(PlaylistDetailFragment.this.getActivity(), PlaylistDetailFragment.this.getDeletePlayListContentMessage(), 0);
                PlaylistDetailFragment playlistDetailFragment = PlaylistDetailFragment.this;
                playlistDetailFragment.mDeletePlaylistJob = playlistDetailFragment.addJob(new DeletePlaylistJob(deleteCloudPlaylist ? playlistDetailFragment.getSourceId() : "cirrus-local", id) { // from class: com.amazon.mp3.library.fragment.PlaylistDetailFragment.10.1
                    @Override // com.amazon.mp3.service.job.Job
                    protected void onFinished(final int resultCode, final Bundle resultBundle) {
                        super.onFinished(resultCode, resultBundle);
                        if (1 == resultCode) {
                            AccessibilityUtil.announceForAccessibility(getContext(), string);
                        } else {
                            AccessibilityUtil.announceForAccessibility(getContext(), string2);
                        }
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.dmusic_button_cancel, (DialogInterface.OnClickListener) null);
        builder.setTitle(deleteCloudPlaylist ? R.string.dmusic_playlist_confirm_delete_title : R.string.dmusic_playlist_context_remove_download);
        builder.setMessage(getString(deleteCloudPlaylist ? R.string.dmusic_prime_playlist_delete_msg_cloud : R.string.dmusic_offline_prime_playlist_delete_msg_device, name));
        return builder.create();
    }

    private void editPlaylist() {
        if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(getSinglePlaylistUri()))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
            return;
        }
        Cursor cursor = getCursor();
        PlaylistUtil.startEditPlaylist(getActivity(), getContentUri(), (cursor != null ? cursor.getCount() : 0) <= 0);
    }

    private void updatePlaylistDescriptionView() {
        boolean z = this.mIsAvailable && !isEmpty() && TrackUtil.containsAtLeastOneAvailableTrack(getCursor(), this.mIsRemote);
        if (this.mUdo && z && Strings.isNotNullOrEmpty(this.mPlaylistDescriptionString)) {
            View viewFindViewById = getView().findViewById(R.id.DescriptionContainer);
            ((TextView) getView().findViewById(R.id.PlaylistDescription)).setText(this.mPlaylistDescriptionString);
            viewFindViewById.setVisibility(0);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.PLAYLIST_DETAIL;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        if (StringUtils.isBlank(this.mUiPageViewIdentifier)) {
            return null;
        }
        String str = this.mUiPageViewIdentifier;
        PageType pageType = PageType.DETAIL_USER_PLAYLIST;
        return str.equalsIgnoreCase(pageType.getMetricsValue()) ? pageType : PageType.DETAIL_AUTO_PLAYLIST;
    }

    public String getUserPlaylistName() throws GUIMetadataNotReadyException {
        String collectionName = getCollectionName();
        if (TextUtils.isEmpty(collectionName)) {
            throw new GUIMetadataNotReadyException("User playlist name metadata not ready in Playlist Detail page");
        }
        return collectionName;
    }

    public boolean isUserPlaylistPage() {
        return this.mUdo;
    }
}
