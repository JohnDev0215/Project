package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactTypefaceUtils;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenStackHeaderConfig.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010p\u001a\u00020o¢\u0006\u0004\bq\u0010rJ\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J0\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0014J\u0006\u0010\u0011\u001a\u00020\u0006J\b\u0010\u0012\u001a\u00020\u0006H\u0014J\b\u0010\u0013\u001a\u00020\u0006H\u0014J\u0006\u0010\u0014\u001a\u00020\u0006J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u000bJ\u0006\u0010\u0019\u001a\u00020\u0006J\u0016\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u000bJ\u0010\u0010\u001d\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u001f\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002J\u0010\u0010!\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0002J\u000e\u0010$\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\"J\u000e\u0010&\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u000bJ\u000e\u0010'\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u000bJ\u000e\u0010)\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\tJ\u0017\u0010*\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b*\u0010+J\u000e\u0010-\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\tJ\u000e\u0010/\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\tJ\u000e\u00101\u001a\u00020\u00062\u0006\u00100\u001a\u00020\tJ\u000e\u00103\u001a\u00020\u00062\u0006\u00102\u001a\u00020\tJ\u000e\u00105\u001a\u00020\u00062\u0006\u00104\u001a\u00020\tJ\u0010\u00107\u001a\u00020\u00062\b\u00106\u001a\u0004\u0018\u00010\u0002R$\u0010:\u001a\u0012\u0012\u0004\u0012\u00020\u001608j\b\u0012\u0004\u0012\u00020\u0016`98\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0017\u0010=\u001a\u00020<8\u0006¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u0018\u0010A\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010C\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0018\u0010G\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010DR\u0018\u0010H\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010DR\u0016\u0010I\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010FR\u0018\u0010L\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010BR\u0016\u0010M\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010NR\u0016\u0010P\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010NR\u0016\u0010Q\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010NR\u0016\u0010R\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010NR\u0016\u0010S\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010NR\u0016\u0010T\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010NR\u0016\u0010U\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010FR\u0016\u0010V\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010NR\u0014\u0010W\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010FR\u0014\u0010X\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010FR\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010_\u001a\u0004\u0018\u00010\\8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b]\u0010^R\u0016\u0010c\u001a\u0004\u0018\u00010`8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\ba\u0010bR\u0016\u0010g\u001a\u0004\u0018\u00010d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\be\u0010fR\u0013\u0010k\u001a\u0004\u0018\u00010h8F¢\u0006\u0006\u001a\u0004\bi\u0010jR\u0011\u0010n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\bl\u0010m¨\u0006s"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "Landroid/view/ViewGroup;", "", "eventName", "Lcom/facebook/react/bridge/WritableMap;", "eventContent", "", "sendEvent", "maybeUpdate", "", "changed", "", "l", "t", "r", "b", "onLayout", "destroy", "onAttachedToWindow", "onDetachedFromWindow", "onUpdate", "index", "Lcom/swmansion/rnscreens/ScreenStackHeaderSubview;", "getConfigSubview", "removeConfigSubview", "removeAllConfigSubviews", "child", "addConfigSubview", "title", "setTitle", "titleFontFamily", "setTitleFontFamily", "fontWeightString", "setTitleFontWeight", "", "titleFontSize", "setTitleFontSize", "color", "setTitleColor", "setTintColor", "topInsetEnabled", "setTopInsetEnabled", "setBackgroundColor", "(Ljava/lang/Integer;)V", "hideShadow", "setHideShadow", "hideBackButton", "setHideBackButton", "hidden", "setHidden", "translucent", "setTranslucent", "backButtonInCustomView", "setBackButtonInCustomView", "direction", "setDirection", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mConfigSubviews", "Ljava/util/ArrayList;", "Lcom/swmansion/rnscreens/CustomToolbar;", "toolbar", "Lcom/swmansion/rnscreens/CustomToolbar;", "getToolbar", "()Lcom/swmansion/rnscreens/CustomToolbar;", "headerTopInset", "Ljava/lang/Integer;", "mTitle", "Ljava/lang/String;", "mTitleColor", "I", "mTitleFontFamily", "mDirection", "mTitleFontSize", "F", "mTitleFontWeight", "mBackgroundColor", "mIsHidden", "Z", "mIsBackButtonHidden", "mIsShadowHidden", "mDestroyed", "mBackButtonInCustomView", "mIsTopInsetEnabled", "mIsTranslucent", "mTintColor", "mIsAttachedToWindow", "mDefaultStartInset", "mDefaultStartInsetWithNavigation", "Landroid/view/View$OnClickListener;", "mBackClickListener", "Landroid/view/View$OnClickListener;", "Lcom/swmansion/rnscreens/Screen;", "getScreen", "()Lcom/swmansion/rnscreens/Screen;", "screen", "Lcom/swmansion/rnscreens/ScreenStack;", "getScreenStack", "()Lcom/swmansion/rnscreens/ScreenStack;", "screenStack", "Landroid/widget/TextView;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "Lcom/swmansion/rnscreens/ScreenStackFragment;", "getScreenFragment", "()Lcom/swmansion/rnscreens/ScreenStackFragment;", "screenFragment", "getConfigSubviewsCount", "()I", "configSubviewsCount", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "react-native-screens_release"}, k = 1, mv = {1, 6, 0})
public final class ScreenStackHeaderConfig extends ViewGroup {
    private Integer headerTopInset;
    private boolean mBackButtonInCustomView;
    private final View.OnClickListener mBackClickListener;
    private Integer mBackgroundColor;
    private final ArrayList<ScreenStackHeaderSubview> mConfigSubviews;
    private final int mDefaultStartInset;
    private final int mDefaultStartInsetWithNavigation;
    private boolean mDestroyed;
    private String mDirection;
    private boolean mIsAttachedToWindow;
    private boolean mIsBackButtonHidden;
    private boolean mIsHidden;
    private boolean mIsShadowHidden;
    private boolean mIsTopInsetEnabled;
    private boolean mIsTranslucent;
    private int mTintColor;
    private String mTitle;
    private int mTitleColor;
    private String mTitleFontFamily;
    private float mTitleFontSize;
    private int mTitleFontWeight;
    private final CustomToolbar toolbar;

