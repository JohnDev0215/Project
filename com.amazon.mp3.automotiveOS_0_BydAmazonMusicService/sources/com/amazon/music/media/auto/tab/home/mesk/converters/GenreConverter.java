package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenreConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/GenreConverter;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverter;", "()V", "convertEntity", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetItemSyncElement", "LTouch/SyncInterface/v1_0/WidgetItemSyncElement;", "widgetSyncElementPrimaryHeader", "", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenreConverter implements EntityConverter {
    @Override // com.amazon.music.media.auto.tab.home.mesk.converters.EntityConverter
    public AutoMediaItem convertEntity(WidgetItemSyncElement widgetItemSyncElement, String widgetSyncElementPrimaryHeader, ContentPlaybackUtil contentPlaybackUtil) {
        String str;
        Intrinsics.checkNotNullParameter(widgetItemSyncElement, "widgetItemSyncElement");
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        String strEntityId = widgetItemSyncElement.entityId();
        if (strEntityId == null) {
            return null;
        }
        GenreMetadata genreMetadata = new GenreMetadata(widgetItemSyncElement.metricsToken(), contentPlaybackUtil.getGenreUri(), null, null, strEntityId, widgetItemSyncElement.primaryText(), widgetItemSyncElement.image(), null, voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET, null);
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
        return new AutoMediaItem(str, widgetItemSyncElement.secondaryText(), null, genreMetadata, strMetricsToken, 5, null, 0, widgetSyncElementPrimaryHeader, Boolean.FALSE, 196, null);
    }
}
