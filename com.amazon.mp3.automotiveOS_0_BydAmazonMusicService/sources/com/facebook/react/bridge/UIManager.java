package com.facebook.react.bridge;

import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public interface UIManager extends JSIModule, PerformanceCounter {
    @UiThread
    @Deprecated
    <T extends View> int addRootView(T t, WritableMap writableMap, @Nullable String str);

    void addUIManagerEventListener(UIManagerListener uIManagerListener);

    void dispatchCommand(int i, int i2, @Nullable ReadableArray readableArray);

    void dispatchCommand(int i, String str, @Nullable ReadableArray readableArray);

    <T> T getEventDispatcher();

    @Deprecated
    void preInitializeViewManagers(List<String> list);

    void receiveEvent(int i, int i2, String str, @Nullable WritableMap writableMap);

    @Deprecated
    void receiveEvent(int i, String str, @Nullable WritableMap writableMap);

    void removeUIManagerEventListener(UIManagerListener uIManagerListener);

    @Nullable
    @Deprecated
    String resolveCustomDirectEventName(@Nullable String str);

    View resolveView(int i);

    void sendAccessibilityEvent(int i, int i2);

    @AnyThread
    <T extends View> int startSurface(T t, String str, WritableMap writableMap, int i, int i2);

    @AnyThread
    void stopSurface(int i);

    @UiThread
    void synchronouslyUpdateViewOnUIThread(int i, ReadableMap readableMap);

    @UiThread
    void updateRootLayoutSpecs(int i, int i2, int i3, int i4, int i5);
}
