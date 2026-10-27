package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.card.prime.TrackCardAdapter;
import com.amazon.mp3.configuration.PreferenceUpdateProvider;
import com.amazon.mp3.dialog.fragment.NetworkErrorDialogHandler;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.prime.AlbumPaginatedFragment;
import com.amazon.mp3.prime.PlaylistPaginatedFragment;
import com.amazon.mp3.prime.TrackPaginatedFragment;
import com.amazon.mp3.prime.activity.StationPaginatedFragment;
import com.amazon.mp3.prime.browse.PersistentScrollPositionHandler;
import com.amazon.mp3.prime.browse.PrimeMusicPagerFragment;
import com.amazon.mp3.prime.browse.metadata.PrimePagerFragmentDelegate;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BasePrimeFragment extends Fragment implements PrimeMusicPagerFragment, ContextMenuSupportingFragment {
    private RetainedPrimeFragment mCache;
    private ContextMenuHandler mContextMenuHandler;
    protected ScrollView mListView;
    protected LibraryPagerAdapter.OnListViewCreatedListener mListViewCreatedListener;
    protected NavigationProvider mNavigationProvider;
    protected NetworkErrorDialog.INetworkErrorDialogHandler mNetworkErrorDialogHandler;
    private PrimePagerFragmentDelegate mPagerFragmentDelegate;
    private PreferenceUpdateProvider.PreferenceUpdateReceiver mPreferenceUpdateReceiver;
    private PersistentScrollPositionHandler mScrollPositionHandler;
    protected boolean mNeedsRefresh = false;
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.library.fragment.BasePrimeFragment.4
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_TERMS_OF_USE_MARKETPLACE) || set.contains(AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE)) {
                BasePrimeFragment.this.mNeedsRefresh = true;
            } else if (set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
                BasePrimeFragment.this.refreshOnUiThread();
            }
        }
    };

    protected abstract String getFragmentTag();

    protected abstract MediaProvider.PrimeBrowseTracksCollection.ContentSource getPageContentSource();

    protected abstract PageType getPageType();

    @Nullable
    public String getScreenTitle() {
        return null;
    }

    protected abstract void refreshData();

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCache = RetainedPrimeFragment.getRetainedFragment(getActivity().getSupportFragmentManager(), getFragmentTag());
        this.mNetworkErrorDialogHandler = new NetworkErrorDialogHandler(context);
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            this.mNavigationProvider = (NowPlayingFragmentActivity) getActivity();
        } else {
            this.mNavigationProvider = (MusicHomeActivity) getActivity();
        }
        registerLanguagePreferenceUpdateReceiver();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        boolean z;
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        boolean z2 = false;
        if (arguments != null) {
            boolean z3 = arguments.getBoolean("pager_list_view_handler", false);
            z = arguments.getBoolean("persistent_scroll_handler_key", false);
            z2 = z3;
        } else {
            z = false;
        }
        if (z2) {
            PrimePagerFragmentDelegate primePagerFragmentDelegate = new PrimePagerFragmentDelegate();
            this.mPagerFragmentDelegate = primePagerFragmentDelegate;
            LibraryPagerAdapter.OnListViewCreatedListener onListViewCreatedListener = this.mListViewCreatedListener;
            if (onListViewCreatedListener != null) {
                primePagerFragmentDelegate.setOnListViewCreatedListener(onListViewCreatedListener);
            }
        }
        if (z) {
            this.mScrollPositionHandler = new PersistentScrollPositionHandler(getContext(), getFragmentTag());
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mNeedsRefresh) {
            refreshData();
            this.mNeedsRefresh = false;
        }
        if (AccessibilityUtil.isAccessibilityEnabled(getContext())) {
            String screenTitle = getScreenTitle();
            if (TextUtils.isEmpty(screenTitle)) {
                return;
            }
            AccessibilityUtil.announceDelayForAccessibility(getContext(), screenTitle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        setLastScrollPosition(this.mListView.getScrollY());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        PreferenceUpdateProvider.deregisterReceiver(this.mPreferenceUpdateReceiver);
        this.mCache = null;
        this.mNavigationProvider = null;
        this.mNetworkErrorDialogHandler = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mScrollPositionHandler = null;
        this.mPagerFragmentDelegate = null;
        this.mPreferenceUpdateReceiver = null;
        this.mListViewCreatedListener = null;
        this.mContextMenuHandler = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setOnListViewCreatedListener(LibraryPagerAdapter.OnListViewCreatedListener listener) {
        PrimePagerFragmentDelegate primePagerFragmentDelegate = this.mPagerFragmentDelegate;
        if (primePagerFragmentDelegate != null) {
            primePagerFragmentDelegate.setOnListViewCreatedListener(listener);
        } else {
            this.mListViewCreatedListener = listener;
        }
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void scrollToTop() {
        PrimePagerFragmentDelegate primePagerFragmentDelegate = this.mPagerFragmentDelegate;
        if (primePagerFragmentDelegate != null) {
            primePagerFragmentDelegate.scrollToTop();
        }
    }

    protected void setListView(ScrollView listView) {
        PrimePagerFragmentDelegate primePagerFragmentDelegate = this.mPagerFragmentDelegate;
        if (primePagerFragmentDelegate != null) {
            primePagerFragmentDelegate.setListView(listView);
        }
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(View.OnTouchListener listener) {
        PrimePagerFragmentDelegate primePagerFragmentDelegate = this.mPagerFragmentDelegate;
        if (primePagerFragmentDelegate != null) {
            primePagerFragmentDelegate.setupTouchListener(listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onListViewCreated() {
        PrimePagerFragmentDelegate primePagerFragmentDelegate = this.mPagerFragmentDelegate;
        if (primePagerFragmentDelegate != null) {
            primePagerFragmentDelegate.onListViewCreated();
        }
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuSupportingFragment
    public void openContextMenuForView(ContextMenuHandler contextMenuHandler, View view) {
        this.mContextMenuHandler = contextMenuHandler;
        registerForContextMenu(view);
        getActivity().openContextMenu(view);
        unregisterForContextMenu(view);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.mContextMenuHandler == null || !getUserVisibleHint()) {
            return false;
        }
        return this.mContextMenuHandler.onContextMenuItemSelected(menuItem, "cirrus");
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ContextMenuHandler contextMenuHandler = this.mContextMenuHandler;
        if (contextMenuHandler != null) {
            contextMenuHandler.onCreateContextMenu(menu, "cirrus");
        }
    }

    protected RetainedPrimeFragment getCacheMap() {
        return this.mCache;
    }

    protected int getLastScrollPosition() {
        PersistentScrollPositionHandler persistentScrollPositionHandler = this.mScrollPositionHandler;
        if (persistentScrollPositionHandler != null) {
            return persistentScrollPositionHandler.getLastScrollPosition();
        }
        return 0;
    }

    protected void setLastScrollPosition(int scrollPosition) {
        PersistentScrollPositionHandler persistentScrollPositionHandler = this.mScrollPositionHandler;
        if (persistentScrollPositionHandler != null) {
            persistentScrollPositionHandler.setLastScrollPosition(scrollPosition);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scrollToLastPosition() {
        final int lastScrollPosition = getLastScrollPosition();
        ScrollView scrollView = this.mListView;
        if (scrollView != null) {
            scrollView.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.BasePrimeFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    ScrollView scrollView2 = BasePrimeFragment.this.mListView;
                    if (scrollView2 != null) {
                        scrollView2.scrollTo(scrollView2.getScrollX(), lastScrollPosition);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onMoreFooterClicked(PaginatedDataType dataType, PaginatedRankType rankType, String refinementId, String headerText, String asin) {
        onMoreFooterClicked(dataType, rankType, null, refinementId, headerText, asin, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.BasePrimeFragment$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType;

        static {
            int[] iArr = new int[PaginatedDataType.values().length];
            $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType = iArr;
            try {
                iArr[PaginatedDataType.ALBUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.TRACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.STATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected void onMoreFooterClicked(PaginatedDataType dataType, PaginatedRankType rankType, List<Map<String, String>> contentInfo, String refinementId, String headerText, String asin, String widgetId) {
        Fragment fragmentNewInstance;
        int i = AnonymousClass7.$SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[dataType.ordinal()];
        if (i == 1) {
            fragmentNewInstance = AlbumPaginatedFragment.newInstance(rankType, refinementId, headerText, asin, widgetId);
        } else if (i == 2) {
            fragmentNewInstance = TrackPaginatedFragment.newInstance(rankType, refinementId, headerText, getPageContentSource(), asin, widgetId);
        } else if (i == 3) {
            fragmentNewInstance = PlaylistPaginatedFragment.newInstance(rankType, refinementId, headerText, asin, widgetId);
        } else if (i == 4) {
            fragmentNewInstance = StationPaginatedFragment.newInstance(rankType, refinementId, headerText, asin, widgetId);
        } else {
            throw new IllegalArgumentException("Incorrect DataType passed in");
        }
        this.mNavigationProvider.changeScreenFragment(fragmentNewInstance, true, false, false);
        handleSeeAllMetric(refinementId, contentInfo);
    }

    protected void handleSeeAllMetric(String refinementId, List<Map<String, String>> contentInfo) {
        PageType pageType = getPageType();
        if (pageType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_SEE_ALL).withInteractionType(InteractionType.TAP).withPageType(pageType).withPageSubType(refinementId).withContentInfo(contentInfo).withEventTime(System.currentTimeMillis()).build());
        }
    }

    protected void setHeaderView(final int fragmentNameId) {
        ((MusicHomeActivity) getActivity()).setHeaderView(new ActionBarView(getActivity(), getActivity().getBaseContext().getResources().getString(fragmentNameId)));
        ((MusicHomeActivity) getActivity()).requestHomeButtonAsBack();
    }

    protected static <T> List<T> reduceListToCardSize(List<T> data, int minSize) {
        return data.subList(0, Math.min(data.size(), minSize));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshOnUiThread() {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.library.fragment.BasePrimeFragment.5
            @Override // java.lang.Runnable
            public void run() {
                BasePrimeFragment.this.refreshData();
            }
        });
    }

    public class AddButtonListener implements TrackCardAdapter.ItemButtonListener {
        private TrackCardAdapter mAdapter;

        public AddButtonListener() {
        }

        public void setAdapter(TrackCardAdapter adapter) {
            this.mAdapter = adapter;
        }

        @Override // com.amazon.mp3.card.prime.TrackCardAdapter.ItemButtonListener
        public void onClick(final View v, final PrimeTrack track, final int position) {
            if (ContentAccessUtil.canContinueCatalogAction(BasePrimeFragment.this.getActivity(), track, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
                TrackCardAdapter trackCardAdapter = this.mAdapter;
                if (trackCardAdapter != null) {
                    trackCardAdapter.updateItemStatus(track);
                    ((TrackStateProvider) this.mAdapter.getStateProvider()).setAddingToLibrary(track, true);
                }
                new AddAndDownloadTrackTask((Context) BasePrimeFragment.this.getActivity(), track.getAsin(), false, true, (OnItemFinishedAddingListener) null).execute(new Void[0]);
                MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, track.getAsin(), SelectionSourceType.SONGS, null);
            }
        }
    }

    private void registerLanguagePreferenceUpdateReceiver() {
        PreferenceUpdateProvider.PreferenceUpdateReceiver preferenceUpdateReceiver = new PreferenceUpdateProvider.PreferenceUpdateReceiver() { // from class: com.amazon.mp3.library.fragment.BasePrimeFragment.6
            @Override // com.amazon.mp3.configuration.PreferenceUpdateProvider.PreferenceUpdateReceiver
            public void onReceive() {
                BasePrimeFragment.this.refreshData();
            }
        };
        this.mPreferenceUpdateReceiver = preferenceUpdateReceiver;
        PreferenceUpdateProvider.registerReceiver(preferenceUpdateReceiver);
    }

    public static abstract class BaseBuilder {
        protected final Bundle bundle;

        public abstract BasePrimeFragment build();

        public BaseBuilder(@NonNull final Bundle bundle) {
            this.bundle = bundle;
        }

        public BaseBuilder saveFragmentScrollPosition(boolean savePosition) {
            this.bundle.putBoolean("persistent_scroll_handler_key", savePosition);
            return this;
        }

        public BaseBuilder usePagerListView(boolean usePagerListView) {
            this.bundle.putBoolean("pager_list_view_handler", usePagerListView);
            return this;
        }
    }
}
