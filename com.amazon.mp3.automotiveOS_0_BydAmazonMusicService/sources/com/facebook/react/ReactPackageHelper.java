package com.facebook.react;

import androidx.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class ReactPackageHelper {
    public static Iterable<ModuleHolder> getNativeModuleIterator(ReactPackage reactPackage, ReactApplicationContext reactApplicationContext, ReactInstanceManager reactInstanceManager) {
        final List<NativeModule> listCreateNativeModules;
        FLog.d("ReactNative", reactPackage.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.");
        if (reactPackage instanceof ReactInstancePackage) {
            listCreateNativeModules = ((ReactInstancePackage) reactPackage).createNativeModules(reactApplicationContext, reactInstanceManager);
        } else {
            listCreateNativeModules = reactPackage.createNativeModules(reactApplicationContext);
        }
        return new Iterable<ModuleHolder>() { // from class: com.facebook.react.ReactPackageHelper.1
            @Override // java.lang.Iterable
            @NonNull
            public Iterator<ModuleHolder> iterator() {
                return new Iterator<ModuleHolder>() { // from class: com.facebook.react.ReactPackageHelper.1.1
                    int position = 0;

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public ModuleHolder next() {
                        List list = listCreateNativeModules;
                        int i = this.position;
                        this.position = i + 1;
                        return new ModuleHolder((NativeModule) list.get(i));
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.position < listCreateNativeModules.size();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException("Cannot remove methods ");
                    }
                };
            }
        };
    }
}
