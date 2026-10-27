package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemContext;
import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import Touch.SyncInterface.v1_0.WidgetSyncElement;
import com.amazon.music.media.auto.AutoBrowserConstants;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.data.AutoWidgetItem;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultWidgetConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J,\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/DefaultWidgetConverter;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/WidgetConverter;", "()V", "convertWidget", "Lcom/amazon/music/media/auto/provider/data/AutoWidgetItem;", "widgetSyncElement", "LTouch/SyncInterface/v1_0/WidgetSyncElement;", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "entityConverterFactory", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverterFactory;", "convertWidgetItemSyncElement", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetItemSyncElement", "LTouch/SyncInterface/v1_0/WidgetItemSyncElement;", "widgetSyncElementPrimaryHeader", "", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultWidgetConverter implements WidgetConverter {
    @Override // com.amazon.music.media.auto.tab.home.mesk.converters.WidgetConverter
    public AutoWidgetItem convertWidget(WidgetSyncElement widgetSyncElement, ContentPlaybackUtil contentPlaybackUtil, EntityConverterFactory entityConverterFactory) {
        Intrinsics.checkNotNullParameter(widgetSyncElement, "widgetSyncElement");
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        Intrinsics.checkNotNullParameter(entityConverterFactory, "entityConverterFactory");
        List<WidgetItemSyncElement> widgetItemSyncElements = widgetSyncElement.items();
        Intrinsics.checkNotNullExpressionValue(widgetItemSyncElements, "widgetItemSyncElements");
        ArrayList arrayList = new ArrayList();
        for (WidgetItemSyncElement widgetItemSyncElement : widgetItemSyncElements) {
            Intrinsics.checkNotNullExpressionValue(widgetItemSyncElement, "widgetItemSyncElement");
            AutoMediaItem autoMediaItemConvertWidgetItemSyncElement = convertWidgetItemSyncElement(widgetItemSyncElement, widgetSyncElement.primaryHeader(), contentPlaybackUtil, entityConverterFactory);
            if (autoMediaItemConvertWidgetItemSyncElement != null) {
                arrayList.add(autoMediaItemConvertWidgetItemSyncElement);
            }
        }
        String strPrimaryHeader = widgetSyncElement.primaryHeader();
        if (strPrimaryHeader == null) {
            strPrimaryHeader = "";
        }
        return new AutoWidgetItem(strPrimaryHeader, ConverterConstants.INSTANCE.getSECONDARY_PRESENTATION_VIEW_TYPE(), Boolean.TRUE, AutoBrowserConstants.INSTANCE.getNUMBER_OF_ENTITIES_PER_WIDGET(), arrayList);
    }

    private final AutoMediaItem convertWidgetItemSyncElement(WidgetItemSyncElement widgetItemSyncElement, String widgetSyncElementPrimaryHeader, ContentPlaybackUtil contentPlaybackUtil, EntityConverterFactory entityConverterFactory) {
        WidgetItemContext widgetItemContext = widgetItemSyncElement.widgetItemContext();
        Intrinsics.checkNotNullExpressionValue(widgetItemContext, "widgetItemSyncElement.widgetItemContext()");
        EntityConverter entityConverter = entityConverterFactory.getEntityConverter(widgetItemContext);
        if (entityConverter == null) {
            return null;
        }
        return entityConverter.convertEntity(widgetItemSyncElement, widgetSyncElementPrimaryHeader, contentPlaybackUtil);
    }
}
