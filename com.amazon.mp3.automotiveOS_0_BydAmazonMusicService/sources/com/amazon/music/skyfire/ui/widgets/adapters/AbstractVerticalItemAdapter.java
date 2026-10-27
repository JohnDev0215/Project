package com.amazon.music.skyfire.ui.widgets.adapters;

import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractVerticalItemAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/AbstractVerticalItemAdapter;", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "()V", "tileWidth", "", "getTileWidth", "()I", "setTileWidth", "(I)V", "addItems", "", "newItems", "", "LTouch/WidgetsInterface/v1_0/VerticalItemElement;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbstractVerticalItemAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private int tileWidth = -1;

    public void addItems(List<? extends VerticalItemElement> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
    }

    public final int getTileWidth() {
        return this.tileWidth;
    }

    public final void setTileWidth(int i) {
        this.tileWidth = i;
    }
}