    /* JADX INFO: compiled from: ScreenStackHeaderConfig.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenStackHeaderSubview.Type.values().length];
            iArr[ScreenStackHeaderSubview.Type.LEFT.ordinal()] = 1;
            iArr[ScreenStackHeaderSubview.Type.RIGHT.ordinal()] = 2;
            iArr[ScreenStackHeaderSubview.Type.CENTER.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenStackHeaderConfig(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mConfigSubviews = new ArrayList<>(3);
        this.mIsTopInsetEnabled = true;
        this.mBackClickListener = new View.OnClickListener() { // from class: com.swmansion.rnscreens.ScreenStackHeaderConfig$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenStackHeaderConfig.m2765mBackClickListener$lambda1(this.f$0, view);
            }
        };
        setVisibility(8);
        CustomToolbar customToolbar = new CustomToolbar(context, this);
        this.toolbar = customToolbar;
        this.mDefaultStartInset = customToolbar.getContentInsetStart();
        this.mDefaultStartInsetWithNavigation = customToolbar.getContentInsetStartWithNavigation();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            customToolbar.setBackgroundColor(typedValue.data);
        }
        customToolbar.setClipChildren(false);
    }

    public final CustomToolbar getToolbar() {
        return this.toolbar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mBackClickListener$lambda-1, reason: not valid java name */
    public static final void m2765mBackClickListener$lambda1(ScreenStackHeaderConfig this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScreenStackFragment screenFragment = this$0.getScreenFragment();
        if (screenFragment == null) {
            return;
        }
        ScreenStack screenStack = this$0.getScreenStack();
        if (screenStack != null && Intrinsics.areEqual(screenStack.getRootScreen(), screenFragment.getScreen())) {
            Fragment parentFragment = screenFragment.getParentFragment();
            if (parentFragment instanceof ScreenStackFragment) {
                ScreenStackFragment screenStackFragment = (ScreenStackFragment) parentFragment;
                if (screenStackFragment.getScreen().getMNativeBackButtonDismissalEnabled()) {
                    screenStackFragment.dismiss();
                    return;
                } else {
                    screenStackFragment.dispatchHeaderBackButtonClickedEvent();
                    return;
                }
            }
            return;
        }
        if (screenFragment.getScreen().getMNativeBackButtonDismissalEnabled()) {
            screenFragment.dismiss();
        } else {
            screenFragment.dispatchHeaderBackButtonClickedEvent();
        }
    }

