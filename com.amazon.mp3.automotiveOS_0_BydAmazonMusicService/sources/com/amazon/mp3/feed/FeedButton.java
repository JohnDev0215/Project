package com.amazon.mp3.feed;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.reactnative.ReactExperienceNavigationRouteHelper;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.connect.feed.ActivityFeedFragment;
import com.amazon.music.connect.feed.FeedItem;
import com.amazon.music.connect.feed.FeedMetrics;
import com.amazon.music.connect.feed.FeedViewModel;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedButton.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0005R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/feed/FeedButton;", "", "headerView", "Landroid/view/View;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroid/view/View;Landroidx/fragment/app/FragmentActivity;)V", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "activityFeedInitializer", "Lcom/amazon/mp3/feed/ActivityFeedInitializer;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "feedButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "feedViewModel", "Lcom/amazon/music/connect/feed/FeedViewModel;", "fragmentController", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "indicatorDisplayed", "", "attachClickListener", "", "attachFeedObserver", "removeObservers", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeedButton {
    private final FragmentActivity activity;
    private final ActivityFeedInitializer activityFeedInitializer;
    private final FeatureGateProvider featureGateProvider;
    private final BaseButton feedButton;
    private FeedViewModel feedViewModel;
    private FragmentController fragmentController;
    private boolean indicatorDisplayed;

    public FeedButton(View headerView, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(headerView, "headerView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        View viewFindViewById = headerView.findViewById(R.id.feed_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "headerView.findViewById(R.id.feed_button)");
        BaseButton baseButton = (BaseButton) viewFindViewById;
        this.feedButton = baseButton;
        this.activityFeedInitializer = new ActivityFeedInitializer();
        this.featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        StyleSheetProvider.getStyleSheet().observe(activity, new Observer() { // from class: com.amazon.mp3.feed.FeedButton$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FeedButton.m988_init_$lambda1(this.f$0, (StyleSheet) obj);
            }
        });
        if (AmazonApplication.getCapabilities().isActivityFeedSupported()) {
            this.fragmentController = new FragmentController(activity, R.id.music_screen_container);
            if (baseButton != null) {
                baseButton.setVisibility(0);
            }
            FeedMetrics.emitIconDisplayMetric(this.indicatorDisplayed);
            attachClickListener();
            attachFeedObserver(activity);
        }
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m988_init_$lambda1(FeedButton this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ButtonSize iconSize = styleSheet.getIconSize(IconSizeKey.MEDIUM);
        if (iconSize == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilderWithSize = new BaseButton.StyleBuilder().withSize(iconSize);
        Drawable drawable = this$0.getActivity().getDrawable(R.drawable.icon_notification_bell);
        Intrinsics.checkNotNull(drawable);
        Intrinsics.checkNotNullExpressionValue(drawable, "activity.getDrawable(R.d…icon_notification_bell)!!");
        styleBuilderWithSize.withIcon(drawable).applyStyle(this$0.feedButton);
    }

    private final void attachClickListener() {
        BaseButton baseButton = this.feedButton;
        if (baseButton == null) {
            return;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.feed.FeedButton$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedButton.m989attachClickListener$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListener$lambda-2, reason: not valid java name */
    public static final void m989attachClickListener$lambda2(FeedButton this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FeedMetrics.emitIconClickMetric(this$0.indicatorDisplayed);
        FeatureGateProvider featureGateProvider = this$0.featureGateProvider;
        boolean z = false;
        if (featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.activity_feed_on_react_native)) {
            FeatureGateProvider featureGateProvider2 = this$0.featureGateProvider;
            if (featureGateProvider2 != null && featureGateProvider2.isFeatureAvailable(Feature.activity_feed_on_react_native_fragment)) {
                z = true;
            }
            if (z) {
                if (Feature.dragonfly_enabled.isEnabled() || Feature.dragonfly_fireos_enabled.isEnabled()) {
                    ReactExperienceNavigationRouteHelper.INSTANCE.navigateToActivityFeedRoute(this$0.activity);
                    return;
                }
                Fragment reactFragment = this$0.activityFeedInitializer.getReactFragment(this$0.activity);
                FragmentController fragmentController = this$0.fragmentController;
                if (fragmentController == null) {
                    return;
                }
                fragmentController.changeScreenFragment(reactFragment, true, true, true);
                return;
            }
            this$0.activityFeedInitializer.startActivityFeed(this$0.activity);
            return;
        }
        FragmentController fragmentController2 = this$0.fragmentController;
        if (fragmentController2 == null) {
            return;
        }
        fragmentController2.changeScreenFragment(new ActivityFeedFragment(), true, true, true);
    }

    private final void attachFeedObserver(final FragmentActivity activity) {
        LiveData<PagedList<FeedItem>> feedItemsLiveData;
        LiveData<PagedList<FeedItem>> feedItemsLiveData2;
        FeedViewModel feedViewModel = new FeedViewModelProvider(activity).get();
        this.feedViewModel = feedViewModel;
        if (feedViewModel != null) {
            feedViewModel.startPeriodicRefresh();
        }
        FeedViewModel feedViewModel2 = this.feedViewModel;
        if (feedViewModel2 != null && (feedItemsLiveData2 = feedViewModel2.getFeedItemsLiveData()) != null) {
            feedItemsLiveData2.removeObservers(activity);
        }
        FeedViewModel feedViewModel3 = this.feedViewModel;
        if (feedViewModel3 == null || (feedItemsLiveData = feedViewModel3.getFeedItemsLiveData()) == null) {
            return;
        }
        feedItemsLiveData.observe(activity, new Observer() { // from class: com.amazon.mp3.feed.FeedButton$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FeedButton.m990attachFeedObserver$lambda3(activity, this, (PagedList) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachFeedObserver$lambda-3, reason: not valid java name */
    public static final void m990attachFeedObserver$lambda3(FragmentActivity activity, FeedButton this$0, PagedList pagedList) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (pagedList == null || pagedList.isEmpty()) {
            return;
        }
        String str = null;
        FeedItem feedItem = pagedList == null ? null : (FeedItem) pagedList.get(0);
        if (Feature.dragonfly_enabled.isEnabled() || Feature.dragonfly_fireos_enabled.isEnabled()) {
            if (feedItem != null) {
                str = feedItem.baseItemId;
            }
        } else if (feedItem != null) {
            str = feedItem.id;
        }
        String string = activity.getSharedPreferences("device_cache_shared_preferences", 0).getString("com.amazon.mp3.userdefaults.connect.lastSeenActivityFeedItemId", "none");
        boolean z = this$0.indicatorDisplayed;
        if (Intrinsics.areEqual(string, str)) {
            BaseButton baseButton = this$0.feedButton;
            Drawable drawable = activity.getDrawable(R.drawable.icon_notification_bell);
            Intrinsics.checkNotNull(drawable);
            baseButton.setIcon(drawable);
            this$0.indicatorDisplayed = false;
        } else {
            BaseButton baseButton2 = this$0.feedButton;
            Drawable drawable2 = activity.getDrawable(R.drawable.icon_notification_bell_new);
            Intrinsics.checkNotNull(drawable2);
            baseButton2.setIcon(drawable2);
            this$0.indicatorDisplayed = true;
        }
        boolean z2 = this$0.indicatorDisplayed;
        if (z2 != z) {
            FeedMetrics.emitIconDisplayMetric(z2);
        }
    }

    public final void removeObservers(FragmentActivity activity) {
        LiveData<PagedList<FeedItem>> feedItemsLiveData;
        Intrinsics.checkNotNullParameter(activity, "activity");
        FeedViewModel feedViewModel = this.feedViewModel;
        if (feedViewModel != null) {
            feedViewModel.stopPeriodicRefresh();
        }
        FeedViewModel feedViewModel2 = this.feedViewModel;
        if (feedViewModel2 == null || (feedItemsLiveData = feedViewModel2.getFeedItemsLiveData()) == null) {
            return;
        }
        feedItemsLiveData.removeObservers(activity);
    }
}
