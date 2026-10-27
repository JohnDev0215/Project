package com.facebook.react.uimanager;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes6.dex */
public interface ReactRoot {
    @Nullable
    Bundle getAppProperties();

    int getHeightMeasureSpec();

    @Nullable
    String getInitialUITemplate();

    String getJSModuleName();

    ViewGroup getRootViewGroup();

    int getRootViewTag();

    AtomicInteger getState();

    @Nullable
    @Deprecated
    String getSurfaceID();

    int getUIManagerType();

    int getWidthMeasureSpec();

    void onStage(int i);

    void runApplication();

    void setRootViewTag(int i);

    void setShouldLogContentAppeared(boolean z);
}
