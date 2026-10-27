package com.swmansion.gesturehandler.react;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.RNGestureHandlerRootViewManagerDelegate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RNGestureHandlerRootViewManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@ReactModule(name = RNGestureHandlerRootViewManager.REACT_CLASS)
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0007\b\u0007\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001\u0014B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0014J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0014J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J \u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000e0\u000eH\u0016R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootView;", "", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "getDelegate", "", "getName", "Lcom/facebook/react/uimanager/ThemedReactContext;", "reactContext", "createViewInstance", "view", "", "onDropViewInstance", "", "getExportedCustomDirectEventTypeConstants", "mDelegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "<init>", "()V", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0})
public final class RNGestureHandlerRootViewManager extends ViewGroupManager<RNGestureHandlerRootView> {
    public static final String REACT_CLASS = "RNGestureHandlerRootView";
    private final ViewManagerDelegate<RNGestureHandlerRootView> mDelegate = new RNGestureHandlerRootViewManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<RNGestureHandlerRootView> getDelegate() {
        return this.mDelegate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RNGestureHandlerRootView createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new RNGestureHandlerRootView(reactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(RNGestureHandlerRootView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.tearDown();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Map<String, String>> getExportedCustomDirectEventTypeConstants() {
        return MapsKt__MapsKt.mutableMapOf(TuplesKt.to("onGestureHandlerEvent", MapsKt__MapsKt.mutableMapOf(TuplesKt.to("registrationName", "onGestureHandlerEvent"))), TuplesKt.to("onGestureHandlerStateChange", MapsKt__MapsKt.mutableMapOf(TuplesKt.to("registrationName", "onGestureHandlerStateChange"))));
    }
}
