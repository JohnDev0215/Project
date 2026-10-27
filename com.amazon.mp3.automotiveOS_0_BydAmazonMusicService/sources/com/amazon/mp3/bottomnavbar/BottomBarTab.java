package com.amazon.mp3.bottomnavbar;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.tabbar.Tab;

/* JADX INFO: loaded from: classes3.dex */
public enum BottomBarTab {
    BROWSE(R.id.browse_music_tab, Tab.BROWSE),
    LIBRARY(R.id.your_library_tab, Tab.LIBRARY),
    SEARCH(R.id.search_button, Tab.SEARCH),
    ALEXA(R.id.alexa_button_tab, Tab.ALEXA),
    UPSELL(R.id.upsell_button_tab, Tab.UPGRADE);

    private Tab mComponentTab;
    private int mMenuId;

    BottomBarTab(int menuId, Tab componentTab) {
        this.mMenuId = menuId;
        this.mComponentTab = componentTab;
    }

    public int getMenuId() {
        return this.mMenuId;
    }

    public Tab getComponentTab() {
        return this.mComponentTab;
    }
}
