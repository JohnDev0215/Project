package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J0\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/TrackConverter;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverter;", "()V", "convertEntity", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetItemSyncElement", "LTouch/SyncInterface/v1_0/WidgetItemSyncElement;", "widgetSyncElementPrimaryHeader", "", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "metadataList", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "trackIndex", "", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TrackConverter implements EntityConverter {
    @Override // com.amazon.music.media.auto.tab.home.mesk.converters.EntityConverter
    public AutoMediaItem convertEntity(WidgetItemSyncElement widgetItemSyncElement, String widgetSyncElementPrimaryHeader, ContentPlaybackUtil contentPlaybackUtil) {
        String str;
        Intrinsics.checkNotNullParameter(widgetItemSyncElement, "widgetItemSyncElement");
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        String strEntityId = widgetItemSyncElement.entityId();
        if (strEntityId == null) {
            return null;
        }
        TrackMetadata trackMetadata = new TrackMetadata(widgetItemSyncElement.metricsToken(), contentPlaybackUtil.getTrackUri(strEntityId), null, strEntityId, widgetItemSyncElement.primaryText(), widgetItemSyncElement.image(), null, null, null, null, null, null, null, null, null, null, null, null, null, new HashSet(), "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1572924, 15, null);
        String strMetricsToken = widgetItemSyncElement.metricsToken();
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText != null) {
            str = strPrimaryText;
        } else if (widgetSyncElementPrimaryHeader == null) {
            strPrimaryText = "";
            str = strPrimaryText;
        } else {
            str = widgetSyncElementPrimaryHeader;
        }
        return new AutoMediaItem(str, widgetItemSyncElement.secondaryText(), null, trackMetadata, strMetricsToken, 2, null, 0, widgetSyncElementPrimaryHeader, Boolean.FALSE, 196, null);
    }

    public final AutoMediaItem convertEntity(WidgetItemSyncElement widgetItemSyncElement, String widgetSyncElementPrimaryHeader, List<? extends ContentMetadata> metadataList, int trackIndex) {
        String str;
        Intrinsics.checkNotNullParameter(widgetItemSyncElement, "widgetItemSyncElement");
        Intrinsics.checkNotNullParameter(metadataList, "metadataList");
        if (widgetItemSyncElement.entityId() == null) {
            return null;
        }
        String strMetricsToken = widgetItemSyncElement.metricsToken();
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText != null) {
            str = strPrimaryText;
        } else if (widgetSyncElementPrimaryHeader == null) {
            strPrimaryText = "";
            str = strPrimaryText;
        } else {
            str = widgetSyncElementPrimaryHeader;
        }
        return new AutoMediaItem(str, widgetItemSyncElement.secondaryText(), null, metadataList.get(trackIndex), strMetricsToken, 2, metadataList, trackIndex, widgetSyncElementPrimaryHeader, Boolean.FALSE, 4, null);
    }
}
