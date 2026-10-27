package com.amazon.mp3.bottomnavbar.tabbar;

import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;

/* JADX INFO: compiled from: Tab.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0003R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000ej\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/bottomnavbar/tabbar/Tab;", "", "tabPosition", "", "tabImageResource", "tabTextResource", "metricsKey", "", "(Ljava/lang/String;IIIILjava/lang/String;)V", "getMetricsKey", "()Ljava/lang/String;", "getTabImageResource", "()I", "setTabImageResource", "(I)V", "getTabPosition", "setTabPosition", "getTabTextResource", "setTabTextResource", "changeTabPosition", "", "newPosition", "BROWSE", "SEARCH", "LIBRARY", "STORE", "ALEXA", "UPGRADE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum Tab {
    BROWSE(0, R.drawable.tab_bar_browse, R.string.browse, "tab_bar_browse_tab"),
    SEARCH(1, R.drawable.tab_bar_search, R.string.find, "tab_bar_search_tab"),
    LIBRARY(2, R.drawable.tab_bar_mymusic, R.string.mymusic, "tab_bar_library_tab"),
    STORE(3, R.drawable.tab_bar_store, R.string.store, "tab_bar_store_tab"),
    ALEXA(4, R.drawable.tab_bar_alexa, R.string.alexa, "tab_bar_alexa_tab"),
    UPGRADE(5, R.drawable.tab_bar_upsell, R.string.upgrade, "tab_bar_upsell_tab");

    private final String metricsKey;
    private int tabImageResource;
    private int tabPosition;
    private int tabTextResource;

    Tab(int i, int i2, int i3, String str) {
        this.tabPosition = i;
        this.tabImageResource = i2;
        this.tabTextResource = i3;
        this.metricsKey = str;
    }

    public final String getMetricsKey() {
        return this.metricsKey;
    }

    public final int getTabImageResource() {
        return this.tabImageResource;
    }

    public final int getTabPosition() {
        return this.tabPosition;
    }

    public final int getTabTextResource() {
        return this.tabTextResource;
    }

    public final void setTabImageResource(int i) {
        this.tabImageResource = i;
    }

    public final void setTabPosition(int i) {
        this.tabPosition = i;
    }

    public final void setTabTextResource(int i) {
        this.tabTextResource = i;
    }

    public final void changeTabPosition(int newPosition) {
        this.tabPosition = newPosition;
    }
}
