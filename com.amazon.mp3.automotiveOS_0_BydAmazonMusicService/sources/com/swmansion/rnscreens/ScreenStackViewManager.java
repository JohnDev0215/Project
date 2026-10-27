package com.swmansion.rnscreens;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenStackManagerDelegate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenStackViewManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0007\b\u0007\u0018\u0000 &2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001&B\u0007¢\u0006\u0004\b$\u0010%J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0014J \u0010\u0014\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0018\u0010\u0015\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0018\u0010\u0017\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001eH\u0014J\u0014\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030 H\u0016R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006'"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStackViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/swmansion/rnscreens/ScreenStack;", "", "Lcom/swmansion/rnscreens/Screen;", "screen", "", "prepareOutTransition", "Landroid/view/ViewGroup;", "parent", "startTransitionRecursive", "", "getName", "Lcom/facebook/react/uimanager/ThemedReactContext;", "reactContext", "createViewInstance", "Landroid/view/View;", "child", "", "index", "addView", "removeViewAt", "getChildCount", "getChildAt", "Lcom/facebook/react/bridge/ReactApplicationContext;", "context", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "createShadowNodeInstance", "", "needsCustomLayoutForChildren", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "getDelegate", "", "getExportedCustomDirectEventTypeConstants", "mDelegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "<init>", "()V", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 6, 0})
@ReactModule(name = ScreenStackViewManager.REACT_CLASS)
public final class ScreenStackViewManager extends ViewGroupManager<ScreenStack> {
    public static final String REACT_CLASS = "RNSScreenStack";
    private final ViewManagerDelegate<ScreenStack> mDelegate = new RNSScreenStackManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ScreenStack createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new ScreenStack(reactContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(ScreenStack parent, View child, int index) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        if (!(child instanceof Screen)) {
            throw new IllegalArgumentException("Attempt attach child that is not of type RNScreen".toString());
        }
        parent.addScreen((Screen) child, index);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(ScreenStack parent, int index) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        prepareOutTransition(parent.getScreenAt(index));
        parent.removeScreenAt(index);
    }

    private final void prepareOutTransition(Screen screen) {
        startTransitionRecursive(screen);
    }

    private final void startTransitionRecursive(ViewGroup parent) {
        if (parent == null) {
            return;
        }
        int i = 0;
        int childCount = parent.getChildCount();
        while (i < childCount) {
            int i2 = i + 1;
            View childAt = parent.getChildAt(i);
            if (childAt != null) {
                parent.startViewTransition(childAt);
            }
            if (childAt instanceof ScreenStackHeaderConfig) {
                startTransitionRecursive(((ScreenStackHeaderConfig) childAt).getToolbar());
            }
            if (childAt instanceof ViewGroup) {
                startTransitionRecursive((ViewGroup) childAt);
            }
            i = i2;
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(ScreenStack parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return parent.getScreenCount();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(ScreenStack parent, int index) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return parent.getScreenAt(index);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance(ReactApplicationContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ScreensShadowNode(context);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<ScreenStack> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapsKt__MapsKt.mutableMapOf(TuplesKt.to("topFinishTransitioning", MapsKt__MapsKt.mutableMapOf(TuplesKt.to("registrationName", "onFinishTransitioning"))));
    }
}
