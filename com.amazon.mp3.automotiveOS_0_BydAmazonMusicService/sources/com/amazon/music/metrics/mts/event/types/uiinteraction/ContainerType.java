package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum ContainerType implements MetricValueType {
    DIALOG("DIALOG"),
    BLOCKING_UPSELL_DIALOG("BLOCKING_UPSELL_DIALOG"),
    NON_BLOCKING_UPSELL_DIALOG("NON_BLOCKING_UPSELL_DIALOG"),
    AD_UPSELL_DIALOG("AD_UPSELL_DIALOG"),
    IMPORT_PLAYLISTS_DIALOG("IMPORT_PLAYLISTS_DIALOG"),
    UI_COLLECTION_VIEW("UI_COLLECTION_VIEW"),
    SEARCH_CARD("SEARCH_CARD"),
    NOW_PLAYING("NOW_PLAYING"),
    MINI_TRANSPORT("MINI_TRANSPORT"),
    RIBBON("RIBBON"),
    ITEM_LIST("ITEM_LIST"),
    BANNER("BANNER"),
    TOAST("TOAST"),
    MODAL("MODAL"),
    HORIZONTAL_TILE_GRID("HORIZONTAL_TILE_GRID"),
    CAROUSEL("CAROUSEL"),
    EVENT_HEADER("EVENT_HEADER"),
    SECTION("SECTION"),
    VISUAL_SHOVELER("VISUAL_SHOVELER"),
    UPSELL_BUTTON("UPSELL_BUTTON");

    private final String mMetricValue;

    ContainerType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
