package com.amazon.mp3.dmsfcore.providers;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.brush.BauhausTooltipExperience;
import com.amazon.mp3.find.view.AlexaTooltipExperience;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.presentation.ActionBarDecorator;
import com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFChromeProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\nJd\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\bH\u0016J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J'\u0010\u001f\u001a\u00020 2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFChromeProvider;", "Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "()V", "createActionBarDecorator", "Lcom/amazon/music/views/library/views/presentation/ActionBarDecorator;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "showOverflowButton", "", "showProfileButton", "(Landroidx/fragment/app/FragmentActivity;ZLjava/lang/Boolean;)Lcom/amazon/music/views/library/views/presentation/ActionBarDecorator;", "createBauhausTooltipExperienceDecorator", "Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "facetNavTooltipStateKey", "", "facetNavTooltipText", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "context", "Landroid/content/Context;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "facetNavMetricsContentName", "pageView", "Landroidx/recyclerview/widget/RecyclerView;", "headerView", "containerIndex", "maxNumPageView", "", "updatePageViewOnShowAndDisableToolTip", "createTooltipExperienceDecorator", "createTranslucentActionBarDecorator", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "(Landroidx/fragment/app/FragmentActivity;ZLjava/lang/Boolean;)Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFChromeProvider implements ChromeProvider {
    @Override // com.amazon.music.skyfire.ui.providers.ChromeProvider
    public ActionBarDecorator createActionBarDecorator(FragmentActivity activity, boolean showOverflowButton, Boolean showProfileButton) {
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (showProfileButton != null) {
            zBooleanValue = showProfileButton.booleanValue();
        } else {
            zBooleanValue = AmazonApplication.getCapabilities().isCustomerProfileSupported();
        }
        ActionBarView actionBarView = new ActionBarView(activity, "", zBooleanValue, showOverflowButton, null);
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity != null) {
            navigationActivity.setHeaderView(actionBarView);
            navigationActivity.requestHomeButtonAsBack();
        }
        return actionBarView;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ChromeProvider
    public TranslucentActionBarDecorator createTranslucentActionBarDecorator(FragmentActivity activity, boolean showOverflowButton, Boolean showProfileButton) {
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (showProfileButton != null) {
            zBooleanValue = showProfileButton.booleanValue();
        } else {
            zBooleanValue = AmazonApplication.getCapabilities().isCustomerProfileSupported();
        }
        BauhausActionBarView bauhausActionBarView = new BauhausActionBarView(activity, "", Boolean.valueOf(zBooleanValue), Boolean.valueOf(showOverflowButton), null);
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity != null) {
            navigationActivity.setHeaderView(bauhausActionBarView);
            navigationActivity.requestHomeButtonAsBack();
        }
        return bauhausActionBarView;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ChromeProvider
    public TooltipExperienceDecorator createTooltipExperienceDecorator(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new AlexaTooltipExperience(context);
    }

    @Override // com.amazon.music.skyfire.ui.providers.ChromeProvider
    public TooltipExperienceDecorator createBauhausTooltipExperienceDecorator(String facetNavTooltipStateKey, String facetNavTooltipText, StyleSheet styleSheet, Context context, PageType pageType, String facetNavMetricsContentName, RecyclerView pageView, RecyclerView headerView, String containerIndex, int maxNumPageView, boolean updatePageViewOnShowAndDisableToolTip) {
        Intrinsics.checkNotNullParameter(facetNavTooltipStateKey, "facetNavTooltipStateKey");
        Intrinsics.checkNotNullParameter(facetNavTooltipText, "facetNavTooltipText");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(facetNavMetricsContentName, "facetNavMetricsContentName");
        Intrinsics.checkNotNullParameter(containerIndex, "containerIndex");
        return new BauhausTooltipExperience(facetNavTooltipStateKey, facetNavTooltipText, styleSheet, context, pageType, facetNavMetricsContentName, pageView, headerView, null, containerIndex, maxNumPageView, updatePageViewOnShowAndDisableToolTip);
    }
}
