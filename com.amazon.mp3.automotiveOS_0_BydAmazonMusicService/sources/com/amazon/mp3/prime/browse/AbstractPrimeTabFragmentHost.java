package com.amazon.mp3.prime.browse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.feed.FeedButton;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.library.fragment.DownloadsFragment;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.provider.InactivityProvider;
import com.amazon.mp3.library.service.sync.PreWidevineDBUpdateOperation;
import com.amazon.mp3.prime.browse.TabBarPagerAdapter;
import com.amazon.mp3.prime.browse.pager.SwipeableViewPager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.nautilus.MusicSubscriptionStateChangeListener;
import com.amazon.music.nautilus.MusicSubscriptionStateField;
import java.util.Iterator;
import java.util.Set;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractPrimeTabFragmentHost<T extends PagerAdapter & TabBarPagerAdapter> extends LibraryBaseFragment {
    private static final String TAG = AbstractPrimeTabFragmentHost.class.getSimpleName();
    private static boolean sRefreshTabs = false;
    private FeedButton feedButton;
    private AccountJobController mAccountJobs;
    protected View mFeatureBanner;
    protected View mFeatureBannerContainer;
    private View mFeatureBannerDismissIcon;
    private View mFeatureBannerExitToAppIcon;
    private View mFeatureBannerExploreIcon;
    private TextView mFeatureBannerMessage;
    private Subscription mFeatureFlagSubscription;
    private View mHeaderView;
    protected T mPagerAdapter;
    protected SwipeableViewPager mViewPager;
    private Subscription preWideVineSyncSubscription;
    private boolean mIsTabToOpenSet = false;
    private boolean mBannerNeedsRefresh = false;
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.6
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_TERMS_OF_USE_MARKETPLACE) || set.contains(AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE)) {
                FragmentActivity activity = AbstractPrimeTabFragmentHost.this.getActivity();
                if (activity == null) {
                    AbstractPrimeTabFragmentHost.this.mBannerNeedsRefresh = true;
                } else {
                    activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (AbstractPrimeTabFragmentHost.this.isActivated()) {
                                AbstractPrimeTabFragmentHost abstractPrimeTabFragmentHost = AbstractPrimeTabFragmentHost.this;
                                abstractPrimeTabFragmentHost.updateBannerView(abstractPrimeTabFragmentHost.getActivity().getApplicationContext());
                            }
                        }
                    });
                }
            }
        }
    };
    private MusicSubscriptionStateChangeListener mMusicStateChangeListener = new MusicSubscriptionStateChangeListener() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.7
        @Override // com.amazon.music.nautilus.MusicSubscriptionStateChangeListener
        public void onMusicSubscriptionStateChange(Set<MusicSubscriptionStateField> set) {
            if (set.contains(MusicSubscriptionStateField.HAS_OPEN_PROBLEMS)) {
                if (!AbstractPrimeTabFragmentHost.this.isActivated()) {
                    AbstractPrimeTabFragmentHost.this.mBannerNeedsRefresh = true;
                } else {
                    AbstractPrimeTabFragmentHost.this.getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentActivity activity = AbstractPrimeTabFragmentHost.this.getActivity();
                            if (activity != null) {
                                AbstractPrimeTabFragmentHost.this.updateBannerView(activity.getApplicationContext());
                            }
                        }
                    });
                }
            }
        }
    };

    protected abstract View createHeaderView(Bundle savedInstance);

    protected abstract T createPagerAdapter();

    protected abstract int getLastViewedTab(Context context);

    protected abstract void setLastViewedTab(Context context, int lastTab);

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

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mAccountJobs.onActivityResult(getSession(), requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mAccountJobs.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    public void setTabToOpen(Context context, int position) {
        this.mIsTabToOpenSet = true;
        setLastViewedTab(context, position);
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
        if (SubscriptionManagerSingleton.isInitialized()) {
            SubscriptionManagerSingleton.getInstance().registerListener(this.mMusicStateChangeListener);
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setHeaderView(this.mHeaderView);
        LastViewedScreenUtil.setLastViewed(getActivity(), LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
        if (sRefreshTabs) {
            sRefreshTabs = false;
            forceRefresh();
        }
        if (this.mBannerNeedsRefresh) {
            updateBannerView(getContext().getApplicationContext());
            this.mBannerNeedsRefresh = false;
        }
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(getLastViewedTab(getActivity()));
        }
        View view = this.mHeaderView;
        if (view instanceof ActionBarView) {
            ((ActionBarView) view).refresh();
        }
    }

    protected void startAccountJob(final boolean immediate) {
        if (immediate) {
            this.mAccountJobs.checkImmediate(getSession(), null);
        } else {
            this.mAccountJobs.check(getSession());
        }
    }

    private void forceRefresh() {
        BootstrapSingletonTask.get(getContext()).subscribeToBootstrapTaskUserObjectObserver(new Action1() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$forceRefresh$0((Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public /* synthetic */ void lambda$forceRefresh$0(Void r2) {
        T t = (T) createPagerAdapter();
        this.mPagerAdapter = t;
        SwipeableViewPager swipeableViewPager = this.mViewPager;
        if (swipeableViewPager != null) {
            swipeableViewPager.setAdapter(t);
        }
    }

    public static void forceRefreshTabs() {
        sRefreshTabs = true;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mHeaderView = createHeaderView(savedInstanceState);
        if (!AmazonApplication.getCapabilities().isFacetedNavigationEnabled()) {
            this.feedButton = new FeedButton(this.mHeaderView, getActivity());
        }
        updateBannerView(getActivity().getApplicationContext());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        View viewInflate = inflater.inflate(R.layout.library_prime_browse_tabs_host, container, false);
        this.mFeatureBannerContainer = viewInflate.findViewById(R.id.feature_banner_container);
        View viewFindViewById = viewInflate.findViewById(R.id.feature_banner);
        this.mFeatureBanner = viewFindViewById;
        this.mFeatureBannerExitToAppIcon = viewFindViewById.findViewById(R.id.feature_banner_icon);
        this.mFeatureBannerDismissIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_icon_dismiss);
        this.mFeatureBannerExploreIcon = this.mFeatureBanner.findViewById(R.id.feature_banner_view);
        this.mFeatureBannerMessage = (TextView) this.mFeatureBanner.findViewById(R.id.feature_banner_message);
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBannerView(final Context context) {
        if (AccountRequestUtil.isAccountValid(context)) {
            this.mFeatureFlagSubscription = BootstrapSingletonTask.get(context).registerFeatureFlagObserver().take(1).subscribe(new Action1<Void>() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.1
                @Override // rx.functions.Action1
                public void call(Void aVoid) {
                    AbstractPrimeTabFragmentHost.this.setBannerView(context);
                }
            }, new Action1<Throwable>() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.2
                @Override // rx.functions.Action1
                public void call(Throwable throwable) {
                    Log.debug(AbstractPrimeTabFragmentHost.TAG, "Error updating banner view: " + throwable);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBannerView(final Context context) {
        if (this instanceof PrimeTabFragmentHost) {
            this.preWideVineSyncSubscription = PreWidevineDBUpdateOperation.getMigrationBannerObservable(getApplication()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.3
                @Override // rx.functions.Action1
                public void call(final Boolean isAvailable) {
                    if (isAvailable.booleanValue()) {
                        if (SettingsUtil.shouldShowRedownloadUIUntilAcknowledged(context)) {
                            Log.debug(AbstractPrimeTabFragmentHost.TAG, "Should show Redownload UI unless already shown and user already acknowledged, as per shared preference.");
                            if (SettingsUtil.getBooleanPref(AbstractPrimeTabFragmentHost.this.getApplication().getApplicationContext(), R.string.setting_key_redownload_popup_dismissed)) {
                                return;
                            }
                            Log.debug(AbstractPrimeTabFragmentHost.TAG, "Should show Redownload pop up as per shared preference.");
                            if (AbstractPrimeTabFragmentHost.this.getActivity() != null) {
                                WideDialogActivity.showActivity(AbstractPrimeTabFragmentHost.this.getActivity(), WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    SettingsUtil.setShouldShowRedownloadUIUntilAcknowledged(context, false);
                    SettingsUtil.setBooleanPref(AbstractPrimeTabFragmentHost.this.getApplication().getApplicationContext(), R.string.setting_key_redownload_popup_dismissed, true);
                }
            }, new Action1<Throwable>() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost.4
                @Override // rx.functions.Action1
                public void call(final Throwable throwable) {
                    Log.error(AbstractPrimeTabFragmentHost.TAG, "Following error occurred while finding offline content: " + throwable.getMessage());
                }
            });
            showDownloadInProgressRibbonIfThereAreQueuedDownloads();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost$5, reason: invalid class name */
    class AnonymousClass5 implements RequestProvider.RequestProviderCallback {
        AnonymousClass5() {
        }

        @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
        public void requestsSubmitted(final Set<RequestItem> requests) {
            Iterator<RequestItem> it = requests.iterator();
            while (it.hasNext()) {
                if (it.next().isBackground()) {
                    it.remove();
                }
            }
            ((BaseFragment) AbstractPrimeTabFragmentHost.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$requestsSubmitted$0(requests);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$requestsSubmitted$0(Set set) {
            if (set.size() > 0) {
                AbstractPrimeTabFragmentHost.this.showDownloadInProgressRibbon();
            } else {
                AbstractPrimeTabFragmentHost.this.showBetaBanner();
            }
        }
    }

    protected void showDownloadInProgressRibbonIfThereAreQueuedDownloads() {
        MusicDownloader.getInstance(getContext()).getRequestProvider().getRequests(new AnonymousClass5());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadInProgressRibbon() {
        if (this.mFeatureBanner == null) {
            return;
        }
        String string = getResources().getString(R.string.dmusic_ribbon_redownload_in_progress);
        if (this.mFeatureBanner.getVisibility() == 0 && this.mFeatureBanner.getTag() != null && this.mFeatureBanner.getTag().toString().equalsIgnoreCase(string)) {
            return;
        }
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            this.mFeatureBanner.setBackgroundColor(getResources().getColor(R.color.cyan_accent));
        } else {
            this.mFeatureBanner.setBackgroundColor(getResources().getColor(R.color.blue_accent));
        }
        this.mFeatureBannerMessage.setGravity(8388627);
        this.mFeatureBannerDismissIcon.setVisibility(8);
        this.mFeatureBannerExitToAppIcon.setVisibility(8);
        this.mFeatureBannerExploreIcon.setVisibility(0);
        ((TextView) this.mFeatureBannerExploreIcon).setText(getText(R.string.dmusic_ribbon_view_downloads));
        this.mFeatureBannerMessage.setText(string);
        this.mFeatureBanner.setTag(string);
        this.mFeatureBanner.setVisibility(0);
        this.mFeatureBanner.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showDownloadInProgressRibbon$1(view);
            }
        });
        MetricsLogger.downloadsQueuedRibbonViewed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showDownloadInProgressRibbon$1(View view) {
        UserInteractionAppEvent.builder("goDownloadQueue").publish();
        startActivity(MusicHomeActivity.getStartIntent(getContext()).putExtra("com.amazon.mp3.fragment.extra", DownloadsFragment.class.getSimpleName()).putExtra("add_to_back_stack", true));
    }

    private void sendBannerUiClickEvent(final String identifier) {
        UserInteractionAppEvent.builder(identifier).withInteractionType(InteractionType.TAP).publish();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mViewPager = (SwipeableViewPager) view.findViewById(R.id.pager);
        initializeViews();
    }

    protected void initializeViews() {
        this.mPagerAdapter = (T) createPagerAdapter();
        if (InactivityProvider.isInactive(getActivity()) && !this.mIsTabToOpenSet) {
            setLastViewedTab(getActivity(), this.mPagerAdapter.getDefaultTabPosition());
        }
        this.mIsTabToOpenSet = false;
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mPagerAdapter.setTabPositionOnStart(getLastViewedTab(getActivity()));
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(getLastViewedTab(getActivity()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBetaBanner() {
        View view;
        View view2 = this.mFeatureBanner;
        if (view2 == null || (view = this.mFeatureBannerExitToAppIcon) == null || this.mFeatureBannerDismissIcon == null) {
            return;
        }
        if (!AmazonApplication.BETA) {
            view2.setVisibility(8);
            return;
        }
        view.setVisibility(8);
        this.mFeatureBannerDismissIcon.setVisibility(8);
        this.mFeatureBannerExploreIcon.setVisibility(0);
        ((TextView) this.mFeatureBannerExploreIcon).setText(getText(R.string.dmusic_beta_banner_explore_button));
        this.mFeatureBannerExploreIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f$0.lambda$showBetaBanner$2(view3);
            }
        });
        this.mFeatureBannerMessage.setText(getText(R.string.dmusic_beta_banner_message));
        this.mFeatureBannerMessage.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showBetaBanner$2(View view) {
        sendBannerUiClickEvent("learnMoreBetaBanner");
        WideDialogActivity.showActivity(getActivity(), WideDialogActivityPurpose.SHOW_BETA_HARLEY_DIALOG);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mViewPager != null) {
            setLastViewedTab(getActivity(), this.mViewPager.getCurrentItem());
        }
        Subscription subscription = this.preWideVineSyncSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.preWideVineSyncSubscription.unsubscribe();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        if (SubscriptionManagerSingleton.isInitialized()) {
            SubscriptionManagerSingleton.getInstance().deregisterListener(this.mMusicStateChangeListener);
        }
        super.onStop();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        SwipeableViewPager swipeableViewPager = this.mViewPager;
        if (swipeableViewPager != null) {
            swipeableViewPager.setOnPageChangeListener(null);
            this.mViewPager.setAdapter(null);
            this.mViewPager = null;
        }
        this.mPagerAdapter = null;
        View view = this.mFeatureBanner;
        if (view != null) {
            view.setOnClickListener(null);
        }
        FeedButton feedButton = this.feedButton;
        if (feedButton != null) {
            feedButton.removeObservers(getActivity());
            this.feedButton = null;
        }
        this.mHeaderView = null;
        this.mFeatureBanner = null;
        this.mFeatureBannerExitToAppIcon = null;
        this.mFeatureBannerContainer = null;
        this.mFeatureBannerDismissIcon = null;
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.mFeatureFlagSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        removeHeaderView();
    }

    protected SharedPreferences getSharedPreference(Context context, String sharedPrefName) {
        return context.getSharedPreferences(sharedPrefName, 4);
    }
}
