package com.amazon.mp3.actionbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.GravityCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.CantileverActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.capability.DemoModeManager;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.store.html5.activity.StoreFragment;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverflowMenuPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002\u001a\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0002\u001a\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0002\u001a\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¨\u0006\u000f"}, d2 = {"Landroid/content/Context;", "context", "", "height", "", "showOverflowMenu", "Landroid/os/Bundle;", "args", "handleStoreNavigation", "handleConnectDeviceClick", "Landroid/view/View;", "view", "handleStoreClick", "handleContactCustomerSupportClick", "handleMyProfileClick", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class OverflowMenuPresenterKt {
    public static final void showOverflowMenu(final Context context, int i) {
        int i2;
        int i3;
        int i4;
        Drawable drawable;
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("layout_inflater");
        LayoutInflater layoutInflater = systemService instanceof LayoutInflater ? (LayoutInflater) systemService : null;
        if (layoutInflater == null) {
            return;
        }
        View viewInflate = layoutInflater.inflate(R.layout.menu_overflow, (ViewGroup) null);
        Capabilities capabilities = AmazonApplication.getCapabilities();
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setContentView(viewInflate);
        popupWindow.setFocusable(true);
        boolean z = context.getResources().getConfiguration().getLayoutDirection() == 0;
        if (z) {
            i2 = GravityCompat.END;
        } else {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            i2 = 8388611;
        }
        popupWindow.showAtLocation(viewInflate, i2 | 48, 0, i);
        TextView textView = (TextView) viewInflate.findViewById(R.id.store);
        boolean zShouldShowStoreIcon = capabilities.shouldShowStoreIcon();
        if (zShouldShowStoreIcon) {
            i3 = 0;
        } else {
            if (zShouldShowStoreIcon) {
                throw new NoWhenBranchMatchedException();
            }
            i3 = 8;
        }
        textView.setVisibility(i3);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.contact_customer_support);
        boolean zIsDemoMode = DemoModeManager.get(context).isDemoMode();
        if (zIsDemoMode) {
            i4 = 0;
        } else {
            if (zIsDemoMode) {
                throw new NoWhenBranchMatchedException();
            }
            i4 = 8;
        }
        textView2.setVisibility(i4);
        textView2.setText(R.string.dmusic_setting_help_and_feedback_title);
        Drawable drawable2 = context.getResources().getDrawable(R.drawable.ic_alerts_help, null);
        Drawable[] compoundDrawablesRelative = textView2.getCompoundDrawablesRelative();
        Intrinsics.checkNotNullExpressionValue(compoundDrawablesRelative, "csItemTextView.compoundDrawablesRelative");
        if ((true ^ (compoundDrawablesRelative.length == 0)) && (drawable = compoundDrawablesRelative[0]) != null) {
            Rect rectCopyBounds = drawable.copyBounds();
            Intrinsics.checkNotNullExpressionValue(rectCopyBounds, "drawables[0].copyBounds()");
            drawable2.setBounds(rectCopyBounds);
        } else {
            drawable2.setBounds(0, 0, 60, 60);
        }
        textView2.setCompoundDrawables(drawable2, null, null, null);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.connect_device);
        if (UserSubscriptionUtil.isNightwingOnly() || NowPlayingUtil.isVideoPlaying() || ChildUserUtil.INSTANCE.isChildUser(context)) {
            textView3.setVisibility(8);
        } else {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.OverflowMenuPresenterKt$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OverflowMenuPresenterKt.m119showOverflowMenu$lambda0(context, popupWindow, view);
                }
            });
        }
        TextView textView4 = (TextView) viewInflate.findViewById(R.id.my_profile);
        if (!capabilities.isCustomerProfileSupported() || !capabilities.isActivityFeedSupported()) {
            textView4.setVisibility(8);
        } else {
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.OverflowMenuPresenterKt$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OverflowMenuPresenterKt.m120showOverflowMenu$lambda1(context, popupWindow, view);
                }
            });
        }
        ((TextView) viewInflate.findViewById(R.id.settings)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.OverflowMenuPresenterKt$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OverflowMenuPresenterKt.m121showOverflowMenu$lambda2(context, popupWindow, view);
            }
        });
        if (Log.getNonCriticalLoggingEnabled() && !SettingsUtil.getBooleanPref(context, R.string.setting_key_dev_debug_contact_us_pref)) {
            textView2.setText(R.string.dmusic_logging_email_logfile_title);
        }
        if (ChildUserUtil.INSTANCE.isChildUser(context)) {
            textView2.setVisibility(8);
            textView.setVisibility(8);
        } else {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.OverflowMenuPresenterKt$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OverflowMenuPresenterKt.m122showOverflowMenu$lambda3(context, popupWindow, view);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.OverflowMenuPresenterKt$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OverflowMenuPresenterKt.m123showOverflowMenu$lambda4(context, popupWindow, view);
                }
            });
        }
        TextView textView5 = (TextView) viewInflate.findViewById(R.id.car_mode_ingress);
        if (CarModeUtility.isCarModeEnabled()) {
            textView5.setVisibility(0);
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.OverflowMenuPresenterKt$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OverflowMenuPresenterKt.m124showOverflowMenu$lambda5(context, popupWindow, view);
                }
            });
        } else {
            textView5.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-0, reason: not valid java name */
    public static final void m119showOverflowMenu$lambda0(Context context, PopupWindow popupWindow, View view) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        handleConnectDeviceClick(context);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-1, reason: not valid java name */
    public static final void m120showOverflowMenu$lambda1(Context context, PopupWindow popupWindow, View view) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        handleMyProfileClick(context);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-2, reason: not valid java name */
    public static final void m121showOverflowMenu$lambda2(Context context, PopupWindow popupWindow, View view) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        context.startActivity(SettingsActivity.getStartIntent(context));
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-3, reason: not valid java name */
    public static final void m122showOverflowMenu$lambda3(Context context, PopupWindow popupWindow, View view) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        handleContactCustomerSupportClick(context);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-4, reason: not valid java name */
    public static final void m123showOverflowMenu$lambda4(Context context, PopupWindow popupWindow, View it) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        handleStoreClick(context, it);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-5, reason: not valid java name */
    public static final void m124showOverflowMenu$lambda5(Context context, PopupWindow popupWindow, View view) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_CAR_MODE_OVERFLOW_OPEN, PageType.BROWSE_HOME);
        CarModeUtility.INSTANCE.navigateToBrushFragment(context, true, false);
        popupWindow.dismiss();
    }

    private static final void handleStoreNavigation(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, MusicHomeActivity.class);
        intent.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
        intent.putExtra("com.amazon.mp3.store.extra.STORE_ARGUMENTS_BUNDLE", bundle);
        context.startActivity(intent);
    }

    private static final void handleConnectDeviceClick(Context context) {
        CastingUIMetricsLogger.ContentName contentName;
        Intent startIntent = CastingFragment.getStartIntent(context);
        if (context instanceof NowPlayingFragmentActivity) {
            contentName = CastingUIMetricsLogger.ContentName.NOW_PLAYING;
        } else {
            contentName = context instanceof MusicHomeActivity ? CastingUIMetricsLogger.ContentName.ACTION_BAR : null;
        }
        CastingUIMetricsLogger.recordCastingButtonClickMetric(contentName);
        context.startActivity(startIntent);
    }

    private static final void handleStoreClick(final Context context, View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "popup.menuInflater");
        menuInflater.inflate(R.menu.menu_store, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.amazon.mp3.actionbar.OverflowMenuPresenterKt$$ExternalSyntheticLambda6
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return OverflowMenuPresenterKt.m118handleStoreClick$lambda13(context, menuItem);
            }
        });
        popupMenu.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleStoreClick$lambda-13, reason: not valid java name */
    public static final boolean m118handleStoreClick$lambda13(Context context, MenuItem menuItem) {
        Bundle bundleBundleForBestsellers;
        Intrinsics.checkNotNullParameter(context, "$context");
        switch (menuItem.getItemId()) {
            case R.id.store_best_sellers /* 2131430259 */:
                bundleBundleForBestsellers = HTMLStoreActivityFactory.bundleForBestsellers();
                bundleBundleForBestsellers.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_best_sellers_tab));
                break;
            case R.id.store_browse_genres /* 2131430261 */:
                bundleBundleForBestsellers = HTMLStoreActivityFactory.bundleForGenres();
                bundleBundleForBestsellers.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_browse_genres_tab));
                break;
            case R.id.store_home /* 2131430264 */:
                bundleBundleForBestsellers = new Bundle();
                bundleBundleForBestsellers.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_music_tab));
                break;
            case R.id.store_new_releases /* 2131430266 */:
                bundleBundleForBestsellers = HTMLStoreActivityFactory.bundleForNewReleases();
                bundleBundleForBestsellers.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_new_releases_tab));
                break;
            case R.id.store_recommended /* 2131430268 */:
                bundleBundleForBestsellers = HTMLStoreActivityFactory.bundleForRecommendedForYou();
                bundleBundleForBestsellers.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_recommended_tab));
                break;
            default:
                bundleBundleForBestsellers = null;
                break;
        }
        if (bundleBundleForBestsellers == null) {
            return true;
        }
        handleStoreNavigation(context, bundleBundleForBestsellers);
        return true;
    }

    private static final void handleContactCustomerSupportClick(Context context) {
        boolean z = !AmazonApplication.BETA;
        boolean booleanPref = SettingsUtil.getBooleanPref(context, R.string.setting_key_dev_debug_contact_us_pref);
        Intent intent = new Intent(context, (Class<?>) CantileverActivity.class);
        if (booleanPref || (z && !Log.getNonCriticalLoggingEnabled())) {
            context.startActivity(intent);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.amazon.mp3.extra.CantileverActivity.EXTRA_LAUNCH_FEEDBACK_FORM", true);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private static final void handleMyProfileClick(Context context) {
        if (context instanceof MusicHomeActivity) {
            CustomerProfileMetrics.emitUiClickMetric(ActionType.GO_CUSTOMER_PROFILE);
            new CustomerProfileNavigation((MusicHomeActivity) context).loadProfile();
        }
    }
}
