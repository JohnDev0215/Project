package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.events.StackFinishTransitioningEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__ReversedViewsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: ScreenStack.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 A2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002ABB\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u001cH\u0014J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0002J\u0010\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020(H\u0014J\b\u0010)\u001a\u00020$H\u0002J\b\u0010*\u001a\u00020$H\u0002J \u0010+\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020(2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0014J\u0010\u00100\u001a\u00020$2\u0006\u00101\u001a\u00020-H\u0016J\u0012\u00102\u001a\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020$H\u0014J\f\u00105\u001a\u00060\bR\u00020\u0000H\u0002J\b\u00106\u001a\u00020$H\u0016J\u0006\u00107\u001a\u00020$J\u0014\u00108\u001a\u00020$2\n\u00109\u001a\u00060\bR\u00020\u0000H\u0002J\b\u0010:\u001a\u00020$H\u0016J\u0010\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020\u0019H\u0016J\u0010\u0010=\u001a\u00020$2\u0006\u00101\u001a\u00020-H\u0016J\u0010\u0010>\u001a\u00020$2\u0006\u00101\u001a\u00020-H\u0016J\u0012\u0010?\u001a\u00020$2\b\u0010@\u001a\u0004\u0018\u00010\u0002H\u0002R\u0018\u0010\u0006\u001a\f\u0012\b\u0012\u00060\bR\u00020\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\f\u0012\b\u0012\u00060\bR\u00020\u00000\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0015j\b\u0012\u0004\u0012\u00020\u0002`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001e¨\u0006C"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStack;", "Lcom/swmansion/rnscreens/ScreenContainer;", "Lcom/swmansion/rnscreens/ScreenStackFragment;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "drawingOpPool", "", "Lcom/swmansion/rnscreens/ScreenStack$DrawingOp;", "drawingOps", "goingForward", "", "getGoingForward", "()Z", "setGoingForward", "(Z)V", "isDetachingCurrentScreen", "mDismissed", "", "mRemovalTransitionStarted", "mStack", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mTopScreen", "previousChildrenCount", "", "reverseLastTwoChildren", "rootScreen", "Lcom/swmansion/rnscreens/Screen;", "getRootScreen", "()Lcom/swmansion/rnscreens/Screen;", "topScreen", "getTopScreen", "adapt", "screen", "dismiss", "", "screenFragment", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "dispatchOnFinishTransitioning", "drawAndRelease", "drawChild", "child", "Landroid/view/View;", "drawingTime", "", "endViewTransition", "view", "hasScreen", "Lcom/swmansion/rnscreens/ScreenFragment;", "notifyContainerUpdate", "obtainDrawingOp", "onUpdate", "onViewAppearTransitionEnd", "performDraw", "op", "removeAllScreens", "removeScreenAt", "index", "removeView", "startViewTransition", "turnOffA11yUnderTransparentScreen", "visibleBottom", "Companion", "DrawingOp", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ScreenStack extends ScreenContainer<ScreenStackFragment> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<DrawingOp> drawingOpPool;
    private List<DrawingOp> drawingOps;
    private boolean goingForward;
    private boolean isDetachingCurrentScreen;
    private final Set<ScreenStackFragment> mDismissed;
    private boolean mRemovalTransitionStarted;
    private final ArrayList<ScreenStackFragment> mStack;
    private ScreenStackFragment mTopScreen;
    private int previousChildrenCount;
    private boolean reverseLastTwoChildren;

    /* JADX INFO: compiled from: ScreenStack.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Screen.StackAnimation.values().length];
            iArr[Screen.StackAnimation.DEFAULT.ordinal()] = 1;
            iArr[Screen.StackAnimation.NONE.ordinal()] = 2;
            iArr[Screen.StackAnimation.FADE.ordinal()] = 3;
            iArr[Screen.StackAnimation.SLIDE_FROM_RIGHT.ordinal()] = 4;
            iArr[Screen.StackAnimation.SLIDE_FROM_LEFT.ordinal()] = 5;
            iArr[Screen.StackAnimation.SLIDE_FROM_BOTTOM.ordinal()] = 6;
            iArr[Screen.StackAnimation.FADE_FROM_BOTTOM.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ScreenStack(Context context) {
        super(context);
        this.mStack = new ArrayList<>();
        this.mDismissed = new HashSet();
        this.drawingOpPool = new ArrayList();
        this.drawingOps = new ArrayList();
    }

    public final boolean getGoingForward() {
        return this.goingForward;
    }

    public final void setGoingForward(boolean z) {
        this.goingForward = z;
    }

    public final void dismiss(ScreenStackFragment screenFragment) {
        Intrinsics.checkNotNullParameter(screenFragment, "screenFragment");
        this.mDismissed.add(screenFragment);
        performUpdatesNow();
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public Screen getTopScreen() {
        ScreenStackFragment screenStackFragment = this.mTopScreen;
        if (screenStackFragment == null) {
            return null;
        }
        return screenStackFragment.getScreen();
    }

    public final Screen getRootScreen() {
        int screenCount = getScreenCount();
        int i = 0;
        while (i < screenCount) {
            int i2 = i + 1;
            Screen screenAt = getScreenAt(i);
            if (!CollectionsKt___CollectionsKt.contains(this.mDismissed, screenAt.getFragment())) {
                return screenAt;
            }
            i = i2;
        }
        throw new IllegalStateException("Stack has no root screen set");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.rnscreens.ScreenContainer
    public ScreenStackFragment adapt(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        return new ScreenStackFragment(screen);
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.startViewTransition(view);
        this.mRemovalTransitionStarted = true;
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.endViewTransition(view);
        if (this.mRemovalTransitionStarted) {
            this.mRemovalTransitionStarted = false;
            dispatchOnFinishTransitioning();
        }
    }

    public final void onViewAppearTransitionEnd() {
        if (this.mRemovalTransitionStarted) {
            return;
        }
        dispatchOnFinishTransitioning();
    }

    private final void dispatchOnFinishTransitioning() {
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        }
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        if (eventDispatcherForReactTag == null) {
            return;
        }
        eventDispatcherForReactTag.dispatchEvent(new StackFinishTransitioningEvent(getId()));
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public void removeScreenAt(int index) {
        this.mDismissed.remove(getScreenAt(index).getFragment());
        super.removeScreenAt(index);
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public void removeAllScreens() {
        this.mDismissed.clear();
        super.removeAllScreens();
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public boolean hasScreen(ScreenFragment screenFragment) {
        return super.hasScreen(screenFragment) && !CollectionsKt___CollectionsKt.contains(this.mDismissed, screenFragment);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0035 A[PHI: r3 r4
  0x0035: PHI (r3v3 com.swmansion.rnscreens.ScreenStackFragment) = (r3v2 com.swmansion.rnscreens.ScreenStackFragment), (r3v4 com.swmansion.rnscreens.ScreenStackFragment) binds: [B:5:0x0025, B:10:0x0032] A[DONT_GENERATE, DONT_INLINE]
  0x0035: PHI (r4v3 com.swmansion.rnscreens.ScreenStackFragment) = (r4v2 com.swmansion.rnscreens.ScreenStackFragment), (r4v5 com.swmansion.rnscreens.ScreenStackFragment) binds: [B:5:0x0025, B:10:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.swmansion.rnscreens.ScreenContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onUpdate() {
        /*
            Method dump skipped, instruction units count: 530
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenStack.onUpdate():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onUpdate$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2764onUpdate$lambda2$lambda1(ScreenStackFragment screenStackFragment) {
        Screen screen;
        if (screenStackFragment == null || (screen = screenStackFragment.getScreen()) == null) {
            return;
        }
        screen.bringToFront();
    }

    private final void turnOffA11yUnderTransparentScreen(ScreenStackFragment visibleBottom) {
        ScreenStackFragment screenStackFragment;
        if (this.mScreenFragments.size() > 1 && visibleBottom != null && (screenStackFragment = this.mTopScreen) != null && INSTANCE.isTransparent(screenStackFragment)) {
            ArrayList<T> arrayList = this.mScreenFragments;
            for (ScreenStackFragment screenStackFragment2 : CollectionsKt__ReversedViewsKt.asReversed(CollectionsKt___CollectionsKt.slice((List) arrayList, RangesKt___RangesKt.until(0, arrayList.size() - 1)))) {
                screenStackFragment2.getScreen().changeAccessibilityMode(4);
                if (Intrinsics.areEqual(screenStackFragment2, visibleBottom)) {
                    break;
                }
            }
        }
        Screen topScreen = getTopScreen();
        if (topScreen == null) {
            return;
        }
        topScreen.changeAccessibilityMode(0);
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    protected void notifyContainerUpdate() {
        Iterator<T> it = this.mStack.iterator();
        while (it.hasNext()) {
            ((ScreenStackFragment) it.next()).onContainerUpdate();
        }
    }

    @Override // com.swmansion.rnscreens.ScreenContainer, android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.isDetachingCurrentScreen) {
            this.isDetachingCurrentScreen = false;
            this.reverseLastTwoChildren = true;
        }
        super.removeView(view);
    }

    private final void drawAndRelease() {
        List<DrawingOp> list = this.drawingOps;
        this.drawingOps = new ArrayList();
        for (DrawingOp drawingOp : list) {
            drawingOp.draw();
            this.drawingOpPool.add(drawingOp);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.dispatchDraw(canvas);
        if (this.drawingOps.size() < this.previousChildrenCount) {
            this.reverseLastTwoChildren = false;
        }
        this.previousChildrenCount = this.drawingOps.size();
        if (this.reverseLastTwoChildren && this.drawingOps.size() >= 2) {
            Collections.swap(this.drawingOps, r4.size() - 1, this.drawingOps.size() - 2);
        }
        drawAndRelease();
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(child, "child");
        this.drawingOps.add(obtainDrawingOp().set(canvas, child, drawingTime));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void performDraw(DrawingOp op) {
        super.drawChild(op.getCanvas(), op.getChild(), op.getDrawingTime());
    }

    private final DrawingOp obtainDrawingOp() {
        if (this.drawingOpPool.isEmpty()) {
            return new DrawingOp(this);
        }
        return this.drawingOpPool.remove(r0.size() - 1);
    }

    /* JADX INFO: compiled from: ScreenStack.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0013\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ)\u0010\t\u001a\u00060\u0000R\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\u0002J\u0006\u0010\u000b\u001a\u00020\nR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStack$DrawingOp;", "", "Landroid/graphics/Canvas;", "canvas", "Landroid/view/View;", "child", "", "drawingTime", "Lcom/swmansion/rnscreens/ScreenStack;", "set", "", "draw", "Landroid/graphics/Canvas;", "getCanvas", "()Landroid/graphics/Canvas;", "setCanvas", "(Landroid/graphics/Canvas;)V", "Landroid/view/View;", "getChild", "()Landroid/view/View;", "setChild", "(Landroid/view/View;)V", "J", "getDrawingTime", "()J", "setDrawingTime", "(J)V", "<init>", "(Lcom/swmansion/rnscreens/ScreenStack;)V", "react-native-screens_release"}, k = 1, mv = {1, 6, 0})
    private final class DrawingOp {
        private Canvas canvas;
        private View child;
        private long drawingTime;
        final /* synthetic */ ScreenStack this$0;

        public DrawingOp(ScreenStack this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        public final Canvas getCanvas() {
            return this.canvas;
        }

        public final View getChild() {
            return this.child;
        }

        public final long getDrawingTime() {
            return this.drawingTime;
        }

        public final DrawingOp set(Canvas canvas, View child, long drawingTime) {
            this.canvas = canvas;
            this.child = child;
            this.drawingTime = drawingTime;
            return this;
        }

        public final void draw() {
            this.this$0.performDraw(this);
            this.canvas = null;
            this.child = null;
            this.drawingTime = 0L;
        }
    }

    /* JADX INFO: compiled from: ScreenStack.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\b"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStack$Companion;", "", "()V", "isTransparent", "", "fragment", "Lcom/swmansion/rnscreens/ScreenStackFragment;", "needsDrawReordering", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isTransparent(ScreenStackFragment fragment) {
            return fragment.getScreen().getStackPresentation() == Screen.StackPresentation.TRANSPARENT_MODAL;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean needsDrawReordering(ScreenStackFragment fragment) {
            return fragment.getScreen().getStackAnimation() == Screen.StackAnimation.SLIDE_FROM_BOTTOM || fragment.getScreen().getStackAnimation() == Screen.StackAnimation.FADE_FROM_BOTTOM;
        }
    }
}
