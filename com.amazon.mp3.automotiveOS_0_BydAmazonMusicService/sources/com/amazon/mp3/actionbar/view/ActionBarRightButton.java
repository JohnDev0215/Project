package com.amazon.mp3.actionbar.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.GravityCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.R$styleable;
import com.amazon.mp3.activity.CantileverActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.DemoModeManager;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.store.html5.activity.StoreFragment;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarRightButton extends FrameLayout {
    private static final String TAG = ActionBarRightButton.class.getSimpleName();
    private DismissHotspotOverlayListener dismissHotspotOverlayListener;
    private BaseButton mOverflow;
    private View mProfile;
    private BaseButton mSearch;
    private OpenHotSpotOverlayListener openHotSpotOverlayListener;

    public interface DismissHotspotOverlayListener {
        void dismissHotspots();
    }

    public interface OpenHotSpotOverlayListener {
        void showHotspots();
    }

    public enum Style {
        SEARCH,
        PROFILE,
        OVERFLOW
    }

    public ActionBarRightButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setStyleFromAttributes(context, attrs);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.actionbar.view.ActionBarRightButton$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$actionbar$view$ActionBarRightButton$Style;

        static {
            int[] iArr = new int[Style.values().length];
            $SwitchMap$com$amazon$mp3$actionbar$view$ActionBarRightButton$Style = iArr;
            try {
                iArr[Style.SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$actionbar$view$ActionBarRightButton$Style[Style.PROFILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$actionbar$view$ActionBarRightButton$Style[Style.OVERFLOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void setStyle(final Style style) {
        int i = AnonymousClass5.$SwitchMap$com$amazon$mp3$actionbar$view$ActionBarRightButton$Style[style.ordinal()];
        if (i == 1) {
            initSearchActionButton(getContext());
            return;
        }
        if (i == 2) {
            initProfileActionButton(getContext());
        } else {
            if (i == 3) {
                initOverflowActionButton(getContext());
                return;
            }
            throw new IllegalArgumentException("Called setStyle for unhandled style " + style);
        }
    }

    public void initButtonStyle(StyleSheet styleSheet) {
        IconSizeKey iconSizeKey = IconSizeKey.MEDIUM;
        IconStyleKey iconStyleKey = IconStyleKey.PRIMARY;
        styleSheet.getIconBuilder(iconSizeKey, iconStyleKey).withIcon(getResources().getDrawable(R.drawable.ic_navigation_settings)).applyStyle(this.mOverflow);
        styleSheet.getIconBuilder(iconSizeKey, iconStyleKey).withIcon(getResources().getDrawable(R.drawable.ic_navigation_search)).applyStyle(this.mSearch);
    }

    private void init(final Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.action_bar_right_button, (ViewGroup) this, true);
        this.mSearch = (BaseButton) findViewById(R.id.Search);
        this.mProfile = findViewById(R.id.Profile);
        BaseButton baseButton = (BaseButton) findViewById(R.id.overflow_button_open);
        this.mOverflow = baseButton;
        baseButton.setBackground(null);
    }

    private void setStyleFromAttributes(final Context context, final AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, R$styleable.ActionBarRightButton, 0, 0);
        try {
            int integer = typedArrayObtainStyledAttributes.getInteger(0, Style.SEARCH.ordinal());
            if (integer < 0 || integer >= Style.values().length) {
                throw new IllegalArgumentException("Ordinal " + integer + " does not map to a Style");
            }
            setStyle(Style.values()[integer]);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void initOverflowActionButton(final Context context) {
        this.mSearch.setVisibility(8);
        this.mProfile.setVisibility(8);
        this.mOverflow.setVisibility(0);
        this.mOverflow.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initOverflowActionButton$0(context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initOverflowActionButton$0(Context context, View view) {
        showOverflowMenu(context, view);
        DismissHotspotOverlayListener dismissHotspotOverlayListener = this.dismissHotspotOverlayListener;
        if (dismissHotspotOverlayListener != null) {
            dismissHotspotOverlayListener.dismissHotspots();
        }
    }

    private void initSearchActionButton(final Context context) {
        this.mSearch.setVisibility(0);
        this.mProfile.setVisibility(8);
        this.mOverflow.setVisibility(8);
        this.mSearch.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                UserInteractionAppEvent.builder(ActionType.GO_SEARCH.getMetricsValue()).withInteractionType(InteractionType.TAP).publish();
                Intent intent = new Intent();
                intent.setClass(context, MusicHomeActivity.class);
                intent.putExtra("com.amazon.mp3.fragment.extra", SearchTabFragmentHost.getFindFragmentName());
                context.startActivity(intent);
            }
        });
    }

    private void initProfileActionButton(final Context context) {
        if (AmazonApplication.getCapabilities().isCustomerProfileSupported() && !AmazonApplication.getCapabilities().isActivityFeedSupported()) {
            this.mSearch.setVisibility(8);
            this.mProfile.setVisibility(0);
            this.mOverflow.setVisibility(8);
            this.mProfile.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton.2
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    if (context instanceof MusicHomeActivity) {
                        CustomerProfileMetrics.emitUiClickMetric(ActionType.GO_CUSTOMER_PROFILE);
                        new CustomerProfileNavigation((MusicHomeActivity) context).loadProfile();
                    }
                }
            });
            return;
        }
        this.mSearch.setVisibility(8);
        this.mProfile.setVisibility(8);
        this.mOverflow.setVisibility(8);
        Log.debug(TAG, "Profiles feature is turned off, not initializing customer's social profile");
    }

    private void showOverflowMenu(final Context context, final View v) {
        Drawable drawable;
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.menu_overflow, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setWindowLayoutMode(-2, -2);
        popupWindow.setContentView(viewInflate);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton.3
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (ActionBarRightButton.this.openHotSpotOverlayListener != null) {
                    ActionBarRightButton.this.openHotSpotOverlayListener.showHotspots();
                }
            }
        });
        popupWindow.showAtLocation(viewInflate, (v.getLayoutDirection() == 0 ? GravityCompat.END : 8388611) | 48, 0, v.getHeight());
        viewInflate.findViewById(R.id.store).setVisibility(AmazonApplication.getCapabilities().shouldShowStoreIcon() ? 0 : 8);
        viewInflate.findViewById(R.id.contact_customer_support).setVisibility(DemoModeManager.get(context).isDemoMode() ? 8 : 0);
        TextView textView = (TextView) viewInflate.findViewById(R.id.contact_customer_support);
        textView.setText(R.string.dmusic_setting_help_and_feedback_title);
        Drawable drawable2 = getContext().getResources().getDrawable(R.drawable.ic_alerts_help);
        Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
        if (compoundDrawablesRelative.length > 0 && (drawable = compoundDrawablesRelative[0]) != null) {
            drawable2.setBounds(drawable.copyBounds());
        } else {
            drawable2.setBounds(0, 0, 60, 60);
        }
        textView.setCompoundDrawables(drawable2, null, null, null);
        if (Log.getNonCriticalLoggingEnabled() && !SettingsUtil.getBooleanPref(context, R.string.setting_key_dev_debug_contact_us_pref)) {
            ((TextView) viewInflate.findViewById(R.id.contact_customer_support)).setText(R.string.dmusic_logging_email_logfile_title);
        }
        if (UserSubscriptionUtil.isNightwingOnly() || NowPlayingUtil.isVideoPlaying() || ChildUserUtil.INSTANCE.isChildUser(context)) {
            viewInflate.findViewById(R.id.connect_device).setVisibility(8);
        } else {
            viewInflate.findViewById(R.id.connect_device).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showOverflowMenu$1(context, popupWindow, view);
                }
            });
        }
        if (!AmazonApplication.getCapabilities().isCustomerProfileSupported() || !AmazonApplication.getCapabilities().isActivityFeedSupported()) {
            viewInflate.findViewById(R.id.my_profile).setVisibility(8);
        } else {
            viewInflate.findViewById(R.id.my_profile).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showOverflowMenu$2(context, popupWindow, view);
                }
            });
        }
        viewInflate.findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActionBarRightButton.lambda$showOverflowMenu$3(context, popupWindow, view);
            }
        });
        if (ChildUserUtil.INSTANCE.isChildUser(context)) {
            viewInflate.findViewById(R.id.contact_customer_support).setVisibility(8);
            viewInflate.findViewById(R.id.store).setVisibility(8);
        } else {
            viewInflate.findViewById(R.id.contact_customer_support).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showOverflowMenu$4(context, popupWindow, view);
                }
            });
            viewInflate.findViewById(R.id.store).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showOverflowMenu$5(context, v, popupWindow, view);
                }
            });
        }
        View viewFindViewById = viewInflate.findViewById(R.id.car_mode_ingress);
        if (CarModeUtility.isCarModeEnabled()) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActionBarRightButton.lambda$showOverflowMenu$6(context, popupWindow, view);
                }
            });
        } else {
            viewFindViewById.setVisibility(8);
        }
        viewInflate.findViewById(R.id.playback_test_page).setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOverflowMenu$1(Context context, PopupWindow popupWindow, View view) {
        handleConnectDeviceClick(context);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOverflowMenu$2(Context context, PopupWindow popupWindow, View view) {
        handleMyProfileClick(context);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showOverflowMenu$3(Context context, PopupWindow popupWindow, View view) {
        context.startActivity(SettingsActivity.getStartIntent(context));
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOverflowMenu$4(Context context, PopupWindow popupWindow, View view) {
        handleContactCustomerSupportClick(context);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOverflowMenu$5(Context context, View view, PopupWindow popupWindow, View view2) {
        handleStoreClick(context, view);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showOverflowMenu$6(Context context, PopupWindow popupWindow, View view) {
        AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_CAR_MODE_OVERFLOW_OPEN, PageType.BROWSE_HOME);
        CarModeUtility.INSTANCE.navigateToBrushFragment(context, true, false);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStoreNavigation(final Context context, final Bundle args) {
        Intent intent = new Intent();
        intent.setClass(context, MusicHomeActivity.class);
        intent.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
        intent.putExtra("com.amazon.mp3.store.extra.STORE_ARGUMENTS_BUNDLE", args);
        context.startActivity(intent);
    }

    private void handleConnectDeviceClick(final Context context) {
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

    private void handleStoreClick(final Context context, View v) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_store, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarRightButton.4
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(final MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.store_best_sellers /* 2131430259 */:
                        Bundle bundleBundleForBestsellers = HTMLStoreActivityFactory.bundleForBestsellers();
                        bundleBundleForBestsellers.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_best_sellers_tab));
                        ActionBarRightButton.this.handleStoreNavigation(context, bundleBundleForBestsellers);
                        break;
                    case R.id.store_browse_genres /* 2131430261 */:
                        Bundle bundleBundleForGenres = HTMLStoreActivityFactory.bundleForGenres();
                        bundleBundleForGenres.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_browse_genres_tab));
                        ActionBarRightButton.this.handleStoreNavigation(context, bundleBundleForGenres);
                        break;
                    case R.id.store_home /* 2131430264 */:
                        Bundle bundle = new Bundle();
                        bundle.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_music_tab));
                        ActionBarRightButton.this.handleStoreNavigation(context, bundle);
                        break;
                    case R.id.store_new_releases /* 2131430266 */:
                        Bundle bundleBundleForNewReleases = HTMLStoreActivityFactory.bundleForNewReleases();
                        bundleBundleForNewReleases.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_new_releases_tab));
                        ActionBarRightButton.this.handleStoreNavigation(context, bundleBundleForNewReleases);
                        break;
                    case R.id.store_recommended /* 2131430268 */:
                        Bundle bundleBundleForRecommendedForYou = HTMLStoreActivityFactory.bundleForRecommendedForYou();
                        bundleBundleForRecommendedForYou.putString("com.amazon.mp3.store.TITLE", context.getString(R.string.dmusic_store_recommended_tab));
                        ActionBarRightButton.this.handleStoreNavigation(context, bundleBundleForRecommendedForYou);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    private void handleContactCustomerSupportClick(final Context context) {
        boolean z = !AmazonApplication.BETA;
        boolean booleanPref = SettingsUtil.getBooleanPref(context, R.string.setting_key_dev_debug_contact_us_pref);
        Intent intent = new Intent(context, (Class<?>) CantileverActivity.class);
        if ((z && !Log.getNonCriticalLoggingEnabled()) || booleanPref) {
            context.startActivity(intent);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.amazon.mp3.extra.CantileverActivity.EXTRA_LAUNCH_FEEDBACK_FORM", true);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private void handleMyProfileClick(final Context context) {
        if (context instanceof MusicHomeActivity) {
            CustomerProfileMetrics.emitUiClickMetric(ActionType.GO_CUSTOMER_PROFILE);
            new CustomerProfileNavigation((MusicHomeActivity) context).loadProfile();
        }
    }

    public void setOpenHotSpotOverlayListener(final OpenHotSpotOverlayListener openHotSpotOverlayListener) {
        this.openHotSpotOverlayListener = openHotSpotOverlayListener;
    }

    public void setDismissHotspotOverlayListener(final DismissHotspotOverlayListener dismissHotspotOverlayListener) {
        this.dismissHotspotOverlayListener = dismissHotspotOverlayListener;
    }
}
