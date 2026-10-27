package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.hermes.reactexecutor.HermesExecutor;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DefaultDevSupportManagerFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.jscexecutor.JSCExecutor;
import com.facebook.react.jscexecutor.JSCExecutorFactory;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ReactInstanceManagerBuilder {
    private static final String TAG = "ReactInstanceManagerBuilder";

    @Nullable
    private Application mApplication;

    @Nullable
    private NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;

    @Nullable
    private Activity mCurrentActivity;

    @Nullable
    private Map<String, Object> mCustomPackagerCommandHandlers;

    @Nullable
    private DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;

    @Nullable
    private DevBundleDownloadListener mDevBundleDownloadListener;

    @Nullable
    private DevSupportManagerFactory mDevSupportManagerFactory;

    @Nullable
    private LifecycleState mInitialLifecycleState;

    @Nullable
    private String mJSBundleAssetUrl;

    @Nullable
    private JSBundleLoader mJSBundleLoader;

    @Nullable
    private JSExceptionHandler mJSExceptionHandler;

    @Nullable
    private JSIModulePackage mJSIModulesPackage;

    @Nullable
    private String mJSMainModulePath;

    @Nullable
    private JavaScriptExecutorFactory mJavaScriptExecutorFactory;
    private boolean mLazyViewManagersEnabled;

    @Nullable
    private RedBoxHandler mRedBoxHandler;
    private boolean mRequireActivity;

    @Nullable
    private SurfaceDelegateFactory mSurfaceDelegateFactory;

    @Nullable
    private ReactPackageTurboModuleManagerDelegate.Builder mTMMDelegateBuilder;
    private boolean mUseDeveloperSupport;
    private final List<ReactPackage> mPackages = new ArrayList();
    private int mMinNumShakes = 1;
    private int mMinTimeLeftInFrameForNonBatchedOperationMs = -1;
    private JSEngineResolutionAlgorithm jsEngineResolutionAlgorithm = null;

    ReactInstanceManagerBuilder() {
    }

    public ReactInstanceManagerBuilder setJSIModulesPackage(@Nullable JSIModulePackage jSIModulePackage) {
        this.mJSIModulesPackage = jSIModulePackage;
        return this;
    }

    public ReactInstanceManagerBuilder setJavaScriptExecutorFactory(@Nullable JavaScriptExecutorFactory javaScriptExecutorFactory) {
        this.mJavaScriptExecutorFactory = javaScriptExecutorFactory;
        return this;
    }

    public ReactInstanceManagerBuilder setBundleAssetName(String str) {
        String str2;
        if (str == null) {
            str2 = null;
        } else {
            str2 = "assets://" + str;
        }
        this.mJSBundleAssetUrl = str2;
        this.mJSBundleLoader = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSBundleFile(String str) {
        if (str.startsWith("assets://")) {
            this.mJSBundleAssetUrl = str;
            this.mJSBundleLoader = null;
            return this;
        }
        return setJSBundleLoader(JSBundleLoader.createFileLoader(str));
    }

    public ReactInstanceManagerBuilder setJSBundleLoader(JSBundleLoader jSBundleLoader) {
        this.mJSBundleLoader = jSBundleLoader;
        this.mJSBundleAssetUrl = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSMainModulePath(String str) {
        this.mJSMainModulePath = str;
        return this;
    }

    public ReactInstanceManagerBuilder addPackage(ReactPackage reactPackage) {
        this.mPackages.add(reactPackage);
        return this;
    }

    public ReactInstanceManagerBuilder setApplication(Application application) {
        this.mApplication = application;
        return this;
    }

    public ReactInstanceManagerBuilder setUseDeveloperSupport(boolean z) {
        this.mUseDeveloperSupport = z;
        return this;
    }

    public ReactInstanceManagerBuilder setDevSupportManagerFactory(DevSupportManagerFactory devSupportManagerFactory) {
        this.mDevSupportManagerFactory = devSupportManagerFactory;
        return this;
    }

    public ReactInstanceManagerBuilder setRequireActivity(boolean z) {
        this.mRequireActivity = z;
        return this;
    }

    public ReactInstanceManagerBuilder setSurfaceDelegateFactory(@Nullable SurfaceDelegateFactory surfaceDelegateFactory) {
        this.mSurfaceDelegateFactory = surfaceDelegateFactory;
        return this;
    }

    public ReactInstanceManagerBuilder setInitialLifecycleState(LifecycleState lifecycleState) {
        this.mInitialLifecycleState = lifecycleState;
        return this;
    }

    public ReactInstanceManagerBuilder setRedBoxHandler(@Nullable RedBoxHandler redBoxHandler) {
        this.mRedBoxHandler = redBoxHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setLazyViewManagersEnabled(boolean z) {
        this.mLazyViewManagersEnabled = z;
        return this;
    }

    public ReactInstanceManagerBuilder setReactPackageTurboModuleManagerDelegateBuilder(@Nullable ReactPackageTurboModuleManagerDelegate.Builder builder) {
        this.mTMMDelegateBuilder = builder;
        return this;
    }

    public ReactInstanceManager build() {
        String str;
        Assertions.assertNotNull(this.mApplication, "Application property has not been set with this builder");
        if (this.mInitialLifecycleState == LifecycleState.RESUMED) {
            Assertions.assertNotNull(this.mCurrentActivity, "Activity needs to be set if initial lifecycle state is resumed");
        }
        boolean z = true;
        Assertions.assertCondition((!this.mUseDeveloperSupport && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) ? false : true, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
        if (this.mJSMainModulePath == null && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) {
            z = false;
        }
        Assertions.assertCondition(z, "Either MainModulePath or JS Bundle File needs to be provided");
        String packageName = this.mApplication.getPackageName();
        String friendlyDeviceName = AndroidInfoHelpers.getFriendlyDeviceName();
        Application application = this.mApplication;
        Activity activity = this.mCurrentActivity;
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        JavaScriptExecutorFactory javaScriptExecutorFactory = this.mJavaScriptExecutorFactory;
        JavaScriptExecutorFactory defaultJSExecutorFactory = javaScriptExecutorFactory == null ? getDefaultJSExecutorFactory(packageName, friendlyDeviceName, application.getApplicationContext()) : javaScriptExecutorFactory;
        JSBundleLoader jSBundleLoaderCreateAssetLoader = this.mJSBundleLoader;
        if (jSBundleLoaderCreateAssetLoader == null && (str = this.mJSBundleAssetUrl) != null) {
            jSBundleLoaderCreateAssetLoader = JSBundleLoader.createAssetLoader(this.mApplication, str, false);
        }
        JSBundleLoader jSBundleLoader = jSBundleLoaderCreateAssetLoader;
        String str2 = this.mJSMainModulePath;
        List<ReactPackage> list = this.mPackages;
        boolean z2 = this.mUseDeveloperSupport;
        DevSupportManagerFactory defaultDevSupportManagerFactory = this.mDevSupportManagerFactory;
        if (defaultDevSupportManagerFactory == null) {
            defaultDevSupportManagerFactory = new DefaultDevSupportManagerFactory();
        }
        return new ReactInstanceManager(application, activity, defaultHardwareBackBtnHandler, defaultJSExecutorFactory, jSBundleLoader, str2, list, z2, defaultDevSupportManagerFactory, this.mRequireActivity, this.mBridgeIdleDebugListener, (LifecycleState) Assertions.assertNotNull(this.mInitialLifecycleState, "Initial lifecycle state was not set"), this.mJSExceptionHandler, this.mRedBoxHandler, this.mLazyViewManagersEnabled, this.mDevBundleDownloadListener, this.mMinNumShakes, this.mMinTimeLeftInFrameForNonBatchedOperationMs, this.mJSIModulesPackage, this.mCustomPackagerCommandHandlers, this.mTMMDelegateBuilder, this.mSurfaceDelegateFactory);
    }

    private JavaScriptExecutorFactory getDefaultJSExecutorFactory(String str, String str2, Context context) {
        JSEngineResolutionAlgorithm jSEngineResolutionAlgorithm = this.jsEngineResolutionAlgorithm;
        if (jSEngineResolutionAlgorithm == null) {
            FLog.w(TAG, "You're not setting the JS Engine Resolution Algorithm. We'll try to load JSC first, and if it fails we'll fallback to Hermes");
            try {
                ReactInstanceManager.initializeSoLoaderIfNecessary(context);
                JSCExecutor.loadLibrary();
                return new JSCExecutorFactory(str, str2);
            } catch (UnsatisfiedLinkError e) {
                if (e.getMessage().contains("__cxa_bad_typeid")) {
                    throw e;
                }
                HermesExecutor.loadLibrary();
                return new HermesExecutorFactory();
            }
        }
        if (jSEngineResolutionAlgorithm == JSEngineResolutionAlgorithm.HERMES) {
            HermesExecutor.loadLibrary();
            return new HermesExecutorFactory();
        }
        JSCExecutor.loadLibrary();
        return new JSCExecutorFactory(str, str2);
    }
}
