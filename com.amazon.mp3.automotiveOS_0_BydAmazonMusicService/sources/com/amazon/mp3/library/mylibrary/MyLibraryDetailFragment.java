package com.amazon.mp3.library.mylibrary;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.catalog.fragment.LibraryArtistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.feed.FeedButton;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.ShovelerExtensionFunctionsKt;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.fragment.LibraryFragmentFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.CompositeOnSourceChangedListener;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.view.BauhausRibbon;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.Outcome;
import com.amazon.music.subscription.UpsellStrategy;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import java.util.NoSuchElementException;
import java.util.Set;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class MyLibraryDetailFragment extends BaseFragment implements ActionBarSourceToggleView.SourceChangedListener {
    private static final String TAG = MyLibraryDetailFragment.class.getSimpleName();
    private Category category;
    private FeedButton feedButton;
    private ActionBarSourceToggleView mActionbarSourceToggleView;
    private BauhausRibbon mBauhausRibbon;
    private View mFeatureBanner;
    private View mFeatureBannerDismissIcon;
    private View mFeatureBannerExitToAppIcon;
    private View mFeatureBannerExploreIcon;
    private TextView mFeatureBannerMessage;
    private View mHeaderContainer;
    private TextView mHeaderTextView;
    private CompositeOnSourceChangedListener mListener;
    private StyleSheet mStyleSheet;
    private Subscription mStylesheetSubscription;
    private Subscription mSubscription;
    private MyLibraryDetailViewModel mViewModel;
    private Uri myLikesPlaylistUri;
    private LastViewedScreenUtil.OnLastViewedSourceChangedListener mOnLastViewedSourceChangedListener = createOnLastViewedSourceChangedListener();
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            FragmentActivity activity;
            if (!set.contains(AccountStateField.USER_BENEFITS) || (activity = MyLibraryDetailFragment.this.getActivity()) == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment.1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (MyLibraryDetailFragment.this.isActivated()) {
                        MyLibraryDetailFragment.this.refreshRedownloadRibbon();
                    }
                }
            });
        }
    };

    public enum Category {
        PLAYLISTS,
        ALBUMS,
        ARTISTS,
        SONGS
    }

    protected boolean shouldSaveLastViewedSource() {
        return false;
    }

    public static MyLibraryDetailFragment newInstance(final Bundle bundle, final String header, final Category content) {
        return new Builder(bundle, header, content).build();
    }

    static class Builder {
        private final Bundle bundle;
        private final Category category;
        private final String title;

        public Builder(final Bundle bundle, final String title, final Category category) {
            this.bundle = bundle;
            this.title = title;
            this.category = category;
        }

        public MyLibraryDetailFragment build() {
            MyLibraryDetailFragment myLibraryDetailFragment = new MyLibraryDetailFragment();
            this.bundle.putString("categoryTitleKey", this.title.toUpperCase());
            myLibraryDetailFragment.category = this.category;
            myLibraryDetailFragment.setArguments(this.bundle);
            return myLibraryDetailFragment;
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
        this.mListener = new CompositeOnSourceChangedListener();
        initStyleSheet();
        if (getActivity() != null) {
            this.mViewModel = (MyLibraryDetailViewModel) new ViewModelProvider(getActivity()).get(MyLibraryDetailViewModel.class);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isBauhausLibraryEnabled()) {
            return;
        }
        this.mActionbarSourceToggleView = new ActionBarSourceToggleView(getActivity(), getFragmentManager(), AmazonApplication.getCapabilities().isOnlineMusicEnabled());
        CompositeOnSourceChangedListener compositeOnSourceChangedListener = this.mListener;
        if (compositeOnSourceChangedListener != null) {
            compositeOnSourceChangedListener.addSourceChangedListener(this);
            this.mActionbarSourceToggleView.setCompositeSourceChangedListener(this.mListener);
        }
        setHasOptionsMenu(getHasOptionsMenu());
    }

    private void cleanup() {
        FeedButton feedButton = this.feedButton;
        if (feedButton != null) {
            feedButton.removeObservers(getActivity());
            this.feedButton = null;
        }
        Subscription subscription = this.mSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.mSubscription.unsubscribe();
        }
        this.mSubscription = null;
        Subscription subscription2 = this.mStylesheetSubscription;
        if (subscription2 != null && !subscription2.isUnsubscribed()) {
            this.mStylesheetSubscription.unsubscribe();
        }
        this.mStylesheetSubscription = null;
        this.mStyleSheet = null;
        LastViewedScreenUtil.OnLastViewedSourceChangedListener onLastViewedSourceChangedListener = this.mOnLastViewedSourceChangedListener;
        if (onLastViewedSourceChangedListener != null) {
            LastViewedScreenUtil.removeViewSourceChangeListener(onLastViewedSourceChangedListener);
            this.mOnLastViewedSourceChangedListener = null;
        }
        if (this.mAccountStateChangeListener != null) {
            AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
            this.mAccountStateChangeListener = null;
        }
        CompositeOnSourceChangedListener compositeOnSourceChangedListener = this.mListener;
        if (compositeOnSourceChangedListener != null) {
            compositeOnSourceChangedListener.removeAllSourceChangedListeners();
            this.mListener = null;
        }
        ActionBarSourceToggleView actionBarSourceToggleView = this.mActionbarSourceToggleView;
        if (actionBarSourceToggleView != null) {
            actionBarSourceToggleView.setCompositeSourceChangedListener(null);
            this.mActionbarSourceToggleView = null;
        }
        BauhausRibbon bauhausRibbon = this.mBauhausRibbon;
        if (bauhausRibbon != null) {
            bauhausRibbon.setDismissButtonClickListener(null);
            this.mBauhausRibbon = null;
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        setDisplayHomeAsUpEnabled(false);
        cleanup();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        cleanup();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!isBauhausLibraryEnabled()) {
            this.feedButton = new FeedButton(this.mActionbarSourceToggleView, getActivity());
            setDisplayHomeAsUpEnabled(true);
        }
        refreshRedownloadRibbon();
        if (this.mOnLastViewedSourceChangedListener == null) {
            this.mOnLastViewedSourceChangedListener = createOnLastViewedSourceChangedListener();
        }
        LastViewedScreenUtil.registerViewSourceChangeListener(this.mOnLastViewedSourceChangedListener);
    }

    private LastViewedScreenUtil.OnLastViewedSourceChangedListener createOnLastViewedSourceChangedListener() {
        return new LastViewedScreenUtil.OnLastViewedSourceChangedListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment.2
            @Override // com.amazon.mp3.activity.settings.LastViewedScreenUtil.OnLastViewedSourceChangedListener
            public void onLastViewChanged() {
                if (MyLibraryDetailFragment.this.mActionbarSourceToggleView != null) {
                    MyLibraryDetailFragment.this.mActionbarSourceToggleView.updateSourceText();
                }
            }
        };
    }

    private void setDisplayHomeAsUpEnabled(final boolean enabled) {
        ActionBar supportActionBar = getActivity() != null ? ((AppCompatActivity) getActivity()).getSupportActionBar() : null;
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(enabled);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshRedownloadRibbon() {
        View view;
        if (SettingsUtil.shouldShowRedownloadUIUntilAcknowledged(getContext())) {
            Log.debug(TAG, "Should show Redownload UI unless already shown and user already acknowledged, as per shared preference.");
            showRedownloadDialogAndRibbon();
        } else {
            if (!SettingsUtil.getBooleanPref(getContext(), R.string.setting_key_redownload_ribbon_dismissed) || (view = this.mFeatureBanner) == null) {
                return;
            }
            view.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.my_music_detail_view, container, false);
        observeLibraryData();
        observeMyLikesPlaylistUri();
        return viewInflate;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View viewFindViewById = view.findViewById(R.id.feature_banner);
        this.mFeatureBanner = viewFindViewById;
        if (viewFindViewById != null) {
            TextView textView = (TextView) viewFindViewById.findViewById(R.id.feature_banner_message);
            this.mFeatureBannerMessage = textView;
            textView.setGravity(8388627);
            this.mFeatureBannerExitToAppIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_icon);
            this.mFeatureBannerDismissIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_icon_dismiss);
            this.mFeatureBannerExploreIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_view);
        }
        setupBauhausRibbon(view);
        this.mHeaderContainer = view.findViewById(R.id.container_header);
        if (!isBauhausLibraryEnabled()) {
            TextView textView2 = (TextView) this.mHeaderContainer.findViewById(R.id.category_text);
            this.mHeaderTextView = textView2;
            textView2.setText(getArguments().getString("categoryTitleKey"));
            this.mHeaderTextView.setGravity(17);
            this.mHeaderContainer.setVisibility(0);
        } else {
            this.mHeaderContainer.setVisibility(8);
            TextView textView3 = (TextView) this.mHeaderContainer.findViewById(R.id.category_text);
            this.mHeaderTextView = textView3;
            textView3.setId(R.id.deprecated_view);
        }
        Category category = this.category;
        if (category != null) {
            updateContent(category);
        }
    }

    private void updateContent(final Category category) {
        Fragment songListFragment;
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (Category.PLAYLISTS.equals(category)) {
            songListFragment = getPlaylistListFragment();
        } else if (Category.ARTISTS.equals(category)) {
            songListFragment = getArtistListFragment();
        } else if (Category.ALBUMS.equals(category)) {
            songListFragment = getAlbumListFragment();
        } else {
            songListFragment = Category.SONGS.equals(category) ? getSongListFragment() : null;
        }
        if (songListFragment != null) {
            childFragmentManager.beginTransaction().replace(R.id.contentView, songListFragment).commit();
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Subscription subscription = this.mSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.mSubscription.unsubscribe();
        }
        Subscription subscription2 = this.mStylesheetSubscription;
        if (subscription2 != null && !subscription2.isUnsubscribed()) {
            this.mStylesheetSubscription.unsubscribe();
        }
        LastViewedScreenUtil.removeViewSourceChangeListener(this.mOnLastViewedSourceChangedListener);
    }

    private boolean getHasOptionsMenu() {
        return AmazonApplication.getCapabilities().shouldUseGridView();
    }

    @Override // com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        LastViewedScreenUtil.LastViewedSource lastViewedSourceFromCirrusMediaSource = LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(source == ActionBarSourceToggleView.Source.CLOUD ? "cirrus" : "cirrus-local");
        if (shouldSaveLastViewedSource()) {
            LastViewedScreenUtil.setLastViewed(getApplication(), LastViewedScreenUtil.LastViewedScreen.LIBRARY, lastViewedSourceFromCirrusMediaSource);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void showRedownloadDialogAndRibbon() {
        /*
            r4 = this;
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            if (r0 == 0) goto L95
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            boolean r0 = r0.isFinishing()
            if (r0 != 0) goto L95
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            boolean r0 = r0.isDestroyed()
            if (r0 == 0) goto L1c
            goto L95
        L1c:
            com.amazon.music.subscription.UpsellStrategy r0 = new com.amazon.music.subscription.UpsellStrategy
            com.amazon.music.account.AccountManager r1 = com.amazon.music.account.AccountManagerSingleton.get()
            com.amazon.music.featureflag.FeatureFlagProvider r2 = com.amazon.mp3.capability.FeatureFlagsProvider.getFeatureFlagProvider()
            r0.<init>(r1, r2)
            com.amazon.music.subscription.UpsellStrategy$Upsell r0 = r0.getUpsellType()
            int[] r1 = com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment.AnonymousClass3.$SwitchMap$com$amazon$music$subscription$UpsellStrategy$Upsell
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L61
            r3 = 2
            if (r0 == r3) goto L5f
            r3 = 3
            if (r0 == r3) goto L40
            goto L72
        L40:
            com.amazon.mp3.environment.MusicTerritory r0 = com.amazon.mp3.environment.MusicTerritory.INDIA
            java.lang.String r1 = com.amazon.mp3.account.details.AccountDetailUtil.getMusicTerritoryOfResidence()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L4d
            goto L5f
        L4d:
            com.amazon.mp3.environment.MusicTerritory r0 = com.amazon.mp3.environment.MusicTerritory.CANADA
            java.lang.String r1 = com.amazon.mp3.account.details.AccountDetailUtil.getMusicTerritoryOfResidence()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L5a
            goto L5f
        L5a:
            boolean r1 = com.amazon.mp3.prime.Branding.shouldShowPrimeBranding()
            goto L72
        L5f:
            r1 = r2
            goto L72
        L61:
            boolean r0 = com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton.isInitialized()
            if (r0 == 0) goto L72
            com.amazon.music.nautilus.SubscriptionManager r0 = com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton.getInstance()
            boolean r0 = r0.isOnHomeOnlyTrial()
            if (r0 == 0) goto L72
            goto L5f
        L72:
            if (r1 == 0) goto L75
            return
        L75:
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            rx.Observable r0 = com.amazon.mp3.library.service.sync.PreWidevineDBUpdateOperation.getMigrationBannerObservable(r0)
            rx.Scheduler r1 = rx.android.schedulers.AndroidSchedulers.mainThread()
            rx.Observable r0 = r0.observeOn(r1)
            com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda4 r1 = new com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda4
            r1.<init>()
            com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda5 r2 = new com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda5
            r2.<init>()
            rx.Subscription r0 = r0.subscribe(r1, r2)
            r4.mSubscription = r0
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment.showRedownloadDialogAndRibbon():void");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$3, reason: invalid class name */
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
            if (!SettingsUtil.getBooleanPref(getContext().getApplicationContext(), R.string.setting_key_redownload_popup_dismissed)) {
                Log.debug(TAG, "Should show Redownload pop up as per shared preference.");
                if (getActivity() != null) {
                    WideDialogActivity.showActivity(getActivity(), WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG);
                }
            }
            if (SettingsUtil.getBooleanPref(getContext(), R.string.setting_key_redownload_ribbon_dismissed)) {
                View view2 = this.mFeatureBanner;
                if (view2 != null) {
                    view2.setVisibility(8);
                    return;
                }
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
            this.mFeatureBannerDismissIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    this.f$0.lambda$showRedownloadDialogAndRibbon$0(view3);
                }
            });
            this.mFeatureBanner.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    this.f$0.lambda$showRedownloadDialogAndRibbon$1(view3);
                }
            });
            MetricsLogger.migrateExistingDownloadsRibbonViewed();
            return;
        }
        View view3 = this.mFeatureBanner;
        if (view3 != null) {
            view3.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRedownloadDialogAndRibbon$0(View view) {
        UserInteractionAppEvent.builder("selectDismiss").publish();
        SettingsUtil.setBooleanPref(getContext().getApplicationContext(), R.string.setting_key_redownload_ribbon_dismissed, true);
        View view2 = this.mFeatureBanner;
        if (view2 != null) {
            view2.setVisibility(8);
        }
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

    private boolean isBauhausLibraryEnabled() {
        return AmazonApplication.getCapabilities().isBauhausLibraryEnabled();
    }

    public String getPageTitle() {
        return getArguments().getString("categoryTitleKey");
    }

    private void initStyleSheet() {
        this.mStylesheetSubscription = StyleSheetProvider.getStyleSheetObservable().observeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$initStyleSheet$4((StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initStyleSheet$4(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
    }

    private void setupBauhausRibbon(final View parent) {
        if (getPageTitle().equalsIgnoreCase(getString(R.string.dmusic_library_songs_tab))) {
            final int songsBannerDismissCount = LibraryPreferences.INSTANCE.readSongsBannerDismissCount(requireContext());
            if (songsBannerDismissCount < 3 && AmazonApplication.getCapabilities().isLikesEverywhereEnabled() && ConnectivityUtil.hasAnyInternetConnection(requireContext())) {
                BauhausRibbon bauhausRibbon = (BauhausRibbon) parent.findViewById(R.id.bauhaus_ribbon);
                this.mBauhausRibbon = bauhausRibbon;
                bauhausRibbon.initViewStyling(this.mStyleSheet);
                this.mBauhausRibbon.setRibbonTextAlignment(2);
                this.mBauhausRibbon.setRibbonTextStyle(this.mStyleSheet, FontStyleKey.SECONDARY);
                this.mBauhausRibbon.setRoundedBackground();
                this.mBauhausRibbon.setRibbonText(getString(R.string.dmusic_songs_page_educational_banner));
                this.mBauhausRibbon.setRibbonDismissIcon(Integer.valueOf(R.drawable.ic_action_cancel2));
                this.mBauhausRibbon.setRibbonNavigationIcon(Integer.valueOf(R.drawable.ic_chevron_right));
                this.mBauhausRibbon.setRibbonClickListener(null);
                if (getContext() != null) {
                    this.mViewModel.updateLibraryArtistsEmptyLiveDataValue(getContext().getContentResolver());
                }
                this.mBauhausRibbon.setNavigationButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setupBauhausRibbon$5(view);
                    }
                });
                this.mBauhausRibbon.setDismissButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$setupBauhausRibbon$6(songsBannerDismissCount, view);
                    }
                });
                return;
            }
            dismissBauhausRibbon();
            return;
        }
        dismissBauhausRibbon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupBauhausRibbon$5(View view) {
        MetricsLogger.sendEvent(UiClickEvent.builder(ActionType.GO_MY_LIKES_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(PageType.CLOUD_LIBRARY_SONGS).withEventTime(System.currentTimeMillis()).withContentInfo(MetricsLogger.getContentInfo(ContentName.LIBRARY_BANNER.getMetricsValue())).build());
        Uri uri = this.myLikesPlaylistUri;
        if (uri != null) {
            navigateToMyLikesPlaylist(uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupBauhausRibbon$6(int i, View view) {
        MetricsLogger.sendEvent(UiClickEvent.builder(ActionType.DISMISS_LIBRARY_SONGS_BANNER).withInteractionType(InteractionType.TAP).withPageType(PageType.CLOUD_LIBRARY_SONGS).withEventTime(System.currentTimeMillis()).withContentInfo(MetricsLogger.getContentInfo(ContentName.LIBRARY_BANNER.getMetricsValue())).build());
        LibraryPreferences.INSTANCE.writeSongsBannerDismissCount(requireContext(), i + 1);
        dismissBauhausRibbon();
    }

    private void observeMyLikesPlaylistUri() {
        this.mViewModel.getMyLikesPlaylistLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$observeMyLikesPlaylistUri$7((Outcome) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observeMyLikesPlaylistUri$7(Outcome outcome) {
        if (outcome instanceof Outcome.Success) {
            Uri uri = (Uri) ((Outcome.Success) outcome).getValue();
            if (uri != null && Boolean.FALSE.equals(this.mViewModel.isLibraryArtistsEmptyLiveData().getValue())) {
                showBauhausRibbon();
                this.myLikesPlaylistUri = uri;
                return;
            } else {
                dismissBauhausRibbon();
                return;
            }
        }
        dismissBauhausRibbon();
    }

    private void observeLibraryData() {
        if (getContext() != null) {
            this.mViewModel.isLibraryArtistsEmptyLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.mylibrary.MyLibraryDetailFragment$$ExternalSyntheticLambda6
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$observeLibraryData$8((Boolean) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observeLibraryData$8(Boolean bool) {
        if (Boolean.FALSE.equals(bool) && getContext() != null) {
            try {
                this.mViewModel.fetchMyLikesPlaylist(getContext(), "cirrus");
                return;
            } catch (NoSuchElementException unused) {
                dismissBauhausRibbon();
                return;
            }
        }
        dismissBauhausRibbon();
    }

    private void navigateToMyLikesPlaylist(Uri myLikesPlaylistUri) {
        if (getActivity() == null || !(getActivity() instanceof NavigationActivity) || myLikesPlaylistUri == null) {
            return;
        }
        ShovelerExtensionFunctionsKt.navigateToPlaylist((NavigationActivity) getActivity(), myLikesPlaylistUri);
    }

    private void showBauhausRibbon() {
        if (this.mBauhausRibbon == null || !getPageTitle().equalsIgnoreCase(getString(R.string.dmusic_library_songs_tab))) {
            return;
        }
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.BANNER).withContentName(ContentName.LIBRARY_BANNER).withImpressionTimestamp(System.currentTimeMillis()).withPageType(PageType.CLOUD_LIBRARY_SONGS.getMetricsValue()).build());
        this.mBauhausRibbon.setVisibility(0);
    }

    private void dismissBauhausRibbon() {
        BauhausRibbon bauhausRibbon = this.mBauhausRibbon;
        if (bauhausRibbon != null) {
            bauhausRibbon.setVisibility(8);
        }
    }

    private Fragment getPlaylistListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryPlaylistsFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Playlists.getFilterContentUri(getSourceId(), "");
        }
        Fragment listFragment = getListFragment(filterContentUri);
        Bundle arguments = listFragment.getArguments();
        if (!AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", false);
        }
        return listFragment;
    }

    private Fragment getArtistListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryArtistsFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Artists.getFilterContentUri(getSourceId(), "");
        }
        return getListFragment(filterContentUri);
    }

    private Fragment getAlbumListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryAlbumsFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Albums.getFilterContentUri(getSourceId(), "");
        }
        return getListFragment(filterContentUri);
    }

    private Fragment getSongListFragment() {
        Uri filterContentUri;
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryTracksFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Tracks.getFilterContentUri(getSourceId(), "");
        }
        Fragment fragment = LibraryFragmentFactory.getFragment(getActivity(), filterContentUri);
        Bundle arguments = fragment.getArguments();
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
        } else {
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_SHUFFLE", true);
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", false);
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        }
        return fragment;
    }

    private Fragment getListFragment(Uri uri) {
        Fragment fragment = LibraryFragmentFactory.getFragment(getActivity(), uri);
        Bundle arguments = fragment.getArguments();
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", uri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
        } else {
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", uri);
            arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", getSourceId());
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", !(AmazonApplication.getCapabilities().shouldUseGridView() ? SettingsUtil.getBooleanPref(R.string.setting_key_listview_selected_library) : true));
            arguments.putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", true);
        }
        return fragment;
    }

    private String getSourceId() {
        return LastViewedScreenUtil.getLastViewedSource(getActivity(), LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE).toCirrusMediaSource();
    }
}
