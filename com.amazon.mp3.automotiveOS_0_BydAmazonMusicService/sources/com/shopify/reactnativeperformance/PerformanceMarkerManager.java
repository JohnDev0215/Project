package com.shopify.reactnativeperformance;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PerformanceMarker.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0014\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\tH\u0007J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\tH\u0007J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\tH\u0007J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\tH\u0007¨\u0006\u0016"}, d2 = {"Lcom/shopify/reactnativeperformance/PerformanceMarkerManager;", "Lcom/facebook/react/uimanager/SimpleViewManager;", "Lcom/shopify/reactnativeperformance/PerformanceMarker;", "()V", "createViewInstance", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "setComponentInstanceId", "", "view", "componentInstanceId", "setDestinationScreen", "destinationScreen", "setInteractive", "interactive", "setRenderPassName", "renderPassName", "shopify_react-native-performance_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PerformanceMarkerManager extends SimpleViewManager<PerformanceMarker> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "PerformanceMarker";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public PerformanceMarker createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new PerformanceMarker(reactContext);
    }

    @ReactProp(name = "destinationScreen")
    public final void setDestinationScreen(PerformanceMarker view, String destinationScreen) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(destinationScreen, "destinationScreen");
        view.setDestinationScreen(destinationScreen);
    }

    @ReactProp(name = "renderPassName")
    public final void setRenderPassName(PerformanceMarker view, String renderPassName) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(renderPassName, "renderPassName");
        view.setRenderPassName(renderPassName);
    }

    @ReactProp(name = "interactive")
    public final void setInteractive(PerformanceMarker view, String interactive) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(interactive, "interactive");
        view.setInteractive(new Interactive(interactive));
    }

    @ReactProp(name = "componentInstanceId")
    public final void setComponentInstanceId(PerformanceMarker view, String componentInstanceId) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(componentInstanceId, "componentInstanceId");
        view.setComponentInstanceId(componentInstanceId);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> mapBuild = MapBuilder.builder().put("onRenderComplete", MapBuilder.of("registrationName", "onRenderComplete")).build();
        Intrinsics.checkNotNullExpressionValue(mapBuild, "builder<String, Any>().p…plete\")\n        ).build()");
        return mapBuild;
    }
}
