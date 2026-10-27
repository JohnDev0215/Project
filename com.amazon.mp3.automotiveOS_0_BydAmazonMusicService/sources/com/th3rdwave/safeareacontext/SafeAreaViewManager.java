package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import java.util.EnumSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeAreaViewManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@ReactModule(name = SafeAreaViewManager.REACT_CLASS)
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001e2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u001eB\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0014J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0016J\u001a\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0017J\u001a\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0017J&\u0010\u001b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0010\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016¨\u0006\u001f"}, d2 = {"Lcom/th3rdwave/safeareacontext/SafeAreaViewManager;", "Lcom/facebook/react/views/view/ReactViewManager;", "", "Lcom/th3rdwave/safeareacontext/SafeAreaView;", "", "getName", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "Lcom/facebook/react/views/view/ReactViewGroup;", "getDelegate", "Lcom/facebook/react/uimanager/ThemedReactContext;", "context", "createViewInstance", "Lcom/th3rdwave/safeareacontext/SafeAreaViewShadowNode;", "createShadowNodeInstance", "Ljava/lang/Class;", "getShadowNodeClass", "view", "mode", "", "setMode", "Lcom/facebook/react/bridge/ReadableArray;", "propList", "setEdges", "Lcom/facebook/react/uimanager/ReactStylesDiffMap;", "props", "Lcom/facebook/react/uimanager/StateWrapper;", "stateWrapper", "updateState", "<init>", "()V", "Companion", "react-native-safe-area-context_release"}, k = 1, mv = {1, 6, 0})
public final class SafeAreaViewManager extends ReactViewManager {
    public static final String REACT_CLASS = "RNCSafeAreaView";

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<ReactViewGroup> getDelegate() {
        return null;
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<SafeAreaViewShadowNode> getShadowNodeClass() {
        return SafeAreaViewShadowNode.class;
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager
    public SafeAreaView createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SafeAreaView(context);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public SafeAreaViewShadowNode createShadowNodeInstance() {
        return new SafeAreaViewShadowNode();
    }

    @ReactProp(name = "mode")
    public void setMode(SafeAreaView view, String mode) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (Intrinsics.areEqual(mode, "padding")) {
            view.setMode(SafeAreaViewMode.PADDING);
        } else if (Intrinsics.areEqual(mode, "margin")) {
            view.setMode(SafeAreaViewMode.MARGIN);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @ReactProp(name = "edges")
    public void setEdges(SafeAreaView view, ReadableArray propList) {
        Intrinsics.checkNotNullParameter(view, "view");
        EnumSet<SafeAreaViewEdges> enumSetNoneOf = EnumSet.noneOf(SafeAreaViewEdges.class);
        if (propList != null) {
            int i = 0;
            int size = propList.size();
            while (i < size) {
                int i2 = i + 1;
                String string = propList.getString(i);
                switch (string.hashCode()) {
                    case -1383228885:
                        if (string.equals("bottom")) {
                            enumSetNoneOf.add(SafeAreaViewEdges.BOTTOM);
                        }
                        break;
                    case 115029:
                        if (string.equals("top")) {
                            enumSetNoneOf.add(SafeAreaViewEdges.TOP);
                        }
                        break;
                    case 3317767:
                        if (string.equals("left")) {
                            enumSetNoneOf.add(SafeAreaViewEdges.LEFT);
                        }
                        break;
                    case 108511772:
                        if (string.equals("right")) {
                            enumSetNoneOf.add(SafeAreaViewEdges.RIGHT);
                        }
                        break;
                }
                i = i2;
            }
            view.setEdges(enumSetNoneOf);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Object updateState(ReactViewGroup view, ReactStylesDiffMap props, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(view, "view");
        ((SafeAreaView) view).getMFabricViewStateManager().setStateWrapper(stateWrapper);
        return null;
    }
}
