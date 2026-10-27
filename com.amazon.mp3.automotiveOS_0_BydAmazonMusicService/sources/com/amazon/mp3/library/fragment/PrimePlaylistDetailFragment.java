package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.PlayerControlsView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.generator.CannotGenerateGroupException;
import com.amazon.mp3.download.generator.PrimePlaylistGroupGenerator;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.external.api.uri.QueryPlaylistByAsin;
import com.amazon.mp3.find.util.EntityTrackingIntentUtil;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistTrackContextMenuProvider;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.adapter.PrimePlaylistAddCallback;
import com.amazon.mp3.library.adapter.PrimePlaylistTrackListAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.dialog.DeletePlaylistUtil;
import com.amazon.mp3.library.fragment.TrackListFragment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.job.AddPrimePlaylistTrackJob;
import com.amazon.mp3.library.job.DeleteOfflinePrimePlaylistJob;
import com.amazon.mp3.library.job.FollowPrimePlaylistJob;
import com.amazon.mp3.library.job.UnfollowPrimePlaylistJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.net.playlist.MusicPlaylistExceptions;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.playlist.PrimePlaylistFollowState;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.playlist.job.GetPrimePlaylistJob;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.prime.cta.TrackMetadata;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.Session;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.voice.hints.guicontext.GUIMetadataNotReadyException;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistDetailFragment extends TrackListFragment {
    private static final String TAG = PrimePlaylistDetailFragment.class.getSimpleName();
    private int contentCatalogStatus;
    private int contentOwnershipStatus;
    private ActionBarView mActionBarView;
    private String mAsin;
    private CatalogPlaylist mCatalogPlaylist;
    private PrimePlaylistTrackContextMenuProvider mContextMenuProvider;
    private boolean mDownloadOnFollow;
    private PrimePlaylistFollowState mFollowState;
    private boolean mPlaylistTrackAddedFlag;
    private Drawable mPrimePlaylistBanner;
    private Subscription mQueryTracksSubscription;
    private boolean mShouldShowRefreshDownloadButton;
    private long mPrimePlaylistArtJob = -1;
    private long mGetPrimePlaylistJobId = -1;
    private long mFollowPlaylistJobId = -1;
    private long mUnfollowPlaylistJobId = -1;
    private long mAddTrackJobId = -1;
    private boolean mIsNightwingPlaylist = false;
    private boolean mIsNightwingOnDemandPlaylist = false;
    private boolean mIsUserNightwingOnly = false;
    private boolean mIsGoldenPlaylist = false;
    private boolean mIsOnDemandGoldenPlaylist = false;
    private final PrimePlaylistAddCallback mPlaylistAddCallback = new PlaylistAddCallback();
    private Playlist mPlaylist = null;
    private View.OnClickListener followClickListner = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.16
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ADD_PLAYLIST_TO_LIBRARY).withInteractionType(InteractionType.TAP).withPageType(PrimePlaylistDetailFragment.this.getPageType()).withEventTime(System.currentTimeMillis()).withEntityId(PrimePlaylistDetailFragment.this.mAsin).withEntityIdType(EntityIdType.ASIN).build());
            PrimePlaylistDetailFragment.this.followPlaylist();
        }
    };

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment
    protected String getArtColumnName() {
        return "artwork_id";
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected DeleteContentHandler getDeleteContentHandler() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getEmptyLayoutID() {
        return R.layout.common_progress_spinner;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    public boolean getHasOptionsMenu() {
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getLandscapeLayout() {
        return R.layout.library_prime_playlist_detail;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    public String[] getProjection() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    public String getSelection() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    public String[] getSelectionArgs() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    public String getSortOrder() {
        return "order_num";
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

    public static PrimePlaylistDetailFragment newInstance(String asin, String playlistName, boolean addToLibrary, Bundle bundle) {
        bundle.putString("ARG_ASIN", asin);
        bundle.putString("ARG_PLAYLIST_NAME", playlistName);
        bundle.putBoolean("ARG_ADD_TO_LIBRARY", addToLibrary);
        PrimePlaylistDetailFragment primePlaylistDetailFragment = new PrimePlaylistDetailFragment();
        primePlaylistDetailFragment.setArguments(bundle);
        return primePlaylistDetailFragment;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String string;
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        showEmptyUI();
        init(viewOnCreateView);
        Bundle arguments = getArguments();
        String string2 = null;
        if (arguments != null) {
            string2 = arguments.getString(DeeplinkIntentKey.KEY_REF_MARKER.toString());
            string = arguments.getString(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
        } else {
            string = null;
        }
        NavigationAppEvent.builder(getPageType().getMetricsValue()).useIdentifierAsPageType().withItemIdentifier(this.mAsin).withItemIdentifierType("ASIN").withRefMarker(string2).withAssociateTag(string).withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getCollectionName() {
        return this.mCatalogPlaylist.getTitle();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LastViewedScreenUtil.setLastViewed(getContext(), LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_prime_playlist_detail, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(@NonNull final Menu menu) {
        if (getActivity() == null) {
            Log.warning(TAG, "Can't show menu because fragment is detached");
            return;
        }
        super.onPrepareOptionsMenu(menu);
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.getBoolean("EXTRA_IS_ENTITY")) {
            return;
        }
        menu.removeItem(R.id.MenuPlaylistShare);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.MenuPlaylistShare) {
            new PlaylistShareProvider(getActivity(), EndpointsProvider.get().getMusicDomain(), this.mAsin, this.mCatalogPlaylist.getTitle(), this.mCatalogPlaylist.getThumbnailArtUrl()).startShare();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void initListViewHeaderForPortrait() {
        View viewInflate = View.inflate(getActivity(), R.layout.library_prime_playlist_detail_header, null);
        viewInflate.setOnClickListener(null);
        ((ListView) getListView()).addHeaderView(viewInflate);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        return new PrimePlaylistTrackListAdapter(getActivity(), getContentUri(), this.mPlaylistAddCallback, this, ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK, false, this.mShowCompressedView);
    }

    public class PlaylistAddCallback implements PrimePlaylistAddCallback {
        public PlaylistAddCallback() {
        }

        @Override // com.amazon.mp3.library.adapter.PrimePlaylistAddCallback
        public boolean onClick(int position) {
            Cursor cursor = PrimePlaylistDetailFragment.this.getCursor();
            if (cursor == null) {
                return false;
            }
            cursor.moveToPosition(position);
            if (ContentCatalogStatus.fromValue(cursor.getInt(cursor.getColumnIndex("catalog_status"))).isPreviouslyCatalog()) {
                return false;
            }
            if (!ConnectivityUtil.hasAnyInternetConnection()) {
                PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                primePlaylistDetailFragment.startActivity(NetworkErrorDialogActivity.getStartIntent(primePlaylistDetailFragment.getActivity()));
                return false;
            }
            String string = cursor.getString(cursor.getColumnIndex("asin"));
            PrimePlaylistDetailFragment.this.addPrimePlaylistTrackJob(string);
            PrimePlaylistDetailFragment.this.sendUiClickMetric(ActionType.ADD_TRACK_TO_LIBRARY, string, EntityIdType.ASIN, Integer.valueOf(position));
            return true;
        }
    }

    public void addPrimePlaylistTrackJob(String asin) {
        this.mPlaylistTrackAddedFlag = true;
        MetricsLogger.clickedAddPrimeContentFromCatalogPlaylist(LinkType.TRACK, asin, this.mCatalogPlaylist.getAsin());
        this.mAddTrackJobId = addJob(new AddPrimePlaylistTrackJob(asin));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected long createPrimeTrackAddJob(String asin) {
        this.mPlaylistTrackAddedFlag = true;
        Log.verbose(TAG, "Add prime playlist track with asin: " + asin);
        return addJob(new AddPrimePlaylistTrackJob(asin));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (jobId == this.mGetPrimePlaylistJobId) {
            onGetPrimePlaylistJobFinished((GetPrimePlaylistJob) job);
        } else if (jobId == this.mPrimePlaylistArtJob) {
            this.mPrimePlaylistArtJob = -1L;
            if (this.mPrimePlaylistBanner != null) {
                ((ImageView) getView().findViewById(R.id.PrimePlaylistDetailImage)).setImageDrawable(this.mPrimePlaylistBanner);
            }
        } else if (jobId == this.mFollowPlaylistJobId) {
            this.mFollowPlaylistJobId = -1L;
            FollowPrimePlaylistJob followPrimePlaylistJob = (FollowPrimePlaylistJob) job;
            if (resultCode == 3) {
                Log.error(TAG, "Error following a prime playlist [%d], error message = %s", Integer.valueOf(followPrimePlaylistJob.getErrorCode()), followPrimePlaylistJob.getErrorLog() == null ? "N/A" : followPrimePlaylistJob.getErrorLog());
            } else {
                this.mCatalogPlaylist.setLuid(followPrimePlaylistJob.getPrimePlaylistLuid());
                this.mCatalogPlaylist.setFollowed(true);
                if (this.mDownloadOnFollow) {
                    this.mDownloadOnFollow = false;
                    handleDownloadBtnClick(false, new NotificationInfo(this.mCatalogPlaylist.getTitle(), null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), this.mAsin).toString()));
                }
                this.mFollowState = followPrimePlaylistJob.getFollowState();
                this.mPlayerControlsView.setIsFollowedState(this.mCatalogPlaylist.isFollowed());
            }
            requeryCursorInBackground();
        } else if (jobId == this.mAddTrackJobId) {
            onAddPrimeTrackJobFinished(resultCode, true);
        } else if (jobId == this.mUnfollowPlaylistJobId && resultCode == 1) {
            this.mCatalogPlaylist.setFollowed(false);
            this.mFollowState = PrimePlaylistFollowState.UNFOLLOWED;
            this.mPlayerControlsView.setIsFollowedState(this.mCatalogPlaylist.isFollowed());
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, TrackMetadata> getTracksMetadata(Collection<CatalogPlaylistTrack> catalogPlaylistTracks) {
        HashMap map = new HashMap();
        for (CatalogPlaylistTrack catalogPlaylistTrack : catalogPlaylistTracks) {
            map.put(catalogPlaylistTrack.getAsin(), new TrackMetadata(catalogPlaylistTrack.getContentEncoding()));
        }
        return map;
    }

    private void onGetPrimePlaylistJobFinished(GetPrimePlaylistJob job) {
        String string;
        String string2;
        String string3;
        Log.debug(TAG, "GetPrimePlaylistJob completed");
        this.mGetPrimePlaylistJobId = -1L;
        this.mShouldShowRefreshDownloadButton = job.isIsLocalPrimePlaylistUpdateable();
        CatalogPlaylist playlist = job.getPlaylist();
        this.mCatalogPlaylist = playlist;
        if (playlist != null && playlist.getTrackCount() > 0) {
            Cursor cursorQuery = getContext().getContentResolver().query(super.getContentUri(), new String[]{"name", "luid", "catalog_status", "ownership_status"}, null, null, null);
            try {
                if (cursorQuery.moveToFirst()) {
                    this.contentCatalogStatus = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("catalog_status"));
                    this.contentOwnershipStatus = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("ownership_status"));
                }
                DbUtil.closeCursor(cursorQuery);
                this.mIsNightwingPlaylist = this.mCatalogPlaylist.isFree();
                this.mIsNightwingOnDemandPlaylist = this.mCatalogPlaylist.isFreeOnDemand();
                this.mIsGoldenPlaylist = this.mCatalogPlaylist.isGoldenPlaylist();
                if (AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled()) {
                    this.mIsOnDemandGoldenPlaylist = this.mIsGoldenPlaylist && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(this.mCatalogPlaylist.getAsin());
                }
                updateContentUriOnAsinChanged();
                this.mFollowState = this.mCatalogPlaylist.isFollowed() ? PrimePlaylistFollowState.FOLLOWING : PrimePlaylistFollowState.UNFOLLOWED;
                EntityTrackingIntentUtil.INSTANCE.sendEntityVisitIntentEvent(getActivity(), this.mCatalogPlaylist, getArguments() != null ? getArguments().getBoolean("navigation_source_extra", false) : false);
                this.mQueryTracksSubscription = this.mCatalogPlaylist.getTracksObservable().subscribe(new Action1<Collection<CatalogPlaylistTrack>>() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.1
                    @Override // rx.functions.Action1
                    public void call(final Collection<CatalogPlaylistTrack> catalogPlaylistTracks) {
                        if (PrimePlaylistDetailFragment.this.getAdapter() instanceof PrimePlaylistTrackListAdapter) {
                            ((PrimePlaylistTrackListAdapter) PrimePlaylistDetailFragment.this.getAdapter()).setIsGoldenPlaylist(PrimePlaylistDetailFragment.this.mIsGoldenPlaylist);
                            ((PrimePlaylistTrackListAdapter) PrimePlaylistDetailFragment.this.getAdapter()).setIsOnDemandGoldenPlaylist(PrimePlaylistDetailFragment.this.mIsOnDemandGoldenPlaylist);
                            ((PrimePlaylistTrackListAdapter) PrimePlaylistDetailFragment.this.getAdapter()).setTracksMetadata(PrimePlaylistDetailFragment.this.getTracksMetadata(catalogPlaylistTracks));
                            ((PrimePlaylistTrackListAdapter) PrimePlaylistDetailFragment.this.getAdapter()).setIsNightwingOnlyUser(UserSubscriptionUtil.isNightwingOnly());
                        }
                        PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                        primePlaylistDetailFragment.mIsAvailable = primePlaylistDetailFragment.mCatalogPlaylist.isAvailable();
                        if (PrimePlaylistDetailFragment.this.mIsUserNightwingOnly) {
                            PrimePlaylistDetailFragment primePlaylistDetailFragment2 = PrimePlaylistDetailFragment.this;
                            primePlaylistDetailFragment2.initNightwingControls(primePlaylistDetailFragment2.getView());
                        } else {
                            PrimePlaylistDetailFragment primePlaylistDetailFragment3 = PrimePlaylistDetailFragment.this;
                            primePlaylistDetailFragment3.initPrimeControls(primePlaylistDetailFragment3.getView());
                        }
                        PrimePlaylistDetailFragment.this.upcreateActionBar();
                        PrimePlaylistDetailFragment primePlaylistDetailFragment4 = PrimePlaylistDetailFragment.this;
                        primePlaylistDetailFragment4.setHasOptionsMenu(primePlaylistDetailFragment4.getHasOptionsMenu());
                        PrimePlaylistDetailFragment primePlaylistDetailFragment5 = PrimePlaylistDetailFragment.this;
                        primePlaylistDetailFragment5.mPlayerControlsView.setIsFollowedState(primePlaylistDetailFragment5.mCatalogPlaylist.isFollowed());
                        PrimePlaylistDetailFragment.this.initPrimePlaylistDescription();
                        PrimePlaylistDetailFragment.this.mPlayerControlsView.updateDownloadButtonToShow(PrimePlaylistDetailFragment.this.getDownloadButtonToUse());
                        PrimePlaylistDetailFragment.this.initDownloadButtonClickListener();
                        final Context context = PrimePlaylistDetailFragment.this.getContext();
                        PrimePlaylistDetailFragment.this.requeryCursorInBackground(new Runnable() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                new TrackListFragment.HandleDeeplinkTask(new QueryPlaylistByAsin(context)).execute(new Void[0]);
                            }
                        });
                        PrimePlaylistDetailFragment primePlaylistDetailFragment6 = PrimePlaylistDetailFragment.this;
                        primePlaylistDetailFragment6.mPrimePlaylistArtJob = primePlaylistDetailFragment6.addJob(new PrimePlaylistArtJob());
                        int size = catalogPlaylistTracks.size();
                        if ((PrimePlaylistDetailFragment.this.getAdapter() instanceof PrimePlaylistTrackListAdapter) && UserSubscriptionUtil.isNightwingOnly()) {
                            ((PrimePlaylistTrackListAdapter) PrimePlaylistDetailFragment.this.getAdapter()).initNightwingSettings(PrimePlaylistDetailFragment.this.mShowCompressedView, size);
                        }
                    }
                });
                return;
            } catch (Throwable th) {
                DbUtil.closeCursor(cursorQuery);
                throw th;
            }
        }
        hideEmptyUI();
        final FragmentActivity activity = getActivity();
        if (ConnectivityUtil.hasAnyInternetConnection(getContext())) {
            if (job.getFailureCause() instanceof MusicPlaylistExceptions.PlaylistFilteredException) {
                string = getString(R.string.dmusic_dialog_prime_kids_content_not_found_title);
                string2 = getString(R.string.dmusic_dialog_prime_kids_content_not_found_message);
                string3 = getString(R.string.dmusic_dialog_prime_kids_content_not_found_button);
            } else {
                string = getString(R.string.dmusic_dialog_prime_playlist_not_found_title);
                string2 = getString(R.string.dmusic_dialog_prime_playlist_not_found_message);
                string3 = getString(R.string.dmusic_dialog_prime_not_found_button);
            }
            new OneButtonErrorDialog(getActivity(), string, string2, new ErrorDialogButton(string3, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    PrimePlaylistDetailFragment.this.mNavigationProvider.changeScreenFragment(PrimeTabFragmentHost.newInstance(new Bundle()), false, true, false);
                }
            })).show();
            return;
        }
        DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                if (activity.getSupportFragmentManager().popBackStackImmediate()) {
                    return;
                }
                activity.finish();
            }
        };
        NoConnectionDialog noConnectionDialogNewInstance = NoConnectionDialog.newInstance(false);
        noConnectionDialogNewInstance.setListener(onDismissListener);
        noConnectionDialogNewInstance.show(getFragmentManager(), "NoConnectionDialog");
    }

    private void init(View rootView) {
        setPlaylistId();
        this.mIsUserNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
        this.mContextMenuProvider = new PrimePlaylistTrackContextMenuProvider((BaseActivity) getActivity(), getHeaderViewsCount(), getArguments().getBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", false), this.mProviderListener);
        if (getContentUri() == null) {
            this.mIsRemote = true;
        } else {
            this.mIsRemote = CirrusMediaSource.matchCloud(getContentUri());
        }
        upcreateActionBar();
    }

    private void setPlaylistId() {
        Uri contentUri;
        String string = getArguments().getString("ARG_ASIN");
        this.mAsin = string;
        if (!TextUtils.isEmpty(string) || (contentUri = getContentUri()) == null) {
            return;
        }
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri)) {
            this.mAsin = MediaProvider.PrimePlaylists.getPlaylistAsin(contentUri);
        } else if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(contentUri)) {
            this.mAsin = MediaProvider.SharedUserPlaylists.getPlaylistKey(contentUri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upcreateActionBar() {
        String str;
        String string = getArguments().getString("ARG_PLAYLIST_NAME");
        if (this.mCatalogPlaylist != null) {
            String strCalculateSongsDuration = PlaylistUtil.calculateSongsDuration(getActivity(), this.mCatalogPlaylist.getTrackCount(), this.mCatalogPlaylist.getDurationSeconds());
            str = strCalculateSongsDuration;
            string = this.mCatalogPlaylist.getTitle();
        } else {
            str = "";
        }
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView == null) {
            this.mActionBarView = new ActionBarView(getActivity(), string, str);
        } else {
            actionBarView.setTitles(string, str);
        }
        setHeaderView(this.mActionBarView);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void initHomeNav() {
        requestHomeButtonAsUp();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeHeaderView();
        if (this.mPlaylistTrackAddedFlag) {
            this.mPlaylistTrackAddedFlag = false;
            SyncService.startSync(getActivity(), 14011);
        }
        Subscription subscription = this.mQueryTracksSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.mQueryTracksSubscription.unsubscribe();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected ContextMenuProvider<TrackListAdapter.RowViewHolder> getContextMenuProvider() {
        return this.mContextMenuProvider;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorChanged(Cursor newCursor, Cursor oldCursor) {
        super.onCursorChanged(newCursor, oldCursor);
        if (newCursor == null || this.mPlayerControlsView == null) {
            return;
        }
        updatePlayerControlsViewState(newCursor, TrackUtil.containsAtLeastOneAvailableTrack(newCursor, this.mIsRemote));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected boolean queryIsTrackGroupDownloaded(final Context context) {
        Cursor cursorQuery = MediaProvider.getInstance().query(getContentUri(), null, null, null, null);
        if (cursorQuery == null) {
            Log.warning(TAG, "Failed to find PrimePlaylist in database (cursor null)");
            return false;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                Log.warning(TAG, "Failed to find PrimePlaylist in database (cursor empty)");
                return false;
            }
            int i = 0;
            while (true) {
                if (cursorQuery.isAfterLast()) {
                    return i > 0;
                }
                MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery);
                boolean z = track.getDownloadState() == 0;
                boolean z2 = ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.STREAM) == null;
                if (!z && z2) {
                    return false;
                }
                if (z) {
                    i++;
                }
                cursorQuery.moveToNext();
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPrimePlaylistDescription() {
        TextView textView = (TextView) getView().findViewById(R.id.PrimePlaylistDescription);
        TextView textView2 = (TextView) getView().findViewById(R.id.CuratedByName);
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.DescriptionContainer);
        textView.setText(getDescriptionText(this.mCatalogPlaylist));
        textView.setVisibility(0);
        if (TextUtils.isEmpty(this.mCatalogPlaylist.getProfileId()) || TextUtils.isEmpty(this.mCatalogPlaylist.getCuratedBy())) {
            return;
        }
        textView2.setText(this.mCatalogPlaylist.getCuratedBy());
        textView2.setVisibility(0);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomerProfileMetrics.emitUiClickMetric(ActionType.GO_CUSTOMER_PROFILE_FROM_PLAYLIST);
                new CustomerProfileNavigation((MusicHomeActivity) PrimePlaylistDetailFragment.this.getActivity()).loadProfile(PrimePlaylistDetailFragment.this.mCatalogPlaylist.getProfileId());
            }
        });
    }

    private String getDescriptionText(final CatalogPlaylist catalogPlaylist) {
        StringBuilder sb = new StringBuilder("");
        if (!TextUtils.isEmpty(catalogPlaylist.getDescription())) {
            sb.append(catalogPlaylist.getDescription());
        }
        if (!TextUtils.isEmpty(catalogPlaylist.getCuratedBy())) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(String.format(getString(R.string.dmusic_library_prime_playlists_description), TextUtils.isEmpty(catalogPlaylist.getProfileId()) ? catalogPlaylist.getCuratedBy() : ""));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initNightwingControls(View rootView) {
        PlayerControlsView.BigButtonConfig bigButtonConfig;
        Intent nowPlayingIntent = NowPlayingUtil.getNowPlayingIntent(getContentUri(), getSortOrder(), 0, getActivity());
        CatalogPlaylist catalogPlaylist = this.mCatalogPlaylist;
        boolean z = catalogPlaylist != null && catalogPlaylist.isFollowed();
        FragmentActivity activity = getActivity();
        if (this.mIsOnDemandGoldenPlaylist) {
            bigButtonConfig = PlayerControlsView.BigButtonConfig.NONE;
        } else {
            bigButtonConfig = this.mIsGoldenPlaylist ? PlayerControlsView.BigButtonConfig.BIG_SHUFFLE_BUTTON : PlayerControlsView.BigButtonConfig.BIG_SFA_BUTTON;
        }
        PlayerControlsView playerControlsView = new PlayerControlsView(activity, nowPlayingIntent, bigButtonConfig, PlaybackPageType.PRIME_PLAYLIST_DETAIL, Boolean.valueOf(z));
        this.mPlayerControlsView = playerControlsView;
        if (this.mIsOnDemandGoldenPlaylist) {
            playerControlsView.setTrackInformationFetcher(this.mFetcher);
            this.mPlayerControlsView.setContentUri(getContentUri());
            this.mPlayerControlsView.setEnabled(true);
            this.mPlayerControlsView.disableSFA();
            this.mPlayerControlsView.setPlayListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FreeTierPlaybackUtil.startPlaylistOnDemand(PrimePlaylistDetailFragment.this.getContext(), new PlayableEntityIdentifier(PrimePlaylistDetailFragment.this.mCatalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST, null);
                }
            });
            this.mPlayerControlsView.setShuffleListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FreeTierPlaybackUtil.playCloudStation(PrimePlaylistDetailFragment.this.getContext(), new PlayableEntityIdentifier(PrimePlaylistDetailFragment.this.mCatalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST);
                }
            });
        } else {
            playerControlsView.setBigButtonListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.7
                @Override // android.view.View.OnClickListener
                public void onClick(final View v) {
                    if (PrimePlaylistDetailFragment.this.mIsGoldenPlaylist) {
                        FreeTierPlaybackUtil.playCloudStation(PrimePlaylistDetailFragment.this.getContext(), new PlayableEntityIdentifier(PrimePlaylistDetailFragment.this.mCatalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST);
                    } else if (!PrimePlaylistDetailFragment.this.mCatalogPlaylist.isSharedUserPlaylist()) {
                        StationUtils.startSFA(PrimePlaylistDetailFragment.this.getContext(), PrimePlaylistDetailFragment.this.mCatalogPlaylist.getAsin(), "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
                    } else {
                        PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                        primePlaylistDetailFragment.startSFAforSharedUserPlaylist(primePlaylistDetailFragment.getContext());
                    }
                }
            });
        }
        this.mPlayerControlsView.setHeartFollowListener(this.followClickListner);
        this.mPlayerControlsView.setPadding(0, getResources().getDimensionPixelOffset(R.dimen.playlist_detail_page_nightwing_controls), 0, 0);
        ((RelativeLayout) rootView.findViewById(R.id.PrimePlaylistPlayerControlsFragment)).addView(this.mPlayerControlsView);
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPrimeControls(View rootView) {
        Intent nowPlayingIntent = NowPlayingUtil.getNowPlayingIntent(getContentUri(), getSortOrder(), 0, getActivity());
        CatalogPlaylist catalogPlaylist = this.mCatalogPlaylist;
        boolean zIsFollowed = catalogPlaylist != null ? catalogPlaylist.isFollowed() : false;
        boolean zIsSourceLocal = isSourceLocal();
        this.mPlayerControlsView = new PlayerControlsView(getActivity(), nowPlayingIntent, getDownloadButtonToUse(), zIsFollowed);
        initDownloadButtonClickListener();
        this.mPlayerControlsView.setOffline(zIsSourceLocal);
        this.mPlayerControlsView.setTrackInformationFetcher(this.mFetcher);
        if (!zIsSourceLocal) {
            this.mPlayerControlsView.setDeleteListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(final View v) {
                    PrimePlaylistDetailFragment.this.showDeleteDialog();
                }
            });
        }
        this.mPlayerControlsView.setFollowListener(this.followClickListner);
        this.mPlayerControlsView.setDownloadProgressListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                Uri uriGenerateUri = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), PrimePlaylistDetailFragment.this.mAsin);
                PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                primePlaylistDetailFragment.handleDownloadBtnClick(false, new NotificationInfo(primePlaylistDetailFragment.mCatalogPlaylist.getTitle(), null, uriGenerateUri.toString()));
            }
        });
        this.mPlayerControlsView.setEnabled(this.mIsAvailable);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                if (PrimePlaylistDetailFragment.this.mIsUserNightwingOnly) {
                    if (PrimePlaylistDetailFragment.this.mIsGoldenPlaylist) {
                        FreeTierPlaybackUtil.playCloudStation(PrimePlaylistDetailFragment.this.getContext(), new PlayableEntityIdentifier(PrimePlaylistDetailFragment.this.mCatalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.GOLDEN_PLAYLIST);
                        return;
                    } else {
                        StationUtils.startSFA(PrimePlaylistDetailFragment.this.getContext(), PrimePlaylistDetailFragment.this.mCatalogPlaylist.getAsin(), "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
                        return;
                    }
                }
                ActionType actionType = null;
                if (R.id.PlayButton == v.getId()) {
                    actionType = ActionType.PLAY_CURATED_PLAYLIST;
                } else if (R.id.ShuffleButton == v.getId()) {
                    actionType = ActionType.PLAY_SHUFFLE_ALL;
                }
                PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                primePlaylistDetailFragment.sendUiClickMetric(actionType, primePlaylistDetailFragment.mAsin, EntityIdType.ASIN, (Integer) (-1));
            }
        };
        this.mPlayerControlsView.setPlayListener(onClickListener);
        this.mPlayerControlsView.setShuffleListener(onClickListener);
        if (TextUtils.isEmpty(this.mAsin) || UserSubscriptionUtil.isNightwingOnly()) {
            this.mPlayerControlsView.hideSFAButton();
        } else {
            this.mPlayerControlsView.setSFAListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initPrimeControls$0(view);
                }
            });
            this.mPlayerControlsView.showSFAButton();
        }
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.PrimePlaylistPlayerControlsFragment);
        if (relativeLayout != null) {
            relativeLayout.addView(this.mPlayerControlsView);
        }
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initPrimeControls$0(View view) {
        Uri contentUri = getContentUri();
        if (contentUri != null) {
            if (MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri)) {
                StationUtils.startSFA(getActivity(), this.mAsin, "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
                StationUtils.sendStartSFAUiClickMetrics(this.mAsin, EntityIdType.SFA_PLAYLIST_SEED);
            } else if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(contentUri)) {
                CatalogPlaylist catalogPlaylist = this.mCatalogPlaylist;
                if (catalogPlaylist != null) {
                    List<Track> trackListFromSharedPlaylistWithLimitation = StationUtils.getTrackListFromSharedPlaylistWithLimitation(catalogPlaylist);
                    StationUtils.startSFA(getActivity(), trackListFromSharedPlaylistWithLimitation, "TRACK_LIST_SEED", PlaybackPageType.PLAYLIST_DETAIL);
                    StationUtils.sendStartSFAUiClickMetrics(StringUtil.join(",", StationUtils.convertTracksToAsins(trackListFromSharedPlaylistWithLimitation)), EntityIdType.SFA_TRACK_LIST_SEED);
                    return;
                }
                Log.error(TAG, "Can not get track list from catalogPlaylist as catalogPlaylist is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDownloadButtonToUse() {
        if (isSourceLocal()) {
            return 0;
        }
        return this.mShouldShowRefreshDownloadButton ? 2 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDownloadButtonClickListener() {
        this.mPlayerControlsView.setDownloadListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                if (PrimePlaylistDetailFragment.this.mIsUserNightwingOnly) {
                    UpsellUtil.showBlockingUpsell(PrimePlaylistDetailFragment.this.getActivity(), UpsellUtil.getUpsellInformation(PrimePlaylistDetailFragment.this.mAsin, UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST));
                } else {
                    PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                    primePlaylistDetailFragment.downloadPlaylist(primePlaylistDetailFragment.mCatalogPlaylist);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadPlaylist(final CatalogPlaylist catalogPlaylist) {
        PrimeContentUtil.checkOfflineAccess(catalogPlaylist, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.12
            @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
            public void resultReceived(final boolean contentAvailable, final ContentUnavailableReason reasonUnavailable) {
                if (ContentAccessUtil.canContinueCatalogStatusAction(PrimePlaylistDetailFragment.this.getActivity(), PrimePlaylistDetailFragment.this.mCatalogPlaylist.isPrime() ? ContentCatalogStatus.PRIME : ContentCatalogStatus.HAWKFIRE, ContentAccessUtil.ContentAccessOperation.DOWNLOAD)) {
                    PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                    primePlaylistDetailFragment.sendUiClickMetric(ActionType.DOWNLOAD_ALL, primePlaylistDetailFragment.mAsin, EntityIdType.ASIN, (Integer) null);
                    if (PrimePlaylistDetailFragment.this.canFollowPrimePlaylist()) {
                        PrimePlaylistDetailFragment.this.mDownloadOnFollow = true;
                        PrimePlaylistDetailFragment.this.followPlaylist();
                        return;
                    }
                    Uri uriGenerateUri = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), PrimePlaylistDetailFragment.this.mAsin);
                    PrimePlaylistDetailFragment primePlaylistDetailFragment2 = PrimePlaylistDetailFragment.this;
                    primePlaylistDetailFragment2.handleDownloadBtnClick(false, new NotificationInfo(primePlaylistDetailFragment2.mCatalogPlaylist.getTitle(), null, uriGenerateUri.toString()));
                    PrimePlaylistDetailFragment primePlaylistDetailFragment3 = PrimePlaylistDetailFragment.this;
                    primePlaylistDetailFragment3.mPlayerControlsView.setDownloadingState(primePlaylistDetailFragment3.getGroupDownloadState());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeleteDialog() {
        final Session session = getSession();
        FragmentActivity activity = getActivity();
        final String string = getString(this.mIsRemote ? R.string.playlist_removed_content_description : R.string.playlist_removed_from_device_content_description);
        final String string2 = getString(R.string.removal_fail_content_description);
        showDialog(DeletePlaylistUtil.CONFIRM_DELETE_PRIME_PLAYLIST_ID, DeletePlaylistUtil.createPrimeDialog(activity, this.mIsRemote, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                PrimePlaylistDetailFragment primePlaylistDetailFragment = PrimePlaylistDetailFragment.this;
                if (!primePlaylistDetailFragment.mIsRemote) {
                    primePlaylistDetailFragment.mUnfollowPlaylistJobId = session.addJob(new DeleteOfflinePrimePlaylistJob(primePlaylistDetailFragment.mCatalogPlaylist.getAsin(), PrimePlaylistDetailFragment.this.mCatalogPlaylist.getLuid()) { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.13.2
                        @Override // com.amazon.mp3.service.job.Job
                        protected void onFinished(final int resultCode, final Bundle resultBundle) {
                            super.onFinished(resultCode, resultBundle);
                            AnonymousClass13 anonymousClass13 = AnonymousClass13.this;
                            PrimePlaylistDetailFragment.this.announceDeleteJobFinished(resultCode, string, string2);
                        }
                    });
                    return;
                }
                BauhausToastUtils.showTextToast(primePlaylistDetailFragment.getActivity(), R.string.dmusic_stop_following_toast_message, 1);
                PrimePlaylistDetailFragment.this.mUnfollowPlaylistJobId = session.addJob(new UnfollowPrimePlaylistJob(PrimePlaylistDetailFragment.this.mCatalogPlaylist.getAsin(), PrimePlaylistDetailFragment.this.mCatalogPlaylist.getLuid(), false) { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.13.1
                    @Override // com.amazon.mp3.service.job.Job
                    protected void onFinished(final int resultCode, final Bundle resultBundle) {
                        super.onFinished(resultCode, resultBundle);
                        PrimePlaylistDetailFragment.this.removeCatalogPlaylist();
                        AnonymousClass13 anonymousClass13 = AnonymousClass13.this;
                        PrimePlaylistDetailFragment.this.announceDeleteJobFinished(resultCode, string, string2);
                    }
                });
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCatalogPlaylist() {
        final Context applicationContext = getContext().getApplicationContext();
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$removeCatalogPlaylist$1((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$removeCatalogPlaylist$2(applicationContext, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeCatalogPlaylist$1(Subscriber subscriber) {
        subscriber.onNext(this.mCatalogPlaylist.getAsin());
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeCatalogPlaylist$2(Context context, String str) {
        PlaylistIntentReceiverService.processRemoveCatalogPlaylist(context, this.mCatalogPlaylist.getAsin());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void announceDeleteJobFinished(final int resultCode, final String successMsg, final String failMsg) {
        if (1 == resultCode) {
            AccessibilityUtil.announceForAccessibility(getContext(), successMsg);
        } else {
            AccessibilityUtil.announceForAccessibility(getContext(), failMsg);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void showRedownloadIcon() {
        this.mPlayerControlsView.updateDownloadButtonToShow(2);
        initDownloadButtonClickListener();
        setGroupDownloadState(5);
        this.mPlayerControlsView.setDownloadingState(getGroupDownloadState());
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void isRedownloadRequired() {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$isRedownloadRequired$3((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber) new Subscriber<Boolean>() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.14
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                Log.error(PrimePlaylistDetailFragment.TAG, "Failed to get the redownload status: " + throwable.getMessage());
            }

            @Override // rx.Observer
            public void onNext(final Boolean showRedownloadIcon) {
                if (showRedownloadIcon.booleanValue()) {
                    PrimePlaylistDetailFragment.this.showRedownloadIcon();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$isRedownloadRequired$3(Subscriber subscriber) {
        Group groupGenerate = null;
        try {
            groupGenerate = new PrimePlaylistGroupGenerator(getContentUri(), getContext(), null, null, null, getStorageLocationFileManager()).generate(new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), null);
        } catch (CannotGenerateGroupException e) {
            Log.error(TAG, "Failed to generate group to evaluate redownload status " + e.getMessage());
        }
        subscriber.onNext(Boolean.valueOf(groupGenerate != null && groupGenerate.size() > 0));
        subscriber.onCompleted();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        onGroupDownloadStateUpdated();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onGroupDownloadStateUpdated() {
        if (this.mPlayerControlsView != null) {
            int groupDownloadState = getGroupDownloadState();
            if (groupDownloadState == 0 && UserSubscriptionUtil.getUserSubscription().isKatana()) {
                isRedownloadRequired();
            }
            this.mPlayerControlsView.setDownloadingState(groupDownloadState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onGroupProgressUpdated(int progress) {
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setDownloadingProgress(progress);
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected void initQuery() {
        if (getContext() == null) {
            return;
        }
        PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager(getContext().getApplicationContext());
        String string = getArguments().getString("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        this.mGetPrimePlaylistJobId = addJob(new GetPrimePlaylistJob(primePlaylistDatabaseManager, this.mAsin, !TextUtils.isEmpty(string) ? PrimePlaylistsTable.translateSource(string) : 0, getArguments().getBoolean("ARG_ADD_TO_LIBRARY", false), getArguments().getBoolean("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_IS_USER_PLAYLIST", false)));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK;
    }

    private class PrimePlaylistArtJob extends Job {
        private PrimePlaylistArtJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            PrimePlaylistDetailFragment.this.mPrimePlaylistBanner = getPrimePlaylistArtwork();
            return 1;
        }

        private Drawable getPrimePlaylistArtwork() {
            return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_BANNER, PrimePlaylistDetailFragment.this.getArguments().getString("com.amazon.mp3.library.EXTRA_SOURCE_ID"), ScreenUtil.getDeviceWidth(), PrimePlaylistDetailFragment.this.mAsin, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void followPlaylist() {
        if (this.mFollowPlaylistJobId == -1 && canFollowPrimePlaylist()) {
            if (!ConnectivityUtil.hasAnyInternetConnection()) {
                showNetworkErrorDialog(this);
                return;
            }
            final FragmentActivity activity = getActivity();
            BauhausToastUtils.showTextToast(getActivity(), activity.getString(R.string.dmusic_follow_toast_message), 0);
            this.mFollowPlaylistJobId = addJob(new FollowPrimePlaylistJob(this.mCatalogPlaylist.getAsin()));
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$followPlaylist$4((Subscriber) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$followPlaylist$5(activity, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$followPlaylist$4(Subscriber subscriber) {
        subscriber.onNext(this.mCatalogPlaylist.getAsin());
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$followPlaylist$5(Context context, String str) {
        PlaylistIntentReceiverService.processUpdateCatalogPlaylist(context, this.mCatalogPlaylist.getAsin());
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        CatalogPlaylist catalogPlaylist = this.mCatalogPlaylist;
        if (catalogPlaylist == null || !catalogPlaylist.isNew()) {
            return;
        }
        clearPrimePlaylistNewTags();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canFollowPrimePlaylist() {
        return this.mCatalogPlaylist != null && this.mFollowState == PrimePlaylistFollowState.UNFOLLOWED && this.mFollowPlaylistJobId == -1;
    }

    private void clearPrimePlaylistNewTags() {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.15
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                if (PrimePlaylistDetailFragment.this.mCatalogPlaylist == null || TextUtils.isEmpty(PrimePlaylistDetailFragment.this.mCatalogPlaylist.getLuid())) {
                    Log.error(PrimePlaylistDetailFragment.TAG, "null or invalid prime playlist");
                    return null;
                }
                new PrimePlaylistDatabaseManager(AmazonApplication.getContext()).setNew(PrimePlaylistDetailFragment.this.mCatalogPlaylist, false);
                PrimePlaylistDetailFragment.this.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                return null;
            }
        }.execute(new Void[0]);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void showEmptyUI() {
        super.showEmptyUI();
        PlayerControlsView playerControlsView = this.mPlayerControlsView;
        if (playerControlsView != null) {
            playerControlsView.setEnabled(false);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected void hideEmptyUI() {
        super.hideEmptyUI();
        if (this.mPlayerControlsView != null) {
            updatePlayerControlsViewState();
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.PRIME_PLAYLIST_DETAIL;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return PageType.DETAIL_PLAYLIST;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected Cursor query() {
        if (this.mCatalogPlaylist == null || getContext() == null) {
            return super.query();
        }
        synchronized (this.mAsin) {
            String asin = PrimePlaylistUtil.getAsin(new PrimePlaylistDatabaseManager(getContext().getApplicationContext()), this.mCatalogPlaylist.getLuid(), PrimePlaylistsTable.translateSource(this.mCatalogPlaylist.getSource()));
            if (asin != null && !asin.equals(this.mAsin)) {
                this.mAsin = asin;
                initQuery();
                return null;
            }
            return super.query();
        }
    }

    private void updateContentUriOnAsinChanged() {
        Uri contentUri;
        CatalogPlaylist catalogPlaylist = this.mCatalogPlaylist;
        if (catalogPlaylist == null || catalogPlaylist.getAsin() == null) {
            return;
        }
        if (!this.mCatalogPlaylist.getAsin().equals(this.mAsin)) {
            this.mAsin = this.mCatalogPlaylist.getAsin();
        }
        String source = this.mCatalogPlaylist.getSource();
        if (this.mCatalogPlaylist.isSharedUserPlaylist() || this.mCatalogPlaylist.isEntityPlaylist()) {
            contentUri = MediaProvider.SharedUserPlaylists.getContentUri(source, this.mAsin);
        } else {
            contentUri = MediaProvider.PrimePlaylists.getContentUri(source, this.mAsin);
        }
        if (contentUri == null || contentUri.getPath() == null) {
            return;
        }
        if (getContentUri() == null || !contentUri.getPath().equals(getContentUri().getPath())) {
            Log.debug(TAG, "Reset PrimePlaylist contentUri from %s to %s ", getContentUri(), contentUri);
            resetContent(contentUri, source);
            onContentUriChanged();
            return;
        }
        Log.debug(TAG, "Not resetting PrimePlaylist contentUri: %s", contentUri);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onItemClick(AbsListView listView, View view, int position, long id) {
        if (this.mIsUserNightwingOnly) {
            CatalogPlaylist catalogPlaylist = this.mCatalogPlaylist;
            if (catalogPlaylist != null) {
                catalogPlaylist.getTrackCount();
            }
            BadgeableListAdapter.BaseRowViewHolder baseRowViewHolder = (BadgeableListAdapter.BaseRowViewHolder) view.getTag();
            if (baseRowViewHolder == null) {
                return;
            }
            setClickedItem(baseRowViewHolder.mLibraryItem);
            AbstractItem abstractItem = baseRowViewHolder.mLibraryItem;
            if ((abstractItem instanceof MusicTrack) && abstractItem.isExplicit() && AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
                handleDisabledContentError(ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED, abstractItem);
                return;
            }
            if (this.mIsGoldenPlaylist) {
                if (this.mIsOnDemandGoldenPlaylist) {
                    FreeTierPlaybackUtil.startPlaylistOnDemand(getContext(), new PlayableEntityIdentifier(this.mCatalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST, abstractItem.getAsin());
                    return;
                } else {
                    FreeTierPlaybackUtil.playCloudStation(getContext(), new PlayableEntityIdentifier(this.mCatalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST);
                    return;
                }
            }
            UpsellManager.getInstance().raiseNonBlockingUpsell(getContext(), UpsellUtil.getUpsellInformation(baseRowViewHolder.mAsin, UpsellReason.PLAY, UpsellSourceEntity.PLAYLIST_DETAIL), MediaCollectionType.AMF_STATION_SEED);
            return;
        }
        super.onItemClick(listView, view, position, id);
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected boolean isWideVineContentIntendedRemote(AbstractItem contentItem) {
        return this.mIsRemote;
    }

    public String getPlaylistName() throws GUIMetadataNotReadyException {
        String collectionName = getCollectionName();
        if (TextUtils.isEmpty(collectionName)) {
            throw new GUIMetadataNotReadyException("Catalog playlist name metadata not ready in Playlist Detail page");
        }
        return collectionName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSFAforSharedUserPlaylist(Context context) {
        if (this.mCatalogPlaylist == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(100);
        Iterator<CatalogPlaylistTrack> it = this.mCatalogPlaylist.getTracks().iterator();
        while (it.hasNext()) {
            arrayList.add(new PlayableEntityIdentifier(it.next().getAsin(), PlayableEntityIdentifierType.ASIN));
            if (arrayList.size() >= 100) {
                break;
            }
        }
        FreeTierPlaybackUtil.playCloudStation(context, (List<PlayableEntityIdentifier>) arrayList, true, false, true, PlaybackPageType.SFA, Clock.now(), UpsellUtil.getUpsellInformation(arrayList, UpsellReason.PLAY, UpsellSourceEntity.PLAYLIST));
    }
}
