package com.facebook.react;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.turbomodule.core.TurboModuleManagerDelegate;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ReactPackageTurboModuleManagerDelegate extends TurboModuleManagerDelegate {
    private final ReactApplicationContext mReactApplicationContext;
    private final List<TurboReactPackage> mPackages = new ArrayList();
    private final Map<TurboReactPackage, Map<String, ReactModuleInfo>> mPackageModuleInfos = new HashMap();

    protected ReactPackageTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<ReactPackage> list) {
        this.mReactApplicationContext = reactApplicationContext;
        for (ReactPackage reactPackage : list) {
            if (reactPackage instanceof TurboReactPackage) {
                TurboReactPackage turboReactPackage = (TurboReactPackage) reactPackage;
                this.mPackages.add(turboReactPackage);
                this.mPackageModuleInfos.put(turboReactPackage, turboReactPackage.getReactModuleInfoProvider().getReactModuleInfos());
            }
        }
    }

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    @Nullable
    public TurboModule getModule(String str) {
        TurboModule turboModuleResolveModule = resolveModule(str);
        if (turboModuleResolveModule == null || (turboModuleResolveModule instanceof CxxModuleWrapper)) {
            return null;
        }
        return turboModuleResolveModule;
    }

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    @Nullable
    @DoNotStrip
    public CxxModuleWrapper getLegacyCxxModule(String str) {
        Object objResolveModule = resolveModule(str);
        if (objResolveModule != null && (objResolveModule instanceof CxxModuleWrapper)) {
            return (CxxModuleWrapper) objResolveModule;
        }
        return null;
    }

    @Nullable
    private TurboModule resolveModule(String str) {
        NativeModule nativeModule = null;
        for (TurboReactPackage turboReactPackage : this.mPackages) {
            try {
                ReactModuleInfo reactModuleInfo = this.mPackageModuleInfos.get(turboReactPackage).get(str);
                if (reactModuleInfo != null && reactModuleInfo.isTurboModule() && (nativeModule == null || reactModuleInfo.canOverrideExistingModule())) {
                    NativeModule module = turboReactPackage.getModule(str, this.mReactApplicationContext);
                    if (module != null) {
                        nativeModule = module;
                    }
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (nativeModule instanceof TurboModule) {
            return (TurboModule) nativeModule;
        }
        return null;
    }

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    public List<String> getEagerInitModuleNames() {
        ArrayList arrayList = new ArrayList();
        Iterator<TurboReactPackage> it = this.mPackages.iterator();
        while (it.hasNext()) {
            for (ReactModuleInfo reactModuleInfo : it.next().getReactModuleInfoProvider().getReactModuleInfos().values()) {
                if (reactModuleInfo.isTurboModule() && reactModuleInfo.needsEagerInit()) {
                    arrayList.add(reactModuleInfo.name());
                }
            }
        }
        return arrayList;
    }

    public static abstract class Builder {

        @Nullable
        private ReactApplicationContext mContext;

        @Nullable
        private List<ReactPackage> mPackages;

        protected abstract ReactPackageTurboModuleManagerDelegate build(ReactApplicationContext reactApplicationContext, List<ReactPackage> list);

        public Builder setPackages(List<ReactPackage> list) {
            this.mPackages = new ArrayList(list);
            return this;
        }

        public Builder setReactApplicationContext(ReactApplicationContext reactApplicationContext) {
            this.mContext = reactApplicationContext;
            return this;
        }

        public ReactPackageTurboModuleManagerDelegate build() {
            Assertions.assertNotNull(this.mContext, "The ReactApplicationContext must be provided to create ReactPackageTurboModuleManagerDelegate");
            Assertions.assertNotNull(this.mPackages, "A set of ReactPackages must be provided to create ReactPackageTurboModuleManagerDelegate");
            return build(this.mContext, this.mPackages);
        }
    }
}
