package com.facebook.react;

import android.app.Application;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ReactNativeHost {
    private final Application mApplication;

    @Nullable
    private ReactInstanceManager mReactInstanceManager;

    @Nullable
    protected abstract String getBundleAssetName();

    @Nullable
    protected DevSupportManagerFactory getDevSupportManagerFactory() {
        return null;
    }

    @Nullable
    protected String getJSBundleFile() {
        return null;
    }

    @Nullable
    protected JSIModulePackage getJSIModulePackage() {
        return null;
    }

    protected abstract String getJSMainModuleName();

    @Nullable
    protected abstract JavaScriptExecutorFactory getJavaScriptExecutorFactory();

    public boolean getLazyViewManagersEnabled() {
        return false;
    }

    protected abstract List<ReactPackage> getPackages();

    @Nullable
    protected ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        return null;
    }

    @Nullable
    protected RedBoxHandler getRedBoxHandler() {
        return null;
    }

    public boolean getShouldRequireActivity() {
        return true;
    }

    public abstract boolean getUseDeveloperSupport();

    protected ReactNativeHost(Application application) {
        this.mApplication = application;
    }

    public ReactInstanceManager getReactInstanceManager() {
        if (this.mReactInstanceManager == null) {
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_START);
            this.mReactInstanceManager = createReactInstanceManager();
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_END);
        }
        return this.mReactInstanceManager;
    }

    public boolean hasInstance() {
        return this.mReactInstanceManager != null;
    }

    protected ReactInstanceManager createReactInstanceManager() {
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_START);
        ReactInstanceManagerBuilder reactPackageTurboModuleManagerDelegateBuilder = ReactInstanceManager.builder().setApplication(this.mApplication).setJSMainModulePath(getJSMainModuleName()).setUseDeveloperSupport(getUseDeveloperSupport()).setDevSupportManagerFactory(getDevSupportManagerFactory()).setRequireActivity(getShouldRequireActivity()).setSurfaceDelegateFactory(getSurfaceDelegateFactory()).setLazyViewManagersEnabled(getLazyViewManagersEnabled()).setRedBoxHandler(getRedBoxHandler()).setJavaScriptExecutorFactory(getJavaScriptExecutorFactory()).setJSIModulesPackage(getJSIModulePackage()).setInitialLifecycleState(LifecycleState.BEFORE_CREATE).setReactPackageTurboModuleManagerDelegateBuilder(getReactPackageTurboModuleManagerDelegateBuilder());
        Iterator<ReactPackage> it = getPackages().iterator();
        while (it.hasNext()) {
            reactPackageTurboModuleManagerDelegateBuilder.addPackage(it.next());
        }
        String jSBundleFile = getJSBundleFile();
        if (jSBundleFile != null) {
            reactPackageTurboModuleManagerDelegateBuilder.setJSBundleFile(jSBundleFile);
        } else {
            reactPackageTurboModuleManagerDelegateBuilder.setBundleAssetName((String) Assertions.assertNotNull(getBundleAssetName()));
        }
        ReactInstanceManager reactInstanceManagerBuild = reactPackageTurboModuleManagerDelegateBuilder.build();
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_END);
        return reactInstanceManagerBuild;
    }

    public SurfaceDelegateFactory getSurfaceDelegateFactory() {
        return new SurfaceDelegateFactory() { // from class: com.facebook.react.ReactNativeHost.1
        };
    }
}
