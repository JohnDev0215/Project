package com.amazon.music.skyfire.ui.providers;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.presentation.ActionBarDecorator;
import com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import kotlin.Metadata;

/* JADX INFO: compiled from: ChromeProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\tJd\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H&J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H&J)\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010 ¨\u0006!"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "", "createActionBarDecorator", "Lcom/amazon/music/views/library/views/presentation/ActionBarDecorator;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "showOverflowButton", "", "showProfileButton", "(Landroidx/fragment/app/FragmentActivity;ZLjava/lang/Boolean;)Lcom/amazon/music/views/library/views/presentation/ActionBarDecorator;", "createBauhausTooltipExperienceDecorator", "Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "facetNavTooltipStateKey", "", "facetNavTooltipText", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "context", "Landroid/content/Context;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "facetNavMetricsContentName", "pageView", "Landroidx/recyclerview/widget/RecyclerView;", "headerView", "containerIndex", "maxNumPageView", "", "updatePageViewOnShowAndDisableToolTip", "createTooltipExperienceDecorator", "createTranslucentActionBarDecorator", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "(Landroidx/fragment/app/FragmentActivity;ZLjava/lang/Boolean;)Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ChromeProvider {
    ActionBarDecorator createActionBarDecorator(FragmentActivity activity, boolean showOverflowButton, Boolean showProfileButton);

    TooltipExperienceDecorator createBauhausTooltipExperienceDecorator(String facetNavTooltipStateKey, String facetNavTooltipText, StyleSheet styleSheet, Context context, PageType pageType, String facetNavMetricsContentName, RecyclerView pageView, RecyclerView headerView, String containerIndex, int maxNumPageView, boolean updatePageViewOnShowAndDisableToolTip);

    TooltipExperienceDecorator createTooltipExperienceDecorator(Context context);

    TranslucentActionBarDecorator createTranslucentActionBarDecorator(FragmentActivity activity, boolean showOverflowButton, Boolean showProfileButton);

    /* JADX INFO: compiled from: ChromeProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ ActionBarDecorator createActionBarDecorator$default(ChromeProvider chromeProvider, FragmentActivity fragmentActivity, boolean z, Boolean bool, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createActionBarDecorator");
            }
            if ((i & 4) != 0) {
                bool = null;
            }
            return chromeProvider.createActionBarDecorator(fragmentActivity, z, bool);
        }

        public static /* synthetic */ TranslucentActionBarDecorator createTranslucentActionBarDecorator$default(ChromeProvider chromeProvider, FragmentActivity fragmentActivity, boolean z, Boolean bool, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createTranslucentActionBarDecorator");
            }
            if ((i & 4) != 0) {
                bool = null;
            }
            return chromeProvider.createTranslucentActionBarDecorator(fragmentActivity, z, bool);
        }
    }
}