    private final void sendEvent(String eventName, WritableMap eventContent) {
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        }
        RCTEventEmitter rCTEventEmitter = (RCTEventEmitter) ((ReactContext) context).getJSModule(RCTEventEmitter.class);
        if (rCTEventEmitter == null) {
            return;
        }
        rCTEventEmitter.receiveEvent(getId(), eventName, eventContent);
    }

    public final void destroy() {
        this.mDestroyed = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        sendEvent("onAttached", null);
        if (this.headerTopInset == null) {
            this.headerTopInset = Integer.valueOf(getRootWindowInsets().getSystemWindowInsetTop());
        }
        onUpdate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
        sendEvent("onDetached", null);
    }

    private final Screen getScreen() {
        ViewParent parent = getParent();
        if (parent instanceof Screen) {
            return (Screen) parent;
        }
        return null;
    }

    private final ScreenStack getScreenStack() {
        Screen screen = getScreen();
        ScreenContainer<?> container = screen == null ? null : screen.getContainer();
        if (container instanceof ScreenStack) {
            return (ScreenStack) container;
        }
        return null;
    }

    public final ScreenStackFragment getScreenFragment() {
        ViewParent parent = getParent();
        if (!(parent instanceof Screen)) {
            return null;
        }
        ScreenFragment fragment = ((Screen) parent).getFragment();
        if (fragment instanceof ScreenStackFragment) {
            return (ScreenStackFragment) fragment;
        }
        return null;
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
    public final void onUpdate() {
        Drawable navigationIcon;
        ScreenStackFragment screenFragment;
        ScreenStackFragment screenFragment2;
        ReactContext reactContextTryGetContext;
        ScreenStack screenStack = getScreenStack();
        boolean z = screenStack == null || Intrinsics.areEqual(screenStack.getTopScreen(), getParent());
        if (this.mIsAttachedToWindow && z && !this.mDestroyed) {
            ScreenStackFragment screenFragment3 = getScreenFragment();
            AppCompatActivity appCompatActivity = (AppCompatActivity) (screenFragment3 == null ? null : screenFragment3.getActivity());
            if (appCompatActivity == null) {
                return;
            }
            String str = this.mDirection;
            if (str != null) {
                if (Intrinsics.areEqual(str, "rtl")) {
                    this.toolbar.setLayoutDirection(1);
                } else if (Intrinsics.areEqual(this.mDirection, "ltr")) {
                    this.toolbar.setLayoutDirection(0);
                }
            }
            Screen screen = getScreen();
            if (screen != null) {
                if (getContext() instanceof ReactContext) {
                    Context context = getContext();
                    if (context == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                    }
                    reactContextTryGetContext = (ReactContext) context;
                } else {
                    ScreenFragment fragment = screen.getFragment();
                    reactContextTryGetContext = fragment == null ? null : fragment.tryGetContext();
                }
                ScreenWindowTraits.INSTANCE.trySetWindowTraits$react_native_screens_release(screen, appCompatActivity, reactContextTryGetContext);
            }
            if (this.mIsHidden) {
                if (this.toolbar.getParent() == null || (screenFragment2 = getScreenFragment()) == null) {
                    return;
                }
                screenFragment2.removeToolbar();
                return;
            }
            if (this.toolbar.getParent() == null && (screenFragment = getScreenFragment()) != null) {
                screenFragment.setToolbar(this.toolbar);
            }
            if (this.mIsTopInsetEnabled) {
                Integer num = this.headerTopInset;
                getToolbar().setPadding(0, num == null ? 0 : num.intValue(), 0, 0);
            } else if (this.toolbar.getPaddingTop() > 0) {
                this.toolbar.setPadding(0, 0, 0, 0);
            }
            appCompatActivity.setSupportActionBar(this.toolbar);
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            this.toolbar.setContentInsetStartWithNavigation(this.mDefaultStartInsetWithNavigation);
            CustomToolbar customToolbar = this.toolbar;
            int i = this.mDefaultStartInset;
            customToolbar.setContentInsetsRelative(i, i);
            ScreenStackFragment screenFragment4 = getScreenFragment();
            supportActionBar.setDisplayHomeAsUpEnabled((screenFragment4 != null && screenFragment4.canNavigateBack()) && !this.mIsBackButtonHidden);
            this.toolbar.setNavigationOnClickListener(this.mBackClickListener);
            ScreenStackFragment screenFragment5 = getScreenFragment();
            if (screenFragment5 != null) {
                screenFragment5.setToolbarShadowHidden(this.mIsShadowHidden);
            }
            ScreenStackFragment screenFragment6 = getScreenFragment();
            if (screenFragment6 != null) {
                screenFragment6.setToolbarTranslucent(this.mIsTranslucent);
            }
            supportActionBar.setTitle(this.mTitle);
            if (TextUtils.isEmpty(this.mTitle)) {
                this.toolbar.setContentInsetStartWithNavigation(0);
            }
            TextView titleTextView = getTitleTextView();
            int i2 = this.mTitleColor;
            if (i2 != 0) {
                this.toolbar.setTitleTextColor(i2);
            }
            if (titleTextView != null) {
                String str2 = this.mTitleFontFamily;
                if (str2 != null || this.mTitleFontWeight > 0) {
                    Typeface typefaceApplyStyles = ReactTypefaceUtils.applyStyles(null, 0, this.mTitleFontWeight, str2, getContext().getAssets());
                    Intrinsics.checkNotNullExpressionValue(typefaceApplyStyles, "applyStyles(\n           ….assets\n                )");
                    titleTextView.setTypeface(typefaceApplyStyles);
                }
                float f = this.mTitleFontSize;
                if (f > 0.0f) {
                    titleTextView.setTextSize(f);
                }
            }
            Integer num2 = this.mBackgroundColor;
            if (num2 != null) {
                getToolbar().setBackgroundColor(num2.intValue());
            }
            if (this.mTintColor != 0 && (navigationIcon = this.toolbar.getNavigationIcon()) != null) {
                navigationIcon.setColorFilter(this.mTintColor, PorterDuff.Mode.SRC_ATOP);
            }
            int childCount = this.toolbar.getChildCount() - 1;
            if (childCount >= 0) {
                while (true) {
                    int i3 = childCount - 1;
                    if (this.toolbar.getChildAt(childCount) instanceof ScreenStackHeaderSubview) {
                        this.toolbar.removeViewAt(childCount);
                    }
                    if (i3 < 0) {
                        break;
                    } else {
                        childCount = i3;
                    }
                }
            }
            int size = this.mConfigSubviews.size();
            for (int i4 = 0; i4 < size; i4++) {
                ScreenStackHeaderSubview screenStackHeaderSubview = this.mConfigSubviews.get(i4);
                Intrinsics.checkNotNullExpressionValue(screenStackHeaderSubview, "mConfigSubviews[i]");
                ScreenStackHeaderSubview screenStackHeaderSubview2 = screenStackHeaderSubview;
                ScreenStackHeaderSubview.Type type = screenStackHeaderSubview2.getType();
                if (type == ScreenStackHeaderSubview.Type.BACK) {
                    View childAt = screenStackHeaderSubview2.getChildAt(0);
                    ImageView imageView = childAt instanceof ImageView ? (ImageView) childAt : null;
                    if (imageView == null) {
                        throw new JSApplicationIllegalArgumentException("Back button header config view should have Image as first child");
                    }
                    supportActionBar.setHomeAsUpIndicator(imageView.getDrawable());
                } else {
                    Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(-2, -1);
                    int i5 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
                    if (i5 == 1) {
                        if (!this.mBackButtonInCustomView) {
                            this.toolbar.setNavigationIcon((Drawable) null);
                        }
                        this.toolbar.setTitle((CharSequence) null);
                        layoutParams.gravity = 8388611;
                    } else if (i5 == 2) {
                        layoutParams.gravity = GravityCompat.END;
                    } else if (i5 == 3) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).width = -1;
                        layoutParams.gravity = 1;
                        this.toolbar.setTitle((CharSequence) null);
                    }
                    screenStackHeaderSubview2.setLayoutParams(layoutParams);
                    this.toolbar.addView(screenStackHeaderSubview2);
                }
            }
        }
    }

    private final void maybeUpdate() {
        if (getParent() == null || this.mDestroyed) {
            return;
        }
        onUpdate();
    }

    public final ScreenStackHeaderSubview getConfigSubview(int index) {
        ScreenStackHeaderSubview screenStackHeaderSubview = this.mConfigSubviews.get(index);
        Intrinsics.checkNotNullExpressionValue(screenStackHeaderSubview, "mConfigSubviews[index]");
        return screenStackHeaderSubview;
    }

    public final int getConfigSubviewsCount() {
        return this.mConfigSubviews.size();
    }

    public final void removeConfigSubview(int index) {
        this.mConfigSubviews.remove(index);
        maybeUpdate();
    }

    public final void removeAllConfigSubviews() {
        this.mConfigSubviews.clear();
        maybeUpdate();
    }

    public final void addConfigSubview(ScreenStackHeaderSubview child, int index) {
        Intrinsics.checkNotNullParameter(child, "child");
        this.mConfigSubviews.add(index, child);
        maybeUpdate();
    }

    private final TextView getTitleTextView() {
        int childCount = this.toolbar.getChildCount();
        int i = 0;
        while (i < childCount) {
            int i2 = i + 1;
            View childAt = this.toolbar.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (Intrinsics.areEqual(textView.getText(), this.toolbar.getTitle())) {
                    return textView;
                }
            }
            i = i2;
        }
        return null;
    }

    public final void setTitle(String title) {
        this.mTitle = title;
    }

    public final void setTitleFontFamily(String titleFontFamily) {
        this.mTitleFontFamily = titleFontFamily;
    }

    public final void setTitleFontWeight(String fontWeightString) {
        this.mTitleFontWeight = ReactTypefaceUtils.parseFontWeight(fontWeightString);
    }

    public final void setTitleFontSize(float titleFontSize) {
        this.mTitleFontSize = titleFontSize;
    }

    public final void setTitleColor(int color) {
        this.mTitleColor = color;
    }

    public final void setTintColor(int color) {
        this.mTintColor = color;
    }

    public final void setTopInsetEnabled(boolean topInsetEnabled) {
        this.mIsTopInsetEnabled = topInsetEnabled;
    }

    public final void setBackgroundColor(Integer color) {
        this.mBackgroundColor = color;
    }

    public final void setHideShadow(boolean hideShadow) {
        this.mIsShadowHidden = hideShadow;
    }

    public final void setHideBackButton(boolean hideBackButton) {
        this.mIsBackButtonHidden = hideBackButton;
    }

    public final void setHidden(boolean hidden) {
        this.mIsHidden = hidden;
    }

    public final void setTranslucent(boolean translucent) {
        this.mIsTranslucent = translucent;
    }

    public final void setBackButtonInCustomView(boolean backButtonInCustomView) {
        this.mBackButtonInCustomView = backButtonInCustomView;
    }

    public final void setDirection(String direction) {
        this.mDirection = direction;
    }
}
