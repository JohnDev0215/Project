package com.facebook.react.uimanager;

import java.util.Collection;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public interface ViewManagerResolver {
    @Nullable
    ViewManager getViewManager(String str);

    Collection<String> getViewManagerNames();
}
