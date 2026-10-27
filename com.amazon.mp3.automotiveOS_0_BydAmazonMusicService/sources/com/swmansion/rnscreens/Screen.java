package com.swmansion.rnscreens;

import UpsellInterface.v1_0.PlaybackMode;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Screen.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\n\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001\u0085\u0001B\u0012\u0012\b\u0010~\u001a\u0004\u0018\u00010}¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0016\u0010\u000e\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0014J\u0016\u0010\u000f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0014J0\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0002H\u0014J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\tJ\u001a\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016J\u000e\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001cJ\u0010\u0010!\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u001fJ\u000e\u0010#\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0002R$\u0010%\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R(\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010+8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R(\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u00101\u001a\u0004\u0018\u00010\u001c8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001d\u00102\u001a\u0004\b3\u00104R\u0016\u00105\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R\"\u00108\u001a\u0002078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010?\u001a\u00020>8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\"\u0010F\u001a\u00020E8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\"\u0010L\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u00106\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR(\u0010 \u001a\u0004\u0018\u00010\u00022\b\u00101\u001a\u0004\u0018\u00010\u00028\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b \u0010P\u001a\u0004\bQ\u0010RR\u0018\u0010S\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010U\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0018\u0010W\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010VR\u0018\u0010X\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010PR\u0018\u0010Y\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010PR\u0018\u0010Z\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010VR$\u0010[\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b[\u0010V\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u00106R\u0013\u0010c\u001a\u0004\u0018\u00010`8F¢\u0006\u0006\u001a\u0004\ba\u0010bR(\u0010d\u001a\u0004\u0018\u00010\u001f2\b\u0010d\u001a\u0004\u0018\u00010\u001f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR(\u0010j\u001a\u0004\u0018\u00010\t2\b\u0010i\u001a\u0004\u0018\u00010\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bj\u0010\\\"\u0004\bk\u0010^R(\u0010m\u001a\u0004\u0018\u00010\t2\b\u0010l\u001a\u0004\u0018\u00010\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bm\u0010\\\"\u0004\bn\u0010^R(\u0010o\u001a\u0004\u0018\u00010\u00022\b\u0010o\u001a\u0004\u0018\u00010\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bp\u0010R\"\u0004\bq\u0010rR(\u0010s\u001a\u0004\u0018\u00010\u00022\b\u0010s\u001a\u0004\u0018\u00010\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bt\u0010R\"\u0004\bu\u0010rR(\u0010w\u001a\u0004\u0018\u00010\t2\b\u0010v\u001a\u0004\u0018\u00010\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bw\u0010\\\"\u0004\bx\u0010^R$\u0010|\u001a\u00020\t2\u0006\u0010y\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bz\u0010M\"\u0004\b{\u0010O¨\u0006\u0086\u0001"}, d2 = {"Lcom/swmansion/rnscreens/Screen;", "Lcom/swmansion/rnscreens/FabricEnabledViewGroup;", "", "width", "height", "", "updateScreenSizePaper", "Landroid/view/ViewGroup;", "viewGroup", "", "hasWebView", "Landroid/util/SparseArray;", "Landroid/os/Parcelable;", "container", "dispatchSaveInstanceState", "dispatchRestoreInstanceState", "changed", "l", "t", "r", "b", "onLayout", "transitioning", "setTransitioning", "layerType", "Landroid/graphics/Paint;", "paint", "setLayerType", "Lcom/swmansion/rnscreens/Screen$ActivityState;", "activityState", "setActivityState", "", "screenOrientation", "setScreenOrientation", "mode", "changeAccessibilityMode", "Lcom/swmansion/rnscreens/ScreenFragment;", "fragment", "Lcom/swmansion/rnscreens/ScreenFragment;", "getFragment", "()Lcom/swmansion/rnscreens/ScreenFragment;", "setFragment", "(Lcom/swmansion/rnscreens/ScreenFragment;)V", "Lcom/swmansion/rnscreens/ScreenContainer;", "Lcom/swmansion/rnscreens/ScreenContainer;", "getContainer", "()Lcom/swmansion/rnscreens/ScreenContainer;", "setContainer", "(Lcom/swmansion/rnscreens/ScreenContainer;)V", "<set-?>", "Lcom/swmansion/rnscreens/Screen$ActivityState;", "getActivityState", "()Lcom/swmansion/rnscreens/Screen$ActivityState;", "mTransitioning", "Z", "Lcom/swmansion/rnscreens/Screen$StackPresentation;", "stackPresentation", "Lcom/swmansion/rnscreens/Screen$StackPresentation;", "getStackPresentation", "()Lcom/swmansion/rnscreens/Screen$StackPresentation;", "setStackPresentation", "(Lcom/swmansion/rnscreens/Screen$StackPresentation;)V", "Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "replaceAnimation", "Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "getReplaceAnimation", "()Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "setReplaceAnimation", "(Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;)V", "Lcom/swmansion/rnscreens/Screen$StackAnimation;", "stackAnimation", "Lcom/swmansion/rnscreens/Screen$StackAnimation;", "getStackAnimation", "()Lcom/swmansion/rnscreens/Screen$StackAnimation;", "setStackAnimation", "(Lcom/swmansion/rnscreens/Screen$StackAnimation;)V", "isGestureEnabled", "()Z", "setGestureEnabled", "(Z)V", "Ljava/lang/Integer;", "getScreenOrientation", "()Ljava/lang/Integer;", "mStatusBarStyle", "Ljava/lang/String;", "mStatusBarHidden", "Ljava/lang/Boolean;", "mStatusBarTranslucent", "mStatusBarColor", "mNavigationBarColor", "mNavigationBarHidden", "isStatusBarAnimated", "()Ljava/lang/Boolean;", "setStatusBarAnimated", "(Ljava/lang/Boolean;)V", "mNativeBackButtonDismissalEnabled", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "getHeaderConfig", "()Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "headerConfig", "statusBarStyle", "getStatusBarStyle", "()Ljava/lang/String;", "setStatusBarStyle", "(Ljava/lang/String;)V", "statusBarHidden", "isStatusBarHidden", "setStatusBarHidden", "statusBarTranslucent", "isStatusBarTranslucent", "setStatusBarTranslucent", "statusBarColor", "getStatusBarColor", "setStatusBarColor", "(Ljava/lang/Integer;)V", "navigationBarColor", "getNavigationBarColor", "setNavigationBarColor", "navigationBarHidden", "isNavigationBarHidden", "setNavigationBarHidden", "enableNativeBackButtonDismissal", "getNativeBackButtonDismissalEnabled", "setNativeBackButtonDismissalEnabled", "nativeBackButtonDismissalEnabled", "Lcom/facebook/react/bridge/ReactContext;", "context", "<init>", "(Lcom/facebook/react/bridge/ReactContext;)V", "ActivityState", "ReplaceAnimation", "StackAnimation", "StackPresentation", "WindowTraits", "react-native-screens_release"}, k = 1, mv = {1, 6, 0})
@SuppressLint({"ViewConstructor"})
public final class Screen extends FabricEnabledViewGroup {
    private ActivityState activityState;
    private ScreenContainer<?> container;
    private ScreenFragment fragment;
    private boolean isGestureEnabled;
    private Boolean isStatusBarAnimated;
    private boolean mNativeBackButtonDismissalEnabled;
    private Integer mNavigationBarColor;
    private Boolean mNavigationBarHidden;
    private Integer mStatusBarColor;
    private Boolean mStatusBarHidden;
    private String mStatusBarStyle;
    private Boolean mStatusBarTranslucent;
    private boolean mTransitioning;
    private ReplaceAnimation replaceAnimation;
    private Integer screenOrientation;
    private StackAnimation stackAnimation;
    private StackPresentation stackPresentation;

