package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemContext;
import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import Touch.SyncInterface.v1_0.WidgetSyncElement;
import com.amazon.music.media.auto.AutoBrowserConstants;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.data.AutoWidgetItem;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TracksOnlyWidgetConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/TracksOnlyWidgetConverter;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/WidgetConverter;", "()V", "buildTracksMetadataList", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "widgetSyncElement", "LTouch/SyncInterface/v1_0/WidgetSyncElement;", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "convertWidget", "Lcom/amazon/music/media/auto/provider/data/AutoWidgetItem;", "entityConverterFactory", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverterFactory;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TracksOnlyWidgetConverter implements WidgetConverter {
    @Override // com.amazon.music.media.auto.tab.home.mesk.converters.WidgetConverter
    public AutoWidgetItem convertWidget(WidgetSyncElement widgetSyncElement, ContentPlaybackUtil contentPlaybackUtil, EntityConverterFactory entityConverterFactory) {
        AutoMediaItem autoMediaItemConvertEntity;
        Intrinsics.checkNotNullParameter(widgetSyncElement, "widgetSyncElement");
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        Intrinsics.checkNotNullParameter(entityConverterFactory, "entityConverterFactory");
        ArrayList arrayList = new ArrayList();
        List<ContentMetadata> listBuildTracksMetadataList = buildTracksMetadataList(widgetSyncElement, contentPlaybackUtil);
        int i = 0;
        for (WidgetItemSyncElement entity : widgetSyncElement.items()) {
            int i2 = i + 1;
            WidgetItemContext widgetItemContext = entity.widgetItemContext();
            Intrinsics.checkNotNullExpressionValue(widgetItemContext, "entity.widgetItemContext()");
            TrackConverter trackConverter = (TrackConverter) entityConverterFactory.getEntityConverter(widgetItemContext);
            if (trackConverter == null) {
                autoMediaItemConvertEntity = null;
            } else {
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                autoMediaItemConvertEntity = trackConverter.convertEntity(entity, widgetSyncElement.primaryHeader(), listBuildTracksMetadataList, i);
            }
            if (autoMediaItemConvertEntity != null) {
                arrayList.add(autoMediaItemConvertEntity);
            }
            i = i2;
        }
        String strPrimaryHeader = widgetSyncElement.primaryHeader();
        if (strPrimaryHeader == null) {
            strPrimaryHeader = "";
        }
        return new AutoWidgetItem(strPrimaryHeader, ConverterConstants.INSTANCE.getSECONDARY_PRESENTATION_VIEW_TYPE(), Boolean.TRUE, AutoBrowserConstants.INSTANCE.getNUMBER_OF_ENTITIES_PER_WIDGET(), arrayList);
    }

    private final List<ContentMetadata> buildTracksMetadataList(WidgetSyncElement widgetSyncElement, ContentPlaybackUtil contentPlaybackUtil) {
        List<WidgetItemSyncElement> trackItems = widgetSyncElement.items();
        Intrinsics.checkNotNullExpressionValue(trackItems, "trackItems");
        ArrayList arrayList = new ArrayList();
        for (WidgetItemSyncElement widgetItemSyncElement : trackItems) {
            String strEntityId = widgetItemSyncElement.entityId();
            TrackMetadata trackMetadata = strEntityId == null ? null : new TrackMetadata(widgetItemSyncElement.metricsToken(), contentPlaybackUtil.getTrackUri(strEntityId), null, strEntityId, widgetItemSyncElement.primaryText(), widgetItemSyncElement.image(), null, null, null, null, null, null, null, null, null, null, null, null, null, new HashSet(), "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1572924, 15, null);
            if (trackMetadata != null) {
                arrayList.add(trackMetadata);
            }
        }
        return arrayList;
    }
}
