package spi;

import androidx.annotation.LayoutRes;
import com.android.car.media.main.R$layout;

/* JADX INFO: compiled from: BrowseItemViewType.java */
/* JADX INFO: loaded from: classes.dex */
public enum a0 {
    HEADER(R$layout.media_browse_header_item),
    GRID_ITEM(R$layout.media_browse_grid_item, 1),
    ICON_GRID_ITEM(R$layout.media_browse_grid_icons_item, 1),
    LIST_ITEM(R$layout.media_browse_list_item),
    ICON_LIST_ITEM(R$layout.media_browse_list_icons_item),
    /* JADX INFO: Fake field, exist only in values array */
    SPACER(R$layout.media_browse_spacer);


    @LayoutRes
    public final int a;
    public final int b;

    a0(@LayoutRes int i) {
        this.a = i;
        this.b = -1;
    }

    a0(@LayoutRes int i, int i2) {
        this.a = i;
        this.b = i2;
    }
}
