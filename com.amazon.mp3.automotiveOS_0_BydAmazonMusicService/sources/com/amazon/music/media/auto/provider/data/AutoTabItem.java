package com.amazon.music.media.auto.provider.data;

import com.amazon.music.media.auto.ui.WidgetsExpandType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoTabItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\n\u001a\u00020\t\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/media/auto/ui/WidgetsExpandType;", "widgetsExpandType", "Lcom/amazon/music/media/auto/ui/WidgetsExpandType;", "getWidgetsExpandType", "()Lcom/amazon/music/media/auto/ui/WidgetsExpandType;", "", "Lcom/amazon/music/media/auto/provider/data/AutoWidgetItem;", "widgets", "Ljava/util/List;", "getWidgets", "()Ljava/util/List;", "<init>", "(Lcom/amazon/music/media/auto/ui/WidgetsExpandType;Ljava/util/List;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AutoTabItem {
    private final List<AutoWidgetItem> widgets;
    private final WidgetsExpandType widgetsExpandType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoTabItem)) {
            return false;
        }
        AutoTabItem autoTabItem = (AutoTabItem) other;
        return this.widgetsExpandType == autoTabItem.widgetsExpandType && Intrinsics.areEqual(this.widgets, autoTabItem.widgets);
    }

    public int hashCode() {
        return (this.widgetsExpandType.hashCode() * 31) + this.widgets.hashCode();
    }

    public String toString() {
        return "AutoTabItem(widgetsExpandType=" + this.widgetsExpandType + ", widgets=" + this.widgets + ')';
    }

    public AutoTabItem(WidgetsExpandType widgetsExpandType, List<AutoWidgetItem> widgets) {
        Intrinsics.checkNotNullParameter(widgetsExpandType, "widgetsExpandType");
        Intrinsics.checkNotNullParameter(widgets, "widgets");
        this.widgetsExpandType = widgetsExpandType;
        this.widgets = widgets;
    }

    public final WidgetsExpandType getWidgetsExpandType() {
        return this.widgetsExpandType;
    }

    public final List<AutoWidgetItem> getWidgets() {
        return this.widgets;
    }
}
