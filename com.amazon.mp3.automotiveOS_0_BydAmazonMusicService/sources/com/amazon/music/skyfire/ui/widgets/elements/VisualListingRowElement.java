package com.amazon.music.skyfire.ui.widgets.elements;

import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualListingRowElement.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/elements/VisualListingRowElement;", "LTouch/WidgetsInterface/v1_0/WidgetElement;", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/VerticalItemElement;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VisualListingRowElement extends WidgetElement {
    private final List<VerticalItemElement> items;

    /* JADX WARN: Multi-variable type inference failed */
    public VisualListingRowElement(List<? extends VerticalItemElement> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items = items;
    }

    public final List<VerticalItemElement> getItems() {
        return this.items;
    }
}