    /* JADX INFO: compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/Screen$ActivityState;", "", "(Ljava/lang/String;I)V", "INACTIVE", "TRANSITIONING_OR_BELOW_TOP", "ON_TOP", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ActivityState {
        INACTIVE,
        TRANSITIONING_OR_BELOW_TOP,
        ON_TOP
    }

    /* JADX INFO: compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "", "(Ljava/lang/String;I)V", "PUSH", "POP", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ReplaceAnimation {
        PUSH,
        POP
    }

    /* JADX INFO: compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/swmansion/rnscreens/Screen$StackAnimation;", "", "(Ljava/lang/String;I)V", "DEFAULT", PlaybackMode.NONE, "FADE", "SLIDE_FROM_BOTTOM", "SLIDE_FROM_RIGHT", "SLIDE_FROM_LEFT", "FADE_FROM_BOTTOM", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum StackAnimation {
        DEFAULT,
        NONE,
        FADE,
        SLIDE_FROM_BOTTOM,
        SLIDE_FROM_RIGHT,
        SLIDE_FROM_LEFT,
        FADE_FROM_BOTTOM
    }

    /* JADX INFO: compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/Screen$StackPresentation;", "", "(Ljava/lang/String;I)V", "PUSH", "MODAL", "TRANSPARENT_MODAL", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum StackPresentation {
        PUSH,
        MODAL,
        TRANSPARENT_MODAL
    }

    /* JADX INFO: compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/Screen$WindowTraits;", "", "(Ljava/lang/String;I)V", "ORIENTATION", "COLOR", Contexts.Notification.style, "TRANSLUCENT", "HIDDEN", "ANIMATED", "NAVIGATION_BAR_COLOR", "NAVIGATION_BAR_HIDDEN", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum WindowTraits {
        ORIENTATION,
        COLOR,
        STYLE,
        TRANSLUCENT,
        HIDDEN,
        ANIMATED,
        NAVIGATION_BAR_COLOR,
        NAVIGATION_BAR_HIDDEN
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // android.view.View
    public void setLayerType(int layerType, Paint paint) {
    }

    public Screen(ReactContext reactContext) {
        super(reactContext);
        this.stackPresentation = StackPresentation.PUSH;
        this.replaceAnimation = ReplaceAnimation.POP;
        this.stackAnimation = StackAnimation.DEFAULT;
        this.isGestureEnabled = true;
        this.mNativeBackButtonDismissalEnabled = true;
        setLayoutParams(new WindowManager.LayoutParams(2));
    }

    public final ScreenFragment getFragment() {
        return this.fragment;
    }

    public final void setFragment(ScreenFragment screenFragment) {
        this.fragment = screenFragment;
    }

    public final ScreenContainer<?> getContainer() {
        return this.container;
    }

    public final void setContainer(ScreenContainer<?> screenContainer) {
        this.container = screenContainer;
    }

    public final ActivityState getActivityState() {
        return this.activityState;
    }

    public final StackPresentation getStackPresentation() {
        return this.stackPresentation;
    }

    public final void setStackPresentation(StackPresentation stackPresentation) {
        Intrinsics.checkNotNullParameter(stackPresentation, "<set-?>");
        this.stackPresentation = stackPresentation;
    }

    public final ReplaceAnimation getReplaceAnimation() {
        return this.replaceAnimation;
    }

    public final void setReplaceAnimation(ReplaceAnimation replaceAnimation) {
        Intrinsics.checkNotNullParameter(replaceAnimation, "<set-?>");
        this.replaceAnimation = replaceAnimation;
    }

    public final StackAnimation getStackAnimation() {
        return this.stackAnimation;
    }

    public final void setStackAnimation(StackAnimation stackAnimation) {
        Intrinsics.checkNotNullParameter(stackAnimation, "<set-?>");
        this.stackAnimation = stackAnimation;
    }

    public final void setGestureEnabled(boolean z) {
        this.isGestureEnabled = z;
    }

    public final Integer getScreenOrientation() {
        return this.screenOrientation;
    }

    /* JADX INFO: renamed from: isStatusBarAnimated, reason: from getter */
    public final Boolean getIsStatusBarAnimated() {
        return this.isStatusBarAnimated;
    }

