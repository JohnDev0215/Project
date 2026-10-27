package com.facebook.react.fabric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.JSIModuleProvider;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.systrace.Systrace;

/* JADX INFO: loaded from: classes6.dex */
public class FabricJSIModuleProvider implements JSIModuleProvider<UIManager> {

    @NonNull
    private final ComponentFactory mComponentFactory;

    @NonNull
    private final ReactNativeConfig mConfig;

    @Nullable
    private final CppComponentRegistry mCppComponentRegistry;

    @NonNull
    private final ReactApplicationContext mReactApplicationContext;

    @NonNull
    private final ViewManagerRegistry mViewManagerRegistry;

    public FabricJSIModuleProvider(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ComponentFactory componentFactory, @NonNull ReactNativeConfig reactNativeConfig, @NonNull ViewManagerRegistry viewManagerRegistry) {
        this(reactApplicationContext, componentFactory, reactNativeConfig, viewManagerRegistry, null);
    }

    public FabricJSIModuleProvider(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ComponentFactory componentFactory, @NonNull ReactNativeConfig reactNativeConfig, @NonNull ViewManagerRegistry viewManagerRegistry, @Nullable CppComponentRegistry cppComponentRegistry) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mComponentFactory = componentFactory;
        this.mConfig = reactNativeConfig;
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mCppComponentRegistry = cppComponentRegistry;
    }

    @Override // com.facebook.react.bridge.JSIModuleProvider
    public UIManager get() {
        Systrace.beginSection(0L, "FabricJSIModuleProvider.get");
        EventBeatManager eventBeatManager = new EventBeatManager(this.mReactApplicationContext);
        FabricUIManager fabricUIManagerCreateUIManager = createUIManager(eventBeatManager);
        Systrace.beginSection(0L, "FabricJSIModuleProvider.registerBinding");
        new Binding().register(this.mReactApplicationContext.getCatalystInstance().getRuntimeExecutor(), this.mReactApplicationContext.getCatalystInstance().getRuntimeScheduler(), fabricUIManagerCreateUIManager, eventBeatManager, this.mComponentFactory, this.mConfig, this.mCppComponentRegistry);
        Systrace.endSection(0L);
        Systrace.endSection(0L);
        return fabricUIManagerCreateUIManager;
    }

    private FabricUIManager createUIManager(@NonNull EventBeatManager eventBeatManager) {
        Systrace.beginSection(0L, "FabricJSIModuleProvider.createUIManager");
        FabricUIManager fabricUIManager = new FabricUIManager(this.mReactApplicationContext, this.mViewManagerRegistry, eventBeatManager);
        Systrace.endSection(0L);
        return fabricUIManager;
    }
}
