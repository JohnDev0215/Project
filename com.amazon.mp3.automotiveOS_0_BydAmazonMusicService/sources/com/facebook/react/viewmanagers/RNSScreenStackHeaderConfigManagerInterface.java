package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public interface RNSScreenStackHeaderConfigManagerInterface<T extends View> {
    void setBackButtonInCustomView(T t, boolean z);

    void setBackTitle(T t, @Nullable String str);

    void setBackTitleFontFamily(T t, @Nullable String str);

    void setBackTitleFontSize(T t, int i);

    void setBackgroundColor(T t, @Nullable Integer num);

    void setColor(T t, @Nullable Integer num);

    void setDirection(T t, @Nullable String str);

    void setDisableBackButtonMenu(T t, boolean z);

    void setHidden(T t, boolean z);

    void setHideBackButton(T t, boolean z);

    void setHideShadow(T t, boolean z);

    void setLargeTitle(T t, boolean z);

    void setLargeTitleBackgroundColor(T t, @Nullable Integer num);

    void setLargeTitleColor(T t, @Nullable Integer num);

    void setLargeTitleFontFamily(T t, @Nullable String str);

    void setLargeTitleFontSize(T t, int i);

    void setLargeTitleFontWeight(T t, @Nullable String str);

    void setLargeTitleHideShadow(T t, boolean z);

    void setTitle(T t, @Nullable String str);

    void setTitleColor(T t, @Nullable Integer num);

    void setTitleFontFamily(T t, @Nullable String str);

    void setTitleFontSize(T t, int i);

    void setTitleFontWeight(T t, @Nullable String str);

    void setTopInsetEnabled(T t, boolean z);

    void setTranslucent(T t, boolean z);
}
