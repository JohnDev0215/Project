package com.facebook.react;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collection;

/* JADX INFO: loaded from: classes6.dex */
public interface ViewManagerOnDemandReactPackage {
    @Nullable
    ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str);

    Collection<String> getViewManagerNames(ReactApplicationContext reactApplicationContext);
}