    public final void setStatusBarAnimated(Boolean bool) {
        this.isStatusBarAnimated = bool;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            updateScreenSizePaper(r - l, b - t);
        }
    }

    private final void updateScreenSizePaper(int width, int height) {
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        }
        ReactContext reactContext = (ReactContext) context;
        reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(this, width, height) { // from class: com.swmansion.rnscreens.Screen.updateScreenSizePaper.1
            final /* synthetic */ int $height;
            final /* synthetic */ int $width;
            final /* synthetic */ Screen this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.$reactContext);
                this.this$0 = this;
                this.$width = width;
                this.$height = height;
            }

            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIManagerModule uIManagerModule = (UIManagerModule) this.$reactContext.getNativeModule(UIManagerModule.class);
                if (uIManagerModule == null) {
                    return;
                }
                uIManagerModule.updateNodeSize(this.this$0.getId(), this.$width, this.$height);
            }
        });
    }

    public final ScreenStackHeaderConfig getHeaderConfig() {
        View childAt = getChildAt(0);
        if (childAt instanceof ScreenStackHeaderConfig) {
            return (ScreenStackHeaderConfig) childAt;
        }
        return null;
    }

    public final void setTransitioning(boolean transitioning) {
        if (this.mTransitioning == transitioning) {
            return;
        }
        this.mTransitioning = transitioning;
        boolean zHasWebView = hasWebView(this);
        if (!zHasWebView || getLayerType() == 2) {
            super.setLayerType((!transitioning || zHasWebView) ? 0 : 2, null);
        }
    }

    private final boolean hasWebView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        int i = 0;
        while (i < childCount) {
            int i2 = i + 1;
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof WebView) {
                return true;
            }
            if ((childAt instanceof ViewGroup) && hasWebView((ViewGroup) childAt)) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    public final void setActivityState(ActivityState activityState) {
        Intrinsics.checkNotNullParameter(activityState, "activityState");
        if (activityState == this.activityState) {
            return;
        }
        this.activityState = activityState;
        ScreenContainer<?> screenContainer = this.container;
        if (screenContainer == null) {
            return;
        }
        screenContainer.notifyChildUpdate();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setScreenOrientation(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L6
            r3 = 0
            r2.screenOrientation = r3
            return
        L6:
            com.swmansion.rnscreens.ScreenWindowTraits r0 = com.swmansion.rnscreens.ScreenWindowTraits.INSTANCE
            r0.applyDidSetOrientation$react_native_screens_release()
            int r1 = r3.hashCode()
            switch(r1) {
                case -1894896954: goto L72;
                case 96673: goto L62;
                case 729267099: goto L53;
                case 1430647483: goto L44;
                case 1651658175: goto L35;
                case 1730732811: goto L25;
                case 2118770584: goto L14;
                default: goto L12;
            }
        L12:
            goto L82
        L14:
            java.lang.String r1 = "landscape_right"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L1e
            goto L82
        L1e:
            r3 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L87
        L25:
            java.lang.String r1 = "landscape_left"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L2e
            goto L82
        L2e:
            r3 = 8
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L87
        L35:
            java.lang.String r1 = "portrait_up"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L3e
            goto L82
        L3e:
            r3 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L87
        L44:
            java.lang.String r1 = "landscape"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L4d
            goto L82
        L4d:
            r3 = 6
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L87
        L53:
            java.lang.String r1 = "portrait"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L5c
            goto L82
        L5c:
            r3 = 7
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L87
        L62:
            java.lang.String r1 = "all"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L6b
            goto L82
        L6b:
            r3 = 10
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L87
        L72:
            java.lang.String r1 = "portrait_down"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L7b
            goto L82
        L7b:
            r3 = 9
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L87
        L82:
            r3 = -1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L87:
            r2.screenOrientation = r3
            com.swmansion.rnscreens.ScreenFragment r3 = r2.fragment
            if (r3 != 0) goto L8e
            goto L95
        L8e:
            android.app.Activity r3 = r3.tryGetActivity()
            r0.setOrientation$react_native_screens_release(r2, r3)
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.Screen.setScreenOrientation(java.lang.String):void");
    }

    public final void changeAccessibilityMode(int mode) {
        setImportantForAccessibility(mode);
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        CustomToolbar toolbar = headerConfig == null ? null : headerConfig.getToolbar();
        if (toolbar == null) {
            return;
        }
        toolbar.setImportantForAccessibility(mode);
    }

    /* JADX INFO: renamed from: getStatusBarStyle, reason: from getter */
    public final String getMStatusBarStyle() {
        return this.mStatusBarStyle;
    }

    public final void setStatusBarStyle(String str) {
        if (str != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.mStatusBarStyle = str;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment == null) {
            return;
        }
        ScreenWindowTraits.INSTANCE.setStyle$react_native_screens_release(this, screenFragment.tryGetActivity(), screenFragment.tryGetContext());
    }

    /* JADX INFO: renamed from: isStatusBarHidden, reason: from getter */
    public final Boolean getMStatusBarHidden() {
        return this.mStatusBarHidden;
    }

    public final void setStatusBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.mStatusBarHidden = bool;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment == null) {
            return;
        }
        ScreenWindowTraits.INSTANCE.setHidden$react_native_screens_release(this, screenFragment.tryGetActivity());
    }

    /* JADX INFO: renamed from: isStatusBarTranslucent, reason: from getter */
    public final Boolean getMStatusBarTranslucent() {
        return this.mStatusBarTranslucent;
    }

    public final void setStatusBarTranslucent(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.mStatusBarTranslucent = bool;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment == null) {
            return;
        }
        ScreenWindowTraits.INSTANCE.setTranslucent$react_native_screens_release(this, screenFragment.tryGetActivity(), screenFragment.tryGetContext());
    }

    /* JADX INFO: renamed from: getStatusBarColor, reason: from getter */
    public final Integer getMStatusBarColor() {
        return this.mStatusBarColor;
    }

    public final void setStatusBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.mStatusBarColor = num;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment == null) {
            return;
        }
        ScreenWindowTraits.INSTANCE.setColor$react_native_screens_release(this, screenFragment.tryGetActivity(), screenFragment.tryGetContext());
    }

    /* JADX INFO: renamed from: getNavigationBarColor, reason: from getter */
    public final Integer getMNavigationBarColor() {
        return this.mNavigationBarColor;
    }

    public final void setNavigationBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.mNavigationBarColor = num;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment == null) {
            return;
        }
        ScreenWindowTraits.INSTANCE.setNavigationBarColor$react_native_screens_release(this, screenFragment.tryGetActivity());
    }

    /* JADX INFO: renamed from: isNavigationBarHidden, reason: from getter */
    public final Boolean getMNavigationBarHidden() {
        return this.mNavigationBarHidden;
    }

    public final void setNavigationBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.mNavigationBarHidden = bool;
        ScreenFragment screenFragment = this.fragment;
        if (screenFragment == null) {
            return;
        }
        ScreenWindowTraits.INSTANCE.setNavigationBarHidden$react_native_screens_release(this, screenFragment.tryGetActivity());
    }

    /* JADX INFO: renamed from: getNativeBackButtonDismissalEnabled, reason: from getter */
    public final boolean getMNativeBackButtonDismissalEnabled() {
        return this.mNativeBackButtonDismissalEnabled;
    }

    public final void setNativeBackButtonDismissalEnabled(boolean z) {
        this.mNativeBackButtonDismissalEnabled = z;
    }
}
