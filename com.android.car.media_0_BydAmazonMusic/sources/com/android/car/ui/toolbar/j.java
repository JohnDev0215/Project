package com.android.car.ui.toolbar;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import java.util.List;
import java.util.function.Consumer;
import spi.ab;
import spi.bb;
import spi.cb;
import spi.db;
import spi.eb;
import spi.s7;
import spi.v9;

/* JADX INFO: compiled from: ToolbarController.java */
/* JADX INFO: loaded from: classes2.dex */
public interface j {
    s7 getNavButtonMode();

    d getSearchCapabilities();

    v9 getSearchMode();

    @Deprecated
    boolean isStateSet();

    @Deprecated
    void registerOnBackListener(bb bbVar);

    @Deprecated
    void registerOnSearchCompletedListener(cb cbVar);

    void registerOnSearchListener(db dbVar);

    @Deprecated
    void registerOnTabSelectedListener(i iVar);

    void registerSearchCompletedListener(Runnable runnable);

    void registerSearchListener(Consumer<String> consumer);

    void selectTab(int i);

    void setBackground(int i);

    void setBackgroundShown(boolean z);

    void setLogo(Drawable drawable);

    void setMenuItems(@Nullable List<b> list);

    @Deprecated
    void setNavButtonMode(ab abVar);

    void setNavButtonMode(s7 s7Var);

    void setSearchBack(int i);

    void setSearchConfig(@Nullable e eVar);

    void setSearchIcon(Drawable drawable);

    void setSearchMode(v9 v9Var);

    void setSearchQuery(String str);

    @Deprecated
    void setState(eb ebVar);

    void setSubtitle(@Nullable CharSequence charSequence);

    void setTabs(@Nullable List<g> list);

    void setTabs(@Nullable List<g> list, int i);

    void setTitle(@StringRes int i);

    void setTitle(@Nullable CharSequence charSequence);

    void showLineView(boolean z);

    void showOtherView();

    void showSignInView();
}
