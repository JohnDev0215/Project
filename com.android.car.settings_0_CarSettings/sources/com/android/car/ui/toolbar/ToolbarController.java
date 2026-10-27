package com.android.car.ui.toolbar;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.car.ui.imewidescreen.CarUiImeSearchListItem;
import com.android.car.ui.toolbar.TabLayout;
import com.android.car.ui.toolbar.Toolbar;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public interface ToolbarController {
    void addTab(TabLayout.Tab tab);

    boolean canShowSearchResultItems();

    boolean canShowSearchResultsView();

    void clearAllTabs();

    MenuItem findMenuItemById(int i);

    boolean getBackgroundShown();

    List<MenuItem> getMenuItems();

    Toolbar.NavButtonMode getNavButtonMode();

    ProgressBarController getProgressBar();

    CharSequence getSearchHint();

    boolean getShowMenuItemsWhileSearching();

    boolean getShowTabsInSubpage();

    Toolbar.State getState();

    CharSequence getSubtitle();

    TabLayout.Tab getTab(int i);

    int getTabCount();

    @Deprecated
    TabLayout getTabLayout();

    int getTabPosition(TabLayout.Tab tab);

    CharSequence getTitle();

    boolean isTabsInSecondRow();

    void registerOnBackListener(Toolbar.OnBackListener onBackListener);

    void registerOnSearchCompletedListener(Toolbar.OnSearchCompletedListener onSearchCompletedListener);

    void registerOnSearchListener(Toolbar.OnSearchListener onSearchListener);

    void registerOnTabSelectedListener(Toolbar.OnTabSelectedListener onTabSelectedListener);

    void registerToolbarHeightChangeListener(Toolbar.OnHeightChangedListener onHeightChangedListener);

    MenuItem requireMenuItemById(int i);

    void selectTab(int i);

    void setBackgroundShown(boolean z);

    void setLogo(int i);

    void setLogo(Drawable drawable);

    List<MenuItem> setMenuItems(int i);

    void setMenuItems(List<MenuItem> list);

    void setNavButtonMode(Toolbar.NavButtonMode navButtonMode);

    void setSearchHint(int i);

    void setSearchHint(CharSequence charSequence);

    void setSearchIcon(int i);

    void setSearchIcon(Drawable drawable);

    void setSearchQuery(String str);

    void setSearchResultItems(List<? extends CarUiImeSearchListItem> list);

    void setSearchResultsView(View view);

    void setShowMenuItemsWhileSearching(boolean z);

    void setShowTabsInSubpage(boolean z);

    void setState(Toolbar.State state);

    void setSubtitle(int i);

    void setSubtitle(CharSequence charSequence);

    void setTitle(int i);

    void setTitle(CharSequence charSequence);

    boolean unregisterOnBackListener(Toolbar.OnBackListener onBackListener);

    boolean unregisterOnSearchCompletedListener(Toolbar.OnSearchCompletedListener onSearchCompletedListener);

    boolean unregisterOnSearchListener(Toolbar.OnSearchListener onSearchListener);

    boolean unregisterOnTabSelectedListener(Toolbar.OnTabSelectedListener onTabSelectedListener);

    boolean unregisterToolbarHeightChangeListener(Toolbar.OnHeightChangedListener onHeightChangedListener);
}
