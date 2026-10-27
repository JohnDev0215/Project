package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.views.library.metadata.StationMetadata;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StationConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/StationConverter;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverter;", "()V", "convertEntity", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetItemSyncElement", "LTouch/SyncInterface/v1_0/WidgetItemSyncElement;", "widgetSyncElementPrimaryHeader", "", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StationConverter implements EntityConverter {
    @Override // com.amazon.music.media.auto.tab.home.mesk.converters.EntityConverter
    public AutoMediaItem convertEntity(WidgetItemSyncElement widgetItemSyncElement, String widgetSyncElementPrimaryHeader, ContentPlaybackUtil contentPlaybackUtil) {
        String str;
        Intrinsics.checkNotNullParameter(widgetItemSyncElement, "widgetItemSyncElement");
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        String strEntityId = widgetItemSyncElement.entityId();
        if (strEntityId == null) {
            return null;
        }
        String strMetricsToken = widgetItemSyncElement.metricsToken();
        String strPrimaryText = widgetItemSyncElement.primaryText();
        String strImage = widgetItemSyncElement.image();
        HashSet hashSet = new HashSet();
        StationMetadata stationMetadata = new StationMetadata(strMetricsToken, contentPlaybackUtil.getStationUri(strEntityId), null, strEntityId, strPrimaryText, null, null, null, strImage, MapsKt__MapsKt.emptyMap(), hashSet, null, null, null, 14564, null);
        String strMetricsToken2 = widgetItemSyncElement.metricsToken();
        String strPrimaryText2 = widgetItemSyncElement.primaryText();
        if (strPrimaryText2 != null) {
            str = strPrimaryText2;
        } else if (widgetSyncElementPrimaryHeader == null) {
            strPrimaryText2 = "";
            str = strPrimaryText2;
        } else {
            str = widgetSyncElementPrimaryHeader;
        }
        return new AutoMediaItem(str, widgetItemSyncElement.secondaryText(), null, stationMetadata, strMetricsToken2, 3, null, 0, widgetSyncElementPrimaryHeader, Boolean.FALSE, 196, null);
    }
}
