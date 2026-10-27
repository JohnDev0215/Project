package com.amazon.mp3.bottomnavbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.tracing.Trace;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.MarketAlexaUiListener;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.bottomnavbar.tabbar.Tab;
import com.amazon.mp3.bottomnavbar.tabbar.TabListener;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.store.html5.activity.StoreFragment;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.voice.AlexaIngressHelper;
import com.amazon.mp3.voice.VoiceManagerFactory;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.music.voice.VoiceManagerSingleton;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.Validate;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class BottomNavigationBarContainer {
    private static final String TAG = "BottomNavigationBarContainer";
    private static BottomNavigationBarContainer mContainerInstance = null;
    private static Boolean mLastToolTipDismissed = null;
    private static String mLastToolTipString = null;
    private static int mLastToolTipTabIndex = -1;
    private AccountJobController mAccountJobController;
    private AccountStateChangeListener mAccountStateChangeListener;
    private AccountStateChangeListener mAccountStateChangeUserBenefitsListener;
    private FragmentActivity mActivity;
    private BottomNavigationBar mBottomBar;
    private View mBottomBarLowerMask;
    private View mBottomBarUpperMask;
    private BottomBarTab mCurrentTab;
    private Handler mForegroundHandler;
    private FragmentController mFragmentController;
    private boolean mIsAlexaEnabled;
    private MarketAlexaUiListener mMarketAlexaUiListener;
    private NoConnectionDialog mNoConnectionDialog;
    private BottomBarUIListener mUIListener;
    private AccountJobController.JobFinishedCallback mAlexaAccountJobFinishedCallback = new AccountJobController.JobFinishedCallback() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.1
        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onSuccess() {
            BottomNavigationBarContainer.this.mAccountJobController.removeCallback(this);
            new AlexaIngressHelper(BottomNavigationBarContainer.this.mActivity).handleAlexaIngress(AlexaEntryPoint.BOTTOM_BAR);
        }

        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onFailure() {
            Log.error(BottomNavigationBarContainer.TAG, "Failed to checkImmediate on AccountJobController");
        }
    };
    private BroadcastReceiver toolTipReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra;
            if (intent == null || (stringExtra = intent.getStringExtra("NAVIGATION_INTENT_FLAG")) == null) {
                return;
            }
            switch (stringExtra) {
                case "ADD_PODCAST_NAVIGATION_TOAST_FLAG":
                    String unused = BottomNavigationBarContainer.mLastToolTipString = context.getString(R.string.podcast_add_onboarding_text);
                    int unused2 = BottomNavigationBarContainer.mLastToolTipTabIndex = Tab.LIBRARY.getTabPosition();
                    break;
                case "DOWNLOAD_PODCAST_NAVIGATION_TOAST_FLAG":
                    String unused3 = BottomNavigationBarContainer.mLastToolTipString = context.getString(R.string.podcast_download_onboarding_text);
                    int unused4 = BottomNavigationBarContainer.mLastToolTipTabIndex = Tab.LIBRARY.getTabPosition();
                    break;
                case "FOLLOW_PODCAST_NAVIGATION_TOAST_FLAG":
                    String unused5 = BottomNavigationBarContainer.mLastToolTipString = context.getString(R.string.podcast_follow_onboarding_text);
                    int unused6 = BottomNavigationBarContainer.mLastToolTipTabIndex = Tab.LIBRARY.getTabPosition();
                    break;
            }
            if (BottomNavigationBarContainer.mLastToolTipTabIndex == -1 || BottomNavigationBarContainer.mLastToolTipString == null) {
                return;
            }
            BottomNavigationBarContainer.this.mBottomBar.showToolTip(BottomNavigationBarContainer.mLastToolTipString, BottomNavigationBarContainer.mLastToolTipTabIndex);
        }
    };
    private final boolean mEnabled = true;

    public static synchronized BottomNavigationBarContainer getInstance() {
        if (mContainerInstance == null) {
            mContainerInstance = new BottomNavigationBarContainer();
        }
        return mContainerInstance;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("LAST_TOOLTIP_STRING", mLastToolTipString);
        bundle.putInt("LAST_TOOLTIP_INDEX", mLastToolTipTabIndex);
        bundle.putBoolean("TOOLTIP_DISMISS_STATE_KEY", this.mBottomBar.getToolTipDismissState().booleanValue());
    }

    public static void onRestoreInstanceState(Bundle bundle) {
        mLastToolTipDismissed = Boolean.valueOf(bundle.getBoolean("TOOLTIP_DISMISS_STATE_KEY"));
        mLastToolTipString = bundle.getString("LAST_TOOLTIP_STRING");
        mLastToolTipTabIndex = bundle.getInt("LAST_TOOLTIP_INDEX");
    }

    public void attach(@NonNull final FragmentActivity activity, @NonNull final ViewGroup parentView, @NonNull final View[] maskViews, @NonNull FragmentController fragmentController, @NonNull BottomBarUIListener uiListener) {
        if (this.mEnabled) {
            Validate.notNull(activity);
            Validate.notNull(fragmentController);
            this.mActivity = activity;
            this.mUIListener = uiListener;
            this.mFragmentController = fragmentController;
            Log.debug(TAG, "Attaching bottom nav bar container to %s [%s]", activity.getClass().getSimpleName(), activity.toString());
            if (this.mActivity instanceof MusicHomeActivity) {
                this.mAccountJobController = new AccountJobController((MusicHomeActivity) this.mActivity);
            }
            loadLastViewedTab();
            LocalBroadcastManager.getInstance(this.mActivity).registerReceiver(this.toolTipReceiver, new IntentFilter("NAVIGATION_EVENT_INTENT_FILTER"));
            this.mBottomBar = BottomNavigationBar.INSTANCE.attach(this.mActivity, parentView, AmazonApplication.getCapabilities().isAlexaEnabled());
            this.mBottomBarLowerMask = maskViews[0];
            this.mBottomBarUpperMask = maskViews[1];
            this.mForegroundHandler = new Handler(this.mActivity.getApplicationContext().getMainLooper());
            setUpBrowseTab();
            setUpFindTab();
            setUpMyLibraryTab();
            showUpsellTabIfNeeded();
            refreshAlexa();
            if (!UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace()) {
                this.mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.3
                    @Override // com.amazon.music.account.AccountStateChangeListener
                    public void onAccountStateChange(final Set<AccountStateField> set) {
                        if (set.contains(AccountStateField.USER_IS_IN_PRIME_MARKETPLACE) || set.contains(AccountStateField.USER_IS_IN_HAWKFIRE_MARKETPLACE)) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (BottomNavigationBarContainer.this.mBottomBar != null) {
                                        BottomNavigationBarContainer.this.refreshBottomBar();
                                    }
                                }
                            });
                            BottomNavigationBarContainer.this.deregisterAccountStateChangeListener();
                        }
                    }
                };
                AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
            }
            setAccountStateChangedUserBenefitsListener();
            refreshBottomBar();
            this.mBottomBar.setupToolTipView();
            showLastToolTip();
        }
    }

    public void showLastToolTip() {
        int i;
        String str;
        Boolean bool = mLastToolTipDismissed;
        if (bool == null || bool.booleanValue() || (i = mLastToolTipTabIndex) == -1 || (str = mLastToolTipString) == null) {
            return;
        }
        this.mBottomBar.showToolTip(str, i);
    }

    public View getRightView() {
        return this.mBottomBar.getRightView();
    }

    public void refreshAlexa() {
        if (this.mBottomBar == null) {
            return;
        }
        boolean zIsAlexaEnabled = AmazonApplication.getCapabilities().isAlexaEnabled();
        this.mIsAlexaEnabled = zIsAlexaEnabled;
        if (zIsAlexaEnabled && !ChildUserUtil.INSTANCE.isChildUser(this.mActivity)) {
            this.mBottomBar.setButton(Tab.ALEXA, new TabListener() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.4
                @Override // com.amazon.mp3.bottomnavbar.tabbar.TabListener
                public boolean onTabSelected() {
                    return BottomNavigationBarContainer.this.onAlexaTabSelected();
                }
            }, 3);
        }
        if (this.mIsAlexaEnabled) {
            if (!VoiceManagerSingleton.isInitialized() || VoiceManagerFactory.shouldRefresh()) {
                VoiceManagerFactory.initializeVoiceManager(this.mActivity.getApplicationContext());
            }
            if (VoiceManagerSingleton.isInitialized()) {
                setUpAlexaTab();
            } else {
                Log.error(TAG, "The VoiceManger is not initialized.");
            }
        }
    }

    private void setUpAlexaTab() {
        if (this.mMarketAlexaUiListener != null) {
            return;
        }
        this.mMarketAlexaUiListener = new MarketAlexaUiListener(this.mBottomBar);
        VoiceManagerSingleton.getInstance().addAlexaUiListener(this.mMarketAlexaUiListener);
    }

    private void showUpsellTabIfNeeded() {
        if (!DynamicMessagingManager.isDynamicMessagingSupported()) {
            this.mBottomBar.disablePersistentUpsellTab();
            return;
        }
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Tab tab = Tab.UPGRADE;
        if (Feature.persistent_upsell_prime_sonic_rush_only.isEnabled()) {
            tab.setTabTextResource(R.string.upgrade_from_prime_tier);
            configureUpsellTabForUpsell(tab, true);
            return;
        }
        if (userSubscription.isNightwingNonHome()) {
            configureUpsellTabForUpsell(tab, false);
            return;
        }
        if (Feature.persistent_upgrade_dm_blocking_upsell_amu_home_only.isEnabled()) {
            configureUpsellTabForUpgrade(tab, false);
            return;
        }
        if (Feature.persistent_upgrade_dm_non_blocking_upsell_amu_home_only.isEnabled()) {
            configureUpsellTabForUpgrade(tab, true);
        } else {
            if (Feature.persistent_upgrade_msf_plan_comparison_amu_home_only.isEnabled()) {
                configurePlanComparisonWebviewUpsell(tab);
                return;
            }
            if (this.mCurrentTab == BottomBarTab.UPSELL) {
                navigateTo(BottomBarTab.BROWSE);
            }
            this.mBottomBar.disablePersistentUpsellTab();
        }
    }

    private void configureUpsellTab(Tab upsellTab, final Runnable clickMetricEmitter, final Callable<Boolean> onTabSelect) {
        UpsellUtil.emitPersistentUpsellTriggerEvent();
        this.mBottomBar.setButton(upsellTab, new TabListener() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.5
            @Override // com.amazon.mp3.bottomnavbar.tabbar.TabListener
            public boolean onTabSelected() {
                clickMetricEmitter.run();
                BottomNavigationBarContainer.this.mCurrentTab = BottomBarTab.UPSELL;
                try {
                    return ((Boolean) onTabSelect.call()).booleanValue();
                } catch (Exception e) {
                    Log.error(BottomNavigationBarContainer.TAG, "Error executing upsell tab callable", e);
                    return false;
                }
            }
        }, 4);
    }

    private void configurePlanComparisonWebviewUpsell(Tab upsellTab) {
        configureUpsellTab(upsellTab, new BottomNavigationBarContainer$$ExternalSyntheticLambda2(), new Callable() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$configurePlanComparisonWebviewUpsell$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$configurePlanComparisonWebviewUpsell$0() throws Exception {
        UpsellUtil.showPlanComparisonUpgradeUpsell(this.mActivity);
        return Boolean.FALSE;
    }

    private void configureUpsellTabForUpgrade(Tab upgradeTab, final boolean nonBlocking) {
        configureUpsellTab(upgradeTab, new BottomNavigationBarContainer$$ExternalSyntheticLambda2(), new Callable() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$configureUpsellTabForUpgrade$1(nonBlocking);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$configureUpsellTabForUpgrade$1(boolean z) throws Exception {
        return Boolean.valueOf(onUpsellTabSelected(z, UpsellReason.PERSISTENT_UPGRADE));
    }

    private void configureUpsellTabForUpsell(Tab upsellTab, final boolean nonBlocking) {
        configureUpsellTab(upsellTab, new Runnable() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpsellUtil.emitPersistentUpsellClickEventMetric();
            }
        }, new Callable() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$configureUpsellTabForUpsell$2(nonBlocking);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$configureUpsellTabForUpsell$2(boolean z) throws Exception {
        return Boolean.valueOf(onUpsellTabSelected(z, UpsellReason.PERSISTENT_UPSELL));
    }

    private boolean onUpsellTabSelected(boolean nonBlocking, UpsellReason upsellReason) {
        FragmentActivity fragmentActivity = this.mActivity;
        if (fragmentActivity == null) {
            Log.error(TAG, "Activity is null");
            return false;
        }
        if (fragmentActivity instanceof MusicHomeActivity) {
            if (nonBlocking) {
                setHeaderViewForUpgradeTab();
                UpsellUtil.showNonBlockingUpsell(this.mFragmentController, this.mActivity, null, upsellReason, UpsellSourceEntity.PERSISTENT_UPSELL_INGRESS);
                return true;
            }
            UpsellUtil.showBlockingUpsell(fragmentActivity, null, upsellReason, UpsellSourceEntity.PERSISTENT_UPSELL_INGRESS);
            return false;
        }
        Log.error(TAG, "Activity is not an instance of MusicHomeActivity: " + this.mActivity.getClass().getName());
        return false;
    }

    public void setHeaderViewForUpgradeTab() {
        ((MusicHomeActivity) this.mActivity).removeHeaderView();
    }

    public boolean onAlexaTabSelected() {
        FragmentActivity fragmentActivity = this.mActivity;
        if (fragmentActivity == null) {
            Log.error(TAG, "Activity is null");
            return false;
        }
        if (!(fragmentActivity instanceof MusicHomeActivity)) {
            Log.error(TAG, "Activity is not an instance of MusicHomeActivity: " + this.mActivity.getClass().getName());
            return false;
        }
        if (this.mAccountJobController == null) {
            Log.error(TAG, "AccountJobController is null");
            return false;
        }
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RENDERING, PageLoadLatencyCode.ALEXA));
        this.mAccountJobController.checkImmediate(((MusicHomeActivity) this.mActivity).getSession(), this.mAlexaAccountJobFinishedCallback);
        return true;
    }

    private void setUpBrowseTab() {
        this.mBottomBar.setButton(Tab.BROWSE, new TabListener() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.6
            @Override // com.amazon.mp3.bottomnavbar.tabbar.TabListener
            public boolean onTabSelected() {
                MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.HOME);
                if (ConnectivityUtil.hasAnyInternetConnection(BottomNavigationBarContainer.this.mActivity)) {
                    BottomNavigationBarContainer.this.navigateTo(BottomBarTab.BROWSE);
                    return true;
                }
                BottomNavigationBarContainer.this.showNoNetwork();
                return false;
            }
        }, 0);
    }

    private void setUpFindTab() {
        this.mBottomBar.setButton(Tab.SEARCH, new TabListener() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.7
            @Override // com.amazon.mp3.bottomnavbar.tabbar.TabListener
            public boolean onTabSelected() {
                MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.FIND_V2);
                BottomNavigationBarContainer.this.navigateTo(BottomBarTab.SEARCH);
                return true;
            }
        }, 1);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$8, reason: invalid class name */
    class AnonymousClass8 implements TabListener {
        AnonymousClass8() {
        }

        @Override // com.amazon.mp3.bottomnavbar.tabbar.TabListener
        public boolean onTabSelected() {
            MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.LIBRARY);
            BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$8$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$onTabSelected$0((Void) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$8$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BottomNavigationBarContainer.AnonymousClass8.lambda$onTabSelected$1((Throwable) obj);
                }
            });
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onTabSelected$0(Void r3) {
            if (BottomNavigationBarContainer.this.mFragmentController != null) {
                Intent intent = new Intent();
                intent.putExtra("com.amazon.mp3.fragment.extra", MusicHomeActivity.getMyLibraryHomeFragmentName());
                BottomNavigationBarContainer.this.mFragmentController.changeScreenFragment(MusicHomeActivity.getMyLibraryHomeFragment(intent), false, false);
                BottomNavigationBarContainer.this.mCurrentTab = BottomBarTab.LIBRARY;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onTabSelected$1(Throwable th) {
            Log.warning(BottomNavigationBarContainer.TAG, "Failed to get bootstrap, falling back to old My Music experience");
        }
    }

    private void setUpMyLibraryTab() {
        this.mBottomBar.setButton(Tab.LIBRARY, new AnonymousClass8(), 2);
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        AccountJobController accountJobController;
        FragmentActivity fragmentActivity = this.mActivity;
        if (!(fragmentActivity instanceof MusicHomeActivity) || (accountJobController = this.mAccountJobController) == null) {
            return;
        }
        accountJobController.onActivityResult(((MusicHomeActivity) fragmentActivity).getSession(), requestCode, resultCode, data);
    }

    public void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        AccountJobController accountJobController = this.mAccountJobController;
        if (accountJobController != null) {
            accountJobController.onJobFinished(jobId, job, resultCode, resultBundle);
        }
    }

    public void detach(@NonNull final FragmentActivity activity) {
        if (this.mEnabled) {
            Validate.notNull(activity);
            String str = TAG;
            Log.debug(str, "Detaching bottom nav bar container from %s [%s]", activity.getClass().getSimpleName(), activity.toString());
            if (this.mIsAlexaEnabled && this.mMarketAlexaUiListener != null && VoiceManagerSingleton.isInitialized()) {
                VoiceManagerSingleton.getInstance().removeAlexaUiListener(this.mMarketAlexaUiListener);
                this.mMarketAlexaUiListener.destroy();
                this.mMarketAlexaUiListener = null;
            }
            AccountJobController accountJobController = this.mAccountJobController;
            if (accountJobController != null) {
                accountJobController.destroy();
                this.mAccountJobController = null;
            }
            this.mUIListener = null;
            if (this.mActivity != activity) {
                Log.error(str, "Detaching an activity which is not the primary activity");
                return;
            }
            Log.debug(str, "Detaching from primary activity");
            this.mActivity = null;
            this.mFragmentController = null;
            this.mBottomBar = null;
            deregisterAccountStateChangeListener();
            deregisterAccountStateChangeUserBenefitsListener();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deregisterAccountStateChangeListener() {
        if (this.mAccountStateChangeListener != null) {
            AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
            this.mAccountStateChangeListener = null;
        }
    }

    private void deregisterAccountStateChangeUserBenefitsListener() {
        if (this.mAccountStateChangeUserBenefitsListener != null) {
            AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeUserBenefitsListener);
            this.mAccountStateChangeUserBenefitsListener = null;
        }
    }

    public void navigateToTab(final LastViewedScreenUtil.LastViewedScreen screen) {
        FragmentController fragmentController;
        setBottomBarTab(screen);
        navigateTo(this.mCurrentTab);
        BottomBarUIListener bottomBarUIListener = this.mUIListener;
        if (bottomBarUIListener == null || (fragmentController = this.mFragmentController) == null) {
            return;
        }
        bottomBarUIListener.onFragmentShown(fragmentController.getCurrentFragment());
    }

    public void setBottomBarTab(LastViewedScreenUtil.LastViewedScreen screen) {
        if (!this.mEnabled || this.mBottomBar == null) {
            return;
        }
        LastViewedScreenUtil.setLastViewed(this.mActivity, screen);
        setActiveTab(screen);
        this.mBottomBar.setActiveTab(this.mCurrentTab.getComponentTab());
    }

    public BottomBarTab getCurrentlySelectedBottomBarTab() {
        return this.mCurrentTab;
    }

    public void showBottomBar(final boolean shouldShow) {
        BottomNavigationBar bottomNavigationBar;
        if (!this.mEnabled || (bottomNavigationBar = this.mBottomBar) == null) {
            return;
        }
        bottomNavigationBar.showBottomBar(shouldShow);
        setBottomBarMaskViewsVisibility(shouldShow);
        if (shouldShow) {
            refreshBottomBar();
        }
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    private void setBottomBarMaskViewsVisibility(final boolean shouldShow) {
        this.mBottomBarLowerMask.setVisibility(shouldShow ? 0 : 8);
        this.mBottomBarUpperMask.setVisibility(shouldShow ? 0 : 8);
    }

    private BottomNavigationBarContainer() {
        mLastToolTipString = null;
        mLastToolTipTabIndex = -1;
        new HandlerThread("Bottom Bar Background Handler Thread", 10).start();
    }

    private void loadLastViewedTab() {
        setActiveTab(LastViewedScreenUtil.getLastViewedScreen(this.mActivity));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer$11, reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen;

        static {
            int[] iArr = new int[LastViewedScreenUtil.LastViewedScreen.values().length];
            $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen = iArr;
            try {
                iArr[LastViewedScreenUtil.LastViewedScreen.LIBRARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[LastViewedScreenUtil.LastViewedScreen.STORE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[LastViewedScreenUtil.LastViewedScreen.SEARCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void setActiveTab(LastViewedScreenUtil.LastViewedScreen screen) {
        BottomBarTab bottomBarTab;
        int i = AnonymousClass11.$SwitchMap$com$amazon$mp3$activity$settings$LastViewedScreenUtil$LastViewedScreen[screen.ordinal()];
        if (i == 1) {
            bottomBarTab = BottomBarTab.LIBRARY;
        } else if (i == 2 || i == 3) {
            bottomBarTab = BottomBarTab.BROWSE;
        } else if (i == 4) {
            bottomBarTab = BottomBarTab.SEARCH;
        } else {
            bottomBarTab = BottomBarTab.BROWSE;
            Log.error(TAG, "Called loadLastViewedTab for unhandled screen " + screen);
        }
        this.mCurrentTab = bottomBarTab;
    }

    public void refreshBottomBar() {
        BottomNavigationBar bottomNavigationBar = this.mBottomBar;
        if (bottomNavigationBar == null) {
            Log.error(TAG, "refreshBottomBar called with mBottomBar = null");
            return;
        }
        BottomBarTab bottomBarTab = this.mCurrentTab;
        if (bottomBarTab == null) {
            Log.error(TAG, "refreshBottomBar called with mCurrentTab = null");
            return;
        }
        bottomNavigationBar.setActiveTab(bottomBarTab.getComponentTab());
        if (UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace()) {
            setUpBrowseTab();
            showUpsellTabIfNeeded();
            if (AmazonApplication.getCapabilities().isAlexaEnabled() && (this.mActivity instanceof MusicHomeActivity)) {
                refreshAlexa();
                return;
            }
            return;
        }
        if (AmazonApplication.getCapabilities().isStoreSupported()) {
            setUpStoreTab();
        } else {
            this.mBottomBar.setTabEnabled(Tab.STORE, false);
            this.mBottomBar.setTabEnabled(Tab.BROWSE, false);
        }
    }

    public BottomNavigationBar getBottomBar() {
        return this.mBottomBar;
    }

    private void setUpStoreTab() {
        this.mBottomBar.setButton(Tab.STORE, new TabListener() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.9
            @Override // com.amazon.mp3.bottomnavbar.tabbar.TabListener
            public boolean onTabSelected() {
                if (ConnectivityUtil.hasAnyInternetConnection(BottomNavigationBarContainer.this.mActivity)) {
                    BottomNavigationBarContainer.this.navigateToStore();
                    return true;
                }
                BottomNavigationBarContainer.this.showNoNetwork();
                return false;
            }
        }, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigateToStore() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.mp3.store.TITLE", this.mActivity.getString(R.string.dmusic_store_music_tab));
        intent.setClass(this.mActivity, MusicHomeActivity.class);
        intent.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
        intent.putExtra("com.amazon.mp3.store.extra.STORE_ARGUMENTS_BUNDLE", bundle);
        this.mActivity.startActivity(intent);
        this.mCurrentTab = BottomBarTab.BROWSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoNetwork() {
        if (this.mActivity == null) {
            return;
        }
        if (this.mNoConnectionDialog == null) {
            this.mNoConnectionDialog = NoConnectionDialog.newInstance(false);
        }
        if (this.mNoConnectionDialog.isShowing()) {
            return;
        }
        this.mNoConnectionDialog.show(this.mActivity.getSupportFragmentManager(), "NoConnectionDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigateTo(final BottomBarTab tab) {
        Trace.beginSection("AMA.BottomNavigationBarContainer.navigateTo");
        if (this.mFragmentController != null) {
            BottomBarTab bottomBarTab = BottomBarTab.SEARCH;
            if (tab != bottomBarTab || (tab == bottomBarTab && !AmazonApplication.getCapabilities().isBauhausLibraryEnabled())) {
                this.mFragmentController.clearBackStack();
            }
            this.mFragmentController.navigateFromMenu(tab.getMenuId());
        }
        this.mCurrentTab = tab;
        Trace.endSection();
    }

    public void selectPreviousTab() {
        BottomNavigationBar bottomNavigationBar = this.mBottomBar;
        if (bottomNavigationBar != null) {
            bottomNavigationBar.selectPreviousTab();
        }
    }

    private void setAccountStateChangedUserBenefitsListener() {
        this.mAccountStateChangeUserBenefitsListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.10
            @Override // com.amazon.music.account.AccountStateChangeListener
            public void onAccountStateChange(final Set<AccountStateField> set) {
                if (set.contains(AccountStateField.USER_BENEFITS)) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer.10.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (BottomNavigationBarContainer.this.mBottomBar != null) {
                                BottomNavigationBarContainer.this.refreshBottomBar();
                            }
                        }
                    });
                }
            }
        };
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeUserBenefitsListener);
    }
}
