package com.amazon.mp3.bottomnavbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.tabbar.Tab;
import com.amazon.mp3.bottomnavbar.tabbar.TabBar;
import com.amazon.mp3.bottomnavbar.tabbar.TabListener;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: BottomNavigationBar.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 *2\u00020\u0001:\u0001*B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005J\u0013\u0010\u0014\u001a\n \u0015*\u0004\u0018\u00010\u00070\u0007¢\u0006\u0002\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0012J\u0010\u0010\u0018\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u001e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0016\u0010 \u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u0007J\u0006\u0010\"\u001a\u00020\u0012J\u000e\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0007J\u0016\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001fJ\u0006\u0010)\u001a\u00020\u0012R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/mp3/bottomnavbar/BottomNavigationBar;", "Landroid/widget/RelativeLayout;", "context", "Landroidx/fragment/app/FragmentActivity;", "rootView", "Landroid/view/View;", "isDisableAlexa", "", "(Landroidx/fragment/app/FragmentActivity;Landroid/view/View;Z)V", "rightView", "Landroid/widget/FrameLayout;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "tabBar", "Lcom/amazon/mp3/bottomnavbar/tabbar/TabBar;", "windowManager", "Landroid/view/WindowManager;", "disablePersistentUpsellTab", "", "getRightView", "getToolTipDismissState", "kotlin.jvm.PlatformType", "()Ljava/lang/Boolean;", "selectPreviousTab", "setActiveTab", "tab", "Lcom/amazon/mp3/bottomnavbar/tabbar/Tab;", "setButton", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/bottomnavbar/tabbar/TabListener;", "index", "", "setTabEnabled", Constants.ENABLE_DISABLE, "setupToolTipView", "showBottomBar", "shouldShow", "showToolTip", "toolTip", "", "tabIndex", "updateView", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BottomNavigationBar extends RelativeLayout {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FrameLayout rightView;
    private StyleSheet styleSheet;
    private final TabBar tabBar;
    private final WindowManager windowManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomNavigationBar(FragmentActivity context, View rootView, boolean z) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.windowManager = (WindowManager) systemService;
        Validate.notNull(rootView);
        View viewFindViewById = rootView.findViewById(R.id.right_view);
        this.rightView = viewFindViewById instanceof FrameLayout ? (FrameLayout) viewFindViewById : null;
        View viewFindViewById2 = rootView.findViewById(R.id.tab_bar);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.bottomnavbar.tabbar.TabBar");
        }
        TabBar tabBar = (TabBar) viewFindViewById2;
        this.tabBar = tabBar;
        if (z) {
            tabBar.disableAlexaTab();
        }
        StyleSheetProvider.getStyleSheet().observe(context, new Observer() { // from class: com.amazon.mp3.bottomnavbar.BottomNavigationBar$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BottomNavigationBar.m426_init_$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m426_init_$lambda0(BottomNavigationBar this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = styleSheet;
        TabBar tabBar = this$0.tabBar;
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        tabBar.setStyle(styleSheet);
    }

    public final void setTabEnabled(Tab tab, boolean isEnabled) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        this.tabBar.setTabEnabled(tab, isEnabled);
    }

    public final void setActiveTab(Tab tab) {
        this.tabBar.selectTab(tab);
    }

    public final void setButton(Tab tab, TabListener listener, int index) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Validate.notNull(tab);
        Validate.notNull(listener);
        this.tabBar.setupTabButton(tab, listener, index);
    }

    public final void setupToolTipView() {
        this.tabBar.setupToolTipView();
    }

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
    public final void showToolTip(String toolTip, int tabIndex) {
        Intrinsics.checkNotNullParameter(toolTip, "toolTip");
        int i = getContext().getResources().getDisplayMetrics().densityDpi;
        if (i == 120 || i == 160) {
            TabBar tabBar = this.tabBar;
            tabBar.showToolTip(toolTip, tabBar);
        } else {
            this.tabBar.showToolTip(toolTip, tabIndex);
        }
    }

    public final Boolean getToolTipDismissState() {
        return this.tabBar.getToolTipDismissState();
    }

    public final void selectPreviousTab() {
        this.tabBar.selectPreviousTab();
    }

    public final View getRightView() {
        return this.rightView;
    }

    public final void showBottomBar(boolean shouldShow) {
        this.tabBar.setVisibility(shouldShow ? 0 : 8);
    }

    public final void updateView() {
        StyleSheet styleSheet = this.styleSheet;
        ScreenSizeKey screenSizeKey = styleSheet == null ? null : styleSheet.getScreenSizeKey(this.tabBar.getWidth());
        if (screenSizeKey != null) {
            this.tabBar.updateView(screenSizeKey.compareTo(ScreenSizeKey.MEDIUM) > 0);
        }
        this.tabBar.measure(-2, -2);
    }

    public final void disablePersistentUpsellTab() {
        this.tabBar.disablePersistentUpsellTab();
    }

    /* JADX INFO: compiled from: BottomNavigationBar.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/bottomnavbar/BottomNavigationBar$Companion;", "", "()V", "attach", "Lcom/amazon/mp3/bottomnavbar/BottomNavigationBar;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "root", "Landroid/view/ViewGroup;", "isDisableAlexa", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmOverloads
        public final BottomNavigationBar attach(FragmentActivity activity, ViewGroup root, boolean isDisableAlexa) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(root, "root");
            Validate.notNull(activity);
            Validate.notNull(root);
            View rootView = LayoutInflater.from(activity).inflate(R.layout.bottom_nav_bar, root, true);
            Intrinsics.checkNotNullExpressionValue(rootView, "rootView");
            return new BottomNavigationBar(activity, rootView, isDisableAlexa);
        }
    }
}
