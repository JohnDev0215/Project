package com.facebook.react.modules.bundleloader;

import androidx.annotation.NonNull;
import com.facebook.fbreact.specs.NativeDevSplitBundleLoaderSpec;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(name = NativeDevSplitBundleLoaderModule.NAME)
public class NativeDevSplitBundleLoaderModule extends NativeDevSplitBundleLoaderSpec {
    public static final String NAME = "DevSplitBundleLoader";
    private static final String REJECTION_CODE = "E_BUNDLE_LOAD_ERROR";
    private final DevSupportManager mDevSupportManager;

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return NAME;
    }

    public NativeDevSplitBundleLoaderModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mDevSupportManager = devSupportManager;
    }

    @Override // com.facebook.fbreact.specs.NativeDevSplitBundleLoaderSpec
    public void loadBundle(String str, final Promise promise) {
        this.mDevSupportManager.loadSplitBundleFromServer(str, new DevSplitBundleCallback() { // from class: com.facebook.react.modules.bundleloader.NativeDevSplitBundleLoaderModule.1
        });
    }
}
