package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemContext;
import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import Touch.SyncInterface.v1_0.WidgetSyncElement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidgetConverterFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/WidgetConverterFactory;", "", "()V", "areAllEntitiesTracks", "", "widgetSyncElement", "LTouch/SyncInterface/v1_0/WidgetSyncElement;", "getWidgetConverter", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/WidgetConverter;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WidgetConverterFactory {
    public static final WidgetConverterFactory INSTANCE = new WidgetConverterFactory();

    private WidgetConverterFactory() {
    }

    public final WidgetConverter getWidgetConverter(WidgetSyncElement widgetSyncElement) {
        Intrinsics.checkNotNullParameter(widgetSyncElement, "widgetSyncElement");
        if (areAllEntitiesTracks(widgetSyncElement)) {
            return new TracksOnlyWidgetConverter();
        }
        return new DefaultWidgetConverter();
    }

    private final boolean areAllEntitiesTracks(WidgetSyncElement widgetSyncElement) {
        List<WidgetItemSyncElement> listItems = widgetSyncElement.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "widgetSyncElement.items()");
        if (!(listItems instanceof Collection) || !listItems.isEmpty()) {
            Iterator<T> it = listItems.iterator();
            while (it.hasNext()) {
                if (!(((WidgetItemSyncElement) it.next()).widgetItemContext() == WidgetItemContext.ENTITY_TRACK)) {
                    return false;
                }
            }
        }
        return true;
    }
}
