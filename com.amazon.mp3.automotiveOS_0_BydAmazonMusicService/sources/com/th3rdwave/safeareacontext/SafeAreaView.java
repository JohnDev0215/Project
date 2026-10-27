package com.th3rdwave.safeareacontext;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.EnumSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SafeAreaView.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0017H\u0014J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0016\u0010\u001a\u001a\u00020\u00172\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bJ\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u000fJ\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0002R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/th3rdwave/safeareacontext/SafeAreaView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "Lcom/facebook/react/uimanager/FabricViewStateManager$HasFabricViewStateManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mEdges", "Ljava/util/EnumSet;", "Lcom/th3rdwave/safeareacontext/SafeAreaViewEdges;", "mFabricViewStateManager", "Lcom/facebook/react/uimanager/FabricViewStateManager;", "mInsets", "Lcom/th3rdwave/safeareacontext/EdgeInsets;", "mMode", "Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "mProviderView", "Landroid/view/View;", "findProvider", "getFabricViewStateManager", "maybeUpdateInsets", "", "onAttachedToWindow", "", "onDetachedFromWindow", "onPreDraw", "setEdges", "edges", "setMode", "mode", "updateInsets", "waitForReactLayout", "react-native-safe-area-context_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SafeAreaView extends ReactViewGroup implements ViewTreeObserver.OnPreDrawListener, FabricViewStateManager.HasFabricViewStateManager {
    private EnumSet<SafeAreaViewEdges> mEdges;
    private final FabricViewStateManager mFabricViewStateManager;
    private EdgeInsets mInsets;
    private SafeAreaViewMode mMode;
    private View mProviderView;

    public SafeAreaView(Context context) {
        super(context);
        this.mMode = SafeAreaViewMode.PADDING;
        this.mFabricViewStateManager = new FabricViewStateManager();
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    /* JADX INFO: renamed from: getFabricViewStateManager, reason: from getter */
    public FabricViewStateManager getMFabricViewStateManager() {
        return this.mFabricViewStateManager;
    }

    private final void updateInsets() {
        final EdgeInsets edgeInsets = this.mInsets;
        if (edgeInsets != null) {
            EnumSet<SafeAreaViewEdges> edges = this.mEdges;
            if (edges == null) {
                edges = EnumSet.allOf(SafeAreaViewEdges.class);
            }
            if (this.mFabricViewStateManager.hasStateWrapper()) {
                this.mFabricViewStateManager.setState(new FabricViewStateManager.StateUpdateCallback() { // from class: com.th3rdwave.safeareacontext.SafeAreaView$$ExternalSyntheticLambda0
                    @Override // com.facebook.react.uimanager.FabricViewStateManager.StateUpdateCallback
                    public final WritableMap getStateUpdate() {
                        return SafeAreaView.m2780updateInsets$lambda0(edgeInsets);
                    }
                });
                return;
            }
            SafeAreaViewMode safeAreaViewMode = this.mMode;
            Intrinsics.checkNotNullExpressionValue(edges, "edges");
            SafeAreaViewLocalData safeAreaViewLocalData = new SafeAreaViewLocalData(edgeInsets, safeAreaViewMode, edges);
            ReactContext reactContext = UIManagerHelperCompatKt.getReactContext(this);
            final UIManagerModule uIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), safeAreaViewLocalData);
                reactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.th3rdwave.safeareacontext.SafeAreaView$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SafeAreaView.m2781updateInsets$lambda1(uIManagerModule);
                    }
                });
                waitForReactLayout();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateInsets$lambda-0, reason: not valid java name */
    public static final WritableMap m2780updateInsets$lambda0(EdgeInsets edgeInsets) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putMap("insets", SerializationUtilsKt.edgeInsetsToJsMap(edgeInsets));
        return writableMapCreateMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateInsets$lambda-1, reason: not valid java name */
    public static final void m2781updateInsets$lambda1(UIManagerModule uIManagerModule) {
        uIManagerModule.getUIImplementation().dispatchViewUpdates(-1);
    }

    private final void waitForReactLayout() {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition conditionNewCondition = reentrantLock.newCondition();
        long jNanoTime = System.nanoTime();
        UIManagerHelperCompatKt.getReactContext(this).runOnNativeModulesQueueThread(new Runnable() { // from class: com.th3rdwave.safeareacontext.SafeAreaView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SafeAreaView.m2782waitForReactLayout$lambda3(reentrantLock, booleanRef, conditionNewCondition);
            }
        });
        reentrantLock.lock();
        long jNanoTime2 = 0;
        while (!booleanRef.element && jNanoTime2 < 500000000) {
            try {
                try {
                    conditionNewCondition.awaitNanos(500000000L);
                } catch (InterruptedException unused) {
                    booleanRef.element = true;
                }
                jNanoTime2 += System.nanoTime() - jNanoTime;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        reentrantLock.unlock();
        if (jNanoTime2 >= 500000000) {
            Log.w("SafeAreaView", "Timed out waiting for layout.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: waitForReactLayout$lambda-3, reason: not valid java name */
    public static final void m2782waitForReactLayout$lambda3(ReentrantLock lock, Ref.BooleanRef done, Condition condition) {
        Intrinsics.checkNotNullParameter(lock, "$lock");
        Intrinsics.checkNotNullParameter(done, "$done");
        lock.lock();
        try {
            if (!done.element) {
                done.element = true;
                condition.signal();
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            lock.unlock();
        }
    }

    public final void setMode(SafeAreaViewMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.mMode = mode;
        updateInsets();
    }

    public final void setEdges(EnumSet<SafeAreaViewEdges> edges) {
        this.mEdges = edges;
        updateInsets();
    }

    private final boolean maybeUpdateInsets() {
        EdgeInsets safeAreaInsets;
        View view = this.mProviderView;
        if (view == null || (safeAreaInsets = SafeAreaUtilsKt.getSafeAreaInsets(view)) == null || Intrinsics.areEqual(this.mInsets, safeAreaInsets)) {
            return false;
        }
        this.mInsets = safeAreaInsets;
        updateInsets();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final View findProvider() {
        for (ViewParent parent = getParent(); parent != 0; parent = parent.getParent()) {
            if (parent instanceof SafeAreaProvider) {
                return (View) parent;
            }
        }
        return this;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        ViewTreeObserver viewTreeObserver;
        super.onAttachedToWindow();
        View viewFindProvider = findProvider();
        this.mProviderView = viewFindProvider;
        if (viewFindProvider != null && (viewTreeObserver = viewFindProvider.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnPreDrawListener(this);
        }
        maybeUpdateInsets();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver;
        super.onDetachedFromWindow();
        View view = this.mProviderView;
        if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnPreDrawListener(this);
        }
        this.mProviderView = null;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        boolean zMaybeUpdateInsets = maybeUpdateInsets();
        if (zMaybeUpdateInsets) {
            requestLayout();
        }
        return !zMaybeUpdateInsets;
    }
}
