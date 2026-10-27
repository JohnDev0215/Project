package com.amazon.music.views.library.styles.keys;

import kotlin.Metadata;

/* JADX INFO: compiled from: GridStyleKey.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/views/library/styles/keys/GridStyleKey;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "VERTICAL_ITEM_GRID", "VERTICAL_16X9_ITEM_GRID", "HORIZONTAL_ITEM_GRID", "FEATURED_ITEM_GRID", "LIST_ITEM_GRID", "BUTTON_NAVIGATOR_GRID", "THUMBNAIL_NAVIGATOR_GRID", "COMPACT_VERTICAL_ITEM_GRID", "DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID", "DESCRIPTIVE_LISTING_GRID", "HORIZONTAL_ITEM_SECTION_NESTED_GRID", "CAR_MODE_GRID", "LARGE_VERTICAL_ITEM_GRID", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum GridStyleKey {
    VERTICAL_ITEM_GRID("Vertical_Item_Grid"),
    VERTICAL_16X9_ITEM_GRID("Vertical_16X9_Item_Grid"),
    HORIZONTAL_ITEM_GRID("Horizontal_Item_Grid"),
    FEATURED_ITEM_GRID("Featured_Item_Grid"),
    LIST_ITEM_GRID("List_Item_Grid"),
    BUTTON_NAVIGATOR_GRID("Button_Navigator_Grid"),
    THUMBNAIL_NAVIGATOR_GRID("Thumbnail_Navigator_Grid"),
    COMPACT_VERTICAL_ITEM_GRID("Compact_Vertical_Item_Grid"),
    DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID("Descriptive_Shoveler_Focused_Tile_grid"),
    DESCRIPTIVE_LISTING_GRID("Descriptive_Listing_Grid"),
    HORIZONTAL_ITEM_SECTION_NESTED_GRID("Horizontal_Item_Section_Nested_Grid"),
    CAR_MODE_GRID("Car_Mode_Grid"),
    LARGE_VERTICAL_ITEM_GRID("Large_Vertical_Item_Grid");

    private final String key;

    GridStyleKey(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }
}
