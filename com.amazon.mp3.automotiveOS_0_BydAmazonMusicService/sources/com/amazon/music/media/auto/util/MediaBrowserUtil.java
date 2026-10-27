package com.amazon.music.media.auto.util;

import com.amazon.music.media.auto.AutoBrowserConstants;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.data.AutoTabItem;
import com.amazon.music.media.auto.provider.data.AutoWidgetItem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaBrowserUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/media/auto/util/MediaBrowserUtil;", "", "()V", "createWidgetMapForHomeTab", "", "", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "tabItem", "Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MediaBrowserUtil {
    public static final MediaBrowserUtil INSTANCE = new MediaBrowserUtil();

    private MediaBrowserUtil() {
    }

    public final Map<String, List<AutoMediaItem>> createWidgetMapForHomeTab(AutoTabItem tabItem) {
        Intrinsics.checkNotNullParameter(tabItem, "tabItem");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<AutoWidgetItem> widgets = tabItem.getWidgets();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(widgets, 10));
        for (AutoWidgetItem autoWidgetItem : widgets) {
            List<AutoMediaItem> entities = autoWidgetItem.getEntities();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
            for (AutoMediaItem autoMediaItem : entities) {
                String title = autoWidgetItem.getTitle();
                Object arrayList3 = linkedHashMap.get(title);
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                    linkedHashMap.put(title, arrayList3);
                }
                ArrayList arrayList4 = (ArrayList) arrayList3;
                if (arrayList4.size() < AutoBrowserConstants.INSTANCE.getNUMBER_OF_ENTITIES_PER_WIDGET()) {
                    arrayList4.add(autoMediaItem);
                }
                arrayList2.add(Unit.INSTANCE);
            }
            arrayList.add(arrayList2);
        }
        return linkedHashMap;
    }
}
