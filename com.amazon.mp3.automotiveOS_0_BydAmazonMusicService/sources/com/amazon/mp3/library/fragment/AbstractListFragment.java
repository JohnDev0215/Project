package com.amazon.mp3.library.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloaderKt;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.InactivityProvider;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.storage.ExternalStorageScanService;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.mp3.util.ListViewUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UpsellStrategy;
import rx.Subscription;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractListFragment extends LibraryBaseFragment implements AbsListView.OnScrollListener, ActionBarSourceToggleView.SourceChangedListener, DeleteContentHandler, VisibleListView {
    private static final IntentFilter SYNC_NOTIFICATION_FILTER;
    private static final String TAG = AbstractListFragment.class.getSimpleName();
    private AccountJobController mAccountJobs;
    private boolean mContentUriChangedWhileDeactivated;
    protected View mEmptyView;
    private View mFeatureBanner;
    private View mFeatureBannerDismissIcon;
    private View mFeatureBannerExitToAppIcon;
    private View mFeatureBannerExploreIcon;
    private TextView mFeatureBannerMessage;
    private boolean mFilterReceiverEnabled;
    private TextView mHeaderTitleView;
    private InputMethodManager mImm;
    private boolean mIsAtOnActivityResult;
    private boolean mIsEmptyViewDisplayed;
    private AccountJobController.JobFinishedCallback mJobFinishedCallback;
    protected AbsListView mListView;
    private LibraryPagerAdapter.OnListViewCreatedListener mListViewCreatedListener;
    private TextView mLoadingText;
    private View mLoadingView;
    private RelativeLayout mRootView;
    private Subscription mSubscription;
    protected boolean mUseGridView;
    private String mCurrentFilterValue = "";
    private int mFilterId = -1;
    protected int mSavedPosition = -1;
    protected long mContextMenuId = -1;
    private AbstractItem mClickedItem = null;
    private BroadcastReceiver mLibraryFilterSetReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.AbstractListFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", -1);
            AbstractListFragment.this.setHomeButtonVisibility(intent.getIntExtra("con.amazon.mp3.EXTRA_HAMBURGER_VISIBILITY", 0));
            if (intExtra == -1 || intExtra != AbstractListFragment.this.mFilterId) {
                return;
            }
            String stringExtra = intent.getStringExtra("com.amazon.mp3.EXTRA_LIBRARY_FILTER_VALUE");
            if (AbstractListFragment.this.mCurrentFilterValue.equals(stringExtra)) {
                return;
            }
            AbstractListFragment.this.mCurrentFilterValue = stringExtra;
            AbstractListFragment.this.raiseContentUriChanged();
        }
    };

    protected void deleteCloudContent() {
    }

    protected abstract ContextMenuProvider<? extends BadgeableListAdapter.BaseRowViewHolder> getContextMenuProvider();

    protected DeleteContentHandler getDeleteContentHandler() {
        return this;
    }

    protected abstract int getDeleteContentMessage();

    protected abstract String getHeaderText();

    protected int getLandscapeLayout() {
        return 0;
    }

    protected abstract PageType getPageType();

    protected void initListViewHeaderForLandscape() {
    }

    protected boolean isLoading() {
        return false;
    }

    protected abstract void onAvailableItemClick(AbsListView listView, View view, int position, long id);

    protected void onContentUriChanged() {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    @Override // com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
    }

    protected boolean shouldBringEmptyUIToFront() {
        return true;
    }

    protected boolean shouldDisplayContextMenuForPosition(int position) {
        return true;
    }

    protected boolean shouldShowLoadingText() {
        return true;
    }

    protected void updateEmptyView(View emptyView, boolean isLoading) {
    }

    static {
        IntentFilter intentFilter = new IntentFilter();
        SYNC_NOTIFICATION_FILTER = intentFilter;
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_SHOW_NOTIFICATION");
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_HIDE_NOTIFICATION");
    }

    protected int getHeaderViewsCount() {
        AbsListView absListView = this.mListView;
        if (absListView instanceof ListView) {
            return ((ListView) absListView).getHeaderViewsCount();
        }
        return 0;
    }

    protected String getSharedPrefName() {
        return "com.amazon.mp3_" + getClass().getSimpleName();
    }

    protected void initListViewHeaderForPortrait() {
        View viewInflate;
        this.mImm = (InputMethodManager) getActivity().getSystemService("input_method");
        if (this.mUseGridView) {
            viewInflate = inflateHeader(R.layout.library_list_header_row);
        } else {
            viewInflate = View.inflate(getActivity(), R.layout.library_list_header_row, null);
            ((ListView) this.mListView).addHeaderView(viewInflate);
        }
        this.mHeaderTitleView = (TextView) viewInflate.findViewById(R.id.HeaderTitle);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getListView().setOverScrollMode(0);
        LibraryPagerAdapter.OnListViewCreatedListener onListViewCreatedListener = this.mListViewCreatedListener;
        if (onListViewCreatedListener != null) {
            onListViewCreatedListener.onListViewCreated(getListView());
        }
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            View customView = supportActionBar.getCustomView();
            if (this.mFromTabHost && (customView instanceof ActionBarSourceToggleView)) {
                ((ActionBarSourceToggleView) customView).registerOnSourceChangedListener(this);
            }
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getListView().setOverScrollMode(2);
        if (this.mFromTabHost) {
            SharedPreferences.Editor editorEdit = getActivity().getSharedPreferences(getSharedPrefName(), 0).edit();
            int firstVisiblePosition = this.mListView.getFirstVisiblePosition();
            this.mSavedPosition = firstVisiblePosition;
            editorEdit.putInt("key_list_saved_position", firstVisiblePosition);
            editorEdit.apply();
        }
        Subscription subscription = this.mSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.mSubscription.unsubscribe();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        View view;
        BadgeableListAdapter.BaseRowViewHolder baseRowViewHolder;
        AbstractItem abstractItem;
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (adapterContextMenuInfo == null || adapterContextMenuInfo.id != this.mContextMenuId || (view = adapterContextMenuInfo.targetView) == null || (baseRowViewHolder = (BadgeableListAdapter.BaseRowViewHolder) view.getTag()) == null || (abstractItem = baseRowViewHolder.mLibraryItem) == null) {
            return false;
        }
        setClickedItem(abstractItem);
        ContextMenuProvider<? extends BadgeableListAdapter.BaseRowViewHolder> contextMenuProvider = getContextMenuProvider();
        contextMenuProvider.setPageType(getPageType());
        return contextMenuProvider.onContextMenuItemSelected(getActivity(), item, adapterContextMenuInfo, baseRowViewHolder.mLibraryItem.getSource());
    }

    public void setShowEmptyList() {
        this.mListView.setEmptyView((LinearLayout) this.mRootView.findViewById(R.id.EmptyView));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        this.mUseGridView = arguments.getBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", false);
        RelativeLayout relativeLayout = (RelativeLayout) View.inflate(getActivity(), getContentViewLayoutId(), null);
        this.mRootView = relativeLayout;
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.AlbumPrimeIndicator);
        if (imageView != null && Branding.shouldShowPrimeBranding()) {
            imageView.setImageResource(Branding.getPrimeSashDrawableId(getActivity()));
        }
        AbsListView absListViewById = getAbsListViewById(this.mRootView);
        this.mListView = absListViewById;
        absListViewById.setOnScrollListener(this);
        AbsListView absListView = this.mListView;
        if (absListView instanceof ListView) {
            ((ListView) absListView).setItemsCanFocus(true);
        }
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.amazon.mp3.library.fragment.AbstractListFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                AbstractListFragment.this.showGridItemSelected(view);
                AbstractListFragment abstractListFragment = AbstractListFragment.this;
                abstractListFragment.onItemClick(abstractListFragment.mListView, view, position, id);
                if (AbstractListFragment.this.mImm != null) {
                    AbstractListFragment.this.mImm.hideSoftInputFromWindow(listView.getWindowToken(), 0);
                }
                if (id >= 0) {
                    AbstractListFragment.this.sendSelectUiClickMetric();
                }
            }
        });
        this.mFromTabHost = arguments.getBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", false);
        this.mTabHostName = arguments.getString("com.amazon.mp3.library.EXTRA_FRAGMENT_TAB_HOST_NAME", "");
        if (arguments.getBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", false)) {
            if (ScreenUtil.isPortrait()) {
                initListViewHeaderForPortrait();
            } else {
                initListViewHeaderForLandscape();
            }
        }
        this.mLoadingView = this.mRootView.findViewById(R.id.ListActivityLoading);
        this.mLoadingText = (TextView) this.mRootView.findViewById(R.id.ListActivityLoadingText);
        reloadFilterFromIntent();
        if (savedInstanceState != null && savedInstanceState.containsKey("key_list_saved_position")) {
            this.mSavedPosition = savedInstanceState.getInt("key_list_saved_position");
        } else {
            this.mSavedPosition = getActivity().getSharedPreferences(getSharedPrefName(), 0).getInt("key_list_saved_position", 0);
        }
        if (ScreenUtil.isPortrait()) {
            setHeaderTitle(getHeaderText());
        }
        View viewFindViewById = this.mRootView.findViewById(R.id.feature_banner);
        this.mFeatureBanner = viewFindViewById;
        if (viewFindViewById != null) {
            this.mFeatureBannerExitToAppIcon = viewFindViewById.findViewById(R.id.feature_banner_icon);
            this.mFeatureBannerDismissIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_icon_dismiss);
            this.mFeatureBannerExploreIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_view);
            TextView textView = (TextView) this.mFeatureBanner.findViewById(R.id.feature_banner_message);
            this.mFeatureBannerMessage = textView;
            textView.setGravity(8388627);
        }
        return this.mRootView;
    }

    public void setHeaderTitle(String headerTitle) {
        TextView textView = this.mHeaderTitleView;
        if (textView != null) {
            textView.setText(headerTitle);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (this.mFilterReceiverEnabled) {
            unregisterReceiver(this.mLibraryFilterSetReceiver);
            this.mFilterReceiverEnabled = false;
        }
        setListViewAdapter(null);
        AbsListView absListView = this.mListView;
        if (absListView != null) {
            absListView.setOnItemClickListener(null);
            this.mListView.setOnCreateContextMenuListener(null);
            this.mListView.setOnScrollListener(null);
            this.mListView.setOnScrollListener(null);
            this.mListView.setOnTouchListener(null);
            this.mListViewCreatedListener = null;
        }
        setHomeButtonVisibility(0);
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        AbsListView absListView = this.mListView;
        if (absListView != null) {
            outState.putInt("key_list_saved_position", absListView.getFirstVisiblePosition());
        }
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
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        if (this.mContentUriChangedWhileDeactivated) {
            onContentUriChanged();
            this.mContentUriChangedWhileDeactivated = false;
        }
        checkShowLoadingText();
        resetGridSelection();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        checkShowLoadingText();
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        InputMethodManager inputMethodManager = this.mImm;
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        int i;
        String str;
        Uri contentUri = super.getContentUri();
        return (contentUri == null || (i = this.mFilterId) == -1 || (str = LibraryFilters.get(i)) == null || str.length() <= 0) ? contentUri : contentUri.buildUpon().appendPath(str).build();
    }

    protected int getEmptyLayoutID() {
        return getSourceId().equals("cirrus") ? R.layout.library_empty_cirrus : R.layout.library_empty_local;
    }

    protected boolean isEmptyViewDisplayed() {
        return this.mIsEmptyViewDisplayed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showEmptyUI() {
        showEmptyView(View.inflate(getActivity(), getEmptyLayoutID(), null), true);
    }

    protected void showEmptyView(final View view, final boolean centered) {
        View view2 = this.mEmptyView;
        if (view2 != null) {
            this.mRootView.removeView(view2);
        }
        this.mEmptyView = view;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (centered) {
            layoutParams.addRule(13);
        }
        this.mEmptyView.setClickable(false);
        this.mEmptyView.setLayoutParams(layoutParams);
        this.mEmptyView.setOnClickListener(null);
        this.mRootView.addView(this.mEmptyView, 0);
        if (shouldBringEmptyUIToFront()) {
            this.mEmptyView.bringToFront();
        }
        this.mIsEmptyViewDisplayed = true;
    }

    protected void hideEmptyUI() {
        View view = this.mEmptyView;
        if (view != null) {
            this.mRootView.removeView(view);
            this.mIsEmptyViewDisplayed = false;
        }
    }

    protected void onItemClick(AbsListView listView, View view, int position, long id) {
        BadgeableListAdapter.BaseRowViewHolder baseRowViewHolder = (BadgeableListAdapter.BaseRowViewHolder) view.getTag();
        if (baseRowViewHolder == null) {
            return;
        }
        AbstractItem abstractItem = baseRowViewHolder.mLibraryItem;
        setClickedItem(abstractItem);
        boolean zIsSourceLocal = isSourceLocal();
        if (abstractItem == null || (abstractItem instanceof Artist) || (abstractItem instanceof Genre) || (abstractItem instanceof Album) || (abstractItem instanceof Playlist)) {
            onAvailableItemClick(listView, view, position, id);
            return;
        }
        if (!zIsSourceLocal && abstractItem.isAvailable()) {
            playItem(listView, view, position, id, abstractItem);
            return;
        }
        if (zIsSourceLocal && abstractItem.isAvailableOffline()) {
            onAvailableItemClick(listView, view, position, id);
            return;
        }
        if (abstractItem instanceof MusicTrack) {
            MetricsLogger.sendUnavailableSongSelectedEvent(abstractItem.getAsin(), getPageType());
        }
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(abstractItem);
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) {
            if (abstractItem.getDownloadState() == 0) {
                BauhausToastUtils.showTextToast(getActivity(), getApplication().getString(R.string.sd_card_not_found_toast), 1);
                return;
            }
            return;
        }
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST) {
            if (abstractItem.getAsin() == null) {
                Log.warning(TAG, "Corrupted item cannot be redownloaded. Asin is null for contentItem of type");
            } else {
                MusicDownloaderKt.INSTANCE.showQueueInvalidTrackRedownloadDialog(abstractItem.getAsin(), AndroidUtils.getCurrentActivity());
            }
        } else if (playCatalogContentUnavailableReason == ContentUnavailableReason.DOWNLOAD_CORRUPTED) {
            if (abstractItem.getAsin() != null) {
                MusicDownloaderKt.INSTANCE.showQueueInvalidTrackRedownloadDialog(abstractItem.getAsin(), AndroidUtils.getCurrentActivity());
            } else {
                Log.warning(TAG, "Corrupted item cannot be redownloaded. Asin is null for contentItem of type");
            }
        } else if (playCatalogContentUnavailableReason == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE && isWideVineContentIntendedRemote(abstractItem)) {
            playItem(listView, view, position, id, abstractItem);
            return;
        }
        handleDisabledContentError(playCatalogContentUnavailableReason, abstractItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSelectUiClickMetric() {
        if (this instanceof GenreListFragment) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_GENRE).withInteractionType(InteractionType.TAP).withPageType(getPageType()).withEventTime(System.currentTimeMillis()).build());
        }
    }

    protected boolean isWideVineContentIntendedRemote(AbstractItem contentItem) {
        return contentItem.isRemote();
    }

    private void playItem(AbsListView listView, View view, int position, long id, AbstractItem contentItem) {
        if (ConnectivityUtil.hasAnyInternetConnection(getActivity()) || LibraryAccessProviderImpl.getInstance(getActivity()).isAvailableOffline(contentItem, false)) {
            onAvailableItemClick(listView, view, position, id);
        } else {
            ConnectivityUtil.checkConnectivityAndShowDialog(getActivity());
        }
    }

    public void handleDisabledContentError(ContentUnavailableReason reason, AbstractItem item) {
        if (reason == ContentUnavailableReason.DEVICE_NOT_PRIME_AUTHORIZED) {
            this.mJobFinishedCallback = new AccountJobCallback(this, reason, item);
            this.mAccountJobs.checkImmediate(getSession(), this.mJobFinishedCallback);
        } else {
            showContentEnabilityDialog(reason);
        }
    }

    private class AccountJobCallback implements AccountJobController.JobFinishedCallback {
        private ContentUnavailableReason mContentUnavailableReason;
        private AbstractListFragment mFragment;
        private AbstractItem mItem;

        public AccountJobCallback(AbstractListFragment fragment, ContentUnavailableReason reason, AbstractItem item) {
            this.mFragment = fragment;
            this.mContentUnavailableReason = reason;
            this.mItem = item;
        }

        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onSuccess() {
            AbstractItem abstractItem;
            if (this.mContentUnavailableReason != null && (abstractItem = this.mItem) != null) {
                PrimeContentUtil.tryToAutoResolveEnabilityProblem(abstractItem, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.library.fragment.AbstractListFragment.AccountJobCallback.1
                    @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
                    public void resultReceived(boolean contentAvailable, ContentUnavailableReason reasonUnavailable) {
                        if (contentAvailable) {
                            return;
                        }
                        if (!AccountJobCallback.this.mFragment.isPaused() || AccountJobCallback.this.mFragment.mIsAtOnActivityResult) {
                            AccountJobCallback accountJobCallback = AccountJobCallback.this;
                            AbstractListFragment.this.showContentEnabilityDialog(accountJobCallback.mContentUnavailableReason);
                        }
                    }
                });
            }
            AbstractListFragment.this.mJobFinishedCallback = null;
        }

        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onFailure() {
            AbstractListFragment.this.mJobFinishedCallback = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showContentEnabilityDialog(ContentUnavailableReason reason) {
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(getContext(), reason, getDeleteContentHandler(), getClickedItem().getItemTypeName(), getClickedItem(), ContentAccessUtil.ContentAccessOperation.STREAM);
        if (dialogForDisabledReason != null) {
            dialogForDisabledReason.show(getFragmentManager(), (String) null);
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onSyncEvent(Bundle extras) {
        checkShowLoadingText();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onSyncServiceStopped() {
        checkShowLoadingText();
    }

    public AbsListView getListView() {
        return this.mListView;
    }

    @Override // com.amazon.mp3.library.fragment.VisibleListView
    public void scrollToTop() {
        this.mListView.smoothScrollToPositionFromTop(0, 0);
    }

    protected AbsListView getAbsListViewById(View v) {
        return (AbsListView) v.findViewById(R.id.ListView);
    }

    protected final String getCurrentFilter() {
        return this.mCurrentFilterValue;
    }

    protected boolean isEmpty() {
        Adapter adapter = this.mListView.getAdapter();
        return adapter == null || adapter.getCount() <= 0;
    }

    protected void setFilterId(int id) {
        this.mFilterId = id;
        if (!this.mFilterReceiverEnabled) {
            registerReceiver(this.mLibraryFilterSetReceiver, new IntentFilter("com.amazon.mp3.library.ACTION_LIBRARY_FILTER_SET"));
            this.mFilterReceiverEnabled = true;
        }
        this.mCurrentFilterValue = LibraryFilters.get(this.mFilterId);
    }

    protected int getFilterId() {
        return this.mFilterId;
    }

    protected int getContentViewLayoutId() {
        if (getActivity().getResources().getConfiguration().orientation != 2 || getLandscapeLayout() == 0) {
            return this.mUseGridView ? R.layout.library_grid_fragment : R.layout.library_list_fragment;
        }
        return getLandscapeLayout();
    }

    protected View inflateHeader(int layoutId) {
        ViewStub viewStub = (ViewStub) this.mRootView.findViewById(R.id.HeaderViewStub);
        viewStub.setLayoutResource(layoutId);
        return viewStub.inflate();
    }

    protected void checkShowLoadingText() {
        if (!isAdded() || this.mListView == null) {
            return;
        }
        boolean zIsEmpty = isEmpty();
        View emptyView = this.mListView.getEmptyView();
        if (isSyncing()) {
            this.mLoadingText.setText(R.string.dmusic_library_no_listiew_items_syncing);
            this.mLoadingView.setVisibility(0);
            this.mListView.setVisibility(0);
            updateEmptyView(emptyView, true);
            return;
        }
        if (zIsEmpty && shouldShowLoadingText() && isLoading()) {
            this.mLoadingText.setText(R.string.dmusic_library_no_listiew_items_loading);
            this.mLoadingView.setVisibility(0);
            this.mListView.setVisibility(8);
            updateEmptyView(emptyView, true);
            return;
        }
        this.mLoadingView.setVisibility(8);
        this.mListView.setVisibility(0);
        updateEmptyView(emptyView, false);
    }

    protected void setListViewAdapter(Adapter adapter) {
        AbsListView listView = getListView();
        if (listView != null) {
            ListViewUtil.stopScroll(listView);
            listView.setAdapter(adapter);
            if (this.mSavedPosition == -1 || InactivityProvider.isInactive(getActivity())) {
                return;
            }
            listView.setSelection(this.mSavedPosition);
            this.mSavedPosition = -1;
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        AccountJobController accountJobController = this.mAccountJobs;
        if (accountJobController != null) {
            accountJobController.onJobFinished(jobId, job, resultCode, resultBundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        this.mIsAtOnActivityResult = true;
        super.onActivityResult(requestCode, resultCode, data);
        AccountJobController accountJobController = this.mAccountJobs;
        if (accountJobController != null) {
            accountJobController.onActivityResult(getSession(), requestCode, resultCode, data);
        }
        this.mIsAtOnActivityResult = false;
    }

    private boolean isSyncing() {
        return (!isSourceLocal() && SyncService.isRunning() && TextUtils.isEmpty(SyncService.getSyncLastCheckpoint(getContext()))) || ExternalStorageScanService.isRunning();
    }

    private void reloadFilterFromIntent() {
        setFilterId(getArguments().getInt("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void raiseContentUriChanged() {
        if (!isActivated()) {
            this.mContentUriChangedWhileDeactivated = true;
        } else {
            onContentUriChanged();
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void resetContent(final Uri uri, final String sourceId) {
        super.resetContent(uri, sourceId);
        if (uri == null) {
            Log.warning(TAG, "null Uri passed to resetContent()");
        }
        LastViewedScreenUtil.LastViewedSource lastViewedSourceFromCirrusMediaSource = LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(sourceId);
        if (shouldSaveLastViewedSource()) {
            LastViewedScreenUtil.setLastViewed(getApplication(), LastViewedScreenUtil.LastViewedScreen.LIBRARY, lastViewedSourceFromCirrusMediaSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGridItemSelected(View view) {
        Adapter adapter = this.mListView.getAdapter();
        if (adapter instanceof BadgeableListAdapter) {
            ((BadgeableListAdapter) adapter).showGridItemSelected(view);
        }
    }

    private void resetGridSelection() {
        Adapter adapter = this.mListView.getAdapter();
        if (adapter instanceof BadgeableListAdapter) {
            ((BadgeableListAdapter) adapter).resetSelectedGridCover();
        }
    }

    protected AbstractItem getClickedItem() {
        return this.mClickedItem;
    }

    public void setClickedItem(AbstractItem clickedItem) {
        this.mClickedItem = clickedItem;
    }

    @Override // com.amazon.mp3.library.fragment.DeleteContentHandler
    public void deleteContent() {
        if (getClickedItem().isRemote()) {
            if (!ConnectivityUtil.hasAnyInternetConnection()) {
                startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
                return;
            } else {
                deleteCloudContent();
                return;
            }
        }
        deleteDeviceContent();
    }

    private void deleteDeviceContent() {
        DeleteUtil.deleteTracksFromDevice(getActivity(), getClickedItem().getContentUri());
        BauhausToastUtils.showTextToast(getActivity(), getDeleteContentMessage(), 1);
    }

    protected void hideRedownloadRibbon() {
        View view = this.mFeatureBanner;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void showRedownloadDialogAndRibbon() {
        /*
            r4 = this;
            com.amazon.music.subscription.UpsellStrategy r0 = new com.amazon.music.subscription.UpsellStrategy
            com.amazon.music.account.AccountManager r1 = com.amazon.music.account.AccountManagerSingleton.get()
            com.amazon.music.featureflag.FeatureFlagProvider r2 = com.amazon.mp3.capability.FeatureFlagsProvider.getFeatureFlagProvider()
            r0.<init>(r1, r2)
            com.amazon.music.subscription.UpsellStrategy$Upsell r0 = r0.getUpsellType()
            int[] r1 = com.amazon.mp3.library.fragment.AbstractListFragment.AnonymousClass3.$SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L45
            r3 = 2
            if (r0 == r3) goto L43
            r3 = 3
            if (r0 == r3) goto L24
            goto L56
        L24:
            com.amazon.mp3.environment.MusicTerritory r0 = com.amazon.mp3.environment.MusicTerritory.INDIA
            java.lang.String r1 = com.amazon.mp3.account.details.AccountDetailUtil.getMusicTerritoryOfResidence()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L31
            goto L43
        L31:
            com.amazon.mp3.environment.MusicTerritory r0 = com.amazon.mp3.environment.MusicTerritory.CANADA
            java.lang.String r1 = com.amazon.mp3.account.details.AccountDetailUtil.getMusicTerritoryOfResidence()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L3e
            goto L43
        L3e:
            boolean r1 = com.amazon.mp3.prime.Branding.shouldShowPrimeBranding()
            goto L56
        L43:
            r1 = r2
            goto L56
        L45:
            boolean r0 = com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton.isInitialized()
            if (r0 == 0) goto L56
            com.amazon.music.nautilus.SubscriptionManager r0 = com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton.getInstance()
            boolean r0 = r0.isOnHomeOnlyTrial()
            if (r0 == 0) goto L56
            goto L43
        L56:
            if (r1 == 0) goto L59
            return
        L59:
            android.content.Context r0 = r4.getApplication()
            rx.Observable r0 = com.amazon.mp3.library.service.sync.PreWidevineDBUpdateOperation.getMigrationBannerObservable(r0)
            rx.Scheduler r1 = rx.android.schedulers.AndroidSchedulers.mainThread()
            rx.Observable r0 = r0.observeOn(r1)
            com.amazon.mp3.library.fragment.AbstractListFragment$$ExternalSyntheticLambda0 r1 = new com.amazon.mp3.library.fragment.AbstractListFragment$$ExternalSyntheticLambda0
            r1.<init>()
            com.amazon.mp3.library.fragment.AbstractListFragment$$ExternalSyntheticLambda1 r2 = new com.amazon.mp3.library.fragment.AbstractListFragment$$ExternalSyntheticLambda1
            r2.<init>()
            rx.Subscription r0 = r0.subscribe(r1, r2)
            r4.mSubscription = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.fragment.AbstractListFragment.showRedownloadDialogAndRibbon():void");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.AbstractListFragment$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell;

        static {
            int[] iArr = new int[UpsellStrategy.Upsell.values().length];
            $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell = iArr;
            try {
                iArr[UpsellStrategy.Upsell.TO_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell[UpsellStrategy.Upsell.TO_HAWKFIRE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell[UpsellStrategy.Upsell.TO_PRIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRedownloadDialogAndRibbon$2(Boolean bool) {
        View view;
        if (bool.booleanValue()) {
            if (!SettingsUtil.getBooleanPref(getApplication().getApplicationContext(), R.string.setting_key_redownload_popup_dismissed)) {
                Log.debug(TAG, "Should show Redownload pop up as per shared preference.");
                if (getActivity() != null) {
                    WideDialogActivity.showActivity(getActivity(), WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG);
                }
            }
            if (SettingsUtil.getBooleanPref(getContext(), R.string.setting_key_redownload_ribbon_dismissed)) {
                hideRedownloadRibbon();
                return;
            }
            if (this.mFeatureBannerExitToAppIcon == null || (view = this.mFeatureBannerExploreIcon) == null || this.mFeatureBannerDismissIcon == null || this.mFeatureBannerMessage == null || this.mFeatureBanner == null) {
                return;
            }
            view.setVisibility(8);
            this.mFeatureBannerExitToAppIcon.setVisibility(8);
            this.mFeatureBannerDismissIcon.setVisibility(0);
            this.mFeatureBannerMessage.setText(getResources().getText(R.string.dmusic_redownload_offline_title));
            this.mFeatureBanner.setVisibility(0);
            this.mFeatureBannerDismissIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AbstractListFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$showRedownloadDialogAndRibbon$0(view2);
                }
            });
            this.mFeatureBanner.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.AbstractListFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$showRedownloadDialogAndRibbon$1(view2);
                }
            });
            MetricsLogger.migrateExistingDownloadsRibbonViewed();
            return;
        }
        hideRedownloadRibbon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRedownloadDialogAndRibbon$0(View view) {
        UserInteractionAppEvent.builder("selectDismiss").publish();
        SettingsUtil.setBooleanPref(getApplication().getApplicationContext(), R.string.setting_key_redownload_ribbon_dismissed, true);
        hideRedownloadRibbon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRedownloadDialogAndRibbon$1(View view) {
        if (getActivity() != null) {
            WideDialogActivity.showActivity(getActivity(), WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showRedownloadDialogAndRibbon$3(Throwable th) {
        Log.error(TAG, "Following error occurred while finding offline content: " + th.getMessage());
    }
}
