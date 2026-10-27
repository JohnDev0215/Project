package com.shopify.reactnative.flash_list;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: AutoLayoutViewManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@ReactModule(name = AutoLayoutViewManager.REACT_CLASS)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0016H\u0007J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0016H\u0007J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0006H\u0007J\u0018\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0006H\u0007J\u0018\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0006H\u0007¨\u0006\""}, d2 = {"Lcom/shopify/reactnative/flash_list/AutoLayoutViewManager;", "Lcom/facebook/react/views/view/ReactViewManager;", "()V", "convertToPixelLayout", "", "dp", "", "density", "createViewInstance", "Lcom/facebook/react/views/view/ReactViewGroup;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "setDisableAutoLayout", "", "view", "Lcom/shopify/reactnative/flash_list/AutoLayoutView;", "disableAutoLayout", "", "setEnableInstrumentation", "enableInstrumentation", "setHorizontal", "isHorizontal", "setRenderAheadOffset", "renderOffset", "setScrollOffset", "scrollOffset", "setWindowSize", "windowSize", "Companion", "shopify_flash-list_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutoLayoutViewManager extends ReactViewManager {
    public static final String REACT_CLASS = "AutoLayoutView";

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager
    public ReactViewGroup createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AutoLayoutView autoLayoutView = new AutoLayoutView(context);
        autoLayoutView.setPixelDensity(context.getResources().getDisplayMetrics().density);
        return autoLayoutView;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> mapBuild = MapBuilder.builder().put("onBlankAreaEvent", MapBuilder.of("registrationName", "onBlankAreaEvent")).build();
        Intrinsics.checkNotNullExpressionValue(mapBuild, "builder<String, Any>().p…Event\")\n        ).build()");
        return mapBuild;
    }

    @ReactProp(name = "horizontal")
    public final void setHorizontal(AutoLayoutView view, boolean isHorizontal) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.getAlShadow().setHorizontal(isHorizontal);
    }

    @ReactProp(name = "disableAutoLayout")
    public final void setDisableAutoLayout(AutoLayoutView view, boolean disableAutoLayout) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setDisableAutoLayout(disableAutoLayout);
    }

    @ReactProp(name = "scrollOffset")
    public final void setScrollOffset(AutoLayoutView view, double scrollOffset) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.getAlShadow().setScrollOffset(convertToPixelLayout(scrollOffset, view.getPixelDensity()));
    }

    @ReactProp(name = "windowSize")
    public final void setWindowSize(AutoLayoutView view, double windowSize) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.getAlShadow().setWindowSize(convertToPixelLayout(windowSize, view.getPixelDensity()));
    }

    @ReactProp(name = "renderAheadOffset")
    public final void setRenderAheadOffset(AutoLayoutView view, double renderOffset) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.getAlShadow().setRenderOffset(convertToPixelLayout(renderOffset, view.getPixelDensity()));
    }

    @ReactProp(name = "enableInstrumentation")
    public final void setEnableInstrumentation(AutoLayoutView view, boolean enableInstrumentation) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setEnableInstrumentation(enableInstrumentation);
    }

    private final int convertToPixelLayout(double dp, double density) {
        return MathKt__MathJVMKt.roundToInt(dp * density);
    }
}
