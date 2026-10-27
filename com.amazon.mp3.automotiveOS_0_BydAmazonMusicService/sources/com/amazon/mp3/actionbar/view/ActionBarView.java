package com.amazon.mp3.actionbar.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.presentation.ActionBarDecorator;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ActionBarView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b%\b\u0016\u0018\u0000 k2\u00020\u00012\u00020\u0002:\u0001kBK\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(\u0012\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u00100\u001a\u00020\u0006\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u000102¢\u0006\u0004\bd\u0010eB\u001d\b\u0016\u0012\b\u0010)\u001a\u0004\u0018\u00010(\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\bd\u0010fB'\b\u0016\u0012\b\u0010)\u001a\u0004\u0018\u00010(\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010g\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\bd\u0010hB-\b\u0016\u0012\b\u0010)\u001a\u0004\u0018\u00010(\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u00100\u001a\u00020\u0006¢\u0006\u0004\bd\u0010iB7\b\u0016\u0012\b\u0010)\u001a\u0004\u0018\u00010(\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u00100\u001a\u00020\u0006\u0012\b\u00103\u001a\u0004\u0018\u000102¢\u0006\u0004\bd\u0010jJ\u001c\u0010\u0007\u001a\u00020\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0006H\u0002J!\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J/\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ7\u0010 \u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0004\b \u0010!J\b\u0010\"\u001a\u00020\tH\u0016J\u001c\u0010%\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010&\u001a\u00020\tH\u0016J\b\u0010'\u001a\u00020\tH\u0016R\u0019\u0010)\u001a\u0004\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0017\u0010\b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010-\u001a\u0004\b.\u0010/R\u0017\u00100\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b0\u0010-\u001a\u0004\b1\u0010/R\u0019\u00103\u001a\u0004\u0018\u0001028\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010A\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010?R\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR(\u0010#\u001a\u0004\u0018\u00010\u00032\b\u0010J\u001a\u0004\u0018\u00010\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR(\u0010$\u001a\u0004\u0018\u00010\u00032\b\u0010J\u001a\u0004\u0018\u00010\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bO\u0010L\"\u0004\bP\u0010NR\u0016\u0010S\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0014\u0010V\u001a\u00020=8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR$\u0010W\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bW\u0010/\"\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020=8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010UR$\u0010\\\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\\\u0010/\"\u0004\b]\u0010YR$\u0010^\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b^\u0010/\"\u0004\b_\u0010YR$\u0010`\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b`\u0010/\"\u0004\ba\u0010Y¨\u0006l"}, d2 = {"Lcom/amazon/mp3/actionbar/view/ActionBarView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/views/presentation/ActionBarDecorator;", "", "origTitle", "newTitle", "", "titleChanged", "showProfileButton", "", "setupActionBar", "Lcom/amazon/mp3/actionbar/view/ActionBarRightButton;", "button", "shouldShow", "updateButtonVisibility", "(Lcom/amazon/mp3/actionbar/view/ActionBarRightButton;Ljava/lang/Boolean;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "updateStyle", "center", "centerTitle", "Landroid/view/View;", "baseButton", "", "left", "right", "setIconMargins", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;)V", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "top", "end", "bottom", "setSearchBarMargins", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "refresh", "title", MediaTrack.ROLE_SUBTITLE, "setTitles", "addTopMargin", "resetHeight", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "Z", "getShowProfileButton", "()Z", "showOverflowButton", "getShowOverflowButton", "Lcom/amazon/music/find/view/FindSearchBarView;", "findSearchBarView", "Lcom/amazon/music/find/view/FindSearchBarView;", "getFindSearchBarView", "()Lcom/amazon/music/find/view/FindSearchBarView;", "Landroid/widget/TextView;", "titleView", "Landroid/widget/TextView;", "subTitleView", "constraintLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/ui/foundations/views/BaseButton;", "backButtonView", "Lcom/amazon/ui/foundations/views/BaseButton;", "feedButton", "actionButtonOverflow", "Lcom/amazon/mp3/actionbar/view/ActionBarRightButton;", "actionButtonView", "Landroid/widget/ImageView;", "logoView", "Landroid/widget/ImageView;", "Landroid/util/DisplayMetrics;", "displayMetrics", "Landroid/util/DisplayMetrics;", "value", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getSubtitle", "setSubtitle", "getSearchBar", "()Landroid/view/View;", "searchBar", "getActionButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "actionButton", "isBackButtonVisible", "setBackButtonVisible", "(Z)V", "getBackButton", "backButton", "isActionBarVisible", "setActionBarVisible", "isLogoVisible", "setLogoVisible", "isFeedButtonVisible", "setFeedButtonVisible", "initialTitle", "initialSubtitle", "<init>", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/String;ZZLcom/amazon/music/find/view/FindSearchBarView;)V", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;)V", "subTitle", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/String;)V", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;ZZ)V", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;ZZLcom/amazon/music/find/view/FindSearchBarView;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class ActionBarView extends ConstraintLayout implements ActionBarDecorator {
    private static final String TAG = ActionBarView.class.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(ActionBarView.class.getName());
    private final ActionBarRightButton actionButtonOverflow;
    private final BaseButton actionButtonView;
    private final FragmentActivity activity;
    private final BaseButton backButtonView;
    private final ConstraintLayout constraintLayout;
    private final DisplayMetrics displayMetrics;
    private final BaseButton feedButton;
    private final FindSearchBarView findSearchBarView;
    private final ImageView logoView;
    private final boolean showOverflowButton;
    private final boolean showProfileButton;
    private final TextView subTitleView;
    private final TextView titleView;

    public ActionBarView() {
        this(null, null, null, false, false, null, 63, null);
    }

    public /* synthetic */ ActionBarView(FragmentActivity fragmentActivity, String str, String str2, boolean z, boolean z2, FindSearchBarView findSearchBarView, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : fragmentActivity, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? null : findSearchBarView);
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public final boolean getShowProfileButton() {
        return this.showProfileButton;
    }

    public final boolean getShowOverflowButton() {
        return this.showOverflowButton;
    }

    public ActionBarView(FragmentActivity fragmentActivity, String str, String str2, boolean z, boolean z2, FindSearchBarView findSearchBarView) {
        super(fragmentActivity);
        this.activity = fragmentActivity;
        this.showProfileButton = z;
        this.showOverflowButton = z2;
        this.findSearchBarView = findSearchBarView;
        DisplayMetrics displayMetrics = getContext().getApplicationContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context\n            .app…          .displayMetrics");
        this.displayMetrics = displayMetrics;
        View.inflate(getContext(), R.layout.action_bar_view, this);
        setLayoutTransition(new LayoutTransition());
        View viewFindViewById = findViewById(R.id.Title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.Title)");
        this.titleView = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.Subtitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.Subtitle)");
        this.subTitleView = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.Action_Button_Back);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.Action_Button_Back)");
        this.backButtonView = (BaseButton) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.feed_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(R.id.feed_button)");
        this.feedButton = (BaseButton) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.Action_Button_Overflow);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(R.id.Action_Button_Overflow)");
        this.actionButtonOverflow = (ActionBarRightButton) viewFindViewById5;
        View viewFindViewById6 = findViewById(R.id.Logo_Image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(R.id.Logo_Image)");
        this.logoView = (ImageView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R.id.action_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(R.id.action_button)");
        this.actionButtonView = (BaseButton) viewFindViewById7;
        View viewFindViewById8 = findViewById(R.id.action_bar_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(R.id.action_bar_layout)");
        ConstraintLayout constraintLayout = (ConstraintLayout) viewFindViewById8;
        this.constraintLayout = constraintLayout;
        setClipChildren(false);
        setClipToPadding(false);
        setTitles(str, str2);
        setupActionBar(z);
        if (findSearchBarView != null) {
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-1, -2);
            layoutParams.topToBottom = constraintLayout.getId();
            int dimension = (int) getResources().getDimension(R.dimen.spacer_20);
            layoutParams.setMargins(dimension, dimension, dimension, dimension);
            findSearchBarView.setLayoutParams(layoutParams);
            addView(findSearchBarView);
        }
        if (fragmentActivity != null) {
            StyleSheetProvider.getStyleSheet().observe(fragmentActivity, new Observer() { // from class: com.amazon.mp3.actionbar.view.ActionBarView$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActionBarView.m128_init_$lambda0(this.f$0, (StyleSheet) obj);
                }
            });
        }
        centerTitle(true);
    }

    public final FindSearchBarView getFindSearchBarView() {
        return this.findSearchBarView;
    }

    public ActionBarView(FragmentActivity fragmentActivity, String str) {
        this(fragmentActivity, str, null, false, false, null);
    }

    public ActionBarView(FragmentActivity fragmentActivity, String str, String str2) {
        this(fragmentActivity, str, str2, false, false, null);
    }

    public ActionBarView(FragmentActivity fragmentActivity, String str, boolean z, boolean z2) {
        this(fragmentActivity, str, null, z, z2, null);
    }

    public ActionBarView(FragmentActivity fragmentActivity, String str, boolean z, boolean z2, FindSearchBarView findSearchBarView) {
        this(fragmentActivity, str, null, z, z2, findSearchBarView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m128_init_$lambda0(ActionBarView this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this$0.updateStyle(styleSheet);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setSearchBarMargins(Integer start, Integer top, Integer end, Integer bottom) {
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(findSearchBarView, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : start, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : end, (240 & 128) != 0 ? null : bottom);
        if (bottom == null) {
            return;
        }
        bottom.intValue();
        setPadding(0, 0, 0, bottom.intValue());
    }

    public void refresh() {
        setupActionBar(AmazonApplication.getCapabilities().isCustomerProfileSupported());
    }

    public void setTitles(String title, String subtitle) {
        CharSequence text = this.titleView.getText();
        String string = text == null ? null : text.toString();
        this.titleView.setText(title);
        if (subtitle == null || subtitle.length() == 0) {
            this.titleView.setGravity(16);
            this.subTitleView.setVisibility(8);
        } else {
            this.titleView.setGravity(0);
            this.subTitleView.setVisibility(0);
            this.subTitleView.setText(subtitle);
        }
        if (AccessibilityUtil.isAccessibilityEnabled(getContext()) && titleChanged(string, title)) {
            this.titleView.sendAccessibilityEvent(8);
        }
    }

    private final boolean titleChanged(String origTitle, String newTitle) {
        if (origTitle == null || newTitle == null) {
            if (origTitle == newTitle) {
                return false;
            }
        } else if (StringsKt__StringsJVMKt.equals(newTitle, origTitle, true)) {
            return false;
        }
        return true;
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void addTopMargin() {
        Resources.Theme theme;
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(this.displayMetrics.widthPixels, -2);
        TypedValue typedValue = new TypedValue();
        FragmentActivity fragmentActivity = this.activity;
        Boolean boolValueOf = null;
        if (fragmentActivity != null && (theme = fragmentActivity.getTheme()) != null) {
            boolValueOf = Boolean.valueOf(theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true));
        }
        if (boolValueOf != null && boolValueOf.booleanValue() && this.findSearchBarView != null) {
            layoutParams.setMargins(0, (TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics()) - ((int) getResources().getDimension(R.dimen.spacer_48))) / 2, 0, 0);
        }
        setLayoutParams(layoutParams);
    }

    private final void setupActionBar(boolean showProfileButton) {
        setLayoutParams(new Toolbar.LayoutParams(this.displayMetrics.widthPixels, -2));
        this.constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(this.displayMetrics.widthPixels, -1));
        if (showProfileButton && AmazonApplication.getCapabilities().isActivityFeedSupported()) {
            if (AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
                CustomerProfileDetailManager.getInstance().syncCustomerInformation(getContext());
            }
        } else if (showProfileButton && AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
            View viewFindViewById = findViewById(R.id.Action_Button_Profile);
            if (viewFindViewById == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.actionbar.view.ActionBarRightButton");
            }
            ActionBarRightButton actionBarRightButton = (ActionBarRightButton) viewFindViewById;
            CustomerProfileDetailManager.getInstance().loadAvatarImage(getContext(), (ImageView) actionBarRightButton.findViewById(R.id.Profile));
            updateButtonVisibility(actionBarRightButton, Boolean.TRUE);
        }
        updateButtonVisibility(this.actionButtonOverflow, Boolean.valueOf(this.showOverflowButton));
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarView.setupActionBar.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ActionBarView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (ActionBarView.this.getShowOverflowButton()) {
                    ActionBarView.this.setX(0.0f);
                }
            }
        });
    }

    private final void updateButtonVisibility(ActionBarRightButton button, Boolean shouldShow) {
        if (shouldShow != null && shouldShow.booleanValue()) {
            button.setVisibility(0);
        } else {
            button.setVisibility(8);
        }
    }

    private final void updateStyle(StyleSheet styleSheet) {
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(this.titleView, fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(this.subTitleView, fontStyle2);
        }
        this.actionButtonOverflow.initButtonStyle(styleSheet);
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
        if (iconBuilder != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_navigation_goback);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…ble.ic_navigation_goback)");
            BaseButton.StyleBuilder styleBuilderWithIcon = iconBuilder.withIcon(drawable);
            if (styleBuilderWithIcon != null) {
                styleBuilderWithIcon.applyStyle(this.backButtonView);
            }
        }
        setIconMargins(this.actionButtonOverflow, spacerInPixels, spacerInPixels);
        setIconMargins(this.feedButton, spacerInPixels, spacerInPixels);
    }

    public String getTitle() {
        CharSequence text = this.titleView.getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setTitle(String str) {
        setTitles(str, getSubtitle());
    }

    public String getSubtitle() {
        CharSequence text = this.subTitleView.getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    public void setSubtitle(String str) {
        setTitles(getTitle(), str);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public View getSearchBar() {
        return this.findSearchBarView;
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    /* JADX INFO: renamed from: getActionButton, reason: from getter */
    public BaseButton getActionButtonView() {
        return this.actionButtonView;
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setBackButtonVisible(boolean z) {
        this.backButtonView.setVisibility(z ? 0 : 8);
        this.backButtonView.setClickable(z);
        this.backButtonView.setEnabled(z);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    /* JADX INFO: renamed from: getBackButton, reason: from getter */
    public BaseButton getBackButtonView() {
        return this.backButtonView;
    }

    private final void centerTitle(boolean center) {
        int dimension = (int) getResources().getDimension(R.dimen.spacer_48);
        if (center) {
            LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
            layoutParamUtils.setLayoutMargins(this.titleView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
            layoutParamUtils.setLayoutMargins(this.subTitleView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
            this.titleView.setPaddingRelative(dimension, 0, dimension, 0);
            this.subTitleView.setPaddingRelative(dimension, 0, dimension, 0);
            this.titleView.setGravity(1);
            this.titleView.setTextAlignment(4);
            this.subTitleView.setGravity(1);
            this.subTitleView.setTextAlignment(4);
            return;
        }
        LayoutParamUtils layoutParamUtils2 = LayoutParamUtils.INSTANCE;
        layoutParamUtils2.setLayoutMargins(this.titleView, 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        layoutParamUtils2.setLayoutMargins(this.subTitleView, 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        this.titleView.setPaddingRelative(0, 0, dimension, 0);
        this.subTitleView.setPaddingRelative(0, 0, dimension, 0);
        this.titleView.setGravity(8388611);
        this.titleView.setTextAlignment(2);
        this.subTitleView.setGravity(8388611);
        this.subTitleView.setTextAlignment(2);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setActionBarVisible(boolean z) {
        this.constraintLayout.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setLogoVisible(boolean z) {
        this.titleView.setVisibility(z ? 8 : 0);
        this.subTitleView.setVisibility(z ? 8 : 0);
        this.logoView.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setFeedButtonVisible(boolean z) {
        this.feedButton.setVisibility(z ? 0 : 8);
    }

    private final void setIconMargins(View baseButton, Integer left, Integer right) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(baseButton, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : 0);
    }

    public void resetHeight() {
        Resources resources;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Integer numValueOf = null;
        if (layoutParams == null) {
            layoutParams = null;
        } else {
            Context context = getContext();
            if (context != null && (resources = context.getResources()) != null) {
                numValueOf = Integer.valueOf((int) resources.getDimension(R.dimen.action_bar_height));
            }
            layoutParams.height = numValueOf == null ? layoutParams.height : numValueOf.intValue();
        }
        setLayoutParams(layoutParams);
    }
}
