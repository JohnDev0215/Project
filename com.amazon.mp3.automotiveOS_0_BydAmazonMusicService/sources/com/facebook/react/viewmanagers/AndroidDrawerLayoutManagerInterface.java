package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public interface AndroidDrawerLayoutManagerInterface<T extends View> {
    void closeDrawer(T t);

    void openDrawer(T t);

    void setDrawerBackgroundColor(T t, @Nullable Integer num);

    void setDrawerLockMode(T t, @Nullable String str);

    void setDrawerPosition(T t, @Nullable String str);

    void setDrawerWidth(T t, @Nullable Float f);

    void setKeyboardDismissMode(T t, @Nullable String str);

    void setStatusBarBackgroundColor(T t, @Nullable Integer num);
}
